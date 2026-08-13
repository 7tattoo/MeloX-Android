package com.lladlam.melox.core.music.provider

import android.content.Context
import com.lladlam.melox.core.account.NeteaseSessionStore
import com.lladlam.melox.core.provider.kugou.KugouProvider
import com.lladlam.melox.core.provider.kugou.KugouSessionStore
import com.lladlam.melox.core.provider.netease.NeteaseProvider
import com.lladlam.melox.core.provider.qqmusic.QQMusicProvider
import com.lladlam.melox.core.provider.qqmusic.QQMusicSessionStore
import okhttp3.OkHttpClient

/** Creates provider instances that all read their authentication state locally. */
object MeloXMusicProviders {
    fun create(
        context: Context,
        httpClient: OkHttpClient = OkHttpClient(),
    ): MusicProviderRegistry {
        val appContext = context.applicationContext
        return MusicProviderRegistry(
            listOf(
                NeteaseProvider(
                    cookieProvider = { NeteaseSessionStore.readCookie(appContext) },
                    httpClient = httpClient,
                ),
                QQMusicProvider(
                    sessionProvider = { QQMusicSessionStore.read(appContext) },
                    httpClient = httpClient,
                ),
                KugouProvider(
                    sessionProvider = { KugouSessionStore.read(appContext) },
                    httpClient = httpClient,
                ),
            ),
        )
    }
}
