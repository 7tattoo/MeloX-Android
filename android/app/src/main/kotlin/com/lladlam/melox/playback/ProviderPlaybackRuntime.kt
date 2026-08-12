package com.lladlam.melox.playback

import android.content.Context
import com.lladlam.melox.core.account.NeteaseSessionStore
import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.core.music.provider.MeloXMusicProviders
import com.lladlam.melox.core.music.provider.MusicProviderRegistry
import com.lladlam.melox.core.provider.kugou.KugouSessionStore
import com.lladlam.melox.core.provider.qqmusic.QQMusicSessionStore

/**
 * Process-local bridge used by Media3's synchronous Resolver callback. No
 * credentials are embedded into MediaItems or custom URIs.
 */
object ProviderPlaybackRuntime {
    @Volatile
    private var appContext: Context? = null

    @Volatile
    private var currentRegistry: MusicProviderRegistry? = null

    fun initialize(context: Context) {
        val application = context.applicationContext
        if (appContext === application && currentRegistry != null) return
        synchronized(this) {
            if (appContext === application && currentRegistry != null) return
            appContext = application
            currentRegistry = MeloXMusicProviders.create(application)
        }
    }

    fun registryOrNull(): MusicProviderRegistry? = currentRegistry

    fun authKey(source: MusicSource): String {
        val context = appContext ?: return ""
        return when (source) {
            MusicSource.Netease -> NeteaseSessionStore.readCookie(context)
            MusicSource.QQMusic -> QQMusicSessionStore.read(context).cookie
            MusicSource.Kugou -> KugouSessionStore.read(context).let { session ->
                listOf(session.userId, session.token, session.vipToken, session.dfid).joinToString("|")
            }
        }
    }
}
