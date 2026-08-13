package com.lladlam.melox.core.provider.netease

import com.lladlam.melox.core.audio.MusicQuality
import com.lladlam.melox.core.audio.NeteaseQualityClient
import com.lladlam.melox.core.lyrics.LyricsDocument
import com.lladlam.melox.core.music.model.AudioQualityTier
import com.lladlam.melox.core.music.model.MusicAlbumRef
import com.lladlam.melox.core.music.model.MusicArtistRef
import com.lladlam.melox.core.music.model.MusicPage
import com.lladlam.melox.core.music.model.MusicResourceId
import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.core.music.model.MusicTrack
import com.lladlam.melox.core.music.model.PlaybackResolution
import com.lladlam.melox.core.music.model.ProviderTrackMetadata
import com.lladlam.melox.core.music.provider.LyricsCapability
import com.lladlam.melox.core.music.provider.MusicCapability
import com.lladlam.melox.core.music.provider.MusicProvider
import com.lladlam.melox.core.music.provider.PlaybackCapability
import com.lladlam.melox.core.music.provider.SearchCapability
import com.lladlam.melox.core.network.NeteaseSearchClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient

/**
 * Compatibility adapter around the already migrated NetEase implementation.
 * NetEase-native capabilities remain in the existing Netease* clients so future
 * MeloX iOS migrations can keep their current one-to-one structure.
 */
class NeteaseProvider(
    cookieProvider: () -> String = { "" },
    httpClient: OkHttpClient = OkHttpClient(),
) : MusicProvider, SearchCapability, LyricsCapability, PlaybackCapability {
    override val source: MusicSource = MusicSource.Netease
    override val displayName: String = source.displayName
    override val capabilities: Set<MusicCapability> = setOf(
        MusicCapability.Search,
        MusicCapability.Playback,
        MusicCapability.Lyrics,
        MusicCapability.Library,
        MusicCapability.Playlists,
        MusicCapability.Albums,
        MusicCapability.Artists,
        MusicCapability.Comments,
        MusicCapability.HomeRecommendations,
        MusicCapability.DailyRecommendations,
        MusicCapability.Rankings,
        MusicCapability.Podcasts,
        MusicCapability.CloudMusic,
        MusicCapability.PrivateFm,
        MusicCapability.HeartMode,
        MusicCapability.ListenTogether,
        MusicCapability.Messages,
        MusicCapability.Recognition,
    )

    private val searchClient = NeteaseSearchClient(
        httpClient = httpClient,
        cookieProvider = cookieProvider,
    )
    private val qualityClient = NeteaseQualityClient(
        cookieProvider = cookieProvider,
        httpClient = httpClient,
    )

    override suspend fun searchSongs(
        query: String,
        page: Int,
        pageSize: Int,
    ): MusicPage<MusicTrack> {
        if (page < 1) return MusicPage(emptyList(), 1, pageSize.coerceAtLeast(1), 0)
        // Current NeteaseSearchClient mirrors the iOS first-page path. Keep it
        // untouched during the no-behaviour-change migration; pagination can be
        // delegated to NeteaseUniversalSearchClient when UI starts requesting it.
        if (page > 1) return MusicPage(emptyList(), page, pageSize.coerceAtLeast(1), null, false)
        val size = pageSize.coerceIn(1, 50)
        // The original NetEase SearchScreen enriches missing artwork after search.
        // Unified search must do the same before mapping into provider-neutral tracks,
        // otherwise the Compose row receives a blank artwork URL.
        val songs = searchClient.ensureArtwork(searchClient.searchSongs(query, size)).map { song ->
            MusicTrack(
                id = MusicResourceId(MusicSource.Netease, song.id.toString()),
                title = song.name,
                artists = song.artists
                    .split(" / ")
                    .map(String::trim)
                    .filter(String::isNotBlank)
                    .map { MusicArtistRef(name = it) },
                album = song.album.takeIf(String::isNotBlank)?.let {
                    MusicAlbumRef(name = it, artworkUrl = song.artworkUrl)
                },
                artworkUrl = song.artworkUrl,
                durationMs = song.durationMs.takeIf { it > 0L },
                providerMetadata = ProviderTrackMetadata.Netease(song.id),
            )
        }
        return MusicPage(
            items = songs,
            page = page,
            pageSize = size,
            total = null,
            hasMore = songs.size >= size,
        )
    }

    override suspend fun lyrics(track: MusicTrack): LyricsDocument =
        searchClient.lyrics(track.requireNeteaseId())

    override suspend fun resolvePlayback(
        track: MusicTrack,
        quality: AudioQualityTier,
    ): PlaybackResolution = withContext(Dispatchers.IO) {
        val requested = quality.toNeteaseQuality()
        val result = qualityClient.playbackSourceBlocking(track.requireNeteaseId(), requested)
        PlaybackResolution.Playable(
            url = result.url,
            requestedQuality = quality,
            actualQuality = result.quality?.toTier(),
            bitrate = result.bitrate,
            format = result.format,
        )
    }
}

private fun MusicTrack.requireNeteaseId(): Long {
    require(id.source == MusicSource.Netease) {
        "NeteaseProvider cannot handle ${id.source.storageValue} track"
    }
    return (providerMetadata as? ProviderTrackMetadata.Netease)?.numericId
        ?: id.value.toLongOrNull()
        ?: error("invalid NetEase track id: ${id.value}")
}

private fun AudioQualityTier.toNeteaseQuality(): MusicQuality = when (this) {
    AudioQualityTier.Standard -> MusicQuality.Standard
    AudioQualityTier.High -> MusicQuality.High
    AudioQualityTier.Lossless -> MusicQuality.Lossless
    AudioQualityTier.HiResolution -> MusicQuality.HiResolution
    AudioQualityTier.Immersive -> MusicQuality.ImmersiveSurround
    AudioQualityTier.Master -> MusicQuality.UltraClearMaster
}

private fun MusicQuality.toTier(): AudioQualityTier = when (this) {
    MusicQuality.Standard -> AudioQualityTier.Standard
    MusicQuality.High -> AudioQualityTier.High
    MusicQuality.Lossless -> AudioQualityTier.Lossless
    MusicQuality.HiResolution -> AudioQualityTier.HiResolution
    MusicQuality.HighDefinitionSurround,
    MusicQuality.ImmersiveSurround -> AudioQualityTier.Immersive
    MusicQuality.UltraClearMaster -> AudioQualityTier.Master
}
