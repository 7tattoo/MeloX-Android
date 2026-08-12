package com.lladlam.melox.playback

import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.lladlam.melox.core.audio.MusicQuality
import com.lladlam.melox.core.audio.MusicQualityPreferences
import com.lladlam.melox.core.audio.MusicQualityRuntime
import com.lladlam.melox.core.music.model.AudioQualityTier
import com.lladlam.melox.core.music.model.MusicResourceId
import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.core.music.model.MusicTrack
import java.util.concurrent.Executor

/** Queue entry point for QQ/Kugou and future mixed-provider results. */
object ProviderPlaybackCommands {
    fun playQueue(
        context: Context,
        tracks: List<MusicTrack>,
        selectedTrackId: MusicResourceId,
        startPositionMs: Long = C.TIME_UNSET,
        onFailure: ((Throwable) -> Unit)? = null,
    ) {
        if (tracks.isEmpty()) return
        val appContext = context.applicationContext
        ProviderPlaybackRuntime.initialize(appContext)
        val neteaseQuality = MusicQualityPreferences.read(appContext)
        MusicQualityRuntime.selected = neteaseQuality
        val qualityTier = neteaseQuality.toCommonTier()
        val startIndex = tracks.indexOfFirst { it.id == selectedTrackId }.coerceAtLeast(0)
        val items = tracks.mapIndexed { index, track ->
            track.toMediaItem(
                neteaseQuality = neteaseQuality,
                qualityTier = qualityTier,
                originalIndex = index,
            )
        }
        val token = SessionToken(
            appContext,
            ComponentName(appContext, MeloXPlaybackService::class.java),
        )
        val future = MediaController.Builder(appContext, token).buildAsync()
        val executor = Executor { command -> appContext.mainExecutor.execute(command) }
        future.addListener(
            {
                runCatching {
                    val controller = future.get()
                    controller.shuffleModeEnabled = false
                    controller.setMediaItems(items, startIndex, startPositionMs)
                    controller.prepare()
                    controller.play()
                }.onFailure { onFailure?.invoke(it) }
            },
            executor,
        )
    }

    internal fun mediaItemFor(
        track: MusicTrack,
        neteaseQuality: MusicQuality = MusicQuality.Standard,
        qualityTier: AudioQualityTier = neteaseQuality.toCommonTier(),
        queueOrigin: String = PlaybackCommands.QUEUE_ORIGIN_BASE,
        originalIndex: Int = PlaybackCommands.QUEUE_ORIGINAL_INDEX_UNSET,
    ): MediaItem = track.toMediaItem(neteaseQuality, qualityTier, queueOrigin, originalIndex)

    private fun MusicTrack.toMediaItem(
        neteaseQuality: MusicQuality,
        qualityTier: AudioQualityTier,
        queueOrigin: String = PlaybackCommands.QUEUE_ORIGIN_BASE,
        originalIndex: Int,
    ): MediaItem {
        val extras = Bundle().apply {
            putString(PlaybackCommands.QUEUE_ORIGIN_KEY, queueOrigin)
            putInt(PlaybackCommands.QUEUE_ORIGINAL_INDEX_KEY, originalIndex)
            putBoolean(PlaybackCommands.HEART_MODE_KEY, false)
            putString(PlaybackTrackIdentity.SourceExtra, id.source.storageValue)
            putString(PlaybackTrackIdentity.ResourceIdExtra, id.value)
        }
        val metadata = MediaMetadata.Builder()
            .setTitle(title)
            .setArtist(artistText)
            .setAlbumTitle(album?.name.orEmpty())
            .setMediaType(MediaMetadata.MEDIA_TYPE_MUSIC)
            .setExtras(extras)
            .apply {
                artworkUrl?.takeIf(String::isNotBlank)?.let {
                    setArtworkUri(android.net.Uri.parse(it))
                }
            }
            .build()

        val neteaseId = id.value.toLongOrNull()
            ?.takeIf { id.source == MusicSource.Netease && it > 0L }
        return MediaItem.Builder()
            .setMediaId(neteaseId?.toString() ?: PlaybackTrackIdentity.encode(id))
            .setUri(
                if (neteaseId != null) {
                    NeteasePlaybackResolver.uriForSong(neteaseId, neteaseQuality)
                } else {
                    ProviderPlaybackResolver.uriForTrack(this, qualityTier)
                },
            )
            .setMediaMetadata(metadata)
            .build()
    }
}

internal fun MusicQuality.toCommonTier(): AudioQualityTier = when (this) {
    MusicQuality.Standard -> AudioQualityTier.Standard
    MusicQuality.High -> AudioQualityTier.High
    MusicQuality.Lossless -> AudioQualityTier.Lossless
    MusicQuality.HiResolution -> AudioQualityTier.HiResolution
    MusicQuality.HighDefinitionSurround,
    MusicQuality.ImmersiveSurround -> AudioQualityTier.Immersive
    MusicQuality.UltraClearMaster -> AudioQualityTier.Master
}
