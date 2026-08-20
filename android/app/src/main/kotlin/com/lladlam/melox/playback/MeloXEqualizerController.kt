package com.lladlam.melox.playback

import android.content.Context
import android.media.audiofx.Equalizer
import android.util.Log
import com.lladlam.melox.ui.settings.MeloXSettingsPreferences
import kotlin.math.ln

/** Owns the Android DSP effect attached to the active ExoPlayer audio session. */
class MeloXEqualizerController(private val context: Context) {
    private var effect: Equalizer? = null
    private var sessionId = 0
    private var appliedSettings: Settings? = null

    fun attach(newSessionId: Int) {
        if (newSessionId <= 0 || newSessionId == sessionId) return
        release()
        sessionId = newSessionId
        effect = runCatching { Equalizer(0, newSessionId) }
            .onFailure { Log.w("MeloXPlayback", "Equalizer unavailable", it) }
            .getOrNull()
        applySettings(force = true)
    }

    fun applySettings(force: Boolean = false) {
        val equalizer = effect ?: return
        runCatching {
            val enabled = MeloXSettingsPreferences.boolean(context, "equalizer_enabled", false)
            val preset = MeloXSettingsPreferences.string(context, "equalizer_preset", "Flat")
            val preamp = MeloXSettingsPreferences.number(context, "equalizer_preamp_db", 0f).coerceIn(-12f, 12f)
            val gains = if (preset == "Custom") {
                List(TARGET_FREQUENCIES_HZ.size) { index ->
                    MeloXSettingsPreferences.number(context, "equalizer_custom_band_$index", 0f).coerceIn(-12f, 12f)
                }
            } else {
                (PRESETS[preset] ?: PRESETS.getValue("Flat")).toList()
            }
            val settings = Settings(enabled, preset, preamp, gains)
            if (!force && settings == appliedSettings) return
            val range = equalizer.bandLevelRange
            repeat(equalizer.numberOfBands.toInt()) { index ->
                val centerHz = equalizer.getCenterFreq(index.toShort()).toFloat() / 1_000f
                val gain = interpolatedGain(centerHz, gains)
                val level = ((gain + preamp) * 100f).toInt().coerceIn(range[0].toInt(), range[1].toInt())
                equalizer.setBandLevel(index.toShort(), level.toShort())
            }
            equalizer.enabled = enabled
            appliedSettings = settings
        }.onFailure {
            Log.w("MeloXPlayback", "Equalizer update failed; releasing effect", it)
            release()
        }
    }

    fun release() {
        runCatching { effect?.release() }
        effect = null
        sessionId = 0
        appliedSettings = null
    }

    private data class Settings(
        val enabled: Boolean,
        val preset: String,
        val preamp: Float,
        val gains: List<Float>,
    )

    companion object {
        val TARGET_FREQUENCIES_HZ = floatArrayOf(31f, 62f, 125f, 250f, 500f, 1_000f, 2_000f, 4_000f, 8_000f, 16_000f)

        val PRESETS = linkedMapOf(
            "Flat" to floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f),
            "Bass" to floatArrayOf(6f, 5f, 4f, 2f, 1f, 0f, -1f, -1f, 0f, 0f),
            "Vocal" to floatArrayOf(-2f, -1f, 0f, 1f, 3f, 4f, 4f, 2f, 0f, -1f),
            "Treble" to floatArrayOf(-2f, -2f, -1f, 0f, 0f, 1f, 2f, 4f, 5f, 6f),
            "Electronic" to floatArrayOf(5f, 4f, 2f, 0f, -1f, 1f, 2f, 3f, 4f, 5f),
            "Rock" to floatArrayOf(4f, 3f, 2f, 0f, -1f, 0f, 2f, 3f, 4f, 4f),
            "Classical" to floatArrayOf(3f, 2f, 1f, 0f, 0f, 0f, -1f, 1f, 2f, 3f),
            "Custom" to floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f),
        )

        internal fun interpolatedGain(centerHz: Float, gains: List<Float>): Float {
            if (centerHz <= TARGET_FREQUENCIES_HZ.first()) return gains.first()
            if (centerHz >= TARGET_FREQUENCIES_HZ.last()) return gains.last()
            val upper = TARGET_FREQUENCIES_HZ.indexOfFirst { it >= centerHz }.coerceAtLeast(1)
            val lower = upper - 1
            val lowerLog = ln(TARGET_FREQUENCIES_HZ[lower])
            val upperLog = ln(TARGET_FREQUENCIES_HZ[upper])
            val fraction = ((ln(centerHz) - lowerLog) / (upperLog - lowerLog)).coerceIn(0f, 1f)
            return gains[lower] + (gains[upper] - gains[lower]) * fraction
        }
    }
}
