package com.lladlam.melox.ui.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class MeloXWindowWidthClass { Compact, Medium, Expanded }

data class MeloXWindowInfo(
    val widthClass: MeloXWindowWidthClass,
    val isLandscape: Boolean,
    val gutter: Dp,
    val maxContentWidth: Dp,
    val gridColumns: Int,
) {
    val supportsTwoPane: Boolean get() = widthClass != MeloXWindowWidthClass.Compact
}

/** One shared window policy for phone, foldable and tablet page layouts. */
@Composable
fun rememberMeloXWindowInfo(): MeloXWindowInfo {
    val configuration = LocalConfiguration.current
    val width = configuration.screenWidthDp
    val height = configuration.screenHeightDp
    val widthClass = when {
        width >= 840 -> MeloXWindowWidthClass.Expanded
        width >= 600 -> MeloXWindowWidthClass.Medium
        else -> MeloXWindowWidthClass.Compact
    }
    return when (widthClass) {
        MeloXWindowWidthClass.Compact -> MeloXWindowInfo(widthClass, width > height, 20.dp, 600.dp, 2)
        MeloXWindowWidthClass.Medium -> MeloXWindowInfo(widthClass, width > height, 28.dp, 920.dp, 3)
        MeloXWindowWidthClass.Expanded -> MeloXWindowInfo(widthClass, width > height, 36.dp, 1180.dp, 4)
    }
}
