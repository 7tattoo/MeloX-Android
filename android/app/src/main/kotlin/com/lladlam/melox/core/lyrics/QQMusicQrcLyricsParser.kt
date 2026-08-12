package com.lladlam.melox.core.lyrics

import java.io.ByteArrayInputStream
import java.util.zip.InflaterInputStream
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

/**
 * QQ Music QRC decoder/parser.
 *
 * Current QQ clients return QRC/translation/romanization as hex-encoded,
 * 3DES-encrypted zlib payloads. The decoded QRC line format is roughly:
 * `[lineStart,lineDuration]word(wordStart,wordDuration)...`.
 */
object QQMusicQrcLyricsParser {
    private val tripleDesKey = "!@#)(*$%123ZXC!@!@#)(NHL".toByteArray(Charsets.US_ASCII)
    private val lineTiming = Regex("^\\[(\\d+),(\\d+)](.*)$")
    private val wordTiming = Regex("\\((\\d+),(\\d+)\\)")
    private val lyricContent = Regex("LyricContent=\"(.*?)\"", setOf(RegexOption.DOT_MATCHES_ALL, RegexOption.IGNORE_CASE))
    private const val AnnotationToleranceMs = 900L

    fun decryptHex(value: String): String {
        val normalized = value.trim()
        if (normalized.isBlank() || normalized.length % 2 != 0) return ""
        return runCatching {
            val encrypted = ByteArray(normalized.length / 2) { index ->
                normalized.substring(index * 2, index * 2 + 2).toInt(16).toByte()
            }
            val cipher = Cipher.getInstance("DESede/ECB/NoPadding")
            cipher.init(Cipher.DECRYPT_MODE, SecretKeySpec(tripleDesKey, "DESede"))
            val compressed = cipher.doFinal(encrypted)
            InflaterInputStream(ByteArrayInputStream(compressed)).use { stream ->
                stream.readBytes().toString(Charsets.UTF_8)
            }.trimEnd('\u0000')
        }.getOrDefault("")
    }

    fun parseEncrypted(
        qrcHex: String,
        translationHex: String = "",
        romanizationHex: String = "",
    ): LyricsDocument = parse(
        primary = decryptHex(qrcHex),
        translation = decryptHex(translationHex),
        romanization = decryptHex(romanizationHex),
    )

    fun parse(
        primary: String,
        translation: String = "",
        romanization: String = "",
    ): LyricsDocument {
        val primaryLines = parseQrcLines(extractLyricText(primary))
        if (primaryLines.isEmpty()) {
            return LrcLyricsParser.parse(
                lrc = extractLyricText(primary),
                translation = extractLyricText(translation),
                romanization = extractLyricText(romanization),
            )
        }
        val translated = parseQrcLines(extractLyricText(translation))
            .ifEmpty { NeteaseLyricParser.parseLrc(extractLyricText(translation)) }
        val romanized = parseQrcLines(extractLyricText(romanization))
            .ifEmpty { NeteaseLyricParser.parseLrc(extractLyricText(romanization)) }

        return LyricsDocument(
            primaryLines.map { line ->
                val translationLine = nearest(line, translated)
                val romanizationLine = nearest(line, romanized)
                line.copy(
                    translation = annotationText(line, translationLine),
                    romanization = annotationText(line, romanizationLine),
                    romanizationSyllables = romanizationLine?.syllables.orEmpty(),
                )
            },
        )
    }

    private fun parseQrcLines(source: String): List<LyricLine> = buildList {
        for (raw in source.lineSequence()) {
            val match = lineTiming.find(raw.trim()) ?: continue
            val lineStart = match.groupValues[1].toLongOrNull() ?: continue
            val lineDuration = match.groupValues[2].toLongOrNull()?.coerceAtLeast(1L) ?: continue
            val content = match.groupValues[3]
            val timingMatches = wordTiming.findAll(content).toList()
            if (timingMatches.isEmpty()) {
                val text = content.trim()
                if (text.isNotBlank()) add(LyricLine(lineStart, lineDuration, text))
                continue
            }

            val syllables = buildList {
                var textStart = 0
                for (timing in timingMatches) {
                    val textEnd = timing.range.first
                    if (textEnd < textStart) continue
                    val text = content.substring(textStart, textEnd)
                    textStart = timing.range.last + 1
                    if (text.isEmpty()) continue
                    val rawStart = timing.groupValues[1].toLongOrNull() ?: continue
                    val duration = timing.groupValues[2].toLongOrNull()?.coerceAtLeast(1L) ?: continue
                    // Most QRC payloads use absolute word timestamps. A few older
                    // payloads use a line-relative offset; accept both forms.
                    val start = if (rawStart < lineStart && lineStart > 0L) lineStart + rawStart else rawStart
                    add(
                        LyricSyllable(
                            text = text,
                            startTimeMs = start,
                            endTimeMs = start + duration,
                        ),
                    )
                }
            }
            val text = syllables.joinToString("") { it.text }.trim()
            if (text.isNotBlank()) {
                add(
                    LyricLine(
                        timeMs = lineStart,
                        durationMs = lineDuration,
                        text = text,
                        syllables = syllables,
                    ),
                )
            }
        }
    }.sortedBy(LyricLine::timeMs)

    private fun extractLyricText(source: String): String {
        val value = source.trim().trimEnd('\u0000')
        if (value.isBlank()) return ""
        val match = lyricContent.find(value)
        val content = match?.groupValues?.getOrNull(1) ?: value
        return unescapeXml(content)
            .replace("\\n", "\n")
            .replace("\\r", "\r")
    }

    private fun unescapeXml(value: String): String = value
        .replace("&#10;", "\n")
        .replace("&#13;", "\r")
        .replace("&quot;", "\"")
        .replace("&apos;", "'")
        .replace("&lt;", "<")
        .replace("&gt;", ">")
        .replace("&amp;", "&")

    private fun nearest(target: LyricLine, candidates: List<LyricLine>): LyricLine? {
        val candidate = candidates.minByOrNull { kotlin.math.abs(it.timeMs - target.timeMs) }
            ?: return null
        return candidate.takeIf { kotlin.math.abs(it.timeMs - target.timeMs) <= AnnotationToleranceMs }
    }

    private fun annotationText(target: LyricLine, candidate: LyricLine?): String? {
        val text = candidate?.text?.trim().orEmpty()
        return text.takeIf { it.isNotBlank() && it != target.text.trim() }
    }
}
