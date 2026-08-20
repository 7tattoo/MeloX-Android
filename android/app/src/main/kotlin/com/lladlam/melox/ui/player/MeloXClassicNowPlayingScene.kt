package com.lladlam.melox.ui.player

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lladlam.melox.ui.glass.MeloXGlassIconButton
import com.lladlam.melox.ui.glass.MeloXSymbol
import com.lladlam.melox.ui.layout.MeloXWindowWidthClass
import com.lladlam.melox.ui.layout.rememberMeloXWindowInfo

/** Classic shell preserves its visual language for every player page and window class. */
@Composable
internal fun MeloXClassicNowPlayingScene(
    state: MeloXPlaybackUiState,
    page: MeloXNowPlayingPage,
    onPageChanged: (MeloXNowPlayingPage) -> Unit,
    onDismiss: () -> Unit,
    onShowActions: () -> Unit,
    onShowQuality: () -> Unit,
) {
    val window = rememberMeloXWindowInfo()
    BoxWithConstraints(Modifier.fillMaxSize()) {
        val wide = window.isLandscape || window.widthClass != MeloXWindowWidthClass.Compact
        val tabletPortrait = window.widthClass != MeloXWindowWidthClass.Compact && !window.isLandscape
        val availableWidth = maxWidth
        val availableHeight = maxHeight
        Column(
            Modifier
                .fillMaxSize()
                .widthIn(max = window.maxContentWidth)
                .padding(horizontal = window.gutter),
        ) {
            Row(
                Modifier.fillMaxWidth().padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                MeloXGlassIconButton(MeloXSymbol.Xmark, onDismiss, contentDescription = "收起播放器")
                Text("正在播放", color = MaterialTheme.colorScheme.onSurface.copy(alpha = .58f), fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                MeloXGlassIconButton(MeloXSymbol.Ellipsis, onShowActions, contentDescription = "播放操作")
            }
            if (page == MeloXNowPlayingPage.Artwork && wide) {
                Row(
                    Modifier.fillMaxSize().padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(38.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Artwork(
                        state.artworkUrl,
                        Modifier.weight(.46f).fillMaxWidth().clip(RoundedCornerShape(18.dp)),
                    )
                    Column(Modifier.weight(.54f).widthIn(max = 560.dp), verticalArrangement = Arrangement.Center) {
                        ClassicMetadata(state)
                        Spacer(Modifier.height(20.dp))
                        MeloXNowPlayingCoreControls(state, page, onPageChanged, onShowQuality)
                    }
                }
            } else if (page == MeloXNowPlayingPage.Artwork) {
                Box(Modifier.weight(1f).fillMaxWidth().padding(top = 16.dp), contentAlignment = Alignment.Center) {
                    Artwork(
                        state.artworkUrl,
                        Modifier.size(if (tabletPortrait) 420.dp else minOf(availableWidth - 38.dp, availableHeight * .43f)).clip(RoundedCornerShape(18.dp)),
                    )
                }
                ClassicMetadata(state)
                Spacer(Modifier.height(8.dp))
                MeloXNowPlayingCoreControls(state, page, onPageChanged, onShowQuality)
            } else if (wide) {
                Row(
                    Modifier.fillMaxSize().padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(32.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Artwork(
                        state.artworkUrl,
                        Modifier
                            .weight(.40f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(18.dp)),
                    )
                    ClassicAlternatePage(
                        state = state,
                        page = page,
                        onPageChanged = onPageChanged,
                        onShowQuality = onShowQuality,
                        modifier = Modifier.weight(.60f).fillMaxSize(),
                    )
                }
            } else {
                ClassicMetadata(state)
                ClassicAlternatePage(
                    state = state,
                    page = page,
                    onPageChanged = onPageChanged,
                    onShowQuality = onShowQuality,
                    modifier = Modifier.weight(1f).fillMaxWidth().padding(top = 16.dp),
                )
            }
        }
    }
}

@Composable
private fun ClassicAlternatePage(
    state: MeloXPlaybackUiState,
    page: MeloXNowPlayingPage,
    onPageChanged: (MeloXNowPlayingPage) -> Unit,
    onShowQuality: () -> Unit,
    modifier: Modifier,
) {
    Column(modifier) {
        ClassicMetadata(state)
        Spacer(Modifier.height(12.dp))
        when (page) {
            MeloXNowPlayingPage.Lyrics -> MeloXIOSLyricsPanel(
                state = state,
                modifier = Modifier.weight(1f).fillMaxWidth(),
                allowAutomaticSkyline = false,
            )
            MeloXNowPlayingPage.Queue -> MeloXQueuePanel(
                state = state,
                modifier = Modifier.weight(1f).fillMaxWidth(),
                showSongHeader = false,
                interactive = true,
            )
            MeloXNowPlayingPage.Artwork -> Unit
        }
        Spacer(Modifier.height(8.dp))
        MeloXNowPlayingCoreControls(
            state = state,
            page = page,
            onPageSelected = onPageChanged,
            onShowQuality = onShowQuality,
        )
    }
}

@Composable
private fun ClassicMetadata(state: MeloXPlaybackUiState) {
    Column(Modifier.fillMaxWidth()) {
        Text(state.title, color = MaterialTheme.colorScheme.onSurface, fontSize = 23.sp, lineHeight = 28.sp, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
        Text(state.artist, color = MaterialTheme.colorScheme.onSurface.copy(alpha = .56f), fontSize = 16.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}
