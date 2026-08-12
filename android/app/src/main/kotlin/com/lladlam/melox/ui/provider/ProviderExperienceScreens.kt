package com.lladlam.melox.ui.provider

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.lladlam.melox.core.account.NeteaseSessionStore
import com.lladlam.melox.core.music.model.MusicAccountSummary
import com.lladlam.melox.core.music.model.MusicHomeFeed
import com.lladlam.melox.core.music.model.MusicPlaylistSummary
import com.lladlam.melox.core.music.model.MusicRankingSummary
import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.core.music.model.MusicTrack
import com.lladlam.melox.core.music.provider.HomeFeedCapability
import com.lladlam.melox.core.music.provider.MeloXMusicProviders
import com.lladlam.melox.core.music.provider.MusicProviderSelectionStore
import com.lladlam.melox.core.music.provider.SearchCapability
import com.lladlam.melox.core.music.provider.UserLibraryCapability
import com.lladlam.melox.core.provider.kugou.KugouSessionStore
import com.lladlam.melox.core.provider.qqmusic.QQMusicSessionStore
import com.lladlam.melox.playback.ProviderPlaybackCommands
import com.lladlam.melox.ui.MeloXBottomContentClearance
import com.lladlam.melox.ui.account.KugouLoginScreen
import com.lladlam.melox.ui.account.QQMusicLoginScreen
import com.lladlam.melox.ui.glass.meloXLiquidButton
import com.lladlam.melox.ui.settings.SettingsScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
            MusicSource.QQMusic -> "歌单 · 新歌 · 排行榜"
            MusicSource.Kugou -> "乐库 · 新歌 · 排行榜"
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
        contentPadding = androidx.compose.foundation.layout.PaddingValues(
            start = 20.dp,
            top = 48.dp,
            end = 20.dp,
            bottom = MeloXBottomContentClearance,
        ),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        item {
            Text(source.displayName, fontSize = 14.sp, color = MaterialTheme.colorScheme.primary)
            Text(title, fontSize = 40.sp, fontWeight = FontWeight.Bold)
            Text(
                subtitle,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.48f),
            )
        }

        if (home == null) {
            item { ProviderSimpleCard("暂不可用", "当前音乐服务没有提供首页 Feed Capability") }
        } else if (loading) {
            item {
                Box(Modifier.fillMaxWidth().padding(vertical = 38.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        } else if (error != null) {
            item {
                ProviderSimpleCard("加载失败", error.orEmpty())
            }
        } else {
            val value = feed ?: MusicHomeFeed()
            if (value.recommendedPlaylists.isNotEmpty()) {
                item { ProviderSectionTitle("推荐歌单") }
                item {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(
                            value.recommendedPlaylists,
                            key = { "playlist:${it.id.source.storageValue}:${it.id.value}" },
                        ) { playlist ->
                            ProviderPlaylistCard(playlist)
                        }
                    }
                }
            }

            if (value.newSongs.isNotEmpty()) {
                item { ProviderSectionTitle("最新歌曲") }
                items(
                    value.newSongs,
                    key = { "newsong:${it.id.source.storageValue}:${it.id.value}" },
                ) { track ->
                    ProviderTrackRow(track) {
                        ProviderPlaybackCommands.playQueue(
                            context = context,
                            tracks = value.newSongs,
                            selectedTrackId = track.id,
                            onFailure = { failure -> playbackError = failure.message ?: "播放失败" },
                        )
                    }
                }
            }

            if (value.rankings.isNotEmpty()) {
                item { ProviderSectionTitle("排行榜") }
                items(
                    value.rankings,
                    key = { "ranking:${it.id.source.storageValue}:${it.id.value}" },
                ) { ranking ->
                    ProviderRankingCard(ranking)
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

        playbackError?.let { message ->
            item { ProviderSimpleCard("播放失败", message) }
        }
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
        contentPadding = androidx.compose.foundation.layout.PaddingValues(
            start = 20.dp,
            top = 48.dp,
            end = 20.dp,
            bottom = MeloXBottomContentClearance,
        ),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            Text(source.displayName, fontSize = 14.sp, color = MaterialTheme.colorScheme.primary)
            Text(if (source == MusicSource.Netease) "音乐库" else "我的", fontSize = 40.sp, fontWeight = FontWeight.Bold)
        }

        when {
            library == null -> item {
                ProviderSimpleCard("暂不可用", "当前音乐服务没有提供个人音乐库 Capability")
            }
            loading -> item {
                Box(Modifier.fillMaxWidth().padding(vertical = 38.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            error != null -> item { ProviderSimpleCard("加载失败", error.orEmpty()) }
            account == null -> item {
                ProviderSimpleCard(
                    "尚未登录",
                    "请先在设置中登录 ${source.displayName} 账号",
                )
            }
            else -> {
                item { ProviderAccountCard(account!!) }
                if (playlists.isNotEmpty()) {
                    item { ProviderSectionTitle("我的歌单") }
                    items(
                        playlists,
                        key = { "library:${it.id.source.storageValue}:${it.id.value}" },
                    ) { playlist ->
                        ProviderLibraryPlaylistRow(playlist)
                    }
                } else {
                    item {
                        ProviderSimpleCard(
                            "我的歌单",
                            "当前账号没有返回可展示的歌单",
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProviderSearchScreen(source: MusicSource) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val provider = remember(source) { MeloXMusicProviders.create(context).require(source) }
    val search = provider as? SearchCapability
    var query by remember(source) { mutableStateOf("") }
    var results by remember(source) { mutableStateOf<List<MusicTrack>>(emptyList()) }
    var loading by remember(source) { mutableStateOf(false) }
    var error by remember(source) { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp)
            .padding(top = 44.dp, bottom = MeloXBottomContentClearance),
    ) {
        Text("${source.displayName} · 搜索", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
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
                    Text("搜索歌曲", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f))
                }
                BasicTextField(
                    value = query,
                    onValueChange = { query = it },
                    singleLine = true,
                    textStyle = androidx.compose.ui.text.TextStyle(
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
                modifier = Modifier.clickable(enabled = query.isNotBlank() && !loading) {
                    val capability = search ?: return@clickable
                    loading = true
                    error = null
                    scope.launch {
                        runCatching {
                            withContext(Dispatchers.IO) { capability.searchSongs(query, page = 1, pageSize = 40).items }
                        }.onSuccess { results = it }
                            .onFailure { error = it.message ?: "搜索失败" }
                        loading = false
                    }
                },
            )
        }
        Spacer(Modifier.height(14.dp))
        when {
            search == null -> Text("当前 Provider 尚未实现搜索能力")
            loading -> Box(Modifier.fillMaxWidth().padding(28.dp), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            error != null -> Text(error.orEmpty(), color = MaterialTheme.colorScheme.error)
            else -> LazyColumn(Modifier.weight(1f)) {
                items(results, key = { "${it.id.source.storageValue}:${it.id.value}" }) { track ->
                    ProviderTrackRow(track) {
                        ProviderPlaybackCommands.playQueue(
                            context = context,
                            tracks = results,
                            selectedTrackId = track.id,
                            onFailure = { failure -> error = failure.message ?: "播放失败" },
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProviderSettingsHub(
    currentSource: MusicSource,
    onSourceSelected: (MusicSource) -> Unit,
    neteaseSession: NeteaseSessionStore,
    onNeteaseLogin: () -> Unit,
) {
    val context = LocalContext.current
    var showNeteaseSettings by remember(currentSource) { mutableStateOf(false) }
    var showQQLogin by remember(currentSource) { mutableStateOf(false) }
    var showKugouLogin by remember(currentSource) { mutableStateOf(false) }
    var loginRevision by remember(currentSource) { mutableStateOf(0) }
    var unifiedEnabled by remember { mutableStateOf(MusicProviderSelectionStore.unifiedEnabled(context)) }
    var automaticFallback by remember { mutableStateOf(MusicProviderSelectionStore.automaticFallbackEnabled(context)) }

    if (showNeteaseSettings && currentSource == MusicSource.Netease) {
        SettingsScreen(
            session = neteaseSession,
            onLogin = onNeteaseLogin,
        )
        return
    }
    if (showQQLogin && currentSource == MusicSource.QQMusic) {
        QQMusicLoginScreen(
            onDismiss = { showQQLogin = false },
            onLoggedIn = {
                showQQLogin = false
                loginRevision += 1
            },
        )
        return
    }
    if (showKugouLogin && currentSource == MusicSource.Kugou) {
        KugouLoginScreen(
            onDismiss = { showKugouLogin = false },
            onLoggedIn = {
                showKugouLogin = false
                loginRevision += 1
            },
        )
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
            .padding(top = 44.dp, bottom = MeloXBottomContentClearance),
    ) {
        Text("设置", fontSize = 40.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(22.dp))
        Text("音乐服务", fontSize = 13.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
        Spacer(Modifier.height(8.dp))
        MusicSource.entries.forEach { source ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .meloXLiquidButton(
                        shape = RoundedCornerShape(24.dp),
                        surfaceColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.045f),
                    )
                    .clickable { onSourceSelected(source) }
                    .padding(horizontal = 16.dp, vertical = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(Modifier.weight(1f)) {
                    Text(source.displayName, fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
                    Text(
                        when (source) {
                            MusicSource.Netease -> "完整 MeloX iOS 迁移体验"
                            MusicSource.QQMusic -> "QQ音乐 Provider"
                            MusicSource.Kugou -> "酷狗音乐 Provider"
                        },
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.48f),
                    )
                }
                Text(if (source == currentSource) "✓" else "", color = MaterialTheme.colorScheme.primary, fontSize = 20.sp)
            }
            Spacer(Modifier.height(10.dp))
        }

        Spacer(Modifier.height(12.dp))
        ProviderSettingToggle(
            title = "跨平台音乐聚合（实验性）",
            subtitle = "默认关闭；仅在你主动开启后组合多个音乐服务",
            checked = unifiedEnabled,
            onCheckedChange = {
                unifiedEnabled = it
                MusicProviderSelectionStore.setUnifiedEnabled(context, it)
                if (!it) {
                    automaticFallback = false
                    MusicProviderSelectionStore.setAutomaticFallbackEnabled(context, false)
                }
            },
        )
        Spacer(Modifier.height(10.dp))
        ProviderSettingToggle(
            title = "自动选择其他来源",
            subtitle = "默认关闭；只有开启聚合后才能单独选择",
            checked = automaticFallback,
            enabled = unifiedEnabled,
            onCheckedChange = {
                automaticFallback = it
                MusicProviderSelectionStore.setAutomaticFallbackEnabled(context, it)
            },
        )

        Spacer(Modifier.height(20.dp))
        Text("当前服务", fontSize = 13.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
        Spacer(Modifier.height(8.dp))
        when (currentSource) {
            MusicSource.Netease -> {
                ProviderSimpleCard(
                    "网易云音乐账号",
                    neteaseSession.profile?.nickname
                        ?: if (neteaseSession.isLoggedIn) "已登录" else "未登录",
                    onClick = if (neteaseSession.isLoggedIn) null else onNeteaseLogin,
                )
                Spacer(Modifier.height(10.dp))
                ProviderSimpleCard(
                    "完整 MeloX 设置",
                    "播放、歌词、云盘、心动模式、一起听等网易云迁移设置",
                    onClick = { showNeteaseSettings = true },
                )
            }
            MusicSource.QQMusic -> {
                val session = remember(loginRevision, currentSource) { QQMusicSessionStore.read(context) }
                ProviderSimpleCard(
                    "QQ音乐账号",
                    if (session.isLoggedIn) "QQ ${session.uin}" else "未登录 · 点击登录",
                    onClick = if (session.isLoggedIn) null else ({ showQQLogin = true }),
                )
                Spacer(Modifier.height(10.dp))
                ProviderSimpleCard("当前能力", "搜索 · 歌词 · 播放 · 推荐 · 排行榜 · 我的歌单")
            }
            MusicSource.Kugou -> {
                val session = remember(loginRevision, currentSource) { KugouSessionStore.read(context) }
                ProviderSimpleCard(
                    "酷狗音乐账号",
                    if (session.isLoggedIn) "用户 ${session.userId}" else "未登录 · 点击扫码登录",
                    onClick = if (session.isLoggedIn) null else ({ showKugouLogin = true }),
                )
                Spacer(Modifier.height(10.dp))
                ProviderSimpleCard("当前能力", "搜索 · KRC逐字歌词 · 播放 · 乐库推荐 · 排行榜 · 我的歌单")
            }
        }
    }
}

@Composable
private fun ProviderSectionTitle(title: String) {
    Text(
        title,
        modifier = Modifier.padding(top = 6.dp),
        fontSize = 23.sp,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
private fun ProviderPlaylistCard(playlist: MusicPlaylistSummary) {
    Column(modifier = Modifier.width(126.dp)) {
        AsyncImage(
            model = playlist.artworkUrl,
            contentDescription = null,
            modifier = Modifier
                .size(126.dp)
                .clip(RoundedCornerShape(18.dp)),
        )
        Spacer(Modifier.height(7.dp))
        Text(
            playlist.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
        )
        val secondary = playlist.creatorName
            ?: playlist.trackCount?.let { "$it 首" }
            ?: playlist.description
        secondary?.takeIf(String::isNotBlank)?.let {
            Text(
                it,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f),
            )
        }
    }
}

@Composable
private fun ProviderRankingCard(ranking: MusicRankingSummary) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .meloXLiquidButton(
                shape = RoundedCornerShape(22.dp),
                surfaceColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.04f),
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        AsyncImage(
            model = ranking.artworkUrl,
            contentDescription = null,
            modifier = Modifier.size(64.dp).clip(RoundedCornerShape(14.dp)),
        )
        Column(Modifier.weight(1f)) {
            Text(ranking.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            ranking.subtitle?.takeIf(String::isNotBlank)?.let {
                Spacer(Modifier.height(3.dp))
                Text(
                    it,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.48f),
                )
            }
        }
    }
}

@Composable
private fun ProviderAccountCard(account: MusicAccountSummary) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .meloXLiquidButton(
                shape = RoundedCornerShape(24.dp),
                surfaceColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.045f),
            )
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(13.dp),
    ) {
        AsyncImage(
            model = account.avatarUrl,
            contentDescription = null,
            modifier = Modifier.size(58.dp).clip(RoundedCornerShape(20.dp)),
        )
        Column(Modifier.weight(1f)) {
            Text(account.displayName, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            account.subtitle?.takeIf(String::isNotBlank)?.let {
                Text(
                    it,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.48f),
                )
            }
        }
    }
}

@Composable
private fun ProviderLibraryPlaylistRow(playlist: MusicPlaylistSummary) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        AsyncImage(
            model = playlist.artworkUrl,
            contentDescription = null,
            modifier = Modifier.size(58.dp).clip(RoundedCornerShape(13.dp)),
        )
        Column(Modifier.weight(1f)) {
            Text(
                playlist.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.SemiBold,
            )
            val secondary = listOfNotNull(
                playlist.trackCount?.let { "$it 首" },
                playlist.creatorName,
            ).joinToString(" · ")
            if (secondary.isNotBlank()) {
                Text(
                    secondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.46f),
                )
            }
        }
    }
}

@Composable
private fun ProviderTrackRow(track: MusicTrack, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        AsyncImage(
            model = track.artworkUrl,
            contentDescription = null,
            modifier = Modifier.size(54.dp).clip(RoundedCornerShape(10.dp)),
        )
        Column(Modifier.weight(1f)) {
            Text(track.title, maxLines = 1, overflow = TextOverflow.Ellipsis, fontWeight = FontWeight.SemiBold)
            Text(
                listOf(track.artistText, track.album?.name).filterNot { it.isNullOrBlank() }.joinToString(" · "),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            )
        }
        Text("▶", color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun ProviderSimpleCard(
    title: String,
    subtitle: String,
    onClick: (() -> Unit)? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .meloXLiquidButton(
                shape = RoundedCornerShape(24.dp),
                surfaceColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.045f),
            )
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(16.dp),
    ) {
        Text(title, fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(3.dp))
        Text(subtitle, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.48f))
    }
}

@Composable
private fun ProviderSettingToggle(
    title: String,
    subtitle: String,
    checked: Boolean,
    enabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .meloXLiquidButton(
                shape = RoundedCornerShape(24.dp),
                surfaceColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.045f),
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.SemiBold)
            Text(subtitle, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.48f))
        }
        Switch(checked = checked, onCheckedChange = onCheckedChange, enabled = enabled)
    }
}
