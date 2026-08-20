package com.lladlam.melox.ui.glass

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.zIndex
import com.lladlam.melox.ui.glass.publicdemo.PublicInteractiveHighlight
import com.lladlam.melox.ui.settings.MeloXSettingsPreferences

/** Generic dropdown menu for settings, matching Mei's IosPopupButton + IosPopupMenu style. */
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
    MeloXPopupDropdown(
        expanded = expanded,
        onExpandedChange = { if (enabled || !it) expanded = it },
        itemCount = items.size,
        modifier = modifier,
        anchor = { openMenu ->
            Row(
                Modifier
                    .clickable(
                        enabled = enabled,
                        interactionSource = null,
                        indication = null,
                        role = Role.Button,
                        onClick = openMenu,
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = title,
                    style = MeloXTypography.body,
                    color = if (enabled) MeloXSystemColors.Red
                    else MaterialTheme.colorScheme.onSurface.copy(alpha = .5f),
                )
                MeloXSymbolIcon(
                    symbol = MeloXSymbol.ChevronRight,
                    modifier = Modifier.size(15.dp).padding(start = 4.dp),
                    color = if (enabled) MeloXSystemColors.Red
                    else MaterialTheme.colorScheme.onSurface.copy(alpha = .5f),
                )
            }
        },
    ) { close ->
        items.forEach { (item, label) ->
            MeloXPopupMenuItem(
                title = label,
                onClick = { onSelected(item); close() },
                checked = item == selected,
            )
        }
    }
}

/** Convenience overload that reads/writes a string preference key. */
@Composable
fun MeloXSettingsDropdown(
    context: android.content.Context,
    title: String,
    prefKey: String,
    default: String,
    items: List<Pair<String, String>>,
) {
    var selected by remember { mutableStateOf(MeloXSettingsPreferences.string(context, prefKey, default)) }
    MeloXSettingsDropdown(
        title = title,
        selected = selected,
        items = items,
        onSelected = { selected = it; MeloXSettingsPreferences.setString(context, prefKey, it) },
    )
}

// ---------- Low-level popup components (ported from Mei Ios27Components) ----------

@Composable
private fun MeloXPopupDropdown(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    itemCount: Int,
    modifier: Modifier = Modifier,
    anchor: @Composable (onClick: () -> Unit) -> Unit,
    content: @Composable androidx.compose.foundation.layout.ColumnScope.(close: () -> Unit) -> Unit,
) {
    val progress = remember { Animatable(if (expanded) 1f else 0f) }
    var popupAlive by remember { mutableStateOf(expanded) }

    LaunchedEffect(expanded) {
        if (expanded) {
            popupAlive = true
            progress.animateTo(
                targetValue = 1f,
                animationSpec = spring(dampingRatio = 0.72f, stiffness = 260f, visibilityThreshold = 0.001f),
            )
        } else {
            progress.animateTo(
                targetValue = 0f,
                animationSpec = spring(dampingRatio = 0.74f, stiffness = 280f, visibilityThreshold = 0.001f),
            )
            popupAlive = false
        }
    }

    Box(modifier) {
        Box(Modifier.alpha(if (expanded) 0f else 1f)) {
            anchor { onExpandedChange(!expanded) }
        }
        if (popupAlive) {
            Popup(
                onDismissRequest = { onExpandedChange(false) },
                properties = PopupProperties(
                    focusable = expanded,
                    dismissOnBackPress = expanded,
                    dismissOnClickOutside = expanded,
                ),
            ) {
                Box(
                    Modifier.clickable(
                        interactionSource = null,
                        indication = null,
                    ) { onExpandedChange(false) },
                ) {
                    MeloXContextMenuShell(
                        visible = true,
                        animationProgress = progress.value,
                        itemCount = itemCount,
                    ) {
                        content(it)
                    }
                }
            }
        }
    }
}

@Composable
private fun MeloXContextMenuShell(
    visible: Boolean,
    animationProgress: Float,
    itemCount: Int,
    content: @Composable androidx.compose.foundation.layout.ColumnScope.(close: () -> Unit) -> Unit,
) {
    val pressScale = 1f + 0.04f * animationProgress
    val menuWidth = 240.dp
    val menuHeightDp = 16.dp + 44.dp * itemCount

    Column(
        Modifier
            .width(menuWidth)
            .height(menuHeightDp)
            .graphicsLayer {
                scaleX = pressScale
                scaleY = pressScale
            }
            .alpha(animationProgress.coerceIn(0f, 1f))
            .zIndex(1f)
            .meloXGlassSurface(
                shape = MeloXShapes.card,
                material = MeloXGlassMaterial.Regular,
            )
            .padding(vertical = 8.dp, horizontal = 12.dp),
    ) {
        content { /* close - handled by popup dismiss */ }
    }
}

@Composable
private fun MeloXPopupMenuItem(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    checked: Boolean = false,
) {
    val scope = rememberCoroutineScope()
    val highlight = remember(scope) { PublicInteractiveHighlight(scope) }

    Row(
        modifier
            .fillMaxWidth()
            .height(44.dp)
            .background(
                color = androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.15f * highlight.pressProgress),
                shape = MeloXShapes.capsule,
            )
            .then(
                Modifier
                    .clickable(interactionSource = null, indication = null, onClick = onClick)
                    .then(highlight.gestureModifier),
            )
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.width(24.dp), contentAlignment = Alignment.Center) {
            if (checked) {
                MeloXSymbolIcon(
                    symbol = MeloXSymbol.Check,
                    modifier = Modifier.size(20.dp),
                    color = MeloXSystemColors.Red,
                )
            }
        }
        Text(
            text = title,
            style = MeloXTypography.body,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f).padding(start = 8.dp),
        )
    }
}
