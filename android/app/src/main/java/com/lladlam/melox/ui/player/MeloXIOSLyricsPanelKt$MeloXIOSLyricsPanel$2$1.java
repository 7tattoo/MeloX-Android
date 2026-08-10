package com.lladlam.melox.p012ui.player;

import android.os.SystemClock;
import androidx.compose.runtime.MutableLongState;
import androidx.media3.extractor.ts.TsExtractor;
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

/* JADX INFO: compiled from: MeloXIOSLyricsPanel.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lladlam.melox.ui.player.MeloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$2$1", f = "MeloXIOSLyricsPanel.kt", i = {}, l = {TsExtractor.TS_STREAM_TYPE_DVBSUBS}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
final class MeloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableLongState $anchorPositionMs$delegate;
    final /* synthetic */ MutableLongState $anchorRealtimeMs$delegate;
    final /* synthetic */ MutableLongState $renderedPositionMs$delegate;
    final /* synthetic */ MeloXPlaybackUiState $state;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MeloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$2$1(MeloXPlaybackUiState meloXPlaybackUiState, MutableLongState mutableLongState, MutableLongState mutableLongState2, MutableLongState mutableLongState3, Continuation<? super MeloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$2$1> continuation) {
        super(2, continuation);
        this.$state = meloXPlaybackUiState;
        this.$anchorPositionMs$delegate = mutableLongState;
        this.$anchorRealtimeMs$delegate = mutableLongState2;
        this.$renderedPositionMs$delegate = mutableLongState3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MeloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$2$1(this.$state, this.$anchorPositionMs$delegate, this.$anchorRealtimeMs$delegate, this.$renderedPositionMs$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MeloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            this.$renderedPositionMs$delegate.setLongValue(this.$state.isPlaying() ? MeloXIOSLyricsPanelKt.MeloXIOSLyricsPanel$lambda$11(this.$anchorPositionMs$delegate) + (SystemClock.elapsedRealtime() - MeloXIOSLyricsPanelKt.MeloXIOSLyricsPanel$lambda$14(this.$anchorRealtimeMs$delegate)) : MeloXIOSLyricsPanelKt.MeloXIOSLyricsPanel$lambda$11(this.$anchorPositionMs$delegate));
            j = this.$state.isPlaying() ? 16L : 200L;
            this.label = 1;
        } while (DelayKt.delay(j, this) != coroutine_suspended);
        return coroutine_suspended;
    }
}
