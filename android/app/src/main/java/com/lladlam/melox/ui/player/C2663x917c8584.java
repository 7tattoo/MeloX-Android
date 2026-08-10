package com.lladlam.melox.ui.player;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.runtime.MutableState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: renamed from: com.lladlam.melox.ui.player.MeloXIOSNowPlayingSharedHostKt$MeloXIOSNowPlayingSharedHost$2$2$1 */
/* JADX INFO: compiled from: MeloXIOSNowPlayingSharedHost.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "velocity", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lladlam.melox.ui.player.MeloXIOSNowPlayingSharedHostKt$MeloXIOSNowPlayingSharedHost$2$2$1", f = "MeloXIOSNowPlayingSharedHost.kt", i = {0, 0, 1, 1}, l = {145, 151}, m = "invokeSuspend", n = {"velocity", "shouldDismiss", "velocity", "shouldDismiss"}, nl = {149, 160}, s = {"F$0", "I$0", "F$0", "I$0"}, v = 2)
final class C2663x917c8584 extends SuspendLambda implements Function3<CoroutineScope, Float, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<Boolean> $committingDismiss$delegate;
    final /* synthetic */ float $dismissThresholdPx;
    final /* synthetic */ Animatable<Float, AnimationVector1D> $dragOffset;
    final /* synthetic */ float $heightPx;
    final /* synthetic */ Function0<Unit> $onDismiss;
    /* synthetic */ float F$0;
    int I$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C2663x917c8584(Animatable<Float, AnimationVector1D> animatable, float f, float f2, Function0<Unit> function0, MutableState<Boolean> mutableState, Continuation<? super C2663x917c8584> continuation) {
        super(3, continuation);
        this.$dragOffset = animatable;
        this.$dismissThresholdPx = f;
        this.$heightPx = f2;
        this.$onDismiss = function0;
        this.$committingDismiss$delegate = mutableState;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Float f, Continuation<? super Unit> continuation) {
        return invoke(coroutineScope, f.floatValue(), continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, float f, Continuation<? super Unit> continuation) {
        C2663x917c8584 c2663x917c8584 = new C2663x917c8584(this.$dragOffset, this.$dismissThresholdPx, this.$heightPx, this.$onDismiss, this.$committingDismiss$delegate, continuation);
        c2663x917c8584.F$0 = f;
        return c2663x917c8584.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        float velocity = this.F$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                int i = (this.$dragOffset.getValue().floatValue() >= this.$dismissThresholdPx || velocity >= 1350.0f) ? 1 : 0;
                if (i != 0) {
                    MeloXIOSNowPlayingSharedHostKt.MeloXIOSNowPlayingSharedHost$lambda$6(this.$committingDismiss$delegate, true);
                    Animatable<Float, AnimationVector1D> animatable = this.$dragOffset;
                    Float fBoxFloat = Boxing.boxFloat(Math.max(this.$dragOffset.getValue().floatValue(), this.$heightPx * 0.24f));
                    TweenSpec tweenSpecTween$default = AnimationSpecKt.tween$default(150, 0, null, 6, null);
                    this.F$0 = velocity;
                    this.I$0 = i;
                    this.label = 1;
                    if (animatable.animateTo(fBoxFloat, (4 & 2) != 0 ? animatable.defaultSpringSpec : tweenSpecTween$default, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : null, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    this.$onDismiss.invoke();
                    Unit unit = Unit.INSTANCE;
                } else {
                    Animatable<Float, AnimationVector1D> animatable2 = this.$dragOffset;
                    Float fBoxFloat2 = Boxing.boxFloat(0.0f);
                    SpringSpec springSpecSpring = AnimationSpecKt.spring(0.82f, 430.0f, Boxing.boxFloat(0.5f));
                    this.F$0 = velocity;
                    this.I$0 = i;
                    this.label = 2;
                    if (animatable2.animateTo(fBoxFloat2, (4 & 2) != 0 ? animatable2.defaultSpringSpec : springSpecSpring, (4 & 4) != 0 ? animatable2.getVelocity() : null, (4 & 8) != 0 ? null : null, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            case 1:
                int i2 = this.I$0;
                ResultKt.throwOnFailure($result);
                this.$onDismiss.invoke();
                Unit unit2 = Unit.INSTANCE;
                return Unit.INSTANCE;
            case 2:
                int i3 = this.I$0;
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
