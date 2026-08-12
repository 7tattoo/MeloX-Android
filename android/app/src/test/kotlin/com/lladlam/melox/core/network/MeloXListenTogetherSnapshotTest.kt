package com.lladlam.melox.core.network

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class MeloXListenTogetherSnapshotTest {
    @Test
    fun randomModeUsesServerRandomOrder() {
        val snapshot = snapshot(
            display = listOf(1L, 2L, 3L),
            random = listOf(2L, 3L, 1L),
            playMode = "RANDOM",
        )
        assertTrue(snapshot.randomMode)
        assertEquals(listOf(2L, 3L, 1L), snapshot.playbackSongIds)
    }

    @Test
    fun normalModeUsesDisplayOrder() {
        val snapshot = snapshot(
            display = listOf(1L, 2L, 3L),
            random = listOf(3L, 1L, 2L),
            playMode = "ORDER",
        )
        assertFalse(snapshot.randomMode)
        assertEquals(listOf(1L, 2L, 3L), snapshot.playbackSongIds)
    }

    @Test
    fun randomModeFallsBackWhenRandomListIsEmpty() {
        val snapshot = snapshot(
            display = listOf(7L, 8L),
            random = emptyList(),
            playMode = "SHUFFLE",
        )
        assertEquals(listOf(7L, 8L), snapshot.playbackSongIds)
    }

    @Test
    fun commandWireValuesMatchNeteaseProtocol() {
        assertEquals("PLAY", MeloXListenTogetherCommandType.Play.wireValue)
        assertEquals("PAUSE", MeloXListenTogetherCommandType.Pause.wireValue)
        assertEquals("PREV", MeloXListenTogetherCommandType.Previous.wireValue)
        assertEquals("NEXT", MeloXListenTogetherCommandType.Next.wireValue)
        assertEquals("GOTO", MeloXListenTogetherCommandType.GoTo.wireValue)
        assertEquals("PROGRESS", MeloXListenTogetherCommandType.Progress.wireValue)
    }

    private fun snapshot(
        display: List<Long>,
        random: List<Long>,
        playMode: String,
    ) = MeloXListenTogetherSnapshot(
        displaySongIds = display,
        randomSongIds = random,
        playMode = playMode,
        targetSongId = display.firstOrNull(),
        formerSongId = null,
        progressMs = 0L,
        isPlaying = true,
        commandUserId = null,
        clientSequence = 0L,
        serverSequence = 0L,
    )
}
