package com.lladlam.melox.ui.discovery;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.snapshots.SnapshotStateMap;
import com.lladlam.melox.core.library.NeteaseDiscoveryCache;
import com.lladlam.melox.core.library.NeteaseLibraryClient;
import com.lladlam.melox.core.library.NeteasePlaylistSummary;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MeloXDiscoveryScreens.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$ExploreBrowser$1$1", f = "MeloXDiscoveryScreens.kt", i = {0, 1}, l = {171, 174}, m = "invokeSuspend", n = {"requested", "requested"}, nl = {171, 176}, s = {"L$0", "L$0"}, v = 2)
final class MeloXDiscoveryScreensKt$ExploreBrowser$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ NeteaseDiscoveryCache $cache;
    final /* synthetic */ MutableState<String> $category$delegate;
    final /* synthetic */ NeteaseLibraryClient $client;
    final /* synthetic */ MutableState<String> $errorMessage$delegate;
    final /* synthetic */ MutableState<Boolean> $loading$delegate;
    final /* synthetic */ SnapshotStateMap<String, List<NeteasePlaylistSummary>> $memoryCache;
    final /* synthetic */ MutableState<List<NeteasePlaylistSummary>> $playlists$delegate;
    Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MeloXDiscoveryScreensKt$ExploreBrowser$1$1(SnapshotStateMap<String, List<NeteasePlaylistSummary>> snapshotStateMap, NeteaseDiscoveryCache neteaseDiscoveryCache, MutableState<String> mutableState, MutableState<List<NeteasePlaylistSummary>> mutableState2, MutableState<Boolean> mutableState3, MutableState<String> mutableState4, NeteaseLibraryClient neteaseLibraryClient, Continuation<? super MeloXDiscoveryScreensKt$ExploreBrowser$1$1> continuation) {
        super(2, continuation);
        this.$memoryCache = snapshotStateMap;
        this.$cache = neteaseDiscoveryCache;
        this.$category$delegate = mutableState;
        this.$playlists$delegate = mutableState2;
        this.$loading$delegate = mutableState3;
        this.$errorMessage$delegate = mutableState4;
        this.$client = neteaseLibraryClient;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MeloXDiscoveryScreensKt$ExploreBrowser$1$1(this.$memoryCache, this.$cache, this.$category$delegate, this.$playlists$delegate, this.$loading$delegate, this.$errorMessage$delegate, this.$client, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MeloXDiscoveryScreensKt$ExploreBrowser$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x005c  */
    /* JADX WARN: Code duplicated, block: B:20:0x0078  */
    /* JADX WARN: Code duplicated, block: B:22:0x009c A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:23:0x009d  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) throws NoSuchAlgorithmException {
        String requested;
        MutableState<List<NeteasePlaylistSummary>> mutableState;
        Object objLoadCategory;
        List listEmptyList;
        String requested2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                requested = MeloXDiscoveryScreensKt.ExploreBrowser$lambda$4(this.$category$delegate);
                List<NeteasePlaylistSummary> list = this.$memoryCache.get(requested);
                mutableState = this.$playlists$delegate;
                if (list == null) {
                    this.L$0 = requested;
                    this.L$1 = mutableState;
                    this.label = 1;
                    objLoadCategory = this.$cache.loadCategory(requested, this);
                    if (objLoadCategory == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    listEmptyList = (List) objLoadCategory;
                    if (listEmptyList == null) {
                        listEmptyList = CollectionsKt.emptyList();
                    }
                    mutableState.setValue(listEmptyList);
                    this.$memoryCache.put(requested, MeloXDiscoveryScreensKt.ExploreBrowser$lambda$7(this.$playlists$delegate));
                    if (NeteaseDiscoveryCache.INSTANCE.beginCategoryColdStartRefresh(requested)) {
                        this.L$0 = SpillingKt.nullOutSpilledVariable(requested);
                        this.L$1 = null;
                        this.label = 2;
                        if (MeloXDiscoveryScreensKt.ExploreBrowser$refresh(this.$memoryCache, this.$category$delegate, this.$playlists$delegate, this.$loading$delegate, this.$errorMessage$delegate, this.$client, this.$cache, true, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        requested2 = requested;
                    }
                    return Unit.INSTANCE;
                }
                mutableState.setValue(list);
                return Unit.INSTANCE;
            case 1:
                MutableState<List<NeteasePlaylistSummary>> mutableState2 = (MutableState) this.L$1;
                String requested3 = (String) this.L$0;
                ResultKt.throwOnFailure($result);
                mutableState = mutableState2;
                requested = requested3;
                objLoadCategory = $result;
                listEmptyList = (List) objLoadCategory;
                if (listEmptyList == null) {
                    listEmptyList = CollectionsKt.emptyList();
                }
                mutableState.setValue(listEmptyList);
                this.$memoryCache.put(requested, MeloXDiscoveryScreensKt.ExploreBrowser$lambda$7(this.$playlists$delegate));
                if (NeteaseDiscoveryCache.INSTANCE.beginCategoryColdStartRefresh(requested)) {
                    this.L$0 = SpillingKt.nullOutSpilledVariable(requested);
                    this.L$1 = null;
                    this.label = 2;
                    if (MeloXDiscoveryScreensKt.ExploreBrowser$refresh(this.$memoryCache, this.$category$delegate, this.$playlists$delegate, this.$loading$delegate, this.$errorMessage$delegate, this.$client, this.$cache, true, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    requested2 = requested;
                }
                return Unit.INSTANCE;
            case 2:
                requested2 = (String) this.L$0;
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
