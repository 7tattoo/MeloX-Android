package com.lladlam.melox.p012ui.library;

import androidx.compose.runtime.MutableState;
import com.lladlam.melox.core.library.NeteaseLibraryCache;
import com.lladlam.melox.core.library.NeteaseLibraryClient;
import com.lladlam.melox.core.library.NeteasePlaylistDetail;
import com.lladlam.melox.core.library.NeteasePlaylistSummary;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LibraryScreen.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(m719c = "com.lladlam.melox.ui.library.LibraryScreenKt$MeloXPlaylistDetailScreen$1$1", m720f = "LibraryScreen.kt", m721i = {}, m722l = {644, 647}, m723m = "invokeSuspend", m724n = {}, m725nl = {1300, 649}, m726s = {}, m727v = 2)
final class LibraryScreenKt$MeloXPlaylistDetailScreen$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ NeteaseLibraryCache $cache;
    final /* synthetic */ NeteaseLibraryClient $client;
    final /* synthetic */ MutableState<NeteasePlaylistDetail> $detail$delegate;
    final /* synthetic */ MutableState<String> $errorMessage$delegate;
    final /* synthetic */ NeteasePlaylistSummary $initialPlaylist;
    final /* synthetic */ MutableState<Boolean> $loading$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LibraryScreenKt$MeloXPlaylistDetailScreen$1$1(NeteaseLibraryCache neteaseLibraryCache, NeteasePlaylistSummary neteasePlaylistSummary, MutableState<NeteasePlaylistDetail> mutableState, MutableState<Boolean> mutableState2, MutableState<String> mutableState3, NeteaseLibraryClient neteaseLibraryClient, Continuation<? super LibraryScreenKt$MeloXPlaylistDetailScreen$1$1> continuation) {
        super(2, continuation);
        this.$cache = neteaseLibraryCache;
        this.$initialPlaylist = neteasePlaylistSummary;
        this.$detail$delegate = mutableState;
        this.$loading$delegate = mutableState2;
        this.$errorMessage$delegate = mutableState3;
        this.$client = neteaseLibraryClient;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LibraryScreenKt$MeloXPlaylistDetailScreen$1$1(this.$cache, this.$initialPlaylist, this.$detail$delegate, this.$loading$delegate, this.$errorMessage$delegate, this.$client, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LibraryScreenKt$MeloXPlaylistDetailScreen$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0036  */
    /* JADX WARN: Code duplicated, block: B:17:0x0047  */
    /* JADX WARN: Code duplicated, block: B:20:0x0059  */
    /* JADX WARN: Code duplicated, block: B:22:0x0071 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object objLoadPlaylistDetail;
        NeteasePlaylistDetail neteasePlaylistDetail;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                objLoadPlaylistDetail = this.$cache.loadPlaylistDetail(this.$initialPlaylist.getId(), this);
                if (objLoadPlaylistDetail == coroutine_suspended) {
                    return coroutine_suspended;
                }
                neteasePlaylistDetail = (NeteasePlaylistDetail) objLoadPlaylistDetail;
                if (neteasePlaylistDetail != null) {
                    this.$detail$delegate.setValue(neteasePlaylistDetail);
                }
                LibraryScreenKt.MeloXPlaylistDetailScreen$lambda$6(this.$loading$delegate, LibraryScreenKt.MeloXPlaylistDetailScreen$lambda$2(this.$detail$delegate) == null);
                if (NeteaseLibraryCache.INSTANCE.beginPlaylistColdStartRefresh(this.$initialPlaylist.getId())) {
                    this.label = 2;
                    if (LibraryScreenKt.MeloXPlaylistDetailScreen$refreshPlaylist(this.$loading$delegate, this.$errorMessage$delegate, this.$client, this.$initialPlaylist, this.$cache, this.$detail$delegate, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure($result);
                objLoadPlaylistDetail = $result;
                neteasePlaylistDetail = (NeteasePlaylistDetail) objLoadPlaylistDetail;
                if (neteasePlaylistDetail != null) {
                    this.$detail$delegate.setValue(neteasePlaylistDetail);
                }
                LibraryScreenKt.MeloXPlaylistDetailScreen$lambda$6(this.$loading$delegate, LibraryScreenKt.MeloXPlaylistDetailScreen$lambda$2(this.$detail$delegate) == null);
                if (NeteaseLibraryCache.INSTANCE.beginPlaylistColdStartRefresh(this.$initialPlaylist.getId())) {
                    this.label = 2;
                    if (LibraryScreenKt.MeloXPlaylistDetailScreen$refreshPlaylist(this.$loading$delegate, this.$errorMessage$delegate, this.$client, this.$initialPlaylist, this.$cache, this.$detail$delegate, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            case 2:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
