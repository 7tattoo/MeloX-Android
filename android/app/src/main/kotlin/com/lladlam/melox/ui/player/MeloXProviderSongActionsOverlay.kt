package com.lladlam.melox.ui.player

import android.content.Context
import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.lladlam.melox.core.music.model.MusicResourceId
import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.core.network.MeloXSearchKind
import com.lladlam.melox.ui.glass.meloXLiquidButton

private enum class ProviderSongActionPage {
    Main,
    Sleep,
}

/**
 * Actions that are valid for non-NetEase provider tracks. Provider-only remote
 * writes (favourite, comments, cloud playlists, etc.) are intentionally absent
 * until that provider has a real write capability instead of calling NetEase
 * endpoints with a QQ/Kugou identifier.
 */
@Composable
internal fun MeloXProviderSongActionsOverlay(
    state: MeloXPlaybackUiState,
    identity: MusicResourceId,
    visible: Boolean,
    onDismiss: () -> Unit,
    onNavigateSearch: ((String, MeloXSearchKind) -> Unit)? = null,
) {
    val context = LocalContext.current
    var page by remember(identity, visible) { mutableStateOf(ProviderSongActionPage.Main) }

    BackHandler(enabled = visible) {
        if (page == ProviderSongActionPage.Main) onDismiss() else page = ProviderSongActionPage.Main
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(spring(stiffness = 520f)) + scaleIn(initialScale = 0.96f),
        exit = fadeOut(spring(stiffness = 620f)) + scaleOut(targetScale = 0.97f),
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.22f))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onDismiss,
                )
                .padding(horizontal = 18.dp)
                .navigationBarsPadding(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp)
                    .meloXLiquidButton(
                        shape = RoundedCornerShape(30.dp),
                        tint = Color.White.copy(alpha = 0.08f),
                        surfaceColor = Color.Black.copy(alpha = 0.12f),
                        blurRadius = 14.dp,
                        lensRadius = 20.dp,
                        refractionHeight = 22.dp,
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {},
                    ),
            ) {
                AnimatedContent(
                    targetState = page,
                    transitionSpec = {
                        (fadeIn(spring(stiffness = 520f)) + scaleIn(initialScale = 0.96f)) togetherWith
                            (fadeOut(spring(stiffness = 620f)) + scaleOut(targetScale = 0.96f))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = "provider-song-action-page",
                ) { target ->
                    Column(
                        Modifier.fillMaxWidth().padding(horizontal = 18.dp, vertical = 18.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                    ) {
                        ProviderActionHeader(
                            state = state,
                            subtitle = when (target) {
                                ProviderSongActionPage.Main -> "${identity.source.displayName} · 歌曲操作"
                                ProviderSongActionPage.Sleep -> "定时关闭"
                            },
                        )

                        when (target) {
                            ProviderSongActionPage.Main -> {
                                ProviderActionItem("定时关闭", "◷") { page = ProviderSongActionPage.Sleep }
                                ProviderActionItem("添加到播放队列", "+") {
                                    state.addCurrentToQueue()
                                    onDismiss()
                                }
                                ProviderActionItem("系统分享", "↗") {
                                    shareProviderSong(context, state, identity)
                                    onDismiss()
                                }
                                if (state.album.isNotBlank() && onNavigateSearch != null) {
                                    ProviderActionItem("前往专辑：${state.album}", "▣") {
                                        onDismiss()
                                        onNavigateSearch(state.album, MeloXSearchKind.Albums)
                                    }
                                }
                                if (state.artist.isNotBlank() && onNavigateSearch != null) {
                                    ProviderActionItem("前往艺人：${state.artist}", "♬") {
                                        onDismiss()
                                        onNavigateSearch(state.artist.substringBefore(" / "), MeloXSearchKind.Artists)
                                    }
                                }
                            }

                            ProviderSongActionPage.Sleep -> {
                                listOf(15, 30, 45, 60).forEach { minutes ->
                                    ProviderActionItem("$minutes 分钟后", "◷") {
                                        state.setSleepTimer(minutes)
                                        onDismiss()
                                    }
                                }
                                if (state.sleepTimerEndRealtimeMs > 0L) {
                                    ProviderActionItem("取消定时", "×") {
                                        state.cancelSleepTimer()
                                        onDismiss()
                                    }
                                }
                                ProviderActionItem("返回", "‹") { page = ProviderSongActionPage.Main }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ProviderActionHeader(
    state: MeloXPlaybackUiState,
    subtitle: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 6.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = state.artworkUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(52.dp),
        )
        Column(Modifier.weight(1f).padding(start = 12.dp)) {
            Text(
                state.title.ifBlank { "正在播放" },
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                state.artist.ifBlank { subtitle },
                color = Color.White.copy(alpha = 0.58f),
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                subtitle,
                color = Color.White.copy(alpha = 0.38f),
                fontSize = 10.sp,
                maxLines = 1,
            )
        }
    }
}

@Composable
private fun ProviderActionItem(
    title: String,
    symbol: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            symbol,
            color = Color.White.copy(alpha = 0.82f),
            fontSize = 20.sp,
            modifier = Modifier.size(34.dp),
        )
        Text(
            title,
            color = Color.White.copy(alpha = 0.94f),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

private fun shareProviderSong(
    context: Context,
    state: MeloXPlaybackUiState,
    identity: MusicResourceId,
) {
    val providerUrl = when (identity.source) {
        MusicSource.QQMusic -> "https://y.qq.com/n/ryqq/songDetail/${identity.value}"
        MusicSource.Kugou,
        MusicSource.Netease -> null
    }
    val text = buildString {
        append(state.title.ifBlank { "正在播放" })
        if (state.artist.isNotBlank()) append(" · ").append(state.artist)
        providerUrl?.let { append('\n').append(it) }
    }
    val intent = Intent.createChooser(
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        },
        "分享歌曲",
    ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
}
