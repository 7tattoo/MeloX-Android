package com.lladlam.melox.p012ui.player;

import android.content.Context;
import android.os.SystemClock;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.foundation.lazy.LazyListStateKt;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.TextKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.PlatformSpanStyle;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotLongStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.media3.exoplayer.RendererCapabilities;
import com.lladlam.melox.core.account.NeteaseSessionStore;
import com.lladlam.melox.core.lyrics.LyricLine;
import com.lladlam.melox.core.lyrics.LyricSyllable;
import com.lladlam.melox.core.lyrics.LyricsDocument;
import com.lladlam.melox.core.network.NeteaseSearchClient;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.OkHttpClient;

/* JADX INFO: compiled from: MeloXLyricsPanel.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0007\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a%\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0003¢\u0006\u0002\u0010\u000e¨\u0006\u000f²\u0006\f\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u008a\u008e\u0002²\u0006\n\u0010\u0012\u001a\u00020\rX\u008a\u008e\u0002²\u0006\f\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u008a\u008e\u0002²\u0006\n\u0010\u0015\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\n\u0010\u0016\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\n\u0010\u0017\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\n\u0010\u0018\u001a\u00020\u0019X\u008a\u0084\u0002"}, d2 = {"MeloXLyricsPanel", "", "state", "Lcom/lladlam/melox/ui/player/MeloXPlaybackUiState;", "modifier", "Landroidx/compose/ui/Modifier;", "(Lcom/lladlam/melox/ui/player/MeloXPlaybackUiState;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "SynchronizedLyricText", "line", "Lcom/lladlam/melox/core/lyrics/LyricLine;", "positionMs", "", "active", "", "(Lcom/lladlam/melox/core/lyrics/LyricLine;JZLandroidx/compose/runtime/Composer;I)V", "app", "lyrics", "Lcom/lladlam/melox/core/lyrics/LyricsDocument;", "isLoading", "errorMessage", "", "anchorPositionMs", "anchorRealtimeMs", "renderedPositionMs", "emphasisScale", ""}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class MeloXLyricsPanelKt {
    static final Unit MeloXLyricsPanel$lambda$24(MeloXPlaybackUiState meloXPlaybackUiState, Modifier modifier, int i, int i2, Composer composer, int i3) {
        MeloXLyricsPanel(meloXPlaybackUiState, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit SynchronizedLyricText$lambda$4(LyricLine lyricLine, long j, boolean z, int i, Composer composer, int i2) {
        SynchronizedLyricText(lyricLine, j, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v17 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v8, types: [int] */
    /* JADX WARN: Type inference failed for: r12v0, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r14v16 */
    /* JADX WARN: Type inference failed for: r14v17 */
    public static final void MeloXLyricsPanel(MeloXPlaybackUiState meloXPlaybackUiState, Modifier modifier, Composer composer, final int i, final int i2) {
        final Modifier modifier2;
        Modifier modifier3;
        boolean z;
        int r11;
        String str;
        MeloXLyricsPanelKt$MeloXLyricsPanel$2$1 meloXLyricsPanelKt$MeloXLyricsPanel$2$1;
        MutableState mutableState;
        MutableState mutableState2;
        Function0<ComposeUiNode> function0;
        Arrangement.HorizontalOrVertical horizontalOrVertical;
        final MeloXPlaybackUiState state = meloXPlaybackUiState;
        Intrinsics.checkNotNullParameter(state, "state");
        int StartRestartGroup = composer.startRestartGroup(-919664552);
        ComposerKt.sourceInformation(StartRestartGroup, "C(MeloXLyricsPanel)N(state,modifier)48@2061L7,49@2105L138,54@2264L23,56@2338L59,57@2419L43,58@2487L51,60@2568L58,61@2655L71,62@2757L58,64@2880L154,64@2821L213,70@3081L304,70@3040L345,81@3415L306,81@3391L330,94@3870L181,94@3828L223,100@4057L3705:MeloXLyricsPanel.kt#qhu5z0");
        int i3 = i;
        if ((i & 6) == 0) {
            i3 |= StartRestartGroup.changed(state) ? 4 : 2;
        }
        int i4 = i2 & 2;
        if (i4 != 0) {
            i3 |= 48;
            modifier2 = modifier;
        } else if ((i & 48) == 0) {
            modifier2 = modifier;
            i3 |= StartRestartGroup.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i5 = i3;
        int i6 = 1;
        if (StartRestartGroup.shouldExecute((i5 & 19) != 18, i5 & 1)) {
            if (i4 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-919664552, i5, -1, "com.lladlam.melox.ui.player.MeloXLyricsPanel (MeloXLyricsPanel.kt:47)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = StartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
            final Context applicationContext = ((Context) objConsume).getApplicationContext();
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, 965634050, "CC(remember):MeloXLyricsPanel.kt#9igjgp");
            boolean zChanged = StartRestartGroup.changed(applicationContext);
            Object objRememberedValue = StartRestartGroup.rememberedValue();
            OkHttpClient okHttpClient = null;
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                NeteaseSearchClient neteaseSearchClient = new NeteaseSearchClient(okHttpClient, new Function0() { // from class: com.lladlam.melox.ui.player.MeloXLyricsPanelKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MeloXLyricsPanelKt.MeloXLyricsPanel$lambda$0$0(applicationContext);
                    }
                }, i6, 0 == true ? 1 : 0);
                StartRestartGroup.updateRememberedValue(neteaseSearchClient);
                objRememberedValue = neteaseSearchClient;
            }
            NeteaseSearchClient neteaseSearchClient2 = (NeteaseSearchClient) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
            LazyListState lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, StartRestartGroup, 0, 3);
            String mediaId = state.getMediaId();
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, 965641427, "CC(remember):MeloXLyricsPanel.kt#9igjgp");
            boolean zChanged2 = StartRestartGroup.changed(mediaId);
            Object objRememberedValue2 = StartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                StartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default);
                objRememberedValue2 = mutableStateMutableStateOf$default;
            }
            MutableState mutableState3 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, 965644003, "CC(remember):MeloXLyricsPanel.kt#9igjgp");
            boolean zChanged3 = StartRestartGroup.changed(mediaId);
            Object objRememberedValue3 = StartRestartGroup.rememberedValue();
            if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                z = false;
                MutableState mutableStateMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                StartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default2);
                objRememberedValue3 = mutableStateMutableStateOf$default2;
            } else {
                z = false;
            }
            MutableState mutableState4 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, 965646187, "CC(remember):MeloXLyricsPanel.kt#9igjgp");
            boolean zChanged4 = StartRestartGroup.changed(mediaId);
            Object objRememberedValue4 = StartRestartGroup.rememberedValue();
            if (zChanged4 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                StartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default3);
                objRememberedValue4 = mutableStateMutableStateOf$default3;
            }
            MutableState mutableState5 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, 965648786, "CC(remember):MeloXLyricsPanel.kt#9igjgp");
            boolean zChanged5 = StartRestartGroup.changed(mediaId);
            Object objRememberedValue5 = StartRestartGroup.rememberedValue();
            if (zChanged5 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                MutableLongState mutableLongStateMutableLongStateOf = SnapshotLongStateKt.mutableLongStateOf(state.getPositionMs());
                StartRestartGroup.updateRememberedValue(mutableLongStateMutableLongStateOf);
                objRememberedValue5 = mutableLongStateMutableLongStateOf;
            }
            MutableLongState mutableLongState = (MutableLongState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, 965651583, "CC(remember):MeloXLyricsPanel.kt#9igjgp");
            boolean zChanged6 = StartRestartGroup.changed(mediaId);
            Object objRememberedValue6 = StartRestartGroup.rememberedValue();
            if (zChanged6 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                MutableLongState mutableLongStateMutableLongStateOf2 = SnapshotLongStateKt.mutableLongStateOf(SystemClock.elapsedRealtime());
                StartRestartGroup.updateRememberedValue(mutableLongStateMutableLongStateOf2);
                objRememberedValue6 = mutableLongStateMutableLongStateOf2;
            }
            MutableLongState mutableLongState2 = (MutableLongState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, 965654834, "CC(remember):MeloXLyricsPanel.kt#9igjgp");
            boolean zChanged7 = StartRestartGroup.changed(mediaId);
            Object objRememberedValue7 = StartRestartGroup.rememberedValue();
            if (zChanged7 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                MutableLongState mutableLongStateMutableLongStateOf3 = SnapshotLongStateKt.mutableLongStateOf(state.getPositionMs());
                StartRestartGroup.updateRememberedValue(mutableLongStateMutableLongStateOf3);
                objRememberedValue7 = mutableLongStateMutableLongStateOf3;
            }
            final MutableLongState mutableLongState3 = (MutableLongState) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
            Long lValueOf = Long.valueOf(state.getPositionMs());
            Boolean boolValueOf = Boolean.valueOf(state.isPlaying());
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, 965658866, "CC(remember):MeloXLyricsPanel.kt#9igjgp");
            boolean zChanged8 = StartRestartGroup.changed(mutableLongState) | ((i5 & 14) == 4 ? true : z) | StartRestartGroup.changed(mutableLongState2) | StartRestartGroup.changed(mutableLongState3);
            MeloXLyricsPanelKt$MeloXLyricsPanel$1$1 meloXLyricsPanelKt$MeloXLyricsPanel$1$1RememberedValue = StartRestartGroup.rememberedValue();
            if (zChanged8 || meloXLyricsPanelKt$MeloXLyricsPanel$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                meloXLyricsPanelKt$MeloXLyricsPanel$1$1RememberedValue = new MeloXLyricsPanelKt$MeloXLyricsPanel$1$1(state, mutableLongState, mutableLongState2, mutableLongState3, null);
                StartRestartGroup.updateRememberedValue(meloXLyricsPanelKt$MeloXLyricsPanel$1$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
            EffectsKt.LaunchedEffect(lValueOf, boolValueOf, mediaId, (Function2) meloXLyricsPanelKt$MeloXLyricsPanel$1$1RememberedValue, StartRestartGroup, 0);
            Boolean boolValueOf2 = Boolean.valueOf(meloXPlaybackUiState.isPlaying());
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, 965665448, "CC(remember):MeloXLyricsPanel.kt#9igjgp");
            boolean zChanged9 = StartRestartGroup.changed(mutableLongState3) | ((i5 & 14) == 4 ? true : z) | StartRestartGroup.changed(mutableLongState) | StartRestartGroup.changed(mutableLongState2);
            Object objRememberedValue8 = StartRestartGroup.rememberedValue();
            if (zChanged9 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                r11 = z;
                str = "CC(remember):MeloXLyricsPanel.kt#9igjgp";
                state = meloXPlaybackUiState;
                meloXLyricsPanelKt$MeloXLyricsPanel$2$1 = new MeloXLyricsPanelKt$MeloXLyricsPanel$2$1(state, mutableLongState, mutableLongState2, mutableLongState3, null);
                StartRestartGroup.updateRememberedValue(meloXLyricsPanelKt$MeloXLyricsPanel$2$1);
            } else {
                r11 = z;
                str = "CC(remember):MeloXLyricsPanel.kt#9igjgp";
                meloXLyricsPanelKt$MeloXLyricsPanel$2$1 = objRememberedValue8;
                state = meloXPlaybackUiState;
            }
            ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
            EffectsKt.LaunchedEffect(boolValueOf2, mediaId, (Function2) meloXLyricsPanelKt$MeloXLyricsPanel$2$1, StartRestartGroup, r11);
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, 965676138, str);
            boolean zChanged10 = StartRestartGroup.changed(mediaId) | StartRestartGroup.changed(mutableState4) | StartRestartGroup.changed(mutableState5) | StartRestartGroup.changedInstance(neteaseSearchClient2) | StartRestartGroup.changed(mutableState3);
            Object objRememberedValue9 = StartRestartGroup.rememberedValue();
            if (zChanged10 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                mutableState = mutableState5;
                mutableState2 = mutableState4;
                MeloXLyricsPanelKt$MeloXLyricsPanel$3$1 meloXLyricsPanelKt$MeloXLyricsPanel$3$1 = new MeloXLyricsPanelKt$MeloXLyricsPanel$3$1(mediaId, mutableState2, mutableState, neteaseSearchClient2, mutableState3, null);
                StartRestartGroup.updateRememberedValue(meloXLyricsPanelKt$MeloXLyricsPanel$3$1);
                objRememberedValue9 = meloXLyricsPanelKt$MeloXLyricsPanel$3$1;
            } else {
                mutableState = mutableState5;
                mutableState2 = mutableState4;
            }
            ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
            EffectsKt.LaunchedEffect(mediaId, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue9, (Composer) StartRestartGroup, 0);
            final LyricsDocument lyricsDocumentMeloXLyricsPanel$lambda$2 = MeloXLyricsPanel$lambda$2(mutableState3);
            final Integer numHighlightedIndex = lyricsDocumentMeloXLyricsPanel$lambda$2 != null ? lyricsDocumentMeloXLyricsPanel$lambda$2.highlightedIndex(MeloXLyricsPanel$lambda$17(mutableLongState3)) : null;
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, 965690573, str);
            boolean zChanged11 = StartRestartGroup.changed(numHighlightedIndex) | StartRestartGroup.changed(lazyListStateRememberLazyListState);
            Object objRememberedValue10 = StartRestartGroup.rememberedValue();
            if (zChanged11 || objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                MeloXLyricsPanelKt$MeloXLyricsPanel$4$1 meloXLyricsPanelKt$MeloXLyricsPanel$4$1 = new MeloXLyricsPanelKt$MeloXLyricsPanel$4$1(numHighlightedIndex, lazyListStateRememberLazyListState, null);
                StartRestartGroup.updateRememberedValue(meloXLyricsPanelKt$MeloXLyricsPanel$4$1);
                objRememberedValue10 = meloXLyricsPanelKt$MeloXLyricsPanel$4$1;
            }
            ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
            EffectsKt.LaunchedEffect(numHighlightedIndex, mediaId, (Function2) objRememberedValue10, StartRestartGroup, 0);
            int i7 = (i5 >> 3) & 14;
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            int i8 = (i7 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(StartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = StartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(StartRestartGroup, modifier3);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i9 = ((i8 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!(StartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            StartRestartGroup.startReusableNode();
            if (StartRestartGroup.getInserting()) {
                function0 = constructor;
                StartRestartGroup.createNode(function0);
            } else {
                function0 = constructor;
                StartRestartGroup.useNode();
            }
            Composer composerM5188constructorimpl = Updater.constructor_impl(StartRestartGroup);
            Updater.set_impl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i10 = (i9 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            int i11 = ((i7 >> 6) & 112) | 6;
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, 1580235937, "C:MeloXLyricsPanel.kt#qhu5z0");
            if (MeloXLyricsPanel$lambda$5(mutableState2) && lyricsDocumentMeloXLyricsPanel$lambda$2 == null) {
                StartRestartGroup.startReplaceGroup(1580179578);
                ComposerKt.sourceInformation(StartRestartGroup, "103@4162L150");
                ProgressIndicatorKt.m3567CircularProgressIndicator4lLiAd8(boxScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenter()), Color.INSTANCE.m6105getWhite0d7_KjU(), 0.0f, 0L, 0, 0.0f, StartRestartGroup, 48, 60);
                StartRestartGroup.endReplaceGroup();
            } else if (MeloXLyricsPanel$lambda$8(mutableState) != null && lyricsDocumentMeloXLyricsPanel$lambda$2 == null) {
                StartRestartGroup.startReplaceGroup(1580422401);
                ComposerKt.sourceInformation(StartRestartGroup, "110@4402L303");
                String strMeloXLyricsPanel$lambda$8 = MeloXLyricsPanel$lambda$8(mutableState);
                if (strMeloXLyricsPanel$lambda$8 == null) {
                    strMeloXLyricsPanel$lambda$8 = "";
                }
                Modifier modifierM1805padding3ABfNKs = PaddingKt.m1805padding3ABfNKs(boxScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenter()), Dp.constructor_impl(24));
                long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
                TextKt.m3912TextNvy7gAk(strMeloXLyricsPanel$lambda$8, modifierM1805padding3ABfNKs, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU) : 0.55f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU) : 0.0f), null, TextUnitKt.getSp(15), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, StartRestartGroup, 24960, 0, 262120);
                StartRestartGroup.endReplaceGroup();
            } else if (lyricsDocumentMeloXLyricsPanel$lambda$2 == null || lyricsDocumentMeloXLyricsPanel$lambda$2.getLines().isEmpty()) {
                StartRestartGroup.startReplaceGroup(1580815388);
                ComposerKt.sourceInformation(StartRestartGroup, "121@4799L276");
                Modifier modifierAlign = boxScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenter());
                long jM6105getWhite0d7_KjU2 = Color.INSTANCE.m6105getWhite0d7_KjU();
                TextKt.m3912TextNvy7gAk("暂无歌词", modifierAlign, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU2, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU2) : 0.45f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU2) : 0.0f), null, TextUnitKt.getSp(18), null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, StartRestartGroup, 1597830, 0, 262056);
                StartRestartGroup.endReplaceGroup();
            } else {
                StartRestartGroup.startReplaceGroup(1581214885);
                ComposerKt.sourceInformation(StartRestartGroup, "136@5405L2327,131@5129L2603");
                Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                PaddingValues paddingValuesM1802PaddingValuesa9UjIt4$default = PaddingKt.m1802PaddingValuesa9UjIt4$default(0.0f, Dp.constructor_impl(62), 0.0f, Dp.constructor_impl(86), 5, null);
                Arrangement.HorizontalOrVertical horizontalOrVerticalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(Dp.constructor_impl(22));
                ComposerKt.sourceInformationMarkerStart(StartRestartGroup, 1436489353, str);
                boolean zChangedInstance = StartRestartGroup.changedInstance(lyricsDocumentMeloXLyricsPanel$lambda$2) | StartRestartGroup.changed(numHighlightedIndex) | ((i5 & 14) == 4) | StartRestartGroup.changed(mutableLongState3);
                Object objRememberedValue11 = StartRestartGroup.rememberedValue();
                if (!zChangedInstance) {
                    horizontalOrVertical = horizontalOrVerticalM1497spacedBy0680j_4;
                    if (objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
                    LazyDslKt.LazyColumn(modifierFillMaxSize$default, lazyListStateRememberLazyListState, paddingValuesM1802PaddingValuesa9UjIt4$default, false, horizontalOrVertical, null, null, false, null, (Function1) objRememberedValue11, StartRestartGroup, 24966, 488);
                    StartRestartGroup.endReplaceGroup();
                } else {
                    horizontalOrVertical = horizontalOrVerticalM1497spacedBy0680j_4;
                }
                Function1 function1 = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXLyricsPanelKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MeloXLyricsPanelKt.MeloXLyricsPanel$lambda$23$0$0(lyricsDocumentMeloXLyricsPanel$lambda$2, numHighlightedIndex, state, mutableLongState3, (LazyListScope) obj);
                    }
                };
                StartRestartGroup.updateRememberedValue(function1);
                objRememberedValue11 = function1;
                ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
                LazyDslKt.LazyColumn(modifierFillMaxSize$default, lazyListStateRememberLazyListState, paddingValuesM1802PaddingValuesa9UjIt4$default, false, horizontalOrVertical, null, null, false, null, (Function1) objRememberedValue11, StartRestartGroup, 24966, 488);
                StartRestartGroup.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
            StartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
        } else {
            StartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = StartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXLyricsPanelKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXLyricsPanelKt.MeloXLyricsPanel$lambda$24(state, modifier2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String MeloXLyricsPanel$lambda$0$0(Context $context) {
        NeteaseSessionStore.Companion companion = NeteaseSessionStore.INSTANCE;
        Intrinsics.checkNotNull($context);
        return companion.readCookie($context);
    }

    private static final LyricsDocument MeloXLyricsPanel$lambda$2(MutableState<LyricsDocument> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean MeloXLyricsPanel$lambda$5(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void MeloXLyricsPanel$lambda$6(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String MeloXLyricsPanel$lambda$8(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long MeloXLyricsPanel$lambda$11(MutableLongState $anchorPositionMs$delegate) {
        return $anchorPositionMs$delegate.getLongValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long MeloXLyricsPanel$lambda$14(MutableLongState $anchorRealtimeMs$delegate) {
        return $anchorRealtimeMs$delegate.getLongValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long MeloXLyricsPanel$lambda$17(MutableLongState $renderedPositionMs$delegate) {
        return $renderedPositionMs$delegate.getLongValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXLyricsPanel$lambda$23$0$0(LyricsDocument $document, final Integer $highlightedIndex, final MeloXPlaybackUiState $state, final MutableLongState $renderedPositionMs$delegate, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        final List<LyricLine> lines = $document.getLines();
        final Function2 function2 = new Function2() { // from class: com.lladlam.melox.ui.player.MeloXLyricsPanelKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return MeloXLyricsPanelKt.MeloXLyricsPanel$lambda$23$0$0$0(((Integer) obj).intValue(), (LyricLine) obj2);
            }
        };
        LazyColumn.items(lines.size(), new Function1<Integer, Object>() { // from class: com.lladlam.melox.ui.player.MeloXLyricsPanelKt$MeloXLyricsPanel$lambda$23$0$0$$inlined$itemsIndexed$default$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int index) {
                return function2.invoke(Integer.valueOf(index), lines.get(index));
            }
        }, new Function1<Integer, Object>() { // from class: com.lladlam.melox.ui.player.MeloXLyricsPanelKt$MeloXLyricsPanel$lambda$23$0$0$$inlined$itemsIndexed$default$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int index) {
                lines.get(index);
                return null;
            }
        }, ComposableLambdaKt.composableLambdaInstance(2039820996, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.lladlam.melox.ui.player.MeloXLyricsPanelKt$MeloXLyricsPanel$lambda$23$0$0$$inlined$itemsIndexed$default$3
            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                return Unit.INSTANCE;
            }

            /* JADX WARN: Code duplicated, block: B:43:0x0172  */
            /* JADX WARN: Code duplicated, block: B:46:0x017e  */
            /* JADX WARN: Code duplicated, block: B:47:0x0184  */
            /* JADX WARN: Code duplicated, block: B:53:0x022b  */
            /* JADX WARN: Code duplicated, block: B:55:0x022f  */
            /* JADX WARN: Code duplicated, block: B:56:0x023a  */
            /* JADX WARN: Code duplicated, block: B:58:0x0275  */
            /* JADX WARN: Code duplicated, block: B:59:0x0279  */
            /* JADX WARN: Code duplicated, block: B:66:0x02d1  */
            /* JADX WARN: Code duplicated, block: B:68:0x02d5  */
            /* JADX WARN: Code duplicated, block: B:69:0x02e0  */
            /* JADX WARN: Code duplicated, block: B:71:0x031d  */
            /* JADX WARN: Code duplicated, block: B:72:0x0321  */
            /* JADX WARN: Code duplicated, block: B:76:0x0384  */
            /* JADX WARN: Code duplicated, block: B:79:? A[RETURN, SYNTHETIC] */
            public final void invoke(LazyItemScope $this$items, int it, Composer $composer, int $changed) {
                int i;
                Function0<ComposeUiNode> constructor;
                Function0<ComposeUiNode> function0;
                String translation;
                String str;
                float f;
                String romanization;
                String str2;
                float f2;
                ComposerKt.sourceInformation($composer, "CN(it)214@10668L26:LazyDsl.kt#428nma");
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
                    ComposerKt.traceEventStart(2039820996, $dirty, -1, "androidx.compose.foundation.lazy.itemsIndexed.<anonymous> (LazyDsl.kt:214)");
                }
                int i2 = ($dirty & 14) | ($dirty & 112);
                final LyricLine lyricLine = (LyricLine) lines.get(it);
                $composer.startReplaceGroup(-1995151722);
                ComposerKt.sourceInformation($composer, "CN(index,line)*145@5836L29,142@5689L2003:MeloXLyricsPanel.kt#qhu5z0");
                Integer num = $highlightedIndex;
                boolean z = num != null && it == num.intValue();
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, -2142565032, "CC(remember):MeloXLyricsPanel.kt#9igjgp");
                boolean zChanged = $composer.changed($state) | $composer.changedInstance(lyricLine);
                Object objRememberedValue = $composer.rememberedValue();
                if (!zChanged) {
                    i = 0;
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Modifier modifierM1806paddingVpY3zN4 = PaddingKt.m1806paddingVpY3zN4(ClickableKt.m1078clickableoSLSa3U$default(modifierFillMaxWidth$default, false, null, null, null, (Function0) objRememberedValue, 15, null), Dp.constructor_impl(2), Dp.constructor_impl(2));
                    int i3 = i;
                    ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((i3 >> 3) & 14) | ((i3 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, i));
                    CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM1806paddingVpY3zN4);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int i4 = ((((i3 << 3) & 112) << 6) & 896) | 6;
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
                    Updater.set_impl(composerM5188constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                    int i5 = (i4 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                    int i6 = ((i3 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 833894352, "C148@6000L211:MeloXLyricsPanel.kt#qhu5z0");
                    MeloXLyricsPanelKt.SynchronizedLyricText(lyricLine, MeloXLyricsPanelKt.MeloXLyricsPanel$lambda$17($renderedPositionMs$delegate), z, $composer, (i2 >> 6) & 14);
                    translation = lyricLine.getTranslation();
                    if (translation != null || StringsKt.isBlank(translation)) {
                        str = null;
                    } else {
                        str = translation;
                    }
                    if (str == null) {
                        $composer.startReplaceGroup(834210984);
                        $composer.endReplaceGroup();
                    } else {
                        $composer.startReplaceGroup(834210985);
                        ComposerKt.sourceInformation($composer, "*157@6410L493");
                        Modifier modifierM1809paddingqDBjuR0$default = PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.constructor_impl(6), 0.0f, 0.0f, 13, null);
                        long sp = TextUnitKt.getSp(14);
                        long sp2 = TextUnitKt.getSp(20);
                        long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
                        if (z) {
                            f = 0.72f;
                        } else {
                            f = 0.28f;
                        }
                        TextKt.m3912TextNvy7gAk(str, modifierM1809paddingqDBjuR0$default, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU) : f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU) : 0.0f), null, sp, null, null, null, 0L, null, null, sp2, 0, false, 0, 0, null, null, $composer, 24624, 48, 260072);
                        $composer.endReplaceGroup();
                    }
                    romanization = lyricLine.getRomanization();
                    if (romanization != null || StringsKt.isBlank(romanization)) {
                        str2 = null;
                    } else {
                        str2 = romanization;
                    }
                    if (str2 == null) {
                        $composer.startReplaceGroup(834932230);
                        $composer.endReplaceGroup();
                    } else {
                        $composer.startReplaceGroup(834932231);
                        ComposerKt.sourceInformation($composer, "*171@7138L494");
                        Modifier modifierM1809paddingqDBjuR0$default2 = PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.constructor_impl(3), 0.0f, 0.0f, 13, null);
                        long sp3 = TextUnitKt.getSp(12);
                        long sp4 = TextUnitKt.getSp(17);
                        long jM6105getWhite0d7_KjU2 = Color.INSTANCE.m6105getWhite0d7_KjU();
                        if (z) {
                            f2 = 0.52f;
                        } else {
                            f2 = 0.22f;
                        }
                        TextKt.m3912TextNvy7gAk(str2, modifierM1809paddingqDBjuR0$default2, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU2, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU2) : f2, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU2) : 0.0f), null, sp3, null, null, null, 0L, null, null, sp4, 0, false, 0, 0, null, null, $composer, 24624, 48, 260072);
                        $composer.endReplaceGroup();
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endReplaceGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                i = 0;
                final MeloXPlaybackUiState meloXPlaybackUiState = $state;
                Object obj = (Function0) new Function0<Unit>() { // from class: com.lladlam.melox.ui.player.MeloXLyricsPanelKt$MeloXLyricsPanel$5$1$1$2$1$1
                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        meloXPlaybackUiState.seekTo(lyricLine.getTimeMs());
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
                ComposerKt.sourceInformationMarkerEnd($composer);
                Modifier modifierM1806paddingVpY3zN5 = PaddingKt.m1806paddingVpY3zN4(ClickableKt.m1078clickableoSLSa3U$default(modifierFillMaxWidth$default, false, null, null, null, (Function0) objRememberedValue, 15, null), Dp.constructor_impl(2), Dp.constructor_impl(2));
                int i7 = i;
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((i7 >> 3) & 14) | ((i7 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, i));
                CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierM1806paddingVpY3zN5);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i8 = ((((i7 << 3) & 112) << 6) & 896) | 6;
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
                Composer composerM5188constructorimpl2 = Updater.constructor_impl($composer);
                Updater.set_impl(composerM5188constructorimpl2, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.set_impl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.set_impl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.reconcile_impl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.set_impl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i9 = (i8 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                int i10 = ((i7 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 833894352, "C148@6000L211:MeloXLyricsPanel.kt#qhu5z0");
                MeloXLyricsPanelKt.SynchronizedLyricText(lyricLine, MeloXLyricsPanelKt.MeloXLyricsPanel$lambda$17($renderedPositionMs$delegate), z, $composer, (i2 >> 6) & 14);
                translation = lyricLine.getTranslation();
                if (translation != null) {
                    str = null;
                } else {
                    str = null;
                }
                if (str == null) {
                    $composer.startReplaceGroup(834210984);
                    $composer.endReplaceGroup();
                } else {
                    $composer.startReplaceGroup(834210985);
                    ComposerKt.sourceInformation($composer, "*157@6410L493");
                    Modifier modifierM1809paddingqDBjuR0$default3 = PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.constructor_impl(6), 0.0f, 0.0f, 13, null);
                    long sp5 = TextUnitKt.getSp(14);
                    long sp6 = TextUnitKt.getSp(20);
                    long jM6105getWhite0d7_KjU3 = Color.INSTANCE.m6105getWhite0d7_KjU();
                    if (z) {
                        f = 0.72f;
                    } else {
                        f = 0.28f;
                    }
                    TextKt.m3912TextNvy7gAk(str, modifierM1809paddingqDBjuR0$default3, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU3, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU3) : f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU3) : 0.0f), null, sp5, null, null, null, 0L, null, null, sp6, 0, false, 0, 0, null, null, $composer, 24624, 48, 260072);
                    $composer.endReplaceGroup();
                }
                romanization = lyricLine.getRomanization();
                if (romanization != null) {
                    str2 = null;
                } else {
                    str2 = null;
                }
                if (str2 == null) {
                    $composer.startReplaceGroup(834932230);
                    $composer.endReplaceGroup();
                } else {
                    $composer.startReplaceGroup(834932231);
                    ComposerKt.sourceInformation($composer, "*171@7138L494");
                    Modifier modifierM1809paddingqDBjuR0$default4 = PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.constructor_impl(3), 0.0f, 0.0f, 13, null);
                    long sp7 = TextUnitKt.getSp(12);
                    long sp8 = TextUnitKt.getSp(17);
                    long jM6105getWhite0d7_KjU4 = Color.INSTANCE.m6105getWhite0d7_KjU();
                    if (z) {
                        f2 = 0.52f;
                    } else {
                        f2 = 0.22f;
                    }
                    TextKt.m3912TextNvy7gAk(str2, modifierM1809paddingqDBjuR0$default4, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU4, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU4) : f2, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU4) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU4) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU4) : 0.0f), null, sp7, null, null, null, 0L, null, null, sp8, 0, false, 0, 0, null, null, $composer, 24624, 48, 260072);
                    $composer.endReplaceGroup();
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object MeloXLyricsPanel$lambda$23$0$0$0(int index, LyricLine line) {
        Intrinsics.checkNotNullParameter(line, "line");
        return line.getTimeMs() + "-" + index;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SynchronizedLyricText(final LyricLine line, final long positionMs, final boolean active, Composer $composer, final int $changed) {
        LyricLine lyricLine;
        AnnotatedString annotated;
        float fCoerceIn;
        long j = positionMs;
        Composer $composer2 = $composer.startRestartGroup(-669584304);
        ComposerKt.sourceInformation($composer2, "C(SynchronizedLyricText)N(line,positionMs,active)195@7906L164,240@9554L81,238@9480L326:MeloXLyricsPanel.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            lyricLine = line;
            $dirty |= $composer2.changedInstance(lyricLine) ? 4 : 2;
        } else {
            lyricLine = line;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(j) ? 32 : 16;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer2.changed(active) ? 256 : 128;
        }
        if ($composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-669584304, $dirty, -1, "com.lladlam.melox.ui.player.SynchronizedLyricText (MeloXLyricsPanel.kt:194)");
            }
            final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(active ? 1.0f : 0.96f, AnimationSpecKt.tween$default(180, 0, null, 6, null), 0.0f, "lyric-line-scale", null, $composer2, 3120, 20);
            if (!active || lyricLine.getSyllables().isEmpty()) {
                AnnotatedString.Builder builder = new AnnotatedString.Builder(0, 1, null);
                long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
                long jM6066copywmQWz5c = Color.copy_wmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU) : active ? 1.0f : 0.36f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU) : 0.0f);
                FontWeight.Companion companion = FontWeight.INSTANCE;
                int iPushStyle = builder.pushStyle(new SpanStyle(jM6066copywmQWz5c, 0L, active ? companion.getBold() : companion.getSemiBold(), (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 65530, (DefaultConstructorMarker) null));
                try {
                    builder.append(line.getText());
                    Unit unit = Unit.INSTANCE;
                    builder.pop(iPushStyle);
                    annotated = builder.toAnnotatedString();
                } catch (Throwable th) {
                    builder.pop(iPushStyle);
                    throw th;
                }
            } else {
                AnnotatedString.Builder builder2 = new AnnotatedString.Builder(0, 1, null);
                AnnotatedString.Builder builder3 = builder2;
                for (Iterator<LyricSyllable> it = lyricLine.getSyllables().iterator(); it.hasNext(); it = it) {
                    LyricSyllable next = it.next();
                    if (j < next.getStartTimeMs()) {
                        fCoerceIn = 0.0f;
                    } else if (j >= next.getEndTimeMs()) {
                        fCoerceIn = 1.0f;
                    } else {
                        fCoerceIn = RangesKt.coerceIn((j - next.getStartTimeMs()) / RangesKt.coerceAtLeast(next.getEndTimeMs() - next.getStartTimeMs(), 1L), 0.0f, 1.0f);
                    }
                    long jM6105getWhite0d7_KjU2 = Color.INSTANCE.m6105getWhite0d7_KjU();
                    AnnotatedString.Builder builder4 = builder3;
                    int iPushStyle2 = builder4.pushStyle(new SpanStyle(Color.copy_wmQWz5c(jM6105getWhite0d7_KjU2, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU2) : (0.7f * fCoerceIn) + 0.3f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU2) : 0.0f), 0L, fCoerceIn > 0.0f ? FontWeight.INSTANCE.getBold() : FontWeight.INSTANCE.getSemiBold(), (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 65530, (DefaultConstructorMarker) null));
                    try {
                        builder4.append(next.getText());
                        Unit unit2 = Unit.INSTANCE;
                        builder4.pop(iPushStyle2);
                        j = positionMs;
                        builder3 = builder3;
                    } catch (Throwable th2) {
                        builder4.pop(iPushStyle2);
                        throw th2;
                    }
                }
                annotated = builder2.toAnnotatedString();
            }
            Modifier.Companion companion2 = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 774634913, "CC(remember):MeloXLyricsPanel.kt#9igjgp");
            boolean zChanged = $composer2.changed(stateAnimateFloatAsState);
            Object objRememberedValue = $composer2.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXLyricsPanelKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return MeloXLyricsPanelKt.SynchronizedLyricText$lambda$3$0(stateAnimateFloatAsState, (GraphicsLayerScope) obj2);
                    }
                };
                $composer2.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            TextKt.m3913TextZ58ophY(annotated, GraphicsLayerModifierKt.graphicsLayer(companion2, (Function1) objRememberedValue), 0L, null, TextUnitKt.getSp(active ? 27 : 22), null, null, null, 0L, null, null, TextUnitKt.getSp(active ? 35 : 30), TextOverflow.INSTANCE.m8816getEllipsisgIe3tQ8(), false, 4, 0, null, null, null, $composer2, 0, 24960, 501740);
            $composer2 = $composer2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXLyricsPanelKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return MeloXLyricsPanelKt.SynchronizedLyricText$lambda$4(line, positionMs, active, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    private static final float SynchronizedLyricText$lambda$0(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SynchronizedLyricText$lambda$3$0(State $emphasisScale$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setScaleX(SynchronizedLyricText$lambda$0($emphasisScale$delegate));
        graphicsLayer.setScaleY(SynchronizedLyricText$lambda$0($emphasisScale$delegate));
        return Unit.INSTANCE;
    }
}
