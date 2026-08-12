package com.lladlam.melox.playback

import android.net.Uri
import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSpec
import androidx.media3.datasource.ResolvingDataSource
import com.lladlam.melox.core.audio.MusicQualityRuntime
import com.lladlam.melox.core.music.model.AudioQualityTier
import com.lladlam.melox.core.music.model.MusicResourceId
import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.core.music.model.MusicTrack
import com.lladlam.melox.core.music.model.PlaybackResolution
import com.lladlam.melox.core.music.model.ProviderTrackMetadata
import com.lladlam.melox.core.music.provider.MusicProviderRegistry
import com.lladlam.melox.core.music.provider.PlaybackCapability
import java.io.IOException
import java.util.concurrent.ConcurrentHashMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

/**
 * Adds provider-aware `melox://track/...` URIs while delegating every legacy
 * `melox://song/<Long>` URI to the untouched NetEase resolver.
 */
@OptIn(UnstableApi::class)
class ProviderPlaybackResolver(
    private val neteaseResolver: NeteasePlaybackResolver,
    private val providers: MusicProviderRegistry,
    private val authKeyProvider: (MusicSource) -> String = { "" },
) : ResolvingDataSource.Resolver {
    private data class ResolveKey(
        val requestUri: String,
        val authKey: String,
        val quality: AudioQualityTier,
    )

    private val resolvedUris = ConcurrentHashMap<ResolveKey, Uri>()

    override fun resolveDataSpec(dataSpec: DataSpec): DataSpec {
        val uri = dataSpec.uri
        if (uri.scheme != MeloXScheme) return dataSpec
        if (uri.host == LegacySongHost) return neteaseResolver.resolveDataSpec(dataSpec)
        if (uri.host != ProviderTrackHost) return dataSpec
        return dataSpec.withUri(resolveProviderUri(uri))
    }

    override fun resolveReportedUri(uri: Uri): Uri {
        if (uri.scheme != MeloXScheme) return uri
        if (uri.host == LegacySongHost) return neteaseResolver.resolveReportedUri(uri)
        if (uri.host != ProviderTrackHost) return uri
        val source = parseSource(uri) ?: return uri
        val quality = currentQuality(uri)
        return resolvedUris[ResolveKey(uri.toString(), authKeyProvider(source), quality)] ?: uri
    }

    private fun resolveProviderUri(uri: Uri): Uri {
        val source = parseSource(uri)
            ?: throw IOException("Invalid MeloX provider source: $uri")
        val resourceValue = uri.pathSegments.getOrNull(1)
            ?.let(Uri::decode)
            ?.takeIf(String::isNotBlank)
            ?: throw IOException("Invalid MeloX provider track ID: $uri")
        val quality = currentQuality(uri)
        val key = ResolveKey(uri.toString(), authKeyProvider(source), quality)
        resolvedUris[key]?.let { return it }

        val id = MusicResourceId(source, resourceValue)
        val track = MusicTrack(
            id = id,
            title = "",
            artists = emptyList(),
            providerMetadata = providerMetadata(uri, id),
        )
        val provider = providers.require(source)
        val playback = provider as? PlaybackCapability
            ?: throw IOException("${provider.displayName} 当前没有实现播放能力")
        val resolution = runBlocking(Dispatchers.IO) {
            playback.resolvePlayback(track, quality)
        }
        val result = when (resolution) {
            is PlaybackResolution.Playable -> Uri.parse(resolution.url)
            is PlaybackResolution.Preview -> Uri.parse(resolution.url)
            PlaybackResolution.LoginRequired -> throw IOException("${provider.displayName} 需要登录后播放")
            PlaybackResolution.SubscriptionRequired -> throw IOException("${provider.displayName} 当前歌曲需要对应会员权益")
            PlaybackResolution.RegionRestricted -> throw IOException("${provider.displayName} 当前地区不可播放")
            PlaybackResolution.CopyrightRestricted -> throw IOException("${provider.displayName} 当前版权不可播放")
            is PlaybackResolution.Unavailable -> throw IOException(
                resolution.reason ?: "${provider.displayName} 暂时没有可播放音源",
            )
        }
        resolvedUris[key] = result
        return result
    }

    private fun currentQuality(uri: Uri): AudioQualityTier {
        // MusicQualityRuntime is updated by the quality selector before Media3 is
        // re-prepared. Using it here makes a provider item re-resolve even though
        // its stable melox://track identity does not change.
        val runtime = MusicQualityRuntime.selected.toCommonTier()
        val encoded = uri.getQueryParameter(QualityQuery)
            ?.let { raw -> AudioQualityTier.entries.firstOrNull { it.name == raw } }
        return if (MusicQualityRuntime.selected.apiLevel.isNotBlank()) runtime else encoded ?: AudioQualityTier.Standard
    }

    private fun parseSource(uri: Uri): MusicSource? {
        val raw = uri.pathSegments.firstOrNull() ?: return null
        return MusicSource.entries.firstOrNull { it.storageValue == raw }
    }

    private fun providerMetadata(uri: Uri, id: MusicResourceId): ProviderTrackMetadata = when (id.source) {
        MusicSource.Netease -> ProviderTrackMetadata.Netease(
            numericId = id.value.toLongOrNull()
                ?: throw IOException("Invalid NetEase track ID: ${id.value}"),
        )
        MusicSource.QQMusic -> ProviderTrackMetadata.QQMusic(
            songMid = id.value,
            mediaMid = uri.getQueryParameter(QQMediaMidQuery)?.takeIf(String::isNotBlank),
            numericSongId = uri.getQueryParameter(QQNumericIdQuery)?.toLongOrNull(),
        )
        MusicSource.Kugou -> ProviderTrackMetadata.Kugou(
            hash = id.value,
            albumAudioId = uri.getQueryParameter(KugouAlbumAudioIdQuery)?.toLongOrNull(),
            albumId = uri.getQueryParameter(KugouAlbumIdQuery)?.takeIf(String::isNotBlank),
        )
    }

    companion object {
        private const val MeloXScheme = "melox"
        private const val LegacySongHost = "song"
        private const val ProviderTrackHost = "track"
        private const val QualityQuery = "qualityTier"
        private const val QQMediaMidQuery = "qqMediaMid"
        private const val QQNumericIdQuery = "qqNumericId"
        private const val KugouAlbumAudioIdQuery = "kgAlbumAudioId"
        private const val KugouAlbumIdQuery = "kgAlbumId"

        fun isProviderTrackUri(uri: Uri): Boolean =
            uri.scheme == MeloXScheme && uri.host == ProviderTrackHost

        fun uriForTrack(
            track: MusicTrack,
            quality: AudioQualityTier,
        ): Uri = Uri.Builder()
            .scheme(MeloXScheme)
            .authority(ProviderTrackHost)
            .appendPath(track.id.source.storageValue)
            .appendPath(track.id.value)
            .appendQueryParameter(QualityQuery, quality.name)
            .apply {
                when (val metadata = track.providerMetadata) {
                    is ProviderTrackMetadata.QQMusic -> {
                        metadata.mediaMid?.takeIf(String::isNotBlank)?.let {
                            appendQueryParameter(QQMediaMidQuery, it)
                        }
                        metadata.numericSongId?.let {
                            appendQueryParameter(QQNumericIdQuery, it.toString())
                        }
                    }
                    is ProviderTrackMetadata.Kugou -> {
                        metadata.albumAudioId?.let {
                            appendQueryParameter(KugouAlbumAudioIdQuery, it.toString())
                        }
                        metadata.albumId?.takeIf(String::isNotBlank)?.let {
                            appendQueryParameter(KugouAlbumIdQuery, it)
                        }
                    }
                    else -> Unit
                }
            }
            .build()
    }
}
