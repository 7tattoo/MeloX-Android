package com.lladlam.melox.core.lyrics

/**
 * Provider-neutral entry point for ordinary LRC lyrics. The mature timing and
 * annotation implementation remains shared with the existing NetEase parser.
 */
object LrcLyricsParser {
    fun parse(
        lrc: String,
        translation: String = "",
        romanization: String = "",
    ): LyricsDocument = NeteaseLyricParser.parse(
        yrc = "",
        lrc = lrc,
        translatedLrc = translation,
        romanizedLrc = romanization,
    )
}
