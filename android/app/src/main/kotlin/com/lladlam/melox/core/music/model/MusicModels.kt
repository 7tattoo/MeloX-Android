package com.lladlam.melox.core.music.model

/** Music service that owns a resource. IDs are only unique together with [MusicSource]. */
enum class MusicSource(
    val storageValue: String,
    val displayName: String,
) {
    Netease("netease", "网易云音乐"),
    QQMusic("qq_music", "QQ音乐"),
    Kugou("kugou", "酷狗音乐");

    companion object {
        fun fromStorageValue(value: String?): MusicSource =
            entries.firstOrNull { it.storageValue == value } ?: Netease
    }
}

data class MusicResourceId(
    val source: MusicSource,
    val value: String,
) {
    init {
        require(value.isNotBlank()) { "music resource id must not be blank" }
    }
}

data class MusicArtistRef(
    val id: MusicResourceId? = null,
    val name: String,
)

data class MusicAlbumRef(
    val id: MusicResourceId? = null,
    val name: String,
    val artworkUrl: String? = null,
)

enum class TrackAvailability {
    Unknown,
    Playable,
    PreviewOnly,
    LoginRequired,
    SubscriptionRequired,
    RegionRestricted,
    CopyrightRestricted,
    Unavailable,
}

/**
 * Provider-only identifiers that must survive mapping into the common model.
 * They intentionally never leak into Compose or Media3 APIs.
 */
sealed interface ProviderTrackMetadata {
    data object Empty : ProviderTrackMetadata

    data class Netease(
        val numericId: Long,
    ) : ProviderTrackMetadata

    data class QQMusic(
        val songMid: String,
        val mediaMid: String? = null,
        val numericSongId: Long? = null,
    ) : ProviderTrackMetadata

    data class Kugou(
        val hash: String,
        val albumAudioId: Long? = null,
        val albumId: String? = null,
    ) : ProviderTrackMetadata
}

data class MusicTrack(
    val id: MusicResourceId,
    val title: String,
    val artists: List<MusicArtistRef>,
    val album: MusicAlbumRef? = null,
    val artworkUrl: String? = album?.artworkUrl,
    val durationMs: Long? = null,
    val availability: TrackAvailability = TrackAvailability.Unknown,
    val providerMetadata: ProviderTrackMetadata = ProviderTrackMetadata.Empty,
) {
    val artistText: String
        get() = artists.joinToString(" / ") { it.name }.ifBlank { "未知歌手" }
}

data class MusicPage<T>(
    val items: List<T>,
    val page: Int,
    val pageSize: Int,
    val total: Long? = null,
    val hasMore: Boolean = total?.let { page.toLong() * pageSize < it } ?: (items.size >= pageSize),
)

enum class AudioQualityTier {
    Standard,
    High,
    Lossless,
    HiResolution,
    Immersive,
    Master,
}

sealed interface PlaybackResolution {
    data class Playable(
        val url: String,
        val requestedQuality: AudioQualityTier,
        val actualQuality: AudioQualityTier? = null,
        val bitrate: Int? = null,
        val format: String? = null,
    ) : PlaybackResolution

    data class Preview(
        val url: String,
        val durationMs: Long? = null,
    ) : PlaybackResolution

    data object LoginRequired : PlaybackResolution
    data object SubscriptionRequired : PlaybackResolution
    data object RegionRestricted : PlaybackResolution
    data object CopyrightRestricted : PlaybackResolution

    data class Unavailable(
        val reason: String? = null,
    ) : PlaybackResolution
}
