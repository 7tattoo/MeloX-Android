package com.lladlam.melox.p012ui.discovery;

import androidx.compose.runtime.MutableState;
import com.lladlam.melox.core.library.NeteaseDiscoveryCache;
import com.lladlam.melox.core.library.NeteaseHomeSnapshot;
import com.lladlam.melox.core.library.NeteaseLibraryClient;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MeloXDiscoveryScreens.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$HomeBrowser$1$1", f = "MeloXDiscoveryScreens.kt", i = {}, l = {283, 284}, m = "invokeSuspend", n = {}, nl = {284, 285}, s = {}, v = 2)
final class MeloXDiscoveryScreensKt$HomeBrowser$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ NeteaseDiscoveryCache $cache;
    final /* synthetic */ NeteaseLibraryClient $client;
    final /* synthetic */ MutableState<String> $errorMessage$delegate;
    final /* synthetic */ MutableState<Boolean> $loading$delegate;
    final /* synthetic */ MutableState<NeteaseHomeSnapshot> $snapshot$delegate;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MeloXDiscoveryScreensKt$HomeBrowser$1$1(NeteaseDiscoveryCache neteaseDiscoveryCache, MutableState<NeteaseHomeSnapshot> mutableState, MutableState<Boolean> mutableState2, MutableState<String> mutableState3, NeteaseLibraryClient neteaseLibraryClient, Continuation<? super MeloXDiscoveryScreensKt$HomeBrowser$1$1> continuation) {
        super(2, continuation);
        this.$cache = neteaseDiscoveryCache;
        this.$snapshot$delegate = mutableState;
        this.$loading$delegate = mutableState2;
        this.$errorMessage$delegate = mutableState3;
        this.$client = neteaseLibraryClient;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MeloXDiscoveryScreensKt$HomeBrowser$1$1(this.$cache, this.$snapshot$delegate, this.$loading$delegate, this.$errorMessage$delegate, this.$client, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MeloXDiscoveryScreensKt$HomeBrowser$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0041  */
    /* JADX WARN: Code duplicated, block: B:15:0x005a A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        MutableState<NeteaseHomeSnapshot> mutableState;
        Object objLoadHome;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                mutableState = this.$snapshot$delegate;
                this.L$0 = mutableState;
                this.label = 1;
                objLoadHome = this.$cache.loadHome(this);
                if (objLoadHome == coroutine_suspended) {
                    return coroutine_suspended;
                }
                mutableState.setValue((NeteaseHomeSnapshot) objLoadHome);
                if (NeteaseDiscoveryCache.INSTANCE.beginHomeColdStartRefresh()) {
                    this.L$0 = null;
                    this.label = 2;
                    if (MeloXDiscoveryScreensKt.HomeBrowser$refresh(this.$loading$delegate, this.$errorMessage$delegate, this.$client, this.$cache, this.$snapshot$delegate, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            case 1:
                mutableState = (MutableState) this.L$0;
                ResultKt.throwOnFailure($result);
                objLoadHome = $result;
                mutableState.setValue((NeteaseHomeSnapshot) objLoadHome);
                if (NeteaseDiscoveryCache.INSTANCE.beginHomeColdStartRefresh()) {
                    this.L$0 = null;
                    this.label = 2;
                    if (MeloXDiscoveryScreensKt.HomeBrowser$refresh(this.$loading$delegate, this.$errorMessage$delegate, this.$client, this.$cache, this.$snapshot$delegate, this) == coroutine_suspended) {
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
