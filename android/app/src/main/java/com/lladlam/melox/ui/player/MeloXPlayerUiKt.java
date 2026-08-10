package com.lladlam.melox.ui.player;

import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.compose.animation.AnimatedContentKt;
import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.animation.AnimatedContentTransitionScope;
import androidx.compose.animation.ContentTransform;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.AspectRatioKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
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
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.SliderDefaults;
import androidx.compose.material3.SliderKt;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TextKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.BlurKt;
import androidx.compose.ui.draw.BlurredEdgeTreatment;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.draw.ScaleKt;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.C1301Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.session.MediaController;
import androidx.media3.session.SessionToken;
import coil3.compose.SingletonAsyncImageKt;
import com.google.common.util.concurrent.ListenableFuture;
import com.lladlam.melox.playback.MeloXPlaybackService;
import java.util.Arrays;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KFunction;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MeloXPlayerUi.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\u001a\r\u0010\u0000\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0002\u001a#\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007H\u0007¢\u0006\u0002\u0010\b\u001a#\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007H\u0007¢\u0006\u0002\u0010\b\u001a\u0017\u0010\u000b\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0003¢\u0006\u0002\u0010\u000e\u001a\u0015\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0002\u0010\u0010\u001a1\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00040\u0015H\u0003¢\u0006\u0002\u0010\u0016\u001a\u0015\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0002\u0010\u0010\u001a\u0015\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0002\u0010\u0010\u001a5\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007H\u0003¢\u0006\u0004\b \u0010!\u001a\u0015\u0010\"\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0002\u0010\u0010\u001a1\u0010#\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00040\u0015H\u0003¢\u0006\u0002\u0010\u0016\u001a5\u0010$\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010%\u001a\u00020\u001e2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007H\u0003¢\u0006\u0002\u0010&\u001a\u001f\u0010'\u001a\u00020\u00042\b\u0010(\u001a\u0004\u0018\u00010\r2\u0006\u0010)\u001a\u00020*H\u0001¢\u0006\u0002\u0010+\u001a\u0010\u0010,\u001a\u00020\r2\u0006\u0010-\u001a\u00020.H\u0002¨\u0006/²\u0006\n\u0010\u0012\u001a\u00020\u0013X\u008a\u008e\u0002²\u0006\n\u00100\u001a\u000201X\u008a\u0084\u0002"}, d2 = {"rememberMeloXPlaybackUiState", "Lcom/lladlam/melox/ui/player/MeloXPlaybackUiState;", "(Landroidx/compose/runtime/Composer;I)Lcom/lladlam/melox/ui/player/MeloXPlaybackUiState;", "MeloXMiniPlayer", "", "state", "onExpand", "Lkotlin/Function0;", "(Lcom/lladlam/melox/ui/player/MeloXPlaybackUiState;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "MeloXNowPlaying", "onDismiss", "MeloXNowPlayingBackground", "artworkUrl", "", "(Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "MeloXArtworkPage", "(Lcom/lladlam/melox/ui/player/MeloXPlaybackUiState;Landroidx/compose/runtime/Composer;I)V", "MeloXBottomControls", "page", "Lcom/lladlam/melox/ui/player/MeloXNowPlayingPage;", "onPageSelected", "Lkotlin/Function1;", "(Lcom/lladlam/melox/ui/player/MeloXPlaybackUiState;Lcom/lladlam/melox/ui/player/MeloXNowPlayingPage;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "MeloXProgressControl", "MeloXTransportControls", "MeloXTransportButton", "label", TtmlNode.ATTR_TTS_FONT_SIZE, "Landroidx/compose/ui/unit/TextUnit;", "enabled", "", "onClick", "MeloXTransportButton-5fiNW4Q", "(Ljava/lang/String;JZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "MeloXVolumeControl", "MeloXPageSelector", "MeloXPageButton", "selected", "(Ljava/lang/String;ZZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "Artwork", "url", "modifier", "Landroidx/compose/ui/Modifier;", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)V", "formatDuration", "milliseconds", "", "app", "artworkScale", ""}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class MeloXPlayerUiKt {

    /* JADX INFO: compiled from: MeloXPlayerUi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

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
        }
    }

    static final Unit Artwork$lambda$1(String str, Modifier modifier, int i, Composer composer, int i2) {
        Artwork(str, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXArtworkPage$lambda$2(MeloXPlaybackUiState meloXPlaybackUiState, int i, Composer composer, int i2) {
        MeloXArtworkPage(meloXPlaybackUiState, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXBottomControls$lambda$1(MeloXPlaybackUiState meloXPlaybackUiState, MeloXNowPlayingPage meloXNowPlayingPage, Function1 function1, int i, Composer composer, int i2) {
        MeloXBottomControls(meloXPlaybackUiState, meloXNowPlayingPage, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXMiniPlayer$lambda$0(MeloXPlaybackUiState meloXPlaybackUiState, Function0 function0, int i, Composer composer, int i2) {
        MeloXMiniPlayer(meloXPlaybackUiState, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXMiniPlayer$lambda$2(MeloXPlaybackUiState meloXPlaybackUiState, Function0 function0, int i, Composer composer, int i2) {
        MeloXMiniPlayer(meloXPlaybackUiState, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXNowPlaying$lambda$4(MeloXPlaybackUiState meloXPlaybackUiState, Function0 function0, int i, Composer composer, int i2) throws Throwable {
        MeloXNowPlaying(meloXPlaybackUiState, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXNowPlayingBackground$lambda$1(String str, int i, Composer composer, int i2) {
        MeloXNowPlayingBackground(str, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXPageButton$lambda$1(String str, boolean z, boolean z2, Function0 function0, int i, int i2, Composer composer, int i3) {
        MeloXPageButton(str, z, z2, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit MeloXPageSelector$lambda$1(MeloXPlaybackUiState meloXPlaybackUiState, MeloXNowPlayingPage meloXNowPlayingPage, Function1 function1, int i, Composer composer, int i2) {
        MeloXPageSelector(meloXPlaybackUiState, meloXNowPlayingPage, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXProgressControl$lambda$1(MeloXPlaybackUiState meloXPlaybackUiState, int i, Composer composer, int i2) {
        MeloXProgressControl(meloXPlaybackUiState, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXTransportButton_5fiNW4Q$lambda$1(String str, long j, boolean z, Function0 function0, int i, Composer composer, int i2) {
        m9700MeloXTransportButton5fiNW4Q(str, j, z, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXTransportControls$lambda$1(MeloXPlaybackUiState meloXPlaybackUiState, int i, Composer composer, int i2) {
        MeloXTransportControls(meloXPlaybackUiState, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXVolumeControl$lambda$1(MeloXPlaybackUiState meloXPlaybackUiState, int i, Composer composer, int i2) {
        MeloXVolumeControl(meloXPlaybackUiState, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final MeloXPlaybackUiState rememberMeloXPlaybackUiState(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1732326817, "C(rememberMeloXPlaybackUiState)239@8083L7,240@8126L35,242@8193L703,242@8167L729,268@8949L125,268@8902L172:MeloXPlayerUi.kt#qhu5z0");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1732326817, $changed, -1, "com.lladlam.melox.ui.player.rememberMeloXPlaybackUiState (MeloXPlayerUi.kt:238)");
        }
        ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localContext);
        ComposerKt.sourceInformationMarkerEnd($composer);
        final Context context = ((Context) objConsume).getApplicationContext();
        ComposerKt.sourceInformationMarkerStart($composer, -1681235420, "CC(remember):MeloXPlayerUi.kt#9igjgp");
        Object objRememberedValue = $composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object meloXPlaybackUiState = new MeloXPlaybackUiState();
            $composer.updateRememberedValue(meloXPlaybackUiState);
            objRememberedValue = meloXPlaybackUiState;
        }
        final MeloXPlaybackUiState state = (MeloXPlaybackUiState) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -1681232608, "CC(remember):MeloXPlayerUi.kt#9igjgp");
        boolean zChangedInstance = $composer.changedInstance(context);
        Object objRememberedValue2 = $composer.rememberedValue();
        if (zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            Object obj = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXPlayerUiKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return MeloXPlayerUiKt.rememberMeloXPlaybackUiState$lambda$1$0(context, state, (DisposableEffectScope) obj2);
                }
            };
            $composer.updateRememberedValue(obj);
            objRememberedValue2 = obj;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.DisposableEffect(context, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, $composer, 0);
        Boolean boolValueOf = Boolean.valueOf(state.isPlaying());
        String mediaId = state.getMediaId();
        ComposerKt.sourceInformationMarkerStart($composer, -1681208994, "CC(remember):MeloXPlayerUi.kt#9igjgp");
        Object objRememberedValue3 = $composer.rememberedValue();
        if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            Object obj2 = (Function2) new MeloXPlayerUiKt$rememberMeloXPlaybackUiState$2$1(state, null);
            $composer.updateRememberedValue(obj2);
            objRememberedValue3 = obj2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.LaunchedEffect(boolValueOf, mediaId, (Function2) objRememberedValue3, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return state;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult rememberMeloXPlaybackUiState$lambda$1$0(Context $context, final MeloXPlaybackUiState $state, final DisposableEffectScope DisposableEffect) {
        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
        SessionToken token = new SessionToken($context, new ComponentName($context, (Class<?>) MeloXPlaybackService.class));
        final ListenableFuture<MediaController> listenableFutureBuildAsync = new MediaController.Builder($context, token).buildAsync();
        Intrinsics.checkNotNullExpressionValue(listenableFutureBuildAsync, "buildAsync(...)");
        final Handler handler = new Handler(Looper.getMainLooper());
        final Ref.BooleanRef disposed = new Ref.BooleanRef();
        listenableFutureBuildAsync.addListener(new Runnable() { // from class: com.lladlam.melox.ui.player.MeloXPlayerUiKt$$ExternalSyntheticLambda18
            @Override // java.lang.Runnable
            public final void run() {
                MeloXPlayerUiKt.rememberMeloXPlaybackUiState$lambda$1$0$0(disposed, DisposableEffect, $state, listenableFutureBuildAsync);
            }
        }, new Executor() { // from class: com.lladlam.melox.ui.player.MeloXPlayerUiKt$$ExternalSyntheticLambda19
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                handler.post(runnable);
            }
        });
        return new DisposableEffectResult() { // from class: com.lladlam.melox.ui.player.MeloXPlayerUiKt$rememberMeloXPlaybackUiState$lambda$1$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                disposed.element = true;
                if (!listenableFutureBuildAsync.isDone()) {
                    listenableFutureBuildAsync.cancel(true);
                }
                $state.unbind$app();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void rememberMeloXPlaybackUiState$lambda$1$0$0(Ref.BooleanRef $disposed, DisposableEffectScope $this_DisposableEffect, MeloXPlaybackUiState $state, ListenableFuture $future) {
        Object objM9714constructorimpl;
        if (!$disposed.element) {
            try {
                Result.Companion companion = Result.INSTANCE;
                objM9714constructorimpl = Result.constructor-impl((MediaController) $future.get());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM9714constructorimpl = Result.constructor-impl(ResultKt.createFailure(th));
            }
            if (Result.m9721isSuccessimpl(objM9714constructorimpl)) {
                $state.bind$app((MediaController) objM9714constructorimpl);
            }
        }
    }

    public static final void MeloXMiniPlayer(final MeloXPlaybackUiState state, final Function0<Unit> onExpand, Composer $composer, final int $changed) {
        Composer $composer2;
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(onExpand, "onExpand");
        Composer $composer3 = $composer.startRestartGroup(209147990);
        ComposerKt.sourceInformation($composer3, "C(MeloXMiniPlayer)N(state,onExpand)291@9469L11,294@9579L1595,285@9229L1945:MeloXPlayerUi.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(state) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(onExpand) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if (!$composer3.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(209147990, $dirty2, -1, "com.lladlam.melox.ui.player.MeloXMiniPlayer (MeloXPlayerUi.kt:282)");
            }
            if (state.getHasMedia()) {
                Modifier modifierM1078clickableoSLSa3U$default = ClickableKt.m1078clickableoSLSa3U$default(PaddingKt.m1806paddingVpY3zN4(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1301Dp.m8905constructorimpl(14), C1301Dp.m8905constructorimpl(4)), false, null, null, null, onExpand, 15, null);
                RoundedCornerShape roundedCornerShapeM2135RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(22));
                long surface = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getSurface();
                $composer2 = $composer3;
                SurfaceKt.m3769SurfaceT9BRK9s(modifierM1078clickableoSLSa3U$default, roundedCornerShapeM2135RoundedCornerShape0680j_4, Color.m6066copywmQWz5c(surface, (14 & 1) != 0 ? Color.m6070getAlphaimpl(surface) : 0.94f, (14 & 2) != 0 ? Color.m6074getRedimpl(surface) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(surface) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(surface) : 0.0f), 0L, C1301Dp.m8905constructorimpl(2), C1301Dp.m8905constructorimpl(5), null, ComposableLambdaKt.rememberComposableLambda(-1024293391, true, new Function2() { // from class: com.lladlam.melox.ui.player.MeloXPlayerUiKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return MeloXPlayerUiKt.MeloXMiniPlayer$lambda$1(state, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer3, 54), $composer2, 12804096, 72);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer3.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXPlayerUiKt$$ExternalSyntheticLambda11
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return MeloXPlayerUiKt.MeloXMiniPlayer$lambda$0(state, onExpand, $changed, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                    return;
                }
                return;
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup2 = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup2 != null) {
            scopeUpdateScopeEndRestartGroup2.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXPlayerUiKt$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXPlayerUiKt.MeloXMiniPlayer$lambda$2(state, onExpand, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit MeloXMiniPlayer$lambda$1(final MeloXPlaybackUiState $state, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function2;
        ComposerKt.sourceInformation($composer, "C295@9589L1579:MeloXPlayerUi.kt#qhu5z0");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1024293391, $changed, -1, "com.lladlam.melox.ui.player.MeloXMiniPlayer.<anonymous> (MeloXPlayerUi.kt:295)");
            }
            Modifier modifierM1805padding3ABfNKs = PaddingKt.m1805padding3ABfNKs(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(8));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.Horizontal horizontalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(C1301Dp.m8905constructorimpl(11));
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalM1497spacedBy0680j_4, centerVertically, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM1805padding3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((438 << 3) & 112) << 6) & 896) | 6;
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
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i3 = ((438 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 1184279742, "C300@9790L185,307@9989L678,328@10825L27,324@10681L477:MeloXPlayerUi.kt#qhu5z0");
            Artwork($state.getArtworkUrl(), ClipKt.clip(SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(52)), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(15))), $composer, 0);
            Modifier modifierWeight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierWeight$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((0 << 3) & 112) << 6) & 896) | 6;
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
            Composer composerM5188constructorimpl2 = Updater.m5188constructorimpl($composer);
            Updater.m5196setimpl(composerM5188constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i6 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -516482291, "C312@10241L10,308@10046L289,319@10529L11,320@10614L10,315@10352L301:MeloXPlayerUi.kt#qhu5z0");
            String title = $state.getTitle();
            if (StringsKt.isBlank(title)) {
                title = "正在播放";
            }
            TextKt.m3912TextNvy7gAk(title, null, 0L, null, 0L, null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, 0L, TextOverflow.INSTANCE.m8816getEllipsisgIe3tQ8(), false, 1, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleSmall(), $composer, 1572864, 24960, 110526);
            String artist = $state.getArtist();
            int iM8816getEllipsisgIe3tQ8 = TextOverflow.INSTANCE.m8816getEllipsisgIe3tQ8();
            long onSurface = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurface();
            TextKt.m3912TextNvy7gAk(artist, null, Color.m6066copywmQWz5c(onSurface, (14 & 1) != 0 ? Color.m6070getAlphaimpl(onSurface) : 0.58f, (14 & 2) != 0 ? Color.m6074getRedimpl(onSurface) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(onSurface) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(onSurface) : 0.0f), null, 0L, null, null, null, 0L, null, null, 0L, iM8816getEllipsisgIe3tQ8, false, 1, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 0, 24960, 110586);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierClip = ClipKt.clip(SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(44)), RoundedCornerShapeKt.getCircleShape());
            ComposerKt.sourceInformationMarkerStart($composer, 869518344, "CC(remember):MeloXPlayerUi.kt#9igjgp");
            boolean zChanged = $composer.changed($state);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.lladlam.melox.ui.player.MeloXPlayerUiKt$$ExternalSyntheticLambda20
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MeloXPlayerUiKt.MeloXMiniPlayer$lambda$1$0$1$0($state);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierM1078clickableoSLSa3U$default = ClickableKt.m1078clickableoSLSa3U$default(modifierClip, false, null, null, null, (Function0) objRememberedValue, 15, null);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifierM1078clickableoSLSa3U$default);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i7 = ((((48 << 3) & 112) << 6) & 896) | 6;
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
            Composer composerM5188constructorimpl3 = Updater.m5188constructorimpl($composer);
            Updater.m5196setimpl(composerM5188constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i8 = (i7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i9 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1507352081, "C331@10939L205:MeloXPlayerUi.kt#qhu5z0");
            TextKt.m3912TextNvy7gAk($state.isPlaying() ? "Ⅱ" : "▶", null, 0L, null, TextUnitKt.getSp($state.isPlaying() ? 22 : 20), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1572864, 0, 262062);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
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
    public static final Unit MeloXMiniPlayer$lambda$1$0$1$0(MeloXPlaybackUiState $state) {
        $state.togglePlayPause();
        return Unit.INSTANCE;
    }

    public static final void MeloXNowPlaying(final MeloXPlaybackUiState state, Function0<Unit> function0, Composer $composer, final int $changed) throws Throwable {
        Composer $composer2;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function2;
        Function0<ComposeUiNode> function3;
        final Function0<Unit> onDismiss = function0;
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(onDismiss, "onDismiss");
        Composer $composer3 = $composer.startRestartGroup(1009406646);
        ComposerKt.sourceInformation($composer3, "C(MeloXNowPlaying)N(state,onDismiss)346@11291L56,348@11353L2176:MeloXPlayerUi.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(state) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(onDismiss) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if (!$composer3.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1009406646, $dirty2, -1, "com.lladlam.melox.ui.player.MeloXNowPlaying (MeloXPlayerUi.kt:345)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -622818802, "CC(remember):MeloXPlayerUi.kt#9igjgp");
            Object objRememberedValue = $composer3.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(MeloXNowPlayingPage.Artwork, null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default);
                objRememberedValue = objMutableStateOf$default;
            }
            final MutableState page$delegate = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierM1043backgroundbw27NRU$default = BackgroundKt.m1043backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), Color.INSTANCE.m6094getBlack0d7_KjU(), null, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1043backgroundbw27NRU$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((6 << 3) & 112) << 6) & 896) | 6;
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
            Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer3);
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -1881242241, "C353@11467L43,355@11520L2003:MeloXPlayerUi.kt#qhu5z0");
            MeloXNowPlayingBackground(state.getArtworkUrl(), $composer3, 0);
            Modifier modifierM1807paddingVpY3zN4$default = PaddingKt.m1807paddingVpY3zN4$default(WindowInsetsPadding_androidKt.statusBarsPadding(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null)), C1301Dp.m8905constructorimpl(32), 0.0f, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer3, modifierM1807paddingVpY3zN4$default);
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
            Composer composerM5188constructorimpl2 = Updater.m5188constructorimpl($composer3);
            Updater.m5196setimpl(composerM5188constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            int i6 = ((0 >> 6) & 112) | 6;
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -936175590, "C361@11698L547,379@12345L91,386@12607L536,377@12259L884,403@13271L227,400@13157L356:MeloXPlayerUi.kt#qhu5z0");
            onDismiss = function0;
            $composer2 = $composer3;
            Modifier modifierM1078clickableoSLSa3U$default = ClickableKt.m1078clickableoSLSa3U$default(SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1301Dp.m8905constructorimpl(30)), false, null, null, null, onDismiss, 15, null);
            Alignment topCenter = Alignment.INSTANCE.getTopCenter();
            ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(topCenter, false);
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap3 = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer3, modifierM1078clickableoSLSa3U$default);
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
            Composer composerM5188constructorimpl3 = Updater.m5188constructorimpl($composer3);
            Updater.m5196setimpl(composerM5188constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i8 = (i7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i9 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -694006925, "C368@11951L280:MeloXPlayerUi.kt#qhu5z0");
            Modifier modifierClip = ClipKt.clip(SizeKt.m1874sizeVpY3zN4(PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, C1301Dp.m8905constructorimpl(8), 0.0f, 0.0f, 13, null), C1301Dp.m8905constructorimpl(60), C1301Dp.m8905constructorimpl(5)), RoundedCornerShapeKt.getCircleShape());
            long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
            BoxKt.Box(BackgroundKt.m1043backgroundbw27NRU$default(modifierClip, Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU) : 0.52f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU) : 0.0f), null, 2, null), $composer3, 0);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            MeloXNowPlayingPage meloXNowPlayingPageMeloXNowPlaying$lambda$1 = MeloXNowPlaying$lambda$1(page$delegate);
            Modifier modifierWeight$default = ColumnScope.weight$default(columnScope, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 1770935085, "CC(remember):MeloXPlayerUi.kt#9igjgp");
            Object objRememberedValue2 = $composer3.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXPlayerUiKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return MeloXPlayerUiKt.MeloXNowPlaying$lambda$3$0$1$0((AnimatedContentTransitionScope) obj2);
                    }
                };
                $composer3.updateRememberedValue(obj);
                objRememberedValue2 = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            AnimatedContentKt.AnimatedContent(meloXNowPlayingPageMeloXNowPlaying$lambda$1, modifierWeight$default, (Function1) objRememberedValue2, null, "melox-now-playing-page", null, ComposableLambdaKt.rememberComposableLambda(1903761201, true, new Function4() { // from class: com.lladlam.melox.ui.player.MeloXPlayerUiKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function4
                public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                    return MeloXPlayerUiKt.MeloXNowPlaying$lambda$3$0$2(state, (AnimatedContentScope) obj2, (MeloXNowPlayingPage) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                }
            }, $composer3, 54), $composer3, 1597824, 40);
            MeloXNowPlayingPage meloXNowPlayingPageMeloXNowPlaying$lambda$2 = MeloXNowPlaying$lambda$1(page$delegate);
            ComposerKt.sourceInformationMarkerStart($composer3, 1770964853, "CC(remember):MeloXPlayerUi.kt#9igjgp");
            Object objRememberedValue3 = $composer3.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXPlayerUiKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return MeloXPlayerUiKt.MeloXNowPlaying$lambda$3$0$3$0(page$delegate, (MeloXNowPlayingPage) obj3);
                    }
                };
                $composer3.updateRememberedValue(obj2);
                objRememberedValue3 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            MeloXBottomControls(state, meloXNowPlayingPageMeloXNowPlaying$lambda$2, (Function1) objRememberedValue3, $composer3, ($dirty2 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXPlayerUiKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return MeloXPlayerUiKt.MeloXNowPlaying$lambda$4(state, onDismiss, $changed, (Composer) obj3, ((Integer) obj4).intValue());
                }
            });
        }
    }

    private static final MeloXNowPlayingPage MeloXNowPlaying$lambda$1(MutableState<MeloXNowPlayingPage> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ContentTransform MeloXNowPlaying$lambda$3$0$1$0(AnimatedContentTransitionScope AnimatedContent) {
        Intrinsics.checkNotNullParameter(AnimatedContent, "$this$AnimatedContent");
        return AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(220, 0, null, 6, null), 0.0f, 2, null), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(180, 0, null, 6, null), 0.0f, 2, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXNowPlaying$lambda$3$0$2(MeloXPlaybackUiState $state, AnimatedContentScope AnimatedContent, MeloXNowPlayingPage selectedPage, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(AnimatedContent, "$this$AnimatedContent");
        Intrinsics.checkNotNullParameter(selectedPage, "selectedPage");
        ComposerKt.sourceInformation($composer, "CN(selectedPage):MeloXPlayerUi.kt#qhu5z0");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1903761201, $changed, -1, "com.lladlam.melox.ui.player.MeloXNowPlaying.<anonymous>.<anonymous>.<anonymous> (MeloXPlayerUi.kt:387)");
        }
        switch (WhenMappings.$EnumSwitchMapping$0[selectedPage.ordinal()]) {
            case 1:
                $composer.startReplaceGroup(-141550488);
                ComposerKt.sourceInformation($composer, "388@12714L23");
                MeloXArtworkPage($state, $composer, 0);
                $composer.endReplaceGroup();
                break;
            case 2:
                $composer.startReplaceGroup(-141548006);
                ComposerKt.sourceInformation($composer, "389@12788L137");
                MeloXLyricsPanelKt.MeloXLyricsPanel($state, SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), $composer, 48, 0);
                $composer.endReplaceGroup();
                break;
            case 3:
                $composer.startReplaceGroup(-141542023);
                ComposerKt.sourceInformation($composer, "393@12975L136");
                MeloXQueuePanelKt.MeloXQueuePanel($state, SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), $composer, 48, 0);
                $composer.endReplaceGroup();
                break;
            default:
                $composer.startReplaceGroup(-141552359);
                $composer.endReplaceGroup();
                throw new NoWhenBranchMatchedException();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXNowPlaying$lambda$3$0$3$0(MutableState $page$delegate, MeloXNowPlayingPage destination) {
        MeloXNowPlayingPage meloXNowPlayingPage;
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (MeloXNowPlaying$lambda$1($page$delegate) == destination) {
            meloXNowPlayingPage = MeloXNowPlayingPage.Artwork;
        } else {
            meloXNowPlayingPage = destination;
        }
        $page$delegate.setValue(meloXNowPlayingPage);
        return Unit.INSTANCE;
    }

    private static final void MeloXNowPlayingBackground(String artworkUrl, Composer $composer, final int $changed) {
        Function0<ComposeUiNode> function0;
        Composer composer;
        Object obj;
        char c;
        char c2;
        final String str = artworkUrl;
        Composer $composer2 = $composer.startRestartGroup(-1754450332);
        ComposerKt.sourceInformation($composer2, "C(MeloXNowPlayingBackground)N(artworkUrl)417@13610L1123:MeloXPlayerUi.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(str) ? 4 : 2;
        }
        if (!$composer2.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
            $composer2 = $composer2;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1754450332, $dirty, -1, "com.lladlam.melox.ui.player.MeloXNowPlayingBackground (MeloXPlayerUi.kt:416)");
            }
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierFillMaxSize$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((6 << 3) & 112) << 6) & 896) | 6;
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
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 998502978, "C433@14136L139,438@14284L443:MeloXPlayerUi.kt#qhu5z0");
            String str2 = artworkUrl;
            if (!(str2 == null || StringsKt.isBlank(str2))) {
                $composer2.startReplaceGroup(998529203);
                ComposerKt.sourceInformation($composer2, "419@13695L421");
                composer = $composer2;
                obj = null;
                c = 2;
                c2 = 0;
                str = artworkUrl;
                SingletonAsyncImageKt.m9399AsyncImage10Xjiaw(str, null, BlurKt.m5590blurF8QBwvs(ScaleKt.scale(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), 1.35f), C1301Dp.m8905constructorimpl(46), BlurredEdgeTreatment.INSTANCE.m5600getUnboundedGoahg()), null, null, null, ContentScale.INSTANCE.getCrop(), 0.0f, null, 0, false, $composer2, ($dirty & 14) | 1572912, 0, 1976);
                $composer2.endReplaceGroup();
            } else {
                str = artworkUrl;
                composer = $composer2;
                obj = null;
                c = 2;
                c2 = 0;
                $composer2.startReplaceGroup(998943704);
                $composer2.endReplaceGroup();
            }
            Modifier modifierFillMaxSize$default2 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, obj);
            long jM6094getBlack0d7_KjU = Color.INSTANCE.m6094getBlack0d7_KjU();
            BoxKt.Box(BackgroundKt.m1043backgroundbw27NRU$default(modifierFillMaxSize$default2, Color.m6066copywmQWz5c(jM6094getBlack0d7_KjU, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6094getBlack0d7_KjU) : 0.12f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6094getBlack0d7_KjU) : 0.0f), null, 2, null), $composer2, 6);
            Modifier modifierFillMaxSize$default3 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, obj);
            Brush.Companion companion = Brush.INSTANCE;
            Color[] colorArr = new Color[3];
            long jM6094getBlack0d7_KjU2 = Color.INSTANCE.m6094getBlack0d7_KjU();
            colorArr[c2] = Color.m6058boximpl(Color.m6066copywmQWz5c(jM6094getBlack0d7_KjU2, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6094getBlack0d7_KjU2) : 0.04f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6094getBlack0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6094getBlack0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6094getBlack0d7_KjU2) : 0.0f));
            long jM6094getBlack0d7_KjU3 = Color.INSTANCE.m6094getBlack0d7_KjU();
            colorArr[1] = Color.m6058boximpl(Color.m6066copywmQWz5c(jM6094getBlack0d7_KjU3, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6094getBlack0d7_KjU3) : 0.14f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6094getBlack0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6094getBlack0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6094getBlack0d7_KjU3) : 0.0f));
            long jM6094getBlack0d7_KjU4 = Color.INSTANCE.m6094getBlack0d7_KjU();
            colorArr[c] = Color.m6058boximpl(Color.m6066copywmQWz5c(jM6094getBlack0d7_KjU4, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6094getBlack0d7_KjU4) : 0.5f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6094getBlack0d7_KjU4) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6094getBlack0d7_KjU4) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6094getBlack0d7_KjU4) : 0.0f));
            BoxKt.Box(BackgroundKt.background$default(modifierFillMaxSize$default3, Brush.Companion.m6023verticalGradient8A3gB4$default(companion, CollectionsKt.listOf((Object[]) colorArr), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null), $composer2, 6);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            r6.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXPlayerUiKt$$ExternalSyntheticLambda22
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return MeloXPlayerUiKt.MeloXNowPlayingBackground$lambda$1(str, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    private static final void MeloXArtworkPage(final MeloXPlaybackUiState state, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Composer $composer3 = $composer.startRestartGroup(-1878269137);
        ComposerKt.sourceInformation($composer3, "C(MeloXArtworkPage)N(state)456@14833L195,462@15034L1525:MeloXPlayerUi.kt#qhu5z0");
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
                ComposerKt.traceEventStart(-1878269137, $dirty2, -1, "com.lladlam.melox.ui.player.MeloXArtworkPage (MeloXPlayerUi.kt:455)");
            }
            State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(state.isPlaying() ? 1.0f : 0.74f, AnimationSpecKt.spring$default(0.78f, 210.0f, null, 4, null), 0.0f, "melox-artwork-scale", null, $composer3, 3120, 20);
            Modifier modifierM1809paddingqDBjuR0$default = PaddingKt.m1809paddingqDBjuR0$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, C1301Dp.m8905constructorimpl(8), 7, null);
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1809paddingqDBjuR0$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((390 << 3) & 112) << 6) & 896) | 6;
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
            Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer3);
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            int i3 = ((390 >> 6) & 112) | 6;
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -762829109, "C468@15210L30,470@15250L574,486@15834L30,488@15874L639,507@16523L30:MeloXPlayerUi.kt#qhu5z0");
            SpacerKt.Spacer(ColumnScope.weight$default(columnScope, Modifier.INSTANCE, 0.34f, false, 2, null), $composer3, 0);
            String artworkUrl = state.getArtworkUrl();
            $composer2 = $composer3;
            Modifier modifierScale = ScaleKt.scale(AspectRatioKt.aspectRatio$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 1.0f, false, 2, null), MeloXArtworkPage$lambda$0(stateAnimateFloatAsState));
            float fM8905constructorimpl = state.isPlaying() ? C1301Dp.m8905constructorimpl(26) : C1301Dp.m8905constructorimpl(14);
            RoundedCornerShape roundedCornerShapeM2135RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(12));
            long jM6094getBlack0d7_KjU = Color.INSTANCE.m6094getBlack0d7_KjU();
            long jM6066copywmQWz5c = Color.m6066copywmQWz5c(jM6094getBlack0d7_KjU, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6094getBlack0d7_KjU) : 0.34f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6094getBlack0d7_KjU) : 0.0f);
            long jM6094getBlack0d7_KjU2 = Color.INSTANCE.m6094getBlack0d7_KjU();
            Artwork(artworkUrl, ClipKt.clip(ShadowKt.m5665shadows4CzXII(modifierScale, fM8905constructorimpl, roundedCornerShapeM2135RoundedCornerShape0680j_4, false, jM6066copywmQWz5c, Color.m6066copywmQWz5c(jM6094getBlack0d7_KjU2, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6094getBlack0d7_KjU2) : 0.34f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6094getBlack0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6094getBlack0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6094getBlack0d7_KjU2) : 0.0f)), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(12))), $composer3, 0);
            SpacerKt.Spacer(ColumnScope.weight$default(columnScope, Modifier.INSTANCE, 0.18f, false, 2, null), $composer3, 0);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer3, modifierFillMaxWidth$default);
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
            Composer composerM5188constructorimpl2 = Updater.m5188constructorimpl($composer3);
            Updater.m5196setimpl(composerM5188constructorimpl2, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i6 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 10366180, "C489@15931L275,497@16219L284:MeloXPlayerUi.kt#qhu5z0");
            String title = state.getTitle();
            if (StringsKt.isBlank(title)) {
                title = "正在播放";
            }
            TextKt.m3912TextNvy7gAk(title, null, Color.INSTANCE.m6105getWhite0d7_KjU(), null, TextUnitKt.getSp(20), null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, 0L, TextOverflow.INSTANCE.m8816getEllipsisgIe3tQ8(), false, 1, 0, null, null, $composer3, 1597824, 24960, 241578);
            String artist = state.getArtist();
            long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
            TextKt.m3912TextNvy7gAk(artist, PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, C1301Dp.m8905constructorimpl(2), 0.0f, 0.0f, 13, null), Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU) : 0.64f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU) : 0.0f), null, TextUnitKt.getSp(19), null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m8816getEllipsisgIe3tQ8(), false, 1, 0, null, null, $composer3, 25008, 24960, 241640);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            SpacerKt.Spacer(SizeKt.m1858height3ABfNKs(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(14)), $composer3, 6);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXPlayerUiKt$$ExternalSyntheticLambda23
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXPlayerUiKt.MeloXArtworkPage$lambda$2(state, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float MeloXArtworkPage$lambda$0(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final void MeloXBottomControls(final MeloXPlaybackUiState state, final MeloXNowPlayingPage page, final Function1<? super MeloXNowPlayingPage, Unit> function1, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        Composer $composer3 = $composer.startRestartGroup(1815752454);
        ComposerKt.sourceInformation($composer3, "C(MeloXBottomControls)N(state,page,onPageSelected)517@16731L464:MeloXPlayerUi.kt#qhu5z0");
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
                ComposerKt.traceEventStart(1815752454, $dirty, -1, "com.lladlam.melox.ui.player.MeloXBottomControls (MeloXPlayerUi.kt:516)");
            }
            Modifier modifierM1858height3ABfNKs = SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1301Dp.m8905constructorimpl(279));
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
            ComposerKt.sourceInformationMarkerStart($composer3, 1885368330, "C522@16840L27,523@16876L30,524@16915L29,525@16953L30,526@16992L25,527@17026L29,528@17064L125:MeloXPlayerUi.kt#qhu5z0");
            MeloXProgressControl(state, $composer3, $dirty & 14);
            SpacerKt.Spacer(SizeKt.m1858height3ABfNKs(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(19)), $composer3, 6);
            MeloXTransportControls(state, $composer3, $dirty & 14);
            SpacerKt.Spacer(SizeKt.m1858height3ABfNKs(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(31)), $composer3, 6);
            MeloXVolumeControl(state, $composer3, $dirty & 14);
            SpacerKt.Spacer(SizeKt.m1858height3ABfNKs(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(3)), $composer3, 6);
            MeloXPageSelector(state, page, function1, $composer3, ($dirty & 14) | ($dirty & 112) | ($dirty & 896));
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXPlayerUiKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXPlayerUiKt.MeloXBottomControls$lambda$1(state, page, function1, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:43:0x02c4  */
    /* JADX WARN: Code duplicated, block: B:46:0x02d0  */
    /* JADX WARN: Code duplicated, block: B:47:0x02d6  */
    /* JADX WARN: Code duplicated, block: B:50:0x0476  */
    /* JADX WARN: Code duplicated, block: B:53:0x0482  */
    /* JADX WARN: Code duplicated, block: B:54:0x0488  */
    /* JADX WARN: Code duplicated, block: B:57:0x060d  */
    private static final void MeloXProgressControl(final MeloXPlaybackUiState state, Composer $composer, final int $changed) {
        Composer $composer2;
        float fCoerceIn;
        Function0<ComposeUiNode> function0;
        Composer composer;
        Composer composer2;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> constructor2;
        Function0<ComposeUiNode> function2;
        Composer $composer3 = $composer.startRestartGroup(1739864700);
        ComposerKt.sourceInformation($composer3, "C(MeloXProgressControl)N(state)544@17441L1893:MeloXPlayerUi.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(state) ? 4 : 2;
        }
        if (!$composer3.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1739864700, $dirty, -1, "com.lladlam.melox.ui.player.MeloXProgressControl (MeloXPlayerUi.kt:537)");
            }
            if (state.getDurationMs() > 0) {
                fCoerceIn = RangesKt.coerceIn(state.getPositionMs() / state.getDurationMs(), 0.0f, 1.0f);
            } else {
                fCoerceIn = 0.0f;
            }
            float progress = fCoerceIn;
            Modifier modifierM1858height3ABfNKs = SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1301Dp.m8905constructorimpl(52));
            Arrangement.Vertical center = Arrangement.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, Alignment.INSTANCE.getStart(), $composer3, ((54 >> 3) & 14) | ((54 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1858height3ABfNKs);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((54 << 3) & 112) << 6) & 896) | 6;
            $composer2 = $composer3;
            int $dirty2 = $dirty;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor3;
                $composer3.createNode(function0);
            } else {
                function0 = constructor3;
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
            int i3 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 1726559260, "C552@17665L162,560@17961L187,550@17599L610,568@18219L1109:MeloXPlayerUi.kt#qhu5z0");
            ComposerKt.sourceInformationMarkerStart($composer3, -914135320, "CC(remember):MeloXPlayerUi.kt#9igjgp");
            boolean z = ($dirty2 & 14) == 4;
            Object objRememberedValue = $composer3.rememberedValue();
            if (!z) {
                composer = $composer3;
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                }
                Function1 function3 = (Function1) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composer);
                Modifier modifierM1858height3ABfNKs2 = SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1301Dp.m8905constructorimpl(20));
                SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                long jM6103getTransparent0d7_KjU = Color.INSTANCE.m6103getTransparent0d7_KjU();
                long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
                long jM6105getWhite0d7_KjU2 = Color.INSTANCE.m6105getWhite0d7_KjU();
                SliderKt.Slider(progress, function3, modifierM1858height3ABfNKs2, false, null, sliderDefaults.m3723colorsq0g_0yA(jM6103getTransparent0d7_KjU, jM6105getWhite0d7_KjU, 0L, Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU2, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU2) : 0.22f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU2) : 0.0f), 0L, 0L, 0L, 0L, 0L, 0L, composer, 3126, 6, 1012), null, 0, ComposableSingletons$MeloXPlayerUiKt.INSTANCE.getLambda$1065021834$app(), null, null, composer, 100663680, 0, 1752);
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                composer2 = composer;
                ComposerKt.sourceInformationMarkerStart(composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, composer2, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
                CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer2, modifierFillMaxWidth$default);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i4 = ((((390 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                if (!(composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    function1 = constructor;
                    composer2.createNode(function1);
                } else {
                    function1 = constructor;
                    composer2.useNode();
                }
                Composer composerM5188constructorimpl2 = Updater.m5188constructorimpl(composer2);
                Updater.m5196setimpl(composerM5188constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m5196setimpl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m5196setimpl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m5194reconcileimpl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m5196setimpl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i5 = (i4 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                int i6 = ((390 >> 6) & 112) | 6;
                RowScope rowScope = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer2, -982584289, "C572@18356L167,577@18536L27,578@18576L477,591@19066L27,592@19106L212:MeloXPlayerUi.kt#qhu5z0");
                String duration = formatDuration(state.getPositionMs());
                long jM6105getWhite0d7_KjU3 = Color.INSTANCE.m6105getWhite0d7_KjU();
                TextKt.m3912TextNvy7gAk(duration, null, Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU3, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU3) : 0.5f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU3) : 0.0f), null, TextUnitKt.getSp(11), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 24960, 0, 262122);
                SpacerKt.Spacer(RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null), composer2, 0);
                Modifier modifierClip = ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(7)));
                long jM6105getWhite0d7_KjU4 = Color.INSTANCE.m6105getWhite0d7_KjU();
                Modifier modifierM1806paddingVpY3zN4 = PaddingKt.m1806paddingVpY3zN4(BackgroundKt.m1043backgroundbw27NRU$default(modifierClip, Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU4, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU4) : 0.12f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU4) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU4) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU4) : 0.0f), null, 2, null), C1301Dp.m8905constructorimpl(9), C1301Dp.m8905constructorimpl(4));
                ComposerKt.sourceInformationMarkerStart(composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
                CompositionLocalMap currentCompositionLocalMap3 = composer2.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer2, modifierM1806paddingVpY3zN4);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i7 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                if (!(composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    function2 = constructor2;
                    composer2.createNode(function2);
                } else {
                    function2 = constructor2;
                    composer2.useNode();
                }
                Composer composerM5188constructorimpl3 = Updater.m5188constructorimpl(composer2);
                Updater.m5196setimpl(composerM5188constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m5196setimpl(composerM5188constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m5196setimpl(composerM5188constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m5194reconcileimpl(composerM5188constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m5196setimpl(composerM5188constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i8 = (i7 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i9 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer2, -1423598024, "C584@18832L207:MeloXPlayerUi.kt#qhu5z0");
                long jM6105getWhite0d7_KjU5 = Color.INSTANCE.m6105getWhite0d7_KjU();
                TextKt.m3912TextNvy7gAk("标准", null, Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU5, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU5) : 0.86f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU5) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU5) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU5) : 0.0f), null, TextUnitKt.getSp(11), null, FontWeight.INSTANCE.getMedium(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 1597830, 0, 262058);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                SpacerKt.Spacer(RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null), composer2, 0);
                String str = "−" + formatDuration(RangesKt.coerceAtLeast(state.getDurationMs() - state.getPositionMs(), 0L));
                long jM6105getWhite0d7_KjU6 = Color.INSTANCE.m6105getWhite0d7_KjU();
                TextKt.m3912TextNvy7gAk(str, null, Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU6, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU6) : 0.5f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU6) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU6) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU6) : 0.0f), null, TextUnitKt.getSp(11), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 24960, 0, 262122);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composer = $composer3;
            }
            objRememberedValue = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXPlayerUiKt$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MeloXPlayerUiKt.MeloXProgressControl$lambda$0$0$0(state, ((Float) obj).floatValue());
                }
            };
            $composer3.updateRememberedValue(objRememberedValue);
            Function1 function4 = (Function1) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composer);
            Modifier modifierM1858height3ABfNKs3 = SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1301Dp.m8905constructorimpl(20));
            SliderDefaults sliderDefaults2 = SliderDefaults.INSTANCE;
            long jM6103getTransparent0d7_KjU2 = Color.INSTANCE.m6103getTransparent0d7_KjU();
            long jM6105getWhite0d7_KjU7 = Color.INSTANCE.m6105getWhite0d7_KjU();
            long jM6105getWhite0d7_KjU8 = Color.INSTANCE.m6105getWhite0d7_KjU();
            SliderKt.Slider(progress, function4, modifierM1858height3ABfNKs3, false, null, sliderDefaults2.m3723colorsq0g_0yA(jM6103getTransparent0d7_KjU2, jM6105getWhite0d7_KjU7, 0L, Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU8, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU8) : 0.22f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU8) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU8) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU8) : 0.0f), 0L, 0L, 0L, 0L, 0L, 0L, composer, 3126, 6, 1012), null, 0, ComposableSingletons$MeloXPlayerUiKt.INSTANCE.getLambda$1065021834$app(), null, null, composer, 100663680, 0, 1752);
            Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
            composer2 = composer;
            ComposerKt.sourceInformationMarkerStart(composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, composer2, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
            CompositionLocalMap currentCompositionLocalMap4 = composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composer2, modifierFillMaxWidth$default2);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i10 = ((((390 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!(composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                function1 = constructor;
                composer2.createNode(function1);
            } else {
                function1 = constructor;
                composer2.useNode();
            }
            Composer composerM5188constructorimpl4 = Updater.m5188constructorimpl(composer2);
            Updater.m5196setimpl(composerM5188constructorimpl4, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            int i11 = (i10 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i12 = ((390 >> 6) & 112) | 6;
            RowScope rowScope2 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer2, -982584289, "C572@18356L167,577@18536L27,578@18576L477,591@19066L27,592@19106L212:MeloXPlayerUi.kt#qhu5z0");
            String duration2 = formatDuration(state.getPositionMs());
            long jM6105getWhite0d7_KjU9 = Color.INSTANCE.m6105getWhite0d7_KjU();
            TextKt.m3912TextNvy7gAk(duration2, null, Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU9, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU9) : 0.5f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU9) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU9) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU9) : 0.0f), null, TextUnitKt.getSp(11), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 24960, 0, 262122);
            SpacerKt.Spacer(RowScope.weight$default(rowScope2, Modifier.INSTANCE, 1.0f, false, 2, null), composer2, 0);
            Modifier modifierClip2 = ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(7)));
            long jM6105getWhite0d7_KjU10 = Color.INSTANCE.m6105getWhite0d7_KjU();
            Modifier modifierM1806paddingVpY3zN5 = PaddingKt.m1806paddingVpY3zN4(BackgroundKt.m1043backgroundbw27NRU$default(modifierClip2, Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU10, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU10) : 0.12f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU10) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU10) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU10) : 0.0f), null, 2, null), C1301Dp.m8905constructorimpl(9), C1301Dp.m8905constructorimpl(4));
            ComposerKt.sourceInformationMarkerStart(composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
            CompositionLocalMap currentCompositionLocalMap5 = composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composer2, modifierM1806paddingVpY3zN5);
            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i13 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!(composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                function2 = constructor2;
                composer2.createNode(function2);
            } else {
                function2 = constructor2;
                composer2.useNode();
            }
            Composer composerM5188constructorimpl5 = Updater.m5188constructorimpl(composer2);
            Updater.m5196setimpl(composerM5188constructorimpl5, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl5, Integer.valueOf(iHashCode5), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl5, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
            int i14 = (i13 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i15 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer2, -1423598024, "C584@18832L207:MeloXPlayerUi.kt#qhu5z0");
            long jM6105getWhite0d7_KjU11 = Color.INSTANCE.m6105getWhite0d7_KjU();
            TextKt.m3912TextNvy7gAk("标准", null, Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU11, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU11) : 0.86f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU11) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU11) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU11) : 0.0f), null, TextUnitKt.getSp(11), null, FontWeight.INSTANCE.getMedium(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 1597830, 0, 262058);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            SpacerKt.Spacer(RowScope.weight$default(rowScope2, Modifier.INSTANCE, 1.0f, false, 2, null), composer2, 0);
            String str2 = "−" + formatDuration(RangesKt.coerceAtLeast(state.getDurationMs() - state.getPositionMs(), 0L));
            long jM6105getWhite0d7_KjU12 = Color.INSTANCE.m6105getWhite0d7_KjU();
            TextKt.m3912TextNvy7gAk(str2, null, Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU12, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU12) : 0.5f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU12) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU12) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU12) : 0.0f), null, TextUnitKt.getSp(11), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 24960, 0, 262122);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXPlayerUiKt$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXPlayerUiKt.MeloXProgressControl$lambda$1(state, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXProgressControl$lambda$0$0$0(MeloXPlaybackUiState $state, float value) {
        if ($state.getDurationMs() > 0) {
            $state.seekTo(MathKt.roundToLong($state.getDurationMs() * value));
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:35:0x0186  */
    /* JADX WARN: Code duplicated, block: B:36:0x0188  */
    /* JADX WARN: Code duplicated, block: B:47:0x01cd  */
    /* JADX WARN: Code duplicated, block: B:48:0x01d0  */
    /* JADX WARN: Code duplicated, block: B:51:0x01da  */
    /* JADX WARN: Code duplicated, block: B:52:0x01dd  */
    /* JADX WARN: Code duplicated, block: B:55:0x01ef  */
    /* JADX WARN: Code duplicated, block: B:56:0x01f1  */
    /* JADX WARN: Code duplicated, block: B:59:0x01ff  */
    /* JADX WARN: Code duplicated, block: B:63:0x020b  */
    /* JADX WARN: Code duplicated, block: B:67:0x023b  */
    /* JADX WARN: Code duplicated, block: B:71:0x0246  */
    /* JADX WARN: Code duplicated, block: B:74:0x0253  */
    /* JADX WARN: Code duplicated, block: B:75:0x0256  */
    /* JADX WARN: Code duplicated, block: B:78:0x0262  */
    /* JADX WARN: Code duplicated, block: B:82:0x026e A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:86:0x02af  */
    private static final void MeloXTransportControls(final MeloXPlaybackUiState state, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        int i;
        boolean z;
        boolean z2;
        boolean z3;
        MeloXPlayerUiKt$MeloXTransportControls$1$1$1 meloXPlayerUiKt$MeloXTransportControls$1$1$1RememberedValue;
        String str;
        int i2;
        boolean z4;
        boolean z5;
        MeloXPlayerUiKt$MeloXTransportControls$1$2$1 meloXPlayerUiKt$MeloXTransportControls$1$2$1RememberedValue;
        Composer composer;
        boolean z6;
        boolean z7;
        Object objRememberedValue;
        Composer $composer3 = $composer.startRestartGroup(1871625931);
        ComposerKt.sourceInformation($composer3, "C(MeloXTransportControls)N(state)603@19420L882:MeloXPlayerUi.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(state) ? 4 : 2;
        }
        if (!$composer3.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1871625931, $dirty, -1, "com.lladlam.melox.ui.player.MeloXTransportControls (MeloXPlayerUi.kt:602)");
            }
            Modifier modifierM1858height3ABfNKs = SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1301Dp.m8905constructorimpl(82));
            Arrangement.Horizontal spaceAround = Arrangement.INSTANCE.getSpaceAround();
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceAround, centerVertically, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1858height3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            $composer2 = $composer3;
            int i3 = ((((438 << 3) & 112) << 6) & 896) | 6;
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
            Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer3);
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i4 = (i3 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i5 = ((438 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 372452906, "C614@19825L15,610@19638L213,620@20049L22,616@19860L222,626@20274L11,622@20091L205:MeloXPlayerUi.kt#qhu5z0");
            long sp = TextUnitKt.getSp(30);
            if (!state.getHasPrevious()) {
                i = 30;
                if (state.getRepeatMode() != 2) {
                    z = false;
                }
                ComposerKt.sourceInformationMarkerStart($composer3, -819264042, "CC(remember):MeloXPlayerUi.kt#9igjgp");
                if (($dirty2 & 14) == 4) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                z3 = z2;
                meloXPlayerUiKt$MeloXTransportControls$1$1$1RememberedValue = $composer3.rememberedValue();
                if (!z3 || meloXPlayerUiKt$MeloXTransportControls$1$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                    meloXPlayerUiKt$MeloXTransportControls$1$1$1RememberedValue = new MeloXPlayerUiKt$MeloXTransportControls$1$1$1(state);
                    $composer3.updateRememberedValue(meloXPlayerUiKt$MeloXTransportControls$1$1$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                m9700MeloXTransportButton5fiNW4Q("◀◀", sp, z, (Function0) ((KFunction) meloXPlayerUiKt$MeloXTransportControls$1$1$1RememberedValue), $composer3, 54);
                if (state.isPlaying()) {
                    str = "Ⅱ";
                } else {
                    str = "▶";
                }
                String str2 = str;
                if (state.isPlaying()) {
                    i2 = 44;
                } else {
                    i2 = 40;
                }
                long sp2 = TextUnitKt.getSp(i2);
                ComposerKt.sourceInformationMarkerStart($composer3, -819256867, "CC(remember):MeloXPlayerUi.kt#9igjgp");
                if (($dirty2 & 14) == 4) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                z5 = z4;
                meloXPlayerUiKt$MeloXTransportControls$1$2$1RememberedValue = $composer3.rememberedValue();
                if (!z5) {
                    composer = $composer3;
                    if (meloXPlayerUiKt$MeloXTransportControls$1$2$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    Composer composer2 = composer;
                    m9700MeloXTransportButton5fiNW4Q(str2, sp2, true, (Function0) ((KFunction) meloXPlayerUiKt$MeloXTransportControls$1$2$1RememberedValue), composer2, RendererCapabilities.DECODER_SUPPORT_MASK);
                    long sp3 = TextUnitKt.getSp(i);
                    if (!state.getHasNext() || state.getRepeatMode() == 2) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    ComposerKt.sourceInformationMarkerStart(composer2, -819249678, "CC(remember):MeloXPlayerUi.kt#9igjgp");
                    if (($dirty2 & 14) == 4) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    objRememberedValue = composer2.rememberedValue();
                    if (!z7 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        Object obj = (KFunction) new MeloXPlayerUiKt$MeloXTransportControls$1$3$1(state);
                        composer2.updateRememberedValue(obj);
                        objRememberedValue = obj;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    m9700MeloXTransportButton5fiNW4Q("▶▶", sp3, z6, (Function0) ((KFunction) objRememberedValue), composer2, 54);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composer = $composer3;
                }
                meloXPlayerUiKt$MeloXTransportControls$1$2$1RememberedValue = new MeloXPlayerUiKt$MeloXTransportControls$1$2$1(state);
                $composer3.updateRememberedValue(meloXPlayerUiKt$MeloXTransportControls$1$2$1RememberedValue);
                ComposerKt.sourceInformationMarkerEnd(composer);
                Composer composer3 = composer;
                m9700MeloXTransportButton5fiNW4Q(str2, sp2, true, (Function0) ((KFunction) meloXPlayerUiKt$MeloXTransportControls$1$2$1RememberedValue), composer3, RendererCapabilities.DECODER_SUPPORT_MASK);
                long sp4 = TextUnitKt.getSp(i);
                if (state.getHasNext()) {
                    z6 = true;
                } else {
                    z6 = true;
                }
                ComposerKt.sourceInformationMarkerStart(composer3, -819249678, "CC(remember):MeloXPlayerUi.kt#9igjgp");
                if (($dirty2 & 14) == 4) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                objRememberedValue = composer3.rememberedValue();
                if (!z7) {
                }
                Object obj2 = (KFunction) new MeloXPlayerUiKt$MeloXTransportControls$1$3$1(state);
                composer3.updateRememberedValue(obj2);
                objRememberedValue = obj2;
                ComposerKt.sourceInformationMarkerEnd(composer3);
                m9700MeloXTransportButton5fiNW4Q("▶▶", sp4, z6, (Function0) ((KFunction) objRememberedValue), composer3, 54);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                i = 30;
            }
            z = true;
            ComposerKt.sourceInformationMarkerStart($composer3, -819264042, "CC(remember):MeloXPlayerUi.kt#9igjgp");
            if (($dirty2 & 14) == 4) {
                z2 = true;
            } else {
                z2 = false;
            }
            z3 = z2;
            meloXPlayerUiKt$MeloXTransportControls$1$1$1RememberedValue = $composer3.rememberedValue();
            if (!z3) {
            }
            meloXPlayerUiKt$MeloXTransportControls$1$1$1RememberedValue = new MeloXPlayerUiKt$MeloXTransportControls$1$1$1(state);
            $composer3.updateRememberedValue(meloXPlayerUiKt$MeloXTransportControls$1$1$1RememberedValue);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            m9700MeloXTransportButton5fiNW4Q("◀◀", sp, z, (Function0) ((KFunction) meloXPlayerUiKt$MeloXTransportControls$1$1$1RememberedValue), $composer3, 54);
            if (state.isPlaying()) {
                str = "Ⅱ";
            } else {
                str = "▶";
            }
            String str3 = str;
            if (state.isPlaying()) {
                i2 = 44;
            } else {
                i2 = 40;
            }
            long sp5 = TextUnitKt.getSp(i2);
            ComposerKt.sourceInformationMarkerStart($composer3, -819256867, "CC(remember):MeloXPlayerUi.kt#9igjgp");
            if (($dirty2 & 14) == 4) {
                z4 = true;
            } else {
                z4 = false;
            }
            z5 = z4;
            meloXPlayerUiKt$MeloXTransportControls$1$2$1RememberedValue = $composer3.rememberedValue();
            if (!z5) {
                composer = $composer3;
                if (meloXPlayerUiKt$MeloXTransportControls$1$2$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                Composer composer4 = composer;
                m9700MeloXTransportButton5fiNW4Q(str3, sp5, true, (Function0) ((KFunction) meloXPlayerUiKt$MeloXTransportControls$1$2$1RememberedValue), composer4, RendererCapabilities.DECODER_SUPPORT_MASK);
                long sp6 = TextUnitKt.getSp(i);
                if (state.getHasNext()) {
                    z6 = true;
                } else {
                    z6 = true;
                }
                ComposerKt.sourceInformationMarkerStart(composer4, -819249678, "CC(remember):MeloXPlayerUi.kt#9igjgp");
                if (($dirty2 & 14) == 4) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                objRememberedValue = composer4.rememberedValue();
                if (!z7) {
                }
                Object obj3 = (KFunction) new MeloXPlayerUiKt$MeloXTransportControls$1$3$1(state);
                composer4.updateRememberedValue(obj3);
                objRememberedValue = obj3;
                ComposerKt.sourceInformationMarkerEnd(composer4);
                m9700MeloXTransportButton5fiNW4Q("▶▶", sp6, z6, (Function0) ((KFunction) objRememberedValue), composer4, 54);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composer = $composer3;
            }
            meloXPlayerUiKt$MeloXTransportControls$1$2$1RememberedValue = new MeloXPlayerUiKt$MeloXTransportControls$1$2$1(state);
            $composer3.updateRememberedValue(meloXPlayerUiKt$MeloXTransportControls$1$2$1RememberedValue);
            ComposerKt.sourceInformationMarkerEnd(composer);
            Composer composer5 = composer;
            m9700MeloXTransportButton5fiNW4Q(str3, sp5, true, (Function0) ((KFunction) meloXPlayerUiKt$MeloXTransportControls$1$2$1RememberedValue), composer5, RendererCapabilities.DECODER_SUPPORT_MASK);
            long sp7 = TextUnitKt.getSp(i);
            if (state.getHasNext()) {
                z6 = true;
            } else {
                z6 = true;
            }
            ComposerKt.sourceInformationMarkerStart(composer5, -819249678, "CC(remember):MeloXPlayerUi.kt#9igjgp");
            if (($dirty2 & 14) == 4) {
                z7 = true;
            } else {
                z7 = false;
            }
            objRememberedValue = composer5.rememberedValue();
            if (!z7) {
            }
            Object obj4 = (KFunction) new MeloXPlayerUiKt$MeloXTransportControls$1$3$1(state);
            composer5.updateRememberedValue(obj4);
            objRememberedValue = obj4;
            ComposerKt.sourceInformationMarkerEnd(composer5);
            m9700MeloXTransportButton5fiNW4Q("▶▶", sp7, z6, (Function0) ((KFunction) objRememberedValue), composer5, 54);
            ComposerKt.sourceInformationMarkerEnd(composer5);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXPlayerUiKt$$ExternalSyntheticLambda21
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj5, Object obj6) {
                    return MeloXPlayerUiKt.MeloXTransportControls$lambda$1(state, $changed, (Composer) obj5, ((Integer) obj6).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: MeloXTransportButton-5fiNW4Q, reason: not valid java name */
    private static final void m9700MeloXTransportButton5fiNW4Q(final String label, final long fontSize, final boolean enabled, final Function0<Unit> function0, Composer $composer, final int $changed) {
        String str;
        Function0<Unit> function1;
        Function0<ComposeUiNode> function2;
        Composer $composer2 = $composer.startRestartGroup(-1851009488);
        ComposerKt.sourceInformation($composer2, "C(MeloXTransportButton)N(label,fontSize:c#ui.unit.TextUnit,enabled,onClick)638@20475L410:MeloXPlayerUi.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            str = label;
            $dirty |= $composer2.changed(str) ? 4 : 2;
        } else {
            str = label;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(fontSize) ? 32 : 16;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer2.changed(enabled) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            function1 = function0;
            $dirty |= $composer2.changedInstance(function1) ? 2048 : 1024;
        } else {
            function1 = function0;
        }
        int $dirty2 = $dirty;
        if ($composer2.shouldExecute(($dirty2 & 1171) != 1170, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1851009488, $dirty2, -1, "com.lladlam.melox.ui.player.MeloXTransportButton (MeloXPlayerUi.kt:637)");
            }
            Modifier modifierM1078clickableoSLSa3U$default = ClickableKt.m1078clickableoSLSa3U$default(ClipKt.clip(SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(64)), RoundedCornerShapeKt.getCircleShape()), enabled, null, null, null, function1, 14, null);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierM1078clickableoSLSa3U$default);
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
            Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer2);
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 235265037, "C645@20687L192:MeloXPlayerUi.kt#qhu5z0");
            long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
            TextKt.m3912TextNvy7gAk(str, null, Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU) : enabled ? 1.0f : 0.28f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU) : 0.0f), null, fontSize, null, FontWeight.INSTANCE.getMedium(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, ($dirty2 & 14) | 1572864 | (($dirty2 << 9) & 57344), 0, 262058);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXPlayerUiKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXPlayerUiKt.MeloXTransportButton_5fiNW4Q$lambda$1(label, fontSize, enabled, function0, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:39:0x02e5  */
    private static final void MeloXVolumeControl(final MeloXPlaybackUiState state, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        Composer composer;
        Composer $composer3 = $composer.startRestartGroup(-672603345);
        ComposerKt.sourceInformation($composer3, "C(MeloXVolumeControl)N(state)656@20967L815:MeloXPlayerUi.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(state) ? 4 : 2;
        }
        if (!$composer3.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-672603345, $dirty, -1, "com.lladlam.melox.ui.player.MeloXVolumeControl (MeloXPlayerUi.kt:655)");
            }
            Modifier modifierM1858height3ABfNKs = SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1301Dp.m8905constructorimpl(42));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.Horizontal horizontalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(C1301Dp.m8905constructorimpl(10));
            ComposerKt.sourceInformationMarkerStart($composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalM1497spacedBy0680j_4, centerVertically, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1858height3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            $composer2 = $composer3;
            int i = ((((438 << 3) & 112) << 6) & 896) | 6;
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
            Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer3);
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i3 = ((438 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 50514093, "C663@21189L69,666@21337L19,670@21486L201,664@21267L431,676@21707L69:MeloXPlayerUi.kt#qhu5z0");
            long sp = TextUnitKt.getSp(12);
            long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
            TextKt.m3912TextNvy7gAk("🔈", null, Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU) : 0.62f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU) : 0.0f), null, sp, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 24966, 0, 262122);
            float volume = state.getVolume();
            ComposerKt.sourceInformationMarkerStart($composer3, -829650338, "CC(remember):MeloXPlayerUi.kt#9igjgp");
            boolean z = ($dirty2 & 14) == 4;
            MeloXPlayerUiKt$MeloXVolumeControl$1$1$1 meloXPlayerUiKt$MeloXVolumeControl$1$1$1RememberedValue = $composer3.rememberedValue();
            if (!z) {
                composer = $composer3;
                if (meloXPlayerUiKt$MeloXVolumeControl$1$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                Modifier modifierM1858height3ABfNKs2 = SizeKt.m1858height3ABfNKs(RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null), C1301Dp.m8905constructorimpl(28));
                SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                long jM6105getWhite0d7_KjU2 = Color.INSTANCE.m6105getWhite0d7_KjU();
                long jM6105getWhite0d7_KjU3 = Color.INSTANCE.m6105getWhite0d7_KjU();
                long jM6066copywmQWz5c = Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU3, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU3) : 0.78f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU3) : 0.0f);
                long jM6105getWhite0d7_KjU4 = Color.INSTANCE.m6105getWhite0d7_KjU();
                Composer composer2 = composer;
                SliderKt.Slider(volume, (Function1) ((KFunction) meloXPlayerUiKt$MeloXVolumeControl$1$1$1RememberedValue), modifierM1858height3ABfNKs2, false, null, 0, null, sliderDefaults.m3723colorsq0g_0yA(jM6105getWhite0d7_KjU2, jM6066copywmQWz5c, 0L, Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU4, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU4) : 0.2f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU4) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU4) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU4) : 0.0f), 0L, 0L, 0L, 0L, 0L, 0L, composer2, 3126, 6, 1012), null, composer2, 0, 376);
                long sp2 = TextUnitKt.getSp(14);
                long jM6105getWhite0d7_KjU5 = Color.INSTANCE.m6105getWhite0d7_KjU();
                TextKt.m3912TextNvy7gAk("🔊", null, Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU5, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU5) : 0.62f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU5) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU5) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU5) : 0.0f), null, sp2, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 24966, 0, 262122);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composer = $composer3;
            }
            meloXPlayerUiKt$MeloXVolumeControl$1$1$1RememberedValue = new MeloXPlayerUiKt$MeloXVolumeControl$1$1$1(state);
            $composer3.updateRememberedValue(meloXPlayerUiKt$MeloXVolumeControl$1$1$1RememberedValue);
            ComposerKt.sourceInformationMarkerEnd(composer);
            Modifier modifierM1858height3ABfNKs3 = SizeKt.m1858height3ABfNKs(RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null), C1301Dp.m8905constructorimpl(28));
            SliderDefaults sliderDefaults2 = SliderDefaults.INSTANCE;
            long jM6105getWhite0d7_KjU6 = Color.INSTANCE.m6105getWhite0d7_KjU();
            long jM6105getWhite0d7_KjU7 = Color.INSTANCE.m6105getWhite0d7_KjU();
            long jM6066copywmQWz5c2 = Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU7, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU7) : 0.78f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU7) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU7) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU7) : 0.0f);
            long jM6105getWhite0d7_KjU8 = Color.INSTANCE.m6105getWhite0d7_KjU();
            Composer composer3 = composer;
            SliderKt.Slider(volume, (Function1) ((KFunction) meloXPlayerUiKt$MeloXVolumeControl$1$1$1RememberedValue), modifierM1858height3ABfNKs3, false, null, 0, null, sliderDefaults2.m3723colorsq0g_0yA(jM6105getWhite0d7_KjU6, jM6066copywmQWz5c2, 0L, Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU8, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU8) : 0.2f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU8) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU8) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU8) : 0.0f), 0L, 0L, 0L, 0L, 0L, 0L, composer3, 3126, 6, 1012), null, composer3, 0, 376);
            long sp3 = TextUnitKt.getSp(14);
            long jM6105getWhite0d7_KjU9 = Color.INSTANCE.m6105getWhite0d7_KjU();
            TextKt.m3912TextNvy7gAk("🔊", null, Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU9, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU9) : 0.62f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU9) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU9) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU9) : 0.0f), null, sp3, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer3, 24966, 0, 262122);
            ComposerKt.sourceInformationMarkerEnd(composer3);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXPlayerUiKt$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXPlayerUiKt.MeloXVolumeControl$lambda$1(state, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void MeloXPageSelector(final MeloXPlaybackUiState state, final MeloXNowPlayingPage page, final Function1<? super MeloXNowPlayingPage, Unit> function1, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function2;
        Function0<ComposeUiNode> function3;
        String str;
        Composer $composer3 = $composer.startRestartGroup(-173461101);
        ComposerKt.sourceInformation($composer3, "C(MeloXPageSelector)N(state,page,onPageSelected)686@21952L1765:MeloXPlayerUi.kt#qhu5z0");
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
                ComposerKt.traceEventStart(-173461101, $dirty, -1, "com.lladlam.melox.ui.player.MeloXPageSelector (MeloXPlayerUi.kt:685)");
            }
            Modifier modifierM1807paddingVpY3zN4$default = PaddingKt.m1807paddingVpY3zN4$default(SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1301Dp.m8905constructorimpl(50)), C1301Dp.m8905constructorimpl(32), 0.0f, 2, null);
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
            Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer3);
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i3 = ((438 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -1371068431, "C697@22335L46,694@22212L180,704@22525L2,700@22402L136,707@22548L1163:MeloXPlayerUi.kt#qhu5z0");
            boolean z = page == MeloXNowPlayingPage.Lyrics;
            ComposerKt.sourceInformationMarkerStart($composer3, -44225531, "CC(remember):MeloXPlayerUi.kt#9igjgp");
            boolean z2 = ($dirty & 896) == 256;
            Object objRememberedValue = $composer3.rememberedValue();
            if (z2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.lladlam.melox.ui.player.MeloXPlayerUiKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MeloXPlayerUiKt.MeloXPageSelector$lambda$0$0$0(function1);
                    }
                };
                $composer3.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            MeloXPageButton("词", z, false, (Function0) objRememberedValue, $composer3, 6, 4);
            ComposerKt.sourceInformationMarkerStart($composer3, -44219495, "CC(remember):MeloXPlayerUi.kt#9igjgp");
            Object objRememberedValue2 = $composer3.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.lladlam.melox.ui.player.MeloXPlayerUiKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Unit.INSTANCE;
                    }
                };
                $composer3.updateRememberedValue(obj);
                objRememberedValue2 = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            MeloXPageButton("浮", false, false, (Function0) objRememberedValue2, $composer3, 3510, 0);
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
            Composer composerM5188constructorimpl2 = Updater.m5188constructorimpl($composer3);
            Updater.m5196setimpl(composerM5188constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            int i6 = ((0 >> 6) & 112) | 6;
            BoxScope boxScope = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 269601431, "C711@22700L45,708@22566L194:MeloXPlayerUi.kt#qhu5z0");
            boolean z3 = page == MeloXNowPlayingPage.Queue;
            ComposerKt.sourceInformationMarkerStart($composer3, -268394646, "CC(remember):MeloXPlayerUi.kt#9igjgp");
            boolean z4 = ($dirty & 896) == 256;
            Object objRememberedValue3 = $composer3.rememberedValue();
            if (z4 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new Function0() { // from class: com.lladlam.melox.ui.player.MeloXPlayerUiKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MeloXPlayerUiKt.MeloXPageSelector$lambda$0$2$0$0(function1);
                    }
                };
                $composer3.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            MeloXPageButton("≡", z3, false, (Function0) objRememberedValue3, $composer3, 6, 4);
            if (page == MeloXNowPlayingPage.Queue || (!state.getShuffleEnabled() && state.getRepeatMode() == 0)) {
                $composer3.startReplaceGroup(270692165);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(269907028);
                ComposerKt.sourceInformation($composer3, "714@22902L785");
                Modifier modifierClip = ClipKt.clip(SizeKt.m1872size3ABfNKs(boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopEnd()), C1301Dp.m8905constructorimpl(15)), RoundedCornerShapeKt.getCircleShape());
                long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
                Modifier modifierM1043backgroundbw27NRU$default = BackgroundKt.m1043backgroundbw27NRU$default(modifierClip, Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU) : 0.82f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU) : 0.0f), null, 2, null);
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
                Composer composerM5188constructorimpl3 = Updater.m5188constructorimpl($composer3);
                Updater.m5196setimpl(composerM5188constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m5196setimpl(composerM5188constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m5196setimpl(composerM5188constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m5194reconcileimpl(composerM5188constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m5196setimpl(composerM5188constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i8 = (i7 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i9 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 1239772283, "C722@23243L426:MeloXPlayerUi.kt#qhu5z0");
                if (state.getShuffleEnabled()) {
                    str = "↝";
                } else {
                    str = state.getRepeatMode() == 1 ? "1" : "↻";
                }
                long jM6094getBlack0d7_KjU = Color.INSTANCE.m6094getBlack0d7_KjU();
                TextKt.m3912TextNvy7gAk(str, null, Color.m6066copywmQWz5c(jM6094getBlack0d7_KjU, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6094getBlack0d7_KjU) : 0.74f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6094getBlack0d7_KjU) : 0.0f), null, TextUnitKt.getSp(8), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 1597824, 0, 262058);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXPlayerUiKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return MeloXPlayerUiKt.MeloXPageSelector$lambda$1(state, page, function1, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXPageSelector$lambda$0$0$0(Function1 $onPageSelected) {
        $onPageSelected.invoke(MeloXNowPlayingPage.Lyrics);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXPageSelector$lambda$0$2$0$0(Function1 $onPageSelected) {
        $onPageSelected.invoke(MeloXNowPlayingPage.Queue);
        return Unit.INSTANCE;
    }

    private static final void MeloXPageButton(final String label, final boolean selected, boolean enabled, final Function0<Unit> function0, Composer $composer, final int $changed, final int i) {
        String str;
        boolean z;
        Function0<Unit> function1;
        final boolean enabled2;
        int i2;
        boolean enabled3;
        long jM6103getTransparent0d7_KjU;
        Function0<ComposeUiNode> function2;
        long jM6066copywmQWz5c;
        Composer $composer2 = $composer.startRestartGroup(1474113723);
        ComposerKt.sourceInformation($composer2, "C(MeloXPageButton)N(label,selected,enabled,onClick)745@23866L647:MeloXPlayerUi.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            str = label;
            $dirty |= $composer2.changed(str) ? 4 : 2;
        } else {
            str = label;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(selected) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= RendererCapabilities.DECODER_SUPPORT_MASK;
            z = enabled;
        } else if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            z = enabled;
            $dirty |= $composer2.changed(z) ? 256 : 128;
        } else {
            z = enabled;
        }
        if (($changed & 3072) == 0) {
            function1 = function0;
            $dirty |= $composer2.changedInstance(function1) ? 2048 : 1024;
        } else {
            function1 = function0;
        }
        if ($composer2.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            if (i3 != 0) {
                enabled3 = true;
                i2 = 0;
            } else {
                i2 = 0;
                enabled3 = z;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1474113723, $dirty, -1, "com.lladlam.melox.ui.player.MeloXPageButton (MeloXPlayerUi.kt:744)");
            }
            Modifier modifierClip = ClipKt.clip(SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(44)), RoundedCornerShapeKt.getCircleShape());
            Color.Companion companion = Color.INSTANCE;
            if (selected) {
                long jM6105getWhite0d7_KjU = companion.m6105getWhite0d7_KjU();
                jM6103getTransparent0d7_KjU = Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU) : 0.68f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU) : 0.0f);
            } else {
                jM6103getTransparent0d7_KjU = companion.m6103getTransparent0d7_KjU();
            }
            Modifier modifierM1078clickableoSLSa3U$default = ClickableKt.m1078clickableoSLSa3U$default(BackgroundKt.m1043backgroundbw27NRU$default(modifierClip, jM6103getTransparent0d7_KjU, null, 2, null), enabled3, null, null, null, function1, 14, null);
            boolean enabled4 = enabled3;
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, i2));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierM1078clickableoSLSa3U$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((48 << 3) & 112) << 6) & 896) | 6;
            int $dirty2 = $dirty;
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
            Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer2);
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i6 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -247843213, "C753@24172L335:MeloXPlayerUi.kt#qhu5z0");
            if (!enabled4) {
                long jM6105getWhite0d7_KjU2 = Color.INSTANCE.m6105getWhite0d7_KjU();
                jM6066copywmQWz5c = Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU2, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU2) : 0.26f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU2) : 0.0f);
            } else if (selected) {
                long jM6094getBlack0d7_KjU = Color.INSTANCE.m6094getBlack0d7_KjU();
                jM6066copywmQWz5c = Color.m6066copywmQWz5c(jM6094getBlack0d7_KjU, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6094getBlack0d7_KjU) : 0.68f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6094getBlack0d7_KjU) : 0.0f);
            } else {
                long jM6105getWhite0d7_KjU3 = Color.INSTANCE.m6105getWhite0d7_KjU();
                jM6066copywmQWz5c = Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU3, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU3) : 0.72f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU3) : 0.0f);
            }
            TextKt.m3912TextNvy7gAk(str, null, jM6066copywmQWz5c, null, TextUnitKt.getSp(19), null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, ($dirty2 & 14) | 1597440, 0, 262058);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            enabled2 = enabled4;
        } else {
            $composer2.skipToGroupEnd();
            enabled2 = z;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXPlayerUiKt$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXPlayerUiKt.MeloXPageButton$lambda$1(label, selected, enabled2, function0, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void Artwork(String url, final Modifier modifier, Composer $composer, final int $changed) {
        Composer $composer2;
        Composer composer;
        Composer composer2;
        Composer composer3;
        Composer composer4;
        final String str = url;
        Intrinsics.checkNotNullParameter(modifier, "modifier");
        Composer $composer3 = $composer.startRestartGroup(460461592);
        ComposerKt.sourceInformation($composer3, "C(Artwork)N(url,modifier)771@24601L563:MeloXPlayerUi.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(str) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(modifier) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if (!$composer3.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(460461592, $dirty2, -1, "com.lladlam.melox.ui.player.Artwork (MeloXPlayerUi.kt:770)");
            }
            long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
            Modifier modifierM1043backgroundbw27NRU$default = BackgroundKt.m1043backgroundbw27NRU$default(modifier, Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU) : 0.07f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU) : 0.0f), null, 2, null);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1043backgroundbw27NRU$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((48 << 3) & 112) << 6) & 896) | 6;
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
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -1279792817, "C:MeloXPlayerUi.kt#qhu5z0");
            String str2 = url;
            if (!(str2 == null || StringsKt.isBlank(str2))) {
                $composer3.startReplaceGroup(-1279773040);
                ComposerKt.sourceInformation($composer3, "776@24780L200");
                composer = $composer3;
                composer2 = $composer3;
                composer3 = $composer3;
                $composer2 = $composer3;
                composer4 = $composer3;
                str = url;
                SingletonAsyncImageKt.m9399AsyncImage10Xjiaw(str, "专辑封面", SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, null, null, ContentScale.INSTANCE.getCrop(), 0.0f, null, 0, false, $composer3, ($dirty2 & 14) | 1573296, 0, 1976);
                $composer3.endReplaceGroup();
            } else {
                composer = $composer3;
                composer2 = $composer3;
                composer3 = $composer3;
                $composer2 = $composer3;
                composer4 = $composer3;
                str = url;
                $composer3.startReplaceGroup(-1279533658);
                ComposerKt.sourceInformation($composer3, "783@25010L138");
                long sp = TextUnitKt.getSp(36);
                long jM6105getWhite0d7_KjU2 = Color.INSTANCE.m6105getWhite0d7_KjU();
                TextKt.m3912TextNvy7gAk("♪", null, Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU2, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU2) : 0.24f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU2) : 0.0f), null, sp, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 24966, 0, 262122);
                $composer3.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer4.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXPlayerUiKt$$ExternalSyntheticLambda24
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXPlayerUiKt.Artwork$lambda$1(str, modifier, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final String formatDuration(long milliseconds) {
        long seconds = RangesKt.coerceAtLeast(milliseconds, 0L) / 1000;
        long minutes = seconds / 60;
        long remainder = seconds % 60;
        String str = String.format("%d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(minutes), Long.valueOf(remainder)}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}
