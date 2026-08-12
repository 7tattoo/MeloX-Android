#!/usr/bin/env python3
from pathlib import Path
import re

ROOT = Path(__file__).resolve().parents[1]

def read(path: str) -> str:
    return (ROOT / path).read_text(encoding="utf-8")

def write(path: str, content: str) -> None:
    target = ROOT / path
    target.parent.mkdir(parents=True, exist_ok=True)
    target.write_text(content, encoding="utf-8")

def replace_once(path: str, old: str, new: str, expected: int = 1) -> None:
    text = read(path)
    count = text.count(old)
    if count != expected:
        raise RuntimeError(f"{path}: expected {expected} matches, got {count}: {old[:240]}")
    write(path, text.replace(old, new))

def regex_once(path: str, pattern: str, repl: str) -> None:
    text = read(path)
    result, count = re.subn(pattern, repl, text, count=1, flags=re.S)
    if count != 1:
        raise RuntimeError(f"{path}: expected one regex match, got {count}: {pattern[:240]}")
    write(path, result)

# Parse the real /homepage/block/page payload instead of issuing and discarding it.
write("android/app/src/main/kotlin/com/lladlam/melox/core/library/NeteaseHomeBlockParser.kt", r'''package com.lladlam.melox.core.library

import com.lladlam.melox.core.model.SearchSong
import org.json.JSONArray
import org.json.JSONObject

internal data class NeteaseParsedHomeBlocks(
    val recommendedPlaylists: List<NeteasePlaylistSummary> = emptyList(),
    val recentlyTrending: List<SearchSong> = emptyList(),
    val tailoredSongs: List<SearchSong> = emptyList(),
    val chartPlaylists: List<NeteasePlaylistSummary> = emptyList(),
    val personalPlaylists: List<NeteasePlaylistSummary> = emptyList(),
    val radarPlaylists: List<NeteasePlaylistSummary> = emptyList(),
    val regionalSongs: List<SearchSong> = emptyList(),
    val roamingSongs: List<SearchSong> = emptyList(),
    val likedSongRecommendations: List<SearchSong> = emptyList(),
    val podcasts: List<NeteaseHomePodcast> = emptyList(),
)

internal object NeteaseHomeBlockParser {
    fun parse(response: JSONObject): NeteaseParsedHomeBlocks {
        val blocks = response.optJSONObject("data")?.optJSONArray("blocks") ?: JSONArray()
        var recommended = emptyList<NeteasePlaylistSummary>()
        var recent = emptyList<SearchSong>()
        var tailored = emptyList<SearchSong>()
        var charts = emptyList<NeteasePlaylistSummary>()
        var personal = emptyList<NeteasePlaylistSummary>()
        var radar = emptyList<NeteasePlaylistSummary>()
        var regional = emptyList<SearchSong>()
        var roaming = emptyList<SearchSong>()
        var likedRecommendations = emptyList<SearchSong>()
        var podcasts = emptyList<NeteaseHomePodcast>()

        for (index in 0 until blocks.length()) {
            val block = blocks.optJSONObject(index) ?: continue
            val code = block.optString("blockCode").uppercase()
            val title = normalizedTitle(block)
            val resources = resources(block)
            val blockPlaylists = resources.mapNotNull(::parsePlaylist).distinctBy(NeteasePlaylistSummary::id)
            val blockSongs = resources.mapNotNull(::parseSong).distinctBy(SearchSong::id)
            val blockPodcasts = resources.mapNotNull(::parsePodcast).distinctBy(NeteaseHomePodcast::id)

            when {
                (code == "HOMEPAGE_BLOCK_PLAYLIST_RCMD" || title == "推荐歌单") && recommended.isEmpty() && blockPlaylists.isNotEmpty() -> recommended = blockPlaylists
                title.contains("近期云村热播") && recent.isEmpty() && blockSongs.isNotEmpty() -> recent = blockSongs
                (code.contains("TOPLIST") || code.contains("RANK") || title == "排行榜") && charts.isEmpty() && blockPlaylists.isNotEmpty() -> charts = blockPlaylists
                (code == "HOMEPAGE_BLOCK_MGC_PLAYLIST" || title.contains("雷达歌单")) && radar.isEmpty() && blockPlaylists.isNotEmpty() -> radar = blockPlaylists
                title.contains("最近的热门歌曲") && regional.isEmpty() && blockSongs.isNotEmpty() -> regional = blockSongs
                title.contains("从你喜欢的歌开始漫游") && roaming.isEmpty() && blockSongs.isNotEmpty() -> roaming = blockSongs
                title.contains("根据你喜爱的歌曲推荐") && likedRecommendations.isEmpty() && blockSongs.isNotEmpty() -> likedRecommendations = blockSongs
                (code == "HOMEPAGE_VOICELIST_RCMD" || title.contains("根据你听过的热门节目推荐")) && podcasts.isEmpty() && blockPodcasts.isNotEmpty() -> podcasts = blockPodcasts
                title.endsWith("的歌单") && title != "推荐歌单" && personal.isEmpty() && blockPlaylists.isNotEmpty() -> personal = blockPlaylists
                title.startsWith("根据") && title.endsWith("为你推荐") && tailored.isEmpty() && blockSongs.isNotEmpty() -> tailored = blockSongs
                code == "HOMEPAGE_BLOCK_STYLE_RCMD" && recent.isEmpty() && blockSongs.isNotEmpty() -> recent = blockSongs
            }
        }
        return NeteaseParsedHomeBlocks(recommended, recent, tailored, charts, personal, radar, regional, roaming, likedRecommendations, podcasts)
    }

    private fun resources(block: JSONObject): List<JSONObject> = buildList {
        val direct = block.optJSONArray("resources") ?: JSONArray()
        for (index in 0 until direct.length()) direct.optJSONObject(index)?.let(::add)
        val creatives = block.optJSONArray("creatives") ?: JSONArray()
        for (creativeIndex in 0 until creatives.length()) {
            val values = creatives.optJSONObject(creativeIndex)?.optJSONArray("resources") ?: JSONArray()
            for (index in 0 until values.length()) values.optJSONObject(index)?.let(::add)
        }
    }

    private fun normalizedTitle(block: JSONObject): String {
        val ui = block.optJSONObject("uiElement")
        val raw = ui?.optJSONObject("subTitle")?.optString("title").orEmpty().ifBlank {
            ui?.optJSONObject("mainTitle")?.optString("title").orEmpty()
        }.ifBlank {
            val creatives = block.optJSONArray("creatives") ?: JSONArray()
            var found = ""
            for (index in 0 until creatives.length()) {
                found = creatives.optJSONObject(index)?.optJSONObject("uiElement")?.optJSONObject("mainTitle")?.optString("title").orEmpty()
                if (found.isNotBlank()) break
            }
            found
        }
        return raw.filterNot(Char::isWhitespace)
    }

    private fun parsePlaylist(value: JSONObject): NeteasePlaylistSummary? {
        val type = value.optString("resourceType").lowercase()
        if (type !in setOf("list", "playlist")) return null
        val id = longValue(value, "resourceId") ?: return null
        val ui = value.optJSONObject("uiElement")
        val ext = value.optJSONObject("resourceExtInfo")
        return NeteasePlaylistSummary(
            id = id,
            name = ui?.optJSONObject("mainTitle")?.optString("title").orEmpty().ifBlank { "未命名歌单" },
            coverUrl = secure(ui?.optJSONObject("image")?.optString("imageUrl")?.takeIf(String::isNotBlank)),
            trackCount = ext?.optInt("trackCount", 0)?.coerceAtLeast(0) ?: 0,
            creatorName = ui?.optJSONObject("subTitle")?.optString("title").orEmpty(),
            playCount = ext?.optLong("playCount", 0L)?.coerceAtLeast(0L) ?: 0L,
            description = ui?.optJSONObject("subTitle")?.optString("title")?.takeIf(String::isNotBlank),
        )
    }

    private fun parseSong(value: JSONObject): SearchSong? {
        val ext = value.optJSONObject("resourceExtInfo")
        val full = ext?.optJSONObject("songData") ?: ext?.optJSONObject("song")
        if (full != null) return parseFullSong(full)
        if (!value.optString("resourceType").equals("song", true)) return null
        val id = longValue(value, "resourceId") ?: return null
        val ui = value.optJSONObject("uiElement")
        val artistsArray = ext?.optJSONArray("artists") ?: JSONArray()
        val artists = buildList {
            for (index in 0 until artistsArray.length()) artistsArray.optJSONObject(index)?.optString("name")?.takeIf(String::isNotBlank)?.let(::add)
        }.joinToString(" / ")
        val title = ui?.optJSONObject("mainTitle")?.optString("title").orEmpty().ifBlank { "未知歌曲" }
        return SearchSong(id, title, artists.ifBlank { "未知歌手" }, "", secure(ui?.optJSONObject("image")?.optString("imageUrl")?.takeIf(String::isNotBlank)), 0L)
    }

    private fun parseFullSong(value: JSONObject): SearchSong? {
        val id = longValue(value, "id") ?: return null
        val artistsArray = value.optJSONArray("ar") ?: value.optJSONArray("artists") ?: JSONArray()
        val artists = buildList {
            for (index in 0 until artistsArray.length()) artistsArray.optJSONObject(index)?.optString("name")?.takeIf(String::isNotBlank)?.let(::add)
        }.joinToString(" / ")
        val album = value.optJSONObject("al") ?: value.optJSONObject("album")
        return SearchSong(
            id = id,
            name = value.optString("name").ifBlank { "未知歌曲" },
            artists = artists.ifBlank { "未知歌手" },
            album = album?.optString("name").orEmpty(),
            artworkUrl = secure(album?.optString("picUrl")?.takeIf(String::isNotBlank) ?: album?.optString("blurPicUrl")?.takeIf(String::isNotBlank)),
            durationMs = value.optLong("dt", value.optLong("duration", 0L)).coerceAtLeast(0L),
        )
    }

    private fun parsePodcast(value: JSONObject): NeteaseHomePodcast? {
        val type = value.optString("resourceType").lowercase()
        if (type !in setOf("voice", "program", "dj_program")) return null
        val ui = value.optJSONObject("uiElement")
        val program = value.optJSONObject("resourceExtInfo")?.optJSONObject("djProgram")
        val radio = program?.optJSONObject("radio")
        val radioId = radio?.let { longValue(it, "id") } ?: return null
        return NeteaseHomePodcast(
            id = radioId,
            name = radio.optString("name").ifBlank { program.optString("name").ifBlank { ui?.optJSONObject("mainTitle")?.optString("title").orEmpty().ifBlank { "播客" } } },
            artworkUrl = secure(program.optString("coverUrl").takeIf(String::isNotBlank) ?: radio.optString("picUrl").takeIf(String::isNotBlank) ?: ui?.optJSONObject("image")?.optString("imageUrl")?.takeIf(String::isNotBlank)),
        )
    }

    private fun longValue(value: JSONObject, key: String): Long? = when (val raw = value.opt(key)) {
        is Number -> raw.toLong().takeIf { it > 0L }
        is String -> raw.toLongOrNull()?.takeIf { it > 0L }
        else -> null
    }
    private fun secure(value: String?): String? = value?.let { if (it.startsWith("http://", true)) "https://${it.substringAfter("://")}" else it }
}
''')

