/*
 * MeloX Backdrop controls
 *
 * Adapted from the LiquidButton and LiquidBottomTabs examples in
 * Kyant0/AndroidLiquidGlass. Upstream is licensed under Apache-2.0.
 * https://github.com/Kyant0/AndroidLiquidGlass
 */
package com.lladlam.melox.ui.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kyant.backdrop.Backdrop
import com.kyant.backdrop.backdrops.rememberCombinedBackdrop
import com.kyant.backdrop.drawBackdrop
import com.kyant.backdrop.effects.blur
import com.kyant.backdrop.effects.lens
import com.kyant.backdrop.effects.vibrancy
import com.lladlam.melox.ui.theme.isMeloXDarkTheme

/** The screen backdrop sampled by all MeloX liquid controls. */
val LocalMeloXBackdrop = staticCompositionLocalOf<Backdrop?> { null }

/**
 * Official LiquidButton-style glass, generalized so existing MeloX controls
 * keep their exact iOS-derived size, shape and content.
 */
@Composable
fun Modifier.meloXLiquidButton(
    shape: Shape,
    material: MeloXGlassMaterial = MeloXGlassMaterial.Regular,
    enabled: Boolean = true,
    tint: Color = Color.Unspecified,
    surfaceColor: Color = Color.Unspecified,
    blurRadius: Dp? = null,
    lensRadius: Dp? = null,
    refractionHeight: Dp? = null,
): Modifier {
    val baseSpec = MeloXGlassSpec.forMaterial(material)
    return meloXGlassSurface(
        shape = shape,
        material = material,
        enabled = enabled,
        tint = tint,
        surfaceColor = surfaceColor,
        spec = baseSpec.copy(
            blurRadius = blurRadius ?: baseSpec.blurRadius,
            lensRadius = lensRadius ?: baseSpec.lensRadius,
            refractionHeight = refractionHeight ?: baseSpec.refractionHeight,
            // Apple reserves the clear variant for visually rich media.
            // Regular controls use blur/vibrancy without the lens distortion.
            useLens = material != MeloXGlassMaterial.Regular,
        ),
    )
}

/** Shared material entry point for all Native Component-style controls. */
@Composable
fun Modifier.meloXGlassSurface(
    shape: Shape,
    material: MeloXGlassMaterial = MeloXGlassMaterial.Regular,
    enabled: Boolean = true,
    tint: Color = Color.Unspecified,
    surfaceColor: Color = Color.Unspecified,
    spec: MeloXGlassSpec = MeloXGlassSpec.forMaterial(material),
): Modifier {
    val backdrop = LocalMeloXBackdrop.current
    val alphaScale = if (enabled) 1f else 0.48f
    val isPlain = surfaceColor == Color.Transparent && tint == Color.Unspecified
    if (isPlain) return this
    if (backdrop == null) {
        val dark = isMeloXDarkTheme()
        // Keep explicit translucency intact. The previous fallback raised a
        // 5% white tint to 72%, turning every regular glass field into a solid
        // gray slab when no sampled backdrop was available.
        val defaultAlpha = if (dark) 0.84f else 0.88f
        val tintAlphaFloor = if (dark) 0.18f else 0.22f
        val stableSurface = when {
            surfaceColor != Color.Unspecified -> surfaceColor.copy(
                alpha = surfaceColor.alpha * alphaScale,
            )
            tint == Color.Unspecified -> MaterialTheme.colorScheme.surface.copy(alpha = defaultAlpha * alphaScale)
            else -> tint.copy(alpha = maxOf(tint.alpha, tintAlphaFloor) * alphaScale)
        }
        return background(stableSurface, shape)
            .border(
                0.75.dp,
                MaterialTheme.colorScheme.onSurface.copy(alpha = if (dark) 0.12f else 0.10f),
                shape,
            )
    }
    return this
        .drawBackdrop(
            backdrop = backdrop,
            shape = { shape },
            effects = {
                vibrancy()
                blur(spec.blurRadius.toPx())
                if (spec.useLens) {
                    lens(
                        spec.lensRadius.toPx(),
                        spec.refractionHeight.toPx(),
                        chromaticAberration = false,
                    )
                }
            },
            onDrawSurface = {
                if (tint != Color.Unspecified && tint.alpha > 0.001f) {
                    drawRect(tint.copy(alpha = tint.alpha * alphaScale), blendMode = BlendMode.Hue)
                    drawRect(tint.copy(alpha = tint.alpha * 0.75f * alphaScale))
                }
                if (surfaceColor != Color.Unspecified) {
                    drawRect(surfaceColor.copy(alpha = surfaceColor.alpha * alphaScale))
                }
            },
        )
}

/**
 * Plain background blur. Unlike Liquid Glass this applies no lens, refraction
 * or vibrancy; it only blurs the recorded scene and optionally lays a tint.
 */
@Composable
fun Modifier.meloXBackdropBlur(
    shape: Shape,
    blurRadius: Dp = 20.dp,
    surfaceColor: Color = Color.Transparent,
): Modifier {
    val backdrop = LocalMeloXBackdrop.current
    if (backdrop == null) return background(surfaceColor, shape)
    return drawBackdrop(
        backdrop = backdrop,
        shape = { shape },
        effects = { blur(blurRadius.toPx()) },
        highlight = null,
        shadow = null,
        innerShadow = null,
        onDrawSurface = {
            if (surfaceColor != Color.Transparent) drawRect(surfaceColor)
        },
    )
}

/**
 * Standard content-layer material. Apple explicitly separates this from
 * Liquid Glass: lists, settings groups and content cards should provide
 * distinction without becoming another floating functional layer.
 */
@Composable
fun Modifier.meloXContentSurface(
    shape: Shape,
    surfaceColor: Color = Color.Unspecified,
): Modifier {
    val color = if (surfaceColor == Color.Unspecified) {
        MaterialTheme.colorScheme.surface
    } else {
        surfaceColor
    }
    return background(color, shape)
        .border(
            width = 0.75.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f),
            shape = shape,
        )
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
        // Flatten the requested translucent material over the current page
        // color. Raising a dark tint to a fixed 48% made light segmented
        // controls look charcoal instead of iOS's subtle neutral fill.
        val stableSurface = surfaceColor.compositeOver(MaterialTheme.colorScheme.background)
        return background(stableSurface, shape)
            .border(
                0.75.dp,
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.10f),
                shape,
            )
    }
    return drawBackdrop(
        backdrop = backdrop,
        shape = { shape },
        effects = {
            vibrancy()
            blur(8.dp.toPx())
            lens(24.dp.toPx(), 24.dp.toPx(), chromaticAberration = false)
        },
        onDrawSurface = {
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
        // In the upstream LiquidBottomTabs demo all selection refraction,
        // highlight and shadows are multiplied by pressProgress. At rest that
        // progress is zero, so keep the stable selected capsule distortion-free.
        effects = {},
        highlight = null,
        shadow = null,
        innerShadow = null,
        onDrawSurface = { drawRect(tint) },
    )
}
