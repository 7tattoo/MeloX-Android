package com.lladlam.melox.ui.glass

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/** The two Liquid Glass variants Apple exposes for custom components. */
enum class MeloXGlassMaterial {
    Clear,
    Regular,
}

/** Semantic colors used by the iOS Native Components reference. */
object MeloXSystemColors {
    val Blue = Color(0xFF0A84FF)
    val Red = Color(0xFFFF3B30)
    val SecondaryFill = Color(0x26787880)
    val TertiaryFill = Color(0x1F767680)
    val Separator = Color(0x4A3C3C43)
}

enum class MeloXGlassButtonStyle {
    Bordered,
    BorderedProminent,
    Plain,
    Destructive,
}

data class MeloXGlassSpec(
    val blurRadius: Dp,
    val lensRadius: Dp,
    val refractionHeight: Dp,
    /** Regular is the opaque-enough control material; Clear is reserved for rich media. */
    val useLens: Boolean,
) {
    companion object {
        fun forMaterial(material: MeloXGlassMaterial): MeloXGlassSpec = when (material) {
            MeloXGlassMaterial.Clear -> MeloXGlassSpec(4.dp, 14.dp, 20.dp, useLens = true)
            MeloXGlassMaterial.Regular -> MeloXGlassSpec(10.dp, 0.dp, 0.dp, useLens = false)
        }
    }
}
