package com.lladlam.melox.ui.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MeloXGlassSlider(
    title: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    modifier: Modifier = Modifier,
    steps: Int = 0,
    valueLabel: (Float) -> String = { "%.0f".format(it) },
) {
    Column(modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(title, style = MeloXTypography.subheadline)
            Text(valueLabel(value), style = MeloXTypography.subheadline, color = MaterialTheme.colorScheme.onSurface.copy(alpha = .55f))
        }
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
            steps = steps,
            modifier = Modifier.fillMaxWidth().semantics { contentDescription = "$title，${valueLabel(value)}" },
        )
    }
}

@Composable
fun MeloXGlassStepper(
    title: String,
    value: Int,
    onValueChange: (Int) -> Unit,
    range: IntRange,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth().heightIn(min = 52.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(title, modifier = Modifier.weight(1f), style = MeloXTypography.body)
        Text(value.toString(), modifier = Modifier.width(38.dp), color = MaterialTheme.colorScheme.onSurface.copy(alpha = .58f))
        MeloXGlassIconButton(
            symbol = MeloXSymbol.Xmark,
            enabled = value > range.first,
            contentDescription = "减少$title",
            onClick = { onValueChange((value - 1).coerceIn(range)) },
        )
        Spacer(Modifier.width(6.dp))
        MeloXGlassIconButton(
            symbol = MeloXSymbol.Plus,
            enabled = value < range.last,
            contentDescription = "增加$title",
            onClick = { onValueChange((value + 1).coerceIn(range)) },
        )
    }
}

@Composable
fun MeloXBottomSearchToolbar(
    value: String,
    onValueChange: (String) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "搜索",
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .meloXGlassSurface(MeloXShapes.capsule, material = MeloXGlassMaterial.Regular)
            .padding(start = 14.dp, end = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MeloXSymbolIcon(MeloXSymbol.Search, Modifier.size(22.dp), MaterialTheme.colorScheme.onSurface.copy(alpha = .56f))
        MeloXGlassTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f),
            placeholder = { Text(placeholder, color = MaterialTheme.colorScheme.onSurface.copy(alpha = .42f)) },
        )
        MeloXGlassIconButton(MeloXSymbol.Xmark, onClose, contentDescription = "关闭搜索")
    }
}

data class MeloXContextAction(
    val title: String,
    val symbol: MeloXSymbol,
    val destructive: Boolean = false,
    val onClick: () -> Unit,
)

@Composable
fun MeloXGlassContextMenu(
    expanded: Boolean,
    onDismiss: () -> Unit,
    actions: List<MeloXContextAction>,
    anchor: @Composable () -> Unit,
) {
    Box {
        anchor()
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismiss,
            modifier = Modifier.background(MaterialTheme.colorScheme.surface, MeloXShapes.compact),
        ) {
            actions.forEach { action ->
                DropdownMenuItem(
                    text = { Text(action.title, color = if (action.destructive) MeloXSystemColors.Red else MaterialTheme.colorScheme.onSurface) },
                    leadingIcon = { MeloXSymbolIcon(action.symbol, Modifier.size(21.dp), if (action.destructive) MeloXSystemColors.Red else MaterialTheme.colorScheme.onSurface) },
                    onClick = { onDismiss(); action.onClick() },
                )
            }
        }
    }
}

@Composable
fun MeloXGlassActionSheet(
    visible: Boolean,
    title: String,
    actions: List<MeloXContextAction>,
    onDismiss: () -> Unit,
) {
    MeloXGlassSheet(visible = visible, onDismiss = onDismiss) {
        Text(title, modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp), style = MeloXTypography.title2)
        actions.forEach { action ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable(role = Role.Button) { onDismiss(); action.onClick() }
                    .padding(horizontal = 18.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                MeloXSymbolIcon(action.symbol, Modifier.size(23.dp), if (action.destructive) MeloXSystemColors.Red else MaterialTheme.colorScheme.onSurface)
                Text(action.title, modifier = Modifier.padding(start = 12.dp), color = if (action.destructive) MeloXSystemColors.Red else MaterialTheme.colorScheme.onSurface)
            }
        }
    }
}

@Composable
fun MeloXGlassAlert(
    visible: Boolean,
    title: String,
    message: String,
    confirmTitle: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    fieldValue: String? = null,
    onFieldValueChange: ((String) -> Unit)? = null,
) {
    MeloXGlassDialog(visible = visible, onDismiss = onDismiss) {
        Text(title, style = MeloXTypography.title2, fontWeight = FontWeight.Bold)
        Text(message, modifier = Modifier.padding(top = 6.dp), color = MaterialTheme.colorScheme.onSurface.copy(alpha = .58f))
        if (fieldValue != null && onFieldValueChange != null) {
            MeloXGlassTextField(fieldValue, onFieldValueChange, Modifier.fillMaxWidth().padding(top = 14.dp))
        }
        Row(Modifier.fillMaxWidth().padding(top = 16.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            MeloXGlassButton(onDismiss, Modifier.weight(1f), contentPadding = PaddingValues(vertical = 11.dp)) { Text("取消") }
            MeloXGlassButton(onConfirm, Modifier.weight(1f), style = MeloXGlassButtonStyle.BorderedProminent, contentPadding = PaddingValues(vertical = 11.dp)) { Text(confirmTitle) }
        }
    }
}

@Composable
fun MeloXGlassModalSurface(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier
            .fillMaxWidth()
            .meloXGlassSurface(MeloXShapes.sheet, material = MeloXGlassMaterial.Regular)
            .padding(18.dp),
        content = content,
    )
}

@Composable
fun MeloXGlassColorPicker(
    colors: List<Color>,
    selectedColor: Color,
    onColorSelected: (Color) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        colors.forEachIndexed { index, color ->
            val selected = color == selectedColor
            Box(
                Modifier
                    .size(if (selected) 38.dp else 34.dp)
                    .background(color, CircleShape)
                    .semantics {
                        role = Role.RadioButton
                        this.selected = selected
                        contentDescription = "颜色 ${index + 1}"
                    }
                    .clickable { onColorSelected(color) },
            )
        }
    }
}
