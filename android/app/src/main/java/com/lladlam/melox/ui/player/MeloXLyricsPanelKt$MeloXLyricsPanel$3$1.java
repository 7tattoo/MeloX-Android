package com.lladlam.melox.ui.player;

import androidx.compose.runtime.MutableState;
import com.lladlam.melox.core.lyrics.LyricsDocument;
import com.lladlam.melox.core.network.NeteaseSearchClient;
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

/* JADX INFO: compiled from: MeloXLyricsPanel.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lladlam.melox.ui.player.MeloXLyricsPanelKt$MeloXLyricsPanel$3$1", f = "MeloXLyricsPanel.kt", i = {0, 0, 0, 0}, l = {86}, m = "invokeSuspend", n = {"$this$LaunchedEffect", "$this$invokeSuspend_u24lambda_u240\\1", "songId", "$i$a$-runCatching-MeloXLyricsPanelKt$MeloXLyricsPanel$3$1$1\\1\\86\\0"}, nl = {86}, s = {"L$0", "L$1", "J$0", "I$0"}, v = 2)
final class MeloXLyricsPanelKt$MeloXLyricsPanel$3$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ NeteaseSearchClient $client;
    final /* synthetic */ MutableState<String> $errorMessage$delegate;
    final /* synthetic */ MutableState<Boolean> $isLoading$delegate;
    final /* synthetic */ MutableState<LyricsDocument> $lyrics$delegate;
    final /* synthetic */ String $mediaId;
    int I$0;
    long J$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MeloXLyricsPanelKt$MeloXLyricsPanel$3$1(String str, MutableState<Boolean> mutableState, MutableState<String> mutableState2, NeteaseSearchClient neteaseSearchClient, MutableState<LyricsDocument> mutableState3, Continuation<? super MeloXLyricsPanelKt$MeloXLyricsPanel$3$1> continuation) {
        super(2, continuation);
        this.$mediaId = str;
        this.$isLoading$delegate = mutableState;
        this.$errorMessage$delegate = mutableState2;
        this.$client = neteaseSearchClient;
        this.$lyrics$delegate = mutableState3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MeloXLyricsPanelKt$MeloXLyricsPanel$3$1 meloXLyricsPanelKt$MeloXLyricsPanel$3$1 = new MeloXLyricsPanelKt$MeloXLyricsPanel$3$1(this.$mediaId, this.$isLoading$delegate, this.$errorMessage$delegate, this.$client, this.$lyrics$delegate, continuation);
        meloXLyricsPanelKt$MeloXLyricsPanel$3$1.L$0 = obj;
        return meloXLyricsPanelKt$MeloXLyricsPanel$3$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MeloXLyricsPanelKt$MeloXLyricsPanel$3$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object objM9714constructorimpl;
        Long longOrNull;
        Object objLyrics;
        CoroutineScope $this$LaunchedEffect = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    String str = this.$mediaId;
                    if (str == null || (longOrNull = StringsKt.toLongOrNull(str)) == null) {
                        return Unit.INSTANCE;
                    }
                    long songId = longOrNull.longValue();
                    MeloXLyricsPanelKt.MeloXLyricsPanel$lambda$6(this.$isLoading$delegate, true);
                    this.$errorMessage$delegate.setValue(null);
                    NeteaseSearchClient neteaseSearchClient = this.$client;
                    Result.Companion companion = Result.INSTANCE;
                    this.L$0 = SpillingKt.nullOutSpilledVariable($this$LaunchedEffect);
                    this.L$1 = SpillingKt.nullOutSpilledVariable($this$LaunchedEffect);
                    this.J$0 = songId;
                    this.I$0 = 0;
                    this.label = 1;
                    objLyrics = neteaseSearchClient.lyrics(songId, this);
                    if (objLyrics == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    int i = this.I$0;
                    long j = this.J$0;
                    ResultKt.throwOnFailure($result);
                    objLyrics = $result;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            objM9714constructorimpl = Result.constructor-impl((LyricsDocument) objLyrics);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM9714constructorimpl = Result.constructor-impl(ResultKt.createFailure(th));
        }
        MutableState<LyricsDocument> mutableState = this.$lyrics$delegate;
        if (Result.m9721isSuccessimpl(objM9714constructorimpl)) {
            mutableState.setValue((LyricsDocument) objM9714constructorimpl);
        }
        MutableState<String> mutableState2 = this.$errorMessage$delegate;
        Throwable thM9717exceptionOrNullimpl = Result.m9717exceptionOrNullimpl(objM9714constructorimpl);
        if (thM9717exceptionOrNullimpl != null) {
            String message = thM9717exceptionOrNullimpl.getMessage();
            if (message == null) {
                message = "歌词加载失败";
            }
            mutableState2.setValue(message);
        }
        MeloXLyricsPanelKt.MeloXLyricsPanel$lambda$6(this.$isLoading$delegate, false);
        return Unit.INSTANCE;
    }
}
