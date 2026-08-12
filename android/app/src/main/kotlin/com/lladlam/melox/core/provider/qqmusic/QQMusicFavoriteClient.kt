package com.lladlam.melox.core.provider.qqmusic

import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.core.music.model.MusicTrack
import java.io.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject

internal const val QQ_LIKED_DIRECTORY_ID = 201
internal fun qqFavoriteWriteMethod(favorite: Boolean): String =
    if (favorite) "AddSonglist" else "DelSonglist"

/** Authenticated QQ Music "我喜欢" writer. */
class QQMusicFavoriteClient(
    private val sessionProvider: () -> QQMusicSession,
    private val httpClient: OkHttpClient = OkHttpClient(),
) {
    internal data class SongWriteRef(
        val songId: Long,
        val songType: Int,
    )

    suspend fun setFavorite(track: MusicTrack, favorite: Boolean) = withContext(Dispatchers.IO) {
        require(track.id.source == MusicSource.QQMusic) { "QQMusicFavoriteClient only accepts QQ Music tracks" }
        val session = sessionProvider()
        if (!session.isLoggedIn) throw IOException("请先登录 QQ音乐账号")

        val song = resolveWriteRef(track.id.value, session)
        val param = buildWriteParam(song)
        val data = postMusicu(
            session = session,
            module = "music.musicasset.PlaylistDetailWrite",
            method = qqFavoriteWriteMethod(favorite),
            param = param,
        )
        val retCode = findInt(data, "retCode", "retcode", "code") ?: 0
        if (retCode != 0) {
            throw IOException("QQ音乐${if (favorite) "收藏" else "取消收藏"}失败：$retCode")
        }
    }

    private fun resolveWriteRef(songMid: String, session: QQMusicSession): SongWriteRef {
        val data = postMusicu(
            session = session,
            module = "music.trackInfo.UniformRuleCtrl",
            method = "CgiGetTrackInfo",
            param = JSONObject()
                .put("ctx", 0)
                .put("client", 1)
                .put("types", JSONArray().put(0))
                .put("modify_stamp", JSONArray().put(0))
                .put("mids", JSONArray().put(songMid)),
        )
        return parseWriteRef(data, songMid)
            ?: throw IOException("QQ音乐无法解析歌曲收藏标识")
    }

    internal fun buildWriteParam(song: SongWriteRef): JSONObject = JSONObject()
        .put("dirId", QQ_LIKED_DIRECTORY_ID)
        .put("tid", 0)
        .put("bFmtUtf8", true)
        .put(
            "v_songInfo",
            JSONArray().put(
                JSONObject()
                    .put("songId", song.songId)
                    .put("songType", song.songType),
            ),
        )

    internal fun parseWriteRef(data: JSONObject, expectedMid: String): SongWriteRef? {
        val tracks = findArray(data, "tracks", "track_list", "songlist") ?: return null
        for (index in 0 until tracks.length()) {
            val item = tracks.optJSONObject(index) ?: continue
            val mid = firstString(item, "mid", "songmid", "songMid")
            if (mid.isNotBlank() && mid != expectedMid) continue
            val id = firstLong(item, "id", "songid", "songId")
            if (id <= 0L) continue
            val type = firstInt(item, "type", "songtype", "songType") ?: 0
            return SongWriteRef(id, type)
        }
        return null
    }

    private fun postMusicu(
        session: QQMusicSession,
        module: String,
        method: String,
        param: JSONObject,
    ): JSONObject {
        val gtk = hash33(session.musicKey)
        val payload = JSONObject()
            .put(
                "comm",
                JSONObject()
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
                    .put("need_new_code", 1),
            )
            .put(
                "req_0",
                JSONObject()
                    .put("module", module)
                    .put("method", method)
                    .put("param", param),
            )

        val request = Request.Builder()
            .url("https://u.y.qq.com/cgi-bin/musicu.fcg")
            .header("User-Agent", DesktopUserAgent)
            .header("Referer", "https://y.qq.com/")
            .header("Cookie", session.cookie)
            .post(payload.toString().toRequestBody(JsonMediaType))
            .build()

        httpClient.newCall(request).execute().use { response ->
            val body = response.body.string()
            if (!response.isSuccessful) throw IOException("QQ音乐账号写入请求失败：HTTP ${response.code}")
            if (body.isBlank()) throw IOException("QQ音乐账号写入返回空响应")
            val root = runCatching { JSONObject(body) }
                .getOrElse { throw IOException("QQ音乐账号写入返回无法解析的数据", it) }
            val req = root.optJSONObject("req_0")
                ?: throw IOException("QQ音乐账号写入响应缺少 req_0")
            val code = req.optInt("code", 0)
            if (code != 0) {
                throw IOException(
                    req.optString("message").ifBlank { "QQ音乐账号写入错误码 $code" },
                )
            }
            return req.optJSONObject("data") ?: JSONObject()
        }
    }

    private fun findArray(value: Any?, vararg keys: String): JSONArray? = when (value) {
        is JSONObject -> {
            keys.asSequence().mapNotNull(value::optJSONArray).firstOrNull()
                ?: value.keys().asSequence().mapNotNull { key -> findArray(value.opt(key), *keys) }.firstOrNull()
        }
        is JSONArray -> (0 until value.length()).asSequence().mapNotNull { index -> findArray(value.opt(index), *keys) }.firstOrNull()
        else -> null
    }

    private fun findInt(value: Any?, vararg keys: String): Int? {
        return when (value) {
            is JSONObject -> {
                for (key in keys) {
                    when (val raw = value.opt(key)) {
                        is Number -> return raw.toInt()
                        is String -> raw.toIntOrNull()?.let { return it }
                    }
                }
                value.keys().asSequence().mapNotNull { key -> findInt(value.opt(key), *keys) }.firstOrNull()
            }
            is JSONArray -> (0 until value.length()).asSequence().mapNotNull { index -> findInt(value.opt(index), *keys) }.firstOrNull()
            else -> null
        }
    }

    private fun firstString(value: JSONObject, vararg keys: String): String =
        keys.asSequence().map(value::optString).firstOrNull(String::isNotBlank).orEmpty()

    private fun firstLong(value: JSONObject, vararg keys: String): Long =
        keys.asSequence().mapNotNull { key ->
            when (val raw = value.opt(key)) {
                is Number -> raw.toLong()
                is String -> raw.toLongOrNull()
                else -> null
            }
        }.firstOrNull() ?: -1L

    private fun firstInt(value: JSONObject, vararg keys: String): Int? =
        keys.asSequence().mapNotNull { key ->
            when (val raw = value.opt(key)) {
                is Number -> raw.toInt()
                is String -> raw.toIntOrNull()
                else -> null
            }
        }.firstOrNull()

    private fun hash33(value: String): Long {
        var hash = 5381L
        value.forEach { char ->
            hash += (hash shl 5) + char.code
            hash = hash and 0xFFFF_FFFFL
        }
        return hash and 0x7FFF_FFFFL
    }

    private companion object {
        val JsonMediaType = "application/json; charset=utf-8".toMediaType()
        const val DesktopUserAgent =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
    }
}
