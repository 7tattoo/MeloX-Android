package com.lladlam.melox.ui.glass

import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.ui.draw.clip
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.geometry.Offset
import com.lladlam.melox.ui.glass.publicdemo.PublicInteractiveHighlight
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.util.lerp
import com.kyant.backdrop.backdrops.layerBackdrop
import com.kyant.backdrop.backdrops.rememberCombinedBackdrop
import com.kyant.backdrop.backdrops.rememberLayerBackdrop
import com.kyant.backdrop.drawBackdrop
import com.kyant.backdrop.effects.blur
import com.kyant.backdrop.effects.lens
import com.kyant.backdrop.highlight.Highlight
import com.kyant.backdrop.shadow.InnerShadow
import com.kyant.backdrop.shadow.Shadow
import com.lladlam.melox.ui.theme.isMeloXDarkTheme

@Composable
fun MeloXGlassButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: MeloXGlassButtonStyle = MeloXGlassButtonStyle.Bordered,
    material: MeloXGlassMaterial = MeloXGlassMaterial.Regular,
    // Apple’s default glass effect shape is a capsule. Larger cards pass an
    // explicit rounded rectangle when they need a different silhouette.
    shape: Shape = MeloXShapes.capsule,
    tint: Color = Color.Unspecified,
    surfaceColor: Color = Color.Unspecified,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
    content: @Composable RowScope.() -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val animationScope = rememberCoroutineScope()
    val interactiveHighlight = remember(animationScope) {
        PublicInteractiveHighlight(animationScope)
    }
    val buttonTint = when (style) {
        MeloXGlassButtonStyle.Bordered -> tint
        MeloXGlassButtonStyle.BorderedProminent -> MeloXSystemColors.Red
        MeloXGlassButtonStyle.Plain -> Color.Transparent
        MeloXGlassButtonStyle.Destructive -> MeloXSystemColors.Red
    }
    val buttonSurface = when {
        surfaceColor != Color.Unspecified -> surfaceColor
        style == MeloXGlassButtonStyle.BorderedProminent -> MeloXSystemColors.Red.copy(alpha = 0.92f)
        style == MeloXGlassButtonStyle.Destructive -> MeloXSystemColors.Red.copy(alpha = 0.12f)
        style == MeloXGlassButtonStyle.Plain -> Color.Transparent
        else -> Color.Unspecified
    }
    val contentColor = when (style) {
        MeloXGlassButtonStyle.BorderedProminent -> Color.White
        MeloXGlassButtonStyle.Destructive -> MeloXSystemColors.Red
        else -> MaterialTheme.colorScheme.onSurface
    }
    Row(
        modifier = modifier
            .meloXGlassSurface(
                shape = shape,
                material = material,
                enabled = enabled,
                tint = buttonTint,
                surfaceColor = buttonSurface,
                pressProgress = if (enabled) interactiveHighlight.pressProgress else 0f,
                dragOffset = if (enabled) interactiveHighlight.offset else Offset.Zero,
            )
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
                role = Role.Button,
                onClick = onClick,
            )
            .then(if (enabled) interactiveHighlight.modifier else Modifier)
            .then(if (enabled) interactiveHighlight.gestureModifier else Modifier)
            .padding(contentPadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor) {
            content()
        }
    }
}

@Composable
fun MeloXGlassIconButton(
    symbol: MeloXSymbol,
    onClick: () -> Unit,
    modifier: Modifier = Modifier.size(44.dp),
    enabled: Boolean = true,
    selected: Boolean = false,
    contentDescription: String? = null,
) {
    MeloXGlassButton(
        onClick = onClick,
        enabled = enabled,
        shape = CircleShape,
        contentPadding = PaddingValues(10.dp),
        modifier = if (contentDescription == null) {
            modifier
        } else {
            modifier.semantics {
                this.contentDescription = contentDescription
            }
        },
    ) {
        MeloXSymbolIcon(
            symbol = symbol,
            modifier = Modifier.size(24.dp),
            color = if (selected) MeloXSystemColors.Red else MaterialTheme.colorScheme.onSurface,
            variant = if (selected) MeloXSymbolVariant.Fill else MeloXSymbolVariant.Regular,
        )
    }
}

