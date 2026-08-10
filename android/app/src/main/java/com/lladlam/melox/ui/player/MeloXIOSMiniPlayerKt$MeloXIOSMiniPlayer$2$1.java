package com.lladlam.melox.p012ui.player;

import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.runtime.MutableFloatState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MeloXIOSMiniPlayer.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final class MeloXIOSMiniPlayerKt$MeloXIOSMiniPlayer$2$1 implements PointerInputEventHandler {
    final /* synthetic */ MutableFloatState $accumulatedDrag$delegate;
    final /* synthetic */ MeloXPlaybackUiState $state;

    MeloXIOSMiniPlayerKt$MeloXIOSMiniPlayer$2$1(MutableFloatState mutableFloatState, MeloXPlaybackUiState meloXPlaybackUiState) {
        this.$accumulatedDrag$delegate = mutableFloatState;
        this.$state = meloXPlaybackUiState;
    }

    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
    public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
        final MutableFloatState mutableFloatState = this.$accumulatedDrag$delegate;
        Function1 function1 = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSMiniPlayerKt$MeloXIOSMiniPlayer$2$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MeloXIOSMiniPlayerKt$MeloXIOSMiniPlayer$2$1.invoke$lambda$0(mutableFloatState, (Offset) obj);
            }
        };
        final MeloXPlaybackUiState meloXPlaybackUiState = this.$state;
        final MutableFloatState mutableFloatState2 = this.$accumulatedDrag$delegate;
        Function0 function0 = new Function0() { // from class: com.lladlam.melox.ui.player.MeloXIOSMiniPlayerKt$MeloXIOSMiniPlayer$2$1$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MeloXIOSMiniPlayerKt$MeloXIOSMiniPlayer$2$1.invoke$lambda$1(meloXPlaybackUiState, mutableFloatState2);
            }
        };
        final MutableFloatState mutableFloatState3 = this.$accumulatedDrag$delegate;
        Function0 function2 = new Function0() { // from class: com.lladlam.melox.ui.player.MeloXIOSMiniPlayerKt$MeloXIOSMiniPlayer$2$1$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MeloXIOSMiniPlayerKt$MeloXIOSMiniPlayer$2$1.invoke$lambda$2(mutableFloatState3);
            }
        };
        final MutableFloatState mutableFloatState4 = this.$accumulatedDrag$delegate;
        Object objDetectHorizontalDragGestures = DragGestureDetectorKt.detectHorizontalDragGestures($this$pointerInput, function1, function0, function2, new Function2() { // from class: com.lladlam.melox.ui.player.MeloXIOSMiniPlayerKt$MeloXIOSMiniPlayer$2$1$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return MeloXIOSMiniPlayerKt$MeloXIOSMiniPlayer$2$1.invoke$lambda$3(mutableFloatState4, (PointerInputChange) obj, ((Float) obj2).floatValue());
            }
        }, continuation);
        return objDetectHorizontalDragGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectHorizontalDragGestures : Unit.INSTANCE;
    }

    static final Unit invoke$lambda$0(MutableFloatState $accumulatedDrag$delegate, Offset it) {
        $accumulatedDrag$delegate.setFloatValue(0.0f);
        return Unit.INSTANCE;
    }

    static final Unit invoke$lambda$3(MutableFloatState $accumulatedDrag$delegate, PointerInputChange pointerInputChange, float dragAmount) {
        Intrinsics.checkNotNullParameter(pointerInputChange, "<unused var>");
        $accumulatedDrag$delegate.setFloatValue(MeloXIOSMiniPlayerKt.MeloXIOSMiniPlayer$lambda$2($accumulatedDrag$delegate) + dragAmount);
        return Unit.INSTANCE;
    }

    static final Unit invoke$lambda$1(MeloXPlaybackUiState $state, MutableFloatState $accumulatedDrag$delegate) {
        if (MeloXIOSMiniPlayerKt.MeloXIOSMiniPlayer$lambda$2($accumulatedDrag$delegate) <= -48.0f) {
            $state.next();
        } else if (MeloXIOSMiniPlayerKt.MeloXIOSMiniPlayer$lambda$2($accumulatedDrag$delegate) >= 48.0f) {
            $state.previous();
        }
        $accumulatedDrag$delegate.setFloatValue(0.0f);
        return Unit.INSTANCE;
    }

    static final Unit invoke$lambda$2(MutableFloatState $accumulatedDrag$delegate) {
        $accumulatedDrag$delegate.setFloatValue(0.0f);
        return Unit.INSTANCE;
    }
}
