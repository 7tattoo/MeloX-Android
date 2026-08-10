package com.lladlam.melox.ui.player;

import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.SliderState;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.C1301Dp;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MeloXPlayerUi.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final class ComposableSingletons$MeloXPlayerUiKt {
    public static final ComposableSingletons$MeloXPlayerUiKt INSTANCE = new ComposableSingletons$MeloXPlayerUiKt();
    private static Function3<SliderState, Composer, Integer, Unit> lambda$1065021834 = ComposableLambdaKt.composableLambdaInstance(1065021834, false, new Function3() { // from class: com.lladlam.melox.ui.player.ComposableSingletons$MeloXPlayerUiKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$MeloXPlayerUiKt.lambda_1065021834$lambda$0((SliderState) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    public final Function3<SliderState, Composer, Integer, Unit> getLambda$1065021834$app() {
        return lambda$1065021834;
    }

    static final Unit lambda_1065021834$lambda$0(SliderState it, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation($composer, "CN(it)565@18172L24:MeloXPlayerUi.kt#qhu5z0");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1065021834, $changed, -1, "com.lladlam.melox.ui.player.ComposableSingletons$MeloXPlayerUiKt.lambda$1065021834.<anonymous> (MeloXPlayerUi.kt:565)");
            }
            BoxKt.Box(SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(1)), $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }
}
