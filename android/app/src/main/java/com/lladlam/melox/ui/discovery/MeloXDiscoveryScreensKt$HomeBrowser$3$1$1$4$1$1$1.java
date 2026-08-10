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
@DebugMetadata(c = "com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$HomeBrowser$3$1$1$4$1$1$1", f = "MeloXDiscoveryScreens.kt", i = {}, l = {330}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
final class MeloXDiscoveryScreensKt$HomeBrowser$3$1$1$4$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ NeteaseDiscoveryCache $cache;
    final /* synthetic */ NeteaseLibraryClient $client;
    final /* synthetic */ MutableState<String> $errorMessage$delegate;
    final /* synthetic */ MutableState<Boolean> $loading$delegate;
    final /* synthetic */ MutableState<NeteaseHomeSnapshot> $snapshot$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MeloXDiscoveryScreensKt$HomeBrowser$3$1$1$4$1$1$1(MutableState<Boolean> mutableState, MutableState<String> mutableState2, NeteaseLibraryClient neteaseLibraryClient, NeteaseDiscoveryCache neteaseDiscoveryCache, MutableState<NeteaseHomeSnapshot> mutableState3, Continuation<? super MeloXDiscoveryScreensKt$HomeBrowser$3$1$1$4$1$1$1> continuation) {
        super(2, continuation);
        this.$loading$delegate = mutableState;
        this.$errorMessage$delegate = mutableState2;
        this.$client = neteaseLibraryClient;
        this.$cache = neteaseDiscoveryCache;
        this.$snapshot$delegate = mutableState3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MeloXDiscoveryScreensKt$HomeBrowser$3$1$1$4$1$1$1(this.$loading$delegate, this.$errorMessage$delegate, this.$client, this.$cache, this.$snapshot$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MeloXDiscoveryScreensKt$HomeBrowser$3$1$1$4$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                if (MeloXDiscoveryScreensKt.HomeBrowser$refresh(this.$loading$delegate, this.$errorMessage$delegate, this.$client, this.$cache, this.$snapshot$delegate, this) == coroutine_suspended) {
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
