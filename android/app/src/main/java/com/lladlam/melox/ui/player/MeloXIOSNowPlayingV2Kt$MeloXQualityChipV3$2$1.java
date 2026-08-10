package com.lladlam.melox.p012ui.player;

import androidx.compose.runtime.MutableState;
import com.lladlam.melox.core.audio.MusicQuality;
import com.lladlam.melox.core.audio.MusicQualityRuntime;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: compiled from: MeloXIOSNowPlayingV2.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$MeloXQualityChipV3$2$1", f = "MeloXIOSNowPlayingV2.kt", i = {0}, l = {516}, m = "invokeSuspend", n = {"songId"}, nl = {-1}, s = {"J$0"}, v = 2)
final class MeloXIOSNowPlayingV2Kt$MeloXQualityChipV3$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<MusicQuality> $actual$delegate;
    final /* synthetic */ MeloXPlaybackUiState $state;
    long J$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MeloXIOSNowPlayingV2Kt$MeloXQualityChipV3$2$1(MeloXPlaybackUiState meloXPlaybackUiState, MutableState<MusicQuality> mutableState, Continuation<? super MeloXIOSNowPlayingV2Kt$MeloXQualityChipV3$2$1> continuation) {
        super(2, continuation);
        this.$state = meloXPlaybackUiState;
        this.$actual$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MeloXIOSNowPlayingV2Kt$MeloXQualityChipV3$2$1(this.$state, this.$actual$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MeloXIOSNowPlayingV2Kt$MeloXQualityChipV3$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Long longOrNull;
        long songId;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                String mediaId = this.$state.getMediaId();
                if (mediaId == null || (longOrNull = StringsKt.toLongOrNull(mediaId)) == null) {
                    return Unit.INSTANCE;
                }
                songId = longOrNull.longValue();
                break;
            case 1:
                songId = this.J$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        do {
            this.$actual$delegate.setValue(MusicQualityRuntime.INSTANCE.actualFor(Boxing.boxLong(songId)));
            this.J$0 = songId;
            this.label = 1;
        } while (DelayKt.delay(180L, this) != coroutine_suspended);
        return coroutine_suspended;
    }
}
