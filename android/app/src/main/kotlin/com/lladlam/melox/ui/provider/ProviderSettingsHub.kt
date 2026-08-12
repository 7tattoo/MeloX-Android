package com.lladlam.melox.ui.provider

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lladlam.melox.core.account.NeteaseSessionStore
import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.core.music.provider.MusicProviderSelectionStore
import com.lladlam.melox.core.music.provider.ProviderAccountManager
import com.lladlam.melox.ui.MeloXBottomContentClearance
import com.lladlam.melox.ui.account.KugouLoginScreen
import com.lladlam.melox.ui.account.QQMusicLoginScreen
import com.lladlam.melox.ui.glass.meloXLiquidButton
import com.lladlam.melox.ui.settings.SettingsScreen

private enum class ProviderAccountAction {
    Logout,
    SwitchAccount,
}

private data class PendingProviderAccountAction(
    val source: MusicSource,
    val action: ProviderAccountAction,
)

@Composable
fun ProviderSettingsHub(
    currentSource: MusicSource,
    onSourceSelected: (MusicSource) -> Unit,
    neteaseSession: NeteaseSessionStore,
    onNeteaseLogin: () -> Unit,
) {
    val context = LocalContext.current
    val accountManager = remember(neteaseSession) {
        ProviderAccountManager(context, neteaseSessionStore = neteaseSession)
    }

    var showNeteaseSettings by remember(currentSource) { mutableStateOf(false) }
    var showQQLogin by remember(currentSource) { mutableStateOf(false) }
    var showKugouLogin by remember(currentSource) { mutableStateOf(false) }
    var loginRevision by remember(currentSource) { mutableStateOf(0) }
    var pendingAccountAction by remember { mutableStateOf<PendingProviderAccountAction?>(null) }

    var unifiedEnabled by remember {
        mutableStateOf(MusicProviderSelectionStore.unifiedEnabled(context))
    }
    var unifiedSources by remember {
        mutableStateOf(MusicProviderSelectionStore.unifiedSources(context))
    }

    // Automatic playback source fallback is intentionally not exposed as active
    // until the playback resolver has a rights-aware implementation.
    LaunchedEffect(Unit) {
        MusicProviderSelectionStore.setAutomaticFallbackEnabled(context, false)
    }

    if (showNeteaseSettings && currentSource == MusicSource.Netease) {
        SettingsScreen(session = neteaseSession, onLogin = onNeteaseLogin)
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

    pendingAccountAction?.let { pending ->
        val actionTitle = when (pending.action) {
            ProviderAccountAction.Logout -> "退出 ${pending.source.displayName}？"
            ProviderAccountAction.SwitchAccount -> "切换 ${pending.source.displayName} 账号？"
        }
        val actionBody = when (pending.action) {
            ProviderAccountAction.Logout -> "只会清除 MeloX 本机保存的该平台登录态，其他音乐服务不会受影响。"
            ProviderAccountAction.SwitchAccount -> "会先清除当前账号的本机登录态，然后重新打开该平台登录流程。"
        }
        AlertDialog(
            onDismissRequest = { pendingAccountAction = null },
            title = { Text(actionTitle) },
            text = { Text(actionBody) },
            confirmButton = {
                TextButton(
                    onClick = {
                        when (pending.action) {
                            ProviderAccountAction.Logout -> accountManager.logout(pending.source)
                            ProviderAccountAction.SwitchAccount -> accountManager.prepareAccountSwitch(pending.source)
                        }
                        loginRevision += 1
                        pendingAccountAction = null
                        if (pending.action == ProviderAccountAction.SwitchAccount) {
                            when (pending.source) {
                                MusicSource.QQMusic -> showQQLogin = true
                                MusicSource.Kugou -> showKugouLogin = true
                                MusicSource.Netease -> onNeteaseLogin()
                            }
                        }
                    },
                ) {
                    Text(if (pending.action == ProviderAccountAction.Logout) "退出" else "继续")
                }
            },
            dismissButton = {
                TextButton(onClick = { pendingAccountAction = null }) { Text("取消") }
            },
        )
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

        Text(
            "音乐服务",
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
        )
        Spacer(Modifier.height(8.dp))
        MusicSource.entries.forEach { source ->
            ProviderSourceSelectionRow(
                source = source,
                selected = source == currentSource,
                accountState = accountManager.state(source),
                onClick = { onSourceSelected(source) },
            )
            Spacer(Modifier.height(10.dp))
        }

        Spacer(Modifier.height(12.dp))
        Text(
            "实验性功能",
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
        )
        Spacer(Modifier.height(8.dp))
        ProviderSettingToggle(
            title = "跨平台音乐聚合",
            subtitle = "默认关闭；开启后也只请求下方明确勾选的平台",
            checked = unifiedEnabled,
            onCheckedChange = { enabled ->
                unifiedEnabled = enabled
                MusicProviderSelectionStore.setUnifiedEnabled(context, enabled)
                unifiedSources = MusicProviderSelectionStore.unifiedSources(context)
            },
        )

        if (unifiedEnabled) {
            Spacer(Modifier.height(10.dp))
            Text(
                "参与聚合的平台",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            )
            Spacer(Modifier.height(6.dp))
            MusicSource.entries.forEach { source ->
                val account = accountManager.state(source)
                ProviderSettingToggle(
                    title = source.displayName,
                    subtitle = when {
                        source == currentSource && account.loggedIn -> "当前平台 · 已登录"
                        source == currentSource -> "当前平台 · 未登录"
                        account.loggedIn -> "已登录 · 仅在聚合歌曲搜索时请求"
                        else -> "未登录 · 仅在你主动勾选后参与搜索"
                    },
                    checked = source in unifiedSources,
                    onCheckedChange = { enabled ->
                        unifiedSources = MusicProviderSelectionStore.setUnifiedSourceEnabled(
                            context = context,
                            source = source,
                            enabled = enabled,
                        )
                    },
                )
                Spacer(Modifier.height(8.dp))
            }
        }

        Spacer(Modifier.height(10.dp))
        ProviderSettingToggle(
            title = "自动选择其他来源",
            subtitle = "暂未开放；当前只提供显式跨平台搜索，不会自动换源",
            checked = false,
            enabled = false,
            onCheckedChange = {},
        )

        Spacer(Modifier.height(22.dp))
        Text(
            "当前服务",
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
        )
        Spacer(Modifier.height(8.dp))

        when (currentSource) {
            MusicSource.Netease -> {
                ProviderSimpleCard(
                    "网易云音乐账号",
                    neteaseSession.profile?.nickname
                        ?: if (neteaseSession.isLoggedIn) "已登录" else "未登录 · 点击登录",
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
                val state = remember(loginRevision, currentSource) { accountManager.state(MusicSource.QQMusic) }
                ProviderSimpleCard(
                    "QQ音乐账号",
                    if (state.loggedIn) "QQ ${state.accountId.orEmpty()}" else "未登录 · 点击登录",
                    onClick = if (state.loggedIn) null else ({ showQQLogin = true }),
                )
                if (state.loggedIn) {
                    Spacer(Modifier.height(10.dp))
                    ProviderSimpleCard(
                        "切换 / 重新登录账号",
                        "清除当前 QQ音乐登录态后重新打开登录页",
                        onClick = {
                            pendingAccountAction = PendingProviderAccountAction(
                                MusicSource.QQMusic,
                                ProviderAccountAction.SwitchAccount,
                            )
                        },
                    )
                    Spacer(Modifier.height(10.dp))
                    ProviderSimpleCard(
                        "退出 QQ音乐",
                        "只清除 QQ音乐账号，不影响网易云或酷狗",
                        onClick = {
                            pendingAccountAction = PendingProviderAccountAction(
                                MusicSource.QQMusic,
                                ProviderAccountAction.Logout,
                            )
                        },
                    )
                }
                Spacer(Modifier.height(10.dp))
                ProviderSimpleCard(
                    "当前能力",
                    "歌曲 / 歌单 / 专辑 / 歌手搜索 · 推荐 · 排行榜 · 我的歌单 · 专辑/歌手详情 · 播放",
                )
                Spacer(Modifier.height(10.dp))
                ProviderSimpleCard(
                    "已知问题",
                    "QQ QRC 逐字歌词和翻译暂时回退为普通 LRC，不影响播放主链",
                )
            }

            MusicSource.Kugou -> {
                val state = remember(loginRevision, currentSource) { accountManager.state(MusicSource.Kugou) }
                ProviderSimpleCard(
                    "酷狗音乐账号",
                    if (state.loggedIn) "用户 ${state.accountId.orEmpty()}" else "未登录 · 点击扫码登录",
                    onClick = if (state.loggedIn) null else ({ showKugouLogin = true }),
                )
                if (state.loggedIn) {
                    Spacer(Modifier.height(10.dp))
                    ProviderSimpleCard(
                        "切换 / 重新登录账号",
                        "保留本机设备身份，只清除用户登录态后重新扫码",
                        onClick = {
                            pendingAccountAction = PendingProviderAccountAction(
                                MusicSource.Kugou,
                                ProviderAccountAction.SwitchAccount,
                            )
                        },
                    )
                    Spacer(Modifier.height(10.dp))
                    ProviderSimpleCard(
                        "退出酷狗音乐",
                        "清除 token 和用户 ID，但保留本机 MID / GUID",
                        onClick = {
                            pendingAccountAction = PendingProviderAccountAction(
                                MusicSource.Kugou,
                                ProviderAccountAction.Logout,
                            )
                        },
                    )
                }
                Spacer(Modifier.height(10.dp))
                ProviderSimpleCard(
                    "当前能力",
                    "歌曲 / 歌单 / 专辑 / 歌手搜索 · KRC逐字歌词 · 推荐 · 排行榜 · 我的歌单 · 专辑/歌手详情 · 播放",
                )
            }
        }
    }
}

@Composable
private fun ProviderSourceSelectionRow(
    source: MusicSource,
    selected: Boolean,
    accountState: ProviderAccountManager.AccountState,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .meloXLiquidButton(
                shape = RoundedCornerShape(24.dp),
                surfaceColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.045f),
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(1f)) {
            Text(source.displayName, fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
            Text(
                when (source) {
                    MusicSource.Netease -> "完整 MeloX iOS 迁移体验"
                    MusicSource.QQMusic -> if (accountState.loggedIn) "QQ音乐 Provider · 已登录" else "QQ音乐 Provider"
                    MusicSource.Kugou -> if (accountState.loggedIn) "酷狗音乐 Provider · 已登录" else "酷狗音乐 Provider"
                },
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.48f),
            )
        }
        Text(
            if (selected) "✓" else "",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 20.sp,
        )
    }
}
