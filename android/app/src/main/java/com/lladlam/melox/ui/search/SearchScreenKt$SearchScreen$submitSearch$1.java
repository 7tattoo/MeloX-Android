package com.lladlam.melox.p012ui.search;

import androidx.compose.runtime.MutableState;
import com.lladlam.melox.core.model.SearchSong;
import com.lladlam.melox.core.network.NeteaseSearchClient;
import java.util.List;
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
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SearchScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(m719c = "com.lladlam.melox.ui.search.SearchScreenKt$SearchScreen$submitSearch$1", m720f = "SearchScreen.kt", m721i = {0, 0, 0}, m722l = {68}, m723m = "invokeSuspend", m724n = {"$this$launch", "$this$invokeSuspend_u24lambda_u240\\1", "$i$a$-runCatching-SearchScreenKt$SearchScreen$submitSearch$1$1\\1\\68\\0"}, m725nl = {68}, m726s = {"L$0", "L$1", "I$0"}, m727v = 2)
final class SearchScreenKt$SearchScreen$submitSearch$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ NeteaseSearchClient $client;
    final /* synthetic */ MutableState<String> $errorMessage$delegate;
    final /* synthetic */ MutableState<Boolean> $isLoading$delegate;
    final /* synthetic */ String $keywords;
    final /* synthetic */ MutableState<List<SearchSong>> $results$delegate;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SearchScreenKt$SearchScreen$submitSearch$1(MutableState<Boolean> mutableState, MutableState<String> mutableState2, NeteaseSearchClient neteaseSearchClient, String str, MutableState<List<SearchSong>> mutableState3, Continuation<? super SearchScreenKt$SearchScreen$submitSearch$1> continuation) {
        super(2, continuation);
        this.$isLoading$delegate = mutableState;
        this.$errorMessage$delegate = mutableState2;
        this.$client = neteaseSearchClient;
        this.$keywords = str;
        this.$results$delegate = mutableState3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SearchScreenKt$SearchScreen$submitSearch$1 searchScreenKt$SearchScreen$submitSearch$1 = new SearchScreenKt$SearchScreen$submitSearch$1(this.$isLoading$delegate, this.$errorMessage$delegate, this.$client, this.$keywords, this.$results$delegate, continuation);
        searchScreenKt$SearchScreen$submitSearch$1.L$0 = obj;
        return searchScreenKt$SearchScreen$submitSearch$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SearchScreenKt$SearchScreen$submitSearch$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object objM9714constructorimpl;
        Object objSearchSongs$default;
        CoroutineScope $this$launch = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    SearchScreenKt.SearchScreen$lambda$9(this.$isLoading$delegate, true);
                    this.$errorMessage$delegate.setValue(null);
                    NeteaseSearchClient neteaseSearchClient = this.$client;
                    String str = this.$keywords;
                    Result.Companion companion = Result.INSTANCE;
                    this.L$0 = SpillingKt.nullOutSpilledVariable($this$launch);
                    this.L$1 = SpillingKt.nullOutSpilledVariable($this$launch);
                    this.I$0 = 0;
                    this.label = 1;
                    objSearchSongs$default = NeteaseSearchClient.searchSongs$default(neteaseSearchClient, str, 0, this, 2, null);
                    if (objSearchSongs$default == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    int i = this.I$0;
                    ResultKt.throwOnFailure($result);
                    objSearchSongs$default = $result;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            objM9714constructorimpl = Result.constructor_impl((List) objSearchSongs$default);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM9714constructorimpl = Result.constructor_impl(ResultKt.createFailure(th));
        }
        MutableState<List<SearchSong>> mutableState = this.$results$delegate;
        if (Result.isSuccess_impl(objM9714constructorimpl)) {
            mutableState.setValue((List) objM9714constructorimpl);
        }
        MutableState<String> mutableState2 = this.$errorMessage$delegate;
        Throwable thM9717exceptionOrNullimpl = Result.exceptionOrNull_impl(objM9714constructorimpl);
        if (thM9717exceptionOrNullimpl != null) {
            String message = thM9717exceptionOrNullimpl.getMessage();
            if (message == null) {
                message = "搜索失败";
            }
            mutableState2.setValue(message);
        }
        SearchScreenKt.SearchScreen$lambda$9(this.$isLoading$delegate, false);
        return Unit.INSTANCE;
    }
}
