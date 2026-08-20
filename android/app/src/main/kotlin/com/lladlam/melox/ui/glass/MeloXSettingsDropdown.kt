package com.lladlam.melox.ui.glass

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp as lerpDp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.PopupProperties
import com.kyant.backdrop.Backdrop
import com.kyant.backdrop.backdrops.LayerBackdrop
import com.kyant.backdrop.backdrops.rememberLayerBackdrop
import com.kyant.backdrop.drawBackdrop
import com.kyant.backdrop.effects.blur
import com.kyant.backdrop.effects.lens
import com.kyant.backdrop.effects.vibrancy
import com.kyant.backdrop.highlight.Highlight
import com.kyant.backdrop.shadow.InnerShadow
import com.kyant.backdrop.shadow.Shadow
import com.kyant.capsule.ContinuousRoundedRectangle
import com.lladlam.melox.ui.glass.publicdemo.PublicInteractiveHighlight
import com.lladlam.melox.ui.settings.MeloXSettingsPreferences
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sin

private class MeloXPopupPositionProvider(
    private val targetMenuHeightPx: Int,
    private val onDirectionResolved: (Boolean) -> Unit,
) : PopupPositionProvider {
    private var opensAbove: Boolean? = null

    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize,
    ): IntOffset {
        val above = opensAbove ?: run {
            val roomBelow = windowSize.height - anchorBounds.bottom
            val roomAbove = anchorBounds.top
            (roomBelow < targetMenuHeightPx && roomAbove > roomBelow).also {
                opensAbove = it
                onDirectionResolved(it)
            }
        }
        val x = (anchorBounds.right - popupContentSize.width)
            .coerceIn(0, (windowSize.width - popupContentSize.width).coerceAtLeast(0))
        val y = if (above) anchorBounds.bottom - popupContentSize.height else anchorBounds.top
        return IntOffset(x, y.coerceIn(0, (windowSize.height - popupContentSize.height).coerceAtLeast(0)))
    }
}

