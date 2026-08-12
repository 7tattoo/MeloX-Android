package com.lladlam.melox.ui.provider

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.lladlam.melox.core.music.model.MusicAlbumDetail
import com.lladlam.melox.core.music.model.MusicAlbumSummary
import com.lladlam.melox.core.music.model.MusicArtistDetail
import com.lladlam.melox.core.music.model.MusicArtistSummary
import com.lladlam.melox.core.music.model.MusicPlaylistSummary
import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.core.music.model.MusicTrack
import com.lladlam.melox.core.music.provider.AlbumCapability
import com.lladlam.melox.core.music.provider.ArtistCapability
import com.lladlam.melox.core.music.provider.CatalogSearchCapability
import com.lladlam.melox.core.music.provider.MeloXMusicProviders
import com.lladlam.melox.core.music.provider.MusicProviderSelectionStore
import com.lladlam.melox.core.music.provider.SearchCapability
import com.lladlam.melox.core.music.provider.UnifiedMusicService
import com.lladlam.melox.core.network.MeloXSearchKind
import com.lladlam.melox.playback.ProviderPlaybackCommands
import com.lladlam.melox.ui.MeloXBottomContentClearance
import com.lladlam.melox.ui.glass.meloXLiquidButton
import com.lladlam.melox.ui.search.MeloXSearchLaunchBus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private enum class ProviderSearchKind(val title: String) {
    Songs("歌曲"),
    Playlists("歌单"),
    Albums("专辑"),
    Artists("歌手"),
}

private fun MeloXSearchKind.toProviderSearchKind(): ProviderSearchKind? = when (this) {
    MeloXSearchKind.Songs -> ProviderSearchKind.Songs
    MeloXSearchKind.Playlists -> ProviderSearchKind.Playlists
    MeloXSearchKind.Albums -> ProviderSearchKind.Albums
    MeloXSearchKind.Artists -> ProviderSearchKind.Artists
    else -> null
}

