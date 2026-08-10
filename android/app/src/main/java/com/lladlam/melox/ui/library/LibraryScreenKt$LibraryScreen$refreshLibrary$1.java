package com.lladlam.melox.ui.library;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: LibraryScreen.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lladlam.melox.ui.library.LibraryScreenKt", f = "LibraryScreen.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {120, 124, 127}, m = "LibraryScreen$refreshLibrary", n = {"$session", "loading$delegate", "errorMessage$delegate", "client", "cache", "snapshot$delegate", "$session", "loading$delegate", "errorMessage$delegate", "client", "cache", "snapshot$delegate", "userId", "$i$a$-runCatching-LibraryScreenKt$LibraryScreen$refreshLibrary$2\\1\\124\\0", "$session", "loading$delegate", "errorMessage$delegate", "client", "cache", "snapshot$delegate", "it\\2", "userId", "$i$a$-onSuccess-LibraryScreenKt$LibraryScreen$refreshLibrary$3\\2\\125\\0"}, nl = {121, 124, 128}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "J$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "J$0", "I$0"}, v = 2)
final class LibraryScreenKt$LibraryScreen$refreshLibrary$1 extends ContinuationImpl {
    int I$0;
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;
    /* synthetic */ Object result;

    LibraryScreenKt$LibraryScreen$refreshLibrary$1(Continuation<? super LibraryScreenKt$LibraryScreen$refreshLibrary$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return LibraryScreenKt.LibraryScreen$refreshLibrary(null, null, null, null, null, null, this);
    }
}
