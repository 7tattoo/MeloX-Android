package com.lladlam.melox.ui.player;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: MeloXIOSMiniPlayer.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final class ComposableSingletons$MeloXIOSMiniPlayerKt {
    public static final ComposableSingletons$MeloXIOSMiniPlayerKt INSTANCE = new ComposableSingletons$MeloXIOSMiniPlayerKt();
    private static Function2<Composer, Integer, Unit> lambda$295594761 = ComposableLambdaKt.composableLambdaInstance(295594761, false, new Function2() { // from class: com.lladlam.melox.ui.player.ComposableSingletons$MeloXIOSMiniPlayerKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MeloXIOSMiniPlayerKt.lambda_295594761$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    public final Function2<Composer, Integer, Unit> getLambda$295594761$app() {
        return lambda$295594761;
    }

    static final Unit lambda_295594761$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C:MeloXIOSMiniPlayer.kt#qhu5z0");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(295594761, $changed, -1, "com.lladlam.melox.ui.player.ComposableSingletons$MeloXIOSMiniPlayerKt.lambda$295594761.<anonymous> (MeloXIOSMiniPlayer.kt:166)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }
}
