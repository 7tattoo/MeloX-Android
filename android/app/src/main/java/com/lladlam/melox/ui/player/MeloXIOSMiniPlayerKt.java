package com.lladlam.melox.ui.player;

import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.EnterExitState;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.animation.SharedTransitionScope;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.DarkThemeKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TextKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.C1301Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.profileinstaller.ProfileVerifier;
import com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KFunction;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MeloXIOSMiniPlayer.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\u001aO\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007¢\u0006\u0002\u0010\r\u001a?\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00072\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0003¢\u0006\u0002\u0010\u0017\u001a \u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u0016H\u0002¨\u0006\u001c²\u0006\n\u0010\u001d\u001a\u00020\u0016X\u008a\u008e\u0002²\u0006\n\u0010\u0019\u001a\u00020\u0016X\u008a\u0084\u0002"}, d2 = {"MeloXIOSMiniPlayer", "", "state", "Lcom/lladlam/melox/ui/player/MeloXPlaybackUiState;", "onExpand", "Lkotlin/Function0;", "inline", "", "dynamicGlassEnabled", "sharedTransitionScope", "Landroidx/compose/animation/SharedTransitionScope;", "animatedVisibilityScope", "Landroidx/compose/animation/AnimatedVisibilityScope;", "(Lcom/lladlam/melox/ui/player/MeloXPlaybackUiState;Lkotlin/jvm/functions/Function0;ZZLandroidx/compose/animation/SharedTransitionScope;Landroidx/compose/animation/AnimatedVisibilityScope;Landroidx/compose/runtime/Composer;II)V", "MiniVectorButton", "kind", "Lcom/lladlam/melox/ui/player/MiniGlyph;", "enabled", "onClick", "modifier", "Landroidx/compose/ui/Modifier;", "visualAlpha", "", "(Lcom/lladlam/melox/ui/player/MiniGlyph;ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;FLandroidx/compose/runtime/Composer;II)V", "smoothStep", "value", TtmlNode.START, TtmlNode.END, "app", "accumulatedDrag"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class MeloXIOSMiniPlayerKt {

    /* JADX INFO: compiled from: MeloXIOSMiniPlayer.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MiniGlyph.values().length];
            try {
                iArr[MiniGlyph.Play.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[MiniGlyph.Pause.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[MiniGlyph.Forward.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit MeloXIOSMiniPlayer$lambda$0(MeloXPlaybackUiState meloXPlaybackUiState, Function0 function0, boolean z, boolean z2, SharedTransitionScope sharedTransitionScope, AnimatedVisibilityScope animatedVisibilityScope, int i, int i2, Composer composer, int i3) {
        MeloXIOSMiniPlayer(meloXPlaybackUiState, function0, z, z2, sharedTransitionScope, animatedVisibilityScope, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit MeloXIOSMiniPlayer$lambda$11(MeloXPlaybackUiState meloXPlaybackUiState, Function0 function0, boolean z, boolean z2, SharedTransitionScope sharedTransitionScope, AnimatedVisibilityScope animatedVisibilityScope, int i, int i2, Composer composer, int i3) {
        MeloXIOSMiniPlayer(meloXPlaybackUiState, function0, z, z2, sharedTransitionScope, animatedVisibilityScope, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit MiniVectorButton$lambda$1(MiniGlyph miniGlyph, boolean z, Function0 function0, Modifier modifier, float f, int i, int i2, Composer composer, int i3) {
        MiniVectorButton(miniGlyph, z, function0, modifier, f, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:119:0x020e  */
    /* JADX WARN: Code duplicated, block: B:121:0x021e  */
    /* JADX WARN: Code duplicated, block: B:122:0x0223  */
    /* JADX WARN: Code duplicated, block: B:160:0x0320  */
    /* JADX WARN: Code duplicated, block: B:161:0x032e  */
    /* JADX WARN: Code duplicated, block: B:164:0x0336  */
    /* JADX WARN: Code duplicated, block: B:165:0x0338  */
    /* JADX WARN: Code duplicated, block: B:168:0x0340  */
    /* JADX WARN: Code duplicated, block: B:183:0x037b  */
    public static final void MeloXIOSMiniPlayer(final MeloXPlaybackUiState state, final Function0<Unit> onExpand, boolean inline, boolean dynamicGlassEnabled, SharedTransitionScope sharedTransitionScope, AnimatedVisibilityScope animatedVisibilityScope, Composer $composer, final int $changed, final int i) {
        boolean z;
        boolean z2;
        SharedTransitionScope sharedTransitionScope2;
        AnimatedVisibilityScope animatedVisibilityScope2;
        MeloXPlaybackUiState meloXPlaybackUiState;
        Composer $composer2;
        final boolean inline2;
        final boolean dynamicGlassEnabled2;
        final SharedTransitionScope sharedTransitionScope3;
        final AnimatedVisibilityScope animatedVisibilityScope3;
        float f;
        float fMeloXIOSMiniPlayer$lambda$6;
        Modifier.Companion sharedContainerModifier;
        long jM6066copywmQWz5c;
        long jM6066copywmQWz5c2;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Modifier.Companion companionSharedElement$default;
        Function0<ComposeUiNode> function2;
        int i2;
        final Transition<EnterExitState> transition;
        Object currentState;
        Object obj;
        int i3;
        EnterExitState enterExitState;
        float f2;
        boolean z3;
        Object objRememberedValue;
        boolean z4;
        Snapshot.Companion companion;
        Snapshot currentThreadSnapshot;
        Function1<Object, Unit> readObserver;
        Function1<Object, Unit> function3;
        Snapshot snapshotMakeCurrentNonObservable;
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(onExpand, "onExpand");
        Composer $composer3 = $composer.startRestartGroup(1832987496);
        ComposerKt.sourceInformation($composer3, "C(MeloXIOSMiniPlayer)N(state,onExpand,inline,dynamicGlassEnabled,sharedTransitionScope,animatedVisibilityScope)58@2495L51,119@4917L589,115@4757L5450:MeloXIOSMiniPlayer.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(state) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(onExpand) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= RendererCapabilities.DECODER_SUPPORT_MASK;
            z = inline;
        } else if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            z = inline;
            $dirty |= $composer3.changed(z) ? 256 : 128;
        } else {
            z = inline;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            z2 = dynamicGlassEnabled;
        } else if (($changed & 3072) == 0) {
            z2 = dynamicGlassEnabled;
            $dirty |= $composer3.changed(z2) ? 2048 : 1024;
        } else {
            z2 = dynamicGlassEnabled;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            sharedTransitionScope2 = sharedTransitionScope;
        } else if (($changed & 24576) == 0) {
            sharedTransitionScope2 = sharedTransitionScope;
            $dirty |= $composer3.changed(sharedTransitionScope2) ? 16384 : 8192;
        } else {
            sharedTransitionScope2 = sharedTransitionScope;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.f253xf2722a21;
            animatedVisibilityScope2 = animatedVisibilityScope;
        } else if ((196608 & $changed) == 0) {
            animatedVisibilityScope2 = animatedVisibilityScope;
            $dirty |= $composer3.changedInstance(animatedVisibilityScope2) ? 131072 : 65536;
        } else {
            animatedVisibilityScope2 = animatedVisibilityScope;
        }
        int $dirty2 = $dirty;
        if ($composer3.shouldExecute((74899 & $dirty2) != 74898, $dirty2 & 1)) {
            final boolean inline3 = i4 != 0 ? false : z;
            final boolean dynamicGlassEnabled3 = i5 != 0 ? true : z2;
            final SharedTransitionScope sharedTransitionScope4 = i6 != 0 ? null : sharedTransitionScope2;
            AnimatedVisibilityScope animatedVisibilityScope4 = i7 != 0 ? null : animatedVisibilityScope2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1832987496, $dirty2, -1, "com.lladlam.melox.ui.player.MeloXIOSMiniPlayer (MeloXIOSMiniPlayer.kt:55)");
            }
            if (!state.getHasMedia()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer3.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final AnimatedVisibilityScope animatedVisibilityScope5 = animatedVisibilityScope4;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXIOSMiniPlayerKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            return MeloXIOSMiniPlayerKt.MeloXIOSMiniPlayer$lambda$0(state, onExpand, inline3, dynamicGlassEnabled3, sharedTransitionScope4, animatedVisibilityScope5, $changed, i, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                    return;
                }
                return;
            }
            meloXPlaybackUiState = state;
            boolean inline4 = inline3;
            boolean dynamicGlassEnabled4 = dynamicGlassEnabled3;
            SharedTransitionScope sharedTransitionScope5 = sharedTransitionScope4;
            String mediaId = meloXPlaybackUiState.getMediaId();
            ComposerKt.sourceInformationMarkerStart($composer3, 564403131, "CC(remember):MeloXIOSMiniPlayer.kt#9igjgp");
            boolean zChanged = $composer3.changed(mediaId);
            Object objRememberedValue2 = $composer3.rememberedValue();
            if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object objMutableFloatStateOf = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
                $composer3.updateRememberedValue(objMutableFloatStateOf);
                objRememberedValue2 = objMutableFloatStateOf;
            }
            MutableFloatState accumulatedDrag$delegate = (MutableFloatState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (animatedVisibilityScope4 != null) {
                $composer3.startReplaceGroup(316757738);
                ComposerKt.sourceInformation($composer3, "61@2671L386");
                Transition<EnterExitState> transition2 = animatedVisibilityScope4.getTransition();
                Function3 function4 = new Function3() { // from class: com.lladlam.melox.ui.player.MeloXIOSMiniPlayerKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj2, Object obj3, Object obj4) {
                        return MeloXIOSMiniPlayerKt.MeloXIOSMiniPlayer$lambda$4((Transition.Segment) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                    }
                };
                f = 1.0f;
                Composer composer = $composer3;
                ComposerKt.sourceInformationMarkerStart(composer, 844118987, "CC(animateFloat)N(transitionSpec,label,targetValueByState)1971@84243L78:Transition.kt#pdpnli");
                TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                int i8 = ((RendererCapabilities.DECODER_SUPPORT_MASK << 3) & 57344) | (384 & 14) | ((RendererCapabilities.DECODER_SUPPORT_MASK << 3) & 896) | ((RendererCapabilities.DECODER_SUPPORT_MASK << 3) & 7168);
                ComposerKt.sourceInformationMarkerStart(composer, 1143035377, "CC(animateValue)N(typeConverter,transitionSpec,label,targetValueByState)1868@79284L32,1875@79757L49,1875@79738L75,1876@79853L45,1876@79838L67,1878@79918L89:Transition.kt#pdpnli");
                if (transition2.isSeeking()) {
                    i2 = i8;
                    composer = composer;
                    transition = transition2;
                    composer.startReplaceGroup(1666827533);
                    composer.endReplaceGroup();
                    currentState = transition.getCurrentState();
                } else {
                    composer.startReplaceGroup(1666573488);
                    ComposerKt.sourceInformation(composer, "1864@79141L67");
                    ComposerKt.sourceInformationMarkerStart(composer, -1054612652, "CC(remember):Transition.kt#9igjgp");
                    i2 = i8;
                    if (((i8 & 14) ^ 6) > 4) {
                        transition = transition2;
                        if (!composer.changed(transition)) {
                        }
                        z4 = z;
                        Object objRememberedValue3 = composer.rememberedValue();
                        if (!z4 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            companion = Snapshot.INSTANCE;
                            currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                            if (currentThreadSnapshot != null) {
                                readObserver = currentThreadSnapshot.getReadObserver();
                            } else {
                                readObserver = null;
                            }
                            function3 = readObserver;
                            snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                            try {
                                EnterExitState currentState2 = transition.getCurrentState();
                                companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, function3);
                                currentState = currentState2;
                                composer.updateRememberedValue(currentState);
                            } catch (Throwable th) {
                                companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, function3);
                                throw th;
                            }
                        } else {
                            currentState = objRememberedValue3;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        composer.endReplaceGroup();
                    } else {
                        transition = transition2;
                    }
                    boolean z5 = (i2 & 6) == 4;
                    z4 = z5;
                    Object objRememberedValue4 = composer.rememberedValue();
                    if (z4) {
                        companion = Snapshot.INSTANCE;
                        currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                        if (currentThreadSnapshot != null) {
                            readObserver = currentThreadSnapshot.getReadObserver();
                        } else {
                            readObserver = null;
                        }
                        function3 = readObserver;
                        snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                        EnterExitState currentState3 = transition.getCurrentState();
                        companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, function3);
                        currentState = currentState3;
                        composer.updateRememberedValue(currentState);
                    } else {
                        companion = Snapshot.INSTANCE;
                        currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                        if (currentThreadSnapshot != null) {
                            readObserver = currentThreadSnapshot.getReadObserver();
                        } else {
                            readObserver = null;
                        }
                        function3 = readObserver;
                        snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                        EnterExitState currentState4 = transition.getCurrentState();
                        companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, function3);
                        currentState = currentState4;
                        composer.updateRememberedValue(currentState);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    composer.endReplaceGroup();
                }
                int i9 = (i2 >> 9) & 112;
                EnterExitState enterExitState2 = (EnterExitState) currentState;
                composer.startReplaceGroup(-813657912);
                ComposerKt.sourceInformation(composer, "CN(visibility):MeloXIOSMiniPlayer.kt#qhu5z0");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-813657912, i9, -1, "com.lladlam.melox.ui.player.MeloXIOSMiniPlayer.<anonymous> (MeloXIOSMiniPlayer.kt:71)");
                }
                float f3 = enterExitState2 == EnterExitState.Visible ? 0.0f : 1.0f;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceGroup();
                Object objValueOf = Float.valueOf(f3);
                ComposerKt.sourceInformationMarkerStart(composer, -1054592958, "CC(remember):Transition.kt#9igjgp");
                boolean z6 = (((i2 & 14) ^ 6) > 4 && composer.changed(transition)) || (i2 & 6) == 4;
                Object objRememberedValue5 = composer.rememberedValue();
                if (z6) {
                    obj = objValueOf;
                } else {
                    obj = objValueOf;
                    if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    i3 = (i2 >> 9) & 112;
                    enterExitState = (EnterExitState) ((State) objRememberedValue5).getValue();
                    composer.startReplaceGroup(-813657912);
                    ComposerKt.sourceInformation(composer, "CN(visibility):MeloXIOSMiniPlayer.kt#qhu5z0");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-813657912, i3, -1, "com.lladlam.melox.ui.player.MeloXIOSMiniPlayer.<anonymous> (MeloXIOSMiniPlayer.kt:71)");
                    }
                    if (enterExitState == EnterExitState.Visible) {
                        f2 = 0.0f;
                    } else {
                        f2 = 1.0f;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer.endReplaceGroup();
                    Object objValueOf2 = Float.valueOf(f2);
                    ComposerKt.sourceInformationMarkerStart(composer, -1054589890, "CC(remember):Transition.kt#9igjgp");
                    z3 = (((i2 & 14) ^ 6) <= 4 && composer.changed(transition)) || (i2 & 6) == 4;
                    objRememberedValue = composer.rememberedValue();
                    if (z3 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        Object objDerivedStateOf = SnapshotStateKt.derivedStateOf(new Function0<Transition.Segment<EnterExitState>>() { // from class: com.lladlam.melox.ui.player.MeloXIOSMiniPlayerKt$MeloXIOSMiniPlayer$$inlined$animateFloat$2
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Transition.Segment<EnterExitState> invoke() {
                                return transition.getSegment();
                            }
                        });
                        composer.updateRememberedValue(objDerivedStateOf);
                        objRememberedValue = objDerivedStateOf;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    State value$delegate = TransitionKt.createTransitionAnimation(transition, obj, objValueOf2, (FiniteAnimationSpec) function4.invoke(((State) objRememberedValue).getValue(), composer, Integer.valueOf((i2 >> 3) & 112)), vectorConverter, "mini-player-expansion-progress", composer, (i2 & 14) | (57344 & (i2 << 9)) | ((i2 << 6) & 458752));
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    fMeloXIOSMiniPlayer$lambda$6 = MeloXIOSMiniPlayer$lambda$6(value$delegate);
                    $composer3.endReplaceGroup();
                }
                Object objDerivedStateOf2 = SnapshotStateKt.derivedStateOf(new Function0<EnterExitState>() { // from class: com.lladlam.melox.ui.player.MeloXIOSMiniPlayerKt$MeloXIOSMiniPlayer$$inlined$animateFloat$1
                    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.animation.EnterExitState, java.lang.Object] */
                    @Override // kotlin.jvm.functions.Function0
                    public final EnterExitState invoke() {
                        return transition.getTargetState();
                    }
                });
                composer.updateRememberedValue(objDerivedStateOf2);
                objRememberedValue5 = objDerivedStateOf2;
                ComposerKt.sourceInformationMarkerEnd(composer);
                i3 = (i2 >> 9) & 112;
                enterExitState = (EnterExitState) ((State) objRememberedValue5).getValue();
                composer.startReplaceGroup(-813657912);
                ComposerKt.sourceInformation(composer, "CN(visibility):MeloXIOSMiniPlayer.kt#qhu5z0");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-813657912, i3, -1, "com.lladlam.melox.ui.player.MeloXIOSMiniPlayer.<anonymous> (MeloXIOSMiniPlayer.kt:71)");
                }
                if (enterExitState == EnterExitState.Visible) {
                    f2 = 0.0f;
                } else {
                    f2 = 1.0f;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceGroup();
                Object objValueOf3 = Float.valueOf(f2);
                ComposerKt.sourceInformationMarkerStart(composer, -1054589890, "CC(remember):Transition.kt#9igjgp");
                if (((i2 & 14) ^ 6) <= 4) {
                }
                objRememberedValue = composer.rememberedValue();
                if (z3) {
                    Object objDerivedStateOf3 = SnapshotStateKt.derivedStateOf(new Function0<Transition.Segment<EnterExitState>>() { // from class: com.lladlam.melox.ui.player.MeloXIOSMiniPlayerKt$MeloXIOSMiniPlayer$$inlined$animateFloat$2
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Transition.Segment<EnterExitState> invoke() {
                            return transition.getSegment();
                        }
                    });
                    composer.updateRememberedValue(objDerivedStateOf3);
                    objRememberedValue = objDerivedStateOf3;
                } else {
                    Object objDerivedStateOf4 = SnapshotStateKt.derivedStateOf(new Function0<Transition.Segment<EnterExitState>>() { // from class: com.lladlam.melox.ui.player.MeloXIOSMiniPlayerKt$MeloXIOSMiniPlayer$$inlined$animateFloat$2
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Transition.Segment<EnterExitState> invoke() {
                            return transition.getSegment();
                        }
                    });
                    composer.updateRememberedValue(objDerivedStateOf4);
                    objRememberedValue = objDerivedStateOf4;
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                State value$delegate2 = TransitionKt.createTransitionAnimation(transition, obj, objValueOf3, (FiniteAnimationSpec) function4.invoke(((State) objRememberedValue).getValue(), composer, Integer.valueOf((i2 >> 3) & 112)), vectorConverter, "mini-player-expansion-progress", composer, (i2 & 14) | (57344 & (i2 << 9)) | ((i2 << 6) & 458752));
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                fMeloXIOSMiniPlayer$lambda$6 = MeloXIOSMiniPlayer$lambda$6(value$delegate2);
                $composer3.endReplaceGroup();
            } else {
                f = 1.0f;
                $composer3.startReplaceGroup(317210152);
                $composer3.endReplaceGroup();
                fMeloXIOSMiniPlayer$lambda$6 = 0.0f;
            }
            float expansionProgress = fMeloXIOSMiniPlayer$lambda$6;
            float miniChromeAlpha = f - smoothStep(expansionProgress, 0.05f, 0.72f);
            final float miniSurfaceAlpha = f - smoothStep(expansionProgress, 0.04f, 0.42f);
            Modifier chromeOverlayModifier = sharedTransitionScope5 != null ? SharedTransitionScope.renderInSharedTransitionScopeOverlay$default(sharedTransitionScope5, Modifier.INSTANCE, 2.0f, null, 2, null) : Modifier.INSTANCE;
            if (sharedTransitionScope5 == null || animatedVisibilityScope4 == null) {
                $composer3.startReplaceGroup(318833498);
                $composer3.endReplaceGroup();
                sharedContainerModifier = Modifier.INSTANCE;
            } else {
                $composer3.startReplaceGroup(318306653);
                ComposerKt.sourceInformation($composer3, "*102@4296L120");
                sharedContainerModifier = SharedTransitionScope.sharedBounds$default(sharedTransitionScope5, Modifier.INSTANCE, sharedTransitionScope5.rememberSharedContentState(MeloXPlayerTransitionKeysKt.sharedPlayerContainerKey(meloXPlaybackUiState.getMediaId()), $composer3, 0), animatedVisibilityScope4, EnterTransition.INSTANCE.getNone(), ExitTransition.INSTANCE.getNone(), null, SharedTransitionScope.ResizeMode.INSTANCE.getRemeasureToBounds(), null, false, 0.0f, null, 976, null);
                $composer3.endReplaceGroup();
            }
            Modifier modifierM1806paddingVpY3zN4 = PaddingKt.m1806paddingVpY3zN4(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1301Dp.m8905constructorimpl(16), C1301Dp.m8905constructorimpl(3));
            String mediaId2 = meloXPlaybackUiState.getMediaId();
            ComposerKt.sourceInformationMarkerStart($composer3, 564481173, "CC(remember):MeloXIOSMiniPlayer.kt#9igjgp");
            boolean zChanged2 = $composer3.changed(accumulatedDrag$delegate) | (($dirty2 & 14) == 4);
            MeloXIOSMiniPlayerKt$MeloXIOSMiniPlayer$2$1 meloXIOSMiniPlayerKt$MeloXIOSMiniPlayer$2$1RememberedValue = $composer3.rememberedValue();
            if (zChanged2 || meloXIOSMiniPlayerKt$MeloXIOSMiniPlayer$2$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                meloXIOSMiniPlayerKt$MeloXIOSMiniPlayer$2$1RememberedValue = new MeloXIOSMiniPlayerKt$MeloXIOSMiniPlayer$2$1(accumulatedDrag$delegate, meloXPlaybackUiState);
                $composer3.updateRememberedValue(meloXIOSMiniPlayerKt$MeloXIOSMiniPlayer$2$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(modifierM1806paddingVpY3zN4, mediaId2, (PointerInputEventHandler) meloXIOSMiniPlayerKt$MeloXIOSMiniPlayer$2$1RememberedValue);
            ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierPointerInput);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i10 = ((((0 << 3) & 112) << 6) & 896) | 6;
            $composer2 = $composer3;
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
            int i11 = (i10 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i12 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 754217328, "C135@5585L21,151@6093L28,152@6139L206,147@5943L705,168@6658L3543:MeloXIOSMiniPlayer.kt#qhu5z0");
            RoundedCornerShape roundedCornerShapeM2135RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(25));
            boolean zIsSystemInDarkTheme = DarkThemeKt.isSystemInDarkTheme($composer3, 0);
            if (zIsSystemInDarkTheme) {
                long jM6094getBlack0d7_KjU = Color.INSTANCE.m6094getBlack0d7_KjU();
                jM6066copywmQWz5c = Color.m6066copywmQWz5c(jM6094getBlack0d7_KjU, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6094getBlack0d7_KjU) : 0.12f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6094getBlack0d7_KjU) : 0.0f);
            } else {
                long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
                jM6066copywmQWz5c = Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU) : 0.16f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU) : 0.0f);
            }
            if (zIsSystemInDarkTheme) {
                $composer3.startReplaceGroup(754343559);
                ComposerKt.sourceInformation($composer3, "142@5823L11");
                long surface = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getSurface();
                jM6066copywmQWz5c2 = Color.m6066copywmQWz5c(surface, (14 & 1) != 0 ? Color.m6070getAlphaimpl(surface) : 0.64f, (14 & 2) != 0 ? Color.m6074getRedimpl(surface) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(surface) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(surface) : 0.0f);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(754425213);
                $composer3.endReplaceGroup();
                long jM6105getWhite0d7_KjU2 = Color.INSTANCE.m6105getWhite0d7_KjU();
                jM6066copywmQWz5c2 = Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU2, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU2) : 0.66f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU2) : 0.0f);
            }
            long j = jM6066copywmQWz5c2;
            Modifier modifierM1858height3ABfNKs = SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(sharedContainerModifier, 0.0f, 1, null), C1301Dp.m8905constructorimpl(50));
            ComposerKt.sourceInformationMarkerStart($composer3, -114204182, "CC(remember):MeloXIOSMiniPlayer.kt#9igjgp");
            boolean zChanged3 = $composer3.changed(miniSurfaceAlpha);
            Object objRememberedValue6 = $composer3.rememberedValue();
            if (zChanged3 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue6 = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSMiniPlayerKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return MeloXIOSMiniPlayerKt.MeloXIOSMiniPlayer$lambda$10$0$0(miniSurfaceAlpha, (GraphicsLayerScope) obj2);
                    }
                };
                $composer3.updateRememberedValue(objRememberedValue6);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            SurfaceKt.m3769SurfaceT9BRK9s(MeloXBackdropComponentsKt.m9632meloXLiquidBottomBar9z6LAg8(GraphicsLayerModifierKt.graphicsLayer(modifierM1858height3ABfNKs, (Function1) objRememberedValue6), roundedCornerShapeM2135RoundedCornerShape0680j_4, jM6066copywmQWz5c, Color.m6066copywmQWz5c(j, (14 & 1) != 0 ? Color.m6070getAlphaimpl(j) : Color.m6070getAlphaimpl(j) * 0.4f, (14 & 2) != 0 ? Color.m6074getRedimpl(j) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(j) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(j) : 0.0f), $composer3, 0), roundedCornerShapeM2135RoundedCornerShape0680j_4, Color.INSTANCE.m6103getTransparent0d7_KjU(), 0L, C1301Dp.m8905constructorimpl(0), dynamicGlassEnabled4 ? C1301Dp.m8905constructorimpl(2.0f * miniSurfaceAlpha) : C1301Dp.m8905constructorimpl(0), null, ComposableSingletons$MeloXIOSMiniPlayerKt.INSTANCE.getLambda$295594761$app(), $composer3, 14180736, 8);
            Modifier modifierM1808paddingqDBjuR0 = PaddingKt.m1808paddingqDBjuR0(SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1301Dp.m8905constructorimpl(50)), C1301Dp.m8905constructorimpl(7), C1301Dp.m8905constructorimpl(5), C1301Dp.m8905constructorimpl(7), C1301Dp.m8905constructorimpl(5));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.Horizontal horizontalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(C1301Dp.m8905constructorimpl(10));
            ComposerKt.sourceInformationMarkerStart($composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalM1497spacedBy0680j_4, centerVertically, $composer3, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer3, modifierM1808paddingqDBjuR0);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i13 = ((((432 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor2;
                $composer3.createNode(function0);
            } else {
                function0 = constructor2;
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl2 = Updater.m5188constructorimpl($composer3);
            Updater.m5196setimpl(composerM5188constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i14 = (i13 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i15 = ((432 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -1095000116, "C176@6986L2543,240@9700L22,237@9543L291:MeloXIOSMiniPlayer.kt#qhu5z0");
            Modifier modifierM1078clickableoSLSa3U$default = ClickableKt.m1078clickableoSLSa3U$default(RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null), false, null, null, null, onExpand, 15, null);
            Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
            Arrangement.Horizontal horizontalM1497spacedBy0680j_5 = Arrangement.INSTANCE.m1497spacedBy0680j_4(C1301Dp.m8905constructorimpl(10));
            ComposerKt.sourceInformationMarkerStart($composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(horizontalM1497spacedBy0680j_5, centerVertically2, $composer3, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap3 = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer3, modifierM1078clickableoSLSa3U$default);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i16 = ((((432 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function1 = constructor3;
                $composer3.createNode(function1);
            } else {
                function1 = constructor3;
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl3 = Updater.m5188constructorimpl($composer3);
            Updater.m5196setimpl(composerM5188constructorimpl3, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i17 = (i16 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i18 = ((432 >> 6) & 112) | 6;
            RowScope rowScope2 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -556172690, "C197@7933L262,204@8213L1302:MeloXIOSMiniPlayer.kt#qhu5z0");
            if (sharedTransitionScope5 == null || animatedVisibilityScope4 == null) {
                $composer3.startReplaceGroup(-555662028);
                $composer3.endReplaceGroup();
                companionSharedElement$default = Modifier.INSTANCE;
            } else {
                $composer3.startReplaceGroup(-556110815);
                ComposerKt.sourceInformation($composer3, "*187@7555L136");
                companionSharedElement$default = SharedTransitionScope.sharedElement$default(sharedTransitionScope5, Modifier.INSTANCE, sharedTransitionScope5.rememberSharedContentState(MeloXSharedTransitionsKt.sharedArtworkKey(meloXPlaybackUiState.getMediaId()), $composer3, 0), animatedVisibilityScope4, null, null, false, 0.0f, null, 124, null);
                $composer3.endReplaceGroup();
            }
            MeloXPlayerUiKt.Artwork(meloXPlaybackUiState.getArtworkUrl(), ClipKt.clip(SizeKt.m1872size3ABfNKs(companionSharedElement$default, inline4 ? C1301Dp.m8905constructorimpl(30) : C1301Dp.m8905constructorimpl(40)), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(inline4 ? C1301Dp.m8905constructorimpl(7) : C1301Dp.m8905constructorimpl(9))), $composer3, 0);
            Modifier modifierThen = RowScope.weight$default(rowScope2, Modifier.INSTANCE, 1.0f, false, 2, null).then(chromeOverlayModifier);
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap4 = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer3, modifierThen);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            int i19 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function2 = constructor4;
                $composer3.createNode(function2);
            } else {
                function2 = constructor4;
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl4 = Updater.m5188constructorimpl($composer3);
            Updater.m5196setimpl(composerM5188constructorimpl4, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            int i20 = (i19 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i21 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 1535536773, "C217@8788L11,209@8391L526:MeloXIOSMiniPlayer.kt#qhu5z0");
            String title = meloXPlaybackUiState.getTitle();
            if (StringsKt.isBlank(title)) {
                title = "正在播放";
            }
            int iM8816getEllipsisgIe3tQ8 = TextOverflow.INSTANCE.m8816getEllipsisgIe3tQ8();
            long sp = TextUnitKt.getSp(14);
            long sp2 = TextUnitKt.getSp(17);
            FontWeight semiBold = FontWeight.INSTANCE.getSemiBold();
            long onSurface = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnSurface();
            TextKt.m3912TextNvy7gAk(title, null, Color.m6066copywmQWz5c(onSurface, (14 & 1) != 0 ? Color.m6070getAlphaimpl(onSurface) : miniChromeAlpha, (14 & 2) != 0 ? Color.m6074getRedimpl(onSurface) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(onSurface) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(onSurface) : 0.0f), null, sp, null, semiBold, null, 0L, null, null, sp2, iM8816getEllipsisgIe3tQ8, false, 1, 0, null, null, $composer3, 1597440, 28080, 231338);
            if (inline4) {
                $composer3.startReplaceGroup(1536599638);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(1536099236);
                ComposerKt.sourceInformation($composer3, "229@9326L11,222@8977L498");
                String artist = meloXPlaybackUiState.getArtist();
                int iM8816getEllipsisgIe3tQ9 = TextOverflow.INSTANCE.m8816getEllipsisgIe3tQ8();
                long sp3 = TextUnitKt.getSp(12);
                long sp4 = TextUnitKt.getSp(15);
                long onSurface2 = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnSurface();
                TextKt.m3912TextNvy7gAk(artist, null, Color.m6066copywmQWz5c(onSurface2, (14 & 1) != 0 ? Color.m6070getAlphaimpl(onSurface2) : miniChromeAlpha * 0.54f, (14 & 2) != 0 ? Color.m6074getRedimpl(onSurface2) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(onSurface2) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(onSurface2) : 0.0f), null, sp3, null, null, null, 0L, null, null, sp4, iM8816getEllipsisgIe3tQ9, false, 1, 0, null, null, $composer3, 24576, 28080, 231402);
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
            MiniGlyph miniGlyph = meloXPlaybackUiState.isPlaying() ? MiniGlyph.Pause : MiniGlyph.Play;
            ComposerKt.sourceInformationMarkerStart($composer3, -589428248, "CC(remember):MeloXIOSMiniPlayer.kt#9igjgp");
            boolean z7 = ($dirty2 & 14) == 4;
            Object objRememberedValue7 = $composer3.rememberedValue();
            if (z7 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = (KFunction) new MeloXIOSMiniPlayerKt$MeloXIOSMiniPlayer$3$2$2$1(meloXPlaybackUiState);
                $composer3.updateRememberedValue(obj2);
                objRememberedValue7 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            MiniVectorButton(miniGlyph, true, (Function0) ((KFunction) objRememberedValue7), chromeOverlayModifier, miniChromeAlpha, $composer3, 48, 0);
            if (inline4) {
                $composer3.startReplaceGroup(-1091920112);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(-1092238203);
                ComposerKt.sourceInformation($composer3, "248@10042L11,245@9878L299");
                MiniGlyph miniGlyph2 = MiniGlyph.Forward;
                boolean z8 = meloXPlaybackUiState.getHasNext() || meloXPlaybackUiState.getRepeatMode() != 0;
                ComposerKt.sourceInformationMarkerStart($composer3, -589417315, "CC(remember):MeloXIOSMiniPlayer.kt#9igjgp");
                boolean z9 = ($dirty2 & 14) == 4;
                Object objRememberedValue8 = $composer3.rememberedValue();
                if (z9 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                    Object obj3 = (KFunction) new MeloXIOSMiniPlayerKt$MeloXIOSMiniPlayer$3$2$3$1(meloXPlaybackUiState);
                    $composer3.updateRememberedValue(obj3);
                    objRememberedValue8 = obj3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                MiniVectorButton(miniGlyph2, z8, (Function0) ((KFunction) objRememberedValue8), chromeOverlayModifier, miniChromeAlpha, $composer3, 6, 0);
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
            inline2 = inline4;
            dynamicGlassEnabled2 = dynamicGlassEnabled4;
            sharedTransitionScope3 = sharedTransitionScope5;
            animatedVisibilityScope3 = animatedVisibilityScope4;
        } else {
            meloXPlaybackUiState = state;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            inline2 = z;
            dynamicGlassEnabled2 = z2;
            sharedTransitionScope3 = sharedTransitionScope2;
            animatedVisibilityScope3 = animatedVisibilityScope2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup2 = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup2 != null) {
            final MeloXPlaybackUiState meloXPlaybackUiState2 = meloXPlaybackUiState;
            scopeUpdateScopeEndRestartGroup2.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXIOSMiniPlayerKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj4, Object obj5) {
                    return MeloXIOSMiniPlayerKt.MeloXIOSMiniPlayer$lambda$11(meloXPlaybackUiState2, onExpand, inline2, dynamicGlassEnabled2, sharedTransitionScope3, animatedVisibilityScope3, $changed, i, (Composer) obj4, ((Integer) obj5).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float MeloXIOSMiniPlayer$lambda$2(MutableFloatState $accumulatedDrag$delegate) {
        return $accumulatedDrag$delegate.getFloatValue();
    }

    private static final float MeloXIOSMiniPlayer$lambda$6(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    static final FiniteAnimationSpec MeloXIOSMiniPlayer$lambda$4(Transition.Segment animateFloat, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(animateFloat, "$this$animateFloat");
        $composer.startReplaceGroup(1437507126);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1437507126, $changed, -1, "com.lladlam.melox.ui.player.MeloXIOSMiniPlayer.<anonymous> (MeloXIOSMiniPlayer.kt:63)");
        }
        SpringSpec springSpecSpring = AnimationSpecKt.spring(0.9f, 320.0f, Float.valueOf(0.001f));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return springSpecSpring;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXIOSMiniPlayer$lambda$10$0$0(float $miniSurfaceAlpha, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setAlpha($miniSurfaceAlpha);
        return Unit.INSTANCE;
    }

    private static final void MiniVectorButton(final MiniGlyph kind, final boolean enabled, final Function0<Unit> function0, Modifier modifier, float visualAlpha, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function1;
        Modifier modifier2;
        float f;
        final Modifier modifier3;
        final float visualAlpha2;
        Function0<ComposeUiNode> function2;
        Composer $composer2 = $composer.startRestartGroup(-196563984);
        ComposerKt.sourceInformation($composer2, "C(MiniVectorButton)N(kind,enabled,onClick,modifier,visualAlpha)268@10524L11,271@10620L2434:MeloXIOSMiniPlayer.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(kind.ordinal()) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(enabled) ? 32 : 16;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            function1 = function0;
            $dirty |= $composer2.changedInstance(function1) ? 256 : 128;
        } else {
            function1 = function0;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 16;
        if (i3 != 0) {
            $dirty |= 24576;
            f = visualAlpha;
        } else if (($changed & 24576) == 0) {
            f = visualAlpha;
            $dirty |= $composer2.changed(f) ? 16384 : 8192;
        } else {
            f = visualAlpha;
        }
        if ($composer2.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            float visualAlpha3 = i3 != 0 ? 1.0f : f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-196563984, $dirty, -1, "com.lladlam.melox.ui.player.MiniVectorButton (MeloXIOSMiniPlayer.kt:266)");
            }
            float baseAlpha = enabled ? 0.94f : 0.26f;
            long onSurface = MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getOnSurface();
            final long color = Color.m6066copywmQWz5c(onSurface, (14 & 1) != 0 ? Color.m6070getAlphaimpl(onSurface) : baseAlpha * RangesKt.coerceIn(visualAlpha3, 0.0f, 1.0f), (14 & 2) != 0 ? Color.m6074getRedimpl(onSurface) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(onSurface) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(onSurface) : 0.0f);
            Modifier modifierM1078clickableoSLSa3U$default = ClickableKt.m1078clickableoSLSa3U$default(ClipKt.clip(SizeKt.m1872size3ABfNKs(modifier4, C1301Dp.m8905constructorimpl(36)), RoundedCornerShapeKt.getCircleShape()), enabled && visualAlpha3 > 0.05f, null, null, null, function1, 14, null);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            int $dirty2 = $dirty;
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierM1078clickableoSLSa3U$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((48 << 3) & 112) << 6) & 896) | 6;
            Modifier modifier5 = modifier4;
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
            ComposerKt.sourceInformationMarkerStart($composer2, 462625387, "C281@10973L2075,281@10902L2146:MeloXIOSMiniPlayer.kt#qhu5z0");
            Modifier modifierM1872size3ABfNKs = SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, kind == MiniGlyph.Forward ? C1301Dp.m8905constructorimpl(25) : C1301Dp.m8905constructorimpl(23));
            ComposerKt.sourceInformationMarkerStart($composer2, 569114929, "CC(remember):MeloXIOSMiniPlayer.kt#9igjgp");
            boolean zChanged = (($dirty2 & 14) == 4) | $composer2.changed(color);
            Object objRememberedValue = $composer2.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSMiniPlayerKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MeloXIOSMiniPlayerKt.MiniVectorButton$lambda$0$0$0(kind, color, (DrawScope) obj);
                    }
                };
                $composer2.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifierM1872size3ABfNKs, (Function1) objRememberedValue, $composer2, 0);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            visualAlpha2 = visualAlpha3;
            modifier3 = modifier5;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            visualAlpha2 = f;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXIOSMiniPlayerKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXIOSMiniPlayerKt.MiniVectorButton$lambda$1(kind, enabled, function0, modifier3, visualAlpha2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MiniVectorButton$lambda$0$0$0(MiniGlyph $kind, long $color, DrawScope Canvas) {
        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
        switch (WhenMappings.$EnumSwitchMapping$0[$kind.ordinal()]) {
            case 1:
                Path path = AndroidPath_androidKt.Path();
                path.moveTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.24f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.14f);
                path.lineTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.82f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.5f);
                path.lineTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.24f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.86f);
                path.close();
                DrawScope.m6632drawPathLG529CI$default(Canvas, path, $color, 0.0f, null, null, 0, 60, null);
                break;
            case 2:
                float fIntBitsToFloat = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.24f;
                float fIntBitsToFloat2 = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.14f;
                long jM5815constructorimpl = Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat2)) & 4294967295L));
                float fIntBitsToFloat3 = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.17f;
                float fIntBitsToFloat4 = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.72f;
                long jM5883constructorimpl = Size.m5883constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat3)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat4)) & 4294967295L));
                float fIntBitsToFloat5 = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.035f;
                DrawScope.m6638drawRoundRectuAw5IA$default(Canvas, $color, jM5815constructorimpl, jM5883constructorimpl, CornerRadius.m5777constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat5)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat5)) & 4294967295L)), null, 0.0f, null, 0, PsExtractor.VIDEO_STREAM_MASK, null);
                float fIntBitsToFloat6 = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.59f;
                float fIntBitsToFloat7 = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.14f;
                long jM5815constructorimpl2 = Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat6)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat7)) & 4294967295L));
                float fIntBitsToFloat8 = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.17f;
                float fIntBitsToFloat9 = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.72f;
                long jM5883constructorimpl2 = Size.m5883constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat8)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat9)) & 4294967295L));
                float fIntBitsToFloat10 = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.035f;
                DrawScope.m6638drawRoundRectuAw5IA$default(Canvas, $color, jM5815constructorimpl2, jM5883constructorimpl2, CornerRadius.m5777constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat10)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat10)) & 4294967295L)), null, 0.0f, null, 0, PsExtractor.VIDEO_STREAM_MASK, null);
                break;
            case 3:
                Path Path = AndroidPath_androidKt.Path();
                Path.moveTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.06f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.16f);
                Path.lineTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.49f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.5f);
                Path.lineTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.06f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.84f);
                Path.close();
                Path Path2 = AndroidPath_androidKt.Path();
                Path2.moveTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.45f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.16f);
                Path2.lineTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.88f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.5f);
                Path2.lineTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.45f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.84f);
                Path2.close();
                DrawScope.m6632drawPathLG529CI$default(Canvas, Path, $color, 0.0f, null, null, 0, 60, null);
                DrawScope.m6632drawPathLG529CI$default(Canvas, Path2, $color, 0.0f, null, null, 0, 60, null);
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
}
