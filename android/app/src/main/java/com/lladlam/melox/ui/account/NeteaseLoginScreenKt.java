package com.lladlam.melox.ui.account;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.activity.compose.BackHandlerKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsetsPadding_androidKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.TextKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.C1301Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.viewinterop.AndroidView_androidKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.media3.exoplayer.RendererCapabilities;
import com.lladlam.melox.core.account.NeteaseSessionStore;
import com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: NeteaseLoginScreen.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a1\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007H\u0007¢\u0006\u0002\u0010\t\u001a\b\u0010\n\u001a\u00020\u0001H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b²\u0006\f\u0010\f\u001a\u0004\u0018\u00010\rX\u008a\u008e\u0002²\u0006\n\u0010\u000e\u001a\u00020\u000fX\u008a\u008e\u0002²\u0006\n\u0010\u0010\u001a\u00020\u000fX\u008a\u008e\u0002²\u0006\f\u0010\u0011\u001a\u0004\u0018\u00010\u0001X\u008a\u008e\u0002²\u0006\f\u0010\u0012\u001a\u0004\u0018\u00010\u0001X\u008a\u008e\u0002"}, d2 = {"NETEASE_LOGIN_URL", "", "NeteaseLoginScreen", "", "session", "Lcom/lladlam/melox/core/account/NeteaseSessionStore;", "onDismiss", "Lkotlin/Function0;", "onLoggedIn", "(Lcom/lladlam/melox/core/account/NeteaseSessionStore;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "collectNeteaseCookieHeader", "app", "webView", "Landroid/webkit/WebView;", "pageLoading", "", "verifying", "verificationError", "handledCookie"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class NeteaseLoginScreenKt {
    private static final String NETEASE_LOGIN_URL = "https://music.163.com/#";

    static final Unit NeteaseLoginScreen$lambda$19(NeteaseSessionStore neteaseSessionStore, Function0 function0, Function0 function1, int i, Composer composer, int i2) {
        NeteaseLoginScreen(neteaseSessionStore, function0, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void NeteaseLoginScreen(NeteaseSessionStore session, final Function0<Unit> onDismiss, Function0<Unit> onLoggedIn, Composer $composer, final int $changed) {
        final Function0<Unit> function0;
        Composer $composer2;
        final NeteaseSessionStore neteaseSessionStore;
        final Function0<Unit> function1;
        Object obj;
        Function0<ComposeUiNode> function2;
        Function0<ComposeUiNode> function3;
        Function0<ComposeUiNode> function4;
        Function0<ComposeUiNode> function5;
        Function0<ComposeUiNode> function6;
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(onDismiss, "onDismiss");
        Intrinsics.checkNotNullParameter(onLoggedIn, "onLoggedIn");
        Composer $composer3 = $composer.startRestartGroup(-1329621952);
        ComposerKt.sourceInformation($composer3, "C(NeteaseLoginScreen)N(session,onDismiss,onLoggedIn)50@2008L43,51@2075L33,52@2130L34,53@2194L42,54@2262L42,56@2322L104,56@2310L116,61@2456L907,61@2432L931,88@3392L103,88@3369L126,98@3602L11,95@3501L4448:NeteaseLoginScreen.kt#jpacru");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(session) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(onDismiss) ? 32 : 16;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer3.changedInstance(onLoggedIn) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (!$composer3.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            function0 = onDismiss;
            $composer2 = $composer3;
            neteaseSessionStore = session;
            function1 = onLoggedIn;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1329621952, $dirty2, -1, "com.lladlam.melox.ui.account.NeteaseLoginScreen (NeteaseLoginScreen.kt:49)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 2133562731, "CC(remember):NeteaseLoginScreen.kt#9igjgp");
            Object objRememberedValue = $composer3.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default);
                objRememberedValue = objMutableStateOf$default;
            }
            final MutableState webView$delegate = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 2133564865, "CC(remember):NeteaseLoginScreen.kt#9igjgp");
            Object objRememberedValue2 = $composer3.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default2);
                objRememberedValue2 = objMutableStateOf$default2;
            }
            final MutableState pageLoading$delegate = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 2133566626, "CC(remember):NeteaseLoginScreen.kt#9igjgp");
            Object objRememberedValue3 = $composer3.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default3);
                objRememberedValue3 = objMutableStateOf$default3;
            }
            MutableState verifying$delegate = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 2133568682, "CC(remember):NeteaseLoginScreen.kt#9igjgp");
            Object objRememberedValue4 = $composer3.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default4);
                objRememberedValue4 = objMutableStateOf$default4;
            }
            MutableState verificationError$delegate = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 2133570858, "CC(remember):NeteaseLoginScreen.kt#9igjgp");
            Object objRememberedValue5 = $composer3.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default5);
                objRememberedValue5 = objMutableStateOf$default5;
            }
            MutableState handledCookie$delegate = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 2133572840, "CC(remember):NeteaseLoginScreen.kt#9igjgp");
            boolean z = ($dirty2 & 112) == 32;
            Object objRememberedValue6 = $composer3.rememberedValue();
            if (z || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function0() { // from class: com.lladlam.melox.ui.account.NeteaseLoginScreenKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return NeteaseLoginScreenKt.NeteaseLoginScreen$lambda$15$0(onDismiss, webView$delegate);
                    }
                };
                $composer3.updateRememberedValue(obj2);
                objRememberedValue6 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            BackHandlerKt.BackHandler(false, (Function0) objRememberedValue6, $composer3, 0, 1);
            WebView webViewNeteaseLoginScreen$lambda$1 = NeteaseLoginScreen$lambda$1(webView$delegate);
            ComposerKt.sourceInformationMarkerStart($composer3, 2133577931, "CC(remember):NeteaseLoginScreen.kt#9igjgp");
            boolean z2 = (($dirty2 & 14) == 4) | (($dirty2 & 896) == 256);
            NeteaseLoginScreenKt$NeteaseLoginScreen$2$1 neteaseLoginScreenKt$NeteaseLoginScreen$2$1RememberedValue = $composer3.rememberedValue();
            if (z2 || neteaseLoginScreenKt$NeteaseLoginScreen$2$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                obj = null;
                neteaseLoginScreenKt$NeteaseLoginScreen$2$1RememberedValue = new NeteaseLoginScreenKt$NeteaseLoginScreen$2$1(session, onLoggedIn, handledCookie$delegate, verifying$delegate, verificationError$delegate, null);
                $composer3.updateRememberedValue(neteaseLoginScreenKt$NeteaseLoginScreen$2$1RememberedValue);
            } else {
                obj = null;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(webViewNeteaseLoginScreen$lambda$1, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) neteaseLoginScreenKt$NeteaseLoginScreen$2$1RememberedValue, $composer3, 0);
            Unit unit = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 2133607079, r3);
            Object objRememberedValue7 = $composer3.rememberedValue();
            if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                Object obj3 = new Function1() { // from class: com.lladlam.melox.ui.account.NeteaseLoginScreenKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        return NeteaseLoginScreenKt.NeteaseLoginScreen$lambda$17$0(webView$delegate, (DisposableEffectScope) obj4);
                    }
                };
                $composer3.updateRememberedValue(obj3);
                objRememberedValue7 = obj3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.DisposableEffect(unit, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue7, $composer3, 54);
            Modifier modifierStatusBarsPadding = WindowInsetsPadding_androidKt.statusBarsPadding(BackgroundKt.m1043backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, obj), MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getBackground(), null, 2, null));
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierStatusBarsPadding);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function2 = constructor;
                $composer3.createNode(function2);
            } else {
                function2 = constructor;
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer3);
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            int i3 = ((0 >> 6) & 112) | 6;
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -1609678206, "C101@3676L1286,141@5082L2861:NeteaseLoginScreen.kt#jpacru");
            Modifier modifierM1806paddingVpY3zN4 = PaddingKt.m1806paddingVpY3zN4(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1301Dp.m8905constructorimpl(18), C1301Dp.m8905constructorimpl(12));
            Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer3, modifierM1806paddingVpY3zN4);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((438 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function3 = constructor2;
                $composer3.createNode(function3);
            } else {
                function3 = constructor2;
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl2 = Updater.m5188constructorimpl($composer3);
            Updater.m5196setimpl(composerM5188constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i6 = ((438 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -811341788, "C112@4100L320,108@3955L644,127@4724L11,124@4612L151,129@4776L176:NeteaseLoginScreen.kt#jpacru");
            Modifier modifierClip = ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(18)));
            RoundedCornerShape roundedCornerShapeM2135RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(18));
            long jColor = ColorKt.Color(4294914375L);
            long jColor2 = ColorKt.Color(4294914375L);
            Modifier modifierM9633meloXLiquidButtonNsDo4u0 = MeloXBackdropComponentsKt.m9633meloXLiquidButtonNsDo4u0(modifierClip, roundedCornerShapeM2135RoundedCornerShape0680j_4, false, jColor, Color.m6066copywmQWz5c(jColor2, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jColor2) : 0.08f, (14 & 2) != 0 ? Color.m6074getRedimpl(jColor2) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jColor2) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jColor2) : 0.0f), 0.0f, C1301Dp.m8905constructorimpl(7), C1301Dp.m8905constructorimpl(11), $composer3, 14183424, 18);
            $composer2 = $composer3;
            neteaseSessionStore = session;
            function0 = onDismiss;
            function1 = onLoggedIn;
            TextKt.m3912TextNvy7gAk("取消", PaddingKt.m1805padding3ABfNKs(ClickableKt.m1078clickableoSLSa3U$default(modifierM9633meloXLiquidButtonNsDo4u0, false, null, null, null, function0, 15, null), C1301Dp.m8905constructorimpl(8)), ColorKt.Color(4294914375L), null, TextUnitKt.getSp(16), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 24966, 0, 262120);
            TextKt.m3912TextNvy7gAk("登录网易云音乐", null, MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnBackground(), null, TextUnitKt.getSp(17), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 24582, 0, 262122);
            TextKt.m3912TextNvy7gAk("取消", PaddingKt.m1805padding3ABfNKs(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(8)), Color.INSTANCE.m6103getTransparent0d7_KjU(), null, TextUnitKt.getSp(16), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 25014, 0, 262120);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (NeteaseLoginScreen$lambda$4(pageLoading$delegate)) {
                $composer3.startReplaceGroup(-1608505415);
                ComposerKt.sourceInformation($composer3, "138@5003L59");
                ProgressIndicatorKt.m3580LinearProgressIndicatorrIrjwxo(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0L, 0L, 0, 0.0f, $composer3, 6, 30);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(-1608425652);
                $composer3.endReplaceGroup();
            }
            Modifier modifierWeight$default = ColumnScope.weight$default(columnScope, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap3 = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer3, modifierWeight$default);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i7 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function4 = constructor3;
                $composer3.createNode(function4);
            } else {
                function4 = constructor3;
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl3 = Updater.m5188constructorimpl($composer3);
            Updater.m5196setimpl(composerM5188constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i8 = (i7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            int i9 = ((0 >> 6) & 112) | 6;
            BoxScope boxScope = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -1171949258, "C144@5222L1347,142@5132L1452:NeteaseLoginScreen.kt#jpacru");
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer3, -176350721, "CC(remember):NeteaseLoginScreen.kt#9igjgp");
            Object objRememberedValue8 = $composer3.rememberedValue();
            if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                Object obj4 = new Function1() { // from class: com.lladlam.melox.ui.account.NeteaseLoginScreenKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj5) {
                        return NeteaseLoginScreenKt.NeteaseLoginScreen$lambda$18$1$0$0(webView$delegate, pageLoading$delegate, (Context) obj5);
                    }
                };
                $composer3.updateRememberedValue(obj4);
                objRememberedValue8 = obj4;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            AndroidView_androidKt.AndroidView((Function1) objRememberedValue8, modifierFillMaxSize$default, null, $composer3, 54, 4);
            if (NeteaseLoginScreen$lambda$7(verifying$delegate)) {
                $composer3.startReplaceGroup(-1170546540);
                ComposerKt.sourceInformation($composer3, "174@6631L626");
                Modifier modifierFillMaxSize$default2 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                long jM6094getBlack0d7_KjU = Color.INSTANCE.m6094getBlack0d7_KjU();
                Modifier modifierM1043backgroundbw27NRU$default = BackgroundKt.m1043backgroundbw27NRU$default(modifierFillMaxSize$default2, Color.m6066copywmQWz5c(jM6094getBlack0d7_KjU, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6094getBlack0d7_KjU) : 0.18f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6094getBlack0d7_KjU) : 0.0f), null, 2, null);
                Alignment center = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                CompositionLocalMap currentCompositionLocalMap4 = $composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer3, modifierM1043backgroundbw27NRU$default);
                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                int i10 = ((((54 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function5 = constructor4;
                    $composer3.createNode(function5);
                } else {
                    function5 = constructor4;
                    $composer3.useNode();
                }
                Composer composerM5188constructorimpl4 = Updater.m5188constructorimpl($composer3);
                Updater.m5196setimpl(composerM5188constructorimpl4, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m5196setimpl(composerM5188constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m5196setimpl(composerM5188constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m5194reconcileimpl(composerM5188constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m5196setimpl(composerM5188constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                int i11 = (i10 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i12 = ((54 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -832236223, "C180@6882L357:NeteaseLoginScreen.kt#jpacru");
                Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier modifier = Modifier.INSTANCE;
                MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, $composer3, ((RendererCapabilities.DECODER_SUPPORT_MASK >> 3) & 14) | ((RendererCapabilities.DECODER_SUPPORT_MASK >> 3) & 112));
                int i13 = (RendererCapabilities.DECODER_SUPPORT_MASK << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                CompositionLocalMap currentCompositionLocalMap5 = $composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier($composer3, modifier);
                Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                int i14 = ((i13 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function6 = constructor5;
                    $composer3.createNode(function6);
                } else {
                    function6 = constructor5;
                    $composer3.useNode();
                }
                Composer composerM5188constructorimpl5 = Updater.m5188constructorimpl($composer3);
                Updater.m5196setimpl(composerM5188constructorimpl5, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m5196setimpl(composerM5188constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m5196setimpl(composerM5188constructorimpl5, Integer.valueOf(iHashCode5), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m5194reconcileimpl(composerM5188constructorimpl5, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m5196setimpl(composerM5188constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                int i15 = (i14 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i16 = ((RendererCapabilities.DECODER_SUPPORT_MASK >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 723690134, "C181@6967L27,182@7019L198:NeteaseLoginScreen.kt#jpacru");
                ProgressIndicatorKt.m3567CircularProgressIndicator4lLiAd8(null, 0L, 0.0f, 0L, 0, 0.0f, $composer3, 0, 63);
                TextKt.m3912TextNvy7gAk("正在验证登录状态…", PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, C1301Dp.m8905constructorimpl(12), 0.0f, 0.0f, 13, null), Color.INSTANCE.m6105getWhite0d7_KjU(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 438, 0, 262136);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(-1169914202);
                $composer3.endReplaceGroup();
            }
            String strNeteaseLoginScreen$lambda$10 = NeteaseLoginScreen$lambda$10(verificationError$delegate);
            if (strNeteaseLoginScreen$lambda$10 == null) {
                $composer3.startReplaceGroup(-1169861968);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(-1169861967);
                ComposerKt.sourceInformation($composer3, "*198@7601L11,199@7679L6,202@7834L11,192@7337L582");
                TextKt.m3912TextNvy7gAk(strNeteaseLoginScreen$lambda$10, PaddingKt.m1806paddingVpY3zN4(BackgroundKt.m1042backgroundbw27NRU(PaddingKt.m1805padding3ABfNKs(boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getBottomCenter()), C1301Dp.m8905constructorimpl(16)), MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getErrorContainer(), MaterialTheme.INSTANCE.getShapes($composer3, MaterialTheme.$stable).getMedium()), C1301Dp.m8905constructorimpl(14), C1301Dp.m8905constructorimpl(10)), MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnErrorContainer(), null, TextUnitKt.getSp(13), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 24576, 0, 262120);
                Unit unit2 = Unit.INSTANCE;
                $composer3.endReplaceGroup();
                Unit unit3 = Unit.INSTANCE;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.account.NeteaseLoginScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj5, Object obj6) {
                    return NeteaseLoginScreenKt.NeteaseLoginScreen$lambda$19(neteaseSessionStore, function0, function1, $changed, (Composer) obj5, ((Integer) obj6).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WebView NeteaseLoginScreen$lambda$1(MutableState<WebView> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean NeteaseLoginScreen$lambda$4(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void NeteaseLoginScreen$lambda$5(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean NeteaseLoginScreen$lambda$7(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void NeteaseLoginScreen$lambda$8(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String NeteaseLoginScreen$lambda$10(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String NeteaseLoginScreen$lambda$13(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit NeteaseLoginScreen$lambda$15$0(Function0 $onDismiss, MutableState $webView$delegate) {
        WebView view = NeteaseLoginScreen$lambda$1($webView$delegate);
        boolean z = false;
        if (view != null && view.canGoBack()) {
            z = true;
        }
        if (z) {
            view.goBack();
        } else {
            $onDismiss.invoke();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult NeteaseLoginScreen$lambda$17$0(final MutableState $webView$delegate, DisposableEffectScope DisposableEffect) {
        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
        return new DisposableEffectResult() { // from class: com.lladlam.melox.ui.account.NeteaseLoginScreenKt$NeteaseLoginScreen$lambda$17$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                WebView webViewNeteaseLoginScreen$lambda$1 = NeteaseLoginScreenKt.NeteaseLoginScreen$lambda$1($webView$delegate);
                if (webViewNeteaseLoginScreen$lambda$1 != null) {
                    webViewNeteaseLoginScreen$lambda$1.stopLoading();
                }
                WebView webViewNeteaseLoginScreen$lambda$2 = NeteaseLoginScreenKt.NeteaseLoginScreen$lambda$1($webView$delegate);
                if (webViewNeteaseLoginScreen$lambda$2 != null) {
                    webViewNeteaseLoginScreen$lambda$2.destroy();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WebView NeteaseLoginScreen$lambda$18$1$0$0(MutableState $webView$delegate, final MutableState $pageLoading$delegate, Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        WebView webView = new WebView(context);
        $webView$delegate.setValue(webView);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setAcceptThirdPartyCookies(webView, true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUserAgentString("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36");
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() { // from class: com.lladlam.melox.ui.account.NeteaseLoginScreenKt$NeteaseLoginScreen$4$2$1$1$1$1
            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView view, String url) {
                NeteaseLoginScreenKt.NeteaseLoginScreen$lambda$5($pageLoading$delegate, false);
                super.onPageFinished(view, url);
            }
        });
        webView.loadUrl(NETEASE_LOGIN_URL);
        return webView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String collectNeteaseCookieHeader() {
        CookieManager manager = CookieManager.getInstance();
        LinkedHashMap values = new LinkedHashMap();
        int i = 2;
        int i2 = 0;
        int i3 = 1;
        Iterable urls = CollectionsKt.listOf((Object[]) new String[]{"https://music.163.com/", "https://interface.music.163.com/"});
        Iterator it = urls.iterator();
        while (it.hasNext()) {
            String cookie = manager.getCookie((String) it.next());
            if (cookie != null) {
                char[] cArr = new char[i3];
                cArr[i2] = ';';
                Iterable iterableSplit$default = StringsKt.split$default((CharSequence) cookie, cArr, false, 0, 6, (Object) null);
                if (iterableSplit$default != null) {
                    Iterator it2 = iterableSplit$default.iterator();
                    while (it2.hasNext()) {
                        String string = StringsKt.trim((CharSequence) it2.next()).toString();
                        int i4 = i2;
                        char[] cArr2 = new char[i3];
                        cArr2[i4] = '=';
                        List listSplit$default = StringsKt.split$default((CharSequence) string, cArr2, false, 2, 2, (Object) null);
                        if (listSplit$default.size() == i && !StringsKt.isBlank((CharSequence) listSplit$default.get(i4))) {
                            values.put(StringsKt.trim((CharSequence) listSplit$default.get(i4)).toString(), StringsKt.trim((CharSequence) listSplit$default.get(1)).toString());
                        }
                        manager = manager;
                        i = 2;
                        i2 = 0;
                        i3 = 1;
                    }
                }
            }
            manager = manager;
            i = 2;
            i2 = 0;
            i3 = 1;
        }
        Set setEntrySet = MapsKt.toSortedMap(values).entrySet();
        Intrinsics.checkNotNullExpressionValue(setEntrySet, "<get-entries>(...)");
        return CollectionsKt.joinToString$default(setEntrySet, "; ", null, null, 0, null, new Function1() { // from class: com.lladlam.melox.ui.account.NeteaseLoginScreenKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NeteaseLoginScreenKt.collectNeteaseCookieHeader$lambda$1((Map.Entry) obj);
            }
        }, 30, null);
    }

    static final CharSequence collectNeteaseCookieHeader$lambda$1(Map.Entry entry) {
        Intrinsics.checkNotNullParameter(entry, "<destruct>");
        String key = (String) entry.getKey();
        String value = (String) entry.getValue();
        return key + "=" + value;
    }
}
