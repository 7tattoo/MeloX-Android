package com.lladlam.melox.ui.library;

import androidx.compose.runtime.MutableState;
import com.lladlam.melox.core.account.NeteaseSessionStore;
import com.lladlam.melox.core.library.NeteaseLibraryCache;
import com.lladlam.melox.core.library.NeteaseLibraryClient;
import com.lladlam.melox.core.library.NeteaseLibrarySnapshot;
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
@DebugMetadata(c = "com.lladlam.melox.ui.library.LibraryScreenKt$LibraryScreen$4$1$1", f = "LibraryScreen.kt", i = {}, l = {152}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
final class LibraryScreenKt$LibraryScreen$4$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ NeteaseLibraryCache $cache;
    final /* synthetic */ NeteaseLibraryClient $client;
    final /* synthetic */ MutableState<String> $errorMessage$delegate;
    final /* synthetic */ MutableState<Boolean> $loading$delegate;
    final /* synthetic */ NeteaseSessionStore $session;
    final /* synthetic */ MutableState<NeteaseLibrarySnapshot> $snapshot$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LibraryScreenKt$LibraryScreen$4$1$1(NeteaseSessionStore neteaseSessionStore, MutableState<Boolean> mutableState, MutableState<String> mutableState2, NeteaseLibraryClient neteaseLibraryClient, NeteaseLibraryCache neteaseLibraryCache, MutableState<NeteaseLibrarySnapshot> mutableState3, Continuation<? super LibraryScreenKt$LibraryScreen$4$1$1> continuation) {
        super(2, continuation);
        this.$session = neteaseSessionStore;
        this.$loading$delegate = mutableState;
        this.$errorMessage$delegate = mutableState2;
        this.$client = neteaseLibraryClient;
        this.$cache = neteaseLibraryCache;
        this.$snapshot$delegate = mutableState3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LibraryScreenKt$LibraryScreen$4$1$1(this.$session, this.$loading$delegate, this.$errorMessage$delegate, this.$client, this.$cache, this.$snapshot$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LibraryScreenKt$LibraryScreen$4$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                if (LibraryScreenKt.LibraryScreen$refreshLibrary(this.$session, this.$loading$delegate, this.$errorMessage$delegate, this.$client, this.$cache, this.$snapshot$delegate, this) == coroutine_suspended) {
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
