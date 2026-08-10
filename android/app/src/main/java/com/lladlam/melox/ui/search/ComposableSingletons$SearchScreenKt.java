package com.lladlam.melox.p012ui.search;

import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: SearchScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final class ComposableSingletons$SearchScreenKt {
    public static final ComposableSingletons$SearchScreenKt INSTANCE = new ComposableSingletons$SearchScreenKt();
    private static Function2<Composer, Integer, Unit> lambda$350283091 = ComposableLambdaKt.composableLambdaInstance(350283091, false, new Function2() { // from class: com.lladlam.melox.ui.search.ComposableSingletons$SearchScreenKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$SearchScreenKt.lambda_350283091$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1532515826 = ComposableLambdaKt.composableLambdaInstance(1532515826, false, new Function2() { // from class: com.lladlam.melox.ui.search.ComposableSingletons$SearchScreenKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$SearchScreenKt.lambda_1532515826$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    public final Function2<Composer, Integer, Unit> getLambda$1532515826$app() {
        return lambda$1532515826;
    }

    public final Function2<Composer, Integer, Unit> getLambda$350283091$app() {
        return lambda$350283091;
    }

    static final Unit lambda_350283091$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C130@5031L10:SearchScreen.kt#p6k06t");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(350283091, $changed, -1, "com.lladlam.melox.ui.search.ComposableSingletons$SearchScreenKt.lambda$350283091.<anonymous> (SearchScreen.kt:130)");
            }
            TextKt.m3912TextNvy7gAk("歌曲", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1532515826$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C131@5077L15:SearchScreen.kt#p6k06t");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1532515826, $changed, -1, "com.lladlam.melox.ui.search.ComposableSingletons$SearchScreenKt.lambda$1532515826.<anonymous> (SearchScreen.kt:131)");
            }
            TextKt.m3912TextNvy7gAk("输入歌曲或歌手", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }
}
