package com.lladlam.melox.p012ui.discovery;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: renamed from: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$HomeEditorialStrip$lambda$0$0$$inlined$items$default$2 */
/* JADX INFO: compiled from: LazyDsl.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final class C2629x9212ca66 implements Function1<Integer, Object> {
    final /* synthetic */ List $items;
    final /* synthetic */ Function1 $key;

    public C2629x9212ca66(Function1 function1, List list) {
        this.$key = function1;
        this.$items = list;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Integer num) {
        return invoke(num.intValue());
    }

    public final Object invoke(int index) {
        return this.$key.invoke(this.$items.get(index));
    }
}
