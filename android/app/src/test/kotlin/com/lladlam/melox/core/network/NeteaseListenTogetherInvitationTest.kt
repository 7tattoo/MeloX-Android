package com.lladlam.melox.core.network
import org.junit.Assert.*
import org.junit.Test
class NeteaseListenTogetherInvitationTest {
 @Test fun parsesInviterUidVariant() { val p = parseNeteaseListenTogetherInvitation("https://st.music.163.com/listen-together/share/?roomId=123456&inviterUid=998877"); assertNotNull(p); assertEquals("123456", p?.roomId); assertEquals("998877", p?.inviterId) }
 @Test fun parsesInviterIdVariant() { val p = parseNeteaseListenTogetherInvitation("https://st.music.163.com/listen-together/share/?roomId=abc&inviterId=42"); assertNotNull(p); assertEquals("abc", p?.roomId); assertEquals("42", p?.inviterId) }
 @Test fun rejectsIncompleteInvitation() { assertNull(parseNeteaseListenTogetherInvitation("https://music.163.com/?roomId=123")) }
}
