package com.lladlam.melox.p012ui.player;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: MeloXPlayerUi.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final /* synthetic */ class MeloXPlayerUiKt$MeloXVolumeControl$1$1$1 extends FunctionReferenceImpl implements Function1<Float, Unit> {
    MeloXPlayerUiKt$MeloXVolumeControl$1$1$1(Object obj) {
        super(1, obj, MeloXPlaybackUiState.class, "changeVolume", "changeVolume(F)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Float f) {
        invoke(f.floatValue());
        return Unit.INSTANCE;
    }

    public final void invoke(float p0) {
        ((MeloXPlaybackUiState) this.receiver).changeVolume(p0);
    }
}
