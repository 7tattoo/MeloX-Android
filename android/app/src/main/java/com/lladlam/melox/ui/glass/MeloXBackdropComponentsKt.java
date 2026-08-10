package com.lladlam.melox.p012ui.glass;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.style.ResolvedStyleKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.profileinstaller.ProfileVerifier;
import com.kyant.backdrop.Backdrop;
import com.kyant.backdrop.BackdropEffectScope;
import com.kyant.backdrop.DrawBackdropModifierKt;
import com.kyant.backdrop.effects.BlurKt;
import com.kyant.backdrop.effects.ColorFilterKt;
import com.kyant.backdrop.highlight.Highlight;
import com.kyant.backdrop.shadow.Shadow;
import com.kyant.shapes.Capsule;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: compiled from: MeloXBackdropComponents.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u001aW\u0010\u0005\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u0012\u0010\u0013\u001a+\u0010\u0014\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0007¢\u0006\u0004\b\u0015\u0010\u0016\u001a+\u0010\u0017\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007¢\u0006\u0004\b\u0019\u0010\u001a\"\u0019\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u001b"}, d2 = {"LocalMeloXBackdrop", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Lcom/kyant/backdrop/Backdrop;", "getLocalMeloXBackdrop", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "meloXLiquidButton", "Landroidx/compose/ui/Modifier;", "shape", "Landroidx/compose/ui/graphics/Shape;", "enabled", "", "tint", "Landroidx/compose/ui/graphics/Color;", "surfaceColor", "blurRadius", "Landroidx/compose/ui/unit/Dp;", "lensRadius", "refractionHeight", "meloXLiquidButton-NsDo4u0", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;ZJJFFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/ui/Modifier;", "meloXLiquidBottomBar", "meloXLiquidBottomBar-9z6LAg8", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;", "meloXLiquidTabSelection", "selected", "meloXLiquidTabSelection-Bx497Mc", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;ZJLandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;", "app"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class MeloXBackdropComponentsKt {
    private static final ProvidableCompositionLocal<Backdrop> LocalMeloXBackdrop = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$$ExternalSyntheticLambda10
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return MeloXBackdropComponentsKt.LocalMeloXBackdrop$lambda$0();
        }
    });

    static final Backdrop LocalMeloXBackdrop$lambda$0() {
        return null;
    }

    public static final ProvidableCompositionLocal<Backdrop> getLocalMeloXBackdrop() {
        return LocalMeloXBackdrop;
    }

    /* JADX WARN: Code duplicated, block: B:109:0x032a  */
    /* JADX WARN: Code duplicated, block: B:112:0x037a  */
    /* JADX WARN: Code duplicated, block: B:115:0x0383  */
    /* JADX WARN: Code duplicated, block: B:118:0x0389 A[PHI: r4
      0x0389: PHI (r4v12 'enabled' boolean) = (r4v10 'enabled' boolean), (r4v13 'enabled' boolean) binds: [B:117:0x0387, B:113:0x0380] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:121:0x03a4  */
    /* JADX WARN: Code duplicated, block: B:125:0x03ae  */
    /* JADX WARN: Code duplicated, block: B:128:0x03cb  */
    /* JADX INFO: renamed from: meloXLiquidButton-NsDo4u0, reason: not valid java name */
    public static final Modifier m9633meloXLiquidButtonNsDo4u0(Modifier meloXLiquidButton, Shape shape, boolean enabled, long tint, long surfaceColor, float blurRadius, float lensRadius, float refractionHeight, Composer $composer, int $changed, int i) {
        boolean enabled2;
        boolean zChangedInstance;
        Object objRememberedValue;
        boolean z;
        Object objRememberedValue2;
        final boolean enabled3;
        boolean zChangedInstance2;
        Object objRememberedValue3;
        long surfaceColor2;
        Intrinsics.checkNotNullParameter(meloXLiquidButton, "$this$meloXLiquidButton");
        Intrinsics.checkNotNullParameter(shape, "shape");
        $composer.startReplaceGroup(1958737740);
        ComposerKt.sourceInformation($composer, "C(meloXLiquidButton)N(shape,enabled,tint:c#ui.graphics.Color,surfaceColor:c#ui.graphics.Color,blurRadius:c#ui.unit.Dp,lensRadius:c#ui.unit.Dp,refractionHeight:c#ui.unit.Dp)53@2014L7,65@2514L24,66@2555L35,71@2684L13,72@2721L82,76@2829L89,79@2941L90,82@3061L290,90@3394L366:MeloXBackdropComponents.kt#hc6ke1");
        boolean enabled4 = (i & 2) != 0 ? true : enabled;
        final long tint2 = (i & 4) != 0 ? Color.INSTANCE.m6104getUnspecified0d7_KjU() : tint;
        long surfaceColor3 = (i & 8) != 0 ? Color.INSTANCE.m6104getUnspecified0d7_KjU() : surfaceColor;
        final float blurRadius2 = (i & 16) != 0 ? Dp.constructor_impl(2) : blurRadius;
        if ((i & 32) != 0) {
            Dp.constructor_impl(12);
        }
        if ((i & 64) != 0) {
            Dp.constructor_impl(24);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1958737740, $changed, -1, "com.lladlam.melox.ui.glass.meloXLiquidButton (MeloXBackdropComponents.kt:52)");
        }
        ProvidableCompositionLocal<Backdrop> providableCompositionLocal = LocalMeloXBackdrop;
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(providableCompositionLocal);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Backdrop backdrop = (Backdrop) objConsume;
        if (backdrop == null) {
            if (!Color.m6069equalsimpl0(surfaceColor3, Color.INSTANCE.m6104getUnspecified0d7_KjU())) {
                long surfaceColor4 = surfaceColor3;
                surfaceColor2 = Color.copy_wmQWz5c(surfaceColor4, (14 & 1) != 0 ? Color.getAlpha_impl(surfaceColor4) : Math.max(Color.getAlpha_impl(surfaceColor3), 0.46f), (14 & 2) != 0 ? Color.getRed_impl(surfaceColor4) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(surfaceColor4) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(surfaceColor4) : 0.0f);
            } else if (Color.m6069equalsimpl0(tint2, Color.INSTANCE.m6104getUnspecified0d7_KjU())) {
                long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
                surfaceColor2 = Color.copy_wmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU) : 0.46f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU) : 0.0f);
            } else {
                long tint3 = tint2;
                surfaceColor2 = Color.copy_wmQWz5c(tint3, (14 & 1) != 0 ? Color.getAlpha_impl(tint3) : Math.max(Color.getAlpha_impl(tint2), 0.42f), (14 & 2) != 0 ? Color.getRed_impl(tint3) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(tint3) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(tint3) : 0.0f);
            }
            Modifier modifierM1042backgroundbw27NRU = BackgroundKt.m1042backgroundbw27NRU(meloXLiquidButton, surfaceColor2, shape);
            float fM8905constructorimpl = Dp.constructor_impl((float) 0.75d);
            long jM6105getWhite0d7_KjU2 = Color.INSTANCE.m6105getWhite0d7_KjU();
            Modifier modifierM1054borderxT4_qwU = BorderKt.m1054borderxT4_qwU(modifierM1042backgroundbw27NRU, fM8905constructorimpl, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU2, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU2) : 0.62f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU2) : 0.0f), shape);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return modifierM1054borderxT4_qwU;
        }
        final long surfaceColor5 = surfaceColor3;
        ComposerKt.sourceInformationMarkerStart($composer, 773894976, "CC(rememberCoroutineScope)N(getContext)616@28039L68:Effects.kt#9igjgp");
        ComposerKt.sourceInformationMarkerStart($composer, 683736516, "CC(remember):Effects.kt#9igjgp");
        Object objRememberedValue4 = $composer.rememberedValue();
        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue4 = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer);
            $composer.updateRememberedValue(objRememberedValue4);
        }
        final CoroutineScope scope = (CoroutineScope) objRememberedValue4;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 1151319503, "CC(remember):MeloXBackdropComponents.kt#9igjgp");
        Object objRememberedValue5 = $composer.rememberedValue();
        if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
            Object objAnimatable = AnimatableKt.Animatable(0.0f, 0.001f);
            $composer.updateRememberedValue(objAnimatable);
            objRememberedValue5 = objAnimatable;
        }
        final Animatable press = (Animatable) objRememberedValue5;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 1151323609, "CC(remember):MeloXBackdropComponents.kt#9igjgp");
        Object objRememberedValue6 = $composer.rememberedValue();
        if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
            Object obj = new Function0() { // from class: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MeloXBackdropComponentsKt.meloXLiquidButton_NsDo4u0$lambda$1$0();
                }
            };
            $composer.updateRememberedValue(obj);
            objRememberedValue6 = obj;
        }
        Function0 function0 = (Function0) objRememberedValue6;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 1151324862, "CC(remember):MeloXBackdropComponents.kt#9igjgp");
        boolean z2 = (((458752 & $changed) ^ ProfileVerifier.CompilationStatus.f253xf2722a21) > 131072 && $composer.changed(blurRadius2)) || ($changed & ProfileVerifier.CompilationStatus.f253xf2722a21) == 131072;
        Object objRememberedValue7 = $composer.rememberedValue();
        if (z2 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
            Object obj2 = new Function1() { // from class: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    return MeloXBackdropComponentsKt.meloXLiquidButton_NsDo4u0$lambda$2$0(blurRadius2, (BackdropEffectScope) obj3);
                }
            };
            $composer.updateRememberedValue(obj2);
            objRememberedValue7 = obj2;
        }
        Function1 function1 = (Function1) objRememberedValue7;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 1151328325, "CC(remember):MeloXBackdropComponents.kt#9igjgp");
        boolean zChangedInstance3 = $composer.changedInstance(press);
        Object objRememberedValue8 = $composer.rememberedValue();
        if (!zChangedInstance3) {
            enabled2 = enabled4;
            if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
            }
            Function0 function2 = (Function0) objRememberedValue8;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, 1151331910, "CC(remember):MeloXBackdropComponents.kt#9igjgp");
            zChangedInstance = $composer.changedInstance(press);
            objRememberedValue = $composer.rememberedValue();
            if (!zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj3 = new Function0() { // from class: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MeloXBackdropComponentsKt.meloXLiquidButton_NsDo4u0$lambda$4$0(press);
                    }
                };
                $composer.updateRememberedValue(obj3);
                objRememberedValue = obj3;
            }
            Function0 function3 = (Function0) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, 1151335950, "CC(remember):MeloXBackdropComponents.kt#9igjgp");
            z = (((($changed & 7168) ^ 3072) <= 2048 && $composer.changed(tint2)) || ($changed & 3072) == 2048) | ((((57344 & $changed) ^ 24576) <= 16384 && $composer.changed(surfaceColor5)) || ($changed & 24576) == 16384);
            objRememberedValue2 = $composer.rememberedValue();
            if (z || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        return MeloXBackdropComponentsKt.meloXLiquidButton_NsDo4u0$lambda$5$0(tint2, surfaceColor5, (DrawScope) obj4);
                    }
                };
                $composer.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierDrawBackdrop = DrawBackdropModifierKt.drawBackdrop(meloXLiquidButton, backdrop, function0, function1, (3040 & 8) != 0 ? DrawBackdropModifierKt.DefaultHighlight : function2, (3040 & 16) != 0 ? DrawBackdropModifierKt.DefaultShadow : function3, (3040 & 32) != 0 ? null : null, (3040 & 64) != 0 ? null : null, (3040 & 128) != 0 ? null : null, (3040 & 256) != 0 ? null : null, (3040 & 512) != 0 ? DrawBackdropModifierKt.DefaultOnDrawBackdrop : null, (3040 & 1024) != 0 ? null : (Function1) objRememberedValue2, (3040 & 2048) != 0 ? null : null);
            Boolean boolValueOf = Boolean.valueOf(enabled2);
            ComposerKt.sourceInformationMarkerStart($composer, 1151346682, "CC(remember):MeloXBackdropComponents.kt#9igjgp");
            if ((($changed & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256) {
                enabled3 = enabled2;
                if (!$composer.changed(enabled3)) {
                }
                zChangedInstance2 = z | $composer.changedInstance(scope) | $composer.changedInstance(press);
                objRememberedValue3 = $composer.rememberedValue();
                if (zChangedInstance2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    Object obj4 = (PointerInputEventHandler) new PointerInputEventHandler() { // from class: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1

                        /* JADX INFO: renamed from: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1 */
                        /* JADX INFO: compiled from: MeloXBackdropComponents.kt */
                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
                        @DebugMetadata(c = "com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1", f = "MeloXBackdropComponents.kt", i = {0, 1}, l = {94, ResolvedStyleKt.InheritedFlags}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture"}, nl = {95, 97}, s = {"L$0", "L$0"}, v = 2)
                        static final class C26361 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                            final /* synthetic */ Animatable<Float, AnimationVector1D> $press;
                            final /* synthetic */ CoroutineScope $scope;
                            private /* synthetic */ Object L$0;
                            int label;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            C26361(CoroutineScope coroutineScope, Animatable<Float, AnimationVector1D> animatable, Continuation<? super C26361> continuation) {
                                super(2, continuation);
                                this.$scope = coroutineScope;
                                this.$press = animatable;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                C26361 c26361 = new C26361(this.$scope, this.$press, continuation);
                                c26361.L$0 = obj;
                                return c26361;
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                                return ((C26361) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            /* JADX WARN: Code duplicated, block: B:13:0x005c A[RETURN] */
                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object $result) {
                                AwaitPointerEventScope $this$awaitEachGesture = (AwaitPointerEventScope) this.L$0;
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch (this.label) {
                                    case 0:
                                        ResultKt.throwOnFailure($result);
                                        this.L$0 = $this$awaitEachGesture;
                                        this.label = 1;
                                        if (TapGestureDetectorKt.awaitFirstDown$default($this$awaitEachGesture, false, null, this, 2, null) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass1(this.$press, null), 3, null);
                                        this.L$0 = SpillingKt.nullOutSpilledVariable($this$awaitEachGesture);
                                        this.label = 2;
                                        if (TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture, null, this, 1, null) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass2(this.$press, null), 3, null);
                                        return Unit.INSTANCE;
                                    case 1:
                                        ResultKt.throwOnFailure($result);
                                        BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass1(this.$press, null), 3, null);
                                        this.L$0 = SpillingKt.nullOutSpilledVariable($this$awaitEachGesture);
                                        this.label = 2;
                                        if (TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture, null, this, 1, null) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass2(this.$press, null), 3, null);
                                        return Unit.INSTANCE;
                                    case 2:
                                        ResultKt.throwOnFailure($result);
                                        BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass2(this.$press, null), 3, null);
                                        return Unit.INSTANCE;
                                    default:
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                            }

                            /* JADX INFO: renamed from: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$1, reason: invalid class name */
                            /* JADX INFO: compiled from: MeloXBackdropComponents.kt */
                            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
                            @DebugMetadata(c = "com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$1", f = "MeloXBackdropComponents.kt", i = {}, l = {95}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
                            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                final /* synthetic */ Animatable<Float, AnimationVector1D> $press;
                                int label;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                AnonymousClass1(Animatable<Float, AnimationVector1D> animatable, Continuation<? super AnonymousClass1> continuation) {
                                    super(2, continuation);
                                    this.$press = animatable;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                    return new AnonymousClass1(this.$press, continuation);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object $result) {
                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    switch (this.label) {
                                        case 0:
                                            ResultKt.throwOnFailure($result);
                                            Animatable<Float, AnimationVector1D> animatable = this.$press;
                                            Float fBoxFloat = Boxing.boxFloat(1.0f);
                                            SpringSpec springSpecSpring = AnimationSpecKt.spring(0.55f, 420.0f, Boxing.boxFloat(0.001f));
                                            this.label = 1;
                                            if (animatable.animateTo(fBoxFloat, (4 & 2) != 0 ? animatable.defaultSpringSpec : springSpecSpring, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : null, this) == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                            break;
                                        case 1:
                                            ResultKt.throwOnFailure($result);
                                            break;
                                        default:
                                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                    }
                                    return Unit.INSTANCE;
                                }
                            }

                            /* JADX INFO: renamed from: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$2, reason: invalid class name */
                            /* JADX INFO: compiled from: MeloXBackdropComponents.kt */
                            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
                            @DebugMetadata(c = "com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$2", f = "MeloXBackdropComponents.kt", i = {}, l = {97}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
                            static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                final /* synthetic */ Animatable<Float, AnimationVector1D> $press;
                                int label;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                AnonymousClass2(Animatable<Float, AnimationVector1D> animatable, Continuation<? super AnonymousClass2> continuation) {
                                    super(2, continuation);
                                    this.$press = animatable;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                    return new AnonymousClass2(this.$press, continuation);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                    return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object $result) {
                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    switch (this.label) {
                                        case 0:
                                            ResultKt.throwOnFailure($result);
                                            Animatable<Float, AnimationVector1D> animatable = this.$press;
                                            Float fBoxFloat = Boxing.boxFloat(0.0f);
                                            SpringSpec springSpecSpring = AnimationSpecKt.spring(0.68f, 360.0f, Boxing.boxFloat(0.001f));
                                            this.label = 1;
                                            if (animatable.animateTo(fBoxFloat, (4 & 2) != 0 ? animatable.defaultSpringSpec : springSpecSpring, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : null, this) == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                            break;
                                        case 1:
                                            ResultKt.throwOnFailure($result);
                                            break;
                                        default:
                                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                    }
                                    return Unit.INSTANCE;
                                }
                            }
                        }

                        @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                        public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
                            Object objAwaitEachGesture;
                            return (enabled3 && (objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$pointerInput, new C26361(scope, press, null), continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objAwaitEachGesture : Unit.INSTANCE;
                        }
                    };
                    $composer.updateRememberedValue(obj4);
                    objRememberedValue3 = obj4;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(modifierDrawBackdrop, boolValueOf, (PointerInputEventHandler) objRememberedValue3);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                return modifierPointerInput;
            }
            enabled3 = enabled2;
            boolean z3 = ($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 256;
            zChangedInstance2 = z3 | $composer.changedInstance(scope) | $composer.changedInstance(press);
            objRememberedValue3 = $composer.rememberedValue();
            if (zChangedInstance2) {
                Object obj5 = (PointerInputEventHandler) new PointerInputEventHandler() { // from class: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1

                    /* JADX INFO: renamed from: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1 */
                    /* JADX INFO: compiled from: MeloXBackdropComponents.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
                    @DebugMetadata(c = "com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1", f = "MeloXBackdropComponents.kt", i = {0, 1}, l = {94, ResolvedStyleKt.InheritedFlags}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture"}, nl = {95, 97}, s = {"L$0", "L$0"}, v = 2)
                    static final class C26361 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ Animatable<Float, AnimationVector1D> $press;
                        final /* synthetic */ CoroutineScope $scope;
                        private /* synthetic */ Object L$0;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C26361(CoroutineScope coroutineScope, Animatable<Float, AnimationVector1D> animatable, Continuation<? super C26361> continuation) {
                            super(2, continuation);
                            this.$scope = coroutineScope;
                            this.$press = animatable;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            C26361 c26361 = new C26361(this.$scope, this.$press, continuation);
                            c26361.L$0 = obj;
                            return c26361;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                            return ((C26361) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        /* JADX WARN: Code duplicated, block: B:13:0x005c A[RETURN] */
                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            AwaitPointerEventScope $this$awaitEachGesture = (AwaitPointerEventScope) this.L$0;
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    this.L$0 = $this$awaitEachGesture;
                                    this.label = 1;
                                    if (TapGestureDetectorKt.awaitFirstDown$default($this$awaitEachGesture, false, null, this, 2, null) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass1(this.$press, null), 3, null);
                                    this.L$0 = SpillingKt.nullOutSpilledVariable($this$awaitEachGesture);
                                    this.label = 2;
                                    if (TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture, null, this, 1, null) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass2(this.$press, null), 3, null);
                                    return Unit.INSTANCE;
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass1(this.$press, null), 3, null);
                                    this.L$0 = SpillingKt.nullOutSpilledVariable($this$awaitEachGesture);
                                    this.label = 2;
                                    if (TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture, null, this, 1, null) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass2(this.$press, null), 3, null);
                                    return Unit.INSTANCE;
                                case 2:
                                    ResultKt.throwOnFailure($result);
                                    BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass2(this.$press, null), 3, null);
                                    return Unit.INSTANCE;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                        }

                        /* JADX INFO: renamed from: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$1, reason: invalid class name */
                        /* JADX INFO: compiled from: MeloXBackdropComponents.kt */
                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
                        @DebugMetadata(c = "com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$1", f = "MeloXBackdropComponents.kt", i = {}, l = {95}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
                        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            final /* synthetic */ Animatable<Float, AnimationVector1D> $press;
                            int label;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass1(Animatable<Float, AnimationVector1D> animatable, Continuation<? super AnonymousClass1> continuation) {
                                super(2, continuation);
                                this.$press = animatable;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                return new AnonymousClass1(this.$press, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object $result) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch (this.label) {
                                    case 0:
                                        ResultKt.throwOnFailure($result);
                                        Animatable<Float, AnimationVector1D> animatable = this.$press;
                                        Float fBoxFloat = Boxing.boxFloat(1.0f);
                                        SpringSpec springSpecSpring = AnimationSpecKt.spring(0.55f, 420.0f, Boxing.boxFloat(0.001f));
                                        this.label = 1;
                                        if (animatable.animateTo(fBoxFloat, (4 & 2) != 0 ? animatable.defaultSpringSpec : springSpecSpring, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : null, this) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        break;
                                    case 1:
                                        ResultKt.throwOnFailure($result);
                                        break;
                                    default:
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                return Unit.INSTANCE;
                            }
                        }

                        /* JADX INFO: renamed from: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$2, reason: invalid class name */
                        /* JADX INFO: compiled from: MeloXBackdropComponents.kt */
                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
                        @DebugMetadata(c = "com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$2", f = "MeloXBackdropComponents.kt", i = {}, l = {97}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
                        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            final /* synthetic */ Animatable<Float, AnimationVector1D> $press;
                            int label;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass2(Animatable<Float, AnimationVector1D> animatable, Continuation<? super AnonymousClass2> continuation) {
                                super(2, continuation);
                                this.$press = animatable;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                return new AnonymousClass2(this.$press, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object $result) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch (this.label) {
                                    case 0:
                                        ResultKt.throwOnFailure($result);
                                        Animatable<Float, AnimationVector1D> animatable = this.$press;
                                        Float fBoxFloat = Boxing.boxFloat(0.0f);
                                        SpringSpec springSpecSpring = AnimationSpecKt.spring(0.68f, 360.0f, Boxing.boxFloat(0.001f));
                                        this.label = 1;
                                        if (animatable.animateTo(fBoxFloat, (4 & 2) != 0 ? animatable.defaultSpringSpec : springSpecSpring, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : null, this) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        break;
                                    case 1:
                                        ResultKt.throwOnFailure($result);
                                        break;
                                    default:
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                return Unit.INSTANCE;
                            }
                        }
                    }

                    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                    public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
                        Object objAwaitEachGesture;
                        return (enabled3 && (objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$pointerInput, new C26361(scope, press, null), continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objAwaitEachGesture : Unit.INSTANCE;
                    }
                };
                $composer.updateRememberedValue(obj5);
                objRememberedValue3 = obj5;
            } else {
                Object obj6 = (PointerInputEventHandler) new PointerInputEventHandler() { // from class: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1

                    /* JADX INFO: renamed from: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1 */
                    /* JADX INFO: compiled from: MeloXBackdropComponents.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
                    @DebugMetadata(c = "com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1", f = "MeloXBackdropComponents.kt", i = {0, 1}, l = {94, ResolvedStyleKt.InheritedFlags}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture"}, nl = {95, 97}, s = {"L$0", "L$0"}, v = 2)
                    static final class C26361 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ Animatable<Float, AnimationVector1D> $press;
                        final /* synthetic */ CoroutineScope $scope;
                        private /* synthetic */ Object L$0;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C26361(CoroutineScope coroutineScope, Animatable<Float, AnimationVector1D> animatable, Continuation<? super C26361> continuation) {
                            super(2, continuation);
                            this.$scope = coroutineScope;
                            this.$press = animatable;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            C26361 c26361 = new C26361(this.$scope, this.$press, continuation);
                            c26361.L$0 = obj;
                            return c26361;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                            return ((C26361) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        /* JADX WARN: Code duplicated, block: B:13:0x005c A[RETURN] */
                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            AwaitPointerEventScope $this$awaitEachGesture = (AwaitPointerEventScope) this.L$0;
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    this.L$0 = $this$awaitEachGesture;
                                    this.label = 1;
                                    if (TapGestureDetectorKt.awaitFirstDown$default($this$awaitEachGesture, false, null, this, 2, null) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass1(this.$press, null), 3, null);
                                    this.L$0 = SpillingKt.nullOutSpilledVariable($this$awaitEachGesture);
                                    this.label = 2;
                                    if (TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture, null, this, 1, null) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass2(this.$press, null), 3, null);
                                    return Unit.INSTANCE;
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass1(this.$press, null), 3, null);
                                    this.L$0 = SpillingKt.nullOutSpilledVariable($this$awaitEachGesture);
                                    this.label = 2;
                                    if (TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture, null, this, 1, null) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass2(this.$press, null), 3, null);
                                    return Unit.INSTANCE;
                                case 2:
                                    ResultKt.throwOnFailure($result);
                                    BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass2(this.$press, null), 3, null);
                                    return Unit.INSTANCE;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                        }

                        /* JADX INFO: renamed from: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$1, reason: invalid class name */
                        /* JADX INFO: compiled from: MeloXBackdropComponents.kt */
                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
                        @DebugMetadata(c = "com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$1", f = "MeloXBackdropComponents.kt", i = {}, l = {95}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
                        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            final /* synthetic */ Animatable<Float, AnimationVector1D> $press;
                            int label;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass1(Animatable<Float, AnimationVector1D> animatable, Continuation<? super AnonymousClass1> continuation) {
                                super(2, continuation);
                                this.$press = animatable;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                return new AnonymousClass1(this.$press, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object $result) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch (this.label) {
                                    case 0:
                                        ResultKt.throwOnFailure($result);
                                        Animatable<Float, AnimationVector1D> animatable = this.$press;
                                        Float fBoxFloat = Boxing.boxFloat(1.0f);
                                        SpringSpec springSpecSpring = AnimationSpecKt.spring(0.55f, 420.0f, Boxing.boxFloat(0.001f));
                                        this.label = 1;
                                        if (animatable.animateTo(fBoxFloat, (4 & 2) != 0 ? animatable.defaultSpringSpec : springSpecSpring, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : null, this) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        break;
                                    case 1:
                                        ResultKt.throwOnFailure($result);
                                        break;
                                    default:
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                return Unit.INSTANCE;
                            }
                        }

                        /* JADX INFO: renamed from: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$2, reason: invalid class name */
                        /* JADX INFO: compiled from: MeloXBackdropComponents.kt */
                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
                        @DebugMetadata(c = "com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$2", f = "MeloXBackdropComponents.kt", i = {}, l = {97}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
                        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            final /* synthetic */ Animatable<Float, AnimationVector1D> $press;
                            int label;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass2(Animatable<Float, AnimationVector1D> animatable, Continuation<? super AnonymousClass2> continuation) {
                                super(2, continuation);
                                this.$press = animatable;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                return new AnonymousClass2(this.$press, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object $result) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch (this.label) {
                                    case 0:
                                        ResultKt.throwOnFailure($result);
                                        Animatable<Float, AnimationVector1D> animatable = this.$press;
                                        Float fBoxFloat = Boxing.boxFloat(0.0f);
                                        SpringSpec springSpecSpring = AnimationSpecKt.spring(0.68f, 360.0f, Boxing.boxFloat(0.001f));
                                        this.label = 1;
                                        if (animatable.animateTo(fBoxFloat, (4 & 2) != 0 ? animatable.defaultSpringSpec : springSpecSpring, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : null, this) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        break;
                                    case 1:
                                        ResultKt.throwOnFailure($result);
                                        break;
                                    default:
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                return Unit.INSTANCE;
                            }
                        }
                    }

                    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                    public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
                        Object objAwaitEachGesture;
                        return (enabled3 && (objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$pointerInput, new C26361(scope, press, null), continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objAwaitEachGesture : Unit.INSTANCE;
                    }
                };
                $composer.updateRememberedValue(obj6);
                objRememberedValue3 = obj6;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierPointerInput2 = SuspendingPointerInputFilterKt.pointerInput(modifierDrawBackdrop, boolValueOf, (PointerInputEventHandler) objRememberedValue3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return modifierPointerInput2;
        }
        enabled2 = enabled4;
        objRememberedValue8 = new Function0() { // from class: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MeloXBackdropComponentsKt.meloXLiquidButton_NsDo4u0$lambda$3$0(press);
            }
        };
        $composer.updateRememberedValue(objRememberedValue8);
        Function0 function4 = (Function0) objRememberedValue8;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 1151331910, "CC(remember):MeloXBackdropComponents.kt#9igjgp");
        zChangedInstance = $composer.changedInstance(press);
        objRememberedValue = $composer.rememberedValue();
        if (!zChangedInstance) {
        }
        Object obj7 = new Function0() { // from class: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MeloXBackdropComponentsKt.meloXLiquidButton_NsDo4u0$lambda$4$0(press);
            }
        };
        $composer.updateRememberedValue(obj7);
        objRememberedValue = obj7;
        Function0 function5 = (Function0) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 1151335950, "CC(remember):MeloXBackdropComponents.kt#9igjgp");
        z = (((($changed & 7168) ^ 3072) <= 2048 && $composer.changed(tint2)) || ($changed & 3072) == 2048) | ((((57344 & $changed) ^ 24576) <= 16384 && $composer.changed(surfaceColor5)) || ($changed & 24576) == 16384);
        objRememberedValue2 = $composer.rememberedValue();
        if (z) {
            objRememberedValue2 = new Function1() { // from class: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj8) {
                    return MeloXBackdropComponentsKt.meloXLiquidButton_NsDo4u0$lambda$5$0(tint2, surfaceColor5, (DrawScope) obj8);
                }
            };
            $composer.updateRememberedValue(objRememberedValue2);
        } else {
            objRememberedValue2 = new Function1() { // from class: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj8) {
                    return MeloXBackdropComponentsKt.meloXLiquidButton_NsDo4u0$lambda$5$0(tint2, surfaceColor5, (DrawScope) obj8);
                }
            };
            $composer.updateRememberedValue(objRememberedValue2);
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifierDrawBackdrop2 = DrawBackdropModifierKt.drawBackdrop(meloXLiquidButton, backdrop, function0, function1, (3040 & 8) != 0 ? DrawBackdropModifierKt.DefaultHighlight : function4, (3040 & 16) != 0 ? DrawBackdropModifierKt.DefaultShadow : function5, (3040 & 32) != 0 ? null : null, (3040 & 64) != 0 ? null : null, (3040 & 128) != 0 ? null : null, (3040 & 256) != 0 ? null : null, (3040 & 512) != 0 ? DrawBackdropModifierKt.DefaultOnDrawBackdrop : null, (3040 & 1024) != 0 ? null : (Function1) objRememberedValue2, (3040 & 2048) != 0 ? null : null);
        Boolean boolValueOf2 = Boolean.valueOf(enabled2);
        ComposerKt.sourceInformationMarkerStart($composer, 1151346682, "CC(remember):MeloXBackdropComponents.kt#9igjgp");
        if ((($changed & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256) {
            enabled3 = enabled2;
            if (!$composer.changed(enabled3)) {
            }
            zChangedInstance2 = z3 | $composer.changedInstance(scope) | $composer.changedInstance(press);
            objRememberedValue3 = $composer.rememberedValue();
            if (zChangedInstance2) {
                Object obj8 = (PointerInputEventHandler) new PointerInputEventHandler() { // from class: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1

                    /* JADX INFO: renamed from: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1 */
                    /* JADX INFO: compiled from: MeloXBackdropComponents.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
                    @DebugMetadata(c = "com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1", f = "MeloXBackdropComponents.kt", i = {0, 1}, l = {94, ResolvedStyleKt.InheritedFlags}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture"}, nl = {95, 97}, s = {"L$0", "L$0"}, v = 2)
                    static final class C26361 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ Animatable<Float, AnimationVector1D> $press;
                        final /* synthetic */ CoroutineScope $scope;
                        private /* synthetic */ Object L$0;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C26361(CoroutineScope coroutineScope, Animatable<Float, AnimationVector1D> animatable, Continuation<? super C26361> continuation) {
                            super(2, continuation);
                            this.$scope = coroutineScope;
                            this.$press = animatable;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            C26361 c26361 = new C26361(this.$scope, this.$press, continuation);
                            c26361.L$0 = obj;
                            return c26361;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                            return ((C26361) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        /* JADX WARN: Code duplicated, block: B:13:0x005c A[RETURN] */
                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            AwaitPointerEventScope $this$awaitEachGesture = (AwaitPointerEventScope) this.L$0;
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    this.L$0 = $this$awaitEachGesture;
                                    this.label = 1;
                                    if (TapGestureDetectorKt.awaitFirstDown$default($this$awaitEachGesture, false, null, this, 2, null) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass1(this.$press, null), 3, null);
                                    this.L$0 = SpillingKt.nullOutSpilledVariable($this$awaitEachGesture);
                                    this.label = 2;
                                    if (TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture, null, this, 1, null) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass2(this.$press, null), 3, null);
                                    return Unit.INSTANCE;
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass1(this.$press, null), 3, null);
                                    this.L$0 = SpillingKt.nullOutSpilledVariable($this$awaitEachGesture);
                                    this.label = 2;
                                    if (TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture, null, this, 1, null) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass2(this.$press, null), 3, null);
                                    return Unit.INSTANCE;
                                case 2:
                                    ResultKt.throwOnFailure($result);
                                    BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass2(this.$press, null), 3, null);
                                    return Unit.INSTANCE;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                        }

                        /* JADX INFO: renamed from: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$1, reason: invalid class name */
                        /* JADX INFO: compiled from: MeloXBackdropComponents.kt */
                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
                        @DebugMetadata(c = "com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$1", f = "MeloXBackdropComponents.kt", i = {}, l = {95}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
                        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            final /* synthetic */ Animatable<Float, AnimationVector1D> $press;
                            int label;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass1(Animatable<Float, AnimationVector1D> animatable, Continuation<? super AnonymousClass1> continuation) {
                                super(2, continuation);
                                this.$press = animatable;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                return new AnonymousClass1(this.$press, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object $result) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch (this.label) {
                                    case 0:
                                        ResultKt.throwOnFailure($result);
                                        Animatable<Float, AnimationVector1D> animatable = this.$press;
                                        Float fBoxFloat = Boxing.boxFloat(1.0f);
                                        SpringSpec springSpecSpring = AnimationSpecKt.spring(0.55f, 420.0f, Boxing.boxFloat(0.001f));
                                        this.label = 1;
                                        if (animatable.animateTo(fBoxFloat, (4 & 2) != 0 ? animatable.defaultSpringSpec : springSpecSpring, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : null, this) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        break;
                                    case 1:
                                        ResultKt.throwOnFailure($result);
                                        break;
                                    default:
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                return Unit.INSTANCE;
                            }
                        }

                        /* JADX INFO: renamed from: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$2, reason: invalid class name */
                        /* JADX INFO: compiled from: MeloXBackdropComponents.kt */
                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
                        @DebugMetadata(c = "com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$2", f = "MeloXBackdropComponents.kt", i = {}, l = {97}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
                        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            final /* synthetic */ Animatable<Float, AnimationVector1D> $press;
                            int label;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass2(Animatable<Float, AnimationVector1D> animatable, Continuation<? super AnonymousClass2> continuation) {
                                super(2, continuation);
                                this.$press = animatable;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                return new AnonymousClass2(this.$press, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object $result) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch (this.label) {
                                    case 0:
                                        ResultKt.throwOnFailure($result);
                                        Animatable<Float, AnimationVector1D> animatable = this.$press;
                                        Float fBoxFloat = Boxing.boxFloat(0.0f);
                                        SpringSpec springSpecSpring = AnimationSpecKt.spring(0.68f, 360.0f, Boxing.boxFloat(0.001f));
                                        this.label = 1;
                                        if (animatable.animateTo(fBoxFloat, (4 & 2) != 0 ? animatable.defaultSpringSpec : springSpecSpring, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : null, this) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        break;
                                    case 1:
                                        ResultKt.throwOnFailure($result);
                                        break;
                                    default:
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                return Unit.INSTANCE;
                            }
                        }
                    }

                    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                    public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
                        Object objAwaitEachGesture;
                        return (enabled3 && (objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$pointerInput, new C26361(scope, press, null), continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objAwaitEachGesture : Unit.INSTANCE;
                    }
                };
                $composer.updateRememberedValue(obj8);
                objRememberedValue3 = obj8;
            } else {
                Object obj9 = (PointerInputEventHandler) new PointerInputEventHandler() { // from class: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1

                    /* JADX INFO: renamed from: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1 */
                    /* JADX INFO: compiled from: MeloXBackdropComponents.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
                    @DebugMetadata(c = "com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1", f = "MeloXBackdropComponents.kt", i = {0, 1}, l = {94, ResolvedStyleKt.InheritedFlags}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture"}, nl = {95, 97}, s = {"L$0", "L$0"}, v = 2)
                    static final class C26361 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ Animatable<Float, AnimationVector1D> $press;
                        final /* synthetic */ CoroutineScope $scope;
                        private /* synthetic */ Object L$0;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C26361(CoroutineScope coroutineScope, Animatable<Float, AnimationVector1D> animatable, Continuation<? super C26361> continuation) {
                            super(2, continuation);
                            this.$scope = coroutineScope;
                            this.$press = animatable;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            C26361 c26361 = new C26361(this.$scope, this.$press, continuation);
                            c26361.L$0 = obj;
                            return c26361;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                            return ((C26361) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        /* JADX WARN: Code duplicated, block: B:13:0x005c A[RETURN] */
                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            AwaitPointerEventScope $this$awaitEachGesture = (AwaitPointerEventScope) this.L$0;
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    this.L$0 = $this$awaitEachGesture;
                                    this.label = 1;
                                    if (TapGestureDetectorKt.awaitFirstDown$default($this$awaitEachGesture, false, null, this, 2, null) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass1(this.$press, null), 3, null);
                                    this.L$0 = SpillingKt.nullOutSpilledVariable($this$awaitEachGesture);
                                    this.label = 2;
                                    if (TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture, null, this, 1, null) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass2(this.$press, null), 3, null);
                                    return Unit.INSTANCE;
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass1(this.$press, null), 3, null);
                                    this.L$0 = SpillingKt.nullOutSpilledVariable($this$awaitEachGesture);
                                    this.label = 2;
                                    if (TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture, null, this, 1, null) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass2(this.$press, null), 3, null);
                                    return Unit.INSTANCE;
                                case 2:
                                    ResultKt.throwOnFailure($result);
                                    BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass2(this.$press, null), 3, null);
                                    return Unit.INSTANCE;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                        }

                        /* JADX INFO: renamed from: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$1, reason: invalid class name */
                        /* JADX INFO: compiled from: MeloXBackdropComponents.kt */
                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
                        @DebugMetadata(c = "com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$1", f = "MeloXBackdropComponents.kt", i = {}, l = {95}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
                        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            final /* synthetic */ Animatable<Float, AnimationVector1D> $press;
                            int label;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass1(Animatable<Float, AnimationVector1D> animatable, Continuation<? super AnonymousClass1> continuation) {
                                super(2, continuation);
                                this.$press = animatable;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                return new AnonymousClass1(this.$press, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object $result) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch (this.label) {
                                    case 0:
                                        ResultKt.throwOnFailure($result);
                                        Animatable<Float, AnimationVector1D> animatable = this.$press;
                                        Float fBoxFloat = Boxing.boxFloat(1.0f);
                                        SpringSpec springSpecSpring = AnimationSpecKt.spring(0.55f, 420.0f, Boxing.boxFloat(0.001f));
                                        this.label = 1;
                                        if (animatable.animateTo(fBoxFloat, (4 & 2) != 0 ? animatable.defaultSpringSpec : springSpecSpring, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : null, this) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        break;
                                    case 1:
                                        ResultKt.throwOnFailure($result);
                                        break;
                                    default:
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                return Unit.INSTANCE;
                            }
                        }

                        /* JADX INFO: renamed from: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$2, reason: invalid class name */
                        /* JADX INFO: compiled from: MeloXBackdropComponents.kt */
                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
                        @DebugMetadata(c = "com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$2", f = "MeloXBackdropComponents.kt", i = {}, l = {97}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
                        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            final /* synthetic */ Animatable<Float, AnimationVector1D> $press;
                            int label;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass2(Animatable<Float, AnimationVector1D> animatable, Continuation<? super AnonymousClass2> continuation) {
                                super(2, continuation);
                                this.$press = animatable;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                return new AnonymousClass2(this.$press, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object $result) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch (this.label) {
                                    case 0:
                                        ResultKt.throwOnFailure($result);
                                        Animatable<Float, AnimationVector1D> animatable = this.$press;
                                        Float fBoxFloat = Boxing.boxFloat(0.0f);
                                        SpringSpec springSpecSpring = AnimationSpecKt.spring(0.68f, 360.0f, Boxing.boxFloat(0.001f));
                                        this.label = 1;
                                        if (animatable.animateTo(fBoxFloat, (4 & 2) != 0 ? animatable.defaultSpringSpec : springSpecSpring, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : null, this) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        break;
                                    case 1:
                                        ResultKt.throwOnFailure($result);
                                        break;
                                    default:
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                return Unit.INSTANCE;
                            }
                        }
                    }

                    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                    public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
                        Object objAwaitEachGesture;
                        return (enabled3 && (objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$pointerInput, new C26361(scope, press, null), continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objAwaitEachGesture : Unit.INSTANCE;
                    }
                };
                $composer.updateRememberedValue(obj9);
                objRememberedValue3 = obj9;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierPointerInput3 = SuspendingPointerInputFilterKt.pointerInput(modifierDrawBackdrop2, boolValueOf2, (PointerInputEventHandler) objRememberedValue3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return modifierPointerInput3;
        }
        enabled3 = enabled2;
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) {
        }
        zChangedInstance2 = z3 | $composer.changedInstance(scope) | $composer.changedInstance(press);
        objRememberedValue3 = $composer.rememberedValue();
        if (zChangedInstance2) {
            Object obj10 = (PointerInputEventHandler) new PointerInputEventHandler() { // from class: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1

                /* JADX INFO: renamed from: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1 */
                /* JADX INFO: compiled from: MeloXBackdropComponents.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
                @DebugMetadata(c = "com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1", f = "MeloXBackdropComponents.kt", i = {0, 1}, l = {94, ResolvedStyleKt.InheritedFlags}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture"}, nl = {95, 97}, s = {"L$0", "L$0"}, v = 2)
                static final class C26361 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ Animatable<Float, AnimationVector1D> $press;
                    final /* synthetic */ CoroutineScope $scope;
                    private /* synthetic */ Object L$0;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C26361(CoroutineScope coroutineScope, Animatable<Float, AnimationVector1D> animatable, Continuation<? super C26361> continuation) {
                        super(2, continuation);
                        this.$scope = coroutineScope;
                        this.$press = animatable;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        C26361 c26361 = new C26361(this.$scope, this.$press, continuation);
                        c26361.L$0 = obj;
                        return c26361;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                        return ((C26361) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARN: Code duplicated, block: B:13:0x005c A[RETURN] */
                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object $result) {
                        AwaitPointerEventScope $this$awaitEachGesture = (AwaitPointerEventScope) this.L$0;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0:
                                ResultKt.throwOnFailure($result);
                                this.L$0 = $this$awaitEachGesture;
                                this.label = 1;
                                if (TapGestureDetectorKt.awaitFirstDown$default($this$awaitEachGesture, false, null, this, 2, null) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass1(this.$press, null), 3, null);
                                this.L$0 = SpillingKt.nullOutSpilledVariable($this$awaitEachGesture);
                                this.label = 2;
                                if (TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture, null, this, 1, null) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass2(this.$press, null), 3, null);
                                return Unit.INSTANCE;
                            case 1:
                                ResultKt.throwOnFailure($result);
                                BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass1(this.$press, null), 3, null);
                                this.L$0 = SpillingKt.nullOutSpilledVariable($this$awaitEachGesture);
                                this.label = 2;
                                if (TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture, null, this, 1, null) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass2(this.$press, null), 3, null);
                                return Unit.INSTANCE;
                            case 2:
                                ResultKt.throwOnFailure($result);
                                BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass2(this.$press, null), 3, null);
                                return Unit.INSTANCE;
                            default:
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }

                    /* JADX INFO: renamed from: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$1, reason: invalid class name */
                    /* JADX INFO: compiled from: MeloXBackdropComponents.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
                    @DebugMetadata(c = "com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$1", f = "MeloXBackdropComponents.kt", i = {}, l = {95}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ Animatable<Float, AnimationVector1D> $press;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(Animatable<Float, AnimationVector1D> animatable, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$press = animatable;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$press, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    Animatable<Float, AnimationVector1D> animatable = this.$press;
                                    Float fBoxFloat = Boxing.boxFloat(1.0f);
                                    SpringSpec springSpecSpring = AnimationSpecKt.spring(0.55f, 420.0f, Boxing.boxFloat(0.001f));
                                    this.label = 1;
                                    if (animatable.animateTo(fBoxFloat, (4 & 2) != 0 ? animatable.defaultSpringSpec : springSpecSpring, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : null, this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    break;
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    /* JADX INFO: renamed from: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$2, reason: invalid class name */
                    /* JADX INFO: compiled from: MeloXBackdropComponents.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
                    @DebugMetadata(c = "com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$2", f = "MeloXBackdropComponents.kt", i = {}, l = {97}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
                    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ Animatable<Float, AnimationVector1D> $press;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass2(Animatable<Float, AnimationVector1D> animatable, Continuation<? super AnonymousClass2> continuation) {
                            super(2, continuation);
                            this.$press = animatable;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass2(this.$press, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    Animatable<Float, AnimationVector1D> animatable = this.$press;
                                    Float fBoxFloat = Boxing.boxFloat(0.0f);
                                    SpringSpec springSpecSpring = AnimationSpecKt.spring(0.68f, 360.0f, Boxing.boxFloat(0.001f));
                                    this.label = 1;
                                    if (animatable.animateTo(fBoxFloat, (4 & 2) != 0 ? animatable.defaultSpringSpec : springSpecSpring, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : null, this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    break;
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }
                }

                @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
                    Object objAwaitEachGesture;
                    return (enabled3 && (objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$pointerInput, new C26361(scope, press, null), continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objAwaitEachGesture : Unit.INSTANCE;
                }
            };
            $composer.updateRememberedValue(obj10);
            objRememberedValue3 = obj10;
        } else {
            Object obj11 = (PointerInputEventHandler) new PointerInputEventHandler() { // from class: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1

                /* JADX INFO: renamed from: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1 */
                /* JADX INFO: compiled from: MeloXBackdropComponents.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
                @DebugMetadata(c = "com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1", f = "MeloXBackdropComponents.kt", i = {0, 1}, l = {94, ResolvedStyleKt.InheritedFlags}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture"}, nl = {95, 97}, s = {"L$0", "L$0"}, v = 2)
                static final class C26361 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ Animatable<Float, AnimationVector1D> $press;
                    final /* synthetic */ CoroutineScope $scope;
                    private /* synthetic */ Object L$0;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C26361(CoroutineScope coroutineScope, Animatable<Float, AnimationVector1D> animatable, Continuation<? super C26361> continuation) {
                        super(2, continuation);
                        this.$scope = coroutineScope;
                        this.$press = animatable;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        C26361 c26361 = new C26361(this.$scope, this.$press, continuation);
                        c26361.L$0 = obj;
                        return c26361;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                        return ((C26361) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARN: Code duplicated, block: B:13:0x005c A[RETURN] */
                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object $result) {
                        AwaitPointerEventScope $this$awaitEachGesture = (AwaitPointerEventScope) this.L$0;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0:
                                ResultKt.throwOnFailure($result);
                                this.L$0 = $this$awaitEachGesture;
                                this.label = 1;
                                if (TapGestureDetectorKt.awaitFirstDown$default($this$awaitEachGesture, false, null, this, 2, null) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass1(this.$press, null), 3, null);
                                this.L$0 = SpillingKt.nullOutSpilledVariable($this$awaitEachGesture);
                                this.label = 2;
                                if (TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture, null, this, 1, null) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass2(this.$press, null), 3, null);
                                return Unit.INSTANCE;
                            case 1:
                                ResultKt.throwOnFailure($result);
                                BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass1(this.$press, null), 3, null);
                                this.L$0 = SpillingKt.nullOutSpilledVariable($this$awaitEachGesture);
                                this.label = 2;
                                if (TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture, null, this, 1, null) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass2(this.$press, null), 3, null);
                                return Unit.INSTANCE;
                            case 2:
                                ResultKt.throwOnFailure($result);
                                BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass2(this.$press, null), 3, null);
                                return Unit.INSTANCE;
                            default:
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }

                    /* JADX INFO: renamed from: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$1, reason: invalid class name */
                    /* JADX INFO: compiled from: MeloXBackdropComponents.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
                    @DebugMetadata(c = "com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$1", f = "MeloXBackdropComponents.kt", i = {}, l = {95}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ Animatable<Float, AnimationVector1D> $press;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(Animatable<Float, AnimationVector1D> animatable, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$press = animatable;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$press, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    Animatable<Float, AnimationVector1D> animatable = this.$press;
                                    Float fBoxFloat = Boxing.boxFloat(1.0f);
                                    SpringSpec springSpecSpring = AnimationSpecKt.spring(0.55f, 420.0f, Boxing.boxFloat(0.001f));
                                    this.label = 1;
                                    if (animatable.animateTo(fBoxFloat, (4 & 2) != 0 ? animatable.defaultSpringSpec : springSpecSpring, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : null, this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    break;
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    /* JADX INFO: renamed from: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$2, reason: invalid class name */
                    /* JADX INFO: compiled from: MeloXBackdropComponents.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
                    @DebugMetadata(c = "com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$meloXLiquidButton$6$1$1$2", f = "MeloXBackdropComponents.kt", i = {}, l = {97}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
                    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ Animatable<Float, AnimationVector1D> $press;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass2(Animatable<Float, AnimationVector1D> animatable, Continuation<? super AnonymousClass2> continuation) {
                            super(2, continuation);
                            this.$press = animatable;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass2(this.$press, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    Animatable<Float, AnimationVector1D> animatable = this.$press;
                                    Float fBoxFloat = Boxing.boxFloat(0.0f);
                                    SpringSpec springSpecSpring = AnimationSpecKt.spring(0.68f, 360.0f, Boxing.boxFloat(0.001f));
                                    this.label = 1;
                                    if (animatable.animateTo(fBoxFloat, (4 & 2) != 0 ? animatable.defaultSpringSpec : springSpecSpring, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : null, this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    break;
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }
                }

                @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
                    Object objAwaitEachGesture;
                    return (enabled3 && (objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$pointerInput, new C26361(scope, press, null), continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objAwaitEachGesture : Unit.INSTANCE;
                }
            };
            $composer.updateRememberedValue(obj11);
            objRememberedValue3 = obj11;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifierPointerInput4 = SuspendingPointerInputFilterKt.pointerInput(modifierDrawBackdrop2, boolValueOf2, (PointerInputEventHandler) objRememberedValue3);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return modifierPointerInput4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Shape meloXLiquidButton_NsDo4u0$lambda$1$0() {
        return new Capsule(null, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit meloXLiquidButton_NsDo4u0$lambda$2$0(float $blurRadius, BackdropEffectScope drawBackdrop) {
        Intrinsics.checkNotNullParameter(drawBackdrop, "$this$drawBackdrop");
        ColorFilterKt.vibrancy(drawBackdrop);
        BlurKt.m9522blur3YTHUZs$default(drawBackdrop, drawBackdrop.mo1189toPx0680j_4($blurRadius), 0, 2, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Highlight meloXLiquidButton_NsDo4u0$lambda$3$0(Animatable $press) {
        return Highlight.m9523copyi1RSzL4$default(Highlight.INSTANCE.getPlain(), 0.0f, 0.0f, (((Number) $press.getValue()).floatValue() * 0.3f) + 0.48f, null, 11, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Shadow meloXLiquidButton_NsDo4u0$lambda$4$0(Animatable $press) {
        return new Shadow(Dp.constructor_impl(5), 0L, 0L, (((Number) $press.getValue()).floatValue() * 0.06f) + 0.12f, 0, 22, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit meloXLiquidButton_NsDo4u0$lambda$5$0(long $tint, long $surfaceColor, DrawScope drawBackdrop) {
        Intrinsics.checkNotNullParameter(drawBackdrop, "$this$drawBackdrop");
        if (!Color.m6069equalsimpl0($tint, Color.INSTANCE.m6104getUnspecified0d7_KjU())) {
            DrawScope.m6636drawRectnJ9OG0$default(drawBackdrop, $tint, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m5994getHue0nO6VwU(), 62, null);
            DrawScope.m6636drawRectnJ9OG0$default(drawBackdrop, Color.copy_wmQWz5c($tint, (14 & 1) != 0 ? Color.getAlpha_impl($tint) : Color.getAlpha_impl($tint) * 0.75f, (14 & 2) != 0 ? Color.getRed_impl($tint) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl($tint) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl($tint) : 0.0f), 0L, 0L, 0.0f, null, null, 0, WebSocketProtocol.PAYLOAD_SHORT, null);
        }
        if (!Color.m6069equalsimpl0($surfaceColor, Color.INSTANCE.m6104getUnspecified0d7_KjU())) {
            DrawScope.m6636drawRectnJ9OG0$default(drawBackdrop, $surfaceColor, 0L, 0L, 0.0f, null, null, 0, WebSocketProtocol.PAYLOAD_SHORT, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: meloXLiquidBottomBar-9z6LAg8, reason: not valid java name */
    public static final Modifier m9632meloXLiquidBottomBar9z6LAg8(Modifier meloXLiquidBottomBar, Shape shape, final long tint, final long surfaceColor, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(meloXLiquidBottomBar, "$this$meloXLiquidBottomBar");
        Intrinsics.checkNotNullParameter(shape, "shape");
        $composer.startReplaceGroup(1074122840);
        ComposerKt.sourceInformation($composer, "C(meloXLiquidBottomBar)N(shape,tint:c#ui.graphics.Color,surfaceColor:c#ui.graphics.Color)108@3975L7,117@4310L13,118@4343L64,122@4429L39,123@4487L40,124@4553L100:MeloXBackdropComponents.kt#hc6ke1");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1074122840, $changed, -1, "com.lladlam.melox.ui.glass.meloXLiquidBottomBar (MeloXBackdropComponents.kt:107)");
        }
        ProvidableCompositionLocal<Backdrop> providableCompositionLocal = LocalMeloXBackdrop;
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(providableCompositionLocal);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Backdrop backdrop = (Backdrop) objConsume;
        if (backdrop == null) {
            long stableSurface = Color.copy_wmQWz5c(surfaceColor, (14 & 1) != 0 ? Color.getAlpha_impl(surfaceColor) : Math.max(Color.getAlpha_impl(surfaceColor), 0.48f), (14 & 2) != 0 ? Color.getRed_impl(surfaceColor) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(surfaceColor) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(surfaceColor) : 0.0f);
            Modifier modifierM1042backgroundbw27NRU = BackgroundKt.m1042backgroundbw27NRU(meloXLiquidBottomBar, stableSurface, shape);
            float fM8905constructorimpl = Dp.constructor_impl((float) 0.75d);
            long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
            Modifier modifierM1054borderxT4_qwU = BorderKt.m1054borderxT4_qwU(modifierM1042backgroundbw27NRU, fM8905constructorimpl, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU) : 0.62f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU) : 0.0f), shape);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return modifierM1054borderxT4_qwU;
        }
        ComposerKt.sourceInformationMarkerStart($composer, 1439913637, "CC(remember):MeloXBackdropComponents.kt#9igjgp");
        Object objRememberedValue = $composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object obj = new Function0() { // from class: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MeloXBackdropComponentsKt.meloXLiquidBottomBar_9z6LAg8$lambda$0$0();
                }
            };
            $composer.updateRememberedValue(obj);
            objRememberedValue = obj;
        }
        Function0 function0 = (Function0) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 1439914744, "CC(remember):MeloXBackdropComponents.kt#9igjgp");
        Object objRememberedValue2 = $composer.rememberedValue();
        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            Object obj2 = new Function1() { // from class: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    return MeloXBackdropComponentsKt.meloXLiquidBottomBar_9z6LAg8$lambda$1$0((BackdropEffectScope) obj3);
                }
            };
            $composer.updateRememberedValue(obj2);
            objRememberedValue2 = obj2;
        }
        Function1 function1 = (Function1) objRememberedValue2;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 1439917471, "CC(remember):MeloXBackdropComponents.kt#9igjgp");
        Object objRememberedValue3 = $composer.rememberedValue();
        if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            Object obj3 = new Function0() { // from class: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MeloXBackdropComponentsKt.meloXLiquidBottomBar_9z6LAg8$lambda$2$0();
                }
            };
            $composer.updateRememberedValue(obj3);
            objRememberedValue3 = obj3;
        }
        Function0 function2 = (Function0) objRememberedValue3;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 1439919328, "CC(remember):MeloXBackdropComponents.kt#9igjgp");
        Object objRememberedValue4 = $composer.rememberedValue();
        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
            Object obj4 = new Function0() { // from class: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MeloXBackdropComponentsKt.meloXLiquidBottomBar_9z6LAg8$lambda$3$0();
                }
            };
            $composer.updateRememberedValue(obj4);
            objRememberedValue4 = obj4;
        }
        Function0 function3 = (Function0) objRememberedValue4;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 1439921500, "CC(remember):MeloXBackdropComponents.kt#9igjgp");
        boolean z = (((($changed & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256 && $composer.changed(tint)) || ($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | (((($changed & 7168) ^ 3072) > 2048 && $composer.changed(surfaceColor)) || ($changed & 3072) == 2048);
        Object objRememberedValue5 = $composer.rememberedValue();
        if (z || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
            Object obj5 = new Function1() { // from class: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj6) {
                    return MeloXBackdropComponentsKt.meloXLiquidBottomBar_9z6LAg8$lambda$4$0(tint, surfaceColor, (DrawScope) obj6);
                }
            };
            $composer.updateRememberedValue(obj5);
            objRememberedValue5 = obj5;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifierDrawBackdrop = DrawBackdropModifierKt.drawBackdrop(meloXLiquidBottomBar, backdrop, function0, function1, (3040 & 8) != 0 ? DrawBackdropModifierKt.DefaultHighlight : function2, (3040 & 16) != 0 ? DrawBackdropModifierKt.DefaultShadow : function3, (3040 & 32) != 0 ? null : null, (3040 & 64) != 0 ? null : null, (3040 & 128) != 0 ? null : null, (3040 & 256) != 0 ? null : null, (3040 & 512) != 0 ? DrawBackdropModifierKt.DefaultOnDrawBackdrop : null, (3040 & 1024) != 0 ? null : (Function1) objRememberedValue5, (3040 & 2048) != 0 ? null : null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return modifierDrawBackdrop;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Shape meloXLiquidBottomBar_9z6LAg8$lambda$0$0() {
        return new Capsule(null, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit meloXLiquidBottomBar_9z6LAg8$lambda$1$0(BackdropEffectScope drawBackdrop) {
        Intrinsics.checkNotNullParameter(drawBackdrop, "$this$drawBackdrop");
        ColorFilterKt.vibrancy(drawBackdrop);
        BlurKt.m9522blur3YTHUZs$default(drawBackdrop, drawBackdrop.mo1189toPx0680j_4(Dp.constructor_impl(8)), 0, 2, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Highlight meloXLiquidBottomBar_9z6LAg8$lambda$2$0() {
        return Highlight.m9523copyi1RSzL4$default(Highlight.INSTANCE.getPlain(), 0.0f, 0.0f, 0.56f, null, 11, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Shadow meloXLiquidBottomBar_9z6LAg8$lambda$3$0() {
        return new Shadow(Dp.constructor_impl(7), 0L, 0L, 0.14f, 0, 22, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit meloXLiquidBottomBar_9z6LAg8$lambda$4$0(long $tint, long $surfaceColor, DrawScope drawBackdrop) {
        Intrinsics.checkNotNullParameter(drawBackdrop, "$this$drawBackdrop");
        DrawScope.m6636drawRectnJ9OG0$default(drawBackdrop, $tint, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m5994getHue0nO6VwU(), 62, null);
        DrawScope.m6636drawRectnJ9OG0$default(drawBackdrop, $surfaceColor, 0L, 0L, 0.0f, null, null, 0, WebSocketProtocol.PAYLOAD_SHORT, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: meloXLiquidTabSelection-Bx497Mc, reason: not valid java name */
    public static final Modifier m9634meloXLiquidTabSelectionBx497Mc(Modifier meloXLiquidTabSelection, Shape shape, boolean selected, final long tint, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(meloXLiquidTabSelection, "$this$meloXLiquidTabSelection");
        Intrinsics.checkNotNullParameter(shape, "shape");
        $composer.startReplaceGroup(593798924);
        ComposerKt.sourceInformation($composer, "C(meloXLiquidTabSelection)N(shape,selected,tint:c#ui.graphics.Color)139@4917L7,147@5193L13,148@5226L64,152@5312L39,153@5370L40,154@5436L18:MeloXBackdropComponents.kt#hc6ke1");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(593798924, $changed, -1, "com.lladlam.melox.ui.glass.meloXLiquidTabSelection (MeloXBackdropComponents.kt:137)");
        }
        if (!selected) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return meloXLiquidTabSelection;
        }
        ProvidableCompositionLocal<Backdrop> providableCompositionLocal = LocalMeloXBackdrop;
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(providableCompositionLocal);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Backdrop backdrop = (Backdrop) objConsume;
        if (backdrop == null) {
            Modifier modifierM1042backgroundbw27NRU = BackgroundKt.m1042backgroundbw27NRU(meloXLiquidTabSelection, Color.copy_wmQWz5c(tint, (14 & 1) != 0 ? Color.getAlpha_impl(tint) : Math.max(Color.getAlpha_impl(tint), 0.36f), (14 & 2) != 0 ? Color.getRed_impl(tint) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(tint) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(tint) : 0.0f), shape);
            float fM8905constructorimpl = Dp.constructor_impl((float) 0.5d);
            long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
            Modifier modifierM1054borderxT4_qwU = BorderKt.m1054borderxT4_qwU(modifierM1042backgroundbw27NRU, fM8905constructorimpl, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU) : 0.58f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU) : 0.0f), shape);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return modifierM1054borderxT4_qwU;
        }
        ComposerKt.sourceInformationMarkerStart($composer, -589869831, "CC(remember):MeloXBackdropComponents.kt#9igjgp");
        Object objRememberedValue = $composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object obj = new Function0() { // from class: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MeloXBackdropComponentsKt.meloXLiquidTabSelection_Bx497Mc$lambda$0$0();
                }
            };
            $composer.updateRememberedValue(obj);
            objRememberedValue = obj;
        }
        Function0 function0 = (Function0) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -589868724, "CC(remember):MeloXBackdropComponents.kt#9igjgp");
        Object objRememberedValue2 = $composer.rememberedValue();
        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            Object obj2 = new Function1() { // from class: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    return MeloXBackdropComponentsKt.meloXLiquidTabSelection_Bx497Mc$lambda$1$0((BackdropEffectScope) obj3);
                }
            };
            $composer.updateRememberedValue(obj2);
            objRememberedValue2 = obj2;
        }
        Function1 function1 = (Function1) objRememberedValue2;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -589865997, "CC(remember):MeloXBackdropComponents.kt#9igjgp");
        Object objRememberedValue3 = $composer.rememberedValue();
        if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            Object obj3 = new Function0() { // from class: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MeloXBackdropComponentsKt.meloXLiquidTabSelection_Bx497Mc$lambda$2$0();
                }
            };
            $composer.updateRememberedValue(obj3);
            objRememberedValue3 = obj3;
        }
        Function0 function2 = (Function0) objRememberedValue3;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -589864140, "CC(remember):MeloXBackdropComponents.kt#9igjgp");
        Object objRememberedValue4 = $composer.rememberedValue();
        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
            Object obj4 = new Function0() { // from class: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MeloXBackdropComponentsKt.meloXLiquidTabSelection_Bx497Mc$lambda$3$0();
                }
            };
            $composer.updateRememberedValue(obj4);
            objRememberedValue4 = obj4;
        }
        Function0 function3 = (Function0) objRememberedValue4;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -589862050, "CC(remember):MeloXBackdropComponents.kt#9igjgp");
        boolean z = ((($changed & 7168) ^ 3072) > 2048 && $composer.changed(tint)) || ($changed & 3072) == 2048;
        Object objRememberedValue5 = $composer.rememberedValue();
        if (z || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
            Object obj5 = new Function1() { // from class: com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj6) {
                    return MeloXBackdropComponentsKt.meloXLiquidTabSelection_Bx497Mc$lambda$4$0(tint, (DrawScope) obj6);
                }
            };
            $composer.updateRememberedValue(obj5);
            objRememberedValue5 = obj5;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifierDrawBackdrop = DrawBackdropModifierKt.drawBackdrop(meloXLiquidTabSelection, backdrop, function0, function1, (3040 & 8) != 0 ? DrawBackdropModifierKt.DefaultHighlight : function2, (3040 & 16) != 0 ? DrawBackdropModifierKt.DefaultShadow : function3, (3040 & 32) != 0 ? null : null, (3040 & 64) != 0 ? null : null, (3040 & 128) != 0 ? null : null, (3040 & 256) != 0 ? null : null, (3040 & 512) != 0 ? DrawBackdropModifierKt.DefaultOnDrawBackdrop : null, (3040 & 1024) != 0 ? null : (Function1) objRememberedValue5, (3040 & 2048) != 0 ? null : null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return modifierDrawBackdrop;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Shape meloXLiquidTabSelection_Bx497Mc$lambda$0$0() {
        return new Capsule(null, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit meloXLiquidTabSelection_Bx497Mc$lambda$1$0(BackdropEffectScope drawBackdrop) {
        Intrinsics.checkNotNullParameter(drawBackdrop, "$this$drawBackdrop");
        ColorFilterKt.vibrancy(drawBackdrop);
        BlurKt.m9522blur3YTHUZs$default(drawBackdrop, drawBackdrop.mo1189toPx0680j_4(Dp.constructor_impl(2)), 0, 2, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Highlight meloXLiquidTabSelection_Bx497Mc$lambda$2$0() {
        return Highlight.m9523copyi1RSzL4$default(Highlight.INSTANCE.getPlain(), 0.0f, 0.0f, 0.66f, null, 11, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Shadow meloXLiquidTabSelection_Bx497Mc$lambda$3$0() {
        return new Shadow(Dp.constructor_impl(3), 0L, 0L, 0.16f, 0, 22, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit meloXLiquidTabSelection_Bx497Mc$lambda$4$0(long $tint, DrawScope drawBackdrop) {
        Intrinsics.checkNotNullParameter(drawBackdrop, "$this$drawBackdrop");
        DrawScope.m6636drawRectnJ9OG0$default(drawBackdrop, $tint, 0L, 0L, 0.0f, null, null, 0, WebSocketProtocol.PAYLOAD_SHORT, null);
        return Unit.INSTANCE;
    }
}
