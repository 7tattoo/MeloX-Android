package com.lladlam.melox.ui.provider

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
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
import com.lladlam.melox.core.music.provider.HomeFeedCapability
import com.lladlam.melox.core.music.provider.MeloXMusicProviders
import com.lladlam.melox.core.music.provider.PlaylistCapability
import com.lladlam.melox.core.music.provider.UserLibraryCapability
import com.lladlam.melox.playback.ProviderPlaybackCommands
import com.lladlam.melox.ui.MeloXBottomContentClearance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Provider-backed discovery/library screens reuse the same visual grammar that
 * MeloX already migrated from iOS: 40sp large titles, 25sp section titles,
 * horizontal media strips, plain track rows and content-layer materials.
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
        title = when (source) {
            MusicSource.QQMusic -> "发现"
            MusicSource.Kugou -> "乐库"
            MusicSource.Netease -> "发现"
        },
        subtitle = when (source) {
            MusicSource.QQMusic -> "推荐歌单 · 新歌 · 排行榜"
            MusicSource.Kugou -> "乐库推荐 · 新歌 · 排行榜"
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
                if (source == MusicSource.Netease) "音乐库" else "我的",
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

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 42.dp,
            end = 20.dp,
            bottom = MeloXBottomContentClearance,
        ),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "‹",
                    modifier = Modifier.clickable(onClick = onBack).padding(end = 10.dp),
                    fontSize = 44.sp,
                    lineHeight = 44.sp,
                )
                Text(
                    playlist.title,
                    fontSize = 30.sp,
                    lineHeight = 36.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
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
            error != null -> item { ProviderSimpleCard("加载失败", error.orEmpty(), onClick = onBack) }
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
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(156.dp).clip(RoundedCornerShape(22.dp)),
                        )
                        Column(Modifier.weight(1f)) {
                            Text(
                                value.summary.title,
                                fontSize = 20.sp,
                                lineHeight = 25.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis,
                            )
                            value.summary.creatorName?.takeIf(String::isNotBlank)?.let {
                                Spacer(Modifier.height(7.dp))
                                Text(
                                    it,
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.52f),
                                )
                            }
                            val count = value.total ?: value.tracks.size.toLong()
                            Spacer(Modifier.height(4.dp))
                            Text(
                                "$count 首歌曲",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.42f),
                            )

                            if (value.tracks.isNotEmpty()) {
                                Text(
                                    "▶  播放全部",
                                    modifier = Modifier
                                        .padding(top = 15.dp)
                                        .clip(RoundedCornerShape(22.dp))
                                        .background(MaterialTheme.colorScheme.primary)
                                        .clickable {
                                            ProviderPlaybackCommands.playQueue(
                                                context = context,
                                                tracks = value.tracks,
                                                selectedTrackId = value.tracks.first().id,
                                                onFailure = { failure ->
                                                    playbackError = failure.message ?: "播放失败"
                                                },
                                            )
                                        }
                                        .padding(horizontal = 16.dp, vertical = 10.dp),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        }
                    }
                }

                value.summary.description?.takeIf(String::isNotBlank)?.let { description ->
                    item {
                        Text(
                            description,
                            maxLines = 4,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 13.sp,
                            lineHeight = 19.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.52f),
                        )
                    }
                }

                if (value.tracks.isNotEmpty()) {
                    items(
                        value.tracks,
                        key = { "detail:${it.id.source.storageValue}:${it.id.value}" },
                    ) { track ->
                        ProviderTrackRow(track) {
                            ProviderPlaybackCommands.playQueue(
                                context = context,
                                tracks = value.tracks,
                                selectedTrackId = track.id,
                                onFailure = { failure ->
                                    playbackError = failure.message ?: "播放失败"
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