write("android/app/src/main/kotlin/com/lladlam/melox/core/library/NeteaseDiscoveryModels.kt", r'''package com.lladlam.melox.core.library
import com.lladlam.melox.core.model.SearchSong

data class NeteaseHomePodcast(val id: Long, val name: String, val artworkUrl: String?)
data class NeteaseHomeContent(
    val playlists: List<NeteasePlaylistSummary>,
    val newSongs: List<SearchSong>,
    val recentlyTrending: List<SearchSong> = emptyList(),
    val tailoredSongs: List<SearchSong> = emptyList(),
    val chartPlaylists: List<NeteasePlaylistSummary> = emptyList(),
    val radarPlaylists: List<NeteasePlaylistSummary> = emptyList(),
    val personalPlaylists: List<NeteasePlaylistSummary> = emptyList(),
    val regionalSongs: List<SearchSong> = emptyList(),
    val roamingSongs: List<SearchSong> = emptyList(),
    val similarSongs: List<SearchSong> = emptyList(),
    val podcasts: List<NeteaseHomePodcast> = emptyList(),
)
''')

regex_once(
    "android/app/src/main/kotlin/com/lladlam/melox/core/library/NeteaseLibraryClient.kt",
    r'''    suspend fun homeContent\(.*?\n    \}\n\n    suspend fun explorePlaylists''',
    r'''    suspend fun homeContent(
        limit: Int = 12,
        area: String = "全部",
        userId: Long? = null,
        podcastsEnabled: Boolean = true,
        refresh: Boolean = false,
    ): NeteaseHomeContent = withContext(Dispatchers.IO) {
        val authenticated = NeteaseSessionStore.containsMusicU(cookieProvider())
        val serverBlocks = if (authenticated) {
            runCatching {
                NeteaseHomeBlockParser.parse(
                    eapi("/api/homepage/block/page", JSONObject().put("refresh", refresh), true),
                )
            }.getOrNull()
        } else null

        val playlistsResponse = eapi(
            "/api/personalized/playlist",
            JSONObject().put("limit", limit).put("total", true).put("n", 1_000),
            authenticated,
        )
        val fallbackPlaylists = parsePlaylists(playlistsResponse.optJSONArray("result") ?: JSONArray())
        val songData = JSONObject().put("type", "recommend").put("limit", limit).put("areaId", areaId(area))
        val songsResponse = if (authenticated) try {
            authenticatedWeapi.post("/api/personalized/newsong", songData)
        } catch (error: IOException) {
            if (!error.message.orEmpty().contains("空响应")) throw error
            eapi("/api/personalized/newsong", songData, true)
        } else eapi("/api/personalized/newsong", songData, false)
        val songItems = songsResponse.optJSONArray("result") ?: JSONArray()
        val fallbackNewSongs = buildList {
            for (index in 0 until songItems.length()) {
                val item = songItems.optJSONObject(index) ?: continue
                parseSong(item.optJSONObject("song") ?: item)?.let(::add)
            }
        }

        val accountPlaylists = if (authenticated && userId != null) {
            runCatching { userPlaylistsBlocking(userId).drop(1) }.getOrDefault(emptyList())
        } else emptyList()
        val fallbackRadar = accountPlaylists.filter { it.name.contains("雷达") }.take(limit)
        val fallbackPersonal = accountPlaylists.filter { it.creatorUserId == userId }.take(limit)
        val fallbackRecent = runCatching { topSongs("全部", limit) }.getOrDefault(emptyList())
        val fallbackCharts = runCatching { explorePlaylists("排行榜", limit) }.getOrDefault(emptyList())
        val fallbackRegional = runCatching { topSongs(area, limit) }.getOrDefault(emptyList())
        val fallbackRoaming = if (authenticated) runCatching { personalFm(explore = true, limit = limit) }.getOrDefault(emptyList()) else emptyList()
        val likedSeedId = if (authenticated && userId != null) runCatching { likedSongIdsBlocking(userId).firstOrNull() }.getOrNull() else null
        val fallbackSimilar = likedSeedId?.let { runCatching { similarSongsBlocking(it, limit) }.getOrDefault(emptyList()) }.orEmpty()
        val fallbackPodcasts = if (podcastsEnabled) runCatching {
            val path = "/api/program/recommend/v1"
            val data = JSONObject().put("limit", limit.coerceIn(1, 50)).put("offset", 0)
            val result = if (authenticated) try {
                authenticatedWeapi.post(path, data)
            } catch (error: IOException) {
                if (!error.message.orEmpty().contains("空响应")) throw error
                eapi(path, data, true)
            } else eapi(path, data, false)
            val values = result.optJSONArray("programs") ?: JSONArray()
            buildList {
                for (index in 0 until values.length()) {
                    val program = values.optJSONObject(index) ?: continue
                    val radio = program.optJSONObject("radio") ?: continue
                    val radioId = radio.optLong("id", -1L)
                    if (radioId <= 0L) continue
                    add(NeteaseHomePodcast(radioId, radio.optString("name").ifBlank { program.optString("name").ifBlank { "播客" } }, secureUrl(program.optString("coverUrl").takeIf(String::isNotBlank) ?: radio.optString("picUrl").orEmpty()).takeIf(String::isNotBlank)))
                }
            }.distinctBy(NeteaseHomePodcast::id).take(limit)
        }.getOrDefault(emptyList()) else emptyList()

        fun <T> serverOrFallback(server: List<T>?, fallback: List<T>): List<T> = server?.takeIf { it.isNotEmpty() } ?: fallback
        NeteaseHomeContent(
            playlists = serverOrFallback(serverBlocks?.recommendedPlaylists, fallbackPlaylists),
            newSongs = fallbackNewSongs,
            recentlyTrending = serverOrFallback(serverBlocks?.recentlyTrending, fallbackRecent),
            tailoredSongs = serverBlocks?.tailoredSongs.orEmpty(),
            chartPlaylists = serverOrFallback(serverBlocks?.chartPlaylists, fallbackCharts),
            radarPlaylists = serverOrFallback(serverBlocks?.radarPlaylists, fallbackRadar),
            personalPlaylists = serverOrFallback(serverBlocks?.personalPlaylists, fallbackPersonal),
            regionalSongs = serverOrFallback(serverBlocks?.regionalSongs, fallbackRegional),
            roamingSongs = serverOrFallback(serverBlocks?.roamingSongs, fallbackRoaming),
            similarSongs = serverOrFallback(serverBlocks?.likedSongRecommendations, fallbackSimilar),
            podcasts = if (podcastsEnabled) serverOrFallback(serverBlocks?.podcasts, fallbackPodcasts) else emptyList(),
        )
    }

    suspend fun explorePlaylists'''
)

