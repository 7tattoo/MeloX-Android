package com.lladlam.melox

import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.lladlam.melox.playback.MeloXListenTogetherCoordinator
import com.lladlam.melox.core.network.parseNeteaseListenTogetherInvitation
import com.lladlam.melox.platform.lyricon.MeloXLyriconBridge
import com.lladlam.melox.platform.xiaomi.HyperOsFocusBridge
import com.lladlam.melox.ui.player.MeloXListenTogetherInviteActivity
import com.lladlam.melox.ui.MeloXApp
import com.lladlam.melox.ui.settings.MeloXSettingsPreferences
import com.lladlam.melox.ui.theme.MeloXTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private var openNowPlayingRequest by mutableIntStateOf(0)
    private var clipboardLinkRequest by mutableStateOf<String?>(null)
    private var lastClipboardText: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        consumePlaybackIntent(intent)
        MeloXSettingsPreferences.initialize(this)

        setContent {
            MeloXTheme {
                MeloXApp(
                    openNowPlayingRequest = openNowPlayingRequest,
                    clipboardLinkRequest = clipboardLinkRequest,
                    onClipboardLinkConsumed = { clipboardLinkRequest = null },
                )
            }
        }

        // These integrations build MediaControllers and register cross-process
        // providers. Starting them before setContent delayed the first frame and
        // left a white window on cold launch. Give the app chrome one frame to
        // render, then restore the same process-lifetime behavior.
        lifecycleScope.launch {
            delay(250L)
            MeloXLyriconBridge.start(applicationContext)
            MeloXListenTogetherCoordinator.ensureStarted(applicationContext)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        consumePlaybackIntent(intent)
    }

    override fun onResume() {
        super.onResume()
        // Shizuku is optional. On HyperOS 3, request permission only when its
        // service is actually running; permission itself acts as the user's opt-in
        // to the short XMSF compatibility pulse used by some restricted ROM builds.
        lifecycleScope.launch {
            delay(250L)
            HyperOsFocusBridge.prepareShizukuCompatibility(this@MainActivity)
        }

        if (!com.lladlam.melox.ui.settings.MeloXSettingsRuntime.clipboardLinksEnabled) return
        val manager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val text = manager.primaryClip?.getItemAt(0)?.coerceToText(this)?.toString()?.trim().orEmpty()
        if (text.isNotBlank() && text != lastClipboardText) {
            lastClipboardText = text
            val together = parseNeteaseListenTogetherInvitation(text)
            if (together != null) { MeloXListenTogetherInviteActivity.launch(this, together.roomId, together.inviterId); return }
            clipboardLinkRequest = text
        }
    }

    private fun consumePlaybackIntent(intent: Intent?) {
        if (intent?.action == ACTION_OPEN_NOW_PLAYING) {
            openNowPlayingRequest += 1
        }
    }

    companion object {
        const val ACTION_OPEN_NOW_PLAYING =
            "com.lladlam.melox.action.OPEN_NOW_PLAYING"
    }
}
