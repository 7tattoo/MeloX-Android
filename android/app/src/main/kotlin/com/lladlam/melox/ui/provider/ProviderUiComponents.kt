package com.lladlam.melox.ui.provider

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.lladlam.melox.core.music.model.MusicAccountSummary
import com.lladlam.melox.core.music.model.MusicAlbumSummary
import com.lladlam.melox.core.music.model.MusicArtistSummary
import com.lladlam.melox.core.music.model.MusicPlaylistSummary
import com.lladlam.melox.core.music.model.MusicRankingSummary
import com.lladlam.melox.core.music.model.MusicTrack

/**
 * Shared provider presentation primitives.
 *
 * Keep these close to the already-migrated MeloX/iOS presentation instead of
 * inventing a second visual system for QQ Music and Kugou. Navigation/controls
 * may use MeloX glass in the shell; media content itself stays on the content
 * layer with the same plain rows, artwork geometry and spacing as MeloX.
 */
@Composable
internal fun ProviderSectionTitle(title: String) {
    Text(
        title,
        modifier = Modifier.padding(top = 4.dp),
        fontSize = 25.sp,
        lineHeight = 30.sp,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
internal fun ProviderSimpleCard(
    title: String,
    subtitle: String,
    onClick: (() -> Unit)? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.055f))
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(horizontal = 16.dp, vertical = 14.dp),
    ) {
        Text(title, fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(3.dp))
        Text(
            subtitle,
            fontSize = 13.sp,
            lineHeight = 18.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.48f),
        )
    }
}

@Composable
internal fun ProviderSettingToggle(
    title: String,
    subtitle: String,
    checked: Boolean,
    enabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.055f))
            .padding(horizontal = 16.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.SemiBold)
            Text(
                subtitle,
                fontSize = 12.sp,
                lineHeight = 17.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = if (enabled) 0.48f else 0.30f),
            )
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
        )
    }
}

@Composable
internal fun ProviderTrackRow(
    track: MusicTrack,
    showSource: Boolean = false,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
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
            val metadata = buildList {
                add(track.artistText)
                track.album?.name?.takeIf(String::isNotBlank)?.let(::add)
                if (showSource) add(track.id.source.displayName)
            }.joinToString(" · ")
            Text(
                metadata,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.48f),
            )
        }
    }
}

/** MeloX/iOS HomeSongCard counterpart for provider-backed tracks. */
@Composable
internal fun ProviderSongCard(
    track: MusicTrack,
    showSource: Boolean = false,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .width(154.dp)
            .clickable(onClick = onClick),
    ) {
        AsyncImage(
            model = track.artworkUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(154.dp).clip(RoundedCornerShape(14.dp)),
        )
        Text(
            track.title,
            modifier = Modifier.padding(top = 7.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontSize = 15.sp,
            lineHeight = 19.sp,
            fontWeight = FontWeight.SemiBold,
        )
        val secondary = buildList {
            add(track.artistText)
            if (showSource) add(track.id.source.displayName)
        }.joinToString(" · ")
        Text(
            secondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f),
        )
    }
}

/** MeloX/iOS HomePlaylistCard uses a 166pt artwork card. */
@Composable
internal fun ProviderPlaylistCard(
    playlist: MusicPlaylistSummary,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .width(166.dp)
            .clickable(onClick = onClick),
    ) {
        AsyncImage(
            model = playlist.artworkUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(166.dp).clip(RoundedCornerShape(14.dp)),
        )
        Text(
            playlist.title,
            modifier = Modifier.padding(top = 7.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontSize = 15.sp,
            lineHeight = 19.sp,
            fontWeight = FontWeight.SemiBold,
        )
        val secondary = playlist.creatorName
            ?: playlist.trackCount?.let { "$it 首" }
            ?: playlist.description
        secondary?.takeIf(String::isNotBlank)?.let {
            Text(
                it,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f),
            )
        }
    }
}

@Composable
internal fun ProviderPlaylistRow(
    playlist: MusicPlaylistSummary,
    showSource: Boolean = false,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        AsyncImage(
            model = playlist.artworkUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(54.dp).clip(RoundedCornerShape(9.dp)),
        )
        Column(Modifier.weight(1f)) {
            Text(
                playlist.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
            )
            val secondary = buildList {
                playlist.trackCount?.let { add("$it 首") }
                playlist.creatorName?.takeIf(String::isNotBlank)?.let(::add)
                if (showSource) add(playlist.id.source.displayName)
            }.joinToString(" · ")
            if (secondary.isNotBlank()) {
                Text(
                    secondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.46f),
                )
            }
        }
    }
}

@Composable
internal fun ProviderAlbumRow(
    album: MusicAlbumSummary,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        AsyncImage(
            model = album.artworkUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(54.dp).clip(RoundedCornerShape(9.dp)),
        )
        Column(Modifier.weight(1f)) {
            Text(
                album.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
            )
            val secondary = buildList {
                album.artists.joinToString(" / ") { it.name }.takeIf(String::isNotBlank)?.let(::add)
                album.releaseDate?.takeIf(String::isNotBlank)?.let(::add)
                album.trackCount?.let { add("$it 首") }
            }.joinToString(" · ")
            if (secondary.isNotBlank()) {
                Text(
                    secondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.48f),
                )
            }
        }
    }
}

@Composable
internal fun ProviderArtistRow(
    artist: MusicArtistSummary,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        AsyncImage(
            model = artist.artworkUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(54.dp).clip(RoundedCornerShape(27.dp)),
        )
        Column(Modifier.weight(1f)) {
            Text(
                artist.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
            )
            val secondary = buildList {
                artist.songCount?.let { add("$it 首歌曲") }
                artist.albumCount?.let { add("$it 张专辑") }
            }.joinToString(" · ")
            Text(
                secondary.ifBlank { artist.id.source.displayName },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.48f),
            )
        }
    }
}

/** Ranking artwork follows the same horizontal media strip as iOS chart playlists. */
@Composable
internal fun ProviderRankingCard(
    ranking: MusicRankingSummary,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .width(166.dp)
            .clickable(onClick = onClick),
    ) {
        AsyncImage(
            model = ranking.artworkUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(166.dp).clip(RoundedCornerShape(14.dp)),
        )
        Text(
            ranking.title,
            modifier = Modifier.padding(top = 7.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontSize = 15.sp,
            lineHeight = 19.sp,
            fontWeight = FontWeight.SemiBold,
        )
        ranking.subtitle?.takeIf(String::isNotBlank)?.let {
            Text(
                it,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f),
            )
        }
    }
}

@Composable
internal fun ProviderAccountCard(account: MusicAccountSummary) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.055f))
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        AsyncImage(
            model = account.avatarUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(54.dp).clip(RoundedCornerShape(27.dp)),
        )
        Column(Modifier.weight(1f)) {
            Text(account.displayName, fontSize = 17.sp, fontWeight = FontWeight.Bold)
            account.subtitle?.takeIf(String::isNotBlank)?.let {
                Text(
                    it,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.48f),
                )
            }
        }
    }
}
