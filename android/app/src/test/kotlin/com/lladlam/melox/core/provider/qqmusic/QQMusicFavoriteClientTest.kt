package com.lladlam.melox.core.provider.qqmusic

import org.junit.Assert.assertEquals
import org.junit.Test

class QQMusicFavoriteClientTest {
    @Test
    fun favoriteUsesFixedLikedDirectory201() {
        assertEquals(201, QQ_LIKED_DIRECTORY_ID)
    }

    @Test
    fun favoriteAndUnfavoriteUseCurrentWriteMethods() {
        assertEquals("AddSonglist", qqFavoriteWriteMethod(true))
        assertEquals("DelSonglist", qqFavoriteWriteMethod(false))
    }
}