/** Exact Mei-style anchored Popup hierarchy for settings choices. */
@Composable
fun <T> MeloXSettingsDropdown(
    title: String,
    selected: T,
    items: List<Pair<T, String>>,
    onSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    var expanded by remember { mutableStateOf(false) }
    var popupAlive by remember { mutableStateOf(false) }
    var anchorSize by remember { mutableStateOf(IntSize.Zero) }
    var opensAbove by remember { mutableStateOf(false) }
    val progress = remember { Animatable(0f) }
    val backdrop = LocalMeloXBackdrop.current

    LaunchedEffect(expanded) {
        if (expanded) {
            popupAlive = true
            progress.animateTo(1f, spring(dampingRatio = 0.72f, stiffness = 260f, visibilityThreshold = 0.001f))
        } else {
            progress.animateTo(0f, spring(dampingRatio = 0.74f, stiffness = 280f, visibilityThreshold = 0.001f))
            popupAlive = false
        }
    }

    Box(modifier.onSizeChanged { anchorSize = it }) {
        SettingsDropdownAnchor(
            title = title,
            value = items.firstOrNull { it.first == selected }?.second.orEmpty(),
            enabled = enabled,
            onClick = { expanded = !expanded },
        )
        if (popupAlive && anchorSize != IntSize.Zero) {
            val density = androidx.compose.ui.platform.LocalDensity.current
            val menuHeightPx = with(density) { (20.dp + 44.dp * items.size).roundToPx() }
            val positionProvider = remember(anchorSize, menuHeightPx) {
                MeloXPopupPositionProvider(menuHeightPx) { opensAbove = it }
            }
            Popup(
                popupPositionProvider = positionProvider,
                onDismissRequest = { expanded = false },
                properties = PopupProperties(focusable = expanded, dismissOnBackPress = expanded, dismissOnClickOutside = expanded),
            ) {
                MeloXSettingsContextMenu(
                    backdrop = backdrop,
                    progress = progress.value,
                    velocity = progress.velocity,
                    interactive = expanded,
                    opensAbove = opensAbove,
                    collapsedSize = anchorSize,
                    itemCount = items.size,
                ) {
                    items.forEach { (item, label) ->
                        MeloXSettingsMenuItem(
                            title = label,
                            checked = item == selected,
                            enabled = expanded,
                            onClick = { onSelected(item); expanded = false },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SettingsDropdownAnchor(title: String, value: String, enabled: Boolean, onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(56.dp)
            .meloXContentSurface(MeloXShapes.largeCard, MaterialTheme.colorScheme.surface)
            .clickable(enabled = enabled, interactionSource = null, indication = null, role = Role.Button, onClick = onClick)
            .padding(horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(title, style = MeloXTypography.body, modifier = Modifier.weight(1f))
        Text(value, style = MeloXTypography.body, color = MeloXSystemColors.Red)
        MeloXSymbolIcon(MeloXSymbol.ChevronUpDown, Modifier.size(16.dp).padding(start = 7.dp), MaterialTheme.colorScheme.onSurface.copy(alpha = .4f))
    }
}

@Composable
private fun MeloXSettingsContextMenu(
    backdrop: Backdrop?,
    progress: Float,
    velocity: Float,
    interactive: Boolean,
    opensAbove: Boolean,
    collapsedSize: IntSize,
    itemCount: Int,
    content: @Composable ColumnScope.() -> Unit,
) {
    val scope = rememberCoroutineScope()
    val highlight = remember(scope) { PublicInteractiveHighlight(scope) }
    val childBackdrop = rememberLayerBackdrop()
    val density = androidx.compose.ui.platform.LocalDensity.current
    val fraction = progress.coerceIn(0f, 1f)
    val pulse = max(sin(Math.PI.toFloat() * fraction), abs(velocity / 18f).coerceIn(0f, 1f) * .65f)
    val collapsedWidth = with(density) { collapsedSize.width.toDp() }
    val collapsedHeight = with(density) { collapsedSize.height.toDp() }
    val menuWidth = 238.dp
    val menuHeight = 20.dp + 44.dp * itemCount
    val width = lerpDp(collapsedWidth, menuWidth, progress.coerceIn(-.04f, 1.06f))
    val height = lerpDp(collapsedHeight, menuHeight, progress.coerceIn(-.04f, 1.06f))
    val shape = ContinuousRoundedRectangle(34.dp)
    val menuSurface = MaterialTheme.colorScheme.surface
    val fallbackSurface = MaterialTheme.colorScheme.surface

    Column(
        Modifier
            .width(menuWidth * 1.15f)
            .height(menuHeight * 1.15f)
            .padding(top = if (opensAbove) 32.dp else 0.dp, bottom = if (opensAbove) 0.dp else 32.dp, start = 64.dp)
            .then(if (interactive) highlight.modifier else Modifier)
            .then(if (interactive) highlight.gestureModifier else Modifier)
            .clip(shape)
            .padding(10.dp)
            .width(width)
            .height(height)
            .blur(10.dp * (1f - fraction), BlurredEdgeTreatment.Unbounded)
            .graphicsLayer {
                alpha = 1f
                transformOrigin = TransformOrigin(1f, if (opensAbove) 1f else 0f)
            }
            .then(
                if (backdrop != null) Modifier.drawBackdrop(
                    backdrop = backdrop,
                    exportedBackdrop = childBackdrop,
                    shape = { shape },
                    effects = {
                        vibrancy()
                        blur(androidx.compose.ui.util.lerp(3.dp.toPx(), 16.dp.toPx(), fraction))
                        lens(
                            refractionHeight = androidx.compose.ui.util.lerp(10.dp.toPx(), 18.dp.toPx(), fraction) + 2.dp.toPx() * pulse,
                            refractionAmount = androidx.compose.ui.util.lerp(16.dp.toPx(), 26.dp.toPx(), fraction) + 4.dp.toPx() * pulse,
                            depthEffect = pulse > .01f,
                            chromaticAberration = true,
                        )
                    },
                    highlight = { Highlight.Default.copy(alpha = fraction * (.46f + .18f * pulse)) },
                    shadow = { Shadow(radius = 15.dp, alpha = .10f) },
                    innerShadow = { InnerShadow(radius = 8.dp, alpha = .10f * fraction) },
                    onDrawSurface = { drawRect(menuSurface) },
                ) else Modifier.meloXContentSurface(shape, fallbackSurface)
            )
            .then(if (interactive) highlight.modifier else Modifier)
            .then(if (interactive) highlight.gestureModifier else Modifier)
            .clip(shape)
            .padding(10.dp)
            .graphicsLayer {
                val contentScale = .92f + .08f * fraction
                scaleX = contentScale
                scaleY = contentScale
                transformOrigin = TransformOrigin(1f, if (opensAbove) 1f else 0f)
            },
        content = content,
    )
}

@Composable
private fun MeloXSettingsMenuItem(title: String, checked: Boolean, enabled: Boolean, onClick: () -> Unit) {
    val scope = rememberCoroutineScope()
    val highlight = remember(scope) { PublicInteractiveHighlight(scope) }
    Row(
        Modifier
            .fillMaxWidth()
            .height(44.dp)
            .background(Color.Black.copy(alpha = .15f * highlight.pressProgress), MeloXShapes.capsule)
            .clickable(enabled = enabled, interactionSource = null, indication = null, onClick = onClick)
            .then(if (enabled) highlight.modifier else Modifier)
            .then(if (enabled) highlight.gestureModifier else Modifier)
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.width(24.dp), contentAlignment = Alignment.Center) {
            if (checked) MeloXSymbolIcon(MeloXSymbol.Check, Modifier.size(17.dp), MeloXSystemColors.Red)
        }
        Text(title, style = MeloXTypography.body, modifier = Modifier.weight(1f).padding(start = 8.dp))
    }
}

@Composable
fun MeloXSettingsDropdown(
    context: android.content.Context,
    title: String,
    prefKey: String,
    default: String,
    items: List<Pair<String, String>>,
) {
    var selected by remember { mutableStateOf(MeloXSettingsPreferences.string(context, prefKey, default)) }
    MeloXSettingsDropdown(title, selected, items, { selected = it; MeloXSettingsPreferences.setString(context, prefKey, it) })
}
