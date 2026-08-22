package com.lladlam.melox.core.music.provider

import com.lladlam.melox.core.music.model.MusicArtistRef
import com.lladlam.melox.core.music.model.MusicResourceId
import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.core.music.model.MusicTrack
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TrackAggregationTest {
    private fun track(source: MusicSource, title: String, duration: Long = 240_000L) = MusicTrack(
        id = MusicResourceId(source, title + source.storageValue),
        title = title,
        artists = listOf(MusicArtistRef(name = "周杰伦")),
        durationMs = duration,
        availability = com.lladlam.melox.core.music.model.TrackAvailability.Playable,
    )

    @Test fun sameStudioTracksAreAggregated() {
        val groups = TrackAggregation.aggregate(listOf(track(MusicSource.Netease, "晴天"), track(MusicSource.QQMusic, "晴天")))
        assertEquals(1, groups.size)
        assertEquals(2, groups.single().candidates.size)
    }

    @Test fun liveVersionDoesNotMergeWithStudio() {
        val groups = TrackAggregation.aggregate(listOf(track(MusicSource.Netease, "晴天"), track(MusicSource.QQMusic, "晴天 (Live)")))
        assertEquals(2, groups.size)
    }

    @Test fun playableCandidateWins() {
        val groups = TrackAggregation.aggregate(listOf(track(MusicSource.Netease, "晴天"), track(MusicSource.QQMusic, "晴天")))
        assertTrue(groups.single().recommendation != null)
    }
}
