package com.lladlam.melox.core.provider.bilibili

import com.lladlam.melox.core.lyrics.LyricLine
import com.lladlam.melox.core.lyrics.LyricSyllable
import com.lladlam.melox.core.lyrics.LyricsDocument
import com.lladlam.melox.core.music.model.MusicArtistRef
import com.lladlam.melox.core.music.model.MusicResourceId
import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.core.music.model.MusicTrack
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class BilibiliWbiSignerTest {
    @Test
    fun mixinKeyUsesCanonicalPermutation() {
        val img = "7cd084941338484aae1ad9425b84077c"
        val sub = "4932caff0ff746eab6f01bf08b70ac45"
        assertEquals(
            "ea1db124af3c7062474693fa704f4ff8",
            BilibiliWbiSigner.mixinKey("https://i0.hdslb.com/bfs/wbi/$img.png", "https://i0.hdslb.com/bfs/wbi/$sub.png"),
        )
    }

    @Test
    fun signingMatchesSortedUrlEncodingAndFiltersForbiddenCharacters() {
        val mixin = "ea1db124af3c7062474693fa704f4ff8"
        val signed = BilibiliWbiSigner.sign(mapOf("keyword" to "周杰伦 !'()* 测试", "page" to "1"), mixin, 1_700_000_000)
        val expectedUnsigned = sortedMapOf(
            "keyword" to "周杰伦  测试", "page" to "1", "web_location" to "1550101", "wts" to "1700000000",
        )
        val query = expectedUnsigned.entries.joinToString("&") { (key, value) ->
            "${encode(key)}=${encode(value)}"
        }
        assertEquals(md5(query + mixin), signed["w_rid"])
    }

    @Test
    fun titleDurationAndIdentityParsingAreStable() {
        assertEquals("歌名", BilibiliProvider.cleanTitle("<em class=\"keyword\">歌</em>名"))
        assertEquals(65_000L, BilibiliProvider.parseDuration("1:05"))
        assertEquals(3_723_000L, BilibiliProvider.parseDuration("1:02:03"))
        assertEquals("BV1xx" to 123L, BilibiliProvider.parseIdentity("BV1xx:123"))
    }

    @Test
    fun sessionRequiresCoreCookiesAndKeepsBuvids() {
        val cookie = "buvid4=four; SESSDATA=session; bili_jct=csrf; DedeUserID=42; buvid3=three"
        val session = BilibiliSessionStore.parse(cookie)
        assertTrue(session.isLoggedIn)
        assertEquals("three", session.buvid3)
        assertEquals("four", session.buvid4)
        assertTrue(BilibiliSessionStore.hasRequiredCookies(cookie))
    }

    @Test
    fun lyricOffsetClampsHashesFullIdentityAndCombinesGlobalAdvance() {
        assertEquals(-5_000, BilibiliLyricOffsetStore.normalizeOffset(-9_000))
        assertEquals(5_000, BilibiliLyricOffsetStore.normalizeOffset(9_000))
        assertEquals(0, BilibiliLyricOffsetStore.normalizeOffset(0))
        assertEquals(
            BilibiliLyricOffsetStore.preferenceKey("BV1abc:100"),
            BilibiliLyricOffsetStore.preferenceKey("BV1abc:100"),
        )
        assertFalse(
            BilibiliLyricOffsetStore.preferenceKey("BV1abc:100") ==
                BilibiliLyricOffsetStore.preferenceKey("BV1abc:101"),
        )
        assertFalse(BilibiliLyricOffsetStore.preferenceKey("BV1abc:100").contains("BV1abc"))
        assertEquals(1_300L, BilibiliLyricOffsetStore.effectiveAdvance(800, 500))
        assertEquals(-200L, BilibiliLyricOffsetStore.effectiveAdvance(800, -1_000))
    }

    @Test
    fun apiCacheKeysTtlSearchAndSessionScopesAreStable() {
        assertEquals("hello world", BilibiliApiCache.normalizeSearchQuery("  Hello   WORLD "))
        assertEquals(
            "user:42:r3|search|page=1&q=test",
            BilibiliApiCache.cacheKey("user:42:r3", "search", mapOf("q" to "test", "page" to "1")),
        )
        assertTrue(BilibiliApiCache.isFresh(1_000, 5_000, 5_999))
        assertFalse(BilibiliApiCache.isFresh(1_000, 5_000, 6_000))
        assertFalse(BilibiliApiCache.isFresh(2_000, 5_000, 1_999))
        assertEquals("user:42:r7", BilibiliSessionStore.scope("42", 7))
        assertFalse(BilibiliSessionStore.scope("42", 7) == BilibiliSessionStore.scope("42", 8))
    }

    @Test
    fun playbackExpiryUsesDeadlineMinusSafetyWindowOrShortDefault() {
        val now = 1_700_000_000_000L
        assertEquals(
            1_700_000_090_000L,
            BilibiliApiCache.playbackExpiry("https://cdn.test/audio?deadline=1700000120", now),
        )
        assertEquals(now + 90_000L, BilibiliApiCache.playbackExpiry("https://cdn.test/audio", now))
        assertEquals(now + 90_000L, BilibiliApiCache.playbackExpiry("https://cdn.test/audio?deadline=1", now))
    }

    @Test
    fun titleExtractionRemovesVideoPackagingAndKeepsSongIdentity() {
        val candidates = BilibiliLyricAlignment.extractTitleCandidates(
            "【官方MV 4K】周杰伦 - 晴天（动态歌词）",
            "音乐频道",
        )
        assertTrue(candidates.any { it.title == "晴天" && it.artist == "周杰伦" })
        assertTrue(
            BilibiliLyricAlignment.extractTitleCandidates("《夜曲》 Live 官方版", "UP主")
                .any { it.title.contains("夜曲") && it.title.contains("Live", ignoreCase = true) },
        )
        assertTrue(
            BilibiliLyricAlignment.extractTitleCandidates(
                "原声带 - NCOP(《アイデン貞貞メルトダウン》完整版)",
                "视频UP主",
            ).first() == BilibiliTitleCandidate("アイデン貞貞メルトダウン"),
        )
        assertTrue(
            BilibiliLyricAlignment.extractTitleCandidates(
                "熏香花朵凛然绽放 OP「まなざしは光」/ 木谷龙也",
                "视频UP主",
            ).first() == BilibiliTitleCandidate("まなざしは光"),
        )
        assertTrue(
            BilibiliLyricAlignment.extractTitleCandidates("刹那芳华", "兰音Reine")
                .first() == BilibiliTitleCandidate("刹那芳华"),
        )
    }

    @Test
    fun lyricDurationUsesAuthoredEndsAndRejectsLowConfidenceConsensus() {
        val precise = preciseLyrics(198_000L)
        val duration = BilibiliLyricAlignment.effectiveDuration(precise)
        assertEquals(198_000L, duration?.durationMs)
        assertEquals(LyricDurationConfidence.High, duration?.confidence)

        val low = LyricsDocument(listOf(LyricLine(195_000L, text = "末句")))
        assertEquals(LyricDurationConfidence.Low, BilibiliLyricAlignment.effectiveDuration(low)?.confidence)
        assertNull(
            BilibiliLyricAlignment.consensus(
                listOf(
                    sourceResult("amll", precise),
                    sourceResult("qq", preciseLyrics(199_000L)),
                    sourceResult("netease", low),
                ),
            ),
        )
    }

    @Test
    fun consensusRequiresAllThreeHighConfidenceSources() {
        val consensus = BilibiliLyricAlignment.consensus(
            listOf(
                sourceResult("amll", preciseLyrics(198_000L)),
                sourceResult("qq", preciseLyrics(199_500L)),
                sourceResult("netease", preciseLyrics(197_500L)),
            ),
        )
        assertEquals(198_000L, consensus)
        assertNull(
            BilibiliLyricAlignment.consensus(
                listOf(sourceResult("amll", preciseLyrics(198_000L)), sourceResult("qq", preciseLyrics(198_000L))),
            ),
        )
    }

    @Test
    fun audioMismatchAllowsOutroButDetectsWrongMedia() {
        assertFalse(BilibiliLyricAlignment.audioClearlyMismatches(205_000L, 198_000L))
        assertTrue(BilibiliLyricAlignment.audioClearlyMismatches(260_000L, 198_000L))
        assertTrue(BilibiliLyricAlignment.audioClearlyMismatches(180_000L, 198_000L))
    }

    @Test
    fun replacementSelectionRequiresSafeTitleDurationAndScore() {
        val good = track("BVgood:2", "晴天", "周杰伦", 198_500L)
        val current = track("BVcurrent:1", "晴天", "周杰伦", 198_000L)
        val compilation = track("BVmix:3", "晴天 歌曲合集串烧", "周杰伦", 198_000L)
        val wrongDuration = track("BVlong:4", "晴天", "周杰伦", 260_000L)
        assertEquals(
            good,
            BilibiliLyricAlignment.selectReplacement(
                listOf(current, compilation, wrongDuration, good),
                "BVcurrent:1",
                "晴天",
                "周杰伦",
                198_000L,
            )?.track,
        )
        assertNull(
            BilibiliLyricAlignment.selectReplacement(
                listOf(compilation, wrongDuration),
                "BVcurrent:1",
                "晴天",
                "周杰伦",
                198_000L,
            ),
        )
    }

    @Test
    fun significantTitlePunctuationPreventsMottoFalsePositive() {
        val requested = BilibiliTitleCandidate("MOTTO!!!")
        assertFalse(BilibiliLyricAlignment.isSafeCatalogMatch(
            track("BVplain:1", "Motto", "Other", 188_000L),
            requested,
        ))
        assertTrue(BilibiliLyricAlignment.isSafeCatalogMatch(
            track("BVexact:2", "MOTTO!!!", "MORE MORE JUMP!", 188_000L),
            requested,
        ))
    }

    private fun preciseLyrics(endMs: Long) = LyricsDocument(
        listOf(
            LyricLine(
                timeMs = endMs - 2_000L,
                durationMs = 2_000L,
                text = "末句",
                syllables = listOf(LyricSyllable("末句", endMs - 2_000L, endMs)),
            ),
        ),
    )

    private fun sourceResult(source: String, document: LyricsDocument) = BilibiliLyricSourceResult(
        source,
        document,
        track("BVtest:1", "晴天", "周杰伦", 198_000L),
    )

    private fun track(id: String, title: String, artist: String, durationMs: Long) = MusicTrack(
        id = MusicResourceId(MusicSource.Bilibili, id),
        title = title,
        artists = listOf(MusicArtistRef(name = artist)),
        durationMs = durationMs,
    )

    private fun encode(value: String) = URLEncoder.encode(value, StandardCharsets.UTF_8.name())
    private fun md5(value: String) = MessageDigest.getInstance("MD5").digest(value.toByteArray())
        .joinToString("") { "%02x".format(it) }
}
