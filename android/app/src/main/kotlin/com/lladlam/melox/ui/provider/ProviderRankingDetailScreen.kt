package com.lladlam.melox.ui.provider

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

    val tracks = page?.items.orEmpty()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 42.dp,
            end = 20.dp,
            bottom = MeloXBottomContentClearance,
        ),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        item {
            RankingDetailHeader(
                title = ranking.title,
                onBack = onBack,
            )
        }

        item {
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 2.dp, bottom = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AsyncImage(
                    model = ranking.artworkUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(190.dp).clip(RoundedCornerShape(16.dp)),
                )
                Text(
                    ranking.title,
                    modifier = Modifier.fillMaxWidth().padding(top = 15.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 27.sp,
                    lineHeight = 32.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                ranking.subtitle?.takeIf(String::isNotBlank)?.let { subtitle ->
                    Text(
                        subtitle,
                        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                        textAlign = TextAlign.Center,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 13.sp,
                        lineHeight = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.52f),
                    )
                }
                if (tracks.isNotEmpty()) {
                    val total = page?.total?.takeIf { it > 0L } ?: tracks.size.toLong()
                    Text(
                        "$total 首歌曲",
                        modifier = Modifier.padding(top = 5.dp),
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.42f),
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(top = 14.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        RankingDetailAction(
                            title = "播放",
                            modifier = Modifier.weight(1f),
                            onClick = {
                                ProviderPlaybackCommands.playQueue(
                                    context = context,
                                    tracks = tracks,
                                    selectedTrackId = tracks.first().id,
                                    onFailure = { playbackError = it.message ?: "播放失败" },
                                )
                            },
                        )
                        RankingDetailAction(
                            title = "随机",
                            modifier = Modifier.weight(1f),
                            onClick = {
                                val shuffled = tracks.shuffled()
                                shuffled.firstOrNull()?.let { first ->
                                    ProviderPlaybackCommands.playQueue(
                                        context = context,
                                        tracks = shuffled,
                                        selectedTrackId = first.id,
                                        onFailure = { playbackError = it.message ?: "播放失败" },
                                    )
                                }
                            },
                        )
                    }
                }
            }
        }

        when {
            capability == null -> item {
                ProviderSimpleCard("暂不可用", "${source.displayName} 尚未实现排行榜详情")
            }
            loading -> item {
                Box(Modifier.fillMaxWidth().padding(vertical = 46.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            error != null -> item { ProviderSimpleCard("加载失败", error.orEmpty()) }
            tracks.isNotEmpty() -> {
                item { ProviderSectionTitle("歌曲") }
                itemsIndexed(
                    tracks,
                    key = { _, track -> "ranking-track:${track.id.source.storageValue}:${track.id.value}" },
                ) { index, track ->
                    RankingTrackRow(
                        rank = index + 1,
                        track = track,
                        onClick = {
                            ProviderPlaybackCommands.playQueue(
                                context = context,
                                tracks = tracks,
                                selectedTrackId = track.id,
                                onFailure = { playbackError = it.message ?: "播放失败" },
                            )
                        },
                    )
                }
            }
            else -> item { ProviderSimpleCard("暂无歌曲", "排行榜当前没有返回歌曲") }
        }

        playbackError?.let { message -> item { ProviderSimpleCard("播放失败", message) } }
    }
}

@Composable
private fun RankingDetailHeader(
    title: String,
    onBack: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth().height(58.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .meloXLiquidButton(shape = CircleShape)
                .clickable(onClick = onBack),
            contentAlignment = Alignment.Center,
        ) {
            Text("‹", fontSize = 30.sp, lineHeight = 30.sp)
        }
        Spacer(Modifier.size(12.dp))
        Text(
            title,
            modifier = Modifier.weight(1f),
            fontSize = 24.sp,
            lineHeight = 29.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun RankingDetailAction(
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .meloXLiquidButton(
                shape = RoundedCornerShape(22.dp),
                surfaceColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.055f),
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 11.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(title, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun RankingTrackRow(
    rank: Int,
    track: MusicTrack,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(62.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            rank.toString(),
            modifier = Modifier.size(30.dp),
            textAlign = TextAlign.Center,
            fontSize = 13.sp,
            fontWeight = if (rank <= 3) FontWeight.Bold else FontWeight.Medium,
            color = if (rank <= 3) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
            },
        )
        AsyncImage(
            model = track.artworkUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(48.dp).clip(RoundedCornerShape(9.dp)),
        )
        Column(Modifier.weight(1f)) {
            Text(
                track.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
            )
            val metadata = listOf(track.artistText, track.album?.name)
                .filterNot { it.isNullOrBlank() }
                .joinToString(" · ")
            Text(
                metadata,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.48f),
            )
        }
    }
}
