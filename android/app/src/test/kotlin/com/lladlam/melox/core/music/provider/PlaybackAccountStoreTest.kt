package com.lladlam.melox.core.music.provider

import org.junit.Assert.assertEquals
import org.junit.Test

class PlaybackAccountStoreTest {
    @Test fun disabledSlotFallsBackToMain() = assertEquals("main", selectPlaybackSession(false, "second", "main") { it.isNotBlank() })
    @Test fun emptyPlaybackSlotFallsBackToMain() = assertEquals("main", selectPlaybackSession(true, "", "main") { it.isNotBlank() })
    @Test fun secondNeteaseSessionWins() = assertEquals("second", selectPlaybackSession(true, "second", "main") { it.isNotBlank() })
    @Test fun secondQqSessionWins() = assertEquals("second", selectPlaybackSession(true, "second", "main") { it.isNotBlank() })
    @Test fun secondKugouSessionWins() = assertEquals("second", selectPlaybackSession(true, "second", "main") { it.isNotBlank() })
}
