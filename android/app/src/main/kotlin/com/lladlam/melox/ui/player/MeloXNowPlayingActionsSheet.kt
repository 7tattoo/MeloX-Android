package com.lladlam.melox.ui.player

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lladlam.melox.core.model.SearchSong
import com.lladlam.melox.core.network.MeloXSearchKind
import com.lladlam.melox.playback.MeloXListenTogetherCoordinator

@Composable
fun MeloXNowPlayingActionsSheet(
    state: MeloXPlaybackUiState,
    visible: Boolean,
    onDismiss: () -> Unit,
    onNavigateSearch: ((String, MeloXSearchKind) -> Unit)? = null,
) {
    val context = LocalContext.current.applicationContext
    LaunchedEffect(Unit) {
        MeloXListenTogetherCoordinator.ensureStarted(context)
    }
    val togetherState by MeloXListenTogetherCoordinator.state(context).collectAsState()
    var openedFromTogetherBadge by remember { mutableStateOf(false) }
    val effectiveVisible = visible || openedFromTogetherBadge

    val id = state.mediaId?.toLongOrNull() ?: -1L
    val song = SearchSong(
        id = id,
        name = state.title.ifBlank { "正在播放" },
        artists = state.artist,
        album = state.album,
        artworkUrl = state.artworkUrl,
        durationMs = state.durationMs,
    )
    val queue = state.queue.mapNotNull { entry ->
        val entryId = entry.mediaId.toLongOrNull()?.takeIf { it > 0L } ?: return@mapNotNull null
        SearchSong(
            id = entryId,
            name = entry.title.ifBlank { "未知歌曲" },
            artists = entry.artist.ifBlank { "未知歌手" },
            album = if (entryId == id) state.album else "",
            artworkUrl = entry.artworkUrl,
            durationMs = if (entryId == id) state.durationMs else 0L,
        )
    }

    Box(Modifier.fillMaxSize()) {
        val room = togetherState.room
        if (room != null && !effectiveVisible) {
            val status = when (togetherState.phase) {
                MeloXListenTogetherCoordinator.Phase.Reconnecting -> "一起听 · 重连中"
                else -> "一起听 · ${room.users.size.coerceAtLeast(1)} 人"
            }
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 38.dp, end = 22.dp)
                    .background(Color.Black.copy(alpha = .28f), RoundedCornerShape(999.dp))
                    .clickable { openedFromTogetherBadge = true }
                    .padding(horizontal = 10.dp, vertical = 6.dp),
            ) {
                androidx.compose.material3.Text(
                    text = status,
                    color = Color.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }

        MeloXSongActionsOverlay(
            song = song,
            queue = queue,
            visible = effectiveVisible && id > 0L,
            onDismiss = {
                if (openedFromTogetherBadge) openedFromTogetherBadge = false
                if (visible) onDismiss()
            },
            playbackState = state,
            onNavigateSearch = onNavigateSearch,
        )
    }
}
