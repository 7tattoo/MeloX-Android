package com.lladlam.melox.ui.player

import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween

// SharedTransition defaults to a spring bounds transform. For an interactive
// seekable transition that makes different children advance by different
// apparent amounts. Use one child timeline and animate only the master
// SeekableTransitionState fraction when the gesture is released.
internal const val MeloXPlayerTransitionDurationMillis = 460

internal val MeloXPlayerLinearBoundsTransform = BoundsTransform { _, _ ->
    tween(
        durationMillis = MeloXPlayerTransitionDurationMillis,
        easing = FastOutSlowInEasing,
    )
}

internal fun meloXPlayerLinearFloatSpec() = tween<Float>(
    durationMillis = MeloXPlayerTransitionDurationMillis,
    easing = FastOutSlowInEasing,
)
