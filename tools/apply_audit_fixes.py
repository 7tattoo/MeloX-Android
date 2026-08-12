#!/usr/bin/env python3
from pathlib import Path
import re

ROOT = Path(__file__).resolve().parents[1]

def p(path: str) -> Path:
    return ROOT / path

def read(path: str) -> str:
    return p(path).read_text(encoding="utf-8")

def write(path: str, content: str) -> None:
    target = p(path)
    target.parent.mkdir(parents=True, exist_ok=True)
    target.write_text(content, encoding="utf-8")

def replace_once(path: str, old: str, new: str) -> None:
    text = read(path)
    count = text.count(old)
    if count != 1:
        raise RuntimeError(f"{path}: expected exactly one match, got {count} for:\n{old[:300]}")
    write(path, text.replace(old, new, 1))

def regex_once(path: str, pattern: str, repl: str, flags=0) -> None:
    text = read(path)
    new, count = re.subn(pattern, repl, text, count=1, flags=flags)
    if count != 1:
        raise RuntimeError(f"{path}: expected one regex match, got {count}: {pattern[:200]}")
    write(path, new)

# Shared EAPI transport: authenticated + guest calls, alternate domains/os.
write("android/app/src/main/kotlin/com/lladlam/melox/core/network/NeteaseAuthenticatedEapi.kt", r'''package com.lladlam.melox.core.network

import com.lladlam.melox.core.account.NeteaseSessionStore
import java.io.IOException
import java.net.URLEncoder
import java.security.MessageDigest
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

/** Shared EAPI transport used by feature modules. */
internal class NeteaseAuthenticatedEapi(
    private val cookieProvider: () -> String,
    private val httpClient: OkHttpClient = OkHttpClient(),
) {
    private val syntheticDeviceId = randomHex(26).uppercase()

    fun post(
        uri: String,
        data: JSONObject = JSONObject(),
        authenticated: Boolean = true,
        domain: String = "https://interface.music.163.com",
        cookieOs: String? = null,
    ): JSONObject {
        val cookie = cookieProvider()
        if (authenticated && !NeteaseSessionStore.containsMusicU(cookie)) throw IOException("请先登录网易云音乐")
        val now = System.currentTimeMillis()
        val cookies = NeteaseSessionStore.parseCookie(cookie)
        val header = if (authenticated) authenticatedHeader(cookies, now, cookieOs) else JSONObject()
            .put("os", cookieOs ?: "ios").put("appver", "9.0.90").put("osver", "18.0")
            .put("buildver", (now / 1_000L).toString()).put("channel", "distribution")
            .put("requestId", "${now}_0000").put("__csrf", "")
        val payload = JSONObject(data.toString()).put("header", header).put("e_r", false)
        val json = payload.toString()
        val digest = md5Hex("nobody${uri}use${json}md5forencrypt")
        val encrypted = "$uri-36cd479b6b5-$json-36cd479b6b5-$digest"
        val params = aes(encrypted.toByteArray(), "e82ckenh8dichen8".toByteArray()).toHex()
        val requestBuilder = Request.Builder()
            .url("${domain.trimEnd('/')}${uri.replace("/api/", "/eapi/")}")
            .header("Accept", "*/*")
            .header("User-Agent", if (cookieOs == "osx") "NeteaseMusic 3.0.18 (Macintosh; Intel Mac OS X 14_5)" else if (authenticated) "NeteaseMusic 9.0.90/5038 (iPhone; iOS 16.2; zh_CN)" else "Mozilla/5.0 (iPhone; CPU iPhone OS 18_0 like Mac OS X) AppleWebKit/605.1.15 Mobile/15E148")
        if (authenticated) requestBuilder.header("Cookie", encodedCookie(header))
        val request = requestBuilder.post(FormBody.Builder().add("params", params).build()).build()
        httpClient.newCall(request).execute().use { response ->
            val body = response.body.string()
            if (!response.isSuccessful) throw IOException("网易云请求失败：HTTP ${response.code}")
            if (body.isBlank()) throw IOException("网易云返回了空响应")
            val result = JSONObject(body)
            val code = result.optInt("code", response.code)
            if (code !in 200..299) throw IOException(result.optString("message").ifBlank { result.optString("msg") }.ifBlank { "请求失败（$code）" })
            return result
        }
    }

    private fun authenticatedHeader(cookies: Map<String, String>, now: Long, cookieOs: String?) = JSONObject()
        .put("osver", cookies["osver"] ?: if (cookieOs == "osx") "14.5" else "16.2")
        .put("deviceId", cookies["deviceId"] ?: syntheticDeviceId).put("os", cookieOs ?: cookies["os"] ?: "iPhone OS")
        .put("appver", cookies["appver"] ?: "9.0.90").put("versioncode", cookies["versioncode"] ?: "140")
        .put("buildver", cookies["buildver"] ?: (now / 1000L).toString()).put("resolution", cookies["resolution"] ?: "1170x2532")
        .put("__csrf", cookies["__csrf"] ?: "").put("channel", cookies["channel"] ?: "distribution")
        .put("requestId", "${now}_${randomDigits(4)}").apply { cookies["MUSIC_U"]?.takeIf(String::isNotBlank)?.let { put("MUSIC_U", it) } }
    private fun encodedCookie(value: JSONObject): String = buildList { val keys = value.keys(); while (keys.hasNext()) add(keys.next()) }.sorted().joinToString("; ") { key -> "${encode(key)}=${encode(value.optString(key))}" }
    private fun encode(value: String) = URLEncoder.encode(value, Charsets.UTF_8.name()).replace("+", "%20")
    private fun randomHex(count: Int): String { val bytes = ByteArray(count); SecureRandom().nextBytes(bytes); return bytes.joinToString("") { "%02x".format(it) } }
    private fun randomDigits(count: Int) = buildString(count) { repeat(count) { append(('0'.code + SecureRandom().nextInt(10)).toChar()) } }
    private fun md5Hex(value: String) = MessageDigest.getInstance("MD5").digest(value.toByteArray()).joinToString("") { "%02x".format(it) }
    private fun aes(data: ByteArray, key: ByteArray) = Cipher.getInstance("AES/ECB/PKCS5Padding").run { init(Cipher.ENCRYPT_MODE, SecretKeySpec(key, "AES")); doFinal(data) }
    private fun ByteArray.toHex() = joinToString("") { "%02X".format(it) }
}
''')

# Library models and full liked-song migration.
write("android/app/src/main/kotlin/com/lladlam/melox/core/library/NeteaseLibraryModels.kt", r'''package com.lladlam.melox.core.library

import com.lladlam.melox.core.model.SearchSong

data class NeteasePlaylistSummary(val id: Long, val name: String, val coverUrl: String?, val trackCount: Int, val creatorName: String, val creatorUserId: Long? = null, val playCount: Long = 0L, val description: String? = null)
data class NeteasePlaylistDetail(val summary: NeteasePlaylistSummary, val songs: List<SearchSong>)
data class NeteaseLibrarySnapshot(val playlists: List<NeteasePlaylistSummary>, val likedSongs: List<SearchSong>, val recentSongs: List<SearchSong>, val likedPlaylistId: Long? = null)
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/core/library/NeteaseLibraryClient.kt", '''    private val syntheticDeviceId: String = randomHex(26).uppercase()\n''', '''    private val syntheticDeviceId: String = randomHex(26).uppercase()\n    private val authenticatedWeapi = com.lladlam.melox.core.network.NeteaseAuthenticatedWeapi(cookieProvider, httpClient)\n''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/core/library/NeteaseLibraryClient.kt", '''    suspend fun snapshot(userId: Long): NeteaseLibrarySnapshot = withContext(Dispatchers.IO) {
        ensureLoggedIn()
        val playlists = userPlaylistsBlocking(userId)
        val likedIds = likedSongIdsBlocking(userId)
        val liked = songDetailsBlocking(likedIds.take(100))
        val recent = recentSongsBlocking(100)
        NeteaseLibrarySnapshot(
            playlists = playlists,
            likedSongs = liked,
            recentSongs = recent,
        )
    }
''', '''    suspend fun snapshot(userId: Long): NeteaseLibrarySnapshot = withContext(Dispatchers.IO) {
        ensureLoggedIn()
        val allPlaylists = userPlaylistsBlocking(userId)
        val likedPlaylistId = allPlaylists.firstOrNull()?.id
        val playlists = if (allPlaylists.isEmpty()) emptyList() else allPlaylists.drop(1)
        val likedIds = likedSongIdsBlocking(userId)
        val likedById = likedIds.chunked(100).flatMap(::songDetailsBlocking).associateBy(SearchSong::id)
        val liked = likedIds.mapNotNull(likedById::get)
        val recent = recentSongsBlocking(100)
        NeteaseLibrarySnapshot(playlists = playlists, likedSongs = liked, recentSongs = recent, likedPlaylistId = likedPlaylistId)
    }
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/core/library/NeteaseLibraryClient.kt", '''    fun similarSongsBlocking(songId: Long, limit: Int = 50): List<SearchSong> {
        if (songId <= 0L) return emptyList()
        // Upstream uses /api/v1/discovery/simiSong. The direct EAPI transport is
        // accepted by the same interface host and keeps Android on one client.
        val response = eapi(
            uri = "/api/v1/discovery/simiSong",
            data = JSONObject().put("songid", songId).put("limit", limit.coerceIn(1, 50)),
            authenticated = NeteaseSessionStore.containsMusicU(cookieProvider()),
        )
        val songs = response.optJSONArray("songs") ?: JSONArray()
        return buildList {
            for (index in 0 until songs.length()) parseSong(songs.optJSONObject(index))?.let(::add)
        }
    }
''', '''    fun similarSongsBlocking(songId: Long, limit: Int = 50): List<SearchSong> {
        if (songId <= 0L) return emptyList()
        val data = JSONObject().put("songid", songId).put("limit", limit.coerceIn(1, 50)).put("offset", 0)
        val loggedIn = NeteaseSessionStore.containsMusicU(cookieProvider())
        val response = if (loggedIn) try { authenticatedWeapi.post("/api/v1/discovery/simiSong", data) } catch (error: IOException) {
            if (!error.message.orEmpty().contains("空响应")) throw error
            eapi("/api/v1/discovery/simiSong", data, true)
        } else eapi("/api/v1/discovery/simiSong", data, false)
        val songs = response.optJSONArray("songs") ?: JSONArray()
        return buildList { for (index in 0 until songs.length()) parseSong(songs.optJSONObject(index))?.let(::add) }
    }

    suspend fun topSongs(area: String, limit: Int = 12): List<SearchSong> = withContext(Dispatchers.IO) {
        val data = JSONObject().put("areaId", areaId(area)).put("total", true)
        val response = if (NeteaseSessionStore.containsMusicU(cookieProvider())) try { authenticatedWeapi.post("/api/v1/discovery/new/songs", data) } catch (error: IOException) {
            if (!error.message.orEmpty().contains("空响应")) throw error
            eapi("/api/v1/discovery/new/songs", data, true)
        } else eapi("/api/v1/discovery/new/songs", data, false)
        val values = response.optJSONArray("data") ?: JSONArray()
        buildList { for (index in 0 until minOf(values.length(), limit.coerceIn(1, 100))) parseSong(values.optJSONObject(index))?.let(::add) }
    }

    private fun areaId(area: String): Int = when (area) { "华语", "中国" -> 7; "日本", "日语" -> 8; "韩国", "韩语" -> 16; "欧美" -> 96; else -> 0 }
