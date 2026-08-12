package com.lladlam.melox.ui.player

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
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
    LaunchedEffect(visible) {
        if (visible) MeloXListenTogetherCoordinator.ensureStarted(context)
    }

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

    MeloXSongActionsOverlay(
        song = song,
        queue = queue,
        visible = visible && id > 0L,
        onDismiss = onDismiss,
        playbackState = state,
        onNavigateSearch = onNavigateSearch,
    )
}
