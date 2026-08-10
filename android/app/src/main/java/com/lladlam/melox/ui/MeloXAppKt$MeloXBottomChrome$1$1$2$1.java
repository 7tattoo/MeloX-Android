package com.lladlam.melox.ui;

import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: MeloXApp.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final class MeloXAppKt$MeloXBottomChrome$1$1$2$1 implements PointerInputEventHandler {
    final /* synthetic */ Function1<AppTab, Unit> $onSelect;
    final /* synthetic */ List<Pair<AppTab, RootGlyph>> $primaryTabs;
    final /* synthetic */ float $progress;
    final /* synthetic */ AppTab $selectedTab;

    /* JADX WARN: Multi-variable type inference failed */
    MeloXAppKt$MeloXBottomChrome$1$1$2$1(float f, Function1<? super AppTab, Unit> function1, List<? extends Pair<? extends AppTab, ? extends RootGlyph>> list, AppTab appTab) {
        this.$progress = f;
        this.$onSelect = function1;
        this.$primaryTabs = list;
        this.$selectedTab = appTab;
    }

    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
    public final Object invoke(final PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
        final float f = this.$progress;
        final Function1<AppTab, Unit> function1 = this.$onSelect;
        final List<Pair<AppTab, RootGlyph>> list = this.$primaryTabs;
        final AppTab appTab = this.$selectedTab;
        Object objDetectTapGestures$default = TapGestureDetectorKt.detectTapGestures$default($this$pointerInput, null, null, null, new Function1() { // from class: com.lladlam.melox.ui.MeloXAppKt$MeloXBottomChrome$1$1$2$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MeloXAppKt$MeloXBottomChrome$1$1$2$1.invoke$lambda$0(f, $this$pointerInput, function1, list, appTab, (Offset) obj);
            }
        }, continuation, 7, null);
        return objDetectTapGestures$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectTapGestures$default : Unit.INSTANCE;
    }

    static final Unit invoke$lambda$0(float $progress, PointerInputScope $this_pointerInput, Function1 $onSelect, List $primaryTabs, AppTab $selectedTab, Offset tap) {
        if ($progress < 0.56f) {
            float segmentWidth = ((int) ($this_pointerInput.getBoundsSize() >> 32)) / 4.0f;
            int index = RangesKt.coerceIn((int) (Float.intBitsToFloat((int) (tap.m5833unboximpl() >> 32)) / segmentWidth), 0, 3);
            $onSelect.invoke(((Pair) $primaryTabs.get(index)).getFirst());
        } else if ($progress >= 0.68f) {
            $onSelect.invoke($selectedTab);
        }
        return Unit.INSTANCE;
    }
}
