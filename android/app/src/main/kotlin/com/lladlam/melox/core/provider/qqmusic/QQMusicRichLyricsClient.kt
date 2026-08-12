package com.lladlam.melox.core.provider.qqmusic

import com.lladlam.melox.core.lyrics.LyricsDocument
import com.lladlam.melox.core.lyrics.QQMusicQrcLyricsParser
import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.core.music.model.MusicTrack
import com.lladlam.melox.core.music.model.ProviderTrackMetadata
import java.io.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

/** QRC/translation/romanization request path used before the legacy LRC fallback. */
class QQMusicRichLyricsClient(
    private val sessionProvider: () -> QQMusicSession,
    private val httpClient: OkHttpClient = OkHttpClient(),
) {
    suspend fun lyrics(track: MusicTrack): LyricsDocument = withContext(Dispatchers.IO) {
        require(track.id.source == MusicSource.QQMusic)
        val metadata = track.providerMetadata as? ProviderTrackMetadata.QQMusic
        val songMid = metadata?.songMid?.takeIf(String::isNotBlank) ?: track.id.value
        val songId = metadata?.numericSongId?.takeIf { it > 0L }
        val session = sessionProvider()

        // Rich lyrics must not depend on QQ Android's device Session/QIMEI setup.
        // Our login state is a web cookie, so use the web musicu profile here.
        val playLyric = runCatching {
            requestPlayLyricInfo(songMid, songId, session)
        }.getOrNull()

        if (playLyric != null &&
            playLyric.lines.any { it.syllables.isNotEmpty() } &&
            playLyric.lines.any { !it.translation.isNullOrBlank() }
        ) {
            return@withContext playLyric
        }

        // QQ also exposes the PC lyric_download path used by Lyricify. It can
        // return encrypted QRC while translation is already plain LRC, so the
        // shared parser deliberately accepts either encrypted or decoded fields.
        val downloaded = runCatching {
            requestDownloadedLyrics(songMid, songId, session)
        }.getOrNull()

        when {
            downloaded != null && downloaded.lines.any { it.syllables.isNotEmpty() } -> downloaded
            playLyric != null && playLyric.lines.any { it.syllables.isNotEmpty() } -> playLyric
            else -> throw IOException("QQ音乐没有返回可用的逐字歌词")
        }
    }

    private fun requestPlayLyricInfo(
        songMid: String,
        songId: Long?,
        session: QQMusicSession,
    ): LyricsDocument {
        val lyricParam = JSONObject()
            .put("crypt", 1)
            .put("lrc_t", 0)
            .put("qrc", 1)
            .put("qrc_t", 0)
            .put("trans", 1)
            .put("trans_t", 0)
            .put("roma", 1)
            .put("roma_t", 0)
            .put("needSingingAnnotations", false)
            .put("type", 1)
        if (songId != null) lyricParam.put("songId", songId) else lyricParam.put("songMid", songMid)

        val gtk = hash33(session.musicKey)
        val comm = JSONObject()
            .put("ct", 24)
            .put("cv", 4_747_474)
            .put("platform", "yqq.json")
            .put("chid", "0")
            .put("uin", session.uin.toLongOrNull() ?: 0L)
            .put("g_tk", gtk)
            .put("g_tk_new_20200303", gtk)
            .put("format", "json")
            .put("inCharset", "utf-8")
            .put("outCharset", "utf-8")
            .put("notice", 0)
            .put("need_new_code", 1)

        val payload = JSONObject()
            .put("comm", comm)
            .put(
                "req_0",
                JSONObject()
                    .put("module", "music.musichallSong.PlayLyricInfo")
                    .put("method", "GetPlayLyricInfo")
                    .put("param", lyricParam),
            )

        val request = Request.Builder()
            .url("https://u.y.qq.com/cgi-bin/musicu.fcg")
            .header("User-Agent", DesktopUserAgent)
            .header("Referer", "https://y.qq.com/")
            .header("Accept", "application/json, text/plain, */*")
            .apply { if (session.cookie.isNotBlank()) header("Cookie", session.cookie) }
            .post(payload.toString().toRequestBody(JsonMediaType))
            .build()

        httpClient.newCall(request).execute().use { response ->
            val body = response.body.string()
            if (!response.isSuccessful) throw IOException("QQ音乐逐字歌词请求失败：HTTP ${response.code}")
            if (body.isBlank()) throw IOException("QQ音乐逐字歌词返回空响应")
            val root = JSONObject(body)
            val req = root.optJSONObject("req_0") ?: throw IOException("QQ音乐逐字歌词响应缺少 req_0")
            val code = req.optInt("code", 0)
            if (code != 0) {
                throw IOException(
                    req.optString("message")
                        .ifBlank { req.optString("msg") }
                        .ifBlank { "QQ音乐逐字歌词错误码 $code" },
                )
            }
            val data = req.optJSONObject("data") ?: JSONObject()
            val lyric = data.optString("lyric")
            if (lyric.isBlank()) throw IOException("QQ音乐没有返回 QRC")
            val parsed = QQMusicQrcLyricsParser.parseEncrypted(
                qrcHex = lyric,
                translationHex = data.optString("trans"),
                romanizationHex = data.optString("roma"),
            )
            if (parsed.lines.none { it.syllables.isNotEmpty() }) {
                throw IOException("QQ音乐 QRC 解码后没有有效逐字时间轴")
            }
            return parsed
        }
    }

    private fun requestDownloadedLyrics(
        songMid: String,
        knownSongId: Long?,
        session: QQMusicSession,
    ): LyricsDocument {
        val songId = knownSongId ?: resolveNumericSongId(songMid, session)
            ?: throw IOException("QQ音乐无法解析歌曲数字 ID")
        val body = FormBody.Builder()
            .add("version", "15")
            .add("miniversion", "82")
            .add("lrctype", "4")
            .add("musicid", songId.toString())
            .build()
        val request = Request.Builder()
            .url("https://c.y.qq.com/qqmusic/fcgi-bin/lyric_download.fcg")
            .header("User-Agent", DesktopUserAgent)
            .header("Referer", "https://c.y.qq.com/")
            .apply { if (session.cookie.isNotBlank()) header("Cookie", session.cookie) }
            .post(body)
            .build()

        httpClient.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("QQ音乐歌词下载失败：HTTP ${response.code}")
            val xml = response.body.string()
                .replace("<!--", "")
                .replace("-->", "")
            val primary = extractXmlText(xml, "content")
            val translation = extractXmlText(xml, "contentts")
            val romanization = extractXmlText(xml, "contentroma")
            if (primary.isBlank()) throw IOException("QQ音乐歌词下载没有返回原文")
            val parsed = QQMusicQrcLyricsParser.parseEncrypted(
                qrcHex = primary,
                translationHex = translation,
                romanizationHex = romanization,
            )
            if (parsed.lines.none { it.syllables.isNotEmpty() }) {
                throw IOException("QQ音乐下载歌词没有有效逐字时间轴")
            }
            return parsed
        }
    }

    private fun resolveNumericSongId(songMid: String, session: QQMusicSession): Long? {
        val url = okhttp3.HttpUrl.Builder()
            .scheme("https")
            .host("c.y.qq.com")
            .addPathSegments("v8/fcg-bin/fcg_play_single_song.fcg")
            .addQueryParameter("songmid", songMid)
            .addQueryParameter("tpl", "yqq_song_detail")
            .addQueryParameter("format", "json")
            .addQueryParameter("g_tk", "5381")
            .addQueryParameter("loginUin", session.uin.ifBlank { "0" })
            .addQueryParameter("hostUin", "0")
            .addQueryParameter("outCharset", "utf8")
            .addQueryParameter("notice", "0")
            .addQueryParameter("platform", "yqq")
            .addQueryParameter("needNewCode", "0")
            .build()
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", DesktopUserAgent)
            .header("Referer", "https://y.qq.com/")
            .apply { if (session.cookie.isNotBlank()) header("Cookie", session.cookie) }
            .get()
            .build()
        httpClient.newCall(request).execute().use { response ->
            if (!response.isSuccessful) return null
            val root = runCatching { JSONObject(response.body.string()) }.getOrNull() ?: return null
            val data = root.optJSONArray("data") ?: return null
            val song = data.optJSONObject(0) ?: return null
            return song.optLong("songid", 0L).takeIf { it > 0L }
                ?: song.optLong("id", 0L).takeIf { it > 0L }
        }
    }

    private fun extractXmlText(xml: String, tag: String): String {
        val pattern = Regex(
            "<$tag(?:\\s[^>]*)?>(.*?)</$tag>",
            setOf(RegexOption.IGNORE_CASE, RegexOption.DOT_MATCHES_ALL),
        )
        return pattern.find(xml)
            ?.groupValues
            ?.getOrNull(1)
            .orEmpty()
            .trim()
            .removePrefix("<![CDATA[")
            .removeSuffix("]]>")
            .trim()
    }

    private fun hash33(value: String): Int {
        var hash = 5381L
        value.forEach { char -> hash += (hash shl 5) + char.code }
        return (hash and 0x7fffffffL).toInt()
    }

    private companion object {
        val JsonMediaType = "application/json; charset=utf-8".toMediaType()
        const val DesktopUserAgent =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
    }
}
