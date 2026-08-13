package com.lladlam.melox.core.provider.qqmusic

import com.lladlam.melox.core.music.model.AudioQualityTier
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class QQMusicPlaybackVkeyClientTest {
    @Test
    fun usesMediaMidDirectlyWhenAvailable() {
        val candidate = QQPlaybackCandidate(
            prefix = "F000",
            extension = ".flac",
            actualTier = AudioQualityTier.Lossless,
        )
        assertEquals(
            "F000MEDIA123.flac",
            candidate.fileName(songMid = "SONG123", mediaMid = "MEDIA123"),
        )
    }

    @Test
    fun duplicatesSongMidOnlyWhenMediaMidIsMissing() {
        val candidate = QQPlaybackCandidate(
            prefix = "M800",
            extension = ".mp3",
            actualTier = AudioQualityTier.High,
        )
        assertEquals(
            "M800SONG123SONG123.mp3",
            candidate.fileName(songMid = "SONG123", mediaMid = null),
        )
    }

    @Test
    fun losslessFallsBackWithoutPretendingTheTierStayedLossless() {
        val candidates = AudioQualityTier.Lossless.qqPlaybackCandidates()
        assertEquals(
            listOf(AudioQualityTier.Lossless, AudioQualityTier.High, AudioQualityTier.Standard),
            candidates.map(QQPlaybackCandidate::actualTier),
        )
        assertEquals(listOf("F000", "M800", "M500"), candidates.map(QQPlaybackCandidate::prefix))
    }

    @Test
    fun masterStartsWithRealMasterFileCode() {
        val candidates = AudioQualityTier.Master.qqPlaybackCandidates()
        assertEquals("AI00", candidates.first().prefix)
        assertEquals(AudioQualityTier.Master, candidates.first().actualTier)
    }

    @Test
    fun vkeyNoPermissionCodeIsReportedHonestly() {
        val message = qqVkeyBusinessReason(104003)
        assertTrue(message.contains("没有该音源的播放权限"))
        assertTrue(message.contains("104003"))
    }

    @Test
    fun noPermissionBeatsGenericVkeyFailureInFinalReason() {
        val reason = linkedSetOf(104004, 104003).qqVkeyFinalReason().orEmpty()
        assertTrue(reason.contains("104003"))
    }

    @Test
    fun deviceRestrictionHasHighestPriority() {
        val reason = linkedSetOf(104003, 104013).qqVkeyFinalReason().orEmpty()
        assertTrue(reason.contains("104013"))
    }
}
