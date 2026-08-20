package com.lladlam.melox.playback

import org.junit.Assert.assertEquals
import org.junit.Test

class MeloXEqualizerControllerTest {
    @Test
    fun physicalBandsUseLogFrequencyInterpolation() {
        val gains = List(10) { it.toFloat() }

        assertEquals(0f, MeloXEqualizerController.interpolatedGain(20f, gains), 0.001f)
        assertEquals(5f, MeloXEqualizerController.interpolatedGain(1_000f, gains), 0.001f)
        assertEquals(9f, MeloXEqualizerController.interpolatedGain(20_000f, gains), 0.001f)
        assertEquals(5.5f, MeloXEqualizerController.interpolatedGain(1_414.2135f, gains), 0.02f)
    }
}
