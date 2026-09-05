package com.lladlam.melox.logic.car

/**
 * vivo 车联投屏歌词协议常量。
 *
 * 协议参考：vivo 车机滚动歌词适配开发文档
 * （https://github.com/huang6668/apple-music-vivo-car-lyrics/tree/main/docs）
 *
 * 铁律：只允许整段 LRC（LYRICS_WHOLE），禁止 LYRICS_LINE 单行通道；
 * 禁止 music.media.extras.* 单行键；加载期间禁止写空歌词或 1/2/3 负状态。
 */
object CarLyricsConstants {

    // ---- Channel A: Metadata（车载 Launcher 直接读取，整段滚动）----
    const val METADATA_KEY_LYRICS_WHOLE  = "ucar.media.metadata.LYRICS_WHOLE"
    const val METADATA_KEY_LYRICS_STATUS = "ucar.media.metadata.LYRICS_STATUS"
    const val METADATA_KEY_UCAR_TITLE    = "ucar.media.metadata.UCAR_TITLE"
    const val METADATA_KEY_UCAR_ARTIST   = "ucar.media.metadata.UCAR_ARTIST"

    // ---- vivo 原子随身听：MediaMetadata 常驻支持位 ----
    const val METADATA_KEY_SUPPORT_EVENT = "vivomusicmix.media.metadata.support_event"

    /** 歌词位 + 进度条位（文档要求 31） */
    const val SUPPORT_EVENT_DEFAULT = 31L

    // ---- 原子随身听事件（setExtras；官方拼写错误 meida / meidia_id 必须保留）----
    const val ATOM_ACTION_KEY       = "vivomusicmix.meida.extra.key.action"
    const val ATOM_ACTION_LRC_CHANGE = "vivomusicmix.extra.lrc_change"
    const val ATOM_LYRIC_KEY        = "vivomusicmix.extra.key.lyric"
    const val ATOM_MEDIA_ID_KEY     = "vivomusicmix.extra.key.meidia_id"

    // ---- 兼容清理：旧版本曾写入的键，关闭开关/升级时需要一并移除 ----
    const val METADATA_KEY_LYRICS_LINE = "ucar.media.metadata.LYRICS_LINE"
    const val METADATA_KEY_LYRIC_INFO  = "lyricInfo"

    const val LYRICS_STATUS_SUCCESS = 0L

    /** 车机歌词功能总开关 */
    const val PREF_CAR_LYRICS_ENABLED = "car_lyrics_enabled"
}