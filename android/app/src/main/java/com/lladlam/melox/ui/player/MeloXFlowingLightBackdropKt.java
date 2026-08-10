package com.lladlam.melox.ui.player;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.TileMode;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.media3.exoplayer.RendererCapabilities;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: compiled from: MeloXFlowingLightBackdrop.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u001a)\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0001¢\u0006\u0002\u0010\f\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r²\u0006\n\u0010\u000e\u001a\u00020\u000fX\u008a\u008e\u0002²\u0006\n\u0010\u0010\u001a\u00020\u0011X\u008a\u008e\u0002²\u0006\n\u0010\u0012\u001a\u00020\u0013X\u008a\u0084\u0002²\u0006\n\u0010\u0014\u001a\u00020\u0013X\u008a\u0084\u0002²\u0006\n\u0010\u0015\u001a\u00020\u0013X\u008a\u0084\u0002²\u0006\n\u0010\u0016\u001a\u00020\u0013X\u008a\u0084\u0002²\u0006\n\u0010\u0017\u001a\u00020\u0013X\u008a\u0084\u0002²\u0006\n\u0010\u0018\u001a\u00020\u0013X\u008a\u0084\u0002²\u0006\n\u0010\u0019\u001a\u00020\u0013X\u008a\u0084\u0002²\u0006\n\u0010\u001a\u001a\u00020\u0013X\u008a\u0084\u0002²\u0006\n\u0010\u001b\u001a\u00020\u0013X\u008a\u0084\u0002²\u0006\n\u0010\u001c\u001a\u00020\u0013X\u008a\u0084\u0002"}, d2 = {"PALETTE_TRANSITION_MS", "", "FLOW_FRAME_MS", "", "MeloXFlowingLightBackdrop", "", "artworkUrl", "", "isPlaying", "", "modifier", "Landroidx/compose/ui/Modifier;", "(Ljava/lang/String;ZLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "app", "targetPalette", "Lcom/lladlam/melox/ui/player/ArtworkDynamicPalette;", "phase", "", "c0", "Landroidx/compose/ui/graphics/Color;", "c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "average"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class MeloXFlowingLightBackdropKt {
    private static final long FLOW_FRAME_MS = 50;
    private static final int PALETTE_TRANSITION_MS = 800;

    static final Unit MeloXFlowingLightBackdrop$lambda$28(String str, boolean z, Modifier modifier, int i, int i2, Composer composer, int i3) {
        MeloXFlowingLightBackdrop(str, z, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void MeloXFlowingLightBackdrop(final String artworkUrl, final boolean isPlaying, Modifier modifier, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        final Modifier.Companion modifier3;
        Composer $composer2 = $composer.startRestartGroup(1853261072);
        ComposerKt.sourceInformation($composer2, "C(MeloXFlowingLightBackdrop)N(artworkUrl,isPlaying,modifier)37@1435L59,38@1512L36,40@1581L84,40@1554L111,44@1709L142,44@1671L180,51@1867L128,52@2010L128,53@2153L128,54@2296L128,55@2439L128,56@2582L128,57@2725L128,58@2868L128,59@3011L128,60@3159L96,63@3363L1519,63@3321L1561:MeloXFlowingLightBackdrop.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(artworkUrl) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(isPlaying) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= RendererCapabilities.DECODER_SUPPORT_MASK;
            modifier2 = modifier;
        } else if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int $dirty2 = $dirty;
        if ($composer2.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1853261072, $dirty2, -1, "com.lladlam.melox.ui.player.MeloXFlowingLightBackdrop (MeloXFlowingLightBackdrop.kt:36)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -1427532981, "CC(remember):MeloXFlowingLightBackdrop.kt#9igjgp");
            Object objRememberedValue = $composer2.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(ArtworkDynamicPalette.INSTANCE.getFallback(), null, 2, null);
                $composer2.updateRememberedValue(objMutableStateOf$default);
                objRememberedValue = objMutableStateOf$default;
            }
            MutableState targetPalette$delegate = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -1427530540, "CC(remember):MeloXFlowingLightBackdrop.kt#9igjgp");
            Object objRememberedValue2 = $composer2.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object objMutableFloatStateOf = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
                $composer2.updateRememberedValue(objMutableFloatStateOf);
                objRememberedValue2 = objMutableFloatStateOf;
            }
            final MutableFloatState phase$delegate = (MutableFloatState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -1427528284, "CC(remember):MeloXFlowingLightBackdrop.kt#9igjgp");
            boolean z = ($dirty2 & 14) == 4;
            Object objRememberedValue3 = $composer2.rememberedValue();
            if (z || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object obj = (Function2) new MeloXFlowingLightBackdropKt$MeloXFlowingLightBackdrop$1$1(artworkUrl, targetPalette$delegate, null);
                $composer2.updateRememberedValue(obj);
                objRememberedValue3 = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(artworkUrl, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue3, $composer2, $dirty2 & 14);
            Boolean boolValueOf = Boolean.valueOf(isPlaying);
            ComposerKt.sourceInformationMarkerStart($composer2, -1427524130, "CC(remember):MeloXFlowingLightBackdrop.kt#9igjgp");
            boolean z2 = ($dirty2 & 112) == 32;
            Object objRememberedValue4 = $composer2.rememberedValue();
            if (z2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = (Function2) new MeloXFlowingLightBackdropKt$MeloXFlowingLightBackdrop$2$1(isPlaying, phase$delegate, null);
                $composer2.updateRememberedValue(obj2);
                objRememberedValue4 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(boolValueOf, artworkUrl, (Function2) objRememberedValue4, $composer2, (($dirty2 >> 3) & 14) | (($dirty2 << 3) & 112));
            List<Color> cells = MeloXFlowingLightBackdrop$lambda$1(targetPalette$delegate).getCells();
            State<Color> stateM913animateColorAsStateeuL9pac = SingleValueAnimationKt.m913animateColorAsStateeuL9pac((cells.size() > 0 ? cells.get(0) : Color.m6058boximpl(MeloXFlowingLightBackdrop$lambda$1(targetPalette$delegate).m9671getAverage0d7_KjU())).m6078unboximpl(), AnimationSpecKt.tween$default(PALETTE_TRANSITION_MS, 0, null, 6, null), "flow-c0", null, $composer2, 432, 8);
            List<Color> cells2 = MeloXFlowingLightBackdrop$lambda$1(targetPalette$delegate).getCells();
            State<Color> stateM913animateColorAsStateeuL9pac2 = SingleValueAnimationKt.m913animateColorAsStateeuL9pac((1 < cells2.size() ? cells2.get(1) : Color.m6058boximpl(MeloXFlowingLightBackdrop$lambda$1(targetPalette$delegate).m9671getAverage0d7_KjU())).m6078unboximpl(), AnimationSpecKt.tween$default(PALETTE_TRANSITION_MS, 0, null, 6, null), "flow-c1", null, $composer2, 432, 8);
            List<Color> cells3 = MeloXFlowingLightBackdrop$lambda$1(targetPalette$delegate).getCells();
            State<Color> stateM913animateColorAsStateeuL9pac3 = SingleValueAnimationKt.m913animateColorAsStateeuL9pac((2 < cells3.size() ? cells3.get(2) : Color.m6058boximpl(MeloXFlowingLightBackdrop$lambda$1(targetPalette$delegate).m9671getAverage0d7_KjU())).m6078unboximpl(), AnimationSpecKt.tween$default(PALETTE_TRANSITION_MS, 0, null, 6, null), "flow-c2", null, $composer2, 432, 8);
            List<Color> cells4 = MeloXFlowingLightBackdrop$lambda$1(targetPalette$delegate).getCells();
            State<Color> stateM913animateColorAsStateeuL9pac4 = SingleValueAnimationKt.m913animateColorAsStateeuL9pac((3 < cells4.size() ? cells4.get(3) : Color.m6058boximpl(MeloXFlowingLightBackdrop$lambda$1(targetPalette$delegate).m9671getAverage0d7_KjU())).m6078unboximpl(), AnimationSpecKt.tween$default(PALETTE_TRANSITION_MS, 0, null, 6, null), "flow-c3", null, $composer2, 432, 8);
            List<Color> cells5 = MeloXFlowingLightBackdrop$lambda$1(targetPalette$delegate).getCells();
            State<Color> stateM913animateColorAsStateeuL9pac5 = SingleValueAnimationKt.m913animateColorAsStateeuL9pac((4 < cells5.size() ? cells5.get(4) : Color.m6058boximpl(MeloXFlowingLightBackdrop$lambda$1(targetPalette$delegate).m9671getAverage0d7_KjU())).m6078unboximpl(), AnimationSpecKt.tween$default(PALETTE_TRANSITION_MS, 0, null, 6, null), "flow-c4", null, $composer2, 432, 8);
            List<Color> cells6 = MeloXFlowingLightBackdrop$lambda$1(targetPalette$delegate).getCells();
            State<Color> stateM913animateColorAsStateeuL9pac6 = SingleValueAnimationKt.m913animateColorAsStateeuL9pac((5 < cells6.size() ? cells6.get(5) : Color.m6058boximpl(MeloXFlowingLightBackdrop$lambda$1(targetPalette$delegate).m9671getAverage0d7_KjU())).m6078unboximpl(), AnimationSpecKt.tween$default(PALETTE_TRANSITION_MS, 0, null, 6, null), "flow-c5", null, $composer2, 432, 8);
            List<Color> cells7 = MeloXFlowingLightBackdrop$lambda$1(targetPalette$delegate).getCells();
            State<Color> stateM913animateColorAsStateeuL9pac7 = SingleValueAnimationKt.m913animateColorAsStateeuL9pac((6 < cells7.size() ? cells7.get(6) : Color.m6058boximpl(MeloXFlowingLightBackdrop$lambda$1(targetPalette$delegate).m9671getAverage0d7_KjU())).m6078unboximpl(), AnimationSpecKt.tween$default(PALETTE_TRANSITION_MS, 0, null, 6, null), "flow-c6", null, $composer2, 432, 8);
            List<Color> cells8 = MeloXFlowingLightBackdrop$lambda$1(targetPalette$delegate).getCells();
            State<Color> stateM913animateColorAsStateeuL9pac8 = SingleValueAnimationKt.m913animateColorAsStateeuL9pac((7 < cells8.size() ? cells8.get(7) : Color.m6058boximpl(MeloXFlowingLightBackdrop$lambda$1(targetPalette$delegate).m9671getAverage0d7_KjU())).m6078unboximpl(), AnimationSpecKt.tween$default(PALETTE_TRANSITION_MS, 0, null, 6, null), "flow-c7", null, $composer2, 432, 8);
            List<Color> cells9 = MeloXFlowingLightBackdrop$lambda$1(targetPalette$delegate).getCells();
            State<Color> stateM913animateColorAsStateeuL9pac9 = SingleValueAnimationKt.m913animateColorAsStateeuL9pac((8 < cells9.size() ? cells9.get(8) : Color.m6058boximpl(MeloXFlowingLightBackdrop$lambda$1(targetPalette$delegate).m9671getAverage0d7_KjU())).m6078unboximpl(), AnimationSpecKt.tween$default(PALETTE_TRANSITION_MS, 0, null, 6, null), "flow-c8", null, $composer2, 432, 8);
            final State<Color> stateM913animateColorAsStateeuL9pac10 = SingleValueAnimationKt.m913animateColorAsStateeuL9pac(MeloXFlowingLightBackdrop$lambda$1(targetPalette$delegate).m9671getAverage0d7_KjU(), AnimationSpecKt.tween$default(PALETTE_TRANSITION_MS, 0, null, 6, null), "flow-average", null, $composer2, 432, 8);
            final List colors = CollectionsKt.listOf((Object[]) new Color[]{Color.m6058boximpl(MeloXFlowingLightBackdrop$lambda$9(stateM913animateColorAsStateeuL9pac)), Color.m6058boximpl(MeloXFlowingLightBackdrop$lambda$11(stateM913animateColorAsStateeuL9pac2)), Color.m6058boximpl(MeloXFlowingLightBackdrop$lambda$13(stateM913animateColorAsStateeuL9pac3)), Color.m6058boximpl(MeloXFlowingLightBackdrop$lambda$15(stateM913animateColorAsStateeuL9pac4)), Color.m6058boximpl(MeloXFlowingLightBackdrop$lambda$17(stateM913animateColorAsStateeuL9pac5)), Color.m6058boximpl(MeloXFlowingLightBackdrop$lambda$19(stateM913animateColorAsStateeuL9pac6)), Color.m6058boximpl(MeloXFlowingLightBackdrop$lambda$21(stateM913animateColorAsStateeuL9pac7)), Color.m6058boximpl(MeloXFlowingLightBackdrop$lambda$23(stateM913animateColorAsStateeuL9pac8)), Color.m6058boximpl(MeloXFlowingLightBackdrop$lambda$25(stateM913animateColorAsStateeuL9pac9))});
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(modifier3, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer2, -1427469825, "CC(remember):MeloXFlowingLightBackdrop.kt#9igjgp");
            boolean zChanged = $composer2.changed(stateM913animateColorAsStateeuL9pac10) | $composer2.changed(colors);
            Object objRememberedValue5 = $composer2.rememberedValue();
            if (zChanged || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                Object obj3 = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXFlowingLightBackdropKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        return MeloXFlowingLightBackdropKt.MeloXFlowingLightBackdrop$lambda$27$0(colors, stateM913animateColorAsStateeuL9pac10, phase$delegate, (DrawScope) obj4);
                    }
                };
                $composer2.updateRememberedValue(obj3);
                objRememberedValue5 = obj3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifierFillMaxSize$default, (Function1) objRememberedValue5, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXFlowingLightBackdropKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj4, Object obj5) {
                    return MeloXFlowingLightBackdropKt.MeloXFlowingLightBackdrop$lambda$28(artworkUrl, isPlaying, modifier3, $changed, i, (Composer) obj4, ((Integer) obj5).intValue());
                }
            });
        }
    }

    private static final ArtworkDynamicPalette MeloXFlowingLightBackdrop$lambda$1(MutableState<ArtworkDynamicPalette> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float MeloXFlowingLightBackdrop$lambda$4(MutableFloatState $phase$delegate) {
        return $phase$delegate.getFloatValue();
    }

    private static final long MeloXFlowingLightBackdrop$lambda$9(State<Color> state) {
        return ((Color) state.getValue()).m6078unboximpl();
    }

    private static final long MeloXFlowingLightBackdrop$lambda$11(State<Color> state) {
        return ((Color) state.getValue()).m6078unboximpl();
    }

    private static final long MeloXFlowingLightBackdrop$lambda$13(State<Color> state) {
        return ((Color) state.getValue()).m6078unboximpl();
    }

    private static final long MeloXFlowingLightBackdrop$lambda$15(State<Color> state) {
        return ((Color) state.getValue()).m6078unboximpl();
    }

    private static final long MeloXFlowingLightBackdrop$lambda$17(State<Color> state) {
        return ((Color) state.getValue()).m6078unboximpl();
    }

    private static final long MeloXFlowingLightBackdrop$lambda$19(State<Color> state) {
        return ((Color) state.getValue()).m6078unboximpl();
    }

    private static final long MeloXFlowingLightBackdrop$lambda$21(State<Color> state) {
        return ((Color) state.getValue()).m6078unboximpl();
    }

    private static final long MeloXFlowingLightBackdrop$lambda$23(State<Color> state) {
        return ((Color) state.getValue()).m6078unboximpl();
    }

    private static final long MeloXFlowingLightBackdrop$lambda$25(State<Color> state) {
        return ((Color) state.getValue()).m6078unboximpl();
    }

    private static final long MeloXFlowingLightBackdrop$lambda$26(State<Color> state) {
        return ((Color) state.getValue()).m6078unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXFlowingLightBackdrop$lambda$27$0(List $colors, State $average$delegate, MutableFloatState $phase$delegate, DrawScope Canvas) {
        float f;
        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
        DrawScope.m6636drawRectnJ9OG0$default(Canvas, MeloXFlowingLightBackdrop$lambda$26($average$delegate), 0L, 0L, 0.0f, null, null, 0, WebSocketProtocol.PAYLOAD_SHORT, null);
        char c = ' ';
        long j = 4294967295L;
        float radius = Math.max(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)), Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)));
        float radius2 = radius * 0.62f;
        int i = 0;
        for (Object obj : $colors) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            long jM6078unboximpl = ((Color) obj).m6078unboximpl();
            int i3 = i / 3;
            float f2 = 0.5f;
            switch (i % 3) {
                case 0:
                    f = 0.08f;
                    break;
                case 1:
                    f = 0.5f;
                    break;
                default:
                    f = 0.92f;
                    break;
            }
            float f3 = f;
            switch (i3) {
                case 0:
                    f2 = 0.1f;
                    break;
                case 1:
                    break;
                default:
                    f2 = 0.9f;
                    break;
            }
            float fMeloXFlowingLightBackdrop$lambda$4 = MeloXFlowingLightBackdrop$lambda$4($phase$delegate) + (i * 0.71f);
            float radius3 = radius2;
            char c2 = c;
            long j2 = j;
            float maxDimension = radius;
            DrawScope.m6635drawRectAsUm42w$default(Canvas, Brush.INSTANCE.m6030radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m6058boximpl(Color.m6066copywmQWz5c(jM6078unboximpl, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6078unboximpl) : 0.82f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6078unboximpl) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6078unboximpl) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6078unboximpl) : 0.0f)), Color.m6058boximpl(Color.m6066copywmQWz5c(jM6078unboximpl, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6078unboximpl) : 0.34f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6078unboximpl) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6078unboximpl) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6078unboximpl) : 0.0f)), Color.m6058boximpl(Color.INSTANCE.m6103getTransparent0d7_KjU())}), (8 & 2) != 0 ? Offset.INSTANCE.m5838getUnspecifiedF1C5BW0() : Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> c)) * (f3 + (((float) Math.sin(fMeloXFlowingLightBackdrop$lambda$4)) * 0.075f)))) << c2) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & j)) * (f2 + (((float) Math.cos(0.83f * fMeloXFlowingLightBackdrop$lambda$4)) * 0.065f)))) & j2)), (8 & 4) != 0 ? Float.POSITIVE_INFINITY : radius3, (8 & 8) != 0 ? TileMode.INSTANCE.m6463getClamp3opZhB0() : 0), 0L, 0L, 0.0f, null, null, 0, WebSocketProtocol.PAYLOAD_SHORT, null);
            radius2 = radius3;
            radius = maxDimension;
            i = i2;
            c = c2;
            j = j2;
        }
        Brush.Companion companion = Brush.INSTANCE;
        Float fValueOf = Float.valueOf(0.0f);
        long jM6094getBlack0d7_KjU = Color.INSTANCE.m6094getBlack0d7_KjU();
        Float fValueOf2 = Float.valueOf(0.52f);
        long jM6094getBlack0d7_KjU2 = Color.INSTANCE.m6094getBlack0d7_KjU();
        Float fValueOf3 = Float.valueOf(1.0f);
        long jM6094getBlack0d7_KjU3 = Color.INSTANCE.m6094getBlack0d7_KjU();
        DrawScope.m6635drawRectAsUm42w$default(Canvas, Brush.Companion.m6024verticalGradient8A3gB4$default(companion, new Pair[]{TuplesKt.m717to(fValueOf, Color.m6058boximpl(Color.m6066copywmQWz5c(jM6094getBlack0d7_KjU, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6094getBlack0d7_KjU) : 0.04f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6094getBlack0d7_KjU) : 0.0f))), TuplesKt.m717to(fValueOf2, Color.m6058boximpl(Color.m6066copywmQWz5c(jM6094getBlack0d7_KjU2, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6094getBlack0d7_KjU2) : 0.1f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6094getBlack0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6094getBlack0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6094getBlack0d7_KjU2) : 0.0f))), TuplesKt.m717to(fValueOf3, Color.m6058boximpl(Color.m6066copywmQWz5c(jM6094getBlack0d7_KjU3, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6094getBlack0d7_KjU3) : 0.48f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6094getBlack0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6094getBlack0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6094getBlack0d7_KjU3) : 0.0f)))}, 0.0f, 0.0f, 0, 14, (Object) null), 0L, 0L, 0.0f, null, null, 0, WebSocketProtocol.PAYLOAD_SHORT, null);
        return Unit.INSTANCE;
    }
}
