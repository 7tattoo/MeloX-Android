package com.lladlam.melox.ui.library;

import android.content.Context;
import androidx.activity.compose.BackHandlerKt;
import androidx.compose.animation.AnimatedContentKt;
import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.animation.AnimatedContentTransitionScope;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.ContentTransform;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.SharedTransitionScope;
import androidx.compose.animation.SharedTransitionScopeKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.BoxWithConstraintsKt;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsetsPadding_androidKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.foundation.lazy.LazyListStateKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.BasicTextFieldKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.DividerKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TextKt;
import androidx.compose.material3.pulltorefresh.PullToRefreshKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.C1301Dp;
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
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.core.location.LocationRequestCompat;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.offline.DownloadService;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.window.core.layout.WindowSizeClass;
import coil3.compose.SingletonAsyncImageKt;
import com.lladlam.melox.core.account.NeteaseAccountProfile;
import com.lladlam.melox.core.account.NeteaseSessionStore;
import com.lladlam.melox.core.library.NeteaseLibraryCache;
import com.lladlam.melox.core.library.NeteaseLibraryClient;
import com.lladlam.melox.core.library.NeteaseLibrarySnapshot;
import com.lladlam.melox.core.library.NeteasePlaylistDetail;
import com.lladlam.melox.core.library.NeteasePlaylistSummary;
import com.lladlam.melox.core.model.SearchSong;
import com.lladlam.melox.ui.MeloXLayoutKt;
import com.lladlam.melox.ui.glass.MeloXBackdropComponentsKt;
import com.lladlam.melox.ui.player.MeloXFlowingLightBackdropKt;
import com.lladlam.melox.playback.PlaybackCommands;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LibraryScreen.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000¤\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u001a-\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\b\u001a\u001b\u0010\t\u001a\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003¢\u0006\u0002\u0010\n\u001a3\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0003¢\u0006\u0002\u0010\u0012\u001a=\u0010\u0013\u001a\u00020\u00012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u000f2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003¢\u0006\u0002\u0010\u0019\u001a\u001b\u0010\u001a\u001a\u00020\u00012\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003¢\u0006\u0002\u0010\n\u001a#\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u00162\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003¢\u0006\u0002\u0010\u001e\u001aG\u0010\u001f\u001a\u00020\u00012\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u00152\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00010\u000f2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0003¢\u0006\u0002\u0010)\u001a\u0017\u0010*\u001a\u00020\u00012\u0006\u0010+\u001a\u00020,H\u0003¢\u0006\u0004\b-\u0010.\u001a;\u0010/\u001a\u00020\u00012\u0006\u00100\u001a\u00020!2\u0006\u00101\u001a\u0002022\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0007¢\u0006\u0002\u00104\u001a%\u00105\u001a\u00020\u00012\u0006\u00106\u001a\u0002072\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003¢\u0006\u0004\b8\u00109\u001a=\u0010:\u001a\u00020\u00012\u0006\u0010;\u001a\u00020<2\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u00010\u000f2\u0006\u00106\u001a\u0002072\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0003¢\u0006\u0004\b>\u0010?\u001aa\u0010@\u001a\u00020\u00012\u0006\u0010A\u001a\u00020!2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u00106\u001a\u0002072\u0006\u0010C\u001a\u0002072\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0003¢\u0006\u0004\bE\u0010F\u001a5\u0010G\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u00162\u0006\u0010H\u001a\u00020I2\u0006\u00106\u001a\u0002072\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003¢\u0006\u0004\bJ\u0010K\u001aJ\u0010L\u001a\u00020\u00012\u0006\u00106\u001a\u0002072\u0006\u0010M\u001a\u00020,2\b\b\u0002\u0010N\u001a\u00020\u00072\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0011\u0010O\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\bPH\u0003¢\u0006\u0004\bQ\u0010R\u001a\u0017\u0010S\u001a\u0002072\u0006\u00106\u001a\u000207H\u0002¢\u0006\u0004\bT\u0010U\u001a\u001f\u0010V\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010W\u001a\u000207H\u0003¢\u0006\u0004\bX\u0010Y\u001a\u001f\u0010Z\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010W\u001a\u000207H\u0003¢\u0006\u0004\b[\u0010Y\u001a\u001f\u0010\\\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010W\u001a\u000207H\u0003¢\u0006\u0004\b]\u0010Y\u001a\u001f\u0010^\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010W\u001a\u000207H\u0003¢\u0006\u0004\b_\u0010Y\u001a\u001f\u0010`\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010W\u001a\u000207H\u0003¢\u0006\u0004\ba\u0010Y\u001a\u0010\u0010b\u001a\u00020<2\u0006\u0010c\u001a\u00020dH\u0002\u001a\u0010\u0010e\u001a\u00020<2\u0006\u0010f\u001a\u00020dH\u0002\u001a\u0010\u0010g\u001a\u00020<2\u0006\u0010;\u001a\u00020dH\u0002\u001a\u0014\u0010h\u001a\u0004\u0018\u00010<2\b\u0010i\u001a\u0004\u0018\u00010<H\u0002¨\u0006j²\u0006\n\u0010k\u001a\u00020\rX\u008a\u008e\u0002²\u0006\f\u0010l\u001a\u0004\u0018\u00010!X\u008a\u008e\u0002²\u0006\f\u0010m\u001a\u0004\u0018\u00010nX\u008a\u008e\u0002²\u0006\n\u0010o\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\f\u0010p\u001a\u0004\u0018\u00010<X\u008a\u008e\u0002²\u0006\f\u0010q\u001a\u0004\u0018\u00010rX\u008a\u008e\u0002²\u0006\n\u0010o\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\f\u0010p\u001a\u0004\u0018\u00010<X\u008a\u008e\u0002²\u0006\n\u0010s\u001a\u00020<X\u008a\u008e\u0002²\u0006\n\u0010t\u001a\u00020uX\u008a\u008e\u0002"}, d2 = {"LibraryScreen", "", "session", "Lcom/lladlam/melox/core/account/NeteaseSessionStore;", "onLogin", "Lkotlin/Function0;", "playlistBackEnabled", "", "(Lcom/lladlam/melox/core/account/NeteaseSessionStore;Lkotlin/jvm/functions/Function0;ZLandroidx/compose/runtime/Composer;II)V", "MeloXLibraryLoginUnavailable", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "MeloXLibrarySegmentedPicker", "selected", "Lcom/lladlam/melox/ui/library/MeloXLibraryPage;", "onSelected", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "(Lcom/lladlam/melox/ui/library/MeloXLibraryPage;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "MeloXLibrarySongsPage", "songs", "", "Lcom/lladlam/melox/core/model/SearchSong;", "onPlay", "onPlayAll", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "MeloXPlayAllRow", "onClick", "MeloXLibraryTrackRow", "song", "(Lcom/lladlam/melox/core/model/SearchSong;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "MeloXLibraryPlaylistsPage", "playlists", "Lcom/lladlam/melox/core/library/NeteasePlaylistSummary;", "onPlaylistClick", "listState", "Landroidx/compose/foundation/lazy/LazyListState;", "sharedTransitionScope", "Landroidx/compose/animation/SharedTransitionScope;", "animatedVisibilityScope", "Landroidx/compose/animation/AnimatedVisibilityScope;", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/animation/SharedTransitionScope;Landroidx/compose/animation/AnimatedVisibilityScope;Landroidx/compose/runtime/Composer;I)V", "MeloXInsetDivider", "leading", "Landroidx/compose/ui/unit/Dp;", "MeloXInsetDivider-8Feqmps", "(FLandroidx/compose/runtime/Composer;I)V", "MeloXPlaylistDetailScreen", "initialPlaylist", "client", "Lcom/lladlam/melox/core/library/NeteaseLibraryClient;", "onBack", "(Lcom/lladlam/melox/core/library/NeteasePlaylistSummary;Lcom/lladlam/melox/core/library/NeteaseLibraryClient;Lkotlin/jvm/functions/Function0;Landroidx/compose/animation/SharedTransitionScope;Landroidx/compose/animation/AnimatedVisibilityScope;Landroidx/compose/runtime/Composer;I)V", "MeloXPlaylistToolbar", DownloadService.KEY_FOREGROUND, "Landroidx/compose/ui/graphics/Color;", "MeloXPlaylistToolbar-Iv8Zu3U", "(JLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "MeloXPlaylistSearchField", "value", "", "onValueChange", "MeloXPlaylistSearchField-cf5BqRc", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;JLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "MeloXStandardPlaylistHero", "playlist", "tracks", "secondary", "onShuffle", "MeloXStandardPlaylistHero-pAZo6Ak", "(Lcom/lladlam/melox/core/library/NeteasePlaylistSummary;Ljava/util/List;JJLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/animation/SharedTransitionScope;Landroidx/compose/animation/AnimatedVisibilityScope;Landroidx/compose/runtime/Composer;I)V", "MeloXPlaylistTrackRow", "index", "", "MeloXPlaylistTrackRow-FNF3uiM", "(Lcom/lladlam/melox/core/model/SearchSong;IJLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "MeloXGlassCircleButton", "size", "enabled", "content", "Landroidx/compose/runtime/Composable;", "MeloXGlassCircleButton-CgHO2UQ", "(JFZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "glassColor", "glassColor-8_81llA", "(J)J", "MeloXPlayGlyph", TtmlNode.ATTR_TTS_COLOR, "MeloXPlayGlyph-RPmYEkk", "(Landroidx/compose/ui/Modifier;JLandroidx/compose/runtime/Composer;I)V", "MeloXBackGlyph", "MeloXBackGlyph-RPmYEkk", "MeloXSearchGlyph", "MeloXSearchGlyph-RPmYEkk", "MeloXShareGlyph", "MeloXShareGlyph-RPmYEkk", "MeloXShuffleGlyph", "MeloXShuffleGlyph-RPmYEkk", "playlistArtworkSharedKey", "playlistId", "", "formatDuration", "milliseconds", "compactPlayCount", "optimized160Artwork", "url", "app", "selectedPage", "selectedPlaylist", "snapshot", "Lcom/lladlam/melox/core/library/NeteaseLibrarySnapshot;", "loading", "errorMessage", "detail", "Lcom/lladlam/melox/core/library/NeteasePlaylistDetail;", "searchQuery", "palette", "Lcom/lladlam/melox/ui/library/MeloXDetailPalette;"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class LibraryScreenKt {

    /* JADX INFO: compiled from: LibraryScreen.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MeloXLibraryPage.values().length];
            try {
                iArr[MeloXLibraryPage.Songs.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[MeloXLibraryPage.Playlists.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[MeloXLibraryPage.History.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit LibraryScreen$lambda$22(NeteaseSessionStore neteaseSessionStore, Function0 function0, boolean z, int i, int i2, Composer composer, int i3) {
        LibraryScreen(neteaseSessionStore, function0, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit LibraryScreen$lambda$25(NeteaseSessionStore neteaseSessionStore, Function0 function0, boolean z, int i, int i2, Composer composer, int i3) {
        LibraryScreen(neteaseSessionStore, function0, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit MeloXBackGlyph_RPmYEkk$lambda$1(Modifier modifier, long j, int i, Composer composer, int i2) {
        m9650MeloXBackGlyphRPmYEkk(modifier, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXGlassCircleButton_CgHO2UQ$lambda$2(long j, float f, boolean z, Function0 function0, Function2 function2, int i, int i2, Composer composer, int i3) {
        m9651MeloXGlassCircleButtonCgHO2UQ(j, f, z, function0, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit MeloXInsetDivider_8Feqmps$lambda$0(float f, int i, Composer composer, int i2) {
        m9652MeloXInsetDivider8Feqmps(f, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXLibraryLoginUnavailable$lambda$1(Function0 function0, int i, Composer composer, int i2) {
        MeloXLibraryLoginUnavailable(function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXLibraryPlaylistsPage$lambda$1(List list, Function1 function1, LazyListState lazyListState, SharedTransitionScope sharedTransitionScope, AnimatedVisibilityScope animatedVisibilityScope, int i, Composer composer, int i2) {
        MeloXLibraryPlaylistsPage(list, function1, lazyListState, sharedTransitionScope, animatedVisibilityScope, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXLibraryPlaylistsPage$lambda$3(List list, Function1 function1, LazyListState lazyListState, SharedTransitionScope sharedTransitionScope, AnimatedVisibilityScope animatedVisibilityScope, int i, Composer composer, int i2) {
        MeloXLibraryPlaylistsPage(list, function1, lazyListState, sharedTransitionScope, animatedVisibilityScope, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXLibrarySegmentedPicker$lambda$1(MeloXLibraryPage meloXLibraryPage, Function1 function1, Modifier modifier, int i, int i2, Composer composer, int i3) {
        MeloXLibrarySegmentedPicker(meloXLibraryPage, function1, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit MeloXLibrarySongsPage$lambda$1(List list, Function1 function1, Function0 function0, int i, Composer composer, int i2) {
        MeloXLibrarySongsPage(list, function1, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXLibrarySongsPage$lambda$3(List list, Function1 function1, Function0 function0, int i, Composer composer, int i2) {
        MeloXLibrarySongsPage(list, function1, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXLibraryTrackRow$lambda$1(SearchSong searchSong, Function0 function0, int i, Composer composer, int i2) {
        MeloXLibraryTrackRow(searchSong, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXPlayAllRow$lambda$1(Function0 function0, int i, Composer composer, int i2) {
        MeloXPlayAllRow(function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXPlayGlyph_RPmYEkk$lambda$1(Modifier modifier, long j, int i, Composer composer, int i2) {
        m9653MeloXPlayGlyphRPmYEkk(modifier, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXPlaylistDetailScreen$lambda$24(NeteasePlaylistSummary neteasePlaylistSummary, NeteaseLibraryClient neteaseLibraryClient, Function0 function0, SharedTransitionScope sharedTransitionScope, AnimatedVisibilityScope animatedVisibilityScope, int i, Composer composer, int i2) {
        MeloXPlaylistDetailScreen(neteasePlaylistSummary, neteaseLibraryClient, function0, sharedTransitionScope, animatedVisibilityScope, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXPlaylistSearchField_cf5BqRc$lambda$1(String str, Function1 function1, long j, Modifier modifier, int i, int i2, Composer composer, int i3) {
        m9654MeloXPlaylistSearchFieldcf5BqRc(str, function1, j, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit MeloXPlaylistToolbar_Iv8Zu3U$lambda$1(long j, Function0 function0, int i, Composer composer, int i2) {
        m9655MeloXPlaylistToolbarIv8Zu3U(j, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXPlaylistTrackRow_FNF3uiM$lambda$1(SearchSong searchSong, int i, long j, Function0 function0, int i2, Composer composer, int i3) {
        m9656MeloXPlaylistTrackRowFNF3uiM(searchSong, i, j, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXSearchGlyph_RPmYEkk$lambda$1(Modifier modifier, long j, int i, Composer composer, int i2) {
        m9657MeloXSearchGlyphRPmYEkk(modifier, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXShareGlyph_RPmYEkk$lambda$1(Modifier modifier, long j, int i, Composer composer, int i2) {
        m9658MeloXShareGlyphRPmYEkk(modifier, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXShuffleGlyph_RPmYEkk$lambda$1(Modifier modifier, long j, int i, Composer composer, int i2) {
        m9659MeloXShuffleGlyphRPmYEkk(modifier, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXStandardPlaylistHero_pAZo6Ak$lambda$1(NeteasePlaylistSummary neteasePlaylistSummary, List list, long j, long j2, Function0 function0, Function0 function1, SharedTransitionScope sharedTransitionScope, AnimatedVisibilityScope animatedVisibilityScope, int i, Composer composer, int i2) {
        m9660MeloXStandardPlaylistHeropAZo6Ak(neteasePlaylistSummary, list, j, j2, function0, function1, sharedTransitionScope, animatedVisibilityScope, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:113:0x0339  */
    /* JADX WARN: Code duplicated, block: B:124:0x037b  */
    /* JADX WARN: Code duplicated, block: B:126:0x0396  */
    /* JADX WARN: Code duplicated, block: B:129:0x039f  */
    /* JADX WARN: Code duplicated, block: B:130:0x03b3  */
    /* JADX WARN: Code duplicated, block: B:132:0x03b7  */
    /* JADX WARN: Code duplicated, block: B:137:0x03d2  */
    /* JADX WARN: Code duplicated, block: B:140:0x03e3  */
    /* JADX WARN: Code duplicated, block: B:141:0x03e5  */
    /* JADX WARN: Code duplicated, block: B:148:0x041d  */
    /* JADX WARN: Code duplicated, block: B:151:0x0492  */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void LibraryScreen(final NeteaseSessionStore session, final Function0<Unit> onLogin, boolean z, Composer composer, final int i, final int i2) {
        boolean z2;
        Composer composer2;
        final boolean z3;
        Long l;
        MutableState mutableState;
        MutableState mutableState2;
        MutableState mutableState3;
        NeteaseLibraryClient neteaseLibraryClient;
        Long l2;
        NeteaseLibraryCache neteaseLibraryCache;
        boolean z4;
        boolean zChanged;
        Object objRememberedValue;
        final MutableState mutableState4;
        final MutableState mutableState5;
        final MutableState mutableState6;
        boolean z5;
        boolean z6;
        boolean zChanged2;
        Object objRememberedValue2;
        final CoroutineScope coroutineScope;
        NeteaseLibraryCache neteaseLibraryCache2;
        MutableState mutableState7;
        MutableState mutableState8;
        MutableState mutableState9;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(onLogin, "onLogin");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1540429028);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(LibraryScreen)N(session,onLogin,playlistBackEnabled)100@4542L7,102@4614L24,103@4656L145,108@4818L56,110@4900L51,111@4980L74,112@5075L74,113@5169L50,114@5244L58,115@5331L23,132@5932L246,132@5876L302,140@6255L39,140@6184L110,151@6489L37,153@6577L7495,149@6399L7673:LibraryScreen.kt#t3x8p4");
        int i3 = i;
        if ((i & 6) == 0) {
            i3 |= composerStartRestartGroup.changed(session) ? 4 : 2;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(onLogin) ? 32 : 16;
        }
        int i4 = i2 & 4;
        if (i4 != 0) {
            i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
            z2 = z;
        } else if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            z2 = z;
            i3 |= composerStartRestartGroup.changed(z2) ? 256 : 128;
        } else {
            z2 = z;
        }
        int i5 = i3;
        if (composerStartRestartGroup.shouldExecute((i5 & 147) != 146, i5 & 1)) {
            boolean z7 = i4 != 0 ? true : z2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1540429028, i5, -1, "com.lladlam.melox.ui.library.LibraryScreen (LibraryScreen.kt:99)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Context context = (Context) objConsume;
            final Context applicationContext = context.getApplicationContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)N(getContext)616@28039L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 683736516, "CC(remember):Effects.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                CoroutineScope coroutineScopeCreateCompositionCoroutineScope = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                composerStartRestartGroup.updateRememberedValue(coroutineScopeCreateCompositionCoroutineScope);
                objRememberedValue3 = coroutineScopeCreateCompositionCoroutineScope;
            }
            CoroutineScope coroutineScope2 = (CoroutineScope) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1411429933, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean zChanged3 = composerStartRestartGroup.changed(applicationContext);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                NeteaseLibraryClient neteaseLibraryClient2 = new NeteaseLibraryClient(new Function0() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda28
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return LibraryScreenKt.LibraryScreen$lambda$0$0(applicationContext);
                    }
                }, null, 2, null == true ? 1 : 0);
                composerStartRestartGroup.updateRememberedValue(neteaseLibraryClient2);
                objRememberedValue4 = neteaseLibraryClient2;
            }
            NeteaseLibraryClient neteaseLibraryClient3 = (NeteaseLibraryClient) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1411435028, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean zChanged4 = composerStartRestartGroup.changed(applicationContext);
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (zChanged4 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                Intrinsics.checkNotNull(applicationContext);
                NeteaseLibraryCache neteaseLibraryCache3 = new NeteaseLibraryCache(applicationContext);
                composerStartRestartGroup.updateRememberedValue(neteaseLibraryCache3);
                objRememberedValue5 = neteaseLibraryCache3;
            }
            NeteaseLibraryCache neteaseLibraryCache4 = (NeteaseLibraryCache) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1411437647, "CC(remember):LibraryScreen.kt#9igjgp");
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(MeloXLibraryPage.Songs, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default);
                objRememberedValue6 = mutableStateMutableStateOf$default;
            }
            final MutableState mutableState10 = (MutableState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            String cookie = session.getCookie();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1411440230, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean zChanged5 = composerStartRestartGroup.changed(cookie);
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (zChanged5 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default2);
                objRememberedValue7 = mutableStateMutableStateOf$default2;
            }
            final MutableState mutableState11 = (MutableState) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            String cookie2 = session.getCookie();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1411443270, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean zChanged6 = composerStartRestartGroup.changed(cookie2);
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (zChanged6 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default3);
                objRememberedValue8 = mutableStateMutableStateOf$default3;
            }
            MutableState mutableState12 = (MutableState) objRememberedValue8;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            String cookie3 = session.getCookie();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1411446254, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean zChanged7 = composerStartRestartGroup.changed(cookie3);
            Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
            if (zChanged7 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default4);
                objRememberedValue9 = mutableStateMutableStateOf$default4;
            }
            MutableState mutableState13 = (MutableState) objRememberedValue9;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            String cookie4 = session.getCookie();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1411448662, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean zChanged8 = composerStartRestartGroup.changed(cookie4);
            Object objRememberedValue10 = composerStartRestartGroup.rememberedValue();
            if (zChanged8 || objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default5);
                objRememberedValue10 = mutableStateMutableStateOf$default5;
            }
            MutableState mutableState14 = (MutableState) objRememberedValue10;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final LazyListState lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composerStartRestartGroup, 0, 3);
            String cookie5 = session.getCookie();
            NeteaseAccountProfile profile = session.getProfile();
            Long lValueOf = profile != null ? Long.valueOf(profile.getUserId()) : null;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1411470866, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean zChangedInstance = ((i5 & 14) == 4) | composerStartRestartGroup.changedInstance(neteaseLibraryCache4) | composerStartRestartGroup.changed(mutableState12) | composerStartRestartGroup.changed(mutableState13) | composerStartRestartGroup.changed(mutableState14) | composerStartRestartGroup.changedInstance(neteaseLibraryClient3);
            LibraryScreenKt$LibraryScreen$1$1 libraryScreenKt$LibraryScreen$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance) {
                l = lValueOf;
            } else {
                l = lValueOf;
                if (libraryScreenKt$LibraryScreen$1$1RememberedValue != Composer.INSTANCE.getEmpty()) {
                    l2 = l;
                    mutableState = mutableState14;
                    mutableState2 = mutableState13;
                    mutableState3 = mutableState12;
                    neteaseLibraryCache = neteaseLibraryCache4;
                    neteaseLibraryClient = neteaseLibraryClient3;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.LaunchedEffect(cookie5, l2, (Function2) libraryScreenKt$LibraryScreen$1$1RememberedValue, composerStartRestartGroup, 0);
                if (z7 || LibraryScreen$lambda$6(mutableState11) == null) {
                    z4 = false;
                } else {
                    z4 = true;
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1411480995, "CC(remember):LibraryScreen.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(mutableState11);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Function0 function0 = new Function0() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda29
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return LibraryScreenKt.LibraryScreen$lambda$21$0(mutableState11);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(function0);
                    objRememberedValue = function0;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                BackHandlerKt.BackHandler(z4, (Function0) objRememberedValue, composerStartRestartGroup, 0, 0);
                if (!session.isLoggedIn()) {
                    composerStartRestartGroup.startReplaceGroup(806308226);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "145@6335L37");
                    MeloXLibraryLoginUnavailable(onLogin, composerStartRestartGroup, (i5 >> 3) & 14);
                    composerStartRestartGroup.endReplaceGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final boolean z8 = z7;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda30
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return LibraryScreenKt.LibraryScreen$lambda$22(session, onLogin, z8, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                        return;
                    }
                    return;
                }
                mutableState4 = mutableState3;
                mutableState5 = mutableState2;
                mutableState6 = mutableState;
                composerStartRestartGroup.startReplaceGroup(806373574);
                composerStartRestartGroup.endReplaceGroup();
                if (LibraryScreen$lambda$12(mutableState5) || LibraryScreen$lambda$9(mutableState4) == null) {
                    z5 = false;
                } else {
                    z5 = true;
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1411488481, "CC(remember):LibraryScreen.kt#9igjgp");
                boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(coroutineScope2);
                if ((i5 & 14) == 4) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                zChanged2 = zChangedInstance2 | z6 | composerStartRestartGroup.changed(mutableState5) | composerStartRestartGroup.changed(mutableState6) | composerStartRestartGroup.changedInstance(neteaseLibraryClient) | composerStartRestartGroup.changed(mutableState4) | composerStartRestartGroup.changedInstance(neteaseLibraryCache);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    final NeteaseLibraryClient neteaseLibraryClient4 = neteaseLibraryClient;
                    final NeteaseLibraryCache neteaseLibraryCache5 = neteaseLibraryCache;
                    coroutineScope = coroutineScope2;
                    objRememberedValue2 = new Function0() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda31
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return LibraryScreenKt.LibraryScreen$lambda$23$0(coroutineScope, session, mutableState5, mutableState6, neteaseLibraryClient4, neteaseLibraryCache5, mutableState4);
                        }
                    };
                    neteaseLibraryCache2 = neteaseLibraryCache5;
                    neteaseLibraryClient = neteaseLibraryClient4;
                    mutableState7 = mutableState6;
                    mutableState8 = mutableState5;
                    mutableState9 = mutableState4;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    neteaseLibraryCache2 = neteaseLibraryCache;
                    mutableState9 = mutableState4;
                    coroutineScope = coroutineScope2;
                    mutableState8 = mutableState5;
                    mutableState7 = mutableState6;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                final CoroutineScope coroutineScope3 = coroutineScope;
                final NeteaseLibraryCache neteaseLibraryCache6 = neteaseLibraryCache2;
                final MutableState mutableState15 = mutableState9;
                final MutableState mutableState16 = mutableState8;
                final MutableState mutableState17 = mutableState7;
                final NeteaseLibraryClient neteaseLibraryClient5 = neteaseLibraryClient;
                PullToRefreshKt.PullToRefreshBox(z5, (Function0) objRememberedValue2, SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, null, null, ComposableLambdaKt.rememberComposableLambda(291899382, true, new Function3() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda32
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return LibraryScreenKt.LibraryScreen$lambda$24(mutableState11, neteaseLibraryClient5, context, mutableState17, lazyListStateRememberLazyListState, mutableState10, mutableState15, coroutineScope3, session, mutableState16, neteaseLibraryCache6, (BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, 1573248, 56);
                composer2 = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z3 = z7;
            }
            mutableState = mutableState14;
            mutableState2 = mutableState13;
            mutableState3 = mutableState12;
            neteaseLibraryClient = neteaseLibraryClient3;
            l2 = l;
            neteaseLibraryCache = neteaseLibraryCache4;
            libraryScreenKt$LibraryScreen$1$1RememberedValue = new LibraryScreenKt$LibraryScreen$1$1(session, neteaseLibraryCache4, mutableState3, mutableState2, mutableState, neteaseLibraryClient, null);
            composerStartRestartGroup.updateRememberedValue(libraryScreenKt$LibraryScreen$1$1RememberedValue);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.LaunchedEffect(cookie5, l2, (Function2) libraryScreenKt$LibraryScreen$1$1RememberedValue, composerStartRestartGroup, 0);
            if (z7) {
                z4 = false;
            } else {
                z4 = false;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1411480995, "CC(remember):LibraryScreen.kt#9igjgp");
            zChanged = composerStartRestartGroup.changed(mutableState11);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!zChanged) {
            }
            Function0 function1 = new Function0() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda29
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return LibraryScreenKt.LibraryScreen$lambda$21$0(mutableState11);
                }
            };
            composerStartRestartGroup.updateRememberedValue(function1);
            objRememberedValue = function1;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            BackHandlerKt.BackHandler(z4, (Function0) objRememberedValue, composerStartRestartGroup, 0, 0);
            if (!session.isLoggedIn()) {
                composerStartRestartGroup.startReplaceGroup(806308226);
                ComposerKt.sourceInformation(composerStartRestartGroup, "145@6335L37");
                MeloXLibraryLoginUnavailable(onLogin, composerStartRestartGroup, (i5 >> 3) & 14);
                composerStartRestartGroup.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final boolean z9 = z7;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda30
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return LibraryScreenKt.LibraryScreen$lambda$22(session, onLogin, z9, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                    return;
                }
                return;
            }
            mutableState4 = mutableState3;
            mutableState5 = mutableState2;
            mutableState6 = mutableState;
            composerStartRestartGroup.startReplaceGroup(806373574);
            composerStartRestartGroup.endReplaceGroup();
            if (LibraryScreen$lambda$12(mutableState5)) {
                z5 = false;
            } else {
                z5 = false;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1411488481, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(coroutineScope2);
            if ((i5 & 14) == 4) {
                z6 = true;
            } else {
                z6 = false;
            }
            zChanged2 = zChangedInstance3 | z6 | composerStartRestartGroup.changed(mutableState5) | composerStartRestartGroup.changed(mutableState6) | composerStartRestartGroup.changedInstance(neteaseLibraryClient) | composerStartRestartGroup.changed(mutableState4) | composerStartRestartGroup.changedInstance(neteaseLibraryCache);
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged2) {
                final NeteaseLibraryClient neteaseLibraryClient6 = neteaseLibraryClient;
                final NeteaseLibraryCache neteaseLibraryCache7 = neteaseLibraryCache;
                coroutineScope = coroutineScope2;
                objRememberedValue2 = new Function0() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda31
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return LibraryScreenKt.LibraryScreen$lambda$23$0(coroutineScope, session, mutableState5, mutableState6, neteaseLibraryClient6, neteaseLibraryCache7, mutableState4);
                    }
                };
                neteaseLibraryCache2 = neteaseLibraryCache7;
                neteaseLibraryClient = neteaseLibraryClient6;
                mutableState7 = mutableState6;
                mutableState8 = mutableState5;
                mutableState9 = mutableState4;
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                final NeteaseLibraryClient neteaseLibraryClient7 = neteaseLibraryClient;
                final NeteaseLibraryCache neteaseLibraryCache8 = neteaseLibraryCache;
                coroutineScope = coroutineScope2;
                objRememberedValue2 = new Function0() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda31
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return LibraryScreenKt.LibraryScreen$lambda$23$0(coroutineScope, session, mutableState5, mutableState6, neteaseLibraryClient7, neteaseLibraryCache8, mutableState4);
                    }
                };
                neteaseLibraryCache2 = neteaseLibraryCache8;
                neteaseLibraryClient = neteaseLibraryClient7;
                mutableState7 = mutableState6;
                mutableState8 = mutableState5;
                mutableState9 = mutableState4;
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final CoroutineScope coroutineScope4 = coroutineScope;
            final NeteaseLibraryCache neteaseLibraryCache9 = neteaseLibraryCache2;
            final MutableState mutableState18 = mutableState9;
            final MutableState mutableState19 = mutableState8;
            final MutableState mutableState110 = mutableState7;
            final NeteaseLibraryClient neteaseLibraryClient8 = neteaseLibraryClient;
            PullToRefreshKt.PullToRefreshBox(z5, (Function0) objRememberedValue2, SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, null, null, ComposableLambdaKt.rememberComposableLambda(291899382, true, new Function3() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda32
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return LibraryScreenKt.LibraryScreen$lambda$24(mutableState11, neteaseLibraryClient8, context, mutableState110, lazyListStateRememberLazyListState, mutableState10, mutableState18, coroutineScope4, session, mutableState19, neteaseLibraryCache9, (BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, 1573248, 56);
            composer2 = composerStartRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            z3 = z7;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            z3 = z2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup2 = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup2 != null) {
            scopeUpdateScopeEndRestartGroup2.updateScope(new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda33
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LibraryScreenKt.LibraryScreen$lambda$25(session, onLogin, z3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String LibraryScreen$lambda$0$0(Context $appContext) {
        NeteaseSessionStore.Companion companion = NeteaseSessionStore.INSTANCE;
        Intrinsics.checkNotNull($appContext);
        return companion.readCookie($appContext);
    }

    private static final MeloXLibraryPage LibraryScreen$lambda$3(MutableState<MeloXLibraryPage> mutableState) {
        return mutableState.getValue();
    }

    private static final NeteasePlaylistSummary LibraryScreen$lambda$6(MutableState<NeteasePlaylistSummary> mutableState) {
        return mutableState.getValue();
    }

    private static final NeteaseLibrarySnapshot LibraryScreen$lambda$9(MutableState<NeteaseLibrarySnapshot> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean LibraryScreen$lambda$12(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void LibraryScreen$lambda$13(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String LibraryScreen$lambda$15(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:33:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:36:0x012e A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:37:0x012f  */
    /* JADX WARN: Code duplicated, block: B:47:0x0172  */
    /* JADX WARN: Code duplicated, block: B:49:0x01aa A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:50:0x01ab  */
    /* JADX WARN: Code duplicated, block: B:54:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:56:0x01bb  */
    /* JADX WARN: Code duplicated, block: B:60:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public static final Object LibraryScreen$refreshLibrary(NeteaseSessionStore $session, MutableState<Boolean> mutableState, MutableState<String> mutableState2, NeteaseLibraryClient client, NeteaseLibraryCache cache, MutableState<NeteaseLibrarySnapshot> mutableState3, Continuation<? super Unit> continuation) {
        LibraryScreenKt$LibraryScreen$refreshLibrary$1 libraryScreenKt$LibraryScreen$refreshLibrary$1;
        NeteaseLibraryClient client2;
        MutableState<String> mutableState4;
        NeteaseLibraryCache cache2;
        MutableState<NeteaseLibrarySnapshot> mutableState5;
        MutableState<Boolean> mutableState6;
        NeteaseSessionStore $session2;
        MutableState<NeteaseLibrarySnapshot> mutableState7;
        NeteaseAccountProfile profile;
        long userId;
        NeteaseLibraryCache cache3;
        long userId2;
        MutableState<Boolean> mutableState8;
        NeteaseLibraryClient client3;
        MutableState<NeteaseLibrarySnapshot> mutableState9;
        MutableState<String> mutableState10;
        NeteaseSessionStore $session3;
        Object objSnapshot;
        MutableState<Boolean> mutableState11;
        NeteaseSessionStore $session4;
        Object objM9714constructorimpl;
        NeteaseSessionStore $session5;
        MutableState<Boolean> mutableState12;
        MutableState<String> mutableState13;
        NeteaseLibraryClient client4;
        NeteaseLibraryCache cache4;
        MutableState<NeteaseLibrarySnapshot> mutableState14;
        long userId3;
        NeteaseLibrarySnapshot neteaseLibrarySnapshot;
        Throwable thM9717exceptionOrNullimpl;
        String message;
        NeteaseSessionStore $session6 = $session;
        if (continuation instanceof LibraryScreenKt$LibraryScreen$refreshLibrary$1) {
            libraryScreenKt$LibraryScreen$refreshLibrary$1 = (LibraryScreenKt$LibraryScreen$refreshLibrary$1) continuation;
            if ((libraryScreenKt$LibraryScreen$refreshLibrary$1.label & Integer.MIN_VALUE) != 0) {
                libraryScreenKt$LibraryScreen$refreshLibrary$1.label -= Integer.MIN_VALUE;
            } else {
                libraryScreenKt$LibraryScreen$refreshLibrary$1 = new LibraryScreenKt$LibraryScreen$refreshLibrary$1(continuation);
            }
        } else {
            libraryScreenKt$LibraryScreen$refreshLibrary$1 = new LibraryScreenKt$LibraryScreen$refreshLibrary$1(continuation);
        }
        Object $result = libraryScreenKt$LibraryScreen$refreshLibrary$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (libraryScreenKt$LibraryScreen$refreshLibrary$1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (!$session6.isLoggedIn()) {
                    return Unit.INSTANCE;
                }
                if ($session6.getProfile() == null) {
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$0 = $session6;
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$1 = mutableState;
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$2 = mutableState2;
                    client2 = client;
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$3 = client2;
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$4 = cache;
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$5 = mutableState3;
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.label = 1;
                    if ($session6.refreshProfile(true, libraryScreenKt$LibraryScreen$refreshLibrary$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    mutableState6 = mutableState;
                    mutableState7 = mutableState3;
                    mutableState4 = mutableState2;
                    cache2 = cache;
                    mutableState5 = mutableState7;
                    $session2 = $session6;
                } else {
                    client2 = client;
                    mutableState4 = mutableState2;
                    cache2 = cache;
                    mutableState5 = mutableState3;
                    mutableState6 = mutableState;
                    $session2 = $session6;
                }
                profile = $session2.getProfile();
                if (profile != null) {
                    return Unit.INSTANCE;
                }
                userId = profile.getUserId();
                LibraryScreen$lambda$13(mutableState6, true);
                mutableState4.setValue(null);
                try {
                    Result.Companion companion = Result.INSTANCE;
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$0 = SpillingKt.nullOutSpilledVariable($session2);
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$1 = mutableState6;
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$2 = mutableState4;
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$3 = SpillingKt.nullOutSpilledVariable(client2);
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$4 = cache2;
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$5 = mutableState5;
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.J$0 = userId;
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.I$0 = 0;
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.label = 2;
                    objSnapshot = client2.snapshot(userId, libraryScreenKt$LibraryScreen$refreshLibrary$1);
                    if (objSnapshot == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    MutableState<String> mutableState15 = mutableState4;
                    cache3 = cache2;
                    mutableState11 = mutableState6;
                    client3 = client2;
                    mutableState9 = mutableState5;
                    mutableState10 = mutableState15;
                    $session4 = $session2;
                    try {
                        userId3 = userId;
                        mutableState12 = mutableState11;
                        mutableState13 = mutableState10;
                        client4 = client3;
                        cache4 = cache3;
                        mutableState14 = mutableState9;
                        objM9714constructorimpl = Result.constructor-impl((NeteaseLibrarySnapshot) objSnapshot);
                        $session5 = $session4;
                    } catch (Throwable th) {
                        th = th;
                        long j = userId;
                        mutableState8 = mutableState11;
                        userId2 = j;
                        $session3 = $session4;
                        Result.Companion companion2 = Result.INSTANCE;
                        MutableState<NeteaseLibrarySnapshot> mutableState16 = mutableState9;
                        objM9714constructorimpl = Result.constructor-impl(ResultKt.createFailure(th));
                        $session5 = $session3;
                        mutableState12 = mutableState8;
                        mutableState13 = mutableState10;
                        client4 = client3;
                        cache4 = cache3;
                        mutableState14 = mutableState16;
                        userId3 = userId2;
                    }
                    if (Result.m9721isSuccessimpl(objM9714constructorimpl)) {
                        neteaseLibrarySnapshot = (NeteaseLibrarySnapshot) objM9714constructorimpl;
                        mutableState14.setValue(neteaseLibrarySnapshot);
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.L$0 = SpillingKt.nullOutSpilledVariable($session5);
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.L$1 = mutableState12;
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.L$2 = mutableState13;
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.L$3 = SpillingKt.nullOutSpilledVariable(client4);
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.L$4 = SpillingKt.nullOutSpilledVariable(cache4);
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.L$5 = SpillingKt.nullOutSpilledVariable(mutableState14);
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.L$6 = objM9714constructorimpl;
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.L$7 = SpillingKt.nullOutSpilledVariable(neteaseLibrarySnapshot);
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.J$0 = userId3;
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.I$0 = 0;
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.label = 3;
                        if (cache4.saveSnapshot(userId3, neteaseLibrarySnapshot, libraryScreenKt$LibraryScreen$refreshLibrary$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    thM9717exceptionOrNullimpl = Result.m9717exceptionOrNullimpl(objM9714constructorimpl);
                    if (thM9717exceptionOrNullimpl != null) {
                        message = thM9717exceptionOrNullimpl.getMessage();
                        if (message == null) {
                            message = "音乐库加载失败";
                        }
                        mutableState13.setValue(message);
                    }
                    LibraryScreen$lambda$13(mutableState12, false);
                    return Unit.INSTANCE;
                } catch (Throwable th2) {
                    th = th2;
                    MutableState<String> mutableState17 = mutableState4;
                    cache3 = cache2;
                    userId2 = userId;
                    mutableState8 = mutableState6;
                    client3 = client2;
                    mutableState9 = mutableState5;
                    mutableState10 = mutableState17;
                    $session3 = $session2;
                    Result.Companion companion3 = Result.INSTANCE;
                    MutableState<NeteaseLibrarySnapshot> mutableState18 = mutableState9;
                    objM9714constructorimpl = Result.constructor-impl(ResultKt.createFailure(th));
                    $session5 = $session3;
                    mutableState12 = mutableState8;
                    mutableState13 = mutableState10;
                    client4 = client3;
                    cache4 = cache3;
                    mutableState14 = mutableState18;
                    userId3 = userId2;
                    if (Result.m9721isSuccessimpl(objM9714constructorimpl)) {
                        neteaseLibrarySnapshot = (NeteaseLibrarySnapshot) objM9714constructorimpl;
                        mutableState14.setValue(neteaseLibrarySnapshot);
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.L$0 = SpillingKt.nullOutSpilledVariable($session5);
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.L$1 = mutableState12;
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.L$2 = mutableState13;
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.L$3 = SpillingKt.nullOutSpilledVariable(client4);
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.L$4 = SpillingKt.nullOutSpilledVariable(cache4);
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.L$5 = SpillingKt.nullOutSpilledVariable(mutableState14);
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.L$6 = objM9714constructorimpl;
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.L$7 = SpillingKt.nullOutSpilledVariable(neteaseLibrarySnapshot);
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.J$0 = userId3;
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.I$0 = 0;
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.label = 3;
                        if (cache4.saveSnapshot(userId3, neteaseLibrarySnapshot, libraryScreenKt$LibraryScreen$refreshLibrary$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    thM9717exceptionOrNullimpl = Result.m9717exceptionOrNullimpl(objM9714constructorimpl);
                    if (thM9717exceptionOrNullimpl != null) {
                        message = thM9717exceptionOrNullimpl.getMessage();
                        if (message == null) {
                            message = "音乐库加载失败";
                        }
                        mutableState13.setValue(message);
                    }
                    LibraryScreen$lambda$13(mutableState12, false);
                    return Unit.INSTANCE;
                }
            case 1:
                mutableState7 = (MutableState) libraryScreenKt$LibraryScreen$refreshLibrary$1.L$5;
                cache2 = (NeteaseLibraryCache) libraryScreenKt$LibraryScreen$refreshLibrary$1.L$4;
                client2 = (NeteaseLibraryClient) libraryScreenKt$LibraryScreen$refreshLibrary$1.L$3;
                mutableState4 = (MutableState) libraryScreenKt$LibraryScreen$refreshLibrary$1.L$2;
                mutableState6 = (MutableState) libraryScreenKt$LibraryScreen$refreshLibrary$1.L$1;
                $session6 = (NeteaseSessionStore) libraryScreenKt$LibraryScreen$refreshLibrary$1.L$0;
                ResultKt.throwOnFailure($result);
                mutableState5 = mutableState7;
                $session2 = $session6;
                profile = $session2.getProfile();
                if (profile != null) {
                    return Unit.INSTANCE;
                }
                userId = profile.getUserId();
                LibraryScreen$lambda$13(mutableState6, true);
                mutableState4.setValue(null);
                Result.Companion companion4 = Result.INSTANCE;
                libraryScreenKt$LibraryScreen$refreshLibrary$1.L$0 = SpillingKt.nullOutSpilledVariable($session2);
                libraryScreenKt$LibraryScreen$refreshLibrary$1.L$1 = mutableState6;
                libraryScreenKt$LibraryScreen$refreshLibrary$1.L$2 = mutableState4;
                libraryScreenKt$LibraryScreen$refreshLibrary$1.L$3 = SpillingKt.nullOutSpilledVariable(client2);
                libraryScreenKt$LibraryScreen$refreshLibrary$1.L$4 = cache2;
                libraryScreenKt$LibraryScreen$refreshLibrary$1.L$5 = mutableState5;
                libraryScreenKt$LibraryScreen$refreshLibrary$1.J$0 = userId;
                libraryScreenKt$LibraryScreen$refreshLibrary$1.I$0 = 0;
                libraryScreenKt$LibraryScreen$refreshLibrary$1.label = 2;
                objSnapshot = client2.snapshot(userId, libraryScreenKt$LibraryScreen$refreshLibrary$1);
                if (objSnapshot == coroutine_suspended) {
                    return coroutine_suspended;
                }
                MutableState<String> mutableState19 = mutableState4;
                cache3 = cache2;
                mutableState11 = mutableState6;
                client3 = client2;
                mutableState9 = mutableState5;
                mutableState10 = mutableState19;
                $session4 = $session2;
                userId3 = userId;
                mutableState12 = mutableState11;
                mutableState13 = mutableState10;
                client4 = client3;
                cache4 = cache3;
                mutableState14 = mutableState9;
                objM9714constructorimpl = Result.constructor-impl((NeteaseLibrarySnapshot) objSnapshot);
                $session5 = $session4;
                if (Result.m9721isSuccessimpl(objM9714constructorimpl)) {
                    neteaseLibrarySnapshot = (NeteaseLibrarySnapshot) objM9714constructorimpl;
                    mutableState14.setValue(neteaseLibrarySnapshot);
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$0 = SpillingKt.nullOutSpilledVariable($session5);
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$1 = mutableState12;
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$2 = mutableState13;
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$3 = SpillingKt.nullOutSpilledVariable(client4);
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$4 = SpillingKt.nullOutSpilledVariable(cache4);
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$5 = SpillingKt.nullOutSpilledVariable(mutableState14);
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$6 = objM9714constructorimpl;
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$7 = SpillingKt.nullOutSpilledVariable(neteaseLibrarySnapshot);
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.J$0 = userId3;
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.I$0 = 0;
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.label = 3;
                    if (cache4.saveSnapshot(userId3, neteaseLibrarySnapshot, libraryScreenKt$LibraryScreen$refreshLibrary$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                thM9717exceptionOrNullimpl = Result.m9717exceptionOrNullimpl(objM9714constructorimpl);
                if (thM9717exceptionOrNullimpl != null) {
                    message = thM9717exceptionOrNullimpl.getMessage();
                    if (message == null) {
                        message = "音乐库加载失败";
                    }
                    mutableState13.setValue(message);
                }
                LibraryScreen$lambda$13(mutableState12, false);
                return Unit.INSTANCE;
            case 2:
                long userId4 = libraryScreenKt$LibraryScreen$refreshLibrary$1.I$0;
                userId2 = libraryScreenKt$LibraryScreen$refreshLibrary$1.J$0;
                mutableState9 = (MutableState) libraryScreenKt$LibraryScreen$refreshLibrary$1.L$5;
                cache3 = (NeteaseLibraryCache) libraryScreenKt$LibraryScreen$refreshLibrary$1.L$4;
                client3 = (NeteaseLibraryClient) libraryScreenKt$LibraryScreen$refreshLibrary$1.L$3;
                mutableState10 = (MutableState) libraryScreenKt$LibraryScreen$refreshLibrary$1.L$2;
                mutableState8 = (MutableState) libraryScreenKt$LibraryScreen$refreshLibrary$1.L$1;
                $session3 = (NeteaseSessionStore) libraryScreenKt$LibraryScreen$refreshLibrary$1.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                    $session4 = $session3;
                    objSnapshot = $result;
                    mutableState11 = mutableState8;
                    userId = userId2;
                    userId3 = userId;
                    mutableState12 = mutableState11;
                    mutableState13 = mutableState10;
                    client4 = client3;
                    cache4 = cache3;
                    mutableState14 = mutableState9;
                    objM9714constructorimpl = Result.constructor-impl((NeteaseLibrarySnapshot) objSnapshot);
                    $session5 = $session4;
                } catch (Throwable th3) {
                    th = th3;
                    Result.Companion companion5 = Result.INSTANCE;
                    MutableState<NeteaseLibrarySnapshot> mutableState110 = mutableState9;
                    objM9714constructorimpl = Result.constructor-impl(ResultKt.createFailure(th));
                    $session5 = $session3;
                    mutableState12 = mutableState8;
                    mutableState13 = mutableState10;
                    client4 = client3;
                    cache4 = cache3;
                    mutableState14 = mutableState110;
                    userId3 = userId2;
                    if (Result.m9721isSuccessimpl(objM9714constructorimpl)) {
                        neteaseLibrarySnapshot = (NeteaseLibrarySnapshot) objM9714constructorimpl;
                        mutableState14.setValue(neteaseLibrarySnapshot);
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.L$0 = SpillingKt.nullOutSpilledVariable($session5);
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.L$1 = mutableState12;
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.L$2 = mutableState13;
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.L$3 = SpillingKt.nullOutSpilledVariable(client4);
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.L$4 = SpillingKt.nullOutSpilledVariable(cache4);
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.L$5 = SpillingKt.nullOutSpilledVariable(mutableState14);
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.L$6 = objM9714constructorimpl;
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.L$7 = SpillingKt.nullOutSpilledVariable(neteaseLibrarySnapshot);
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.J$0 = userId3;
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.I$0 = 0;
                        libraryScreenKt$LibraryScreen$refreshLibrary$1.label = 3;
                        if (cache4.saveSnapshot(userId3, neteaseLibrarySnapshot, libraryScreenKt$LibraryScreen$refreshLibrary$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    thM9717exceptionOrNullimpl = Result.m9717exceptionOrNullimpl(objM9714constructorimpl);
                    if (thM9717exceptionOrNullimpl != null) {
                        message = thM9717exceptionOrNullimpl.getMessage();
                        if (message == null) {
                            message = "音乐库加载失败";
                        }
                        mutableState13.setValue(message);
                    }
                    LibraryScreen$lambda$13(mutableState12, false);
                    return Unit.INSTANCE;
                }
                if (Result.m9721isSuccessimpl(objM9714constructorimpl)) {
                    neteaseLibrarySnapshot = (NeteaseLibrarySnapshot) objM9714constructorimpl;
                    mutableState14.setValue(neteaseLibrarySnapshot);
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$0 = SpillingKt.nullOutSpilledVariable($session5);
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$1 = mutableState12;
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$2 = mutableState13;
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$3 = SpillingKt.nullOutSpilledVariable(client4);
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$4 = SpillingKt.nullOutSpilledVariable(cache4);
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$5 = SpillingKt.nullOutSpilledVariable(mutableState14);
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$6 = objM9714constructorimpl;
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.L$7 = SpillingKt.nullOutSpilledVariable(neteaseLibrarySnapshot);
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.J$0 = userId3;
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.I$0 = 0;
                    libraryScreenKt$LibraryScreen$refreshLibrary$1.label = 3;
                    if (cache4.saveSnapshot(userId3, neteaseLibrarySnapshot, libraryScreenKt$LibraryScreen$refreshLibrary$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                thM9717exceptionOrNullimpl = Result.m9717exceptionOrNullimpl(objM9714constructorimpl);
                if (thM9717exceptionOrNullimpl != null) {
                    message = thM9717exceptionOrNullimpl.getMessage();
                    if (message == null) {
                        message = "音乐库加载失败";
                    }
                    mutableState13.setValue(message);
                }
                LibraryScreen$lambda$13(mutableState12, false);
                return Unit.INSTANCE;
            case 3:
                int i = libraryScreenKt$LibraryScreen$refreshLibrary$1.I$0;
                long j2 = libraryScreenKt$LibraryScreen$refreshLibrary$1.J$0;
                objM9714constructorimpl = libraryScreenKt$LibraryScreen$refreshLibrary$1.L$6;
                mutableState13 = (MutableState) libraryScreenKt$LibraryScreen$refreshLibrary$1.L$2;
                mutableState12 = (MutableState) libraryScreenKt$LibraryScreen$refreshLibrary$1.L$1;
                ResultKt.throwOnFailure($result);
                thM9717exceptionOrNullimpl = Result.m9717exceptionOrNullimpl(objM9714constructorimpl);
                if (thM9717exceptionOrNullimpl != null) {
                    message = thM9717exceptionOrNullimpl.getMessage();
                    if (message == null) {
                        message = "音乐库加载失败";
                    }
                    mutableState13.setValue(message);
                }
                LibraryScreen$lambda$13(mutableState12, false);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LibraryScreen$lambda$21$0(MutableState $selectedPlaylist$delegate) {
        $selectedPlaylist$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LibraryScreen$lambda$23$0(CoroutineScope $scope, NeteaseSessionStore $session, MutableState $loading$delegate, MutableState $errorMessage$delegate, NeteaseLibraryClient $client, NeteaseLibraryCache $cache, MutableState $snapshot$delegate) {
        BuildersKt__Builders_commonKt.launch$default($scope, null, null, new LibraryScreenKt$LibraryScreen$4$1$1($session, $loading$delegate, $errorMessage$delegate, $client, $cache, $snapshot$delegate, null), 3, null);
        return Unit.INSTANCE;
    }

    static final Unit LibraryScreen$lambda$24(final MutableState $selectedPlaylist$delegate, final NeteaseLibraryClient $client, final Context $context, final MutableState $errorMessage$delegate, final LazyListState $playlistListState, final MutableState $selectedPage$delegate, final MutableState $snapshot$delegate, final CoroutineScope $scope, final NeteaseSessionStore $session, final MutableState $loading$delegate, final NeteaseLibraryCache $cache, BoxScope PullToRefreshBox, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(PullToRefreshBox, "$this$PullToRefreshBox");
        ComposerKt.sourceInformation($composer, "C154@6643L7423,154@6585L7481:LibraryScreen.kt#t3x8p4");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(291899382, $changed, -1, "com.lladlam.melox.ui.library.LibraryScreen.<anonymous> (LibraryScreen.kt:154)");
            }
            SharedTransitionScopeKt.SharedTransitionLayout(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ComposableLambdaKt.rememberComposableLambda(1604907128, true, new Function3() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return LibraryScreenKt.LibraryScreen$lambda$24$0($selectedPlaylist$delegate, $client, $context, $errorMessage$delegate, $playlistListState, $selectedPage$delegate, $snapshot$delegate, $scope, $session, $loading$delegate, $cache, (SharedTransitionScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 54, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LibraryScreen$lambda$24$0(final MutableState $selectedPlaylist$delegate, final NeteaseLibraryClient $client, final Context $context, final MutableState $errorMessage$delegate, final LazyListState $playlistListState, final MutableState $selectedPage$delegate, final MutableState $snapshot$delegate, final CoroutineScope $scope, final NeteaseSessionStore $session, final MutableState $loading$delegate, final NeteaseLibraryCache $cache, final SharedTransitionScope SharedTransitionLayout, Composer $composer, int $changed) throws Throwable {
        Intrinsics.checkNotNullParameter(SharedTransitionLayout, "$this$SharedTransitionLayout");
        ComposerKt.sourceInformation($composer, "C160@6824L877,181@7728L46,183@7844L6214,157@6687L7371:LibraryScreen.kt#t3x8p4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(SharedTransitionLayout) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (!$composer.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1604907128, $dirty2, -1, "com.lladlam.melox.ui.library.LibraryScreen.<anonymous>.<anonymous> (LibraryScreen.kt:155)");
            }
            NeteasePlaylistSummary neteasePlaylistSummaryLibraryScreen$lambda$6 = LibraryScreen$lambda$6($selectedPlaylist$delegate);
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 422497413, "CC(remember):LibraryScreen.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda51
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return LibraryScreenKt.LibraryScreen$lambda$24$0$0$0((AnimatedContentTransitionScope) obj2);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            Function1 function1 = (Function1) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, 422525510, "CC(remember):LibraryScreen.kt#9igjgp");
            Object objRememberedValue2 = $composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function1() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda52
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return LibraryScreenKt.LibraryScreen$lambda$24$0$1$0((NeteasePlaylistSummary) obj3);
                    }
                };
                $composer.updateRememberedValue(obj2);
                objRememberedValue2 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            AnimatedContentKt.AnimatedContent(neteasePlaylistSummaryLibraryScreen$lambda$6, modifierFillMaxSize$default, function1, null, "library-playlist-detail-transition", (Function1) objRememberedValue2, ComposableLambdaKt.rememberComposableLambda(-929040074, true, new Function4() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda53
                @Override // kotlin.jvm.functions.Function4
                public final Object invoke(Object obj3, Object obj4, Object obj5, Object obj6) {
                    return LibraryScreenKt.LibraryScreen$lambda$24$0$2($client, $selectedPlaylist$delegate, SharedTransitionLayout, $context, $errorMessage$delegate, $playlistListState, $selectedPage$delegate, $snapshot$delegate, $scope, $session, $loading$delegate, $cache, (AnimatedContentScope) obj3, (NeteasePlaylistSummary) obj4, (Composer) obj5, ((Integer) obj6).intValue());
                }
            }, $composer, 54), $composer, 1794480, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ContentTransform LibraryScreen$lambda$24$0$0$0(AnimatedContentTransitionScope AnimatedContent) {
        Intrinsics.checkNotNullParameter(AnimatedContent, "$this$AnimatedContent");
        boolean openingDetail = AnimatedContent.getTargetState() != 0;
        ContentTransform contentTransform = AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween(320, 55, EasingKt.getFastOutSlowInEasing()), 0.0f, 2, null), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(360, 0, EasingKt.getFastOutSlowInEasing(), 2, null), 0.0f, 2, null));
        contentTransform.setTargetContentZIndex(openingDetail ? 2.0f : 0.0f);
        return contentTransform;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object LibraryScreen$lambda$24$0$1$0(NeteasePlaylistSummary playlist) {
        return Long.valueOf(playlist != null ? playlist.getId() : Long.MIN_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LibraryScreen$lambda$24$0$2(final NeteaseLibraryClient $client, final MutableState $selectedPlaylist$delegate, SharedTransitionScope $sharedScope, final Context $context, final MutableState $errorMessage$delegate, LazyListState $playlistListState, final MutableState $selectedPage$delegate, final MutableState $snapshot$delegate, final CoroutineScope $scope, final NeteaseSessionStore $session, final MutableState $loading$delegate, final NeteaseLibraryCache $cache, AnimatedContentScope AnimatedContent, NeteasePlaylistSummary targetPlaylist, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Composer composer;
        Composer composer2;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function2;
        Function0<ComposeUiNode> function3;
        Intrinsics.checkNotNullParameter(AnimatedContent, "$this$AnimatedContent");
        ComposerKt.sourceInformation($composer, "CN(targetPlaylist):LibraryScreen.kt#t3x8p4");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-929040074, $changed, -1, "com.lladlam.melox.ui.library.LibraryScreen.<anonymous>.<anonymous>.<anonymous> (LibraryScreen.kt:184)");
        }
        if (targetPlaylist != null) {
            $composer.startReplaceGroup(-263461791);
            ComposerKt.sourceInformation($composer, "189@8126L27,186@7979L331");
            ComposerKt.sourceInformationMarkerStart($composer, 545695505, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean zChanged = $composer.changed($selectedPlaylist$delegate);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda58
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return LibraryScreenKt.LibraryScreen$lambda$24$0$2$0$0($selectedPlaylist$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            MeloXPlaylistDetailScreen(targetPlaylist, $client, (Function0) objRememberedValue, $sharedScope, AnimatedContent, $composer, (($changed >> 3) & 14) | (57344 & ($changed << 12)));
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(-262929738);
            ComposerKt.sourceInformation($composer, "197@8485L11,194@8348L5686");
            Modifier modifierStatusBarsPadding = WindowInsetsPadding_androidKt.statusBarsPadding(BackgroundKt.m1043backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getBackground(), null, 2, null));
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierStatusBarsPadding);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((0 << 3) & 112) << 6) & 896) | 6;
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
            int i3 = ((0 >> 6) & 112) | 6;
            Composer composer3 = $composer;
            ComposerKt.sourceInformationMarkerStart($composer, -1523622, "C206@8906L11,200@8595L358,211@9090L21,209@8975L250:LibraryScreen.kt#t3x8p4");
            TextKt.m3912TextNvy7gAk("音乐库", PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(20), C1301Dp.m8905constructorimpl(46), 0.0f, 0.0f, 12, null), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnBackground(), null, TextUnitKt.getSp(36), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, TextUnitKt.getSp(42), 0, false, 0, 0, null, null, $composer, 1597494, 48, 260008);
            MeloXLibraryPage meloXLibraryPageLibraryScreen$lambda$3 = LibraryScreen$lambda$3($selectedPage$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -1939701359, "CC(remember):LibraryScreen.kt#9igjgp");
            Object objRememberedValue2 = $composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda59
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return LibraryScreenKt.LibraryScreen$lambda$24$0$2$1$0$0($selectedPage$delegate, (MeloXLibraryPage) obj2);
                    }
                };
                $composer.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            MeloXLibrarySegmentedPicker(meloXLibraryPageLibraryScreen$lambda$3, (Function1) objRememberedValue2, PaddingKt.m1806paddingVpY3zN4(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(14), C1301Dp.m8905constructorimpl(24)), $composer, 432, 0);
            if (LibraryScreen$lambda$15($errorMessage$delegate) == null || LibraryScreen$lambda$9($snapshot$delegate) != null) {
                composer = $composer;
                composer3 = composer3;
                if (LibraryScreen$lambda$12($loading$delegate) && LibraryScreen$lambda$9($snapshot$delegate) == null) {
                    $composer.startReplaceGroup(336098);
                    ComposerKt.sourceInformation($composer, "239@10659L148");
                    Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Alignment center = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxSize$default);
                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int i4 = ((((54 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function1 = constructor2;
                        $composer.createNode(function1);
                    } else {
                        function1 = constructor2;
                        $composer.useNode();
                    }
                    Composer composerM5188constructorimpl2 = Updater.m5188constructorimpl($composer);
                    Updater.m5196setimpl(composerM5188constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m5196setimpl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m5196setimpl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m5194reconcileimpl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m5196setimpl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i5 = (i4 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    int i6 = ((54 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -817567690, "C240@10754L27:LibraryScreen.kt#t3x8p4");
                    ProgressIndicatorKt.m3567CircularProgressIndicator4lLiAd8(null, 0L, 0.0f, 0L, 0, 0.0f, $composer, 0, 63);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endReplaceGroup();
                    composer2 = $composer;
                } else {
                    $composer.startReplaceGroup(629017);
                    ComposerKt.sourceInformation($composer, "");
                    NeteaseLibrarySnapshot neteaseLibrarySnapshotLibraryScreen$lambda$9 = LibraryScreen$lambda$9($snapshot$delegate);
                    if (neteaseLibrarySnapshotLibraryScreen$lambda$9 == null) {
                        neteaseLibrarySnapshotLibraryScreen$lambda$9 = new NeteaseLibrarySnapshot(CollectionsKt.emptyList(), CollectionsKt.emptyList(), CollectionsKt.emptyList());
                    }
                    final NeteaseLibrarySnapshot neteaseLibrarySnapshot = neteaseLibrarySnapshotLibraryScreen$lambda$9;
                    switch (WhenMappings.$EnumSwitchMapping$0[LibraryScreen$lambda$3($selectedPage$delegate).ordinal()]) {
                        case 1:
                            composer2 = $composer;
                            composer2.startReplaceGroup(-1939637597);
                            ComposerKt.sourceInformation(composer2, "247@11167L428,255@11641L565,245@11046L1191");
                            List<SearchSong> likedSongs = neteaseLibrarySnapshot.getLikedSongs();
                            ComposerKt.sourceInformationMarkerStart(composer2, -1939634488, "CC(remember):LibraryScreen.kt#9igjgp");
                            boolean zChangedInstance = composer2.changedInstance($context) | composer2.changedInstance(neteaseLibrarySnapshot) | composer2.changed($errorMessage$delegate);
                            Object objRememberedValue3 = composer2.rememberedValue();
                            if (zChangedInstance || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                Object obj2 = new Function1() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda61
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj3) {
                                        return LibraryScreenKt.LibraryScreen$lambda$24$0$2$1$3$0($context, neteaseLibrarySnapshot, $errorMessage$delegate, (SearchSong) obj3);
                                    }
                                };
                                composer2.updateRememberedValue(obj2);
                                objRememberedValue3 = obj2;
                            }
                            Function1 function4 = (Function1) objRememberedValue3;
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerStart(composer2, -1939619183, "CC(remember):LibraryScreen.kt#9igjgp");
                            boolean zChangedInstance2 = composer2.changedInstance(neteaseLibrarySnapshot) | composer2.changedInstance($context) | composer2.changed($errorMessage$delegate);
                            Object objRememberedValue4 = composer2.rememberedValue();
                            if (zChangedInstance2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                Object obj3 = new Function0() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda62
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return LibraryScreenKt.LibraryScreen$lambda$24$0$2$1$4$0(neteaseLibrarySnapshot, $context, $errorMessage$delegate);
                                    }
                                };
                                composer2.updateRememberedValue(obj3);
                                objRememberedValue4 = obj3;
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            MeloXLibrarySongsPage(likedSongs, function4, (Function0) objRememberedValue4, composer2, 0);
                            composer2.endReplaceGroup();
                            Unit unit = Unit.INSTANCE;
                            break;
                        case 2:
                            $composer.startReplaceGroup(-1939598338);
                            ComposerKt.sourceInformation($composer, "269@12434L25,267@12297L418");
                            List<NeteasePlaylistSummary> playlists = neteaseLibrarySnapshot.getPlaylists();
                            ComposerKt.sourceInformationMarkerStart($composer, -1939594347, "CC(remember):LibraryScreen.kt#9igjgp");
                            boolean zChanged2 = $composer.changed($selectedPlaylist$delegate);
                            Object objRememberedValue5 = $composer.rememberedValue();
                            if (zChanged2 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                Object obj4 = new Function1() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda63
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj5) {
                                        return LibraryScreenKt.LibraryScreen$lambda$24$0$2$1$5$0($selectedPlaylist$delegate, (NeteasePlaylistSummary) obj5);
                                    }
                                };
                                $composer.updateRememberedValue(obj4);
                                objRememberedValue5 = obj4;
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            composer2 = $composer;
                            MeloXLibraryPlaylistsPage(playlists, (Function1) objRememberedValue5, $playlistListState, $sharedScope, AnimatedContent, composer2, ($changed << 12) & 57344);
                            composer2.endReplaceGroup();
                            Unit unit2 = Unit.INSTANCE;
                            break;
                        case 3:
                            $composer.startReplaceGroup(-1939582329);
                            ComposerKt.sourceInformation($composer, "277@12895L429,285@13370L567,275@12773L1195");
                            List<SearchSong> recentSongs = neteaseLibrarySnapshot.getRecentSongs();
                            ComposerKt.sourceInformationMarkerStart($composer, -1939579191, "CC(remember):LibraryScreen.kt#9igjgp");
                            boolean zChangedInstance3 = $composer.changedInstance($context) | $composer.changedInstance(neteaseLibrarySnapshot) | $composer.changed($errorMessage$delegate);
                            Object objRememberedValue6 = $composer.rememberedValue();
                            if (zChangedInstance3 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                Object obj5 = new Function1() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda64
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj6) {
                                        return LibraryScreenKt.LibraryScreen$lambda$24$0$2$1$6$0($context, neteaseLibrarySnapshot, $errorMessage$delegate, (SearchSong) obj6);
                                    }
                                };
                                $composer.updateRememberedValue(obj5);
                                objRememberedValue6 = obj5;
                            }
                            Function1 function5 = (Function1) objRememberedValue6;
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerStart($composer, -1939563853, "CC(remember):LibraryScreen.kt#9igjgp");
                            boolean zChangedInstance4 = $composer.changedInstance(neteaseLibrarySnapshot) | $composer.changedInstance($context) | $composer.changed($errorMessage$delegate);
                            Object objRememberedValue7 = $composer.rememberedValue();
                            if (zChangedInstance4 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                                Object obj6 = new Function0() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda65
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return LibraryScreenKt.LibraryScreen$lambda$24$0$2$1$7$0(neteaseLibrarySnapshot, $context, $errorMessage$delegate);
                                    }
                                };
                                $composer.updateRememberedValue(obj6);
                                objRememberedValue7 = obj6;
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            MeloXLibrarySongsPage(recentSongs, function5, (Function0) objRememberedValue7, $composer, 0);
                            $composer.endReplaceGroup();
                            Unit unit3 = Unit.INSTANCE;
                            composer2 = $composer;
                            break;
                        default:
                            $composer.startReplaceGroup(-1939638196);
                            $composer.endReplaceGroup();
                            throw new NoWhenBranchMatchedException();
                    }
                    composer2.endReplaceGroup();
                }
            } else {
                $composer.startReplaceGroup(-958927);
                ComposerKt.sourceInformation($composer, "216@9319L1253");
                composer = $composer;
                Modifier modifierFillMaxSize$default2 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                Alignment center2 = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxSize$default2);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int i7 = ((((54 << 3) & 112) << 6) & 896) | 6;
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
                Composer composerM5188constructorimpl3 = Updater.m5188constructorimpl($composer);
                Updater.m5196setimpl(composerM5188constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m5196setimpl(composerM5188constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m5196setimpl(composerM5188constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m5194reconcileimpl(composerM5188constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m5196setimpl(composerM5188constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i8 = (i7 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                int i9 = ((54 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -1570611910, "C220@9508L1038:LibraryScreen.kt#t3x8p4");
                Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier modifier = Modifier.INSTANCE;
                MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, $composer, ((RendererCapabilities.DECODER_SUPPORT_MASK >> 3) & 14) | ((RendererCapabilities.DECODER_SUPPORT_MASK >> 3) & 112));
                int i10 = (RendererCapabilities.DECODER_SUPPORT_MASK << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap4 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer, modifier);
                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                int i11 = ((i10 << 6) & 896) | 6;
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
                Composer composerM5188constructorimpl4 = Updater.m5188constructorimpl($composer);
                Updater.m5196setimpl(composerM5188constructorimpl4, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m5196setimpl(composerM5188constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m5196setimpl(composerM5188constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m5194reconcileimpl(composerM5188constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m5196setimpl(composerM5188constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                int i12 = (i11 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                int i13 = ((RendererCapabilities.DECODER_SUPPORT_MASK >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 1138819787, "C223@9732L11,221@9601L276,231@10209L37,233@10392L11,226@9910L606:LibraryScreen.kt#t3x8p4");
                String strLibraryScreen$lambda$15 = LibraryScreen$lambda$15($errorMessage$delegate);
                String str = strLibraryScreen$lambda$15 == null ? "" : strLibraryScreen$lambda$15;
                long onBackground = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnBackground();
                TextKt.m3912TextNvy7gAk(str, null, Color.m6066copywmQWz5c(onBackground, (14 & 1) != 0 ? Color.m6070getAlphaimpl(onBackground) : 0.55f, (14 & 2) != 0 ? Color.m6074getRedimpl(onBackground) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(onBackground) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(onBackground) : 0.0f), null, 0L, null, null, null, 0L, null, TextAlign.m8751boximpl(TextAlign.INSTANCE.m8758getCentere0LSkKk()), 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 261114);
                Modifier modifierClip = ClipKt.clip(PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, C1301Dp.m8905constructorimpl(12), 0.0f, 0.0f, 13, null), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(18)));
                ComposerKt.sourceInformationMarkerStart($composer, 2114964682, "CC(remember):LibraryScreen.kt#9igjgp");
                boolean zChangedInstance5 = $composer.changedInstance($scope) | $composer.changed($session) | $composer.changed($loading$delegate) | $composer.changed($errorMessage$delegate) | $composer.changedInstance($client) | $composer.changed($snapshot$delegate) | $composer.changedInstance($cache);
                Object objRememberedValue8 = $composer.rememberedValue();
                if (zChangedInstance5 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                    Object obj7 = new Function0() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda60
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return LibraryScreenKt.LibraryScreen$lambda$24$0$2$1$1$0$0$0($scope, $session, $loading$delegate, $errorMessage$delegate, $client, $cache, $snapshot$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj7);
                    objRememberedValue8 = obj7;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                TextKt.m3912TextNvy7gAk("重新载入", PaddingKt.m1806paddingVpY3zN4(ClickableKt.m1078clickableoSLSa3U$default(modifierClip, false, null, null, null, (Function0) objRememberedValue8, 15, null), C1301Dp.m8905constructorimpl(16), C1301Dp.m8905constructorimpl(8)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), null, 0L, null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1572870, 0, 262072);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                r83.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                r55.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endReplaceGroup();
                composer2 = $composer;
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            $composer.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LibraryScreen$lambda$24$0$2$0$0(MutableState $selectedPlaylist$delegate) {
        $selectedPlaylist$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LibraryScreen$lambda$24$0$2$1$0$0(MutableState $selectedPage$delegate, MeloXLibraryPage it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $selectedPage$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LibraryScreen$lambda$24$0$2$1$1$0$0$0(CoroutineScope $scope, NeteaseSessionStore $session, MutableState $loading$delegate, MutableState $errorMessage$delegate, NeteaseLibraryClient $client, NeteaseLibraryCache $cache, MutableState $snapshot$delegate) {
        BuildersKt__Builders_commonKt.launch$default($scope, null, null, new LibraryScreenKt$LibraryScreen$5$1$3$2$2$1$1$1$1($session, $loading$delegate, $errorMessage$delegate, $client, $cache, $snapshot$delegate, null), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LibraryScreen$lambda$24$0$2$1$3$0(Context $context, NeteaseLibrarySnapshot $data, final MutableState $errorMessage$delegate, SearchSong song) {
        Intrinsics.checkNotNullParameter(song, "song");
        PlaybackCommands.INSTANCE.playQueue($context, $data.getLikedSongs(), song.getId(), new Function1() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LibraryScreenKt.LibraryScreen$lambda$24$0$2$1$3$0$0($errorMessage$delegate, (Throwable) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LibraryScreen$lambda$24$0$2$1$3$0$0(MutableState $errorMessage$delegate, Throwable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String message = it.getMessage();
        if (message == null) {
            message = "播放失败";
        }
        $errorMessage$delegate.setValue(message);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LibraryScreen$lambda$24$0$2$1$4$0(NeteaseLibrarySnapshot $data, Context $context, final MutableState $errorMessage$delegate) {
        SearchSong searchSong = (SearchSong) CollectionsKt.firstOrNull((List) $data.getLikedSongs());
        if (searchSong != null) {
            PlaybackCommands.INSTANCE.playQueue($context, $data.getLikedSongs(), searchSong.getId(), new Function1() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return LibraryScreenKt.LibraryScreen$lambda$24$0$2$1$4$0$0$0($errorMessage$delegate, (Throwable) obj);
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LibraryScreen$lambda$24$0$2$1$4$0$0$0(MutableState $errorMessage$delegate, Throwable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String message = it.getMessage();
        if (message == null) {
            message = "播放失败";
        }
        $errorMessage$delegate.setValue(message);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LibraryScreen$lambda$24$0$2$1$5$0(MutableState $selectedPlaylist$delegate, NeteasePlaylistSummary it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $selectedPlaylist$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LibraryScreen$lambda$24$0$2$1$6$0(Context $context, NeteaseLibrarySnapshot $data, final MutableState $errorMessage$delegate, SearchSong song) {
        Intrinsics.checkNotNullParameter(song, "song");
        PlaybackCommands.INSTANCE.playQueue($context, $data.getRecentSongs(), song.getId(), new Function1() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda54
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LibraryScreenKt.LibraryScreen$lambda$24$0$2$1$6$0$0($errorMessage$delegate, (Throwable) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LibraryScreen$lambda$24$0$2$1$6$0$0(MutableState $errorMessage$delegate, Throwable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String message = it.getMessage();
        if (message == null) {
            message = "播放失败";
        }
        $errorMessage$delegate.setValue(message);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LibraryScreen$lambda$24$0$2$1$7$0(NeteaseLibrarySnapshot $data, Context $context, final MutableState $errorMessage$delegate) {
        SearchSong searchSong = (SearchSong) CollectionsKt.firstOrNull((List) $data.getRecentSongs());
        if (searchSong != null) {
            PlaybackCommands.INSTANCE.playQueue($context, $data.getRecentSongs(), searchSong.getId(), new Function1() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda37
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return LibraryScreenKt.LibraryScreen$lambda$24$0$2$1$7$0$0$0($errorMessage$delegate, (Throwable) obj);
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LibraryScreen$lambda$24$0$2$1$7$0$0$0(MutableState $errorMessage$delegate, Throwable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String message = it.getMessage();
        if (message == null) {
            message = "播放失败";
        }
        $errorMessage$delegate.setValue(message);
        return Unit.INSTANCE;
    }

    private static final void MeloXLibraryLoginUnavailable(Function0<Unit> function0, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function2;
        final Function0<Unit> function3 = function0;
        Composer $composer3 = $composer.startRestartGroup(553317552);
        ComposerKt.sourceInformation($composer3, "C(MeloXLibraryLoginUnavailable)N(onLogin)310@14257L11,307@14156L1965:LibraryScreen.kt#t3x8p4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (!$composer3.shouldExecute(($dirty2 & 3) != 2, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(553317552, $dirty2, -1, "com.lladlam.melox.ui.library.MeloXLibraryLoginUnavailable (LibraryScreen.kt:306)");
            }
            Modifier modifierM1807paddingVpY3zN4$default = PaddingKt.m1807paddingVpY3zN4$default(WindowInsetsPadding_androidKt.statusBarsPadding(BackgroundKt.m1043backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getBackground(), null, 2, null)), C1301Dp.m8905constructorimpl(20), 0.0f, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1807paddingVpY3zN4$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function1 = constructor;
                $composer3.createNode(function1);
            } else {
                function1 = constructor;
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
            ComposerKt.sourceInformationMarkerStart($composer3, -687250278, "C314@14372L192,321@14573L1542:LibraryScreen.kt#t3x8p4");
            TextKt.m3912TextNvy7gAk("音乐库", PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, C1301Dp.m8905constructorimpl(46), 0.0f, 0.0f, 13, null), 0L, null, TextUnitKt.getSp(36), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, TextUnitKt.getSp(42), 0, false, 0, 0, null, null, $composer3, 1597494, 48, 260012);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(ColumnScope.weight$default(columnScope, Modifier.INSTANCE, 1.0f, false, 2, null), 0.0f, 1, null);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer3.getCurrentCompositionLocalMap();
            $composer2 = $composer3;
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer3, modifierFillMaxWidth$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor2);
            } else {
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl2 = Updater.m5188constructorimpl($composer3);
            Updater.m5196setimpl(composerM5188constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i6 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -714956834, "C327@14744L1361:LibraryScreen.kt#t3x8p4");
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, $composer3, ((RendererCapabilities.DECODER_SUPPORT_MASK >> 3) & 14) | ((RendererCapabilities.DECODER_SUPPORT_MASK >> 3) & 112));
            int i7 = (RendererCapabilities.DECODER_SUPPORT_MASK << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap3 = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer3, modifier);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i8 = ((i7 << 6) & 896) | 6;
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
            Composer composerM5188constructorimpl3 = Updater.m5188constructorimpl($composer3);
            Updater.m5196setimpl(composerM5188constructorimpl3, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i9 = (i8 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i10 = ((RendererCapabilities.DECODER_SUPPORT_MASK >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 1788933199, "C328@14821L64,332@15054L11,329@14902L265,340@15435L11,341@15513L11,338@15304L275,345@15732L11,335@15184L907:LibraryScreen.kt#t3x8p4");
            TextKt.m3912TextNvy7gAk("需要登录", null, 0L, null, TextUnitKt.getSp(22), null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 1597446, 0, 262062);
            Modifier modifierM1809paddingqDBjuR0$default = PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, C1301Dp.m8905constructorimpl(8), 0.0f, 0.0f, 13, null);
            long onBackground = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnBackground();
            TextKt.m3912TextNvy7gAk("登录后可读取收藏歌曲、歌单和播放记录。", modifierM1809paddingqDBjuR0$default, Color.m6066copywmQWz5c(onBackground, (14 & 1) != 0 ? Color.m6070getAlphaimpl(onBackground) : 0.5f, (14 & 2) != 0 ? Color.m6074getRedimpl(onBackground) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(onBackground) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(onBackground) : 0.0f), null, 0L, null, null, null, 0L, null, TextAlign.m8751boximpl(TextAlign.INSTANCE.m8758getCentere0LSkKk()), 0L, 0, false, 0, 0, null, null, $composer3, 54, 0, 261112);
            Modifier modifierM1809paddingqDBjuR0$default2 = PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, C1301Dp.m8905constructorimpl(18), 0.0f, 0.0f, 13, null);
            RoundedCornerShape roundedCornerShapeM2135RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(18));
            long primary = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getPrimary();
            long primary2 = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getPrimary();
            function3 = function0;
            SurfaceKt.m3769SurfaceT9BRK9s(ClickableKt.m1078clickableoSLSa3U$default(MeloXBackdropComponentsKt.m9633meloXLiquidButtonNsDo4u0(modifierM1809paddingqDBjuR0$default2, roundedCornerShapeM2135RoundedCornerShape0680j_4, false, primary, Color.m6066copywmQWz5c(primary2, (14 & 1) != 0 ? Color.m6070getAlphaimpl(primary2) : 0.62f, (14 & 2) != 0 ? Color.m6074getRedimpl(primary2) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(primary2) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(primary2) : 0.0f), 0.0f, 0.0f, 0.0f, $composer3, 6, 114), false, null, null, null, function3, 15, null), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(18)), MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getPrimary(), 0L, 0.0f, 0.0f, null, ComposableSingletons$LibraryScreenKt.INSTANCE.m9635getLambda$2107823229$app(), $composer3, 12582912, 120);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LibraryScreenKt.MeloXLibraryLoginUnavailable$lambda$1(function3, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void MeloXLibrarySegmentedPicker(final MeloXLibraryPage selected, final Function1<? super MeloXLibraryPage, Unit> function1, Modifier modifier, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        final Modifier modifier3;
        Modifier.Companion modifier4;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function2;
        MeloXLibraryPage meloXLibraryPage = selected;
        final Function1<? super MeloXLibraryPage, Unit> function3 = function1;
        Composer $composer2 = $composer.startRestartGroup(1869510754);
        ComposerKt.sourceInformation($composer2, "C(MeloXLibrarySegmentedPicker)N(selected,onSelected,modifier)372@16553L11,373@16639L11,370@16443L256,365@16297L1585:LibraryScreen.kt#t3x8p4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(meloXLibraryPage.ordinal()) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= RendererCapabilities.DECODER_SUPPORT_MASK;
            modifier2 = modifier;
        } else if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1869510754, $dirty2, -1, "com.lladlam.melox.ui.library.MeloXLibrarySegmentedPicker (LibraryScreen.kt:364)");
            }
            Modifier modifierClip = ClipKt.clip(SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(modifier4, 0.0f, 1, null), C1301Dp.m8905constructorimpl(30)), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(16)));
            RoundedCornerShape roundedCornerShapeM2135RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(16));
            long surface = MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getSurface();
            long jM6066copywmQWz5c = Color.m6066copywmQWz5c(surface, (14 & 1) != 0 ? Color.m6070getAlphaimpl(surface) : 0.1f, (14 & 2) != 0 ? Color.m6074getRedimpl(surface) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(surface) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(surface) : 0.0f);
            long onBackground = MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getOnBackground();
            Modifier modifierM9632meloXLiquidBottomBar9z6LAg8 = MeloXBackdropComponentsKt.m9632meloXLiquidBottomBar9z6LAg8(modifierClip, roundedCornerShapeM2135RoundedCornerShape0680j_4, jM6066copywmQWz5c, Color.m6066copywmQWz5c(onBackground, (14 & 1) != 0 ? Color.m6070getAlphaimpl(onBackground) : 0.055f, (14 & 2) != 0 ? Color.m6074getRedimpl(onBackground) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(onBackground) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(onBackground) : 0.0f), $composer2, 0);
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Composer composer = $composer2;
            ComposerKt.sourceInformationMarkerStart(composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, composer, ((RendererCapabilities.DECODER_SUPPORT_MASK >> 3) & 14) | ((RendererCapabilities.DECODER_SUPPORT_MASK >> 3) & 112));
            int i3 = (RendererCapabilities.DECODER_SUPPORT_MASK << 3) & 112;
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            Modifier modifier5 = modifier4;
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierM9632meloXLiquidBottomBar9z6LAg8);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((i3 << 6) & 896) | 6;
            String str = "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp";
            ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                function0 = constructor;
                composer.createNode(function0);
            } else {
                function0 = constructor;
                composer.useNode();
            }
            Composer composerM5188constructorimpl = Updater.m5188constructorimpl(composer);
            MeasurePolicy measurePolicy = measurePolicyRowMeasurePolicy;
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            int i6 = 0;
            ComposerKt.sourceInformationMarkerStart(composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScope rowScope = RowScopeInstance.INSTANCE;
            int i7 = ((RendererCapabilities.DECODER_SUPPORT_MASK >> 6) & 112) | 6;
            RowScope rowScope2 = rowScope;
            Composer composer2 = composer;
            ComposerKt.sourceInformationMarkerStart(composer2, -2086438706, "C:LibraryScreen.kt#t3x8p4");
            composer2.startReplaceGroup(-621493028);
            ComposerKt.sourceInformation(composer2, "*388@17280L11,385@17104L238,391@17419L39,393@17525L20,379@16874L992");
            Iterable<MeloXLibraryPage> entries = MeloXLibraryPage.getEntries();
            int i8 = 0;
            for (final MeloXLibraryPage meloXLibraryPage2 : entries) {
                Iterable iterable = entries;
                boolean z = meloXLibraryPage2 == meloXLibraryPage;
                int i9 = i8;
                int i10 = i6;
                MeasurePolicy measurePolicy2 = measurePolicy;
                Composer composer3 = composer;
                Modifier modifierClip2 = ClipKt.clip(PaddingKt.m1807paddingVpY3zN4$default(SizeKt.m1858height3ABfNKs(RowScope.weight$default(rowScope2, Modifier.INSTANCE, 1.0f, false, 2, null), C1301Dp.m8905constructorimpl(28)), C1301Dp.m8905constructorimpl(1), 0.0f, 2, null), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(15)));
                RoundedCornerShape roundedCornerShapeM2135RoundedCornerShape0680j_5 = RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(15));
                long surface2 = MaterialTheme.INSTANCE.getColorScheme(composer2, MaterialTheme.$stable).getSurface();
                Modifier modifierM9634meloXLiquidTabSelectionBx497Mc = MeloXBackdropComponentsKt.m9634meloXLiquidTabSelectionBx497Mc(modifierClip2, roundedCornerShapeM2135RoundedCornerShape0680j_5, z, Color.m6066copywmQWz5c(surface2, (14 & 1) != 0 ? Color.m6070getAlphaimpl(surface2) : 0.74f, (14 & 2) != 0 ? Color.m6074getRedimpl(surface2) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(surface2) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(surface2) : 0.0f), composer2, 0);
                ComposerKt.sourceInformationMarkerStart(composer2, -1536737639, "CC(remember):LibraryScreen.kt#9igjgp");
                Composer composer4 = composer2;
                Object objRememberedValue = composer4.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object objMutableInteractionSource = InteractionSourceKt.MutableInteractionSource();
                    composer4.updateRememberedValue(objMutableInteractionSource);
                    objRememberedValue = objMutableInteractionSource;
                }
                MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerStart(composer2, -1536734266, "CC(remember):LibraryScreen.kt#9igjgp");
                boolean zChanged = $composer2.changed(meloXLibraryPage2.ordinal()) | (($dirty2 & 112) == 32);
                Composer composer5 = composer2;
                Object objRememberedValue2 = composer5.rememberedValue();
                if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function0() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return LibraryScreenKt.MeloXLibrarySegmentedPicker$lambda$0$0$1$0(function3, meloXLibraryPage2);
                        }
                    };
                    composer5.updateRememberedValue(obj);
                    objRememberedValue2 = obj;
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                Modifier modifierM1073clickableO2vRcR0 = ClickableKt.m1073clickableO2vRcR0(modifierM9634meloXLiquidTabSelectionBx497Mc, mutableInteractionSource, null, (24 & 4) != 0, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : null, (Function0) objRememberedValue2);
                Alignment center = Alignment.INSTANCE.getCenter();
                Composer composer6 = composer2;
                ComposerKt.sourceInformationMarkerStart(composer6, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                ComposerKt.sourceInformationMarkerStart(composer6, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer6, 0));
                CompositionLocalMap currentCompositionLocalMap2 = composer6.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer6, modifierM1073clickableO2vRcR0);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i11 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer6, -553112988, str);
                if (!(composer6.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer6.startReusableNode();
                if (composer6.getInserting()) {
                    function2 = constructor2;
                    composer6.createNode(function2);
                } else {
                    function2 = constructor2;
                    composer6.useNode();
                }
                Composer composerM5188constructorimpl2 = Updater.m5188constructorimpl(composer6);
                Updater.m5196setimpl(composerM5188constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m5196setimpl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m5196setimpl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m5194reconcileimpl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m5196setimpl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i12 = (i11 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer6, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i13 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer6, 2124478939, "C400@17809L11,396@17632L220:LibraryScreen.kt#t3x8p4");
                TextKt.m3912TextNvy7gAk(meloXLibraryPage2.getTitle(), null, MaterialTheme.INSTANCE.getColorScheme(composer6, MaterialTheme.$stable).getOnBackground(), null, TextUnitKt.getSp(13), null, FontWeight.INSTANCE.getMedium(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer6, 1597440, 0, 262058);
                ComposerKt.sourceInformationMarkerEnd(composer6);
                ComposerKt.sourceInformationMarkerEnd(composer6);
                composer6.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer6);
                ComposerKt.sourceInformationMarkerEnd(composer6);
                ComposerKt.sourceInformationMarkerEnd(composer6);
                meloXLibraryPage = selected;
                function3 = function1;
                composer = composer3;
                measurePolicy = measurePolicy2;
                composer2 = composer2;
                entries = iterable;
                i8 = i9;
                i6 = i10;
                str = str;
            }
            Composer composer7 = composer2;
            composer7.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer7);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return LibraryScreenKt.MeloXLibrarySegmentedPicker$lambda$1(selected, function1, modifier3, $changed, i, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXLibrarySegmentedPicker$lambda$0$0$1$0(Function1 $onSelected, MeloXLibraryPage $page) {
        $onSelected.invoke($page);
        return Unit.INSTANCE;
    }

    private static final void MeloXLibrarySongsPage(final List<SearchSong> list, final Function1<? super SearchSong, Unit> function1, final Function0<Unit> function0, Composer $composer, final int $changed) {
        Function0<ComposeUiNode> function2;
        Composer $composer2 = $composer.startRestartGroup(-1045090453);
        ComposerKt.sourceInformation($composer2, "C(MeloXLibrarySongsPage)N(songs,onPlay,onPlayAll)427@18478L297,424@18339L436:LibraryScreen.kt#t3x8p4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(list) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 32 : 16;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 256 : 128;
        }
        if ($composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1045090453, $dirty, -1, "com.lladlam.melox.ui.library.MeloXLibrarySongsPage (LibraryScreen.kt:412)");
            }
            if (list.isEmpty()) {
                $composer2.startReplaceGroup(-71603074);
                ComposerKt.sourceInformation($composer2, "414@18062L250");
                Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                Alignment center = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierFillMaxSize$default);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((54 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function2 = constructor;
                    $composer2.createNode(function2);
                } else {
                    function2 = constructor;
                    $composer2.useNode();
                }
                Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer2);
                Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i3 = ((54 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 1648762840, "C417@18209L11,415@18141L161:LibraryScreen.kt#t3x8p4");
                long onBackground = MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getOnBackground();
                TextKt.m3912TextNvy7gAk("暂无歌曲", null, Color.m6066copywmQWz5c(onBackground, (14 & 1) != 0 ? Color.m6070getAlphaimpl(onBackground) : 0.48f, (14 & 2) != 0 ? Color.m6074getRedimpl(onBackground) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(onBackground) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(onBackground) : 0.0f), null, TextUnitKt.getSp(17), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24582, 0, 262122);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda18
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return LibraryScreenKt.MeloXLibrarySongsPage$lambda$1(list, function1, function0, $changed, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                    return;
                }
                return;
            }
            $composer2.startReplaceGroup(-71333033);
            $composer2.endReplaceGroup();
            Modifier modifierFillMaxSize$default2 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            PaddingValues paddingValuesM1802PaddingValuesa9UjIt4$default = PaddingKt.m1802PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, MeloXLayoutKt.getMeloXBottomContentClearance(), 7, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 690440532, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean zChangedInstance = (($dirty & 896) == 256) | $composer2.changedInstance(list) | (($dirty & 112) == 32);
            Object objRememberedValue = $composer2.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda19
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return LibraryScreenKt.MeloXLibrarySongsPage$lambda$2$0(list, function0, function1, (LazyListScope) obj2);
                    }
                };
                $composer2.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LazyDslKt.LazyColumn(modifierFillMaxSize$default2, null, paddingValuesM1802PaddingValuesa9UjIt4$default, false, null, null, null, false, null, (Function1) objRememberedValue, $composer2, 390, 506);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup2 = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup2 != null) {
            scopeUpdateScopeEndRestartGroup2.updateScope(new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda20
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return LibraryScreenKt.MeloXLibrarySongsPage$lambda$3(list, function1, function0, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXLibrarySongsPage$lambda$2$0(final List $songs, final Function0 $onPlayAll, final Function1 $onPlay, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1854204106, true, new Function3() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return LibraryScreenKt.MeloXLibrarySongsPage$lambda$2$0$0($onPlayAll, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        final Function1 function1 = new Function1() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LibraryScreenKt.MeloXLibrarySongsPage$lambda$2$0$1((SearchSong) obj);
            }
        };
        final Function1 function2 = new Function1() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$MeloXLibrarySongsPage$lambda$2$0$$inlined$items$default$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                return invoke((SearchSong) p1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Void invoke(SearchSong searchSong) {
                return null;
            }
        };
        LazyColumn.items($songs.size(), new Function1<Integer, Object>() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$MeloXLibrarySongsPage$lambda$2$0$$inlined$items$default$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int index) {
                return function1.invoke($songs.get(index));
            }
        }, new Function1<Integer, Object>() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$MeloXLibrarySongsPage$lambda$2$0$$inlined$items$default$3
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int index) {
                return function2.invoke($songs.get(index));
            }
        }, ComposableLambdaKt.composableLambdaInstance(802480018, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$MeloXLibrarySongsPage$lambda$2$0$$inlined$items$default$4
            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope $this$items, int it, Composer $composer, int $changed) {
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
                final SearchSong searchSong = (SearchSong) $songs.get(it);
                $composer.startReplaceGroup(-422682736);
                ComposerKt.sourceInformation($composer, "CN(song)*433@18695L16,433@18651L61,434@18725L34:LibraryScreen.kt#t3x8p4");
                ComposerKt.sourceInformationMarkerStart($composer, -2091843593, "CC(remember):LibraryScreen.kt#9igjgp");
                boolean zChanged = $composer.changed($onPlay) | ((((i & 112) ^ 48) > 32 && $composer.changed(searchSong)) || (i & 48) == 32);
                Object objRememberedValue = $composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    final Function1 function3 = $onPlay;
                    Object obj = (Function0) new Function0<Unit>() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$MeloXLibrarySongsPage$3$1$3$1$1
                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            function3.invoke(searchSong);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                LibraryScreenKt.MeloXLibraryTrackRow(searchSong, (Function0) objRememberedValue, $composer, (i >> 3) & 14);
                LibraryScreenKt.m9652MeloXInsetDivider8Feqmps(C1301Dp.m8905constructorimpl(68), $composer, 6);
                $composer.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXLibrarySongsPage$lambda$2$0$0(Function0 $onPlayAll, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C429@18507L26,430@18546L34:LibraryScreen.kt#t3x8p4");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1854204106, $changed, -1, "com.lladlam.melox.ui.library.MeloXLibrarySongsPage.<anonymous>.<anonymous>.<anonymous> (LibraryScreen.kt:429)");
            }
            MeloXPlayAllRow($onPlayAll, $composer, 0);
            m9652MeloXInsetDivider8Feqmps(C1301Dp.m8905constructorimpl(68), $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object MeloXLibrarySongsPage$lambda$2$0$1(SearchSong it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Long.valueOf(it.getId());
    }

    private static final void MeloXPlayAllRow(final Function0<Unit> function0, Composer $composer, final int $changed) {
        Function0<ComposeUiNode> function1;
        Composer $composer2 = $composer.startRestartGroup(1997518457);
        ComposerKt.sourceInformation($composer2, "C(MeloXPlayAllRow)N(onClick)441@18846L613:LibraryScreen.kt#t3x8p4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if ($composer2.shouldExecute(($dirty2 & 3) != 2, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1997518457, $dirty2, -1, "com.lladlam.melox.ui.library.MeloXPlayAllRow (LibraryScreen.kt:440)");
            }
            Modifier modifierM1809paddingqDBjuR0$default = PaddingKt.m1809paddingqDBjuR0$default(ClickableKt.m1078clickableoSLSa3U$default(SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1301Dp.m8905constructorimpl(58)), false, null, null, null, function0, 15, null), C1301Dp.m8905constructorimpl(20), 0.0f, C1301Dp.m8905constructorimpl(18), 0.0f, 10, null);
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.Horizontal horizontalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(C1301Dp.m8905constructorimpl(16));
            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalM1497spacedBy0680j_4, centerVertically, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierM1809paddingqDBjuR0$default);
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
            Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer2);
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i3 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1538933344, "C450@19159L109,458@19418L11,454@19277L176:LibraryScreen.kt#t3x8p4");
            m9653MeloXPlayGlyphRPmYEkk(SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(28)), ColorKt.Color(4294914375L), $composer2, 54);
            TextKt.m3912TextNvy7gAk("播放全部", null, MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getOnBackground(), null, TextUnitKt.getSp(17), null, FontWeight.INSTANCE.getNormal(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 262058);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda43
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LibraryScreenKt.MeloXPlayAllRow$lambda$1(function0, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void MeloXLibraryTrackRow(final SearchSong song, final Function0<Unit> function0, Composer $composer, final int $changed) {
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function2;
        Composer $composer2 = $composer.startRestartGroup(945197091);
        ComposerKt.sourceInformation($composer2, "C(MeloXLibraryTrackRow)N(song,onClick)468@19564L1551:LibraryScreen.kt#t3x8p4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(song) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if ($composer2.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(945197091, $dirty2, -1, "com.lladlam.melox.ui.library.MeloXLibraryTrackRow (LibraryScreen.kt:467)");
            }
            Modifier modifierM1809paddingqDBjuR0$default = PaddingKt.m1809paddingqDBjuR0$default(ClickableKt.m1078clickableoSLSa3U$default(SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1301Dp.m8905constructorimpl(66)), false, null, null, null, function0, 15, null), C1301Dp.m8905constructorimpl(18), 0.0f, C1301Dp.m8905constructorimpl(18), 0.0f, 10, null);
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.Horizontal horizontalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(C1301Dp.m8905constructorimpl(12));
            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalM1497spacedBy0680j_4, centerVertically, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierM1809paddingqDBjuR0$default);
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
            Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer2);
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i3 = ((432 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 700500492, "C477@19877L253,485@20139L718:LibraryScreen.kt#t3x8p4");
            SingletonAsyncImageKt.m9399AsyncImage10Xjiaw(song.getArtworkUrl(), null, ClipKt.clip(SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(44)), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(6))), null, null, null, ContentScale.INSTANCE.getCrop(), 0.0f, null, 0, false, $composer2, 1572912, 0, 1976);
            Modifier modifierWeight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
            Arrangement.Vertical verticalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(C1301Dp.m8905constructorimpl(3));
            ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM1497spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer2, modifierWeight$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((48 << 3) & 112) << 6) & 896) | 6;
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
            Composer composerM5188constructorimpl2 = Updater.m5188constructorimpl($composer2);
            Updater.m5196setimpl(composerM5188constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i6 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 171237036, "C495@20505L11,489@20277L267,503@20788L11,497@20557L290:LibraryScreen.kt#t3x8p4");
            TextKt.m3912TextNvy7gAk(song.getName(), null, MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getOnBackground(), null, TextUnitKt.getSp(17), null, null, null, 0L, null, null, TextUnitKt.getSp(21), TextOverflow.INSTANCE.m8816getEllipsisgIe3tQ8(), false, 1, 0, null, null, $composer2, 24576, 25008, 239594);
            String artists = song.getArtists();
            long sp = TextUnitKt.getSp(13);
            long sp2 = TextUnitKt.getSp(16);
            int iM8816getEllipsisgIe3tQ8 = TextOverflow.INSTANCE.m8816getEllipsisgIe3tQ8();
            long onBackground = MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getOnBackground();
            TextKt.m3912TextNvy7gAk(artists, null, Color.m6066copywmQWz5c(onBackground, (14 & 1) != 0 ? Color.m6070getAlphaimpl(onBackground) : 0.46f, (14 & 2) != 0 ? Color.m6074getRedimpl(onBackground) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(onBackground) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(onBackground) : 0.0f), null, sp, null, null, null, 0L, null, null, sp2, iM8816getEllipsisgIe3tQ8, false, 1, 0, null, null, $composer2, 24576, 25008, 239594);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (song.getDurationMs() > 0) {
                $composer2.startReplaceGroup(701489050);
                ComposerKt.sourceInformation($composer2, "510@21040L11,507@20906L193");
                String duration = formatDuration(song.getDurationMs());
                long sp3 = TextUnitKt.getSp(13);
                long onBackground2 = MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getOnBackground();
                TextKt.m3912TextNvy7gAk(duration, null, Color.m6066copywmQWz5c(onBackground2, (14 & 1) != 0 ? Color.m6070getAlphaimpl(onBackground2) : 0.46f, (14 & 2) != 0 ? Color.m6074getRedimpl(onBackground2) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(onBackground2) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(onBackground2) : 0.0f), null, sp3, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24576, 0, 262122);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(701684443);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LibraryScreenKt.MeloXLibraryTrackRow$lambda$1(song, function0, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void MeloXLibraryPlaylistsPage(final List<NeteasePlaylistSummary> list, final Function1<? super NeteasePlaylistSummary, Unit> function1, final LazyListState listState, final SharedTransitionScope sharedTransitionScope, final AnimatedVisibilityScope animatedVisibilityScope, Composer $composer, final int $changed) {
        LazyListState lazyListState;
        final List<NeteasePlaylistSummary> list2;
        Function0<ComposeUiNode> function0;
        Composer $composer2 = $composer.startRestartGroup(14499216);
        ComposerKt.sourceInformation($composer2, "C(MeloXLibraryPlaylistsPage)N(playlists,onPlaylistClick,listState,sharedTransitionScope,animatedVisibilityScope)540@21940L2375,536@21774L2541:LibraryScreen.kt#t3x8p4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(list) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 32 : 16;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            lazyListState = listState;
            $dirty |= $composer2.changed(lazyListState) ? 256 : 128;
        } else {
            lazyListState = listState;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(sharedTransitionScope) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(animatedVisibilityScope) ? 16384 : 8192;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 9363) != 9362, $dirty2 & 1)) {
            list2 = list;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(14499216, $dirty2, -1, "com.lladlam.melox.ui.library.MeloXLibraryPlaylistsPage (LibraryScreen.kt:524)");
            }
            if (list.isEmpty()) {
                $composer2.startReplaceGroup(-1845248618);
                ComposerKt.sourceInformation($composer2, "526@21494L253");
                Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                Alignment center = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierFillMaxSize$default);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((54 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function0 = constructor;
                    $composer2.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer2.useNode();
                }
                Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer2);
                Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i3 = ((54 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 1422242096, "C529@21644L11,527@21573L164:LibraryScreen.kt#t3x8p4");
                long onBackground = MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getOnBackground();
                TextKt.m3912TextNvy7gAk("还没有收藏歌单", null, Color.m6066copywmQWz5c(onBackground, (14 & 1) != 0 ? Color.m6070getAlphaimpl(onBackground) : 0.48f, (14 & 2) != 0 ? Color.m6074getRedimpl(onBackground) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(onBackground) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(onBackground) : 0.0f), null, TextUnitKt.getSp(17), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24582, 0, 262122);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final LazyListState lazyListState2 = lazyListState;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda39
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return LibraryScreenKt.MeloXLibraryPlaylistsPage$lambda$1(list, function1, lazyListState2, sharedTransitionScope, animatedVisibilityScope, $changed, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                    return;
                }
                return;
            }
            list2 = list;
            $composer2.startReplaceGroup(-1844975694);
            $composer2.endReplaceGroup();
            Modifier modifierFillMaxSize$default2 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            PaddingValues paddingValuesM1802PaddingValuesa9UjIt4$default = PaddingKt.m1802PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, MeloXLayoutKt.getMeloXBottomContentClearance(), 7, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 1048871191, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean zChangedInstance = $composer2.changedInstance(list2) | (($dirty2 & 112) == 32) | (($dirty2 & 7168) == 2048) | $composer2.changedInstance(animatedVisibilityScope);
            Object objRememberedValue = $composer2.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda40
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return LibraryScreenKt.MeloXLibraryPlaylistsPage$lambda$2$0(list2, function1, sharedTransitionScope, animatedVisibilityScope, (LazyListScope) obj2);
                    }
                };
                $composer2.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LazyDslKt.LazyColumn(modifierFillMaxSize$default2, listState, paddingValuesM1802PaddingValuesa9UjIt4$default, false, null, null, null, false, null, (Function1) objRememberedValue, $composer2, (($dirty2 >> 3) & 112) | 390, 504);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup2 = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup2 != null) {
            final List<NeteasePlaylistSummary> list3 = list2;
            scopeUpdateScopeEndRestartGroup2.updateScope(new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda41
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return LibraryScreenKt.MeloXLibraryPlaylistsPage$lambda$3(list3, function1, listState, sharedTransitionScope, animatedVisibilityScope, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXLibraryPlaylistsPage$lambda$2$0(final List $playlists, final Function1 $onPlaylistClick, final SharedTransitionScope $sharedTransitionScope, final AnimatedVisibilityScope $animatedVisibilityScope, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$LibraryScreenKt.INSTANCE.getLambda$162496283$app(), 3, null);
        final Function1 function1 = new Function1() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda34
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LibraryScreenKt.MeloXLibraryPlaylistsPage$lambda$2$0$0((NeteasePlaylistSummary) obj);
            }
        };
        final Function1 function2 = new Function1() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$MeloXLibraryPlaylistsPage$lambda$2$0$$inlined$items$default$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                return invoke((NeteasePlaylistSummary) p1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Void invoke(NeteasePlaylistSummary neteasePlaylistSummary) {
                return null;
            }
        };
        LazyColumn.items($playlists.size(), new Function1<Integer, Object>() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$MeloXLibraryPlaylistsPage$lambda$2$0$$inlined$items$default$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int index) {
                return function1.invoke($playlists.get(index));
            }
        }, new Function1<Integer, Object>() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$MeloXLibraryPlaylistsPage$lambda$2$0$$inlined$items$default$3
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int index) {
                return function2.invoke($playlists.get(index));
            }
        }, ComposableLambdaKt.composableLambdaInstance(802480018, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$MeloXLibraryPlaylistsPage$lambda$2$0$$inlined$items$default$4
            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                return Unit.INSTANCE;
            }

            /* JADX WARN: Code duplicated, block: B:44:0x0180  */
            /* JADX WARN: Code duplicated, block: B:47:0x018c  */
            /* JADX WARN: Code duplicated, block: B:48:0x0192  */
            /* JADX WARN: Code duplicated, block: B:51:0x032a  */
            /* JADX WARN: Code duplicated, block: B:54:0x0336  */
            /* JADX WARN: Code duplicated, block: B:55:0x033c  */
            /* JADX WARN: Code duplicated, block: B:58:0x04d1  */
            /* JADX WARN: Code duplicated, block: B:61:? A[RETURN, SYNTHETIC] */
            public final void invoke(LazyItemScope $this$items, int it, Composer $composer, int $changed) {
                int i;
                Function0<ComposeUiNode> constructor;
                Function0<ComposeUiNode> function0;
                Function0<ComposeUiNode> constructor2;
                Function0<ComposeUiNode> function3;
                ComposerKt.sourceInformation($composer, "CN(it)178@8834L22:LazyDsl.kt#428nma");
                int $dirty = $changed;
                if (($changed & 6) == 0) {
                    $dirty |= $composer.changed($this$items) ? 4 : 2;
                }
                if (($changed & 48) == 0) {
                    $dirty |= $composer.changed(it) ? 32 : 16;
                }
                boolean z = true;
                if (!$composer.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
                    $composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(802480018, $dirty, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:178)");
                }
                int i2 = $dirty & 14;
                final NeteasePlaylistSummary neteasePlaylistSummary = (NeteasePlaylistSummary) $playlists.get(it);
                $composer.startReplaceGroup(1533409973);
                ComposerKt.sourceInformation($composer, "CN(playlist)*553@22409L29,550@22301L1951,597@24265L34:LibraryScreen.kt#t3x8p4");
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, 326560989, "CC(remember):LibraryScreen.kt#9igjgp");
                boolean zChanged = $composer.changed($onPlaylistClick);
                if ((((i2 & 112) ^ 48) <= 32 || !$composer.changed(neteasePlaylistSummary)) && (i2 & 48) != 32) {
                    z = false;
                }
                boolean z2 = zChanged | z;
                Object objRememberedValue = $composer.rememberedValue();
                if (!z2) {
                    i = 48;
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Modifier modifierM1806paddingVpY3zN4 = PaddingKt.m1806paddingVpY3zN4(ClickableKt.m1078clickableoSLSa3U$default(modifierFillMaxWidth$default, false, null, null, null, (Function0) objRememberedValue, 15, null), C1301Dp.m8905constructorimpl(18), C1301Dp.m8905constructorimpl(6));
                    Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                    Arrangement.Horizontal horizontalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(C1301Dp.m8905constructorimpl(12));
                    ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalM1497spacedBy0680j_4, centerVertically, $composer, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM1806paddingVpY3zN4);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int i3 = ((((432 << 3) & 112) << 6) & 896) | 6;
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
                    Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                    int i4 = (i3 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    int i5 = ((432 >> 6) & 112) | 6;
                    RowScope rowScope = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer, -82723680, "C567@23078L324,575@23419L628,594@24175L11,591@24064L174:LibraryScreen.kt#t3x8p4");
                    $composer.startReplaceGroup(967162509);
                    ComposerKt.sourceInformation($composer, "*560@22818L126");
                    SharedTransitionScope sharedTransitionScope = $sharedTransitionScope;
                    Modifier modifierSharedElement$default = SharedTransitionScope.sharedElement$default(sharedTransitionScope, Modifier.INSTANCE, sharedTransitionScope.rememberSharedContentState(LibraryScreenKt.playlistArtworkSharedKey(neteasePlaylistSummary.getId()), $composer, 0), $animatedVisibilityScope, null, null, false, 0.0f, null, 124, null);
                    $composer.endReplaceGroup();
                    SingletonAsyncImageKt.m9399AsyncImage10Xjiaw(neteasePlaylistSummary.getCoverUrl(), null, ClipKt.clip(SizeKt.m1872size3ABfNKs(modifierSharedElement$default, C1301Dp.m8905constructorimpl(54)), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(7))), null, null, null, ContentScale.INSTANCE.getCrop(), 0.0f, null, 0, false, $composer, 1572912, 0, 1976);
                    Modifier modifierWeight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
                    Arrangement.Vertical verticalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(C1301Dp.m8905constructorimpl(4));
                    ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM1497spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer, ((i >> 3) & 14) | ((i >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierWeight$default);
                    constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int i6 = ((((i << 3) & 112) << 6) & 896) | 6;
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
                    Composer composerM5188constructorimpl2 = Updater.m5188constructorimpl($composer);
                    Updater.m5196setimpl(composerM5188constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m5196setimpl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m5196setimpl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m5194reconcileimpl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m5196setimpl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i7 = (i6 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                    int i8 = ((i >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -2020103951, "C579@23589L204,588@23962L11,585@23814L215:LibraryScreen.kt#t3x8p4");
                    TextKt.m3912TextNvy7gAk(neteasePlaylistSummary.getName(), null, 0L, null, TextUnitKt.getSp(17), null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m8816getEllipsisgIe3tQ8(), false, 1, 0, null, null, $composer, 24576, 24960, 241646);
                    String str = neteasePlaylistSummary.getTrackCount() + " 首歌曲";
                    long sp = TextUnitKt.getSp(12);
                    long onBackground = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnBackground();
                    TextKt.m3912TextNvy7gAk(str, null, Color.m6066copywmQWz5c(onBackground, (14 & 1) != 0 ? Color.m6070getAlphaimpl(onBackground) : 0.48f, (14 & 2) != 0 ? Color.m6074getRedimpl(onBackground) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(onBackground) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(onBackground) : 0.0f), null, sp, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24576, 0, 262122);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    long sp2 = TextUnitKt.getSp(24);
                    long onBackground2 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnBackground();
                    TextKt.m3912TextNvy7gAk("›", null, Color.m6066copywmQWz5c(onBackground2, (14 & 1) != 0 ? Color.m6070getAlphaimpl(onBackground2) : 0.24f, (14 & 2) != 0 ? Color.m6074getRedimpl(onBackground2) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(onBackground2) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(onBackground2) : 0.0f), null, sp2, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24582, 0, 262122);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    LibraryScreenKt.m9652MeloXInsetDivider8Feqmps(C1301Dp.m8905constructorimpl(84), $composer, 6);
                    $composer.endReplaceGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                i = 48;
                final Function1 function4 = $onPlaylistClick;
                Object obj = (Function0) new Function0<Unit>() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$MeloXLibraryPlaylistsPage$3$1$2$1$1
                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        function4.invoke(neteasePlaylistSummary);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
                ComposerKt.sourceInformationMarkerEnd($composer);
                Modifier modifierM1806paddingVpY3zN5 = PaddingKt.m1806paddingVpY3zN4(ClickableKt.m1078clickableoSLSa3U$default(modifierFillMaxWidth$default, false, null, null, null, (Function0) objRememberedValue, 15, null), C1301Dp.m8905constructorimpl(18), C1301Dp.m8905constructorimpl(6));
                Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
                Arrangement.Horizontal horizontalM1497spacedBy0680j_5 = Arrangement.INSTANCE.m1497spacedBy0680j_4(C1301Dp.m8905constructorimpl(12));
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(horizontalM1497spacedBy0680j_5, centerVertically2, $composer, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifierM1806paddingVpY3zN5);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i9 = ((((432 << 3) & 112) << 6) & 896) | 6;
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
                Composer composerM5188constructorimpl3 = Updater.m5188constructorimpl($composer);
                Updater.m5196setimpl(composerM5188constructorimpl3, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m5196setimpl(composerM5188constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m5196setimpl(composerM5188constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m5194reconcileimpl(composerM5188constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m5196setimpl(composerM5188constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i10 = (i9 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                int i11 = ((432 >> 6) & 112) | 6;
                RowScope rowScope2 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, -82723680, "C567@23078L324,575@23419L628,594@24175L11,591@24064L174:LibraryScreen.kt#t3x8p4");
                $composer.startReplaceGroup(967162509);
                ComposerKt.sourceInformation($composer, "*560@22818L126");
                SharedTransitionScope sharedTransitionScope2 = $sharedTransitionScope;
                Modifier modifierSharedElement$default2 = SharedTransitionScope.sharedElement$default(sharedTransitionScope2, Modifier.INSTANCE, sharedTransitionScope2.rememberSharedContentState(LibraryScreenKt.playlistArtworkSharedKey(neteasePlaylistSummary.getId()), $composer, 0), $animatedVisibilityScope, null, null, false, 0.0f, null, 124, null);
                $composer.endReplaceGroup();
                SingletonAsyncImageKt.m9399AsyncImage10Xjiaw(neteasePlaylistSummary.getCoverUrl(), null, ClipKt.clip(SizeKt.m1872size3ABfNKs(modifierSharedElement$default2, C1301Dp.m8905constructorimpl(54)), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(7))), null, null, null, ContentScale.INSTANCE.getCrop(), 0.0f, null, 0, false, $composer, 1572912, 0, 1976);
                Modifier modifierWeight$default2 = RowScope.weight$default(rowScope2, Modifier.INSTANCE, 1.0f, false, 2, null);
                Arrangement.Vertical verticalM1497spacedBy0680j_5 = Arrangement.INSTANCE.m1497spacedBy0680j_4(C1301Dp.m8905constructorimpl(4));
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(verticalM1497spacedBy0680j_5, Alignment.INSTANCE.getStart(), $composer, ((i >> 3) & 14) | ((i >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap4 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer, modifierWeight$default2);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i12 = ((((i << 3) & 112) << 6) & 896) | 6;
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
                Composer composerM5188constructorimpl4 = Updater.m5188constructorimpl($composer);
                Updater.m5196setimpl(composerM5188constructorimpl4, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m5196setimpl(composerM5188constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m5196setimpl(composerM5188constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m5194reconcileimpl(composerM5188constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m5196setimpl(composerM5188constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                int i13 = (i12 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                int i14 = ((i >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -2020103951, "C579@23589L204,588@23962L11,585@23814L215:LibraryScreen.kt#t3x8p4");
                TextKt.m3912TextNvy7gAk(neteasePlaylistSummary.getName(), null, 0L, null, TextUnitKt.getSp(17), null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m8816getEllipsisgIe3tQ8(), false, 1, 0, null, null, $composer, 24576, 24960, 241646);
                String str2 = neteasePlaylistSummary.getTrackCount() + " 首歌曲";
                long sp3 = TextUnitKt.getSp(12);
                long onBackground3 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnBackground();
                TextKt.m3912TextNvy7gAk(str2, null, Color.m6066copywmQWz5c(onBackground3, (14 & 1) != 0 ? Color.m6070getAlphaimpl(onBackground3) : 0.48f, (14 & 2) != 0 ? Color.m6074getRedimpl(onBackground3) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(onBackground3) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(onBackground3) : 0.0f), null, sp3, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24576, 0, 262122);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                long sp4 = TextUnitKt.getSp(24);
                long onBackground4 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnBackground();
                TextKt.m3912TextNvy7gAk("›", null, Color.m6066copywmQWz5c(onBackground4, (14 & 1) != 0 ? Color.m6070getAlphaimpl(onBackground4) : 0.24f, (14 & 2) != 0 ? Color.m6074getRedimpl(onBackground4) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(onBackground4) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(onBackground4) : 0.0f), null, sp4, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24582, 0, 262122);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                LibraryScreenKt.m9652MeloXInsetDivider8Feqmps(C1301Dp.m8905constructorimpl(84), $composer, 6);
                $composer.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object MeloXLibraryPlaylistsPage$lambda$2$0$0(NeteasePlaylistSummary it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Long.valueOf(it.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: MeloXInsetDivider-8Feqmps, reason: not valid java name */
    public static final void m9652MeloXInsetDivider8Feqmps(final float leading, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-1267114378);
        ComposerKt.sourceInformation($composer2, "C(MeloXInsetDivider)N(leading:c#ui.unit.Dp)607@24549L11,604@24405L195:LibraryScreen.kt#t3x8p4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(leading) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if ($composer2.shouldExecute(($dirty2 & 3) != 2, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1267114378, $dirty2, -1, "com.lladlam.melox.ui.library.MeloXInsetDivider (LibraryScreen.kt:603)");
            }
            Modifier modifierM1809paddingqDBjuR0$default = PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, leading, 0.0f, C1301Dp.m8905constructorimpl(18), 0.0f, 10, null);
            float fM8905constructorimpl = C1301Dp.m8905constructorimpl((float) 0.6d);
            long onBackground = MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getOnBackground();
            DividerKt.m3239HorizontalDivider9IZ8Weo(modifierM1809paddingqDBjuR0$default, fM8905constructorimpl, Color.m6066copywmQWz5c(onBackground, (14 & 1) != 0 ? Color.m6070getAlphaimpl(onBackground) : 0.12f, (14 & 2) != 0 ? Color.m6074getRedimpl(onBackground) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(onBackground) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(onBackground) : 0.0f), $composer2, 48, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda55
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LibraryScreenKt.MeloXInsetDivider_8Feqmps$lambda$0(leading, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:153:0x0493  */
    public static final void MeloXPlaylistDetailScreen(final NeteasePlaylistSummary initialPlaylist, final NeteaseLibraryClient client, final Function0<Unit> onBack, final SharedTransitionScope sharedTransitionScope, final AnimatedVisibilityScope animatedVisibilityScope, Composer $composer, final int $changed) {
        Composer $composer2;
        Object objMutableStateOf$default;
        MutableState loading$delegate;
        NeteaseLibraryClient neteaseLibraryClient;
        NeteasePlaylistSummary summary;
        MutableState palette$delegate;
        NeteasePlaylistSummary displayed;
        List<SearchSong> list;
        boolean z;
        List<SearchSong> list2;
        boolean z2;
        Intrinsics.checkNotNullParameter(initialPlaylist, "initialPlaylist");
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(onBack, "onBack");
        Intrinsics.checkNotNullParameter(sharedTransitionScope, "sharedTransitionScope");
        Intrinsics.checkNotNullParameter(animatedVisibilityScope, "animatedVisibilityScope");
        Composer $composer3 = $composer.startRestartGroup(-1702201522);
        ComposerKt.sourceInformation($composer3, "C(MeloXPlaylistDetailScreen)N(initialPlaylist,client,onBack,sharedTransitionScope,animatedVisibilityScope)620@24936L7,622@25008L24,623@25049L56,624@25124L77,625@25221L53,626@25299L62,627@25385L51,628@25456L87,642@25965L239,642@25930L274,653@26341L83,653@26306L118,659@26586L328,670@27008L38,674@27147L6065,668@26920L6292:LibraryScreen.kt#t3x8p4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(initialPlaylist) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(client) ? 32 : 16;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer3.changedInstance(onBack) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(sharedTransitionScope) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changedInstance(animatedVisibilityScope) ? 16384 : 8192;
        }
        if ($composer3.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1702201522, $dirty, -1, "com.lladlam.melox.ui.library.MeloXPlaylistDetailScreen (LibraryScreen.kt:619)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Context context = (Context) objConsume;
            Context appContext = context.getApplicationContext();
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
            ComposerKt.sourceInformationMarkerStart($composer3, 566889542, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean zChanged = $composer3.changed(appContext);
            Object objRememberedValue2 = $composer3.rememberedValue();
            if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Intrinsics.checkNotNull(appContext);
                Object neteaseLibraryCache = new NeteaseLibraryCache(appContext);
                $composer3.updateRememberedValue(neteaseLibraryCache);
                objRememberedValue2 = neteaseLibraryCache;
            }
            final NeteaseLibraryCache cache = (NeteaseLibraryCache) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            long id = initialPlaylist.getId();
            ComposerKt.sourceInformationMarkerStart($composer3, 566891963, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean zChanged2 = $composer3.changed(id);
            Object objRememberedValue3 = $composer3.rememberedValue();
            if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default2);
                objRememberedValue3 = objMutableStateOf$default2;
            }
            final MutableState detail$delegate = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            long id2 = initialPlaylist.getId();
            ComposerKt.sourceInformationMarkerStart($composer3, 566895043, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean zChanged3 = $composer3.changed(id2);
            Object objRememberedValue4 = $composer3.rememberedValue();
            if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default3);
                objRememberedValue4 = objMutableStateOf$default3;
            }
            MutableState loading$delegate2 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            long id3 = initialPlaylist.getId();
            ComposerKt.sourceInformationMarkerStart($composer3, 566897548, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean zChanged4 = $composer3.changed(id3);
            Object objRememberedValue5 = $composer3.rememberedValue();
            if (zChanged4 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer3.updateRememberedValue(mutableStateMutableStateOf$default);
                objRememberedValue5 = mutableStateMutableStateOf$default;
            }
            final MutableState errorMessage$delegate = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            long id4 = initialPlaylist.getId();
            ComposerKt.sourceInformationMarkerStart($composer3, 566900289, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean zChanged5 = $composer3.changed(id4);
            Object objRememberedValue6 = $composer3.rememberedValue();
            if (zChanged5 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default4);
                objRememberedValue6 = objMutableStateOf$default4;
            }
            final MutableState searchQuery$delegate = (MutableState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            String coverUrl = initialPlaylist.getCoverUrl();
            ComposerKt.sourceInformationMarkerStart($composer3, 566902597, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean zChanged6 = $composer3.changed(coverUrl);
            Object objRememberedValue7 = $composer3.rememberedValue();
            if (zChanged6 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(MeloXDetailPalette.INSTANCE.getLightFallback(), null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default);
            } else {
                objMutableStateOf$default = objRememberedValue7;
            }
            MutableState palette$delegate2 = (MutableState) objMutableStateOf$default;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Long lValueOf = Long.valueOf(initialPlaylist.getId());
            ComposerKt.sourceInformationMarkerStart($composer3, 566919037, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean zChangedInstance = (($dirty & 14) == 4) | $composer3.changedInstance(cache) | $composer3.changed(detail$delegate) | $composer3.changed(loading$delegate2) | $composer3.changed(errorMessage$delegate) | $composer3.changedInstance(client);
            Object objRememberedValue8 = $composer3.rememberedValue();
            if (zChangedInstance || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                loading$delegate = loading$delegate2;
                neteaseLibraryClient = client;
                objRememberedValue8 = new LibraryScreenKt$MeloXPlaylistDetailScreen$1$1(cache, initialPlaylist, detail$delegate, loading$delegate, errorMessage$delegate, client, null);
                $composer3.updateRememberedValue(objRememberedValue8);
            } else {
                loading$delegate = loading$delegate2;
                neteaseLibraryClient = client;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(lValueOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue8, $composer3, 0);
            NeteasePlaylistDetail neteasePlaylistDetailMeloXPlaylistDetailScreen$lambda$2 = MeloXPlaylistDetailScreen$lambda$2(detail$delegate);
            if (neteasePlaylistDetailMeloXPlaylistDetailScreen$lambda$2 == null || (summary = neteasePlaylistDetailMeloXPlaylistDetailScreen$lambda$2.getSummary()) == null) {
                summary = initialPlaylist;
            }
            NeteasePlaylistSummary displayed2 = summary;
            NeteasePlaylistDetail neteasePlaylistDetailMeloXPlaylistDetailScreen$lambda$3 = MeloXPlaylistDetailScreen$lambda$2(detail$delegate);
            List<SearchSong> songs = neteasePlaylistDetailMeloXPlaylistDetailScreen$lambda$3 != null ? neteasePlaylistDetailMeloXPlaylistDetailScreen$lambda$3.getSongs() : null;
            if (songs == null) {
                songs = CollectionsKt.emptyList();
            }
            List<SearchSong> list3 = songs;
            String coverUrl2 = displayed2.getCoverUrl();
            ComposerKt.sourceInformationMarkerStart($composer3, 566930913, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean zChanged7 = $composer3.changed(palette$delegate) | $composer3.changed(displayed2);
            Object objRememberedValue9 = $composer3.rememberedValue();
            if (zChanged7 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                Object obj = (Function2) new LibraryScreenKt$MeloXPlaylistDetailScreen$2$1(displayed2, palette$delegate2, null);
                $composer3.updateRememberedValue(obj);
                objRememberedValue9 = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(coverUrl2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue9, $composer3, 0);
            final long foreground = MeloXPlaylistDetailScreen$lambda$14(palette$delegate).getPrefersDarkAppearance() ? Color.INSTANCE.m6105getWhite0d7_KjU() : Color.INSTANCE.m6094getBlack0d7_KjU();
            final long secondary = Color.m6066copywmQWz5c(foreground, (14 & 1) != 0 ? Color.m6070getAlphaimpl(foreground) : 0.48f, (14 & 2) != 0 ? Color.m6074getRedimpl(foreground) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(foreground) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(foreground) : 0.0f);
            String strMeloXPlaylistDetailScreen$lambda$11 = MeloXPlaylistDetailScreen$lambda$11(searchQuery$delegate);
            ComposerKt.sourceInformationMarkerStart($composer3, 566938998, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean zChanged8 = $composer3.changed(strMeloXPlaylistDetailScreen$lambda$11) | $composer3.changed(list3);
            Object objRememberedValue10 = $composer3.rememberedValue();
            if (zChanged8 || objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                Object lowerCase = StringsKt.trim((CharSequence) MeloXPlaylistDetailScreen$lambda$11(searchQuery$delegate)).toString().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                if (((CharSequence) lowerCase).length() == 0) {
                    displayed = displayed2;
                    list2 = list3;
                    list = list2;
                    z = false;
                } else {
                    Collection arrayList = new ArrayList();
                    for (Object obj2 : list3) {
                        Object obj3 = lowerCase;
                        SearchSong searchSong = (SearchSong) obj2;
                        NeteasePlaylistSummary displayed3 = displayed2;
                        List<SearchSong> list4 = list3;
                        String lowerCase2 = searchSong.getName().toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                        Object obj4 = objRememberedValue10;
                        MutableState palette$delegate3 = palette$delegate;
                        if (StringsKt.contains$default((CharSequence) lowerCase2, (CharSequence) obj3, false, 2, (Object) null)) {
                            z2 = true;
                        } else {
                            String lowerCase3 = searchSong.getArtists().toLowerCase(Locale.ROOT);
                            Intrinsics.checkNotNullExpressionValue(lowerCase3, "toLowerCase(...)");
                            if (StringsKt.contains$default((CharSequence) lowerCase3, (CharSequence) obj3, false, 2, (Object) null)) {
                                z2 = true;
                            } else {
                                String lowerCase4 = searchSong.getAlbum().toLowerCase(Locale.ROOT);
                                Intrinsics.checkNotNullExpressionValue(lowerCase4, "toLowerCase(...)");
                                if (StringsKt.contains$default((CharSequence) lowerCase4, (CharSequence) obj3, false, 2, (Object) null)) {
                                    z2 = true;
                                } else {
                                    z2 = false;
                                }
                            }
                        }
                        if (z2) {
                            arrayList.add(obj2);
                        }
                        palette$delegate = palette$delegate3;
                        lowerCase = obj3;
                        displayed2 = displayed3;
                        list3 = list4;
                        objRememberedValue10 = obj4;
                    }
                    displayed = displayed2;
                    list = list3;
                    z = false;
                    list2 = (List) arrayList;
                }
                $composer3.updateRememberedValue(list2);
                objRememberedValue10 = list2;
            } else {
                displayed = displayed2;
                list = list3;
                z = false;
            }
            final List filteredSongs = (List) objRememberedValue10;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            boolean z3 = (!MeloXPlaylistDetailScreen$lambda$5(loading$delegate) || MeloXPlaylistDetailScreen$lambda$2(detail$delegate) == null) ? z : true;
            ComposerKt.sourceInformationMarkerStart($composer3, 566952212, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean zChangedInstance2 = $composer3.changedInstance(scope) | $composer3.changed(loading$delegate) | $composer3.changed(errorMessage$delegate) | $composer3.changedInstance(neteaseLibraryClient);
            if (($dirty & 14) == 4) {
                z = true;
            }
            boolean zChanged9 = zChangedInstance2 | z | $composer3.changed(detail$delegate) | $composer3.changedInstance(cache);
            Object objRememberedValue11 = $composer3.rememberedValue();
            if (zChanged9 || objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                final MutableState loading$delegate3 = loading$delegate;
                final NeteaseLibraryClient neteaseLibraryClient2 = neteaseLibraryClient;
                Object obj5 = new Function0() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return LibraryScreenKt.MeloXPlaylistDetailScreen$lambda$22$0(scope, loading$delegate3, errorMessage$delegate, neteaseLibraryClient2, initialPlaylist, cache, detail$delegate);
                    }
                };
                loading$delegate = loading$delegate3;
                errorMessage$delegate = errorMessage$delegate;
                cache = cache;
                detail$delegate = detail$delegate;
                $composer3.updateRememberedValue(obj5);
                objRememberedValue11 = obj5;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final NeteaseLibraryCache cache2 = cache;
            final MutableState loading$delegate4 = loading$delegate;
            final NeteasePlaylistSummary displayed4 = displayed;
            final List<SearchSong> list5 = list;
            final MutableState detail$delegate2 = detail$delegate;
            final MutableState errorMessage$delegate2 = errorMessage$delegate;
            PullToRefreshKt.PullToRefreshBox(z3, (Function0) objRememberedValue11, BackgroundKt.m1043backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), Color.INSTANCE.m6094getBlack0d7_KjU(), null, 2, null), null, null, null, ComposableLambdaKt.rememberComposableLambda(100596648, true, new Function3() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj6, Object obj7, Object obj8) {
                    return LibraryScreenKt.MeloXPlaylistDetailScreen$lambda$23(displayed4, foreground, onBack, searchQuery$delegate, list5, secondary, context, errorMessage$delegate2, sharedTransitionScope, animatedVisibilityScope, loading$delegate4, scope, client, initialPlaylist, detail$delegate2, cache2, filteredSongs, (BoxScope) obj6, (Composer) obj7, ((Integer) obj8).intValue());
                }
            }, $composer3, 54), $composer3, 1573248, 56);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj6, Object obj7) {
                    return LibraryScreenKt.MeloXPlaylistDetailScreen$lambda$24(initialPlaylist, client, onBack, sharedTransitionScope, animatedVisibilityScope, $changed, (Composer) obj6, ((Integer) obj7).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NeteasePlaylistDetail MeloXPlaylistDetailScreen$lambda$2(MutableState<NeteasePlaylistDetail> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean MeloXPlaylistDetailScreen$lambda$5(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void MeloXPlaylistDetailScreen$lambda$6(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String MeloXPlaylistDetailScreen$lambda$8(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String MeloXPlaylistDetailScreen$lambda$11(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final MeloXDetailPalette MeloXPlaylistDetailScreen$lambda$14(MutableState<MeloXDetailPalette> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:26:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:28:0x00fd A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:29:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:33:0x0107  */
    /* JADX WARN: Code duplicated, block: B:35:0x010e  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object MeloXPlaylistDetailScreen$refreshPlaylist(MutableState<Boolean> mutableState, MutableState<String> mutableState2, NeteaseLibraryClient $client, NeteasePlaylistSummary $initialPlaylist, NeteaseLibraryCache cache, MutableState<NeteasePlaylistDetail> mutableState3, Continuation<? super Unit> continuation) {
        LibraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1 libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1;
        Object objM9714constructorimpl;
        Object obj;
        NeteasePlaylistDetail neteasePlaylistDetail;
        long id;
        Throwable thM9717exceptionOrNullimpl;
        String message;
        Object objPlaylistDetail;
        if (continuation instanceof LibraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1) {
            libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1 = (LibraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1) continuation;
            if ((libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.label & Integer.MIN_VALUE) != 0) {
                libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.label -= Integer.MIN_VALUE;
            } else {
                libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1 = new LibraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1(continuation);
            }
        } else {
            libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1 = new LibraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1(continuation);
        }
        Object $result = libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    MeloXPlaylistDetailScreen$lambda$6(mutableState, true);
                    mutableState2.setValue(null);
                    Result.Companion companion = Result.INSTANCE;
                    long id2 = $initialPlaylist.getId();
                    libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$0 = mutableState;
                    libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$1 = mutableState2;
                    libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$2 = SpillingKt.nullOutSpilledVariable($client);
                    libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$3 = $initialPlaylist;
                    libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$4 = cache;
                    libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$5 = mutableState3;
                    libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.I$0 = 0;
                    libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.label = 1;
                    objPlaylistDetail = $client.playlistDetail(id2, libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1);
                    if (objPlaylistDetail == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    objM9714constructorimpl = Result.constructor-impl((NeteasePlaylistDetail) objPlaylistDetail);
                    obj = objM9714constructorimpl;
                    if (Result.m9721isSuccessimpl(obj)) {
                        neteasePlaylistDetail = (NeteasePlaylistDetail) obj;
                        mutableState3.setValue(neteasePlaylistDetail);
                        id = $initialPlaylist.getId();
                        libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$0 = mutableState;
                        libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$1 = mutableState2;
                        libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$2 = SpillingKt.nullOutSpilledVariable($client);
                        libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$3 = SpillingKt.nullOutSpilledVariable($initialPlaylist);
                        libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$4 = SpillingKt.nullOutSpilledVariable(cache);
                        libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$5 = SpillingKt.nullOutSpilledVariable(mutableState3);
                        libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$6 = obj;
                        libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$7 = SpillingKt.nullOutSpilledVariable(neteasePlaylistDetail);
                        libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.I$0 = 0;
                        libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.label = 2;
                        if (cache.savePlaylistDetail(id, neteasePlaylistDetail, libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    thM9717exceptionOrNullimpl = Result.m9717exceptionOrNullimpl(obj);
                    if (thM9717exceptionOrNullimpl != null) {
                        message = thM9717exceptionOrNullimpl.getMessage();
                        if (message == null) {
                            message = "歌单加载失败";
                        }
                        mutableState2.setValue(message);
                    }
                    MeloXPlaylistDetailScreen$lambda$6(mutableState, false);
                    return Unit.INSTANCE;
                case 1:
                    int i = libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.I$0;
                    mutableState3 = (MutableState) libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$5;
                    cache = (NeteaseLibraryCache) libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$4;
                    $initialPlaylist = (NeteasePlaylistSummary) libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$3;
                    $client = (NeteaseLibraryClient) libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$2;
                    mutableState2 = (MutableState) libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$1;
                    mutableState = (MutableState) libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$0;
                    ResultKt.throwOnFailure($result);
                    objPlaylistDetail = $result;
                    objM9714constructorimpl = Result.constructor-impl((NeteasePlaylistDetail) objPlaylistDetail);
                    obj = objM9714constructorimpl;
                    if (Result.m9721isSuccessimpl(obj)) {
                        neteasePlaylistDetail = (NeteasePlaylistDetail) obj;
                        mutableState3.setValue(neteasePlaylistDetail);
                        id = $initialPlaylist.getId();
                        libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$0 = mutableState;
                        libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$1 = mutableState2;
                        libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$2 = SpillingKt.nullOutSpilledVariable($client);
                        libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$3 = SpillingKt.nullOutSpilledVariable($initialPlaylist);
                        libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$4 = SpillingKt.nullOutSpilledVariable(cache);
                        libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$5 = SpillingKt.nullOutSpilledVariable(mutableState3);
                        libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$6 = obj;
                        libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$7 = SpillingKt.nullOutSpilledVariable(neteasePlaylistDetail);
                        libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.I$0 = 0;
                        libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.label = 2;
                        if (cache.savePlaylistDetail(id, neteasePlaylistDetail, libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    thM9717exceptionOrNullimpl = Result.m9717exceptionOrNullimpl(obj);
                    if (thM9717exceptionOrNullimpl != null) {
                        message = thM9717exceptionOrNullimpl.getMessage();
                        if (message == null) {
                            message = "歌单加载失败";
                        }
                        mutableState2.setValue(message);
                    }
                    MeloXPlaylistDetailScreen$lambda$6(mutableState, false);
                    return Unit.INSTANCE;
                case 2:
                    int i2 = libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.I$0;
                    obj = libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$6;
                    mutableState2 = (MutableState) libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$1;
                    mutableState = (MutableState) libraryScreenKt$MeloXPlaylistDetailScreen$refreshPlaylist$1.L$0;
                    ResultKt.throwOnFailure($result);
                    thM9717exceptionOrNullimpl = Result.m9717exceptionOrNullimpl(obj);
                    if (thM9717exceptionOrNullimpl != null) {
                        message = thM9717exceptionOrNullimpl.getMessage();
                        if (message == null) {
                            message = "歌单加载失败";
                        }
                        mutableState2.setValue(message);
                    }
                    MeloXPlaylistDetailScreen$lambda$6(mutableState, false);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM9714constructorimpl = Result.constructor-impl(ResultKt.createFailure(th));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXPlaylistDetailScreen$lambda$22$0(CoroutineScope $scope, MutableState $loading$delegate, MutableState $errorMessage$delegate, NeteaseLibraryClient $client, NeteasePlaylistSummary $initialPlaylist, NeteaseLibraryCache $cache, MutableState $detail$delegate) {
        BuildersKt__Builders_commonKt.launch$default($scope, null, null, new LibraryScreenKt$MeloXPlaylistDetailScreen$3$1$1($loading$delegate, $errorMessage$delegate, $client, $initialPlaylist, $cache, $detail$delegate, null), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:36:0x02f7  */
    static final Unit MeloXPlaylistDetailScreen$lambda$23(final NeteasePlaylistSummary $displayed, final long $foreground, Function0 $onBack, final MutableState $searchQuery$delegate, final List $songs, final long $secondary, final Context $context, final MutableState $errorMessage$delegate, final SharedTransitionScope $sharedTransitionScope, final AnimatedVisibilityScope $animatedVisibilityScope, final MutableState $loading$delegate, final CoroutineScope $scope, final NeteaseLibraryClient $client, final NeteasePlaylistSummary $initialPlaylist, final MutableState $detail$delegate, final NeteaseLibraryCache $cache, final List $filteredSongs, BoxScope PullToRefreshBox, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Composer composer;
        Intrinsics.checkNotNullParameter(PullToRefreshBox, "$this$PullToRefreshBox");
        ComposerKt.sourceInformation($composer, "C677@27319L159,683@27488L5718:LibraryScreen.kt#t3x8p4");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(100596648, $changed, -1, "com.lladlam.melox.ui.library.MeloXPlaylistDetailScreen.<anonymous> (LibraryScreen.kt:677)");
            }
            MeloXFlowingLightBackdropKt.MeloXFlowingLightBackdrop($displayed.getCoverUrl(), false, SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), $composer, 432, 0);
            Modifier modifierStatusBarsPadding = WindowInsetsPadding_androidKt.statusBarsPadding(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null));
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierStatusBarsPadding);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((0 << 3) & 112) << 6) & 896) | 6;
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
            int i3 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1448564566, "C688@27621L109,694@27838L20,692@27743L254,702@28174L5022,699@28011L5185:LibraryScreen.kt#t3x8p4");
            m9655MeloXPlaylistToolbarIv8Zu3U($foreground, $onBack, $composer, 0);
            String strMeloXPlaylistDetailScreen$lambda$11 = MeloXPlaylistDetailScreen$lambda$11($searchQuery$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -1847841818, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean zChanged = $composer.changed($searchQuery$delegate);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda44
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return LibraryScreenKt.MeloXPlaylistDetailScreen$lambda$23$0$0$0($searchQuery$delegate, (String) obj);
                    }
                };
                $composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            m9654MeloXPlaylistSearchFieldcf5BqRc(strMeloXPlaylistDetailScreen$lambda$11, (Function1) objRememberedValue, $foreground, PaddingKt.m1806paddingVpY3zN4(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(18), C1301Dp.m8905constructorimpl(10)), $composer, 3072, 0);
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            PaddingValues paddingValuesM1802PaddingValuesa9UjIt4$default = PaddingKt.m1802PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, MeloXLayoutKt.getMeloXBottomContentClearance(), 7, null);
            ComposerKt.sourceInformationMarkerStart($composer, -1847826064, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean zChanged2 = $composer.changed($displayed) | $composer.changedInstance($songs) | $composer.changed($foreground) | $composer.changed($secondary) | $composer.changedInstance($context) | $composer.changed($errorMessage$delegate) | $composer.changed($sharedTransitionScope) | $composer.changedInstance($animatedVisibilityScope) | $composer.changed($loading$delegate) | $composer.changedInstance($scope) | $composer.changedInstance($client) | $composer.changed($initialPlaylist) | $composer.changed($detail$delegate) | $composer.changedInstance($cache) | $composer.changedInstance($filteredSongs);
            Object objRememberedValue2 = $composer.rememberedValue();
            if (!zChanged2) {
                composer = $composer;
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                LazyDslKt.LazyColumn(modifierFillMaxSize$default, null, paddingValuesM1802PaddingValuesa9UjIt4$default, false, null, null, null, false, null, (Function1) objRememberedValue2, composer, 390, 506);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composer = $composer;
            }
            objRememberedValue2 = new Function1() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda45
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return LibraryScreenKt.MeloXPlaylistDetailScreen$lambda$23$0$1$0($songs, $filteredSongs, $displayed, $foreground, $secondary, $context, $errorMessage$delegate, $sharedTransitionScope, $animatedVisibilityScope, $loading$delegate, $scope, $client, $initialPlaylist, $detail$delegate, $cache, (LazyListScope) obj);
                }
            };
            $composer.updateRememberedValue(objRememberedValue2);
            ComposerKt.sourceInformationMarkerEnd(composer);
            LazyDslKt.LazyColumn(modifierFillMaxSize$default, null, paddingValuesM1802PaddingValuesa9UjIt4$default, false, null, null, null, false, null, (Function1) objRememberedValue2, composer, 390, 506);
            ComposerKt.sourceInformationMarkerEnd(composer);
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
    public static final Unit MeloXPlaylistDetailScreen$lambda$23$0$0$0(MutableState $searchQuery$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $searchQuery$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXPlaylistDetailScreen$lambda$23$0$1$0(final List $songs, final List $filteredSongs, final NeteasePlaylistSummary $displayed, final long $foreground, final long $secondary, final Context $context, final MutableState $errorMessage$delegate, final SharedTransitionScope $sharedTransitionScope, final AnimatedVisibilityScope $animatedVisibilityScope, final MutableState $loading$delegate, final CoroutineScope $scope, final NeteaseLibraryClient $client, final NeteasePlaylistSummary $initialPlaylist, final MutableState $detail$delegate, final NeteaseLibraryCache $cache, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-41092611, true, new Function3() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda23
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return LibraryScreenKt.MeloXPlaylistDetailScreen$lambda$23$0$1$0$0($displayed, $songs, $foreground, $secondary, $context, $errorMessage$delegate, $sharedTransitionScope, $animatedVisibilityScope, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        if (MeloXPlaylistDetailScreen$lambda$5($loading$delegate) && $songs.isEmpty()) {
            LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-113290491, true, new Function3() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda24
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return LibraryScreenKt.MeloXPlaylistDetailScreen$lambda$23$0$1$0$1($foreground, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }), 3, null);
        } else if (MeloXPlaylistDetailScreen$lambda$8($errorMessage$delegate) != null && $songs.isEmpty()) {
            LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(240482172, true, new Function3() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda25
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return LibraryScreenKt.MeloXPlaylistDetailScreen$lambda$23$0$1$0$2($secondary, $scope, $loading$delegate, $errorMessage$delegate, $client, $initialPlaylist, $detail$delegate, $cache, $foreground, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }), 3, null);
        } else if ($filteredSongs.isEmpty()) {
            LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(462393853, true, new Function3() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda26
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return LibraryScreenKt.MeloXPlaylistDetailScreen$lambda$23$0$1$0$3($secondary, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }), 3, null);
        } else {
            final Function2 function2 = new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda27
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LibraryScreenKt.MeloXPlaylistDetailScreen$lambda$23$0$1$0$4(((Integer) obj).intValue(), (SearchSong) obj2);
                }
            };
            LazyColumn.items($filteredSongs.size(), new Function1<Integer, Object>() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$MeloXPlaylistDetailScreen$lambda$23$0$1$0$$inlined$itemsIndexed$default$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final Object invoke(int index) {
                    return function2.invoke(Integer.valueOf(index), $filteredSongs.get(index));
                }
            }, new Function1<Integer, Object>() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$MeloXPlaylistDetailScreen$lambda$23$0$1$0$$inlined$itemsIndexed$default$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final Object invoke(int index) {
                    $filteredSongs.get(index);
                    return null;
                }
            }, ComposableLambdaKt.composableLambdaInstance(2039820996, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$MeloXPlaylistDetailScreen$lambda$23$0$1$0$$inlined$itemsIndexed$default$3
                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                    invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(LazyItemScope $this$items, int it, Composer $composer, int $changed) {
                    ComposerKt.sourceInformation($composer, "CN(it)214@10668L26:LazyDsl.kt#428nma");
                    int $dirty = $changed;
                    if (($changed & 6) == 0) {
                        $dirty |= $composer.changed($this$items) ? 4 : 2;
                    }
                    if (($changed & 48) == 0) {
                        $dirty |= $composer.changed(it) ? 32 : 16;
                    }
                    if ($composer.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(2039820996, $dirty, -1, "androidx.compose.foundation.lazy.itemsIndexed.<anonymous> (LazyDsl.kt:214)");
                        }
                        int i = ($dirty & 14) | ($dirty & 112);
                        final SearchSong searchSong = (SearchSong) $filteredSongs.get(it);
                        Composer composer = $composer;
                        composer.startReplaceGroup(1161009056);
                        ComposerKt.sourceInformation(composer, "CN(index,song)*792@32336L390,788@32138L615:LibraryScreen.kt#t3x8p4");
                        long j = $foreground;
                        ComposerKt.sourceInformationMarkerStart(composer, -2040752355, "CC(remember):LibraryScreen.kt#9igjgp");
                        boolean zChangedInstance = ((((i & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256 && composer.changed(searchSong)) || (i & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | composer.changedInstance($context) | composer.changedInstance($filteredSongs) | composer.changed($errorMessage$delegate);
                        Object objRememberedValue = composer.rememberedValue();
                        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            final Context context = $context;
                            final List list = $filteredSongs;
                            final MutableState mutableState = $errorMessage$delegate;
                            Object obj = (Function0) new Function0<Unit>() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$MeloXPlaylistDetailScreen$4$1$2$1$6$1$1
                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    PlaybackCommands playbackCommands = PlaybackCommands.INSTANCE;
                                    Context context2 = context;
                                    List<SearchSong> list2 = list;
                                    long id = searchSong.getId();
                                    final MutableState<String> mutableState2 = mutableState;
                                    playbackCommands.playQueue(context2, list2, id, new Function1<Throwable, Unit>() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$MeloXPlaylistDetailScreen$4$1$2$1$6$1$1.1
                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                            invoke2(th);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Throwable it2) {
                                            Intrinsics.checkNotNullParameter(it2, "it");
                                            MutableState<String> mutableState3 = mutableState2;
                                            String message = it2.getMessage();
                                            if (message == null) {
                                                message = "播放失败";
                                            }
                                            mutableState3.setValue(message);
                                        }
                                    });
                                }
                            };
                            composer.updateRememberedValue(obj);
                            objRememberedValue = obj;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        LibraryScreenKt.m9656MeloXPlaylistTrackRowFNF3uiM(searchSong, it, j, (Function0) objRememberedValue, composer, ((i >> 6) & 14) | (i & 112));
                        SearchSong searchSong2 = (SearchSong) CollectionsKt.lastOrNull($filteredSongs);
                        if (!(searchSong2 != null && searchSong.getId() == searchSong2.getId())) {
                            composer.startReplaceGroup(1161669262);
                            ComposerKt.sourceInformation(composer, "802@32855L261");
                            Modifier modifierM1809paddingqDBjuR0$default = PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(66), 0.0f, C1301Dp.m8905constructorimpl(20), 0.0f, 10, null);
                            float fM8905constructorimpl = C1301Dp.m8905constructorimpl((float) 0.6d);
                            long j2 = $foreground;
                            DividerKt.m3239HorizontalDivider9IZ8Weo(modifierM1809paddingqDBjuR0$default, fM8905constructorimpl, Color.m6066copywmQWz5c(j2, (14 & 1) != 0 ? Color.m6070getAlphaimpl(j2) : 0.12f, (14 & 2) != 0 ? Color.m6074getRedimpl(j2) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(j2) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(j2) : 0.0f), composer, 54, 0);
                            composer = composer;
                            composer.endReplaceGroup();
                        } else {
                            composer.startReplaceGroup(1161973899);
                            composer.endReplaceGroup();
                        }
                        composer.endReplaceGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer.skipToGroupEnd();
                }
            }));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXPlaylistDetailScreen$lambda$23$0$1$0$0(NeteasePlaylistSummary $displayed, final List $songs, long $foreground, long $secondary, final Context $context, final MutableState $errorMessage$delegate, SharedTransitionScope $sharedTransitionScope, AnimatedVisibilityScope $animatedVisibilityScope, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C709@28461L473,719@28972L539,704@28219L1461:LibraryScreen.kt#t3x8p4");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-41092611, $changed, -1, "com.lladlam.melox.ui.library.MeloXPlaylistDetailScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (LibraryScreen.kt:704)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -835284586, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance($songs) | $composer.changedInstance($context) | $composer.changed($errorMessage$delegate);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda35
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return LibraryScreenKt.MeloXPlaylistDetailScreen$lambda$23$0$1$0$0$0$0($songs, $context, $errorMessage$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            Function0 function0 = (Function0) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, -835268168, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean zChangedInstance2 = $composer.changedInstance($songs) | $composer.changedInstance($context) | $composer.changed($errorMessage$delegate);
            Object objRememberedValue2 = $composer.rememberedValue();
            if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function0() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda36
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return LibraryScreenKt.MeloXPlaylistDetailScreen$lambda$23$0$1$0$0$1$0($songs, $context, $errorMessage$delegate);
                    }
                };
                $composer.updateRememberedValue(obj2);
                objRememberedValue2 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            m9660MeloXStandardPlaylistHeropAZo6Ak($displayed, $songs, $foreground, $secondary, function0, (Function0) objRememberedValue2, $sharedTransitionScope, $animatedVisibilityScope, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXPlaylistDetailScreen$lambda$23$0$1$0$0$0$0(List $songs, Context $context, final MutableState $errorMessage$delegate) {
        SearchSong searchSong = (SearchSong) CollectionsKt.firstOrNull($songs);
        if (searchSong != null) {
            PlaybackCommands.INSTANCE.playQueue($context, $songs, searchSong.getId(), new Function1() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda46
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return LibraryScreenKt.MeloXPlaylistDetailScreen$lambda$23$0$1$0$0$0$0$0$0($errorMessage$delegate, (Throwable) obj);
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXPlaylistDetailScreen$lambda$23$0$1$0$0$0$0$0$0(MutableState $errorMessage$delegate, Throwable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String message = it.getMessage();
        if (message == null) {
            message = "播放失败";
        }
        $errorMessage$delegate.setValue(message);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXPlaylistDetailScreen$lambda$23$0$1$0$0$1$0(List $songs, Context $context, final MutableState $errorMessage$delegate) {
        List<SearchSong> listShuffled = CollectionsKt.shuffled($songs);
        SearchSong searchSong = (SearchSong) CollectionsKt.firstOrNull((List) listShuffled);
        if (searchSong != null) {
            PlaybackCommands.INSTANCE.playQueue($context, listShuffled, searchSong.getId(), new Function1() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda38
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return LibraryScreenKt.MeloXPlaylistDetailScreen$lambda$23$0$1$0$0$1$0$0$0($errorMessage$delegate, (Throwable) obj);
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXPlaylistDetailScreen$lambda$23$0$1$0$0$1$0$0$0(MutableState $errorMessage$delegate, Throwable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String message = it.getMessage();
        if (message == null) {
            message = "播放失败";
        }
        $errorMessage$delegate.setValue(message);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXPlaylistDetailScreen$lambda$23$0$1$0$1(long $foreground, LazyItemScope item, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C737@29804L342:LibraryScreen.kt#t3x8p4");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-113290491, $changed, -1, "com.lladlam.melox.ui.library.MeloXPlaylistDetailScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (LibraryScreen.kt:737)");
            }
            Modifier modifierM1858height3ABfNKs = SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1301Dp.m8905constructorimpl(180));
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM1858height3ABfNKs);
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
            Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer);
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 2032553367, "C743@30075L45:LibraryScreen.kt#t3x8p4");
            ProgressIndicatorKt.m3567CircularProgressIndicator4lLiAd8(null, $foreground, 0.0f, 0L, 0, 0.0f, $composer, 0, 61);
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
    public static final Unit MeloXPlaylistDetailScreen$lambda$23$0$1$0$2(long $secondary, final CoroutineScope $scope, final MutableState $loading$delegate, final MutableState $errorMessage$delegate, final NeteaseLibraryClient $client, final NeteasePlaylistSummary $initialPlaylist, final MutableState $detail$delegate, final NeteaseLibraryCache $cache, long $foreground, LazyItemScope item, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Object obj;
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C747@30263L1217:LibraryScreen.kt#t3x8p4");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(240482172, $changed, -1, "com.lladlam.melox.ui.library.MeloXPlaylistDetailScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (LibraryScreen.kt:747)");
            }
            Modifier modifierM1858height3ABfNKs = SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1301Dp.m8905constructorimpl(220));
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            Arrangement.Vertical center = Arrangement.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM1858height3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((438 << 3) & 112) << 6) & 896) | 6;
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
            int i3 = ((438 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 171788285, "C754@30622L204,763@31056L198,759@30855L599:LibraryScreen.kt#t3x8p4");
            String strMeloXPlaylistDetailScreen$lambda$8 = MeloXPlaylistDetailScreen$lambda$8($errorMessage$delegate);
            if (strMeloXPlaylistDetailScreen$lambda$8 == null) {
                strMeloXPlaylistDetailScreen$lambda$8 = "";
            }
            TextKt.m3912TextNvy7gAk(strMeloXPlaylistDetailScreen$lambda$8, null, $secondary, null, 0L, null, null, null, 0L, null, TextAlign.m8751boximpl(TextAlign.INSTANCE.m8758getCentere0LSkKk()), 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 261114);
            Modifier modifierM1809paddingqDBjuR0$default = PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, C1301Dp.m8905constructorimpl(12), 0.0f, 0.0f, 13, null);
            ComposerKt.sourceInformationMarkerStart($composer, 559744140, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance($scope) | $composer.changed($loading$delegate) | $composer.changed($errorMessage$delegate) | $composer.changedInstance($client) | $composer.changed($initialPlaylist) | $composer.changed($detail$delegate) | $composer.changedInstance($cache);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                obj = new Function0() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda42
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return LibraryScreenKt.MeloXPlaylistDetailScreen$lambda$23$0$1$0$2$0$0$0($scope, $loading$delegate, $errorMessage$delegate, $client, $initialPlaylist, $cache, $detail$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = objRememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            TextKt.m3912TextNvy7gAk("重试", PaddingKt.m1805padding3ABfNKs(ClickableKt.m1078clickableoSLSa3U$default(modifierM1809paddingqDBjuR0$default, false, null, null, null, (Function0) obj, 15, null), C1301Dp.m8905constructorimpl(8)), $foreground, null, 0L, null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1572870, 0, 262072);
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
    public static final Unit MeloXPlaylistDetailScreen$lambda$23$0$1$0$2$0$0$0(CoroutineScope $scope, MutableState $loading$delegate, MutableState $errorMessage$delegate, NeteaseLibraryClient $client, NeteasePlaylistSummary $initialPlaylist, NeteaseLibraryCache $cache, MutableState $detail$delegate) {
        BuildersKt__Builders_commonKt.launch$default($scope, null, null, new LibraryScreenKt$MeloXPlaylistDetailScreen$4$1$2$1$3$1$1$1$1($loading$delegate, $errorMessage$delegate, $client, $initialPlaylist, $cache, $detail$delegate, null), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXPlaylistDetailScreen$lambda$23$0$1$0$3(long $secondary, LazyItemScope item, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C775@31581L328:LibraryScreen.kt#t3x8p4");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(462393853, $changed, -1, "com.lladlam.melox.ui.library.MeloXPlaylistDetailScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (LibraryScreen.kt:775)");
            }
            Modifier modifierM1858height3ABfNKs = SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1301Dp.m8905constructorimpl(180));
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM1858height3ABfNKs);
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
            Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer);
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1854529645, "C781@31852L31:LibraryScreen.kt#t3x8p4");
            TextKt.m3912TextNvy7gAk("暂无歌曲", null, $secondary, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262138);
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
    public static final Object MeloXPlaylistDetailScreen$lambda$23$0$1$0$4(int i, SearchSong song) {
        Intrinsics.checkNotNullParameter(song, "song");
        return Long.valueOf(song.getId());
    }

    /* JADX INFO: renamed from: MeloXPlaylistToolbar-Iv8Zu3U, reason: not valid java name */
    private static final void m9655MeloXPlaylistToolbarIv8Zu3U(long foreground, Function0<Unit> function0, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function2;
        Function0<ComposeUiNode> function3;
        Function0<ComposeUiNode> function4;
        final long j = foreground;
        final Function0<Unit> function5 = function0;
        Composer $composer3 = $composer.startRestartGroup(-1965650802);
        ComposerKt.sourceInformation($composer3, "C(MeloXPlaylistToolbar)N(foreground:c#ui.graphics.Color,onBack)820@33317L2024:LibraryScreen.kt#t3x8p4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(j) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function5) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if ($composer3.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1965650802, $dirty2, -1, "com.lladlam.melox.ui.library.MeloXPlaylistToolbar (LibraryScreen.kt:819)");
            }
            Modifier modifierM1806paddingVpY3zN4 = PaddingKt.m1806paddingVpY3zN4(SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1301Dp.m8905constructorimpl(58)), C1301Dp.m8905constructorimpl(18), C1301Dp.m8905constructorimpl(7));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
            ComposerKt.sourceInformationMarkerStart($composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1806paddingVpY3zN4);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((438 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function1 = constructor;
                $composer3.createNode(function1);
            } else {
                function1 = constructor;
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer3);
            $composer2 = $composer3;
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i3 = ((438 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 1278280620, "C832@33721L72,828@33594L199,840@33937L244,836@33803L1532:LibraryScreen.kt#t3x8p4");
            function5 = function0;
            j = foreground;
            m9651MeloXGlassCircleButtonCgHO2UQ(j, C1301Dp.m8905constructorimpl(44), false, function5, ComposableLambdaKt.rememberComposableLambda(-400762056, true, new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda70
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LibraryScreenKt.MeloXPlaylistToolbar_Iv8Zu3U$lambda$0$0(j, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), $composer3, ($dirty2 & 14) | 24624 | (($dirty2 << 6) & 7168), 4);
            Modifier modifierClip = ClipKt.clip(SizeKt.m1858height3ABfNKs(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(44)), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(22)));
            RoundedCornerShape roundedCornerShapeM2135RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(22));
            long jM9663glassColor8_81llA = m9663glassColor8_81llA(j);
            long jM6066copywmQWz5c = Color.m6066copywmQWz5c(jM9663glassColor8_81llA, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM9663glassColor8_81llA) : 0.18f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM9663glassColor8_81llA) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM9663glassColor8_81llA) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM9663glassColor8_81llA) : 0.0f);
            long jM9663glassColor8_81llA2 = m9663glassColor8_81llA(j);
            Modifier modifierM1807paddingVpY3zN4$default = PaddingKt.m1807paddingVpY3zN4$default(MeloXBackdropComponentsKt.m9632meloXLiquidBottomBar9z6LAg8(modifierClip, roundedCornerShapeM2135RoundedCornerShape0680j_4, jM6066copywmQWz5c, Color.m6066copywmQWz5c(jM9663glassColor8_81llA2, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM9663glassColor8_81llA2) : 0.42f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM9663glassColor8_81llA2) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM9663glassColor8_81llA2) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM9663glassColor8_81llA2) : 0.0f), $composer3, 0), C1301Dp.m8905constructorimpl(4), 0.0f, 2, null);
            Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, $composer3, ((RendererCapabilities.DECODER_SUPPORT_MASK >> 3) & 14) | ((RendererCapabilities.DECODER_SUPPORT_MASK >> 3) & 112));
            int i4 = (RendererCapabilities.DECODER_SUPPORT_MASK << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer3, modifierM1807paddingVpY3zN4$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i5 = ((i4 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function2 = constructor2;
                $composer3.createNode(function2);
            } else {
                function2 = constructor2;
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl2 = Updater.m5188constructorimpl($composer3);
            Updater.m5196setimpl(composerM5188constructorimpl2, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i6 = (i5 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            int i7 = ((RendererCapabilities.DECODER_SUPPORT_MASK >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 998339975, "C852@34461L39,854@34567L2,848@34311L415,863@34889L39,865@34995L2,859@34739L586:LibraryScreen.kt#t3x8p4");
            Modifier modifierM1872size3ABfNKs = SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(40));
            ComposerKt.sourceInformationMarkerStart($composer3, 863492333, "CC(remember):LibraryScreen.kt#9igjgp");
            Object objRememberedValue = $composer3.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object objMutableInteractionSource = InteractionSourceKt.MutableInteractionSource();
                $composer3.updateRememberedValue(objMutableInteractionSource);
                objRememberedValue = objMutableInteractionSource;
            }
            MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 863495688, "CC(remember):LibraryScreen.kt#9igjgp");
            Object objRememberedValue2 = $composer3.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda71
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Unit.INSTANCE;
                    }
                };
                $composer3.updateRememberedValue(obj);
                objRememberedValue2 = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierM1073clickableO2vRcR0 = ClickableKt.m1073clickableO2vRcR0(modifierM1872size3ABfNKs, mutableInteractionSource, null, (24 & 4) != 0, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : null, (Function0) objRememberedValue2);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap3 = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer3, modifierM1073clickableO2vRcR0);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i8 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function3 = constructor3;
                $composer3.createNode(function3);
            } else {
                function3 = constructor3;
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl3 = Updater.m5188constructorimpl($composer3);
            Updater.m5196setimpl(composerM5188constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i9 = (i8 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i10 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -1140017525, "C857@34656L56:LibraryScreen.kt#t3x8p4");
            m9658MeloXShareGlyphRPmYEkk(SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(22)), ColorKt.Color(4294914375L), $composer3, 54);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierM1872size3ABfNKs2 = SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(40));
            ComposerKt.sourceInformationMarkerStart($composer3, 863506029, "CC(remember):LibraryScreen.kt#9igjgp");
            Object objRememberedValue3 = $composer3.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object objMutableInteractionSource2 = InteractionSourceKt.MutableInteractionSource();
                $composer3.updateRememberedValue(objMutableInteractionSource2);
                objRememberedValue3 = objMutableInteractionSource2;
            }
            MutableInteractionSource mutableInteractionSource2 = (MutableInteractionSource) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 863509384, "CC(remember):LibraryScreen.kt#9igjgp");
            Object objRememberedValue4 = $composer3.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function0() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda72
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Unit.INSTANCE;
                    }
                };
                $composer3.updateRememberedValue(obj2);
                objRememberedValue4 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierM1073clickableO2vRcR1 = ClickableKt.m1073clickableO2vRcR0(modifierM1872size3ABfNKs2, mutableInteractionSource2, null, (24 & 4) != 0, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : null, (Function0) objRememberedValue4);
            Alignment center2 = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap4 = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer3, modifierM1073clickableO2vRcR1);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            int i11 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function4 = constructor4;
                $composer3.createNode(function4);
            } else {
                function4 = constructor4;
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl4 = Updater.m5188constructorimpl($composer3);
            Updater.m5196setimpl(composerM5188constructorimpl4, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            int i12 = (i11 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i13 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 221578679, "C868@35084L227:LibraryScreen.kt#t3x8p4");
            TextKt.m3912TextNvy7gAk("•••", null, ColorKt.Color(4294914375L), null, TextUnitKt.getSp(15), null, FontWeight.INSTANCE.getBold(), null, TextUnitKt.getSp(1), null, null, 0L, 0, false, 0, 0, null, null, $composer3, 102261126, 0, 261802);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda73
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return LibraryScreenKt.MeloXPlaylistToolbar_Iv8Zu3U$lambda$1(j, function5, $changed, (Composer) obj3, ((Integer) obj4).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXPlaylistToolbar_Iv8Zu3U$lambda$0$0(long $foreground, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C833@33735L48:LibraryScreen.kt#t3x8p4");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-400762056, $changed, -1, "com.lladlam.melox.ui.library.MeloXPlaylistToolbar.<anonymous>.<anonymous> (LibraryScreen.kt:833)");
            }
            m9650MeloXBackGlyphRPmYEkk(SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(22)), $foreground, $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: MeloXPlaylistSearchField-cf5BqRc, reason: not valid java name */
    private static final void m9654MeloXPlaylistSearchFieldcf5BqRc(final String value, final Function1<? super String, Unit> function1, final long foreground, Modifier modifier, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Composer $composer2;
        final Modifier modifier3;
        Modifier modifier4;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function2;
        Composer composer;
        Composer composer2;
        Composer composer3;
        Composer composer4;
        Composer composer5;
        int i2;
        Object obj;
        Composer $composer3 = $composer.startRestartGroup(-1129845788);
        ComposerKt.sourceInformation($composer3, "C(MeloXPlaylistSearchField)N(value,onValueChange,foreground:c#ui.graphics.Color,modifier)887@35517L1201:LibraryScreen.kt#t3x8p4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(value) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 32 : 16;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer3.changed(foreground) ? 256 : 128;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int $dirty2 = $dirty;
        if (!$composer3.shouldExecute(($dirty2 & 1171) != 1170, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            if (i3 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1129845788, $dirty2, -1, "com.lladlam.melox.ui.library.MeloXPlaylistSearchField (LibraryScreen.kt:886)");
            }
            Modifier modifierM1043backgroundbw27NRU$default = BackgroundKt.m1043backgroundbw27NRU$default(ClipKt.clip(SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(modifier4, 0.0f, 1, null), C1301Dp.m8905constructorimpl(44)), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(22))), m9663glassColor8_81llA(foreground), null, 2, null);
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer3, ((RendererCapabilities.DECODER_SUPPORT_MASK >> 3) & 14) | ((RendererCapabilities.DECODER_SUPPORT_MASK >> 3) & 112));
            int i4 = (RendererCapabilities.DECODER_SUPPORT_MASK << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1043backgroundbw27NRU$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i5 = ((i4 << 6) & 896) | 6;
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
            Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer3);
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i6 = (i5 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScope rowScope = RowScopeInstance.INSTANCE;
            int i7 = ((RendererCapabilities.DECODER_SUPPORT_MASK >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 884433334, "C895@35771L161,901@35941L771:LibraryScreen.kt#t3x8p4");
            m9657MeloXSearchGlyphRPmYEkk(SizeKt.m1872size3ABfNKs(PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(14), 0.0f, 0.0f, 0.0f, 14, null), C1301Dp.m8905constructorimpl(20)), foreground, $composer3, (($dirty2 >> 3) & 112) | 6);
            Modifier modifierM1807paddingVpY3zN4$default = PaddingKt.m1807paddingVpY3zN4$default(RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null), C1301Dp.m8905constructorimpl(10), 0.0f, 2, null);
            Alignment centerStart = Alignment.INSTANCE.getCenterStart();
            ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(centerStart, false);
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer3, modifierM1807paddingVpY3zN4$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i8 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function2 = constructor2;
                $composer3.createNode(function2);
            } else {
                function2 = constructor2;
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl2 = Updater.m5188constructorimpl($composer3);
            Updater.m5196setimpl(composerM5188constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i9 = (i8 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i10 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 485213581, "C914@36347L355:LibraryScreen.kt#t3x8p4");
            if (value.length() == 0) {
                $composer3.startReplaceGroup(485222353);
                ComposerKt.sourceInformation($composer3, "908@36169L151");
                composer5 = $composer3;
                composer2 = $composer3;
                composer3 = $composer3;
                composer4 = $composer3;
                i2 = 17;
                obj = null;
                TextKt.m3912TextNvy7gAk("在歌单中搜索", null, Color.m6066copywmQWz5c(foreground, (14 & 1) != 0 ? Color.m6070getAlphaimpl(foreground) : 0.46f, (14 & 2) != 0 ? Color.m6074getRedimpl(foreground) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(foreground) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(foreground) : 0.0f), null, TextUnitKt.getSp(17), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 24582, 0, 262122);
                composer = $composer3;
                composer.endReplaceGroup();
            } else {
                composer = $composer3;
                composer2 = $composer3;
                composer3 = $composer3;
                composer4 = $composer3;
                composer5 = $composer3;
                i2 = 17;
                obj = null;
                composer.startReplaceGroup(485398216);
                composer.endReplaceGroup();
            }
            Modifier modifier5 = modifier4;
            $composer2 = $composer3;
            Composer composer6 = composer;
            BasicTextFieldKt.BasicTextField(value, function1, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, obj), false, false, new TextStyle(foreground, TextUnitKt.getSp(i2), (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777212, (DefaultConstructorMarker) null), (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, (MutableInteractionSource) null, (Brush) null, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) null, composer6, ($dirty2 & 14) | 100663680 | ($dirty2 & 112), 0, 65240);
            ComposerKt.sourceInformationMarkerEnd(composer6);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd(composer5);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return LibraryScreenKt.MeloXPlaylistSearchField_cf5BqRc$lambda$1(value, function1, foreground, modifier3, $changed, i, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: MeloXStandardPlaylistHero-pAZo6Ak, reason: not valid java name */
    private static final void m9660MeloXStandardPlaylistHeropAZo6Ak(final NeteasePlaylistSummary playlist, final List<SearchSong> list, final long foreground, final long secondary, final Function0<Unit> function0, final Function0<Unit> function1, final SharedTransitionScope sharedTransitionScope, final AnimatedVisibilityScope animatedVisibilityScope, Composer $composer, final int $changed) {
        final NeteasePlaylistSummary neteasePlaylistSummary;
        List<SearchSong> list2;
        long j;
        long j2;
        Function0<Unit> function2;
        Function0<Unit> function3;
        final SharedTransitionScope sharedTransitionScope2;
        AnimatedVisibilityScope animatedVisibilityScope2;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(1535232965);
        ComposerKt.sourceInformation($composer3, "C(MeloXStandardPlaylistHero)N(playlist,tracks,foreground:c#ui.graphics.Color,secondary:c#ui.graphics.Color,onPlay,onShuffle,sharedTransitionScope,animatedVisibilityScope)940@37151L5271,940@37096L5326:LibraryScreen.kt#t3x8p4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            neteasePlaylistSummary = playlist;
            $dirty |= $composer3.changed(neteasePlaylistSummary) ? 4 : 2;
        } else {
            neteasePlaylistSummary = playlist;
        }
        if (($changed & 48) == 0) {
            list2 = list;
            $dirty |= $composer3.changedInstance(list2) ? 32 : 16;
        } else {
            list2 = list;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            j = foreground;
            $dirty |= $composer3.changed(j) ? 256 : 128;
        } else {
            j = foreground;
        }
        if (($changed & 3072) == 0) {
            j2 = secondary;
            $dirty |= $composer3.changed(j2) ? 2048 : 1024;
        } else {
            j2 = secondary;
        }
        if (($changed & 24576) == 0) {
            function2 = function0;
            $dirty |= $composer3.changedInstance(function2) ? 16384 : 8192;
        } else {
            function2 = function0;
        }
        if ((196608 & $changed) == 0) {
            function3 = function1;
            $dirty |= $composer3.changedInstance(function3) ? 131072 : 65536;
        } else {
            function3 = function1;
        }
        if ((1572864 & $changed) == 0) {
            sharedTransitionScope2 = sharedTransitionScope;
            $dirty |= $composer3.changed(sharedTransitionScope2) ? 1048576 : 524288;
        } else {
            sharedTransitionScope2 = sharedTransitionScope;
        }
        if ((12582912 & $changed) == 0) {
            animatedVisibilityScope2 = animatedVisibilityScope;
            $dirty |= $composer3.changedInstance(animatedVisibilityScope2) ? 8388608 : 4194304;
        } else {
            animatedVisibilityScope2 = animatedVisibilityScope;
        }
        if ($composer3.shouldExecute((4793491 & $dirty) != 4793490, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1535232965, $dirty, -1, "com.lladlam.melox.ui.library.MeloXStandardPlaylistHero (LibraryScreen.kt:939)");
            }
            final List<SearchSong> list3 = list2;
            final long j3 = j;
            final long j4 = j2;
            final Function0<Unit> function4 = function2;
            final Function0<Unit> function5 = function3;
            final AnimatedVisibilityScope animatedVisibilityScope3 = animatedVisibilityScope2;
            BoxWithConstraintsKt.BoxWithConstraints(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), null, false, ComposableLambdaKt.rememberComposableLambda(1945821083, true, new Function3() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda47
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return LibraryScreenKt.MeloXStandardPlaylistHero_pAZo6Ak$lambda$0(sharedTransitionScope2, neteasePlaylistSummary, j3, list3, j4, animatedVisibilityScope3, function5, function4, (BoxWithConstraintsScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer3, 54), $composer3, 3078, 6);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda48
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LibraryScreenKt.MeloXStandardPlaylistHero_pAZo6Ak$lambda$1(playlist, list, foreground, secondary, function0, function1, sharedTransitionScope, animatedVisibilityScope, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit MeloXStandardPlaylistHero_pAZo6Ak$lambda$0(SharedTransitionScope $sharedTransitionScope, NeteasePlaylistSummary $playlist, final long $foreground, List $tracks, long $secondary, AnimatedVisibilityScope $animatedVisibilityScope, Function0 $onShuffle, Function0 $onPlay, BoxWithConstraintsScope BoxWithConstraints, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        long jM6066copywmQWz5c;
        Function0<ComposeUiNode> function2;
        Function0<ComposeUiNode> function3;
        Intrinsics.checkNotNullParameter(BoxWithConstraints, "$this$BoxWithConstraints");
        ComposerKt.sourceInformation($composer, "C942@37219L5197:LibraryScreen.kt#t3x8p4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(BoxWithConstraints) ? 4 : 2;
        }
        if ($composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1945821083, $dirty, -1, "com.lladlam.melox.ui.library.MeloXStandardPlaylistHero.<anonymous> (LibraryScreen.kt:941)");
            }
            float artworkSize = ((C1301Dp) ComparisonsKt.minOf(C1301Dp.m8903boximpl(C1301Dp.m8905constructorimpl(BoxWithConstraints.mo1523getMaxWidthD9Ej5fM() * 0.68f)), C1301Dp.m8903boximpl(C1301Dp.m8905constructorimpl(300)))).m8919unboximpl();
            Modifier modifierM1809paddingqDBjuR0$default = PaddingKt.m1809paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, C1301Dp.m8905constructorimpl(26), 0.0f, C1301Dp.m8905constructorimpl(22), 5, null);
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM1809paddingqDBjuR0$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((390 << 3) & 112) << 6) & 896) | 6;
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
            int i3 = ((390 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1844812250, "C957@37809L635,973@38458L421,985@38893L327,995@39234L404,1005@39652L2754:LibraryScreen.kt#t3x8p4");
            $composer.startReplaceGroup(1741601502);
            ComposerKt.sourceInformation($composer, "*950@37573L118");
            Modifier modifierSharedElement$default = SharedTransitionScope.sharedElement$default($sharedTransitionScope, Modifier.INSTANCE, $sharedTransitionScope.rememberSharedContentState(playlistArtworkSharedKey($playlist.getId()), $composer, 0), $animatedVisibilityScope, null, null, false, 0.0f, null, 124, null);
            $composer.endReplaceGroup();
            String coverUrl = $playlist.getCoverUrl();
            ContentScale crop = ContentScale.INSTANCE.getCrop();
            Modifier modifierM1872size3ABfNKs = SizeKt.m1872size3ABfNKs(modifierSharedElement$default, artworkSize);
            float fM8905constructorimpl = C1301Dp.m8905constructorimpl(18);
            RoundedCornerShape roundedCornerShapeM2135RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(12));
            long jM6094getBlack0d7_KjU = Color.INSTANCE.m6094getBlack0d7_KjU();
            long jM6066copywmQWz5c2 = Color.m6066copywmQWz5c(jM6094getBlack0d7_KjU, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6094getBlack0d7_KjU) : 0.18f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6094getBlack0d7_KjU) : 0.0f);
            long jM6094getBlack0d7_KjU2 = Color.INSTANCE.m6094getBlack0d7_KjU();
            SingletonAsyncImageKt.m9399AsyncImage10Xjiaw(coverUrl, null, ClipKt.clip(ShadowKt.m5665shadows4CzXII(modifierM1872size3ABfNKs, fM8905constructorimpl, roundedCornerShapeM2135RoundedCornerShape0680j_4, false, jM6066copywmQWz5c2, Color.m6066copywmQWz5c(jM6094getBlack0d7_KjU2, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6094getBlack0d7_KjU2) : 0.18f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6094getBlack0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6094getBlack0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6094getBlack0d7_KjU2) : 0.0f)), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(12))), null, null, null, crop, 0.0f, null, 0, false, $composer, 1572912, 0, 1976);
            TextKt.m3912TextNvy7gAk($playlist.getName(), PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(24), C1301Dp.m8905constructorimpl(24), C1301Dp.m8905constructorimpl(24), 0.0f, 8, null), $foreground, null, TextUnitKt.getSp(22), null, FontWeight.INSTANCE.getBold(), null, 0L, null, TextAlign.m8751boximpl(TextAlign.INSTANCE.m8758getCentere0LSkKk()), TextUnitKt.getSp(27), TextOverflow.INSTANCE.m8816getEllipsisgIe3tQ8(), false, 2, 0, null, null, $composer, 1597440, 25008, 238504);
            String creatorName = $playlist.getCreatorName();
            if (StringsKt.isBlank(creatorName)) {
                creatorName = "网易云音乐";
            }
            TextKt.m3912TextNvy7gAk(creatorName, PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, C1301Dp.m8905constructorimpl(8), 0.0f, 0.0f, 13, null), $foreground, null, TextUnitKt.getSp(20), null, null, null, 0L, null, null, TextUnitKt.getSp(24), TextOverflow.INSTANCE.m8816getEllipsisgIe3tQ8(), false, 1, 0, null, null, $composer, 24624, 25008, 239592);
            TextKt.m3912TextNvy7gAk(($playlist.getTrackCount() > 0 ? $playlist.getTrackCount() : $tracks.size()) + " 首歌曲 · " + compactPlayCount($playlist.getPlayCount()) + " 次播放", PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, C1301Dp.m8905constructorimpl(7), 0.0f, 0.0f, 13, null), $secondary, null, TextUnitKt.getSp(15), null, FontWeight.INSTANCE.getMedium(), null, 0L, null, null, TextUnitKt.getSp(19), 0, false, 1, 0, null, null, $composer, 1597488, 24624, 243624);
            Modifier modifierM1809paddingqDBjuR0$default2 = PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, C1301Dp.m8905constructorimpl(17), 0.0f, 0.0f, 13, null);
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.Horizontal horizontalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(C1301Dp.m8905constructorimpl(14));
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalM1497spacedBy0680j_4, centerVertically, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierM1809paddingqDBjuR0$default2);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((438 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function1 = constructor2;
                $composer.createNode(function1);
            } else {
                function1 = constructor2;
                $composer.useNode();
            }
            Composer composerM5188constructorimpl2 = Updater.m5188constructorimpl($composer);
            Updater.m5196setimpl(composerM5188constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i6 = ((438 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1645021730, "C1015@40093L91,1010@39880L304,1024@40407L636,1019@40202L1736,1059@42089L2,1060@42111L281,1056@41956L436:LibraryScreen.kt#t3x8p4");
            m9651MeloXGlassCircleButtonCgHO2UQ($foreground, C1301Dp.m8905constructorimpl(54), !$tracks.isEmpty(), $onShuffle, ComposableLambdaKt.rememberComposableLambda(-1600593753, true, new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LibraryScreenKt.MeloXStandardPlaylistHero_pAZo6Ak$lambda$0$0$2$0($foreground, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 24624, 0);
            Modifier modifierClip = ClipKt.clip(SizeKt.m1858height3ABfNKs(SizeKt.m1877width3ABfNKs(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(140)), C1301Dp.m8905constructorimpl(50)), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(25)));
            RoundedCornerShape roundedCornerShapeM2135RoundedCornerShape0680j_5 = RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(C1301Dp.m8905constructorimpl(25));
            boolean z = !$tracks.isEmpty();
            long jM6105getWhite0d7_KjU = Color.m6069equalsimpl0($foreground, Color.INSTANCE.m6105getWhite0d7_KjU()) ? Color.INSTANCE.m6105getWhite0d7_KjU() : Color.INSTANCE.m6094getBlack0d7_KjU();
            if (Color.m6069equalsimpl0($foreground, Color.INSTANCE.m6105getWhite0d7_KjU())) {
                long jM6105getWhite0d7_KjU2 = Color.INSTANCE.m6105getWhite0d7_KjU();
                jM6066copywmQWz5c = Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU2, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU2) : 0.82f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU2) : 0.0f);
            } else {
                long jM6094getBlack0d7_KjU3 = Color.INSTANCE.m6094getBlack0d7_KjU();
                jM6066copywmQWz5c = Color.m6066copywmQWz5c(jM6094getBlack0d7_KjU3, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6094getBlack0d7_KjU3) : 0.82f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6094getBlack0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6094getBlack0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6094getBlack0d7_KjU3) : 0.0f);
            }
            Modifier modifierM1078clickableoSLSa3U$default = ClickableKt.m1078clickableoSLSa3U$default(MeloXBackdropComponentsKt.m9633meloXLiquidButtonNsDo4u0(modifierClip, roundedCornerShapeM2135RoundedCornerShape0680j_5, z, jM6105getWhite0d7_KjU, jM6066copywmQWz5c, 0.0f, C1301Dp.m8905constructorimpl(12), C1301Dp.m8905constructorimpl(20), $composer, 14155776, 16), !$tracks.isEmpty(), null, null, null, $onPlay, 14, null);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifierM1078clickableoSLSa3U$default);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i7 = ((((48 << 3) & 112) << 6) & 896) | 6;
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
            Composer composerM5188constructorimpl3 = Updater.m5188constructorimpl($composer);
            Updater.m5196setimpl(composerM5188constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i8 = (i7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i9 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -755468174, "C1039@41226L694:LibraryScreen.kt#t3x8p4");
            Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
            Arrangement.Horizontal horizontalM1497spacedBy0680j_5 = Arrangement.INSTANCE.m1497spacedBy0680j_4(C1301Dp.m8905constructorimpl(7));
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(horizontalM1497spacedBy0680j_5, centerVertically2, $composer, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap4 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            int i10 = ((((432 << 3) & 112) << 6) & 896) | 6;
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
            Composer composerM5188constructorimpl4 = Updater.m5188constructorimpl($composer);
            Updater.m5196setimpl(composerM5188constructorimpl4, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            int i11 = (i10 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            int i12 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 2039297293, "C1043@41427L180,1047@41632L266:LibraryScreen.kt#t3x8p4");
            m9653MeloXPlayGlyphRPmYEkk(SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(19)), Color.m6069equalsimpl0($foreground, Color.INSTANCE.m6105getWhite0d7_KjU()) ? Color.INSTANCE.m6094getBlack0d7_KjU() : Color.INSTANCE.m6105getWhite0d7_KjU(), $composer, 6);
            TextKt.m3912TextNvy7gAk("播放", null, Color.m6069equalsimpl0($foreground, Color.INSTANCE.m6105getWhite0d7_KjU()) ? Color.INSTANCE.m6094getBlack0d7_KjU() : Color.INSTANCE.m6105getWhite0d7_KjU(), null, TextUnitKt.getSp(19), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1597446, 0, 262058);
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
            float fM8905constructorimpl2 = C1301Dp.m8905constructorimpl(54);
            ComposerKt.sourceInformationMarkerStart($composer, -1992659689, "CC(remember):LibraryScreen.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Unit.INSTANCE;
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            m9651MeloXGlassCircleButtonCgHO2UQ($foreground, fM8905constructorimpl2, false, (Function0) objRememberedValue, ComposableLambdaKt.rememberComposableLambda(-289785890, true, new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return LibraryScreenKt.MeloXStandardPlaylistHero_pAZo6Ak$lambda$0$0$2$3($foreground, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 27696, 4);
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
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXStandardPlaylistHero_pAZo6Ak$lambda$0$0$2$0(long $foreground, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1016@40115L51:LibraryScreen.kt#t3x8p4");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1600593753, $changed, -1, "com.lladlam.melox.ui.library.MeloXStandardPlaylistHero.<anonymous>.<anonymous>.<anonymous>.<anonymous> (LibraryScreen.kt:1016)");
            }
            m9659MeloXShuffleGlyphRPmYEkk(SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(26)), $foreground, $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXStandardPlaylistHero_pAZo6Ak$lambda$0$0$2$3(long $foreground, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1061@42133L241:LibraryScreen.kt#t3x8p4");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-289785890, $changed, -1, "com.lladlam.melox.ui.library.MeloXStandardPlaylistHero.<anonymous>.<anonymous>.<anonymous>.<anonymous> (LibraryScreen.kt:1061)");
            }
            TextKt.m3912TextNvy7gAk("+", null, $foreground, null, TextUnitKt.getSp(34), null, FontWeight.INSTANCE.getLight(), null, 0L, null, null, TextUnitKt.getSp(34), 0, false, 0, 0, null, null, $composer, 1597446, 48, 260010);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: MeloXPlaylistTrackRow-FNF3uiM, reason: not valid java name */
    public static final void m9656MeloXPlaylistTrackRowFNF3uiM(final SearchSong song, final int index, final long foreground, final Function0<Unit> function0, Composer $composer, final int $changed) {
        Function0<Unit> function1;
        Function0<ComposeUiNode> function2;
        Composer $composer2 = $composer.startRestartGroup(177602024);
        ComposerKt.sourceInformation($composer2, "C(MeloXPlaylistTrackRow)N(song,index,foreground:c#ui.graphics.Color,onClick)1081@42567L1672:LibraryScreen.kt#t3x8p4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(song) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(index) ? 32 : 16;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer2.changed(foreground) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            function1 = function0;
            $dirty |= $composer2.changedInstance(function1) ? 2048 : 1024;
        } else {
            function1 = function0;
        }
        if ($composer2.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(177602024, $dirty, -1, "com.lladlam.melox.ui.library.MeloXPlaylistTrackRow (LibraryScreen.kt:1080)");
            }
            Modifier modifierM1808paddingqDBjuR0 = PaddingKt.m1808paddingqDBjuR0(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1301Dp.m8905constructorimpl(20), C1301Dp.m8905constructorimpl(11), C1301Dp.m8905constructorimpl(8), C1301Dp.m8905constructorimpl(11));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.Horizontal horizontalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(C1301Dp.m8905constructorimpl(4));
            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalM1497spacedBy0680j_4, centerVertically, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierM1808paddingqDBjuR0);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((432 << 3) & 112) << 6) & 896) | 6;
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
            Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer2);
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i3 = ((432 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, -71210452, "C1088@42838L845,1117@43850L39,1119@43948L2,1113@43692L541:LibraryScreen.kt#t3x8p4");
            Modifier modifierM1078clickableoSLSa3U$default = ClickableKt.m1078clickableoSLSa3U$default(RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null), false, null, null, null, function1, 15, null);
            Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
            Arrangement.Horizontal horizontalM1497spacedBy0680j_5 = Arrangement.INSTANCE.m1497spacedBy0680j_4(C1301Dp.m8905constructorimpl(12));
            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(horizontalM1497spacedBy0680j_5, centerVertically2, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer2, modifierM1078clickableoSLSa3U$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((432 << 3) & 112) << 6) & 896) | 6;
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
            Composer composerM5188constructorimpl2 = Updater.m5188constructorimpl($composer2);
            Updater.m5196setimpl(composerM5188constructorimpl2, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i6 = ((432 >> 6) & 112) | 6;
            RowScope rowScope2 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 951617701, "C1095@43099L274,1103@43386L287:LibraryScreen.kt#t3x8p4");
            TextKt.m3912TextNvy7gAk(String.valueOf(index + 1), SizeKt.m1877width3ABfNKs(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(40)), Color.m6066copywmQWz5c(foreground, (14 & 1) != 0 ? Color.m6070getAlphaimpl(foreground) : 0.48f, (14 & 2) != 0 ? Color.m6074getRedimpl(foreground) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(foreground) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(foreground) : 0.0f), null, TextUnitKt.getSp(20), null, null, null, 0L, null, TextAlign.m8751boximpl(TextAlign.INSTANCE.m8758getCentere0LSkKk()), 0L, 0, false, 1, 0, null, null, $composer2, 24624, 24576, 244712);
            TextKt.m3912TextNvy7gAk(song.getName(), RowScope.weight$default(rowScope2, Modifier.INSTANCE, 1.0f, false, 2, null), foreground, null, TextUnitKt.getSp(17), null, null, null, 0L, null, null, TextUnitKt.getSp(22), TextOverflow.INSTANCE.m8816getEllipsisgIe3tQ8(), false, 2, 0, null, null, $composer2, ($dirty & 896) | 24576, 25008, 239592);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierM1874sizeVpY3zN4 = SizeKt.m1874sizeVpY3zN4(Modifier.INSTANCE, C1301Dp.m8905constructorimpl(42), C1301Dp.m8905constructorimpl(44));
            ComposerKt.sourceInformationMarkerStart($composer2, -1664834069, "CC(remember):LibraryScreen.kt#9igjgp");
            Object objRememberedValue = $composer2.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object objMutableInteractionSource = InteractionSourceKt.MutableInteractionSource();
                $composer2.updateRememberedValue(objMutableInteractionSource);
                objRememberedValue = objMutableInteractionSource;
            }
            MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -1664830970, "CC(remember):LibraryScreen.kt#9igjgp");
            Object objRememberedValue2 = $composer2.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda68
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Unit.INSTANCE;
                    }
                };
                $composer2.updateRememberedValue(obj);
                objRememberedValue2 = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierM1073clickableO2vRcR0 = ClickableKt.m1073clickableO2vRcR0(modifierM1874sizeVpY3zN4, mutableInteractionSource, null, (24 & 4) != 0, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : null, (Function0) objRememberedValue2);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap3 = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer2, modifierM1073clickableO2vRcR0);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i7 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor3);
            } else {
                $composer2.useNode();
            }
            Composer composerM5188constructorimpl3 = Updater.m5188constructorimpl($composer2);
            Updater.m5196setimpl(composerM5188constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i8 = (i7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i9 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 431676607, "C1122@44025L198:LibraryScreen.kt#t3x8p4");
            TextKt.m3912TextNvy7gAk("•••", null, foreground, null, TextUnitKt.getSp(13), null, FontWeight.INSTANCE.getBold(), null, TextUnitKt.getSp(0.5d), null, null, 0L, 0, false, 0, 0, null, null, $composer2, ($dirty & 896) | 102260742, 0, 261802);
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
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda69
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return LibraryScreenKt.MeloXPlaylistTrackRow_FNF3uiM$lambda$1(song, index, foreground, function0, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: MeloXGlassCircleButton-CgHO2UQ, reason: not valid java name */
    private static final void m9651MeloXGlassCircleButtonCgHO2UQ(final long foreground, final float size, boolean enabled, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        long j;
        boolean z;
        Composer $composer2;
        final boolean enabled2;
        Function0<ComposeUiNode> function1;
        Composer $composer3 = $composer.startRestartGroup(-1190931102);
        ComposerKt.sourceInformation($composer3, "C(MeloXGlassCircleButton)N(foreground:c#ui.graphics.Color,size:c#ui.unit.Dp,enabled,onClick,content)1145@44553L257,1154@44906L39,1141@44452L656:LibraryScreen.kt#t3x8p4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            j = foreground;
            $dirty |= $composer3.changed(j) ? 4 : 2;
        } else {
            j = foreground;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(size) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
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
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 16384 : 8192;
        }
        if ($composer3.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            boolean enabled3 = i2 != 0 ? true : z;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1190931102, $dirty, -1, "com.lladlam.melox.ui.library.MeloXGlassCircleButton (LibraryScreen.kt:1140)");
            }
            Modifier modifierClip = ClipKt.clip(SizeKt.m1872size3ABfNKs(Modifier.INSTANCE, size), RoundedCornerShapeKt.getCircleShape());
            RoundedCornerShape circleShape = RoundedCornerShapeKt.getCircleShape();
            long jM9663glassColor8_81llA = m9663glassColor8_81llA(j);
            boolean enabled4 = enabled3;
            Modifier modifierM9633meloXLiquidButtonNsDo4u0 = MeloXBackdropComponentsKt.m9633meloXLiquidButtonNsDo4u0(modifierClip, circleShape, enabled4, 0L, Color.m6066copywmQWz5c(jM9663glassColor8_81llA, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM9663glassColor8_81llA) : 0.48f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM9663glassColor8_81llA) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM9663glassColor8_81llA) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM9663glassColor8_81llA) : 0.0f), 0.0f, C1301Dp.m8905constructorimpl(11), C1301Dp.m8905constructorimpl(18), $composer3, ($dirty & 896) | 14155776, 20);
            $composer2 = $composer3;
            ComposerKt.sourceInformationMarkerStart($composer2, -2023467671, "CC(remember):LibraryScreen.kt#9igjgp");
            Object objRememberedValue = $composer2.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object objMutableInteractionSource = InteractionSourceKt.MutableInteractionSource();
                $composer2.updateRememberedValue(objMutableInteractionSource);
                objRememberedValue = objMutableInteractionSource;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierM1073clickableO2vRcR0 = ClickableKt.m1073clickableO2vRcR0(modifierM9633meloXLiquidButtonNsDo4u0, (MutableInteractionSource) objRememberedValue, null, (24 & 4) != 0 ? true : enabled4, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : null, function0);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            int $dirty2 = $dirty;
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierM1073clickableO2vRcR0);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i3 = ((((48 << 3) & 112) << 6) & 896) | 6;
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
            Composer composerM5188constructorimpl = Updater.m5188constructorimpl($composer2);
            Updater.m5196setimpl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m5196setimpl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m5196setimpl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m5194reconcileimpl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m5196setimpl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i4 = (i3 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i5 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 248016242, "C1160@45093L9:LibraryScreen.kt#t3x8p4");
            function2.invoke($composer2, Integer.valueOf(($dirty2 >> 12) & 14));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            enabled2 = enabled4;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            enabled2 = z;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda74
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LibraryScreenKt.MeloXGlassCircleButton_CgHO2UQ$lambda$2(foreground, size, enabled2, function0, function2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: glassColor-8_81llA, reason: not valid java name */
    private static final long m9663glassColor8_81llA(long foreground) {
        if (Color.m6069equalsimpl0(foreground, Color.INSTANCE.m6105getWhite0d7_KjU())) {
            long jM6094getBlack0d7_KjU = Color.INSTANCE.m6094getBlack0d7_KjU();
            return Color.m6066copywmQWz5c(jM6094getBlack0d7_KjU, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6094getBlack0d7_KjU) : 0.22f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6094getBlack0d7_KjU) : 0.0f);
        }
        long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
        return Color.m6066copywmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.m6070getAlphaimpl(jM6105getWhite0d7_KjU) : 0.64f, (14 & 2) != 0 ? Color.m6074getRedimpl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m6073getGreenimpl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m6071getBlueimpl(jM6105getWhite0d7_KjU) : 0.0f);
    }

    /* JADX INFO: renamed from: MeloXPlayGlyph-RPmYEkk, reason: not valid java name */
    private static final void m9653MeloXPlayGlyphRPmYEkk(final Modifier modifier, final long color, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-866063366);
        ComposerKt.sourceInformation($composer2, "C(MeloXPlayGlyph)N(modifier,color:c#ui.graphics.Color)1170@45368L281,1170@45351L298:LibraryScreen.kt#t3x8p4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(color) ? 32 : 16;
        }
        if (!$composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-866063366, $dirty, -1, "com.lladlam.melox.ui.library.MeloXPlayGlyph (LibraryScreen.kt:1169)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 936763411, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean z = ($dirty & 112) == 32;
            Object objRememberedValue = $composer2.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda21
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return LibraryScreenKt.MeloXPlayGlyph_RPmYEkk$lambda$0$0(color, (DrawScope) obj2);
                    }
                };
                $composer2.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifier, (Function1) objRememberedValue, $composer2, $dirty & 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda22
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return LibraryScreenKt.MeloXPlayGlyph_RPmYEkk$lambda$1(modifier, color, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXPlayGlyph_RPmYEkk$lambda$0$0(long $color, DrawScope Canvas) {
        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
        Path path = AndroidPath_androidKt.Path();
        path.moveTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.24f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.12f);
        path.lineTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.86f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.5f);
        path.lineTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.24f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.88f);
        path.close();
        DrawScope.m6632drawPathLG529CI$default(Canvas, path, $color, 0.0f, null, null, 0, 60, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: MeloXBackGlyph-RPmYEkk, reason: not valid java name */
    private static final void m9650MeloXBackGlyphRPmYEkk(final Modifier modifier, final long color, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-1059616851);
        ComposerKt.sourceInformation($composer2, "C(MeloXBackGlyph)N(modifier,color:c#ui.graphics.Color)1183@45749L357,1183@45732L374:LibraryScreen.kt#t3x8p4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(color) ? 32 : 16;
        }
        if (!$composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1059616851, $dirty, -1, "com.lladlam.melox.ui.library.MeloXBackGlyph (LibraryScreen.kt:1182)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -384541870, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean z = ($dirty & 112) == 32;
            Object objRememberedValue = $composer2.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return LibraryScreenKt.MeloXBackGlyph_RPmYEkk$lambda$0$0(color, (DrawScope) obj2);
                    }
                };
                $composer2.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifier, (Function1) objRememberedValue, $composer2, $dirty & 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return LibraryScreenKt.MeloXBackGlyph_RPmYEkk$lambda$1(modifier, color, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXBackGlyph_RPmYEkk$lambda$0$0(long $color, DrawScope Canvas) {
        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
        float stroke = Size.m5891getMinDimensionimpl(Canvas.mo6642getSizeNHjbRc()) * 0.14f;
        Path Path = AndroidPath_androidKt.Path();
        Path.moveTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.67f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.14f);
        Path.lineTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.32f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.5f);
        Path.lineTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.67f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.86f);
        DrawScope.m6632drawPathLG529CI$default(Canvas, Path, $color, 0.0f, new Stroke(stroke, 0.0f, StrokeCap.INSTANCE.m6443getRoundKaPHkGw(), 0, null, 26, null), null, 0, 52, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: MeloXSearchGlyph-RPmYEkk, reason: not valid java name */
    private static final void m9657MeloXSearchGlyphRPmYEkk(final Modifier modifier, final long color, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(929965742);
        ComposerKt.sourceInformation($composer2, "C(MeloXSearchGlyph)N(modifier,color:c#ui.graphics.Color)1196@46208L533,1196@46191L550:LibraryScreen.kt#t3x8p4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(color) ? 32 : 16;
        }
        if (!$composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(929965742, $dirty, -1, "com.lladlam.melox.ui.library.MeloXSearchGlyph (LibraryScreen.kt:1195)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 343883523, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean z = ($dirty & 112) == 32;
            Object objRememberedValue = $composer2.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda66
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return LibraryScreenKt.MeloXSearchGlyph_RPmYEkk$lambda$0$0(color, (DrawScope) obj2);
                    }
                };
                $composer2.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifier, (Function1) objRememberedValue, $composer2, $dirty & 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda67
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return LibraryScreenKt.MeloXSearchGlyph_RPmYEkk$lambda$1(modifier, color, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXSearchGlyph_RPmYEkk$lambda$0$0(long $color, DrawScope Canvas) {
        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
        float stroke = Size.m5891getMinDimensionimpl(Canvas.mo6642getSizeNHjbRc()) * 0.11f;
        float fM5891getMinDimensionimpl = Size.m5891getMinDimensionimpl(Canvas.mo6642getSizeNHjbRc()) * 0.3f;
        float fIntBitsToFloat = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.42f;
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.4f;
        DrawScope.m6623drawCircleVaOC9Bg$default(Canvas, $color, fM5891getMinDimensionimpl, Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat2)) & 4294967295L)), 0.0f, new Stroke(stroke, 0.0f, 0, 0, null, 30, null), null, 0, LocationRequestCompat.QUALITY_LOW_POWER, null);
        float fIntBitsToFloat3 = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.62f;
        float fIntBitsToFloat4 = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.61f;
        long jM5815constructorimpl = Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat3)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat4)) & 4294967295L));
        float fIntBitsToFloat5 = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.86f;
        float fIntBitsToFloat6 = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.85f;
        DrawScope.m6628drawLineNGM6Ib0$default(Canvas, $color, jM5815constructorimpl, Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat5)) << 32) | (4294967295L & ((long) Float.floatToRawIntBits(fIntBitsToFloat6)))), stroke, StrokeCap.INSTANCE.m6443getRoundKaPHkGw(), null, 0.0f, null, 0, WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: MeloXShareGlyph-RPmYEkk, reason: not valid java name */
    private static final void m9658MeloXShareGlyphRPmYEkk(final Modifier modifier, final long color, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-723606041);
        ComposerKt.sourceInformation($composer2, "C(MeloXShareGlyph)N(modifier,color:c#ui.graphics.Color)1216@46842L985,1216@46825L1002:LibraryScreen.kt#t3x8p4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(color) ? 32 : 16;
        }
        if (!$composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-723606041, $dirty, -1, "com.lladlam.melox.ui.library.MeloXShareGlyph (LibraryScreen.kt:1215)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 399300000, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean z = ($dirty & 112) == 32;
            Object objRememberedValue = $composer2.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda56
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return LibraryScreenKt.MeloXShareGlyph_RPmYEkk$lambda$0$0(color, (DrawScope) obj2);
                    }
                };
                $composer2.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifier, (Function1) objRememberedValue, $composer2, $dirty & 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda57
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return LibraryScreenKt.MeloXShareGlyph_RPmYEkk$lambda$1(modifier, color, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXShareGlyph_RPmYEkk$lambda$0$0(long $color, DrawScope Canvas) {
        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
        float stroke = Size.m5891getMinDimensionimpl(Canvas.mo6642getSizeNHjbRc()) * 0.09f;
        float fIntBitsToFloat = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.2f;
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.4f;
        long jM5815constructorimpl = Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat2)) & 4294967295L));
        float fIntBitsToFloat3 = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.6f;
        float fIntBitsToFloat4 = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.5f;
        long jM5883constructorimpl = Size.m5883constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat3)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat4)) & 4294967295L));
        float fIntBitsToFloat5 = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.08f;
        DrawScope.m6638drawRoundRectuAw5IA$default(Canvas, $color, jM5815constructorimpl, jM5883constructorimpl, CornerRadius.m5777constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat5)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat5)) & 4294967295L)), new Stroke(stroke, 0.0f, 0, 0, null, 30, null), 0.0f, null, 0, 224, null);
        float fIntBitsToFloat6 = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.5f;
        float fIntBitsToFloat7 = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.63f;
        long jM5815constructorimpl2 = Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat6)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat7)) & 4294967295L));
        float fIntBitsToFloat8 = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.5f;
        float fIntBitsToFloat9 = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.12f;
        DrawScope.m6628drawLineNGM6Ib0$default(Canvas, $color, jM5815constructorimpl2, Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat8)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat9)) & 4294967295L)), stroke, StrokeCap.INSTANCE.m6443getRoundKaPHkGw(), null, 0.0f, null, 0, WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND, null);
        Path arrow = AndroidPath_androidKt.Path();
        arrow.moveTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.34f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.28f);
        arrow.lineTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.5f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.11f);
        arrow.lineTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.66f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.28f);
        DrawScope.m6632drawPathLG529CI$default(Canvas, arrow, $color, 0.0f, new Stroke(stroke, 0.0f, StrokeCap.INSTANCE.m6443getRoundKaPHkGw(), 0, null, 26, null), null, 0, 52, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: MeloXShuffleGlyph-RPmYEkk, reason: not valid java name */
    private static final void m9659MeloXShuffleGlyphRPmYEkk(final Modifier modifier, final long color, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-1960314687);
        ComposerKt.sourceInformation($composer2, "C(MeloXShuffleGlyph)N(modifier,color:c#ui.graphics.Color)1243@47930L1457,1243@47913L1474:LibraryScreen.kt#t3x8p4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(color) ? 32 : 16;
        }
        if (!$composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1960314687, $dirty, -1, "com.lladlam.melox.ui.library.MeloXShuffleGlyph (LibraryScreen.kt:1242)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 1628232722, "CC(remember):LibraryScreen.kt#9igjgp");
            boolean z = ($dirty & 112) == 32;
            Object objRememberedValue = $composer2.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda49
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return LibraryScreenKt.MeloXShuffleGlyph_RPmYEkk$lambda$0$0(color, (DrawScope) obj2);
                    }
                };
                $composer2.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifier, (Function1) objRememberedValue, $composer2, $dirty & 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.library.LibraryScreenKt$$ExternalSyntheticLambda50
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return LibraryScreenKt.MeloXShuffleGlyph_RPmYEkk$lambda$1(modifier, color, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXShuffleGlyph_RPmYEkk$lambda$0$0(long $color, DrawScope Canvas) {
        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
        float stroke = Size.m5891getMinDimensionimpl(Canvas.mo6642getSizeNHjbRc()) * 0.095f;
        Path top = AndroidPath_androidKt.Path();
        top.moveTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.1f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.28f);
        top.cubicTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.34f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.28f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.54f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.72f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.78f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.72f);
        Path Path = AndroidPath_androidKt.Path();
        Path.moveTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.1f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.72f);
        Path.cubicTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.34f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.72f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.54f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.28f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.78f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.28f);
        DrawScope.m6632drawPathLG529CI$default(Canvas, top, $color, 0.0f, new Stroke(stroke, 0.0f, StrokeCap.INSTANCE.m6443getRoundKaPHkGw(), 0, null, 26, null), null, 0, 52, null);
        DrawScope.m6632drawPathLG529CI$default(Canvas, Path, $color, 0.0f, new Stroke(stroke, 0.0f, StrokeCap.INSTANCE.m6443getRoundKaPHkGw(), 0, null, 26, null), null, 0, 52, null);
        Path a1 = AndroidPath_androidKt.Path();
        a1.moveTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.7f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.17f);
        a1.lineTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.89f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.28f);
        a1.lineTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.7f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.39f);
        Path Path2 = AndroidPath_androidKt.Path();
        Path2.moveTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.7f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.61f);
        Path2.lineTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.89f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.72f);
        Path2.lineTo(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * 0.7f, Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.83f);
        DrawScope.m6632drawPathLG529CI$default(Canvas, a1, $color, 0.0f, new Stroke(stroke, 0.0f, StrokeCap.INSTANCE.m6443getRoundKaPHkGw(), 0, null, 26, null), null, 0, 52, null);
        DrawScope.m6632drawPathLG529CI$default(Canvas, Path2, $color, 0.0f, new Stroke(stroke, 0.0f, StrokeCap.INSTANCE.m6443getRoundKaPHkGw(), 0, null, 26, null), null, 0, 52, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String playlistArtworkSharedKey(long playlistId) {
        return "library-playlist-artwork-" + playlistId;
    }

    private static final String formatDuration(long milliseconds) {
        long totalSeconds = RangesKt.coerceAtLeast(milliseconds, 0L) / 1000;
        String str = String.format("%d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(totalSeconds / 60), Long.valueOf(totalSeconds % 60)}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    private static final String compactPlayCount(long value) {
        if (value >= 100000000) {
            String str = String.format("%.1f 亿", Arrays.copyOf(new Object[]{Double.valueOf(value / 1.0E8d)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            return str;
        }
        if (value < Renderer.DEFAULT_DURATION_TO_PROGRESS_US) {
            return String.valueOf(value);
        }
        String str2 = String.format("%.1f 万", Arrays.copyOf(new Object[]{Double.valueOf(value / 10000.0d)}, 1));
        Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
        return str2;
    }

    private static final String optimized160Artwork(String url) {
        if (url != null) {
            String source = !StringsKt.isBlank(url) ? url : null;
            if (source != null) {
                if (!StringsKt.contains$default((CharSequence) source, (CharSequence) ".music.126.net", false, 2, (Object) null)) {
                    return source;
                }
                char separator = StringsKt.contains$default((CharSequence) source, '?', false, 2, (Object) null) ? Typography.amp : '?';
                return StringsKt.contains$default((CharSequence) source, (CharSequence) "param=", false, 2, (Object) null) ? source : source + separator + "param=160y160";
            }
        }
        return null;
    }
}
