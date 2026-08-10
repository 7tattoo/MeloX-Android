package com.lladlam.melox.p012ui.discovery;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: MeloXDiscoveryScreens.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(m719c = "com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt", m720f = "MeloXDiscoveryScreens.kt", m721i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, m722l = {155, 159}, m723m = "ExploreBrowser$refresh", m724n = {"memoryCache", "category$delegate", "playlists$delegate", "loading$delegate", "errorMessage$delegate", "client", "cache", "force", "$i$a$-runCatching-MeloXDiscoveryScreensKt$ExploreBrowser$refresh$2\\1\\155\\0", "memoryCache", "category$delegate", "playlists$delegate", "loading$delegate", "errorMessage$delegate", "client", "cache", "loaded\\2", "force", "$i$a$-onSuccess-MeloXDiscoveryScreensKt$ExploreBrowser$refresh$3\\2\\156\\0"}, m725nl = {155, 160}, m726s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$8", "Z$0", "I$0"}, m727v = 2)
final class MeloXDiscoveryScreensKt$ExploreBrowser$refresh$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;

    MeloXDiscoveryScreensKt$ExploreBrowser$refresh$1(Continuation<? super MeloXDiscoveryScreensKt$ExploreBrowser$refresh$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return MeloXDiscoveryScreensKt.ExploreBrowser$refresh(null, null, null, null, null, null, null, false, this);
    }
}
