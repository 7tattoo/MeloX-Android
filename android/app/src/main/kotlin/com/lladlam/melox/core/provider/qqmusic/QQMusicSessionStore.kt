package com.lladlam.melox.core.provider.qqmusic

import android.content.Context

data class QQMusicSession(
    val cookie: String,
    val uin: String,
    val musicKey: String,
) {
    val isLoggedIn: Boolean
        get() = uin.isNotBlank() && musicKey.isNotBlank()
}

/** QQ Music login state is stored only in the app's local preferences. */
object QQMusicSessionStore {
    private const val PreferencesName = "melox_qq_music_session"
    private const val KeyCookie = "cookie"

    fun read(context: Context): QQMusicSession =
        parse(
            context.applicationContext
                .getSharedPreferences(PreferencesName, Context.MODE_PRIVATE)
                .getString(KeyCookie, "")
                .orEmpty(),
        )

    fun write(context: Context, cookie: String): QQMusicSession {
        val session = parse(cookie)
        require(session.isLoggedIn) { "QQ音乐登录态不完整" }
        context.applicationContext
            .getSharedPreferences(PreferencesName, Context.MODE_PRIVATE)
            .edit()
            .putString(KeyCookie, cookie.trim())
            .apply()
        return session
    }

    fun clear(context: Context) {
        context.applicationContext
            .getSharedPreferences(PreferencesName, Context.MODE_PRIVATE)
            .edit()
            .clear()
            .apply()
    }

    fun parse(cookie: String): QQMusicSession {
        val values = cookie
            .split(';')
            .map(String::trim)
            .mapNotNull { entry ->
                val separator = entry.indexOf('=')
                if (separator <= 0) null
                else entry.substring(0, separator).trim() to entry.substring(separator + 1).trim()
            }
            .filter { (key, _) -> key.isNotBlank() }
            .toMap()

        val rawUin = values["qqmusic_uin"]
            .orEmpty()
            .ifBlank {
                if (values["login_type"] == "2") values["wxuin"].orEmpty()
                else values["uin"].orEmpty()
            }
            .ifBlank { values["wxuin"].orEmpty() }
        val uin = rawUin.filter(Char::isDigit)
        val musicKey = values["qm_keyst"]
            .orEmpty()
            .ifBlank { values["qqmusic_key"].orEmpty() }
        return QQMusicSession(
            cookie = cookie.trim(),
            uin = uin,
            musicKey = musicKey,
        )
    }
}
