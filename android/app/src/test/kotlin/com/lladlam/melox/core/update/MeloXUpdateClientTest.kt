package com.lladlam.melox.core.update

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class MeloXUpdateClientTest {
    private val client = MeloXUpdateClient()

    @Test
    fun androidTagPrefixIsIgnored() {
        assertTrue(client.isNewer("android-v0.4.2-Dev", "0.4.1-Beta"))
    }

    @Test
    fun equalAndroidTagIsNotNewer() {
        assertFalse(client.isNewer("android-v0.4.2-Dev", "0.4.2-Dev"))
    }

    @Test
    fun regularVersionPrefixStillWorks() {
        assertTrue(client.isNewer("v0.5.0", "0.4.2-Dev"))
    }
}
