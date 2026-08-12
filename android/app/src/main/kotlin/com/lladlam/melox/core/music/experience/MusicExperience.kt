package com.lladlam.melox.core.music.experience

import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.core.music.provider.MusicCapability

enum class ExperienceTabId {
    Home,
    Explore,
    Library,
    Settings,
    Search,
}

data class ExperienceTab(
    val id: ExperienceTabId,
    val title: String,
)

enum class HomeSectionKind {
    QuickActions,
    Recommendations,
    Playlists,
    NewSongs,
    Rankings,
    Artists,
    Radio,
    Podcasts,
}

/**
 * Product/navigation description for one music service. The Compose shell keeps
 * MeloX visual language, while the available content can differ by provider.
 */
data class MusicExperience(
    val source: MusicSource,
    val tabs: List<ExperienceTab>,
    val homeSections: List<HomeSectionKind>,
    val providerNativeCapabilities: Set<MusicCapability> = emptySet(),
)

object MusicExperiences {
    val netease = MusicExperience(
        source = MusicSource.Netease,
        tabs = listOf(
            ExperienceTab(ExperienceTabId.Home, "首页"),
            ExperienceTab(ExperienceTabId.Explore, "发现"),
            ExperienceTab(ExperienceTabId.Library, "音乐库"),
            ExperienceTab(ExperienceTabId.Settings, "设置"),
        ),
        homeSections = listOf(
            HomeSectionKind.QuickActions,
            HomeSectionKind.Recommendations,
            HomeSectionKind.Playlists,
            HomeSectionKind.NewSongs,
        ),
        providerNativeCapabilities = setOf(
            MusicCapability.DailyRecommendations,
            MusicCapability.Podcasts,
            MusicCapability.CloudMusic,
            MusicCapability.PrivateFm,
            MusicCapability.HeartMode,
            MusicCapability.ListenTogether,
            MusicCapability.Messages,
            MusicCapability.Recognition,
        ),
    )

    val qqMusic = MusicExperience(
        source = MusicSource.QQMusic,
        tabs = listOf(
            ExperienceTab(ExperienceTabId.Home, "首页"),
            ExperienceTab(ExperienceTabId.Explore, "发现"),
            ExperienceTab(ExperienceTabId.Library, "我的"),
            ExperienceTab(ExperienceTabId.Settings, "设置"),
        ),
        homeSections = listOf(
            HomeSectionKind.Recommendations,
            HomeSectionKind.Playlists,
            HomeSectionKind.NewSongs,
            HomeSectionKind.Rankings,
            HomeSectionKind.Radio,
        ),
    )

    val kugou = MusicExperience(
        source = MusicSource.Kugou,
        tabs = listOf(
            ExperienceTab(ExperienceTabId.Home, "首页"),
            ExperienceTab(ExperienceTabId.Explore, "乐库"),
            ExperienceTab(ExperienceTabId.Library, "我的"),
            ExperienceTab(ExperienceTabId.Settings, "设置"),
        ),
        homeSections = listOf(
            HomeSectionKind.Recommendations,
            HomeSectionKind.Playlists,
            HomeSectionKind.Rankings,
            HomeSectionKind.Radio,
        ),
    )

    fun forSource(source: MusicSource): MusicExperience = when (source) {
        MusicSource.Netease -> netease
        MusicSource.QQMusic -> qqMusic
        MusicSource.Kugou -> kugou
    }
}
