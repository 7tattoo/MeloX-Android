package com.lladlam.melox.p012ui.player;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MeloXIOSNowPlayingSharedHost.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lladlam.melox.ui.player.MeloXIOSNowPlayingSharedHostKt$MeloXIOSNowPlayingSharedHost$1$1", f = "MeloXIOSNowPlayingSharedHost.kt", i = {}, l = {91}, m = "invokeSuspend", n = {}, nl = {92}, s = {}, v = 2)
final class MeloXIOSNowPlayingSharedHostKt$MeloXIOSNowPlayingSharedHost$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<Boolean> $committingDismiss$delegate;
    final /* synthetic */ Animatable<Float, AnimationVector1D> $dragOffset;
    final /* synthetic */ State<Float> $expansionProgress$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MeloXIOSNowPlayingSharedHostKt$MeloXIOSNowPlayingSharedHost$1$1(Animatable<Float, AnimationVector1D> animatable, State<Float> state, MutableState<Boolean> mutableState, Continuation<? super MeloXIOSNowPlayingSharedHostKt$MeloXIOSNowPlayingSharedHost$1$1> continuation) {
        super(2, continuation);
        this.$dragOffset = animatable;
        this.$expansionProgress$delegate = state;
        this.$committingDismiss$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MeloXIOSNowPlayingSharedHostKt$MeloXIOSNowPlayingSharedHost$1$1(this.$dragOffset, this.$expansionProgress$delegate, this.$committingDismiss$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MeloXIOSNowPlayingSharedHostKt$MeloXIOSNowPlayingSharedHost$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (MeloXIOSNowPlayingSharedHostKt.MeloXIOSNowPlayingSharedHost$lambda$10(this.$expansionProgress$delegate) <= 0.01f) {
                    this.label = 1;
                    if (this.$dragOffset.snapTo(Boxing.boxFloat(0.0f), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    MeloXIOSNowPlayingSharedHostKt.MeloXIOSNowPlayingSharedHost$lambda$6(this.$committingDismiss$delegate, false);
                }
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure($result);
                MeloXIOSNowPlayingSharedHostKt.MeloXIOSNowPlayingSharedHost$lambda$6(this.$committingDismiss$delegate, false);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
