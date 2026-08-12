package com.lladlam.melox.core.music.provider

import com.lladlam.melox.core.lyrics.LyricsDocument
import com.lladlam.melox.core.music.model.AudioQualityTier
import com.lladlam.melox.core.music.model.MusicAccountSummary
import com.lladlam.melox.core.music.model.MusicHomeFeed
import com.lladlam.melox.core.music.model.MusicPage
import com.lladlam.melox.core.music.model.MusicPlaylistDetail
import com.lladlam.melox.core.music.model.MusicPlaylistSummary
import com.lladlam.melox.core.music.model.MusicRankingSummary
import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.core.music.model.MusicTrack
import com.lladlam.melox.core.music.model.PlaybackResolution

enum class MusicCapability {
    Search,
    Playback,
    Lyrics,
    Library,
    Playlists,
    Albums,
    Artists,
    Comments,
    HomeRecommendations,
    DailyRecommendations,
    Rankings,
    Podcasts,
    CloudMusic,
    PrivateFm,
    HeartMode,
    ListenTogether,
    Messages,
    Recognition,
}

/**
 * A provider only declares identity/capabilities. Provider-specific product
 * features must not be forced into this interface just because NetEase has them.
 */
interface MusicProvider {
    val source: MusicSource
    val displayName: String
    val capabilities: Set<MusicCapability>

    fun supports(capability: MusicCapability): Boolean = capability in capabilities
}

interface SearchCapability {
    suspend fun searchSongs(
        query: String,
        page: Int = 1,
        pageSize: Int = 30,
    ): MusicPage<MusicTrack>
}

interface LyricsCapability {
    suspend fun lyrics(track: MusicTrack): LyricsDocument
}

interface PlaybackCapability {
    suspend fun resolvePlayback(
        track: MusicTrack,
        quality: AudioQualityTier,
    ): PlaybackResolution
}

/** Home semantic feed. Providers return only the sections they actually expose. */
interface HomeFeedCapability {
    suspend fun homeFeed(
        playlistLimit: Int = 12,
        newSongLimit: Int = 12,
        rankingLimit: Int = 8,
    ): MusicHomeFeed
}

/** Logged-in account/library data. Credentials remain private to each provider. */
interface UserLibraryCapability {
    suspend fun accountSummary(): MusicAccountSummary?

    suspend fun userPlaylists(
        page: Int = 1,
        pageSize: Int = 30,
    ): MusicPage<MusicPlaylistSummary>
}

/** Read-only playlist surface shared by provider-specific Experience UIs. */
interface PlaylistCapability {
    suspend fun playlistDetail(
        playlist: MusicPlaylistSummary,
        page: Int = 1,
        pageSize: Int = 100,
    ): MusicPlaylistDetail
}

/** Rankings are not forced into playlist semantics even if a provider implements them similarly. */
interface RankingCapability {
    suspend fun rankingTracks(
        ranking: MusicRankingSummary,
        page: Int = 1,
        pageSize: Int = 100,
    ): MusicPage<MusicTrack>
}

/** Small registry used by repositories and the provider-aware UI layer. */
class MusicProviderRegistry(
    providers: Iterable<MusicProvider>,
) {
    private val providersBySource = providers.associateBy(MusicProvider::source)

    init {
        require(providersBySource.isNotEmpty()) { "at least one music provider is required" }
        require(providersBySource.size == providers.count()) { "duplicate music provider source" }
    }

    val providers: List<MusicProvider>
        get() = providersBySource.values.toList()

    operator fun get(source: MusicSource): MusicProvider? = providersBySource[source]

    fun require(source: MusicSource): MusicProvider =
        providersBySource[source] ?: error("Music provider ${source.storageValue} is not registered")
}