replace_once(
    "android/app/src/main/kotlin/com/lladlam/melox/core/library/NeteaseLibraryClient.kt",
    '''    fun userPlaylistsBlocking(userId: Long, limit: Int = 2_000): List<NeteasePlaylistSummary> {
        ensureLoggedIn()
        val response = eapi(
            uri = "/api/user/playlist",
            data = JSONObject()
                .put("uid", userId)
                .put("limit", limit)
                .put("offset", 0)
                .put("includeVideo", true),
            authenticated = true,
        )
''',
    '''    fun userPlaylistsBlocking(userId: Long, limit: Int = 2_000): List<NeteasePlaylistSummary> {
        val authenticated = NeteaseSessionStore.containsMusicU(cookieProvider())
        val response = eapi(
            uri = "/api/user/playlist",
            data = JSONObject()
                .put("uid", userId)
                .put("limit", limit)
                .put("offset", 0)
                .put("includeVideo", true),
            authenticated = authenticated,
        )
'''
)

replace_once(
    "android/app/src/main/kotlin/com/lladlam/melox/core/library/NeteaseLibraryClient.kt",
    '''    fun recentSongsBlocking(limit: Int = 100): List<SearchSong> {
        ensureLoggedIn()
        val response = eapi(
            uri = "/api/play-record/song/list",
            data = JSONObject().put("limit", limit),
            authenticated = true,
        )
''',
    '''    fun recentSongsBlocking(limit: Int = 100): List<SearchSong> {
        ensureLoggedIn()
        val path = "/api/play-record/song/list"
        val data = JSONObject().put("limit", limit)
        val response = try {
            authenticatedWeapi.post(path, data)
        } catch (error: IOException) {
            if (!error.message.orEmpty().contains("空响应")) throw error
            eapi(path, data, true)
        }
'''
)