''')

# Rich home data and cache.
write("android/app/src/main/kotlin/com/lladlam/melox/core/library/NeteaseDiscoveryModels.kt", r'''package com.lladlam.melox.core.library
import com.lladlam.melox.core.model.SearchSong
data class NeteaseHomePodcast(val id: Long, val name: String, val artworkUrl: String?)
data class NeteaseHomeContent(
    val playlists: List<NeteasePlaylistSummary>, val newSongs: List<SearchSong>,
    val radarPlaylists: List<NeteasePlaylistSummary> = emptyList(), val personalPlaylists: List<NeteasePlaylistSummary> = emptyList(),
    val regionalSongs: List<SearchSong> = emptyList(), val roamingSongs: List<SearchSong> = emptyList(),
    val similarSongs: List<SearchSong> = emptyList(), val podcasts: List<NeteaseHomePodcast> = emptyList(),
)
''')
regex_once("android/app/src/main/kotlin/com/lladlam/melox/core/library/NeteaseLibraryClient.kt", r'''    suspend fun homeContent\(limit: Int = 12, area: String = "全部"\): NeteaseHomeContent = withContext\(Dispatchers\.IO\) \{.*?        NeteaseHomeContent\(\n            playlists = parsePlaylists\(playlistsResponse\.optJSONArray\("result"\) \?: JSONArray\(\)\),\n            newSongs = songs,\n        \)\n    \}\n''', r'''    suspend fun homeContent(
        limit: Int = 12, area: String = "全部", userId: Long? = null,
        currentSongId: Long? = null, podcastsEnabled: Boolean = true,
    ): NeteaseHomeContent = withContext(Dispatchers.IO) {
        val authenticated = NeteaseSessionStore.containsMusicU(cookieProvider())
        if (authenticated) runCatching { eapi("/api/homepage/block/page", JSONObject().put("refresh", false), true) }
        val playlistsResponse = eapi("/api/personalized/playlist", JSONObject().put("limit", limit).put("total", true).put("n", 1_000), authenticated)
        val songData = JSONObject().put("type", "recommend").put("limit", limit).put("areaId", areaId(area))
        val songsResponse = if (authenticated) try { authenticatedWeapi.post("/api/personalized/newsong", songData) } catch (error: IOException) {
            if (!error.message.orEmpty().contains("空响应")) throw error
            eapi("/api/personalized/newsong", songData, true)
        } else eapi("/api/personalized/newsong", songData, false)
        val songItems = songsResponse.optJSONArray("result") ?: JSONArray()
        val songs = buildList { for (index in 0 until songItems.length()) { val item = songItems.optJSONObject(index) ?: continue; parseSong(item.optJSONObject("song") ?: item)?.let(::add) } }
        val accountPlaylists = if (authenticated && userId != null) runCatching { userPlaylistsBlocking(userId).drop(1) }.getOrDefault(emptyList()) else emptyList()
        val radar = accountPlaylists.filter { it.name.contains("雷达") }.take(limit)
        val personal = accountPlaylists.filter { it.creatorUserId == userId }.take(limit)
        val regional = runCatching { topSongs(area, limit) }.getOrDefault(emptyList())
        val roaming = if (authenticated) runCatching { personalFm(explore = true, limit = limit) }.getOrDefault(emptyList()) else emptyList()
        val similar = currentSongId?.let { runCatching { similarSongsBlocking(it, limit) }.getOrDefault(emptyList()) }.orEmpty()
        val podcasts = if (podcastsEnabled) runCatching {
            val result = eapi("/api/program/recommend/v1", JSONObject().put("limit", limit.coerceIn(1, 50)).put("offset", 0), authenticated)
            val values = result.optJSONArray("programs") ?: JSONArray()
            buildList {
                for (index in 0 until values.length()) {
                    val program = values.optJSONObject(index) ?: continue; val pid = program.optLong("id", -1L); if (pid <= 0L) continue
                    val radio = program.optJSONObject("radio")
                    add(NeteaseHomePodcast(radio?.optLong("id", 0L)?.takeIf { it > 0L } ?: pid, radio?.optString("name").orEmpty().ifBlank { program.optString("name").ifBlank { "播客" } }, secureUrl(program.optString("coverUrl").takeIf(String::isNotBlank) ?: radio?.optString("picUrl").orEmpty()).takeIf(String::isNotBlank)))
                }
            }.distinctBy(NeteaseHomePodcast::id).take(limit)
        }.getOrDefault(emptyList()) else emptyList()
        NeteaseHomeContent(parsePlaylists(playlistsResponse.optJSONArray("result") ?: JSONArray()), songs, radar, personal, regional, roaming, similar, podcasts)
    }
''', flags=re.S)
replace_once("android/app/src/main/kotlin/com/lladlam/melox/core/library/NeteaseLibraryCache.kt", '''    suspend fun loadHomeContent(): NeteaseHomeContent? = readJson(
        File(directory, "home.json"),
    ) { value ->
        NeteaseHomeContent(
            playlists = decodePlaylists(value.optJSONArray("playlists") ?: JSONArray()),
            newSongs = decodeSongs(value.optJSONArray("newSongs") ?: JSONArray()),
        )
    }

    suspend fun saveHomeContent(content: NeteaseHomeContent) {
        writeJson(
            File(directory, "home.json"),
            JSONObject()
                .put("playlists", encodePlaylists(content.playlists))
                .put("newSongs", encodeSongs(content.newSongs)),
        )
    }
''', '''    suspend fun loadHomeContent(cacheKey: String): NeteaseHomeContent? = readJson(File(directory, "home_${safeCacheKey(cacheKey)}.json")) { value ->
        NeteaseHomeContent(
            playlists = decodePlaylists(value.optJSONArray("playlists") ?: JSONArray()), newSongs = decodeSongs(value.optJSONArray("newSongs") ?: JSONArray()),
            radarPlaylists = decodePlaylists(value.optJSONArray("radarPlaylists") ?: JSONArray()), personalPlaylists = decodePlaylists(value.optJSONArray("personalPlaylists") ?: JSONArray()),
            regionalSongs = decodeSongs(value.optJSONArray("regionalSongs") ?: JSONArray()), roamingSongs = decodeSongs(value.optJSONArray("roamingSongs") ?: JSONArray()),
            similarSongs = decodeSongs(value.optJSONArray("similarSongs") ?: JSONArray()), podcasts = decodeHomePodcasts(value.optJSONArray("podcasts") ?: JSONArray()),
        )
    }
    suspend fun saveHomeContent(cacheKey: String, content: NeteaseHomeContent) {
        writeJson(File(directory, "home_${safeCacheKey(cacheKey)}.json"), JSONObject()
            .put("playlists", encodePlaylists(content.playlists)).put("newSongs", encodeSongs(content.newSongs))
            .put("radarPlaylists", encodePlaylists(content.radarPlaylists)).put("personalPlaylists", encodePlaylists(content.personalPlaylists))
            .put("regionalSongs", encodeSongs(content.regionalSongs)).put("roamingSongs", encodeSongs(content.roamingSongs))
            .put("similarSongs", encodeSongs(content.similarSongs)).put("podcasts", encodeHomePodcasts(content.podcasts)))
    }
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/core/library/NeteaseLibraryCache.kt", '''        private var refreshedHome = false
        private val refreshedExplore = mutableSetOf<String>()
''', '''        private val refreshedHomes = mutableSetOf<String>()
        private val refreshedExplore = mutableSetOf<String>()
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/core/library/NeteaseLibraryCache.kt", '''        @Synchronized
        fun beginHomeColdStartRefresh(): Boolean {
            if (refreshedHome) return false
            refreshedHome = true
            return true
        }
''', '''        @Synchronized
        fun beginHomeColdStartRefresh(cacheKey: String): Boolean = refreshedHomes.add(cacheKey)
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/core/library/NeteaseLibraryCache.kt", '''private fun encodeSnapshot(value: NeteaseLibrarySnapshot) = JSONObject()
    .put("playlists", encodePlaylists(value.playlists))
    .put("likedSongs", encodeSongs(value.likedSongs))
    .put("recentSongs", encodeSongs(value.recentSongs))

private fun decodeSnapshot(value: JSONObject) = NeteaseLibrarySnapshot(
    playlists = decodePlaylists(value.optJSONArray("playlists") ?: JSONArray()),
    likedSongs = decodeSongs(value.optJSONArray("likedSongs") ?: JSONArray()),
    recentSongs = decodeSongs(value.optJSONArray("recentSongs") ?: JSONArray()),
)
''', '''private fun encodeSnapshot(value: NeteaseLibrarySnapshot) = JSONObject().put("playlists", encodePlaylists(value.playlists)).put("likedSongs", encodeSongs(value.likedSongs)).put("recentSongs", encodeSongs(value.recentSongs)).put("likedPlaylistId", value.likedPlaylistId)
private fun decodeSnapshot(value: JSONObject) = NeteaseLibrarySnapshot(decodePlaylists(value.optJSONArray("playlists") ?: JSONArray()), decodeSongs(value.optJSONArray("likedSongs") ?: JSONArray()), decodeSongs(value.optJSONArray("recentSongs") ?: JSONArray()), value.optLong("likedPlaylistId", -1L).takeIf { it > 0L })
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/core/library/NeteaseLibraryCache.kt", '''private fun JSONObject.optNullableString(name: String): String? =
    if (isNull(name)) null else optString(name).takeIf(String::isNotBlank)
''', '''private fun encodeHomePodcasts(values: List<NeteaseHomePodcast>) = JSONArray().apply { values.forEach { put(JSONObject().put("id", it.id).put("name", it.name).put("artworkUrl", it.artworkUrl)) } }
private fun decodeHomePodcasts(values: JSONArray) = buildList { for (index in 0 until values.length()) { val value = values.optJSONObject(index) ?: continue; val id = value.optLong("id", -1L); if (id > 0L) add(NeteaseHomePodcast(id, value.optString("name").ifBlank { "播客" }, value.optNullableString("artworkUrl"))) } }
private fun safeCacheKey(value: String): String = value.hashCode().toUInt().toString(16)
private fun JSONObject.optNullableString(name: String): String? = if (isNull(name)) null else optString(name).takeIf(String::isNotBlank)
''')

# Comments, ranks, and NetEase in-app song sharing.
write("android/app/src/main/kotlin/com/lladlam/melox/core/network/NeteaseSocialExtrasClient.kt", r'''package com.lladlam.melox.core.network

import com.lladlam.melox.core.account.NeteaseSessionStore
import com.lladlam.melox.core.model.SearchSong
import java.io.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import org.json.JSONArray
import org.json.JSONObject

enum class MeloXUserPlayRecordPeriod(val apiValue: Int) { Week(1), AllTime(0) }
data class MeloXUserPlayRecord(val song: SearchSong, val playCount: Int, val score: Int?)
data class MeloXCommentRepliesPage(val ownerComment: MeloXMusicComment?, val replies: List<MeloXMusicComment>, val totalCount: Int, val hasMore: Boolean, val nextTime: Long)
data class MeloXCommentsPage(val hotComments: List<MeloXMusicComment>, val comments: List<MeloXMusicComment>, val totalCount: Int, val hasMore: Boolean, val nextOffset: Int, val beforeTime: Long)

class NeteaseSocialExtrasClient(cookieProvider: () -> String, httpClient: OkHttpClient = OkHttpClient()) {
    private val cookieProvider = cookieProvider
    private val eapi = NeteaseAuthenticatedEapi(cookieProvider, httpClient)
    private val weapi = NeteaseAuthenticatedWeapi(cookieProvider, httpClient)

    suspend fun userPlayRecords(userId: Long, period: MeloXUserPlayRecordPeriod): List<MeloXUserPlayRecord> = withContext(Dispatchers.IO) {
        val response = socialRead("/api/v1/play/record", JSONObject().put("uid", userId).put("type", period.apiValue))
        val values = when (period) { MeloXUserPlayRecordPeriod.Week -> response.optJSONArray("weekData"); MeloXUserPlayRecordPeriod.AllTime -> response.optJSONArray("allData") } ?: JSONArray()
        buildList {
            for (index in 0 until values.length()) { val value = values.optJSONObject(index) ?: continue; val song = parseSong(value.optJSONObject("song")) ?: continue
                add(MeloXUserPlayRecord(song, value.optInt("playCount", 0).coerceAtLeast(0), value.opt("score")?.let { if (it is Number) it.toInt() else it.toString().toIntOrNull() })) }
        }.sortedByDescending(MeloXUserPlayRecord::playCount)
    }

