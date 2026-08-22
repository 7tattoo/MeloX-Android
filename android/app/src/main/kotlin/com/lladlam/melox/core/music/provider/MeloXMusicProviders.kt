package com.lladlam.melox.core.music.provider

import android.content.Context
import com.lladlam.melox.core.account.NeteaseSessionStore
import com.lladlam.melox.core.provider.applemusic.AppleMusicApiClient
import com.lladlam.melox.core.provider.applemusic.AppleMusicSessionStore
import com.lladlam.melox.core.provider.kugou.KugouProvider
import com.lladlam.melox.core.provider.kugou.KugouSessionStore
import com.lladlam.melox.core.provider.netease.NeteaseProvider
import com.lladlam.melox.core.provider.qqmusic.QQMusicProvider
import com.lladlam.melox.core.provider.qqmusic.QQMusicSessionStore
import com.lladlam.melox.core.network.MeloXHttpClient
import okhttp3.OkHttpClient

/** Creates provider instances that all read their authentication state locally. */
object MeloXMusicProviders {
    @Volatile
    private var sharedRegistry: MusicProviderRegistry? = null

    fun create(
        context: Context,
        httpClient: OkHttpClient = MeloXHttpClient.shared,
    ): MusicProviderRegistry {
        val appContext = context.applicationContext
        if (httpClient !== MeloXHttpClient.shared) return buildRegistry(appContext, httpClient)
        return sharedRegistry ?: synchronized(this) {
            sharedRegistry ?: buildRegistry(appContext, httpClient).also { sharedRegistry = it }
        }
    }

    /** Registry reserved for URL resolution and quality probing. */
    fun createPlayback(
        context: Context,
        httpClient: OkHttpClient = MeloXHttpClient.shared,
    ): MusicProviderRegistry {
        val appContext = context.applicationContext
        return MusicProviderRegistry(
            listOf(
                NeteaseProvider({ PlaybackAccountStore.neteaseCookie(appContext) }, httpClient),
                QQMusicProvider({ PlaybackAccountStore.qqSession(appContext) }, httpClient),
                KugouProvider({ PlaybackAccountStore.kugouSession(appContext) }, httpClient),
                AppleMusicApiClient({ AppleMusicSessionStore.read(appContext) }, httpClient),
            ),
        )
    }

    private fun buildRegistry(context: Context, httpClient: OkHttpClient): MusicProviderRegistry =
        MusicProviderRegistry(
            listOf(
                NeteaseProvider(
                    cookieProvider = { NeteaseSessionStore.readCookie(context) },
                    httpClient = httpClient,
                ),
                QQMusicProvider(
                    sessionProvider = { QQMusicSessionStore.read(context) },
                    httpClient = httpClient,
                ),
                KugouProvider(
                    sessionProvider = { KugouSessionStore.read(context) },
                    httpClient = httpClient,
                ),
                AppleMusicApiClient(
                    sessionProvider = { AppleMusicSessionStore.read(context) },
                    httpClient = httpClient,
                ),
            ),
        )
}
