package com.lladlam.melox.p012ui.player;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.media3.container.MdtaMetadataEntry;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: renamed from: com.lladlam.melox.ui.player.MeloXIOSNowPlayingSharedHostKt$MeloXIOSNowPlayingSharedHost$dragState$1$1$1 */
/* JADX INFO: compiled from: MeloXIOSNowPlayingSharedHost.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(m719c = "com.lladlam.melox.ui.player.MeloXIOSNowPlayingSharedHostKt$MeloXIOSNowPlayingSharedHost$dragState$1$1$1", m720f = "MeloXIOSNowPlayingSharedHost.kt", m721i = {}, m722l = {66}, m723m = "invokeSuspend", m724n = {}, m725nl = {MdtaMetadataEntry.TYPE_INDICATOR_INT32}, m726s = {}, m727v = 2)
final class C2664x7fe5eddb extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Animatable<Float, AnimationVector1D> $dragOffset;
    final /* synthetic */ float $next;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C2664x7fe5eddb(Animatable<Float, AnimationVector1D> animatable, float f, Continuation<? super C2664x7fe5eddb> continuation) {
        super(2, continuation);
        this.$dragOffset = animatable;
        this.$next = f;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new C2664x7fe5eddb(this.$dragOffset, this.$next, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C2664x7fe5eddb) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                if (this.$dragOffset.snapTo(Boxing.boxFloat(this.$next), this) == coroutine_suspended) {
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
