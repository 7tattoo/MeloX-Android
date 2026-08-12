package com.lladlam.melox.core.provider.qqmusic

import com.lladlam.melox.core.lyrics.LyricsDocument
import com.lladlam.melox.core.lyrics.QQMusicQrcLyricsParser
import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.core.music.model.MusicTrack
import com.lladlam.melox.core.music.model.ProviderTrackMetadata
import java.io.IOException
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
    suspend fun lyrics(track: MusicTrack): LyricsDocument {
        require(track.id.source == MusicSource.QQMusic)
        val metadata = track.providerMetadata as? ProviderTrackMetadata.QQMusic
        val songMid = metadata?.songMid?.takeIf(String::isNotBlank) ?: track.id.value
        val songId = metadata?.numericSongId?.takeIf { it > 0L }
        val session = sessionProvider()

        // Keep this request shape in lock-step with PlayLyricInfo/GetPlayLyricInfo.
        // The service accepts *either* songId or songMid. Sending songID=0 together
        // with the old songMID spelling makes some tracks resolve as an empty QRC.
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

        val payload = JSONObject()
            .put(
                "comm",
                JSONObject()
                    .put("ct", 11)
                    .put("cv", 13_020_508)
                    .put("v", 13_020_508)
                    .put("uin", session.uin.toLongOrNull() ?: 0L)
                    .put("authst", session.musicKey)
                    .put("format", "json")
                    .put("tmeAppID", "qqmusic"),
            )
            .put(
                "req_0",
                JSONObject()
                    .put("module", "music.musichallSong.PlayLyricInfo")
                    .put("method", "GetPlayLyricInfo")
                    .put("param", lyricParam),
            )

        val request = Request.Builder()
            .url("https://u.y.qq.com/cgi-bin/musicu.fcg")
            .header("User-Agent", "QQMusic 13020508(android 15)")
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
                throw IOException(req.optString("message").ifBlank { "QQ音乐逐字歌词错误码 $code" })
            }
            val data = req.optJSONObject("data") ?: JSONObject()
            val qrc = data.optString("lyric")
            if (qrc.isBlank()) throw IOException("QQ音乐没有返回 QRC")
            val parsed = QQMusicQrcLyricsParser.parseEncrypted(
                qrcHex = qrc,
                translationHex = data.optString("trans"),
                romanizationHex = data.optString("roma"),
            )
            if (parsed.lines.isEmpty()) throw IOException("QQ音乐 QRC 解码后没有有效歌词")
            return parsed
        }
    }

    private companion object {
        val JsonMediaType = "application/json; charset=utf-8".toMediaType()
    }
}
