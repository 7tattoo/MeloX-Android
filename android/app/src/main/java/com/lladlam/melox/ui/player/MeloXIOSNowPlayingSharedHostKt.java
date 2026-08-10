package com.lladlam.melox.p012ui.player;

import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.EnterExitState;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.animation.SharedTransitionScope;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.BoxWithConstraintsKt;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.layout.WindowInsetsPadding_androidKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.TextKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.ts.TsExtractor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: compiled from: MeloXIOSNowPlayingSharedHost.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\u001a3\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\u0010\n\u001a5\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0003¢\u0006\u0002\u0010\u0010\u001a \u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000fH\u0002¨\u0006\u0015²\u0006\n\u0010\f\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010\u0016\u001a\u00020\u0017X\u008a\u008e\u0002²\u0006\n\u0010\u000e\u001a\u00020\u000fX\u008a\u0084\u0002²\u0006\n\u0010\u0018\u001a\u00020\u000fX\u008a\u0084\u0002²\u0006\n\u0010\u0019\u001a\u00020\u001aX\u008a\u0084\u0002"}, d2 = {"MeloXIOSNowPlayingSharedHost", "", "state", "Lcom/lladlam/melox/ui/player/MeloXPlaybackUiState;", "onDismiss", "Lkotlin/Function0;", "sharedTransitionScope", "Landroidx/compose/animation/SharedTransitionScope;", "animatedVisibilityScope", "Landroidx/compose/animation/AnimatedVisibilityScope;", "(Lcom/lladlam/melox/ui/player/MeloXPlaybackUiState;Lkotlin/jvm/functions/Function0;Landroidx/compose/animation/SharedTransitionScope;Landroidx/compose/animation/AnimatedVisibilityScope;Landroidx/compose/runtime/Composer;I)V", "SharedArtworkDestination", "page", "Lcom/lladlam/melox/ui/player/MeloXNowPlayingPage;", "expansionProgress", "", "(Lcom/lladlam/melox/ui/player/MeloXPlaybackUiState;Lcom/lladlam/melox/ui/player/MeloXNowPlayingPage;FLandroidx/compose/animation/SharedTransitionScope;Landroidx/compose/animation/AnimatedVisibilityScope;Landroidx/compose/runtime/Composer;I)V", "smoothStep", "value", TtmlNode.START, TtmlNode.END, "app", "committingDismiss", "", "playbackScale", "shadowElevation", "Landroidx/compose/ui/unit/Dp;"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class MeloXIOSNowPlayingSharedHostKt {
    static final Unit MeloXIOSNowPlayingSharedHost$lambda$14(MeloXPlaybackUiState meloXPlaybackUiState, Function0 function0, SharedTransitionScope sharedTransitionScope, AnimatedVisibilityScope animatedVisibilityScope, int i, Composer composer, int i2) {
        MeloXIOSNowPlayingSharedHost(meloXPlaybackUiState, function0, sharedTransitionScope, animatedVisibilityScope, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit SharedArtworkDestination$lambda$3(MeloXPlaybackUiState meloXPlaybackUiState, MeloXNowPlayingPage meloXNowPlayingPage, float f, SharedTransitionScope sharedTransitionScope, AnimatedVisibilityScope animatedVisibilityScope, int i, Composer composer, int i2) {
        SharedArtworkDestination(meloXPlaybackUiState, meloXNowPlayingPage, f, sharedTransitionScope, animatedVisibilityScope, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:90:0x026b  */
    /* JADX WARN: Code duplicated, block: B:92:0x027b  */
    /* JADX WARN: Code duplicated, block: B:93:0x0280  */
    public static final void MeloXIOSNowPlayingSharedHost(final MeloXPlaybackUiState state, final Function0<Unit> onDismiss, final SharedTransitionScope sharedTransitionScope, final AnimatedVisibilityScope animatedVisibilityScope, Composer composer, final int i) {
        Composer composer2;
        boolean z;
        MutableState mutableState;
        final Transition<EnterExitState> transition;
        Object currentState;
        boolean z2;
        Snapshot.Companion companion;
        Snapshot currentThreadSnapshot;
        Function1<Object, Unit> readObserver;
        Function1<Object, Unit> function1;
        Snapshot snapshotMakeCurrentNonObservable;
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(onDismiss, "onDismiss");
        Intrinsics.checkNotNullParameter(sharedTransitionScope, "sharedTransitionScope");
        Intrinsics.checkNotNullParameter(animatedVisibilityScope, "animatedVisibilityScope");
        Composer composerStartRestartGroup = composer.startRestartGroup(-807436074);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(MeloXIOSNowPlayingSharedHost)N(state,onDismiss,sharedTransitionScope,animatedVisibilityScope)57@2555L71,58@2648L74,59@2752L49,60@2822L24,61@2890L251,61@2867L274,69@3207L342,88@3983L131,88@3949L165,114@4987L3902,111@4890L3999:MeloXIOSNowPlayingSharedHost.kt#qhu5z0");
        int i2 = i;
        if ((i & 6) == 0) {
            i2 |= composerStartRestartGroup.changed(state) ? 4 : 2;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onDismiss) ? 32 : 16;
        }
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i2 |= composerStartRestartGroup.changed(sharedTransitionScope) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(animatedVisibilityScope) ? 2048 : 1024;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 1171) != 1170, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-807436074, i2, -1, "com.lladlam.melox.ui.player.MeloXIOSNowPlayingSharedHost (MeloXIOSNowPlayingSharedHost.kt:56)");
            }
            String mediaId = state.getMediaId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1443020509, "CC(remember):MeloXIOSNowPlayingSharedHost.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(mediaId);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                z = false;
                MutableState mutableStateMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(MeloXNowPlayingPage.Artwork, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default);
                objRememberedValue = mutableStateMutableStateOf$default;
            } else {
                z = false;
            }
            MutableState mutableState2 = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            String mediaId2 = state.getMediaId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1443023488, "CC(remember):MeloXIOSNowPlayingSharedHost.kt#9igjgp");
            boolean zChanged2 = composerStartRestartGroup.changed(mediaId2);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Animatable animatableAnimatable$default = AnimatableKt.Animatable$default(0.0f, 0.0f, 2, null);
                composerStartRestartGroup.updateRememberedValue(animatableAnimatable$default);
                objRememberedValue2 = animatableAnimatable$default;
            }
            final Animatable animatable = (Animatable) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            String mediaId3 = state.getMediaId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1443026791, "CC(remember):MeloXIOSNowPlayingSharedHost.kt#9igjgp");
            boolean zChanged3 = composerStartRestartGroup.changed(mediaId3);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(z), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default2);
                objRememberedValue3 = mutableStateMutableStateOf$default2;
            }
            final MutableState mutableState3 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)N(getContext)616@28039L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 683736516, "CC(remember):Effects.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                CoroutineScope coroutineScopeCreateCompositionCoroutineScope = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                composerStartRestartGroup.updateRememberedValue(coroutineScopeCreateCompositionCoroutineScope);
                objRememberedValue4 = coroutineScopeCreateCompositionCoroutineScope;
            }
            final CoroutineScope coroutineScope = (CoroutineScope) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1443031409, "CC(remember):MeloXIOSNowPlayingSharedHost.kt#9igjgp");
            boolean zChanged4 = composerStartRestartGroup.changed(mutableState3) | composerStartRestartGroup.changedInstance(animatable) | composerStartRestartGroup.changedInstance(coroutineScope);
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (zChanged4 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                Function1 function2 = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingSharedHostKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MeloXIOSNowPlayingSharedHostKt.MeloXIOSNowPlayingSharedHost$lambda$7$0(animatable, coroutineScope, mutableState3, ((Float) obj).floatValue());
                    }
                };
                composerStartRestartGroup.updateRememberedValue(function2);
                objRememberedValue5 = function2;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final DraggableState draggableStateRememberDraggableState = DraggableKt.rememberDraggableState((Function1) objRememberedValue5, composerStartRestartGroup, z ? 1 : 0);
            Transition<EnterExitState> transition2 = animatedVisibilityScope.getTransition();
            Function3 function3 = new Function3() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingSharedHostKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return MeloXIOSNowPlayingSharedHostKt.MeloXIOSNowPlayingSharedHost$lambda$8((Transition.Segment) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            };
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 844118987, "CC(animateFloat)N(transitionSpec,label,targetValueByState)1971@84243L78:Transition.kt#pdpnli");
            TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            int i3 = ((RendererCapabilities.DECODER_SUPPORT_MASK << 3) & 57344) | (384 & 14) | ((RendererCapabilities.DECODER_SUPPORT_MASK << 3) & 896) | ((RendererCapabilities.DECODER_SUPPORT_MASK << 3) & 7168);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1143035377, "CC(animateValue)N(typeConverter,transitionSpec,label,targetValueByState)1868@79284L32,1875@79757L49,1875@79738L75,1876@79853L45,1876@79838L67,1878@79918L89:Transition.kt#pdpnli");
            if (transition2.isSeeking()) {
                mutableState = mutableState2;
                transition = transition2;
                composerStartRestartGroup.startReplaceGroup(1666827533);
                composerStartRestartGroup.endReplaceGroup();
                currentState = transition.getCurrentState();
            } else {
                composerStartRestartGroup.startReplaceGroup(1666573488);
                ComposerKt.sourceInformation(composerStartRestartGroup, "1864@79141L67");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1054612652, "CC(remember):Transition.kt#9igjgp");
                if (((i3 & 14) ^ 6) > 4) {
                    transition = transition2;
                    if (composerStartRestartGroup.changed(transition)) {
                        mutableState = mutableState2;
                    }
                    z2 = z;
                    currentState = composerStartRestartGroup.rememberedValue();
                    if (z2 || currentState == Composer.INSTANCE.getEmpty()) {
                        companion = Snapshot.INSTANCE;
                        currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                        if (currentThreadSnapshot != null) {
                            readObserver = currentThreadSnapshot.getReadObserver();
                        } else {
                            readObserver = null;
                        }
                        function1 = readObserver;
                        snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                        try {
                            EnterExitState currentState2 = transition.getCurrentState();
                            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, function1);
                            composerStartRestartGroup.updateRememberedValue(currentState2);
                            currentState = currentState2;
                        } catch (Throwable th) {
                            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, function1);
                            throw th;
                        }
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    transition = transition2;
                }
                mutableState = mutableState2;
                boolean z3 = (i3 & 6) == 4;
                z2 = z3;
                currentState = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    companion = Snapshot.INSTANCE;
                    currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                    if (currentThreadSnapshot != null) {
                        readObserver = currentThreadSnapshot.getReadObserver();
                    } else {
                        readObserver = null;
                    }
                    function1 = readObserver;
                    snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                    EnterExitState currentState3 = transition.getCurrentState();
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, function1);
                    composerStartRestartGroup.updateRememberedValue(currentState3);
                    currentState = currentState3;
                } else {
                    companion = Snapshot.INSTANCE;
                    currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                    if (currentThreadSnapshot != null) {
                        readObserver = currentThreadSnapshot.getReadObserver();
                    } else {
                        readObserver = null;
                    }
                    function1 = readObserver;
                    snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                    EnterExitState currentState4 = transition.getCurrentState();
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, function1);
                    composerStartRestartGroup.updateRememberedValue(currentState4);
                    currentState = currentState4;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endReplaceGroup();
            }
            int i4 = (i3 >> 9) & 112;
            EnterExitState enterExitState = (EnterExitState) currentState;
            composerStartRestartGroup.startReplaceGroup(1890489295);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CN(visibility):MeloXIOSNowPlayingSharedHost.kt#qhu5z0");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1890489295, i4, -1, "com.lladlam.melox.ui.player.MeloXIOSNowPlayingSharedHost.<anonymous> (MeloXIOSNowPlayingSharedHost.kt:79)");
            }
            float f = enterExitState == EnterExitState.Visible ? 1.0f : 0.0f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composerStartRestartGroup.endReplaceGroup();
            Float fValueOf = Float.valueOf(f);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1054592958, "CC(remember):Transition.kt#9igjgp");
            boolean z4 = (((i3 & 14) ^ 6) > 4 && composerStartRestartGroup.changed(transition)) || (i3 & 6) == 4;
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (z4 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                State stateDerivedStateOf = SnapshotStateKt.derivedStateOf(new Function0<EnterExitState>() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingSharedHostKt$MeloXIOSNowPlayingSharedHost$$inlined$animateFloat$1
                    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.animation.EnterExitState, java.lang.Object] */
                    @Override // kotlin.jvm.functions.Function0
                    public final EnterExitState invoke() {
                        return transition.getTargetState();
                    }
                });
                composerStartRestartGroup.updateRememberedValue(stateDerivedStateOf);
                objRememberedValue6 = stateDerivedStateOf;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            int i5 = (i3 >> 9) & 112;
            EnterExitState enterExitState2 = (EnterExitState) ((State) objRememberedValue6).getValue();
            composerStartRestartGroup.startReplaceGroup(1890489295);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CN(visibility):MeloXIOSNowPlayingSharedHost.kt#qhu5z0");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1890489295, i5, -1, "com.lladlam.melox.ui.player.MeloXIOSNowPlayingSharedHost.<anonymous> (MeloXIOSNowPlayingSharedHost.kt:79)");
            }
            float f2 = enterExitState2 == EnterExitState.Visible ? 1.0f : 0.0f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composerStartRestartGroup.endReplaceGroup();
            Float fValueOf2 = Float.valueOf(f2);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1054589890, "CC(remember):Transition.kt#9igjgp");
            boolean z5 = (((i3 & 14) ^ 6) > 4 && composerStartRestartGroup.changed(transition)) || (i3 & 6) == 4;
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (z5 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                State stateDerivedStateOf2 = SnapshotStateKt.derivedStateOf(new Function0<Transition.Segment<EnterExitState>>() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingSharedHostKt$MeloXIOSNowPlayingSharedHost$$inlined$animateFloat$2
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Transition.Segment<EnterExitState> invoke() {
                        return transition.getSegment();
                    }
                });
                composerStartRestartGroup.updateRememberedValue(stateDerivedStateOf2);
                objRememberedValue7 = stateDerivedStateOf2;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final State stateCreateTransitionAnimation = TransitionKt.createTransitionAnimation(transition, fValueOf, fValueOf2, (FiniteAnimationSpec) function3.invoke(((State) objRememberedValue7).getValue(), composerStartRestartGroup, Integer.valueOf((i3 >> 3) & 112)), vectorConverter, "full-player-expansion-progress", composerStartRestartGroup, (i3 & 14) | ((i3 << 9) & 57344) | ((i3 << 6) & 458752));
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final float fSmoothStep = smoothStep(MeloXIOSNowPlayingSharedHost$lambda$10(stateCreateTransitionAnimation), 0.0f, 0.42f);
            final float fSmoothStep2 = smoothStep(MeloXIOSNowPlayingSharedHost$lambda$10(stateCreateTransitionAnimation), 0.62f, 0.96f);
            final float fSmoothStep3 = 22.0f * (1.0f - smoothStep(MeloXIOSNowPlayingSharedHost$lambda$10(stateCreateTransitionAnimation), 0.0f, 0.94f));
            Float fValueOf3 = Float.valueOf(MeloXIOSNowPlayingSharedHost$lambda$10(stateCreateTransitionAnimation));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1443066265, "CC(remember):MeloXIOSNowPlayingSharedHost.kt#9igjgp");
            boolean zChanged5 = composerStartRestartGroup.changed(stateCreateTransitionAnimation) | composerStartRestartGroup.changedInstance(animatable) | composerStartRestartGroup.changed(mutableState3);
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (zChanged5 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                MeloXIOSNowPlayingSharedHostKt$MeloXIOSNowPlayingSharedHost$1$1 meloXIOSNowPlayingSharedHostKt$MeloXIOSNowPlayingSharedHost$1$1 = new MeloXIOSNowPlayingSharedHostKt$MeloXIOSNowPlayingSharedHost$1$1(animatable, stateCreateTransitionAnimation, mutableState3, null);
                composerStartRestartGroup.updateRememberedValue(meloXIOSNowPlayingSharedHostKt$MeloXIOSNowPlayingSharedHost$1$1);
                objRememberedValue8 = meloXIOSNowPlayingSharedHostKt$MeloXIOSNowPlayingSharedHost$1$1;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.LaunchedEffect(fValueOf3, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue8, composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceGroup(1443072212);
            ComposerKt.sourceInformation(composerStartRestartGroup, "*97@4244L104");
            Modifier modifierSharedBounds$default = SharedTransitionScope.sharedBounds$default(sharedTransitionScope, Modifier.INSTANCE, sharedTransitionScope.rememberSharedContentState(MeloXPlayerTransitionKeysKt.sharedPlayerContainerKey(state.getMediaId()), composerStartRestartGroup, 0), animatedVisibilityScope, EnterTransition.INSTANCE.getNone(), ExitTransition.INSTANCE.getNone(), null, SharedTransitionScope.ResizeMode.INSTANCE.getRemeasureToBounds(), null, false, 0.0f, null, 976, null);
            composerStartRestartGroup.endReplaceGroup();
            composer2 = composerStartRestartGroup;
            final MutableState mutableState4 = mutableState;
            BoxWithConstraintsKt.BoxWithConstraints(SizeKt.fillMaxSize$default(modifierSharedBounds$default, 0.0f, 1, null), null, false, ComposableLambdaKt.rememberComposableLambda(1227302316, true, new Function3() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingSharedHostKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return MeloXIOSNowPlayingSharedHostKt.MeloXIOSNowPlayingSharedHost$lambda$13(animatable, fSmoothStep3, fSmoothStep2, draggableStateRememberDraggableState, mutableState3, onDismiss, mutableState4, stateCreateTransitionAnimation, fSmoothStep, state, sharedTransitionScope, animatedVisibilityScope, (BoxWithConstraintsScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composer2, 54), composer2, 3072, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingSharedHostKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXIOSNowPlayingSharedHostKt.MeloXIOSNowPlayingSharedHost$lambda$14(state, onDismiss, sharedTransitionScope, animatedVisibilityScope, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final MeloXNowPlayingPage MeloXIOSNowPlayingSharedHost$lambda$1(MutableState<MeloXNowPlayingPage> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean MeloXIOSNowPlayingSharedHost$lambda$5(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void MeloXIOSNowPlayingSharedHost$lambda$6(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXIOSNowPlayingSharedHost$lambda$7$0(Animatable $dragOffset, CoroutineScope $dragScope, MutableState $committingDismiss$delegate, float delta) {
        if (MeloXIOSNowPlayingSharedHost$lambda$5($committingDismiss$delegate)) {
            return Unit.INSTANCE;
        }
        float next = RangesKt.coerceAtLeast(((Number) $dragOffset.getValue()).floatValue() + delta, 0.0f);
        BuildersKt__Builders_commonKt.launch$default($dragScope, null, CoroutineStart.UNDISPATCHED, new C2664x7fe5eddb($dragOffset, next, null), 1, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float MeloXIOSNowPlayingSharedHost$lambda$10(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    static final FiniteAnimationSpec MeloXIOSNowPlayingSharedHost$lambda$8(Transition.Segment animateFloat, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(animateFloat, "$this$animateFloat");
        $composer.startReplaceGroup(-1370407619);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1370407619, $changed, -1, "com.lladlam.melox.ui.player.MeloXIOSNowPlayingSharedHost.<anonymous> (MeloXIOSNowPlayingSharedHost.kt:71)");
        }
        SpringSpec springSpecSpring = AnimationSpecKt.spring(0.9f, 320.0f, Float.valueOf(0.001f));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return springSpecSpring;
    }

    /* JADX WARN: Code duplicated, block: B:90:0x060b  */
    static final Unit MeloXIOSNowPlayingSharedHost$lambda$13(final Animatable $dragOffset, float $baseCornerRadius, final float $fullPlayerAlpha, DraggableState $dragState, MutableState $committingDismiss$delegate, Function0 $onDismiss, final MutableState $page$delegate, State $expansionProgress$delegate, final float $backdropAlpha, MeloXPlaybackUiState $state, SharedTransitionScope $sharedTransitionScope, AnimatedVisibilityScope $animatedVisibilityScope, BoxWithConstraintsScope BoxWithConstraints, Composer $composer, int $changed) throws Throwable {
        float dismissThresholdPx;
        Modifier.Companion companionDraggable;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function2;
        Composer composer;
        float dismissThresholdPx2;
        Intrinsics.checkNotNullParameter(BoxWithConstraints, "$this$BoxWithConstraints");
        ComposerKt.sourceInformation($composer, "C115@5024L7,125@5488L231,122@5389L3494:MeloXIOSNowPlayingSharedHost.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(BoxWithConstraints) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if ($composer.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1227302316, $dirty2, -1, "com.lladlam.melox.ui.player.MeloXIOSNowPlayingSharedHost.<anonymous> (MeloXIOSNowPlayingSharedHost.kt:115)");
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Density density = (Density) objConsume;
            float heightPx = RangesKt.coerceAtLeast(Constraints.m8857getMaxHeightimpl(BoxWithConstraints.mo1521getConstraintsmsEJaDk()), 1.0f);
            float dismissThresholdPx3 = density.mo1189toPx0680j_4(Dp.constructor_impl(132));
            float dragProgress = RangesKt.coerceIn(((Number) $dragOffset.getValue()).floatValue() / (0.42f * heightPx), 0.0f, 1.0f);
            float dragCornerRadius = Math.max($baseCornerRadius, 30.0f * dragProgress);
            final float dragScale = 1.0f - (0.035f * dragProgress);
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, -1678464461, "CC(remember):MeloXIOSNowPlayingSharedHost.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance($dragOffset) | $composer.changed($fullPlayerAlpha) | $composer.changed(dragScale);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingSharedHostKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return MeloXIOSNowPlayingSharedHostKt.MeloXIOSNowPlayingSharedHost$lambda$13$1$0($dragOffset, $fullPlayerAlpha, dragScale, (GraphicsLayerScope) obj2);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierClip = ClipKt.clip(GraphicsLayerModifierKt.graphicsLayer(modifierFillMaxSize$default, (Function1) objRememberedValue), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(dragCornerRadius)));
            if (MeloXIOSNowPlayingSharedHost$lambda$1($page$delegate) == MeloXNowPlayingPage.Artwork) {
                $composer.startReplaceGroup(-492374531);
                ComposerKt.sourceInformation($composer, "138@6156L1191");
                Modifier.Companion companion = Modifier.INSTANCE;
                Orientation orientation = Orientation.Vertical;
                boolean z = MeloXIOSNowPlayingSharedHost$lambda$10($expansionProgress$delegate) >= 0.98f && !MeloXIOSNowPlayingSharedHost$lambda$5($committingDismiss$delegate);
                ComposerKt.sourceInformationMarkerStart($composer, -1678442125, "CC(remember):MeloXIOSNowPlayingSharedHost.kt#9igjgp");
                boolean zChangedInstance2 = $composer.changedInstance($dragOffset) | $composer.changed(dismissThresholdPx3) | $composer.changed($committingDismiss$delegate) | $composer.changed(heightPx) | $composer.changed($onDismiss);
                Object objRememberedValue2 = $composer.rememberedValue();
                if (!zChangedInstance2) {
                    dismissThresholdPx2 = dismissThresholdPx3;
                    if (objRememberedValue2 != Composer.INSTANCE.getEmpty()) {
                        dismissThresholdPx = dismissThresholdPx2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    companionDraggable = DraggableKt.draggable(companion, $dragState, orientation, (TsExtractor.TS_PACKET_SIZE & 4) != 0 ? true : z, (TsExtractor.TS_PACKET_SIZE & 8) != 0 ? null : null, (TsExtractor.TS_PACKET_SIZE & 16) != 0 ? false : false, (TsExtractor.TS_PACKET_SIZE & 32) != 0 ? DraggableKt.NoOpOnDragStarted : null, (TsExtractor.TS_PACKET_SIZE & 64) != 0 ? DraggableKt.NoOpOnDragStopped : (Function3) objRememberedValue2, (TsExtractor.TS_PACKET_SIZE & 128) != 0 ? false : false);
                    $composer.endReplaceGroup();
                } else {
                    dismissThresholdPx2 = dismissThresholdPx3;
                }
                dismissThresholdPx = dismissThresholdPx2;
                objRememberedValue2 = new C2663x917c8584($dragOffset, dismissThresholdPx, heightPx, $onDismiss, $committingDismiss$delegate, null);
                $composer.updateRememberedValue(objRememberedValue2);
                ComposerKt.sourceInformationMarkerEnd($composer);
                companionDraggable = DraggableKt.draggable(companion, $dragState, orientation, (TsExtractor.TS_PACKET_SIZE & 4) != 0 ? true : z, (TsExtractor.TS_PACKET_SIZE & 8) != 0 ? null : null, (TsExtractor.TS_PACKET_SIZE & 16) != 0 ? false : false, (TsExtractor.TS_PACKET_SIZE & 32) != 0 ? DraggableKt.NoOpOnDragStarted : null, (TsExtractor.TS_PACKET_SIZE & 64) != 0 ? DraggableKt.NoOpOnDragStopped : (Function3) objRememberedValue2, (TsExtractor.TS_PACKET_SIZE & 128) != 0 ? false : false);
                $composer.endReplaceGroup();
            } else {
                dismissThresholdPx = dismissThresholdPx3;
                $composer.startReplaceGroup(-490897474);
                $composer.endReplaceGroup();
                companionDraggable = Modifier.INSTANCE;
            }
            Modifier modifierThen = modifierClip.then(companionDraggable);
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierThen);
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
            ComposerKt.sourceInformationMarkerStart($composer, 795483063, "C169@7614L25,166@7503L328,180@7956L27,177@7845L470,195@8589L284:MeloXIOSNowPlayingSharedHost.kt#qhu5z0");
            Modifier modifierFillMaxSize$default2 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 164210283, "CC(remember):MeloXIOSNowPlayingSharedHost.kt#9igjgp");
            boolean zChanged = $composer.changed($backdropAlpha);
            Object objRememberedValue3 = $composer.rememberedValue();
            if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingSharedHostKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return MeloXIOSNowPlayingSharedHostKt.MeloXIOSNowPlayingSharedHost$lambda$13$3$0$0($backdropAlpha, (GraphicsLayerScope) obj2);
                    }
                };
                $composer.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(modifierFillMaxSize$default2, (Function1) objRememberedValue3);
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierGraphicsLayer);
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
            Composer composerM5188constructorimpl2 = Updater.constructor_impl($composer);
            Updater.set_impl(composerM5188constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i6 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -7948805, "C171@7673L144:MeloXIOSNowPlayingSharedHost.kt#qhu5z0");
            MeloXFlowingLightBackdropKt.MeloXFlowingLightBackdrop($state.getArtworkUrl(), $state.isPlaying(), null, $composer, 0, 4);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierFillMaxSize$default3 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 164221229, "CC(remember):MeloXIOSNowPlayingSharedHost.kt#9igjgp");
            boolean zChanged2 = $composer.changed($fullPlayerAlpha);
            Object objRememberedValue4 = $composer.rememberedValue();
            if (zChanged2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingSharedHostKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return MeloXIOSNowPlayingSharedHostKt.MeloXIOSNowPlayingSharedHost$lambda$13$3$2$0($fullPlayerAlpha, (GraphicsLayerScope) obj3);
                    }
                };
                $composer.updateRememberedValue(obj2);
                objRememberedValue4 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierGraphicsLayer2 = GraphicsLayerModifierKt.graphicsLayer(modifierFillMaxSize$default3, (Function1) objRememberedValue4);
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifierGraphicsLayer2);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i7 = ((((0 << 3) & 112) << 6) & 896) | 6;
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
            Updater.set_impl(composerM5188constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i8 = (i7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
            int i9 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1820967592, "C186@8186L13,182@8017L284:MeloXIOSNowPlayingSharedHost.kt#qhu5z0");
            MeloXNowPlayingPage meloXNowPlayingPageMeloXIOSNowPlayingSharedHost$lambda$1 = MeloXIOSNowPlayingSharedHost$lambda$1($page$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, 1188190236, "CC(remember):MeloXIOSNowPlayingSharedHost.kt#9igjgp");
            boolean zChanged3 = $composer.changed($page$delegate);
            Object objRememberedValue5 = $composer.rememberedValue();
            if (!zChanged3) {
                composer = $composer;
                if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                MeloXIOSNowPlayingV2Kt.MeloXIOSNowPlayingV2($state, $onDismiss, meloXNowPlayingPageMeloXIOSNowPlayingSharedHost$lambda$1, (Function1) objRememberedValue5, false, false, composer, 221184, 0);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SharedArtworkDestination($state, MeloXIOSNowPlayingSharedHost$lambda$1($page$delegate), MeloXIOSNowPlayingSharedHost$lambda$10($expansionProgress$delegate), $sharedTransitionScope, $animatedVisibilityScope, $composer, 0);
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
                composer = $composer;
            }
            objRememberedValue5 = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingSharedHostKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    return MeloXIOSNowPlayingSharedHostKt.MeloXIOSNowPlayingSharedHost$lambda$13$3$3$0$0($page$delegate, (MeloXNowPlayingPage) obj3);
                }
            };
            $composer.updateRememberedValue(objRememberedValue5);
            ComposerKt.sourceInformationMarkerEnd(composer);
            MeloXIOSNowPlayingV2Kt.MeloXIOSNowPlayingV2($state, $onDismiss, meloXNowPlayingPageMeloXIOSNowPlayingSharedHost$lambda$1, (Function1) objRememberedValue5, false, false, composer, 221184, 0);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            SharedArtworkDestination($state, MeloXIOSNowPlayingSharedHost$lambda$1($page$delegate), MeloXIOSNowPlayingSharedHost$lambda$10($expansionProgress$delegate), $sharedTransitionScope, $animatedVisibilityScope, $composer, 0);
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
    public static final Unit MeloXIOSNowPlayingSharedHost$lambda$13$1$0(Animatable $dragOffset, float $fullPlayerAlpha, float $dragScale, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setTranslationY(((Number) $dragOffset.getValue()).floatValue() * $fullPlayerAlpha);
        graphicsLayer.setScaleX($dragScale);
        graphicsLayer.setScaleY($dragScale);
        graphicsLayer.mo6269setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(0.5f, 0.0f));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXIOSNowPlayingSharedHost$lambda$13$3$0$0(float $backdropAlpha, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setAlpha($backdropAlpha);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXIOSNowPlayingSharedHost$lambda$13$3$2$0(float $fullPlayerAlpha, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setAlpha($fullPlayerAlpha);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXIOSNowPlayingSharedHost$lambda$13$3$3$0$0(MutableState $page$delegate, MeloXNowPlayingPage it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $page$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    private static final void SharedArtworkDestination(final MeloXPlaybackUiState state, final MeloXNowPlayingPage page, final float expansionProgress, final SharedTransitionScope sharedTransitionScope, final AnimatedVisibilityScope animatedVisibilityScope, Composer $composer, final int $changed) {
        MeloXPlaybackUiState meloXPlaybackUiState;
        Composer $composer2;
        SpringSpec springSpecSpring;
        final float artworkAlpha;
        Function0<ComposeUiNode> function0;
        Composer $composer3 = $composer.startRestartGroup(-225110328);
        ComposerKt.sourceInformation($composer3, "C(SharedArtworkDestination)N(state,page,expansionProgress,sharedTransitionScope,animatedVisibilityScope)215@9217L512,232@9757L232,255@10700L3011:MeloXIOSNowPlayingSharedHost.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            meloXPlaybackUiState = state;
            $dirty |= $composer3.changed(meloXPlaybackUiState) ? 4 : 2;
        } else {
            meloXPlaybackUiState = state;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(page.ordinal()) ? 32 : 16;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer3.changed(expansionProgress) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(sharedTransitionScope) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changedInstance(animatedVisibilityScope) ? 16384 : 8192;
        }
        if ($composer3.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-225110328, $dirty, -1, "com.lladlam.melox.ui.player.SharedArtworkDestination (MeloXIOSNowPlayingSharedHost.kt:214)");
            }
            float f = meloXPlaybackUiState.isPlaying() ? 1.0f : 0.74f;
            if (meloXPlaybackUiState.isPlaying()) {
                springSpecSpring = AnimationSpecKt.spring(0.7f, 280.0f, Float.valueOf(0.001f));
            } else {
                springSpecSpring = AnimationSpecKt.spring(0.94f, 360.0f, Float.valueOf(0.001f));
            }
            State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, springSpecSpring, 0.0f, "shared-artwork-playback-scale", null, $composer3, 3072, 20);
            final State<Dp> stateM940animateDpAsStateAjpBEmI = AnimateAsStateKt.m940animateDpAsStateAjpBEmI(meloXPlaybackUiState.isPlaying() ? Dp.constructor_impl(26) : Dp.constructor_impl(14), AnimationSpecKt.spring$default(0.92f, 320.0f, null, 4, null), "shared-artwork-shadow", null, $composer3, 432, 8);
            $composer2 = $composer3;
            if (page == MeloXNowPlayingPage.Artwork) {
                artworkAlpha = 1.0f;
            } else {
                artworkAlpha = 1.0f - smoothStep(expansionProgress, 0.72f, 0.985f);
            }
            float fullScreenScaleBlend = smoothStep(expansionProgress, 0.3f, 0.88f);
            final float effectiveScale = ((SharedArtworkDestination$lambda$0(stateAnimateFloatAsState) - 1.0f) * fullScreenScaleBlend) + 1.0f;
            Modifier modifierM1807paddingVpY3zN4$default = PaddingKt.m1807paddingVpY3zN4$default(WindowInsetsPadding_androidKt.statusBarsPadding(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null)), Dp.constructor_impl(32), 0.0f, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierM1807paddingVpY3zN4$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((0 << 3) & 112) << 6) & 896) | 6;
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
            Updater.set_impl(composerM5188constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            int i3 = ((0 >> 6) & 112) | 6;
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, -1456954750, "C261@10854L30,267@11017L2647,263@10894L2770,335@13674L31:MeloXIOSNowPlayingSharedHost.kt#qhu5z0");
            SpacerKt.Spacer(SizeKt.m1858height3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(30)), $composer2, 6);
            BoxWithConstraintsKt.BoxWithConstraints(ColumnScope.weight$default(columnScope, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 1.0f, false, 2, null), null, false, ComposableLambdaKt.rememberComposableLambda(1253814676, true, new Function3() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingSharedHostKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return MeloXIOSNowPlayingSharedHostKt.SharedArtworkDestination$lambda$2$0(sharedTransitionScope, state, animatedVisibilityScope, artworkAlpha, effectiveScale, stateM940animateDpAsStateAjpBEmI, (BoxWithConstraintsScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer2, 54), $composer2, 3072, 6);
            SpacerKt.Spacer(SizeKt.m1858height3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(279)), $composer2, 6);
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
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingSharedHostKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXIOSNowPlayingSharedHostKt.SharedArtworkDestination$lambda$3(state, page, expansionProgress, sharedTransitionScope, animatedVisibilityScope, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float SharedArtworkDestination$lambda$0(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float SharedArtworkDestination$lambda$1(State<Dp> state) {
        return ((Dp) state.getValue()).m8919unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SharedArtworkDestination$lambda$2$0(SharedTransitionScope $sharedTransitionScope, MeloXPlaybackUiState $state, AnimatedVisibilityScope $animatedVisibilityScope, final float $artworkAlpha, final float $effectiveScale, State $shadowElevation$delegate, BoxWithConstraintsScope BoxWithConstraints, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function2;
        Intrinsics.checkNotNullParameter(BoxWithConstraints, "$this$BoxWithConstraints");
        ComposerKt.sourceInformation($composer, "C273@11167L2487:MeloXIOSNowPlayingSharedHost.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(BoxWithConstraints) ? 4 : 2;
        }
        if ($composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1253814676, $dirty, -1, "com.lladlam.melox.ui.player.SharedArtworkDestination.<anonymous>.<anonymous> (MeloXIOSNowPlayingSharedHost.kt:268)");
            }
            float artworkSize = ((Dp) ComparisonsKt.maxOf(Dp.m8903boximpl(Dp.constructor_impl(170)), ComparisonsKt.minOf(Dp.m8903boximpl(Dp.constructor_impl(BoxWithConstraints.mo1523getMaxWidthD9Ej5fM() + Dp.constructor_impl(16))), Dp.m8903boximpl(Dp.constructor_impl(BoxWithConstraints.mo1522getMaxHeightD9Ej5fM() - Dp.constructor_impl(92)))))).m8919unboximpl();
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
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
            int i3 = ((6 >> 6) & 112) | 6;
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 334614401, "C274@11216L27,285@11655L1048,309@12721L30,312@12849L744,331@13611L29:MeloXIOSNowPlayingSharedHost.kt#qhu5z0");
            SpacerKt.Spacer(ColumnScope.weight$default(columnScope, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 0);
            $composer.startReplaceGroup(1257720045);
            ComposerKt.sourceInformation($composer, "*278@11401L120");
            Modifier modifierSharedElement$default = SharedTransitionScope.sharedElement$default($sharedTransitionScope, Modifier.INSTANCE, $sharedTransitionScope.rememberSharedContentState(MeloXSharedTransitionsKt.sharedArtworkKey($state.getMediaId()), $composer, 0), $animatedVisibilityScope, null, null, false, 0.0f, null, 124, null);
            $composer.endReplaceGroup();
            Modifier modifierM1872size3ABfNKs = SizeKt.m1872size3ABfNKs(modifierSharedElement$default, artworkSize);
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierM1872size3ABfNKs);
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
            Composer composerM5188constructorimpl2 = Updater.constructor_impl($composer);
            Updater.set_impl(composerM5188constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i6 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -186015340, "C293@11977L196,289@11790L895:MeloXIOSNowPlayingSharedHost.kt#qhu5z0");
            String artworkUrl = $state.getArtworkUrl();
            Modifier modifierFillMaxSize$default2 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 2072214772, "CC(remember):MeloXIOSNowPlayingSharedHost.kt#9igjgp");
            boolean zChanged = $composer.changed($artworkAlpha) | $composer.changed($effectiveScale);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSNowPlayingSharedHostKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MeloXIOSNowPlayingSharedHostKt.SharedArtworkDestination$lambda$2$0$0$1$0$0($artworkAlpha, $effectiveScale, (GraphicsLayerScope) obj);
                    }
                };
                $composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(modifierFillMaxSize$default2, (Function1) objRememberedValue);
            float fSharedArtworkDestination$lambda$1 = SharedArtworkDestination$lambda$1($shadowElevation$delegate);
            RoundedCornerShape roundedCornerShapeM2135RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(12));
            long jM6094getBlack0d7_KjU = Color.INSTANCE.m6094getBlack0d7_KjU();
            long jM6066copywmQWz5c = Color.copy_wmQWz5c(jM6094getBlack0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6094getBlack0d7_KjU) : $artworkAlpha * 0.28f, (14 & 2) != 0 ? Color.getRed_impl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6094getBlack0d7_KjU) : 0.0f);
            long jM6094getBlack0d7_KjU2 = Color.INSTANCE.m6094getBlack0d7_KjU();
            MeloXPlayerUiKt.Artwork(artworkUrl, ClipKt.clip(ShadowKt.m5665shadows4CzXII(modifierGraphicsLayer, fSharedArtworkDestination$lambda$1, roundedCornerShapeM2135RoundedCornerShape0680j_4, false, jM6066copywmQWz5c, Color.copy_wmQWz5c(jM6094getBlack0d7_KjU2, (14 & 1) != 0 ? Color.getAlpha_impl(jM6094getBlack0d7_KjU2) : $artworkAlpha * 0.28f, (14 & 2) != 0 ? Color.getRed_impl(jM6094getBlack0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6094getBlack0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6094getBlack0d7_KjU2) : 0.0f)), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(12))), $composer, 0);
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
            ComposerKt.sourceInformationMarkerStart($composer, -2046834653, "C313@12903L323,321@13247L328:MeloXIOSNowPlayingSharedHost.kt#qhu5z0");
            String title = $state.getTitle();
            if (StringsKt.isBlank(title)) {
                title = "正在播放";
            }
            TextKt.m3912TextNvy7gAk(title, null, Color.INSTANCE.m6103getTransparent0d7_KjU(), null, TextUnitKt.getSp(20), null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, TextUnitKt.getSp(24), 0, false, 1, 0, null, null, $composer, 1597824, 24624, 243626);
            String artist = $state.getArtist();
            if (StringsKt.isBlank(artist)) {
                artist = " ";
            }
            TextKt.m3912TextNvy7gAk(artist, PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.constructor_impl(2), 0.0f, 0.0f, 13, null), Color.INSTANCE.m6103getTransparent0d7_KjU(), null, TextUnitKt.getSp(20), null, null, null, 0L, null, null, TextUnitKt.getSp(24), 0, false, 1, 0, null, null, $composer, 25008, 24624, 243688);
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
    public static final Unit SharedArtworkDestination$lambda$2$0$0$1$0$0(float $artworkAlpha, float $effectiveScale, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setAlpha($artworkAlpha);
        graphicsLayer.setScaleX($effectiveScale);
        graphicsLayer.setScaleY($effectiveScale);
        return Unit.INSTANCE;
    }

    private static final float smoothStep(float value, float start, float end) {
        if (end <= start) {
            return value >= end ? 1.0f : 0.0f;
        }
        float t = RangesKt.coerceIn((value - start) / (end - start), 0.0f, 1.0f);
        return t * t * (3.0f - (2.0f * t));
    }
}
