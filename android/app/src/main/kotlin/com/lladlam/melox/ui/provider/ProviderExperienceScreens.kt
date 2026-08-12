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
import androidx.compose.foundation.lazy.LazyColumn
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
import com.lladlam.melox.core.music.experience.HomeSectionKind
import com.lladlam.melox.core.music.experience.MusicExperiences
import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.core.music.model.MusicTrack
import com.lladlam.melox.core.music.provider.MeloXMusicProviders
import com.lladlam.melox.core.music.provider.MusicProviderSelectionStore
import com.lladlam.melox.core.music.provider.SearchCapability
import com.lladlam.melox.core.provider.kugou.KugouSessionStore
import com.lladlam.melox.core.provider.qqmusic.QQMusicSessionStore
import com.lladlam.melox.playback.ProviderPlaybackCommands
import com.lladlam.melox.ui.MeloXBottomContentClearance
import com.lladlam.melox.ui.glass.meloXLiquidButton
import com.lladlam.melox.ui.settings.SettingsScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ProviderHomeScreen(source: MusicSource) {
    val experience = MusicExperiences.forSource(source)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
            .padding(top = 48.dp, bottom = MeloXBottomContentClearance),
    ) {
        Text(source.displayName, fontSize = 14.sp, color = MaterialTheme.colorScheme.primary)
        Text("首页", fontSize = 40.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(22.dp))
        experience.homeSections.forEach { kind ->
            ProviderSectionCard(kind)
            Spacer(Modifier.height(14.dp))
        }
    }
}

@Composable
fun ProviderExploreScreen(source: MusicSource) {
    val title = when (source) {
        MusicSource.QQMusic -> "发现"
        MusicSource.Kugou -> "乐库"
        MusicSource.Netease -> "发现"
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
            .padding(top = 48.dp, bottom = MeloXBottomContentClearance),
    ) {
        Text(source.displayName, fontSize = 14.sp, color = MaterialTheme.colorScheme.primary)
        Text(title, fontSize = 40.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(20.dp))
        val descriptions = when (source) {
            MusicSource.QQMusic -> listOf("歌单与新歌", "排行榜", "电台与推荐")
            MusicSource.Kugou -> listOf("推荐与歌单", "排行榜", "电台与乐库")
            MusicSource.Netease -> listOf("推荐", "歌单", "排行榜")
        }
        descriptions.forEach { label ->
            ProviderSimpleCard(label, "内容由 ${source.displayName} Provider 按平台实际能力提供")
            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
fun ProviderLibraryScreen(source: MusicSource) {
    val context = LocalContext.current
    val accountText = when (source) {
        MusicSource.QQMusic -> QQMusicSessionStore.read(context).let {
            if (it.isLoggedIn) "QQ ${it.uin}" else "尚未登录 QQ音乐"
        }
        MusicSource.Kugou -> KugouSessionStore.read(context).let {
            if (it.isLoggedIn) "酷狗用户 ${it.userId}" else "尚未登录酷狗音乐"
        }
        MusicSource.Netease -> "网易云音乐库"
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
            .padding(top = 48.dp, bottom = MeloXBottomContentClearance),
    ) {
        Text(source.displayName, fontSize = 14.sp, color = MaterialTheme.colorScheme.primary)
        Text(if (source == MusicSource.Netease) "音乐库" else "我的", fontSize = 40.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(20.dp))
        ProviderSimpleCard("账户", accountText)
        Spacer(Modifier.height(12.dp))
        when (source) {
            MusicSource.QQMusic -> {
                ProviderSimpleCard("收藏与歌单", "仅显示 QQ音乐实际提供并已接入的个人内容")
                Spacer(Modifier.height(12.dp))
                ProviderSimpleCard("数字内容", "QQ音乐特有内容将在对应 Capability 接入后显示")
            }
            MusicSource.Kugou -> {
                ProviderSimpleCard("收藏与歌单", "仅显示酷狗音乐实际提供并已接入的个人内容")
                Spacer(Modifier.height(12.dp))
                ProviderSimpleCard("云端内容", "酷狗 Provider 独有能力不会伪装成网易云功能")
            }
            MusicSource.Netease -> Unit
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
    var unifiedEnabled by remember { mutableStateOf(MusicProviderSelectionStore.unifiedEnabled(context)) }
    var automaticFallback by remember { mutableStateOf(MusicProviderSelectionStore.automaticFallbackEnabled(context)) }

    if (showNeteaseSettings && currentSource == MusicSource.Netease) {
        SettingsScreen(
            session = neteaseSession,
            onLogin = onNeteaseLogin,
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
                val session = QQMusicSessionStore.read(context)
                ProviderSimpleCard("QQ音乐账号", if (session.isLoggedIn) "QQ ${session.uin}" else "未登录")
                Spacer(Modifier.height(10.dp))
                ProviderSimpleCard("当前能力", "搜索 · 歌词 · 播放；其他 QQ音乐能力按 Capability 逐步显示")
            }
            MusicSource.Kugou -> {
                val session = KugouSessionStore.read(context)
                ProviderSimpleCard("酷狗音乐账号", if (session.isLoggedIn) "用户 ${session.userId}" else "未登录")
                Spacer(Modifier.height(10.dp))
                ProviderSimpleCard("当前能力", "搜索 · KRC 逐字歌词 · 播放；其他酷狗能力按 Capability 逐步显示")
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
private fun ProviderSectionCard(kind: HomeSectionKind) {
    val label = when (kind) {
        HomeSectionKind.QuickActions -> "快捷入口"
        HomeSectionKind.Recommendations -> "为你推荐"
        HomeSectionKind.Playlists -> "歌单"
        HomeSectionKind.NewSongs -> "新歌"
        HomeSectionKind.Rankings -> "排行榜"
        HomeSectionKind.Artists -> "歌手"
        HomeSectionKind.Radio -> "电台"
        HomeSectionKind.Podcasts -> "播客"
    }
    ProviderSimpleCard(label, "由当前音乐服务的 Experience 与 Capability 提供")
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
