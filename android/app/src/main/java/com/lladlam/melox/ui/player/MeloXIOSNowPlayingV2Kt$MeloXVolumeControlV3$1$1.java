package com.lladlam.melox.p012ui.player;

import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MeloXIOSNowPlayingV2.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$MeloXVolumeControlV3$1$1", f = "MeloXIOSNowPlayingV2.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class MeloXIOSNowPlayingV2Kt$MeloXVolumeControlV3$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<Boolean> $dragging$delegate;
    final /* synthetic */ MutableFloatState $localVolume$delegate;
    final /* synthetic */ MeloXPlaybackUiState $state;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MeloXIOSNowPlayingV2Kt$MeloXVolumeControlV3$1$1(MeloXPlaybackUiState meloXPlaybackUiState, MutableState<Boolean> mutableState, MutableFloatState mutableFloatState, Continuation<? super MeloXIOSNowPlayingV2Kt$MeloXVolumeControlV3$1$1> continuation) {
        super(2, continuation);
        this.$state = meloXPlaybackUiState;
        this.$dragging$delegate = mutableState;
        this.$localVolume$delegate = mutableFloatState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MeloXIOSNowPlayingV2Kt$MeloXVolumeControlV3$1$1(this.$state, this.$dragging$delegate, this.$localVolume$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MeloXIOSNowPlayingV2Kt$MeloXVolumeControlV3$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (!MeloXIOSNowPlayingV2Kt.MeloXVolumeControlV3$lambda$1(this.$dragging$delegate)) {
                    this.$localVolume$delegate.setFloatValue(this.$state.getVolume());
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
