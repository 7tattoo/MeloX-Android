package com.lladlam.melox.core.lyrics

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class LrcLyricsParserTest {
    @Test
    fun providerLrcFallbackDoesNotBecomeSyntheticWordTiming() {
        val document = LrcLyricsParser.parse(
            lrc = "[00:01.00]第一句歌词\n[00:04.00]第二句歌词",
        )

        assertFalse(document.pseudoTimingAllowed)
        assertTrue(document.lines.isNotEmpty())
        assertTrue(document.withPseudoTiming().lines.all { it.syllables.isEmpty() })
    }

    @Test
    fun nativeNeteaseLineLyricsKeepExistingPseudoTimingBehavior() {
        val document = NeteaseLyricParser.parse(
            yrc = "",
            lrc = "[00:01.00]第一句歌词\n[00:04.00]第二句歌词",
        )

        assertTrue(document.pseudoTimingAllowed)
        assertTrue(document.withPseudoTiming().lines.first().syllables.isNotEmpty())
    }
}
