package com.lladlam.melox.ui.glass

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun MeloXGlassButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: MeloXGlassButtonStyle = MeloXGlassButtonStyle.Bordered,
    material: MeloXGlassMaterial = MeloXGlassMaterial.Regular,
    // Apple’s default glass effect shape is a capsule. Larger cards pass an
    // explicit rounded rectangle when they need a different silhouette.
    shape: Shape = RoundedCornerShape(999.dp),
    tint: Color = Color.Unspecified,
    surfaceColor: Color = Color.Unspecified,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
    content: @Composable RowScope.() -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val buttonTint = when (style) {
        MeloXGlassButtonStyle.Bordered -> tint
        MeloXGlassButtonStyle.BorderedProminent -> MeloXSystemColors.Blue
        MeloXGlassButtonStyle.Plain -> Color.Transparent
        MeloXGlassButtonStyle.Destructive -> MeloXSystemColors.Red
    }
    val buttonSurface = when {
        surfaceColor != Color.Unspecified -> surfaceColor
        style == MeloXGlassButtonStyle.BorderedProminent -> MeloXSystemColors.Blue.copy(alpha = 0.92f)
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
            .graphicsLayer {
                // Match the subtle compression used by Apple’s interactive
                // glass controls without introducing an Android ripple.
                val scale = if (pressed && enabled) 0.965f else 1f
                scaleX = scale
                scaleY = scale
            }
            .meloXGlassSurface(
                shape = shape,
                material = material,
                enabled = enabled,
                tint = buttonTint,
                surfaceColor = buttonSurface,
            )
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
                role = Role.Button,
                onClick = onClick,
            )
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
            color = if (selected) MeloXSystemColors.Blue else MaterialTheme.colorScheme.onSurface,
            variant = if (selected) MeloXSymbolVariant.Fill else MeloXSymbolVariant.Regular,
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
) {
    Row(
        modifier = modifier
            .heightIn(min = 50.dp)
            .meloXGlassSurface(
                shape = RoundedCornerShape(25.dp),
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
            modifier = Modifier.weight(1f),
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
        shape = RoundedCornerShape(999.dp),
        style = if (selected) MeloXGlassButtonStyle.BorderedProminent else MeloXGlassButtonStyle.Bordered,
        tint = if (selected) MeloXSystemColors.Blue.copy(alpha = 0.22f) else Color.Unspecified,
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
    ) {
        Text(
            text = title,
            color = if (selected) MeloXSystemColors.Blue else MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}