regex_once(
    "android/app/src/main/kotlin/com/lladlam/melox/core/library/NeteaseLibraryClient.kt",
    r'''    fun songDetailsBlocking\(ids: List<Long>\): List<SearchSong> \{.*?        val songs = response\.optJSONArray\("songs"\) \?: JSONArray\(\)''',
    r'''    fun songDetailsBlocking(ids: List<Long>): List<SearchSong> {
        if (ids.isEmpty()) return emptyList()
        val descriptors = JSONArray().apply { ids.take(100).forEach { put(JSONObject().put("id", it)) } }
        val path = "/api/v3/song/detail"
        val data = JSONObject().put("c", descriptors.toString())
        val authenticated = NeteaseSessionStore.containsMusicU(cookieProvider())
        val response = if (authenticated) try {
            authenticatedWeapi.post(path, data)
        } catch (error: IOException) {
            if (!error.message.orEmpty().contains("空响应")) throw error
            eapi(path, data, true)
        } else eapi(path, data, false)
        val songs = response.optJSONArray("songs") ?: JSONArray()'''
)

# Cache every server-backed homepage slot.
replace_once(
    "android/app/src/main/kotlin/com/lladlam/melox/core/library/NeteaseLibraryCache.kt",
    '''        NeteaseHomeContent(
            playlists = decodePlaylists(value.optJSONArray("playlists") ?: JSONArray()), newSongs = decodeSongs(value.optJSONArray("newSongs") ?: JSONArray()),
            radarPlaylists = decodePlaylists(value.optJSONArray("radarPlaylists") ?: JSONArray()), personalPlaylists = decodePlaylists(value.optJSONArray("personalPlaylists") ?: JSONArray()),
            regionalSongs = decodeSongs(value.optJSONArray("regionalSongs") ?: JSONArray()), roamingSongs = decodeSongs(value.optJSONArray("roamingSongs") ?: JSONArray()),
            similarSongs = decodeSongs(value.optJSONArray("similarSongs") ?: JSONArray()), podcasts = decodeHomePodcasts(value.optJSONArray("podcasts") ?: JSONArray()),
        )
''',
    '''        NeteaseHomeContent(
            playlists = decodePlaylists(value.optJSONArray("playlists") ?: JSONArray()),
            newSongs = decodeSongs(value.optJSONArray("newSongs") ?: JSONArray()),
            recentlyTrending = decodeSongs(value.optJSONArray("recentlyTrending") ?: JSONArray()),
            tailoredSongs = decodeSongs(value.optJSONArray("tailoredSongs") ?: JSONArray()),
            chartPlaylists = decodePlaylists(value.optJSONArray("chartPlaylists") ?: JSONArray()),
            radarPlaylists = decodePlaylists(value.optJSONArray("radarPlaylists") ?: JSONArray()),
            personalPlaylists = decodePlaylists(value.optJSONArray("personalPlaylists") ?: JSONArray()),
            regionalSongs = decodeSongs(value.optJSONArray("regionalSongs") ?: JSONArray()),
            roamingSongs = decodeSongs(value.optJSONArray("roamingSongs") ?: JSONArray()),
            similarSongs = decodeSongs(value.optJSONArray("similarSongs") ?: JSONArray()),
            podcasts = decodeHomePodcasts(value.optJSONArray("podcasts") ?: JSONArray()),
        )
'''
)
replace_once(
    "android/app/src/main/kotlin/com/lladlam/melox/core/library/NeteaseLibraryCache.kt",
    '''            .put("playlists", encodePlaylists(content.playlists)).put("newSongs", encodeSongs(content.newSongs))
            .put("radarPlaylists", encodePlaylists(content.radarPlaylists)).put("personalPlaylists", encodePlaylists(content.personalPlaylists))
''',
    '''            .put("playlists", encodePlaylists(content.playlists)).put("newSongs", encodeSongs(content.newSongs))
            .put("recentlyTrending", encodeSongs(content.recentlyTrending)).put("tailoredSongs", encodeSongs(content.tailoredSongs))
            .put("chartPlaylists", encodePlaylists(content.chartPlaylists))
            .put("radarPlaylists", encodePlaylists(content.radarPlaylists)).put("personalPlaylists", encodePlaylists(content.personalPlaylists))
'''
)

