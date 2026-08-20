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

/** Classic shell reuses the same playback state and controls as the Apple shell. */
@Composable
internal fun MeloXClassicNowPlayingScene(
    state: MeloXPlaybackUiState,
    page: MeloXNowPlayingPage,
    onPageChanged: (MeloXNowPlayingPage) -> Unit,
    onDismiss: () -> Unit,
    onShowActions: () -> Unit,
    onShowQuality: () -> Unit,
) {
    BoxWithConstraints(Modifier.fillMaxSize()) {
        val wide = maxWidth >= 700.dp || maxWidth > maxHeight
        val tabletPortrait = maxWidth >= 600.dp && !wide
        val availableWidth = maxWidth
        val availableHeight = maxHeight
        Column(Modifier.fillMaxSize().padding(horizontal = if (tabletPortrait) 42.dp else 22.dp)) {
            Row(
                Modifier.fillMaxWidth().padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                MeloXGlassIconButton(MeloXSymbol.Xmark, onDismiss, contentDescription = "收起播放器")
                Text("正在播放", color = MaterialTheme.colorScheme.onSurface.copy(alpha = .58f), fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                MeloXGlassIconButton(MeloXSymbol.Ellipsis, onShowActions, contentDescription = "播放操作")
            }
            if (wide) {
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
            } else {
                Box(Modifier.weight(1f).fillMaxWidth().padding(top = 16.dp), contentAlignment = Alignment.Center) {
                    Artwork(
                        state.artworkUrl,
                        Modifier.size(if (tabletPortrait) 420.dp else minOf(availableWidth - 38.dp, availableHeight * .43f)).clip(RoundedCornerShape(18.dp)),
                    )
                }
                ClassicMetadata(state)
                Spacer(Modifier.height(8.dp))
                MeloXNowPlayingCoreControls(state, page, onPageChanged, onShowQuality)
            }
        }
    }
}

@Composable
private fun ClassicMetadata(state: MeloXPlaybackUiState) {
    Column(Modifier.fillMaxWidth()) {
        Text(state.title, color = MaterialTheme.colorScheme.onSurface, fontSize = 23.sp, lineHeight = 28.sp, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
        Text(state.artist, color = MaterialTheme.colorScheme.onSurface.copy(alpha = .56f), fontSize = 16.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}
