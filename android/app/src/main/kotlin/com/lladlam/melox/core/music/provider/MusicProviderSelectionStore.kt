package com.lladlam.melox.core.music.provider

import android.content.Context
import com.lladlam.melox.core.music.model.MusicSource

/**
 * Provider selection is local-only. Cross-provider aggregation is deliberately
 * opt-in and defaults to disabled.
 */
object MusicProviderSelectionStore {
    private const val PreferencesName = "melox_music_providers"
    private const val KeySelectedSource = "selected_source"
    private const val KeyUnifiedEnabled = "unified_enabled"
    private const val KeyAutomaticFallback = "automatic_source_fallback"
    private const val KeyUnifiedSources = "unified_sources"

    fun selectedSource(context: Context): MusicSource {
        val preferences = context.applicationContext
            .getSharedPreferences(PreferencesName, Context.MODE_PRIVATE)
        return MusicSource.fromStorageValue(preferences.getString(KeySelectedSource, null))
    }

    fun setSelectedSource(context: Context, source: MusicSource) {
        context.applicationContext
            .getSharedPreferences(PreferencesName, Context.MODE_PRIVATE)
            .edit()
            .putString(KeySelectedSource, source.storageValue)
            .apply()
    }

    fun unifiedEnabled(context: Context): Boolean =
        context.applicationContext
            .getSharedPreferences(PreferencesName, Context.MODE_PRIVATE)
            .getBoolean(KeyUnifiedEnabled, false)

    fun setUnifiedEnabled(context: Context, enabled: Boolean) {
        context.applicationContext
            .getSharedPreferences(PreferencesName, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(KeyUnifiedEnabled, enabled)
            .apply()
    }

    fun automaticFallbackEnabled(context: Context): Boolean =
        context.applicationContext
            .getSharedPreferences(PreferencesName, Context.MODE_PRIVATE)
            .getBoolean(KeyAutomaticFallback, false)

    fun setAutomaticFallbackEnabled(context: Context, enabled: Boolean) {
        context.applicationContext
            .getSharedPreferences(PreferencesName, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(KeyAutomaticFallback, enabled)
            .apply()
    }

    fun unifiedSources(context: Context): Set<MusicSource> {
        val raw = context.applicationContext
            .getSharedPreferences(PreferencesName, Context.MODE_PRIVATE)
            .getStringSet(KeyUnifiedSources, emptySet())
            .orEmpty()
        return raw.mapTo(linkedSetOf(), MusicSource::fromStorageValue)
    }

    fun setUnifiedSources(context: Context, sources: Set<MusicSource>) {
        context.applicationContext
            .getSharedPreferences(PreferencesName, Context.MODE_PRIVATE)
            .edit()
            .putStringSet(KeyUnifiedSources, sources.mapTo(linkedSetOf()) { it.storageValue })
            .apply()
    }
}
