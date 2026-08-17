package com.lladlam.melox.core.provider.applemusic

import android.content.Context
import android.content.Intent
import java.lang.reflect.Proxy

/**
 * Optional bridge to Apple's official MusicKit for Android AARs.
 *
 * Apple distributes `musickitauth-release-*.aar` and
 * `mediaplayback-release-*.aar` through the developer download portal rather
 * than Maven. Reflection keeps the catalog-only build compilable until those
 * official binaries are supplied; it never loads classes from the reverse-
 * engineered Apple Music APK.
 */
object AppleMusicSdkBridge {
    private const val AuthFactory = "com.apple.android.sdk.authentication.AuthenticationFactory"
    private const val MediaControllerFactory = "com.apple.android.music.playback.controller.MediaPlayerControllerFactory"
    private const val TokenProvider = "com.apple.android.sdk.authentication.TokenProvider"
    private const val QueueBuilder = "com.apple.android.music.playback.queue.CatalogPlaybackQueueItemProvider\$Builder"
    private const val QueueProvider = "com.apple.android.music.playback.queue.PlaybackQueueItemProvider"
    private const val MediaItemType = "com.apple.android.music.playback.model.MediaItemType"

    @Volatile
    private var authenticationManager: Any? = null

    @Volatile
    private var mediaPlayerController: Any? = null

    fun isAuthenticationSdkAvailable(): Boolean = classAvailable(AuthFactory)

    fun isPlaybackSdkAvailable(): Boolean =
        classAvailable(MediaControllerFactory) && classAvailable(QueueBuilder)

    fun createAuthenticationIntent(context: Context, developerToken: String): Intent? = runCatching {
        val factory = Class.forName(AuthFactory)
        val manager = factory.getMethod("createAuthenticationManager", Context::class.java)
            .invoke(null, context.applicationContext)
        authenticationManager = manager
        val builder = manager.javaClass
            .getMethod("createIntentBuilder", String::class.java)
            .invoke(manager, developerToken)
        runCatching {
            builder.javaClass
                .getMethod("setHideStartScreen", Boolean::class.javaPrimitiveType)
                .invoke(builder, false)
        }
        builder.javaClass.getMethod("build").invoke(builder) as Intent
    }.getOrNull()

    fun extractMusicUserToken(data: Intent?): String? {
        if (data == null) return null
        return runCatching {
            val manager = authenticationManager ?: return@runCatching null
            val result = manager.javaClass
                .getMethod("handleTokenResult", Intent::class.java)
                .invoke(manager, data)
            val isError = result.javaClass
                .getMethod("isError")
                .invoke(result) as Boolean
            if (isError) {
                null
            } else {
                result.javaClass
                    .getMethod("getMusicUserToken")
                    .invoke(result) as? String
            }
        }.getOrNull()?.takeIf(String::isNotBlank)
    }

    /**
     * Sends a catalog-id queue to Apple's Media Playback SDK. The SDK performs
     * the subscription check, playback lease and DRM session itself.
     */
    fun playCatalogQueue(
        context: Context,
        session: AppleMusicSession,
        catalogIds: List<String>,
        startIndex: Int,
    ): Boolean = runCatching {
        if (!isPlaybackSdkAvailable() || !session.isConfigured || !session.hasUserAuthorization) return false
        val tokenProviderClass = Class.forName(TokenProvider)
        val provider = Proxy.newProxyInstance(
            tokenProviderClass.classLoader,
            arrayOf(tokenProviderClass),
        ) { _, method, _ ->
            when (method.name) {
                "getDeveloperToken" -> session.developerToken
                "getUserToken" -> session.musicUserToken
                else -> null
            }
        }
        val controllerFactory = Class.forName(MediaControllerFactory)
        val controller = controllerFactory
            .getMethod("createLocalController", Context::class.java, tokenProviderClass)
            .invoke(null, context.applicationContext, provider)
        val builder = Class.forName(QueueBuilder).getConstructor().newInstance()
        val songType = Class.forName(MediaItemType).getField("SONG").getInt(null)
        val ids = catalogIds.toTypedArray()
        builder.javaClass
            .getMethod("items", Int::class.javaPrimitiveType, Array<String>::class.java)
            .invoke(builder, songType, ids)
        runCatching {
            builder.javaClass
                .getMethod("startItemIndex", Int::class.javaPrimitiveType)
                .invoke(builder, startIndex.coerceIn(0, ids.lastIndex.coerceAtLeast(0)))
        }
        val queueProvider = builder.javaClass.getMethod("build").invoke(builder)
        controller.javaClass
            .getMethod("prepare", Class.forName(QueueProvider), Boolean::class.javaPrimitiveType)
            .invoke(controller, queueProvider, true)
        mediaPlayerController = controller
        true
    }.getOrDefault(false)

    fun releasePlayback() {
        runCatching { mediaPlayerController?.javaClass?.getMethod("release")?.invoke(mediaPlayerController) }
        mediaPlayerController = null
    }

    private fun classAvailable(name: String): Boolean = runCatching { Class.forName(name); true }.getOrDefault(false)
}
