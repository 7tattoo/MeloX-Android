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
 * Semantic icon inventory aligned with SF Symbols names. The personal-use
 * distribution bundles the matching glyph font in res/font/sf_pro.ttf; any
 * unknown symbol falls back to Material Symbols rather than rendering a blank.
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
    Play("play.fill", "play_arrow"),
    Pause("pause.fill", "pause"),
    Previous("backward.fill", "skip_previous"),
    Next("forward.fill", "skip_next"),
    Shuffle("shuffle", "shuffle"),
    Repeat("repeat", "repeat"),
    Volume("speaker.wave.2", "volume_up"),
    Queue("text.line.first.and.arrowtriangle.forward", "queue_music"),
    MoreVertical("ellipsis", "more_vert"),
    Circle("circle", "radio_button_unchecked"),
    CheckCircle("checkmark.circle.fill", "check_circle"),
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

private val MeloXSfSymbolsFont = FontFamily(
    Font(R.font.sf_pro, weight = FontWeight.Normal),
)

private val MeloXSfSymbolCodePoints = mapOf(
    "house" to 0x10039E,
    "safari" to 0x1003AC,
    "music.note.list" to 0x10046C,
    "gearshape" to 0x1008CB,
    "person.crop.circle" to 0x10026D,
    "magnifyingglass" to 0x1002AB,
    "chevron.left" to 0x100189,
    "chevron.right" to 0x10018A,
    "xmark" to 0x100184,
    "ellipsis" to 0x100360,
    "clock" to 0x10042B,
    "plus" to 0x10017C,
    "square.and.arrow.up" to 0x100202,
    "message" to 0x100324,
    "info.circle" to 0x100174,
    "heart" to 0x1002B4,
    "list.bullet" to 0x1002F2,
    "checkmark" to 0x100185,
    "arrow.clockwise" to 0x100148,
    "music.note" to 0x10046A,
    "calendar" to 0x100249,
    "flame.fill" to 0x10066D,
    "dot.radiowaves.left.and.right" to 0x100319,
    "figure.walk.motion" to 0x101411,
    "sparkles" to 0x1001BF,
    "rectangle.on.rectangle" to 0x10089A,
    "rectangle.landscape.rotate" to 0x101EEF,
    "mic" to 0x1002B0,
    "internaldrive" to 0x10097E,
    "ladybug" to 0x100BD4,
    "play.fill" to 0x100284,
    "pause.fill" to 0x100286,
    "backward.fill" to 0x10028A,
    "forward.fill" to 0x10028C,
    "shuffle" to 0x10029D,
    "repeat" to 0x10029E,
    "checkmark.circle.fill" to 0x100063,
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
    val sfCodePoint = MeloXSfSymbolCodePoints[symbol.sfSymbolName]
    Text(
        text = sfCodePoint?.let { String(Character.toChars(it)) } ?: symbol.materialLigature,
        modifier = if (contentDescription == null) modifier else modifier.semantics {
            this.contentDescription = contentDescription
        },
        color = color,
        fontFamily = if (sfCodePoint != null) {
            MeloXSfSymbolsFont
        } else if (variant == MeloXSymbolVariant.Fill) {
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
        "○" -> MeloXSymbol.Circle
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
        "♪" -> MeloXSymbol.MusicNote
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
        MeloXSymbol.Play -> "播放"
        MeloXSymbol.Pause -> "暂停"
        MeloXSymbol.Previous -> "上一首"
        MeloXSymbol.Next -> "下一首"
        MeloXSymbol.Shuffle -> "随机播放"
        MeloXSymbol.Repeat -> "重复播放"
        MeloXSymbol.Volume -> "音量"
        MeloXSymbol.Queue -> "播放队列"
        MeloXSymbol.MoreVertical -> "更多操作"
        MeloXSymbol.Circle -> "未选择"
        MeloXSymbol.CheckCircle -> "已选择"
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
