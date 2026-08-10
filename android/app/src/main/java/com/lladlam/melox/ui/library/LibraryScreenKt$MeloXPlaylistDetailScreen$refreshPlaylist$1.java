package com.lladlam.melox.ui.library;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: LibraryScreen.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lladlam.melox.ui.library.LibraryScreenKt", f = "LibraryScreen.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1}, l = {634, 637}, m = "MeloXPlaylistDetailScreen$refreshPlaylist", n = {"loading$delegate", "errorMessage$delegate", "$client", "$initialPlaylist", "cache", "detail$delegate", "$i$a$-runCatching-LibraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$2\\1\\634\\0", "loading$delegate", "errorMessage$delegate", "$client", "$initialPlaylist", "cache", "detail$delegate", "it\\2", "$i$a$-onSuccess-LibraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$3\\2\\635\\0"}, nl = {634, 638}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "I$0"}, v = 2)
final class LibraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1 extends ContinuationImpl {
    int I$0;
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

    LibraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1(Continuation<? super LibraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return LibraryScreenKt.MeloXPlaylistDetailScreen$refreshPlaylist(null, null, null, null, null, null, this);
    }
}
