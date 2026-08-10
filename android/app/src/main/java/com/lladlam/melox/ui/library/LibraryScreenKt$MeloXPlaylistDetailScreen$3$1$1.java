package com.lladlam.melox.ui.library;

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
@DebugMetadata(c = "com.lladlam.melox.ui.library.LibraryScreenKt$MeloXPlaylistDetailScreen$3$1$1", f = "LibraryScreen.kt", i = {}, l = {671}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
final class LibraryScreenKt$MeloXPlaylistDetailScreen$3$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ NeteaseLibraryCache $cache;
    final /* synthetic */ NeteaseLibraryClient $client;
    final /* synthetic */ MutableState<NeteasePlaylistDetail> $detail$delegate;
    final /* synthetic */ MutableState<String> $errorMessage$delegate;
    final /* synthetic */ NeteasePlaylistSummary $initialPlaylist;
    final /* synthetic */ MutableState<Boolean> $loading$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LibraryScreenKt$MeloXPlaylistDetailScreen$3$1$1(MutableState<Boolean> mutableState, MutableState<String> mutableState2, NeteaseLibraryClient neteaseLibraryClient, NeteasePlaylistSummary neteasePlaylistSummary, NeteaseLibraryCache neteaseLibraryCache, MutableState<NeteasePlaylistDetail> mutableState3, Continuation<? super LibraryScreenKt$MeloXPlaylistDetailScreen$3$1$1> continuation) {
        super(2, continuation);
        this.$loading$delegate = mutableState;
        this.$errorMessage$delegate = mutableState2;
        this.$client = neteaseLibraryClient;
        this.$initialPlaylist = neteasePlaylistSummary;
        this.$cache = neteaseLibraryCache;
        this.$detail$delegate = mutableState3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LibraryScreenKt$MeloXPlaylistDetailScreen$3$1$1(this.$loading$delegate, this.$errorMessage$delegate, this.$client, this.$initialPlaylist, this.$cache, this.$detail$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LibraryScreenKt$MeloXPlaylistDetailScreen$3$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                if (LibraryScreenKt.MeloXPlaylistDetailScreen$refreshPlaylist(this.$loading$delegate, this.$errorMessage$delegate, this.$client, this.$initialPlaylist, this.$cache, this.$detail$delegate, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