    suspend fun songComments(songId: Long, offset: Int = 0, beforeTime: Long = 0L, limit: Int = 20): MeloXCommentsPage = withContext(Dispatchers.IO) {
        val response = socialRead("/api/v1/resource/comments/R_SO_4_$songId", JSONObject().put("rid", songId).put("limit", limit.coerceIn(1, 100)).put("offset", offset.coerceAtLeast(0)).put("beforeTime", beforeTime), allowGuest = true)
        val hot = if (offset == 0) parseComments(response.optJSONArray("hotComments")) else emptyList(); val comments = parseComments(response.optJSONArray("comments"))
        val total = response.optInt("total", offset + comments.size).coerceAtLeast(offset + comments.size); val more = response.optBoolean("more", offset + comments.size < total) && comments.isNotEmpty()
        val raw = response.optJSONArray("comments"); val lastTime = raw?.takeIf { it.length() > 0 }?.optJSONObject(raw.length() - 1)?.optLong("time", beforeTime) ?: beforeTime
        MeloXCommentsPage(hot, comments, total, more, offset + comments.size, if (offset + comments.size >= 5_000) lastTime else 0L)
    }

    suspend fun songCommentReplies(songId: Long, parentCommentId: Long, time: Long = -1L, limit: Int = 20): MeloXCommentRepliesPage = withContext(Dispatchers.IO) {
        val response = socialRead("/api/resource/comment/floor/get", JSONObject().put("parentCommentId", parentCommentId).put("threadId", "R_SO_4_$songId").put("time", time).put("limit", limit.coerceIn(1, 100)), allowGuest = true)
        val data = response.optJSONObject("data") ?: JSONObject(); val owner = parseComment(data.optJSONObject("ownerComment"))?.first; val values = data.optJSONArray("comments") ?: JSONArray(); var nextTime = time
        val replies = buildList { for (index in 0 until values.length()) { val parsed = parseComment(values.optJSONObject(index)) ?: continue; add(parsed.first); if (parsed.second > 0L) nextTime = parsed.second } }
        MeloXCommentRepliesPage(owner, replies, data.optInt("totalCount", data.optInt("total", replies.size)).coerceAtLeast(replies.size), data.optBoolean("hasMore", false) && replies.isNotEmpty(), nextTime)
    }

    suspend fun sendSongToUser(songId: Long, userId: Long, message: String = "") = withContext(Dispatchers.IO) { eapi.post("/api/msg/private/send", JSONObject().put("id", songId).put("msg", message).put("type", "song").put("userIds", "[$userId]")); Unit }
    suspend fun shareSongToTimeline(songId: Long, message: String = "") = withContext(Dispatchers.IO) { eapi.post("/api/share/friends/resource", JSONObject().put("type", "song").put("msg", message).put("id", songId)); Unit }

    private fun socialRead(path: String, data: JSONObject, allowGuest: Boolean = false): JSONObject {
        val loggedIn = NeteaseSessionStore.containsMusicU(cookieProvider()); if (!loggedIn) { if (!allowGuest) throw IOException("请先登录网易云音乐"); return eapi.post(path, data, false) }
        return try { weapi.post(path, data) } catch (error: IOException) { if (!error.message.orEmpty().contains("空响应")) throw error; eapi.post(path, data) }
    }
    private fun parseComments(values: JSONArray?): List<MeloXMusicComment> = buildList { val source = values ?: JSONArray(); for (index in 0 until source.length()) parseComment(source.optJSONObject(index))?.first?.let(::add) }
    private fun parseComment(value: JSONObject?): Pair<MeloXMusicComment, Long>? { value ?: return null; val id = value.optLong("commentId", -1L); if (id <= 0L) return null; val user = value.optJSONObject("user"); val time = value.optLong("time", -1L)
        return MeloXMusicComment(id, user?.optString("nickname").orEmpty().ifBlank { "网易云用户" }, secure(user?.optString("avatarUrl")?.takeIf(String::isNotBlank)), value.optString("content").ifBlank { "…" }, value.optLong("likedCount", 0L), value.optString("timeStr"), value.optInt("replyCount", value.optJSONArray("beReplied")?.length() ?: 0).coerceAtLeast(0)) to time }
    private fun parseSong(value: JSONObject?): SearchSong? { value ?: return null; val id = value.optLong("id", -1L); if (id <= 0L) return null; val a = value.optJSONArray("ar") ?: value.optJSONArray("artists") ?: JSONArray(); val artists = buildList { for (i in 0 until a.length()) a.optJSONObject(i)?.optString("name")?.takeIf(String::isNotBlank)?.let(::add) }.joinToString(" / "); val album = value.optJSONObject("al") ?: value.optJSONObject("album"); return SearchSong(id, value.optString("name").ifBlank { "未知歌曲" }, artists.ifBlank { "未知歌手" }, album?.optString("name").orEmpty(), secure(album?.optString("picUrl")?.takeIf(String::isNotBlank)), value.optLong("dt", value.optLong("duration", 0L)).coerceAtLeast(0L)) }
    private fun secure(value: String?): String? = value?.let { if (it.startsWith("http://", true)) "https://${it.substringAfter("://")}" else it }
}
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/core/network/NeteaseMusicOperationsClient.kt", '''data class MeloXMusicComment(
    val id: Long,
    val user: String,
    val avatarUrl: String?,
    val content: String,
    val likedCount: Long,
    val timeText: String,
)
''', '''data class MeloXMusicComment(
    val id: Long, val user: String, val avatarUrl: String?, val content: String,
    val likedCount: Long, val timeText: String, val replyCount: Int = 0,
)
''')
regex_once("android/app/src/main/kotlin/com/lladlam/melox/core/network/NeteaseMusicOperationsClient.kt", r'''    suspend fun songComments\(songId: Long, limit: Int = 100\): List<MeloXMusicComment> = withContext\(Dispatchers\.IO\) \{.*?    \}\n\n    suspend fun songWiki''', r'''    suspend fun songComments(songId: Long, limit: Int = 100): List<MeloXMusicComment> = withContext(Dispatchers.IO) {
        val path = "/api/v1/resource/comments/R_SO_4_$songId"; val data = JSONObject().put("rid", songId).put("limit", limit.coerceIn(1, 100)).put("offset", 0).put("beforeTime", 0)
        val loggedIn = NeteaseSessionStore.containsMusicU(cookieProvider()); val result = if (loggedIn) try { authenticatedWeapi.post(path, data) } catch (error: IOException) { if (!error.message.orEmpty().contains("空响应")) throw error; eapi(path, data, true) } else eapi(path, data, false)
        val hot = result.optJSONArray("hotComments") ?: JSONArray(); val normal = result.optJSONArray("comments") ?: JSONArray(); val seen = mutableSetOf<Long>()
        buildList { fun addArray(values: JSONArray) { for (i in 0 until values.length()) { val c = values.optJSONObject(i) ?: continue; val id = c.optLong("commentId", -1L); if (id <= 0L || !seen.add(id)) continue; val user = c.optJSONObject("user"); add(MeloXMusicComment(id, user?.optString("nickname").orEmpty().ifBlank { "网易云用户" }, secure(user?.optString("avatarUrl")?.takeIf(String::isNotBlank)), c.optString("content").ifBlank { "…" }, c.optLong("likedCount", 0L), c.optString("timeStr"), c.optInt("replyCount", c.optJSONArray("beReplied")?.length() ?: 0))) } }; addArray(hot); addArray(normal) }
    }

    suspend fun songWiki''', flags=re.S)
for old, new in [
('''        val response = eapi(
            "/api/user/getfollows/$userId",
            JSONObject().put("offset", 0).put("limit", limit.coerceIn(1, 1_000)).put("order", true),
            true,
        )
''', '''        val path = "/api/user/getfollows/$userId"; val data = JSONObject().put("offset", 0).put("limit", limit.coerceIn(1, 1_000)).put("order", true); val response = socialRead(path, data)
'''),
('''        val response = eapi(
            "/api/msg/private/users",
            JSONObject().put("offset", 0).put("limit", limit.coerceIn(1, 100)).put("total", "true"),
            true,
        )
''', '''        val response = socialRead("/api/msg/private/users", JSONObject().put("offset", 0).put("limit", limit.coerceIn(1, 100)).put("total", "true"))
'''),
('''        val response = eapi(
            "/api/msg/private/history",
            JSONObject().put("userId", userId).put("limit", limit.coerceIn(1, 200)).put("time", -1).put("total", "true"),
            true,
        )
