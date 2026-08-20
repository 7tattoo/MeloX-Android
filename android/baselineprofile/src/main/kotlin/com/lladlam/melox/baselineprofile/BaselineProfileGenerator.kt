package com.lladlam.melox.baselineprofile

import androidx.benchmark.macro.junit4.BaselineProfileRule
import org.junit.Rule
import org.junit.Test

class BaselineProfileGenerator {
    @get:Rule
    val rule = BaselineProfileRule()

    @Test
    fun generate() = rule.collect(
        packageName = MeloXPackage,
        includeInStartupProfile = true,
    ) {
        val device = startMeloX()
        device.openSettingsAndScroll()
        device.exercisePlayerIfAvailable()
    }
}
