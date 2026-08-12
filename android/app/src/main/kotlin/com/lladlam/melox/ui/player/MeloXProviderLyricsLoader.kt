package com.lladlam.melox.ui.player

import android.content.Context
import com.lladlam.melox.core.account.NeteaseSessionStore
import com.lladlam.melox.core.download.MeloXDownloadStore
import com.lladlam.melox.core.lyrics.LyricsDocument
import com.lladlam.melox.core.music.model.MusicAlbumRef
import com.lladlam.melox.core.music.model.MusicArtistRef
import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.core.music.model.MusicTrack
import com.lladlam.melox.core.music.provider.LyricsCapability
import com.lladlam.melox.core.music.provider.MeloXMusicProviders
import com.lladlam.melox.core.network.NeteaseSearchClient
import com.lladlam.melox.playback.PlaybackTrackIdentity

/**
 * Single Now Playing lyric data entry point for every visual lyric style.
 *
 * NetEase keeps its existing downloaded-lyrics-first behavior. QQ Music and
 * Kugou reconstruct a provider-neutral track from Media3 metadata and then use
 * the provider's [LyricsCapability]. Rendering stays completely provider agnostic.
 */
internal object MeloXProviderLyricsLoader {
    suspend fun load(
        context: Context,
        state: MeloXPlaybackUiState,
    ): LyricsDocument {
        val appContext = context.applicationContext
        val mediaId = state.mediaId?.takeIf(String::isNotBlank)
            ?: return LyricsDocument(emptyList())
        val resourceId = PlaybackTrackIdentity.decode(mediaId)
            ?: return LyricsDocument(emptyList())

        if (resourceId.source == MusicSource.Netease) {
            val songId = resourceId.value.toLongOrNull()
                ?: return LyricsDocument(emptyList())
            MeloXDownloadStore.get(appContext).localLyrics(songId)?.let { return it }
            return NeteaseSearchClient(
                cookieProvider = { NeteaseSessionStore.readCookie(appContext) },
            ).lyrics(songId)
        }

        val provider = MeloXMusicProviders.create(appContext).require(resourceId.source)
        val lyricCapability = provider as? LyricsCapability
            ?: return LyricsDocument(emptyList())
        val artistRefs = state.artist
            .split(Regex("\\s*(?:、|/|&|,|;|；)\\s*"))
            .map(String::trim)
            .filter(String::isNotBlank)
            .ifEmpty { listOf("未知歌手") }
            .map { MusicArtistRef(name = it) }
        val album = state.album.takeIf(String::isNotBlank)?.let {
            MusicAlbumRef(
                name = it,
                artworkUrl = state.artworkUrl,
            )
        }
        val track = MusicTrack(
            id = resourceId,
            title = state.title.ifBlank { "未知歌曲" },
            artists = artistRefs,
            album = album,
            artworkUrl = state.artworkUrl,
            durationMs = state.durationMs.takeIf { it > 0L },
        )
        return lyricCapability.lyrics(track)
    }
}
