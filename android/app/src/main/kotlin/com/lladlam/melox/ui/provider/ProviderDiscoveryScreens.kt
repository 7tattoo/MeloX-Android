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
import com.lladlam.melox.core.music.model.MusicAccountSummary
import com.lladlam.melox.core.music.model.MusicHomeFeed
import com.lladlam.melox.core.music.model.MusicPlaylistDetail
import com.lladlam.melox.core.music.model.MusicPlaylistSummary
import com.lladlam.melox.core.music.model.MusicRankingSummary
import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.core.music.model.MusicTrack
import com.lladlam.melox.core.music.provider.HomeFeedCapability
import com.lladlam.melox.core.music.provider.MeloXMusicProviders
import com.lladlam.melox.core.music.provider.PlaylistCapability
import com.lladlam.melox.core.music.provider.UserLibraryCapability
import com.lladlam.melox.playback.ProviderPlaybackCommands
import com.lladlam.melox.ui.MeloXBottomContentClearance
import com.lladlam.melox.ui.glass.meloXLiquidButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Provider-backed discovery/library screens reuse MeloX's root presentation.
 * Provider differences stay in the returned content, not in a second navigation
 * vocabulary or a separate provider-only visual language.
 */
@Composable
fun ProviderHomeScreen(source: MusicSource) {
    ProviderDiscoveryFeedScreen(
        source = source,
        title = "首页",
        subtitle = when (source) {
            MusicSource.QQMusic -> "QQ音乐推荐"
            MusicSource.Kugou -> "酷狗音乐推荐"
            MusicSource.Netease -> "推荐"
        },
    )
}

@Composable
fun ProviderExploreScreen(source: MusicSource) {
    ProviderDiscoveryFeedScreen(
        source = source,
        title = "发现",
        subtitle = when (source) {
            MusicSource.QQMusic -> "QQ音乐 · 推荐歌单 · 新歌 · 排行榜"
            MusicSource.Kugou -> "酷狗音乐 · 推荐歌单 · 新歌 · 排行榜"
            MusicSource.Netease -> "发现"
        },
    )
}

