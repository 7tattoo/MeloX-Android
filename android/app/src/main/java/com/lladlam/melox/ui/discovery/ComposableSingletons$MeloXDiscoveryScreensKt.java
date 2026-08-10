package com.lladlam.melox.p012ui.discovery;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MeloXDiscoveryScreens.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final class ComposableSingletons$MeloXDiscoveryScreensKt {
    public static final ComposableSingletons$MeloXDiscoveryScreensKt INSTANCE = new ComposableSingletons$MeloXDiscoveryScreensKt();

    /* JADX INFO: renamed from: lambda$-66146226, reason: not valid java name */
    private static Function3<LazyItemScope, Composer, Integer, Unit> f431lambda$66146226 = ComposableLambdaKt.composableLambdaInstance(-66146226, false, new Function3() { // from class: com.lladlam.melox.ui.discovery.ComposableSingletons$MeloXDiscoveryScreensKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$MeloXDiscoveryScreensKt.lambda__66146226$lambda$0((LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-813508049, reason: not valid java name */
    private static Function3<LazyItemScope, Composer, Integer, Unit> f432lambda$813508049 = ComposableLambdaKt.composableLambdaInstance(-813508049, false, new Function3() { // from class: com.lladlam.melox.ui.discovery.ComposableSingletons$MeloXDiscoveryScreensKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$MeloXDiscoveryScreensKt.lambda__813508049$lambda$0((LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1012347441, reason: not valid java name */
    private static Function3<LazyItemScope, Composer, Integer, Unit> f429lambda$1012347441 = ComposableLambdaKt.composableLambdaInstance(-1012347441, false, new Function3() { // from class: com.lladlam.melox.ui.discovery.ComposableSingletons$MeloXDiscoveryScreensKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$MeloXDiscoveryScreensKt.lambda__1012347441$lambda$0((LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1828055472, reason: not valid java name */
    private static Function3<LazyItemScope, Composer, Integer, Unit> f430lambda$1828055472 = ComposableLambdaKt.composableLambdaInstance(-1828055472, false, new Function3() { // from class: com.lladlam.melox.ui.discovery.ComposableSingletons$MeloXDiscoveryScreensKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$MeloXDiscoveryScreensKt.lambda__1828055472$lambda$0((LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: getLambda$-1012347441$app, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m9608getLambda$1012347441$app() {
        return f429lambda$1012347441;
    }

    /* JADX INFO: renamed from: getLambda$-1828055472$app, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m9609getLambda$1828055472$app() {
        return f430lambda$1828055472;
    }

    /* JADX INFO: renamed from: getLambda$-66146226$app, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m9610getLambda$66146226$app() {
        return f431lambda$66146226;
    }

    /* JADX INFO: renamed from: getLambda$-813508049$app, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m9611getLambda$813508049$app() {
        return f432lambda$813508049;
    }

    static final Unit lambda__66146226$lambda$0(LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C197@8048L11,191@7749L342:MeloXDiscoveryScreens.kt#301xg3");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-66146226, $changed, -1, "com.lladlam.melox.ui.discovery.ComposableSingletons$MeloXDiscoveryScreensKt.lambda$-66146226.<anonymous> (MeloXDiscoveryScreens.kt:191)");
            }
            TextKt.m3912TextNvy7gAk("发现", PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, Dp.constructor_impl(28), Dp.constructor_impl(42), Dp.constructor_impl(20), 0.0f, 8, null), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnBackground(), null, TextUnitKt.getSp(36), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, TextUnitKt.getSp(42), 0, false, 0, 0, null, null, $composer, 1597446, 48, 260008);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__813508049$lambda$0(LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C244@10041L23:MeloXDiscoveryScreens.kt#301xg3");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-813508049, $changed, -1, "com.lladlam.melox.ui.discovery.ComposableSingletons$MeloXDiscoveryScreensKt.lambda$-813508049.<anonymous> (MeloXDiscoveryScreens.kt:244)");
            }
            MeloXDiscoveryScreensKt.LoadingState("正在发现好音乐", $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1012347441$lambda$0(LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C248@10261L18:MeloXDiscoveryScreens.kt#301xg3");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1012347441, $changed, -1, "com.lladlam.melox.ui.discovery.ComposableSingletons$MeloXDiscoveryScreensKt.lambda$-1012347441.<anonymous> (MeloXDiscoveryScreens.kt:248)");
            }
            MeloXDiscoveryScreensKt.EmptyState("暂无歌单", $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1828055472$lambda$0(LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C308@12404L11,302@12105L342:MeloXDiscoveryScreens.kt#301xg3");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1828055472, $changed, -1, "com.lladlam.melox.ui.discovery.ComposableSingletons$MeloXDiscoveryScreensKt.lambda$-1828055472.<anonymous> (MeloXDiscoveryScreens.kt:302)");
            }
            TextKt.m3912TextNvy7gAk("首页", PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, Dp.constructor_impl(28), Dp.constructor_impl(42), Dp.constructor_impl(20), 0.0f, 8, null), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnBackground(), null, TextUnitKt.getSp(36), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, TextUnitKt.getSp(42), 0, false, 0, 0, null, null, $composer, 1597446, 48, 260008);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }
}
