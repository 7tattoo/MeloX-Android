package com.lladlam.melox.core.provider.qqmusic

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Ignore
import org.junit.Test

@Ignore("Live QQ Music integration test; run explicitly when validating the upstream service")
class QQMusicRichLyricsIntegrationTest {
    @Test
    fun fetchesTeyvatBalladFromLiveQqMusic() = runBlocking {
        val session = QQMusicSession("", "", "")
        val api = QQMusicApiClient(sessionProvider = { session })
        val track = api.searchSongs("提瓦特民谣", page = 1, pageSize = 20).items
            .firstOrNull { it.title.contains("提瓦特民谣") }
            ?: error("QQ音乐搜索未返回提瓦特民谣")
        val document = QQMusicRichLyricsClient(sessionProvider = { session }).lyrics(track)

        println("TRACK=${track.title} / ${track.artistText}")
        println("LINES=${document.lines.size}, WORD_SYNC=${document.lines.count { it.syllables.isNotEmpty() }}")
        println("TRANSLATED=${document.lines.count { !it.translation.isNullOrBlank() }}")
        println("ROMANIZED=${document.lines.count { !it.romanization.isNullOrBlank() }}")
        println("PREVIEW=${document.lines.take(5).joinToString(" | ") { it.text }}")
        assertTrue(document.lines.isNotEmpty())
        assertTrue(document.lines.any { it.syllables.isNotEmpty() })
    }
}
