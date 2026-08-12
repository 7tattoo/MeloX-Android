package com.lladlam.melox.core.provider.qqmusic

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class QQMusicSessionStoreTest {
    @Test
    fun parsesQqCookieAndMusicKey() {
        val session = QQMusicSessionStore.parse(
            "uin=o123456; qqmusic_key=secret; other=value",
        )
        assertEquals("123456", session.uin)
        assertEquals("secret", session.musicKey)
        assertTrue(session.isLoggedIn)
    }

    @Test
    fun usesWxUinForWechatLogin() {
        val session = QQMusicSessionStore.parse(
            "login_type=2; wxuin=987654321; qm_keyst=wechat-key",
        )
        assertEquals("987654321", session.uin)
        assertEquals("wechat-key", session.musicKey)
        assertTrue(session.isLoggedIn)
    }
}
