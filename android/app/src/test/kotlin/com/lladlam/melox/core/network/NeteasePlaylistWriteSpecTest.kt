package com.lladlam.melox.core.network

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class NeteasePlaylistWriteSpecTest {
    @Test
    fun createSpecNormalizesNameAndPrivacy() {
        assertEquals(NeteasePlaylistCreateSpec("旅行", 0), neteasePlaylistCreateSpec("  旅行  ", false))
        assertEquals(NeteasePlaylistCreateSpec("私人", 10), neteasePlaylistCreateSpec("私人", true))
        assertThrows(IllegalArgumentException::class.java) { neteasePlaylistCreateSpec("   ", false) }
    }

    @Test
    fun mutationSpecAllowsOnlyValidAddOrDelete() {
        assertEquals(NeteasePlaylistMutationSpec(12L, 34L, "add"), neteasePlaylistMutationSpec(12L, 34L, "add"))
        assertEquals(NeteasePlaylistMutationSpec(12L, 34L, "del"), neteasePlaylistMutationSpec(12L, 34L, "del"))
        assertThrows(IllegalArgumentException::class.java) { neteasePlaylistMutationSpec(0L, 34L, "add") }
        assertThrows(IllegalArgumentException::class.java) { neteasePlaylistMutationSpec(12L, 34L, "replace") }
    }
}
