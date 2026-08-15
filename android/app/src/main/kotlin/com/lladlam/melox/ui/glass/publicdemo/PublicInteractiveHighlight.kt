package com.lladlam.melox.ui.glass.publicdemo

/*
 * Based on the InteractiveHighlight demo shipped with
 * Kyant0/AndroidLiquidGlass (Apache-2.0). Kept as a tiny local adapter so the
 * app can use the demo's press/drag behavior without depending on the demo
 * application module, which is not published as a Maven artifact.
 */

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.input.pointer.AwaitPointerEventScope
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerId
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.changedToUpIgnoreConsumed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.util.fastCoerceIn
import androidx.compose.ui.util.fastFirstOrNull
import com.kyant.backdrop.RuntimeShader
import com.kyant.backdrop.asComposeShader
import com.kyant.backdrop.isRuntimeShaderSupported
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/** Public-demo press highlight with a moving optical focus point. */
class PublicInteractiveHighlight(
    private val animationScope: CoroutineScope,
    private val position: (size: Size, offset: Offset) -> Offset = { _, offset -> offset },
) {
    private val pressSpec = spring<Float>(0.5f, 300f, 0.001f)
    private val positionSpec = spring(0.5f, 300f, Offset.VisibilityThreshold)
    private val pressAnimation = Animatable(0f, 0.001f)
    private val positionAnimation = Animatable(Offset.Zero, Offset.VectorConverter, Offset.VisibilityThreshold)
    private var startPosition = Offset.Zero

    val pressProgress: Float get() = pressAnimation.value
    val offset: Offset get() = positionAnimation.value - startPosition

    private val shader = if (isRuntimeShaderSupported()) {
        RuntimeShader(
            """
uniform float2 size;
layout(color) uniform half4 color;
uniform float radius;
uniform float2 position;
half4 main(float2 coord) {
    float dist = distance(coord, position);
    float intensity = smoothstep(radius, radius * 0.5, dist);
    return color * intensity;
}
""".trimIndent(),
        )
    } else null

    val modifier: Modifier = Modifier.drawWithContent {
        val progress = pressAnimation.value
        if (progress > 0f) {
            if (shader != null) {
                drawRect(Color.White.copy(alpha = 0.08f * progress), blendMode = BlendMode.Plus)
                shader.apply {
                    val focus = position(size, positionAnimation.value)
                    setFloatUniform("size", size.width, size.height)
                    setColorUniform("color", Color.White.copy(alpha = 0.15f * progress))
                    setFloatUniform("radius", size.minDimension * 1.5f)
                    setFloatUniform("position", focus.x.fastCoerceIn(0f, size.width), focus.y.fastCoerceIn(0f, size.height))
                }
                drawRect(ShaderBrush(shader.asComposeShader()), blendMode = BlendMode.Plus)
            } else {
                drawRect(Color.White.copy(alpha = 0.25f * progress), blendMode = BlendMode.Plus)
            }
        }
        drawContent()
    }

    val gestureModifier: Modifier = Modifier.pointerInput(animationScope) {
        publicInspectDragGestures(
            onDragStart = { down ->
                startPosition = down
                animationScope.launch {
                    launch { pressAnimation.animateTo(1f, pressSpec) }
                    launch { positionAnimation.snapTo(startPosition) }
                }
            },
            onDragEnd = {
                animationScope.launch {
                    launch { pressAnimation.animateTo(0f, pressSpec) }
                    launch { positionAnimation.animateTo(startPosition, positionSpec) }
                }
            },
            onDragCancel = {
                animationScope.launch {
                    launch { pressAnimation.animateTo(0f, pressSpec) }
                    launch { positionAnimation.animateTo(startPosition, positionSpec) }
                }
            },
        ) { change, _ ->
            animationScope.launch { positionAnimation.snapTo(change.position) }
        }
    }
}

internal suspend fun PointerInputScope.publicInspectDragGestures(
    onDragStart: (Offset) -> Unit,
    onDragEnd: () -> Unit,
    onDragCancel: () -> Unit,
    onDrag: (PointerInputChange, Offset) -> Unit,
) {
    // This is the catalog helper's key behavior: press starts on pointer
    // down, before Compose's drag-slop threshold is crossed.
    awaitEachGesture {
        val initialDown = awaitFirstDown(false, PointerEventPass.Initial)
        val down = awaitFirstDown(false)
        onDragStart(down.position)
        onDrag(initialDown, Offset.Zero)
        val upEvent = drag(initialDown.id) { change ->
            onDrag(change, change.positionChange())
        }
        if (upEvent == null) onDragCancel() else onDragEnd()
    }
}

private suspend inline fun AwaitPointerEventScope.drag(
    pointerId: PointerId,
    onDrag: (PointerInputChange) -> Unit,
): PointerInputChange? {
    if (currentEvent.changes.fastFirstOrNull { it.id == pointerId }?.pressed != true) return null
    var pointer = pointerId
    while (true) {
        val change = awaitDragOrUp(pointer) ?: return null
        if (change.changedToUpIgnoreConsumed()) return change
        if (change.isConsumed) return null
        onDrag(change)
        pointer = change.id
    }
}

private suspend inline fun AwaitPointerEventScope.awaitDragOrUp(
    pointerId: PointerId,
): PointerInputChange? {
    var pointer = pointerId
    while (true) {
        val event = awaitPointerEvent()
        val change = event.changes.fastFirstOrNull { it.id == pointer } ?: return null
        if (change.changedToUpIgnoreConsumed()) {
            val otherDown = event.changes.fastFirstOrNull { it.pressed }
            if (otherDown == null) return change
            pointer = otherDown.id
        } else if (change.previousPosition != change.position) {
            return change
        }
    }
}
