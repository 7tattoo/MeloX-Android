package com.lladlam.melox.p012ui.player;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: compiled from: MeloXPlayerUi.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(m719c = "com.lladlam.melox.ui.player.MeloXPlayerUiKt$rememberMeloXPlaybackUiState$2$1", m720f = "MeloXPlayerUi.kt", m721i = {}, m722l = {272}, m723m = "invokeSuspend", m724n = {}, m725nl = {-1}, m726s = {}, m727v = 2)
final class MeloXPlayerUiKt$rememberMeloXPlaybackUiState$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MeloXPlaybackUiState $state;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MeloXPlayerUiKt$rememberMeloXPlaybackUiState$2$1(MeloXPlaybackUiState meloXPlaybackUiState, Continuation<? super MeloXPlayerUiKt$rememberMeloXPlaybackUiState$2$1> continuation) {
        super(2, continuation);
        this.$state = meloXPlaybackUiState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MeloXPlayerUiKt$rememberMeloXPlaybackUiState$2$1(this.$state, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MeloXPlayerUiKt$rememberMeloXPlaybackUiState$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        long j;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        do {
            this.$state.refresh$app();
            j = this.$state.isPlaying() ? 500L : 1000L;
            this.label = 1;
        } while (DelayKt.delay(j, this) != coroutine_suspended);
        return coroutine_suspended;
    }
}
