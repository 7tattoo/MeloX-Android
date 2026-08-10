package com.lladlam.melox.ui.search;

import androidx.compose.runtime.MutableState;
import com.lladlam.melox.core.model.SearchSong;
import com.lladlam.melox.core.network.NeteaseSearchClient;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SearchScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final /* synthetic */ class SearchScreenKt$SearchScreen$1$1$2$1 extends FunctionReferenceImpl implements Function0<Unit> {
    final /* synthetic */ NeteaseSearchClient $client;
    final /* synthetic */ MutableState<String> $errorMessage$delegate;
    final /* synthetic */ MutableState<Boolean> $isLoading$delegate;
    final /* synthetic */ MutableState<String> $query$delegate;
    final /* synthetic */ MutableState<List<SearchSong>> $results$delegate;
    final /* synthetic */ CoroutineScope $scope;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SearchScreenKt$SearchScreen$1$1$2$1(CoroutineScope coroutineScope, MutableState<String> mutableState, MutableState<Boolean> mutableState2, MutableState<String> mutableState3, NeteaseSearchClient neteaseSearchClient, MutableState<List<SearchSong>> mutableState4) {
        super(0, Intrinsics.Kotlin.class, "submitSearch", "SearchScreen$submitSearch(Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/runtime/MutableState;Landroidx/compose/runtime/MutableState;Landroidx/compose/runtime/MutableState;Lcom/lladlam/melox/core/network/NeteaseSearchClient;Landroidx/compose/runtime/MutableState;)V", 0);
        this.$scope = coroutineScope;
        this.$query$delegate = mutableState;
        this.$isLoading$delegate = mutableState2;
        this.$errorMessage$delegate = mutableState3;
        this.$client = neteaseSearchClient;
        this.$results$delegate = mutableState4;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        SearchScreenKt.SearchScreen$submitSearch(this.$scope, this.$query$delegate, this.$isLoading$delegate, this.$errorMessage$delegate, this.$client, this.$results$delegate);
    }
}