# Pull-to-refresh now forwards refresh=true, and homepage liked-song recommendations use liked songs, not the current player item.
replace_once(
    "android/app/src/main/kotlin/com/lladlam/melox/ui/discovery/MeloXDiscoveryScreens.kt",
    '''    fun refresh() {
        if (refreshing) return
        scope.launch {
            refreshing = true
            runCatching { if (session.isLoggedIn && session.profile == null) session.refreshProfile(force = true); client.homeContent(area = MeloXSettingsRuntime.musicArea, userId = session.profile?.userId, currentSongId = PlaybackCommands.currentSongId(), podcastsEnabled = MeloXSettingsRuntime.podcastsEnabled) }
''',
    '''    fun refresh(forceServer: Boolean = false) {
        if (refreshing) return
        scope.launch {
            refreshing = true
            runCatching { if (session.isLoggedIn && session.profile == null) session.refreshProfile(force = true); client.homeContent(area = MeloXSettingsRuntime.musicArea, userId = session.profile?.userId, podcastsEnabled = MeloXSettingsRuntime.podcastsEnabled, refresh = forceServer) }
'''
)
replace_once(
    "android/app/src/main/kotlin/com/lladlam/melox/ui/discovery/MeloXDiscoveryScreens.kt",
    '''    LaunchedEffect(homeCacheKey) { content = cache.loadHomeContent(homeCacheKey); if (session.isLoggedIn) session.refreshProfile(); if (NeteaseLibraryCache.beginHomeColdStartRefresh(homeCacheKey)) refresh() }

    PullToRefreshBox(isRefreshing = refreshing, onRefresh = ::refresh, modifier = Modifier.fillMaxSize()) {
''',
    '''    LaunchedEffect(homeCacheKey) { content = cache.loadHomeContent(homeCacheKey); if (session.isLoggedIn) session.refreshProfile(); if (NeteaseLibraryCache.beginHomeColdStartRefresh(homeCacheKey)) refresh(false) }

    PullToRefreshBox(isRefreshing = refreshing, onRefresh = { refresh(true) }, modifier = Modifier.fillMaxSize()) {
'''
)
replace_once(
    "android/app/src/main/kotlin/com/lladlam/melox/ui/discovery/MeloXDiscoveryScreens.kt",
    '''                if (value.radarPlaylists.isNotEmpty()) { item { SectionTitle("私人雷达", "你的雷达歌单") }; item { PlaylistRow(value.radarPlaylists) { selectedPlaylist = it } } }
''',
    '''                if (value.recentlyTrending.isNotEmpty()) { item { SectionTitle("近期云村热播", "来自网易云首页") }; items(value.recentlyTrending, key = { "recent-trending-${it.id}" }) { song -> SongRow(song) { PlaybackCommands.playQueue(context, value.recentlyTrending, song.id) } } }
                if (value.tailoredSongs.isNotEmpty()) { item { SectionTitle("根据你的喜好为你推荐", "个性化") }; items(value.tailoredSongs, key = { "tailored-${it.id}" }) { song -> SongRow(song) { PlaybackCommands.playQueue(context, value.tailoredSongs, song.id) } } }
                if (value.chartPlaylists.isNotEmpty()) { item { SectionTitle("排行榜", "网易云榜单") }; item { PlaylistRow(value.chartPlaylists) { selectedPlaylist = it } } }
                if (value.radarPlaylists.isNotEmpty()) { item { SectionTitle("私人雷达", "你的雷达歌单") }; item { PlaylistRow(value.radarPlaylists) { selectedPlaylist = it } } }
'''
)

