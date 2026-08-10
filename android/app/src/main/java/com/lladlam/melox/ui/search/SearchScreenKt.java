package com.lladlam.melox.p012ui.search;

import android.content.Context;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
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
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.DividerKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.media3.exoplayer.RendererCapabilities;
import com.lladlam.melox.core.account.NeteaseSessionStore;
import com.lladlam.melox.core.model.SearchSong;
import com.lladlam.melox.core.network.NeteaseSearchClient;
import com.lladlam.melox.p012ui.MeloXLayoutKt;
import com.lladlam.melox.p012ui.glass.MeloXBackdropComponentsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SearchScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0002\u001a+\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0003¢\u0006\u0002\u0010\n¨\u0006\u000b²\u0006\n\u0010\f\u001a\u00020\rX\u008a\u008e\u0002²\u0006\u0010\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fX\u008a\u008e\u0002²\u0006\n\u0010\u0010\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\f\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u008a\u008e\u0002²\u0006\f\u0010\u0013\u001a\u0004\u0018\u00010\rX\u008a\u008e\u0002"}, d2 = {"SearchScreen", "", "(Landroidx/compose/runtime/Composer;I)V", "SongResultRow", "song", "Lcom/lladlam/melox/core/model/SearchSong;", "isResolving", "", "onClick", "Lkotlin/Function0;", "(Lcom/lladlam/melox/core/model/SearchSong;ZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app", "query", "", "results", "", "isLoading", "resolvingSongId", "", "errorMessage"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class SearchScreenKt {
    static final Unit SearchScreen$lambda$17(int i, Composer composer, int i2) {
        SearchScreen(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit SongResultRow$lambda$1(SearchSong searchSong, boolean z, Function0 function0, int i, Composer composer, int i2) {
        SongResultRow(searchSong, z, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void SearchScreen(Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        MutableState isLoading$delegate;
        String str;
        Function0<ComposeUiNode> function2;
        Composer $composer3 = $composer.startRestartGroup(-321241377);
        ComposerKt.sourceInformation($composer3, "C(SearchScreen)45@1991L7,47@2063L24,48@2105L144,54@2268L31,55@2319L58,56@2399L34,57@2461L40,58@2526L42,107@4253L4026:SearchScreen.kt#p6k06t");
        if ($composer3.shouldExecute($changed != 0, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-321241377, $changed, -1, "com.lladlam.melox.ui.search.SearchScreen (SearchScreen.kt:44)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Context context = (Context) objConsume;
            final Context appContext = context.getApplicationContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)N(getContext)616@28039L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer3, 683736516, "CC(remember):Effects.kt#9igjgp");
            Object objRememberedValue = $composer3.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object objCreateCompositionCoroutineScope = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3);
                $composer3.updateRememberedValue(objCreateCompositionCoroutineScope);
                objRememberedValue = objCreateCompositionCoroutineScope;
            }
            final CoroutineScope scope = (CoroutineScope) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 524687471, "CC(remember):SearchScreen.kt#9igjgp");
            boolean zChanged = $composer3.changed(appContext);
            Object objRememberedValue2 = $composer3.rememberedValue();
            if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object neteaseSearchClient = new NeteaseSearchClient(null, new Function0() { // from class: com.lladlam.melox.ui.search.SearchScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SearchScreenKt.SearchScreen$lambda$0$0(appContext);
                    }
                }, 1, null);
                $composer3.updateRememberedValue(neteaseSearchClient);
                objRememberedValue2 = neteaseSearchClient;
            }
            final NeteaseSearchClient client = (NeteaseSearchClient) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 524692574, "CC(remember):SearchScreen.kt#9igjgp");
            Object objRememberedValue3 = $composer3.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default);
                objRememberedValue3 = objMutableStateOf$default;
            }
            final MutableState query$delegate = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 524694233, "CC(remember):SearchScreen.kt#9igjgp");
            Object objRememberedValue4 = $composer3.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(CollectionsKt.emptyList(), null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default2);
                objRememberedValue4 = objMutableStateOf$default2;
            }
            final MutableState results$delegate = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 524696769, "CC(remember):SearchScreen.kt#9igjgp");
            Object objRememberedValue5 = $composer3.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf((boolean) r3), null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default3);
                objRememberedValue5 = objMutableStateOf$default3;
            }
            MutableState mutableState = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 524698759, "CC(remember):SearchScreen.kt#9igjgp");
            Object objRememberedValue6 = $composer3.rememberedValue();
            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default4);
                objRememberedValue6 = objMutableStateOf$default4;
            }
            final MutableState resolvingSongId$delegate = (MutableState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 524700841, "CC(remember):SearchScreen.kt#9igjgp");
            Object objRememberedValue7 = $composer3.rememberedValue();
            if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default5);
                objRememberedValue7 = objMutableStateOf$default5;
            }
            final MutableState errorMessage$delegate = (MutableState) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierM1806paddingVpY3zN4 = PaddingKt.m1806paddingVpY3zN4(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.constructor_impl(22), Dp.constructor_impl(14));
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            $composer2 = $composer3;
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1806paddingVpY3zN4);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((6 << 3) & 112) << 6) & 896) | 6;
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
            int i3 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -219799099, "C114@4457L10,112@4392L181,118@4582L30,120@4622L1826,165@6458L30:SearchScreen.kt#p6k06t");
            TextKt.m3912TextNvy7gAk("搜索", null, 0L, null, 0L, null, FontWeight.INSTANCE.getBold(), null, TextUnitKt.getSp(-0.5d), null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getHeadlineLarge(), $composer3, 1572870, 0, 130750);
            SpacerKt.Spacer(SizeKt.m1858height3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(12)), $composer3, 6);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.Horizontal horizontalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(Dp.constructor_impl(10));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalM1497spacedBy0680j_4, centerVertically, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer3, modifierFillMaxWidth$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((438 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function1 = constructor2;
                $composer3.createNode(function1);
            } else {
                function1 = constructor2;
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl2 = Updater.constructor_impl($composer3);
            Updater.set_impl(composerM5188constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i6 = ((438 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 234673884, "C127@4906L14,125@4824L285,141@5513L11,142@5587L11,138@5341L308,144@5716L14,147@5841L597,134@5187L1251:SearchScreen.kt#p6k06t");
            String strSearchScreen$lambda$2 = SearchScreen$lambda$2(query$delegate);
            Modifier modifierWeight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 1947233799, "CC(remember):SearchScreen.kt#9igjgp");
            Object objRememberedValue8 = $composer3.rememberedValue();
            if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue8 = new Function1() { // from class: com.lladlam.melox.ui.search.SearchScreenKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SearchScreenKt.SearchScreen$lambda$16$0$0$0(query$delegate, (String) obj);
                    }
                };
                $composer3.updateRememberedValue(objRememberedValue8);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            OutlinedTextFieldKt.OutlinedTextField(strSearchScreen$lambda$2, (Function1<? super String, Unit>) objRememberedValue8, modifierWeight$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$SearchScreenKt.INSTANCE.getLambda$350283091$app(), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$SearchScreenKt.INSTANCE.getLambda$1532515826$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer3, 14155824, 12582912, 0, 8257336);
            boolean z = (StringsKt.isBlank(SearchScreen$lambda$2(query$delegate)) || SearchScreen$lambda$8(mutableState)) ? false : true;
            Modifier modifierClip = ClipKt.clip(SizeKt.m1858height3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(48)), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(24)));
            RoundedCornerShape roundedCornerShapeM2135RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(24));
            long primary = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getPrimary();
            long primary2 = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getPrimary();
            Modifier modifierM9633meloXLiquidButtonNsDo4u0 = MeloXBackdropComponentsKt.m9633meloXLiquidButtonNsDo4u0(modifierClip, roundedCornerShapeM2135RoundedCornerShape0680j_4, z, primary, Color.copy_wmQWz5c(primary2, (14 & 1) != 0 ? Color.getAlpha_impl(primary2) : 0.58f, (14 & 2) != 0 ? Color.getRed_impl(primary2) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(primary2) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(primary2) : 0.0f), 0.0f, 0.0f, 0.0f, $composer3, 0, 112);
            ComposerKt.sourceInformationMarkerStart($composer3, 1947259719, "CC(remember):SearchScreen.kt#9igjgp");
            boolean zChangedInstance = $composer3.changedInstance(scope) | $composer3.changedInstance(client);
            Object objRememberedValue9 = $composer3.rememberedValue();
            if (zChangedInstance || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                isLoading$delegate = mutableState;
                Object searchScreenKt$SearchScreen$1$1$2$1 = new SearchScreenKt$SearchScreen$1$1$2$1(scope, query$delegate, isLoading$delegate, errorMessage$delegate, client, results$delegate);
                str = "";
                client = client;
                Object obj = (KFunction) searchScreenKt$SearchScreen$1$1$2$1;
                $composer3.updateRememberedValue(obj);
                objRememberedValue9 = obj;
            } else {
                isLoading$delegate = mutableState;
                str = "";
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final boolean z2 = z;
            SurfaceKt.m3769SurfaceT9BRK9s(ClickableKt.m1078clickableoSLSa3U$default(modifierM9633meloXLiquidButtonNsDo4u0, z2, null, null, null, (Function0) ((KFunction) objRememberedValue9), 14, null), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(24)), Color.INSTANCE.m6103getTransparent0d7_KjU(), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(604201428, true, new Function2() { // from class: com.lladlam.melox.ui.search.SearchScreenKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return SearchScreenKt.SearchScreen$lambda$16$0$2(z2, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer3, 54), $composer3, 12583296, 120);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            SpacerKt.Spacer(SizeKt.m1858height3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(12)), $composer3, 6);
            if (SearchScreen$lambda$14(errorMessage$delegate) != null) {
                $composer3.startReplaceGroup(-217796717);
                ComposerKt.sourceInformation($composer3, "170@6629L11,171@6686L10,168@6538L184,173@6735L29");
                String strSearchScreen$lambda$14 = SearchScreen$lambda$14(errorMessage$delegate);
                TextKt.m3912TextNvy7gAk(strSearchScreen$lambda$14 == null ? str : strSearchScreen$lambda$14, null, MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getError(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getBodyMedium(), $composer3, 0, 0, 131066);
                SpacerKt.Spacer(SizeKt.m1858height3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(8)), $composer3, 6);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(-217556467);
                $composer3.endReplaceGroup();
            }
            if (SearchScreen$lambda$8(isLoading$delegate)) {
                $composer3.startReplaceGroup(-217507301);
                ComposerKt.sourceInformation($composer3, "178@6834L210");
                Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Arrangement.Horizontal center = Arrangement.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(center, Alignment.INSTANCE.getTop(), $composer3, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                CompositionLocalMap currentCompositionLocalMap3 = $composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer3, modifierFillMaxWidth$default2);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int i7 = ((((54 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function2 = constructor3;
                    $composer3.createNode(function2);
                } else {
                    function2 = constructor3;
                    $composer3.useNode();
                }
                Composer composerM5188constructorimpl3 = Updater.constructor_impl($composer3);
                Updater.set_impl(composerM5188constructorimpl3, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.set_impl(composerM5188constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.set_impl(composerM5188constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.reconcile_impl(composerM5188constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.set_impl(composerM5188constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i8 = (i7 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int i9 = ((54 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 895192887, "C182@6999L27:SearchScreen.kt#p6k06t");
                ProgressIndicatorKt.m3567CircularProgressIndicator4lLiAd8(null, 0L, 0.0f, 0L, 0, 0.0f, $composer3, 0, 63);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
            } else if (SearchScreen$lambda$5(results$delegate).isEmpty()) {
                $composer3.startReplaceGroup(-217231308);
                ComposerKt.sourceInformation($composer3, "189@7236L11,190@7321L10,187@7111L249");
                long onSurface = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnSurface();
                TextKt.m3912TextNvy7gAk("先搜索一首歌。当前 Android 版会使用 MeloX 同源的网易云 EAPI 获取结果。", null, Color.copy_wmQWz5c(onSurface, (14 & 1) != 0 ? Color.getAlpha_impl(onSurface) : 0.6f, (14 & 2) != 0 ? Color.getRed_impl(onSurface) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(onSurface) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(onSurface) : 0.0f), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getBodyLarge(), $composer3, 6, 0, 131066);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(-216912566);
                ComposerKt.sourceInformation($composer3, "200@7671L578,195@7414L835");
                Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                PaddingValues paddingValuesM1802PaddingValuesa9UjIt4$default = PaddingKt.m1802PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, MeloXLayoutKt.getMeloXBottomContentClearance(), 7, null);
                ComposerKt.sourceInformationMarkerStart($composer3, -422630665, "CC(remember):SearchScreen.kt#9igjgp");
                boolean zChangedInstance2 = $composer3.changedInstance(scope) | $composer3.changedInstance(client) | $composer3.changedInstance(context);
                Object objRememberedValue10 = $composer3.rememberedValue();
                if (zChangedInstance2 || objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = new Function1() { // from class: com.lladlam.melox.ui.search.SearchScreenKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            return SearchScreenKt.SearchScreen$lambda$16$2$0(results$delegate, scope, client, context, resolvingSongId$delegate, errorMessage$delegate, (LazyListScope) obj3);
                        }
                    };
                    $composer3.updateRememberedValue(obj2);
                    objRememberedValue10 = obj2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                LazyDslKt.LazyColumn(modifierFillMaxSize$default, null, paddingValuesM1802PaddingValuesa9UjIt4$default, false, null, null, null, false, null, (Function1) objRememberedValue10, $composer3, 390, 506);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.search.SearchScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return SearchScreenKt.SearchScreen$lambda$17($changed, (Composer) obj3, ((Integer) obj4).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String SearchScreen$lambda$0$0(Context $appContext) {
        NeteaseSessionStore.Companion companion = NeteaseSessionStore.INSTANCE;
        Intrinsics.checkNotNull($appContext);
        return companion.readCookie($appContext);
    }

    private static final String SearchScreen$lambda$2(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<SearchSong> SearchScreen$lambda$5(MutableState<List<SearchSong>> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean SearchScreen$lambda$8(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SearchScreen$lambda$9(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Long SearchScreen$lambda$11(MutableState<Long> mutableState) {
        return mutableState.getValue();
    }

    private static final String SearchScreen$lambda$14(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SearchScreen$submitSearch(CoroutineScope scope, MutableState<String> mutableState, MutableState<Boolean> mutableState2, MutableState<String> mutableState3, NeteaseSearchClient client, MutableState<List<SearchSong>> mutableState4) {
        String keywords = StringsKt.trim((CharSequence) SearchScreen$lambda$2(mutableState)).toString();
        if (!(keywords.length() == 0) && !SearchScreen$lambda$8(mutableState2)) {
            BuildersKt__Builders_commonKt.launch$default(scope, null, null, new SearchScreenKt$SearchScreen$submitSearch$1(mutableState2, mutableState3, client, keywords, mutableState4, null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SearchScreen$playSong(CoroutineScope scope, MutableState<Long> mutableState, MutableState<String> mutableState2, NeteaseSearchClient client, MutableState<List<SearchSong>> mutableState3, Context context, SearchSong song) {
        if (SearchScreen$lambda$11(mutableState) != null) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new SearchScreenKt$SearchScreen$playSong$1(song, mutableState, mutableState2, client, mutableState3, context, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SearchScreen$lambda$16$0$0$0(MutableState $query$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $query$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SearchScreen$lambda$16$0$2(boolean $searchEnabled, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        long j;
        ComposerKt.sourceInformation($composer, "C148@5859L565:SearchScreen.kt#p6k06t");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(604201428, $changed, -1, "com.lladlam.melox.ui.search.SearchScreen.<anonymous>.<anonymous>.<anonymous> (SearchScreen.kt:148)");
            }
            Modifier modifierM1807paddingVpY3zN4$default = PaddingKt.m1807paddingVpY3zN4$default(Modifier.INSTANCE, Dp.constructor_impl(18), 0.0f, 2, null);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM1807paddingVpY3zN4$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((54 << 3) & 112) << 6) & 896) | 6;
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
            Updater.set_impl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1771961725, "C152@6030L376:SearchScreen.kt#p6k06t");
            if ($searchEnabled) {
                $composer.startReplaceGroup(1772039193);
                ComposerKt.sourceInformation($composer, "155@6161L11");
                long onPrimary = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnPrimary();
                $composer.endReplaceGroup();
                j = onPrimary;
            } else {
                $composer.startReplaceGroup(1772136037);
                ComposerKt.sourceInformation($composer, "157@6258L11");
                long onSurface = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurface();
                long jM6066copywmQWz5c = Color.copy_wmQWz5c(onSurface, (14 & 1) != 0 ? Color.getAlpha_impl(onSurface) : 0.32f, (14 & 2) != 0 ? Color.getRed_impl(onSurface) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(onSurface) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(onSurface) : 0.0f);
                $composer.endReplaceGroup();
                j = jM6066copywmQWz5c;
            }
            TextKt.m3912TextNvy7gAk("搜索", null, j, null, 0L, null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1572870, 0, 262074);
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

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SearchScreen$lambda$16$2$0(final MutableState $results$delegate, final CoroutineScope $scope, final NeteaseSearchClient $client, final Context $context, final MutableState $resolvingSongId$delegate, final MutableState $errorMessage$delegate, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        final List<SearchSong> listSearchScreen$lambda$5 = SearchScreen$lambda$5($results$delegate);
        final Function1 function1 = new Function1() { // from class: com.lladlam.melox.ui.search.SearchScreenKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SearchScreenKt.SearchScreen$lambda$16$2$0$0((SearchSong) obj);
            }
        };
        final Function1 function2 = new Function1() { // from class: com.lladlam.melox.ui.search.SearchScreenKt$SearchScreen$lambda$16$2$0$$inlined$items$default$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                return invoke((SearchSong) p1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Void invoke(SearchSong searchSong) {
                return null;
            }
        };
        LazyColumn.items(listSearchScreen$lambda$5.size(), new Function1<Integer, Object>() { // from class: com.lladlam.melox.ui.search.SearchScreenKt$SearchScreen$lambda$16$2$0$$inlined$items$default$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int index) {
                return function1.invoke(listSearchScreen$lambda$5.get(index));
            }
        }, new Function1<Integer, Object>() { // from class: com.lladlam.melox.ui.search.SearchScreenKt$SearchScreen$lambda$16$2$0$$inlined$items$default$3
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int index) {
                return function2.invoke(listSearchScreen$lambda$5.get(index));
            }
        }, ComposableLambdaKt.composableLambdaInstance(802480018, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.lladlam.melox.ui.search.SearchScreenKt$SearchScreen$lambda$16$2$0$$inlined$items$default$4
            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope $this$items, int it, Composer $composer, int $changed) {
                boolean z;
                Composer composer;
                ComposerKt.sourceInformation($composer, "CN(it)178@8834L22:LazyDsl.kt#428nma");
                int $dirty = $changed;
                if (($changed & 6) == 0) {
                    $dirty |= $composer.changed($this$items) ? 4 : 2;
                }
                if (($changed & 48) == 0) {
                    $dirty |= $composer.changed(it) ? 32 : 16;
                }
                if (!$composer.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
                    $composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(802480018, $dirty, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:178)");
                }
                int i = $dirty & 14;
                final SearchSong searchSong = (SearchSong) listSearchScreen$lambda$5.get(it);
                $composer.startReplaceGroup(-70529322);
                ComposerKt.sourceInformation($composer, "CN(song)*208@8002L18,205@7838L209,211@8141L11,210@8072L137:SearchScreen.kt#p6k06t");
                Long lSearchScreen$lambda$11 = SearchScreenKt.SearchScreen$lambda$11($resolvingSongId$delegate);
                boolean z2 = lSearchScreen$lambda$11 != null && lSearchScreen$lambda$11.longValue() == searchSong.getId();
                ComposerKt.sourceInformationMarkerStart($composer, 551919084, "CC(remember):SearchScreen.kt#9igjgp");
                boolean zChangedInstance = $composer.changedInstance($scope) | $composer.changedInstance($client) | $composer.changedInstance($context) | ((((i & 112) ^ 48) > 32 && $composer.changed(searchSong)) || (i & 48) == 32);
                Object objRememberedValue = $composer.rememberedValue();
                if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    boolean z3 = z2;
                    final CoroutineScope coroutineScope = $scope;
                    final MutableState mutableState = $resolvingSongId$delegate;
                    final MutableState mutableState2 = $errorMessage$delegate;
                    final NeteaseSearchClient neteaseSearchClient = $client;
                    final MutableState mutableState3 = $results$delegate;
                    final Context context = $context;
                    z = z3;
                    composer = $composer;
                    objRememberedValue = new Function0<Unit>() { // from class: com.lladlam.melox.ui.search.SearchScreenKt$SearchScreen$1$3$1$2$1$1
                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            SearchScreenKt.SearchScreen$playSong(coroutineScope, mutableState, mutableState2, neteaseSearchClient, mutableState3, context, searchSong);
                        }
                    };
                    $composer.updateRememberedValue(objRememberedValue);
                } else {
                    composer = $composer;
                    z = z2;
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                SearchScreenKt.SongResultRow(searchSong, z, (Function0) objRememberedValue, composer, (i >> 3) & 14);
                long onSurface = MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getOnSurface();
                Composer composer2 = composer;
                DividerKt.m3239HorizontalDivider9IZ8Weo(null, 0.0f, Color.copy_wmQWz5c(onSurface, (14 & 1) != 0 ? Color.getAlpha_impl(onSurface) : 0.08f, (14 & 2) != 0 ? Color.getRed_impl(onSurface) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(onSurface) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(onSurface) : 0.0f), composer2, 0, 3);
                composer2.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object SearchScreen$lambda$16$2$0$0(SearchSong it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Long.valueOf(it.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SongResultRow(final SearchSong song, final boolean isResolving, final Function0<Unit> function0, Composer $composer, final int $changed) {
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function2;
        Composer composer;
        Composer $composer2 = $composer.startRestartGroup(1641348908);
        ComposerKt.sourceInformation($composer2, "C(SongResultRow)N(song,isResolving,onClick)226@8403L1079:SearchScreen.kt#p6k06t");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(song) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(isResolving) ? 32 : 16;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1641348908, $dirty2, -1, "com.lladlam.melox.ui.search.SongResultRow (SearchScreen.kt:225)");
            }
            Modifier modifierM1807paddingVpY3zN4$default = PaddingKt.m1807paddingVpY3zN4$default(ClickableKt.m1078clickableoSLSa3U$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), !isResolving, null, null, null, function0, 14, null), 0.0f, Dp.constructor_impl(13), 1, null);
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.Horizontal horizontalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(Dp.constructor_impl(12));
            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalM1497spacedBy0680j_4, centerVertically, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierM1807paddingVpY3zN4$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((432 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function1 = constructor;
                $composer2.createNode(function1);
            } else {
                function1 = constructor;
                $composer2.useNode();
            }
            Composer composerM5188constructorimpl = Updater.constructor_impl($composer2);
            Updater.set_impl(composerM5188constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i3 = ((432 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, -1742643946, "C234@8703L695:SearchScreen.kt#p6k06t");
            Modifier modifierWeight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer2, modifierWeight$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function2 = constructor2;
                $composer2.createNode(function2);
            } else {
                function2 = constructor2;
                $composer2.useNode();
            }
            Composer composerM5188constructorimpl2 = Updater.constructor_impl($composer2);
            Updater.set_impl(composerM5188constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i6 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1149777145, "C237@8834L10,235@8756L165,240@8934L29,249@9271L11,250@9352L10,241@8976L412:SearchScreen.kt#p6k06t");
            TextKt.m3912TextNvy7gAk(song.getName(), null, 0L, null, 0L, null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer2, MaterialTheme.$stable).getTitleMedium(), $composer2, 1572864, 0, 131006);
            SpacerKt.Spacer(SizeKt.m1858height3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(3)), $composer2, 6);
            StringBuilder sb = new StringBuilder();
            sb.append(song.getArtists());
            if (!StringsKt.isBlank(song.getAlbum())) {
                sb.append(" · ");
                sb.append(song.getAlbum());
            }
            String string = sb.toString();
            long onSurface = MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getOnSurface();
            TextKt.m3912TextNvy7gAk(string, null, Color.copy_wmQWz5c(onSurface, (14 & 1) != 0 ? Color.getAlpha_impl(onSurface) : 0.58f, (14 & 2) != 0 ? Color.getRed_impl(onSurface) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(onSurface) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(onSurface) : 0.0f), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer2, MaterialTheme.$stable).getBodyMedium(), $composer2, 0, 0, 131066);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (!isResolving) {
                composer = $composer2;
                composer.startReplaceGroup(-1741901094);
                composer.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-1741950105);
                ComposerKt.sourceInformation($composer2, "255@9439L27");
                ProgressIndicatorKt.m3567CircularProgressIndicator4lLiAd8(null, 0L, 0.0f, 0L, 0, 0.0f, $composer2, 0, 63);
                composer = $composer2;
                composer.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.search.SearchScreenKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SearchScreenKt.SongResultRow$lambda$1(song, isResolving, function0, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
