package com.lladlam.melox.playback

import com.lladlam.melox.core.music.model.AudioQualityTier
import com.lladlam.melox.core.music.model.MusicResourceId
import java.util.concurrent.ConcurrentHashMap

/** Process-local bridge from provider VKey resolution to the shared player UI. */
object ProviderPlaybackQualityRuntime {
    private val actualByTrack = ConcurrentHashMap<String, AudioQualityTier>()

    fun recordActual(id: MusicResourceId, quality: AudioQualityTier) {
        actualByTrack[PlaybackTrackIdentity.encode(id)] = quality
    }

    fun actualFor(id: MusicResourceId?): AudioQualityTier? =
        id?.let { actualByTrack[PlaybackTrackIdentity.encode(it)] }

    fun clear(id: MusicResourceId? = null) {
        if (id == null) actualByTrack.clear()
        else actualByTrack.remove(PlaybackTrackIdentity.encode(id))
    }
}
