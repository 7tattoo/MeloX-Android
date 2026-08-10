package com.lladlam.melox.p012ui.player;

import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MeloXIOSNowPlayingV2.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(m719c = "com.lladlam.melox.ui.player.MeloXIOSNowPlayingV2Kt$MeloXProgressControlV3$1$1", m720f = "MeloXIOSNowPlayingV2.kt", m721i = {}, m722l = {}, m723m = "invokeSuspend", m724n = {}, m725nl = {}, m726s = {}, m727v = 2)
final class MeloXIOSNowPlayingV2Kt$MeloXProgressControlV3$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableFloatState $localProgress$delegate;
    final /* synthetic */ MutableState<Boolean> $scrubbing$delegate;
    final /* synthetic */ float $sourceProgress;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MeloXIOSNowPlayingV2Kt$MeloXProgressControlV3$1$1(float f, MutableState<Boolean> mutableState, MutableFloatState mutableFloatState, Continuation<? super MeloXIOSNowPlayingV2Kt$MeloXProgressControlV3$1$1> continuation) {
        super(2, continuation);
        this.$sourceProgress = f;
        this.$scrubbing$delegate = mutableState;
        this.$localProgress$delegate = mutableFloatState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MeloXIOSNowPlayingV2Kt$MeloXProgressControlV3$1$1(this.$sourceProgress, this.$scrubbing$delegate, this.$localProgress$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MeloXIOSNowPlayingV2Kt$MeloXProgressControlV3$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (!MeloXIOSNowPlayingV2Kt.MeloXProgressControlV3$lambda$1(this.$scrubbing$delegate)) {
                    this.$localProgress$delegate.setFloatValue(this.$sourceProgress);
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