@Composable
private fun ProviderDiscoveryFeedScreen(
    source: MusicSource,
    title: String,
    subtitle: String,
) {
    val context = LocalContext.current
    val provider = remember(source) { MeloXMusicProviders.create(context).require(source) }
    val home = provider as? HomeFeedCapability
    var feed by remember(source) { mutableStateOf<MusicHomeFeed?>(null) }
    var loading by remember(source) { mutableStateOf(home != null) }
    var error by remember(source) { mutableStateOf<String?>(null) }
    var playbackError by remember(source) { mutableStateOf<String?>(null) }
    var selectedPlaylist by remember(source) { mutableStateOf<MusicPlaylistSummary?>(null) }
    var selectedRanking by remember(source) { mutableStateOf<MusicRankingSummary?>(null) }

    selectedPlaylist?.let { playlist ->
        ProviderPlaylistDetailScreen(
            source = playlist.id.source,
            playlist = playlist,
            onBack = { selectedPlaylist = null },
        )
        return
    }
    selectedRanking?.let { ranking ->
        ProviderRankingDetailScreen(
            source = ranking.id.source,
            ranking = ranking,
            onBack = { selectedRanking = null },
        )
        return
    }

    LaunchedEffect(source, home) {
        if (home == null) return@LaunchedEffect
        loading = true
        error = null
        runCatching {
            withContext(Dispatchers.IO) {
                home.homeFeed(
                    playlistLimit = 12,
                    newSongLimit = 16,
                    rankingLimit = 10,
                )
            }
        }.onSuccess { feed = it }
            .onFailure { error = it.message ?: "无法加载 ${source.displayName} 内容" }
        loading = false
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 70.dp,
            end = 20.dp,
            bottom = MeloXBottomContentClearance,
        ),
        verticalArrangement = Arrangement.spacedBy(22.dp),
    ) {
        item {
            Text(
                title,
                fontSize = 40.sp,
                lineHeight = 46.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                subtitle,
                modifier = Modifier.padding(top = 2.dp),
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.46f),
            )
        }

        when {
            home == null -> item {
                ProviderSimpleCard("暂不可用", "当前音乐服务没有提供首页 Feed 能力")
            }
            loading -> item {
                Box(Modifier.fillMaxWidth().padding(vertical = 38.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            error != null -> item { ProviderSimpleCard("加载失败", error.orEmpty()) }
            else -> {
                val value = feed ?: MusicHomeFeed()

                if (value.recommendedPlaylists.isNotEmpty()) {
                    item {
                        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                            ProviderSectionTitle("推荐歌单")
                            LazyRow(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                                items(
                                    value.recommendedPlaylists,
                                    key = { "playlist:${it.id.source.storageValue}:${it.id.value}" },
                                ) { playlist ->
                                    ProviderPlaylistCard(playlist) { selectedPlaylist = playlist }
                                }
                            }
                        }
                    }
                }

                if (value.newSongs.isNotEmpty()) {
                    item {
                        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                            ProviderSectionTitle("最新歌曲")
                            LazyRow(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                                items(
                                    value.newSongs,
                                    key = { "newsong:${it.id.source.storageValue}:${it.id.value}" },
                                ) { track ->
                                    ProviderSongCard(track) {
                                        ProviderPlaybackCommands.playQueue(
                                            context = context,
                                            tracks = value.newSongs,
                                            selectedTrackId = track.id,
                                            onFailure = { failure ->
                                                playbackError = failure.message ?: "播放失败"
                                            },
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                if (value.rankings.isNotEmpty()) {
                    item {
                        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                            ProviderSectionTitle("排行榜")
                            LazyRow(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                                items(
                                    value.rankings,
                                    key = { "ranking:${it.id.source.storageValue}:${it.id.value}" },
                                ) { ranking ->
                                    ProviderRankingCard(ranking) { selectedRanking = ranking }
                                }
                            }
                        }
                    }
                }

                if (
                    value.recommendedPlaylists.isEmpty() &&
                    value.newSongs.isEmpty() &&
                    value.rankings.isEmpty()
                ) {
                    item {
                        ProviderSimpleCard(
                            "暂无内容",
                            "${source.displayName} 当前没有返回可展示的推荐内容",
                        )
                    }
                }
            }
        }

        playbackError?.let { message -> item { ProviderSimpleCard("播放失败", message) } }
    }
}

@Composable
fun ProviderLibraryScreen(source: MusicSource) {
    val context = LocalContext.current
    val provider = remember(source) { MeloXMusicProviders.create(context).require(source) }
    val library = provider as? UserLibraryCapability
    var account by remember(source) { mutableStateOf<MusicAccountSummary?>(null) }
    var playlists by remember(source) { mutableStateOf<List<MusicPlaylistSummary>>(emptyList()) }
    var loading by remember(source) { mutableStateOf(library != null) }
    var error by remember(source) { mutableStateOf<String?>(null) }
    var selectedPlaylist by remember(source) { mutableStateOf<MusicPlaylistSummary?>(null) }

    selectedPlaylist?.let { playlist ->
        ProviderPlaylistDetailScreen(
            source = playlist.id.source,
            playlist = playlist,
            onBack = { selectedPlaylist = null },
        )
        return
    }

    LaunchedEffect(source, library) {
        if (library == null) return@LaunchedEffect
        loading = true
        error = null
        runCatching {
            withContext(Dispatchers.IO) {
                val accountResult = library.accountSummary()
                val playlistsResult = if (accountResult != null) {
                    library.userPlaylists(page = 1, pageSize = 50).items
                } else {
                    emptyList()
                }
                accountResult to playlistsResult
            }
        }.onSuccess { (accountResult, playlistResult) ->
            account = accountResult
            playlists = playlistResult
        }.onFailure { error = it.message ?: "无法加载音乐库" }
        loading = false
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 70.dp,
            end = 20.dp,
            bottom = MeloXBottomContentClearance,
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            Text(
                "音乐库",
                fontSize = 40.sp,
                lineHeight = 46.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                source.displayName,
                modifier = Modifier.padding(top = 2.dp),
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.46f),
            )
        }

        when {
            library == null -> item {
                ProviderSimpleCard("暂不可用", "当前音乐服务没有提供个人音乐库能力")
            }
            loading -> item {
                Box(Modifier.fillMaxWidth().padding(vertical = 38.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            error != null -> item { ProviderSimpleCard("加载失败", error.orEmpty()) }
            account == null -> item {
                ProviderSimpleCard("尚未登录", "请先在设置中登录 ${source.displayName} 账号")
            }
            else -> {
                item { ProviderAccountCard(account!!) }
                if (playlists.isNotEmpty()) {
                    item { ProviderSectionTitle("我的歌单") }
                    items(
                        playlists,
                        key = { "library:${it.id.source.storageValue}:${it.id.value}" },
                    ) { playlist ->
                        ProviderPlaylistRow(playlist) { selectedPlaylist = playlist }
                    }
                } else {
                    item { ProviderSimpleCard("我的歌单", "当前账号没有返回可展示的歌单") }
                }
            }
        }
    }
}

@Composable
internal fun ProviderPlaylistDetailScreen(
    source: MusicSource,
    playlist: MusicPlaylistSummary,
    onBack: () -> Unit,
) {
    BackHandler(onBack = onBack)
    val context = LocalContext.current
    val provider = remember(source) { MeloXMusicProviders.create(context).require(source) }
    val capability = provider as? PlaylistCapability
    var detail by remember(playlist.id) { mutableStateOf<MusicPlaylistDetail?>(null) }
    var loading by remember(playlist.id) { mutableStateOf(capability != null) }
    var error by remember(playlist.id) { mutableStateOf<String?>(null) }
    var playbackError by remember(playlist.id) { mutableStateOf<String?>(null) }
    var trackQuery by remember(playlist.id) { mutableStateOf("") }

    LaunchedEffect(playlist.id, capability) {
        if (capability == null) return@LaunchedEffect
        loading = true
        error = null
        runCatching {
            withContext(Dispatchers.IO) {
                capability.playlistDetail(playlist, page = 1, pageSize = 150)
            }
        }.onSuccess { detail = it }
            .onFailure { error = it.message ?: "歌单加载失败" }
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
            PlaylistDetailHeader(
                title = value?.summary?.title ?: playlist.title,
                onBack = onBack,
            )
        }

        when {
            capability == null -> item {
                ProviderSimpleCard("暂不可用", "${source.displayName} 尚未实现歌单详情能力")
            }
            loading -> item {
                Box(Modifier.fillMaxWidth().padding(vertical = 52.dp), contentAlignment = Alignment.Center) {
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
                        value.summary.creatorName?.takeIf(String::isNotBlank)?.let { creator ->
                            Text(
                                creator,
                                modifier = Modifier.fillMaxWidth().padding(top = 5.dp),
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 15.sp,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.58f),
                            )
                        }
                        val metadata = buildList {
                            val total = value.total ?: value.summary.trackCount?.toLong() ?: tracks.size.toLong()
                            if (total > 0L) add("$total 首歌曲")
                            value.summary.playCount?.takeIf { it > 0L }?.let { add("$it 次播放") }
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
                                PlaylistDetailAction(
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
                                PlaylistDetailAction(
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

                value.summary.description?.takeIf(String::isNotBlank)?.let { description ->
                    item {
                        Text(
                            description,
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp, vertical = 2.dp),
                            maxLines = 5,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 13.sp,
                            lineHeight = 19.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f),
                        )
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
                                            "在歌单中搜索",
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
                            key = { _, track -> "detail:${track.id.source.storageValue}:${track.id.value}" },
                        ) { index, track ->
                            PlaylistTrackRow(
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
                    item { ProviderSimpleCard("暂无歌曲", "这个歌单当前没有返回可播放歌曲") }
                }

                playbackError?.let { message -> item { ProviderSimpleCard("播放失败", message) } }
            }
        }
    }
}

@Composable
private fun PlaylistDetailHeader(
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
private fun PlaylistDetailAction(
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
private fun PlaylistTrackRow(
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
