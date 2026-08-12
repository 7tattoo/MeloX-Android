package com.lladlam.melox.ui.provider

import androidx.compose.runtime.Composable
import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.ui.search.SearchScreen

/**
 * Compatibility entry point kept while MeloXApp still references the old name.
 * The provider-specific search UI has been removed: all services render the
 * already-migrated MeloX SearchScreen and only swap the backing data source.
 */
@Composable
fun ProviderSearchScreen(source: MusicSource) = SearchScreen(source)