@Composable
fun ProviderSearchScreen(source: MusicSource) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val registry = remember(source) { MeloXMusicProviders.create(context) }
    val provider = remember(source, registry) { registry.require(source) }
    val songSearch = provider as? SearchCapability
    val catalogSearch = provider as? CatalogSearchCapability
    val unifiedEnabled = MusicProviderSelectionStore.unifiedEnabled(context)
    val unifiedSources = MusicProviderSelectionStore.unifiedSources(context)
    val launchRequest = MeloXSearchLaunchBus.request

    var query by remember(source) { mutableStateOf("") }
    var kind by remember(source) { mutableStateOf(ProviderSearchKind.Songs) }
    var songResults by remember(source) { mutableStateOf<List<MusicTrack>>(emptyList()) }
    var playlistResults by remember(source) { mutableStateOf<List<MusicPlaylistSummary>>(emptyList()) }
    var albumResults by remember(source) { mutableStateOf<List<MusicAlbumSummary>>(emptyList()) }
    var artistResults by remember(source) { mutableStateOf<List<MusicArtistSummary>>(emptyList()) }
    var unifiedFailures by remember(source) { mutableStateOf<List<UnifiedMusicService.SearchFailure>>(emptyList()) }
    var loading by remember(source) { mutableStateOf(false) }
    var error by remember(source) { mutableStateOf<String?>(null) }
    var selectedPlaylist by remember(source) { mutableStateOf<MusicPlaylistSummary?>(null) }
    var selectedAlbum by remember(source) { mutableStateOf<MusicAlbumSummary?>(null) }
    var selectedArtist by remember(source) { mutableStateOf<MusicArtistSummary?>(null) }

    selectedPlaylist?.let { playlist ->
        ProviderPlaylistDetailScreen(
            source = playlist.id.source,
            playlist = playlist,
            onBack = { selectedPlaylist = null },
        )
        return
    }
    selectedAlbum?.let { album ->
        ProviderAlbumDetailScreen(
            album = album,
            onBack = { selectedAlbum = null },
        )
        return
    }
    selectedArtist?.let { artist ->
        ProviderArtistDetailScreen(
            artist = artist,
            onBack = { selectedArtist = null },
        )
        return
    }

    fun submitSearch(
        requestedQuery: String = query,
        requestedKind: ProviderSearchKind = kind,
    ) {
        val normalized = requestedQuery.trim()
        if (normalized.isBlank() || loading) return
        loading = true
        error = null
        unifiedFailures = emptyList()
        scope.launch {
            runCatching {
                withContext(Dispatchers.IO) {
                    when (requestedKind) {
                        ProviderSearchKind.Songs -> {
                            if (unifiedEnabled) {
                                val result = UnifiedMusicService(registry).searchSongs(
                                    query = normalized,
                                    sources = unifiedSources,
                                    page = 1,
                                    pageSizePerProvider = 25,
                                )
                                SearchPayload.Songs(result.tracks, result.failures)
                            } else {
                                val capability = songSearch
                                    ?: throw IllegalStateException("${source.displayName} 当前没有歌曲搜索能力")
                                SearchPayload.Songs(
                                    tracks = capability.searchSongs(normalized, page = 1, pageSize = 50).items,
                                    failures = emptyList(),
                                )
                            }
                        }

                        ProviderSearchKind.Playlists -> {
                            val capability = catalogSearch
                                ?: throw IllegalStateException("${source.displayName} 当前没有歌单搜索能力")
                            SearchPayload.Playlists(
                                capability.searchPlaylists(normalized, page = 1, pageSize = 40).items,
                            )
                        }

                        ProviderSearchKind.Albums -> {
                            val capability = catalogSearch
                                ?: throw IllegalStateException("${source.displayName} 当前没有专辑搜索能力")
                            SearchPayload.Albums(
                                capability.searchAlbums(normalized, page = 1, pageSize = 40).items,
                            )
                        }

                        ProviderSearchKind.Artists -> {
                            val capability = catalogSearch
                                ?: throw IllegalStateException("${source.displayName} 当前没有歌手搜索能力")
                            SearchPayload.Artists(
                                capability.searchArtists(normalized, page = 1, pageSize = 40).items,
                            )
                        }
                    }
                }
            }.onSuccess { payload ->
                kind = requestedKind
                query = requestedQuery
                when (payload) {
                    is SearchPayload.Songs -> {
                        songResults = payload.tracks
                        unifiedFailures = payload.failures
                    }
                    is SearchPayload.Playlists -> playlistResults = payload.items
                    is SearchPayload.Albums -> albumResults = payload.items
                    is SearchPayload.Artists -> artistResults = payload.items
                }
            }.onFailure { failure ->
                error = failure.message ?: "搜索失败"
            }
            loading = false
        }
    }

    LaunchedEffect(launchRequest, source) {
        val request = launchRequest ?: return@LaunchedEffect
        val requestedKind = request.kind.toProviderSearchKind() ?: return@LaunchedEffect
        query = request.query
        kind = requestedKind
        MeloXSearchLaunchBus.consume(request)
        submitSearch(request.query, requestedKind)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp)
            .padding(top = 44.dp, bottom = MeloXBottomContentClearance),
    ) {
        Text("搜索", fontSize = 36.sp, fontWeight = FontWeight.Bold)
        Text(
            if (unifiedEnabled) {
                "${source.displayName} · 跨平台歌曲搜索：${unifiedSources.joinToString(" / ") { it.displayName }}"
            } else {
                source.displayName
            },
            fontSize = 12.sp,
            color = if (unifiedEnabled) {
                MaterialTheme.colorScheme.primary.copy(alpha = 0.78f)
            } else {
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f)
            },
        )
        Spacer(Modifier.height(14.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .meloXLiquidButton(
                    shape = RoundedCornerShape(24.dp),
                    surfaceColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.055f),
                )
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Box(Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                if (query.isBlank()) {
                    Text(
                        "搜索${kind.title}",
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                    )
                }
                BasicTextField(
                    value = query,
                    onValueChange = { query = it },
                    singleLine = true,
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 16.sp,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            Text(
                "搜索",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable(
                    enabled = query.isNotBlank() && !loading,
                    onClick = { submitSearch() },
                ),
            )
        }

        Spacer(Modifier.height(12.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(ProviderSearchKind.entries, key = ProviderSearchKind::name) { candidate ->
                ProviderSearchKindChip(
                    title = candidate.title,
                    selected = candidate == kind,
                    enabled = candidate == ProviderSearchKind.Songs || catalogSearch != null,
                    onClick = {
                        kind = candidate
                        error = null
                    },
                )
            }
        }
        Spacer(Modifier.height(12.dp))

        when {
            loading -> Box(Modifier.fillMaxWidth().padding(28.dp), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            error != null -> ProviderSimpleCard("搜索失败", error.orEmpty())
            else -> when (kind) {
                ProviderSearchKind.Songs -> {
                    Column(Modifier.weight(1f)) {
                        if (unifiedFailures.isNotEmpty()) {
                            ProviderSimpleCard(
                                "部分平台搜索失败",
                                unifiedFailures.joinToString("；") { "${it.source.displayName}: ${it.message}" },
                            )
                            Spacer(Modifier.height(8.dp))
                        }
                        LazyColumn(Modifier.weight(1f)) {
                            items(
                                songResults,
                                key = { "song:${it.id.source.storageValue}:${it.id.value}" },
                            ) { track ->
                                ProviderTrackRow(
                                    track = track,
                                    showSource = unifiedEnabled,
                                    onClick = {
                                        ProviderPlaybackCommands.playQueue(
                                            context = context,
                                            tracks = songResults,
                                            selectedTrackId = track.id,
                                            onFailure = { failure -> error = failure.message ?: "播放失败" },
                                        )
                                    },
                                )
                            }
                        }
                    }
                }

                ProviderSearchKind.Playlists -> LazyColumn(Modifier.weight(1f)) {
                    items(
                        playlistResults,
                        key = { "playlist:${it.id.source.storageValue}:${it.id.value}" },
                    ) { playlist ->
                        ProviderPlaylistRow(playlist) { selectedPlaylist = playlist }
                    }
                }

                ProviderSearchKind.Albums -> LazyColumn(Modifier.weight(1f)) {
                    items(
                        albumResults,
                        key = { "album:${it.id.source.storageValue}:${it.id.value}" },
                    ) { album ->
                        ProviderAlbumRow(album) { selectedAlbum = album }
                    }
                }

                ProviderSearchKind.Artists -> LazyColumn(Modifier.weight(1f)) {
                    items(
                        artistResults,
                        key = { "artist:${it.id.source.storageValue}:${it.id.value}" },
                    ) { artist ->
                        ProviderArtistRow(artist) { selectedArtist = artist }
                    }
                }
            }
        }
    }
}

@Composable
private fun ProviderSearchKindChip(
    title: String,
    selected: Boolean,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    Text(
        title,
        modifier = Modifier
            .meloXLiquidButton(
                shape = RoundedCornerShape(18.dp),
                surfaceColor = MaterialTheme.colorScheme.onBackground.copy(
                    alpha = if (selected) 0.10f else 0.04f,
                ),
            )
            .clickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = 15.dp, vertical = 9.dp),
        color = when {
            !enabled -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.28f)
            selected -> MaterialTheme.colorScheme.primary
            else -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.72f)
        },
        fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
        fontSize = 13.sp,
    )
}

