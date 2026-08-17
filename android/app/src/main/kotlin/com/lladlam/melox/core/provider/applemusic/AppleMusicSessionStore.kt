package com.lladlam.melox.core.provider.applemusic

import android.content.Context

/**
 * Local Apple Music API credentials.
 *
 * The developer token should normally be issued by the app's own backend. It
 * is intentionally entered/configured by the user here; MeloX never embeds an
 * Apple private key or extracts tokens from the Apple Music APK.
 */
data class AppleMusicSession(
    val developerToken: String,
    val musicUserToken: String,
    val storefront: String,
) {
    val isConfigured: Boolean
        get() = developerToken.isNotBlank()

    val hasUserAuthorization: Boolean
        get() = musicUserToken.isNotBlank()
}

object AppleMusicSessionStore {
    private const val PreferencesName = "melox_apple_music"
    private const val DeveloperTokenKey = "developer_token"
    private const val MusicUserTokenKey = "music_user_token"
    private const val StorefrontKey = "storefront"

    fun read(context: Context): AppleMusicSession {
        val preferences = context.applicationContext
            .getSharedPreferences(PreferencesName, Context.MODE_PRIVATE)
        return AppleMusicSession(
            developerToken = preferences.getString(DeveloperTokenKey, "").orEmpty(),
            musicUserToken = preferences.getString(MusicUserTokenKey, "").orEmpty(),
            storefront = preferences.getString(StorefrontKey, "us")
                .orEmpty()
                .ifBlank { "us" },
        )
    }

    fun write(
        context: Context,
        developerToken: String,
        musicUserToken: String,
        storefront: String,
    ) {
        context.applicationContext
            .getSharedPreferences(PreferencesName, Context.MODE_PRIVATE)
            .edit()
            .putString(DeveloperTokenKey, developerToken.trim())
            .putString(MusicUserTokenKey, musicUserToken.trim())
            .putString(StorefrontKey, storefront.trim().lowercase().ifBlank { "us" })
            .apply()
    }

    fun clear(context: Context) {
        context.applicationContext
            .getSharedPreferences(PreferencesName, Context.MODE_PRIVATE)
            .edit()
            .clear()
            .apply()
    }
}
