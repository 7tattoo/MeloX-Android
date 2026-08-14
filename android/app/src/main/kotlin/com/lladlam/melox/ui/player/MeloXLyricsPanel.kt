package com.lladlam.melox.ui.player

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Compatibility entry point for the legacy player scene.
 *
 * MeloXIOSLyricsPanel is the single lyrics implementation now. Keeping this
 * small adapter avoids two independent playback clocks, lyric fetches and
 * scrolling engines drifting apart while the older player shell is migrated.
 */
@Composable
fun MeloXLyricsPanel(
    state: MeloXPlaybackUiState,
    modifier: Modifier = Modifier,
) {
    MeloXIOSLyricsPanel(
        state = state,
        modifier = modifier,
        allowAutomaticSkyline = false,
    )
}
