package com.lladlam.melox.core.music.provider

import com.lladlam.melox.core.lyrics.LyricsDocument
import com.lladlam.melox.core.music.model.AudioQualityTier
import com.lladlam.melox.core.music.model.MusicPage
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

/** Small registry used by repositories and the future provider-aware UI layer. */
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
