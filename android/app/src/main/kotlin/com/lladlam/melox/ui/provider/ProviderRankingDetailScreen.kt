package com.lladlam.melox.ui.provider

import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.lladlam.melox.core.music.model.MusicPage
import com.lladlam.melox.core.music.model.MusicRankingSummary
import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.core.music.model.MusicTrack
import com.lladlam.melox.core.music.provider.MeloXMusicProviders
import com.lladlam.melox.core.music.provider.RankingCapability
import com.lladlam.melox.playback.ProviderPlaybackCommands
import com.lladlam.melox.ui.MeloXBottomContentClearance
import com.lladlam.melox.ui.glass.meloXLiquidButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
internal fun ProviderRankingDetailScreen(
    source: MusicSource,
    ranking: MusicRankingSummary,
    onBack: () -> Unit,
) {
    BackHandler(onBack = onBack)
    val context = LocalContext.current
    val provider = remember(source) { MeloXMusicProviders.create(context).require(source) }
    val capability = provider as? RankingCapability
    var page by remember(ranking.id) { mutableStateOf<MusicPage<MusicTrack>?>(null) }
    var loading by remember(ranking.id) { mutableStateOf(capability != null) }
    var error by remember(ranking.id) { mutableStateOf<String?>(null) }
    var playbackError by remember(ranking.id) { mutableStateOf<String?>(null) }

    LaunchedEffect(ranking.id, capability) {
        if (capability == null) return@LaunchedEffect
        loading = true
        error = null
        runCatching {
            withContext(Dispatchers.IO) {
                capability.rankingTracks(ranking, page = 1, pageSize = 150)
            }
        }.onSuccess { page = it }
            .onFailure { error = it.message ?: "排行榜加载失败" }
        loading = false
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(
            start = 20.dp,
            top = 42.dp,
            end = 20.dp,
            bottom = MeloXBottomContentClearance,
        ),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        item {
            Text(
                "‹ 返回",
                modifier = Modifier.clickable(onClick = onBack).padding(vertical = 8.dp),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
            )
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                AsyncImage(
                    model = ranking.artworkUrl,
                    contentDescription = null,
                    modifier = Modifier.size(110.dp).clip(RoundedCornerShape(22.dp)),
                )
                Column(Modifier.weight(1f)) {
                    Text(
                        ranking.title,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                    )
                    ranking.subtitle?.takeIf(String::isNotBlank)?.let {
                        Spacer(Modifier.height(5.dp))
                        Text(
                            it,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        )
                    }
                }
            }
        }

        when {
            capability == null -> item { RankingMessageCard("暂不可用", "${source.displayName} 尚未实现排行榜详情") }
            loading -> item {
                Box(Modifier.fillMaxWidth().padding(vertical = 46.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            error != null -> item { RankingMessageCard("加载失败", error.orEmpty()) }
            else -> {
                val tracks = page?.items.orEmpty()
                if (tracks.isNotEmpty()) {
                    item {
                        RankingMessageCard(
                            "播放全部",
                            "从第一首开始播放 · ${tracks.size} 首已加载",
                            onClick = {
                                ProviderPlaybackCommands.playQueue(
                                    context = context,
                                    tracks = tracks,
                                    selectedTrackId = tracks.first().id,
                                    onFailure = { playbackError = it.message ?: "播放失败" },
                                )
                            },
                        )
                    }
                    items(
                        tracks,
                        key = { "ranking-track:${it.id.source.storageValue}:${it.id.value}" },
                    ) { track ->
                        RankingTrackRow(track) {
                            ProviderPlaybackCommands.playQueue(
                                context = context,
                                tracks = tracks,
                                selectedTrackId = track.id,
                                onFailure = { playbackError = it.message ?: "播放失败" },
                            )
                        }
                    }
                } else {
                    item { RankingMessageCard("暂无歌曲", "排行榜当前没有返回歌曲") }
                }
                playbackError?.let { message -> item { RankingMessageCard("播放失败", message) } }
            }
        }
    }
}

@Composable
private fun RankingTrackRow(track: MusicTrack, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick).padding(vertical = 8.dp),
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
private fun RankingMessageCard(
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
        Text(title, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(3.dp))
        Text(subtitle, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.48f))
    }
}
