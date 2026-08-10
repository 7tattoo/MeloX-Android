package com.lladlam.melox.p012ui.player;

import android.content.Context;
import android.os.SystemClock;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
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
import androidx.compose.ui.graphics.TransformOriginKt;
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
import androidx.compose.ui.text.style.TextAlign;
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
import androidx.core.app.NotificationCompat;
import androidx.media3.exoplayer.RendererCapabilities;
import com.lladlam.melox.core.account.NeteaseSessionStore;
import com.lladlam.melox.core.lyrics.LyricLine;
import com.lladlam.melox.core.lyrics.LyricSyllable;
import com.lladlam.melox.core.lyrics.LyricsDocument;
import com.lladlam.melox.core.network.NeteaseSearchClient;
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

/* JADX INFO: compiled from: MeloXIOSLyricsPanel.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000P\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a\u001f\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\u0010\n\u001a3\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012H\u0003¢\u0006\u0002\u0010\u0013\u001a-\u0010\u0014\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0016H\u0003¢\u0006\u0002\u0010\u0017\u001a \u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u0016H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001c²\u0006\f\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u008a\u008e\u0002²\u0006\n\u0010\u001f\u001a\u00020\u0010X\u008a\u008e\u0002²\u0006\f\u0010 \u001a\u0004\u0018\u00010!X\u008a\u008e\u0002²\u0006\n\u0010\"\u001a\u00020\u0001X\u008a\u008e\u0002²\u0006\n\u0010#\u001a\u00020\u0001X\u008a\u008e\u0002²\u0006\n\u0010$\u001a\u00020\u0001X\u008a\u008e\u0002²\u0006\n\u0010%\u001a\u00020\u0016X\u008a\u0084\u0002²\u0006\n\u0010&\u001a\u00020\u0016X\u008a\u0084\u0002"}, d2 = {"LYRIC_FRAME_DELAY_MS", "", "FOCUS_COLOR_DURATION_MS", "", "MeloXIOSLyricsPanel", "", "state", "Lcom/lladlam/melox/ui/player/MeloXPlaybackUiState;", "modifier", "Landroidx/compose/ui/Modifier;", "(Lcom/lladlam/melox/ui/player/MeloXPlaybackUiState;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "MeloXAnimatedLyricLine", "line", "Lcom/lladlam/melox/core/lyrics/LyricLine;", "positionMs", "active", "", "onClick", "Lkotlin/Function0;", "(Lcom/lladlam/melox/core/lyrics/LyricLine;JZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "MeloXAlignedLyricText", "focusProgress", "", "(Lcom/lladlam/melox/core/lyrics/LyricLine;JZFLandroidx/compose/runtime/Composer;I)V", "lerp", TtmlNode.START, TtmlNode.END, NotificationCompat.CATEGORY_PROGRESS, "app", "lyrics", "Lcom/lladlam/melox/core/lyrics/LyricsDocument;", "isLoading", "errorMessage", "", "anchorPositionMs", "anchorRealtimeMs", "renderedPositionMs", "focusColorProgress", "focusScaleProgress"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class MeloXIOSLyricsPanelKt {
    private static final int FOCUS_COLOR_DURATION_MS = 120;
    private static final long LYRIC_FRAME_DELAY_MS = 16;

    static final Unit MeloXAlignedLyricText$lambda$2(LyricLine lyricLine, long j, boolean z, float f, int i, Composer composer, int i2) throws Throwable {
        MeloXAlignedLyricText(lyricLine, j, z, f, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXAnimatedLyricLine$lambda$4(LyricLine lyricLine, long j, boolean z, Function0 function0, int i, Composer composer, int i2) throws Throwable {
        MeloXAnimatedLyricLine(lyricLine, j, z, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXIOSLyricsPanel$lambda$24(MeloXPlaybackUiState meloXPlaybackUiState, Modifier modifier, int i, int i2, Composer composer, int i3) {
        MeloXIOSLyricsPanel(meloXPlaybackUiState, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v17 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v8, types: [int] */
    /* JADX WARN: Type inference failed for: r12v0, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r14v16 */
    /* JADX WARN: Type inference failed for: r14v17 */
    public static final void MeloXIOSLyricsPanel(MeloXPlaybackUiState meloXPlaybackUiState, Modifier modifier, Composer composer, final int i, final int i2) {
        final Modifier modifier2;
        Modifier modifier3;
        boolean z;
        int r11;
        String str;
        MeloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$2$1 meloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$2$1;
        MutableState mutableState;
        MutableState mutableState2;
        Function0<ComposeUiNode> function0;
        Arrangement.HorizontalOrVertical horizontalOrVertical;
        final MeloXPlaybackUiState state = meloXPlaybackUiState;
        Intrinsics.checkNotNullParameter(state, "state");
        int StartRestartGroup = composer.startRestartGroup(-175512526);
        ComposerKt.sourceInformation(StartRestartGroup, "C(MeloXIOSLyricsPanel)N(state,modifier)56@2409L7,57@2453L138,62@2612L23,64@2686L59,65@2767L43,66@2835L51,68@2916L58,69@3003L71,70@3105L58,72@3228L154,72@3169L213,81@3632L321,81@3591L362,92@3983L306,92@3959L330,108@4665L181,108@4623L223,114@4852L1947:MeloXIOSLyricsPanel.kt#qhu5z0");
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
                ComposerKt.traceEventStart(-175512526, i5, -1, "com.lladlam.melox.ui.player.MeloXIOSLyricsPanel (MeloXIOSLyricsPanel.kt:55)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = StartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
            final Context applicationContext = ((Context) objConsume).getApplicationContext();
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, -1163807300, "CC(remember):MeloXIOSLyricsPanel.kt#9igjgp");
            boolean zChanged = StartRestartGroup.changed(applicationContext);
            Object objRememberedValue = StartRestartGroup.rememberedValue();
            OkHttpClient okHttpClient = null;
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                NeteaseSearchClient neteaseSearchClient = new NeteaseSearchClient(okHttpClient, new Function0() { // from class: com.lladlam.melox.ui.player.MeloXIOSLyricsPanelKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MeloXIOSLyricsPanelKt.MeloXIOSLyricsPanel$lambda$0$0(applicationContext);
                    }
                }, i6, 0 == true ? 1 : 0);
                StartRestartGroup.updateRememberedValue(neteaseSearchClient);
                objRememberedValue = neteaseSearchClient;
            }
            NeteaseSearchClient neteaseSearchClient2 = (NeteaseSearchClient) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
            LazyListState lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, StartRestartGroup, 0, 3);
            String mediaId = state.getMediaId();
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, -1163799923, "CC(remember):MeloXIOSLyricsPanel.kt#9igjgp");
            boolean zChanged2 = StartRestartGroup.changed(mediaId);
            Object objRememberedValue2 = StartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                StartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default);
                objRememberedValue2 = mutableStateMutableStateOf$default;
            }
            MutableState mutableState3 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, -1163797347, "CC(remember):MeloXIOSLyricsPanel.kt#9igjgp");
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
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, -1163795163, "CC(remember):MeloXIOSLyricsPanel.kt#9igjgp");
            boolean zChanged4 = StartRestartGroup.changed(mediaId);
            Object objRememberedValue4 = StartRestartGroup.rememberedValue();
            if (zChanged4 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                StartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default3);
                objRememberedValue4 = mutableStateMutableStateOf$default3;
            }
            MutableState mutableState5 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, -1163792564, "CC(remember):MeloXIOSLyricsPanel.kt#9igjgp");
            boolean zChanged5 = StartRestartGroup.changed(mediaId);
            Object objRememberedValue5 = StartRestartGroup.rememberedValue();
            if (zChanged5 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                MutableLongState mutableLongStateMutableLongStateOf = SnapshotLongStateKt.mutableLongStateOf(state.getPositionMs());
                StartRestartGroup.updateRememberedValue(mutableLongStateMutableLongStateOf);
                objRememberedValue5 = mutableLongStateMutableLongStateOf;
            }
            MutableLongState mutableLongState = (MutableLongState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, -1163789767, "CC(remember):MeloXIOSLyricsPanel.kt#9igjgp");
            boolean zChanged6 = StartRestartGroup.changed(mediaId);
            Object objRememberedValue6 = StartRestartGroup.rememberedValue();
            if (zChanged6 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                MutableLongState mutableLongStateMutableLongStateOf2 = SnapshotLongStateKt.mutableLongStateOf(SystemClock.elapsedRealtime());
                StartRestartGroup.updateRememberedValue(mutableLongStateMutableLongStateOf2);
                objRememberedValue6 = mutableLongStateMutableLongStateOf2;
            }
            MutableLongState mutableLongState2 = (MutableLongState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, -1163786516, "CC(remember):MeloXIOSLyricsPanel.kt#9igjgp");
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
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, -1163782484, "CC(remember):MeloXIOSLyricsPanel.kt#9igjgp");
            boolean zChanged8 = StartRestartGroup.changed(mutableLongState) | ((i5 & 14) == 4 ? true : z) | StartRestartGroup.changed(mutableLongState2) | StartRestartGroup.changed(mutableLongState3);
            MeloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$1$1 meloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$1$1RememberedValue = StartRestartGroup.rememberedValue();
            if (zChanged8 || meloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                meloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$1$1RememberedValue = new MeloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$1$1(state, mutableLongState, mutableLongState2, mutableLongState3, null);
                StartRestartGroup.updateRememberedValue(meloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$1$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
            EffectsKt.LaunchedEffect(lValueOf, boolValueOf, mediaId, (Function2) meloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$1$1RememberedValue, StartRestartGroup, 0);
            Boolean boolValueOf2 = Boolean.valueOf(meloXPlaybackUiState.isPlaying());
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, -1163769389, "CC(remember):MeloXIOSLyricsPanel.kt#9igjgp");
            boolean zChanged9 = StartRestartGroup.changed(mutableLongState3) | ((i5 & 14) == 4 ? true : z) | StartRestartGroup.changed(mutableLongState) | StartRestartGroup.changed(mutableLongState2);
            Object objRememberedValue8 = StartRestartGroup.rememberedValue();
            if (zChanged9 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                r11 = z;
                str = "CC(remember):MeloXIOSLyricsPanel.kt#9igjgp";
                state = meloXPlaybackUiState;
                meloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$2$1 = new MeloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$2$1(state, mutableLongState, mutableLongState2, mutableLongState3, null);
                StartRestartGroup.updateRememberedValue(meloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$2$1);
            } else {
                r11 = z;
                str = "CC(remember):MeloXIOSLyricsPanel.kt#9igjgp";
                meloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$2$1 = objRememberedValue8;
                state = meloXPlaybackUiState;
            }
            ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
            EffectsKt.LaunchedEffect(boolValueOf2, mediaId, (Function2) meloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$2$1, StartRestartGroup, r11);
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, -1163758172, str);
            boolean zChanged10 = StartRestartGroup.changed(mediaId) | StartRestartGroup.changed(mutableState4) | StartRestartGroup.changed(mutableState5) | StartRestartGroup.changedInstance(neteaseSearchClient2) | StartRestartGroup.changed(mutableState3);
            Object objRememberedValue9 = StartRestartGroup.rememberedValue();
            if (zChanged10 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                mutableState = mutableState5;
                mutableState2 = mutableState4;
                MeloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$3$1 meloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$3$1 = new MeloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$3$1(mediaId, mutableState2, mutableState, neteaseSearchClient2, mutableState3, null);
                StartRestartGroup.updateRememberedValue(meloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$3$1);
                objRememberedValue9 = meloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$3$1;
            } else {
                mutableState = mutableState5;
                mutableState2 = mutableState4;
            }
            ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
            EffectsKt.LaunchedEffect(mediaId, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue9, (Composer) StartRestartGroup, 0);
            final LyricsDocument lyricsDocumentMeloXIOSLyricsPanel$lambda$2 = MeloXIOSLyricsPanel$lambda$2(mutableState3);
            final Integer numHighlightedIndex = lyricsDocumentMeloXIOSLyricsPanel$lambda$2 != null ? lyricsDocumentMeloXIOSLyricsPanel$lambda$2.highlightedIndex(MeloXIOSLyricsPanel$lambda$17(mutableLongState3)) : null;
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, -1163736473, str);
            boolean zChanged11 = StartRestartGroup.changed(numHighlightedIndex) | StartRestartGroup.changed(lazyListStateRememberLazyListState);
            Object objRememberedValue10 = StartRestartGroup.rememberedValue();
            if (zChanged11 || objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                MeloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$4$1 meloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$4$1 = new MeloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$4$1(numHighlightedIndex, lazyListStateRememberLazyListState, null);
                StartRestartGroup.updateRememberedValue(meloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$4$1);
                objRememberedValue10 = meloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$4$1;
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
            ComposerKt.sourceInformationMarkerStart(StartRestartGroup, -1110685403, "C:MeloXIOSLyricsPanel.kt#qhu5z0");
            if (MeloXIOSLyricsPanel$lambda$5(mutableState2) && lyricsDocumentMeloXIOSLyricsPanel$lambda$2 == null) {
                StartRestartGroup.startReplaceGroup(-1110686675);
                ComposerKt.sourceInformation(StartRestartGroup, "117@4957L169");
                Modifier modifierAlign = boxScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenter());
                long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
                ProgressIndicatorKt.m3567CircularProgressIndicator4lLiAd8(modifierAlign, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU) : 0.9f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU) : 0.0f), 0.0f, 0L, 0, 0.0f, StartRestartGroup, 48, 60);
                StartRestartGroup.endReplaceGroup();
            } else if (MeloXIOSLyricsPanel$lambda$8(mutableState) != null && lyricsDocumentMeloXIOSLyricsPanel$lambda$2 == null) {
                StartRestartGroup.startReplaceGroup(-1110425593);
                ComposerKt.sourceInformation(StartRestartGroup, "124@5216L303");
                String strMeloXIOSLyricsPanel$lambda$8 = MeloXIOSLyricsPanel$lambda$8(mutableState);
                if (strMeloXIOSLyricsPanel$lambda$8 == null) {
                    strMeloXIOSLyricsPanel$lambda$8 = "";
                }
                Modifier modifierM1805padding3ABfNKs = PaddingKt.m1805padding3ABfNKs(boxScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenter()), Dp.constructor_impl(24));
                long jM6105getWhite0d7_KjU2 = Color.INSTANCE.m6105getWhite0d7_KjU();
                TextKt.m3912TextNvy7gAk(strMeloXIOSLyricsPanel$lambda$8, modifierM1805padding3ABfNKs, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU2, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU2) : 0.52f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU2) : 0.0f), null, TextUnitKt.getSp(15), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, StartRestartGroup, 24960, 0, 262120);
                StartRestartGroup.endReplaceGroup();
            } else if (lyricsDocumentMeloXIOSLyricsPanel$lambda$2 == null || lyricsDocumentMeloXIOSLyricsPanel$lambda$2.getLines().isEmpty()) {
                StartRestartGroup.startReplaceGroup(-1110034280);
                ComposerKt.sourceInformation(StartRestartGroup, "135@5613L222");
                Modifier modifierAlign2 = boxScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenter());
                long jM6105getWhite0d7_KjU3 = Color.INSTANCE.m6105getWhite0d7_KjU();
                TextKt.m3912TextNvy7gAk("暂无歌词", modifierAlign2, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU3, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU3) : 0.42f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU3) : 0.0f), null, TextUnitKt.getSp(18), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, StartRestartGroup, 24966, 0, 262120);
                StartRestartGroup.endReplaceGroup();
            } else {
                StartRestartGroup.startReplaceGroup(-1109740090);
                ComposerKt.sourceInformation(StartRestartGroup, "150@6224L545,144@5889L880");
                Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                PaddingValues paddingValuesM1802PaddingValuesa9UjIt4$default = PaddingKt.m1802PaddingValuesa9UjIt4$default(0.0f, Dp.constructor_impl(58), 0.0f, Dp.constructor_impl(76), 5, null);
                Arrangement.HorizontalOrVertical horizontalOrVerticalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(Dp.constructor_impl(22));
                Alignment.Horizontal start = Alignment.INSTANCE.getStart();
                ComposerKt.sourceInformationMarkerStart(StartRestartGroup, -589976467, str);
                boolean zChangedInstance = StartRestartGroup.changedInstance(lyricsDocumentMeloXIOSLyricsPanel$lambda$2) | StartRestartGroup.changed(mutableLongState3) | StartRestartGroup.changed(numHighlightedIndex) | ((i5 & 14) == 4);
                Object objRememberedValue11 = StartRestartGroup.rememberedValue();
                if (!zChangedInstance) {
                    horizontalOrVertical = horizontalOrVerticalM1497spacedBy0680j_4;
                    if (objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
                    LazyDslKt.LazyColumn(modifierFillMaxSize$default, lazyListStateRememberLazyListState, paddingValuesM1802PaddingValuesa9UjIt4$default, false, horizontalOrVertical, start, null, false, null, (Function1) objRememberedValue11, StartRestartGroup, 221574, 456);
                    StartRestartGroup.endReplaceGroup();
                } else {
                    horizontalOrVertical = horizontalOrVerticalM1497spacedBy0680j_4;
                }
                Function1 function1 = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSLyricsPanelKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MeloXIOSLyricsPanelKt.MeloXIOSLyricsPanel$lambda$23$0$0(lyricsDocumentMeloXIOSLyricsPanel$lambda$2, numHighlightedIndex, state, mutableLongState3, (LazyListScope) obj);
                    }
                };
                StartRestartGroup.updateRememberedValue(function1);
                objRememberedValue11 = function1;
                ComposerKt.sourceInformationMarkerEnd(StartRestartGroup);
                LazyDslKt.LazyColumn(modifierFillMaxSize$default, lazyListStateRememberLazyListState, paddingValuesM1802PaddingValuesa9UjIt4$default, false, horizontalOrVertical, start, null, false, null, (Function1) objRememberedValue11, StartRestartGroup, 221574, 456);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXIOSLyricsPanelKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXIOSLyricsPanelKt.MeloXIOSLyricsPanel$lambda$24(state, modifier2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String MeloXIOSLyricsPanel$lambda$0$0(Context $context) {
        NeteaseSessionStore.Companion companion = NeteaseSessionStore.INSTANCE;
        Intrinsics.checkNotNull($context);
        return companion.readCookie($context);
    }

    private static final LyricsDocument MeloXIOSLyricsPanel$lambda$2(MutableState<LyricsDocument> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean MeloXIOSLyricsPanel$lambda$5(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void MeloXIOSLyricsPanel$lambda$6(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String MeloXIOSLyricsPanel$lambda$8(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long MeloXIOSLyricsPanel$lambda$11(MutableLongState $anchorPositionMs$delegate) {
        return $anchorPositionMs$delegate.getLongValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long MeloXIOSLyricsPanel$lambda$14(MutableLongState $anchorRealtimeMs$delegate) {
        return $anchorRealtimeMs$delegate.getLongValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long MeloXIOSLyricsPanel$lambda$17(MutableLongState $renderedPositionMs$delegate) {
        return $renderedPositionMs$delegate.getLongValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXIOSLyricsPanel$lambda$23$0$0(LyricsDocument $document, final Integer $highlightedIndex, final MeloXPlaybackUiState $state, final MutableLongState $renderedPositionMs$delegate, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        final List<LyricLine> lines = $document.getLines();
        final Function2 function2 = new Function2() { // from class: com.lladlam.melox.ui.player.MeloXIOSLyricsPanelKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return MeloXIOSLyricsPanelKt.MeloXIOSLyricsPanel$lambda$23$0$0$0(((Integer) obj).intValue(), (LyricLine) obj2);
            }
        };
        LazyColumn.items(lines.size(), new Function1<Integer, Object>() { // from class: com.lladlam.melox.ui.player.MeloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$lambda$23$0$0$$inlined$itemsIndexed$default$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int index) {
                return function2.invoke(Integer.valueOf(index), lines.get(index));
            }
        }, new Function1<Integer, Object>() { // from class: com.lladlam.melox.ui.player.MeloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$lambda$23$0$0$$inlined$itemsIndexed$default$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int index) {
                lines.get(index);
                return null;
            }
        }, ComposableLambdaKt.composableLambdaInstance(2039820996, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.lladlam.melox.ui.player.MeloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$lambda$23$0$0$$inlined$itemsIndexed$default$3
            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) throws Throwable {
                invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope $this$items, int it, Composer $composer, int $changed) throws Throwable {
                ComposerKt.sourceInformation($composer, "CN(it)214@10668L26:LazyDsl.kt#428nma");
                int $dirty = $changed;
                if (($changed & 6) == 0) {
                    $dirty |= $composer.changed($this$items) ? 4 : 2;
                }
                if (($changed & 48) == 0) {
                    $dirty |= $composer.changed(it) ? 32 : 16;
                }
                boolean z = false;
                if (!$composer.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
                    $composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(2039820996, $dirty, -1, "androidx.compose.foundation.lazy.itemsIndexed.<anonymous> (LazyDsl.kt:214)");
                }
                int i = ($dirty & 14) | ($dirty & 112);
                final LyricLine lyricLine = (LyricLine) lines.get(it);
                $composer.startReplaceGroup(96211698);
                ComposerKt.sourceInformation($composer, "CN(index,line)*159@6673L29,155@6445L284:MeloXIOSLyricsPanel.kt#qhu5z0");
                long jMeloXIOSLyricsPanel$lambda$17 = MeloXIOSLyricsPanelKt.MeloXIOSLyricsPanel$lambda$17($renderedPositionMs$delegate);
                Integer num = $highlightedIndex;
                if (num != null && it == num.intValue()) {
                    z = true;
                }
                ComposerKt.sourceInformationMarkerStart($composer, 2081320626, "CC(remember):MeloXIOSLyricsPanel.kt#9igjgp");
                boolean zChanged = $composer.changed($state) | $composer.changedInstance(lyricLine);
                Object objRememberedValue = $composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    final MeloXPlaybackUiState meloXPlaybackUiState = $state;
                    objRememberedValue = (Function0) new Function0<Unit>() { // from class: com.lladlam.melox.ui.player.MeloXIOSLyricsPanelKt$MeloXIOSLyricsPanel$5$1$1$2$1$1
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
                    $composer.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                MeloXIOSLyricsPanelKt.MeloXAnimatedLyricLine(lyricLine, jMeloXIOSLyricsPanel$lambda$17, z, (Function0) objRememberedValue, $composer, (i >> 6) & 14);
                $composer.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object MeloXIOSLyricsPanel$lambda$23$0$0$0(int index, LyricLine line) {
        Intrinsics.checkNotNullParameter(line, "line");
        return line.getTimeMs() + "-" + index;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void MeloXAnimatedLyricLine(final LyricLine line, final long positionMs, final boolean active, final Function0<Unit> function0, Composer $composer, final int $changed) throws Throwable {
        LyricLine lyricLine;
        Composer $composer2 = $composer.startRestartGroup(-1093028394);
        ComposerKt.sourceInformation($composer2, "C(MeloXAnimatedLyricLine)N(line,positionMs,active,onClick)178@7221L262,186@7514L273,204@8182L149,201@8091L1764:MeloXIOSLyricsPanel.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            lyricLine = line;
            $dirty |= $composer2.changedInstance(lyricLine) ? 4 : 2;
        } else {
            lyricLine = line;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(positionMs) ? 32 : 16;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer2.changed(active) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 2048 : 1024;
        }
        if ($composer2.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1093028394, $dirty, -1, "com.lladlam.melox.ui.player.MeloXAnimatedLyricLine (MeloXIOSLyricsPanel.kt:174)");
            }
            State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(active ? 1.0f : 0.0f, AnimationSpecKt.tween$default(FOCUS_COLOR_DURATION_MS, 0, EasingKt.getFastOutSlowInEasing(), 2, null), 0.0f, "lyric-focus-color-" + lyricLine.getTimeMs(), null, $composer2, 0, 20);
            final float visualScale = (MeloXAnimatedLyricLine$lambda$1(AnimateAsStateKt.animateFloatAsState(active ? 1.0f : 0.0f, AnimationSpecKt.spring(0.88f, 300.0f, Float.valueOf(0.001f)), 0.0f, "lyric-focus-scale-" + lyricLine.getTimeMs(), null, $composer2, 48, 20)) * 0.16f) + 0.84f;
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 1866744523, "CC(remember):MeloXIOSLyricsPanel.kt#9igjgp");
            boolean zChanged = $composer2.changed(visualScale);
            Object objRememberedValue = $composer2.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.lladlam.melox.ui.player.MeloXIOSLyricsPanelKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return MeloXIOSLyricsPanelKt.MeloXAnimatedLyricLine$lambda$2$0(visualScale, (GraphicsLayerScope) obj2);
                    }
                };
                $composer2.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierM1806paddingVpY3zN4 = PaddingKt.m1806paddingVpY3zN4(ClickableKt.m1078clickableoSLSa3U$default(GraphicsLayerModifierKt.graphicsLayer(modifierFillMaxWidth$default, (Function1) objRememberedValue), false, null, null, null, function0, 15, null), Dp.constructor_impl(8), Dp.constructor_impl(2));
            Alignment.Horizontal start = Alignment.INSTANCE.getStart();
            ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), start, $composer2, ((RendererCapabilities.DECODER_SUPPORT_MASK >> 3) & 14) | ((RendererCapabilities.DECODER_SUPPORT_MASK >> 3) & 112));
            int i = (RendererCapabilities.DECODER_SUPPORT_MASK << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierM1806paddingVpY3zN4);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i2 = ((i << 6) & 896) | 6;
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
            Updater.set_impl(composerM5188constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i3 = (i2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i4 = ((RendererCapabilities.DECODER_SUPPORT_MASK >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1361116697, "C213@8495L171:MeloXIOSLyricsPanel.kt#qhu5z0");
            MeloXAlignedLyricText(line, positionMs, active, MeloXAnimatedLyricLine$lambda$0(stateAnimateFloatAsState), $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896));
            String translation = line.getTranslation();
            String str = (translation == null || StringsKt.isBlank(translation)) ? null : translation;
            if (str == null) {
                $composer2.startReplaceGroup(1361341445);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(1361341446);
                ComposerKt.sourceInformation($composer2, "*223@8785L457");
                Modifier modifierM1809paddingqDBjuR0$default = PaddingKt.m1809paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, Dp.constructor_impl(5), 0.0f, 0.0f, 13, null);
                int iM8763getStarte0LSkKk = TextAlign.INSTANCE.m8763getStarte0LSkKk();
                long sp = TextUnitKt.getSp(14);
                long sp2 = TextUnitKt.getSp(19);
                long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
                TextKt.m3912TextNvy7gAk(str, modifierM1809paddingqDBjuR0$default, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU) : lerp(0.28f, 0.68f, MeloXAnimatedLyricLine$lambda$0(stateAnimateFloatAsState)), (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU) : 0.0f), null, sp, null, null, null, 0L, null, TextAlign.m8751boximpl(iM8763getStarte0LSkKk), sp2, 0, false, 0, 0, null, null, $composer2, 24624, 48, 259048);
                $composer2.endReplaceGroup();
            }
            String romanization = line.getRomanization();
            String str2 = (romanization == null || StringsKt.isBlank(romanization)) ? null : romanization;
            if (str2 == null) {
                $composer2.startReplaceGroup(1361927779);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(1361927780);
                ComposerKt.sourceInformation($composer2, "*240@9377L458");
                Modifier modifierM1809paddingqDBjuR0$default2 = PaddingKt.m1809paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, Dp.constructor_impl(3), 0.0f, 0.0f, 13, null);
                int iM8763getStarte0LSkKk2 = TextAlign.INSTANCE.m8763getStarte0LSkKk();
                long sp3 = TextUnitKt.getSp(12);
                long sp4 = TextUnitKt.getSp(17);
                long jM6105getWhite0d7_KjU2 = Color.INSTANCE.m6105getWhite0d7_KjU();
                TextKt.m3912TextNvy7gAk(str2, modifierM1809paddingqDBjuR0$default2, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU2, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU2) : lerp(0.22f, 0.5f, MeloXAnimatedLyricLine$lambda$0(stateAnimateFloatAsState)), (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU2) : 0.0f), null, sp3, null, null, null, 0L, null, TextAlign.m8751boximpl(iM8763getStarte0LSkKk2), sp4, 0, false, 0, 0, null, null, $composer2, 24624, 48, 259048);
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
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXIOSLyricsPanelKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return MeloXIOSLyricsPanelKt.MeloXAnimatedLyricLine$lambda$4(line, positionMs, active, function0, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    private static final float MeloXAnimatedLyricLine$lambda$0(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float MeloXAnimatedLyricLine$lambda$1(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXAnimatedLyricLine$lambda$2$0(float $visualScale, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setScaleX($visualScale);
        graphicsLayer.setScaleY($visualScale);
        graphicsLayer.mo6269setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(0.0f, 0.5f));
        return Unit.INSTANCE;
    }

    private static final void MeloXAlignedLyricText(final LyricLine line, final long positionMs, final boolean active, final float focusProgress, Composer $composer, final int $changed) throws Throwable {
        LyricLine lyricLine;
        Composer $composer2;
        int i;
        AnnotatedString annotated;
        float fCoerceIn;
        Composer $composer3 = $composer.startRestartGroup(1919547432);
        ComposerKt.sourceInformation($composer3, "C(MeloXAlignedLyricText)N(line,positionMs,active,focusProgress)304@11482L400:MeloXIOSLyricsPanel.kt#qhu5z0");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            lyricLine = line;
            $dirty |= $composer3.changedInstance(lyricLine) ? 4 : 2;
        } else {
            lyricLine = line;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(positionMs) ? 32 : 16;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer3.changed(active) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(focusProgress) ? 2048 : 1024;
        }
        if (!$composer3.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1919547432, $dirty, -1, "com.lladlam.melox.ui.player.MeloXAlignedLyricText (MeloXIOSLyricsPanel.kt:262)");
            }
            float f = 0.0f;
            int i2 = 1065353216;
            if (!active || lyricLine.getSyllables().isEmpty()) {
                $composer2 = $composer3;
                i = 32;
                AnnotatedString.Builder builder = new AnnotatedString.Builder(0, 1, null);
                long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
                int iPushStyle = builder.pushStyle(new SpanStyle(Color.copy_wmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU) : lerp(0.34f, 1.0f, focusProgress), (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU) : 0.0f), 0L, FontWeight.INSTANCE.getSemiBold(), (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 65530, (DefaultConstructorMarker) null));
                try {
                    builder.append(lyricLine.getText());
                    Unit unit = Unit.INSTANCE;
                    builder.pop(iPushStyle);
                    annotated = builder.toAnnotatedString();
                } catch (Throwable th) {
                    builder.pop(iPushStyle);
                    throw th;
                }
            } else {
                i = 32;
                AnnotatedString.Builder builder2 = new AnnotatedString.Builder(0, 1, null);
                for (LyricSyllable lyricSyllable : lyricLine.getSyllables()) {
                    if (positionMs < lyricSyllable.getStartTimeMs()) {
                        fCoerceIn = f;
                    } else if (positionMs >= lyricSyllable.getEndTimeMs()) {
                        fCoerceIn = i2;
                    } else {
                        fCoerceIn = RangesKt.coerceIn((positionMs - lyricSyllable.getStartTimeMs()) / RangesKt.coerceAtLeast(lyricSyllable.getEndTimeMs() - lyricSyllable.getStartTimeMs(), 1L), f, (float) i2);
                    }
                    long jM6105getWhite0d7_KjU2 = Color.INSTANCE.m6105getWhite0d7_KjU();
                    int iPushStyle2 = builder2.pushStyle(new SpanStyle(Color.copy_wmQWz5c(jM6105getWhite0d7_KjU2, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU2) : lerp(0.34f, 0.3f + (0.7f * fCoerceIn), focusProgress), (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU2) : 0.0f), 0L, FontWeight.INSTANCE.getSemiBold(), (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 65530, (DefaultConstructorMarker) null));
                    try {
                        try {
                            builder2.append(lyricSyllable.getText());
                            Unit unit2 = Unit.INSTANCE;
                            builder2.pop(iPushStyle2);
                            $composer3 = $composer3;
                            f = 0.0f;
                            i2 = 1065353216;
                        } catch (Throwable th2) {
                            th = th2;
                            builder2.pop(iPushStyle2);
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
                $composer2 = $composer3;
                annotated = builder2.toAnnotatedString();
            }
            AnnotatedString annotated2 = annotated;
            TextKt.m3913TextZ58ophY(annotated2, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0L, null, TextUnitKt.getSp(25), null, null, null, 0L, null, TextAlign.m8751boximpl(TextAlign.INSTANCE.m8763getStarte0LSkKk()), TextUnitKt.getSp(i), TextOverflow.INSTANCE.m8816getEllipsisgIe3tQ8(), false, 4, 0, null, null, null, $composer2, 24624, 25008, 500716);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.player.MeloXIOSLyricsPanelKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXIOSLyricsPanelKt.MeloXAlignedLyricText$lambda$2(line, positionMs, active, focusProgress, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float lerp(float start, float end, float progress) {
        return ((end - start) * RangesKt.coerceIn(progress, 0.0f, 1.0f)) + start;
    }
}
