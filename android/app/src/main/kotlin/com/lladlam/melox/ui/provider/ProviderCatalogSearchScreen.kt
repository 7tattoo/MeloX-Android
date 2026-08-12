package com.lladlam.melox.ui.provider

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
    var trackQuery by remember(album.id) { mutableStateOf("") }

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

    val value = detail
    val tracks = value?.tracks.orEmpty()
    val filteredTracks = remember(tracks, trackQuery) {
        val normalized = trackQuery.trim().lowercase()
        if (normalized.isBlank()) {
            tracks
        } else {
            tracks.filter { track ->
                track.title.lowercase().contains(normalized) ||
                    track.artistText.lowercase().contains(normalized) ||
                    track.album?.name?.lowercase()?.contains(normalized) == true
            }
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 42.dp,
            end = 20.dp,
            bottom = MeloXBottomContentClearance,
        ),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        item {
            ProviderDetailHeader(
                title = value?.summary?.title ?: album.title,
                onBack = onBack,
            )
        }
        when {
            capability == null -> item {
                ProviderSimpleCard("暂不可用", "${album.id.source.displayName} 尚未实现专辑详情")
            }
            loading -> item {
                Box(Modifier.fillMaxWidth().padding(vertical = 48.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            error != null -> item { ProviderSimpleCard("加载失败", error.orEmpty()) }
            value != null -> {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(top = 2.dp, bottom = 4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        AsyncImage(
                            model = value.summary.artworkUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(210.dp).clip(RoundedCornerShape(16.dp)),
                        )
                        Text(
                            value.summary.title,
                            modifier = Modifier.fillMaxWidth().padding(top = 15.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            lineHeight = 29.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )
                        val artistText = value.summary.artists.joinToString(" / ") { it.name }
                        if (artistText.isNotBlank()) {
                            Text(
                                artistText,
                                modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                                textAlign = TextAlign.Center,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 15.sp,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.58f),
                            )
                        }
                        val metadata = buildList {
                            value.summary.releaseDate?.takeIf(String::isNotBlank)?.let(::add)
                            val total = value.totalTracks ?: value.summary.trackCount ?: tracks.size.toLong()
                            if (total > 0L) add("$total 首歌曲")
                        }.joinToString(" · ")
                        if (metadata.isNotBlank()) {
                            Text(
                                metadata,
                                modifier = Modifier.padding(top = 5.dp),
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.42f),
                            )
                        }
                        if (tracks.isNotEmpty()) {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(top = 14.dp),
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                            ) {
                                ProviderDetailAction(
                                    title = "播放",
                                    modifier = Modifier.weight(1f),
                                    onClick = {
                                        ProviderPlaybackCommands.playQueue(
                                            context = context,
                                            tracks = tracks,
                                            selectedTrackId = tracks.first().id,
                                            onFailure = { playbackError = it.message ?: "播放失败" },
                                        )
                                    },
                                )
                                ProviderDetailAction(
                                    title = "随机",
                                    modifier = Modifier.weight(1f),
                                    onClick = {
                                        val shuffled = tracks.shuffled()
                                        shuffled.firstOrNull()?.let { first ->
                                            ProviderPlaybackCommands.playQueue(
                                                context = context,
                                                tracks = shuffled,
                                                selectedTrackId = first.id,
                                                onFailure = { playbackError = it.message ?: "播放失败" },
                                            )
                                        }
                                    },
                                )
                            }
                        }
                    }
                }
                if (tracks.isNotEmpty()) {
                    item {
                        BasicTextField(
                            value = trackQuery,
                            onValueChange = { trackQuery = it },
                            singleLine = true,
                            textStyle = TextStyle(
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 16.sp,
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .meloXLiquidButton(
                                    shape = RoundedCornerShape(22.dp),
                                    surfaceColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f),
                                )
                                .padding(horizontal = 14.dp, vertical = 11.dp),
                            decorationBox = { inner ->
                                Box(contentAlignment = Alignment.CenterStart) {
                                    if (trackQuery.isBlank()) {
                                        Text(
                                            "在专辑中搜索",
                                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.40f),
                                            fontSize = 15.sp,
                                        )
                                    }
                                    inner()
                                }
                            },
                        )
                    }
                    item { ProviderSectionTitle("歌曲") }
                    if (filteredTracks.isEmpty()) {
                        item { ProviderSimpleCard("没有匹配歌曲", "换一个关键词试试") }
                    } else {
                        itemsIndexed(
                            filteredTracks,
                            key = { _, track -> "album-track:${track.id.source.storageValue}:${track.id.value}" },
                        ) { index, track ->
                            ProviderAlbumTrackRow(
                                index = index + 1,
                                track = track,
                                onClick = {
                                    ProviderPlaybackCommands.playQueue(
                                        context = context,
                                        tracks = tracks,
                                        selectedTrackId = track.id,
                                        onFailure = { playbackError = it.message ?: "播放失败" },
                                    )
                                },
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

    val value = detail
    val tracks = value?.tracks.orEmpty()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 42.dp,
            end = 20.dp,
            bottom = MeloXBottomContentClearance,
        ),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        item {
            ProviderDetailHeader(
                title = value?.summary?.name ?: artist.name,
                onBack = onBack,
            )
        }
        when {
            capability == null -> item {
                ProviderSimpleCard("暂不可用", "${artist.id.source.displayName} 尚未实现歌手详情")
            }
            loading -> item {
                Box(Modifier.fillMaxWidth().padding(vertical = 48.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            error != null -> item { ProviderSimpleCard("加载失败", error.orEmpty()) }
            value != null -> {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(top = 2.dp, bottom = 4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        AsyncImage(
                            model = value.summary.artworkUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(176.dp).clip(CircleShape),
                        )
                        Text(
                            value.summary.name,
                            modifier = Modifier.fillMaxWidth().padding(top = 13.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 28.sp,
                            lineHeight = 33.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )
                        val countText = buildList {
                            value.summary.songCount?.let { add("$it 首歌曲") }
                            value.summary.albumCount?.let { add("$it 张专辑") }
                        }.joinToString(" · ")
                        if (countText.isNotBlank()) {
                            Text(
                                countText,
                                modifier = Modifier.padding(top = 5.dp),
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.48f),
                            )
                        }
                        if (tracks.isNotEmpty()) {
                            ProviderDetailAction(
                                title = "播放热门歌曲",
                                modifier = Modifier.fillMaxWidth().padding(top = 14.dp),
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
                    }
                }
                value.summary.description?.takeIf(String::isNotBlank)?.let { description ->
                    item {
                        Text(
                            description,
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp, vertical = 2.dp),
                            maxLines = 6,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 13.sp,
                            lineHeight = 19.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f),
                        )
                    }
                }
                if (tracks.isNotEmpty()) {
                    item { ProviderSectionTitle("热门歌曲") }
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

@Composable
private fun ProviderDetailHeader(
    title: String,
    onBack: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth().height(58.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .meloXLiquidButton(shape = CircleShape)
                .clickable(onClick = onBack),
            contentAlignment = Alignment.Center,
        ) {
            Text("‹", fontSize = 30.sp, lineHeight = 30.sp)
        }
        Spacer(Modifier.size(12.dp))
        Text(
            title,
            modifier = Modifier.weight(1f),
            fontSize = 24.sp,
            lineHeight = 29.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun ProviderDetailAction(
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .meloXLiquidButton(
                shape = RoundedCornerShape(22.dp),
                surfaceColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.055f),
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 11.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(title, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun ProviderAlbumTrackRow(
    index: Int,
    track: MusicTrack,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            index.toString(),
            modifier = Modifier.size(34.dp),
            textAlign = TextAlign.Center,
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
        )
        Column(Modifier.weight(1f)) {
            Text(
                track.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                track.artistText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.46f),
            )
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