@Composable
private fun ProviderAlbumDetailScreen(
    album: MusicAlbumSummary,
    onBack: () -> Unit,
) {
    BackHandler(onBack = onBack)
    val context = LocalContext.current
    val provider = remember(album.id.source) { MeloXMusicProviders.create(context).require(album.id.source) }
    val capability = provider as? AlbumCapability
    var detail by remember(album.id) { mutableStateOf<MusicAlbumDetail?>(null) }
    var loading by remember(album.id) { mutableStateOf(capability != null) }
    var error by remember(album.id) { mutableStateOf<String?>(null) }
    var playbackError by remember(album.id) { mutableStateOf<String?>(null) }

    LaunchedEffect(album.id, capability) {
        if (capability == null) return@LaunchedEffect
        loading = true
        error = null
        runCatching {
            withContext(Dispatchers.IO) { capability.albumDetail(album, page = 1, pageSize = 150) }
        }.onSuccess { detail = it }
            .onFailure { error = it.message ?: "专辑加载失败" }
        loading = false
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(
            start = 20.dp,
            top = 42.dp,
            end = 20.dp,
            bottom = MeloXBottomContentClearance,
        ),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        item {
            Text(
                "‹ 返回",
                modifier = Modifier.clickable(onClick = onBack).padding(vertical = 8.dp),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
            )
        }
        when {
            capability == null -> item { ProviderSimpleCard("暂不可用", "${album.id.source.displayName} 尚未实现专辑详情") }
            loading -> item {
                Box(Modifier.fillMaxWidth().padding(vertical = 48.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            error != null -> item { ProviderSimpleCard("加载失败", error.orEmpty()) }
            detail != null -> {
                val value = detail!!
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        AsyncImage(
                            model = value.summary.artworkUrl,
                            contentDescription = null,
                            modifier = Modifier.size(118.dp).clip(RoundedCornerShape(22.dp)),
                        )
                        Column(Modifier.weight(1f)) {
                            Text(
                                value.summary.title,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis,
                            )
                            val artistText = value.summary.artists.joinToString(" / ") { it.name }
                            if (artistText.isNotBlank()) {
                                Text(
                                    artistText,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.52f),
                                )
                            }
                            value.summary.releaseDate?.takeIf(String::isNotBlank)?.let {
                                Text(
                                    it,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.42f),
                                )
                            }
                        }
                    }
                }
                val tracks = value.tracks
                if (tracks.isNotEmpty()) {
                    item {
                        ProviderSimpleCard(
                            "播放全部",
                            "${value.totalTracks ?: tracks.size.toLong()} 首歌曲 · ${album.id.source.displayName}",
                            onClick = {
                                ProviderPlaybackCommands.playQueue(
                                    context = context,
                                    tracks = tracks,
                                    selectedTrackId = tracks.first().id,
                                    onFailure = { playbackError = it.message ?: "播放失败" },
                                )
                            },
                        )
                    }
                    items(
                        tracks,
                        key = { "album-track:${it.id.source.storageValue}:${it.id.value}" },
                    ) { track ->
                        ProviderTrackRow(track) {
                            ProviderPlaybackCommands.playQueue(
                                context = context,
                                tracks = tracks,
                                selectedTrackId = track.id,
                                onFailure = { playbackError = it.message ?: "播放失败" },
                            )
                        }
                    }
                } else {
                    item { ProviderSimpleCard("暂无歌曲", "这个专辑当前没有返回歌曲") }
                }
                playbackError?.let { message -> item { ProviderSimpleCard("播放失败", message) } }
            }
        }
    }
}