''', '''        val response = socialRead("/api/msg/private/history", JSONObject().put("userId", userId).put("limit", limit.coerceIn(1, 200)).put("time", -1).put("total", "true"))
''')]: replace_once("android/app/src/main/kotlin/com/lladlam/melox/core/network/NeteaseMusicOperationsClient.kt", old, new)
replace_once("android/app/src/main/kotlin/com/lladlam/melox/core/network/NeteaseMusicOperationsClient.kt", '''    private fun parseMessageContacts(values: JSONArray?): List<MeloXMessageContact> = buildList {
''', '''    private fun socialRead(path: String, data: JSONObject): JSONObject = try { authenticatedWeapi.post(path, data) } catch (error: IOException) { if (!error.message.orEmpty().contains("空响应")) throw error; eapi(path, data, true) }

    private fun parseMessageContacts(values: JSONArray?): List<MeloXMessageContact> = buildList {
''')

# Account detail and user search.
write("android/app/src/main/kotlin/com/lladlam/melox/core/network/NeteaseAccountDetailsClient.kt", r'''package com.lladlam.melox.core.network
import com.lladlam.melox.core.account.NeteaseSessionStore
import java.io.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import org.json.JSONObject

data class MeloXAccountDetail(val userId: Long, val nickname: String, val avatarUrl: String?, val backgroundUrl: String?, val signature: String?, val level: Int, val listenSongs: Int, val follows: Int, val followers: Int, val playlistCount: Int)
class NeteaseAccountDetailsClient(cookieProvider: () -> String, httpClient: OkHttpClient = OkHttpClient()) {
    private val cookieProvider = cookieProvider; private val weapi = NeteaseAuthenticatedWeapi(cookieProvider, httpClient); private val eapi = NeteaseAuthenticatedEapi(cookieProvider, httpClient)
    suspend fun userDetail(userId: Long): MeloXAccountDetail = withContext(Dispatchers.IO) {
        if (!NeteaseSessionStore.containsMusicU(cookieProvider())) throw IOException("请先登录网易云音乐")
        val response = try { weapi.post("/api/v1/user/detail/$userId") } catch (error: IOException) { if (!error.message.orEmpty().contains("空响应")) throw error; eapi.post("/api/w/v1/user/detail/$userId", JSONObject().put("all", "true").put("userId", userId)) }
        val profile = response.optJSONObject("profile") ?: throw IOException("网易云没有返回用户资料"); val id = profile.optLong("userId", userId).takeIf { it > 0L } ?: userId
        MeloXAccountDetail(id, profile.optString("nickname").ifBlank { "网易云用户" }, secure(profile.optString("avatarUrl").takeIf(String::isNotBlank)), secure(profile.optString("backgroundUrl").takeIf(String::isNotBlank)), profile.optString("signature").takeIf(String::isNotBlank), response.optInt("level", 0).coerceAtLeast(0), response.optInt("listenSongs", 0).coerceAtLeast(0), profile.optInt("follows", 0).coerceAtLeast(0), profile.optInt("followeds", 0).coerceAtLeast(0), profile.optInt("playlistCount", 0).coerceAtLeast(0))
    }
    private fun secure(value: String?): String? = value?.let { if (it.startsWith("http://", true)) "https://${it.substringAfter("://")}" else it }
}
''')
write("android/app/src/main/kotlin/com/lladlam/melox/ui/account/MeloXAccountActivity.kt", r'''package com.lladlam.melox.ui.account
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.lladlam.melox.core.account.NeteaseSessionStore
import com.lladlam.melox.core.library.NeteaseLibraryClient
import com.lladlam.melox.core.library.NeteasePlaylistSummary
import com.lladlam.melox.core.network.*
import com.lladlam.melox.playback.PlaybackCommands
import com.lladlam.melox.ui.MeloXBottomContentClearance
import com.lladlam.melox.ui.glass.meloXLiquidButton
import com.lladlam.melox.ui.theme.MeloXTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MeloXAccountActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); enableEdgeToEdge(); val userId = intent.getLongExtra(EXTRA_USER_ID, -1L); if (userId <= 0L) { finish(); return }; setContent { MeloXTheme { Screen(userId, ::finish) } } }
    companion object { private const val EXTRA_USER_ID = "user_id"; fun launch(context: Context, userId: Long) { if (userId <= 0L) return; context.startActivity(Intent(context, MeloXAccountActivity::class.java).putExtra(EXTRA_USER_ID, userId).apply { if (context !is Activity) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }) } }
}
@Composable private fun Screen(userId: Long, onBack: () -> Unit) {
    val context = LocalContext.current; val app = context.applicationContext; val cookie = remember(app) { { NeteaseSessionStore.readCookie(app) } }; val details = remember(app) { NeteaseAccountDetailsClient(cookie) }; val social = remember(app) { NeteaseSocialExtrasClient(cookie) }; val library = remember(app) { NeteaseLibraryClient(cookie) }; val scope = rememberCoroutineScope()
    var profile by remember(userId) { mutableStateOf<MeloXAccountDetail?>(null) }; var playlists by remember(userId) { mutableStateOf<List<NeteasePlaylistSummary>>(emptyList()) }; var period by remember(userId) { mutableStateOf(MeloXUserPlayRecordPeriod.Week) }; var records by remember(userId) { mutableStateOf<List<MeloXUserPlayRecord>>(emptyList()) }; var loading by remember(userId) { mutableStateOf(true) }; var error by remember(userId) { mutableStateOf<String?>(null) }
    suspend fun loadRank(requested: MeloXUserPlayRecordPeriod) { period = requested; runCatching { social.userPlayRecords(userId, requested) }.onSuccess { records = it }.onFailure { error = it.message ?: "听歌排行加载失败" } }
    LaunchedEffect(userId) { loading = true; runCatching { details.userDetail(userId) to withContext(Dispatchers.IO) { library.userPlaylistsBlocking(userId) } }.onSuccess { (p, lists) -> profile = p; playlists = if (lists.firstOrNull()?.name?.contains("喜欢") == true) lists.drop(1) else lists; loadRank(MeloXUserPlayRecordPeriod.Week) }.onFailure { error = it.message ?: "用户资料加载失败" }; loading = false }
    BackHandler(onBack = onBack)
    LazyColumn(Modifier.fillMaxSize().statusBarsPadding(), contentPadding = PaddingValues(20.dp, 12.dp, 20.dp, MeloXBottomContentClearance), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        item { Row(verticalAlignment = Alignment.CenterVertically) { Box(Modifier.size(44.dp).meloXLiquidButton(shape = CircleShape).clickable(onClick = onBack), contentAlignment = Alignment.Center) { Text("‹", fontSize = 30.sp) }; Spacer(Modifier.width(12.dp)); Text("用户主页", fontSize = 26.sp, fontWeight = FontWeight.Bold) } }
        profile?.let { v -> item { Column(Modifier.fillMaxWidth().padding(vertical = 10.dp), horizontalAlignment = Alignment.CenterHorizontally) { AsyncImage(v.avatarUrl, null, contentScale = ContentScale.Crop, modifier = Modifier.size(132.dp).clip(CircleShape)); Text(v.nickname, Modifier.padding(top = 14.dp), fontSize = 24.sp, fontWeight = FontWeight.Bold); v.signature?.let { Text(it, Modifier.padding(top = 6.dp), color = MaterialTheme.colorScheme.onSurface.copy(alpha = .58f), maxLines = 3, overflow = TextOverflow.Ellipsis) }; Text("Lv.${v.level} · 累计听歌 ${v.listenSongs} 首", Modifier.padding(top = 7.dp), color = MaterialTheme.colorScheme.onSurface.copy(alpha = .52f)); Row(Modifier.fillMaxWidth().padding(top = 16.dp), horizontalArrangement = Arrangement.SpaceEvenly) { Metric(v.follows, "关注"); Metric(v.followers, "粉丝"); Metric(if (v.playlistCount > 0) v.playlistCount else playlists.size, "歌单") } } } }
        if (loading) item { Box(Modifier.fillMaxWidth().height(150.dp), contentAlignment = Alignment.Center) { CircularProgressIndicator() } }; error?.let { item { Text(it, color = MaterialTheme.colorScheme.error) } }
        if (profile != null) { item { Text("听歌排行", fontSize = 22.sp, fontWeight = FontWeight.Bold) }; item { Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) { RankButton("最近一周", period == MeloXUserPlayRecordPeriod.Week, Modifier.weight(1f)) { scope.launch { loadRank(MeloXUserPlayRecordPeriod.Week) } }; RankButton("所有时间", period == MeloXUserPlayRecordPeriod.AllTime, Modifier.weight(1f)) { scope.launch { loadRank(MeloXUserPlayRecordPeriod.AllTime) } } } }; items(records.take(100), key = { "rank-${it.song.id}" }) { r -> Row(Modifier.fillMaxWidth().clickable { PlaybackCommands.playQueue(context, records.map(MeloXUserPlayRecord::song), r.song.id) }.padding(vertical = 7.dp), verticalAlignment = Alignment.CenterVertically) { AsyncImage(r.song.artworkUrl, null, contentScale = ContentScale.Crop, modifier = Modifier.size(46.dp).clip(RoundedCornerShape(8.dp))); Column(Modifier.weight(1f).padding(start = 10.dp)) { Text(r.song.name, maxLines = 1, overflow = TextOverflow.Ellipsis); Text(r.song.artists, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = .48f), maxLines = 1) }; Text("${r.playCount} 次", fontSize = 12.sp) } }; if (playlists.isNotEmpty()) { item { Text("歌单", fontSize = 22.sp, fontWeight = FontWeight.Bold) }; items(playlists, key = { "pl-${it.id}" }) { pl -> Row(Modifier.fillMaxWidth().clickable { scope.launch { runCatching { library.playlistDetail(pl.id) }.onSuccess { d -> d.songs.firstOrNull()?.let { PlaybackCommands.playQueue(context, d.songs, it.id) } } } }.padding(vertical = 7.dp), verticalAlignment = Alignment.CenterVertically) { AsyncImage(pl.coverUrl, null, contentScale = ContentScale.Crop, modifier = Modifier.size(50.dp).clip(RoundedCornerShape(9.dp))); Column(Modifier.weight(1f).padding(start = 11.dp)) { Text(pl.name, maxLines = 1); Text("${pl.trackCount} 首歌曲", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = .48f)) } } } } }
    }
}
@Composable private fun Metric(value: Int, title: String) = Column(horizontalAlignment = Alignment.CenterHorizontally) { Text(value.toString(), fontWeight = FontWeight.Bold, fontSize = 18.sp); Text(title, fontSize = 12.sp) }
@Composable private fun RankButton(title: String, selected: Boolean, modifier: Modifier, onClick: () -> Unit) = Box(modifier.clip(RoundedCornerShape(16.dp)).background(MaterialTheme.colorScheme.onSurface.copy(alpha = if (selected) .12f else .05f)).clickable(onClick = onClick).padding(vertical = 10.dp), contentAlignment = Alignment.Center) { Text(title, fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium) }
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/core/network/NeteaseUniversalSearchClient.kt", '''    Songs(1, "歌曲"), Albums(10, "专辑"), Artists(100, "歌手"), Playlists(1000, "歌单"), Podcasts(1009, "播客")
''', '''    Songs(1, "歌曲"), Albums(10, "专辑"), Artists(100, "歌手"), Playlists(1000, "歌单"), Podcasts(1009, "播客"), Users(1002, "用户")
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/core/network/NeteaseUniversalSearchClient.kt", '''            MeloXSearchKind.Podcasts -> result.optJSONArray("djRadios") ?: result.optJSONArray("radios")
            else -> null
''', '''            MeloXSearchKind.Podcasts -> result.optJSONArray("djRadios") ?: result.optJSONArray("radios")
            MeloXSearchKind.Users -> result.optJSONArray("userprofiles") ?: result.optJSONArray("userProfiles")
            else -> null
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/core/network/NeteaseUniversalSearchClient.kt", '''                    MeloXSearchKind.Podcasts -> add(MeloXSearchMediaItem(
                        id, kind,
                        value.optString("name").ifBlank { "未命名播客" },
                        value.optJSONObject("dj")?.optString("nickname").orEmpty(),
                        secure(value.optString("picUrl").takeIf(String::isNotBlank)),
                        value.optInt("programCount", 0),
                    ))
                    else -> Unit
