package com.lladlam.melox.p012ui.player;

import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material3.SliderState;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MeloXIOSNowPlayingV2.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final class ComposableSingletons$MeloXIOSNowPlayingV2Kt {
    public static final ComposableSingletons$MeloXIOSNowPlayingV2Kt INSTANCE = new ComposableSingletons$MeloXIOSNowPlayingV2Kt();
    private static Function3<SliderState, Composer, Integer, Unit> lambda$1770962769 = ComposableLambdaKt.composableLambdaInstance(1770962769, false, new Function3() { // from class: com.lladlam.melox.ui.player.ComposableSingletons$MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$MeloXIOSNowPlayingV2Kt.lambda_1770962769$lambda$0((SliderState) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1310663515, reason: not valid java name */
    private static Function4<AnimatedContentScope, Boolean, Composer, Integer, Unit> f434lambda$1310663515 = ComposableLambdaKt.composableLambdaInstance(-1310663515, false, new Function4() { // from class: com.lladlam.melox.ui.player.ComposableSingletons$MeloXIOSNowPlayingV2Kt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            return ComposableSingletons$MeloXIOSNowPlayingV2Kt.lambda__1310663515$lambda$0((AnimatedContentScope) obj, ((Boolean) obj2).booleanValue(), (Composer) obj3, ((Integer) obj4).intValue());
        }
    });

    /* JADX INFO: renamed from: getLambda$-1310663515$app, reason: not valid java name */
    public final Function4<AnimatedContentScope, Boolean, Composer, Integer, Unit> m9673getLambda$1310663515$app() {
        return f434lambda$1310663515;
    }

    public final Function3<SliderState, Composer, Integer, Unit> getLambda$1770962769$app() {
        return lambda$1770962769;
    }

    static final Unit lambda_1770962769$lambda$0(SliderState it, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation($composer, "CN(it)429@15196L27:MeloXIOSNowPlayingV2.kt#qhu5z0");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1770962769, $changed, -1, "com.lladlam.melox.ui.player.ComposableSingletons$MeloXIOSNowPlayingV2Kt.lambda$1770962769.<anonymous> (MeloXIOSNowPlayingV2.kt:429)");
            }
            SpacerKt.Spacer(SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(0)), $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1310663515$lambda$0(AnimatedContentScope AnimatedContent, boolean playing, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(AnimatedContent, "$this$AnimatedContent");
        ComposerKt.sourceInformation($composer, "CN(playing)669@23429L206:MeloXIOSNowPlayingV2.kt#qhu5z0");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1310663515, $changed, -1, "com.lladlam.melox.ui.player.ComposableSingletons$MeloXIOSNowPlayingV2Kt.lambda$-1310663515.<anonymous> (MeloXIOSNowPlayingV2.kt:669)");
        }
        MeloXIOSNowPlayingV2Kt.m9689CupertinoGlyphXOJAsU(playing ? CupertinoGlyphKind.Pause : CupertinoGlyphKind.Play, SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(48)), Color.INSTANCE.m6105getWhite0d7_KjU(), $composer, 432);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }
}
