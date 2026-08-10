/*
 * MeloX Backdrop controls
 *
 * Adapted from the LiquidButton and LiquidBottomTabs examples in
 * Kyant0/AndroidLiquidGlass. Upstream is licensed under Apache-2.0.
 * https://github.com/Kyant0/AndroidLiquidGlass
 */
package com.lladlam.melox.ui.glass

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kyant.backdrop.Backdrop
import com.kyant.backdrop.backdrops.rememberCombinedBackdrop
import com.kyant.backdrop.drawBackdrop
import com.kyant.backdrop.effects.blur
import com.kyant.backdrop.effects.lens
import com.kyant.backdrop.effects.vibrancy
import com.kyant.backdrop.highlight.Highlight
import com.kyant.backdrop.shadow.InnerShadow
import com.kyant.backdrop.shadow.Shadow
import kotlinx.coroutines.launch

/** The screen backdrop sampled by all MeloX liquid controls. */
val LocalMeloXBackdrop = staticCompositionLocalOf<Backdrop?> { null }

/**
 * Official LiquidButton-style glass, generalized so existing MeloX controls
 * keep their exact iOS-derived size, shape and content.
 */
@Composable
fun Modifier.meloXLiquidButton(
    shape: Shape,
    enabled: Boolean = true,
    tint: Color = Color.Unspecified,
    surfaceColor: Color = Color.Unspecified,
    blurRadius: Dp = 2.dp,
    lensRadius: Dp = 12.dp,
    refractionHeight: Dp = 24.dp,
): Modifier {
    val backdrop = LocalMeloXBackdrop.current
    if (backdrop == null) {
        val stableSurface = when {
            surfaceColor != Color.Unspecified -> surfaceColor.copy(
                alpha = maxOf(surfaceColor.alpha, 0.46f),
            )
            tint == Color.Unspecified -> Color.White.copy(alpha = 0.46f)
            else -> tint.copy(alpha = maxOf(tint.alpha, 0.42f))
        }
        return background(stableSurface, shape)
            .border(0.75.dp, Color.White.copy(alpha = 0.62f), shape)
    }
    val scope = rememberCoroutineScope()
    val press = remember { Animatable(0f, 0.001f) }

    return this
        .drawBackdrop(
            backdrop = backdrop,
            shape = { shape },
            effects = {
                vibrancy()
                blur(blurRadius.toPx())
                // AndroidLiquidGlass' official button uses a 12/24dp lens.
                // Keep the same effect order with a shallower, non-chromatic
                // lens: full demo refraction produced polygonal artifacts on
                // the target MediaTek renderer.
                lens(
                    (lensRadius * 0.42f).toPx(),
                    (refractionHeight * 0.32f).toPx(),
                    chromaticAberration = false,
                )
            },
            highlight = {
                Highlight.Default.copy(alpha = 0.44f + press.value * 0.36f)
            },
            shadow = {
                Shadow(radius = 4.dp, alpha = 0.14f + press.value * 0.08f)
            },
            innerShadow = {
                InnerShadow(
                    radius = 4.dp * press.value,
                    alpha = press.value * 0.72f,
                )
            },
            onDrawSurface = {
                if (tint != Color.Unspecified) {
                    drawRect(tint, blendMode = BlendMode.Hue)
                    drawRect(tint.copy(alpha = tint.alpha * 0.75f))
                }
                if (surfaceColor != Color.Unspecified) drawRect(surfaceColor)
            },
        )
        .pointerInput(enabled) {
            if (!enabled) return@pointerInput
            awaitEachGesture {
                awaitFirstDown(requireUnconsumed = false)
                scope.launch { press.animateTo(1f, spring(0.55f, 420f, 0.001f)) }
                waitForUpOrCancellation()
                scope.launch { press.animateTo(0f, spring(0.68f, 360f, 0.001f)) }
            }
        }
}

/** Official LiquidBottomTabs-style outer panel. */
@Composable
fun Modifier.meloXLiquidBottomBar(
    shape: Shape,
    tint: Color,
    surfaceColor: Color,
): Modifier {
    val backdrop = LocalMeloXBackdrop.current
    if (backdrop == null) {
        val stableSurface = surfaceColor.copy(alpha = maxOf(surfaceColor.alpha, 0.48f))
        return background(stableSurface, shape)
            .border(0.75.dp, Color.White.copy(alpha = 0.62f), shape)
    }
    return drawBackdrop(
        backdrop = backdrop,
        shape = { shape },
        effects = {
            vibrancy()
            blur(8.dp.toPx())
            // Official LiquidBottomTabs uses a 24/24dp lens. A shallow lens
            // preserves the edge refraction while remaining stable on the
            // target GPU; blur and vibrancy remain at the official values.
            lens(3.dp.toPx(), 4.dp.toPx(), chromaticAberration = false)
        },
        highlight = { Highlight.Default.copy(alpha = 0.68f) },
        shadow = { Shadow(radius = 6.dp, alpha = 0.16f) },
        onDrawSurface = {
            drawRect(tint, blendMode = BlendMode.Hue)
            drawRect(surfaceColor)
        },
    )
}

/** Moving/selected tab lens used inside the bottom panel. */
@Composable
fun Modifier.meloXLiquidTabSelection(
    shape: Shape,
    selected: Boolean,
    tint: Color,
    panelBackdrop: Backdrop? = null,
): Modifier {
    if (!selected) return this
    val backdrop = LocalMeloXBackdrop.current
    if (backdrop == null) {
        return background(tint.copy(alpha = maxOf(tint.alpha, 0.36f)), shape)
            .border(0.5.dp, Color.White.copy(alpha = 0.58f), shape)
    }
    // Official LiquidBottomTabs records the panel into a second Backdrop and
    // samples the combined page + panel scene for the moving selection.
    // Without this, the selected capsule samples page artwork directly and
    // appears skewed or punched through.
    val selectionBackdrop = if (panelBackdrop != null) {
        rememberCombinedBackdrop(backdrop, panelBackdrop)
    } else {
        backdrop
    }
    return drawBackdrop(
        backdrop = selectionBackdrop,
        shape = { shape },
        effects = {
            // At rest the official selection has almost no refraction; the
            // large chromatic lens is only introduced while dragging.
            lens(0.25.dp.toPx(), 0.5.dp.toPx(), chromaticAberration = false)
        },
        highlight = { Highlight.Default.copy(alpha = 0.56f) },
        shadow = { Shadow(radius = 3.dp, alpha = 0.12f) },
        innerShadow = { InnerShadow(radius = 3.dp, alpha = 0.18f) },
        onDrawSurface = { drawRect(tint) },
    )
}
