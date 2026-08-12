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
 * Content/capability description for one music service.
 *
 * Root presentation is intentionally canonical MeloX presentation. Providers may
 * expose different content/capabilities, but must not redefine root navigation,
 * animation or visual language. This keeps future MeloX iOS -> Android UI ports
 * provider-agnostic: migrate the canonical renderer once and every compatible
 * provider inherits it automatically.
 */
data class MusicExperience(
    val source: MusicSource,
    val tabs: List<ExperienceTab> = CanonicalMeloXTabs,
    val homeSections: List<HomeSectionKind>,
    val providerNativeCapabilities: Set<MusicCapability> = emptySet(),
)

val CanonicalMeloXTabs: List<ExperienceTab> = listOf(
    ExperienceTab(ExperienceTabId.Home, "首页"),
    ExperienceTab(ExperienceTabId.Explore, "发现"),
    ExperienceTab(ExperienceTabId.Library, "音乐库"),
    ExperienceTab(ExperienceTabId.Settings, "设置"),
)

object MusicExperiences {
    val netease = MusicExperience(
        source = MusicSource.Netease,
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
