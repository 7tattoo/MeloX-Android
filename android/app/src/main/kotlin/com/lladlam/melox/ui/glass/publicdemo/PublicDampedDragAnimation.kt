package com.lladlam.melox.ui.glass.publicdemo

/* Port of AndroidLiquidGlass' public DampedDragAnimation helper (Apache-2.0). */

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.MutatorMutex
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.time.Clock

class PublicDampedDragAnimation(
    private val animationScope: CoroutineScope,
    initialValue: Float,
    val valueRange: ClosedRange<Float>,
    visibilityThreshold: Float,
    initialScale: Float,
    val pressedScale: Float,
    val onDragStarted: PublicDampedDragAnimation.(position: Offset) -> Unit = {},
    val onDragStopped: PublicDampedDragAnimation.() -> Unit = {},
    val onTap: (position: Offset) -> Unit = {},
    val onDrag: PublicDampedDragAnimation.(size: IntSize, dragAmount: Offset) -> Unit,
) {
    private val valueAnimationSpec = spring(1f, 1000f, visibilityThreshold)
    private val velocityAnimationSpec = spring(0.5f, 300f, visibilityThreshold * 10f)
    private val pressProgressAnimationSpec = spring(1f, 1000f, 0.001f)
    private val scaleXAnimationSpec = spring(0.6f, 250f, 0.001f)
    private val scaleYAnimationSpec = spring(0.7f, 250f, 0.001f)
    private val valueAnimation = Animatable(initialValue, visibilityThreshold)
    private val velocityAnimation = Animatable(0f, 5f)
    private val pressProgressAnimation = Animatable(0f, 0.001f)
    private val scaleXAnimation = Animatable(initialScale, 0.001f)
    private val scaleYAnimation = Animatable(initialScale, 0.001f)
    private val mutatorMutex = MutatorMutex()
    private val velocityTracker = VelocityTracker()
    private var downPosition = Offset.Zero
    private var movedDuringGesture = false

    val value: Float get() = valueAnimation.value
    val targetValue: Float get() = valueAnimation.targetValue
    val pressProgress: Float get() = pressProgressAnimation.value
    val scaleX: Float get() = scaleXAnimation.value
    val scaleY: Float get() = scaleYAnimation.value
    val velocity: Float get() = velocityAnimation.value

    val modifier: Modifier = Modifier.pointerInput(Unit) {
        publicInspectDragGestures(
            onDragStart = { down ->
                downPosition = down
                movedDuringGesture = false
                onDragStarted(down)
                press()
            },
            onDragEnd = {
                if (movedDuringGesture) onDragStopped() else onTap(downPosition)
                release()
            },
            onDragCancel = { onDragStopped(); release() },
        ) { change, dragAmount ->
            if (dragAmount != Offset.Zero) movedDuringGesture = true
            onDrag(size, dragAmount)
            change.consume()
        }
    }

    fun press() {
        velocityTracker.resetTracking()
        animationScope.launch {
            launch { pressProgressAnimation.animateTo(1f, pressProgressAnimationSpec) }
            launch { scaleXAnimation.animateTo(pressedScale, scaleXAnimationSpec) }
            launch { scaleYAnimation.animateTo(pressedScale, scaleYAnimationSpec) }
        }
    }

    fun release() {
        animationScope.launch {
            androidx.compose.runtime.withFrameNanos { }
            if (valueAnimation.value != valueAnimation.targetValue) {
                val threshold = (valueRange.endInclusive - valueRange.start) * 0.025f
                snapshotFlow { valueAnimation.value }
                    .filter { abs(it - valueAnimation.targetValue) < threshold }
                    .first()
            }
            launch { pressProgressAnimation.animateTo(0f, pressProgressAnimationSpec) }
            launch { scaleXAnimation.animateTo(1f, scaleXAnimationSpec) }
            launch { scaleYAnimation.animateTo(1f, scaleYAnimationSpec) }
        }
    }

    fun updateValue(value: Float) {
        val target = value.coerceIn(valueRange)
        animationScope.launch {
            // During an active drag the finger is the source of truth. Starting
            // a spring for every pointer event creates a queue of cancelled
            // animations and makes the dock visibly stutter. The spring is
            // reserved for the release/settle phase.
            valueAnimation.snapTo(target)
            updateVelocity()
        }
    }

    fun animateToValue(value: Float) {
        animationScope.launch {
            mutatorMutex.mutate {
                press()
                launch { valueAnimation.animateTo(value.coerceIn(valueRange), valueAnimationSpec) }
                if (velocity != 0f) launch { velocityAnimation.animateTo(0f, velocityAnimationSpec) }
                release()
            }
        }
    }

    private suspend fun updateVelocity() {
        velocityTracker.addPosition(
            Clock.System.now().toEpochMilliseconds(),
            Offset(valueAnimation.value, 0f),
        )
        val targetVelocity = velocityTracker.calculateVelocity().x /
            (valueRange.endInclusive - valueRange.start)
        velocityAnimation.snapTo(targetVelocity)
    }
}
