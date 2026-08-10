package com.lladlam.melox.ui.player

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.Color
import java.net.URI
import java.util.concurrent.ConcurrentHashMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import kotlin.math.abs

internal data class ArtworkDynamicPalette(
    val cells: List<Color>,
    val average: Color,
) {
    companion object {
        val Fallback = ArtworkDynamicPalette(
            cells = listOf(
                Color(0xFF101E3D), Color(0xFF2E1A57), Color(0xFF0F3B4D),
                Color(0xFF38143D), Color(0xFF24476B), Color(0xFF4D1F4D),
                Color(0xFF0D2E42), Color(0xFF291A4D), Color(0xFF121C33),
            ),
            average = Color(0xFF171D32),
        )
    }
}

/**
 * Android counterpart of MeloX iOS ArtworkAccentColorProvider.
 *
 * The iOS implementation down-samples artwork to roughly 160 px, samples a
 * 3x3 grid and uses those nine average colors to drive the flowing-light
 * player background. Keep the same data model here instead of reducing an
 * artwork to one dominant swatch.
 */
internal object ArtworkDynamicPaletteProvider {
    private const val GRID = 3
    private const val TARGET_SIZE = 160
    private val cache = ConcurrentHashMap<String, ArtworkDynamicPalette>()
    private val http = OkHttpClient()

    suspend fun paletteFor(url: String?): ArtworkDynamicPalette {
        val source = url?.takeIf(String::isNotBlank) ?: return ArtworkDynamicPalette.Fallback
        cache[source]?.let { return it }

        return withContext(Dispatchers.IO) {
            cache[source]?.let { return@withContext it }
            val palette = runCatching {
                val request = Request.Builder()
                    .url(optimizedArtworkUrl(source))
                    .header("User-Agent", "MeloX-Android/0.1")
                    .build()
                http.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) error("Artwork HTTP ${response.code}")
                    val bytes = response.body.bytes()
                    val decoded = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                        ?: error("Unable to decode artwork")
                    val scaled = if (decoded.width == TARGET_SIZE && decoded.height == TARGET_SIZE) {
                        decoded
                    } else {
                        Bitmap.createScaledBitmap(decoded, TARGET_SIZE, TARGET_SIZE, true)
                    }
                    try {
                        makePalette(scaled)
                    } finally {
                        if (scaled !== decoded) scaled.recycle()
                        decoded.recycle()
                    }
                }
            }.getOrNull()

