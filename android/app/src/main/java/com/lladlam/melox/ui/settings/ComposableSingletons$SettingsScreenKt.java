package com.lladlam.melox.ui.settings;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.C1301Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: SettingsScreen.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final class ComposableSingletons$SettingsScreenKt {
    public static final ComposableSingletons$SettingsScreenKt INSTANCE = new ComposableSingletons$SettingsScreenKt();

    /* JADX INFO: renamed from: lambda$-1421026967, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f435lambda$1421026967 = ComposableLambdaKt.composableLambdaInstance(-1421026967, false, new Function2() { // from class: com.lladlam.melox.ui.settings.ComposableSingletons$SettingsScreenKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$SettingsScreenKt.lambda__1421026967$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$671273175 = ComposableLambdaKt.composableLambdaInstance(671273175, false, new Function2() { // from class: com.lladlam.melox.ui.settings.ComposableSingletons$SettingsScreenKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$SettingsScreenKt.lambda_671273175$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: getLambda$-1421026967$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m9706getLambda$1421026967$app() {
        return f435lambda$1421026967;
    }

    public final Function2<Composer, Integer, Unit> getLambda$671273175$app() {
        return lambda$671273175;
    }

    static final Unit lambda__1421026967$lambda$0(Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C149@6329L330:SettingsScreen.kt#ukg3ie");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1421026967, $changed, -1, "com.lladlam.melox.ui.settings.ComposableSingletons$SettingsScreenKt.lambda$-1421026967.<anonymous> (SettingsScreen.kt:149)");
            }
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            Arrangement.Vertical center = Arrangement.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, $composer, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((432 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
                $composer.useNode();
            }
            Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer);
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -814863574, "C153@6609L11,153@6559L70:SettingsScreen.kt#ukg3ie");
            TextKt.m3912TextNvy7gAk("＋", null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), null, TextUnitKt.getSp(28), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24582, 0, 262122);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_671273175$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C216@9162L11,213@8992L296:SettingsScreen.kt#ukg3ie");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(671273175, $changed, -1, "com.lladlam.melox.ui.settings.ComposableSingletons$SettingsScreenKt.lambda$671273175.<anonymous> (SettingsScreen.kt:213)");
            }
            TextKt.m3912TextNvy7gAk("退出登录", PaddingKt.m1806paddingVpY3zN4(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(18), C1301Dp.m8905constructorimpl(18)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getError(), null, TextUnitKt.getSp(16), null, FontWeight.INSTANCE.getMedium(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1597494, 0, 262056);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