''', '''                    MeloXSearchKind.Podcasts -> add(MeloXSearchMediaItem(id, kind, value.optString("name").ifBlank { "未命名播客" }, value.optJSONObject("dj")?.optString("nickname").orEmpty(), secure(value.optString("picUrl").takeIf(String::isNotBlank)), value.optInt("programCount", 0)))
                    MeloXSearchKind.Users -> add(MeloXSearchMediaItem(value.optLong("userId", id), kind, value.optString("nickname").ifBlank { "网易云用户" }, value.optString("signature"), secure(value.optString("avatarUrl").takeIf(String::isNotBlank))))
                    else -> Unit
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/core/network/NeteaseUniversalSearchClient.kt", '''            MeloXSearchKind.Podcasts -> {
''', '''            MeloXSearchKind.Users -> JSONArray()
            MeloXSearchKind.Podcasts -> {
''')

# Full album/artist/podcast detail.
write("android/app/src/main/kotlin/com/lladlam/melox/core/network/NeteaseCollectionDetailsClient.kt", r'''package com.lladlam.melox.core.network
import com.lladlam.melox.core.account.NeteaseSessionStore
import com.lladlam.melox.core.model.SearchSong
import java.io.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import org.json.JSONArray
import org.json.JSONObject

data class MeloXAlbumSummary(val id: Long, val name: String, val artworkUrl: String?, val artistText: String, val type: String?)
data class MeloXAlbumDetail(val album: MeloXAlbumSummary, val description: String?, val songs: List<SearchSong>, val subscribed: Boolean?)
data class MeloXArtistDetail(val id: Long, val name: String, val artworkUrl: String?, val aliases: List<String>, val hotSongs: List<SearchSong>, val albums: List<MeloXAlbumSummary>)
class NeteaseCollectionDetailsClient(cookieProvider: () -> String, httpClient: OkHttpClient = OkHttpClient()) {
    private val cookieProvider = cookieProvider; private val eapi = NeteaseAuthenticatedEapi(cookieProvider, httpClient); private val weapi = NeteaseAuthenticatedWeapi(cookieProvider, httpClient)
    suspend fun albumDetail(id: Long): MeloXAlbumDetail = withContext(Dispatchers.IO) { val logged = NeteaseSessionStore.containsMusicU(cookieProvider()); val r = eapi.post("/api/v1/album/$id", authenticated = logged); val obj = r.optJSONObject("album") ?: throw IOException("网易云没有返回专辑信息"); val album = parseAlbum(obj) ?: throw IOException("无法解析专辑"); val songs = parseSongs(r.optJSONArray("songs")); val sub = if (logged) runCatching { val data = JSONObject().put("id", id); val d = try { weapi.post("/api/album/detail/dynamic", data) } catch (e: IOException) { if (!e.message.orEmpty().contains("空响应")) throw e; eapi.post("/api/album/detail/dynamic", data) }; d.optBoolean("isSub", false) }.getOrNull() else null; MeloXAlbumDetail(album, obj.optString("description").takeIf(String::isNotBlank) ?: obj.optString("briefDesc").takeIf(String::isNotBlank), songs, sub) }
    suspend fun setAlbumSubscribed(id: Long, subscribed: Boolean) = withContext(Dispatchers.IO) { val path = if (subscribed) "/api/album/sub" else "/api/album/unsub"; val data = JSONObject().put("id", id); try { weapi.post(path, data) } catch (e: IOException) { if (!e.message.orEmpty().contains("空响应")) throw e; eapi.post(path, data) }; Unit }
    suspend fun artistDetail(id: Long): MeloXArtistDetail = withContext(Dispatchers.IO) { val logged = NeteaseSessionStore.containsMusicU(cookieProvider()); val detail = eapi.post("/api/v1/artist/$id", authenticated = logged); val a = detail.optJSONObject("artist") ?: throw IOException("网易云没有返回歌手信息"); val ar = eapi.post("/api/artist/albums/$id", JSONObject().put("limit", 100).put("offset", 0).put("total", true), logged); val aliasesJson = a.optJSONArray("alias") ?: JSONArray(); val aliases = buildList { for (i in 0 until aliasesJson.length()) aliasesJson.optString(i).takeIf(String::isNotBlank)?.let(::add) }; val albumsJson = ar.optJSONArray("hotAlbums") ?: JSONArray(); val albums = buildList { for (i in 0 until albumsJson.length()) parseAlbum(albumsJson.optJSONObject(i))?.let(::add) }; MeloXArtistDetail(a.optLong("id", id), a.optString("name").ifBlank { "未知歌手" }, secure(a.optString("picUrl").takeIf(String::isNotBlank) ?: a.optString("img1v1Url").takeIf(String::isNotBlank)), aliases, parseSongs(detail.optJSONArray("hotSongs")), albums) }
    private fun parseSongs(values: JSONArray?): List<SearchSong> = buildList { val s = values ?: JSONArray(); for (i in 0 until s.length()) parseSong(s.optJSONObject(i))?.let(::add) }
    private fun parseAlbum(v: JSONObject?): MeloXAlbumSummary? { v ?: return null; val id = v.optLong("id", -1L); if (id <= 0L) return null; val aa = v.optJSONArray("artists") ?: v.optJSONArray("ar") ?: JSONArray(); val artists = buildList { for (i in 0 until aa.length()) aa.optJSONObject(i)?.optString("name")?.takeIf(String::isNotBlank)?.let(::add) }.joinToString(" / "); return MeloXAlbumSummary(id, v.optString("name").ifBlank { "未命名专辑" }, secure(v.optString("picUrl").takeIf(String::isNotBlank) ?: v.optString("blurPicUrl").takeIf(String::isNotBlank)), artists.ifBlank { v.optJSONObject("artist")?.optString("name").orEmpty().ifBlank { "未知歌手" } }, v.optString("type").takeIf(String::isNotBlank)) }
    private fun parseSong(v: JSONObject?): SearchSong? { v ?: return null; val id = v.optLong("id", -1L); if (id <= 0L) return null; val aa = v.optJSONArray("ar") ?: v.optJSONArray("artists") ?: JSONArray(); val artists = buildList { for (i in 0 until aa.length()) aa.optJSONObject(i)?.optString("name")?.takeIf(String::isNotBlank)?.let(::add) }.joinToString(" / "); val al = v.optJSONObject("al") ?: v.optJSONObject("album"); return SearchSong(id, v.optString("name").ifBlank { "未知歌曲" }, artists.ifBlank { "未知歌手" }, al?.optString("name").orEmpty(), secure(al?.optString("picUrl")?.takeIf(String::isNotBlank) ?: al?.optString("blurPicUrl")?.takeIf(String::isNotBlank)), v.optLong("dt", v.optLong("duration", 0L)).coerceAtLeast(0L)) }
    private fun secure(v: String?): String? = v?.let { if (it.startsWith("http://", true)) "https://${it.substringAfter("://")}" else it }
}
''')
write("android/app/src/main/kotlin/com/lladlam/melox/ui/collection/MeloXCollectionDetailActivity.kt", r'''package com.lladlam.melox.ui.collection
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.lladlam.melox.core.account.NeteaseSessionStore
import com.lladlam.melox.core.audio.MusicQualityPreferences
import com.lladlam.melox.core.download.MeloXDownloadStore
import com.lladlam.melox.core.model.SearchSong
import com.lladlam.melox.core.network.*
import com.lladlam.melox.playback.PlaybackCommands
import com.lladlam.melox.ui.MeloXBottomContentClearance
import com.lladlam.melox.ui.glass.meloXLiquidButton
import com.lladlam.melox.ui.theme.MeloXTheme
import kotlinx.coroutines.launch

class MeloXCollectionDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); enableEdgeToEdge(); val id = intent.getLongExtra(EXTRA_ID, -1L); val kind = runCatching { MeloXSearchKind.valueOf(intent.getStringExtra(EXTRA_KIND).orEmpty()) }.getOrNull(); if (id <= 0L || kind !in setOf(MeloXSearchKind.Albums, MeloXSearchKind.Artists, MeloXSearchKind.Podcasts)) { finish(); return }; setContent { MeloXTheme { when (kind) { MeloXSearchKind.Albums -> AlbumScreen(id, ::finish); MeloXSearchKind.Artists -> ArtistScreen(id, ::finish); MeloXSearchKind.Podcasts -> PodcastScreen(id, ::finish); else -> Unit } } } }
    companion object { private const val EXTRA_ID = "id"; private const val EXTRA_KIND = "kind"; fun launch(context: Context, item: MeloXSearchMediaItem) = launch(context, item.id, item.kind); fun launchAlbum(context: Context, a: MeloXAlbumSummary) = launch(context, a.id, MeloXSearchKind.Albums); fun launchPodcast(context: Context, id: Long) = launch(context, id, MeloXSearchKind.Podcasts); private fun launch(context: Context, id: Long, kind: MeloXSearchKind) { context.startActivity(Intent(context, MeloXCollectionDetailActivity::class.java).putExtra(EXTRA_ID, id).putExtra(EXTRA_KIND, kind.name).apply { if (context !is Activity) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }) } }
}
@Composable private fun Header(title: String, onBack: () -> Unit) = Row(Modifier.fillMaxWidth().height(58.dp), verticalAlignment = Alignment.CenterVertically) { Box(Modifier.size(44.dp).meloXLiquidButton(shape = CircleShape).clickable(onClick = onBack), contentAlignment = Alignment.Center) { Text("‹", fontSize = 30.sp) }; Spacer(Modifier.width(12.dp)); Text(title, fontSize = 24.sp, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis) }
@Composable private fun AlbumScreen(id: Long, onBack: () -> Unit) { val context = LocalContext.current; val app = context.applicationContext; val client = remember(app) { NeteaseCollectionDetailsClient { NeteaseSessionStore.readCookie(app) } }; val downloads = remember(app) { MeloXDownloadStore.get(app) }; val scope = rememberCoroutineScope(); var detail by remember(id) { mutableStateOf<MeloXAlbumDetail?>(null) }; var loading by remember(id) { mutableStateOf(true) }; var error by remember(id) { mutableStateOf<String?>(null) }; var query by remember(id) { mutableStateOf("") }; var subscribed by remember(id) { mutableStateOf<Boolean?>(null) }; LaunchedEffect(id) { runCatching { client.albumDetail(id) }.onSuccess { detail = it; subscribed = it.subscribed }.onFailure { error = it.message ?: "专辑加载失败" }; loading = false }; BackHandler(onBack = onBack); val songs = detail?.songs.orEmpty(); val filtered = remember(songs, query) { val q = query.trim().lowercase(); if (q.isBlank()) songs else songs.filter { it.name.lowercase().contains(q) || it.artists.lowercase().contains(q) } }; LazyColumn(Modifier.fillMaxSize().statusBarsPadding(), contentPadding = PaddingValues(20.dp, 8.dp, 20.dp, MeloXBottomContentClearance)) { item { Header(detail?.album?.name ?: "专辑", onBack) }; detail?.let { v -> item { Column(Modifier.fillMaxWidth().padding(vertical = 12.dp), horizontalAlignment = Alignment.CenterHorizontally) { AsyncImage(v.album.artworkUrl, null, contentScale = ContentScale.Crop, modifier = Modifier.size(210.dp).clip(RoundedCornerShape(16.dp))); Text(v.album.name, Modifier.padding(top = 15.dp), fontSize = 23.sp, fontWeight = FontWeight.Bold, maxLines = 2); Text(v.album.artistText, Modifier.padding(top = 5.dp), color = MaterialTheme.colorScheme.onSurface.copy(alpha = .55f)); Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) { Action("播放") { songs.firstOrNull()?.let { PlaybackCommands.playQueue(context, songs, it.id) } }; Action("随机") { val s = songs.shuffled(); s.firstOrNull()?.let { PlaybackCommands.playQueue(context, s, it.id) } }; subscribed?.let { state -> Action(if (state) "已收藏" else "收藏") { val target = !state; scope.launch { runCatching { client.setAlbumSubscribed(id, target) }.onSuccess { subscribed = target }.onFailure { error = it.message } } } } }; Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) { Action("下载全部") { val q = MusicQualityPreferences.read(app); songs.forEach { downloads.start(it, q) } }; Action("分享") { runCatching { context.startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT, "${v.album.name}\nhttps://music.163.com/album?id=$id"), "分享专辑")) } } }; v.description?.let { Text(it, Modifier.fillMaxWidth().padding(top = 12.dp), color = MaterialTheme.colorScheme.onSurface.copy(alpha = .55f), fontSize = 13.sp) } } }; item { BasicTextField(query, { query = it }, singleLine = true, modifier = Modifier.fillMaxWidth().meloXLiquidButton(shape = RoundedCornerShape(22.dp)).padding(horizontal = 14.dp, vertical = 11.dp), textStyle = androidx.compose.ui.text.TextStyle(color = MaterialTheme.colorScheme.onSurface, fontSize = 16.sp), decorationBox = { inner -> if (query.isBlank()) Text("在专辑中搜索", color = MaterialTheme.colorScheme.onSurface.copy(alpha = .4f)); inner() }) } }; if (loading) item { Box(Modifier.fillMaxWidth().height(150.dp), contentAlignment = Alignment.Center) { CircularProgressIndicator() } }; error?.let { item { Text(it, color = MaterialTheme.colorScheme.error) } }; items(filtered, key = { "album-${it.id}" }) { song -> Track(song, { PlaybackCommands.playQueue(context, songs, song.id) }) { downloads.start(song, MusicQualityPreferences.read(app)) } } } }
@Composable private fun ArtistScreen(id: Long, onBack: () -> Unit) { val context = LocalContext.current; val app = context.applicationContext; val client = remember(app) { NeteaseCollectionDetailsClient { NeteaseSessionStore.readCookie(app) } }; var detail by remember(id) { mutableStateOf<MeloXArtistDetail?>(null) }; var loading by remember(id) { mutableStateOf(true) }; var error by remember(id) { mutableStateOf<String?>(null) }; LaunchedEffect(id) { runCatching { client.artistDetail(id) }.onSuccess { detail = it }.onFailure { error = it.message ?: "歌手加载失败" }; loading = false }; BackHandler(onBack = onBack); val v = detail; LazyColumn(Modifier.fillMaxSize().statusBarsPadding(), contentPadding = PaddingValues(20.dp, 8.dp, 20.dp, MeloXBottomContentClearance)) { item { Header(v?.name ?: "歌手", onBack) }; v?.let { a -> item { Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) { AsyncImage(a.artworkUrl, null, contentScale = ContentScale.Crop, modifier = Modifier.size(176.dp).clip(CircleShape)); Text(a.name, Modifier.padding(top = 12.dp), fontSize = 24.sp, fontWeight = FontWeight.Bold); if (a.aliases.isNotEmpty()) Text(a.aliases.joinToString(" / ")); Action("播放热门歌曲") { a.hotSongs.firstOrNull()?.let { PlaybackCommands.playQueue(context, a.hotSongs, it.id) } } } }; item { Text("热门歌曲", fontSize = 21.sp, fontWeight = FontWeight.Bold) }; items(a.hotSongs.take(50), key = { "artist-song-${it.id}" }) { Track(it, { PlaybackCommands.playQueue(context, a.hotSongs, it.id) }) }; if (a.albums.isNotEmpty()) { item { Text("专辑", fontSize = 21.sp, fontWeight = FontWeight.Bold) }; items(a.albums, key = { "artist-album-${it.id}" }) { al -> Row(Modifier.fillMaxWidth().clickable { MeloXCollectionDetailActivity.launchAlbum(context, al) }.padding(vertical = 7.dp), verticalAlignment = Alignment.CenterVertically) { AsyncImage(al.artworkUrl, null, contentScale = ContentScale.Crop, modifier = Modifier.size(54.dp).clip(RoundedCornerShape(9.dp))); Column(Modifier.weight(1f).padding(start = 11.dp)) { Text(al.name, maxLines = 1); Text(al.type ?: al.artistText, fontSize = 12.sp) }; Text("›", fontSize = 24.sp) } } } }; if (loading) item { CircularProgressIndicator() }; error?.let { item { Text(it, color = MaterialTheme.colorScheme.error) } } } }
@Composable private fun PodcastScreen(id: Long, onBack: () -> Unit) { val context = LocalContext.current; val app = context.applicationContext; val client = remember(app) { NeteaseUniversalSearchClient { NeteaseSessionStore.readCookie(app) } }; val scope = rememberCoroutineScope(); var podcast by remember(id) { mutableStateOf<MeloXPodcast?>(null) }; var programs by remember(id) { mutableStateOf<List<MeloXPodcastProgram>>(emptyList()) }; var loading by remember(id) { mutableStateOf(true) }; var error by remember(id) { mutableStateOf<String?>(null) }; var subscribed by remember(id) { mutableStateOf(false) }; LaunchedEffect(id) { runCatching { client.podcastDetail(id) to client.podcastPrograms(id, limit = 100).values }.onSuccess { (p, list) -> podcast = p; subscribed = p?.subscribed == true; programs = list }.onFailure { error = it.message ?: "播客加载失败" }; loading = false }; BackHandler(onBack = onBack); val playable = programs.mapNotNull(MeloXPodcastProgram::playbackSong); LazyColumn(Modifier.fillMaxSize().statusBarsPadding(), contentPadding = PaddingValues(20.dp, 8.dp, 20.dp, MeloXBottomContentClearance)) { item { Header(podcast?.name ?: "播客", onBack) }; podcast?.let { p -> item { Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) { AsyncImage(p.artworkUrl, null, contentScale = ContentScale.Crop, modifier = Modifier.size(190.dp).clip(RoundedCornerShape(18.dp))); Text(p.name, Modifier.padding(top = 12.dp), fontSize = 23.sp, fontWeight = FontWeight.Bold); Row { Action("播放") { playable.firstOrNull()?.let { PlaybackCommands.playQueue(context, playable, it.id) } }; Action(if (subscribed) "已订阅" else "订阅") { scope.launch { runCatching { client.setPodcastSubscribed(id, !subscribed) }.onSuccess { subscribed = !subscribed }.onFailure { error = it.message } } } }; p.description?.let { Text(it, Modifier.padding(top = 10.dp)) } } } }; if (loading) item { CircularProgressIndicator() }; error?.let { item { Text(it, color = MaterialTheme.colorScheme.error) } }; items(programs, key = { "program-${it.id}" }) { pr -> pr.playbackSong?.let { song -> Track(song, { PlaybackCommands.playQueue(context, playable, song.id) }) } ?: Text(pr.name) } } }
@Composable private fun Track(song: SearchSong, onPlay: () -> Unit, onDownload: (() -> Unit)? = null) = Row(Modifier.fillMaxWidth().clickable(onClick = onPlay).padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) { AsyncImage(song.artworkUrl, null, contentScale = ContentScale.Crop, modifier = Modifier.size(48.dp).clip(RoundedCornerShape(8.dp))); Column(Modifier.weight(1f).padding(start = 10.dp)) { Text(song.name, maxLines = 1); Text(song.artists, fontSize = 12.sp) }; onDownload?.let { Text("↓", Modifier.clickable(onClick = it).padding(10.dp)) } }
@Composable private fun Action(title: String, onClick: () -> Unit) = Box(Modifier.padding(6.dp).meloXLiquidButton(shape = RoundedCornerShape(20.dp)).clickable(onClick = onClick).padding(horizontal = 12.dp, vertical = 8.dp), contentAlignment = Alignment.Center) { Text(title, fontWeight = FontWeight.SemiBold, fontSize = 13.sp) }
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/ui/search/SearchScreen.kt", '''import com.lladlam.melox.ui.settings.MeloXSettingsRuntime
import com.lladlam.melox.ui.podcast.MeloXPodcastScreen
''', '''import com.lladlam.melox.ui.settings.MeloXSettingsRuntime
import com.lladlam.melox.ui.podcast.MeloXPodcastScreen
import com.lladlam.melox.ui.account.MeloXAccountActivity
import com.lladlam.melox.ui.collection.MeloXCollectionDetailActivity
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/ui/search/SearchScreen.kt", '''                else -> SearchMediaResults(media) { selectedMedia = it }
''', '''                else -> SearchMediaResults(media) { item ->
                    when (item.kind) {
                        MeloXSearchKind.Albums, MeloXSearchKind.Artists, MeloXSearchKind.Podcasts -> MeloXCollectionDetailActivity.launch(context, item)
                        MeloXSearchKind.Users -> MeloXAccountActivity.launch(context, item.id)
                        else -> selectedMedia = item
                    }
                }
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/ui/search/SearchScreen.kt", '''AsyncImage(item.artworkUrl, null, contentScale = ContentScale.Crop, modifier = Modifier.size(54.dp).clip(if (item.kind == MeloXSearchKind.Artists) CircleShape else RoundedCornerShape(8.dp)))
''', '''AsyncImage(item.artworkUrl, null, contentScale = ContentScale.Crop, modifier = Modifier.size(54.dp).clip(if (item.kind == MeloXSearchKind.Artists || item.kind == MeloXSearchKind.Users) CircleShape else RoundedCornerShape(8.dp)))
''')

# Playback history upload.
write("android/app/src/main/kotlin/com/lladlam/melox/playback/MeloXPlaybackHistoryReporter.kt", r'''package com.lladlam.melox.playback
import android.content.Context
import android.util.Log
import com.lladlam.melox.core.account.NeteaseSessionStore
import com.lladlam.melox.core.network.NeteaseAuthenticatedEapi
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.json.JSONArray
import org.json.JSONObject
internal class MeloXPlaybackHistoryReporter(context: Context) {
    private val app = context.applicationContext; private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO); private val mutex = Mutex(); private val eapi = NeteaseAuthenticatedEapi { NeteaseSessionStore.readCookie(app) }
    fun recordStart(songId: Long, sourceId: Long = 0L) { if (songId > 0L) submit("startplay", JSONObject().put("id", songId.toString()).put("type", "song").put("mainsite", "1").put("mainsiteWeb", "1").put("content", "id=$sourceId")) }
    fun recordDuration(songId: Long, sourceId: Long = 0L, elapsedMs: Long, durationMs: Long? = null, completed: Boolean = false) { if (songId <= 0L) return; val elapsed = (elapsedMs.coerceAtLeast(0L) / 1000L).toInt(); val duration = durationMs?.takeIf { it > 0L }?.let { (it / 1000L).toInt() }; val seconds = if (completed && duration != null) duration else duration?.let { minOf(elapsed, it) } ?: elapsed; if (seconds <= 0) return; submit("play", JSONObject().put("download", 0).put("end", "playend").put("id", songId.toString()).put("sourceId", sourceId.toString()).put("time", seconds.toString()).put("type", "song").put("wifi", 0).put("source", "list").put("mainsite", "1").put("mainsiteWeb", "1").put("content", "id=$sourceId")) }
    fun close() { scope.cancel() }
    private fun submit(action: String, fields: JSONObject) { if (!NeteaseSessionStore.containsMusicU(NeteaseSessionStore.readCookie(app))) return; scope.launch { mutex.withLock { runCatching { val logs = JSONArray().put(JSONObject().put("action", action).put("json", fields)); eapi.post("/api/feedback/weblog", JSONObject().put("logs", logs.toString()), domain = "https://clientlog.music.163.com", cookieOs = "osx") }.onFailure { Log.w("MeloXHistory", "NetEase playback history upload failed", it) } } } }
}
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/playback/MeloXPlaybackService.kt", '''    private lateinit var equalizerController: MeloXEqualizerController
''', '''    private lateinit var equalizerController: MeloXEqualizerController
    private lateinit var playbackHistoryReporter: MeloXPlaybackHistoryReporter
    private var historySongId: Long? = null
    private var historyPositionMs = 0L
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/playback/MeloXPlaybackService.kt", '''        override fun onPlaybackStateChanged(playbackState: Int) {
            val active = player ?: return
            if (playbackState == Player.STATE_ENDED) {
''', '''        override fun onPlaybackStateChanged(playbackState: Int) {
            val active = player ?: return
            if (playbackState == Player.STATE_ENDED) {
                historySongId?.let { playbackHistoryReporter.recordDuration(it, elapsedMs = historyPositionMs, durationMs = active.duration.takeIf { value -> value != C.TIME_UNSET && value > 0L }, completed = true) }
                historySongId = null; historyPositionMs = 0L
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/playback/MeloXPlaybackService.kt", '''            val transitionedId = mediaItem?.mediaId?.toLongOrNull()
            reactiveAnalysisJob?.cancel()
''', '''            val transitionedId = mediaItem?.mediaId?.toLongOrNull(); val previousHistoryId = historySongId
            if (previousHistoryId != null && previousHistoryId != transitionedId) playbackHistoryReporter.recordDuration(previousHistoryId, elapsedMs = historyPositionMs)
            if (transitionedId != null && transitionedId != previousHistoryId) { historySongId = transitionedId; historyPositionMs = 0L; playbackHistoryReporter.recordStart(transitionedId) }
            reactiveAnalysisJob?.cancel()
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/playback/MeloXPlaybackService.kt", '''            if (active != null) {
                runCatching {
                    applyLocalArtworkMetadata(active)
''', '''            if (active != null) {
                active.currentMediaItem?.mediaId?.toLongOrNull()?.let { current -> if (current == historySongId) historyPositionMs = active.currentPosition.coerceAtLeast(0L) }
                runCatching {
                    applyLocalArtworkMetadata(active)
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/playback/MeloXPlaybackService.kt", '''        downloadStore = MeloXDownloadStore.get(this)
        equalizerController = MeloXEqualizerController(this)
''', '''        downloadStore = MeloXDownloadStore.get(this)
        equalizerController = MeloXEqualizerController(this)
        playbackHistoryReporter = MeloXPlaybackHistoryReporter(this)
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/playback/MeloXPlaybackService.kt", '''    override fun onDestroy() {
        handler.removeCallbacks(modeMonitor)
''', '''    override fun onDestroy() {
        historySongId?.let { playbackHistoryReporter.recordDuration(it, elapsedMs = historyPositionMs) }; historySongId = null; playbackHistoryReporter.close()
        handler.removeCallbacks(modeMonitor)
''')

# Together coordinator/state and UI.
replace_once("android/app/src/main/kotlin/com/lladlam/melox/playback/MeloXListenTogetherCoordinator.kt", '''    fun state(context: Context): StateFlow<State> {
        ensureStarted(context)
        return runtime!!.state
    }
''', '''    fun state(context: Context): StateFlow<State> { ensureStarted(context); return runtime!!.state }
    fun adoptRoom(context: Context, room: MeloXListenTogetherRoom) { ensureStarted(context); runtime!!.adoptRoom(room) }
    fun clearRoom(context: Context) { ensureStarted(context); runtime!!.clearRoom() }
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/playback/MeloXListenTogetherCoordinator.kt", '''        init {
            connectController()
            scope.launch { monitorLoop() }
        }

        private fun connectController() {
''', '''        init { connectController(); scope.launch { monitorLoop() } }
        fun adoptRoom(latest: MeloXListenTogetherRoom) { room = latest; failures = 0; firstSyncForRoom = true; lastRemoteQueueSignature = null; lastRemoteCommandSignature = null; playlistVersion = 1; heartbeatTick = HEARTBEAT_EVERY_TICKS; statusTick = 0; mutableState.value = State(Phase.Connected, latest) }
        fun clearRoom() = resetRoom()

        private fun connectController() {
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/ui/player/MeloXSongActionsOverlay.kt", '''import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
''', '''import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/ui/player/MeloXSongActionsOverlay.kt", '''    Comments,
    CommentReplies,
''', '''    Comments,
    CommentReplies,
    ShareContacts,
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/ui/player/MeloXSongActionsOverlay.kt", '''    var comments by remember(song.id, visible) { mutableStateOf<List<MeloXMusicComment>>(emptyList()) }
    var selectedComment by remember(song.id, visible) { mutableStateOf<MeloXMusicComment?>(null) }
    var repliesPage by remember(song.id, visible) { mutableStateOf<MeloXCommentRepliesPage?>(null) }
''', '''    var hotComments by remember(song.id, visible) { mutableStateOf<List<MeloXMusicComment>>(emptyList()) }
    var comments by remember(song.id, visible) { mutableStateOf<List<MeloXMusicComment>>(emptyList()) }
    var commentsPage by remember(song.id, visible) { mutableStateOf<com.lladlam.melox.core.network.MeloXCommentsPage?>(null) }
    var selectedComment by remember(song.id, visible) { mutableStateOf<MeloXMusicComment?>(null) }
    var repliesPage by remember(song.id, visible) { mutableStateOf<MeloXCommentRepliesPage?>(null) }
    var shareContacts by remember(song.id, visible) { mutableStateOf<List<com.lladlam.melox.core.network.MeloXMessageContact>>(emptyList()) }
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/ui/player/MeloXSongActionsOverlay.kt", '''    var listenRoom by remember(visible) { mutableStateOf<MeloXListenTogetherRoom?>(null) }
    var invitationText by remember(visible) { mutableStateOf("") }
''', '''    val togetherState by MeloXListenTogetherCoordinator.state(app).collectAsState()
    val listenRoom = togetherState.room
    var invitationText by remember(visible) { mutableStateOf("") }
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/ui/player/MeloXSongActionsOverlay.kt", '''    LaunchedEffect(visible, playbackState != null) {
        if (!visible) return@LaunchedEffect
        if (playbackState != null) MeloXListenTogetherCoordinator.ensureStarted(app)
        runCatching { ops.listenTogetherRoomStatus() }.onSuccess { listenRoom = it }
    }
''', '''    LaunchedEffect(visible, playbackState != null) { if (!visible) return@LaunchedEffect; if (playbackState != null) MeloXListenTogetherCoordinator.ensureStarted(app) }
''')
regex_once("android/app/src/main/kotlin/com/lladlam/melox/ui/player/MeloXSongActionsOverlay.kt", r'''    suspend fun loadComments\(\) \{.*?    \}\n\n    suspend fun loadReplies''', r'''    suspend fun loadComments(append: Boolean = false) { busy = true; message = null; val current = commentsPage; val offset = if (append) current?.nextOffset ?: comments.size else 0; val before = if (append) current?.beforeTime ?: 0L else 0L; runCatching { social.songComments(song.id, offset, before) }.onSuccess { loaded -> if (append) comments = (comments + loaded.comments).distinctBy(MeloXMusicComment::id) else { hotComments = loaded.hotComments; comments = loaded.comments }; commentsPage = loaded.copy(comments = comments, nextOffset = comments.size) }.onFailure { message = it.message ?: "评论加载失败" }; busy = false }
    suspend fun loadShareContacts() { busy = true; message = null; runCatching { val profile = account.accountProfile(); ops.messageContacts(profile.userId) }.onSuccess { shareContacts = it }.onFailure { message = it.message ?: "联系人加载失败" }; busy = false }

    suspend fun loadReplies''', flags=re.S)
replace_once("android/app/src/main/kotlin/com/lladlam/melox/ui/player/MeloXSongActionsOverlay.kt", '''                                SongActionPage.CommentReplies -> "评论回复"
                                SongActionPage.ListeningRank -> "我的听歌排行"
''', '''                                SongActionPage.CommentReplies -> "评论回复"
                                SongActionPage.ShareContacts -> "发送给网易云好友"
                                SongActionPage.ListeningRank -> "我的听歌排行"
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/ui/player/MeloXSongActionsOverlay.kt", '''                                ActionItem("分享", "↗") { shareSong(context, song); onDismiss() }
                                ActionItem("查看评论", "◌") {
                                    page = SongActionPage.Comments
                                    scope.launch { loadComments() }
                                }
''', '''                                ActionItem("系统分享", "↗") { shareSong(context, song); onDismiss() }
                                ActionItem("发送给网易云好友", "✉") { page = SongActionPage.ShareContacts; scope.launch { loadShareContacts() } }
                                ActionItem("分享到网易云动态", "◎") { if (!busy) { busy = true; scope.launch { runCatching { social.shareSongToTimeline(song.id) }.onSuccess { message = "已分享到网易云动态" }.onFailure { message = it.message ?: "动态分享失败" }; busy = false } } }
                                ActionItem("查看评论", "◌") { page = SongActionPage.Comments; scope.launch { loadComments(false) } }
''')
regex_once("android/app/src/main/kotlin/com/lladlam/melox/ui/player/MeloXSongActionsOverlay.kt", r'''                            SongActionPage\.Comments -> \{.*?                            SongActionPage\.CommentReplies -> \{''', r'''                            SongActionPage.Comments -> {
                                if (busy && comments.isEmpty()) LoadingRow("正在读取评论")
                                if (hotComments.isNotEmpty()) Text("热门评论", color = Color.White.copy(alpha = .55f), fontSize = 12.sp)
                                LazyColumn(Modifier.fillMaxWidth().height(360.dp)) {
                                    items(hotComments, key = { "hot-${it.id}" }) { c -> Column(Modifier.fillMaxWidth().clickable { selectedComment = c; repliesPage = null; page = SongActionPage.CommentReplies; scope.launch { loadReplies(c, false) } }.padding(vertical = 9.dp)) { CommentRow(c) } }
                                    if (comments.isNotEmpty()) item { Text("最新评论 · ${commentsPage?.totalCount ?: comments.size}", color = Color.White.copy(alpha = .55f), fontSize = 12.sp) }
                                    items(comments, key = { "latest-${it.id}" }) { c -> Column(Modifier.fillMaxWidth().clickable { selectedComment = c; repliesPage = null; page = SongActionPage.CommentReplies; scope.launch { loadReplies(c, false) } }.padding(vertical = 9.dp)) { CommentRow(c) } }
                                }
                                if (commentsPage?.hasMore == true) ActionItem("加载更多评论", "+") { if (!busy) scope.launch { loadComments(true) } }
                                ActionItem("返回", "‹") { page = SongActionPage.Main }
                            }
                            SongActionPage.ShareContacts -> {
                                if (busy && shareContacts.isEmpty()) LoadingRow("正在读取联系人")
                                LazyColumn(Modifier.fillMaxWidth().height(350.dp)) { items(shareContacts, key = { "share-${it.id}" }) { contact -> Row(Modifier.fillMaxWidth().clickable(enabled = !busy) { busy = true; scope.launch { runCatching { social.sendSongToUser(song.id, contact.id) }.onSuccess { message = "已发送给 ${contact.name}"; page = SongActionPage.Main }.onFailure { message = it.message ?: "发送失败" }; busy = false } }.padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) { AsyncImage(contact.avatarUrl, null, contentScale = ContentScale.Crop, modifier = Modifier.size(44.dp)); Column(Modifier.weight(1f).padding(start = 10.dp)) { Text(contact.name, color = Color.White); if (contact.signature.isNotBlank()) Text(contact.signature, color = Color.White.copy(alpha = .45f), fontSize = 11.sp, maxLines = 1) } } } }
                                ActionItem("返回", "‹") { page = SongActionPage.Main }
                            }
                            SongActionPage.CommentReplies -> {''', flags=re.S)
replace_once("android/app/src/main/kotlin/com/lladlam/melox/ui/player/MeloXSongActionsOverlay.kt", '''                                                }.onSuccess {
                                                    listenRoom = it
                                                    MeloXListenTogetherCoordinator.ensureStarted(app)
                                                }.onFailure { message = it.message ?: "创建房间失败" }
''', '''                                                }.onSuccess { MeloXListenTogetherCoordinator.adoptRoom(app, it) }.onFailure { message = it.message ?: "创建房间失败" }
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/ui/player/MeloXSongActionsOverlay.kt", '''                                                    .onSuccess {
                                                        listenRoom = it
                                                        MeloXListenTogetherCoordinator.ensureStarted(app)
                                                    }
''', '''                                                    .onSuccess { MeloXListenTogetherCoordinator.adoptRoom(app, it) }
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/ui/player/MeloXSongActionsOverlay.kt", '''                                                    .onSuccess { listenRoom = null }
''', '''                                                    .onSuccess { MeloXListenTogetherCoordinator.clearRoom(app) }
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/ui/player/MeloXSongActionsOverlay.kt", '''                                        "后台自动同步已启用 · 1 秒状态同步 · 5 秒心跳",
''', '''                                        when (togetherState.phase) { MeloXListenTogetherCoordinator.Phase.Reconnecting -> "正在重新连接 · ${togetherState.consecutiveFailures} 次失败"; MeloXListenTogetherCoordinator.Phase.Connected -> "后台自动同步已启用 · 1 秒状态同步 · 5 秒心跳"; MeloXListenTogetherCoordinator.Phase.Idle -> "正在恢复房间状态" },
''')

# Global Together clipboard invite.
write("android/app/src/main/kotlin/com/lladlam/melox/ui/player/MeloXListenTogetherInviteActivity.kt", r'''package com.lladlam.melox.ui.player
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.lladlam.melox.core.account.NeteaseSessionStore
import com.lladlam.melox.core.network.NeteaseMusicOperationsClient
import com.lladlam.melox.playback.MeloXListenTogetherCoordinator
import com.lladlam.melox.ui.theme.MeloXTheme
import kotlinx.coroutines.launch
class MeloXListenTogetherInviteActivity : ComponentActivity() {
 override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); enableEdgeToEdge(); val room = intent.getStringExtra("room").orEmpty(); val inviter = intent.getStringExtra("inviter").orEmpty(); if (room.isBlank() || inviter.isBlank()) { finish(); return }; setContent { MeloXTheme { val context = LocalContext.current; val scope = rememberCoroutineScope(); var busy by remember { mutableStateOf(false) }; var error by remember { mutableStateOf<String?>(null) }; AlertDialog(onDismissRequest = { if (!busy) finish() }, title = { Text("发现一起听邀请") }, text = { if (busy) CircularProgressIndicator() else Text(error ?: "房间 $room\n是否加入？") }, dismissButton = { TextButton(onClick = ::finish, enabled = !busy) { Text("取消") } }, confirmButton = { TextButton(enabled = !busy, onClick = { busy = true; scope.launch { val ops = NeteaseMusicOperationsClient { NeteaseSessionStore.readCookie(context.applicationContext) }; runCatching { ops.joinListenTogetherRoom(room, inviter) }.onSuccess { MeloXListenTogetherCoordinator.adoptRoom(context.applicationContext, it); finish() }.onFailure { error = it.message ?: "加入房间失败" }; busy = false } }) { Text("加入") } }) } } }
 companion object { fun launch(context: Context, room: String, inviter: String) { context.startActivity(Intent(context, MeloXListenTogetherInviteActivity::class.java).putExtra("room", room).putExtra("inviter", inviter).apply { if (context !is Activity) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }) } }
}
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/MainActivity.kt", '''import com.lladlam.melox.playback.MeloXListenTogetherCoordinator
import com.lladlam.melox.ui.MeloXApp
''', '''import com.lladlam.melox.playback.MeloXListenTogetherCoordinator
import com.lladlam.melox.core.network.parseNeteaseListenTogetherInvitation
import com.lladlam.melox.ui.player.MeloXListenTogetherInviteActivity
import com.lladlam.melox.ui.MeloXApp
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/MainActivity.kt", '''        if (text.isNotBlank() && text != lastClipboardText) {
            lastClipboardText = text
            clipboardLinkRequest = text
        }
''', '''        if (text.isNotBlank() && text != lastClipboardText) {
            lastClipboardText = text
            val together = parseNeteaseListenTogetherInvitation(text)
            if (together != null) { MeloXListenTogetherInviteActivity.launch(this, together.roomId, together.inviterId); return }
            clipboardLinkRequest = text
        }
''')
replace_once("android/app/src/main/AndroidManifest.xml", '''        <provider
''', '''        <activity android:name=".ui.account.MeloXAccountActivity" android:exported="false" />
        <activity android:name=".ui.collection.MeloXCollectionDetailActivity" android:exported="false" />
        <activity android:name=".ui.player.MeloXListenTogetherInviteActivity" android:exported="false" />

        <provider
''')

# Home UI and library heart mode.
replace_once("android/app/src/main/kotlin/com/lladlam/melox/ui/discovery/MeloXDiscoveryScreens.kt", '''import com.lladlam.melox.core.account.NeteaseSessionStore
import com.lladlam.melox.core.account.rememberNeteaseSessionStore
''', '''import com.lladlam.melox.core.account.NeteaseSessionStore
import com.lladlam.melox.core.account.NeteaseAccountProfile
import com.lladlam.melox.core.account.rememberNeteaseSessionStore
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/ui/discovery/MeloXDiscoveryScreens.kt", '''import com.lladlam.melox.ui.podcast.MeloXPodcastScreen
''', '''import com.lladlam.melox.ui.podcast.MeloXPodcastScreen
import com.lladlam.melox.ui.account.MeloXAccountActivity
import com.lladlam.melox.ui.collection.MeloXCollectionDetailActivity
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/ui/discovery/MeloXDiscoveryScreens.kt", '''    var activeAction by remember { mutableStateOf<String?>(null) }
''', '''    var activeAction by remember { mutableStateOf<String?>(null) }
    val homeCacheKey = "${session.cookie.hashCode()}_${MeloXSettingsRuntime.musicArea}_${MeloXSettingsRuntime.podcastsEnabled}"
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/ui/discovery/MeloXDiscoveryScreens.kt", '''            runCatching { client.homeContent(area = MeloXSettingsRuntime.musicArea) }
                .onSuccess { content = it; cache.saveHomeContent(it); error = null }
''', '''            runCatching { if (session.isLoggedIn && session.profile == null) session.refreshProfile(force = true); client.homeContent(area = MeloXSettingsRuntime.musicArea, userId = session.profile?.userId, currentSongId = PlaybackCommands.currentSongId(), podcastsEnabled = MeloXSettingsRuntime.podcastsEnabled) }
                .onSuccess { content = it; cache.saveHomeContent(homeCacheKey, it); error = null }
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/ui/discovery/MeloXDiscoveryScreens.kt", '''    LaunchedEffect(Unit) {
        content = cache.loadHomeContent()
        if (session.isLoggedIn) session.refreshProfile()
        if (NeteaseLibraryCache.beginHomeColdStartRefresh()) refresh()
    }
''', '''    LaunchedEffect(homeCacheKey) { content = cache.loadHomeContent(homeCacheKey); if (session.isLoggedIn) session.refreshProfile(); if (NeteaseLibraryCache.beginHomeColdStartRefresh(homeCacheKey)) refresh() }
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/ui/discovery/MeloXDiscoveryScreens.kt", '''                item { LargeTitle("首页") }
''', '''                item { LargeTitle("首页") }
                session.profile?.let { profile -> item { HomeAccountCard(profile) { MeloXAccountActivity.launch(context, profile.userId) } } }
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/ui/discovery/MeloXDiscoveryScreens.kt", '''                                            "私人漫游" -> client.personalFm(explore = true)
                                            "相似歌曲" -> PlaybackCommands.currentSongId()?.let { client.similarSongsBlocking(it) }
''', '''                                            "私人漫游" -> client.personalFm(explore = true)
                                            "私人雷达" -> { val uid = session.profile?.userId ?: throw IllegalStateException("请先登录网易云音乐"); val s = client.snapshot(uid); val radar = s.playlists.firstOrNull { it.name.contains("雷达") } ?: throw IllegalStateException("当前账号没有可用的私人雷达"); client.playlistDetail(radar.id).songs }
                                            "相似歌曲" -> PlaybackCommands.currentSongId()?.let { client.similarSongsBlocking(it) }
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/ui/discovery/MeloXDiscoveryScreens.kt", '''                                                val playlist = snapshot.playlists.firstOrNull() ?: throw IllegalStateException("没有可用歌单")
                                                client.intelligenceModeSongs(seed.id, playlist.id)
''', '''                                                val playlistId = snapshot.likedPlaylistId ?: throw IllegalStateException("没有找到“我喜欢的音乐”歌单")
                                                client.intelligenceModeSongs(seed.id, playlistId)
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/ui/discovery/MeloXDiscoveryScreens.kt", '''                error?.let { message -> item { Text(message, color = MaterialTheme.colorScheme.error, fontSize = 13.sp) } }
''', '''                if (value.radarPlaylists.isNotEmpty()) { item { SectionTitle("私人雷达", "你的雷达歌单") }; item { PlaylistRow(value.radarPlaylists) { selectedPlaylist = it } } }
                if (value.personalPlaylists.isNotEmpty()) { item { SectionTitle("我的歌单", "为你保留") }; item { PlaylistRow(value.personalPlaylists) { selectedPlaylist = it } } }
                if (value.regionalSongs.isNotEmpty()) { item { SectionTitle("${MeloXSettingsRuntime.musicArea}最近热门", "地区推荐") }; items(value.regionalSongs, key = { "region-${it.id}" }) { song -> SongRow(song) { PlaybackCommands.playQueue(context, value.regionalSongs, song.id) } } }
                if (value.roamingSongs.isNotEmpty()) { item { SectionTitle("私人漫游", "探索更多") }; items(value.roamingSongs, key = { "roaming-${it.id}" }) { song -> SongRow(song) { PlaybackCommands.playQueue(context, value.roamingSongs, song.id) } } }
                if (value.similarSongs.isNotEmpty()) { item { SectionTitle("相似歌曲", "根据当前播放") }; items(value.similarSongs, key = { "similar-${it.id}" }) { song -> SongRow(song) { PlaybackCommands.playQueue(context, value.similarSongs, song.id) } } }
                if (value.podcasts.isNotEmpty() && MeloXSettingsRuntime.podcastsEnabled) { item { SectionTitle("播客推荐", "继续发现") }; item { LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) { items(value.podcasts, key = { "podcast-${it.id}" }) { podcast -> Column(Modifier.width(150.dp).clickable { MeloXCollectionDetailActivity.launchPodcast(context, podcast.id) }) { AsyncImage(podcast.artworkUrl, null, contentScale = ContentScale.Crop, modifier = Modifier.size(150.dp).clip(RoundedCornerShape(14.dp))); Text(podcast.name, Modifier.padding(top = 6.dp), maxLines = 2, overflow = TextOverflow.Ellipsis, fontWeight = FontWeight.SemiBold) } } } } }
                error?.let { message -> item { Text(message, color = MaterialTheme.colorScheme.error, fontSize = 13.sp) } }
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/ui/discovery/MeloXDiscoveryScreens.kt", '''        Triple("私人漫游", "探索模式", Color(0xFF4285F4)),
        Triple("相似歌曲", "从当前歌曲出发", Color(0xFF17A589)),
''', '''        Triple("私人漫游", "探索模式", Color(0xFF4285F4)),
        Triple("私人雷达", "你的雷达歌单", Color(0xFF7B61FF)),
        Triple("相似歌曲", "从当前歌曲出发", Color(0xFF17A589)),
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/ui/discovery/MeloXDiscoveryScreens.kt", '''@Composable
private fun HomeQuickActions(active: String?, perform: (String) -> Unit) {
''', '''@Composable private fun HomeAccountCard(profile: NeteaseAccountProfile, onClick: () -> Unit) { Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp)).background(MaterialTheme.colorScheme.onBackground.copy(alpha = .055f)).clickable(onClick = onClick).padding(14.dp), verticalAlignment = Alignment.CenterVertically) { AsyncImage(profile.avatarUrl, null, contentScale = ContentScale.Crop, modifier = Modifier.size(54.dp).clip(RoundedCornerShape(27.dp))); Column(Modifier.weight(1f).padding(start = 12.dp)) { Text(profile.nickname, fontWeight = FontWeight.Bold, fontSize = 17.sp); Text(profile.signature ?: "查看主页、听歌排行与歌单", color = MaterialTheme.colorScheme.onBackground.copy(alpha = .48f), fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis) }; Text("›", fontSize = 24.sp) } }