            // Do not cache transient HTTP/decoder failures. A cold start or a
            // later retry must be able to recover instead of pinning fallback
            // colors to this artwork URL for the entire process lifetime.
            if (palette != null) cache[source] = palette
            palette ?: ArtworkDynamicPalette.Fallback
        }
    }

    private fun makePalette(bitmap: Bitmap): ArtworkDynamicPalette {
        val width = bitmap.width
        val height = bitmap.height
        val cellWidth = width / GRID
        val cellHeight = height / GRID
        val cells = buildList(GRID * GRID) {
            for (row in 0 until GRID) {
                for (column in 0 until GRID) {
                    val left = column * cellWidth
                    val top = row * cellHeight
                    val right = if (column == GRID - 1) width else (column + 1) * cellWidth
                    val bottom = if (row == GRID - 1) height else (row + 1) * cellHeight
                    add(averageColor(bitmap, left, top, right, bottom))
                }
            }
        }
        val displayCells = displayColors(cells)
        return ArtworkDynamicPalette(
            cells = displayCells,
            average = average(displayCells),
        )
    }

    private fun averageColor(
        bitmap: Bitmap,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int,
    ): Color {
        var red = 0L
        var green = 0L
        var blue = 0L
        var count = 0L

        // Sample every second pixel. A 160x160 input is already tiny and this
        // keeps palette extraction cheap enough to perform on every song swap.
        var y = top
        while (y < bottom) {
            var x = left
            while (x < right) {
                val pixel = bitmap.getPixel(x, y)
                red += (pixel shr 16) and 0xFF
                green += (pixel shr 8) and 0xFF
                blue += pixel and 0xFF
                count += 1
                x += 2
            }
            y += 2
        }

        if (count == 0L) return Color(0xFF5B4B45)
        return Color(
            red = (red.toFloat() / count / 255f).coerceIn(0f, 1f),
            green = (green.toFloat() / count / 255f).coerceIn(0f, 1f),
            blue = (blue.toFloat() / count / 255f).coerceIn(0f, 1f),
            alpha = 1f,
        )
    }

    /** Port of ArtworkFlowingLightPalette.displayColorsRGB from MeloX. */
    private fun displayColors(source: List<Color>): List<Color> {
        val hsv = source.map(::toHsv)
        val anchor = hsv.maxByOrNull { it.saturation * (0.38f + it.value * 0.62f) }
            ?: Hsv(0.62f, 0.72f, 0.48f)
        val averageValue = hsv.map(Hsv::value).average().toFloat()
        val chromaticStrength = (anchor.saturation / 0.34f).coerceIn(0f, 1f)
        val targetValues = floatArrayOf(
            0.34f, 0.46f, 0.28f,
            0.27f, 0.50f, 0.36f,
            0.17f, 0.30f, 0.15f,
        )
        val targetSaturations = floatArrayOf(
            0.76f, 0.64f, 0.82f,
            0.84f, 0.66f, 0.78f,
            0.90f, 0.80f, 0.92f,
        )
        return hsv.indices.map { index ->
            val item = hsv[index]
            val hue = if (item.saturation >= 0.16f && item.value >= 0.08f) {
                blendedHue(anchor.hue, item.hue, 0.62f)
            } else {
                anchor.hue
            }
            val artworkSaturation = item.saturation * 0.58f + anchor.saturation * 0.42f
            val roleSaturation = 0.16f + (targetSaturations[index] - 0.16f) * chromaticStrength
            val saturation = maxOf(roleSaturation, artworkSaturation).coerceIn(0.12f, 0.96f)
            val offset = (item.value - averageValue).coerceIn(-0.12f, 0.12f) * 0.24f
            fromHsv(Hsv(hue, saturation, (targetValues[index] + offset).coerceIn(0.12f, 0.54f)))
        }
    }

    private data class Hsv(val hue: Float, val saturation: Float, val value: Float)

    private fun toHsv(color: Color): Hsv {
        val maximum = maxOf(color.red, color.green, color.blue)
        val minimum = minOf(color.red, color.green, color.blue)
        val delta = maximum - minimum
        val saturation = if (maximum > 0f) delta / maximum else 0f
        if (delta <= 0.00001f) return Hsv(0f, saturation, maximum)
        val raw = when (maximum) {
            color.red -> ((color.green - color.blue) / delta) % 6f
            color.green -> (color.blue - color.red) / delta + 2f
            else -> (color.red - color.green) / delta + 4f
        }
        val normalized = raw / 6f
        return Hsv(if (normalized >= 0f) normalized else normalized + 1f, saturation, maximum)
    }

    private fun fromHsv(hsv: Hsv): Color {
        val chroma = hsv.value * hsv.saturation
        val sector = hsv.hue * 6f
        val secondary = chroma * (1f - abs(sector % 2f - 1f))
        val offset = hsv.value - chroma
        val (red, green, blue) = when {
            sector < 1f -> Triple(chroma, secondary, 0f)
            sector < 2f -> Triple(secondary, chroma, 0f)
            sector < 3f -> Triple(0f, chroma, secondary)
            sector < 4f -> Triple(0f, secondary, chroma)
            sector < 5f -> Triple(secondary, 0f, chroma)
            else -> Triple(chroma, 0f, secondary)
        }
        return Color(red + offset, green + offset, blue + offset, 1f)
    }

    private fun blendedHue(source: Float, destination: Float, weight: Float): Float {
        var delta = destination - source
        if (delta > 0.5f) delta -= 1f else if (delta < -0.5f) delta += 1f
        val result = source + delta * weight
        return when {
            result < 0f -> result + 1f
            result >= 1f -> result - 1f
            else -> result
        }
    }

    private fun average(colors: List<Color>): Color {
        if (colors.isEmpty()) return ArtworkDynamicPalette.Fallback.average
        return Color(
            red = colors.map(Color::red).average().toFloat(),
            green = colors.map(Color::green).average().toFloat(),
            blue = colors.map(Color::blue).average().toFloat(),
            alpha = 1f,
        )
    }

    private fun optimizedArtworkUrl(source: String): String {
        val uri = runCatching { URI(source) }.getOrNull() ?: return source
        if (uri.host?.endsWith(".music.126.net") != true) return source
        return source.toHttpUrlOrNull()
            ?.newBuilder()
            ?.setQueryParameter("param", "160y160")
            ?.build()
            ?.toString()
            ?: source
    }
}
