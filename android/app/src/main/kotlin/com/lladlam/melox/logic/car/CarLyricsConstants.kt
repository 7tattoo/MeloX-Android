package com.lladlam.melox.logic.car

/**
 * vivo 车联投屏歌词协议常量。
 * 定义双通道键名、状态枚举与功能开关键。
 * 参考：https://github.com/lladlam/MeloX-Android
 */
object CarLyricsConstants {

    // ---- Channel A: Metadata（车载 Launcher 直接读取）----
    const val METADATA_KEY_LYRICS_LINE   = "ucar.media.metadata.LYRICS_LINE"
    const val METADATA_KEY_LYRICS_WHOLE  = "ucar.media.metadata.LYRICS_WHOLE"
    const val METADATA_KEY_LYRICS_STATUS = "ucar.media.metadata.LYRICS_STATUS"

    // ---- Channel B: Extras（手机端智慧车联 App 转发）----
    const val EXTRAS_KEY_LYRIC        = "music.media.extras.LYRIC"
    const val EXTRAS_KEY_LYRIC_ALLOWED = "music.media.extras.LYRIC_IS_ALLOWED"
    const val EXTRAS_KEY_NOTICE_CAR   = "music.media.extras.NOTICE_CAR"

    // ---- 歌词状态枚举（MediaConstants$LyricsState）----
    const val LYRICS_STATUS_SUCCESS   = 0L
    const val LYRICS_STATUS_NO_LYRICS = 1L
    const val LYRICS_STATUS_LOADING   = 2L
    const val LYRICS_STATUS_FAIL      = 3L

    /** 车机歌词功能总开关 */
    const val PREF_CAR_LYRICS_ENABLED = "car_lyrics_enabled"
}