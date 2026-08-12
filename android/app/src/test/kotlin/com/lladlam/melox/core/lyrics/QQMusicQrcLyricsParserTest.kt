package com.lladlam.melox.core.lyrics

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class QQMusicQrcLyricsParserTest {
    @Test
    fun parsesAbsoluteWordTimestamps() {
        val document = QQMusicQrcLyricsParser.parse(
            primary = "[1000,1000]你(1000,300)好(1300,300)呀(1600,400)",
        )
        val line = document.lines.single()
        assertEquals(1000L, line.timeMs)
        assertEquals("你好呀", line.text)
        assertEquals(3, line.syllables.size)
        assertEquals(1000L, line.syllables[0].startTimeMs)
        assertEquals(1300L, line.syllables[1].startTimeMs)
        assertEquals(2000L, line.syllables[2].endTimeMs)
    }

    @Test
    fun acceptsLineRelativeWordOffsets() {
        val document = QQMusicQrcLyricsParser.parse(
            primary = "[5000,900]A(0,300)B(300,300)C(600,300)",
        )
        val line = document.lines.single()
        assertEquals(listOf(5000L, 5300L, 5600L), line.syllables.map { it.startTimeMs })
    }

    @Test
    fun extractsXmlLyricContentAndMergesAnnotations() {
        val primary = "<QrcInfos><LyricInfo><Lyric_1 LyricType=\"1\" LyricContent=\"[1000,800]Hello(1000,800)&#10;[2000,800]World(2000,800)\"/></LyricInfo></QrcInfos>"
        val translation = "[00:01.00]你好\n[00:02.00]世界"
        val romanization = "[00:01.00]hello\n[00:02.00]world"
        val document = QQMusicQrcLyricsParser.parse(primary, translation, romanization)
        assertEquals(2, document.lines.size)
        assertEquals("你好", document.lines[0].translation)
        assertEquals("hello", document.lines[0].romanization)
        assertTrue(document.lines[0].syllables.isNotEmpty())
    }

    @Test
    fun parseEncryptedAlsoAcceptsAlreadyDecodedGatewayPayload() {
        val document = QQMusicQrcLyricsParser.parseEncrypted(
            qrcHex = "[1000,600]A(1000,300)B(1300,300)",
            translationHex = "[00:01.00]甲乙",
            romanizationHex = "[00:01.00]a b",
        )
        assertEquals("AB", document.lines.single().text)
        assertEquals("甲乙", document.lines.single().translation)
        assertEquals("a b", document.lines.single().romanization)
    }

    @Test
    fun plainLrcTranslationCanAlignByLineWhenTimestampsDrift() {
        val document = QQMusicQrcLyricsParser.parseEncrypted(
            qrcHex = "[1000,600]A(1000,300)B(1300,300)\n[3000,600]C(3000,300)D(3300,300)",
            translationHex = "[00:03.00]甲乙\n[00:05.00]丙丁",
        )
        assertEquals(2, document.lines.size)
        assertEquals("甲乙", document.lines[0].translation)
        assertEquals("丙丁", document.lines[1].translation)
        assertTrue(document.lines.all { it.syllables.isNotEmpty() })
    }
}