/** Compact iOS-style switch used by settings and provider controls. */
@Composable
fun MeloXGlassToggle(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
) {
    val dark = isMeloXDarkTheme()
    val trackBackdrop = rememberLayerBackdrop()
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val pressProgress by animateFloatAsState(
        targetValue = if (pressed && enabled) 1f else 0f,
        animationSpec = spring(dampingRatio = 0.5f, stiffness = 300f),
        label = "melox-glass-toggle-press",
    )
    val fraction by animateFloatAsState(
        targetValue = if (checked) 1f else 0f,
        animationSpec = spring(dampingRatio = 0.78f, stiffness = 520f),
        label = "melox-glass-toggle-value",
    )
    val trackColor = when {
        !enabled -> MaterialTheme.colorScheme.onSurface.copy(alpha = if (dark) 0.18f else 0.16f)
        checked -> Color(0xFF34C759)
        else -> Color(0xFF787878).copy(alpha = if (dark) 0.36f else 0.20f)
    }
    val pageBackdrop = LocalMeloXBackdrop.current
    Box(
        modifier = modifier
            .width(64.dp)
            .height(28.dp)
            .clip(MeloXShapes.capsule)
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
                role = Role.Switch,
                onClick = { onCheckedChange(!checked) },
            )
            .padding(0.dp),
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .layerBackdrop(trackBackdrop)
                .background(trackColor, MeloXShapes.capsule),
        )
        Box(
            Modifier
                .offset(x = 2.dp + 20.dp * fraction, y = 2.dp)
                .size(width = 40.dp, height = 24.dp)
                .then(
                    if (pageBackdrop != null) {
                        Modifier.drawBackdrop(
                            backdrop = rememberCombinedBackdrop(pageBackdrop, trackBackdrop),
                            shape = { MeloXShapes.capsule },
                            effects = {
                                blur(8.dp.toPx() * (1f - pressProgress))
                                lens(
                                    5.dp.toPx() * pressProgress,
                                    10.dp.toPx() * pressProgress,
                                    chromaticAberration = true,
                                )
                            },
                            highlight = {
                                Highlight.Ambient.copy(alpha = pressProgress)
                            },
                            shadow = {
                                Shadow(radius = 4.dp, color = Color.Black.copy(alpha = 0.05f))
                            },
                            innerShadow = {
                                InnerShadow(radius = 4.dp * pressProgress, alpha = pressProgress)
                            },
                            layerBlock = {
                                val scale = lerp(1f, 1.5f, pressProgress)
                                scaleX = scale
                                scaleY = lerp(1f, 0.92f, pressProgress)
                                alpha = if (enabled) 1f else 0.45f
                            },
                            onDrawSurface = {
                                drawRect(Color.White.copy(alpha = 1f - pressProgress), blendMode = BlendMode.Screen)
                            },
                        )
                    } else {
                        Modifier
                            .background(Color.White, MeloXShapes.capsule)
                            .graphicsLayer { alpha = if (enabled) 1f else 0.45f }
                    },
                ),
        )
    }
}

@Composable
fun MeloXGlassTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: @Composable (() -> Unit)? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    textStyle: TextStyle = TextStyle.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onFocusChanged: ((Boolean) -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .heightIn(min = 50.dp)
            .meloXGlassSurface(
                shape = MeloXShapes.capsule,
                material = MeloXGlassMaterial.Regular,
                enabled = enabled,
                surfaceColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.055f),
            )
            .padding(horizontal = 14.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        leadingContent?.invoke()
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            singleLine = singleLine,
            textStyle = textStyle,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            modifier = Modifier
                .weight(1f)
                .onFocusChanged { onFocusChanged?.invoke(it.isFocused) },
            decorationBox = { innerTextField ->
                Box(contentAlignment = Alignment.CenterStart) {
                    if (value.isBlank()) placeholder?.invoke()
                    innerTextField()
                }
            },
        )
        trailingContent?.invoke()
    }
}

@Composable
fun MeloXGlassToolbarButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
) {
    MeloXGlassButton(
        onClick = onClick,
        modifier = modifier,
        shape = MeloXShapes.capsule,
        style = if (selected) MeloXGlassButtonStyle.BorderedProminent else MeloXGlassButtonStyle.Bordered,
                tint = if (selected) MeloXSystemColors.Red.copy(alpha = 0.22f) else Color.Unspecified,
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
    ) {
        Text(
            text = title,
            color = if (selected) MeloXSystemColors.Red else MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

/** A reusable iOS grouped-content surface for cards and settings sections. */
@Composable
fun MeloXGlassCard(
    modifier: Modifier = Modifier,
    shape: Shape = MeloXShapes.card,
    material: MeloXGlassMaterial = MeloXGlassMaterial.Regular,
    surfaceColor: Color = Color.Unspecified,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    androidx.compose.foundation.layout.Column(
        modifier = modifier
            .meloXGlassSurface(
                shape = shape,
                material = material,
                surfaceColor = surfaceColor,
            )
            .then(
                if (onClick != null) Modifier.clickable(
                    interactionSource = null,
                    indication = null,
                    role = Role.Button,
                    onClick = onClick,
                ) else Modifier,
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = content,
    )
}

/** iOS segmented/capsule selection control built on the same glass primitive. */
@Composable
fun MeloXGlassSegmentedControl(
    items: List<String>,
    selectedIndex: Int,
    onSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .meloXGlassSurface(
                shape = MeloXShapes.capsule,
                material = MeloXGlassMaterial.Regular,
                surfaceColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.055f),
            )
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items.forEachIndexed { index, item ->
            MeloXGlassButton(
                onClick = { onSelected(index) },
                modifier = Modifier.weight(1f),
                style = if (index == selectedIndex) MeloXGlassButtonStyle.BorderedProminent
                else MeloXGlassButtonStyle.Plain,
                shape = MeloXShapes.capsule,
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
            ) {
                Text(
                    text = item,
                    style = MeloXTypography.subheadline,
                    maxLines = 1,
                )
            }
        }
    }
}
