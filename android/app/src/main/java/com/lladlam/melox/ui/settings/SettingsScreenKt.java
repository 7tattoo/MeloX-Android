package com.lladlam.melox.p012ui.settings;

import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TextKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.core.location.LocationRequestCompat;
import coil3.compose.SingletonAsyncImageKt;
import com.lladlam.melox.core.account.NeteaseAccountProfile;
import com.lladlam.melox.core.account.NeteaseSessionStore;
import com.lladlam.melox.p012ui.MeloXLayoutKt;
import com.lladlam.melox.p012ui.glass.MeloXBackdropComponentsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SettingsScreen.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a#\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"SettingsScreen", "", "session", "Lcom/lladlam/melox/core/account/NeteaseSessionStore;", "onLogin", "Lkotlin/Function0;", "(Lcom/lladlam/melox/core/account/NeteaseSessionStore;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class SettingsScreenKt {
    static final Unit SettingsScreen$lambda$2(NeteaseSessionStore neteaseSessionStore, Function0 function0, int i, Composer composer, int i2) {
        SettingsScreen(neteaseSessionStore, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void SettingsScreen(final NeteaseSessionStore session, final Function0<Unit> onLogin, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(onLogin, "onLogin");
        Composer $composer3 = $composer.startRestartGroup(614229850);
        ComposerKt.sourceInformation($composer3, "C(SettingsScreen)N(session,onLogin)39@1642L64,39@1611L95,46@1803L21,43@1712L7606:SettingsScreen.kt#ukg3ie");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(session) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(onLogin) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if ($composer3.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(614229850, $dirty2, -1, "com.lladlam.melox.ui.settings.SettingsScreen (SettingsScreen.kt:38)");
            }
            String cookie = session.getCookie();
            ComposerKt.sourceInformationMarkerStart($composer3, 1864370138, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean z = ($dirty2 & 14) == 4;
            Object objRememberedValue = $composer3.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = (Function2) new SettingsScreenKt$SettingsScreen$1$1(session, null);
                $composer3.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(cookie, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue, $composer3, 0);
            Modifier modifierM1809paddingqDBjuR0$default = PaddingKt.m1809paddingqDBjuR0$default(PaddingKt.m1807paddingVpY3zN4$default(ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(0, $composer3, 0, 1), false, null, false, 14, null), Dp.constructor_impl(24), 0.0f, 2, null), 0.0f, Dp.constructor_impl(52), 0.0f, MeloXLayoutKt.getMeloXBottomContentClearance(), 5, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1809paddingqDBjuR0$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor;
                $composer3.createNode(function0);
            } else {
                function0 = constructor;
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl = Updater.constructor_impl($composer3);
            Updater.set_impl(composerM5188constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -867644573, "C50@1956L144,57@2110L30,62@2250L11,59@2150L225,72@2649L11,69@2475L325,80@3039L4532,66@2385L5186:SettingsScreen.kt#ukg3ie");
            TextKt.m3912TextNvy7gAk("设置", null, 0L, null, TextUnitKt.getSp(36), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, TextUnitKt.getSp(40), 0, false, 0, 0, null, null, $composer3, 1597446, 48, 260014);
            SpacerKt.Spacer(SizeKt.m1858height3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(28)), $composer3, 6);
            long sp = TextUnitKt.getSp(13);
            long onBackground = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnBackground();
            TextKt.m3912TextNvy7gAk("网易云音乐账号", PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, Dp.constructor_impl(8), 0.0f, 0.0f, Dp.constructor_impl(8), 6, null), Color.copy_wmQWz5c(onBackground, (14 & 1) != 0 ? Color.getAlpha_impl(onBackground) : 0.48f, (14 & 2) != 0 ? Color.getRed_impl(onBackground) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(onBackground) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(onBackground) : 0.0f), null, sp, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 24630, 0, 262120);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            RoundedCornerShape roundedCornerShapeM2135RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(28));
            boolean z2 = !session.isLoggedIn();
            long surfaceVariant = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getSurfaceVariant();
            $composer2 = $composer3;
            SurfaceKt.m3769SurfaceT9BRK9s(ClickableKt.m1078clickableoSLSa3U$default(MeloXBackdropComponentsKt.m9633meloXLiquidButtonNsDo4u0(modifierFillMaxWidth$default, roundedCornerShapeM2135RoundedCornerShape0680j_4, z2, 0L, Color.copy_wmQWz5c(surfaceVariant, (14 & 1) != 0 ? Color.getAlpha_impl(surfaceVariant) : 0.3f, (14 & 2) != 0 ? Color.getRed_impl(surfaceVariant) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(surfaceVariant) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(surfaceVariant) : 0.0f), 0.0f, Dp.constructor_impl(10), Dp.constructor_impl(18), $composer3, 14155782, 20), !session.isLoggedIn(), null, null, null, onLogin, 14, null), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(28)), Color.INSTANCE.m6103getTransparent0d7_KjU(), 0L, Dp.constructor_impl(0), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(2057249001, true, new Function2() { // from class: com.lladlam.melox.ui.settings.SettingsScreenKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return SettingsScreenKt.SettingsScreen$lambda$1$0(session, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer3, 54), $composer3, 12607872, LocationRequestCompat.QUALITY_LOW_POWER);
            if (session.isLoggedIn()) {
                $composer3.startReplaceGroup(-861967234);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(-862245273);
                ComposerKt.sourceInformation($composer3, "183@7840L11,179@7620L279");
                Modifier modifierM1806paddingVpY3zN4 = PaddingKt.m1806paddingVpY3zN4(Modifier.INSTANCE, Dp.constructor_impl(14), Dp.constructor_impl(10));
                long sp2 = TextUnitKt.getSp(12);
                long onBackground2 = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnBackground();
                TextKt.m3912TextNvy7gAk("登录 Cookie 仅保存在本机，用于同步收藏、云盘和账号内容。", modifierM1806paddingVpY3zN4, Color.copy_wmQWz5c(onBackground2, (14 & 1) != 0 ? Color.getAlpha_impl(onBackground2) : 0.46f, (14 & 2) != 0 ? Color.getRed_impl(onBackground2) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(onBackground2) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(onBackground2) : 0.0f), null, sp2, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 24630, 0, 262120);
                $composer3.endReplaceGroup();
            }
            String errorMessage = session.getErrorMessage();
            if (errorMessage == null) {
                $composer3.startReplaceGroup(-861927338);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(-861927337);
                ComposerKt.sourceInformation($composer3, "*192@8162L11,188@7970L224");
                TextKt.m3912TextNvy7gAk(errorMessage, PaddingKt.m1806paddingVpY3zN4(Modifier.INSTANCE, Dp.constructor_impl(14), Dp.constructor_impl(8)), MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getError(), null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 24624, 0, 262120);
                Unit unit = Unit.INSTANCE;
                $composer3.endReplaceGroup();
                Unit unit2 = Unit.INSTANCE;
            }
            if (session.isLoggedIn()) {
                $composer3.startReplaceGroup(-861607572);
                ComposerKt.sourceInformation($composer3, "197@8252L30,203@8520L11,204@8592L11,201@8397L348,208@8777L19,198@8295L1007");
                SpacerKt.Spacer(SizeKt.m1858height3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(28)), $composer3, 6);
                Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                RoundedCornerShape roundedCornerShapeM2135RoundedCornerShape0680j_5 = RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(26));
                long error = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getError();
                long error2 = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getError();
                Modifier modifierM9633meloXLiquidButtonNsDo4u0 = MeloXBackdropComponentsKt.m9633meloXLiquidButtonNsDo4u0(modifierFillMaxWidth$default2, roundedCornerShapeM2135RoundedCornerShape0680j_5, false, error, Color.copy_wmQWz5c(error2, (14 & 1) != 0 ? Color.getAlpha_impl(error2) : 0.1f, (14 & 2) != 0 ? Color.getRed_impl(error2) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(error2) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(error2) : 0.0f), 0.0f, Dp.constructor_impl(9), Dp.constructor_impl(16), $composer3, 14155782, 18);
                ComposerKt.sourceInformationMarkerStart($composer3, -1413250921, "CC(remember):SettingsScreen.kt#9igjgp");
                boolean z3 = ($dirty2 & 14) == 4;
                Object objRememberedValue2 = $composer3.rememberedValue();
                if (z3 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = new Function0() { // from class: com.lladlam.melox.ui.settings.SettingsScreenKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return SettingsScreenKt.SettingsScreen$lambda$1$2$0(session);
                        }
                    };
                    $composer3.updateRememberedValue(obj2);
                    objRememberedValue2 = obj2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                SurfaceKt.m3769SurfaceT9BRK9s(ClickableKt.m1078clickableoSLSa3U$default(modifierM9633meloXLiquidButtonNsDo4u0, false, null, null, null, (Function0) objRememberedValue2, 15, null), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(26)), Color.INSTANCE.m6103getTransparent0d7_KjU(), 0L, Dp.constructor_impl(0), 0.0f, null, ComposableSingletons$SettingsScreenKt.INSTANCE.getLambda$671273175$app(), $composer3, 12607872, LocationRequestCompat.QUALITY_LOW_POWER);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(-860575458);
                $composer3.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.settings.SettingsScreenKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return SettingsScreenKt.SettingsScreen$lambda$2(session, onLogin, $changed, (Composer) obj3, ((Integer) obj4).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$1$0(NeteaseSessionStore $session, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function2;
        Function0<ComposeUiNode> function3;
        Function0<ComposeUiNode> function4;
        Function0<ComposeUiNode> function5;
        ComposerKt.sourceInformation($composer, "C:SettingsScreen.kt#ukg3ie");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2057249001, $changed, -1, "com.lladlam.melox.ui.settings.SettingsScreen.<anonymous>.<anonymous> (SettingsScreen.kt:81)");
            }
            if ($session.isLoggedIn() && $session.getProfile() != null) {
                $composer.startReplaceGroup(-1678186867);
                ComposerKt.sourceInformation($composer, "84@3199L1616");
                NeteaseAccountProfile profile = $session.getProfile();
                Intrinsics.checkNotNull(profile);
                Modifier modifierM1806paddingVpY3zN4 = PaddingKt.m1806paddingVpY3zN4(Modifier.INSTANCE, Dp.constructor_impl(18), Dp.constructor_impl(16));
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                Arrangement.Horizontal horizontalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(Dp.constructor_impl(16));
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalM1497spacedBy0680j_4, centerVertically, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM1806paddingVpY3zN4);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((438 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function4 = constructor;
                    $composer.createNode(function4);
                } else {
                    function4 = constructor;
                    $composer.useNode();
                }
                Composer composerM5188constructorimpl = Updater.constructor_impl($composer);
                Updater.set_impl(composerM5188constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                int i3 = ((438 >> 6) & 112) | 6;
                RowScope rowScope = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, -425826199, "C89@3492L292,97@3810L748,115@4725L11,112@4583L210:SettingsScreen.kt#ukg3ie");
                SingletonAsyncImageKt.m9399AsyncImage10Xjiaw(profile.getAvatarUrl(), null, ClipKt.clip(SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(60)), RoundedCornerShapeKt.getCircleShape()), null, null, null, null, 0.0f, null, 0, false, $composer, 48, 0, 2040);
                Modifier modifierWeight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierWeight$default);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i4 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function5 = constructor2;
                    $composer.createNode(function5);
                } else {
                    function5 = constructor2;
                    $composer.useNode();
                }
                Composer composerM5188constructorimpl2 = Updater.constructor_impl($composer);
                Updater.set_impl(composerM5188constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.set_impl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.set_impl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.reconcile_impl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.set_impl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i5 = (i4 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i6 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 848395579, "C98@3879L320,108@4414L11,105@4228L304:SettingsScreen.kt#ukg3ie");
                TextKt.m3912TextNvy7gAk(profile.getNickname(), null, 0L, null, TextUnitKt.getSp(18), null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, 0L, TextOverflow.INSTANCE.m8816getEllipsisgIe3tQ8(), false, 1, 0, null, null, $composer, 1597440, 24960, 241582);
                String str = "用户 ID " + profile.getUserId() + " · 账号信息与同步";
                long sp = TextUnitKt.getSp(14);
                long onSurface = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurface();
                TextKt.m3912TextNvy7gAk(str, null, Color.copy_wmQWz5c(onSurface, (14 & 1) != 0 ? Color.getAlpha_impl(onSurface) : 0.52f, (14 & 2) != 0 ? Color.getRed_impl(onSurface) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(onSurface) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(onSurface) : 0.0f), null, sp, null, null, null, 0L, null, null, 0L, 0, false, 2, 0, null, null, $composer, 24576, 24576, 245738);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                long sp2 = TextUnitKt.getSp(28);
                long onSurface2 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurface();
                TextKt.m3912TextNvy7gAk("›", null, Color.copy_wmQWz5c(onSurface2, (14 & 1) != 0 ? Color.getAlpha_impl(onSurface2) : 0.28f, (14 & 2) != 0 ? Color.getRed_impl(onSurface2) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(onSurface2) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(onSurface2) : 0.0f), null, sp2, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24582, 0, 262122);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endReplaceGroup();
            } else if ($session.isLoggedIn() && $session.isRefreshing()) {
                $composer.startReplaceGroup(-1676435894);
                ComposerKt.sourceInformation($composer, "121@4919L783");
                Modifier modifierM1806paddingVpY3zN5 = PaddingKt.m1806paddingVpY3zN4(Modifier.INSTANCE, Dp.constructor_impl(18), Dp.constructor_impl(20));
                Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
                Arrangement.Horizontal horizontalM1497spacedBy0680j_5 = Arrangement.INSTANCE.m1497spacedBy0680j_4(Dp.constructor_impl(16));
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(horizontalM1497spacedBy0680j_5, centerVertically2, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifierM1806paddingVpY3zN5);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int i7 = ((((438 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function2 = constructor3;
                    $composer.createNode(function2);
                } else {
                    function2 = constructor3;
                    $composer.useNode();
                }
                Composer composerM5188constructorimpl3 = Updater.constructor_impl($composer);
                Updater.set_impl(composerM5188constructorimpl3, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.set_impl(composerM5188constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.set_impl(composerM5188constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.reconcile_impl(composerM5188constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.set_impl(composerM5188constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i8 = (i7 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int i9 = ((438 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -1239193151, "C126@5212L58,127@5295L385:SettingsScreen.kt#ukg3ie");
                ProgressIndicatorKt.m3567CircularProgressIndicator4lLiAd8(SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(38)), 0L, 0.0f, 0L, 0, 0.0f, $composer, 6, 62);
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier modifier = Modifier.INSTANCE;
                MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap4 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer, modifier);
                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                int i10 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function3 = constructor4;
                    $composer.createNode(function3);
                } else {
                    function3 = constructor4;
                    $composer.useNode();
                }
                Composer composerM5188constructorimpl4 = Updater.constructor_impl($composer);
                Updater.set_impl(composerM5188constructorimpl4, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.set_impl(composerM5188constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.set_impl(composerM5188constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.reconcile_impl(composerM5188constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.set_impl(composerM5188constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                int i11 = (i10 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                int i12 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -762253091, "C128@5332L67,132@5582L11,129@5428L226:SettingsScreen.kt#ukg3ie");
                TextKt.m3912TextNvy7gAk("网易云音乐账号", null, 0L, null, TextUnitKt.getSp(18), null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1597446, 0, 262062);
                long sp3 = TextUnitKt.getSp(14);
                long onSurface3 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurface();
                TextKt.m3912TextNvy7gAk("正在读取账号信息", null, Color.copy_wmQWz5c(onSurface3, (14 & 1) != 0 ? Color.getAlpha_impl(onSurface3) : 0.52f, (14 & 2) != 0 ? Color.getRed_impl(onSurface3) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(onSurface3) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(onSurface3) : 0.0f), null, sp3, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24582, 0, 262122);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-1675563368);
                ComposerKt.sourceInformation($composer, "139@5768L1761");
                Modifier modifierM1806paddingVpY3zN6 = PaddingKt.m1806paddingVpY3zN4(Modifier.INSTANCE, Dp.constructor_impl(18), Dp.constructor_impl(16));
                Alignment.Vertical centerVertically3 = Alignment.INSTANCE.getCenterVertically();
                Arrangement.Horizontal horizontalM1497spacedBy0680j_6 = Arrangement.INSTANCE.m1497spacedBy0680j_4(Dp.constructor_impl(16));
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(horizontalM1497spacedBy0680j_6, centerVertically3, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap5 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier($composer, modifierM1806paddingVpY3zN6);
                Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                int i13 = ((((438 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor5;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor5;
                    $composer.useNode();
                }
                Composer composerM5188constructorimpl5 = Updater.constructor_impl($composer);
                Updater.set_impl(composerM5188constructorimpl5, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.set_impl(composerM5188constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.set_impl(composerM5188constructorimpl5, Integer.valueOf(iHashCode5), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.reconcile_impl(composerM5188constructorimpl5, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.set_impl(composerM5188constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                int i14 = (i13 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                int i15 = ((438 >> 6) & 112) | 6;
                RowScope rowScope2 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, -2006720753, "C147@6230L11,144@6061L624,156@6710L562,171@7439L11,168@7297L210:SettingsScreen.kt#ukg3ie");
                Modifier modifierM1872size3ABfNKs = SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(60));
                RoundedCornerShape circleShape = RoundedCornerShapeKt.getCircleShape();
                long onSurface4 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurface();
                SurfaceKt.m3769SurfaceT9BRK9s(modifierM1872size3ABfNKs, circleShape, Color.copy_wmQWz5c(onSurface4, (14 & 1) != 0 ? Color.getAlpha_impl(onSurface4) : 0.07f, (14 & 2) != 0 ? Color.getRed_impl(onSurface4) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(onSurface4) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(onSurface4) : 0.0f), 0L, 0.0f, 0.0f, null, ComposableSingletons$SettingsScreenKt.INSTANCE.m9706getLambda$1421026967$app(), $composer, 12582918, 120);
                Modifier modifierWeight$default2 = RowScope.weight$default(rowScope2, Modifier.INSTANCE, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy3 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap6 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier($composer, modifierWeight$default2);
                Function0<ComposeUiNode> constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                int i16 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function1 = constructor6;
                    $composer.createNode(function1);
                } else {
                    function1 = constructor6;
                    $composer.useNode();
                }
                Composer composerM5188constructorimpl6 = Updater.constructor_impl($composer);
                Updater.set_impl(composerM5188constructorimpl6, measurePolicyColumnMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.set_impl(composerM5188constructorimpl6, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.set_impl(composerM5188constructorimpl6, Integer.valueOf(iHashCode6), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.reconcile_impl(composerM5188constructorimpl6, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.set_impl(composerM5188constructorimpl6, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                int i17 = (i16 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
                int i18 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 130889292, "C157@6779L201,165@7174L11,162@7009L237:SettingsScreen.kt#ukg3ie");
                TextKt.m3912TextNvy7gAk("登录网易云音乐", null, 0L, null, TextUnitKt.getSp(18), null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1597446, 0, 262062);
                long sp4 = TextUnitKt.getSp(14);
                long onSurface5 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurface();
                TextKt.m3912TextNvy7gAk("同步收藏、云盘与播放记录", null, Color.copy_wmQWz5c(onSurface5, (14 & 1) != 0 ? Color.getAlpha_impl(onSurface5) : 0.52f, (14 & 2) != 0 ? Color.getRed_impl(onSurface5) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(onSurface5) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(onSurface5) : 0.0f), null, sp4, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24582, 0, 262122);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                long sp5 = TextUnitKt.getSp(28);
                long onSurface6 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurface();
                TextKt.m3912TextNvy7gAk("›", null, Color.copy_wmQWz5c(onSurface6, (14 & 1) != 0 ? Color.getAlpha_impl(onSurface6) : 0.28f, (14 & 2) != 0 ? Color.getRed_impl(onSurface6) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(onSurface6) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(onSurface6) : 0.0f), null, sp5, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24582, 0, 262122);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$1$2$0(NeteaseSessionStore $session) {
        $session.clear();
        return Unit.INSTANCE;
    }
}
