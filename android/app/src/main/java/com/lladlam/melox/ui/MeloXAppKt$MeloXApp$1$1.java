package com.lladlam.melox.p012ui;

import androidx.compose.runtime.MutableState;
import com.lladlam.melox.p012ui.player.MeloXPlaybackUiState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MeloXApp.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lladlam.melox.ui.MeloXAppKt$MeloXApp$1$1", f = "MeloXApp.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class MeloXAppKt$MeloXApp$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $openNowPlayingRequest;
    final /* synthetic */ MeloXPlaybackUiState $playbackState;
    final /* synthetic */ MutableState<Boolean> $showNowPlaying$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MeloXAppKt$MeloXApp$1$1(int i, MeloXPlaybackUiState meloXPlaybackUiState, MutableState<Boolean> mutableState, Continuation<? super MeloXAppKt$MeloXApp$1$1> continuation) {
        super(2, continuation);
        this.$openNowPlayingRequest = i;
        this.$playbackState = meloXPlaybackUiState;
        this.$showNowPlaying$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MeloXAppKt$MeloXApp$1$1(this.$openNowPlayingRequest, this.$playbackState, this.$showNowPlaying$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MeloXAppKt$MeloXApp$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (this.$openNowPlayingRequest > 0 && this.$playbackState.getHasMedia()) {
                    MeloXAppKt.MeloXApp$lambda$5(this.$showNowPlaying$delegate, true);
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
