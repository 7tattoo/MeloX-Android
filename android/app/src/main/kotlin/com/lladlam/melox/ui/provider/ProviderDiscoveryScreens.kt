package com.lladlam.melox.ui.provider

import androidx.compose.runtime.Composable
import com.lladlam.melox.core.account.rememberNeteaseSessionStore
import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.ui.discovery.MeloXExploreScreen
import com.lladlam.melox.ui.discovery.MeloXHomeScreen
import com.lladlam.melox.ui.library.LibraryScreen

/**
 * Compatibility entry points while MeloXApp still imports provider-prefixed names.
 *
 * These functions intentionally contain no provider presentation. Provider
 * differences end at the data/capability bridge; all animation, liquid-glass,
 * flowing-light backgrounds and future MeloX/iOS UI migrations live in the
 * canonical MeloX screens.
 */
@Composable
fun ProviderHomeScreen(source: MusicSource) = MeloXHomeScreen(source)

@Composable
fun ProviderExploreScreen(source: MusicSource) = MeloXExploreScreen(source)

@Composable
fun ProviderLibraryScreen(source: MusicSource) {
    LibraryScreen(
        session = rememberNeteaseSessionStore(),
        onLogin = null,
        source = source,
    )
}