@Composable
private fun ProviderArtistDetailScreen(
    artist: MusicArtistSummary,
    onBack: () -> Unit,
) {
    BackHandler(onBack = onBack)
    val context = LocalContext.current
    val provider = remember(artist.id.source) { MeloXMusicProviders.create(context).require(artist.id.source) }
    val capability = provider as? ArtistCapability
    var detail by remember(artist.id) { mutableStateOf<MusicArtistDetail?>(null) }
    var loading by remember(artist.id) { mutableStateOf(capability != null) }
    var error by remember(artist.id) { mutableStateOf<String?>(null) }
    var playbackError by remember(artist.id) { mutableStateOf<String?>(null) }

    LaunchedEffect(artist.id, capability) {
        if (capability == null) return@LaunchedEffect
        loading = true
        error = null
        runCatching {
            withContext(Dispatchers.IO) { capability.artistDetail(artist, page = 1, pageSize = 150) }
        }.onSuccess { detail = it }
            .onFailure { error = it.message ?: "歌手加载失败" }
        loading = false
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(
            start = 20.dp,
            top = 42.dp,
            end = 20.dp,
            bottom = MeloXBottomContentClearance,
        ),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        item {
            Text(
                "‹ 返回",
                modifier = Modifier.clickable(onClick = onBack).padding(vertical = 8.dp),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
            )
        }
        when {
            capability == null -> item { ProviderSimpleCard("暂不可用", "${artist.id.source.displayName} 尚未实现歌手详情") }
            loading -> item {
                Box(Modifier.fillMaxWidth().padding(vertical = 48.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            error != null -> item { ProviderSimpleCard("加载失败", error.orEmpty()) }
            detail != null -> {
                val value = detail!!
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        AsyncImage(
                            model = value.summary.artworkUrl,
                            contentDescription = null,
                            modifier = Modifier.size(112.dp).clip(RoundedCornerShape(56.dp)),
                        )
                        Column(Modifier.weight(1f)) {
                            Text(value.summary.name, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                            val countText = buildList {
                                value.summary.songCount?.let { add("$it 首歌曲") }
                                value.summary.albumCount?.let { add("$it 张专辑") }
                            }.joinToString(" · ")
                            if (countText.isNotBlank()) {
                                Text(
                                    countText,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.48f),
                                )
                            }
                        }
                    }
                }
                value.summary.description?.takeIf(String::isNotBlank)?.let { description ->
                    item {
                        Text(
                            description,
                            maxLines = 5,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.52f),
                        )
                    }
                }
                val tracks = value.tracks
                if (tracks.isNotEmpty()) {
                    item { ProviderSectionTitle("热门歌曲") }
                    item {
                        ProviderSimpleCard(
                            "播放全部",
                            "${value.totalTracks ?: tracks.size.toLong()} 首已加载 · ${artist.id.source.displayName}",
                            onClick = {
                                ProviderPlaybackCommands.playQueue(
                                    context = context,
                                    tracks = tracks,
                                    selectedTrackId = tracks.first().id,
                                    onFailure = { playbackError = it.message ?: "播放失败" },
                                )
                            },
                        )
                    }
                    items(
                        tracks,
                        key = { "artist-track:${it.id.source.storageValue}:${it.id.value}" },
                    ) { track ->
                        ProviderTrackRow(track) {
                            ProviderPlaybackCommands.playQueue(
                                context = context,
                                tracks = tracks,
                                selectedTrackId = track.id,
                                onFailure = { playbackError = it.message ?: "播放失败" },
                            )
                        }
                    }
                } else {
                    item { ProviderSimpleCard("暂无歌曲", "这个歌手当前没有返回歌曲") }
                }
                playbackError?.let { message -> item { ProviderSimpleCard("播放失败", message) } }
            }
        }
    }
}

private sealed interface SearchPayload {
    data class Songs(
        val tracks: List<MusicTrack>,
        val failures: List<UnifiedMusicService.SearchFailure>,
    ) : SearchPayload

    data class Playlists(val items: List<MusicPlaylistSummary>) : SearchPayload
    data class Albums(val items: List<MusicAlbumSummary>) : SearchPayload
    data class Artists(val items: List<MusicArtistSummary>) : SearchPayload
}
