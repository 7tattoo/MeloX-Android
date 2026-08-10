package com.lladlam.melox.p012ui.library;

import androidx.compose.runtime.MutableState;
import androidx.media3.extractor.ts.TsExtractor;
import com.lladlam.melox.core.account.NeteaseAccountProfile;
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
@DebugMetadata(c = "com.lladlam.melox.ui.library.LibraryScreenKt$LibraryScreen$1$1", f = "LibraryScreen.kt", i = {0, 1}, l = {TsExtractor.TS_STREAM_TYPE_E_AC3, 137}, m = "invokeSuspend", n = {"userId", "userId"}, nl = {1300, TsExtractor.TS_STREAM_TYPE_DTS_UHD}, s = {"J$0", "J$0"}, v = 2)
final class LibraryScreenKt$LibraryScreen$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ NeteaseLibraryCache $cache;
    final /* synthetic */ NeteaseLibraryClient $client;
    final /* synthetic */ MutableState<String> $errorMessage$delegate;
    final /* synthetic */ MutableState<Boolean> $loading$delegate;
    final /* synthetic */ NeteaseSessionStore $session;
    final /* synthetic */ MutableState<NeteaseLibrarySnapshot> $snapshot$delegate;
    long J$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LibraryScreenKt$LibraryScreen$1$1(NeteaseSessionStore neteaseSessionStore, NeteaseLibraryCache neteaseLibraryCache, MutableState<NeteaseLibrarySnapshot> mutableState, MutableState<Boolean> mutableState2, MutableState<String> mutableState3, NeteaseLibraryClient neteaseLibraryClient, Continuation<? super LibraryScreenKt$LibraryScreen$1$1> continuation) {
        super(2, continuation);
        this.$session = neteaseSessionStore;
        this.$cache = neteaseLibraryCache;
        this.$snapshot$delegate = mutableState;
        this.$loading$delegate = mutableState2;
        this.$errorMessage$delegate = mutableState3;
        this.$client = neteaseLibraryClient;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LibraryScreenKt$LibraryScreen$1$1(this.$session, this.$cache, this.$snapshot$delegate, this.$loading$delegate, this.$errorMessage$delegate, this.$client, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LibraryScreenKt$LibraryScreen$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0042  */
    /* JADX WARN: Code duplicated, block: B:18:0x0050  */
    /* JADX WARN: Code duplicated, block: B:20:0x006a A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:21:0x006b  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        long userId;
        Object objLoadSnapshot;
        NeteaseLibrarySnapshot neteaseLibrarySnapshot;
        long userId2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                NeteaseAccountProfile profile = this.$session.getProfile();
                if (profile == null) {
                    return Unit.INSTANCE;
                }
                userId = profile.getUserId();
                this.J$0 = userId;
                this.label = 1;
                objLoadSnapshot = this.$cache.loadSnapshot(userId, this);
                if (objLoadSnapshot == coroutine_suspended) {
                    return coroutine_suspended;
                }
                neteaseLibrarySnapshot = (NeteaseLibrarySnapshot) objLoadSnapshot;
                if (neteaseLibrarySnapshot != null) {
                    this.$snapshot$delegate.setValue(neteaseLibrarySnapshot);
                }
                if (NeteaseLibraryCache.INSTANCE.beginLibraryColdStartRefresh(userId)) {
                    this.J$0 = userId;
                    this.label = 2;
                    if (LibraryScreenKt.LibraryScreen$refreshLibrary(this.$session, this.$loading$delegate, this.$errorMessage$delegate, this.$client, this.$cache, this.$snapshot$delegate, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    userId2 = userId;
                }
                return Unit.INSTANCE;
            case 1:
                userId = this.J$0;
                ResultKt.throwOnFailure($result);
                objLoadSnapshot = $result;
                neteaseLibrarySnapshot = (NeteaseLibrarySnapshot) objLoadSnapshot;
                if (neteaseLibrarySnapshot != null) {
                    this.$snapshot$delegate.setValue(neteaseLibrarySnapshot);
                }
                if (NeteaseLibraryCache.INSTANCE.beginLibraryColdStartRefresh(userId)) {
                    this.J$0 = userId;
                    this.label = 2;
                    if (LibraryScreenKt.LibraryScreen$refreshLibrary(this.$session, this.$loading$delegate, this.$errorMessage$delegate, this.$client, this.$cache, this.$snapshot$delegate, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    userId2 = userId;
                }
                return Unit.INSTANCE;
            case 2:
                userId2 = this.J$0;
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
