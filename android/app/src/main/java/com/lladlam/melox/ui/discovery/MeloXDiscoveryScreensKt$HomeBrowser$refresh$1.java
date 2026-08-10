package com.lladlam.melox.p012ui.discovery;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: MeloXDiscoveryScreens.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt", f = "MeloXDiscoveryScreens.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1}, l = {273, 276}, m = "HomeBrowser$refresh", n = {"loading$delegate", "errorMessage$delegate", "client", "cache", "snapshot$delegate", "$i$a$-runCatching-MeloXDiscoveryScreensKt$HomeBrowser$refresh$2\\1\\273\\0", "loading$delegate", "errorMessage$delegate", "client", "cache", "snapshot$delegate", "it\\2", "$i$a$-onSuccess-MeloXDiscoveryScreensKt$HomeBrowser$refresh$3\\2\\274\\0"}, nl = {273, 277}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$6", "I$0"}, v = 2)
final class MeloXDiscoveryScreensKt$HomeBrowser$refresh$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    /* synthetic */ Object result;

    MeloXDiscoveryScreensKt$HomeBrowser$refresh$1(Continuation<? super MeloXDiscoveryScreensKt$HomeBrowser$refresh$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return MeloXDiscoveryScreensKt.HomeBrowser$refresh(null, null, null, null, null, this);
    }
}
