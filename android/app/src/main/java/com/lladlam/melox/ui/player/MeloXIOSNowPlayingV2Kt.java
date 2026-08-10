package com.lladlam.melox.p012ui.player;

import android.content.Context;
import androidx.compose.animation.AnimatedContentKt;
import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.animation.AnimatedContentTransitionScope;
import androidx.compose.animation.ContentTransform;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteractionKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.BoxWithConstraintsKt;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.layout.WindowInsetsPadding_androidKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.AndroidMenu_androidKt;
import androidx.compose.material3.SliderKt;
import androidx.compose.material3.SliderState;
import androidx.compose.material3.TextKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.profileinstaller.ProfileVerifier;
import androidx.window.core.layout.WindowSizeClass;
import com.lladlam.melox.core.account.NeteaseSessionStore;
import com.lladlam.melox.core.audio.MusicQuality;
import com.lladlam.melox.core.audio.MusicQualityPreferences;
import com.lladlam.melox.core.audio.MusicQualityRuntime;
import com.lladlam.melox.core.audio.NeteaseQualityClient;
import com.lladlam.melox.core.audio.SongAudioAvailability;
import com.lladlam.melox.p012ui.glass.MeloXBackdropComponentsKt;
import com.lladlam.melox.playback.PlaybackCommands;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KFunction;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MeloXIOSNowPlayingV2.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000z\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001aW\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\rH\u0007¢\u0006\u0002\u0010\u000f\u001a\u001b\u0010\u0010\u001a\u00020\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007H\u0003¢\u0006\u0002\u0010\u0011\u001a\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\tH\u0002\u001a\u001d\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\rH\u0003¢\u0006\u0002\u0010\u0017\u001a1\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u000bH\u0003¢\u0006\u0002\u0010\u001a\u001a\u0015\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0003¢\u0006\u0002\u0010\u001c\u001a\u001f\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0003¢\u0006\u0002\u0010 \u001a\u0015\u0010!\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0003¢\u0006\u0002\u0010\u001c\u001a\u0015\u0010\"\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0003¢\u0006\u0002\u0010\u001c\u001a-\u0010#\u001a\u00020\u00032\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007H\u0003¢\u0006\u0004\b)\u0010*\u001a\u0015\u0010+\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0003¢\u0006\u0002\u0010\u001c\u001a1\u0010,\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u000bH\u0003¢\u0006\u0002\u0010\u001a\u001a3\u0010-\u001a\u00020\u00032\u0006\u0010$\u001a\u00020%2\u0006\u0010.\u001a\u00020\r2\u0006\u0010/\u001a\u00020\r2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007H\u0003¢\u0006\u0002\u00100\u001a'\u00101\u001a\u00020\u00032\u0006\u0010$\u001a\u00020%2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u00102\u001a\u000203H\u0003¢\u0006\u0004\b4\u00105\u001a\u0010\u00106\u001a\u0002072\u0006\u00108\u001a\u000209H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006:²\u0006\n\u0010;\u001a\u00020\rX\u008a\u0084\u0002²\u0006\n\u0010<\u001a\u00020=X\u008a\u0084\u0002²\u0006\n\u0010>\u001a\u00020=X\u008a\u0084\u0002²\u0006\n\u0010?\u001a\u00020'X\u008a\u0084\u0002²\u0006\n\u0010@\u001a\u00020=X\u008a\u0084\u0002²\u0006\n\u0010A\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010B\u001a\u00020=X\u008a\u008e\u0002²\u0006\n\u0010C\u001a\u00020'X\u008a\u0084\u0002²\u0006\n\u0010D\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010.\u001a\u00020EX\u008a\u008e\u0002²\u0006\f\u0010F\u001a\u0004\u0018\u00010EX\u008a\u008e\u0002²\u0006\n\u0010G\u001a\u00020HX\u008a\u008e\u0002²\u0006\n\u0010;\u001a\u00020\rX\u008a\u0084\u0002²\u0006\n\u0010<\u001a\u00020=X\u008a\u0084\u0002²\u0006\n\u0010;\u001a\u00020\rX\u008a\u0084\u0002²\u0006\n\u0010<\u001a\u00020=X\u008a\u0084\u0002²\u0006\n\u0010;\u001a\u00020\rX\u008a\u0084\u0002²\u0006\n\u0010<\u001a\u00020=X\u008a\u0084\u0002²\u0006\n\u0010I\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010J\u001a\u00020=X\u008a\u008e\u0002²\u0006\n\u0010K\u001a\u00020'X\u008a\u0084\u0002²\u0006\n\u0010C\u001a\u00020'X\u008a\u0084\u0002²\u0006\n\u0010;\u001a\u00020\rX\u008a\u0084\u0002²\u0006\n\u0010L\u001a\u00020=X\u008a\u0084\u0002²\u0006\n\u0010M\u001a\u00020=X\u008a\u0084\u0002²\u0006\n\u0010N\u001a\u00020=X\u008a\u0084\u0002"}, d2 = {"PLAYER_CONTROLS_HEIGHT", "", "MeloXIOSNowPlayingV2", "", "state", "Lcom/lladlam/melox/ui/player/MeloXPlaybackUiState;", "onDismiss", "Lkotlin/Function0;", "page", "Lcom/lladlam/melox/ui/player/MeloXNowPlayingPage;", "onPageChanged", "Lkotlin/Function1;", "drawBackdrop", "", "drawArtwork", "(Lcom/lladlam/melox/ui/player/MeloXPlaybackUiState;Lkotlin/jvm/functions/Function0;Lcom/lladlam/melox/ui/player/MeloXNowPlayingPage;Lkotlin/jvm/functions/Function1;ZZLandroidx/compose/runtime/Composer;II)V", "MeloXGrabber", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "pageTransform", "Landroidx/compose/animation/ContentTransform;", "initial", "target", "MeloXArtworkPageV3", "(Lcom/lladlam/melox/ui/player/MeloXPlaybackUiState;ZLandroidx/compose/runtime/Composer;I)V", "MeloXBottomControlsV3", "onPageSelected", "(Lcom/lladlam/melox/ui/player/MeloXPlaybackUiState;Lcom/lladlam/melox/ui/player/MeloXNowPlayingPage;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "MeloXProgressControlV3", "(Lcom/lladlam/melox/ui/player/MeloXPlaybackUiState;Landroidx/compose/runtime/Composer;I)V", "MeloXQualityChipV3", "modifier", "Landroidx/compose/ui/Modifier;", "(Lcom/lladlam/melox/ui/player/MeloXPlaybackUiState;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "MeloXTransportControlsV3", "CupertinoPlayPauseButton", "CupertinoTransportButton", "kind", "Lcom/lladlam/melox/ui/player/CupertinoGlyphKind;", "visualSize", "Landroidx/compose/ui/unit/Dp;", "onClick", "CupertinoTransportButton-rAjV9yQ", "(Lcom/lladlam/melox/ui/player/CupertinoGlyphKind;FLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "MeloXVolumeControlV3", "MeloXPageSelectorV3", "CupertinoPageButton", "selected", "enabled", "(Lcom/lladlam/melox/ui/player/CupertinoGlyphKind;ZZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "CupertinoGlyph", TtmlNode.ATTR_TTS_COLOR, "Landroidx/compose/ui/graphics/Color;", "CupertinoGlyph-XO-JAsU", "(Lcom/lladlam/melox/ui/player/CupertinoGlyphKind;Landroidx/compose/ui/Modifier;JLandroidx/compose/runtime/Composer;I)V", "formatDurationV3", "", "milliseconds", "", "app", "pressed", "scale", "", "artworkScale", "shadowElevation", "shadowAlpha", "scrubbing", "localProgress", "trackHeight", "expanded", "Lcom/lladlam/melox/core/audio/MusicQuality;", "actual", "availability", "Lcom/lladlam/melox/core/audio/SongAudioAvailability;", "dragging", "localVolume", "thumbSize", "pressScale", "selectionScale", "backgroundAlpha"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class MeloXIOSNowPlayingV2Kt {
    private static final int PLAYER_CONTROLS_HEIGHT = 279;

    /* JADX INFO: compiled from: MeloXIOSNowPlayingV2.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[MeloXNowPlayingPage.values().length];
            try {
                iArr[MeloXNowPlayingPage.Artwork.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[MeloXNowPlayingPage.Lyrics.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[MeloXNowPlayingPage.Queue.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[CupertinoGlyphKind.values().length];
            try {
                iArr2[CupertinoGlyphKind.Play.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr2[CupertinoGlyphKind.Pause.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[CupertinoGlyphKind.Backward.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr2[CupertinoGlyphKind.Forward.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr2[CupertinoGlyphKind.SpeakerLow.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr2[CupertinoGlyphKind.SpeakerHigh.ordinal()] = 6;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr2[CupertinoGlyphKind.Lyrics.ordinal()] = 7;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr2[CupertinoGlyphKind.PipEnter.ordinal()] = 8;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr2[CupertinoGlyphKind.Queue.ordinal()] = 9;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr2[CupertinoGlyphKind.Waveform.ordinal()] = 10;
            } catch (NoSuchFieldError e13) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    static final Unit CupertinoGlyph_XO_JAsU$lambda$1(CupertinoGlyphKind cupertinoGlyphKind, Modifier modifier, long j, int i, Composer composer, int i2) {
        m9689CupertinoGlyphXOJAsU(cupertinoGlyphKind, modifier, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit CupertinoPageButton$lambda$7(CupertinoGlyphKind cupertinoGlyphKind, boolean z, boolean z2, Function0 function0, int i, Composer composer, int i2) {
        CupertinoPageButton(cupertinoGlyphKind, z, z2, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit CupertinoPlayPauseButton$lambda$6(MeloXPlaybackUiState meloXPlaybackUiState, int i, Composer composer, int i2) throws Throwable {
        CupertinoPlayPauseButton(meloXPlaybackUiState, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit CupertinoTransportButton_rAjV9yQ$lambda$5(CupertinoGlyphKind cupertinoGlyphKind, float f, Function0 function0, int i, Composer composer, int i2) {
        m9690CupertinoTransportButtonrAjV9yQ(cupertinoGlyphKind, f, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXArtworkPageV3$lambda$4(MeloXPlaybackUiState meloXPlaybackUiState, boolean z, int i, Composer composer, int i2) {
        MeloXArtworkPageV3(meloXPlaybackUiState, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXBottomControlsV3$lambda$1(MeloXPlaybackUiState meloXPlaybackUiState, MeloXNowPlayingPage meloXNowPlayingPage, Function1 function1, int i, Composer composer, int i2) throws Throwable {
        MeloXBottomControlsV3(meloXPlaybackUiState, meloXNowPlayingPage, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXGrabber$lambda$4(Function0 function0, int i, Composer composer, int i2) {
        MeloXGrabber(function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXIOSNowPlayingV2$lambda$2(MeloXPlaybackUiState meloXPlaybackUiState, Function0 function0, MeloXNowPlayingPage meloXNowPlayingPage, Function1 function1, boolean z, boolean z2, int i, int i2, Composer composer, int i3) throws Throwable {
        MeloXIOSNowPlayingV2(meloXPlaybackUiState, function0, meloXNowPlayingPage, function1, z, z2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit MeloXPageSelectorV3$lambda$1(MeloXPlaybackUiState meloXPlaybackUiState, MeloXNowPlayingPage meloXNowPlayingPage, Function1 function1, int i, Composer composer, int i2) {
        MeloXPageSelectorV3(meloXPlaybackUiState, meloXNowPlayingPage, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXProgressControlV3$lambda$9(MeloXPlaybackUiState meloXPlaybackUiState, int i, Composer composer, int i2) {
        MeloXProgressControlV3(meloXPlaybackUiState, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXQualityChipV3$lambda$19(MeloXPlaybackUiState meloXPlaybackUiState, Modifier modifier, int i, int i2, Composer composer, int i3) {
        MeloXQualityChipV3(meloXPlaybackUiState, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit MeloXTransportControlsV3$lambda$1(MeloXPlaybackUiState meloXPlaybackUiState, int i, Composer composer, int i2) throws Throwable {
        MeloXTransportControlsV3(meloXPlaybackUiState, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXVolumeControlV3$lambda$10(MeloXPlaybackUiState meloXPlaybackUiState, int i, Composer composer, int i2) {
        MeloXVolumeControlV3(meloXPlaybackUiState, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXIOSNowPlayingV2$lambda$0$0(MeloXNowPlayingPage it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:130:0x04b3  */
    public static final void MeloXIOSNowPlayingV2(final MeloXPlaybackUiState state, final Function0<Unit> onDismiss, MeloXNowPlayingPage page, Function1<? super MeloXNowPlayingPage, Unit> function1, boolean drawBackdrop, boolean drawArtwork, Composer $composer, final int $changed, final int i) throws Throwable {
        final Function1<? super MeloXNowPlayingPage, Unit> function2;
        boolean drawBackdrop2;
        final boolean drawArtwork2;
        Composer $composer2;
        final MeloXNowPlayingPage page2;
        final Function1<? super MeloXNowPlayingPage, Unit> function3;
        final boolean drawBackdrop3;
        MeloXNowPlayingPage page3;
        final boolean drawArtwork3;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function4;
        boolean drawArtwork4;
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(onDismiss, "onDismiss");
        Composer $composer3 = $composer.startRestartGroup(-190044140);
        ComposerKt.sourceInformation($composer3, "C(MeloXIOSNowPlayingV2)N(state,onDismiss,page,onPageChanged,drawBackdrop,drawArtwork)85@3904L2,89@3983L2013:MeloXIOSNowPlayingV2.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(state) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(onDismiss) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer3.changed(page == null ? -1 : page.ordinal()) ? 256 : 128;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
            function2 = function1;
        } else if (($changed & 3072) == 0) {
            function2 = function1;
            $dirty |= $composer3.changedInstance(function2) ? 2048 : 1024;
        } else {
            function2 = function1;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty |= 24576;
            drawBackdrop2 = drawBackdrop;
        } else if (($changed & 24576) == 0) {
            drawBackdrop2 = drawBackdrop;
            $dirty |= $composer3.changed(drawBackdrop2) ? 16384 : 8192;
        } else {
            drawBackdrop2 = drawBackdrop;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.f253xf2722a21;
            drawArtwork2 = drawArtwork;
        } else if (($changed & ProfileVerifier.CompilationStatus.f253xf2722a21) == 0) {
            drawArtwork2 = drawArtwork;
            $dirty |= $composer3.changed(drawArtwork2) ? 131072 : 65536;
        } else {
            drawArtwork2 = drawArtwork;
        }
        if (!$composer3.shouldExecute(($dirty & 74899) != 74898, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            page2 = page;
            function3 = function2;
            drawBackdrop3 = drawBackdrop2;
        } else {
            if (i2 != 0) {
                page3 = MeloXNowPlayingPage.Artwork;
            } else {
                page3 = page;
            }
            if (i3 != 0) {
                ComposerKt.sourceInformationMarkerStart($composer3, 2051181526, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
                Object objRememberedValue = $composer3.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return MeloXIOSNowPlayingV2Kt.MeloXIOSNowPlayingV2$lambda$0$0((MeloXNowPlayingPage) obj2);
                        }
                    };
                    $composer3.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                function2 = (Function1) objRememberedValue;
            }
            if (i4 != 0) {
                drawBackdrop2 = true;
            }
            if (i5 == 0) {
                drawArtwork3 = drawArtwork2;
            } else {
                drawArtwork3 = true;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-190044140, $dirty, -1, "com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2 (MeloXIOSNowPlayingV2.kt:88)");
            }
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            Color.Companion companion = Color.INSTANCE;
            Modifier modifierM1043backgroundbw27NRU$default = BackgroundKt.m1043backgroundbw27NRU$default(modifierFillMaxSize$default, drawBackdrop2 ? companion.m6094getBlack0d7_KjU() : companion.m6103getTransparent0d7_KjU(), null, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1043backgroundbw27NRU$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i6 = ((((0 << 3) & 112) << 6) & 896) | 6;
            $composer2 = $composer3;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor;
                $composer3.createNode(function0);
            } else {
                function0 = constructor;
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl = Updater.constructor_impl($composer3);
            Updater.set_impl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i7 = (i6 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i8 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -328690131, "C101@4322L1668:MeloXIOSNowPlayingV2.kt#qhu5z0");
            if (drawBackdrop2) {
                $composer3.startReplaceGroup(-328724852);
                ComposerKt.sourceInformation($composer3, "95@4170L132");
                MeloXFlowingLightBackdropKt.MeloXFlowingLightBackdrop(state.getArtworkUrl(), state.isPlaying(), null, $composer3, 0, 4);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(-328574936);
                $composer3.endReplaceGroup();
            }
            Modifier modifierM1807paddingVpY3zN4$default = PaddingKt.m1807paddingVpY3zN4$default(WindowInsetsPadding_androidKt.statusBarsPadding(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null)), Dp.constructor_impl(32), 0.0f, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer3, modifierM1807paddingVpY3zN4$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i9 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function4 = constructor2;
                $composer3.createNode(function4);
            } else {
                function4 = constructor2;
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl2 = Updater.constructor_impl($composer3);
            Updater.set_impl(composerM5188constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i10 = (i9 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            int i11 = ((0 >> 6) & 112) | 6;
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -935180181, "C107@4500L23,111@4623L92,118@4890L647,109@4537L1000,138@5667L298,135@5551L429:MeloXIOSNowPlayingV2.kt#qhu5z0");
            MeloXGrabber(onDismiss, $composer3, ($dirty >> 3) & 14);
            Modifier modifierWeight$default = ColumnScope.weight$default(columnScope, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer3, -1415637876, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            Object objRememberedValue2 = $composer3.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return MeloXIOSNowPlayingV2Kt.MeloXIOSNowPlayingV2$lambda$1$0$0$0((AnimatedContentTransitionScope) obj3);
                    }
                };
                $composer3.updateRememberedValue(obj2);
                objRememberedValue2 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            AnimatedContentKt.AnimatedContent(page3, modifierWeight$default, (Function1) objRememberedValue2, null, "melox-now-playing-pages-v4", null, ComposableLambdaKt.rememberComposableLambda(-2070459185, true, new Function4() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function4
                public final Object invoke(Object obj3, Object obj4, Object obj5, Object obj6) {
                    return MeloXIOSNowPlayingV2Kt.MeloXIOSNowPlayingV2$lambda$1$0$1(state, drawArtwork3, (AnimatedContentScope) obj3, (MeloXNowPlayingPage) obj4, (Composer) obj5, ((Integer) obj6).intValue());
                }
            }, $composer3, 54), $composer3, (($dirty >> 6) & 14) | 1597824, 40);
            page2 = page3;
            ComposerKt.sourceInformationMarkerStart($composer3, -1415604262, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            boolean z = (($dirty & 7168) == 2048) | (($dirty & 896) == 256);
            Object objRememberedValue3 = $composer3.rememberedValue();
            if (!z) {
                drawArtwork4 = drawArtwork3;
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                MeloXBottomControlsV3(state, page2, (Function1) objRememberedValue3, $composer3, ($dirty & 14) | (($dirty >> 3) & 112));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                drawArtwork2 = drawArtwork4;
                function3 = function2;
                drawBackdrop3 = drawBackdrop2;
            } else {
                drawArtwork4 = drawArtwork3;
            }
            Object obj3 = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj4) {
                    return MeloXIOSNowPlayingV2Kt.MeloXIOSNowPlayingV2$lambda$1$0$2$0(function2, page2, (MeloXNowPlayingPage) obj4);
                }
            };
            $composer3.updateRememberedValue(obj3);
            objRememberedValue3 = obj3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            MeloXBottomControlsV3(state, page2, (Function1) objRememberedValue3, $composer3, ($dirty & 14) | (($dirty >> 3) & 112));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            drawArtwork2 = drawArtwork4;
            function3 = function2;
            drawBackdrop3 = drawBackdrop2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final MeloXNowPlayingPage page4 = page2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj4, Object obj5) {
                    return MeloXIOSNowPlayingV2Kt.MeloXIOSNowPlayingV2$lambda$2(state, onDismiss, page4, function3, drawBackdrop3, drawArtwork2, $changed, i, (Composer) obj4, ((Integer) obj5).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final ContentTransform MeloXIOSNowPlayingV2$lambda$1$0$0$0(AnimatedContentTransitionScope AnimatedContent) {
        Intrinsics.checkNotNullParameter(AnimatedContent, "$this$AnimatedContent");
        return AnimatedContent.using(pageTransform((MeloXNowPlayingPage) AnimatedContent.getInitialState(), (MeloXNowPlayingPage) AnimatedContent.getTargetState()), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXIOSNowPlayingV2$lambda$1$0$1(MeloXPlaybackUiState $state, boolean $drawArtwork, AnimatedContentScope AnimatedContent, MeloXNowPlayingPage selectedPage, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(AnimatedContent, "$this$AnimatedContent");
        Intrinsics.checkNotNullParameter(selectedPage, "selectedPage");
        ComposerKt.sourceInformation($composer, "CN(selectedPage):MeloXIOSNowPlayingV2.kt#qhu5z0");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2070459185, $changed, -1, "com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2.<anonymous>.<anonymous>.<anonymous> (MeloXIOSNowPlayingV2.kt:119)");
        }
        switch (WhenMappings.$EnumSwitchMapping$0[selectedPage.ordinal()]) {
            case 1:
                $composer.startReplaceGroup(-1141415182);
                ComposerKt.sourceInformation($composer, "120@4997L131");
                MeloXArtworkPageV3($state, $drawArtwork, $composer, 0);
                $composer.endReplaceGroup();
                break;
            case 2:
                $composer.startReplaceGroup(-1141409349);
                ComposerKt.sourceInformation($composer, "124@5179L140");
                MeloXIOSLyricsPanelKt.MeloXIOSLyricsPanel($state, SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), $composer, 48, 0);
                $composer.endReplaceGroup();
                break;
            case 3:
                $composer.startReplaceGroup(-1141403273);
                ComposerKt.sourceInformation($composer, "128@5369L136");
                MeloXQueuePanelKt.MeloXQueuePanel($state, SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), $composer, 48, 0);
                $composer.endReplaceGroup();
                break;
            default:
                $composer.startReplaceGroup(-1141417050);
                $composer.endReplaceGroup();
                throw new NoWhenBranchMatchedException();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXIOSNowPlayingV2$lambda$1$0$2$0(Function1 $onPageChanged, MeloXNowPlayingPage $page, MeloXNowPlayingPage destination) {
        MeloXNowPlayingPage meloXNowPlayingPage;
        Intrinsics.checkNotNullParameter(destination, "destination");
        if ($page == destination) {
            meloXNowPlayingPage = MeloXNowPlayingPage.Artwork;
        } else {
            meloXNowPlayingPage = destination;
        }
        $onPageChanged.invoke(meloXNowPlayingPage);
        return Unit.INSTANCE;
    }

    private static final void MeloXGrabber(final Function0<Unit> function0, Composer $composer, final int $changed) {
        Function0<ComposeUiNode> function1;
        Composer $composer2 = $composer.startRestartGroup(-86812364);
        ComposerKt.sourceInformation($composer2, "C(MeloXGrabber)N(onDismiss)154@6084L39,155@6155L25,156@6198L260,165@6464L671:MeloXIOSNowPlayingV2.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 4 : 2;
        }
        if ($composer2.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-86812364, $dirty, -1, "com.lladlam.melox.ui.player.MeloXGrabber (MeloXIOSNowPlayingV2.kt:153)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -1822301093, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            Object objRememberedValue = $composer2.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object objMutableInteractionSource = InteractionSourceKt.MutableInteractionSource();
                $composer2.updateRememberedValue(objMutableInteractionSource);
                objRememberedValue = objMutableInteractionSource;
            }
            MutableInteractionSource interaction = (MutableInteractionSource) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(MeloXGrabber$lambda$1(PressInteractionKt.collectIsPressedAsState(interaction, $composer2, 6)) ? 0.88f : 1.0f, AnimationSpecKt.spring$default(0.5f, 1500.0f, null, 4, null), 0.0f, "grabber-scale", null, $composer2, 3120, 20);
            Modifier modifierM1073clickableO2vRcR0 = ClickableKt.m1073clickableO2vRcR0(SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.constructor_impl(30)), interaction, null, (24 & 4) != 0, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : null, function0);
            Alignment topCenter = Alignment.INSTANCE.getTopCenter();
            ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(topCenter, false);
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierM1073clickableO2vRcR0);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function1 = constructor;
                $composer2.createNode(function1);
            } else {
                function1 = constructor;
                $composer2.useNode();
            }
            Composer composerM5188constructorimpl = Updater.constructor_impl($composer2);
            Updater.set_impl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1794368428, "C180@6933L89,176@6776L353:MeloXIOSNowPlayingV2.kt#qhu5z0");
            Modifier modifierM1874sizeVpY3zN4 = SizeKt.m1874sizeVpY3zN4(PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.constructor_impl(8), 0.0f, 0.0f, 13, null), Dp.constructor_impl(60), Dp.constructor_impl(5));
            ComposerKt.sourceInformationMarkerStart($composer2, -473520089, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            boolean zChanged = $composer2.changed(stateAnimateFloatAsState);
            Object objRememberedValue2 = $composer2.rememberedValue();
            if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return MeloXIOSNowPlayingV2Kt.MeloXGrabber$lambda$3$0$0(stateAnimateFloatAsState, (GraphicsLayerScope) obj2);
                    }
                };
                $composer2.updateRememberedValue(obj);
                objRememberedValue2 = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierClip = ClipKt.clip(GraphicsLayerModifierKt.graphicsLayer(modifierM1874sizeVpY3zN4, (Function1) objRememberedValue2), RoundedCornerShapeKt.getCircleShape());
            long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
            BoxKt.Box(BackgroundKt.m1043backgroundbw27NRU$default(modifierClip, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU) : 0.52f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU) : 0.0f), null, 2, null), $composer2, 0);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return MeloXIOSNowPlayingV2Kt.MeloXGrabber$lambda$4(function0, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    private static final boolean MeloXGrabber$lambda$1(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final float MeloXGrabber$lambda$2(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXGrabber$lambda$3$0$0(State $scale$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setScaleX(MeloXGrabber$lambda$2($scale$delegate));
        graphicsLayer.setScaleY(MeloXGrabber$lambda$2($scale$delegate));
        return Unit.INSTANCE;
    }

    private static final ContentTransform pageTransform(MeloXNowPlayingPage initial, MeloXNowPlayingPage target) {
        boolean directLyricsQueue = (initial == MeloXNowPlayingPage.Lyrics && target == MeloXNowPlayingPage.Queue) || (initial == MeloXNowPlayingPage.Queue && target == MeloXNowPlayingPage.Lyrics);
        if (directLyricsQueue) {
            return AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(440, 0, null, 6, null), 0.0f, 2, null).plus(EnterExitTransitionKt.m844scaleInL8ZKhE$default(AnimationSpecKt.tween$default(440, 0, EasingKt.getFastOutSlowInEasing(), 2, null), 0.92f, 0L, 4, null)), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(300, 0, null, 6, null), 0.0f, 2, null).plus(EnterExitTransitionKt.m846scaleOutL8ZKhE$default(AnimationSpecKt.tween$default(300, 0, EasingKt.getFastOutSlowInEasing(), 2, null), 0.92f, 0L, 4, null)));
        }
        switch (WhenMappings.$EnumSwitchMapping$0[target.ordinal()]) {
            case 1:
                return AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(220, 70, null, 4, null), 0.0f, 2, null).plus(EnterExitTransitionKt.slideInVertically(AnimationSpecKt.tween(220, 70, EasingKt.getFastOutSlowInEasing()), new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Integer.valueOf(MeloXIOSNowPlayingV2Kt.pageTransform$lambda$0(((Integer) obj).intValue()));
                    }
                })), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(PsExtractor.VIDEO_STREAM_MASK, 0, null, 6, null), 0.0f, 2, null).plus(EnterExitTransitionKt.slideOutVertically(AnimationSpecKt.tween$default(PsExtractor.VIDEO_STREAM_MASK, 0, EasingKt.getFastOutSlowInEasing(), 2, null), new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Integer.valueOf(MeloXIOSNowPlayingV2Kt.pageTransform$lambda$1(((Integer) obj).intValue()));
                    }
                })));
            case 2:
                return AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(340, 110, null, 4, null), 0.0f, 2, null).plus(EnterExitTransitionKt.slideInVertically(AnimationSpecKt.tween(340, 110, EasingKt.getFastOutSlowInEasing()), new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Integer.valueOf(MeloXIOSNowPlayingV2Kt.pageTransform$lambda$2(((Integer) obj).intValue()));
                    }
                })), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(PsExtractor.VIDEO_STREAM_MASK, 0, null, 6, null), 0.0f, 2, null).plus(EnterExitTransitionKt.slideOutVertically(AnimationSpecKt.tween$default(PsExtractor.VIDEO_STREAM_MASK, 0, EasingKt.getFastOutSlowInEasing(), 2, null), new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Integer.valueOf(MeloXIOSNowPlayingV2Kt.pageTransform$lambda$3(((Integer) obj).intValue()));
                    }
                })));
            case 3:
                return AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(220, 70, null, 4, null), 0.0f, 2, null).plus(EnterExitTransitionKt.slideInVertically(AnimationSpecKt.tween(220, 70, EasingKt.getFastOutSlowInEasing()), new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Integer.valueOf(MeloXIOSNowPlayingV2Kt.pageTransform$lambda$4(((Integer) obj).intValue()));
                    }
                })), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(PsExtractor.VIDEO_STREAM_MASK, 0, null, 6, null), 0.0f, 2, null).plus(EnterExitTransitionKt.slideOutVertically(AnimationSpecKt.tween$default(PsExtractor.VIDEO_STREAM_MASK, 0, EasingKt.getFastOutSlowInEasing(), 2, null), new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Integer.valueOf(MeloXIOSNowPlayingV2Kt.pageTransform$lambda$5(((Integer) obj).intValue()));
                    }
                })));
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    static final int pageTransform$lambda$0(int it) {
        return -((int) (it * 0.42f));
    }

    static final int pageTransform$lambda$1(int it) {
        return -((int) (it * 0.42f));
    }

    static final int pageTransform$lambda$2(int it) {
        return (int) (it * 0.58f);
    }

    static final int pageTransform$lambda$3(int it) {
        return (int) (it * 0.58f);
    }

    static final int pageTransform$lambda$4(int it) {
        return (int) (it * 0.58f);
    }

    static final int pageTransform$lambda$5(int it) {
        return (int) (it * 0.58f);
    }

    private static final void MeloXArtworkPageV3(final MeloXPlaybackUiState state, final boolean drawArtwork, Composer $composer, final int $changed) {
        SpringSpec springSpecSpring;
        Composer $composer2 = $composer.startRestartGroup(-229142872);
        ComposerKt.sourceInformation($composer2, "C(MeloXArtworkPageV3)N(state,drawArtwork)264@9849L499,281@10376L238,289@10638L237,298@10935L2243,298@10881L2297:MeloXIOSNowPlayingV2.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(state) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(drawArtwork) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-229142872, $dirty2, -1, "com.lladlam.melox.ui.player.MeloXArtworkPageV3 (MeloXIOSNowPlayingV2.kt:263)");
            }
            float f = state.isPlaying() ? 1.0f : 0.74f;
            if (state.isPlaying()) {
                springSpecSpring = AnimationSpecKt.spring(0.7f, 280.0f, Float.valueOf(0.001f));
            } else {
                springSpecSpring = AnimationSpecKt.spring(0.94f, 360.0f, Float.valueOf(0.001f));
            }
            final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, springSpecSpring, 0.0f, "artwork-scale-v4", null, $composer2, 3072, 20);
            final State<Dp> stateM940animateDpAsStateAjpBEmI = AnimateAsStateKt.m940animateDpAsStateAjpBEmI(state.isPlaying() ? Dp.constructor_impl(26) : Dp.constructor_impl(14), AnimationSpecKt.spring$default(0.92f, 320.0f, null, 4, null), "artwork-shadow-elevation-v4", null, $composer2, 432, 8);
            final State<Float> stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(state.isPlaying() ? 0.34f : 0.18f, AnimationSpecKt.spring$default(0.92f, 320.0f, null, 4, null), 0.0f, "artwork-shadow-alpha-v4", null, $composer2, 3120, 20);
            BoxWithConstraintsKt.BoxWithConstraints(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, false, ComposableLambdaKt.rememberComposableLambda(-1123278594, true, new Function3() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return MeloXIOSNowPlayingV2Kt.MeloXArtworkPageV3$lambda$3(stateAnimateFloatAsState, drawArtwork, state, stateM940animateDpAsStateAjpBEmI, stateAnimateFloatAsState2, (BoxWithConstraintsScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer2, 54), $composer2, 3078, 6);
            $composer2 = $composer2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXIOSNowPlayingV2Kt.MeloXArtworkPageV3$lambda$4(state, drawArtwork, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float MeloXArtworkPageV3$lambda$0(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float MeloXArtworkPageV3$lambda$1(State<Dp> state) {
        return ((Dp) state.getValue()).m8919unboximpl();
    }

    private static final float MeloXArtworkPageV3$lambda$2(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    static final Unit MeloXArtworkPageV3$lambda$3(final State $artworkScale$delegate, boolean $drawArtwork, MeloXPlaybackUiState $state, State $shadowElevation$delegate, State $shadowAlpha$delegate, BoxWithConstraintsScope BoxWithConstraints, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function2;
        Intrinsics.checkNotNullParameter(BoxWithConstraints, "$this$BoxWithConstraints");
        ComposerKt.sourceInformation($composer, "C304@11065L2107:MeloXIOSNowPlayingV2.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(BoxWithConstraints) ? 4 : 2;
        }
        if ($composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1123278594, $dirty, -1, "com.lladlam.melox.ui.player.MeloXArtworkPageV3.<anonymous> (MeloXIOSNowPlayingV2.kt:299)");
            }
            float artworkSize = ((Dp) ComparisonsKt.maxOf(Dp.m8903boximpl(Dp.constructor_impl(170)), ComparisonsKt.minOf(Dp.m8903boximpl(Dp.constructor_impl(BoxWithConstraints.mo1523getMaxWidthD9Ej5fM() + Dp.constructor_impl(16))), Dp.m8903boximpl(Dp.constructor_impl(BoxWithConstraints.mo1522getMaxHeightD9Ej5fM() - Dp.constructor_impl(92)))))).m8919unboximpl();
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierFillMaxSize$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((390 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
                $composer.useNode();
            }
            Composer composerM5188constructorimpl = Updater.constructor_impl($composer);
            Updater.set_impl(composerM5188constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            int i3 = ((390 >> 6) & 112) | 6;
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, -1265069587, "C308@11208L27,313@11364L115,310@11249L1025,336@12288L30,338@12332L787,359@13133L29:MeloXIOSNowPlayingV2.kt#qhu5z0");
            SpacerKt.Spacer(ColumnScope.weight$default(columnScope, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 0);
            Modifier modifierM1872size3ABfNKs = SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, artworkSize);
            ComposerKt.sourceInformationMarkerStart($composer, -1703373529, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            boolean zChanged = $composer.changed($artworkScale$delegate);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda24
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MeloXIOSNowPlayingV2Kt.MeloXArtworkPageV3$lambda$3$0$0$0($artworkScale$delegate, (GraphicsLayerScope) obj);
                    }
                };
                $composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(modifierM1872size3ABfNKs, (Function1) objRememberedValue);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierGraphicsLayer);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function1 = constructor2;
                $composer.createNode(function1);
            } else {
                function1 = constructor2;
                $composer.useNode();
            }
            Composer composerM5188constructorimpl2 = Updater.constructor_impl($composer);
            Updater.set_impl(composerM5188constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i6 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1475201811, "C:MeloXIOSNowPlayingV2.kt#qhu5z0");
            if ($drawArtwork) {
                $composer.startReplaceGroup(1475218147);
                ComposerKt.sourceInformation($composer, "320@11605L637");
                String artworkUrl = $state.getArtworkUrl();
                Modifier modifierFillMaxSize$default2 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                float fMeloXArtworkPageV3$lambda$1 = MeloXArtworkPageV3$lambda$1($shadowElevation$delegate);
                RoundedCornerShape roundedCornerShapeM2135RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(12));
                long jM6094getBlack0d7_KjU = Color.INSTANCE.m6094getBlack0d7_KjU();
                long jM6066copywmQWz5c = Color.copy_wmQWz5c(jM6094getBlack0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6094getBlack0d7_KjU) : MeloXArtworkPageV3$lambda$2($shadowAlpha$delegate), (14 & 2) != 0 ? Color.getRed_impl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6094getBlack0d7_KjU) : 0.0f);
                long jM6094getBlack0d7_KjU2 = Color.INSTANCE.m6094getBlack0d7_KjU();
                MeloXPlayerUiKt.Artwork(artworkUrl, ClipKt.clip(ShadowKt.m5665shadows4CzXII(modifierFillMaxSize$default2, fMeloXArtworkPageV3$lambda$1, roundedCornerShapeM2135RoundedCornerShape0680j_4, false, jM6066copywmQWz5c, Color.copy_wmQWz5c(jM6094getBlack0d7_KjU2, (14 & 1) != 0 ? Color.getAlpha_impl(jM6094getBlack0d7_KjU2) : MeloXArtworkPageV3$lambda$2($shadowAlpha$delegate), (14 & 2) != 0 ? Color.getRed_impl(jM6094getBlack0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6094getBlack0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6094getBlack0d7_KjU2) : 0.0f)), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(12))), $composer, 0);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(1475868744);
                $composer.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            SpacerKt.Spacer(SizeKt.m1858height3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(20)), $composer, 6);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxWidth$default);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i7 = ((((6 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function2 = constructor3;
                $composer.createNode(function2);
            } else {
                function2 = constructor3;
                $composer.useNode();
            }
            Composer composerM5188constructorimpl3 = Updater.constructor_impl($composer);
            Updater.set_impl(composerM5188constructorimpl3, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i8 = (i7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i9 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1448070447, "C339@12393L343,348@12753L352:MeloXIOSNowPlayingV2.kt#qhu5z0");
            String title = $state.getTitle();
            if (StringsKt.isBlank(title)) {
                title = "正在播放";
            }
            TextKt.m3912TextNvy7gAk(title, null, Color.INSTANCE.m6105getWhite0d7_KjU(), null, TextUnitKt.getSp(20), null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, TextUnitKt.getSp(24), TextOverflow.INSTANCE.m8816getEllipsisgIe3tQ8(), false, 1, 0, null, null, $composer, 1597824, 25008, 239530);
            String artist = $state.getArtist();
            long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
            TextKt.m3912TextNvy7gAk(artist, PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.constructor_impl(2), 0.0f, 0.0f, 13, null), Color.copy_wmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU) : 0.64f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU) : 0.0f), null, TextUnitKt.getSp(20), null, null, null, 0L, null, null, TextUnitKt.getSp(24), TextOverflow.INSTANCE.m8816getEllipsisgIe3tQ8(), false, 1, 0, null, null, $composer, 25008, 25008, 239592);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            SpacerKt.Spacer(SizeKt.m1858height3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(8)), $composer, 6);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXArtworkPageV3$lambda$3$0$0$0(State $artworkScale$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setScaleX(MeloXArtworkPageV3$lambda$0($artworkScale$delegate));
        graphicsLayer.setScaleY(MeloXArtworkPageV3$lambda$0($artworkScale$delegate));
        return Unit.INSTANCE;
    }

    private static final void MeloXBottomControlsV3(final MeloXPlaybackUiState state, final MeloXNowPlayingPage page, final Function1<? super MeloXNowPlayingPage, Unit> function1, Composer $composer, final int $changed) throws Throwable {
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        Composer $composer3 = $composer.startRestartGroup(-839460011);
        ComposerKt.sourceInformation($composer3, "C(MeloXBottomControlsV3)N(state,page,onPageSelected)370@13352L491:MeloXIOSNowPlayingV2.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(state) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(page.ordinal()) ? 32 : 16;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 256 : 128;
        }
        if (!$composer3.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-839460011, $dirty, -1, "com.lladlam.melox.ui.player.MeloXBottomControlsV3 (MeloXIOSNowPlayingV2.kt:369)");
            }
            Modifier modifierM1858height3ABfNKs = SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.constructor_impl(PLAYER_CONTROLS_HEIGHT));
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            $composer2 = $composer3;
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1858height3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((6 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor;
                $composer3.createNode(function0);
            } else {
                function0 = constructor;
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl = Updater.constructor_impl($composer3);
            Updater.set_impl(composerM5188constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 1895361023, "C375@13480L29,376@13518L30,377@13557L31,378@13597L30,379@13636L27,380@13672L29,381@13710L127:MeloXIOSNowPlayingV2.kt#qhu5z0");
            MeloXProgressControlV3(state, $composer3, $dirty & 14);
            SpacerKt.Spacer(SizeKt.m1858height3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(19)), $composer3, 6);
            MeloXTransportControlsV3(state, $composer3, $dirty & 14);
            SpacerKt.Spacer(SizeKt.m1858height3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(31)), $composer3, 6);
            MeloXVolumeControlV3(state, $composer3, $dirty & 14);
            SpacerKt.Spacer(SizeKt.m1858height3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(3)), $composer3, 6);
            MeloXPageSelectorV3(state, page, function1, $composer3, ($dirty & 14) | ($dirty & 112) | ($dirty & 896));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda50
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXIOSNowPlayingV2Kt.MeloXBottomControlsV3$lambda$1(state, page, function1, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void MeloXProgressControlV3(final MeloXPlaybackUiState state, Composer $composer, final int $changed) {
        Composer $composer2;
        float sourceProgress;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        long positionMs;
        Composer $composer3 = $composer.startRestartGroup(112420511);
        ComposerKt.sourceInformation($composer3, "C(MeloXProgressControlV3)N(state)396@14113L34,397@14173L48,398@14245L153,404@14446L62,404@14404L104,408@14514L2412:MeloXIOSNowPlayingV2.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(state) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (!$composer3.shouldExecute(($dirty2 & 3) != 2, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(112420511, $dirty2, -1, "com.lladlam.melox.ui.player.MeloXProgressControlV3 (MeloXIOSNowPlayingV2.kt:390)");
            }
            if (state.getDurationMs() > 0) {
                sourceProgress = RangesKt.coerceIn(state.getPositionMs() / state.getDurationMs(), 0.0f, 1.0f);
            } else {
                sourceProgress = 0.0f;
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 661936577, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            Object objRememberedValue = $composer3.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default);
                objRememberedValue = objMutableStateOf$default;
            }
            final MutableState scrubbing$delegate = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 661938511, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            Object objRememberedValue2 = $composer3.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object objMutableFloatStateOf = PrimitiveSnapshotStateKt.mutableFloatStateOf(sourceProgress);
                $composer3.updateRememberedValue(objMutableFloatStateOf);
                objRememberedValue2 = objMutableFloatStateOf;
            }
            final MutableFloatState localProgress$delegate = (MutableFloatState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final State<Dp> stateM940animateDpAsStateAjpBEmI = AnimateAsStateKt.m940animateDpAsStateAjpBEmI(MeloXProgressControlV3$lambda$1(scrubbing$delegate) ? Dp.constructor_impl(6) : Dp.constructor_impl(4), AnimationSpecKt.tween$default(120, 0, null, 6, null), "progress-track-height", null, $composer3, 432, 8);
            Float fValueOf = Float.valueOf(sourceProgress);
            Boolean boolValueOf = Boolean.valueOf(MeloXProgressControlV3$lambda$1(scrubbing$delegate));
            ComposerKt.sourceInformationMarkerStart($composer3, 661947261, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            boolean zChanged = $composer3.changed(sourceProgress);
            Object objRememberedValue3 = $composer3.rememberedValue();
            if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object obj = (Function2) new MeloXIOSNowPlayingV2Kt$MeloXProgressControlV3$1$1(sourceProgress, scrubbing$delegate, localProgress$delegate, null);
                $composer3.updateRememberedValue(obj);
                objRememberedValue3 = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(fValueOf, boolValueOf, (Function2) objRememberedValue3, $composer3, 0);
            Modifier modifierM1858height3ABfNKs = SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.constructor_impl(52));
            Arrangement.Vertical center = Arrangement.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, Alignment.INSTANCE.getStart(), $composer3, ((54 >> 3) & 14) | ((54 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            $composer2 = $composer3;
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1858height3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((54 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor;
                $composer3.createNode(function0);
            } else {
                function0 = constructor;
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl = Updater.constructor_impl($composer3);
            Updater.set_impl(composerM5188constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 1901237446, "C416@14743L100,420@14881L195,430@15247L579,414@14672L1165,448@15847L1073:MeloXIOSNowPlayingV2.kt#qhu5z0");
            float fMeloXProgressControlV3$lambda$4 = MeloXProgressControlV3$lambda$4(localProgress$delegate);
            Modifier modifierM1858height3ABfNKs2 = SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.constructor_impl(20));
            ComposerKt.sourceInformationMarkerStart($composer3, 1031161689, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            Object objRememberedValue4 = $composer3.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda25
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return MeloXIOSNowPlayingV2Kt.MeloXProgressControlV3$lambda$8$0$0(scrubbing$delegate, localProgress$delegate, ((Float) obj3).floatValue());
                    }
                };
                $composer3.updateRememberedValue(obj2);
                objRememberedValue4 = obj2;
            }
            Function1 function2 = (Function1) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 1031166200, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            boolean z = ($dirty2 & 14) == 4;
            Object objRememberedValue5 = $composer3.rememberedValue();
            if (z || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                Object obj3 = new Function0() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda26
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MeloXIOSNowPlayingV2Kt.MeloXProgressControlV3$lambda$8$1$0(state, localProgress$delegate, scrubbing$delegate);
                    }
                };
                $composer3.updateRememberedValue(obj3);
                objRememberedValue5 = obj3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            SliderKt.Slider(fMeloXProgressControlV3$lambda$4, function2, modifierM1858height3ABfNKs2, false, (Function0) objRememberedValue5, null, null, 0, ComposableSingletons$MeloXIOSNowPlayingV2Kt.INSTANCE.getLambda$1770962769$app(), ComposableLambdaKt.rememberComposableLambda(1362939154, true, new Function3() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda27
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj4, Object obj5, Object obj6) {
                    return MeloXIOSNowPlayingV2Kt.MeloXProgressControlV3$lambda$8$2(stateM940animateDpAsStateAjpBEmI, localProgress$delegate, (SliderState) obj4, (Composer) obj5, ((Integer) obj6).intValue());
                }
            }, $composer3, 54), null, $composer3, 905970096, 0, 1256);
            Modifier modifierM1858height3ABfNKs3 = SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.constructor_impl(26));
            ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer3, modifierM1858height3ABfNKs3);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((6 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function1 = constructor2;
                $composer3.createNode(function1);
            } else {
                function1 = constructor2;
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl2 = Updater.constructor_impl($composer3);
            Updater.set_impl(composerM5188constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            int i6 = ((6 >> 6) & 112) | 6;
            BoxScope boxScope = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -744076322, "C458@16154L280,466@16448L125,471@16587L323:MeloXIOSNowPlayingV2.kt#qhu5z0");
            if (MeloXProgressControlV3$lambda$1(scrubbing$delegate)) {
                positionMs = MathKt.roundToLong(state.getDurationMs() * MeloXProgressControlV3$lambda$4(localProgress$delegate));
            } else {
                positionMs = state.getPositionMs();
            }
            String durationV3 = formatDurationV3(positionMs);
            Modifier modifierAlign = boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterStart());
            long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
            TextKt.m3912TextNvy7gAk(durationV3, modifierAlign, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU) : 0.5f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU) : 0.0f), null, TextUnitKt.getSp(11), null, FontWeight.INSTANCE.getMedium(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 1597824, 0, 262056);
            MeloXQualityChipV3(state, boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenter()), $composer3, $dirty2 & 14, 0);
            String str = "−" + formatDurationV3(RangesKt.coerceAtLeast(state.getDurationMs() - positionMs, 0L));
            Modifier modifierAlign2 = boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterEnd());
            long jM6105getWhite0d7_KjU2 = Color.INSTANCE.m6105getWhite0d7_KjU();
            TextKt.m3912TextNvy7gAk(str, modifierAlign2, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU2, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU2) : 0.5f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU2) : 0.0f), null, TextUnitKt.getSp(11), null, FontWeight.INSTANCE.getMedium(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 1597824, 0, 262056);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda28
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj4, Object obj5) {
                    return MeloXIOSNowPlayingV2Kt.MeloXProgressControlV3$lambda$9(state, $changed, (Composer) obj4, ((Integer) obj5).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean MeloXProgressControlV3$lambda$1(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void MeloXProgressControlV3$lambda$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final float MeloXProgressControlV3$lambda$4(MutableFloatState $localProgress$delegate) {
        return $localProgress$delegate.getFloatValue();
    }

    private static final float MeloXProgressControlV3$lambda$6(State<Dp> state) {
        return ((Dp) state.getValue()).m8919unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXProgressControlV3$lambda$8$0$0(MutableState $scrubbing$delegate, MutableFloatState $localProgress$delegate, float it) {
        MeloXProgressControlV3$lambda$2($scrubbing$delegate, true);
        $localProgress$delegate.setFloatValue(RangesKt.coerceIn(it, 0.0f, 1.0f));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXProgressControlV3$lambda$8$1$0(MeloXPlaybackUiState $state, MutableFloatState $localProgress$delegate, MutableState $scrubbing$delegate) {
        if ($state.getDurationMs() > 0) {
            $state.seekTo(MathKt.roundToLong($state.getDurationMs() * MeloXProgressControlV3$lambda$4($localProgress$delegate)));
        }
        MeloXProgressControlV3$lambda$2($scrubbing$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXProgressControlV3$lambda$8$2(State $trackHeight$delegate, MutableFloatState $localProgress$delegate, SliderState it, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation($composer, "CN(it)431@15265L547:MeloXIOSNowPlayingV2.kt#qhu5z0");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1362939154, $changed, -1, "com.lladlam.melox.ui.player.MeloXProgressControlV3.<anonymous>.<anonymous> (MeloXIOSNowPlayingV2.kt:431)");
            }
            Modifier modifierClip = ClipKt.clip(SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), MeloXProgressControlV3$lambda$6($trackHeight$delegate)), RoundedCornerShapeKt.getCircleShape());
            long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
            Modifier modifierM1043backgroundbw27NRU$default = BackgroundKt.m1043backgroundbw27NRU$default(modifierClip, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU) : 0.2f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU) : 0.0f), null, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM1043backgroundbw27NRU$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
                $composer.useNode();
            }
            Composer composerM5188constructorimpl = Updater.constructor_impl($composer);
            Updater.set_impl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1425123393, "C438@15548L246:MeloXIOSNowPlayingV2.kt#qhu5z0");
            Modifier modifierFillMaxHeight$default = SizeKt.fillMaxHeight$default(SizeKt.fillMaxWidth(Modifier.INSTANCE, MeloXProgressControlV3$lambda$4($localProgress$delegate)), 0.0f, 1, null);
            long jM6105getWhite0d7_KjU2 = Color.INSTANCE.m6105getWhite0d7_KjU();
            BoxKt.Box(BackgroundKt.m1043backgroundbw27NRU$default(modifierFillMaxHeight$default, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU2, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU2) : 0.96f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU2) : 0.0f), null, 2, null), $composer, 0);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:102:0x02c7  */
    /* JADX WARN: Code duplicated, block: B:103:0x02cb  */
    /* JADX WARN: Code duplicated, block: B:106:0x0375  */
    /* JADX WARN: Code duplicated, block: B:109:0x0381  */
    /* JADX WARN: Code duplicated, block: B:110:0x0387  */
    /* JADX WARN: Code duplicated, block: B:113:0x041c  */
    /* JADX WARN: Code duplicated, block: B:116:0x0427  */
    /* JADX WARN: Code duplicated, block: B:117:0x042a  */
    /* JADX WARN: Code duplicated, block: B:121:0x04c5  */
    /* JADX WARN: Code duplicated, block: B:122:0x04d3  */
    /* JADX WARN: Code duplicated, block: B:125:0x0595  */
    /* JADX WARN: Code duplicated, block: B:128:0x05a1  */
    /* JADX WARN: Code duplicated, block: B:129:0x05a7  */
    /* JADX WARN: Code duplicated, block: B:132:0x06a6  */
    /* JADX WARN: Code duplicated, block: B:135:0x06b2  */
    /* JADX WARN: Code duplicated, block: B:136:0x06b8  */
    /* JADX WARN: Code duplicated, block: B:139:0x07fc  */
    /* JADX WARN: Code duplicated, block: B:143:0x086a  */
    /* JADX WARN: Code duplicated, block: B:42:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:43:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:50:0x011c  */
    /* JADX WARN: Code duplicated, block: B:57:0x016d  */
    /* JADX WARN: Code duplicated, block: B:59:0x0176  */
    /* JADX WARN: Code duplicated, block: B:60:0x0181  */
    /* JADX WARN: Code duplicated, block: B:68:0x01c1  */
    /* JADX WARN: Code duplicated, block: B:71:0x01ed  */
    /* JADX WARN: Code duplicated, block: B:72:0x01ef  */
    /* JADX WARN: Code duplicated, block: B:83:0x0244  */
    /* JADX WARN: Code duplicated, block: B:84:0x0246  */
    /* JADX WARN: Code duplicated, block: B:95:0x0288  */
    /* JADX WARN: Code duplicated, block: B:98:0x02a4  */
    private static final void MeloXQualityChipV3(final MeloXPlaybackUiState meloXPlaybackUiState, Modifier modifier, Composer composer, final int i, final int i2) {
        final Modifier modifier2;
        Composer composer2;
        Modifier modifier3;
        boolean z;
        Object objRememberedValue;
        final MutableState mutableState;
        boolean zChanged;
        Object objRememberedValue2;
        MutableState mutableState2;
        boolean zChanged2;
        Object objRememberedValue3;
        String mediaId;
        Long longOrNull;
        boolean zChanged3;
        Object objRememberedValue4;
        boolean z2;
        boolean zChanged4;
        Object objRememberedValue5;
        boolean z3;
        boolean zChanged5;
        Object objRememberedValue6;
        MusicQuality musicQualityMeloXQualityChipV3$lambda$8;
        Object objRememberedValue7;
        MutableInteractionSource mutableInteractionSource;
        float f;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function0;
        boolean zChanged6;
        Object objRememberedValue8;
        Composer composer3;
        Composer composer4;
        Object objRememberedValue9;
        Function0<ComposeUiNode> constructor2;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> constructor3;
        Function0<ComposeUiNode> function2;
        Object objRememberedValue10;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1246474753);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(MeloXQualityChipV3)N(state,modifier)487@17077L7,488@17128L139,493@17288L34,494@17343L153,499@17515L116,502@17656L85,506@17777L219,506@17747L249,511@18041L199,511@18001L239,520@18308L39,521@18379L25,522@18422L263,531@18691L2332:MeloXIOSNowPlayingV2.kt#qhu5z0");
        int i3 = i;
        if ((i & 6) == 0) {
            i3 |= composerStartRestartGroup.changed(meloXPlaybackUiState) ? 4 : 2;
        }
        int i4 = i2 & 2;
        if (i4 != 0) {
            i3 |= 48;
            modifier2 = modifier;
        } else if ((i & 48) == 0) {
            modifier2 = modifier;
            i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i5 = i3;
        if (!composerStartRestartGroup.shouldExecute((i5 & 19) != 18, i5 & 1)) {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        } else {
            if (i4 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1246474753, i5, -1, "com.lladlam.melox.ui.player.MeloXQualityChipV3 (MeloXIOSNowPlayingV2.kt:486)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Context applicationContext = ((Context) objConsume).getApplicationContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 433643786, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            boolean zChanged7 = composerStartRestartGroup.changed(applicationContext);
            Object objRememberedValue11 = composerStartRestartGroup.rememberedValue();
            if (!zChanged7) {
                z = false;
                if (objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                }
                NeteaseQualityClient neteaseQualityClient = (NeteaseQualityClient) objRememberedValue11;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 433648801, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    MutableState mutableStateMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(z), null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default);
                    objRememberedValue = mutableStateMutableStateOf$default;
                }
                mutableState = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 433650680, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(applicationContext);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    MusicQualityPreferences musicQualityPreferences = MusicQualityPreferences.INSTANCE;
                    Intrinsics.checkNotNull(applicationContext);
                    MusicQuality musicQuality = musicQualityPreferences.read(applicationContext);
                    MusicQualityRuntime.INSTANCE.setSelected(musicQuality);
                    MutableState mutableStateMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(musicQuality, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default2);
                    objRememberedValue2 = mutableStateMutableStateOf$default2;
                }
                mutableState2 = (MutableState) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                String mediaId2 = meloXPlaybackUiState.getMediaId();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 433656147, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
                zChanged2 = composerStartRestartGroup.changed(mediaId2);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    MusicQualityRuntime musicQualityRuntime = MusicQualityRuntime.INSTANCE;
                    mediaId = meloXPlaybackUiState.getMediaId();
                    if (mediaId != null) {
                        longOrNull = StringsKt.toLongOrNull(mediaId);
                    } else {
                        longOrNull = null;
                    }
                    MutableState mutableStateMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(musicQualityRuntime.actualFor(longOrNull), null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default3);
                    objRememberedValue3 = mutableStateMutableStateOf$default3;
                } else {
                    mutableState2 = mutableState2;
                }
                final MutableState mutableState3 = (MutableState) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                String mediaId3 = meloXPlaybackUiState.getMediaId();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 433660628, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
                zChanged3 = composerStartRestartGroup.changed(mediaId3);
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (!zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    MutableState mutableStateMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(SongAudioAvailability.INSTANCE.getUnknown(), null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default4);
                    objRememberedValue4 = mutableStateMutableStateOf$default4;
                }
                final MutableState mutableState4 = (MutableState) objRememberedValue4;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                String mediaId4 = meloXPlaybackUiState.getMediaId();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 433664634, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
                if ((i5 & 14) == 4) {
                    z2 = true;
                } else {
                    z2 = z;
                }
                zChanged4 = z2 | composerStartRestartGroup.changed(mutableState4) | composerStartRestartGroup.changedInstance(neteaseQualityClient);
                objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (!zChanged4 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    MeloXIOSNowPlayingV2Kt$MeloXQualityChipV3$1$1 meloXIOSNowPlayingV2Kt$MeloXQualityChipV3$1$1 = new MeloXIOSNowPlayingV2Kt$MeloXQualityChipV3$1$1(meloXPlaybackUiState, neteaseQualityClient, mutableState4, null);
                    composerStartRestartGroup.updateRememberedValue(meloXIOSNowPlayingV2Kt$MeloXQualityChipV3$1$1);
                    objRememberedValue5 = meloXIOSNowPlayingV2Kt$MeloXQualityChipV3$1$1;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.LaunchedEffect(mediaId4, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue5, composerStartRestartGroup, z ? 1 : 0);
                String mediaId5 = meloXPlaybackUiState.getMediaId();
                MusicQuality musicQualityMeloXQualityChipV3$lambda$5 = MeloXQualityChipV3$lambda$5(mutableState2);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 433673062, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
                if ((i5 & 14) == 4) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                zChanged5 = z3 | composerStartRestartGroup.changed(mutableState3);
                objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                if (!zChanged5 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    MeloXIOSNowPlayingV2Kt$MeloXQualityChipV3$2$1 meloXIOSNowPlayingV2Kt$MeloXQualityChipV3$2$1 = new MeloXIOSNowPlayingV2Kt$MeloXQualityChipV3$2$1(meloXPlaybackUiState, mutableState3, null);
                    composerStartRestartGroup.updateRememberedValue(meloXIOSNowPlayingV2Kt$MeloXQualityChipV3$2$1);
                    objRememberedValue6 = meloXIOSNowPlayingV2Kt$MeloXQualityChipV3$2$1;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.LaunchedEffect(mediaId5, musicQualityMeloXQualityChipV3$lambda$5, (Function2) objRememberedValue6, composerStartRestartGroup, 0);
                musicQualityMeloXQualityChipV3$lambda$8 = MeloXQualityChipV3$lambda$8(mutableState3);
                if (musicQualityMeloXQualityChipV3$lambda$8 == null) {
                    musicQualityMeloXQualityChipV3$lambda$8 = MeloXQualityChipV3$lambda$5(mutableState2);
                }
                MusicQuality musicQuality2 = musicQualityMeloXQualityChipV3$lambda$8;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 433681446, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
                objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                    MutableInteractionSource MutableInteractionSource = InteractionSourceKt.MutableInteractionSource();
                    composerStartRestartGroup.updateRememberedValue(MutableInteractionSource);
                    objRememberedValue7 = MutableInteractionSource;
                }
                mutableInteractionSource = (MutableInteractionSource) objRememberedValue7;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (MeloXQualityChipV3$lambda$16(PressInteractionKt.collectIsPressedAsState(mutableInteractionSource, composerStartRestartGroup, 6))) {
                    f = 0.94f;
                } else {
                    f = 1.0f;
                }
                final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, AnimationSpecKt.spring$default(0.5f, 10000.0f, null, 4, null), 0.0f, "quality-chip-press", null, composerStartRestartGroup, 3120, 20);
                int i6 = (i5 >> 3) & 14;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                int i7 = (i6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                composer2 = composerStartRestartGroup;
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i8 = ((i7 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    function0 = constructor;
                    composerStartRestartGroup.createNode(function0);
                } else {
                    function0 = constructor;
                    composerStartRestartGroup.useNode();
                }
                Composer composerM5188constructorimpl = Updater.constructor_impl(composerStartRestartGroup);
                Updater.set_impl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i9 = (i8 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i10 = ((i6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1165663959, "C534@18794L89,540@18980L242,549@19361L19,532@18726L1466,575@20280L20,576@20312L705,573@20202L815:MeloXIOSNowPlayingV2.kt#qhu5z0");
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1007433362, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
                zChanged6 = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                objRememberedValue8 = composerStartRestartGroup.rememberedValue();
                if (!zChanged6) {
                    composer3 = composerStartRestartGroup;
                    if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    Modifier modifierClip = ClipKt.clip(SizeKt.m1858height3ABfNKs(GraphicsLayerModifierKt.graphicsLayer(companion, (Function1) objRememberedValue8), Dp.constructor_impl(24)), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(7)));
                    RoundedCornerShape roundedCornerShapeM2135RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(7));
                    long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
                    Modifier modifierM9633meloXLiquidButtonNsDo4u0 = MeloXBackdropComponentsKt.m9633meloXLiquidButtonNsDo4u0(modifierClip, roundedCornerShapeM2135RoundedCornerShape0680j_4, false, 0L, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU) : 0.1f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU) : 0.0f), 0.0f, Dp.constructor_impl(6), Dp.constructor_impl(9), composer3, 14180352, 22);
                    composer4 = composer3;
                    ComposerKt.sourceInformationMarkerStart(composer4, 1007451436, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
                    objRememberedValue9 = composer4.rememberedValue();
                    if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue9 = new Function0() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda37
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return MeloXIOSNowPlayingV2Kt.MeloXQualityChipV3$lambda$18$1$0(mutableState);
                            }
                        };
                        composer4.updateRememberedValue(objRememberedValue9);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    Modifier modifierM1807paddingVpY3zN4$default = PaddingKt.m1807paddingVpY3zN4$default(ClickableKt.m1073clickableO2vRcR0(modifierM9633meloXLiquidButtonNsDo4u0, mutableInteractionSource, null, (24 & 4) != 0, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : null, (Function0) objRememberedValue9), Dp.constructor_impl(9), 0.0f, 2, null);
                    Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                    Arrangement.HorizontalOrVertical horizontalOrVerticalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(Dp.constructor_impl(5));
                    ComposerKt.sourceInformationMarkerStart(composer4, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM1497spacedBy0680j_4, centerVertically, composer4, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, 0));
                    CompositionLocalMap currentCompositionLocalMap2 = composer4.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer4, modifierM1807paddingVpY3zN4$default);
                    constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int i11 = ((((432 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!(composer4.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer4.startReusableNode();
                    if (composer4.getInserting()) {
                        function1 = constructor2;
                        composer4.createNode(function1);
                    } else {
                        function1 = constructor2;
                        composer4.useNode();
                    }
                    Composer composerM5188constructorimpl2 = Updater.constructor_impl(composer4);
                    Updater.set_impl(composerM5188constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.set_impl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.set_impl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.reconcile_impl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.set_impl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i12 = (i11 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer4, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                    int i13 = ((432 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer4, 576605166, "C554@19574L356,564@19943L239:MeloXIOSNowPlayingV2.kt#qhu5z0");
                    Modifier modifierM1872size3ABfNKs = SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(14));
                    Alignment center = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart(composer4, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                    ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, 0));
                    CompositionLocalMap currentCompositionLocalMap3 = composer4.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer4, modifierM1872size3ABfNKs);
                    constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                    int i14 = ((((54 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!(composer4.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer4.startReusableNode();
                    if (composer4.getInserting()) {
                        function2 = constructor3;
                        composer4.createNode(function2);
                    } else {
                        function2 = constructor3;
                        composer4.useNode();
                    }
                    Composer composerM5188constructorimpl3 = Updater.constructor_impl(composer4);
                    Updater.set_impl(composerM5188constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.set_impl(composerM5188constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.set_impl(composerM5188constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.reconcile_impl(composerM5188constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.set_impl(composerM5188constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                    int i15 = (i14 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer4, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                    int i16 = ((54 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer4, 777632361, "C558@19713L203:MeloXIOSNowPlayingV2.kt#qhu5z0");
                    CupertinoGlyphKind cupertinoGlyphKind = CupertinoGlyphKind.Waveform;
                    Modifier modifierM1872size3ABfNKs2 = SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(12));
                    long jM6105getWhite0d7_KjU2 = Color.INSTANCE.m6105getWhite0d7_KjU();
                    m9689CupertinoGlyphXOJAsU(cupertinoGlyphKind, modifierM1872size3ABfNKs2, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU2, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU2) : 0.86f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU2) : 0.0f), composer4, 438);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    String title = musicQuality2.getTitle();
                    long jM6105getWhite0d7_KjU3 = Color.INSTANCE.m6105getWhite0d7_KjU();
                    TextKt.m3912TextNvy7gAk(title, null, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU3, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU3) : 0.86f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU3) : 0.0f), null, TextUnitKt.getSp(11), null, FontWeight.INSTANCE.getMedium(), null, 0L, null, null, TextUnitKt.getSp(13), 0, false, 0, 0, null, null, composer4, 1597824, 48, 260010);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    boolean zMeloXQualityChipV3$lambda$2 = MeloXQualityChipV3$lambda$2(mutableState);
                    ComposerKt.sourceInformationMarkerStart(composer4, 1007480845, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
                    objRememberedValue10 = composer4.rememberedValue();
                    if (objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                        Function0 function3 = new Function0() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda38
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return MeloXIOSNowPlayingV2Kt.MeloXQualityChipV3$lambda$18$3$0(mutableState);
                            }
                        };
                        composer4.updateRememberedValue(function3);
                        objRememberedValue10 = function3;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    final MutableState mutableState5 = mutableState2;
                    AndroidMenu_androidKt.m2908DropdownMenuIlH_yew(zMeloXQualityChipV3$lambda$2, (Function0) objRememberedValue10, null, 0L, null, null, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-938125708, true, new Function3() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda39
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            return MeloXIOSNowPlayingV2Kt.MeloXQualityChipV3$lambda$18$4(mutableState5, mutableState3, applicationContext, mutableState4, mutableState, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, composer4, 54), composer4, 48, 48, 2044);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier3;
                } else {
                    composer3 = composerStartRestartGroup;
                }
                objRememberedValue8 = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda36
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MeloXIOSNowPlayingV2Kt.MeloXQualityChipV3$lambda$18$0$0(stateAnimateFloatAsState, (GraphicsLayerScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                Modifier modifierClip2 = ClipKt.clip(SizeKt.m1858height3ABfNKs(GraphicsLayerModifierKt.graphicsLayer(companion, (Function1) objRememberedValue8), Dp.constructor_impl(24)), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(7)));
                RoundedCornerShape roundedCornerShapeM2135RoundedCornerShape0680j_5 = RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(7));
                long jM6105getWhite0d7_KjU4 = Color.INSTANCE.m6105getWhite0d7_KjU();
                Modifier modifierM9633meloXLiquidButtonNsDo4u1 = MeloXBackdropComponentsKt.m9633meloXLiquidButtonNsDo4u0(modifierClip2, roundedCornerShapeM2135RoundedCornerShape0680j_5, false, 0L, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU4, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU4) : 0.1f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU4) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU4) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU4) : 0.0f), 0.0f, Dp.constructor_impl(6), Dp.constructor_impl(9), composer3, 14180352, 22);
                composer4 = composer3;
                ComposerKt.sourceInformationMarkerStart(composer4, 1007451436, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
                objRememberedValue9 = composer4.rememberedValue();
                if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue9 = new Function0() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda37
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MeloXIOSNowPlayingV2Kt.MeloXQualityChipV3$lambda$18$1$0(mutableState);
                        }
                    };
                    composer4.updateRememberedValue(objRememberedValue9);
                }
                ComposerKt.sourceInformationMarkerEnd(composer4);
                Modifier modifierM1807paddingVpY3zN4$default2 = PaddingKt.m1807paddingVpY3zN4$default(ClickableKt.m1073clickableO2vRcR0(modifierM9633meloXLiquidButtonNsDo4u1, mutableInteractionSource, null, (24 & 4) != 0, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : null, (Function0) objRememberedValue9), Dp.constructor_impl(9), 0.0f, 2, null);
                Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
                Arrangement.HorizontalOrVertical horizontalOrVerticalM1497spacedBy0680j_5 = Arrangement.INSTANCE.m1497spacedBy0680j_4(Dp.constructor_impl(5));
                ComposerKt.sourceInformationMarkerStart(composer4, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1497spacedBy0680j_5, centerVertically2, composer4, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, 0));
                CompositionLocalMap currentCompositionLocalMap4 = composer4.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composer4, modifierM1807paddingVpY3zN4$default2);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i17 = ((((432 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                if (!(composer4.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer4.startReusableNode();
                if (composer4.getInserting()) {
                    function1 = constructor2;
                    composer4.createNode(function1);
                } else {
                    function1 = constructor2;
                    composer4.useNode();
                }
                Composer composerM5188constructorimpl4 = Updater.constructor_impl(composer4);
                Updater.set_impl(composerM5188constructorimpl4, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.set_impl(composerM5188constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.set_impl(composerM5188constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.reconcile_impl(composerM5188constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.set_impl(composerM5188constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                int i18 = (i17 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer4, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                int i19 = ((432 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer4, 576605166, "C554@19574L356,564@19943L239:MeloXIOSNowPlayingV2.kt#qhu5z0");
                Modifier modifierM1872size3ABfNKs3 = SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(14));
                Alignment center2 = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart(composer4, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
                ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, 0));
                CompositionLocalMap currentCompositionLocalMap5 = composer4.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composer4, modifierM1872size3ABfNKs3);
                constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int i110 = ((((54 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                if (!(composer4.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer4.startReusableNode();
                if (composer4.getInserting()) {
                    function2 = constructor3;
                    composer4.createNode(function2);
                } else {
                    function2 = constructor3;
                    composer4.useNode();
                }
                Composer composerM5188constructorimpl5 = Updater.constructor_impl(composer4);
                Updater.set_impl(composerM5188constructorimpl5, measurePolicyMaybeCachedBoxMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.set_impl(composerM5188constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.set_impl(composerM5188constructorimpl5, Integer.valueOf(iHashCode5), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.reconcile_impl(composerM5188constructorimpl5, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.set_impl(composerM5188constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                int i111 = (i110 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer4, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                int i112 = ((54 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer4, 777632361, "C558@19713L203:MeloXIOSNowPlayingV2.kt#qhu5z0");
                CupertinoGlyphKind cupertinoGlyphKind2 = CupertinoGlyphKind.Waveform;
                Modifier modifierM1872size3ABfNKs4 = SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(12));
                long jM6105getWhite0d7_KjU5 = Color.INSTANCE.m6105getWhite0d7_KjU();
                m9689CupertinoGlyphXOJAsU(cupertinoGlyphKind2, modifierM1872size3ABfNKs4, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU5, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU5) : 0.86f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU5) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU5) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU5) : 0.0f), composer4, 438);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                composer4.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                String title2 = musicQuality2.getTitle();
                long jM6105getWhite0d7_KjU6 = Color.INSTANCE.m6105getWhite0d7_KjU();
                TextKt.m3912TextNvy7gAk(title2, null, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU6, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU6) : 0.86f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU6) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU6) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU6) : 0.0f), null, TextUnitKt.getSp(11), null, FontWeight.INSTANCE.getMedium(), null, 0L, null, null, TextUnitKt.getSp(13), 0, false, 0, 0, null, null, composer4, 1597824, 48, 260010);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                composer4.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                boolean zMeloXQualityChipV3$lambda$3 = MeloXQualityChipV3$lambda$2(mutableState);
                ComposerKt.sourceInformationMarkerStart(composer4, 1007480845, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
                objRememberedValue10 = composer4.rememberedValue();
                if (objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                    Function0 function4 = new Function0() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda38
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MeloXIOSNowPlayingV2Kt.MeloXQualityChipV3$lambda$18$3$0(mutableState);
                        }
                    };
                    composer4.updateRememberedValue(function4);
                    objRememberedValue10 = function4;
                }
                ComposerKt.sourceInformationMarkerEnd(composer4);
                final MutableState mutableState6 = mutableState2;
                AndroidMenu_androidKt.m2908DropdownMenuIlH_yew(zMeloXQualityChipV3$lambda$3, (Function0) objRememberedValue10, null, 0L, null, null, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-938125708, true, new Function3() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda39
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return MeloXIOSNowPlayingV2Kt.MeloXQualityChipV3$lambda$18$4(mutableState6, mutableState3, applicationContext, mutableState4, mutableState, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composer4, 54), composer4, 48, 48, 2044);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier3;
            } else {
                z = false;
            }
            NeteaseQualityClient neteaseQualityClient2 = new NeteaseQualityClient(new Function0() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda35
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MeloXIOSNowPlayingV2Kt.MeloXQualityChipV3$lambda$0$0(applicationContext);
                }
            }, null, 2, null);
            composerStartRestartGroup.updateRememberedValue(neteaseQualityClient2);
            objRememberedValue11 = neteaseQualityClient2;
            NeteaseQualityClient neteaseQualityClient3 = (NeteaseQualityClient) objRememberedValue11;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 433648801, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(z), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default5);
                objRememberedValue = mutableStateMutableStateOf$default5;
            }
            mutableState = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 433650680, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            zChanged = composerStartRestartGroup.changed(applicationContext);
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!zChanged) {
                MusicQualityPreferences musicQualityPreferences2 = MusicQualityPreferences.INSTANCE;
                Intrinsics.checkNotNull(applicationContext);
                MusicQuality musicQuality3 = musicQualityPreferences2.read(applicationContext);
                MusicQualityRuntime.INSTANCE.setSelected(musicQuality3);
                MutableState mutableStateMutableStateOf$default6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(musicQuality3, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default6);
                objRememberedValue2 = mutableStateMutableStateOf$default6;
            } else {
                MusicQualityPreferences musicQualityPreferences3 = MusicQualityPreferences.INSTANCE;
                Intrinsics.checkNotNull(applicationContext);
                MusicQuality musicQuality4 = musicQualityPreferences3.read(applicationContext);
                MusicQualityRuntime.INSTANCE.setSelected(musicQuality4);
                MutableState mutableStateMutableStateOf$default7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(musicQuality4, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default7);
                objRememberedValue2 = mutableStateMutableStateOf$default7;
            }
            mutableState2 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            String mediaId6 = meloXPlaybackUiState.getMediaId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 433656147, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            zChanged2 = composerStartRestartGroup.changed(mediaId6);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (!zChanged2) {
                MusicQualityRuntime musicQualityRuntime2 = MusicQualityRuntime.INSTANCE;
                mediaId = meloXPlaybackUiState.getMediaId();
                if (mediaId != null) {
                    longOrNull = StringsKt.toLongOrNull(mediaId);
                } else {
                    longOrNull = null;
                }
                MutableState mutableStateMutableStateOf$default8 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(musicQualityRuntime2.actualFor(longOrNull), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default8);
                objRememberedValue3 = mutableStateMutableStateOf$default8;
            } else {
                MusicQualityRuntime musicQualityRuntime3 = MusicQualityRuntime.INSTANCE;
                mediaId = meloXPlaybackUiState.getMediaId();
                if (mediaId != null) {
                    longOrNull = StringsKt.toLongOrNull(mediaId);
                } else {
                    longOrNull = null;
                }
                MutableState mutableStateMutableStateOf$default9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(musicQualityRuntime3.actualFor(longOrNull), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default9);
                objRememberedValue3 = mutableStateMutableStateOf$default9;
            }
            final MutableState mutableState7 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            String mediaId7 = meloXPlaybackUiState.getMediaId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 433660628, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            zChanged3 = composerStartRestartGroup.changed(mediaId7);
            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (!zChanged3) {
                MutableState mutableStateMutableStateOf$default10 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(SongAudioAvailability.INSTANCE.getUnknown(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default10);
                objRememberedValue4 = mutableStateMutableStateOf$default10;
            } else {
                MutableState mutableStateMutableStateOf$default11 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(SongAudioAvailability.INSTANCE.getUnknown(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default11);
                objRememberedValue4 = mutableStateMutableStateOf$default11;
            }
            final MutableState mutableState8 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            String mediaId8 = meloXPlaybackUiState.getMediaId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 433664634, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            if ((i5 & 14) == 4) {
                z2 = true;
            } else {
                z2 = z;
            }
            zChanged4 = z2 | composerStartRestartGroup.changed(mutableState8) | composerStartRestartGroup.changedInstance(neteaseQualityClient3);
            objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (!zChanged4) {
            }
            MeloXIOSNowPlayingV2Kt$MeloXQualityChipV3$1$1 meloXIOSNowPlayingV2Kt$MeloXQualityChipV3$1$2 = new MeloXIOSNowPlayingV2Kt$MeloXQualityChipV3$1$1(meloXPlaybackUiState, neteaseQualityClient3, mutableState8, null);
            composerStartRestartGroup.updateRememberedValue(meloXIOSNowPlayingV2Kt$MeloXQualityChipV3$1$2);
            objRememberedValue5 = meloXIOSNowPlayingV2Kt$MeloXQualityChipV3$1$2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.LaunchedEffect(mediaId8, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue5, composerStartRestartGroup, z ? 1 : 0);
            String mediaId9 = meloXPlaybackUiState.getMediaId();
            MusicQuality musicQualityMeloXQualityChipV3$lambda$6 = MeloXQualityChipV3$lambda$5(mutableState2);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 433673062, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            if ((i5 & 14) == 4) {
                z3 = true;
            } else {
                z3 = false;
            }
            zChanged5 = z3 | composerStartRestartGroup.changed(mutableState7);
            objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (!zChanged5) {
            }
            MeloXIOSNowPlayingV2Kt$MeloXQualityChipV3$2$1 meloXIOSNowPlayingV2Kt$MeloXQualityChipV3$2$2 = new MeloXIOSNowPlayingV2Kt$MeloXQualityChipV3$2$1(meloXPlaybackUiState, mutableState7, null);
            composerStartRestartGroup.updateRememberedValue(meloXIOSNowPlayingV2Kt$MeloXQualityChipV3$2$2);
            objRememberedValue6 = meloXIOSNowPlayingV2Kt$MeloXQualityChipV3$2$2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.LaunchedEffect(mediaId9, musicQualityMeloXQualityChipV3$lambda$6, (Function2) objRememberedValue6, composerStartRestartGroup, 0);
            musicQualityMeloXQualityChipV3$lambda$8 = MeloXQualityChipV3$lambda$8(mutableState7);
            if (musicQualityMeloXQualityChipV3$lambda$8 == null) {
                musicQualityMeloXQualityChipV3$lambda$8 = MeloXQualityChipV3$lambda$5(mutableState2);
            }
            MusicQuality musicQuality5 = musicQualityMeloXQualityChipV3$lambda$8;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 433681446, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                MutableInteractionSource MutableInteractionSource2 = InteractionSourceKt.MutableInteractionSource();
                composerStartRestartGroup.updateRememberedValue(MutableInteractionSource2);
                objRememberedValue7 = MutableInteractionSource2;
            }
            mutableInteractionSource = (MutableInteractionSource) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (MeloXQualityChipV3$lambda$16(PressInteractionKt.collectIsPressedAsState(mutableInteractionSource, composerStartRestartGroup, 6))) {
                f = 0.94f;
            } else {
                f = 1.0f;
            }
            final State stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(f, AnimationSpecKt.spring$default(0.5f, 10000.0f, null, 4, null), 0.0f, "quality-chip-press", null, composerStartRestartGroup, 3120, 20);
            int i20 = (i5 >> 3) & 14;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            int i21 = (i20 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            composer2 = composerStartRestartGroup;
            Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i22 = ((i21 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                function0 = constructor;
                composerStartRestartGroup.createNode(function0);
            } else {
                function0 = constructor;
                composerStartRestartGroup.useNode();
            }
            Composer composerM5188constructorimpl6 = Updater.constructor_impl(composerStartRestartGroup);
            Updater.set_impl(composerM5188constructorimpl6, measurePolicyMaybeCachedBoxMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl6, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl6, Integer.valueOf(iHashCode6), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl6, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl6, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
            int i23 = (i22 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
            int i113 = ((i20 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1165663959, "C534@18794L89,540@18980L242,549@19361L19,532@18726L1466,575@20280L20,576@20312L705,573@20202L815:MeloXIOSNowPlayingV2.kt#qhu5z0");
            Modifier.Companion companion2 = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1007433362, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            zChanged6 = composerStartRestartGroup.changed(stateAnimateFloatAsState2);
            objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (!zChanged6) {
                composer3 = composerStartRestartGroup;
                if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd(composer3);
                Modifier modifierClip3 = ClipKt.clip(SizeKt.m1858height3ABfNKs(GraphicsLayerModifierKt.graphicsLayer(companion2, (Function1) objRememberedValue8), Dp.constructor_impl(24)), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(7)));
                RoundedCornerShape roundedCornerShapeM2135RoundedCornerShape0680j_6 = RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(7));
                long jM6105getWhite0d7_KjU7 = Color.INSTANCE.m6105getWhite0d7_KjU();
                Modifier modifierM9633meloXLiquidButtonNsDo4u2 = MeloXBackdropComponentsKt.m9633meloXLiquidButtonNsDo4u0(modifierClip3, roundedCornerShapeM2135RoundedCornerShape0680j_6, false, 0L, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU7, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU7) : 0.1f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU7) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU7) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU7) : 0.0f), 0.0f, Dp.constructor_impl(6), Dp.constructor_impl(9), composer3, 14180352, 22);
                composer4 = composer3;
                ComposerKt.sourceInformationMarkerStart(composer4, 1007451436, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
                objRememberedValue9 = composer4.rememberedValue();
                if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue9 = new Function0() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda37
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MeloXIOSNowPlayingV2Kt.MeloXQualityChipV3$lambda$18$1$0(mutableState);
                        }
                    };
                    composer4.updateRememberedValue(objRememberedValue9);
                }
                ComposerKt.sourceInformationMarkerEnd(composer4);
                Modifier modifierM1807paddingVpY3zN4$default3 = PaddingKt.m1807paddingVpY3zN4$default(ClickableKt.m1073clickableO2vRcR0(modifierM9633meloXLiquidButtonNsDo4u2, mutableInteractionSource, null, (24 & 4) != 0, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : null, (Function0) objRememberedValue9), Dp.constructor_impl(9), 0.0f, 2, null);
                Alignment.Vertical centerVertically3 = Alignment.INSTANCE.getCenterVertically();
                Arrangement.HorizontalOrVertical horizontalOrVerticalM1497spacedBy0680j_6 = Arrangement.INSTANCE.m1497spacedBy0680j_4(Dp.constructor_impl(5));
                ComposerKt.sourceInformationMarkerStart(composer4, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1497spacedBy0680j_6, centerVertically3, composer4, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode7 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, 0));
                CompositionLocalMap currentCompositionLocalMap7 = composer4.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composer4, modifierM1807paddingVpY3zN4$default3);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i114 = ((((432 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                if (!(composer4.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer4.startReusableNode();
                if (composer4.getInserting()) {
                    function1 = constructor2;
                    composer4.createNode(function1);
                } else {
                    function1 = constructor2;
                    composer4.useNode();
                }
                Composer composerM5188constructorimpl7 = Updater.constructor_impl(composer4);
                Updater.set_impl(composerM5188constructorimpl7, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.set_impl(composerM5188constructorimpl7, currentCompositionLocalMap7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.set_impl(composerM5188constructorimpl7, Integer.valueOf(iHashCode7), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.reconcile_impl(composerM5188constructorimpl7, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.set_impl(composerM5188constructorimpl7, modifierMaterializeModifier7, ComposeUiNode.INSTANCE.getSetModifier());
                int i115 = (i114 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer4, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
                int i116 = ((432 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer4, 576605166, "C554@19574L356,564@19943L239:MeloXIOSNowPlayingV2.kt#qhu5z0");
                Modifier modifierM1872size3ABfNKs5 = SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(14));
                Alignment center3 = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart(composer4, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy5 = BoxKt.maybeCachedBoxMeasurePolicy(center3, false);
                ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode8 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, 0));
                CompositionLocalMap currentCompositionLocalMap8 = composer4.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composer4, modifierM1872size3ABfNKs5);
                constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int i117 = ((((54 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                if (!(composer4.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer4.startReusableNode();
                if (composer4.getInserting()) {
                    function2 = constructor3;
                    composer4.createNode(function2);
                } else {
                    function2 = constructor3;
                    composer4.useNode();
                }
                Composer composerM5188constructorimpl8 = Updater.constructor_impl(composer4);
                Updater.set_impl(composerM5188constructorimpl8, measurePolicyMaybeCachedBoxMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.set_impl(composerM5188constructorimpl8, currentCompositionLocalMap8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.set_impl(composerM5188constructorimpl8, Integer.valueOf(iHashCode8), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.reconcile_impl(composerM5188constructorimpl8, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.set_impl(composerM5188constructorimpl8, modifierMaterializeModifier8, ComposeUiNode.INSTANCE.getSetModifier());
                int i118 = (i117 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer4, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                int i119 = ((54 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer4, 777632361, "C558@19713L203:MeloXIOSNowPlayingV2.kt#qhu5z0");
                CupertinoGlyphKind cupertinoGlyphKind3 = CupertinoGlyphKind.Waveform;
                Modifier modifierM1872size3ABfNKs6 = SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(12));
                long jM6105getWhite0d7_KjU8 = Color.INSTANCE.m6105getWhite0d7_KjU();
                m9689CupertinoGlyphXOJAsU(cupertinoGlyphKind3, modifierM1872size3ABfNKs6, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU8, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU8) : 0.86f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU8) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU8) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU8) : 0.0f), composer4, 438);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                composer4.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                String title3 = musicQuality5.getTitle();
                long jM6105getWhite0d7_KjU9 = Color.INSTANCE.m6105getWhite0d7_KjU();
                TextKt.m3912TextNvy7gAk(title3, null, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU9, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU9) : 0.86f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU9) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU9) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU9) : 0.0f), null, TextUnitKt.getSp(11), null, FontWeight.INSTANCE.getMedium(), null, 0L, null, null, TextUnitKt.getSp(13), 0, false, 0, 0, null, null, composer4, 1597824, 48, 260010);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                composer4.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                boolean zMeloXQualityChipV3$lambda$4 = MeloXQualityChipV3$lambda$2(mutableState);
                ComposerKt.sourceInformationMarkerStart(composer4, 1007480845, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
                objRememberedValue10 = composer4.rememberedValue();
                if (objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                    Function0 function5 = new Function0() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda38
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MeloXIOSNowPlayingV2Kt.MeloXQualityChipV3$lambda$18$3$0(mutableState);
                        }
                    };
                    composer4.updateRememberedValue(function5);
                    objRememberedValue10 = function5;
                }
                ComposerKt.sourceInformationMarkerEnd(composer4);
                final MutableState mutableState9 = mutableState2;
                AndroidMenu_androidKt.m2908DropdownMenuIlH_yew(zMeloXQualityChipV3$lambda$4, (Function0) objRememberedValue10, null, 0L, null, null, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-938125708, true, new Function3() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda39
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return MeloXIOSNowPlayingV2Kt.MeloXQualityChipV3$lambda$18$4(mutableState9, mutableState7, applicationContext, mutableState8, mutableState, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composer4, 54), composer4, 48, 48, 2044);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier3;
            } else {
                composer3 = composerStartRestartGroup;
            }
            objRememberedValue8 = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda36
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MeloXIOSNowPlayingV2Kt.MeloXQualityChipV3$lambda$18$0$0(stateAnimateFloatAsState2, (GraphicsLayerScope) obj);
                }
            };
            composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            Modifier modifierClip4 = ClipKt.clip(SizeKt.m1858height3ABfNKs(GraphicsLayerModifierKt.graphicsLayer(companion2, (Function1) objRememberedValue8), Dp.constructor_impl(24)), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(7)));
            RoundedCornerShape roundedCornerShapeM2135RoundedCornerShape0680j_7 = RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(7));
            long jM6105getWhite0d7_KjU10 = Color.INSTANCE.m6105getWhite0d7_KjU();
            Modifier modifierM9633meloXLiquidButtonNsDo4u3 = MeloXBackdropComponentsKt.m9633meloXLiquidButtonNsDo4u0(modifierClip4, roundedCornerShapeM2135RoundedCornerShape0680j_7, false, 0L, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU10, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU10) : 0.1f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU10) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU10) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU10) : 0.0f), 0.0f, Dp.constructor_impl(6), Dp.constructor_impl(9), composer3, 14180352, 22);
            composer4 = composer3;
            ComposerKt.sourceInformationMarkerStart(composer4, 1007451436, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            objRememberedValue9 = composer4.rememberedValue();
            if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue9 = new Function0() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda37
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MeloXIOSNowPlayingV2Kt.MeloXQualityChipV3$lambda$18$1$0(mutableState);
                    }
                };
                composer4.updateRememberedValue(objRememberedValue9);
            }
            ComposerKt.sourceInformationMarkerEnd(composer4);
            Modifier modifierM1807paddingVpY3zN4$default4 = PaddingKt.m1807paddingVpY3zN4$default(ClickableKt.m1073clickableO2vRcR0(modifierM9633meloXLiquidButtonNsDo4u3, mutableInteractionSource, null, (24 & 4) != 0, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : null, (Function0) objRememberedValue9), Dp.constructor_impl(9), 0.0f, 2, null);
            Alignment.Vertical centerVertically4 = Alignment.INSTANCE.getCenterVertically();
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1497spacedBy0680j_7 = Arrangement.INSTANCE.m1497spacedBy0680j_4(Dp.constructor_impl(5));
            ComposerKt.sourceInformationMarkerStart(composer4, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy4 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1497spacedBy0680j_7, centerVertically4, composer4, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode9 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, 0));
            CompositionLocalMap currentCompositionLocalMap9 = composer4.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier9 = ComposedModifierKt.materializeModifier(composer4, modifierM1807paddingVpY3zN4$default4);
            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i1110 = ((((432 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!(composer4.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer4.startReusableNode();
            if (composer4.getInserting()) {
                function1 = constructor2;
                composer4.createNode(function1);
            } else {
                function1 = constructor2;
                composer4.useNode();
            }
            Composer composerM5188constructorimpl9 = Updater.constructor_impl(composer4);
            Updater.set_impl(composerM5188constructorimpl9, measurePolicyRowMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl9, currentCompositionLocalMap9, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl9, Integer.valueOf(iHashCode9), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl9, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl9, modifierMaterializeModifier9, ComposeUiNode.INSTANCE.getSetModifier());
            int i1111 = (i1110 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer4, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
            int i1112 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer4, 576605166, "C554@19574L356,564@19943L239:MeloXIOSNowPlayingV2.kt#qhu5z0");
            Modifier modifierM1872size3ABfNKs7 = SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(14));
            Alignment center4 = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart(composer4, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy6 = BoxKt.maybeCachedBoxMeasurePolicy(center4, false);
            ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode10 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, 0));
            CompositionLocalMap currentCompositionLocalMap10 = composer4.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier10 = ComposedModifierKt.materializeModifier(composer4, modifierM1872size3ABfNKs7);
            constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i1113 = ((((54 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!(composer4.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer4.startReusableNode();
            if (composer4.getInserting()) {
                function2 = constructor3;
                composer4.createNode(function2);
            } else {
                function2 = constructor3;
                composer4.useNode();
            }
            Composer composerM5188constructorimpl10 = Updater.constructor_impl(composer4);
            Updater.set_impl(composerM5188constructorimpl10, measurePolicyMaybeCachedBoxMeasurePolicy6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl10, currentCompositionLocalMap10, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl10, Integer.valueOf(iHashCode10), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl10, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl10, modifierMaterializeModifier10, ComposeUiNode.INSTANCE.getSetModifier());
            int i1114 = (i1113 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer4, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
            int i1115 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer4, 777632361, "C558@19713L203:MeloXIOSNowPlayingV2.kt#qhu5z0");
            CupertinoGlyphKind cupertinoGlyphKind4 = CupertinoGlyphKind.Waveform;
            Modifier modifierM1872size3ABfNKs8 = SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(12));
            long jM6105getWhite0d7_KjU11 = Color.INSTANCE.m6105getWhite0d7_KjU();
            m9689CupertinoGlyphXOJAsU(cupertinoGlyphKind4, modifierM1872size3ABfNKs8, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU11, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU11) : 0.86f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU11) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU11) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU11) : 0.0f), composer4, 438);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            composer4.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            String title4 = musicQuality5.getTitle();
            long jM6105getWhite0d7_KjU12 = Color.INSTANCE.m6105getWhite0d7_KjU();
            TextKt.m3912TextNvy7gAk(title4, null, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU12, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU12) : 0.86f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU12) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU12) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU12) : 0.0f), null, TextUnitKt.getSp(11), null, FontWeight.INSTANCE.getMedium(), null, 0L, null, null, TextUnitKt.getSp(13), 0, false, 0, 0, null, null, composer4, 1597824, 48, 260010);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            composer4.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            boolean zMeloXQualityChipV3$lambda$5 = MeloXQualityChipV3$lambda$2(mutableState);
            ComposerKt.sourceInformationMarkerStart(composer4, 1007480845, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            objRememberedValue10 = composer4.rememberedValue();
            if (objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                Function0 function6 = new Function0() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda38
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MeloXIOSNowPlayingV2Kt.MeloXQualityChipV3$lambda$18$3$0(mutableState);
                    }
                };
                composer4.updateRememberedValue(function6);
                objRememberedValue10 = function6;
            }
            ComposerKt.sourceInformationMarkerEnd(composer4);
            final MutableState mutableState10 = mutableState2;
            AndroidMenu_androidKt.m2908DropdownMenuIlH_yew(zMeloXQualityChipV3$lambda$5, (Function0) objRememberedValue10, null, 0L, null, null, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-938125708, true, new Function3() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda39
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return MeloXIOSNowPlayingV2Kt.MeloXQualityChipV3$lambda$18$4(mutableState10, mutableState7, applicationContext, mutableState8, mutableState, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composer4, 54), composer4, 48, 48, 2044);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda40
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXIOSNowPlayingV2Kt.MeloXQualityChipV3$lambda$19(meloXPlaybackUiState, modifier2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String MeloXQualityChipV3$lambda$0$0(Context $context) {
        NeteaseSessionStore.Companion companion = NeteaseSessionStore.INSTANCE;
        Intrinsics.checkNotNull($context);
        return companion.readCookie($context);
    }

    private static final boolean MeloXQualityChipV3$lambda$2(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void MeloXQualityChipV3$lambda$3(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final MusicQuality MeloXQualityChipV3$lambda$5(MutableState<MusicQuality> mutableState) {
        return mutableState.getValue();
    }

    private static final MusicQuality MeloXQualityChipV3$lambda$8(MutableState<MusicQuality> mutableState) {
        return mutableState.getValue();
    }

    private static final SongAudioAvailability MeloXQualityChipV3$lambda$11(MutableState<SongAudioAvailability> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean MeloXQualityChipV3$lambda$16(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final float MeloXQualityChipV3$lambda$17(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXQualityChipV3$lambda$18$0$0(State $scale$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setScaleX(MeloXQualityChipV3$lambda$17($scale$delegate));
        graphicsLayer.setScaleY(MeloXQualityChipV3$lambda$17($scale$delegate));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXQualityChipV3$lambda$18$1$0(MutableState $expanded$delegate) {
        MeloXQualityChipV3$lambda$3($expanded$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXQualityChipV3$lambda$18$3$0(MutableState $expanded$delegate) {
        MeloXQualityChipV3$lambda$3($expanded$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXQualityChipV3$lambda$18$4(final MutableState $selected$delegate, final MutableState $actual$delegate, final Context $context, MutableState $availability$delegate, final MutableState $expanded$delegate, ColumnScope DropdownMenu, Composer $composer, int $changed) {
        Composer composer = $composer;
        Intrinsics.checkNotNullParameter(DropdownMenu, "$this$DropdownMenu");
        ComposerKt.sourceInformation(composer, "C*581@20551L173,586@20756L218,579@20465L528:MeloXIOSNowPlayingV2.kt#qhu5z0");
        if (!composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-938125708, $changed, -1, "com.lladlam.melox.ui.player.MeloXQualityChipV3.<anonymous>.<anonymous> (MeloXIOSNowPlayingV2.kt:577)");
            }
            for (final MusicQuality musicQuality : MusicQuality.getEntries()) {
                boolean z = !Intrinsics.areEqual((Object) MeloXQualityChipV3$lambda$11($availability$delegate).supports(musicQuality.getApiLevel()), (Object) false);
                ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1885179715, true, new Function2() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda20
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return MeloXIOSNowPlayingV2Kt.MeloXQualityChipV3$lambda$18$4$0$0(musicQuality, $selected$delegate, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composer, 54);
                ComposerKt.sourceInformationMarkerStart(composer, 904042055, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
                boolean zChanged = composer.changed($selected$delegate) | composer.changed(musicQuality.ordinal()) | composer.changed($actual$delegate) | composer.changedInstance($context);
                Object objRememberedValue = $composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda21
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MeloXIOSNowPlayingV2Kt.MeloXQualityChipV3$lambda$18$4$0$1$0(musicQuality, $context, $selected$delegate, $actual$delegate, $expanded$delegate);
                        }
                    };
                    $composer.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                AndroidMenu_androidKt.DropdownMenuItem(composableLambdaRememberComposableLambda, (Function0) objRememberedValue, null, null, null, z, null, null, null, composer, 6, 476);
                composer = $composer;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXQualityChipV3$lambda$18$4$0$0(MusicQuality $quality, MutableState $selected$delegate, Composer $composer, int $changed) {
        String title;
        ComposerKt.sourceInformation($composer, "C582@20577L125:MeloXIOSNowPlayingV2.kt#qhu5z0");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1885179715, $changed, -1, "com.lladlam.melox.ui.player.MeloXQualityChipV3.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MeloXIOSNowPlayingV2.kt:582)");
            }
            if ($quality == MeloXQualityChipV3$lambda$5($selected$delegate)) {
                title = "✓ " + $quality.getTitle();
            } else {
                title = $quality.getTitle();
            }
            TextKt.m3912TextNvy7gAk(title, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXQualityChipV3$lambda$18$4$0$1$0(MusicQuality $quality, Context $context, MutableState $selected$delegate, MutableState $actual$delegate, MutableState $expanded$delegate) {
        $selected$delegate.setValue($quality);
        $actual$delegate.setValue(null);
        MeloXQualityChipV3$lambda$3($expanded$delegate, false);
        PlaybackCommands playbackCommands = PlaybackCommands.INSTANCE;
        Intrinsics.checkNotNull($context);
        playbackCommands.changeQuality($context, $quality);
        return Unit.INSTANCE;
    }

    private static final void MeloXTransportControlsV3(final MeloXPlaybackUiState state, Composer $composer, final int $changed) throws Throwable {
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        Composer $composer3 = $composer.startRestartGroup(1964460848);
        ComposerKt.sourceInformation($composer3, "C(MeloXTransportControlsV3)N(state)600@21111L741:MeloXIOSNowPlayingV2.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(state) ? 4 : 2;
        }
        if (!$composer3.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1964460848, $dirty, -1, "com.lladlam.melox.ui.player.MeloXTransportControlsV3 (MeloXIOSNowPlayingV2.kt:599)");
            }
            Modifier modifierM1858height3ABfNKs = SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.constructor_impl(82));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            $composer2 = $composer3;
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1858height3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((390 << 3) & 112) << 6) & 896) | 6;
            int $dirty2 = $dirty;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor;
                $composer3.createNode(function0);
            } else {
                function0 = constructor;
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl = Updater.constructor_impl($composer3);
            Updater.set_impl(composerM5188constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i3 = ((390 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -512944847, "C606@21272L27,610@21436L93,607@21308L232,614@21549L27,615@21585L31,616@21625L27,620@21788L11,617@21661L149,622@21819L27:MeloXIOSNowPlayingV2.kt#qhu5z0");
            SpacerKt.Spacer(RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
            CupertinoGlyphKind cupertinoGlyphKind = CupertinoGlyphKind.Backward;
            float fM8905constructorimpl = Dp.constructor_impl(34);
            ComposerKt.sourceInformationMarkerStart($composer3, -2094751823, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            boolean z = ($dirty2 & 14) == 4;
            Object objRememberedValue = $composer3.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda33
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MeloXIOSNowPlayingV2Kt.MeloXTransportControlsV3$lambda$0$0$0(state);
                    }
                };
                $composer3.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            m9690CupertinoTransportButtonrAjV9yQ(cupertinoGlyphKind, fM8905constructorimpl, (Function0) objRememberedValue, $composer3, 54);
            SpacerKt.Spacer(RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
            CupertinoPlayPauseButton(state, $composer3, $dirty2 & 14);
            SpacerKt.Spacer(RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
            CupertinoGlyphKind cupertinoGlyphKind2 = CupertinoGlyphKind.Forward;
            float fM8905constructorimpl2 = Dp.constructor_impl(34);
            ComposerKt.sourceInformationMarkerStart($composer3, -2094740641, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            boolean z2 = ($dirty2 & 14) == 4;
            Object objRememberedValue2 = $composer3.rememberedValue();
            if (z2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj = (KFunction) new MeloXIOSNowPlayingV2Kt$MeloXTransportControlsV3$1$2$1(state);
                $composer3.updateRememberedValue(obj);
                objRememberedValue2 = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            m9690CupertinoTransportButtonrAjV9yQ(cupertinoGlyphKind2, fM8905constructorimpl2, (Function0) ((KFunction) objRememberedValue2), $composer3, 54);
            SpacerKt.Spacer(RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda34
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return MeloXIOSNowPlayingV2Kt.MeloXTransportControlsV3$lambda$1(state, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXTransportControlsV3$lambda$0$0$0(MeloXPlaybackUiState $state) {
        if ($state.getHasPrevious()) {
            $state.previous();
        } else {
            $state.seekTo(0L);
        }
        return Unit.INSTANCE;
    }

    private static final void CupertinoPlayPauseButton(final MeloXPlaybackUiState state, Composer $composer, final int $changed) throws Throwable {
        Function0<ComposeUiNode> function0;
        Composer $composer2 = $composer.startRestartGroup(-739466020);
        ComposerKt.sourceInformation($composer2, "C(CupertinoPlayPauseButton)N(state)628@21958L39,629@22029L25,630@22072L245,642@22408L77,650@22651L22,639@22323L1328:MeloXIOSNowPlayingV2.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(state) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 3) != 2, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-739466020, $dirty2, -1, "com.lladlam.melox.ui.player.CupertinoPlayPauseButton (MeloXIOSNowPlayingV2.kt:627)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -1957238717, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            Object objRememberedValue = $composer2.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object objMutableInteractionSource = InteractionSourceKt.MutableInteractionSource();
                $composer2.updateRememberedValue(objMutableInteractionSource);
                objRememberedValue = objMutableInteractionSource;
            }
            MutableInteractionSource interaction = (MutableInteractionSource) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(CupertinoPlayPauseButton$lambda$1(PressInteractionKt.collectIsPressedAsState(interaction, $composer2, 6)) ? 0.86f : 1.0f, AnimationSpecKt.spring$default(0.5f, 620.0f, null, 4, null), 0.0f, "play-pause-press", null, $composer2, 3120, 20);
            Modifier modifierM1872size3ABfNKs = SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(64));
            ComposerKt.sourceInformationMarkerStart($composer2, -1957224279, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            boolean zChanged = $composer2.changed(stateAnimateFloatAsState);
            Object objRememberedValue2 = $composer2.rememberedValue();
            if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return MeloXIOSNowPlayingV2Kt.CupertinoPlayPauseButton$lambda$3$0(stateAnimateFloatAsState, (GraphicsLayerScope) obj2);
                    }
                };
                $composer2.updateRememberedValue(obj);
                objRememberedValue2 = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierClip = ClipKt.clip(GraphicsLayerModifierKt.graphicsLayer(modifierM1872size3ABfNKs, (Function1) objRememberedValue2), RoundedCornerShapeKt.getCircleShape());
            ComposerKt.sourceInformationMarkerStart($composer2, -1957216558, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            boolean z = ($dirty2 & 14) == 4;
            Object objRememberedValue3 = $composer2.rememberedValue();
            if (z || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = (KFunction) new MeloXIOSNowPlayingV2Kt$CupertinoPlayPauseButton$2$1(state);
                $composer2.updateRememberedValue(obj2);
                objRememberedValue3 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierM1073clickableO2vRcR0 = ClickableKt.m1073clickableO2vRcR0(modifierClip, interaction, null, (24 & 4) != 0, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : null, (Function0) ((KFunction) objRememberedValue3));
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierM1073clickableO2vRcR0);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            Composer composerM5188constructorimpl = Updater.constructor_impl($composer2);
            Updater.set_impl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1514137245, "C656@22840L503,654@22751L894:MeloXIOSNowPlayingV2.kt#qhu5z0");
            Boolean boolValueOf = Boolean.valueOf(state.isPlaying());
            ComposerKt.sourceInformationMarkerStart($composer2, 1059537977, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            Object objRememberedValue4 = $composer2.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object obj3 = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        return MeloXIOSNowPlayingV2Kt.CupertinoPlayPauseButton$lambda$5$0$0((AnimatedContentTransitionScope) obj4);
                    }
                };
                $composer2.updateRememberedValue(obj3);
                objRememberedValue4 = obj3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            AnimatedContentKt.AnimatedContent(boolValueOf, null, (Function1) objRememberedValue4, null, "play-pause-symbol-replace", null, ComposableSingletons$MeloXIOSNowPlayingV2Kt.INSTANCE.m9673getLambda$1310663515$app(), $composer2, 1597824, 42);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj4, Object obj5) {
                    return MeloXIOSNowPlayingV2Kt.CupertinoPlayPauseButton$lambda$6(state, $changed, (Composer) obj4, ((Integer) obj5).intValue());
                }
            });
        }
    }

    private static final boolean CupertinoPlayPauseButton$lambda$1(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final float CupertinoPlayPauseButton$lambda$2(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CupertinoPlayPauseButton$lambda$3$0(State $scale$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setScaleX(CupertinoPlayPauseButton$lambda$2($scale$delegate));
        graphicsLayer.setScaleY(CupertinoPlayPauseButton$lambda$2($scale$delegate));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ContentTransform CupertinoPlayPauseButton$lambda$5$0$0(AnimatedContentTransitionScope AnimatedContent) {
        Intrinsics.checkNotNullParameter(AnimatedContent, "$this$AnimatedContent");
        return AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(180, 0, null, 6, null), 0.0f, 2, null).plus(EnterExitTransitionKt.m844scaleInL8ZKhE$default(AnimationSpecKt.tween$default(200, 0, null, 6, null), 0.78f, 0L, 4, null)).plus(EnterExitTransitionKt.slideInVertically(AnimationSpecKt.tween$default(200, 0, null, 6, null), new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda31
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Integer.valueOf(MeloXIOSNowPlayingV2Kt.CupertinoPlayPauseButton$lambda$5$0$0$0(((Integer) obj).intValue()));
            }
        })), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(150, 0, null, 6, null), 0.0f, 2, null).plus(EnterExitTransitionKt.m846scaleOutL8ZKhE$default(AnimationSpecKt.tween$default(180, 0, null, 6, null), 0.78f, 0L, 4, null)).plus(EnterExitTransitionKt.slideOutVertically(AnimationSpecKt.tween$default(180, 0, null, 6, null), new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda32
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Integer.valueOf(MeloXIOSNowPlayingV2Kt.CupertinoPlayPauseButton$lambda$5$0$0$1(((Integer) obj).intValue()));
            }
        })));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int CupertinoPlayPauseButton$lambda$5$0$0$0(int it) {
        return (int) (it * 0.24f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int CupertinoPlayPauseButton$lambda$5$0$0$1(int it) {
        return -((int) (it * 0.24f));
    }

    /* JADX INFO: renamed from: CupertinoTransportButton-rAjV9yQ, reason: not valid java name */
    private static final void m9690CupertinoTransportButtonrAjV9yQ(CupertinoGlyphKind kind, final float visualSize, final Function0<Unit> function0, Composer $composer, int $changed) {
        final int i;
        Composer $composer2;
        final CupertinoGlyphKind cupertinoGlyphKind;
        Function0<ComposeUiNode> function1;
        Composer $composer3 = $composer.startRestartGroup(-745383275);
        ComposerKt.sourceInformation($composer3, "C(CupertinoTransportButton)N(kind,visualSize:c#ui.unit.Dp,onClick)684@23806L39,685@23877L25,686@23920L257,698@24268L77,695@24183L552:MeloXIOSNowPlayingV2.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(kind.ordinal()) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(visualSize) ? 32 : 16;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 256 : 128;
        }
        if (!$composer3.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            i = $changed;
            $composer2 = $composer3;
            cupertinoGlyphKind = kind;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-745383275, $dirty, -1, "com.lladlam.melox.ui.player.CupertinoTransportButton (MeloXIOSNowPlayingV2.kt:683)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 946215388, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            Object objRememberedValue = $composer3.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object objMutableInteractionSource = InteractionSourceKt.MutableInteractionSource();
                $composer3.updateRememberedValue(objMutableInteractionSource);
                objRememberedValue = objMutableInteractionSource;
            }
            MutableInteractionSource interaction = (MutableInteractionSource) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(CupertinoTransportButton_rAjV9yQ$lambda$1(PressInteractionKt.collectIsPressedAsState(interaction, $composer3, 6)) ? 0.84f : 1.0f, AnimationSpecKt.spring$default(0.5f, 620.0f, null, 4, null), 0.0f, "transport-press-" + kind.name(), null, $composer3, 48, 20);
            Modifier modifierM1872size3ABfNKs = SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(64));
            ComposerKt.sourceInformationMarkerStart($composer3, 946230210, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            boolean zChanged = $composer3.changed(stateAnimateFloatAsState);
            Object objRememberedValue2 = $composer3.rememberedValue();
            if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda29
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return MeloXIOSNowPlayingV2Kt.CupertinoTransportButton_rAjV9yQ$lambda$3$0(stateAnimateFloatAsState, (GraphicsLayerScope) obj2);
                    }
                };
                $composer3.updateRememberedValue(obj);
                objRememberedValue2 = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierM1073clickableO2vRcR0 = ClickableKt.m1073clickableO2vRcR0(ClipKt.clip(GraphicsLayerModifierKt.graphicsLayer(modifierM1872size3ABfNKs, (Function1) objRememberedValue2), RoundedCornerShapeKt.getCircleShape()), interaction, null, (24 & 4) != 0, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : null, function0);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1073clickableO2vRcR0);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i2 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function1 = constructor;
                $composer3.createNode(function1);
            } else {
                function1 = constructor;
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl = Updater.constructor_impl($composer3);
            Updater.set_impl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i3 = (i2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i4 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -243673297, "C710@24596L133:MeloXIOSNowPlayingV2.kt#qhu5z0");
            i = $changed;
            cupertinoGlyphKind = kind;
            $composer2 = $composer3;
            m9689CupertinoGlyphXOJAsU(cupertinoGlyphKind, SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, visualSize), Color.INSTANCE.m6105getWhite0d7_KjU(), $composer3, ($dirty & 14) | RendererCapabilities.DECODER_SUPPORT_MASK);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda30
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return MeloXIOSNowPlayingV2Kt.CupertinoTransportButton_rAjV9yQ$lambda$5(cupertinoGlyphKind, visualSize, function0, i, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    private static final boolean CupertinoTransportButton_rAjV9yQ$lambda$1(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final float CupertinoTransportButton_rAjV9yQ$lambda$2(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CupertinoTransportButton_rAjV9yQ$lambda$3$0(State $scale$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setScaleX(CupertinoTransportButton_rAjV9yQ$lambda$2($scale$delegate));
        graphicsLayer.setScaleY(CupertinoTransportButton_rAjV9yQ$lambda$2($scale$delegate));
        return Unit.INSTANCE;
    }

    private static final void MeloXVolumeControlV3(final MeloXPlaybackUiState state, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        Composer $composer3 = $composer.startRestartGroup(-1835673780);
        ComposerKt.sourceInformation($composer3, "C(MeloXVolumeControlV3)N(state)720@24835L34,721@24893L46,722@24961L150,727@25135L150,733@25330L57,733@25291L96,737@25393L1870:MeloXIOSNowPlayingV2.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(state) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (!$composer3.shouldExecute(($dirty2 & 3) != 2, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1835673780, $dirty2, -1, "com.lladlam.melox.ui.player.MeloXVolumeControlV3 (MeloXIOSNowPlayingV2.kt:719)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 1149058542, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            Object objRememberedValue = $composer3.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default);
                objRememberedValue = objMutableStateOf$default;
            }
            final MutableState dragging$delegate = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 1149060410, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            Object objRememberedValue2 = $composer3.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object objMutableFloatStateOf = PrimitiveSnapshotStateKt.mutableFloatStateOf(state.getVolume());
                $composer3.updateRememberedValue(objMutableFloatStateOf);
                objRememberedValue2 = objMutableFloatStateOf;
            }
            final MutableFloatState localVolume$delegate = (MutableFloatState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final State<Dp> stateM940animateDpAsStateAjpBEmI = AnimateAsStateKt.m940animateDpAsStateAjpBEmI(MeloXVolumeControlV3$lambda$1(dragging$delegate) ? Dp.constructor_impl(16) : Dp.constructor_impl(14), AnimationSpecKt.tween$default(120, 0, null, 6, null), "volume-thumb-size", null, $composer3, 432, 8);
            final State<Dp> stateM940animateDpAsStateAjpBEmI2 = AnimateAsStateKt.m940animateDpAsStateAjpBEmI(MeloXVolumeControlV3$lambda$1(dragging$delegate) ? Dp.constructor_impl(4) : Dp.constructor_impl(3), AnimationSpecKt.tween$default(120, 0, null, 6, null), "volume-track-height", null, $composer3, 432, 8);
            Float fValueOf = Float.valueOf(state.getVolume());
            Boolean boolValueOf = Boolean.valueOf(MeloXVolumeControlV3$lambda$1(dragging$delegate));
            ComposerKt.sourceInformationMarkerStart($composer3, 1149074405, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            boolean z = ($dirty2 & 14) == 4;
            Object objRememberedValue3 = $composer3.rememberedValue();
            if (z || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object obj = (Function2) new MeloXIOSNowPlayingV2Kt$MeloXVolumeControlV3$1$1(state, dragging$delegate, localVolume$delegate, null);
                $composer3.updateRememberedValue(obj);
                objRememberedValue3 = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(fValueOf, boolValueOf, (Function2) objRememberedValue3, $composer3, 0);
            Modifier modifierM1858height3ABfNKs = SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.constructor_impl(42));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.Horizontal horizontalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(Dp.constructor_impl(10));
            ComposerKt.sourceInformationMarkerStart($composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalM1497spacedBy0680j_4, centerVertically, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            $composer2 = $composer3;
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1858height3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((438 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor;
                $composer3.createNode(function0);
            } else {
                function0 = constructor;
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl = Updater.constructor_impl($composer3);
            Updater.set_impl(composerM5188constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i3 = ((438 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 1754116841, "C744@25615L173,752@25867L145,757@26050L20,761@26184L279,770@26485L577,750@25798L1275,788@27083L174:MeloXIOSNowPlayingV2.kt#qhu5z0");
            CupertinoGlyphKind cupertinoGlyphKind = CupertinoGlyphKind.SpeakerLow;
            Modifier modifierM1872size3ABfNKs = SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(12));
            long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
            m9689CupertinoGlyphXOJAsU(cupertinoGlyphKind, modifierM1872size3ABfNKs, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU) : 0.62f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU) : 0.0f), $composer3, 438);
            float fMeloXVolumeControlV3$lambda$4 = MeloXVolumeControlV3$lambda$4(localVolume$delegate);
            Modifier modifierM1858height3ABfNKs2 = SizeKt.m1858height3ABfNKs(RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null), Dp.constructor_impl(32));
            ComposerKt.sourceInformationMarkerStart($composer3, -81956351, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            boolean z2 = ($dirty2 & 14) == 4;
            Object objRememberedValue4 = $composer3.rememberedValue();
            if (z2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda45
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return MeloXIOSNowPlayingV2Kt.MeloXVolumeControlV3$lambda$9$0$0(state, dragging$delegate, localVolume$delegate, ((Float) obj3).floatValue());
                    }
                };
                $composer3.updateRememberedValue(obj2);
                objRememberedValue4 = obj2;
            }
            Function1 function1 = (Function1) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -81950620, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            Object objRememberedValue5 = $composer3.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                Object obj3 = new Function0() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda46
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MeloXIOSNowPlayingV2Kt.MeloXVolumeControlV3$lambda$9$1$0(dragging$delegate);
                    }
                };
                $composer3.updateRememberedValue(obj3);
                objRememberedValue5 = obj3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            SliderKt.Slider(fMeloXVolumeControlV3$lambda$4, function1, modifierM1858height3ABfNKs2, false, (Function0) objRememberedValue5, null, null, 0, ComposableLambdaKt.rememberComposableLambda(2023885292, true, new Function3() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda47
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj4, Object obj5, Object obj6) {
                    return MeloXIOSNowPlayingV2Kt.MeloXVolumeControlV3$lambda$9$2(stateM940animateDpAsStateAjpBEmI, (SliderState) obj4, (Composer) obj5, ((Integer) obj6).intValue());
                }
            }, $composer3, 54), ComposableLambdaKt.rememberComposableLambda(200698029, true, new Function3() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda48
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj4, Object obj5, Object obj6) {
                    return MeloXIOSNowPlayingV2Kt.MeloXVolumeControlV3$lambda$9$3(stateM940animateDpAsStateAjpBEmI2, localVolume$delegate, (SliderState) obj4, (Composer) obj5, ((Integer) obj6).intValue());
                }
            }, $composer3, 54), null, $composer3, 905994240, 0, 1256);
            CupertinoGlyphKind cupertinoGlyphKind2 = CupertinoGlyphKind.SpeakerHigh;
            Modifier modifierM1872size3ABfNKs2 = SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(15));
            long jM6105getWhite0d7_KjU2 = Color.INSTANCE.m6105getWhite0d7_KjU();
            m9689CupertinoGlyphXOJAsU(cupertinoGlyphKind2, modifierM1872size3ABfNKs2, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU2, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU2) : 0.62f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU2) : 0.0f), $composer3, 438);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda49
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj4, Object obj5) {
                    return MeloXIOSNowPlayingV2Kt.MeloXVolumeControlV3$lambda$10(state, $changed, (Composer) obj4, ((Integer) obj5).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean MeloXVolumeControlV3$lambda$1(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void MeloXVolumeControlV3$lambda$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final float MeloXVolumeControlV3$lambda$4(MutableFloatState $localVolume$delegate) {
        return $localVolume$delegate.getFloatValue();
    }

    private static final float MeloXVolumeControlV3$lambda$6(State<Dp> state) {
        return ((Dp) state.getValue()).m8919unboximpl();
    }

    private static final float MeloXVolumeControlV3$lambda$7(State<Dp> state) {
        return ((Dp) state.getValue()).m8919unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXVolumeControlV3$lambda$9$0$0(MeloXPlaybackUiState $state, MutableState $dragging$delegate, MutableFloatState $localVolume$delegate, float it) {
        MeloXVolumeControlV3$lambda$2($dragging$delegate, true);
        $localVolume$delegate.setFloatValue(RangesKt.coerceIn(it, 0.0f, 1.0f));
        $state.changeVolume(MeloXVolumeControlV3$lambda$4($localVolume$delegate));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXVolumeControlV3$lambda$9$1$0(MutableState $dragging$delegate) {
        MeloXVolumeControlV3$lambda$2($dragging$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXVolumeControlV3$lambda$9$2(State $thumbSize$delegate, SliderState it, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation($composer, "CN(it)762@26202L247:MeloXIOSNowPlayingV2.kt#qhu5z0");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2023885292, $changed, -1, "com.lladlam.melox.ui.player.MeloXVolumeControlV3.<anonymous>.<anonymous> (MeloXIOSNowPlayingV2.kt:762)");
            }
            BoxKt.Box(BackgroundKt.m1043backgroundbw27NRU$default(ClipKt.clip(ShadowKt.m5666shadows4CzXII$default(SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, MeloXVolumeControlV3$lambda$6($thumbSize$delegate)), Dp.constructor_impl(3), RoundedCornerShapeKt.getCircleShape(), false, 0L, 0L, 28, null), RoundedCornerShapeKt.getCircleShape()), Color.INSTANCE.m6105getWhite0d7_KjU(), null, 2, null), $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXVolumeControlV3$lambda$9$3(State $trackHeight$delegate, MutableFloatState $localVolume$delegate, SliderState it, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation($composer, "CN(it)771@26503L545:MeloXIOSNowPlayingV2.kt#qhu5z0");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(200698029, $changed, -1, "com.lladlam.melox.ui.player.MeloXVolumeControlV3.<anonymous>.<anonymous> (MeloXIOSNowPlayingV2.kt:771)");
            }
            Modifier modifierClip = ClipKt.clip(SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), MeloXVolumeControlV3$lambda$7($trackHeight$delegate)), RoundedCornerShapeKt.getCircleShape());
            long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
            Modifier modifierM1043backgroundbw27NRU$default = BackgroundKt.m1043backgroundbw27NRU$default(modifierClip, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU) : 0.2f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU) : 0.0f), null, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM1043backgroundbw27NRU$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
                $composer.useNode();
            }
            Composer composerM5188constructorimpl = Updater.constructor_impl($composer);
            Updater.set_impl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -680818552, "C778@26786L244:MeloXIOSNowPlayingV2.kt#qhu5z0");
            Modifier modifierFillMaxHeight$default = SizeKt.fillMaxHeight$default(SizeKt.fillMaxWidth(Modifier.INSTANCE, MeloXVolumeControlV3$lambda$4($localVolume$delegate)), 0.0f, 1, null);
            long jM6105getWhite0d7_KjU2 = Color.INSTANCE.m6105getWhite0d7_KjU();
            BoxKt.Box(BackgroundKt.m1043backgroundbw27NRU$default(modifierFillMaxHeight$default, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU2, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU2) : 0.82f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU2) : 0.0f), null, 2, null), $composer, 0);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final void MeloXPageSelectorV3(final MeloXPlaybackUiState state, final MeloXNowPlayingPage page, final Function1<? super MeloXNowPlayingPage, Unit> function1, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function2;
        Function0<ComposeUiNode> function3;
        String str;
        Composer $composer3 = $composer.startRestartGroup(-1866635416);
        ComposerKt.sourceInformation($composer3, "C(MeloXPageSelectorV3)N(state,page,onPageSelected)802@27435L1906:MeloXIOSNowPlayingV2.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(state) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(page.ordinal()) ? 32 : 16;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 256 : 128;
        }
        if (!$composer3.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1866635416, $dirty, -1, "com.lladlam.melox.ui.player.MeloXPageSelectorV3 (MeloXIOSNowPlayingV2.kt:801)");
            }
            Modifier modifierM1807paddingVpY3zN4$default = PaddingKt.m1807paddingVpY3zN4$default(SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.constructor_impl(50)), Dp.constructor_impl(32), 0.0f, 2, null);
            Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            $composer2 = $composer3;
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1807paddingVpY3zN4$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((438 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor;
                $composer3.createNode(function0);
            } else {
                function0 = constructor;
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl = Updater.constructor_impl($composer3);
            Updater.set_impl(composerM5188constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i3 = ((438 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -2003123625, "C814@27871L46,810@27695L233,821@28088L2,817@27938L163,824@28111L1224:MeloXIOSNowPlayingV2.kt#qhu5z0");
            CupertinoGlyphKind cupertinoGlyphKind = CupertinoGlyphKind.Lyrics;
            boolean z = page == MeloXNowPlayingPage.Lyrics;
            ComposerKt.sourceInformationMarkerStart($composer3, -895896846, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            boolean z2 = ($dirty & 896) == 256;
            Object objRememberedValue = $composer3.rememberedValue();
            if (z2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda41
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MeloXIOSNowPlayingV2Kt.MeloXPageSelectorV3$lambda$0$0$0(function1);
                    }
                };
                $composer3.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            CupertinoPageButton(cupertinoGlyphKind, z, true, (Function0) objRememberedValue, $composer3, 390);
            CupertinoGlyphKind cupertinoGlyphKind2 = CupertinoGlyphKind.PipEnter;
            ComposerKt.sourceInformationMarkerStart($composer3, -895889946, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            Object objRememberedValue2 = $composer3.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda42
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Unit.INSTANCE;
                    }
                };
                $composer3.updateRememberedValue(obj);
                objRememberedValue2 = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            CupertinoPageButton(cupertinoGlyphKind2, false, false, (Function0) objRememberedValue2, $composer3, 3510);
            ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer3, modifier);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function2 = constructor2;
                $composer3.createNode(function2);
            } else {
                function2 = constructor2;
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl2 = Updater.constructor_impl($composer3);
            Updater.set_impl(composerM5188constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            int i6 = ((0 >> 6) & 112) | 6;
            BoxScope boxScope = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -1374003687, "C829@28319L45,825@28129L250:MeloXIOSNowPlayingV2.kt#qhu5z0");
            CupertinoGlyphKind cupertinoGlyphKind3 = CupertinoGlyphKind.Queue;
            boolean z3 = page == MeloXNowPlayingPage.Queue;
            ComposerKt.sourceInformationMarkerStart($composer3, 1756797547, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            boolean z4 = ($dirty & 896) == 256;
            Object objRememberedValue3 = $composer3.rememberedValue();
            if (z4 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new Function0() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda43
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MeloXIOSNowPlayingV2Kt.MeloXPageSelectorV3$lambda$0$2$0$0(function1);
                    }
                };
                $composer3.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            CupertinoPageButton(cupertinoGlyphKind3, z3, true, (Function0) objRememberedValue3, $composer3, 390);
            if (page == MeloXNowPlayingPage.Queue || (!state.getShuffleEnabled() && state.getRepeatMode() == 0)) {
                $composer3.startReplaceGroup(-1372854332);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(-1373619288);
                ComposerKt.sourceInformation($composer3, "836@28547L764");
                Modifier modifierClip = ClipKt.clip(SizeKt.m1872size3ABfNKs(boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopEnd()), Dp.constructor_impl(15)), RoundedCornerShapeKt.getCircleShape());
                long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
                Modifier modifierM1043backgroundbw27NRU$default = BackgroundKt.m1043backgroundbw27NRU$default(modifierClip, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU) : 0.82f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU) : 0.0f), null, 2, null);
                Alignment center = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                CompositionLocalMap currentCompositionLocalMap3 = $composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer3, modifierM1043backgroundbw27NRU$default);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int i7 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function3 = constructor3;
                    $composer3.createNode(function3);
                } else {
                    function3 = constructor3;
                    $composer3.useNode();
                }
                Composer composerM5188constructorimpl3 = Updater.constructor_impl($composer3);
                Updater.set_impl(composerM5188constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.set_impl(composerM5188constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.set_impl(composerM5188constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.reconcile_impl(composerM5188constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.set_impl(composerM5188constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i8 = (i7 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i9 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 199535473, "C844@28888L405:MeloXIOSNowPlayingV2.kt#qhu5z0");
                if (state.getShuffleEnabled()) {
                    str = ExifInterface.LATITUDE_SOUTH;
                } else {
                    str = state.getRepeatMode() == 1 ? "1" : "R";
                }
                long jM6094getBlack0d7_KjU = Color.INSTANCE.m6094getBlack0d7_KjU();
                TextKt.m3912TextNvy7gAk(str, null, Color.copy_wmQWz5c(jM6094getBlack0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6094getBlack0d7_KjU) : 0.72f, (14 & 2) != 0 ? Color.getRed_impl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6094getBlack0d7_KjU) : 0.0f), null, TextUnitKt.getSp(7), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 1597824, 0, 262058);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda44
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return MeloXIOSNowPlayingV2Kt.MeloXPageSelectorV3$lambda$1(state, page, function1, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXPageSelectorV3$lambda$0$0$0(Function1 $onPageSelected) {
        $onPageSelected.invoke(MeloXNowPlayingPage.Lyrics);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXPageSelectorV3$lambda$0$2$0$0(Function1 $onPageSelected) {
        $onPageSelected.invoke(MeloXNowPlayingPage.Queue);
        return Unit.INSTANCE;
    }

    private static final void CupertinoPageButton(final CupertinoGlyphKind kind, final boolean selected, final boolean enabled, final Function0<Unit> function0, Composer $composer, final int $changed) {
        Function0<Unit> function1;
        Composer $composer2;
        Function0<ComposeUiNode> function2;
        long jM6066copywmQWz5c;
        Composer $composer3 = $composer.startRestartGroup(-1041760345);
        ComposerKt.sourceInformation($composer3, "C(CupertinoPageButton)N(kind,selected,enabled,onClick)867@29516L39,868@29587L25,869@29635L259,877@29921L281,885@30230L190,894@30511L121,891@30426L886:MeloXIOSNowPlayingV2.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(kind.ordinal()) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(selected) ? 32 : 16;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer3.changed(enabled) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            function1 = function0;
            $dirty |= $composer3.changedInstance(function1) ? 2048 : 1024;
        } else {
            function1 = function0;
        }
        int $dirty2 = $dirty;
        if (!$composer3.shouldExecute(($dirty2 & 1171) != 1170, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1041760345, $dirty2, -1, "com.lladlam.melox.ui.player.CupertinoPageButton (MeloXIOSNowPlayingV2.kt:866)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -404306706, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            Object objRememberedValue = $composer3.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object objMutableInteractionSource = InteractionSourceKt.MutableInteractionSource();
                $composer3.updateRememberedValue(objMutableInteractionSource);
                objRememberedValue = objMutableInteractionSource;
            }
            MutableInteractionSource interaction = (MutableInteractionSource) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(CupertinoPageButton$lambda$1(PressInteractionKt.collectIsPressedAsState(interaction, $composer3, 6)) ? 0.86f : 1.0f, AnimationSpecKt.spring$default(0.5f, 650.0f, null, 4, null), 0.0f, "page-button-press-" + kind.name(), null, $composer3, 48, 20);
            final State<Float> stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(selected ? 1.04f : 1.0f, AnimationSpecKt.spring$default(0.5f, 1500.0f, null, 4, null), 0.0f, "page-button-selected-" + kind.name(), null, $composer3, 48, 20);
            State<Float> stateAnimateFloatAsState3 = AnimateAsStateKt.animateFloatAsState(selected ? 0.68f : 0.0f, AnimationSpecKt.tween$default(220, 0, EasingKt.getFastOutSlowInEasing(), 2, null), 0.0f, "page-button-bg-" + kind.name(), null, $composer3, 0, 20);
            Modifier modifierM1872size3ABfNKs = SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(44));
            ComposerKt.sourceInformationMarkerStart($composer3, -404274784, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            boolean zChanged = $composer3.changed(stateAnimateFloatAsState) | $composer3.changed(stateAnimateFloatAsState2);
            Object objRememberedValue2 = $composer3.rememberedValue();
            if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda22
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return MeloXIOSNowPlayingV2Kt.CupertinoPageButton$lambda$5$0(stateAnimateFloatAsState, stateAnimateFloatAsState2, (GraphicsLayerScope) obj2);
                    }
                };
                $composer3.updateRememberedValue(obj);
                objRememberedValue2 = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierClip = ClipKt.clip(GraphicsLayerModifierKt.graphicsLayer(modifierM1872size3ABfNKs, (Function1) objRememberedValue2), RoundedCornerShapeKt.getCircleShape());
            long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
            Function0<Unit> function3 = function1;
            $composer2 = $composer3;
            Modifier modifierM1073clickableO2vRcR0 = ClickableKt.m1073clickableO2vRcR0(BackgroundKt.m1043backgroundbw27NRU$default(modifierClip, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU) : CupertinoPageButton$lambda$4(stateAnimateFloatAsState3) * 0.16f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU) : 0.0f), null, 2, null), interaction, null, (24 & 4) != 0 ? true : enabled, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : null, function3);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierM1073clickableO2vRcR0);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function2 = constructor;
                $composer2.createNode(function2);
            } else {
                function2 = constructor;
                $composer2.useNode();
            }
            Composer composerM5188constructorimpl = Updater.constructor_impl($composer2);
            Updater.set_impl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1598426967, "C909@30993L313:MeloXIOSNowPlayingV2.kt#qhu5z0");
            Modifier modifierM1872size3ABfNKs2 = SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(22));
            if (!enabled) {
                long jM6105getWhite0d7_KjU2 = Color.INSTANCE.m6105getWhite0d7_KjU();
                jM6066copywmQWz5c = Color.copy_wmQWz5c(jM6105getWhite0d7_KjU2, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU2) : 0.26f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU2) : 0.0f);
            } else if (selected) {
                long jM6094getBlack0d7_KjU = Color.INSTANCE.m6094getBlack0d7_KjU();
                jM6066copywmQWz5c = Color.copy_wmQWz5c(jM6094getBlack0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6094getBlack0d7_KjU) : 0.68f, (14 & 2) != 0 ? Color.getRed_impl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6094getBlack0d7_KjU) : 0.0f);
            } else {
                long jM6105getWhite0d7_KjU3 = Color.INSTANCE.m6105getWhite0d7_KjU();
                jM6066copywmQWz5c = Color.copy_wmQWz5c(jM6105getWhite0d7_KjU3, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU3) : 0.72f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU3) : 0.0f);
            }
            m9689CupertinoGlyphXOJAsU(kind, modifierM1872size3ABfNKs2, jM6066copywmQWz5c, $composer2, ($dirty2 & 14) | 48);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda23
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return MeloXIOSNowPlayingV2Kt.CupertinoPageButton$lambda$7(kind, selected, enabled, function0, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    private static final boolean CupertinoPageButton$lambda$1(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final float CupertinoPageButton$lambda$2(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float CupertinoPageButton$lambda$3(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float CupertinoPageButton$lambda$4(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CupertinoPageButton$lambda$5$0(State $pressScale$delegate, State $selectionScale$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        float s = CupertinoPageButton$lambda$2($pressScale$delegate) * CupertinoPageButton$lambda$3($selectionScale$delegate);
        graphicsLayer.setScaleX(s);
        graphicsLayer.setScaleY(s);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: CupertinoGlyph-XO-JAsU, reason: not valid java name */
    public static final void m9689CupertinoGlyphXOJAsU(final CupertinoGlyphKind kind, final Modifier modifier, final long color, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-1069033188);
        ComposerKt.sourceInformation($composer2, "C(CupertinoGlyph)N(kind,modifier,color:c#ui.graphics.Color)940@31639L8089,940@31611L8117:MeloXIOSNowPlayingV2.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(kind.ordinal()) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer2.changed(color) ? 256 : 128;
        }
        if (!$composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1069033188, $dirty, -1, "com.lladlam.melox.ui.player.CupertinoGlyph (MeloXIOSNowPlayingV2.kt:939)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -843697739, "CC(remember):MeloXIOSNowPlayingV2.kt#9igjgp");
            boolean z = (($dirty & 14) == 4) | (($dirty & 896) == 256);
            Object objRememberedValue = $composer2.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda18
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return MeloXIOSNowPlayingV2Kt.CupertinoGlyph_XO_JAsU$lambda$0$0(kind, color, (DrawScope) obj2);
                    }
                };
                $composer2.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifier, (Function1) objRememberedValue, $composer2, ($dirty >> 3) & 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return MeloXIOSNowPlayingV2Kt.CupertinoGlyph_XO_JAsU$lambda$1(kind, modifier, color, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CupertinoGlyph_XO_JAsU$lambda$0$0(CupertinoGlyphKind $kind, long $color, DrawScope $this$Canvas) {
        DrawScope Canvas = $this$Canvas;
        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
        float w = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32));
        float h = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L));
        float min = Size.m5891getMinDimensionimpl(Canvas.mo6642getSizeNHjbRc());
        float stroke = RangesKt.coerceAtLeast(0.085f * min, 1.35f);
        switch (WhenMappings.$EnumSwitchMapping$1[$kind.ordinal()]) {
            case 1:
                Path p = AndroidPath_androidKt.Path();
                p.moveTo(w * 0.28f, 0.13f * h);
                p.quadraticBezierTo(w * 0.22f, h * 0.1f, w * 0.22f, h * 0.22f);
                p.lineTo(w * 0.22f, h * 0.78f);
                p.quadraticBezierTo(w * 0.22f, 0.9f * h, w * 0.3f, 0.86f * h);
                p.lineTo(w * 0.82f, 0.56f * h);
                p.quadraticBezierTo(0.91f * w, h * 0.5f, w * 0.82f, 0.44f * h);
                p.close();
                DrawScope.m6632drawPathLG529CI$default($this$Canvas, p, $color, 0.0f, null, null, 0, 60, null);
                Unit unit = Unit.INSTANCE;
                break;
            case 2:
                float f = 0.045f * w;
                DrawScope.m6638drawRoundRectuAw5IA$default($this$Canvas, $color, Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(0.24f * w)) << 32) | (((long) Float.floatToRawIntBits(h * 0.1f)) & 4294967295L)), Size.m5883constructorimpl((((long) Float.floatToRawIntBits(w * 0.18f)) << 32) | (((long) Float.floatToRawIntBits(h * 0.8f)) & 4294967295L)), CornerRadius.m5777constructorimpl((((long) Float.floatToRawIntBits(f)) << 32) | (((long) Float.floatToRawIntBits(f)) & 4294967295L)), null, 0.0f, null, 0, PsExtractor.VIDEO_STREAM_MASK, null);
                float f2 = 0.045f * w;
                DrawScope.m6638drawRoundRectuAw5IA$default($this$Canvas, $color, Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(w * 0.58f)) << 32) | (((long) Float.floatToRawIntBits(h * 0.1f)) & 4294967295L)), Size.m5883constructorimpl((((long) Float.floatToRawIntBits(w * 0.18f)) << 32) | (((long) Float.floatToRawIntBits(h * 0.8f)) & 4294967295L)), CornerRadius.m5777constructorimpl((((long) Float.floatToRawIntBits(f2)) << 32) | (((long) Float.floatToRawIntBits(f2)) & 4294967295L)), null, 0.0f, null, 0, PsExtractor.VIDEO_STREAM_MASK, null);
                Unit unit2 = Unit.INSTANCE;
                break;
            case 3:
            case 4:
                boolean forward = $kind == CupertinoGlyphKind.Forward;
                CupertinoGlyph_XO_JAsU$lambda$0$0$triangle(forward, h, $this$Canvas, $color, w * 0.1f, w * 0.5f);
                CupertinoGlyph_XO_JAsU$lambda$0$0$triangle(forward, h, $this$Canvas, $color, w * 0.45f, w * 0.88f);
                Unit unit3 = Unit.INSTANCE;
                break;
            case 5:
            case 6:
                Path speaker = AndroidPath_androidKt.Path();
                speaker.moveTo(0.08f * w, 0.41f * h);
                speaker.lineTo(w * 0.29f, 0.41f * h);
                speaker.lineTo(0.54f * w, 0.22f * h);
                speaker.quadraticBezierTo(w * 0.58f, 0.19f * h, w * 0.58f, 0.27f * h);
                speaker.lineTo(w * 0.58f, 0.73f * h);
                speaker.quadraticBezierTo(w * 0.58f, 0.81f * h, 0.54f * w, h * 0.78f);
                speaker.lineTo(w * 0.29f, 0.59f * h);
                speaker.lineTo(0.08f * w, 0.59f * h);
                speaker.close();
                DrawScope.m6632drawPathLG529CI$default($this$Canvas, speaker, $color, 0.0f, null, null, 0, 60, null);
                if ($kind == CupertinoGlyphKind.SpeakerHigh) {
                    DrawScope.m6621drawArcyD3GUKo$default($this$Canvas, $color, -46.0f, 92.0f, false, Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(0.45f * w)) << 32) | (((long) Float.floatToRawIntBits(h * 0.3f)) & 4294967295L)), Size.m5883constructorimpl((((long) Float.floatToRawIntBits(w * 0.3f)) << 32) | (((long) Float.floatToRawIntBits(0.4f * h)) & 4294967295L)), 0.0f, new Stroke(stroke, 0.0f, StrokeCap.INSTANCE.m6443getRoundKaPHkGw(), 0, null, 26, null), null, 0, 832, null);
                    DrawScope.m6621drawArcyD3GUKo$default($this$Canvas, $color, -48.0f, 96.0f, false, Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(0.43f * w)) << 32) | (((long) Float.floatToRawIntBits(0.14f * h)) & 4294967295L)), Size.m5883constructorimpl((((long) Float.floatToRawIntBits(0.52f * w)) << 32) | (((long) Float.floatToRawIntBits(0.72f * h)) & 4294967295L)), 0.0f, new Stroke(stroke, 0.0f, StrokeCap.INSTANCE.m6443getRoundKaPHkGw(), 0, null, 26, null), null, 0, 832, null);
                }
                Unit unit4 = Unit.INSTANCE;
                break;
            case 7:
                float f3 = w * 0.18f;
                DrawScope.m6638drawRoundRectuAw5IA$default($this$Canvas, $color, Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(0.07f * w)) << 32) | (((long) Float.floatToRawIntBits(0.09f * h)) & 4294967295L)), Size.m5883constructorimpl((((long) Float.floatToRawIntBits(w * 0.86f)) << 32) | (((long) Float.floatToRawIntBits(0.7f * h)) & 4294967295L)), CornerRadius.m5777constructorimpl((((long) Float.floatToRawIntBits(f3)) << 32) | (((long) Float.floatToRawIntBits(f3)) & 4294967295L)), new Stroke(stroke, 0.0f, StrokeCap.INSTANCE.m6443getRoundKaPHkGw(), 0, null, 26, null), 0.0f, null, 0, 224, null);
                Path tail = AndroidPath_androidKt.Path();
                tail.moveTo(0.61f * w, h * 0.78f);
                tail.lineTo(0.52f * w, 0.93f * h);
                tail.lineTo(w * 0.72f, 0.79f * h);
                DrawScope.m6632drawPathLG529CI$default($this$Canvas, tail, $color, 0.0f, new Stroke(stroke, 0.0f, StrokeCap.INSTANCE.m6443getRoundKaPHkGw(), 0, null, 26, null), null, 0, 52, null);
                float f4 = 0.03f * w;
                DrawScope.m6638drawRoundRectuAw5IA$default($this$Canvas, $color, Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(w * 0.28f)) << 32) | (((long) Float.floatToRawIntBits(h * 0.31f)) & 4294967295L)), Size.m5883constructorimpl((((long) Float.floatToRawIntBits(w * 0.1f)) << 32) | (((long) Float.floatToRawIntBits(h * 0.18f)) & 4294967295L)), CornerRadius.m5777constructorimpl((((long) Float.floatToRawIntBits(f4)) << 32) | (((long) Float.floatToRawIntBits(f4)) & 4294967295L)), null, 0.0f, null, 0, PsExtractor.VIDEO_STREAM_MASK, null);
                float f5 = 0.03f * w;
                DrawScope.m6638drawRoundRectuAw5IA$default($this$Canvas, $color, Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(0.53f * w)) << 32) | (((long) Float.floatToRawIntBits(0.31f * h)) & 4294967295L)), Size.m5883constructorimpl((((long) Float.floatToRawIntBits(w * 0.1f)) << 32) | (((long) Float.floatToRawIntBits(0.18f * h)) & 4294967295L)), CornerRadius.m5777constructorimpl((((long) Float.floatToRawIntBits(f5)) << 32) | (((long) Float.floatToRawIntBits(f5)) & 4294967295L)), null, 0.0f, null, 0, PsExtractor.VIDEO_STREAM_MASK, null);
                DrawScope.m6628drawLineNGM6Ib0$default($this$Canvas, $color, Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(w * 0.28f)) << 32) | (((long) Float.floatToRawIntBits(h * 0.49f)) & 4294967295L)), Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(0.23f * w)) << 32) | (((long) Float.floatToRawIntBits(h * 0.58f)) & 4294967295L)), stroke, StrokeCap.INSTANCE.m6443getRoundKaPHkGw(), null, 0.0f, null, 0, WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND, null);
                DrawScope.m6628drawLineNGM6Ib0$default($this$Canvas, $color, Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(0.53f * w)) << 32) | (((long) Float.floatToRawIntBits(0.49f * h)) & 4294967295L)), Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(w * 0.48f)) << 32) | (((long) Float.floatToRawIntBits(0.58f * h)) & 4294967295L)), stroke, StrokeCap.INSTANCE.m6443getRoundKaPHkGw(), null, 0.0f, null, 0, WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND, null);
                Unit unit5 = Unit.INSTANCE;
                break;
            case 8:
                float f6 = 0.1f * w;
                DrawScope.m6638drawRoundRectuAw5IA$default($this$Canvas, $color, Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(w * 0.1f)) << 32) | (((long) Float.floatToRawIntBits(0.12f * h)) & 4294967295L)), Size.m5883constructorimpl((((long) Float.floatToRawIntBits(0.8f * w)) << 32) | (((long) Float.floatToRawIntBits(0.68f * h)) & 4294967295L)), CornerRadius.m5777constructorimpl((((long) Float.floatToRawIntBits(f6)) << 32) | (((long) Float.floatToRawIntBits(f6)) & 4294967295L)), new Stroke(stroke, 0.0f, StrokeCap.INSTANCE.m6443getRoundKaPHkGw(), 0, null, 26, null), 0.0f, null, 0, 224, null);
                float f7 = 0.07f * w;
                DrawScope.m6638drawRoundRectuAw5IA$default($this$Canvas, $color, Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(w * 0.48f)) << 32) | (((long) Float.floatToRawIntBits(0.51f * h)) & 4294967295L)), Size.m5883constructorimpl((((long) Float.floatToRawIntBits(0.38f * w)) << 32) | (((long) Float.floatToRawIntBits(0.34f * h)) & 4294967295L)), CornerRadius.m5777constructorimpl((((long) Float.floatToRawIntBits(f7)) << 32) | (((long) Float.floatToRawIntBits(f7)) & 4294967295L)), new Stroke(stroke, 0.0f, StrokeCap.INSTANCE.m6443getRoundKaPHkGw(), 0, null, 26, null), 0.0f, null, 0, 224, null);
                DrawScope.m6628drawLineNGM6Ib0$default($this$Canvas, $color, Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(w * 0.29f)) << 32) | (((long) Float.floatToRawIntBits(0.31f * h)) & 4294967295L)), Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(0.47f * w)) << 32) | (((long) Float.floatToRawIntBits(h * 0.49f)) & 4294967295L)), stroke, StrokeCap.INSTANCE.m6443getRoundKaPHkGw(), null, 0.0f, null, 0, WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND, null);
                Path arrow = AndroidPath_androidKt.Path();
                arrow.moveTo(0.39f * w, h * 0.48f);
                arrow.lineTo(w * 0.49f, h * 0.49f);
                arrow.lineTo(w * 0.48f, 0.39f * h);
                DrawScope.m6632drawPathLG529CI$default($this$Canvas, arrow, $color, 0.0f, new Stroke(stroke, 0.0f, StrokeCap.INSTANCE.m6443getRoundKaPHkGw(), 0, null, 26, null), null, 0, 52, null);
                Unit unit6 = Unit.INSTANCE;
                break;
            case 9:
                float stroke2 = stroke;
                Iterator it = CollectionsKt.listOf((Object[]) new Float[]{Float.valueOf(0.27f), Float.valueOf(0.5f), Float.valueOf(0.73f)}).iterator();
                while (it.hasNext()) {
                    float fFloatValue = ((Number) it.next()).floatValue();
                    DrawScope.m6623drawCircleVaOC9Bg$default($this$Canvas, $color, stroke2 * 0.72f, Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(0.17f * w)) << 32) | (((long) Float.floatToRawIntBits(h * fFloatValue)) & 4294967295L)), 0.0f, null, null, 0, 120, null);
                    float stroke3 = stroke2;
                    DrawScope.m6628drawLineNGM6Ib0$default($this$Canvas, $color, Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(0.33f * w)) << 32) | (((long) Float.floatToRawIntBits(h * fFloatValue)) & 4294967295L)), Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(0.88f * w)) << 32) | (((long) Float.floatToRawIntBits(h * fFloatValue)) & 4294967295L)), stroke3, StrokeCap.INSTANCE.m6443getRoundKaPHkGw(), null, 0.0f, null, 0, WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND, null);
                    stroke2 = stroke3;
                }
                Unit unit7 = Unit.INSTANCE;
                break;
            case 10:
                List xs = CollectionsKt.listOf((Object[]) new Float[]{Float.valueOf(0.18f), Float.valueOf(0.38f), Float.valueOf(0.6f), Float.valueOf(0.82f)});
                List heights = CollectionsKt.listOf((Object[]) new Float[]{Float.valueOf(0.36f), Float.valueOf(0.72f), Float.valueOf(0.55f), Float.valueOf(0.3f)});
                for (Pair pair : CollectionsKt.zip(xs, heights)) {
                    float fFloatValue2 = ((Number) pair.component1()).floatValue();
                    float fFloatValue3 = h * ((Number) pair.component2()).floatValue() * 0.5f;
                    float stroke4 = stroke;
                    DrawScope.m6628drawLineNGM6Ib0$default(Canvas, $color, Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(w * fFloatValue2)) << 32) | (((long) Float.floatToRawIntBits((h * 0.5f) - fFloatValue3)) & 4294967295L)), Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(w * fFloatValue2)) << 32) | (((long) Float.floatToRawIntBits((h * 0.5f) + fFloatValue3)) & 4294967295L)), stroke4, StrokeCap.INSTANCE.m6443getRoundKaPHkGw(), null, 0.0f, null, 0, WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND, null);
                    Canvas = $this$Canvas;
                    stroke = stroke4;
                }
                Unit unit8 = Unit.INSTANCE;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        return Unit.INSTANCE;
    }

    private static final void CupertinoGlyph_XO_JAsU$lambda$0$0$triangle(boolean forward, float h, DrawScope $this_Canvas, long $color, float x0, float x1) {
        Path p = AndroidPath_androidKt.Path();
        if (forward) {
            p.moveTo(x0, 0.17f * h);
            p.lineTo(x1, 0.5f * h);
            p.lineTo(x0, 0.83f * h);
        } else {
            p.moveTo(x1, 0.17f * h);
            p.lineTo(x0, 0.5f * h);
            p.lineTo(x1, 0.83f * h);
        }
        p.close();
        DrawScope.m6632drawPathLG529CI$default($this_Canvas, p, $color, 0.0f, null, null, 0, 60, null);
    }

    private static final String formatDurationV3(long milliseconds) {
        long seconds = RangesKt.coerceAtLeast(milliseconds, 0L) / 1000;
        String str = String.format("%d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(seconds / 60), Long.valueOf(seconds % 60)}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}
