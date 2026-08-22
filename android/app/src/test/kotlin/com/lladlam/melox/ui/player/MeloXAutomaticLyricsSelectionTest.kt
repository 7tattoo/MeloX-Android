package com.lladlam.melox.ui.player

import com.lladlam.melox.core.lyrics.LyricLine
import com.lladlam.melox.core.lyrics.LyricSyllable
import com.lladlam.melox.core.lyrics.LyricsDocument
import com.lladlam.melox.core.lyrics.BoundLyricSource
import com.lladlam.melox.core.lyrics.LyricBinding
import com.lladlam.melox.core.music.model.MusicArtistRef
import com.lladlam.melox.core.music.model.MusicResourceId
import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.core.music.model.MusicTrack
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Test

class MeloXAutomaticLyricsSelectionTest {
    @Test
    fun qqPriorityWinsEvenWhenLowerSourcesHaveMoreAnnotations() {
        val qq = document("QQ", wordSynced = true)
        val netease = document("网易", wordSynced = true, translation = "translation")
        val current = document("当前", wordSynced = true, translation = "translation")

        assertEquals(
            qq,
            selectAutomaticLyrics(
                listOf(
                    AutoLyricCandidate(0, qq),
                    AutoLyricCandidate(1, netease),
                    AutoLyricCandidate(2, current),
                ),
            ),
        )
    }

    @Test
    fun emptyHigherPriorityFallsBackToNetease() {
        val netease = document("网易", wordSynced = true)
        val current = document("当前", wordSynced = false)

        assertEquals(
            netease,
            selectAutomaticLyrics(
                listOf(
                    AutoLyricCandidate(0, LyricsDocument(emptyList())),
                    AutoLyricCandidate(1, netease),
                    AutoLyricCandidate(2, current),
                ),
            ),
        )
    }

    @Test
    fun nonWordSyncedAmlLFallsBackToWordSyncedQq() {
        val selected = selectAutomaticLyrics(
            listOf(
                AutoLyricCandidate(0, document("AMLL 行级", wordSynced = false, translation = "translation")),
                AutoLyricCandidate(1, document("QQ 逐字", wordSynced = true)),
                AutoLyricCandidate(2, document("网易 逐字", wordSynced = true)),
            ),
        )
        assertEquals(document("QQ 逐字", wordSynced = true), selected)
    }

    @Test
    fun whenNoSourceHasWordTimingPriorityStillChoosesNonEmptyAmlL() {
        val selected = selectAutomaticLyrics(
            listOf(
                AutoLyricCandidate(0, document("AMLL 行级", wordSynced = false)),
                AutoLyricCandidate(1, document("QQ 行级", wordSynced = false)),
            ),
        )
        assertEquals(document("AMLL 行级", wordSynced = false), selected)
    }

    @Test
    fun originalDoesNotMatchDjLiveRemixOrInstrumental() {
        listOf(
            "提瓦特民谣 DJ版",
            "提瓦特民谣 (Live)",
            "提瓦特民谣 Remix",
            "提瓦特民谣 伴奏",
        ).forEach { title ->
            assertFalse(isSafeCrossProviderLyricMatch("提瓦特民谣", "宴宁", 240_000, track(title, "宴宁", 240_000)))
        }
    }

    @Test
    fun versionedTracksRequireSameVersionLabelAndStrictDuration() {
        assertTrue(isSafeCrossProviderLyricMatch("提瓦特民谣 DJ版", "宴宁", 240_000, track("提瓦特民谣 DJ版", "宴宁", 240_800)))
        assertFalse(isSafeCrossProviderLyricMatch("提瓦特民谣 DJ版", "宴宁", 240_000, track("提瓦特民谣 Remix", "宴宁", 240_000)))
        assertFalse(isSafeCrossProviderLyricMatch("提瓦特民谣 DJ版", "宴宁", 240_000, track("提瓦特民谣 DJ版", "宴宁", 242_000)))
    }

    @Test
    fun exactOriginalRequiresArtistAndTwoSecondDurationWindow() {
        assertTrue(isSafeCrossProviderLyricMatch("提瓦特民谣", "宴宁", 240_000, track("提瓦特民谣", "宴宁 / 陶典", 241_500)))
        assertFalse(isSafeCrossProviderLyricMatch("提瓦特民谣", "宴宁", 240_000, track("提瓦特民谣", "其他歌手", 240_000)))
        assertFalse(isSafeCrossProviderLyricMatch("提瓦特民谣", "宴宁", 240_000, track("提瓦特民谣", "宴宁", 243_000)))
    }

    @Test
    fun selectedCandidateRetainsExactBindingIdentity() {
        val binding = LyricBinding(
            source = BoundLyricSource.AmlL,
            resourceValue = "2750140001",
            title = "提瓦特民谣",
            artist = "宴宁",
            durationMs = 240_000,
        )
        val selected = selectAutomaticLyricCandidate(
            listOf(AutoLyricCandidate(0, document("AMLL", true), binding)),
        )
        assertEquals(binding, selected?.binding)
    }

    private fun document(text: String, wordSynced: Boolean, translation: String? = null): LyricsDocument =
        LyricsDocument(
            listOf(
                LyricLine(
                    timeMs = 0,
                    durationMs = 1_000,
                    text = text,
                    syllables = if (wordSynced) listOf(LyricSyllable(text, 0, 1_000)) else emptyList(),
                    translation = translation,
                ),
            ),
        )

    private fun track(title: String, artist: String, durationMs: Long) = MusicTrack(
        id = MusicResourceId(MusicSource.QQMusic, title),
        title = title,
        artists = listOf(MusicArtistRef(name = artist)),
        durationMs = durationMs,
    )
}
