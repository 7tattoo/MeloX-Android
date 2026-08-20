package com.lladlam.melox.ui.glass

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
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
    Unknown("questionmark.circle", "help"),
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
    contentDescription: String? = null,
) {
    // Material Symbols uses a taller font box than SF Symbols. Rendering the
    // glyph at a slightly smaller em size leaves a real optical inset inside
    // callers' 18/20/24dp icon boxes, instead of clipping the gear and arrows
    // at their ascender/descender edges.
    val glyphSize = iconSize * 0.86f
    Text(
        text = symbol.materialLigature,
        modifier = if (contentDescription == null) modifier else modifier.semantics {
            this.contentDescription = contentDescription
        },
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

/**
 * Apple Music-style search affordance: the magnifier and back arrow share one
 * 300ms path-like transition instead of abruptly replacing the glyph.
 */
@Composable
fun MeloXSearchBackMorphIcon(
    focused: Boolean,
    modifier: Modifier = Modifier,
    color: Color,
    contentDescription: String? = null,
) {
    val progress by animateFloatAsState(
        targetValue = if (focused) 1f else 0f,
        animationSpec = tween(300, easing = LinearOutSlowInEasing),
        label = "search-back-morph",
    )
    Canvas(if (contentDescription == null) modifier else modifier.semantics { this.contentDescription = contentDescription }) {
        val strokeWidth = size.minDimension * 0.095f
        val magnifierAlpha = (1f - progress).coerceIn(0f, 1f)
        val arrowAlpha = progress.coerceIn(0f, 1f)
        val stroke = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        if (magnifierAlpha > 0f) {
            drawCircle(
                color = color.copy(alpha = color.alpha * magnifierAlpha),
                radius = size.minDimension * 0.22f,
                center = androidx.compose.ui.geometry.Offset(
                    size.width * 0.40f,
                    size.height * 0.40f,
                ),
                style = stroke,
            )
            drawLine(
                color = color.copy(alpha = color.alpha * magnifierAlpha),
                start = androidx.compose.ui.geometry.Offset(size.width * 0.56f, size.height * 0.56f),
                end = androidx.compose.ui.geometry.Offset(size.width * 0.80f, size.height * 0.80f),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round,
            )
        }
        if (arrowAlpha > 0f) {
            val arrowColor = color.copy(alpha = color.alpha * arrowAlpha)
            drawLine(arrowColor, Offset(size.width * 0.78f, size.height * 0.50f), Offset(size.width * 0.22f, size.height * 0.50f), strokeWidth, StrokeCap.Round)
            drawLine(arrowColor, Offset(size.width * 0.22f, size.height * 0.50f), Offset(size.width * 0.46f, size.height * 0.27f), strokeWidth, StrokeCap.Round)
            drawLine(arrowColor, Offset(size.width * 0.22f, size.height * 0.50f), Offset(size.width * 0.46f, size.height * 0.73f), strokeWidth, StrokeCap.Round)
        }
    }
}

@Composable
fun MeloXActionIcon(
    token: String,
    modifier: Modifier = Modifier,
    color: Color,
    enabled: Boolean = true,
    contentDescription: String? = null,
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
        else -> MeloXSymbol.Unknown
    }
    val semanticLabel = contentDescription ?: when (symbol) {
        MeloXSymbol.Clock -> "历史记录"
        MeloXSymbol.Plus -> "添加"
        MeloXSymbol.Download -> "下载"
        MeloXSymbol.Share -> "分享"
        MeloXSymbol.Mail -> "私信"
        MeloXSymbol.Message -> "消息"
        MeloXSymbol.Info -> "信息"
        MeloXSymbol.Heart -> "收藏"
        MeloXSymbol.List -> "列表"
        MeloXSymbol.Check -> "完成"
        MeloXSymbol.MusicNote -> "音乐"
        MeloXSymbol.Sparkles -> "智能功能"
        MeloXSymbol.Quote -> "引用"
        MeloXSymbol.Devices -> "设备"
        MeloXSymbol.Landscape -> "横屏"
        MeloXSymbol.PictureInPicture -> "画中画"
        MeloXSymbol.Apps -> "页面布局"
        MeloXSymbol.Microphone -> "听歌识曲"
        MeloXSymbol.Storage -> "存储"
        MeloXSymbol.Settings -> "设置"
        MeloXSymbol.Bug -> "诊断"
        MeloXSymbol.ArrowUp -> "上移"
        MeloXSymbol.ArrowDown -> "下移"
        MeloXSymbol.ChevronLeft -> "返回"
        MeloXSymbol.ChevronRight -> "打开"
        MeloXSymbol.Ellipsis -> "更多操作"
        MeloXSymbol.Xmark -> "关闭"
        MeloXSymbol.Refresh -> "刷新"
        MeloXSymbol.Unknown -> "未知操作图标 $token"
        else -> symbol.sfSymbolName
    }
    MeloXSymbolIcon(
        symbol = symbol,
        modifier = modifier,
        color = color.copy(alpha = if (enabled) color.alpha else color.alpha * 0.38f),
        variant = if (token == "♥") MeloXSymbolVariant.Fill else MeloXSymbolVariant.Regular,
        contentDescription = semanticLabel,
    )
}
