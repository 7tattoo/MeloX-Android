package com.lladlam.melox.p012ui.player;

import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
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
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.media3.exoplayer.RendererCapabilities;
import com.lladlam.melox.p012ui.glass.MeloXBackdropComponentsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;

/* JADX INFO: compiled from: MeloXQueuePanel.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a?\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0003¢\u0006\u0002\u0010\u000f¨\u0006\u0010"}, d2 = {"MeloXQueuePanel", "", "state", "Lcom/lladlam/melox/ui/player/MeloXPlaybackUiState;", "modifier", "Landroidx/compose/ui/Modifier;", "(Lcom/lladlam/melox/ui/player/MeloXPlaybackUiState;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "QueueModeButton", "label", "", "selected", "", "enabled", "onClick", "Lkotlin/Function0;", "(Ljava/lang/String;ZZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "app"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class MeloXQueuePanelKt {
    static final Unit MeloXQueuePanel$lambda$2(MeloXPlaybackUiState meloXPlaybackUiState, Modifier modifier, int i, int i2, Composer composer, int i3) {
        MeloXQueuePanel(meloXPlaybackUiState, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit QueueModeButton$lambda$1(String str, boolean z, boolean z2, Function0 function0, Modifier modifier, int i, int i2, Composer composer, int i3) {
        QueueModeButton(str, z, z2, function0, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:109:0x07b9  */
    /* JADX WARN: Code duplicated, block: B:113:0x080c  */
    /* JADX WARN: Code duplicated, block: B:117:0x08bc  */
    /* JADX WARN: Code duplicated, block: B:119:0x0950  */
    /* JADX WARN: Code duplicated, block: B:122:0x095c  */
    /* JADX WARN: Code duplicated, block: B:123:0x0962  */
    /* JADX WARN: Code duplicated, block: B:125:0x0a3f  */
    /* JADX WARN: Code duplicated, block: B:127:0x0a8c  */
    /* JADX WARN: Code duplicated, block: B:128:0x0a8e  */
    /* JADX WARN: Code duplicated, block: B:135:0x0aa3  */
    /* JADX WARN: Code duplicated, block: B:139:0x0aeb  */
    public static final void MeloXQueuePanel(final MeloXPlaybackUiState state, Modifier modifier, Composer $composer, final int $changed, final int i) {
        final Modifier modifier2;
        final List upcoming;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function2;
        Function0<ComposeUiNode> function3;
        Composer composer;
        Composer composer2;
        Object objRememberedValue;
        Composer composer3;
        Object objRememberedValue2;
        boolean z;
        boolean z2;
        Object objRememberedValue3;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function4;
        Intrinsics.checkNotNullParameter(state, "state");
        Composer $composer2 = $composer.startRestartGroup(1206337810);
        ComposerKt.sourceInformation($composer2, "C(MeloXQueuePanel)N(state,modifier)48@1879L5244:MeloXQueuePanel.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(state) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if ($composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            Modifier modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1206337810, $dirty, -1, "com.lladlam.melox.ui.player.MeloXQueuePanel (MeloXQueuePanel.kt:35)");
            }
            MeloXQueueEntry currentEntry = (MeloXQueueEntry) CollectionsKt.getOrNull(state.getQueue(), state.getCurrentIndex());
            if (state.getCurrentIndex() < 0 || state.getQueue().isEmpty()) {
                upcoming = CollectionsKt.emptyList();
            } else {
                List listCreateListBuilder = CollectionsKt.createListBuilder();
                listCreateListBuilder.addAll(CollectionsKt.drop(state.getQueue(), state.getCurrentIndex() + 1));
                if (state.getRepeatMode() == 2 && state.getCurrentIndex() > 0) {
                    listCreateListBuilder.addAll(CollectionsKt.take(state.getQueue(), state.getCurrentIndex()));
                }
                upcoming = CollectionsKt.build(listCreateListBuilder);
            }
            Modifier modifierM1809paddingqDBjuR0$default = PaddingKt.m1809paddingqDBjuR0$default(modifier3, 0.0f, Dp.constructor_impl(8), 0.0f, 0.0f, 13, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $dirty2 = $dirty;
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierM1809paddingqDBjuR0$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i3 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor2;
                $composer2.createNode(function0);
            } else {
                function0 = constructor2;
                $composer2.useNode();
            }
            Composer composerM5188constructorimpl = Updater.constructor_impl($composer2);
            Updater.set_impl(composerM5188constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i4 = (i3 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i5 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -810160806, "C86@3279L30,88@3319L1089,120@4418L217:MeloXQueuePanel.kt#qhu5z0");
            if (currentEntry == null) {
                $composer2.startReplaceGroup(-810266642);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-810266641);
                ComposerKt.sourceInformation($composer2, "*52@1993L1266");
                Modifier modifierM1858height3ABfNKs = SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.constructor_impl(72));
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                Arrangement.Horizontal horizontalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(Dp.constructor_impl(12));
                ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalM1497spacedBy0680j_4, centerVertically, $composer2, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap2 = $composer2.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer2, modifierM1858height3ABfNKs);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int i6 = ((((438 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function1 = constructor3;
                    $composer2.createNode(function1);
                } else {
                    function1 = constructor3;
                    $composer2.useNode();
                }
                Composer composerM5188constructorimpl2 = Updater.constructor_impl($composer2);
                Updater.set_impl(composerM5188constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.set_impl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.set_impl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.reconcile_impl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.set_impl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i7 = (i6 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                int i8 = ((438 >> 6) & 112) | 6;
                RowScope rowScope = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, 1406843510, "C59@2271L205,65@2493L752:MeloXQueuePanel.kt#qhu5z0");
                MeloXPlayerUiKt.Artwork(currentEntry.getArtworkUrl(), ClipKt.clip(SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(68)), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(10))), $composer2, 0);
                Modifier modifierWeight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap3 = $composer2.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer2, modifierWeight$default);
                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                int i9 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function2 = constructor4;
                    $composer2.createNode(function2);
                } else {
                    function2 = constructor4;
                    $composer2.useNode();
                }
                Composer composerM5188constructorimpl3 = Updater.constructor_impl($composer2);
                Updater.set_impl(composerM5188constructorimpl3, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.set_impl(composerM5188constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.set_impl(composerM5188constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.reconcile_impl(composerM5188constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.set_impl(composerM5188constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i10 = (i9 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                int i11 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 1525150969, "C66@2554L312,74@2887L340:MeloXQueuePanel.kt#qhu5z0");
                TextKt.m3912TextNvy7gAk(currentEntry.getTitle(), null, Color.INSTANCE.m6105getWhite0d7_KjU(), null, TextUnitKt.getSp(17), null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, 0L, TextOverflow.INSTANCE.m8816getEllipsisgIe3tQ8(), false, 1, 0, null, null, $composer2, 1597824, 24960, 241578);
                String artist = currentEntry.getArtist();
                long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
                TextKt.m3912TextNvy7gAk(artist, PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.constructor_impl(2), 0.0f, 0.0f, 13, null), Color.copy_wmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU) : 0.64f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU) : 0.0f), null, TextUnitKt.getSp(14), null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m8816getEllipsisgIe3tQ8(), false, 1, 0, null, null, $composer2, 25008, 24960, 241640);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Unit unit = Unit.INSTANCE;
                $composer2.endReplaceGroup();
                Unit unit2 = Unit.INSTANCE;
            }
            SpacerKt.Spacer(SizeKt.m1858height3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(14)), $composer2, 6);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.Horizontal horizontalM1497spacedBy0680j_5 = Arrangement.INSTANCE.m1497spacedBy0680j_4(Dp.constructor_impl(14));
            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(horizontalM1497spacedBy0680j_5, Alignment.INSTANCE.getTop(), $composer2, ((54 >> 3) & 14) | ((54 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap4 = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer2, modifierFillMaxWidth$default);
            Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
            int i12 = ((((54 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function3 = constructor5;
                $composer2.createNode(function3);
            } else {
                function3 = constructor5;
                $composer2.useNode();
            }
            Composer composerM5188constructorimpl4 = Updater.constructor_impl($composer2);
            Updater.set_impl(composerM5188constructorimpl4, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            int i13 = (i12 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i14 = ((54 >> 6) & 112) | 6;
            RowScope rowScope2 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 212561730, "C95@3582L20,92@3461L204,101@3879L22,98@3678L286,108@4116L2,104@3977L204,115@4333L2,111@4194L204:MeloXQueuePanel.kt#qhu5z0");
            boolean shuffleEnabled = state.getShuffleEnabled();
            ComposerKt.sourceInformationMarkerStart($composer2, -962971540, "CC(remember):MeloXQueuePanel.kt#9igjgp");
            boolean z3 = ($dirty2 & 14) == 4;
            MeloXQueuePanelKt$MeloXQueuePanel$1$2$1$1 meloXQueuePanelKt$MeloXQueuePanel$1$2$1$1RememberedValue = $composer2.rememberedValue();
            if (z3 || meloXQueuePanelKt$MeloXQueuePanel$1$2$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                meloXQueuePanelKt$MeloXQueuePanel$1$2$1$1RememberedValue = new MeloXQueuePanelKt$MeloXQueuePanel$1$2$1$1(state);
                $composer2.updateRememberedValue(meloXQueuePanelKt$MeloXQueuePanel$1$2$1$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            QueueModeButton("↝", shuffleEnabled, false, (Function0) ((KFunction) meloXQueuePanelKt$MeloXQueuePanel$1$2$1$1RememberedValue), RowScope.weight$default(rowScope2, Modifier.INSTANCE, 1.0f, false, 2, null), $composer2, 6, 4);
            String str = state.getRepeatMode() == 1 ? "↻1" : "↻";
            boolean z4 = state.getRepeatMode() != 0;
            ComposerKt.sourceInformationMarkerStart($composer2, -962962034, "CC(remember):MeloXQueuePanel.kt#9igjgp");
            boolean z5 = ($dirty2 & 14) == 4;
            MeloXQueuePanelKt$MeloXQueuePanel$1$2$2$1 meloXQueuePanelKt$MeloXQueuePanel$1$2$2$1RememberedValue = $composer2.rememberedValue();
            if (z5) {
                composer = $composer2;
            } else {
                composer = $composer2;
                if (meloXQueuePanelKt$MeloXQueuePanel$1$2$2$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                QueueModeButton(str, z4, false, (Function0) ((KFunction) meloXQueuePanelKt$MeloXQueuePanel$1$2$2$1RememberedValue), RowScope.weight$default(rowScope2, Modifier.INSTANCE, 1.0f, false, 2, null), composer, 0, 4);
                composer2 = composer;
                ComposerKt.sourceInformationMarkerStart(composer2, -962954470, "CC(remember):MeloXQueuePanel.kt#9igjgp");
                objRememberedValue = composer2.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function0() { // from class: com.lladlam.melox.ui.player.MeloXQueuePanelKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    composer2.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                QueueModeButton("∞", false, false, (Function0) objRememberedValue, RowScope.weight$default(rowScope2, Modifier.INSTANCE, 1.0f, false, 2, null), composer, 3510, 0);
                composer3 = composer;
                ComposerKt.sourceInformationMarkerStart(composer3, -962947526, "CC(remember):MeloXQueuePanel.kt#9igjgp");
                objRememberedValue2 = composer3.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = new Function0() { // from class: com.lladlam.melox.ui.player.MeloXQueuePanelKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    composer3.updateRememberedValue(obj2);
                    objRememberedValue2 = obj2;
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                QueueModeButton("◎", false, false, (Function0) objRememberedValue2, RowScope.weight$default(rowScope2, Modifier.INSTANCE, 1.0f, false, 2, null), composer, 3510, 0);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                TextKt.m3912TextNvy7gAk("继续播放", PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.constructor_impl(18), 0.0f, Dp.constructor_impl(10), 5, null), Color.INSTANCE.m6105getWhite0d7_KjU(), null, TextUnitKt.getSp(22), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597878, 0, 262056);
                if (upcoming.isEmpty()) {
                    $composer2.startReplaceGroup(-807613351);
                    ComposerKt.sourceInformation($composer2, "129@4683L373");
                    Modifier modifierM1858height3ABfNKs2 = SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.constructor_impl(112));
                    Alignment center = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap5 = $composer2.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier($composer2, modifierM1858height3ABfNKs2);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int i15 = ((((54 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                        function4 = constructor;
                        $composer2.createNode(function4);
                    } else {
                        function4 = constructor;
                        $composer2.useNode();
                    }
                    Composer composerM5188constructorimpl5 = Updater.constructor_impl($composer2);
                    Updater.set_impl(composerM5188constructorimpl5, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.set_impl(composerM5188constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.set_impl(composerM5188constructorimpl5, Integer.valueOf(iHashCode5), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.reconcile_impl(composerM5188constructorimpl5, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.set_impl(composerM5188constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                    int i16 = (i15 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    int i17 = ((54 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 189739304, "C135@4882L160:MeloXQueuePanel.kt#qhu5z0");
                    long jM6105getWhite0d7_KjU2 = Color.INSTANCE.m6105getWhite0d7_KjU();
                    TextKt.m3912TextNvy7gAk("没有待播放歌曲", null, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU2, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU2) : 0.55f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU2) : 0.0f), null, TextUnitKt.getSp(15), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24966, 0, 262122);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endReplaceGroup();
                } else {
                    $composer2.startReplaceGroup(-807162487);
                    ComposerKt.sourceInformation($composer2, "146@5293L1814,142@5086L2021");
                    Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                    PaddingValues paddingValuesM1802PaddingValuesa9UjIt4$default = PaddingKt.m1802PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, Dp.constructor_impl(12), 7, null);
                    Arrangement.HorizontalOrVertical horizontalOrVerticalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(Dp.constructor_impl(4));
                    ComposerKt.sourceInformationMarkerStart($composer2, 666706002, "CC(remember):MeloXQueuePanel.kt#9igjgp");
                    boolean zChangedInstance = $composer2.changedInstance(upcoming);
                    if (($dirty2 & 14) == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    z2 = zChangedInstance | z;
                    objRememberedValue3 = $composer2.rememberedValue();
                    if (z2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        Object obj3 = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXQueuePanelKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj4) {
                                return MeloXQueuePanelKt.MeloXQueuePanel$lambda$1$3$0(upcoming, state, (LazyListScope) obj4);
                            }
                        };
                        $composer2.updateRememberedValue(obj3);
                        objRememberedValue3 = obj3;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    LazyDslKt.LazyColumn(modifierFillMaxSize$default, null, paddingValuesM1802PaddingValuesa9UjIt4$default, false, horizontalOrVerticalM1497spacedBy0680j_4, null, null, false, null, (Function1) objRememberedValue3, $composer2, 24966, 490);
                    $composer2.endReplaceGroup();
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier3;
            }
            meloXQueuePanelKt$MeloXQueuePanel$1$2$2$1RememberedValue = new MeloXQueuePanelKt$MeloXQueuePanel$1$2$2$1(state);
            $composer2.updateRememberedValue(meloXQueuePanelKt$MeloXQueuePanel$1$2$2$1RememberedValue);
            ComposerKt.sourceInformationMarkerEnd(composer);
            QueueModeButton(str, z4, false, (Function0) ((KFunction) meloXQueuePanelKt$MeloXQueuePanel$1$2$2$1RememberedValue), RowScope.weight$default(rowScope2, Modifier.INSTANCE, 1.0f, false, 2, null), composer, 0, 4);
            composer2 = composer;
            ComposerKt.sourceInformationMarkerStart(composer2, -962954470, "CC(remember):MeloXQueuePanel.kt#9igjgp");
            objRememberedValue = composer2.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj4 = new Function0() { // from class: com.lladlam.melox.ui.player.MeloXQueuePanelKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Unit.INSTANCE;
                    }
                };
                composer2.updateRememberedValue(obj4);
                objRememberedValue = obj4;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            QueueModeButton("∞", false, false, (Function0) objRememberedValue, RowScope.weight$default(rowScope2, Modifier.INSTANCE, 1.0f, false, 2, null), composer, 3510, 0);
            composer3 = composer;
            ComposerKt.sourceInformationMarkerStart(composer3, -962947526, "CC(remember):MeloXQueuePanel.kt#9igjgp");
            objRememberedValue2 = composer3.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj5 = new Function0() { // from class: com.lladlam.melox.ui.player.MeloXQueuePanelKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Unit.INSTANCE;
                    }
                };
                composer3.updateRememberedValue(obj5);
                objRememberedValue2 = obj5;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            QueueModeButton("◎", false, false, (Function0) objRememberedValue2, RowScope.weight$default(rowScope2, Modifier.INSTANCE, 1.0f, false, 2, null), composer, 3510, 0);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            TextKt.m3912TextNvy7gAk("继续播放", PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.constructor_impl(18), 0.0f, Dp.constructor_impl(10), 5, null), Color.INSTANCE.m6105getWhite0d7_KjU(), null, TextUnitKt.getSp(22), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597878, 0, 262056);
            if (upcoming.isEmpty()) {
                $composer2.startReplaceGroup(-807613351);
                ComposerKt.sourceInformation($composer2, "129@4683L373");
                Modifier modifierM1858height3ABfNKs3 = SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.constructor_impl(112));
                Alignment center2 = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap6 = $composer2.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier($composer2, modifierM1858height3ABfNKs3);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i18 = ((((54 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function4 = constructor;
                    $composer2.createNode(function4);
                } else {
                    function4 = constructor;
                    $composer2.useNode();
                }
                Composer composerM5188constructorimpl6 = Updater.constructor_impl($composer2);
                Updater.set_impl(composerM5188constructorimpl6, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.set_impl(composerM5188constructorimpl6, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.set_impl(composerM5188constructorimpl6, Integer.valueOf(iHashCode6), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.reconcile_impl(composerM5188constructorimpl6, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.set_impl(composerM5188constructorimpl6, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                int i19 = (i18 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                int i110 = ((54 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 189739304, "C135@4882L160:MeloXQueuePanel.kt#qhu5z0");
                long jM6105getWhite0d7_KjU3 = Color.INSTANCE.m6105getWhite0d7_KjU();
                TextKt.m3912TextNvy7gAk("没有待播放歌曲", null, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU3, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU3) : 0.55f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU3) : 0.0f), null, TextUnitKt.getSp(15), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24966, 0, 262122);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-807162487);
                ComposerKt.sourceInformation($composer2, "146@5293L1814,142@5086L2021");
                Modifier modifierFillMaxSize$default2 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                PaddingValues paddingValuesM1802PaddingValuesa9UjIt4$default2 = PaddingKt.m1802PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, Dp.constructor_impl(12), 7, null);
                Arrangement.HorizontalOrVertical horizontalOrVerticalM1497spacedBy0680j_5 = Arrangement.INSTANCE.m1497spacedBy0680j_4(Dp.constructor_impl(4));
                ComposerKt.sourceInformationMarkerStart($composer2, 666706002, "CC(remember):MeloXQueuePanel.kt#9igjgp");
                boolean zChangedInstance2 = $composer2.changedInstance(upcoming);
                if (($dirty2 & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                z2 = zChangedInstance2 | z;
                objRememberedValue3 = $composer2.rememberedValue();
                if (z2) {
                    Object obj6 = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXQueuePanelKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj7) {
                            return MeloXQueuePanelKt.MeloXQueuePanel$lambda$1$3$0(upcoming, state, (LazyListScope) obj7);
                        }
                    };
                    $composer2.updateRememberedValue(obj6);
                    objRememberedValue3 = obj6;
                } else {
                    Object obj7 = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXQueuePanelKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj8) {
                            return MeloXQueuePanelKt.MeloXQueuePanel$lambda$1$3$0(upcoming, state, (LazyListScope) obj8);
                        }
                    };
                    $composer2.updateRememberedValue(obj7);
                    objRememberedValue3 = obj7;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                LazyDslKt.LazyColumn(modifierFillMaxSize$default2, null, paddingValuesM1802PaddingValuesa9UjIt4$default2, false, horizontalOrVerticalM1497spacedBy0680j_5, null, null, false, null, (Function1) objRememberedValue3, $composer2, 24966, 490);
                $composer2.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXQueuePanelKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj8, Object obj9) {
                    return MeloXQueuePanelKt.MeloXQueuePanel$lambda$2(state, modifier2, $changed, i, (Composer) obj8, ((Integer) obj9).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXQueuePanel$lambda$1$3$0(final List $upcoming, final MeloXPlaybackUiState $state, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        final Function1 function1 = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXQueuePanelKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MeloXQueuePanelKt.MeloXQueuePanel$lambda$1$3$0$0((MeloXQueueEntry) obj);
            }
        };
        final Function1 function2 = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXQueuePanelKt$MeloXQueuePanel$lambda$1$3$0$$inlined$items$default$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                return invoke((MeloXQueueEntry) p1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Void invoke(MeloXQueueEntry meloXQueueEntry) {
                return null;
            }
        };
        LazyColumn.items($upcoming.size(), new Function1<Integer, Object>() { // from class: com.lladlam.melox.ui.player.MeloXQueuePanelKt$MeloXQueuePanel$lambda$1$3$0$$inlined$items$default$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int index) {
                return function1.invoke($upcoming.get(index));
            }
        }, new Function1<Integer, Object>() { // from class: com.lladlam.melox.ui.player.MeloXQueuePanelKt$MeloXQueuePanel$lambda$1$3$0$$inlined$items$default$3
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int index) {
                return function2.invoke($upcoming.get(index));
            }
        }, ComposableLambdaKt.composableLambdaInstance(802480018, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.lladlam.melox.ui.player.MeloXQueuePanelKt$MeloXQueuePanel$lambda$1$3$0$$inlined$items$default$4
            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope $this$items, int it, Composer $composer, int $changed) {
                Function0<ComposeUiNode> function0;
                Function0<ComposeUiNode> function3;
                ComposerKt.sourceInformation($composer, "CN(it)178@8834L22:LazyDsl.kt#428nma");
                int $dirty = $changed;
                if (($changed & 6) == 0) {
                    $dirty |= $composer.changed($this$items) ? 4 : 2;
                }
                if (($changed & 48) == 0) {
                    $dirty |= $composer.changed(it) ? 32 : 16;
                }
                if ($composer.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(802480018, $dirty, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:178)");
                    }
                    int i = $dirty & 14;
                    final MeloXQueueEntry meloXQueueEntry = (MeloXQueueEntry) $upcoming.get(it);
                    $composer.startReplaceGroup(232162172);
                    ComposerKt.sourceInformation($composer, "CN(entry)*155@5672L37,151@5479L1596:MeloXQueuePanel.kt#qhu5z0");
                    Modifier modifierClip = ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(12)));
                    ComposerKt.sourceInformationMarkerStart($composer, 838777712, "CC(remember):MeloXQueuePanel.kt#9igjgp");
                    boolean zChanged = $composer.changed($state) | ((((i & 112) ^ 48) > 32 && $composer.changed(meloXQueueEntry)) || (i & 48) == 32);
                    Object objRememberedValue = $composer.rememberedValue();
                    if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        final MeloXPlaybackUiState meloXPlaybackUiState = $state;
                        Object obj = (Function0) new Function0<Unit>() { // from class: com.lladlam.melox.ui.player.MeloXQueuePanelKt$MeloXQueuePanel$1$4$1$2$1$1
                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                meloXPlaybackUiState.playQueueIndex(meloXQueueEntry.getIndex());
                            }
                        };
                        $composer.updateRememberedValue(obj);
                        objRememberedValue = obj;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Modifier modifierM1807paddingVpY3zN4$default = PaddingKt.m1807paddingVpY3zN4$default(ClickableKt.m1078clickableoSLSa3U$default(modifierClip, false, null, null, null, (Function0) objRememberedValue, 15, null), 0.0f, Dp.constructor_impl(4), 1, null);
                    Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                    Arrangement.Horizontal horizontalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(Dp.constructor_impl(12));
                    ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalM1497spacedBy0680j_4, centerVertically, $composer, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM1807paddingVpY3zN4$default);
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int i2 = ((((432 << 3) & 112) << 6) & 896) | 6;
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
                    Composer composerM5188constructorimpl = Updater.constructor_impl($composer);
                    Updater.set_impl(composerM5188constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                    int i3 = (i2 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    int i4 = ((432 >> 6) & 112) | 6;
                    RowScope rowScope = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer, 872745561, "C160@5962L244,166@6231L822:MeloXQueuePanel.kt#qhu5z0");
                    MeloXPlayerUiKt.Artwork(meloXQueueEntry.getArtworkUrl(), ClipKt.clip(SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(48)), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(6))), $composer, 0);
                    Modifier modifierWeight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierWeight$default);
                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int i5 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function3 = constructor2;
                        $composer.createNode(function3);
                    } else {
                        function3 = constructor2;
                        $composer.useNode();
                    }
                    Composer composerM5188constructorimpl2 = Updater.constructor_impl($composer);
                    Updater.set_impl(composerM5188constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.set_impl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.set_impl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.reconcile_impl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.set_impl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i6 = (i5 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                    int i7 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 1641527099, "C167@6300L302,174@6631L396:MeloXQueuePanel.kt#qhu5z0");
                    TextKt.m3912TextNvy7gAk(meloXQueueEntry.getTitle(), null, Color.INSTANCE.m6105getWhite0d7_KjU(), null, TextUnitKt.getSp(16), null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m8816getEllipsisgIe3tQ8(), false, 1, 0, null, null, $composer, 24960, 24960, 241642);
                    String artist = meloXQueueEntry.getArtist();
                    long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
                    TextKt.m3912TextNvy7gAk(artist, PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.constructor_impl(2), 0.0f, 0.0f, 13, null), Color.copy_wmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU) : 0.58f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU) : 0.0f), null, TextUnitKt.getSp(14), null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m8816getEllipsisgIe3tQ8(), false, 1, 0, null, null, $composer, 25008, 24960, 241640);
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
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                $composer.skipToGroupEnd();
            }
        }));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object MeloXQueuePanel$lambda$1$3$0$0(MeloXQueueEntry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        return entry.getMediaId() + "-" + entry.getIndex();
    }

    private static final void QueueModeButton(final String label, final boolean selected, boolean enabled, final Function0<Unit> function0, Modifier modifier, Composer $composer, final int $changed, final int i) {
        String str;
        boolean z;
        Modifier modifier2;
        Composer $composer2;
        boolean enabled2;
        final Modifier.Companion modifier3;
        int i2;
        boolean enabled3;
        long jM6066copywmQWz5c;
        long jM6066copywmQWz5c2;
        Composer $composer3 = $composer.startRestartGroup(1477669708);
        ComposerKt.sourceInformation($composer3, "C(QueueModeButton)N(label,selected,enabled,onClick,modifier)202@7425L415,198@7307L998:MeloXQueuePanel.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            str = label;
            $dirty |= $composer3.changed(str) ? 4 : 2;
        } else {
            str = label;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(selected) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= RendererCapabilities.DECODER_SUPPORT_MASK;
            z = enabled;
        } else if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            z = enabled;
            $dirty |= $composer3.changed(z) ? 256 : 128;
        } else {
            z = enabled;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 2048 : 1024;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty |= 24576;
            modifier2 = modifier;
        } else if (($changed & 24576) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 16384 : 8192;
        } else {
            modifier2 = modifier;
        }
        if (!$composer3.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            enabled2 = z;
            modifier3 = modifier2;
        } else {
            if (i3 != 0) {
                enabled3 = true;
                i2 = i4;
            } else {
                i2 = i4;
                enabled3 = z;
            }
            if (i2 == 0) {
                modifier3 = modifier2;
            } else {
                modifier3 = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1477669708, $dirty, -1, "com.lladlam.melox.ui.player.QueueModeButton (MeloXQueuePanel.kt:197)");
            }
            Modifier modifierClip = ClipKt.clip(SizeKt.m1858height3ABfNKs(modifier3, Dp.constructor_impl(44)), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(22)));
            RoundedCornerShape roundedCornerShapeM2135RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(22));
            if (selected) {
                long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
                jM6066copywmQWz5c = Color.copy_wmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU) : 0.62f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU) : 0.0f);
            } else {
                long jM6105getWhite0d7_KjU2 = Color.INSTANCE.m6105getWhite0d7_KjU();
                jM6066copywmQWz5c = Color.copy_wmQWz5c(jM6105getWhite0d7_KjU2, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU2) : 0.1f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU2) : 0.0f);
            }
            long j = jM6066copywmQWz5c;
            $composer2 = $composer3;
            boolean enabled4 = enabled3;
            Modifier modifierM1078clickableoSLSa3U$default = ClickableKt.m1078clickableoSLSa3U$default(MeloXBackdropComponentsKt.m9633meloXLiquidButtonNsDo4u0(modifierClip, roundedCornerShapeM2135RoundedCornerShape0680j_4, enabled3, 0L, j, 0.0f, selected ? Dp.constructor_impl(11) : Dp.constructor_impl(8), Dp.constructor_impl(16), $composer3, ($dirty & 896) | 12582912, 20), enabled4, null, null, null, function0, 14, null);
            enabled2 = enabled4;
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierM1078clickableoSLSa3U$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i5 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor);
            } else {
                $composer2.useNode();
            }
            Composer composerM5188constructorimpl = Updater.constructor_impl($composer2);
            Updater.set_impl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i6 = (i5 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i7 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1661565582, "C216@7964L335:MeloXQueuePanel.kt#qhu5z0");
            if (enabled2) {
                if (!selected) {
                    long jM6105getWhite0d7_KjU3 = Color.INSTANCE.m6105getWhite0d7_KjU();
                    jM6066copywmQWz5c2 = Color.copy_wmQWz5c(jM6105getWhite0d7_KjU3, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU3) : 0.86f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU3) : 0.0f);
                } else {
                    long jM6094getBlack0d7_KjU = Color.INSTANCE.m6094getBlack0d7_KjU();
                    jM6066copywmQWz5c2 = Color.copy_wmQWz5c(jM6094getBlack0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6094getBlack0d7_KjU) : 0.62f, (14 & 2) != 0 ? Color.getRed_impl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6094getBlack0d7_KjU) : 0.0f);
                }
            } else {
                long jM6105getWhite0d7_KjU4 = Color.INSTANCE.m6105getWhite0d7_KjU();
                jM6066copywmQWz5c2 = Color.copy_wmQWz5c(jM6105getWhite0d7_KjU4, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU4) : 0.25f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU4) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU4) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU4) : 0.0f);
            }
            TextKt.m3912TextNvy7gAk(str, null, jM6066copywmQWz5c2, null, TextUnitKt.getSp(18), null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, ($dirty & 14) | 1597440, 0, 262058);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final boolean enabled5 = enabled2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXQueuePanelKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXQueuePanelKt.QueueModeButton$lambda$1(label, selected, enabled5, function0, modifier3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