# Public user results should not fail just because the viewer is logged out.
replace_once(
    "android/app/src/main/kotlin/com/lladlam/melox/core/network/NeteaseAccountDetailsClient.kt",
    '''    suspend fun userDetail(userId: Long): MeloXAccountDetail = withContext(Dispatchers.IO) {
        if (!NeteaseSessionStore.containsMusicU(cookieProvider())) throw IOException("请先登录网易云音乐")
        val response = try { weapi.post("/api/v1/user/detail/$userId") } catch (error: IOException) { if (!error.message.orEmpty().contains("空响应")) throw error; eapi.post("/api/w/v1/user/detail/$userId", JSONObject().put("all", "true").put("userId", userId)) }
''',
    '''    suspend fun userDetail(userId: Long): MeloXAccountDetail = withContext(Dispatchers.IO) {
        val loggedIn = NeteaseSessionStore.containsMusicU(cookieProvider())
        val fallbackData = JSONObject().put("all", "true").put("userId", userId)
        val response = if (loggedIn) try { weapi.post("/api/v1/user/detail/$userId") } catch (error: IOException) { if (!error.message.orEmpty().contains("空响应")) throw error; eapi.post("/api/w/v1/user/detail/$userId", fallbackData) } else eapi.post("/api/w/v1/user/detail/$userId", fallbackData, authenticated = false)
'''
)
replace_once(
    "android/app/src/main/kotlin/com/lladlam/melox/core/network/NeteaseSocialExtrasClient.kt",
    '''        val response = socialRead("/api/v1/play/record", JSONObject().put("uid", userId).put("type", period.apiValue))
''',
    '''        val response = socialRead("/api/v1/play/record", JSONObject().put("uid", userId).put("type", period.apiValue), allowGuest = true)
'''
)

# Generic NetEase resource sharing for song / playlist / album.
replace_once(
    "android/app/src/main/kotlin/com/lladlam/melox/core/network/NeteaseSocialExtrasClient.kt",
    '''    suspend fun sendSongToUser(songId: Long, userId: Long, message: String = "") = withContext(Dispatchers.IO) { eapi.post("/api/msg/private/send", JSONObject().put("id", songId).put("msg", message).put("type", "song").put("userIds", "[$userId]")); Unit }
    suspend fun shareSongToTimeline(songId: Long, message: String = "") = withContext(Dispatchers.IO) { eapi.post("/api/share/friends/resource", JSONObject().put("type", "song").put("msg", message).put("id", songId)); Unit }
''',
    '''    suspend fun sendSongToUser(songId: Long, userId: Long, message: String = "") = sendResourceToUser("song", songId, userId, message)
    suspend fun shareSongToTimeline(songId: Long, message: String = "") = shareResourceToTimeline("song", songId, message)
    suspend fun sendResourceToUser(resourceType: String, resourceId: Long, userId: Long, message: String = "") = withContext(Dispatchers.IO) {
        require(resourceType in setOf("song", "playlist", "album")) { "不支持的网易云资源类型" }
        eapi.post("/api/msg/private/send", JSONObject().put("id", resourceId).put("msg", message).put("type", resourceType).put("userIds", "[$userId]")); Unit
    }
    suspend fun shareResourceToTimeline(resourceType: String, resourceId: Long, message: String = "") = withContext(Dispatchers.IO) {
        require(resourceType in setOf("song", "playlist")) { "网易云音乐暂不支持将此类型的内容转发到动态" }
        eapi.post("/api/share/friends/resource", JSONObject().put("type", resourceType).put("msg", message).put("id", resourceId)); Unit
    }
'''
)

