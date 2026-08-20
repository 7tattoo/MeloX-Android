package com.lladlam.melox.ui.glass

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.lladlam.melox.ui.settings.MeloXSettingsPreferences

/**
 * Glass-styled dropdown for settings pages.
 *
 * Layout matches Mei's IosPopupButton: a single row with title on the left
 * and the current value + chevron on the right. Tapping opens a spring-animated
 * DropdownMenu with glass surface styling.
 */
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
    val progress = remember { Animatable(if (expanded) 1f else 0f) }

    LaunchedEffect(expanded) {
        progress.animateTo(
            targetValue = if (expanded) 1f else 0f,
            animationSpec = spring(
                dampingRatio = if (expanded) 0.72f else 0.74f,
                stiffness = if (expanded) 260f else 280f,
            ),
        )
    }

    val menuBg = MaterialTheme.colorScheme.surface

    Column(modifier) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clickable(
                    enabled = enabled,
                    interactionSource = null,
                    indication = null,
                    role = Role.Button,
                    onClick = { expanded = !expanded },
                )
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                style = MeloXTypography.body,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = items.firstOrNull { it.first == selected }?.second.orEmpty(),
                style = MeloXTypography.body,
                color = if (enabled) MeloXSystemColors.Red
                else MaterialTheme.colorScheme.onSurface.copy(alpha = .5f),
            )
            MeloXSymbolIcon(
                symbol = MeloXSymbol.ChevronRight,
                modifier = Modifier.size(14.dp).padding(start = 6.dp),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = .35f),
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(220.dp)
                .graphicsLayer {
                    scaleX = progress.value.coerceIn(0.85f, 1f)
                    scaleY = progress.value.coerceIn(0.85f, 1f)
                    alpha = progress.value.coerceIn(0f, 1f)
                },
        ) {
            items.forEach { (item, label) ->
                DropdownMenuItem(
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (item == selected) {
                                MeloXSymbolIcon(
                                    symbol = MeloXSymbol.Check,
                                    modifier = Modifier.size(18.dp).padding(end = 10.dp),
                                    color = MeloXSystemColors.Red,
                                )
                            }
                            Text(
                                text = label,
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                        }
                    },
                    onClick = {
                        onSelected(item)
                        expanded = false
                    },
                )
            }
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
