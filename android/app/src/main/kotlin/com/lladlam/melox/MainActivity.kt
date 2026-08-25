package com.lladlam.melox

import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.content.res.Configuration
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.lifecycle.lifecycleScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
        MeloXSettingsPreferences.initializeCritical(this)

        setContent {
            MeloXTheme {
                // 投屏（车机横屏）适配：车机屏幕宽（>600dp）且横屏时，整体缩小 UI
                // 密度（density × 0.8），使主页面/播放页部件变小、布局按比例重排。
                val configuration = LocalConfiguration.current
                val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
                val isWide = configuration.screenWidthDp >= 600
                val baseDensity = LocalDensity.current
                CompositionLocalProvider(
                    LocalDensity provides Density(
                        density = baseDensity.density * if (isLandscape && isWide) CAR_UI_SCALE else 1f,
                        fontScale = baseDensity.fontScale,
                    ),
                ) {
                    MeloXApp(
                        openNowPlayingRequest = openNowPlayingRequest,
                        clipboardLinkRequest = clipboardLinkRequest,
                        onClipboardLinkConsumed = { clipboardLinkRequest = null },
                    )
                }
            }
        }

        lifecycleScope.launch {
            delay(350L)
            MeloXSettingsPreferences.initialize(this@MainActivity)
        }

        // Lyricon registers a cross-process provider. Starting it before
        // setContent delayed the first frame and left a white window on cold
        // launch. Listen Together is intentionally not started here: its UI or
        // an incoming invitation creates the coordinator only when needed.
        lifecycleScope.launch {
            delay(250L)
            MeloXLyriconBridge.start(applicationContext)
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
        /** 横屏(车机投屏)时 UI 整体缩放因子。1.0 为原始尺寸，0.8 缩小 20% 更适配车机。 */
        const val CAR_UI_SCALE = 0.8f
    }
}
