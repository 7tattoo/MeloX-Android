package com.lladlam.melox.ui.player

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import java.net.URI
import java.util.LinkedHashMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

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
    private const val MAX_CACHE_ENTRIES = 48
    private val cache = object : LinkedHashMap<String, ArtworkDynamicPalette>(MAX_CACHE_ENTRIES, .75f, true) {
        override fun removeEldestEntry(
            eldest: MutableMap.MutableEntry<String, ArtworkDynamicPalette>?,
        ): Boolean = size > MAX_CACHE_ENTRIES
    }
    private val http = com.lladlam.melox.core.network.MeloXHttpClient.shared

    suspend fun paletteFor(context: Context, url: String?): ArtworkDynamicPalette {
        val source = url?.takeIf(String::isNotBlank) ?: return ArtworkDynamicPalette.Fallback
        cached(source)?.let { return it }

        return withContext(Dispatchers.IO) {
            cached(source)?.let { return@withContext it }
            val palette = runCatching {
                val decoded = decodeArtwork(context, source)
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
            }.getOrNull()

            // Do not cache transient HTTP/decoder failures. A cold start or a
            // later retry must be able to recover instead of pinning fallback
            // colors to this artwork URL for the entire process lifetime.
            if (palette != null) synchronized(cache) { cache[source] = palette }
            palette ?: ArtworkDynamicPalette.Fallback
        }
    }

    private fun cached(source: String): ArtworkDynamicPalette? = synchronized(cache) { cache[source] }

    fun clearMemoryCache() = synchronized(cache) { cache.clear() }

    suspend fun bitmapFor(context: Context, source: String?): Bitmap? = withContext(Dispatchers.IO) {
        val value = source?.takeIf(String::isNotBlank) ?: return@withContext null
        runCatching { decodeArtwork(context.applicationContext, value) }.getOrNull()
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
            error("Unable to read artwork file bounds")
        }
        return BitmapFactory.decodeFile(path, decodeOptions(bounds.outWidth, bounds.outHeight))
            ?: error("Unable to decode artwork file")
    }

    private fun decodeBytes(bytes: ByteArray): Bitmap {
        val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        BitmapFactory.decodeByteArray(bytes, 0, bytes.size, bounds)
        if (bounds.outWidth <= 0 || bounds.outHeight <= 0) {
            error("Unable to read artwork bounds")
        }
        return BitmapFactory.decodeByteArray(
            bytes,
            0,
            bytes.size,
            decodeOptions(bounds.outWidth, bounds.outHeight),
        ) ?: error("Unable to decode artwork")
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

    private fun makePalette(bitmap: Bitmap): ArtworkDynamicPalette {
        val width = bitmap.width
        val height = bitmap.height
        val cellWidth = width / GRID
        val cellHeight = height / GRID
        val rawCells = buildList(GRID * GRID) {
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
        val cells = rawCells.map { it.boostSaturation(1.2f) }
        return ArtworkDynamicPalette(
            cells = cells,
            average = averageColor(bitmap, 0, 0, width, height).boostSaturation(1.1f),
        )
    }

    private fun Color.boostSaturation(multiplier: Float): Color {
        val hsl = FloatArray(3)
        androidx.core.graphics.ColorUtils.colorToHSL(this.toArgb(), hsl)
        if (hsl[1] < 0.08f) return this
        hsl[1] = (hsl[1] * multiplier).coerceIn(0f, 1f)
        return Color(androidx.core.graphics.ColorUtils.HSLToColor(hsl))
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
