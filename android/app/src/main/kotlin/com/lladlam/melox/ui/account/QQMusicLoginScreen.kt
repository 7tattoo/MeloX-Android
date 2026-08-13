package com.lladlam.melox.ui.account

import android.annotation.SuppressLint
import android.graphics.Color as AndroidColor
import android.os.SystemClock
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.lladlam.melox.core.provider.qqmusic.QQMusicApiClient
import com.lladlam.melox.core.provider.qqmusic.QQMusicSessionStore
import com.lladlam.melox.ui.glass.meloXLiquidButton
import kotlinx.coroutines.delay

private const val QQ_MUSIC_LOGIN_URL = "https://y.qq.com/"
private const val COOKIE_POLL_INTERVAL_MS = 500L
private const val COOKIE_STABLE_POLLS_BEFORE_VERIFY = 3
private const val VERIFY_RETRY_COOLDOWN_MS = 8_000L

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun QQMusicLoginScreen(
    onDismiss: () -> Unit,
    onLoggedIn: () -> Unit,
) {
    val context = LocalContext.current.applicationContext
    var webView by remember { mutableStateOf<WebView?>(null) }
    var pageLoading by remember { mutableStateOf(true) }
    var verifying by remember { mutableStateOf(false) }
    var verificationError by remember { mutableStateOf<String?>(null) }

    BackHandler {
        val view = webView
        if (view?.canGoBack() == true) view.goBack() else onDismiss()
    }

    LaunchedEffect(webView) {
        if (webView == null) return@LaunchedEffect

        var previousCandidate = ""
        var stablePolls = 0
        var lastAttemptFingerprint: String? = null
        var lastAttemptAt = 0L

        while (true) {
            val candidate = collectQQMusicCookieHeader()
            val session = QQMusicSessionStore.parse(candidate)

            if (candidate.isNotBlank() && candidate == previousCandidate) {
                stablePolls += 1
            } else {
                previousCandidate = candidate
                stablePolls = if (candidate.isBlank()) 0 else 1
            }

            if (session.isLoggedIn && stablePolls >= COOKIE_STABLE_POLLS_BEFORE_VERIFY && !verifying) {
                val fingerprint = "${session.uin}:${session.musicKey}"
                val now = SystemClock.elapsedRealtime()
                val shouldAttempt =
                    fingerprint != lastAttemptFingerprint || now - lastAttemptAt >= VERIFY_RETRY_COOLDOWN_MS

                if (shouldAttempt) {
                    lastAttemptFingerprint = fingerprint
                    lastAttemptAt = now
                    verifying = true
                    verificationError = null

                    val result = runCatching {
                        QQMusicApiClient(sessionProvider = { session }).accountProfile(session)
                    }

                    verifying = false
                    if (result.isSuccess) {
                        QQMusicSessionStore.write(context, candidate)
                        CookieManager.getInstance().flush()
                        onLoggedIn()
                        return@LaunchedEffect
                    }

                    verificationError = result.exceptionOrNull()?.message
                        ?: "QQ音乐登录状态验证失败，请稍后重试"
                }
            }

            delay(COOKIE_POLL_INTERVAL_MS)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            webView?.stopLoading()
            webView?.destroy()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "取消",
                modifier = Modifier
                    .clip(RoundedCornerShape(18.dp))
                    .meloXLiquidButton(
                        shape = RoundedCornerShape(18.dp),
                        tint = Color(0xFFFF3147),
                        surfaceColor = Color(0xFFFF3147).copy(alpha = 0.08f),
                        lensRadius = 7.dp,
                        refractionHeight = 11.dp,
                    )
                    .clickable(onClick = onDismiss)
                    .padding(8.dp),
                color = Color(0xFFFF3147),
                fontSize = 16.sp,
            )
            Text(
                text = "登录 QQ音乐",
                fontSize = 17.sp,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Text(
                text = "取消",
                modifier = Modifier.padding(8.dp),
                color = Color.Transparent,
                fontSize = 16.sp,
            )
        }

        if (pageLoading) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())

        Box(modifier = Modifier.weight(1f)) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { viewContext ->
                    WebView(viewContext).apply {
                        webView = this
                        setBackgroundColor(AndroidColor.TRANSPARENT)

                        val cookieManager = CookieManager.getInstance()
                        cookieManager.setAcceptCookie(true)
                        cookieManager.setAcceptThirdPartyCookies(this, true)

                        settings.javaScriptEnabled = true
                        settings.domStorageEnabled = true
                        settings.databaseEnabled = true
                        settings.useWideViewPort = true
                        settings.loadWithOverviewMode = true
                        settings.userAgentString =
                            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                                "AppleWebKit/537.36 (KHTML, like Gecko) " +
                                "Chrome/124.0.0.0 Safari/537.36"

                        webChromeClient = WebChromeClient()
                        webViewClient = object : WebViewClient() {
                            private var firstVisiblePageCommitted = false

                            override fun onPageCommitVisible(view: WebView?, url: String?) {
                                if (!firstVisiblePageCommitted) {
                                    firstVisiblePageCommitted = true
                                    pageLoading = false
                                }
                                super.onPageCommitVisible(view, url)
                            }

                            override fun onPageFinished(view: WebView?, url: String?) {
                                if (!firstVisiblePageCommitted) {
                                    firstVisiblePageCommitted = true
                                    pageLoading = false
                                }
                                super.onPageFinished(view, url)
                            }
                        }
                        loadUrl(QQ_MUSIC_LOGIN_URL)
                    }
                },
            )

            if (verifying) {
                Row(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 12.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.94f),
                            shape = RoundedCornerShape(18.dp),
                        )
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(9.dp),
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(18.dp), strokeWidth = 2.dp)
                    Text(
                        text = "正在验证 QQ音乐登录状态…",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 13.sp,
                    )
                }
            }

            verificationError?.let { message ->
                Text(
                    text = message,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                        .background(
                            color = MaterialTheme.colorScheme.errorContainer,
                            shape = MaterialTheme.shapes.medium,
                        )
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    fontSize = 13.sp,
                )
            }
        }
    }
}

private fun collectQQMusicCookieHeader(): String {
    val manager = CookieManager.getInstance()
    val values = linkedMapOf<String, String>()
    val urls = listOf(
        "https://y.qq.com/",
        "https://u.y.qq.com/",
        "https://c.y.qq.com/",
        "https://c6.y.qq.com/",
        "https://music.qq.com/",
        "https://qq.com/",
    )
    urls.forEach { url ->
        manager.getCookie(url)
            ?.split(';')
            ?.forEach { item ->
                val parts = item.trim().split('=', limit = 2)
                if (parts.size == 2 && parts[0].isNotBlank()) {
                    values[parts[0].trim()] = parts[1].trim()
                }
            }
    }
    return values.toSortedMap().entries.joinToString("; ") { (key, value) -> "$key=$value" }
}
