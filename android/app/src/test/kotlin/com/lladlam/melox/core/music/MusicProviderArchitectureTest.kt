package com.lladlam.melox.core.music

import com.lladlam.melox.core.music.experience.ExperienceTabId
import com.lladlam.melox.core.music.experience.MusicExperiences
import com.lladlam.melox.core.music.model.MusicResourceId
import com.lladlam.melox.core.music.model.MusicSource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MusicProviderArchitectureTest {
    @Test
    fun resourceIdsAreNamespacedByProvider() {
        val netease = MusicResourceId(MusicSource.Netease, "123")
        val qq = MusicResourceId(MusicSource.QQMusic, "123")
        assertNotEquals(netease, qq)
    }

    @Test
    fun neteaseExperienceKeepsIosStyleLibraryWhileOtherProvidersCanDiffer() {
        val neteaseLibrary = MusicExperiences.netease.tabs.single { it.id == ExperienceTabId.Library }
        val qqLibrary = MusicExperiences.qqMusic.tabs.single { it.id == ExperienceTabId.Library }
        val kugouExplore = MusicExperiences.kugou.tabs.single { it.id == ExperienceTabId.Explore }

        assertEquals("音乐库", neteaseLibrary.title)
        assertEquals("我的", qqLibrary.title)
        assertEquals("乐库", kugouExplore.title)
        assertTrue(MusicExperiences.netease.providerNativeCapabilities.isNotEmpty())
    }
}
