package com.lladlam.melox.ui.player;

import androidx.compose.runtime.MutableState;
import com.lladlam.melox.core.audio.NeteaseQualityClient;
import com.lladlam.melox.core.audio.SongAudioAvailability;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MeloXIOSNowPlayingV2.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$MeloXQualityChipV3$1$1", f = "MeloXIOSNowPlayingV2.kt", i = {0, 0, 0, 0}, l = {509}, m = "invokeSuspend", n = {"$this$LaunchedEffect", "$this$invokeSuspend_u24lambda_u240\\1", "songId", "$i$a$-runCatching-MeloXIOSNowPlayingV2Kt$MeloXQualityChipV3$1$1$1\\1\\509\\0"}, nl = {509}, s = {"L$0", "L$1", "J$0", "I$0"}, v = 2)
final class MeloXIOSNowPlayingV2Kt$MeloXQualityChipV3$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<SongAudioAvailability> $availability$delegate;
    final /* synthetic */ NeteaseQualityClient $qualityClient;
    final /* synthetic */ MeloXPlaybackUiState $state;
    int I$0;
    long J$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MeloXIOSNowPlayingV2Kt$MeloXQualityChipV3$1$1(MeloXPlaybackUiState meloXPlaybackUiState, NeteaseQualityClient neteaseQualityClient, MutableState<SongAudioAvailability> mutableState, Continuation<? super MeloXIOSNowPlayingV2Kt$MeloXQualityChipV3$1$1> continuation) {
        super(2, continuation);
        this.$state = meloXPlaybackUiState;
        this.$qualityClient = neteaseQualityClient;
        this.$availability$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MeloXIOSNowPlayingV2Kt$MeloXQualityChipV3$1$1 meloXIOSNowPlayingV2Kt$MeloXQualityChipV3$1$1 = new MeloXIOSNowPlayingV2Kt$MeloXQualityChipV3$1$1(this.$state, this.$qualityClient, this.$availability$delegate, continuation);
        meloXIOSNowPlayingV2Kt$MeloXQualityChipV3$1$1.L$0 = obj;
        return meloXIOSNowPlayingV2Kt$MeloXQualityChipV3$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MeloXIOSNowPlayingV2Kt$MeloXQualityChipV3$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object objM9714constructorimpl;
        Long longOrNull;
        MutableState<SongAudioAvailability> mutableState;
        Object objAudioAvailability;
        CoroutineScope $this$LaunchedEffect = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    String mediaId = this.$state.getMediaId();
                    if (mediaId == null || (longOrNull = StringsKt.toLongOrNull(mediaId)) == null) {
                        return Unit.INSTANCE;
                    }
                    long songId = longOrNull.longValue();
                    mutableState = this.$availability$delegate;
                    NeteaseQualityClient neteaseQualityClient = this.$qualityClient;
                    Result.Companion companion = Result.INSTANCE;
                    this.L$0 = SpillingKt.nullOutSpilledVariable($this$LaunchedEffect);
                    this.L$1 = SpillingKt.nullOutSpilledVariable($this$LaunchedEffect);
                    this.L$2 = mutableState;
                    this.J$0 = songId;
                    this.I$0 = 0;
                    this.label = 1;
                    objAudioAvailability = neteaseQualityClient.audioAvailability(songId, this);
                    if (objAudioAvailability == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    int i = this.I$0;
                    long j = this.J$0;
                    mutableState = (MutableState) this.L$2;
                    ResultKt.throwOnFailure($result);
                    objAudioAvailability = $result;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            objM9714constructorimpl = Result.constructor-impl((SongAudioAvailability) objAudioAvailability);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM9714constructorimpl = Result.constructor-impl(ResultKt.createFailure(th));
        }
        SongAudioAvailability unknown = SongAudioAvailability.INSTANCE.getUnknown();
        if (Result.m9720isFailureimpl(objM9714constructorimpl)) {
            objM9714constructorimpl = unknown;
        }
        mutableState.setValue((SongAudioAvailability) objM9714constructorimpl);
        return Unit.INSTANCE;
    }
}
