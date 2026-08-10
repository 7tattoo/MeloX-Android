package com.lladlam.melox.ui;

import android.content.Context;
import androidx.activity.compose.BackHandlerKt;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.animation.SharedTransitionScope;
import androidx.compose.animation.SharedTransitionScopeKt;
import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.DarkThemeKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.BoxWithConstraintsKt;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.foundation.layout.WindowInsetsPadding_androidKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TextKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.C1301Dp;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.core.app.NotificationCompat;
import androidx.core.location.LocationRequestCompat;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.offline.DownloadService;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.profileinstaller.ProfileVerifier;
import androidx.window.core.layout.WindowSizeClass;
import com.kyant.backdrop.Backdrop;
import com.kyant.backdrop.backdrops.LayerBackdrop;
import com.kyant.backdrop.backdrops.LayerBackdropKt;
import com.kyant.backdrop.backdrops.LayerBackdropModifierKt;
import com.lladlam.melox.core.account.NeteaseSessionStore;
import com.lladlam.melox.core.account.NeteaseSessionStoreKt;
import com.lladlam.melox.core.crash.MeloXCrashReporter;
import com.lladlam.melox.ui.account.NeteaseLoginScreenKt;
import com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt;
import com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt;
import com.lladlam.melox.ui.library.LibraryScreenKt;
import com.lladlam.melox.ui.player.MeloXIOSMiniPlayerKt;
import com.lladlam.melox.ui.player.MeloXIOSNowPlayingSharedHostKt;
import com.lladlam.melox.ui.player.MeloXPlaybackUiState;
import com.lladlam.melox.ui.player.MeloXPlayerUiKt;
import com.lladlam.melox.ui.search.SearchScreenKt;
import com.lladlam.melox.ui.settings.SettingsScreenKt;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MeloXApp.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\r\u001a\u0017\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007¢\u0006\u0002\u0010\u0007\u001a\u001d\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0003¢\u0006\u0002\u0010\f\u001a^\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00040\u00132\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00112\b\b\u0002\u0010\u0016\u001a\u00020\u00172\u0011\u0010\u0018\u001a\r\u0012\u0004\u0012\u00020\u00040\u0019¢\u0006\u0002\b\u001aH\u0003¢\u0006\u0002\u0010\u001b\u001a\r\u0010\u001c\u001a\u00020\u0001H\u0003¢\u0006\u0002\u0010\u001d\u001a\r\u0010\u001e\u001a\u00020\u0001H\u0003¢\u0006\u0002\u0010\u001d\u001a1\u0010\u001f\u001a\u00020\u0004*\u00020 2\u0006\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00112\u0006\u0010%\u001a\u00020&H\u0003¢\u0006\u0002\u0010'\u001a\f\u0010(\u001a\u00020#*\u00020\u000fH\u0002\u001a'\u0010)\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010*\u001a\u00020\u0001H\u0003¢\u0006\u0004\b+\u0010,\u001a \u0010-\u001a\u00020&2\u0006\u0010.\u001a\u00020&2\u0006\u0010/\u001a\u00020&2\u0006\u00100\u001a\u00020&H\u0002\u001a'\u00101\u001a\u0002022\u0006\u0010/\u001a\u0002022\u0006\u00100\u001a\u0002022\u0006\u00103\u001a\u00020&H\u0002¢\u0006\u0004\b4\u00105\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002¨\u00066²\u0006\n\u0010\u000e\u001a\u00020\u000fX\u008a\u008e\u0002²\u0006\n\u00107\u001a\u00020\u0011X\u008a\u008e\u0002²\u0006\n\u00108\u001a\u00020\u0011X\u008a\u008e\u0002²\u0006\n\u00109\u001a\u00020\u000fX\u008a\u008e\u0002²\u0006\n\u0010:\u001a\u00020\u0011X\u008a\u008e\u0002²\u0006\n\u0010;\u001a\u00020&X\u008a\u008e\u0002²\u0006\n\u0010<\u001a\u00020&X\u008a\u0084\u0002²\u0006\n\u0010=\u001a\u00020&X\u008a\u0084\u0002²\u0006\n\u0010>\u001a\u00020&X\u008a\u0084\u0002²\u0006\n\u0010?\u001a\u00020\u0001X\u008a\u0084\u0002"}, d2 = {"MeloXAccent", "Landroidx/compose/ui/graphics/Color;", "J", "MeloXApp", "", "openNowPlayingRequest", "", "(ILandroidx/compose/runtime/Composer;II)V", "MeloXSectionShell", "title", "", "subtitle", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "MeloXBottomChrome", "selectedTab", "Lcom/lladlam/melox/ui/AppTab;", "dynamicGlassEnabled", "", "onSelect", "Lkotlin/Function1;", "hasMedia", "minimized", "modifier", "Landroidx/compose/ui/Modifier;", "miniPlayer", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Lcom/lladlam/melox/ui/AppTab;ZLkotlin/jvm/functions/Function1;ZZLandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "bottomLiquidGlassTint", "(Landroidx/compose/runtime/Composer;I)J", "bottomGlassFallbackColor", "RootTabButton", "Landroidx/compose/foundation/layout/RowScope;", "tab", "glyph", "Lcom/lladlam/melox/ui/RootGlyph;", "selected", "labelAlpha", "", "(Landroidx/compose/foundation/layout/RowScope;Lcom/lladlam/melox/ui/AppTab;Lcom/lladlam/melox/ui/RootGlyph;ZFLandroidx/compose/runtime/Composer;I)V", "rootGlyph", "RootGlyphIcon", TtmlNode.ATTR_TTS_COLOR, "RootGlyphIcon-XO-JAsU", "(Lcom/lladlam/melox/ui/RootGlyph;Landroidx/compose/ui/Modifier;JLandroidx/compose/runtime/Composer;I)V", "smoothStep", "value", TtmlNode.START, TtmlNode.END, "lerpDp", "Landroidx/compose/ui/unit/Dp;", NotificationCompat.CATEGORY_PROGRESS, "lerpDp-Md-fbLM", "(FFF)F", "app", "showNowPlaying", "showNeteaseLogin", "loginReturnTab", "tabBarMinimized", "scrollAccumulator", "rawProgress", "lensPosition", "lensAlpha", DownloadService.KEY_FOREGROUND}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class MeloXAppKt {
    private static final long MeloXAccent = ColorKt.Color(4294914375L);

    /* JADX INFO: compiled from: MeloXApp.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[AppTab.values().length];
            try {
                iArr[AppTab.Search.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AppTab.Home.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[AppTab.Explore.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[AppTab.Library.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[AppTab.Settings.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[RootGlyph.values().length];
            try {
                iArr2[RootGlyph.Home.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr2[RootGlyph.Explore.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr2[RootGlyph.Library.ordinal()] = 3;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr2[RootGlyph.Settings.ordinal()] = 4;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr2[RootGlyph.Search.ordinal()] = 5;
            } catch (NoSuchFieldError e10) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    static final Unit MeloXApp$lambda$24(int i, int i2, int i3, Composer composer, int i4) {
        MeloXApp(i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit MeloXBottomChrome$lambda$2(AppTab appTab, boolean z, Function1 function1, boolean z2, boolean z3, Modifier modifier, Function2 function2, int i, int i2, Composer composer, int i3) {
        MeloXBottomChrome(appTab, z, function1, z2, z3, modifier, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit MeloXSectionShell$lambda$1(String str, String str2, int i, Composer composer, int i2) {
        MeloXSectionShell(str, str2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit RootGlyphIcon_XO_JAsU$lambda$1(RootGlyph rootGlyph, Modifier modifier, long j, int i, Composer composer, int i2) {
        m9604RootGlyphIconXOJAsU(rootGlyph, modifier, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit RootTabButton$lambda$2(RowScope rowScope, AppTab appTab, RootGlyph rootGlyph, boolean z, float f, int i, Composer composer, int i2) {
        RootTabButton(rowScope, appTab, rootGlyph, z, f, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:82:0x0289  */
    /* JADX WARN: Code duplicated, block: B:83:0x0298  */
    /* JADX WARN: Code duplicated, block: B:86:0x02ad  */
    /* JADX WARN: Code duplicated, block: B:87:0x02b0  */
    /* JADX WARN: Code duplicated, block: B:90:0x02db  */
    public static final void MeloXApp(int i, Composer composer, final int i2, final int i3) {
        final int i4;
        int i5;
        boolean z;
        final MutableState mutableState;
        Object objRememberedValue;
        LayerBackdrop layerBackdrop;
        LayerBackdrop layerBackdrop2;
        Composer composerStartRestartGroup = composer.startRestartGroup(579020746);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(MeloXApp)N(openNowPlayingRequest)100@4491L40,101@4558L34,102@4621L34,103@4682L44,104@4754L34,105@4818L36,106@4879L30,107@4935L29,108@4989L23,109@5047L7,112@5200L1029,140@6297L118,140@6235L180,146@6459L102,146@6421L140,152@6595L70,152@6567L98,159@6785L5253,157@6671L5367:MeloXApp.kt#5am3v9");
        int i6 = i2;
        int i7 = i3 & 1;
        if (i7 != 0) {
            i6 |= 6;
            i4 = i;
        } else if ((i2 & 6) == 0) {
            i4 = i;
            i6 |= composerStartRestartGroup.changed(i4) ? 4 : 2;
        } else {
            i4 = i;
        }
        if (composerStartRestartGroup.shouldExecute((i6 & 3) != 2, i6 & 1)) {
            if (i7 != 0) {
                i5 = 0;
            } else {
                i5 = i4;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(579020746, i6, -1, "com.lladlam.melox.ui.MeloXApp (MeloXApp.kt:99)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1906809070, "CC(remember):MeloXApp.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AppTab.Home, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default);
                objRememberedValue2 = objMutableStateOf$default;
            }
            final MutableState mutableState2 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1906806932, "CC(remember):MeloXApp.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default2);
                objRememberedValue3 = objMutableStateOf$default2;
            }
            MutableState mutableState3 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1906804916, "CC(remember):MeloXApp.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                z = false;
                Object objMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default3);
                objRememberedValue4 = objMutableStateOf$default3;
            } else {
                z = false;
            }
            final MutableState mutableState4 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1906802954, "CC(remember):MeloXApp.kt#9igjgp");
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AppTab.Settings, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default4);
                objRememberedValue5 = objMutableStateOf$default4;
            }
            final MutableState mutableState5 = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1906800660, "CC(remember):MeloXApp.kt#9igjgp");
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(z), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default5);
                objRememberedValue6 = objMutableStateOf$default5;
            }
            final MutableState mutableState6 = (MutableState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1906798610, "CC(remember):MeloXApp.kt#9igjgp");
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                Object objMutableFloatStateOf = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
                composerStartRestartGroup.updateRememberedValue(objMutableFloatStateOf);
                objRememberedValue7 = objMutableFloatStateOf;
            }
            final MutableFloatState mutableFloatState = (MutableFloatState) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            boolean z2 = z;
            final MeloXPlaybackUiState meloXPlaybackUiStateRememberMeloXPlaybackUiState = MeloXPlayerUiKt.rememberMeloXPlaybackUiState(composerStartRestartGroup, z2 ? 1 : 0);
            final NeteaseSessionStore neteaseSessionStoreRememberNeteaseSessionStore = NeteaseSessionStoreKt.rememberNeteaseSessionStore(composerStartRestartGroup, z2 ? 1 : 0);
            final LayerBackdrop layerBackdropRememberLayerBackdrop = LayerBackdropKt.rememberLayerBackdrop(null, null, composerStartRestartGroup, z2 ? 1 : 0, 3);
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            int i8 = i6;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Context applicationContext = ((Context) objConsume).getApplicationContext();
            final boolean z3 = MeloXApp$lambda$1(mutableState2) == AppTab.Home || MeloXApp$lambda$1(mutableState2) == AppTab.Explore;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1906785393, "CC(remember):MeloXApp.kt#9igjgp");
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                Object obj = new NestedScrollConnection() { // from class: com.lladlam.melox.ui.MeloXAppKt$MeloXApp$tabBarMinimizeConnection$1$1
                    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
                    /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
                    public /* bridge */ Object mo1357onPostFlingRZ2iAVY(long consumed, long available, Continuation<? super Velocity> continuation) {
                        return super.mo1357onPostFlingRZ2iAVY(consumed, available, continuation);
                    }

                    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
                    /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
                    public /* bridge */ long mo1358onPostScrollDzOQY0M(long consumed, long available, int source) {
                        return super.mo1358onPostScrollDzOQY0M(consumed, available, source);
                    }

                    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
                    /* JADX INFO: renamed from: onPreFling-QWom1Mo */
                    public /* bridge */ Object mo1904onPreFlingQWom1Mo(long available, Continuation<? super Velocity> continuation) {
                        return super.mo1904onPreFlingQWom1Mo(available, continuation);
                    }

                    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
                    /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
                    public long mo1905onPreScrollOzD1aCk(long available, int source) {
                        if (!NestedScrollSource.m7262equalsimpl0(source, NestedScrollSource.INSTANCE.m7274getUserInputWNlRxjI())) {
                            return Offset.INSTANCE.m5839getZeroF1C5BW0();
                        }
                        if (Float.intBitsToFloat((int) (available & 4294967295L)) < 0.0f) {
                            if (MeloXAppKt.MeloXApp$lambda$16(mutableFloatState) > 0.0f) {
                                mutableFloatState.setFloatValue(0.0f);
                            }
                            mutableFloatState.setFloatValue(MeloXAppKt.MeloXApp$lambda$16(mutableFloatState) + Float.intBitsToFloat((int) (4294967295L & available)));
                            if (MeloXAppKt.MeloXApp$lambda$16(mutableFloatState) <= -18.0f) {
                                MeloXAppKt.MeloXApp$lambda$14(mutableState6, true);
                                mutableFloatState.setFloatValue(0.0f);
                            }
                        } else if (Float.intBitsToFloat((int) (available & 4294967295L)) > 0.0f) {
                            if (MeloXAppKt.MeloXApp$lambda$16(mutableFloatState) < 0.0f) {
                                mutableFloatState.setFloatValue(0.0f);
                            }
                            mutableFloatState.setFloatValue(MeloXAppKt.MeloXApp$lambda$16(mutableFloatState) + Float.intBitsToFloat((int) (4294967295L & available)));
                            if (MeloXAppKt.MeloXApp$lambda$16(mutableFloatState) >= 18.0f) {
                                MeloXAppKt.MeloXApp$lambda$14(mutableState6, false);
                                mutableFloatState.setFloatValue(0.0f);
                            }
                        }
                        return Offset.INSTANCE.m5839getZeroF1C5BW0();
                    }
                };
                composerStartRestartGroup.updateRememberedValue(obj);
                objRememberedValue8 = obj;
            }
            final MeloXAppKt$MeloXApp$tabBarMinimizeConnection$1$1 meloXAppKt$MeloXApp$tabBarMinimizeConnection$1$1 = (MeloXAppKt$MeloXApp$tabBarMinimizeConnection$1$1) objRememberedValue8;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Integer numValueOf = Integer.valueOf(i5);
            Boolean boolValueOf = Boolean.valueOf(meloXPlaybackUiStateRememberMeloXPlaybackUiState.getHasMedia());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1906751200, "CC(remember):MeloXApp.kt#9igjgp");
            boolean zChanged = ((i8 & 14) == 4) | composerStartRestartGroup.changed(meloXPlaybackUiStateRememberMeloXPlaybackUiState);
            Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = (Function2) new MeloXAppKt$MeloXApp$1$1(i5, meloXPlaybackUiStateRememberMeloXPlaybackUiState, mutableState3, null);
                composerStartRestartGroup.updateRememberedValue(obj2);
                objRememberedValue9 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.LaunchedEffect(numValueOf, boolValueOf, (Function2) objRememberedValue9, composerStartRestartGroup, i8 & 14);
            String cookie = neteaseSessionStoreRememberNeteaseSessionStore.getCookie();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1906746032, "CC(remember):MeloXApp.kt#9igjgp");
            boolean zChanged2 = composerStartRestartGroup.changed(neteaseSessionStoreRememberNeteaseSessionStore);
            Object objRememberedValue10 = composerStartRestartGroup.rememberedValue();
            if (!zChanged2) {
                mutableState = mutableState3;
                if (objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.LaunchedEffect(cookie, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue10, composerStartRestartGroup, 0);
                AppTab appTabMeloXApp$lambda$1 = MeloXApp$lambda$1(mutableState2);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1906741712, "CC(remember):MeloXApp.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    layerBackdrop = null;
                    Object obj3 = (Function2) new MeloXAppKt$MeloXApp$3$1(mutableState6, mutableFloatState, null);
                    composerStartRestartGroup.updateRememberedValue(obj3);
                    objRememberedValue = obj3;
                } else {
                    layerBackdrop = null;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.LaunchedEffect(appTabMeloXApp$lambda$1, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue, composerStartRestartGroup, 0);
                ProvidableCompositionLocal<Backdrop> localMeloXBackdrop = MeloXBackdropComponentsKt.getLocalMeloXBackdrop();
                if (z3) {
                    layerBackdrop2 = layerBackdropRememberLayerBackdrop;
                } else {
                    layerBackdrop2 = layerBackdrop;
                }
                CompositionLocalKt.CompositionLocalProvider(localMeloXBackdrop.provides(layerBackdrop2), ComposableLambdaKt.rememberComposableLambda(2120191114, true, new Function2() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj4, Object obj5) {
                        return MeloXAppKt.MeloXApp$lambda$23(neteaseSessionStoreRememberNeteaseSessionStore, meloXPlaybackUiStateRememberMeloXPlaybackUiState, meloXAppKt$MeloXApp$tabBarMinimizeConnection$1$1, z3, layerBackdropRememberLayerBackdrop, applicationContext, mutableState, mutableState2, mutableState5, mutableState4, mutableState6, (Composer) obj4, ((Integer) obj5).intValue());
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                i4 = i5;
            } else {
                mutableState = mutableState3;
            }
            Object obj4 = (Function2) new MeloXAppKt$MeloXApp$2$1(neteaseSessionStoreRememberNeteaseSessionStore, null);
            composerStartRestartGroup.updateRememberedValue(obj4);
            objRememberedValue10 = obj4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.LaunchedEffect(cookie, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue10, composerStartRestartGroup, 0);
            AppTab appTabMeloXApp$lambda$2 = MeloXApp$lambda$1(mutableState2);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1906741712, "CC(remember):MeloXApp.kt#9igjgp");
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                layerBackdrop = null;
                Object obj5 = (Function2) new MeloXAppKt$MeloXApp$3$1(mutableState6, mutableFloatState, null);
                composerStartRestartGroup.updateRememberedValue(obj5);
                objRememberedValue = obj5;
            } else {
                layerBackdrop = null;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.LaunchedEffect(appTabMeloXApp$lambda$2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue, composerStartRestartGroup, 0);
            ProvidableCompositionLocal<Backdrop> localMeloXBackdrop2 = MeloXBackdropComponentsKt.getLocalMeloXBackdrop();
            if (z3) {
                layerBackdrop2 = layerBackdropRememberLayerBackdrop;
            } else {
                layerBackdrop2 = layerBackdrop;
            }
            CompositionLocalKt.CompositionLocalProvider(localMeloXBackdrop2.provides(layerBackdrop2), ComposableLambdaKt.rememberComposableLambda(2120191114, true, new Function2() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj6, Object obj7) {
                    return MeloXAppKt.MeloXApp$lambda$23(neteaseSessionStoreRememberNeteaseSessionStore, meloXPlaybackUiStateRememberMeloXPlaybackUiState, meloXAppKt$MeloXApp$tabBarMinimizeConnection$1$1, z3, layerBackdropRememberLayerBackdrop, applicationContext, mutableState, mutableState2, mutableState5, mutableState4, mutableState6, (Composer) obj6, ((Integer) obj7).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            i4 = i5;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj6, Object obj7) {
                    return MeloXAppKt.MeloXApp$lambda$24(i4, i2, i3, (Composer) obj6, ((Integer) obj7).intValue());
                }
            });
        }
    }

    private static final AppTab MeloXApp$lambda$1(MutableState<AppTab> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean MeloXApp$lambda$4(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void MeloXApp$lambda$5(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean MeloXApp$lambda$7(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void MeloXApp$lambda$8(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final AppTab MeloXApp$lambda$10(MutableState<AppTab> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean MeloXApp$lambda$13(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void MeloXApp$lambda$14(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float MeloXApp$lambda$16(MutableFloatState $scrollAccumulator$delegate) {
        return $scrollAccumulator$delegate.getFloatValue();
    }

    static final Unit MeloXApp$lambda$23(final NeteaseSessionStore $neteaseSession, final MeloXPlaybackUiState $playbackState, final MeloXAppKt$MeloXApp$tabBarMinimizeConnection$1$1 $tabBarMinimizeConnection, final boolean $dynamicGlassEnabled, final LayerBackdrop $glassBackdrop, final Context $appContext, final MutableState $showNowPlaying$delegate, final MutableState $selectedTab$delegate, final MutableState $loginReturnTab$delegate, final MutableState $showNeteaseLogin$delegate, final MutableState $tabBarMinimized$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C160@6793L5239:MeloXApp.kt#5am3v9");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2120191114, $changed, -1, "com.lladlam.melox.ui.MeloXApp.<anonymous> (MeloXApp.kt:160)");
            }
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierFillMaxSize$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((6 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor);
            } else {
                $composer.useNode();
            }
            Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer);
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            int i3 = ((6 >> 6) & 112) | 6;
            final BoxScope boxScope = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 1779039925, "C161@6900L4791,161@6842L4849:MeloXApp.kt#5am3v9");
            SharedTransitionScopeKt.SharedTransitionLayout(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ComposableLambdaKt.rememberComposableLambda(-1937950898, true, new Function3() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return MeloXAppKt.MeloXApp$lambda$23$0$0($playbackState, $tabBarMinimizeConnection, $dynamicGlassEnabled, $glassBackdrop, $appContext, boxScope, $showNowPlaying$delegate, $neteaseSession, $selectedTab$delegate, $loginReturnTab$delegate, $showNeteaseLogin$delegate, $tabBarMinimized$delegate, (SharedTransitionScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 54, 0);
            if (MeloXApp$lambda$7($showNeteaseLogin$delegate)) {
                $composer.startReplaceGroup(1783730565);
                ComposerKt.sourceInformation($composer, "275@11827L28,276@11886L113,273@11737L277");
                ComposerKt.sourceInformationMarkerStart($composer, 1581563404, "CC(remember):MeloXApp.kt#9igjgp");
                Object objRememberedValue = $composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function0() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda20
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MeloXAppKt.MeloXApp$lambda$23$0$1$0($showNeteaseLogin$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                Function0 function0 = (Function0) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerStart($composer, 1581565377, "CC(remember):MeloXApp.kt#9igjgp");
                Object objRememberedValue2 = $composer.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = new Function0() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda21
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MeloXAppKt.MeloXApp$lambda$23$0$2$0($showNeteaseLogin$delegate, $loginReturnTab$delegate, $selectedTab$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj2);
                    objRememberedValue2 = obj2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                NeteaseLoginScreenKt.NeteaseLoginScreen($neteaseSession, function0, (Function0) objRememberedValue2, $composer, 432);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(1784019826);
                $composer.endReplaceGroup();
            }
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
    public static final Unit MeloXApp$lambda$23$0$0(final MeloXPlaybackUiState $playbackState, MeloXAppKt$MeloXApp$tabBarMinimizeConnection$1$1 $tabBarMinimizeConnection, final boolean $dynamicGlassEnabled, LayerBackdrop $glassBackdrop, final Context $appContext, BoxScope $this_Box, final MutableState $showNowPlaying$delegate, final NeteaseSessionStore $neteaseSession, final MutableState $selectedTab$delegate, final MutableState $loginReturnTab$delegate, final MutableState $showNeteaseLogin$delegate, MutableState $tabBarMinimized$delegate, final SharedTransitionScope SharedTransitionLayout, Composer $composer, int $changed) {
        final MutableState mutableState;
        float f;
        Object obj;
        Intrinsics.checkNotNullParameter(SharedTransitionLayout, "$this$SharedTransitionLayout");
        ComposerKt.sourceInformation($composer, "C173@7454L11,174@7492L1436,164@7026L1902,209@9090L190,217@9474L702,206@8942L1249,255@11013L291,250@10793L511,267@11627L54,267@11565L116:MeloXApp.kt#5am3v9");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(SharedTransitionLayout) ? 4 : 2;
        }
        if ($composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1937950898, $dirty, -1, "com.lladlam.melox.ui.MeloXApp.<anonymous>.<anonymous>.<anonymous> (MeloXApp.kt:162)");
            }
            final boolean fullPlayerVisible = MeloXApp$lambda$4($showNowPlaying$delegate) && $playbackState.getHasMedia();
            ScaffoldKt.m3605ScaffoldTvnljyQ(NestedScrollModifierKt.nestedScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), $tabBarMinimizeConnection, null, 2, null).then($dynamicGlassEnabled ? LayerBackdropModifierKt.layerBackdrop(Modifier.INSTANCE, $glassBackdrop) : Modifier.INSTANCE), null, null, null, null, 0, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getBackground(), 0L, WindowInsetsKt.WindowInsets(0, 0, 0, 0), ComposableLambdaKt.rememberComposableLambda(-281011875, true, new Function3() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return MeloXAppKt.MeloXApp$lambda$23$0$0$0($neteaseSession, fullPlayerVisible, $selectedTab$delegate, $loginReturnTab$delegate, $showNeteaseLogin$delegate, (PaddingValues) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), $composer, 805306368, 190);
            AppTab appTabMeloXApp$lambda$1 = MeloXApp$lambda$1($selectedTab$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, 1645063756, "CC(remember):MeloXApp.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance($appContext);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                mutableState = $tabBarMinimized$delegate;
                Object obj2 = new Function1() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return MeloXAppKt.MeloXApp$lambda$23$0$0$1$0($appContext, mutableState, $selectedTab$delegate, (AppTab) obj3);
                    }
                };
                $composer.updateRememberedValue(obj2);
                objRememberedValue = obj2;
            } else {
                mutableState = $tabBarMinimized$delegate;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            boolean z = true;
            MeloXBottomChrome(appTabMeloXApp$lambda$1, $dynamicGlassEnabled, (Function1) objRememberedValue, $playbackState.getHasMedia(), MeloXApp$lambda$13(mutableState), $this_Box.align(Modifier.INSTANCE, Alignment.INSTANCE.getBottomCenter()), ComposableLambdaKt.rememberComposableLambda(2010369375, true, new Function2() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return MeloXAppKt.MeloXApp$lambda$23$0$0$2(fullPlayerVisible, $playbackState, $dynamicGlassEnabled, SharedTransitionLayout, $showNowPlaying$delegate, mutableState, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), $composer, 1572864, 0);
            if (fullPlayerVisible) {
                $composer.startReplaceGroup(-541491027);
                ComposerKt.sourceInformation($composer, "239@10374L372,236@10246L519");
                f = 0.0f;
                obj = null;
                Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                Unit unit = Unit.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, 1645105026, "CC(remember):MeloXApp.kt#9igjgp");
                Object objRememberedValue2 = $composer.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object obj3 = (PointerInputEventHandler) new PointerInputEventHandler() { // from class: com.lladlam.melox.ui.MeloXAppKt$MeloXApp$5$1$1$4$1

                        /* JADX INFO: renamed from: com.lladlam.melox.ui.MeloXAppKt$MeloXApp$5$1$1$4$1$1 */
                        /* JADX INFO: compiled from: MeloXApp.kt */
                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
                        @DebugMetadata(c = "com.lladlam.melox.ui.MeloXAppKt$MeloXApp$5$1$1$4$1$1", f = "MeloXApp.kt", i = {0}, l = {243}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope"}, nl = {244}, s = {"L$0"}, v = 2)
                        static final class C26181 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                            private /* synthetic */ Object L$0;
                            int label;

                            C26181(Continuation<? super C26181> continuation) {
                                super(2, continuation);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                C26181 c26181 = new C26181(continuation);
                                c26181.L$0 = obj;
                                return c26181;
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                                return ((C26181) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            /* JADX WARN: Code duplicated, block: B:10:0x0032 A[RETURN] */
                            /* JADX WARN: Code duplicated, block: B:11:0x0033  */
                            /* JADX WARN: Code duplicated, block: B:15:0x004c A[LOOP:0: B:13:0x0046->B:15:0x004c, LOOP_END] */
                            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x0033 -> B:12:0x0039). Please report as a decompilation issue!!! */
                            /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                                jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:11:0x0033
                                	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                                	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                                	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                                */
                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final java.lang.Object invokeSuspend(java.lang.Object r12) {
                                /*
                                    r11 = this;
                                    java.lang.Object r0 = r11.L$0
                                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r0 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r0
                                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                                    int r2 = r11.label
                                    switch(r2) {
                                        case 0: goto L1d;
                                        case 1: goto L15;
                                        default: goto Ld;
                                    }
                                Ld:
                                    java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                                    java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                                    r1.<init>(r2)
                                    throw r1
                                L15:
                                    kotlin.ResultKt.throwOnFailure(r12)
                                    r3 = r11
                                    r2 = r1
                                    r1 = r0
                                    r0 = r12
                                    goto L39
                                L1d:
                                    kotlin.ResultKt.throwOnFailure(r12)
                                    r2 = r11
                                L21:
                                    androidx.compose.ui.input.pointer.PointerEventPass r3 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                                    r4 = r2
                                    kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                                    r2.L$0 = r0
                                    r5 = 1
                                    r2.label = r5
                                    java.lang.Object r3 = r0.awaitPointerEvent(r3, r4)
                                    if (r3 != r1) goto L33
                                    return r1
                                L33:
                                    r10 = r0
                                    r0 = r12
                                    r12 = r3
                                    r3 = r2
                                    r2 = r1
                                    r1 = r10
                                L39:
                                    androidx.compose.ui.input.pointer.PointerEvent r12 = (androidx.compose.ui.input.pointer.PointerEvent) r12
                                    java.util.List r4 = r12.getChanges()
                                    java.lang.Iterable r4 = (java.lang.Iterable) r4
                                    r5 = 0
                                    java.util.Iterator r6 = r4.iterator()
                                L46:
                                    boolean r7 = r6.hasNext()
                                    if (r7 == 0) goto L58
                                    java.lang.Object r7 = r6.next()
                                    r8 = r7
                                    androidx.compose.ui.input.pointer.PointerInputChange r8 = (androidx.compose.ui.input.pointer.PointerInputChange) r8
                                    r9 = 0
                                    r8.consume()
                                    goto L46
                                L58:
                                    r12 = r0
                                    r0 = r1
                                    r1 = r2
                                    r2 = r3
                                    goto L21
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.lladlam.melox.ui.MeloXAppKt$MeloXApp$5$1$1$4$1.C26181.invokeSuspend(java.lang.Object):java.lang.Object");
                            }
                        }

                        @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                        public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
                            Object objAwaitPointerEventScope = $this$pointerInput.awaitPointerEventScope(new C26181(null), continuation);
                            return objAwaitPointerEventScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitPointerEventScope : Unit.INSTANCE;
                        }
                    };
                    $composer.updateRememberedValue(obj3);
                    objRememberedValue2 = obj3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                BoxKt.Box(SuspendingPointerInputFilterKt.pointerInput(modifierFillMaxSize$default, unit, (PointerInputEventHandler) objRememberedValue2), $composer, 0);
                $composer.endReplaceGroup();
            } else {
                f = 0.0f;
                obj = null;
                $composer.startReplaceGroup(-540961516);
                $composer.endReplaceGroup();
            }
            AnimatedVisibilityKt.AnimatedVisibility(fullPlayerVisible, SizeKt.fillMaxSize$default(Modifier.INSTANCE, f, 1, obj), EnterTransition.INSTANCE.getNone(), ExitTransition.INSTANCE.getNone(), (String) null, ComposableLambdaKt.rememberComposableLambda(-851222490, true, new Function3() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj4, Object obj5, Object obj6) {
                    return MeloXAppKt.MeloXApp$lambda$23$0$0$4($playbackState, SharedTransitionLayout, $showNowPlaying$delegate, (AnimatedVisibilityScope) obj4, (Composer) obj5, ((Integer) obj6).intValue());
                }
            }, $composer, 54), $composer, 196656, 16);
            if (!fullPlayerVisible || MeloXApp$lambda$7($showNeteaseLogin$delegate)) {
                z = false;
            }
            ComposerKt.sourceInformationMarkerStart($composer, 1645144804, "CC(remember):MeloXApp.kt#9igjgp");
            Object objRememberedValue3 = $composer.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object obj4 = new Function0() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MeloXAppKt.MeloXApp$lambda$23$0$0$5$0($showNowPlaying$delegate);
                    }
                };
                $composer.updateRememberedValue(obj4);
                objRememberedValue3 = obj4;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            BackHandlerKt.BackHandler(z, (Function0) objRememberedValue3, $composer, 48, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXApp$lambda$23$0$0$0(NeteaseSessionStore $neteaseSession, boolean $fullPlayerVisible, MutableState $selectedTab$delegate, final MutableState $loginReturnTab$delegate, final MutableState $showNeteaseLogin$delegate, PaddingValues innerPadding, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Intrinsics.checkNotNullParameter(innerPadding, "innerPadding");
        ComposerKt.sourceInformation($composer, "CN(innerPadding)175@7526L1388:MeloXApp.kt#5am3v9");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(innerPadding) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (!$composer.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-281011875, $dirty2, -1, "com.lladlam.melox.ui.MeloXApp.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MeloXApp.kt:175)");
            }
            Modifier modifierPadding = PaddingKt.padding(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), innerPadding);
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierPadding);
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
            Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer);
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -965789602, "C:MeloXApp.kt#5am3v9");
            switch (WhenMappings.$EnumSwitchMapping$0[MeloXApp$lambda$1($selectedTab$delegate).ordinal()]) {
                case 1:
                    $composer.startReplaceGroup(384488293);
                    ComposerKt.sourceInformation($composer, "181@7760L14");
                    SearchScreenKt.SearchScreen($composer, 0);
                    $composer.endReplaceGroup();
                    break;
                case 2:
                    $composer.startReplaceGroup(384490038);
                    ComposerKt.sourceInformation($composer, "182@7814L31");
                    MeloXDiscoveryScreensKt.MeloXHomeScreen($neteaseSession, $composer, 0);
                    $composer.endReplaceGroup();
                    break;
                case 3:
                    $composer.startReplaceGroup(384492409);
                    ComposerKt.sourceInformation($composer, "183@7888L34");
                    MeloXDiscoveryScreensKt.MeloXExploreScreen($neteaseSession, $composer, 0);
                    $composer.endReplaceGroup();
                    break;
                case 4:
                    $composer.startReplaceGroup(-965543959);
                    ComposerKt.sourceInformation($composer, "190@8365L151,184@7965L578");
                    boolean z = !$fullPlayerVisible;
                    ComposerKt.sourceInformationMarkerStart($composer, 384507790, "CC(remember):MeloXApp.kt#9igjgp");
                    Object objRememberedValue = $composer.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function0() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda14
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return MeloXAppKt.MeloXApp$lambda$23$0$0$0$0$0$0($loginReturnTab$delegate, $showNeteaseLogin$delegate);
                            }
                        };
                        $composer.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    LibraryScreenKt.LibraryScreen($neteaseSession, (Function0) objRememberedValue, z, $composer, 48, 0);
                    $composer.endReplaceGroup();
                    break;
                case 5:
                    $composer.startReplaceGroup(384515030);
                    ComposerKt.sourceInformation($composer, "197@8695L152,195@8587L287");
                    ComposerKt.sourceInformationMarkerStart($composer, 384518351, "CC(remember):MeloXApp.kt#9igjgp");
                    Object objRememberedValue2 = $composer.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue2 = new Function0() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda15
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return MeloXAppKt.MeloXApp$lambda$23$0$0$0$0$1$0($loginReturnTab$delegate, $showNeteaseLogin$delegate);
                            }
                        };
                        $composer.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    SettingsScreenKt.SettingsScreen($neteaseSession, (Function0) objRememberedValue2, $composer, 48);
                    $composer.endReplaceGroup();
                    break;
                default:
                    $composer.startReplaceGroup(384487493);
                    $composer.endReplaceGroup();
                    throw new NoWhenBranchMatchedException();
            }
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

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXApp$lambda$23$0$0$0$0$0$0(MutableState $loginReturnTab$delegate, MutableState $showNeteaseLogin$delegate) {
        $loginReturnTab$delegate.setValue(AppTab.Library);
        MeloXApp$lambda$8($showNeteaseLogin$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXApp$lambda$23$0$0$0$0$1$0(MutableState $loginReturnTab$delegate, MutableState $showNeteaseLogin$delegate) {
        $loginReturnTab$delegate.setValue(AppTab.Settings);
        MeloXApp$lambda$8($showNeteaseLogin$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXApp$lambda$23$0$0$1$0(Context $appContext, MutableState $tabBarMinimized$delegate, MutableState $selectedTab$delegate, AppTab tab) {
        Intrinsics.checkNotNullParameter(tab, "tab");
        MeloXCrashReporter meloXCrashReporter = MeloXCrashReporter.INSTANCE;
        Intrinsics.checkNotNull($appContext);
        meloXCrashReporter.recordAction($appContext, "打开" + tab.getTitle());
        MeloXApp$lambda$14($tabBarMinimized$delegate, false);
        $selectedTab$delegate.setValue(tab);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXApp$lambda$23$0$0$2(boolean $fullPlayerVisible, final MeloXPlaybackUiState $playbackState, final boolean $dynamicGlassEnabled, final SharedTransitionScope $sharedScope, final MutableState $showNowPlaying$delegate, final MutableState $tabBarMinimized$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C222@9698L460,218@9496L662:MeloXApp.kt#5am3v9");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2010369375, $changed, -1, "com.lladlam.melox.ui.MeloXApp.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MeloXApp.kt:218)");
            }
            AnimatedVisibilityKt.AnimatedVisibility(!$fullPlayerVisible, (Modifier) null, EnterTransition.INSTANCE.getNone(), ExitTransition.INSTANCE.getNone(), (String) null, ComposableLambdaKt.rememberComposableLambda(607053191, true, new Function3() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda25
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return MeloXAppKt.MeloXApp$lambda$23$0$0$2$0($playbackState, $dynamicGlassEnabled, $sharedScope, $showNowPlaying$delegate, $tabBarMinimized$delegate, (AnimatedVisibilityScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, ProfileVerifier.CompilationStatus.f253xf2722a21, 18);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXApp$lambda$23$0$0$2$0(MeloXPlaybackUiState $playbackState, boolean $dynamicGlassEnabled, SharedTransitionScope $sharedScope, final MutableState $showNowPlaying$delegate, MutableState $tabBarMinimized$delegate, AnimatedVisibilityScope AnimatedVisibility, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
        ComposerKt.sourceInformation($composer, "C225@9834L25,223@9724L412:MeloXApp.kt#5am3v9");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(607053191, $changed, -1, "com.lladlam.melox.ui.MeloXApp.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MeloXApp.kt:223)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -737431328, "CC(remember):MeloXApp.kt#9igjgp");
        Object objRememberedValue = $composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object obj = new Function0() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MeloXAppKt.MeloXApp$lambda$23$0$0$2$0$0$0($showNowPlaying$delegate);
                }
            };
            $composer.updateRememberedValue(obj);
            objRememberedValue = obj;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        MeloXIOSMiniPlayerKt.MeloXIOSMiniPlayer($playbackState, (Function0) objRememberedValue, MeloXApp$lambda$13($tabBarMinimized$delegate), $dynamicGlassEnabled, $sharedScope, AnimatedVisibility, $composer, (($changed << 15) & 458752) | 48, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXApp$lambda$23$0$0$2$0$0$0(MutableState $showNowPlaying$delegate) {
        MeloXApp$lambda$5($showNowPlaying$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXApp$lambda$23$0$0$4(MeloXPlaybackUiState $playbackState, SharedTransitionScope $sharedScope, final MutableState $showNowPlaying$delegate, AnimatedVisibilityScope AnimatedVisibility, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
        ComposerKt.sourceInformation($composer, "C258@11136L26,256@11031L259:MeloXApp.kt#5am3v9");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-851222490, $changed, -1, "com.lladlam.melox.ui.MeloXApp.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MeloXApp.kt:256)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -1981022688, "CC(remember):MeloXApp.kt#9igjgp");
        Object objRememberedValue = $composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object obj = new Function0() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MeloXAppKt.MeloXApp$lambda$23$0$0$4$0$0($showNowPlaying$delegate);
                }
            };
            $composer.updateRememberedValue(obj);
            objRememberedValue = obj;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        MeloXIOSNowPlayingSharedHostKt.MeloXIOSNowPlayingSharedHost($playbackState, (Function0) objRememberedValue, $sharedScope, AnimatedVisibility, $composer, (($changed << 9) & 7168) | 48);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXApp$lambda$23$0$0$4$0$0(MutableState $showNowPlaying$delegate) {
        MeloXApp$lambda$5($showNowPlaying$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXApp$lambda$23$0$0$5$0(MutableState $showNowPlaying$delegate) {
        MeloXApp$lambda$5($showNowPlaying$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXApp$lambda$23$0$1$0(MutableState $showNeteaseLogin$delegate) {
        MeloXApp$lambda$8($showNeteaseLogin$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXApp$lambda$23$0$2$0(MutableState $showNeteaseLogin$delegate, MutableState $loginReturnTab$delegate, MutableState $selectedTab$delegate) {
        MeloXApp$lambda$8($showNeteaseLogin$delegate, false);
        $selectedTab$delegate.setValue(MeloXApp$lambda$10($loginReturnTab$delegate));
        return Unit.INSTANCE;
    }

    private static final void MeloXSectionShell(final String title, String subtitle, Composer $composer, final int $changed) {
        final String str;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(1291568069);
        ComposerKt.sourceInformation($composer3, "C(MeloXSectionShell)N(title,subtitle)291@12134L552:MeloXApp.kt#5am3v9");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(title) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(subtitle) ? 32 : 16;
        }
        if (!$composer3.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            str = subtitle;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1291568069, $dirty, -1, "com.lladlam.melox.ui.MeloXSectionShell (MeloXApp.kt:290)");
            }
            Modifier modifierM1806paddingVpY3zN4 = PaddingKt.m1806paddingVpY3zN4(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), C1301Dp.m8905constructorimpl(28), C1301Dp.m8905constructorimpl(48));
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1806paddingVpY3zN4);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((6 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor);
            } else {
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer3);
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 839644829, "C301@12443L11,296@12273L205,303@12487L30,307@12625L11,304@12526L154:MeloXApp.kt#5am3v9");
            $composer2 = $composer3;
            TextKt.m3912TextNvy7gAk(title, null, MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnBackground(), null, TextUnitKt.getSp(36), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, TextUnitKt.getSp(40), 0, false, 0, 0, null, null, $composer3, ($dirty & 14) | 1597440, 48, 260010);
            SpacerKt.Spacer(SizeKt.m1858height3ABfNKs(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(20)), $composer3, 6);
            long sp = TextUnitKt.getSp(16);
            long onBackground = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnBackground();
            str = subtitle;
            TextKt.m3912TextNvy7gAk(str, null, Color.m6066copywmQWz5c(onBackground, (14 & 1) != 0 ? Color.m6070getAlphaimpl(onBackground) : 0.48f, (14 & 2) != 0 ? Color.m6074getRedimpl(onBackground) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(onBackground) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(onBackground) : 0.0f), null, sp, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, (($dirty >> 3) & 14) | 24576, 0, 262122);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda22
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXAppKt.MeloXSectionShell$lambda$1(title, str, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void MeloXBottomChrome(final AppTab selectedTab, final boolean dynamicGlassEnabled, final Function1<? super AppTab, Unit> function1, final boolean hasMedia, final boolean minimized, Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        boolean z;
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function3;
        Composer $composer2;
        final Modifier modifier3;
        Modifier.Companion modifier4;
        Function0<ComposeUiNode> function0;
        Composer $composer3 = $composer.startRestartGroup(-790995350);
        ComposerKt.sourceInformation($composer3, "C(MeloXBottomChrome)N(selectedTab,dynamicGlassEnabled,onSelect,hasMedia,minimized,modifier,miniPlayer)322@12973L271,346@13930L8506:MeloXApp.kt#5am3v9");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(selectedTab.ordinal()) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            z = dynamicGlassEnabled;
            $dirty |= $composer3.changed(z) ? 32 : 16;
        } else {
            z = dynamicGlassEnabled;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(hasMedia) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changed(minimized) ? 16384 : 8192;
        }
        int i2 = i & 32;
        if (i2 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.f253xf2722a21;
            modifier2 = modifier;
        } else if ((196608 & $changed) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 131072 : 65536;
        } else {
            modifier2 = modifier;
        }
        if ((1572864 & $changed) == 0) {
            function3 = function2;
            $dirty |= $composer3.changedInstance(function3) ? 1048576 : 524288;
        } else {
            function3 = function2;
        }
        if ($composer3.shouldExecute((599187 & $dirty) != 599186, $dirty & 1)) {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-790995350, $dirty, -1, "com.lladlam.melox.ui.MeloXBottomChrome (MeloXApp.kt:321)");
            }
            Modifier modifier5 = modifier4;
            $composer2 = $composer3;
            final float progress = RangesKt.coerceIn(MeloXBottomChrome$lambda$0(AnimateAsStateKt.animateFloatAsState(minimized ? 1.0f : 0.0f, AnimationSpecKt.spring(0.9f, 330.0f, Float.valueOf(0.001f)), 0.0f, "melox-tab-minimize-progress", null, $composer3, 3120, 20)), 0.0f, 1.0f);
            float labelStage = smoothStep(progress, 0.0f, 0.32f);
            final float sizeStage = smoothStep(progress, 0.0f, 0.36f);
            final float shrinkStage = smoothStep(progress, 0.25f, 0.82f);
            float dropStage = smoothStep(progress, 0.78f, 1.0f);
            final float navHeight = m9605lerpDpMdfbLM(C1301Dp.m8905constructorimpl(56), C1301Dp.m8905constructorimpl(52), sizeStage);
            final float searchSize = m9605lerpDpMdfbLM(C1301Dp.m8905constructorimpl(56), C1301Dp.m8905constructorimpl(52), sizeStage);
            float expandedChromeHeight = hasMedia ? C1301Dp.m8905constructorimpl(119) : C1301Dp.m8905constructorimpl(62);
            float chromeHeight = m9605lerpDpMdfbLM(expandedChromeHeight, C1301Dp.m8905constructorimpl(58), dropStage);
            final float labelAlpha = 1.0f - labelStage;
            final float expandedLayerAlpha = 1.0f - smoothStep(progress, 0.43f, 0.72f);
            final float compactLayerAlpha = smoothStep(progress, 0.52f, 0.82f);
            Modifier modifierM1809paddingqDBjuR0$default = PaddingKt.m1809paddingqDBjuR0$default(WindowInsetsPadding_androidKt.navigationBarsPadding(SizeKt.fillMaxWidth$default(modifier5, 0.0f, 1, null)), 0.0f, 0.0f, 0.0f, C1301Dp.m8905constructorimpl(5), 7, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierM1809paddingqDBjuR0$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i3 = ((((0 << 3) & 112) << 6) & 896) | 6;
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
            Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer2);
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i4 = (i3 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i5 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1873935051, "C356@14217L8213,352@14084L8346:MeloXApp.kt#5am3v9");
            final boolean z2 = z;
            final Function2<? super Composer, ? super Integer, Unit> function4 = function3;
            BoxWithConstraintsKt.BoxWithConstraints(SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), chromeHeight), null, false, ComposableLambdaKt.rememberComposableLambda(863626782, true, new Function3() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda23
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return MeloXAppKt.MeloXBottomChrome$lambda$1$0(shrinkStage, hasMedia, navHeight, progress, selectedTab, function1, z2, searchSize, function4, expandedLayerAlpha, compactLayerAlpha, labelAlpha, sizeStage, (BoxWithConstraintsScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer2, 54), $composer2, 3072, 6);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda24
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXAppKt.MeloXBottomChrome$lambda$2(selectedTab, dynamicGlassEnabled, function1, hasMedia, minimized, modifier3, function2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float MeloXBottomChrome$lambda$0(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXBottomChrome$lambda$1$0(float $shrinkStage, boolean $hasMedia, float $navHeight, final float $progress, final AppTab $selectedTab, final Function1 $onSelect, boolean $dynamicGlassEnabled, float $searchSize, Function2 $miniPlayer, final float $expandedLayerAlpha, final float $compactLayerAlpha, final float $labelAlpha, final float $sizeStage, BoxWithConstraintsScope BoxWithConstraints, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Intrinsics.checkNotNullParameter(BoxWithConstraints, "$this$BoxWithConstraints");
        ComposerKt.sourceInformation($composer, "C404@16323L23,405@16387L26,402@16228L228,407@16514L520,427@17376L3533,396@15975L4934,508@21232L23,512@21433L26,506@21137L365,514@21534L27,524@21906L514,501@20923L1497:MeloXApp.kt#5am3v9");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(BoxWithConstraints) ? 4 : 2;
        }
        if ($composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(863626782, $dirty, -1, "com.lladlam.melox.ui.MeloXBottomChrome.<anonymous>.<anonymous> (MeloXApp.kt:357)");
            }
            float horizontalMargin = C1301Dp.m8905constructorimpl(16);
            float compactSize = C1301Dp.m8905constructorimpl(52);
            float fM8905constructorimpl = C1301Dp.m8905constructorimpl(8);
            float fM8905constructorimpl2 = C1301Dp.m8905constructorimpl(6);
            float navWidth = m9605lerpDpMdfbLM(C1301Dp.m8905constructorimpl(C1301Dp.m8905constructorimpl(C1301Dp.m8905constructorimpl(BoxWithConstraints.mo1523getMaxWidthD9Ej5fM() - C1301Dp.m8905constructorimpl(2 * horizontalMargin)) - fM8905constructorimpl) - C1301Dp.m8905constructorimpl(56)), compactSize, $shrinkStage);
            float navRadius = m9605lerpDpMdfbLM(C1301Dp.m8905constructorimpl(28), C1301Dp.m8905constructorimpl(26), $shrinkStage);
            RoundedCornerShape navShape = RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(navRadius);
            final List primaryTabs = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.m717to(AppTab.Home, RootGlyph.Home), TuplesKt.m717to(AppTab.Explore, RootGlyph.Explore), TuplesKt.m717to(AppTab.Library, RootGlyph.Library), TuplesKt.m717to(AppTab.Settings, RootGlyph.Settings)});
            float desiredCompactMiniVisibleWidth = ((C1301Dp) RangesKt.coerceAtLeast(C1301Dp.m8903boximpl(C1301Dp.m8905constructorimpl(C1301Dp.m8905constructorimpl(C1301Dp.m8905constructorimpl(BoxWithConstraints.mo1523getMaxWidthD9Ej5fM() - C1301Dp.m8905constructorimpl(2 * horizontalMargin)) - C1301Dp.m8905constructorimpl(2 * compactSize)) - C1301Dp.m8905constructorimpl(2 * fM8905constructorimpl2))), C1301Dp.m8903boximpl(C1301Dp.m8905constructorimpl(80)))).m8919unboximpl();
            float compactMiniWrapperWidth = ((C1301Dp) RangesKt.coerceAtMost(C1301Dp.m8903boximpl(C1301Dp.m8905constructorimpl(desiredCompactMiniVisibleWidth + C1301Dp.m8905constructorimpl(32))), C1301Dp.m8903boximpl(BoxWithConstraints.mo1523getMaxWidthD9Ej5fM()))).m8919unboximpl();
            float fM8905constructorimpl3 = C1301Dp.m8905constructorimpl(C1301Dp.m8905constructorimpl(C1301Dp.m8905constructorimpl(horizontalMargin + compactSize) + fM8905constructorimpl2) - C1301Dp.m8905constructorimpl(16));
            float miniWrapperWidth = m9605lerpDpMdfbLM(BoxWithConstraints.mo1523getMaxWidthD9Ej5fM(), compactMiniWrapperWidth, $shrinkStage);
            float compactMiniWrapperWidth2 = 0;
            float miniWrapperX = m9605lerpDpMdfbLM(C1301Dp.m8905constructorimpl(compactMiniWrapperWidth2), fM8905constructorimpl3, $shrinkStage);
            float expandedNavWidth = 62;
            float miniLift = m9605lerpDpMdfbLM(C1301Dp.m8905constructorimpl(expandedNavWidth), C1301Dp.m8905constructorimpl(0), $shrinkStage);
            if ($hasMedia) {
                $composer.startReplaceGroup(1452699406);
                ComposerKt.sourceInformation($composer, "383@15573L374");
                Modifier modifierM1877width3ABfNKs = SizeKt.m1877width3ABfNKs(OffsetKt.m1764offsetVpY3zN4(BoxWithConstraints.align(Modifier.INSTANCE, Alignment.INSTANCE.getBottomStart()), miniWrapperX, C1301Dp.m8905constructorimpl(C1301Dp.m8905constructorimpl(-C1301Dp.m8905constructorimpl(3)) - miniLift)), miniWrapperWidth);
                ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM1877width3ABfNKs);
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
                Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer);
                Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i3 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 1041897914, "C392@15917L12:MeloXApp.kt#5am3v9");
                $miniPlayer.invoke($composer, 0);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(1453089572);
                $composer.endReplaceGroup();
            }
            long jBottomLiquidGlassTint = bottomLiquidGlassTint($composer, 0);
            long jBottomGlassFallbackColor = bottomGlassFallbackColor($composer, 0);
            Modifier modifierM9632meloXLiquidBottomBar9z6LAg8 = MeloXBackdropComponentsKt.m9632meloXLiquidBottomBar9z6LAg8(SizeKt.m1858height3ABfNKs(SizeKt.m1877width3ABfNKs(OffsetKt.m1764offsetVpY3zN4(BoxWithConstraints.align(Modifier.INSTANCE, Alignment.INSTANCE.getBottomStart()), horizontalMargin, C1301Dp.m8905constructorimpl(-C1301Dp.m8905constructorimpl(3))), navWidth), $navHeight), navShape, jBottomLiquidGlassTint, Color.m6066copywmQWz5c(jBottomGlassFallbackColor, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jBottomGlassFallbackColor) : 0.16f, (14 & 2) != 0 ? Color.m6074getRedimpl(jBottomGlassFallbackColor) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jBottomGlassFallbackColor) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jBottomGlassFallbackColor) : 0.0f), $composer, 0);
            Float fValueOf = Float.valueOf($progress);
            ComposerKt.sourceInformationMarkerStart($composer, 1016723398, "CC(remember):MeloXApp.kt#9igjgp");
            boolean zChanged = $composer.changed($progress) | $composer.changed($onSelect) | $composer.changed($selectedTab.ordinal());
            Object objRememberedValue = $composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = (PointerInputEventHandler) new MeloXAppKt$MeloXBottomChrome$1$1$2$1($progress, $onSelect, primaryTabs, $selectedTab);
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            SurfaceKt.m3769SurfaceT9BRK9s(SuspendingPointerInputFilterKt.pointerInput(modifierM9632meloXLiquidBottomBar9z6LAg8, fValueOf, $selectedTab, (PointerInputEventHandler) objRememberedValue), navShape, Color.INSTANCE.m6103getTransparent0d7_KjU(), 0L, C1301Dp.m8905constructorimpl(0), $dynamicGlassEnabled ? m9605lerpDpMdfbLM(C1301Dp.m8905constructorimpl(2), C1301Dp.m8905constructorimpl(4), $progress) : C1301Dp.m8905constructorimpl(0), null, ComposableLambdaKt.rememberComposableLambda(1045197411, true, new Function2() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return MeloXAppKt.MeloXBottomChrome$lambda$1$0$2($expandedLayerAlpha, $progress, $compactLayerAlpha, primaryTabs, $selectedTab, $labelAlpha, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 14180736, 8);
            Modifier modifierM1872size3ABfNKs = SizeKt.m1872size3ABfNKs(OffsetKt.m1764offsetVpY3zN4(BoxWithConstraints.align(Modifier.INSTANCE, Alignment.INSTANCE.getBottomEnd()), C1301Dp.m8905constructorimpl(-horizontalMargin), C1301Dp.m8905constructorimpl(-C1301Dp.m8905constructorimpl(3))), $searchSize);
            RoundedCornerShape circleShape = RoundedCornerShapeKt.getCircleShape();
            long jBottomLiquidGlassTint2 = bottomLiquidGlassTint($composer, 0);
            float fM8905constructorimpl4 = C1301Dp.m8905constructorimpl(6);
            float fM8905constructorimpl5 = C1301Dp.m8905constructorimpl(12);
            float fM8905constructorimpl6 = C1301Dp.m8905constructorimpl(18);
            long jBottomGlassFallbackColor2 = bottomGlassFallbackColor($composer, 0);
            Modifier modifierM9633meloXLiquidButtonNsDo4u0 = MeloXBackdropComponentsKt.m9633meloXLiquidButtonNsDo4u0(modifierM1872size3ABfNKs, circleShape, false, jBottomLiquidGlassTint2, Color.m6066copywmQWz5c(jBottomGlassFallbackColor2, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jBottomGlassFallbackColor2) : 0.1f, (14 & 2) != 0 ? Color.m6074getRedimpl(jBottomGlassFallbackColor2) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jBottomGlassFallbackColor2) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jBottomGlassFallbackColor2) : 0.0f), fM8905constructorimpl4, fM8905constructorimpl5, fM8905constructorimpl6, $composer, 14352384, 2);
            ComposerKt.sourceInformationMarkerStart($composer, 1016883545, "CC(remember):MeloXApp.kt#9igjgp");
            boolean zChanged2 = $composer.changed($onSelect);
            Object objRememberedValue2 = $composer.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function0() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MeloXAppKt.MeloXBottomChrome$lambda$1$0$3$0($onSelect);
                    }
                };
                $composer.updateRememberedValue(obj2);
                objRememberedValue2 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            SurfaceKt.m3769SurfaceT9BRK9s(ClickableKt.m1078clickableoSLSa3U$default(modifierM9633meloXLiquidButtonNsDo4u0, false, null, null, null, (Function0) objRememberedValue2, 15, null), RoundedCornerShapeKt.getCircleShape(), Color.INSTANCE.m6103getTransparent0d7_KjU(), 0L, C1301Dp.m8905constructorimpl(0), $dynamicGlassEnabled ? m9605lerpDpMdfbLM(C1301Dp.m8905constructorimpl(2), C1301Dp.m8905constructorimpl(4), $progress) : C1301Dp.m8905constructorimpl(0), null, ComposableLambdaKt.rememberComposableLambda(-962456244, true, new Function2() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return MeloXAppKt.MeloXBottomChrome$lambda$1$0$4($sizeStage, $selectedTab, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), $composer, 14180736, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:28:0x01b5  */
    /* JADX WARN: Code duplicated, block: B:38:0x0272  */
    /* JADX WARN: Code duplicated, block: B:41:0x027e  */
    /* JADX WARN: Code duplicated, block: B:42:0x0284  */
    /* JADX WARN: Code duplicated, block: B:44:0x032f  */
    /* JADX WARN: Code duplicated, block: B:47:0x0355  */
    public static final Unit MeloXBottomChrome$lambda$1$0$2(final float $expandedLayerAlpha, float $progress, final float $compactLayerAlpha, final List $primaryTabs, final AppTab $selectedTab, final float $labelAlpha, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Composer composer;
        boolean zChanged;
        Object objRememberedValue;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function1;
        ComposerKt.sourceInformation($composer, "C428@17394L3501:MeloXApp.kt#5am3v9");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1045197411, $changed, -1, "com.lladlam.melox.ui.MeloXBottomChrome.<anonymous>.<anonymous>.<anonymous> (MeloXApp.kt:428)");
            }
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierFillMaxSize$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((6 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor2;
                $composer.createNode(function0);
            } else {
                function0 = constructor2;
                $composer.useNode();
            }
            Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer);
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -7897999, "C432@17594L30,434@17721L2512,429@17444L2789:MeloXApp.kt#5am3v9");
            Modifier modifierFillMaxSize$default2 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 276841287, "CC(remember):MeloXApp.kt#9igjgp");
            boolean zChanged2 = $composer.changed($expandedLayerAlpha);
            Object objRememberedValue2 = $composer.rememberedValue();
            if (!zChanged2) {
                composer = $composer;
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                BoxWithConstraintsKt.BoxWithConstraints(PaddingKt.m1806paddingVpY3zN4(GraphicsLayerModifierKt.graphicsLayer(modifierFillMaxSize$default2, (Function1) objRememberedValue2), C1301Dp.m8905constructorimpl(4), C1301Dp.m8905constructorimpl(4)), null, false, ComposableLambdaKt.rememberComposableLambda(882072895, true, new Function3() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda27
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return MeloXAppKt.MeloXBottomChrome$lambda$1$0$2$0$1($primaryTabs, $selectedTab, $labelAlpha, (BoxWithConstraintsScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, $composer, 54), $composer, 3072, 6);
                if ($progress > 0.5f) {
                    $composer.startReplaceGroup(-5175487);
                    ComposerKt.sourceInformation($composer, "488@20450L29,485@20303L552");
                    Modifier modifierFillMaxSize$default3 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer, 276932678, "CC(remember):MeloXApp.kt#9igjgp");
                    zChanged = $composer.changed($compactLayerAlpha);
                    objRememberedValue = $composer.rememberedValue();
                    if (!zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        Object obj = new Function1() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda28
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return MeloXAppKt.MeloXBottomChrome$lambda$1$0$2$0$2$0($compactLayerAlpha, (GraphicsLayerScope) obj2);
                            }
                        };
                        $composer.updateRememberedValue(obj);
                        objRememberedValue = obj;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(modifierFillMaxSize$default3, (Function1) objRememberedValue);
                    Alignment center = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierGraphicsLayer);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int i4 = ((((48 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function1 = constructor;
                        $composer.createNode(function1);
                    } else {
                        function1 = constructor;
                        $composer.useNode();
                    }
                    Composer composerM5188constructorimpl2 = Updater.m5188constructorimpl($composer);
                    Updater.m5196setimpl(composerM5188constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m5196setimpl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m5196setimpl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m5194reconcileimpl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m5196setimpl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i5 = (i4 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                    int i6 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 1085787190, "C491@20602L227:MeloXApp.kt#5am3v9");
                    m9604RootGlyphIconXOJAsU(rootGlyph($selectedTab), SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(25)), MeloXAccent, $composer, 432);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endReplaceGroup();
                } else {
                    $composer.startReplaceGroup(-4598887);
                    $composer.endReplaceGroup();
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composer = $composer;
            }
            objRememberedValue2 = new Function1() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda26
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return MeloXAppKt.MeloXBottomChrome$lambda$1$0$2$0$0$0($expandedLayerAlpha, (GraphicsLayerScope) obj2);
                }
            };
            $composer.updateRememberedValue(objRememberedValue2);
            ComposerKt.sourceInformationMarkerEnd($composer);
            BoxWithConstraintsKt.BoxWithConstraints(PaddingKt.m1806paddingVpY3zN4(GraphicsLayerModifierKt.graphicsLayer(modifierFillMaxSize$default2, (Function1) objRememberedValue2), C1301Dp.m8905constructorimpl(4), C1301Dp.m8905constructorimpl(4)), null, false, ComposableLambdaKt.rememberComposableLambda(882072895, true, new Function3() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda27
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return MeloXAppKt.MeloXBottomChrome$lambda$1$0$2$0$1($primaryTabs, $selectedTab, $labelAlpha, (BoxWithConstraintsScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), $composer, 3072, 6);
            if ($progress > 0.5f) {
                $composer.startReplaceGroup(-5175487);
                ComposerKt.sourceInformation($composer, "488@20450L29,485@20303L552");
                Modifier modifierFillMaxSize$default4 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, 276932678, "CC(remember):MeloXApp.kt#9igjgp");
                zChanged = $composer.changed($compactLayerAlpha);
                objRememberedValue = $composer.rememberedValue();
                if (!zChanged) {
                }
                Object obj2 = new Function1() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda28
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return MeloXAppKt.MeloXBottomChrome$lambda$1$0$2$0$2$0($compactLayerAlpha, (GraphicsLayerScope) obj3);
                    }
                };
                $composer.updateRememberedValue(obj2);
                objRememberedValue = obj2;
                ComposerKt.sourceInformationMarkerEnd($composer);
                Modifier modifierGraphicsLayer2 = GraphicsLayerModifierKt.graphicsLayer(modifierFillMaxSize$default4, (Function1) objRememberedValue);
                Alignment center2 = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifierGraphicsLayer2);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i7 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function1 = constructor;
                    $composer.createNode(function1);
                } else {
                    function1 = constructor;
                    $composer.useNode();
                }
                Composer composerM5188constructorimpl3 = Updater.m5188constructorimpl($composer);
                Updater.m5196setimpl(composerM5188constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m5196setimpl(composerM5188constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m5196setimpl(composerM5188constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m5194reconcileimpl(composerM5188constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m5196setimpl(composerM5188constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i8 = (i7 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                int i9 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 1085787190, "C491@20602L227:MeloXApp.kt#5am3v9");
                m9604RootGlyphIconXOJAsU(rootGlyph($selectedTab), SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(25)), MeloXAccent, $composer, 432);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-4598887);
                $composer.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXBottomChrome$lambda$1$0$2$0$0$0(float $expandedLayerAlpha, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setAlpha($expandedLayerAlpha);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXBottomChrome$lambda$1$0$2$0$1(List $primaryTabs, AppTab $selectedTab, float $labelAlpha, final BoxWithConstraintsScope BoxWithConstraints, Composer $composer, int $changed) {
        long jM6066copywmQWz5c;
        Function0<ComposeUiNode> function0;
        Intrinsics.checkNotNullParameter(BoxWithConstraints, "$this$BoxWithConstraints");
        ComposerKt.sourceInformation($composer, "C436@17864L447,445@18353L281,454@18861L181,461@19272L21,458@19076L482,450@18659L926,469@19611L600:MeloXApp.kt#5am3v9");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(BoxWithConstraints) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (!$composer.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            $composer.skipToGroupEnd();
        } else {
            int i = -1;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(882072895, $dirty2, -1, "com.lladlam.melox.ui.MeloXBottomChrome.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MeloXApp.kt:435)");
            }
            int i2 = 0;
            Iterator it = $primaryTabs.iterator();
            while (it.hasNext()) {
                if (((Pair) it.next()).getFirst() == $selectedTab) {
                    i = i2;
                    break;
                }
                i2++;
            }
            int selectedIndex = i;
            final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(RangesKt.coerceAtLeast(selectedIndex, 0), AnimationSpecKt.spring(0.78f, 360.0f, Float.valueOf(0.001f)), 0.0f, "melox-tab-selection-position", null, $composer, 3120, 20);
            final State<Float> stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(selectedIndex >= 0 ? 1.0f : 0.0f, AnimationSpecKt.spring$default(0.9f, 420.0f, null, 4, null), 0.0f, "melox-tab-selection-alpha", null, $composer, 3120, 20);
            Modifier modifierFillMaxHeight$default = SizeKt.fillMaxHeight$default(SizeKt.fillMaxWidth(Modifier.INSTANCE, 0.25f), 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1563531444, "CC(remember):MeloXApp.kt#9igjgp");
            boolean zChanged = $composer.changed(stateAnimateFloatAsState) | (($dirty2 & 14) == 4) | $composer.changed(stateAnimateFloatAsState2);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return MeloXAppKt.MeloXBottomChrome$lambda$1$0$2$0$1$3$0(BoxWithConstraints, stateAnimateFloatAsState, stateAnimateFloatAsState2, (GraphicsLayerScope) obj2);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(modifierFillMaxHeight$default, (Function1) objRememberedValue);
            RoundedCornerShape roundedCornerShapeM2135RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(24));
            if (DarkThemeKt.isSystemInDarkTheme($composer, 0)) {
                long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
                jM6066copywmQWz5c = Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU) : 0.18f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU) : 0.0f);
            } else {
                long jM6105getWhite0d7_KjU2 = Color.INSTANCE.m6105getWhite0d7_KjU();
                jM6066copywmQWz5c = Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU2, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU2) : 0.32f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU2) : 0.0f);
            }
            BoxKt.Box(MeloXBackdropComponentsKt.m9634meloXLiquidTabSelectionBx497Mc(modifierGraphicsLayer, roundedCornerShapeM2135RoundedCornerShape0680j_4, true, jM6066copywmQWz5c, $composer, RendererCapabilities.DECODER_SUPPORT_MASK), $composer, 0);
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierFillMaxSize$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i3 = ((((390 << 3) & 112) << 6) & 896) | 6;
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
            Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer);
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i4 = (i3 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i5 = ((390 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 461844234, "C:MeloXApp.kt#5am3v9");
            $composer.startReplaceGroup(14898573);
            ComposerKt.sourceInformation($composer, "*474@19881L274");
            List<Pair> list = $primaryTabs;
            for (Pair pair : list) {
                Iterable iterable = list;
                AppTab appTab = (AppTab) pair.component1();
                RootTabButton(rowScope, appTab, (RootGlyph) pair.component2(), $selectedTab == appTab, $labelAlpha, $composer, i5 & 14);
                list = iterable;
            }
            $composer.endReplaceGroup();
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

    private static final float MeloXBottomChrome$lambda$1$0$2$0$1$1(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float MeloXBottomChrome$lambda$1$0$2$0$1$2(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXBottomChrome$lambda$1$0$2$0$1$3$0(BoxWithConstraintsScope $this_BoxWithConstraints, State $lensPosition$delegate, State $lensAlpha$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setTranslationX((MeloXBottomChrome$lambda$1$0$2$0$1$1($lensPosition$delegate) * Constraints.m8858getMaxWidthimpl($this_BoxWithConstraints.mo1521getConstraintsmsEJaDk())) / 4.0f);
        graphicsLayer.setAlpha(MeloXBottomChrome$lambda$1$0$2$0$1$2($lensAlpha$delegate));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXBottomChrome$lambda$1$0$2$0$2$0(float $compactLayerAlpha, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setAlpha($compactLayerAlpha);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXBottomChrome$lambda$1$0$3$0(Function1 $onSelect) {
        $onSelect.invoke(AppTab.Search);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXBottomChrome$lambda$1$0$4(float $sizeStage, AppTab $selectedTab, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        long onSurface;
        ComposerKt.sourceInformation($composer, "C525@21924L482:MeloXApp.kt#5am3v9");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-962456244, $changed, -1, "com.lladlam.melox.ui.MeloXBottomChrome.<anonymous>.<anonymous>.<anonymous> (MeloXApp.kt:525)");
            }
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((48 << 3) & 112) << 6) & 896) | 6;
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
            Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer);
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1500761920, "C526@21987L401:MeloXApp.kt#5am3v9");
            RootGlyph rootGlyph = RootGlyph.Search;
            Modifier modifierM1872size3ABfNKs = SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, m9605lerpDpMdfbLM(C1301Dp.m8905constructorimpl(28), C1301Dp.m8905constructorimpl(27), $sizeStage));
            if ($selectedTab == AppTab.Search) {
                $composer.startReplaceGroup(1500963853);
                $composer.endReplaceGroup();
                onSurface = MeloXAccent;
            } else {
                $composer.startReplaceGroup(1501037013);
                ComposerKt.sourceInformation($composer, "532@22318L11");
                onSurface = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurface();
                $composer.endReplaceGroup();
            }
            m9604RootGlyphIconXOJAsU(rootGlyph, modifierM1872size3ABfNKs, onSurface, $composer, 6);
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

    private static final long bottomLiquidGlassTint(Composer $composer, int $changed) {
        long jM6066copywmQWz5c;
        ComposerKt.sourceInformationMarkerStart($composer, 1738610134, "C(bottomLiquidGlassTint)543@22505L21:MeloXApp.kt#5am3v9");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1738610134, $changed, -1, "com.lladlam.melox.ui.bottomLiquidGlassTint (MeloXApp.kt:543)");
        }
        if (DarkThemeKt.isSystemInDarkTheme($composer, 0)) {
            long jM6094getBlack0d7_KjU = Color.INSTANCE.m6094getBlack0d7_KjU();
            jM6066copywmQWz5c = Color.m6066copywmQWz5c(jM6094getBlack0d7_KjU, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6094getBlack0d7_KjU) : 0.1f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6094getBlack0d7_KjU) : 0.0f);
        } else {
            long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
            jM6066copywmQWz5c = Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU) : 0.12f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU) : 0.0f);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return jM6066copywmQWz5c;
    }

    private static final long bottomGlassFallbackColor(Composer $composer, int $changed) {
        long jM6066copywmQWz5c;
        ComposerKt.sourceInformationMarkerStart($composer, -141019398, "C(bottomGlassFallbackColor)551@22698L21:MeloXApp.kt#5am3v9");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-141019398, $changed, -1, "com.lladlam.melox.ui.bottomGlassFallbackColor (MeloXApp.kt:551)");
        }
        if (DarkThemeKt.isSystemInDarkTheme($composer, 0)) {
            $composer.startReplaceGroup(-630797757);
            ComposerKt.sourceInformation($composer, "552@22745L11");
            long surface = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getSurface();
            jM6066copywmQWz5c = Color.m6066copywmQWz5c(surface, (14 & 1) != 0 ? Color.m6070getAlphaimpl(surface) : 0.58f, (14 & 2) != 0 ? Color.m6074getRedimpl(surface) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(surface) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(surface) : 0.0f);
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(-630724039);
            $composer.endReplaceGroup();
            long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
            jM6066copywmQWz5c = Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU) : 0.56f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU) : 0.0f);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return jM6066copywmQWz5c;
    }

    private static final void RootTabButton(final RowScope $this$RootTabButton, final AppTab tab, final RootGlyph glyph, final boolean selected, final float labelAlpha, Composer $composer, final int $changed) {
        RowScope rowScope;
        long jM6066copywmQWz5c;
        Function0<ComposeUiNode> function0;
        Composer $composer2 = $composer.startRestartGroup(-744572004);
        ComposerKt.sourceInformation($composer2, "C(RootTabButton)N(tab,glyph,selected,labelAlpha)564@23004L268,570@23277L660:MeloXApp.kt#5am3v9");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            rowScope = $this$RootTabButton;
            $dirty |= $composer2.changed(rowScope) ? 4 : 2;
        } else {
            rowScope = $this$RootTabButton;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(tab.ordinal()) ? 32 : 16;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer2.changed(glyph.ordinal()) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(selected) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changed(labelAlpha) ? 16384 : 8192;
        }
        if ($composer2.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-744572004, $dirty, -1, "com.lladlam.melox.ui.RootTabButton (MeloXApp.kt:563)");
            }
            if (selected) {
                $composer2.startReplaceGroup(1725848871);
                $composer2.endReplaceGroup();
                jM6066copywmQWz5c = MeloXAccent;
            } else {
                $composer2.startReplaceGroup(1725850831);
                ComposerKt.sourceInformation($composer2, "566@23100L11");
                long onSurface = MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getOnSurface();
                jM6066copywmQWz5c = Color.m6066copywmQWz5c(onSurface, (14 & 1) != 0 ? Color.m6070getAlphaimpl(onSurface) : 0.78f, (14 & 2) != 0 ? Color.m6074getRedimpl(onSurface) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(onSurface) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(onSurface) : 0.0f);
                $composer2.endReplaceGroup();
            }
            State<Color> stateM913animateColorAsStateeuL9pac = SingleValueAnimationKt.m913animateColorAsStateeuL9pac(jM6066copywmQWz5c, AnimationSpecKt.spring$default(0.86f, 420.0f, null, 4, null), "melox-tab-foreground-" + tab.name(), null, $composer2, 48, 8);
            Modifier modifierM1806paddingVpY3zN4 = PaddingKt.m1806paddingVpY3zN4(SizeKt.fillMaxHeight$default(RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null), 0.0f, 1, null), C1301Dp.m8905constructorimpl(3), C1301Dp.m8905constructorimpl(3));
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            Arrangement.Vertical center = Arrangement.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            int $dirty2 = $dirty;
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierM1806paddingVpY3zN4);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((432 << 3) & 112) << 6) & 896) | 6;
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
            Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer2);
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1250886720, "C578@23550L81,581@23722L22,579@23640L291:MeloXApp.kt#5am3v9");
            m9604RootGlyphIconXOJAsU(glyph, SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(24)), RootTabButton$lambda$0(stateM913animateColorAsStateeuL9pac), $composer2, (($dirty2 >> 6) & 14) | 48);
            String title = tab.getTitle();
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, -455988036, "CC(remember):MeloXApp.kt#9igjgp");
            boolean z = ($dirty2 & 57344) == 16384;
            Object objRememberedValue = $composer2.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MeloXAppKt.RootTabButton$lambda$1$0$0(labelAlpha, (GraphicsLayerScope) obj);
                    }
                };
                $composer2.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(companion, (Function1) objRememberedValue);
            long sp = TextUnitKt.getSp(9);
            long sp2 = TextUnitKt.getSp(10);
            FontWeight.Companion companion2 = FontWeight.INSTANCE;
            TextKt.m3912TextNvy7gAk(title, modifierGraphicsLayer, RootTabButton$lambda$0(stateM913animateColorAsStateeuL9pac), null, sp, null, selected ? companion2.getSemiBold() : companion2.getMedium(), null, 0L, null, null, sp2, 0, false, 0, 0, null, null, $composer2, 24576, 48, 260008);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXAppKt.RootTabButton$lambda$2($this$RootTabButton, tab, glyph, selected, labelAlpha, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final long RootTabButton$lambda$0(State<Color> state) {
        return ((Color) state.getValue()).m6078unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RootTabButton$lambda$1$0$0(float $labelAlpha, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setAlpha($labelAlpha);
        return Unit.INSTANCE;
    }

    private static final RootGlyph rootGlyph(AppTab $this$rootGlyph) {
        switch (WhenMappings.$EnumSwitchMapping$0[$this$rootGlyph.ordinal()]) {
            case 1:
                return RootGlyph.Search;
            case 2:
                return RootGlyph.Home;
            case 3:
                return RootGlyph.Explore;
            case 4:
                return RootGlyph.Library;
            case 5:
                return RootGlyph.Settings;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: renamed from: RootGlyphIcon-XO-JAsU, reason: not valid java name */
    private static final void m9604RootGlyphIconXOJAsU(final RootGlyph glyph, final Modifier modifier, final long color, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-818493056);
        ComposerKt.sourceInformation($composer2, "C(RootGlyphIcon)N(glyph,modifier,color:c#ui.graphics.Color)606@24399L3593,606@24382L3610:MeloXApp.kt#5am3v9");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(glyph.ordinal()) ? 4 : 2;
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
                ComposerKt.traceEventStart(-818493056, $dirty, -1, "com.lladlam.melox.ui.RootGlyphIcon (MeloXApp.kt:605)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -592804599, "CC(remember):MeloXApp.kt#9igjgp");
            boolean z = (($dirty & 14) == 4) | (($dirty & 896) == 256);
            Object objRememberedValue = $composer2.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return MeloXAppKt.RootGlyphIcon_XO_JAsU$lambda$0$0(glyph, color, (DrawScope) obj2);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.MeloXAppKt$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return MeloXAppKt.RootGlyphIcon_XO_JAsU$lambda$1(glyph, modifier, color, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RootGlyphIcon_XO_JAsU$lambda$0$0(RootGlyph $glyph, long $color, DrawScope Canvas) {
        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
        float w = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32));
        float h = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L));
        float stroke = Size.m5891getMinDimensionimpl(Canvas.mo6642getSizeNHjbRc()) * 0.115f;
        switch (WhenMappings.$EnumSwitchMapping$1[$glyph.ordinal()]) {
            case 1:
                Path roof = AndroidPath_androidKt.Path();
                roof.moveTo(0.1f * w, h * 0.48f);
                roof.lineTo(0.5f * w, 0.14f * h);
                roof.lineTo(0.9f * w, 0.48f * h);
                DrawScope.m6632drawPathLG529CI$default(Canvas, roof, $color, 0.0f, new Stroke(stroke, 0.0f, StrokeCap.INSTANCE.m6443getRoundKaPHkGw(), 0, null, 26, null), null, 0, 52, null);
                float f = 0.43f * h;
                float f2 = 0.05f * w;
                DrawScope.m6638drawRoundRectuAw5IA$default(Canvas, $color, Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(0.24f * w)) << 32) | (((long) Float.floatToRawIntBits(h * 0.43f)) & 4294967295L)), Size.m5883constructorimpl((((long) Float.floatToRawIntBits(0.52f * w)) << 32) | (((long) Float.floatToRawIntBits(f)) & 4294967295L)), CornerRadius.m5777constructorimpl((((long) Float.floatToRawIntBits(f2)) << 32) | (((long) Float.floatToRawIntBits(f2)) & 4294967295L)), new Stroke(stroke, 0.0f, 0, 0, null, 30, null), 0.0f, null, 0, 224, null);
                break;
            case 2:
                DrawScope.m6623drawCircleVaOC9Bg$default(Canvas, $color, 0.35f * w, 0L, 0.0f, new Stroke(stroke, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                Path needle = AndroidPath_androidKt.Path();
                needle.moveTo(0.38f * w, h * 0.62f);
                needle.lineTo(0.57f * w, 0.36f * h);
                needle.lineTo(0.63f * w, 0.55f * h);
                needle.close();
                DrawScope.m6632drawPathLG529CI$default(Canvas, needle, $color, 0.0f, null, null, 0, 60, null);
                break;
            case 3:
                Path p = AndroidPath_androidKt.Path();
                p.moveTo(w * 0.26f, 0.22f * h);
                p.lineTo(w * 0.26f, 0.72f * h);
                p.cubicTo(0.26f * w, h * 0.83f, w * 0.1f, h * 0.84f, w * 0.1f, 0.7f * h);
                p.cubicTo(w * 0.1f, h * 0.57f, w * 0.29f, h * 0.55f, w * 0.37f, h * 0.62f);
                p.lineTo(w * 0.37f, 0.28f * h);
                p.lineTo(w * 0.83f, 0.18f * h);
                p.lineTo(w * 0.83f, h * 0.61f);
                p.cubicTo(w * 0.83f, h * 0.74f, w * 0.66f, 0.77f * h, w * 0.61f, h * 0.66f);
                DrawScope.m6632drawPathLG529CI$default(Canvas, p, $color, 0.0f, new Stroke(stroke, 0.0f, StrokeCap.INSTANCE.m6443getRoundKaPHkGw(), 0, null, 26, null), null, 0, 52, null);
                break;
            case 4:
                float stroke2 = stroke;
                DrawScope.m6623drawCircleVaOC9Bg$default(Canvas, $color, 0.33f * w, 0L, 0.0f, new Stroke(stroke, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                DrawScope.m6623drawCircleVaOC9Bg$default(Canvas, $color, w * 0.095f, 0L, 0.0f, null, null, 0, 124, null);
                int i = 0;
                while (i < 8) {
                    double radians = Math.toRadians((((double) i) * 45.0d) - 90.0d);
                    float f3 = w / 2.0f;
                    float f4 = h / 2.0f;
                    float f5 = w * 0.37f;
                    float f6 = w * 0.47f;
                    float fCos = f3 + (((float) Math.cos(radians)) * f5);
                    float fSin = f4 + (((float) Math.sin(radians)) * f5);
                    long jM5815constructorimpl = Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(fCos)) << 32) | (((long) Float.floatToRawIntBits(fSin)) & 4294967295L));
                    float fCos2 = f3 + (((float) Math.cos(radians)) * f6);
                    float fSin2 = f4 + (((float) Math.sin(radians)) * f6);
                    float stroke3 = stroke2;
                    DrawScope.m6628drawLineNGM6Ib0$default(Canvas, $color, jM5815constructorimpl, Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(fCos2)) << 32) | (((long) Float.floatToRawIntBits(fSin2)) & 4294967295L)), stroke3, StrokeCap.INSTANCE.m6443getRoundKaPHkGw(), null, 0.0f, null, 0, WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND, null);
                    i++;
                    stroke2 = stroke3;
                }
                break;
            case 5:
                DrawScope.m6623drawCircleVaOC9Bg$default(Canvas, $color, 0.29f * w, Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(0.43f * w)) << 32) | (((long) Float.floatToRawIntBits(0.4f * h)) & 4294967295L)), 0.0f, new Stroke(stroke, 0.0f, 0, 0, null, 30, null), null, 0, LocationRequestCompat.QUALITY_LOW_POWER, null);
                float f7 = 0.84f * h;
                DrawScope.m6628drawLineNGM6Ib0$default(Canvas, $color, Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(0.64f * w)) << 32) | (((long) Float.floatToRawIntBits(0.62f * h)) & 4294967295L)), Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(0.86f * w)) << 32) | (((long) Float.floatToRawIntBits(f7)) & 4294967295L)), stroke, StrokeCap.INSTANCE.m6443getRoundKaPHkGw(), null, 0.0f, null, 0, WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND, null);
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        return Unit.INSTANCE;
    }

    private static final float smoothStep(float value, float start, float end) {
        if (end <= start) {
            return value >= end ? 1.0f : 0.0f;
        }
        float t = RangesKt.coerceIn((value - start) / (end - start), 0.0f, 1.0f);
        return t * t * (3.0f - (2.0f * t));
    }

    /* JADX INFO: renamed from: lerpDp-Md-fbLM, reason: not valid java name */
    private static final float m9605lerpDpMdfbLM(float start, float end, float progress) {
        return C1301Dp.m8905constructorimpl(((end - start) * RangesKt.coerceIn(progress, 0.0f, 1.0f)) + start);
    }
}
