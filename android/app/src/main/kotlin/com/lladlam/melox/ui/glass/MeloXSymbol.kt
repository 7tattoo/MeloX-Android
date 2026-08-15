package com.lladlam.melox.ui.glass

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.lladlam.melox.R

/**
 * Semantic icon inventory aligned with the SF Symbols names in the supplied
 * Figma library. Android cannot bundle Apple's private SF Symbols font, so
 * these names render through the license-compatible Material Symbols Rounded
 * font shipped with the app. The semantic names remain Apple-compatible and
 * can be swapped to a native SF font on a platform that provides one.
 */
enum class MeloXSymbol(
    val sfSymbolName: String,
    val materialLigature: String,
) {
    Home("house", "home"),
    Explore("safari", "explore"),
    Library("music.note.list", "library_music"),
    Settings("gearshape", "settings"),
    Person("person.crop.circle", "account_circle"),
    Search("magnifyingglass", "search"),
    ChevronLeft("chevron.left", "chevron_left"),
    ChevronRight("chevron.right", "chevron_right"),
    Xmark("xmark", "close"),
    Ellipsis("ellipsis", "more_horiz"),
    Clock("clock", "schedule"),
    Plus("plus", "add"),
    Download("arrow.down.to.line", "download"),
    Share("square.and.arrow.up", "ios_share"),
    Mail("envelope", "mail"),
    Message("message", "chat_bubble"),
    Info("info.circle", "info"),
    Heart("heart", "favorite"),
    List("list.bullet", "format_list_bulleted"),
    Check("checkmark", "check"),
    ArrowUp("arrow.up", "arrow_upward"),
    ArrowDown("arrow.down", "arrow_downward"),
    Refresh("arrow.clockwise", "refresh"),
    MusicNote("music.note", "music_note"),
    Calendar("calendar", "calendar_month"),
    Flame("flame.fill", "local_fire_department"),
    RadioWaves("dot.radiowaves.left.and.right", "graphic_eq"),
    Walk("figure.walk.motion", "directions_walk"),
    Sparkles("sparkles", "auto_awesome"),
    Quote("quote.opening", "format_quote"),
    Devices("rectangle.on.rectangle", "devices"),
    Landscape("rectangle.landscape.rotate", "screen_rotation"),
    PictureInPicture("rectangle.on.rectangle", "picture_in_picture"),
    Apps("square.grid.2x2", "apps"),
    Microphone("mic", "mic"),
    Storage("internaldrive", "storage"),
    Bug("ladybug", "bug_report"),
}

enum class MeloXSymbolVariant {
    Regular,
    Fill,
}

private val MeloXSymbolsFont = FontFamily(
    Font(R.font.material_symbols_rounded, weight = FontWeight.Normal),
)

private val MeloXSymbolsFilledFont = FontFamily(
    Font(R.font.material_symbols_rounded_filled, weight = FontWeight.Medium),
)

@Composable
fun MeloXSymbolIcon(
    symbol: MeloXSymbol,
    modifier: Modifier = Modifier,
    color: Color,
    variant: MeloXSymbolVariant = MeloXSymbolVariant.Regular,
    iconSize: TextUnit = 24.sp,
) {
    // Material Symbols uses a taller font box than SF Symbols. Rendering the
    // glyph at a slightly smaller em size leaves a real optical inset inside
    // callers' 18/20/24dp icon boxes, instead of clipping the gear and arrows
    // at their ascender/descender edges.
    val glyphSize = iconSize * 0.86f
    Text(
        text = symbol.materialLigature,
        modifier = modifier,
        color = color,
        fontFamily = if (variant == MeloXSymbolVariant.Fill) {
            MeloXSymbolsFilledFont
        } else {
            MeloXSymbolsFont
        },
        fontWeight = if (variant == MeloXSymbolVariant.Fill) FontWeight.Medium else FontWeight.Normal,
        fontSize = glyphSize,
        lineHeight = iconSize,
        style = TextStyle(
            platformStyle = PlatformTextStyle(includeFontPadding = false),
        ),
        textAlign = TextAlign.Center,
        maxLines = 1,
    )
}

@Composable
fun MeloXActionIcon(
    token: String,
    modifier: Modifier = Modifier,
    color: Color,
    enabled: Boolean = true,
) {
    val symbol = when (token) {
        "◷" -> MeloXSymbol.Clock
        "+", "＋" -> MeloXSymbol.Plus
        "↓×" -> MeloXSymbol.Download
        "↗" -> MeloXSymbol.Share
        "✉" -> MeloXSymbol.Mail
        "◎", "◌" -> MeloXSymbol.Message
        "i", "#" -> MeloXSymbol.Info
        "♡", "♥" -> MeloXSymbol.Heart
        "▣", "⇥", "♬" -> MeloXSymbol.List
        "✓" -> MeloXSymbol.Check
        "♫", "♬" -> MeloXSymbol.MusicNote
        "✦" -> MeloXSymbol.Sparkles
        "❞" -> MeloXSymbol.Quote
        "▣" -> MeloXSymbol.Devices
        "▱" -> MeloXSymbol.Landscape
        "▤" -> MeloXSymbol.PictureInPicture
        "☷", "▦", "▥" -> MeloXSymbol.Apps
        "⌁" -> MeloXSymbol.Microphone
        "▰" -> MeloXSymbol.Storage
        "⚙" -> MeloXSymbol.Settings
        "ⓘ" -> MeloXSymbol.Info
        "⌘" -> MeloXSymbol.Bug
        "↑" -> MeloXSymbol.ArrowUp
        "↓" -> MeloXSymbol.Download
        "‹" -> MeloXSymbol.ChevronLeft
        "›" -> MeloXSymbol.ChevronRight
        "•••", "…" -> MeloXSymbol.Ellipsis
        "×" -> MeloXSymbol.Xmark
        "↻" -> MeloXSymbol.Refresh
        else -> MeloXSymbol.Info
    }
    MeloXSymbolIcon(
        symbol = symbol,
        modifier = modifier,
        color = color.copy(alpha = if (enabled) color.alpha else color.alpha * 0.38f),
        variant = if (token == "♥") MeloXSymbolVariant.Fill else MeloXSymbolVariant.Regular,
    )
}
