package com.lladlam.melox.ui.player

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.ColorUtils
import java.net.URI
import java.util.concurrent.ConcurrentHashMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

/**
 * Enhanced palette extraction with saturation boost and hue shifting.
 * Provides richer, more vibrant colors compared to basic pixel sampling.
 * No external Palette library dependency - pure pixel analysis.
 */
internal object PaletteDynamicPaletteProvider {
    private const val TARGET_SIZE = 200
    private const val GRID = 3
    private val cache = ConcurrentHashMap<String, ArtworkDynamicPalette>()
    private val http = OkHttpClient()

    suspend fun paletteFor(context: Context, url: String?): ArtworkDynamicPalette {
        val source = url?.takeIf(String::isNotBlank) ?: return ArtworkDynamicPalette.Fallback
        cache[source]?.let { return it }

        return withContext(Dispatchers.IO) {
            cache[source]?.let { return@withContext it }
            val palette = runCatching {
                val decoded = decodeArtwork(context, source)
                val scaled = Bitmap.createScaledBitmap(decoded, TARGET_SIZE, TARGET_SIZE, true)
                try {
                    extractPalette(scaled)
                } finally {
                    if (scaled !== decoded) scaled.recycle()
                    decoded.recycle()
                }
            }.getOrNull()

            if (palette != null) cache[source] = palette
            palette ?: ArtworkDynamicPalette.Fallback
        }
    }

    private fun decodeArtwork(context: Context, source: String): Bitmap {
        val uri = Uri.parse(source)
        return when (uri.scheme?.lowercase()) {
            "http", "https" -> decodeNetworkArtwork(source)
            ContentResolver.SCHEME_CONTENT,
            ContentResolver.SCHEME_FILE,
            ContentResolver.SCHEME_ANDROID_RESOURCE -> decodeLocalArtwork(context, uri)
            null -> decodeFileArtwork(source)
            else -> error("Unsupported artwork URI scheme: ${uri.scheme}")
        }
    }

    private fun decodeNetworkArtwork(source: String): Bitmap {
        val request = Request.Builder()
            .url(optimizedArtworkUrl(source))
            .header("User-Agent", "MeloX-Android/0.1")
            .build()
        return http.newCall(request).execute().use { response ->
            if (!response.isSuccessful) error("Artwork HTTP ${response.code}")
            decodeBytes(response.body.bytes())
        }
    }

    private fun decodeLocalArtwork(context: Context, uri: Uri): Bitmap {
        val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        val boundsStream = context.contentResolver.openInputStream(uri)
            ?: error("Unable to open local artwork")
        boundsStream.use { stream ->
            BitmapFactory.decodeStream(stream, null, bounds)
        }
        if (bounds.outWidth <= 0 || bounds.outHeight <= 0) {
            error("Unable to read local artwork bounds")
        }
        val options = decodeOptions(bounds.outWidth, bounds.outHeight)
        return context.contentResolver.openInputStream(uri)?.use { stream ->
            BitmapFactory.decodeStream(stream, null, options)
        } ?: error("Unable to decode local artwork")
    }

    private fun decodeFileArtwork(path: String): Bitmap {
        val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        BitmapFactory.decodeFile(path, bounds)
        if (bounds.outWidth <= 0 || bounds.outHeight <= 0) {
            error("Unable to read artwork file")
        }
        return BitmapFactory.decodeFile(path, decodeOptions(bounds.outWidth, bounds.outHeight))
            ?: error("Unable to decode artwork file")
    }

    private fun decodeBytes(bytes: ByteArray): Bitmap {
        val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        BitmapFactory.decodeByteArray(bytes, 0, bytes.size, bounds)
        if (bounds.outWidth <= 0 || bounds.outHeight <= 0) {
            error("Unable to read artwork bytes")
        }
        return BitmapFactory.decodeByteArray(
            bytes, 0, bytes.size, decodeOptions(bounds.outWidth, bounds.outHeight),
        ) ?: error("Unable to decode artwork bytes")
    }

    private fun decodeOptions(width: Int, height: Int): BitmapFactory.Options {
        var sampleSize = 1
        while (width / (sampleSize * 2) >= TARGET_SIZE &&
            height / (sampleSize * 2) >= TARGET_SIZE
        ) {
            sampleSize *= 2
        }
        return BitmapFactory.Options().apply {
            inSampleSize = sampleSize
            inPreferredConfig = Bitmap.Config.ARGB_8888
        }
    }

    private fun extractPalette(bitmap: Bitmap): ArtworkDynamicPalette {
        val width = bitmap.width
        val height = bitmap.height
        val cellWidth = width / GRID
        val cellHeight = height / GRID

        val cellColors = mutableListOf<Color>()
        for (row in 0 until GRID) {
            for (column in 0 until GRID) {
                val left = column * cellWidth
                val top = row * cellHeight
                val right = if (column == GRID - 1) width else (column + 1) * cellWidth
                val bottom = if (row == GRID - 1) height else (row + 1) * cellHeight
                cellColors.add(averageColor(bitmap, left, top, right, bottom))
            }
        }

        val average = averageColor(bitmap, 0, 0, width, height)

        val enhanced = cellColors.map { color ->
            var c = color
            if (c.isGrayscale()) c = c.boostSaturation(3.0f).forceHueIfGray()
            c.boostSaturation(1.3f)
        }

        val seedColor = enhanced.firstOrNull { !it.isGrayscale() } ?: average
        val c1 = enhanced.getOrElse(0) { seedColor }
        val c2 = enhanced.getOrElse(1) { seedColor.darken(0.4f) }
        val c3 = enhanced.getOrElse(2) { seedColor.lighten(0.3f) }
        val c4 = c1.shiftHue(40f).lighten(0.1f)

        return ArtworkDynamicPalette(
            cells = listOf(c1, c2, c3, c4, c1, c2, c3, c4, average),
            average = average,
        )
    }

    private fun averageColor(bitmap: Bitmap, left: Int, top: Int, right: Int, bottom: Int): Color {
        var red = 0L; var green = 0L; var blue = 0L; var count = 0L
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

    private fun optimizedArtworkUrl(source: String): String {
        val uri = runCatching { URI(source) }.getOrNull() ?: return source
        if (uri.host?.endsWith(".music.126.net") != true) return source
        return source.toHttpUrlOrNull()
            ?.newBuilder()
            ?.setQueryParameter("param", "200y200")
            ?.build()
            ?.toString()
            ?: source
    }
}