write("android/app/src/main/kotlin/com/lladlam/melox/ui/sharing/MeloXNeteaseResourceShareActivity.kt", r'''package com.lladlam.melox.ui.sharing

import android.app.Activity
import android.content.ComponentName
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
import com.lladlam.melox.core.network.MeloXMessageContact
import com.lladlam.melox.core.network.NeteaseMusicOperationsClient
import com.lladlam.melox.core.network.NeteaseSearchClient
import com.lladlam.melox.core.network.NeteaseSocialExtrasClient
import com.lladlam.melox.ui.glass.meloXLiquidButton
import com.lladlam.melox.ui.theme.MeloXTheme
import kotlinx.coroutines.launch

private data class ShareResource(val type: String, val id: Long, val title: String, val url: String) {
    val supportsTimeline: Boolean get() = type == "song" || type == "playlist"
    val kindTitle: String get() = when (type) { "song" -> "歌曲"; "playlist" -> "歌单"; "album" -> "专辑"; else -> "内容" }
}

class MeloXNeteaseResourceShareActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState); enableEdgeToEdge()
        val direct = intent.getStringExtra(EXTRA_TYPE)?.let { type ->
            val id = intent.getLongExtra(EXTRA_ID, -1L); if (id > 0L) ShareResource(type, id, intent.getStringExtra(EXTRA_TITLE).orEmpty(), intent.getStringExtra(EXTRA_URL).orEmpty()) else null
        }
        val incoming = if (intent.action == Intent.ACTION_SEND) parseText(intent.getStringExtra(Intent.EXTRA_TEXT).orEmpty()) else null
        val resource = direct ?: incoming
        if (resource == null || resource.type !in setOf("song", "playlist", "album")) { finish(); return }
        setContent { MeloXTheme { ShareScreen(resource, ::finish) } }
    }

    companion object {
        private const val EXTRA_TYPE = "resource_type"; private const val EXTRA_ID = "resource_id"; private const val EXTRA_TITLE = "resource_title"; private const val EXTRA_URL = "resource_url"
        fun launch(context: Context, type: String, id: Long, title: String, url: String) {
            if (type !in setOf("song", "playlist", "album") || id <= 0L) return
            context.startActivity(Intent(context, MeloXNeteaseResourceShareActivity::class.java).putExtra(EXTRA_TYPE, type).putExtra(EXTRA_ID, id).putExtra(EXTRA_TITLE, title).putExtra(EXTRA_URL, url).apply { if (context !is Activity) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) })
        }
        private fun parseText(text: String): ShareResource? {
            val candidates = listOf("song" to Regex("(?:song(?:/|\\?id=)|songId=)(\\d+)", RegexOption.IGNORE_CASE), "playlist" to Regex("(?:playlist(?:/|\\?id=)|playlistId=)(\\d+)", RegexOption.IGNORE_CASE), "album" to Regex("(?:album(?:/|\\?id=)|albumId=)(\\d+)", RegexOption.IGNORE_CASE))
            for ((type, regex) in candidates) regex.find(text)?.groupValues?.getOrNull(1)?.toLongOrNull()?.takeIf { it > 0L }?.let { id -> return ShareResource(type, id, text.lineSequence().firstOrNull().orEmpty(), "https://music.163.com/$type?id=$id") }
            return null
        }
    }
}

@Composable private fun ShareScreen(resource: ShareResource, onBack: () -> Unit) {
    val context = LocalContext.current; val app = context.applicationContext; val cookieProvider = remember(app) { { NeteaseSessionStore.readCookie(app) } }; val ops = remember(app) { NeteaseMusicOperationsClient(cookieProvider = cookieProvider) }; val social = remember(app) { NeteaseSocialExtrasClient(cookieProvider = cookieProvider) }; val account = remember(app) { NeteaseSearchClient(cookieProvider = cookieProvider) }; val scope = rememberCoroutineScope()
    var contacts by remember(resource.id) { mutableStateOf<List<MeloXMessageContact>>(emptyList()) }; var loading by remember(resource.id) { mutableStateOf(true) }; var busy by remember(resource.id) { mutableStateOf(false) }; var message by remember(resource.id) { mutableStateOf<String?>(null) }
    LaunchedEffect(resource.id) { val cookie = NeteaseSessionStore.readCookie(app); if (!NeteaseSessionStore.containsMusicU(cookie)) { message = "登录网易云音乐后可发送给好友或分享到动态。"; loading = false; return@LaunchedEffect }; runCatching { val profile = account.accountProfile(); ops.messageContacts(profile.userId) }.onSuccess { contacts = it }.onFailure { message = it.message ?: "联系人加载失败" }; loading = false }
    BackHandler(onBack = onBack)
    LazyColumn(Modifier.fillMaxSize().statusBarsPadding(), contentPadding = PaddingValues(20.dp, 14.dp, 20.dp, 36.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        item { Row(verticalAlignment = Alignment.CenterVertically) { Box(Modifier.size(44.dp).meloXLiquidButton(shape = CircleShape).clickable(onClick = onBack), contentAlignment = Alignment.Center) { Text("‹", fontSize = 30.sp) }; Column(Modifier.weight(1f).padding(start = 12.dp)) { Text("分享${resource.kindTitle}", fontSize = 25.sp, fontWeight = FontWeight.Bold); Text(resource.title.ifBlank { "网易云音乐" }, maxLines = 1, overflow = TextOverflow.Ellipsis, color = MaterialTheme.colorScheme.onSurface.copy(alpha = .52f)) } } }
        item { Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) { ShareAction("系统分享", Modifier.weight(1f), enabled = !busy) { val send = Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT, "${resource.title}\n${resource.url}"); val chooser = Intent.createChooser(send, "系统分享").putExtra(Intent.EXTRA_EXCLUDE_COMPONENTS, arrayOf(ComponentName(context, MeloXNeteaseResourceShareActivity::class.java))); context.startActivity(chooser) }; if (resource.supportsTimeline) ShareAction("分享到动态", Modifier.weight(1f), enabled = !busy && NeteaseSessionStore.containsMusicU(NeteaseSessionStore.readCookie(app))) { busy = true; scope.launch { runCatching { social.shareResourceToTimeline(resource.type, resource.id) }.onSuccess { message = "已分享到网易云动态" }.onFailure { message = it.message ?: "动态分享失败" }; busy = false } } } }
        message?.let { item { Text(it, color = if (it.startsWith("已")) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = .58f), fontSize = 13.sp) } }
        if (loading) item { Box(Modifier.fillMaxWidth().height(100.dp), contentAlignment = Alignment.Center) { CircularProgressIndicator() } }
        if (contacts.isNotEmpty()) item { Text("发送给网易云好友", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp)) }
        items(contacts, key = { "contact-${it.id}" }) { contact -> Row(Modifier.fillMaxWidth().clickable(enabled = !busy) { busy = true; scope.launch { runCatching { social.sendResourceToUser(resource.type, resource.id, contact.id) }.onSuccess { message = "已发送给 ${contact.name}" }.onFailure { message = it.message ?: "发送失败" }; busy = false } }.padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) { AsyncImage(contact.avatarUrl, null, contentScale = ContentScale.Crop, modifier = Modifier.size(48.dp).clip(CircleShape)); Column(Modifier.weight(1f).padding(start = 11.dp)) { Text(contact.name, fontWeight = FontWeight.SemiBold); if (contact.signature.isNotBlank()) Text(contact.signature, maxLines = 1, overflow = TextOverflow.Ellipsis, color = MaterialTheme.colorScheme.onSurface.copy(alpha = .45f), fontSize = 12.sp) }; Text("发送", color = MaterialTheme.colorScheme.primary, fontSize = 13.sp) } }
    }
}
@Composable private fun ShareAction(title: String, modifier: Modifier, enabled: Boolean, onClick: () -> Unit) = Box(modifier.height(46.dp).meloXLiquidButton(shape = RoundedCornerShape(20.dp), enabled = enabled).clickable(enabled = enabled, onClick = onClick), contentAlignment = Alignment.Center) { Text(title, fontWeight = FontWeight.SemiBold) }
''')

