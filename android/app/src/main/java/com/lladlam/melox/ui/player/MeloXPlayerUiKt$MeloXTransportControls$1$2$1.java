package com.lladlam.melox.ui.player;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: MeloXPlayerUi.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final /* synthetic */ class MeloXPlayerUiKt$MeloXTransportControls$1$2$1 extends FunctionReferenceImpl implements Function0<Unit> {
    MeloXPlayerUiKt$MeloXTransportControls$1$2$1(Object obj) {
        super(0, obj, MeloXPlaybackUiState.class, "togglePlayPause", "togglePlayPause()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        ((MeloXPlaybackUiState) this.receiver).togglePlayPause();
    }
}
