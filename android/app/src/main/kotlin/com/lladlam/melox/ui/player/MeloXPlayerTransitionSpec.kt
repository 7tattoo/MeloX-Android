package com.lladlam.melox.ui.player

import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween

// SharedTransition defaults to a spring bounds transform. For an interactive
// seekable transition that makes different children advance by different
// apparent amounts. Use one child timeline and animate only the master
// SeekableTransitionState fraction when the gesture is released.
// Slower than the original 460ms so the morph is readable, but not so
// slow that it feels sluggish.
internal const val MeloXPlayerTransitionDurationMillis = 750

internal val MeloXPlayerLinearBoundsTransform = BoundsTransform { _, _ ->
    tween(
        durationMillis = MeloXPlayerTransitionDurationMillis,
        easing = LinearOutSlowInEasing,
    )
}

internal fun meloXPlayerLinearFloatSpec() = tween<Float>(
    durationMillis = MeloXPlayerTransitionDurationMillis,
    easing = LinearOutSlowInEasing,
)

internal fun playerAutomaticFractionSpec() = tween<Float>(
    durationMillis = MeloXPlayerTransitionDurationMillis,
    easing = LinearOutSlowInEasing,
)

internal fun playerGestureSettleSpec() = spring<Float>(
    dampingRatio = 1.0f,
    stiffness = 200f,
    visibilityThreshold = 0.001f,
)
