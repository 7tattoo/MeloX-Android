package com.lladlam.melox.ui.player;

import androidx.compose.runtime.MutableState;
import androidx.core.view.MotionEventCompat;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MeloXFlowingLightBackdrop.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lladlam.melox.ui.player.MeloXFlowingLightBackdropKt$MeloXFlowingLightBackdrop$1$1", f = "MeloXFlowingLightBackdrop.kt", i = {}, l = {42}, m = "invokeSuspend", n = {}, nl = {MotionEventCompat.AXIS_GENERIC_12}, s = {}, v = 2)
final class MeloXFlowingLightBackdropKt$MeloXFlowingLightBackdrop$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $artworkUrl;
    final /* synthetic */ MutableState<ArtworkDynamicPalette> $targetPalette$delegate;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MeloXFlowingLightBackdropKt$MeloXFlowingLightBackdrop$1$1(String str, MutableState<ArtworkDynamicPalette> mutableState, Continuation<? super MeloXFlowingLightBackdropKt$MeloXFlowingLightBackdrop$1$1> continuation) {
        super(2, continuation);
        this.$artworkUrl = str;
        this.$targetPalette$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MeloXFlowingLightBackdropKt$MeloXFlowingLightBackdrop$1$1(this.$artworkUrl, this.$targetPalette$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MeloXFlowingLightBackdropKt$MeloXFlowingLightBackdrop$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object objPaletteFor;
        MutableState<ArtworkDynamicPalette> mutableState;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                MutableState<ArtworkDynamicPalette> mutableState2 = this.$targetPalette$delegate;
                this.L$0 = mutableState2;
                this.label = 1;
                objPaletteFor = ArtworkDynamicPaletteProvider.INSTANCE.paletteFor(this.$artworkUrl, this);
                if (objPaletteFor == coroutine_suspended) {
                    return coroutine_suspended;
                }
                mutableState = mutableState2;
                break;
                break;
            case 1:
                mutableState = (MutableState) this.L$0;
                ResultKt.throwOnFailure($result);
                objPaletteFor = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        mutableState.setValue((ArtworkDynamicPalette) objPaletteFor);
        return Unit.INSTANCE;
    }
}
