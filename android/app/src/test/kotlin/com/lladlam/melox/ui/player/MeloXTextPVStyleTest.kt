package com.lladlam.melox.ui.player

import com.lladlam.melox.ui.settings.MeloXTextPVStyle
import org.junit.Assert.assertEquals
import org.junit.Test

class MeloXTextPVStyleTest {
    @Test
    fun eighteenPublishedStylesHaveIndependentVisualSignatures() {
        val compatibility = setOf(MeloXTextPVStyle.Dynamic, MeloXTextPVStyle.Minimal, MeloXTextPVStyle.Cyber)
        val published = MeloXTextPVStyle.entries.filterNot(compatibility::contains)
        assertEquals(18, published.size)
        assertEquals(18, published.map(MeloXTextPVStyle::visualSignature).distinct().size)
    }
}
