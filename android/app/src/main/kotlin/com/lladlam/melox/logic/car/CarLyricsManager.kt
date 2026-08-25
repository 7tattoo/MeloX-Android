package com.lladlam.melox.logic.car

import android.os.Bundle
import androidx.media3.session.MediaSession
import com.lladlam.melox.core.lyrics.LyricsDocument
import com.lladlam.melox.logic.car.CarLyricsConstants.EXTRAS_KEY_LYRIC
import com.lladlam.melox.logic.car.CarLyricsConstants.EXTRAS_KEY_LYRIC_ALLOWED
import com.lladlam.melox.logic.car.CarLyricsConstants.EXTRAS_KEY_NOTICE_CAR
import com.lladlam.melox.logic.car.CarLyricsConstants.LYRICS_STATUS_FAIL
import com.lladlam.melox.logic.car.CarLyricsConstants.LYRICS_STATUS_LOADING
import com.lladlam.melox.logic.car.CarLyricsConstants.LYRICS_STATUS_NO_LYRICS
import com.lladlam.melox.logic.car.CarLyricsConstants.LYRICS_STATUS_SUCCESS
import com.lladlam.melox.logic.car.CarLyricsConstants.METADATA_KEY_LYRICS_LINE
import com.lladlam.melox.logic.car.CarLyricsConstants.METADATA_KEY_LYRICS_STATUS
import com.lladlam.melox.logic.car.CarLyricsConstants.METADATA_KEY_LYRICS_WHOLE

/**
 * vivo 车联歌词状态管理器（MeloX 适配版）。
 *
 * 职责：
 *   1. 维护当前歌词状态
 *   2. 构建双通道数据（Channel A / Channel B）
 *   3. 内置变化检测
 *   4. lyricInfo 镜像键
 */
class CarLyricsManager(
    private val mediaSession: MediaSession,
) {
    var enabled: Boolean = true

    /** 当前播放歌曲 ID（写入 vivomusicmix.extra.key.meidia_id） */
    var currentMediaId: String? = null

    /** 当前状态（供系统 session 同步读取） */
    val currentStatus: Long get() = status
    val currentLineText: String? get() = currentLine
    val currentWholeText: String? get() = wholeLrc

    private var currentLine: String? = null
    private var wholeLrc: String? = null
    private var status: Long = LYRICS_STATUS_NO_LYRICS

    private var lastPushedLine: String? = null
    private var lastPushedWhole: String? = null
    private var lastPushedStatus: Long = LYRICS_STATUS_NO_LYRICS

    /** 进入加载中状态 */
    fun setLoading(): Boolean {
        currentLine = null
        wholeLrc = null
        status = LYRICS_STATUS_LOADING
        return push()
    }

    /** 设置加载失败 */
    fun setFail(): Boolean {
        currentLine = null
        wholeLrc = null
        status = LYRICS_STATUS_FAIL
        return push()
    }

    /**
     * 更新歌词状态（歌词加载完成后调用）。
     * @param currentLine 当前歌词行文本
     * @param lyrics 完整歌词文档（可为 null）
     */
    fun updateLyric(currentLine: String?, lyrics: LyricsDocument?): Boolean {
        this.currentLine = currentLine?.takeIf { it.isNotBlank() }
        wholeLrc = lyrics?.toLrcString()
        status = if (!wholeLrc.isNullOrBlank()) LYRICS_STATUS_SUCCESS else LYRICS_STATUS_NO_LYRICS
        return push()
    }

    /** 统一推送入口，含变化检测与 Channel B 推送 */
    fun push(): Boolean {
        val lineToPush = if (enabled) currentLine else null
        val wholeToPush = if (enabled) {
            when (status) {
                LYRICS_STATUS_SUCCESS -> wholeLrc ?: "-1"
                LYRICS_STATUS_LOADING -> ""
                else -> "-1"
            }
        } else null
        val statusToPush = if (enabled) status else LYRICS_STATUS_NO_LYRICS

        val changed = lineToPush != lastPushedLine
            || wholeToPush != lastPushedWhole
            || statusToPush != lastPushedStatus

        lastPushedLine = lineToPush
        lastPushedWhole = wholeToPush
        lastPushedStatus = statusToPush

        // Channel B: Extras
        if (!enabled) {
            mediaSession.setSessionExtras(Bundle())
            return changed
        }
        val extras = Bundle().apply {
            putBoolean(EXTRAS_KEY_LYRIC_ALLOWED, true)
            if (!lineToPush.isNullOrEmpty()) putString(EXTRAS_KEY_LYRIC, lineToPush)
            putBoolean(EXTRAS_KEY_NOTICE_CAR, true)
            // vivomusicmix 歌词协议（vivo 车联手机端 App 读取的键）：
            // 手机端 onExtrasChanged 校验 action==lrc_change 后，
            // 读取 meidia_id + lyric 并推送完整歌词到车机。
            putString("vivomusicmix.meida.extra.key.action", "vivomusicmix.extra.lrc_change")
            if (!wholeToPush.isNullOrEmpty() && wholeToPush != "-1") {
                putString("vivomusicmix.extra.key.lyric", wholeToPush)
            }
            currentMediaId?.let { putString("vivomusicmix.extra.key.meidia_id", it) }
        }
        mediaSession.setSessionExtras(extras)
        return changed
    }

    /** 构建 Channel A 元数据 extras（含 lyricInfo 镜像） */
    fun buildMetadataExtras(): Bundle {
        val extras = Bundle()
        if (!enabled) return extras

        val lineToPush = currentLine
        if (!lineToPush.isNullOrEmpty()) {
            extras.putString(METADATA_KEY_LYRICS_LINE, lineToPush)
        }
        when (status) {
            LYRICS_STATUS_SUCCESS -> {
                extras.putString(METADATA_KEY_LYRICS_WHOLE, wholeLrc ?: "-1")
                extras.putLong(METADATA_KEY_LYRICS_STATUS, status)
            }
            LYRICS_STATUS_LOADING -> {
                extras.putString(METADATA_KEY_LYRICS_WHOLE, "")
                extras.putLong(METADATA_KEY_LYRICS_STATUS, status)
            }
            else -> {
                extras.putString(METADATA_KEY_LYRICS_WHOLE, "-1")
                extras.putLong(METADATA_KEY_LYRICS_STATUS, status)
            }
        }
        // lyricInfo 镜像：status|wholeLrcHash|lineHash
        extras.putString(
            "lyricInfo",
            "$status|${wholeLrc?.hashCode()}|${lineToPush?.hashCode()}"
        )
        return extras
    }

    /** LyricsDocument 转标准 LRC 字符串 */
    private fun LyricsDocument.toLrcString(): String {
        val sb = StringBuilder()
        for (line in lines) {
            val time = line.timeMs
            val text = line.text.trim()
            if (text.isBlank()) continue
            val min = (time / 60000).toInt()
            val sec = (time % 60000 / 1000).toInt()
            val ms = (time % 1000).toInt() / 10
            sb.appendLine(String.format("[%02d:%02d.%02d]%s", min, sec, ms, text))
            line.translation?.takeIf { it.isNotBlank() }?.let { trans ->
                sb.appendLine(String.format("[%02d:%02d.%02d]%s", min, sec, ms, trans))
            }
        }
        return sb.toString()
    }
}