package com.lladlam.melox.ui.player

import android.graphics.Bitmap
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.lladlam.melox.playback.MeloXAudioReactiveRuntime
import com.lladlam.melox.ui.settings.MeloXLyricsRenderingQuality
import com.lladlam.melox.ui.settings.MeloXSettingsRuntime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.math.cos
import kotlin.math.sin

@Composable
internal fun MeloXBlurredArtworkBackdrop(
    artworkUrl: String?,
    modifier: Modifier = Modifier,
) {
    androidx.compose.foundation.layout.Box(modifier.fillMaxSize()) {
        AsyncImage(
            model = artworkUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize().graphicsLayer { scaleX = 1.18f; scaleY = 1.18f }.blur(38.dp),
        )
        Canvas(Modifier.fillMaxSize()) {
            drawRect(Color.Black.copy(alpha = .30f))
            drawRect(
                brush = Brush.verticalGradient(
                    listOf(Color.Black.copy(alpha = .05f), Color.Black.copy(alpha = .48f)),
                ),
            )
        }
    }
}

/**
 * Android renderer for MeloX's artwork-driven Flowing Light background.
 * Palette extraction is identical in shape to ArtworkAccentColorProvider:
 * 160px downsample -> 3x3 cell averages. Android has no SwiftUI MeshGradient
 * equivalent on all supported API levels, so the same nine control colors are
 * blended as overlapping moving radial fields.
 */
@Composable
internal fun MeloXFlowingLightBackdrop(
    artworkUrl: String?,
    isPlaying: Boolean,
    mediaId: String? = null,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    var targetPalette by remember { mutableStateOf(ArtworkDynamicPalette.Fallback) }
    val renderingQuality = MeloXSettingsRuntime.lyricRenderingQuality
    val meshWidth = when (renderingQuality) {
        MeloXLyricsRenderingQuality.Low -> 24
        MeloXLyricsRenderingQuality.Balanced -> 32
        MeloXLyricsRenderingQuality.High -> 48
    }
    val meshHeight = meshWidth * 23 / 10
    val meshBitmaps = remember(meshWidth, meshHeight) {
        List(2) { Bitmap.createBitmap(meshWidth, meshHeight, Bitmap.Config.ARGB_8888) }
    }
    val meshImages = remember(meshBitmaps) { meshBitmaps.map(Bitmap::asImageBitmap) }
    var meshImage by remember(meshImages) { mutableStateOf(meshImages.first()) }

    LaunchedEffect(artworkUrl) {
        targetPalette = ArtworkDynamicPaletteProvider.paletteFor(context, artworkUrl)
    }

    LaunchedEffect(isPlaying, artworkUrl, mediaId, renderingQuality, meshBitmaps, targetPalette) {
        val frameDelayMs = when (renderingQuality) {
            MeloXLyricsRenderingQuality.Low -> 50L
            MeloXLyricsRenderingQuality.Balanced -> 33L
            MeloXLyricsRenderingQuality.High -> 16L
        }
        var phase = 0f
        var energy = .18f
        var beatPulse = 0f
        var downbeatPulse = 0f
        var writeIndex = 1
        val currentColors = MutableList(9) { index ->
            targetPalette.cells.getOrElse(index) { targetPalette.average }
        }
        var currentAverage = targetPalette.average
        val pixels = IntArray(meshWidth * meshHeight)
        if (!isPlaying) {
            withContext(Dispatchers.Default) {
                fillFlowingMeshPixels(
                    pixels = pixels,
                    meshWidth = meshWidth,
                    meshHeight = meshHeight,
                    colors = currentColors,
                    average = currentAverage,
                    phase = phase,
                    energy = energy,
                    beatPulse = beatPulse,
                    downbeatPulse = downbeatPulse,
                )
                meshBitmaps[writeIndex].setPixels(pixels, 0, meshWidth, 0, 0, meshWidth, meshHeight)
            }
            meshImage = meshImages[writeIndex]
            // Playlist/detail backdrops pass isPlaying=false. Their mesh is
            // static, so keeping a 20-60 Hz generator alive underneath the
            // full-screen player only steals CPU and invalidates hidden layers.
            awaitCancellation()
        }
        while (true) {
            val sample = MeloXAudioReactiveRuntime.sample(mediaId)
            energy += (sample.energy - energy) * .18f
            beatPulse += (sample.beat - beatPulse) * .32f
            downbeatPulse += (sample.downbeat - downbeatPulse) * .24f
            val motion = .026f + energy.coerceIn(0f, 1f) * .038f + beatPulse * .016f
            phase = (phase + motion) % (Math.PI.toFloat() * 2f)
            val paletteBlend = (frameDelayMs / 800f).coerceIn(.02f, .18f)
            currentColors.indices.forEach { index ->
                currentColors[index] = lerpColor(
                    currentColors[index],
                    targetPalette.cells.getOrElse(index) { targetPalette.average },
                    paletteBlend,
                )
            }
            currentAverage = lerpColor(currentAverage, targetPalette.average, paletteBlend)
            val bitmap = meshBitmaps[writeIndex]
            withContext(Dispatchers.Default) {
                fillFlowingMeshPixels(
                    pixels = pixels,
                    meshWidth = meshWidth,
                    meshHeight = meshHeight,
                    colors = currentColors,
                    average = currentAverage,
                    phase = phase,
                    energy = energy,
                    beatPulse = beatPulse,
                    downbeatPulse = downbeatPulse,
                )
                bitmap.setPixels(pixels, 0, meshWidth, 0, 0, meshWidth, meshHeight)
            }
            meshImage = meshImages[writeIndex]
            writeIndex = 1 - writeIndex
            delay(frameDelayMs)
        }
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        drawImage(
            image = meshImage,
            dstSize = IntSize(size.width.toInt().coerceAtLeast(1), size.height.toInt().coerceAtLeast(1)),
            filterQuality = FilterQuality.High,
        )

        // MeloX keeps the lower control region darker for white text/controls.
        drawRect(
            brush = Brush.verticalGradient(
                colorStops = arrayOf(
                    0f to Color.Black.copy(alpha = 0.04f),
                    0.52f to Color.Black.copy(alpha = 0.10f),
                    1f to Color.Black.copy(alpha = 0.48f),
                ),
            ),
        )

        // The downbeat vignette is folded into the mesh pixels above so it does
        // not require another full-screen blend pass.
    }
}

