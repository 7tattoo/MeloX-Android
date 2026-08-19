package com.lladlam.melox.ui.player

import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween

// SharedTransition defaults to a spring bounds transform. For an interactive
// seekable transition that makes different children advance by different
// apparent amounts. Use one child timeline and animate only the master
// SeekableTransitionState fraction when the gesture is released.
// Slow enough that the mini→full morph is readable and the reverse
// collapse does not feel like a snap.
internal const val MeloXPlayerTransitionDurationMillis = 1200

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

internal fun playerAutomaticFractionSpec() = tween<Float>(
    durationMillis = MeloXPlayerTransitionDurationMillis,
    easing = FastOutSlowInEasing,
)

internal fun playerGestureSettleSpec() = spring<Float>(
    dampingRatio = 1.0f,
    stiffness = 200f,
    visibilityThreshold = 0.001f,
)
