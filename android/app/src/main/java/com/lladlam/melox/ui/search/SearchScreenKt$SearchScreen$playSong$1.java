package com.lladlam.melox.ui.search;

import android.content.Context;
import androidx.compose.runtime.MutableState;
import com.lladlam.melox.core.model.SearchSong;
import com.lladlam.melox.core.network.NeteaseSearchClient;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SearchScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lladlam.melox.ui.search.SearchScreenKt$SearchScreen$playSong$1", f = "SearchScreen.kt", i = {0, 0, 0}, l = {85}, m = "invokeSuspend", n = {"$this$launch", "$this$invokeSuspend_u24lambda_u240\\1", "$i$a$-runCatching-SearchScreenKt$SearchScreen$playSong$1$1\\1\\81\\0"}, nl = {81}, s = {"L$0", "L$1", "I$0"}, v = 2)
final class SearchScreenKt$SearchScreen$playSong$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ NeteaseSearchClient $client;
    final /* synthetic */ Context $context;
    final /* synthetic */ MutableState<String> $errorMessage$delegate;
    final /* synthetic */ MutableState<Long> $resolvingSongId$delegate;
    final /* synthetic */ MutableState<List<SearchSong>> $results$delegate;
    final /* synthetic */ SearchSong $song;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SearchScreenKt$SearchScreen$playSong$1(SearchSong searchSong, MutableState<Long> mutableState, MutableState<String> mutableState2, NeteaseSearchClient neteaseSearchClient, MutableState<List<SearchSong>> mutableState3, Context context, Continuation<? super SearchScreenKt$SearchScreen$playSong$1> continuation) {
        super(2, continuation);
        this.$song = searchSong;
        this.$resolvingSongId$delegate = mutableState;
        this.$errorMessage$delegate = mutableState2;
        this.$client = neteaseSearchClient;
        this.$results$delegate = mutableState3;
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SearchScreenKt$SearchScreen$playSong$1 searchScreenKt$SearchScreen$playSong$1 = new SearchScreenKt$SearchScreen$playSong$1(this.$song, this.$resolvingSongId$delegate, this.$errorMessage$delegate, this.$client, this.$results$delegate, this.$context, continuation);
        searchScreenKt$SearchScreen$playSong$1.L$0 = obj;
        return searchScreenKt$SearchScreen$playSong$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SearchScreenKt$SearchScreen$playSong$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:33:0x00b8  */
    /*  JADX ERROR: JadxRuntimeException in pass: SwitchBreakVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r14v4 java.lang.Object, still in use, count: 2, list:
          (r14v4 java.lang.Object) from 0x00b3: PHI (r14 I:??) = (r14v1 java.lang.Object), (r14v4 java.lang.Object) binds: [B:29:0x00b2, B:46:0x00b3] A[DONT_GENERATE, DONT_INLINE]
          (r14v4 java.lang.Object A[D('element\3' java.lang.Object)]) from 0x009a: CHECK_CAST (com.lladlam.melox.core.model.SearchSong) (r14v4 java.lang.Object A[D('element\3' java.lang.Object)])
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:93)
        	at jadx.core.dex.visitors.regions.TernaryMod.makeTernaryInsn(TernaryMod.java:132)
        	at jadx.core.dex.visitors.regions.TernaryMod.processRegion(TernaryMod.java:67)
        	at jadx.core.dex.visitors.regions.TernaryMod.enterRegion(TernaryMod.java:50)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:96)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.TernaryMod.process(TernaryMod.java:36)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.process(IfRegionVisitor.java:44)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.processIfRequested(IfRegionVisitor.java:36)
        	at jadx.core.dex.visitors.regions.SwitchBreakVisitor.visit(SwitchBreakVisitor.java:47)
        */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final java.lang.Object invokeSuspend(java.lang.Object r22) {
        /*
            Method dump skipped, instruction units count: 246
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lladlam.melox.ui.search.SearchScreenKt$SearchScreen$playSong$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$1$1(MutableState $errorMessage$delegate, Throwable error) {
        String message = error.getMessage();
        if (message == null) {
            message = "播放器连接失败";
        }
        $errorMessage$delegate.setValue(message);
        return Unit.INSTANCE;
    }
}
