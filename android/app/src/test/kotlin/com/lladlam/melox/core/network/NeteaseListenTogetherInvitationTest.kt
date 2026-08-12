package com.lladlam.melox.core.network

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class NeteaseListenTogetherInvitationTest {
    @Test
    fun parsesCurrentMultishareInviteWithInviterUid() {
        val invite = parseNeteaseListenTogetherInvitation(
            "https://st.music.163.com/listen-together/multishare/index.html?roomId=6083bea62e687673f3d050bc6265caa0_1785467085082&inviterUid=8162097994&isFLT=false",
        )
        assertNotNull(invite)
        assertEquals("6083bea62e687673f3d050bc6265caa0_1785467085082", invite?.roomId)
        assertEquals("8162097994", invite?.inviterId)
    }

    @Test
    fun parsesLegacyShareInviteWithInviterId() {
        val invite = parseNeteaseListenTogetherInvitation(
            "https://st.music.163.com/listen-together/share/?songId=123&roomId=room_456&inviterId=789",
        )
        assertEquals("room_456", invite?.roomId)
        assertEquals("789", invite?.inviterId)
        assertEquals(123L, invite?.songId)
    }

    @Test
    fun parsesHtmlEscapedInviteInsideClipboardText() {
        val invite = parseNeteaseListenTogetherInvitation(
            "一起听 https://st.music.163.com/listen-together/multishare/index.html?roomId=abc_1&amp;inviterUid=42&amp;isFLT=false 快来",
        )
        assertEquals("abc_1", invite?.roomId)
        assertEquals("42", invite?.inviterId)
    }
}
