package com.lladlam.melox.logic.car

import android.os.Bundle
import android.os.SystemClock
import androidx.media3.session.MediaSession
import com.lladlam.melox.core.lyrics.LyricsDocument

/**
 * vivo 车联歌词状态管理器（MeloX 适配版）。
 *
 * 协议要点（vivo 车机滚动歌词适配开发文档）：
 *   1. 整段滚动：车机按 PlaybackState 自行滚动，应用只提供完整 LRC；
 *      LYRICS_LINE 与 music.media.extras.* 均为单行信号，禁止写入。
 *   2. 没有完整 LRC 时不写任何歌词字段，不得推空歌词或 1/2/3 负状态。
 *   3. 原子随身听：lrc_change 事件携带完整 LRC；官方拼写 meida/meidia_id 必须保留。
 *   4. 无事件时不得发送空 Bundle（会清掉车机已显示的歌词）。
 */
class CarLyricsManager(
    private val mediaSession: MediaSession,
) {
    var enabled: Boolean = true

    /** 当前播放歌曲 ID（写入 vivomusicmix.extra.key.meidia_id） */
    var currentMediaId: String? = null

    /** 当前完整 LRC（未就绪为 null） */
    val currentWholeText: String? get() = wholeLrc

    private var wholeLrc: String? = null
    private var lastDocRef: LyricsDocument? = null

    /** 上次原子事件发送时间（elapsedRealtime）；0 = 尚未发送，切歌时重置 */
    private var lastAtomEventRealtimeMs: Long = 0L

    /** 歌词加载完成：缓存完整 LRC（无歌词则清空，不触碰车机） */
    fun updateLyric(lyrics: LyricsDocument?) {
        if (lyrics === lastDocRef) return
        lastDocRef = lyrics
        wholeLrc = lyrics?.takeIf { it.lines.isNotEmpty() }
            ?.toLrcString()?.takeIf { it.isNotBlank() }
    }

    /** 切歌重置：清状态 + 重置原子事件计时（歌词就绪后下一拍立即发送新事件） */
    fun reset() {
        wholeLrc = null
        lastDocRef = null
        lastAtomEventRealtimeMs = 0L
    }

    /**
     * 原子 lrc_change 事件：歌词就绪后首次调用立即发送，之后约 25 秒兜底重发
     * （覆盖车机/原子组件晚于播放开始才连接的情况）。
     * 返回本次事件 Bundle（已同步推给 Media3 session），无事件时返回 null——
     * 调用方对 null 不得发送空 extras。
     */
    fun atomEventIfDue(): Bundle? {
        if (!enabled) return null
        val lrc = wholeLrc ?: return null
        val now = SystemClock.elapsedRealtime()
        if (lastAtomEventRealtimeMs != 0L &&
            now - lastAtomEventRealtimeMs < ATOM_EVENT_RESEND_INTERVAL_MS
        ) return null
        lastAtomEventRealtimeMs = now
        val event = Bundle().apply {
            putString(CarLyricsConstants.ATOM_ACTION_KEY, CarLyricsConstants.ATOM_ACTION_LRC_CHANGE)
            putString(CarLyricsConstants.ATOM_LYRIC_KEY, lrc)
            currentMediaId?.let { putString(CarLyricsConstants.ATOM_MEDIA_ID_KEY, it) }
        }
        mediaSession.setSessionExtras(event)
        return event
    }

    /**
     * 车载 metadata extras：support_event=31 常驻（原子随身听支持位）；
     * 有完整 LRC 时才追加 LYRICS_WHOLE + 状态 0。禁止写入 LYRICS_LINE。
     */
    fun buildMetadataExtras(): Bundle = Bundle().apply {
        putLong(CarLyricsConstants.METADATA_KEY_SUPPORT_EVENT, CarLyricsConstants.SUPPORT_EVENT_DEFAULT)
        val lrc = wholeLrc
        if (!lrc.isNullOrBlank()) {
            putString(CarLyricsConstants.METADATA_KEY_LYRICS_WHOLE, lrc)
            putLong(CarLyricsConstants.METADATA_KEY_LYRICS_STATUS, CarLyricsConstants.LYRICS_STATUS_SUCCESS)
        }
    }

    /** LyricsDocument 转标准 LRC 字符串（仅原歌词行，不含翻译行） */
    private fun LyricsDocument.toLrcString(): String {
        val sb = StringBuilder()
        for (line in lines) {
            val time = line.timeMs
            val text = line.text.trim()
            if (text.isBlank()) continue
            val min = (time / 60000).toInt()
            val sec = (time % 60000 / 1000).toInt()
            val ms = (time % 1000).toInt() / 10
            // 只写原歌词行：翻译行若作为独立 LRC 行（时间戳与原行相同），
            // 车机端滚动匹配时会高亮翻译行而非原行（外语歌曲 bug）。
            sb.appendLine(String.format("[%02d:%02d.%02d]%s", min, sec, ms, text))
        }
        return sb.toString()
    }

    private companion object {
        /** 原子事件兜底重发间隔（文档：约 25 秒） */
        const val ATOM_EVENT_RESEND_INTERVAL_MS = 25_000L
    }
}