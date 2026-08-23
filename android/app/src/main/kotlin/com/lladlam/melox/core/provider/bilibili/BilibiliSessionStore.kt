package com.lladlam.melox.core.provider.bilibili

import android.content.Context
import android.webkit.CookieManager

data class BilibiliSession(
    val cookie: String,
    val sessData: String,
    val biliJct: String,
    val userId: String,
    val buvid3: String,
    val buvid4: String,
) {
    val isLoggedIn: Boolean get() = sessData.isNotBlank() && biliJct.isNotBlank() && userId.isNotBlank()
}

object BilibiliSessionStore {
    private const val PreferencesName = "melox_bilibili_session"
    private const val KeyCookie = "cookie"
    private const val KeyRevision = "revision"
    private val RequiredNames = setOf("SESSDATA", "bili_jct", "DedeUserID")

    fun read(context: Context): BilibiliSession = parse(
        context.applicationContext.getSharedPreferences(PreferencesName, Context.MODE_PRIVATE)
            .getString(KeyCookie, "").orEmpty(),
    )

    fun write(context: Context, cookie: String): BilibiliSession {
        val session = parse(cookie)
        require(session.isLoggedIn) { "Bilibili 登录态不完整" }
        val preferences = context.applicationContext.getSharedPreferences(PreferencesName, Context.MODE_PRIVATE)
        if (preferences.getString(KeyCookie, "").orEmpty() != session.cookie) {
            preferences.edit().putString(KeyCookie, session.cookie)
                .putLong(KeyRevision, preferences.getLong(KeyRevision, 0L) + 1L).apply()
        }
        return session
    }

    fun parse(cookie: String): BilibiliSession {
        val values = cookie.split(';').map(String::trim).mapNotNull { entry ->
            val separator = entry.indexOf('=')
            if (separator <= 0) null else entry.substring(0, separator) to entry.substring(separator + 1)
        }.toMap()
        val normalized = values.toSortedMap().entries.joinToString("; ") { (key, value) -> "$key=$value" }
        return BilibiliSession(
            cookie = normalized,
            sessData = values["SESSDATA"].orEmpty(),
            biliJct = values["bili_jct"].orEmpty(),
            userId = values["DedeUserID"].orEmpty(),
            buvid3 = values["buvid3"].orEmpty(),
            buvid4 = values["buvid4"].orEmpty(),
        )
    }

    fun clear(context: Context, clearWebCookies: Boolean) {
        val names = read(context).cookie.split(';').mapNotNull { it.substringBefore('=').trim().takeIf(String::isNotBlank) }
        val preferences = context.applicationContext.getSharedPreferences(PreferencesName, Context.MODE_PRIVATE)
        if (preferences.getString(KeyCookie, "").orEmpty().isNotBlank()) {
            preferences.edit().remove(KeyCookie)
                .putLong(KeyRevision, preferences.getLong(KeyRevision, 0L) + 1L).apply()
        }
        if (clearWebCookies && names.isNotEmpty()) runCatching {
            CookieManager.getInstance().let { manager ->
                listOf("https://www.bilibili.com/", "https://passport.bilibili.com/", "https://bilibili.com/").forEach { domain ->
                    names.forEach { manager.setCookie(domain, "$it=; Max-Age=0; Path=/") }
                }
                manager.flush()
            }
        }
    }

    fun hasRequiredCookies(cookie: String): Boolean {
        val names = cookie.split(';').map { it.substringBefore('=').trim() }.toSet()
        return RequiredNames.all(names::contains)
    }

    fun revision(context: Context): Long = context.applicationContext
        .getSharedPreferences(PreferencesName, Context.MODE_PRIVATE).getLong(KeyRevision, 0L)

    fun scope(userId: String, revision: Long): String = "user:${userId.ifBlank { "anonymous" }}:r$revision"
}
