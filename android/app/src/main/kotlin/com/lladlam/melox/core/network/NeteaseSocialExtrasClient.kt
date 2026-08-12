package com.lladlam.melox.core.network

import com.lladlam.melox.core.model.SearchSong
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import org.json.JSONArray
import org.json.JSONObject

enum class MeloXUserPlayRecordPeriod(val apiValue: Int) {
    Week(1),
    AllTime(0),
}

data class MeloXUserPlayRecord(
    val song: SearchSong,
    val playCount: Int,
    val score: Int?,
)

data class MeloXCommentRepliesPage(
    val ownerComment: MeloXMusicComment?,
    val replies: List<MeloXMusicComment>,
    val totalCount: Int,
    val hasMore: Boolean,
    val nextTime: Long,
)

/** Missing social/account read routes kept separate from the main operations client. */
class NeteaseSocialExtrasClient(
    cookieProvider: () -> String,
    httpClient: OkHttpClient = OkHttpClient(),
) {
    private val eapi = NeteaseAuthenticatedEapi(cookieProvider, httpClient)

    suspend fun userPlayRecords(
        userId: Long,
        period: MeloXUserPlayRecordPeriod,
    ): List<MeloXUserPlayRecord> = withContext(Dispatchers.IO) {
        val response = eapi.post(
            "/api/v1/play/record",
            JSONObject().put("uid", userId).put("type", period.apiValue),
        )
        val values = when (period) {
            MeloXUserPlayRecordPeriod.Week -> response.optJSONArray("weekData")
            MeloXUserPlayRecordPeriod.AllTime -> response.optJSONArray("allData")
        } ?: JSONArray()
        buildList {
            for (index in 0 until values.length()) {
                val value = values.optJSONObject(index) ?: continue
                val song = parseSong(value.optJSONObject("song")) ?: continue
                add(
                    MeloXUserPlayRecord(
                        song = song,
                        playCount = value.optInt("playCount", 0).coerceAtLeast(0),
                        score = value.opt("score")?.let { raw ->
                            when (raw) {
                                is Number -> raw.toInt()
                                is String -> raw.toIntOrNull()
                                else -> null
                            }
                        },
                    ),
                )
            }
        }.sortedByDescending(MeloXUserPlayRecord::playCount)
    }

    suspend fun songCommentReplies(
        songId: Long,
        parentCommentId: Long,
        time: Long = -1L,
        limit: Int = 20,
    ): MeloXCommentRepliesPage = withContext(Dispatchers.IO) {
        val response = eapi.post(
            "/api/resource/comment/floor/get",
            JSONObject()
                .put("parentCommentId", parentCommentId)
                .put("threadId", "R_SO_4_$songId")
                .put("time", time)
                .put("limit", limit.coerceIn(1, 100)),
        )
        val data = response.optJSONObject("data") ?: JSONObject()
        val owner = parseComment(data.optJSONObject("ownerComment"))?.first
        val values = data.optJSONArray("comments") ?: JSONArray()
        var nextTime = time
        val replies = buildList {
            for (index in 0 until values.length()) {
                val parsed = parseComment(values.optJSONObject(index)) ?: continue
                add(parsed.first)
                if (parsed.second > 0L) nextTime = parsed.second
            }
        }
        MeloXCommentRepliesPage(
            ownerComment = owner,
            replies = replies,
            totalCount = data.optInt("totalCount", data.optInt("total", replies.size)).coerceAtLeast(replies.size),
            hasMore = data.optBoolean("hasMore", false) && replies.isNotEmpty(),
            nextTime = nextTime,
        )
    }

    private fun parseComment(value: JSONObject?): Pair<MeloXMusicComment, Long>? {
        value ?: return null
        val id = value.optLong("commentId", -1L)
        if (id <= 0L) return null
        val user = value.optJSONObject("user")
        val time = value.optLong("time", -1L)
        return MeloXMusicComment(
            id = id,
            user = user?.optString("nickname").orEmpty().ifBlank { "网易云用户" },
            avatarUrl = secure(user?.optString("avatarUrl")?.takeIf(String::isNotBlank)),
            content = value.optString("content").ifBlank { "…" },
            likedCount = value.optLong("likedCount", 0L),
            timeText = value.optString("timeStr").ifBlank { "" },
        ) to time
    }

    private fun parseSong(value: JSONObject?): SearchSong? {
        value ?: return null
        val id = value.optLong("id", -1L)
        if (id <= 0L) return null
        val artistsArray = value.optJSONArray("ar") ?: value.optJSONArray("artists") ?: JSONArray()
        val artists = buildList {
            for (index in 0 until artistsArray.length()) {
                artistsArray.optJSONObject(index)?.optString("name")?.takeIf(String::isNotBlank)?.let(::add)
            }
        }.joinToString(" / ")
        val albumObject = value.optJSONObject("al") ?: value.optJSONObject("album")
        val artwork = albumObject?.optString("picUrl")?.takeIf(String::isNotBlank)?.let(::secure)
        val duration = when {
            value.has("dt") -> value.optLong("dt", 0L)
            else -> value.optLong("duration", 0L)
        }
        return SearchSong(
            id = id,
            name = value.optString("name").ifBlank { "未知歌曲" },
            artists = artists.ifBlank { "未知歌手" },
            album = albumObject?.optString("name").orEmpty(),
            artworkUrl = artwork,
            durationMs = duration.coerceAtLeast(0L),
        )
    }

    private fun secure(value: String?): String? = value?.let {
        if (it.startsWith("http://", ignoreCase = true)) "https://${it.substringAfter("://")}" else it
    }
}