private fun lerpColor(from: Color, to: Color, amount: Float): Color = Color(
    red = from.red + (to.red - from.red) * amount,
    green = from.green + (to.green - from.green) * amount,
    blue = from.blue + (to.blue - from.blue) * amount,
    alpha = 1f,
)

private fun fillFlowingMeshPixels(
    pixels: IntArray,
    meshWidth: Int,
    meshHeight: Int,
    colors: List<Color>,
    average: Color,
    phase: Float,
    energy: Float,
    beatPulse: Float,
    downbeatPulse: Float,
) {
    val radiusNormalized = (0.58f + energy.coerceIn(0f, 1f) * .08f + beatPulse * .035f)
        .coerceAtLeast(.01f)
    val centersX = FloatArray(colors.size)
    val centersY = FloatArray(colors.size)
    colors.indices.forEach { index ->
        val row = index / 3
        val column = index % 3
        val baseX = when (column) { 0 -> .08f; 1 -> .50f; else -> .92f }
        val baseY = when (row) { 0 -> .10f; 1 -> .50f; else -> .90f }
        val localPhase = phase + index * .71f
        val displacement = .052f + energy.coerceIn(0f, 1f) * .045f
        centersX[index] = baseX + sin(localPhase) * displacement
        centersY[index] = baseY + cos(localPhase * .83f) * displacement * .87f
    }
    val maxDimension = maxOf(meshWidth, meshHeight).toFloat()
    val widthScale = meshWidth / maxDimension
    val heightScale = meshHeight / maxDimension
    val baseWeight = .22f
    val pulseGain = 1f + energy.coerceIn(0f, 1f) * .10f + beatPulse * .07f
    val downbeatShade = 1f - (downbeatPulse * .16f).coerceIn(0f, .16f)
    var pixelIndex = 0
    for (yIndex in 0 until meshHeight) {
        val v = yIndex.toFloat() / (meshHeight - 1).coerceAtLeast(1).toFloat()
        for (xIndex in 0 until meshWidth) {
            val u = xIndex.toFloat() / (meshWidth - 1).coerceAtLeast(1).toFloat()
            var totalWeight = baseWeight
            var red = average.red * baseWeight
            var green = average.green * baseWeight
            var blue = average.blue * baseWeight
            colors.indices.forEach { colorIndex ->
                val dx = (u - centersX[colorIndex]) * widthScale / radiusNormalized
                val dy = (v - centersY[colorIndex]) * heightScale / radiusNormalized
                val distanceSquared = dx * dx + dy * dy
                val falloff = 1f / (1f + distanceSquared * 4.5f)
                val weight = falloff * falloff
                val color = colors[colorIndex]
                totalWeight += weight
                red += color.red * weight
                green += color.green * weight
                blue += color.blue * weight
            }
            val r = ((red / totalWeight) * pulseGain * downbeatShade).coerceIn(0f, 1f)
            val g = ((green / totalWeight) * pulseGain * downbeatShade).coerceIn(0f, 1f)
            val b = ((blue / totalWeight) * pulseGain * downbeatShade).coerceIn(0f, 1f)
            pixels[pixelIndex++] = (0xFF shl 24) or
                ((r * 255f).toInt() shl 16) or
                ((g * 255f).toInt() shl 8) or
                (b * 255f).toInt()
        }
    }
}