@Composable
private fun HomeQuickActions(active: String?, perform: (String) -> Unit) {
''')
replace_once("android/app/src/main/kotlin/com/lladlam/melox/ui/library/LibraryScreen.kt", '''                                    val playlistId = data.playlists.firstOrNull()?.id
''', '''                                    val playlistId = data.likedPlaylistId
''')

# README + tests.
readme = read("README.md").replace("已完成当前 Android 平台范围内的功能迁移", "已完成当前 Android 平台范围内的核心功能迁移，并持续以 MeloX 主线作为行为基准")
if "收藏歌曲会按 100 首一批完整读取" not in readme:
    idx = readme.find("## "); note = "\n> Android 迁移说明：收藏歌曲会按 100 首一批完整读取（不再截断）；播放开始与听歌时长会回传网易云；用户主页/用户搜索、专辑收藏、歌手专辑、评论分页、网易云站内资源分享与全局一起听邀请均已接入。\n\n"; readme = readme[:idx] + note + readme[idx:] if idx >= 0 else readme
write("README.md", readme)
write("android/app/src/test/kotlin/com/lladlam/melox/core/network/NeteaseListenTogetherInvitationTest.kt", r'''package com.lladlam.melox.core.network
import org.junit.Assert.*
import org.junit.Test
class NeteaseListenTogetherInvitationTest {
 @Test fun parsesInviterUidVariant() { val p = parseNeteaseListenTogetherInvitation("https://st.music.163.com/listen-together/share/?roomId=123456&inviterUid=998877"); assertNotNull(p); assertEquals("123456", p?.roomId); assertEquals("998877", p?.inviterId) }
 @Test fun parsesInviterIdVariant() { val p = parseNeteaseListenTogetherInvitation("https://st.music.163.com/listen-together/share/?roomId=abc&inviterId=42"); assertNotNull(p); assertEquals("abc", p?.roomId); assertEquals("42", p?.inviterId) }
 @Test fun rejectsIncompleteInvitation() { assertNull(parseNeteaseListenTogetherInvitation("https://music.163.com/?roomId=123")) }
}
''')

# Remove temporary patch infrastructure in the validated commit.
for temporary in ["tools/apply_audit_fixes.py", ".github/workflows/apply-audit-fixes.yml"]:
    target = p(temporary)
    if target.exists(): target.unlink()