replace_once(
    "android/app/src/main/AndroidManifest.xml",
    '''        <activity android:name=".ui.player.MeloXListenTogetherInviteActivity" android:exported="false" />

        <provider
''',
    '''        <activity android:name=".ui.player.MeloXListenTogetherInviteActivity" android:exported="false" />
        <activity android:name=".ui.sharing.MeloXNeteaseResourceShareActivity" android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.SEND" />
                <category android:name="android.intent.category.DEFAULT" />
                <data android:mimeType="text/plain" />
            </intent-filter>
        </activity>

        <provider
'''
)

replace_once(
    "android/app/src/main/kotlin/com/lladlam/melox/ui/library/LibraryScreen.kt",
    '''private fun sharePlaylistFromDetail(context: android.content.Context, playlist: NeteasePlaylistSummary) {
    runCatching {
        context.startActivity(
            android.content.Intent.createChooser(
                android.content.Intent(android.content.Intent.ACTION_SEND)
                    .setType("text/plain")
                    .putExtra(android.content.Intent.EXTRA_TEXT, "${playlist.name}\\nhttps://music.163.com/playlist?id=${playlist.id}"),
                "分享歌单",
            ).addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK),
        )
    }
}
''',
    '''private fun sharePlaylistFromDetail(context: android.content.Context, playlist: NeteasePlaylistSummary) {
    com.lladlam.melox.ui.sharing.MeloXNeteaseResourceShareActivity.launch(
        context = context,
        type = "playlist",
        id = playlist.id,
        title = playlist.name,
        url = "https://music.163.com/playlist?id=${playlist.id}",
    )
}
'''
)

replace_once(
    "android/app/src/main/kotlin/com/lladlam/melox/ui/collection/MeloXCollectionDetailActivity.kt",
    '''import com.lladlam.melox.ui.glass.meloXLiquidButton
''',
    '''import com.lladlam.melox.ui.glass.meloXLiquidButton
import com.lladlam.melox.ui.sharing.MeloXNeteaseResourceShareActivity
'''
)
replace_once(
    "android/app/src/main/kotlin/com/lladlam/melox/ui/collection/MeloXCollectionDetailActivity.kt",
    '''Action("分享") { runCatching { context.startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT, "${v.album.name}\\nhttps://music.163.com/album?id=$id"), "分享专辑")) } }''',
    '''Action("分享") { MeloXNeteaseResourceShareActivity.launch(context, "album", id, v.album.name, "https://music.163.com/album?id=$id") }'''
)

# Let the final playback-history event finish rather than cancelling it immediately on service teardown.
replace_once(
    "android/app/src/main/kotlin/com/lladlam/melox/playback/MeloXPlaybackHistoryReporter.kt",
    '''    fun close() { scope.cancel() }
''',
    '''    fun close() { scope.launch { delay(1_500L); scope.cancel() } }
'''
)

# Tests: real homepage slot mapping and public resource semantics.
write("android/app/src/test/kotlin/com/lladlam/melox/core/library/NeteaseHomeBlockParserTest.kt", r'''package com.lladlam.melox.core.library

import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class NeteaseHomeBlockParserTest {
    @Test fun mapsServerBlocksToMeloXSlots() {
        val playlist = JSONObject().put("resourceType", "playlist").put("resourceId", "123").put("uiElement", JSONObject().put("mainTitle", JSONObject().put("title", "服务器推荐歌单")).put("image", JSONObject().put("imageUrl", "http://example.test/a.jpg")))
        val songData = JSONObject().put("id", 456).put("name", "服务器歌曲").put("ar", JSONArray().put(JSONObject().put("name", "歌手"))).put("al", JSONObject().put("name", "专辑").put("picUrl", "http://example.test/b.jpg"))
        val song = JSONObject().put("resourceType", "song").put("resourceId", "456").put("resourceExtInfo", JSONObject().put("songData", songData))
        val blocks = JSONArray()
            .put(JSONObject().put("blockCode", "HOMEPAGE_BLOCK_PLAYLIST_RCMD").put("uiElement", JSONObject().put("mainTitle", JSONObject().put("title", "推荐歌单"))).put("creatives", JSONArray().put(JSONObject().put("resources", JSONArray().put(playlist)))))
            .put(JSONObject().put("blockCode", "HOMEPAGE_BLOCK_STYLE_RCMD").put("uiElement", JSONObject().put("mainTitle", JSONObject().put("title", "近期云村热播"))).put("creatives", JSONArray().put(JSONObject().put("resources", JSONArray().put(song)))))
        val parsed = NeteaseHomeBlockParser.parse(JSONObject().put("data", JSONObject().put("blocks", blocks)))
        assertEquals(123L, parsed.recommendedPlaylists.single().id)
        assertEquals(456L, parsed.recentlyTrending.single().id)
        assertTrue(parsed.recommendedPlaylists.single().coverUrl.orEmpty().startsWith("https://"))
    }
}
''')

# README now describes actual server-block consumption and full resource sharing.
replace_once(
    "README.md",
    '''> Android 迁移说明：收藏歌曲会按 100 首一批完整读取（不再截断）；播放开始与听歌时长会回传网易云；用户主页/用户搜索、专辑收藏、歌手专辑、评论分页、网易云站内资源分享与全局一起听邀请均已接入。
''',
    '''> Android 迁移说明：收藏歌曲会按 100 首一批完整读取（不再截断）；播放开始与听歌时长会回传网易云；首页会消费网易云 `/homepage/block/page` 推荐块并在缺块时回退；用户主页/用户搜索、专辑收藏、歌手专辑、评论分页、歌曲/歌单/专辑站内资源分享与全局一起听邀请均已接入。
'''
)

# Temporary repair infrastructure must not survive the validated commit.
for temporary in ["tools/finalize_audit_parity.py", ".github/workflows/finalize-audit-parity.yml"]:
    target = ROOT / temporary
    if target.exists(): target.unlink()
