package com.lladlam.melox.core.provider.bilibili

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import java.security.MessageDigest
import java.util.concurrent.ConcurrentHashMap

object BilibiliLyricOffsetStore {
    const val MinOffsetMs = -5_000
    const val MaxOffsetMs = 5_000
    private const val PreferencesName = "melox_bilibili_lyric_offsets"
    private val states = ConcurrentHashMap<String, androidx.compose.runtime.MutableIntState>()

    fun normalizeOffset(offsetMs: Int): Int = offsetMs.coerceIn(MinOffsetMs, MaxOffsetMs)

    fun preferenceKey(resourceValue: String): String = "track_" + sha256(resourceValue)

    fun read(context: Context, resourceValue: String): Int = normalizeOffset(
        context.applicationContext.getSharedPreferences(PreferencesName, Context.MODE_PRIVATE)
            .getInt(preferenceKey(resourceValue), 0),
    )

    fun state(context: Context, resourceValue: String): State<Int> {
        val key = preferenceKey(resourceValue)
        return states.getOrPut(key) { mutableIntStateOf(read(context, resourceValue)) }
    }

    fun write(context: Context, resourceValue: String, offsetMs: Int) {
        val normalized = normalizeOffset(offsetMs)
        val key = preferenceKey(resourceValue)
        context.applicationContext.getSharedPreferences(PreferencesName, Context.MODE_PRIVATE).edit().apply {
            if (normalized == 0) remove(key) else putInt(key, normalized)
        }.apply()
        states.getOrPut(key) { mutableIntStateOf(normalized) }.intValue = normalized
    }

    fun effectiveAdvance(globalAdvanceMs: Int, trackOffsetMs: Int): Long =
        globalAdvanceMs.toLong() + normalizeOffset(trackOffsetMs)

    private fun sha256(value: String): String = MessageDigest.getInstance("SHA-256")
        .digest(value.toByteArray()).joinToString("") { "%02x".format(it) }
}
