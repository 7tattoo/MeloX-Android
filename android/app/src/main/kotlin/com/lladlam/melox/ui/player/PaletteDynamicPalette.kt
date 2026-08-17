package com.lladlam.melox.ui.player

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.ColorUtils
import androidx.palette.graphics.Palette
import coil3.ImageLoader
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.request.allowHardware
import coil3.toBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.ConcurrentHashMap

/**
 * Enhanced palette extraction using AndroidX Palette library.
 * Provides richer, more vibrant colors compared to manual pixel sampling.
 * Ported from Mei_MeloX_Android's AmbientBackground color extraction.
 */
internal object PaletteDynamicPaletteProvider {
    private const val TARGET_SIZE = 200
    private val cache = ConcurrentHashMap<String, ArtworkDynamicPalette>()

    suspend fun paletteFor(context: Context, url: String?): ArtworkDynamicPalette {
        val source = url?.takeIf(String::isNotBlank) ?: return ArtworkDynamicPalette.Fallback
        cache[source]?.let { return it }

        return withContext(Dispatchers.IO) {
            cache[source]?.let { return@withContext it }
            val palette = runCatching {
                val loader = ImageLoader(context)
                val request = ImageRequest.Builder(context)
                    .data(source)
                    .size(TARGET_SIZE)
                    .allowHardware(false)
                    .build()
                val result = loader.execute(request)
                if (result is SuccessResult) {
                    val bitmap = result.image.toBitmap()
                    extractPalette(bitmap)
                } else null
            }.getOrNull()

            if (palette != null) cache[source] = palette
            palette ?: ArtworkDynamicPalette.Fallback
        }
    }

    private fun extractPalette(bitmap: Bitmap): ArtworkDynamicPalette {
        val palette = Palette.from(bitmap)
            .maximumColorCount(24)
            .generate()

        val vibrant = palette.vibrantSwatch
        val darkVibrant = palette.darkVibrantSwatch
        val lightVibrant = palette.lightVibrantSwatch
        val dominant = palette.dominantSwatch

        val seedSwatch = vibrant ?: dominant
        val seedColor = if (seedSwatch != null) {
            Color(seedSwatch.rgb)
        } else {
            Color(0xFF1A237E)
        }

        var c1 = vibrant?.rgb?.let { Color(it) } ?: seedColor.boostSaturation(1.5f)
        var c2 = darkVibrant?.rgb?.let { Color(it) } ?: c1.darken(0.4f)
        var c3 = lightVibrant?.rgb?.let { Color(it) } ?: c1.lighten(0.3f)

        if (c1.isGrayscale()) c1 = c1.boostSaturation(3.0f).forceHueIfGray()
        if (c2.isGrayscale()) c2 = c2.boostSaturation(2.0f).forceHueIfGray()

        c1 = c1.boostSaturation(1.3f)
        c2 = c2.boostSaturation(1.3f)
        c3 = c3.boostSaturation(1.3f).lighten(0.1f)

        val c4 = c1.shiftHue(40f).lighten(0.1f)

        val colors = listOf(c1, c2, c3, c4)

        return ArtworkDynamicPalette(
            cells = colors + colors + listOf(seedColor.darken(0.3f)),
            average = seedColor,
        )
    }

    private fun Color.isGrayscale(threshold: Float = 0.15f): Boolean {
        val hsl = FloatArray(3)
        ColorUtils.colorToHSL(this.toArgb(), hsl)
        return hsl[1] < threshold
    }

    private fun Color.forceHueIfGray(): Color {
        val hsl = FloatArray(3)
        ColorUtils.colorToHSL(this.toArgb(), hsl)
        if (hsl[1] < 0.05f) {
            hsl[0] = 240f
            hsl[1] = 0.5f
        }
        return Color(ColorUtils.HSLToColor(hsl))
    }

    private fun Color.boostSaturation(multiplier: Float): Color {
        val hsl = FloatArray(3)
        ColorUtils.colorToHSL(this.toArgb(), hsl)
        if (hsl[2] < 0.2f) hsl[2] = 0.2f
        hsl[1] = (hsl[1] * multiplier).coerceIn(0.2f, 1f)
        return Color(ColorUtils.HSLToColor(hsl))
    }

    private fun Color.darken(factor: Float): Color {
        val hsl = FloatArray(3)
        ColorUtils.colorToHSL(this.toArgb(), hsl)
        hsl[2] = (hsl[2] * (1f - factor)).coerceIn(0f, 1f)
        return Color(ColorUtils.HSLToColor(hsl))
    }

    private fun Color.lighten(factor: Float): Color {
        val hsl = FloatArray(3)
        ColorUtils.colorToHSL(this.toArgb(), hsl)
        hsl[2] = (hsl[2] + (1f - hsl[2]) * factor).coerceIn(0f, 1f)
        return Color(ColorUtils.HSLToColor(hsl))
    }

    private fun Color.shiftHue(amount: Float): Color {
        val hsl = FloatArray(3)
        ColorUtils.colorToHSL(this.toArgb(), hsl)
        hsl[0] = (hsl[0] + amount).mod(360f)
        return Color(ColorUtils.HSLToColor(hsl))
    }
}
