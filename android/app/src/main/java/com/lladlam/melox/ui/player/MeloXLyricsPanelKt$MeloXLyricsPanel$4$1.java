package com.lladlam.melox.ui.player;

import androidx.compose.foundation.lazy.LazyListState;
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
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MeloXLyricsPanel.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lladlam.melox.ui.player.MeloXLyricsPanelKt$MeloXLyricsPanel$4$1", f = "MeloXLyricsPanel.kt", i = {0, 0, 0, 0, 0}, l = {98}, m = "invokeSuspend", n = {"$this$LaunchedEffect", "$this$invokeSuspend_u24lambda_u240\\1", "index", "target", "$i$a$-runCatching-MeloXLyricsPanelKt$MeloXLyricsPanel$4$1$1\\1\\98\\0"}, nl = {98}, s = {"L$0", "L$1", "I$0", "I$1", "I$2"}, v = 2)
final class MeloXLyricsPanelKt$MeloXLyricsPanel$4$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Integer $highlightedIndex;
    final /* synthetic */ LazyListState $listState;
    int I$0;
    int I$1;
    int I$2;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MeloXLyricsPanelKt$MeloXLyricsPanel$4$1(Integer num, LazyListState lazyListState, Continuation<? super MeloXLyricsPanelKt$MeloXLyricsPanel$4$1> continuation) {
        super(2, continuation);
        this.$highlightedIndex = num;
        this.$listState = lazyListState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MeloXLyricsPanelKt$MeloXLyricsPanel$4$1 meloXLyricsPanelKt$MeloXLyricsPanel$4$1 = new MeloXLyricsPanelKt$MeloXLyricsPanel$4$1(this.$highlightedIndex, this.$listState, continuation);
        meloXLyricsPanelKt$MeloXLyricsPanel$4$1.L$0 = obj;
        return meloXLyricsPanelKt$MeloXLyricsPanel$4$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MeloXLyricsPanelKt$MeloXLyricsPanel$4$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        CoroutineScope $this$LaunchedEffect = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Integer num = this.$highlightedIndex;
                if (num == null) {
                    return Unit.INSTANCE;
                }
                int index = num.intValue();
                int target = RangesKt.coerceAtLeast(index - 2, 0);
                LazyListState lazyListState = this.$listState;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    this.L$0 = SpillingKt.nullOutSpilledVariable($this$LaunchedEffect);
                    this.L$1 = SpillingKt.nullOutSpilledVariable($this$LaunchedEffect);
                    this.I$0 = index;
                    this.I$1 = target;
                    this.I$2 = 0;
                    this.label = 1;
                    if (LazyListState.animateScrollToItem$default(lazyListState, target, 0, this, 2, null) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    Result.constructor-impl(Unit.INSTANCE);
                    return Unit.INSTANCE;
                } catch (Throwable th) {
                    th = th;
                    Result.Companion companion2 = Result.INSTANCE;
                    Result.constructor-impl(ResultKt.createFailure(th));
                }
                break;
            case 1:
                int i = this.I$2;
                int i2 = this.I$1;
                int i3 = this.I$0;
                try {
                    ResultKt.throwOnFailure($result);
                    Result.constructor-impl(Unit.INSTANCE);
                    break;
                } catch (Throwable th2) {
                    th = th2;
                    Result.Companion companion3 = Result.INSTANCE;
                    Result.constructor-impl(ResultKt.createFailure(th));
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
