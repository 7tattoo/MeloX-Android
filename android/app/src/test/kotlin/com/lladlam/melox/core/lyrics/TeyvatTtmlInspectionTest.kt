package com.lladlam.melox.core.lyrics

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TeyvatTtmlInspectionTest {
    @Test
    fun parsesDownloadedTeyvatTtml() {
        val ttml = requireNotNull(javaClass.classLoader?.getResource("teyvat.ttml"))
            .readText()
        val document = TtmlLyricsParser.parse(ttml)
        println("LINES=${document.lines.size}")
        println("WORDS=${document.lines.count { it.syllables.isNotEmpty() }}")
        println("LEFT=${document.lines.count { it.agent?.alignment == LyricAgentAlignment.Normal }}")
        println("RIGHT=${document.lines.count { it.agent?.alignment == LyricAgentAlignment.Flipped }}")
        println("BG=${document.lines.sumOf { it.accompaniment.size }}")
        println("TRANS=${document.lines.count { !it.translation.isNullOrBlank() }}")
        println("ROMA=${document.lines.count { !it.romanization.isNullOrBlank() }}")
        assertEquals(53, document.lines.size)
        assertTrue(document.lines.all { it.syllables.isNotEmpty() })
        assertTrue(document.lines.any { it.agent?.alignment == LyricAgentAlignment.Flipped })
        assertEquals(17, document.lines.sumOf { it.accompaniment.size })
    }
}
