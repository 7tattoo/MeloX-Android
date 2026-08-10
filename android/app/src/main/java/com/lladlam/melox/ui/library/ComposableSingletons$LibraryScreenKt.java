package com.lladlam.melox.ui.library;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.C1301Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryScreen.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final class ComposableSingletons$LibraryScreenKt {
    public static final ComposableSingletons$LibraryScreenKt INSTANCE = new ComposableSingletons$LibraryScreenKt();

    /* JADX INFO: renamed from: lambda$-2107823229, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f433lambda$2107823229 = ComposableLambdaKt.composableLambdaInstance(-2107823229, false, new Function2() { // from class: com.lladlam.melox.ui.library.ComposableSingletons$LibraryScreenKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$LibraryScreenKt.lambda__2107823229$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<LazyItemScope, Composer, Integer, Unit> lambda$162496283 = ComposableLambdaKt.composableLambdaInstance(162496283, false, new Function3() { // from class: com.lladlam.melox.ui.library.ComposableSingletons$LibraryScreenKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$LibraryScreenKt.lambda_162496283$lambda$0((LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: getLambda$-2107823229$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m9635getLambda$2107823229$app() {
        return f433lambda$2107823229;
    }

    public final Function3<LazyItemScope, Composer, Integer, Unit> getLambda$162496283$app() {
        return lambda$162496283;
    }

    static final Unit lambda__2107823229$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C350@15971L11,347@15793L280:LibraryScreen.kt#t3x8p4");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2107823229, $changed, -1, "com.lladlam.melox.ui.library.ComposableSingletons$LibraryScreenKt.lambda$-2107823229.<anonymous> (LibraryScreen.kt:347)");
            }
            TextKt.m3912TextNvy7gAk("登录网易云音乐", PaddingKt.m1806paddingVpY3zN4(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(18), C1301Dp.m8905constructorimpl(10)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnPrimary(), null, 0L, null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1572918, 0, 262072);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_162496283$lambda$0(LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C546@22163L11,542@21969L253:LibraryScreen.kt#t3x8p4");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(162496283, $changed, -1, "com.lladlam.melox.ui.library.ComposableSingletons$LibraryScreenKt.lambda$162496283.<anonymous> (LibraryScreen.kt:542)");
            }
            Modifier modifierM1809paddingqDBjuR0$default = PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(20), C1301Dp.m8905constructorimpl(8), 0.0f, C1301Dp.m8905constructorimpl(6), 4, null);
            long sp = TextUnitKt.getSp(13);
            long onBackground = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnBackground();
            TextKt.m3912TextNvy7gAk("歌单", modifierM1809paddingqDBjuR0$default, Color.m6066copywmQWz5c(onBackground, (14 & 1) != 0 ? Color.m6070getAlphaimpl(onBackground) : 0.5f, (14 & 2) != 0 ? Color.m6074getRedimpl(onBackground) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(onBackground) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(onBackground) : 0.0f), null, sp, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24630, 0, 262120);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }
}
