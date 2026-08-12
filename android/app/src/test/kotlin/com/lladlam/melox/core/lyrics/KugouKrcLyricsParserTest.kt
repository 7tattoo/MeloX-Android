package com.lladlam.melox.core.lyrics

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class KugouKrcLyricsParserTest {
    @Test
    fun parsesRelativeWordTimingIntoAbsoluteMeloXSyllables() {
        val source = "[1000,2000]<0,500,0>Hello <500,600,0>world"
        val document = KugouKrcLyricsParser.parse(source)

        assertEquals(1, document.lines.size)
        val line = document.lines.single()
        assertEquals(1000L, line.timeMs)
        assertEquals(2000L, line.durationMs)
        assertEquals("Hello world", line.text)
        assertEquals(2, line.syllables.size)
        assertEquals(1000L, line.syllables[0].startTimeMs)
        assertEquals(1500L, line.syllables[0].endTimeMs)
        assertEquals(1500L, line.syllables[1].startTimeMs)
        assertEquals(2100L, line.syllables[1].endTimeMs)
        assertTrue(line.syllables[1].text.contains("world"))
    }
}
