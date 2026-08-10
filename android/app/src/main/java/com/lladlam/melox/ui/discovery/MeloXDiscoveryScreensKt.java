package com.lladlam.melox.p012ui.discovery;

import android.content.Context;
import androidx.activity.compose.BackHandlerKt;
import androidx.compose.animation.AnimatedContentKt;
import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.animation.AnimatedContentTransitionScope;
import androidx.compose.animation.ContentTransform;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.SharedTransitionScope;
import androidx.compose.animation.SharedTransitionScopeKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.AspectRatioKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
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
import androidx.compose.foundation.layout.WindowInsetsPadding_androidKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.TextKt;
import androidx.compose.material3.pulltorefresh.PullToRefreshKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.TileMode;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
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
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.SnapshotStateMap;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.window.core.layout.WindowSizeClass;
import coil3.compose.SingletonAsyncImageKt;
import com.lladlam.melox.core.account.NeteaseSessionStore;
import com.lladlam.melox.core.library.NeteaseDiscoveryCache;
import com.lladlam.melox.core.library.NeteaseHomeSnapshot;
import com.lladlam.melox.core.library.NeteaseLibraryClient;
import com.lladlam.melox.core.library.NeteasePlaylistSummary;
import com.lladlam.melox.p012ui.MeloXLayoutKt;
import com.lladlam.melox.p012ui.library.LibraryScreenKt;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.OkHttpClient;

/* JADX INFO: compiled from: MeloXDiscoveryScreens.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\u001a\u0015\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\u0010\n\u001a\u0015\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\u0010\n\u001a:\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2#\u0010\r\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00070\u000e\u0012\u0004\u0012\u00020\u00070\u000e¢\u0006\u0002\b\u0010H\u0003¢\u0006\u0002\u0010\u0011\u001a)\u0010\u0012\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00070\u000eH\u0003¢\u0006\u0002\u0010\u0014\u001a)\u0010\u0015\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00070\u000eH\u0003¢\u0006\u0002\u0010\u0014\u001a)\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00052\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00070\u000eH\u0003¢\u0006\u0002\u0010\u0019\u001a+\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u00052\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00070\u001eH\u0003¢\u0006\u0002\u0010\u001f\u001a!\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u00052\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0005H\u0003¢\u0006\u0002\u0010#\u001a-\u0010$\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u000f2\b\b\u0002\u0010%\u001a\u00020&2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00070\u001eH\u0003¢\u0006\u0002\u0010'\u001a?\u0010(\u001a\u00020\u00072\b\u0010)\u001a\u0004\u0018\u00010\u000f2\b\u0010*\u001a\u0004\u0018\u00010\u000f2\b\u0010+\u001a\u0004\u0018\u00010\u000f2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00070\u000eH\u0003¢\u0006\u0002\u0010,\u001a\u001f\u0010-\u001a\u00020\u00072\u0006\u0010.\u001a\u00020/2\b\b\u0002\u0010%\u001a\u00020&H\u0003¢\u0006\u0002\u00100\u001a7\u00101\u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u00052\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00070\u000eH\u0003¢\u0006\u0002\u00103\u001a\u0015\u00104\u001a\u00020\u00072\u0006\u00105\u001a\u00020\u0005H\u0003¢\u0006\u0002\u00106\u001a#\u00107\u001a\u00020\u00072\u0006\u00108\u001a\u00020\u00052\f\u00109\u001a\b\u0012\u0004\u0012\u00020\u00070\u001eH\u0003¢\u0006\u0002\u0010:\u001a\u0015\u0010;\u001a\u00020\u00072\u0006\u00108\u001a\u00020\u0005H\u0003¢\u0006\u0002\u00106\u001a\u0010\u0010<\u001a\u00020\u00052\u0006\u0010=\u001a\u00020\u0005H\u0002\u001a\u0010\u0010>\u001a\u00020\u00052\u0006\u0010=\u001a\u00020\u0005H\u0002\u001a\u0010\u0010?\u001a\u00020\u00052\u0006\u0010@\u001a\u00020AH\u0002\u001a\u0010\u0010B\u001a\u00020\u00052\u0006\u0010C\u001a\u00020DH\u0002\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006E²\u0006\f\u0010F\u001a\u0004\u0018\u00010\u000fX\u008a\u008e\u0002²\u0006\n\u0010=\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\u0010\u00102\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0004X\u008a\u008e\u0002²\u0006\n\u0010G\u001a\u00020HX\u008a\u008e\u0002²\u0006\f\u0010I\u001a\u0004\u0018\u00010\u0005X\u008a\u008e\u0002²\u0006\f\u0010J\u001a\u0004\u0018\u00010KX\u008a\u008e\u0002²\u0006\n\u0010G\u001a\u00020HX\u008a\u008e\u0002²\u0006\f\u0010I\u001a\u0004\u0018\u00010\u0005X\u008a\u008e\u0002"}, d2 = {"MeloXAccent", "Landroidx/compose/ui/graphics/Color;", "J", "ExploreCategories", "", "", "MeloXExploreScreen", "", "session", "Lcom/lladlam/melox/core/account/NeteaseSessionStore;", "(Lcom/lladlam/melox/core/account/NeteaseSessionStore;Landroidx/compose/runtime/Composer;I)V", "MeloXHomeScreen", "MeloXPlaylistBrowserHost", "browser", "Lkotlin/Function1;", "Lcom/lladlam/melox/core/library/NeteasePlaylistSummary;", "Landroidx/compose/runtime/Composable;", "(Lcom/lladlam/melox/core/account/NeteaseSessionStore;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "ExploreBrowser", "openPlaylist", "(Lcom/lladlam/melox/core/account/NeteaseSessionStore;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "HomeBrowser", "CategoryPicker", "selected", "select", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "FeaturedPlaylist", "playlist", "badge", "onClick", "Lkotlin/Function0;", "(Lcom/lladlam/melox/core/library/NeteasePlaylistSummary;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "SectionHeader", "title", "trailing", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)V", "PlaylistGridCard", "modifier", "Landroidx/compose/ui/Modifier;", "(Lcom/lladlam/melox/core/library/NeteasePlaylistSummary;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "HomeEditorialStrip", "daily", "fresh", "hot", "(Lcom/lladlam/melox/core/library/NeteasePlaylistSummary;Lcom/lladlam/melox/core/library/NeteasePlaylistSummary;Lcom/lladlam/melox/core/library/NeteasePlaylistSummary;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "EditorialGlyphIcon", "glyph", "Lcom/lladlam/melox/ui/discovery/EditorialGlyph;", "(Lcom/lladlam/melox/ui/discovery/EditorialGlyph;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "HomeMediaStrip", "playlists", "(Ljava/lang/String;Ljava/util/List;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "LoadingState", "text", "(Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "ErrorState", "message", "retry", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "EmptyState", "featuredBadge", "category", "collectionTitle", "compactCount", "count", "", "formatCompact", "value", "", "app", "selectedPlaylist", "loading", "", "errorMessage", "snapshot", "Lcom/lladlam/melox/core/library/NeteaseHomeSnapshot;"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class MeloXDiscoveryScreensKt {
    private static final long MeloXAccent = ColorKt.Color(4294914375L);
    private static final List<String> ExploreCategories = CollectionsKt.listOf((Object[]) new String[]{"推荐歌单", "排行榜", "精品歌单", "全部", "华语", "欧美", "流行", "摇滚", "民谣", "电子", "轻音乐", "影视原声", "ACG"});

    /* JADX INFO: compiled from: MeloXDiscoveryScreens.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EditorialGlyph.values().length];
            try {
                iArr[EditorialGlyph.Calendar.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[EditorialGlyph.Disc.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[EditorialGlyph.Chart.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit CategoryPicker$lambda$1(String str, Function1 function1, int i, Composer composer, int i2) {
        CategoryPicker(str, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit EditorialGlyphIcon$lambda$1(EditorialGlyph editorialGlyph, Modifier modifier, int i, int i2, Composer composer, int i3) {
        EditorialGlyphIcon(editorialGlyph, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit EmptyState$lambda$1(String str, int i, Composer composer, int i2) {
        EmptyState(str, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit ErrorState$lambda$1(String str, Function0 function0, int i, Composer composer, int i2) {
        ErrorState(str, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit ExploreBrowser$lambda$21(NeteaseSessionStore neteaseSessionStore, Function1 function1, int i, Composer composer, int i2) {
        ExploreBrowser(neteaseSessionStore, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit FeaturedPlaylist$lambda$1(NeteasePlaylistSummary neteasePlaylistSummary, String str, Function0 function0, int i, Composer composer, int i2) {
        FeaturedPlaylist(neteasePlaylistSummary, str, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit HomeBrowser$lambda$17(NeteaseSessionStore neteaseSessionStore, Function1 function1, int i, Composer composer, int i2) {
        HomeBrowser(neteaseSessionStore, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit HomeEditorialStrip$lambda$1(NeteasePlaylistSummary neteasePlaylistSummary, NeteasePlaylistSummary neteasePlaylistSummary2, NeteasePlaylistSummary neteasePlaylistSummary3, Function1 function1, int i, Composer composer, int i2) {
        HomeEditorialStrip(neteasePlaylistSummary, neteasePlaylistSummary2, neteasePlaylistSummary3, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit HomeMediaStrip$lambda$1(String str, List list, Function1 function1, int i, Composer composer, int i2) {
        HomeMediaStrip(str, list, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit LoadingState$lambda$1(String str, int i, Composer composer, int i2) {
        LoadingState(str, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXExploreScreen$lambda$1(NeteaseSessionStore neteaseSessionStore, int i, Composer composer, int i2) {
        MeloXExploreScreen(neteaseSessionStore, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXHomeScreen$lambda$1(NeteaseSessionStore neteaseSessionStore, int i, Composer composer, int i2) {
        MeloXHomeScreen(neteaseSessionStore, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MeloXPlaylistBrowserHost$lambda$6(NeteaseSessionStore neteaseSessionStore, Function3 function3, int i, Composer composer, int i2) {
        MeloXPlaylistBrowserHost(neteaseSessionStore, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit PlaylistGridCard$lambda$1(NeteasePlaylistSummary neteasePlaylistSummary, Modifier modifier, Function0 function0, int i, int i2, Composer composer, int i3) {
        PlaylistGridCard(neteasePlaylistSummary, modifier, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit SectionHeader$lambda$1(String str, String str2, int i, int i2, Composer composer, int i3) {
        SectionHeader(str, str2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void MeloXExploreScreen(final NeteaseSessionStore session, Composer $composer, final int $changed) {
        Intrinsics.checkNotNullParameter(session, "session");
        Composer $composer2 = $composer.startRestartGroup(1236952179);
        ComposerKt.sourceInformation($composer2, "C(MeloXExploreScreen)N(session)78@3598L69,78@3564L103:MeloXDiscoveryScreens.kt#301xg3");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(session) ? 4 : 2;
        }
        if (!$composer2.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1236952179, $dirty, -1, "com.lladlam.melox.ui.discovery.MeloXExploreScreen (MeloXDiscoveryScreens.kt:77)");
            }
            MeloXPlaylistBrowserHost(session, ComposableLambdaKt.rememberComposableLambda(1061449031, true, new Function3() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda33
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return MeloXDiscoveryScreensKt.MeloXExploreScreen$lambda$0(session, (Function1) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer2, 54), $composer2, ($dirty & 14) | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda34
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXDiscoveryScreensKt.MeloXExploreScreen$lambda$1(session, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit MeloXExploreScreen$lambda$0(NeteaseSessionStore $session, Function1 openPlaylist, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(openPlaylist, "openPlaylist");
        ComposerKt.sourceInformation($composer, "CN(openPlaylist)79@3624L37:MeloXDiscoveryScreens.kt#301xg3");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changedInstance(openPlaylist) ? 4 : 2;
        }
        if (!$composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1061449031, $dirty, -1, "com.lladlam.melox.ui.discovery.MeloXExploreScreen.<anonymous> (MeloXDiscoveryScreens.kt:79)");
            }
            ExploreBrowser($session, openPlaylist, $composer, ($dirty << 3) & 112);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    public static final void MeloXHomeScreen(final NeteaseSessionStore session, Composer $composer, final int $changed) {
        Intrinsics.checkNotNullParameter(session, "session");
        Composer $composer2 = $composer.startRestartGroup(1445719997);
        ComposerKt.sourceInformation($composer2, "C(MeloXHomeScreen)N(session)85@3773L66,85@3739L100:MeloXDiscoveryScreens.kt#301xg3");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(session) ? 4 : 2;
        }
        if (!$composer2.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1445719997, $dirty, -1, "com.lladlam.melox.ui.discovery.MeloXHomeScreen (MeloXDiscoveryScreens.kt:84)");
            }
            MeloXPlaylistBrowserHost(session, ComposableLambdaKt.rememberComposableLambda(556185449, true, new Function3() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda43
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return MeloXDiscoveryScreensKt.MeloXHomeScreen$lambda$0(session, (Function1) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer2, 54), $composer2, ($dirty & 14) | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda44
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXDiscoveryScreensKt.MeloXHomeScreen$lambda$1(session, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit MeloXHomeScreen$lambda$0(NeteaseSessionStore $session, Function1 openPlaylist, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(openPlaylist, "openPlaylist");
        ComposerKt.sourceInformation($composer, "CN(openPlaylist)86@3799L34:MeloXDiscoveryScreens.kt#301xg3");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changedInstance(openPlaylist) ? 4 : 2;
        }
        if (!$composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(556185449, $dirty, -1, "com.lladlam.melox.ui.discovery.MeloXHomeScreen.<anonymous> (MeloXDiscoveryScreens.kt:86)");
            }
            HomeBrowser($session, openPlaylist, $composer, ($dirty << 3) & 112);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final void MeloXPlaylistBrowserHost(final NeteaseSessionStore neteaseSessionStore, final Function3<? super Function1<? super NeteasePlaylistSummary, Unit>, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(1622934098);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(MeloXPlaylistBrowserHost)N(session,browser)96@4081L7,97@4125L138,100@4292L86,104@4432L27,104@4384L75,106@4523L792,106@4465L850:MeloXDiscoveryScreens.kt#301xg3");
        int i2 = i;
        int i3 = 2;
        if ((i & 6) == 0) {
            i2 |= composerStartRestartGroup.changed(neteaseSessionStore) ? 4 : 2;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function3) ? 32 : 16;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1622934098, i2, -1, "com.lladlam.melox.ui.discovery.MeloXPlaylistBrowserHost (MeloXDiscoveryScreens.kt:95)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Context applicationContext = ((Context) objConsume).getApplicationContext();
            Object cookie = neteaseSessionStore.getCookie();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 566546748, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(cookie) | composerStartRestartGroup.changed(applicationContext);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            OkHttpClient okHttpClient = null;
            Object[] objArr = 0;
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object neteaseLibraryClient = new NeteaseLibraryClient(new Function0() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda22
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MeloXDiscoveryScreensKt.MeloXPlaylistBrowserHost$lambda$0$0(applicationContext);
                    }
                }, okHttpClient, i3, objArr == true ? 1 : 0);
                composerStartRestartGroup.updateRememberedValue(neteaseLibraryClient);
                objRememberedValue = neteaseLibraryClient;
            }
            final NeteaseLibraryClient neteaseLibraryClient2 = (NeteaseLibraryClient) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Object cookie2 = neteaseSessionStore.getCookie();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 566552040, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            boolean zChanged2 = composerStartRestartGroup.changed(cookie2);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default);
                objRememberedValue2 = objMutableStateOf$default;
            }
            final MutableState mutableState = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            boolean z = MeloXPlaylistBrowserHost$lambda$2(mutableState) != null;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 566556461, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            boolean zChanged3 = composerStartRestartGroup.changed(mutableState);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda23
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MeloXDiscoveryScreensKt.MeloXPlaylistBrowserHost$lambda$4$0(mutableState);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(obj);
                objRememberedValue3 = obj;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            BackHandlerKt.BackHandler(z, (Function0) objRememberedValue3, composerStartRestartGroup, 0, 0);
            SharedTransitionScopeKt.SharedTransitionLayout(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ComposableLambdaKt.rememberComposableLambda(1861354452, true, new Function3() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda24
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return MeloXDiscoveryScreensKt.MeloXPlaylistBrowserHost$lambda$5(mutableState, function3, neteaseLibraryClient2, (SharedTransitionScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, 54, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda25
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return MeloXDiscoveryScreensKt.MeloXPlaylistBrowserHost$lambda$6(neteaseSessionStore, function3, i, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String MeloXPlaylistBrowserHost$lambda$0$0(Context $appContext) {
        NeteaseSessionStore.Companion companion = NeteaseSessionStore.INSTANCE;
        Intrinsics.checkNotNull($appContext);
        return companion.readCookie($appContext);
    }

    private static final NeteasePlaylistSummary MeloXPlaylistBrowserHost$lambda$2(MutableState<NeteasePlaylistSummary> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXPlaylistBrowserHost$lambda$4$0(MutableState $selectedPlaylist$delegate) {
        $selectedPlaylist$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    static final Unit MeloXPlaylistBrowserHost$lambda$5(final MutableState $selectedPlaylist$delegate, final Function3 $browser, final NeteaseLibraryClient $client, final SharedTransitionScope SharedTransitionLayout, Composer $composer, int $changed) throws Throwable {
        Intrinsics.checkNotNullParameter(SharedTransitionLayout, "$this$SharedTransitionLayout");
        ComposerKt.sourceInformation($composer, "C111@4701L35,112@4763L28,114@4852L457,108@4564L745:MeloXDiscoveryScreens.kt#301xg3");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(SharedTransitionLayout) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if ($composer.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1861354452, $dirty2, -1, "com.lladlam.melox.ui.discovery.MeloXPlaylistBrowserHost.<anonymous> (MeloXDiscoveryScreens.kt:107)");
            }
            NeteasePlaylistSummary neteasePlaylistSummaryMeloXPlaylistBrowserHost$lambda$2 = MeloXPlaylistBrowserHost$lambda$2($selectedPlaylist$delegate);
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 2055238967, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return MeloXDiscoveryScreensKt.MeloXPlaylistBrowserHost$lambda$5$0$0((AnimatedContentTransitionScope) obj2);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            Function1 function1 = (Function1) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, 2055240944, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            Object objRememberedValue2 = $composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function1() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return MeloXDiscoveryScreensKt.MeloXPlaylistBrowserHost$lambda$5$1$0((NeteasePlaylistSummary) obj3);
                    }
                };
                $composer.updateRememberedValue(obj2);
                objRememberedValue2 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            AnimatedContentKt.AnimatedContent(neteasePlaylistSummaryMeloXPlaylistBrowserHost$lambda$2, modifierFillMaxSize$default, function1, null, "discovery-playlist-detail", (Function1) objRememberedValue2, ComposableLambdaKt.rememberComposableLambda(1739953042, true, new Function4() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function4
                public final Object invoke(Object obj3, Object obj4, Object obj5, Object obj6) {
                    return MeloXDiscoveryScreensKt.MeloXPlaylistBrowserHost$lambda$5$2($browser, $selectedPlaylist$delegate, $client, SharedTransitionLayout, (AnimatedContentScope) obj3, (NeteasePlaylistSummary) obj4, (Composer) obj5, ((Integer) obj6).intValue());
                }
            }, $composer, 54), $composer, 1794480, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ContentTransform MeloXPlaylistBrowserHost$lambda$5$0$0(AnimatedContentTransitionScope AnimatedContent) {
        Intrinsics.checkNotNullParameter(AnimatedContent, "$this$AnimatedContent");
        return AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null), EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object MeloXPlaylistBrowserHost$lambda$5$1$0(NeteasePlaylistSummary it) {
        return Long.valueOf(it != null ? it.getId() : Long.MIN_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXPlaylistBrowserHost$lambda$5$2(Function3 $browser, final MutableState $selectedPlaylist$delegate, NeteaseLibraryClient $client, SharedTransitionScope $sharedScope, AnimatedContentScope AnimatedContent, NeteasePlaylistSummary playlist, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(AnimatedContent, "$this$AnimatedContent");
        ComposerKt.sourceInformation($composer, "CN(playlist):MeloXDiscoveryScreens.kt#301xg3");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1739953042, $changed, -1, "com.lladlam.melox.ui.discovery.MeloXPlaylistBrowserHost.<anonymous>.<anonymous> (MeloXDiscoveryScreens.kt:115)");
        }
        if (playlist == null) {
            $composer.startReplaceGroup(-1019171569);
            ComposerKt.sourceInformation($composer, "116@4926L25,116@4918L33");
            ComposerKt.sourceInformationMarkerStart($composer, 1352597611, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            boolean zChanged = $composer.changed($selectedPlaylist$delegate);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return MeloXDiscoveryScreensKt.MeloXPlaylistBrowserHost$lambda$5$2$0$0($selectedPlaylist$delegate, (NeteasePlaylistSummary) obj2);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            $browser.invoke((Function1) objRememberedValue, $composer, 0);
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(-1019092984);
            ComposerKt.sourceInformation($composer, "121@5130L27,118@4989L296");
            ComposerKt.sourceInformationMarkerStart($composer, 1352604141, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            boolean zChanged2 = $composer.changed($selectedPlaylist$delegate);
            Object objRememberedValue2 = $composer.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function0() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MeloXDiscoveryScreensKt.MeloXPlaylistBrowserHost$lambda$5$2$1$0($selectedPlaylist$delegate);
                    }
                };
                $composer.updateRememberedValue(obj2);
                objRememberedValue2 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            LibraryScreenKt.MeloXPlaylistDetailScreen(playlist, $client, (Function0) objRememberedValue2, $sharedScope, AnimatedContent, $composer, (($changed >> 3) & 14) | (($changed << 12) & 57344));
            $composer.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXPlaylistBrowserHost$lambda$5$2$0$0(MutableState $selectedPlaylist$delegate, NeteasePlaylistSummary it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $selectedPlaylist$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MeloXPlaylistBrowserHost$lambda$5$2$1$0(MutableState $selectedPlaylist$delegate) {
        $selectedPlaylist$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final void ExploreBrowser(final NeteaseSessionStore neteaseSessionStore, Function1<? super NeteasePlaylistSummary, Unit> function1, Composer composer, final int i) {
        Composer composer2;
        final NeteaseDiscoveryCache neteaseDiscoveryCache;
        final Function1<? super NeteasePlaylistSummary, Unit> function2 = function1;
        Composer composerStartRestartGroup = composer.startRestartGroup(729570067);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ExploreBrowser)N(session,openPlaylist)135@5483L7,136@5526L24,137@5568L138,140@5723L58,141@5804L70,142@5895L54,143@5971L70,144@6061L34,145@6120L42,164@6799L374,164@6758L415,179@7275L42,181@7368L2943,177@7179L3132:MeloXDiscoveryScreens.kt#301xg3");
        int i2 = i;
        if ((i & 6) == 0) {
            i2 |= composerStartRestartGroup.changed(neteaseSessionStore) ? 4 : 2;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        int i3 = i2;
        if (composerStartRestartGroup.shouldExecute((i3 & 19) != 18, i3 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(729570067, i3, -1, "com.lladlam.melox.ui.discovery.ExploreBrowser (MeloXDiscoveryScreens.kt:134)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Context applicationContext = ((Context) objConsume).getApplicationContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)N(getContext)616@28039L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 683736516, "CC(remember):Effects.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                CoroutineScope coroutineScopeCreateCompositionCoroutineScope = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                composerStartRestartGroup.updateRememberedValue(coroutineScopeCreateCompositionCoroutineScope);
                objRememberedValue = coroutineScopeCreateCompositionCoroutineScope;
            }
            final CoroutineScope coroutineScope = (CoroutineScope) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            String cookie = neteaseSessionStore.getCookie();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1037172605, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(cookie) | composerStartRestartGroup.changed(applicationContext);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            OkHttpClient okHttpClient = null;
            if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                NeteaseLibraryClient neteaseLibraryClient = new NeteaseLibraryClient(new Function0() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MeloXDiscoveryScreensKt.ExploreBrowser$lambda$0$0(applicationContext);
                    }
                }, okHttpClient, 2, null == true ? 1 : 0);
                composerStartRestartGroup.updateRememberedValue(neteaseLibraryClient);
                objRememberedValue2 = neteaseLibraryClient;
            }
            final NeteaseLibraryClient neteaseLibraryClient2 = (NeteaseLibraryClient) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1037177485, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            boolean zChanged2 = composerStartRestartGroup.changed(applicationContext);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Intrinsics.checkNotNull(applicationContext);
                NeteaseDiscoveryCache neteaseDiscoveryCache2 = new NeteaseDiscoveryCache(applicationContext);
                composerStartRestartGroup.updateRememberedValue(neteaseDiscoveryCache2);
                objRememberedValue3 = neteaseDiscoveryCache2;
            }
            NeteaseDiscoveryCache neteaseDiscoveryCache3 = (NeteaseDiscoveryCache) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1037180089, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                SnapshotStateMap snapshotStateMapMutableStateMapOf = SnapshotStateKt.mutableStateMapOf();
                composerStartRestartGroup.updateRememberedValue(snapshotStateMapMutableStateMapOf);
                objRememberedValue4 = snapshotStateMapMutableStateMapOf;
            }
            final SnapshotStateMap snapshotStateMap = (SnapshotStateMap) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1037182985, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(CollectionsKt.first((List) ExploreCategories), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default);
                objRememberedValue5 = mutableStateMutableStateOf$default;
            }
            final MutableState mutableState = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1037185433, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(CollectionsKt.emptyList(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default2);
                objRememberedValue6 = mutableStateMutableStateOf$default2;
            }
            final MutableState mutableState2 = (MutableState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1037188277, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default3);
                objRememberedValue7 = mutableStateMutableStateOf$default3;
            }
            final MutableState mutableState3 = (MutableState) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1037190173, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default4);
                objRememberedValue8 = mutableStateMutableStateOf$default4;
            }
            final MutableState mutableState4 = (MutableState) objRememberedValue8;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            String strExploreBrowser$lambda$4 = ExploreBrowser$lambda$4(mutableState);
            String cookie2 = neteaseSessionStore.getCookie();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1037212233, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(neteaseDiscoveryCache3) | composerStartRestartGroup.changedInstance(neteaseLibraryClient2);
            Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                MeloXDiscoveryScreensKt$ExploreBrowser$1$1 meloXDiscoveryScreensKt$ExploreBrowser$1$1 = new MeloXDiscoveryScreensKt$ExploreBrowser$1$1(snapshotStateMap, neteaseDiscoveryCache3, mutableState, mutableState2, mutableState3, mutableState4, neteaseLibraryClient2, null);
                composerStartRestartGroup.updateRememberedValue(meloXDiscoveryScreensKt$ExploreBrowser$1$1);
                objRememberedValue9 = meloXDiscoveryScreensKt$ExploreBrowser$1$1;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.LaunchedEffect(strExploreBrowser$lambda$4, cookie2, (Function2) objRememberedValue9, composerStartRestartGroup, 0);
            boolean z = ExploreBrowser$lambda$10(mutableState3) && !ExploreBrowser$lambda$7(mutableState2).isEmpty();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1037227133, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(coroutineScope) | composerStartRestartGroup.changedInstance(neteaseLibraryClient2) | composerStartRestartGroup.changedInstance(neteaseDiscoveryCache3);
            Object objRememberedValue10 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                neteaseDiscoveryCache = neteaseDiscoveryCache3;
                Function0 function0 = new Function0() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MeloXDiscoveryScreensKt.ExploreBrowser$lambda$19$0(coroutineScope, snapshotStateMap, mutableState, mutableState2, mutableState3, mutableState4, neteaseLibraryClient2, neteaseDiscoveryCache);
                    }
                };
                snapshotStateMap = snapshotStateMap;
                composerStartRestartGroup.updateRememberedValue(function0);
                objRememberedValue10 = function0;
            } else {
                neteaseDiscoveryCache = neteaseDiscoveryCache3;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            function2 = function1;
            final SnapshotStateMap snapshotStateMap2 = snapshotStateMap;
            final NeteaseDiscoveryCache neteaseDiscoveryCache4 = neteaseDiscoveryCache;
            composer2 = composerStartRestartGroup;
            PullToRefreshKt.PullToRefreshBox(z, (Function0) objRememberedValue10, SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, null, null, ComposableLambdaKt.rememberComposableLambda(-1409099783, true, new Function3() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return MeloXDiscoveryScreensKt.ExploreBrowser$lambda$20(function2, coroutineScope, neteaseLibraryClient2, neteaseDiscoveryCache4, mutableState, snapshotStateMap2, mutableState2, mutableState4, mutableState3, (BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composerStartRestartGroup, 54), composer2, 1573248, 56);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXDiscoveryScreensKt.ExploreBrowser$lambda$21(neteaseSessionStore, function2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String ExploreBrowser$lambda$0$0(Context $appContext) {
        NeteaseSessionStore.Companion companion = NeteaseSessionStore.INSTANCE;
        Intrinsics.checkNotNull($appContext);
        return companion.readCookie($appContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String ExploreBrowser$lambda$4(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<NeteasePlaylistSummary> ExploreBrowser$lambda$7(MutableState<List<NeteasePlaylistSummary>> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean ExploreBrowser$lambda$10(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void ExploreBrowser$lambda$11(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String ExploreBrowser$lambda$13(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:39:0x0119  */
    /* JADX WARN: Code duplicated, block: B:41:0x0167 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:42:0x0168  */
    /* JADX WARN: Code duplicated, block: B:44:0x016b  */
    /* JADX WARN: Code duplicated, block: B:47:0x0174  */
    /* JADX WARN: Code duplicated, block: B:49:0x017b  */
    /* JADX WARN: Code duplicated, block: B:7:0x0020  */
    public static final Object ExploreBrowser$refresh(SnapshotStateMap<String, List<NeteasePlaylistSummary>> snapshotStateMap, MutableState<String> mutableState, MutableState<List<NeteasePlaylistSummary>> mutableState2, MutableState<Boolean> mutableState3, MutableState<String> mutableState4, NeteaseLibraryClient client, NeteaseDiscoveryCache cache, boolean force, Continuation<? super Unit> continuation) {
        MeloXDiscoveryScreensKt$ExploreBrowser$refresh$1 meloXDiscoveryScreensKt$ExploreBrowser$refresh$1;
        MutableState<String> mutableState5;
        NeteaseLibraryClient client2;
        NeteaseDiscoveryCache cache2;
        Object objDiscoveryPlaylists$default;
        Object objM9714constructorimpl;
        NeteaseLibraryClient client3;
        MutableState<String> mutableState6;
        Object obj;
        NeteaseDiscoveryCache cache3;
        List<NeteasePlaylistSummary> list;
        String strExploreBrowser$lambda$4;
        Throwable thM9717exceptionOrNullimpl;
        String message;
        SnapshotStateMap<String, List<NeteasePlaylistSummary>> snapshotStateMap2 = snapshotStateMap;
        MutableState<List<NeteasePlaylistSummary>> mutableState7 = mutableState2;
        MutableState<Boolean> mutableState8 = mutableState3;
        MutableState<String> mutableState9 = mutableState4;
        boolean force2 = force;
        if (continuation instanceof MeloXDiscoveryScreensKt$ExploreBrowser$refresh$1) {
            meloXDiscoveryScreensKt$ExploreBrowser$refresh$1 = (MeloXDiscoveryScreensKt$ExploreBrowser$refresh$1) continuation;
            if ((meloXDiscoveryScreensKt$ExploreBrowser$refresh$1.label & Integer.MIN_VALUE) != 0) {
                meloXDiscoveryScreensKt$ExploreBrowser$refresh$1.label -= Integer.MIN_VALUE;
            } else {
                meloXDiscoveryScreensKt$ExploreBrowser$refresh$1 = new MeloXDiscoveryScreensKt$ExploreBrowser$refresh$1(continuation);
            }
        } else {
            meloXDiscoveryScreensKt$ExploreBrowser$refresh$1 = new MeloXDiscoveryScreensKt$ExploreBrowser$refresh$1(continuation);
        }
        MeloXDiscoveryScreensKt$ExploreBrowser$refresh$1 meloXDiscoveryScreensKt$ExploreBrowser$refresh$2 = meloXDiscoveryScreensKt$ExploreBrowser$refresh$1;
        Object $result = meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (!force2 && snapshotStateMap2.containsKey(ExploreBrowser$lambda$4(mutableState))) {
                    List<NeteasePlaylistSummary> listEmptyList = snapshotStateMap2.get(ExploreBrowser$lambda$4(mutableState));
                    if (listEmptyList == null) {
                        listEmptyList = CollectionsKt.emptyList();
                    }
                    mutableState7.setValue(listEmptyList);
                    return Unit.INSTANCE;
                }
                ExploreBrowser$lambda$11(mutableState8, true);
                mutableState9.setValue(null);
                try {
                    Result.Companion companion = Result.INSTANCE;
                    String strExploreBrowser$lambda$5 = ExploreBrowser$lambda$4(mutableState);
                    meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$0 = snapshotStateMap2;
                    meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$1 = mutableState;
                    meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$2 = mutableState7;
                    meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$3 = mutableState8;
                    meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$4 = mutableState9;
                    meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$5 = SpillingKt.nullOutSpilledVariable(client);
                    meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$6 = cache;
                    meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.Z$0 = force2;
                    meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.I$0 = 0;
                    meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.label = 1;
                    objDiscoveryPlaylists$default = NeteaseLibraryClient.discoveryPlaylists$default(client, strExploreBrowser$lambda$5, 0, meloXDiscoveryScreensKt$ExploreBrowser$refresh$2, 2, null);
                    if (objDiscoveryPlaylists$default == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    mutableState5 = mutableState;
                    client2 = client;
                    cache2 = cache;
                    objM9714constructorimpl = Result.constructor_impl((List) objDiscoveryPlaylists$default);
                    client3 = client2;
                    mutableState6 = mutableState5;
                    obj = objM9714constructorimpl;
                    cache3 = cache2;
                    if (Result.isSuccess_impl(obj)) {
                        list = (List) obj;
                        SnapshotStateMap<String, List<NeteasePlaylistSummary>> snapshotStateMap3 = snapshotStateMap2;
                        snapshotStateMap2.put(ExploreBrowser$lambda$4(mutableState6), list);
                        mutableState7.setValue(list);
                        strExploreBrowser$lambda$4 = ExploreBrowser$lambda$4(mutableState6);
                        meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$0 = SpillingKt.nullOutSpilledVariable(snapshotStateMap3);
                        meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$1 = SpillingKt.nullOutSpilledVariable(mutableState6);
                        meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$2 = SpillingKt.nullOutSpilledVariable(mutableState7);
                        meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$3 = mutableState8;
                        meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$4 = mutableState9;
                        meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$5 = SpillingKt.nullOutSpilledVariable(client3);
                        meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$6 = SpillingKt.nullOutSpilledVariable(cache3);
                        meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$7 = obj;
                        meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$8 = SpillingKt.nullOutSpilledVariable(list);
                        meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.Z$0 = force2;
                        meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.I$0 = 0;
                        meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.label = 2;
                        if (cache3.saveCategory(strExploreBrowser$lambda$4, list, meloXDiscoveryScreensKt$ExploreBrowser$refresh$2) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    thM9717exceptionOrNullimpl = Result.exceptionOrNull_impl(obj);
                    if (thM9717exceptionOrNullimpl != null) {
                        message = thM9717exceptionOrNullimpl.getMessage();
                        if (message == null) {
                            message = "发现页加载失败";
                        }
                        mutableState9.setValue(message);
                    }
                    ExploreBrowser$lambda$11(mutableState8, false);
                    return Unit.INSTANCE;
                } catch (Throwable th) {
                    th = th;
                    mutableState5 = mutableState;
                    client2 = client;
                    cache2 = cache;
                    Result.Companion companion2 = Result.INSTANCE;
                    objM9714constructorimpl = Result.constructor_impl(ResultKt.createFailure(th));
                }
                break;
            case 1:
                int i = meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.I$0;
                force2 = meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.Z$0;
                cache2 = (NeteaseDiscoveryCache) meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$6;
                client2 = (NeteaseLibraryClient) meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$5;
                mutableState9 = (MutableState) meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$4;
                mutableState8 = (MutableState) meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$3;
                mutableState7 = (MutableState) meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$2;
                mutableState5 = (MutableState) meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$1;
                snapshotStateMap2 = (SnapshotStateMap) meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                    objDiscoveryPlaylists$default = $result;
                    objM9714constructorimpl = Result.constructor_impl((List) objDiscoveryPlaylists$default);
                    break;
                } catch (Throwable th2) {
                    th = th2;
                    Result.Companion companion3 = Result.INSTANCE;
                    objM9714constructorimpl = Result.constructor_impl(ResultKt.createFailure(th));
                }
                client3 = client2;
                mutableState6 = mutableState5;
                obj = objM9714constructorimpl;
                cache3 = cache2;
                if (Result.isSuccess_impl(obj)) {
                    list = (List) obj;
                    SnapshotStateMap<String, List<NeteasePlaylistSummary>> snapshotStateMap4 = snapshotStateMap2;
                    snapshotStateMap2.put(ExploreBrowser$lambda$4(mutableState6), list);
                    mutableState7.setValue(list);
                    strExploreBrowser$lambda$4 = ExploreBrowser$lambda$4(mutableState6);
                    meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$0 = SpillingKt.nullOutSpilledVariable(snapshotStateMap4);
                    meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$1 = SpillingKt.nullOutSpilledVariable(mutableState6);
                    meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$2 = SpillingKt.nullOutSpilledVariable(mutableState7);
                    meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$3 = mutableState8;
                    meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$4 = mutableState9;
                    meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$5 = SpillingKt.nullOutSpilledVariable(client3);
                    meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$6 = SpillingKt.nullOutSpilledVariable(cache3);
                    meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$7 = obj;
                    meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$8 = SpillingKt.nullOutSpilledVariable(list);
                    meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.Z$0 = force2;
                    meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.I$0 = 0;
                    meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.label = 2;
                    if (cache3.saveCategory(strExploreBrowser$lambda$4, list, meloXDiscoveryScreensKt$ExploreBrowser$refresh$2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                thM9717exceptionOrNullimpl = Result.exceptionOrNull_impl(obj);
                if (thM9717exceptionOrNullimpl != null) {
                    message = thM9717exceptionOrNullimpl.getMessage();
                    if (message == null) {
                        message = "发现页加载失败";
                    }
                    mutableState9.setValue(message);
                }
                ExploreBrowser$lambda$11(mutableState8, false);
                return Unit.INSTANCE;
            case 2:
                int i2 = meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.I$0;
                boolean z = meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.Z$0;
                obj = meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$7;
                mutableState9 = (MutableState) meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$4;
                mutableState8 = (MutableState) meloXDiscoveryScreensKt$ExploreBrowser$refresh$2.L$3;
                ResultKt.throwOnFailure($result);
                thM9717exceptionOrNullimpl = Result.exceptionOrNull_impl(obj);
                if (thM9717exceptionOrNullimpl != null) {
                    message = thM9717exceptionOrNullimpl.getMessage();
                    if (message == null) {
                        message = "发现页加载失败";
                    }
                    mutableState9.setValue(message);
                }
                ExploreBrowser$lambda$11(mutableState8, false);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ExploreBrowser$lambda$19$0(CoroutineScope $scope, SnapshotStateMap $memoryCache, MutableState $category$delegate, MutableState $playlists$delegate, MutableState $loading$delegate, MutableState $errorMessage$delegate, NeteaseLibraryClient $client, NeteaseDiscoveryCache $cache) {
        BuildersKt__Builders_commonKt.launch$default($scope, null, null, new MeloXDiscoveryScreensKt$ExploreBrowser$2$1$1($memoryCache, $category$delegate, $playlists$delegate, $loading$delegate, $errorMessage$delegate, $client, $cache, null), 3, null);
        return Unit.INSTANCE;
    }

    static final Unit ExploreBrowser$lambda$20(final Function1 $openPlaylist, final CoroutineScope $scope, final NeteaseLibraryClient $client, final NeteaseDiscoveryCache $cache, final MutableState $category$delegate, final SnapshotStateMap $memoryCache, final MutableState $playlists$delegate, final MutableState $errorMessage$delegate, final MutableState $loading$delegate, BoxScope PullToRefreshBox, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(PullToRefreshBox, "$this$PullToRefreshBox");
        ComposerKt.sourceInformation($composer, "C185@7495L11,189@7712L2593,182@7378L2927:MeloXDiscoveryScreens.kt#301xg3");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1409099783, $changed, -1, "com.lladlam.melox.ui.discovery.ExploreBrowser.<anonymous> (MeloXDiscoveryScreens.kt:182)");
            }
            Modifier modifierStatusBarsPadding = WindowInsetsPadding_androidKt.statusBarsPadding(BackgroundKt.m1043backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getBackground(), null, 2, null));
            PaddingValues paddingValuesM1802PaddingValuesa9UjIt4$default = PaddingKt.m1802PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, MeloXLayoutKt.getMeloXBottomContentClearance(), 7, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(Dp.constructor_impl(22));
            ComposerKt.sourceInformationMarkerStart($composer, -1230179622, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            boolean zChanged = $composer.changed($openPlaylist) | $composer.changedInstance($scope) | $composer.changedInstance($client) | $composer.changedInstance($cache);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return MeloXDiscoveryScreensKt.ExploreBrowser$lambda$20$0$0($category$delegate, $memoryCache, $playlists$delegate, $errorMessage$delegate, $openPlaylist, $loading$delegate, $scope, $client, $cache, (LazyListScope) obj2);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            LazyDslKt.LazyColumn(modifierStatusBarsPadding, null, paddingValuesM1802PaddingValuesa9UjIt4$default, false, horizontalOrVerticalM1497spacedBy0680j_4, null, null, false, null, (Function1) objRememberedValue, $composer, 24960, 490);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ExploreBrowser$lambda$20$0$0(final MutableState $category$delegate, final SnapshotStateMap $memoryCache, final MutableState $playlists$delegate, final MutableState $errorMessage$delegate, final Function1 $openPlaylist, final MutableState $loading$delegate, final CoroutineScope $scope, final NeteaseLibraryClient $client, final NeteaseDiscoveryCache $cache, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$MeloXDiscoveryScreensKt.INSTANCE.m9610getLambda$66146226$app(), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-528945915, true, new Function3() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda28
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return MeloXDiscoveryScreensKt.ExploreBrowser$lambda$20$0$0$0($category$delegate, $memoryCache, $playlists$delegate, $errorMessage$delegate, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        if (!ExploreBrowser$lambda$7($playlists$delegate).isEmpty()) {
            LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-817218490, true, new Function3() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda29
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return MeloXDiscoveryScreensKt.ExploreBrowser$lambda$20$0$0$1($openPlaylist, $playlists$delegate, $category$delegate, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }), 3, null);
            LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1713103235, true, new Function3() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda30
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return MeloXDiscoveryScreensKt.ExploreBrowser$lambda$20$0$0$2($category$delegate, $playlists$delegate, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }), 3, null);
            final List listChunked = CollectionsKt.chunked(CollectionsKt.drop(ExploreBrowser$lambda$7($playlists$delegate), 1), 2);
            final Function1 function1 = new Function1() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda31
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MeloXDiscoveryScreensKt.ExploreBrowser$lambda$20$0$0$3((List) obj);
                }
            };
            final Function1 function2 = new Function1() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$ExploreBrowser$lambda$20$0$0$$inlined$items$default$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                    return invoke((List<? extends NeteasePlaylistSummary>) p1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Void invoke(List<? extends NeteasePlaylistSummary> list) {
                    return null;
                }
            };
            LazyColumn.items(listChunked.size(), new Function1<Integer, Object>() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$ExploreBrowser$lambda$20$0$0$$inlined$items$default$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final Object invoke(int index) {
                    return function1.invoke(listChunked.get(index));
                }
            }, new Function1<Integer, Object>() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$ExploreBrowser$lambda$20$0$0$$inlined$items$default$3
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final Object invoke(int index) {
                    return function2.invoke(listChunked.get(index));
                }
            }, ComposableLambdaKt.composableLambdaInstance(802480018, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$ExploreBrowser$lambda$20$0$0$$inlined$items$default$4
                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                    invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(LazyItemScope $this$items, int it, Composer $composer, int $changed) {
                    Function0<ComposeUiNode> function0;
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
                        List list = (List) listChunked.get(it);
                        $composer.startReplaceGroup(1719081179);
                        ComposerKt.sourceInformation($composer, "CN(row)*229@9308L658:MeloXDiscoveryScreens.kt#301xg3");
                        Modifier modifierM1807paddingVpY3zN4$default = PaddingKt.m1807paddingVpY3zN4$default(Modifier.INSTANCE, Dp.constructor_impl(16), 0.0f, 2, null);
                        Arrangement.Horizontal horizontalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(Dp.constructor_impl(14));
                        ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalM1497spacedBy0680j_4, Alignment.INSTANCE.getTop(), $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                        int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                        CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM1807paddingVpY3zN4$default);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        int i2 = ((((54 << 3) & 112) << 6) & 896) | 6;
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
                        int i4 = ((54 >> 6) & 112) | 6;
                        RowScope rowScope = RowScopeInstance.INSTANCE;
                        Composer composer = $composer;
                        ComposerKt.sourceInformationMarkerStart(composer, -493214988, "C:MeloXDiscoveryScreens.kt#301xg3");
                        composer.startReplaceGroup(-15910112);
                        ComposerKt.sourceInformation(composer, "*237@9774L26,234@9585L250");
                        List<NeteasePlaylistSummary> list2 = list;
                        int i5 = 0;
                        for (final NeteasePlaylistSummary neteasePlaylistSummary : list2) {
                            Iterable iterable = list2;
                            Modifier modifierWeight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
                            int i6 = i5;
                            Modifier modifier = modifierMaterializeModifier;
                            ComposerKt.sourceInformationMarkerStart(composer, 447576804, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
                            boolean zChanged = composer.changed($openPlaylist) | composer.changed(neteasePlaylistSummary);
                            Composer composer2 = composer;
                            Composer composer3 = composer;
                            Object objRememberedValue = composer2.rememberedValue();
                            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                final Function1 function3 = $openPlaylist;
                                objRememberedValue = (Function0) new Function0<Unit>() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$ExploreBrowser$3$1$1$5$1$1$1$1
                                    @Override // kotlin.jvm.functions.Function0
                                    public /* bridge */ /* synthetic */ Unit invoke() {
                                        invoke2();
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2() {
                                        function3.invoke(neteasePlaylistSummary);
                                    }
                                };
                                composer2.updateRememberedValue(objRememberedValue);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            MeloXDiscoveryScreensKt.PlaylistGridCard(neteasePlaylistSummary, modifierWeight$default, (Function0) objRememberedValue, composer3, 0, 0);
                            composer = composer3;
                            list2 = iterable;
                            i5 = i6;
                            modifierMaterializeModifier = modifier;
                        }
                        Composer composer4 = composer;
                        composer4.endReplaceGroup();
                        if (list.size() == 1) {
                            composer4.startReplaceGroup(-15898195);
                            ComposerKt.sourceInformation(composer4, "240@9913L27");
                            SpacerKt.Spacer(RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null), composer4, 0);
                        } else {
                            composer4.startReplaceGroup(-492818096);
                        }
                        composer4.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerEnd(composer4);
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
        } else if (ExploreBrowser$lambda$10($loading$delegate)) {
            LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$MeloXDiscoveryScreensKt.INSTANCE.m9611getLambda$813508049$app(), 3, null);
        } else if (ExploreBrowser$lambda$13($errorMessage$delegate) != null) {
            LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-563406770, true, new Function3() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda32
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return MeloXDiscoveryScreensKt.ExploreBrowser$lambda$20$0$0$5($scope, $client, $cache, $errorMessage$delegate, $memoryCache, $category$delegate, $playlists$delegate, $loading$delegate, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }), 3, null);
        } else {
            LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$MeloXDiscoveryScreensKt.INSTANCE.m9608getLambda$1012347441$app(), 3, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ExploreBrowser$lambda$20$0$0$0(final MutableState $category$delegate, final SnapshotStateMap $memoryCache, final MutableState $playlists$delegate, final MutableState $errorMessage$delegate, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C203@8227L281,201@8141L386:MeloXDiscoveryScreens.kt#301xg3");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-528945915, $changed, -1, "com.lladlam.melox.ui.discovery.ExploreBrowser.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MeloXDiscoveryScreens.kt:201)");
            }
            String strExploreBrowser$lambda$4 = ExploreBrowser$lambda$4($category$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -1510879778, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return MeloXDiscoveryScreensKt.ExploreBrowser$lambda$20$0$0$0$0$0($memoryCache, $category$delegate, $playlists$delegate, $errorMessage$delegate, (String) obj2);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            CategoryPicker(strExploreBrowser$lambda$4, (Function1) objRememberedValue, $composer, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ExploreBrowser$lambda$20$0$0$0$0$0(SnapshotStateMap $memoryCache, MutableState $category$delegate, MutableState $playlists$delegate, MutableState $errorMessage$delegate, String selected) {
        Intrinsics.checkNotNullParameter(selected, "selected");
        if (!Intrinsics.areEqual(selected, ExploreBrowser$lambda$4($category$delegate))) {
            $category$delegate.setValue(selected);
            List listEmptyList = (List) $memoryCache.get(selected);
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            $playlists$delegate.setValue(listEmptyList);
            $errorMessage$delegate.setValue(null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ExploreBrowser$lambda$20$0$0$1(final Function1 $openPlaylist, final MutableState $playlists$delegate, MutableState $category$delegate, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C219@8832L35,216@8657L237:MeloXDiscoveryScreens.kt#301xg3");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-817218490, $changed, -1, "com.lladlam.melox.ui.discovery.ExploreBrowser.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MeloXDiscoveryScreens.kt:216)");
            }
            NeteasePlaylistSummary neteasePlaylistSummary = (NeteasePlaylistSummary) CollectionsKt.first((List) ExploreBrowser$lambda$7($playlists$delegate));
            String strFeaturedBadge = featuredBadge(ExploreBrowser$lambda$4($category$delegate));
            ComposerKt.sourceInformationMarkerStart($composer, 632052745, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            boolean zChanged = $composer.changed($openPlaylist);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda35
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MeloXDiscoveryScreensKt.ExploreBrowser$lambda$20$0$0$1$0$0($openPlaylist, $playlists$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            FeaturedPlaylist(neteasePlaylistSummary, strFeaturedBadge, (Function0) objRememberedValue, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ExploreBrowser$lambda$20$0$0$1$0$0(Function1 $openPlaylist, MutableState $playlists$delegate) {
        $openPlaylist.invoke(CollectionsKt.first((List) ExploreBrowser$lambda$7($playlists$delegate)));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ExploreBrowser$lambda$20$0$0$2(MutableState $category$delegate, MutableState $playlists$delegate, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C223@8968L167:MeloXDiscoveryScreens.kt#301xg3");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1713103235, $changed, -1, "com.lladlam.melox.ui.discovery.ExploreBrowser.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MeloXDiscoveryScreens.kt:223)");
            }
            SectionHeader(collectionTitle(ExploreBrowser$lambda$4($category$delegate)), ExploreBrowser$lambda$7($playlists$delegate).size() + " 个歌单", $composer, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object ExploreBrowser$lambda$20$0$0$3(List row) {
        Intrinsics.checkNotNullParameter(row, "row");
        return CollectionsKt.joinToString$default(row, "-", null, null, 0, null, new Function1() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MeloXDiscoveryScreensKt.ExploreBrowser$lambda$20$0$0$3$0((NeteasePlaylistSummary) obj);
            }
        }, 30, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence ExploreBrowser$lambda$20$0$0$3$0(NeteasePlaylistSummary it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return String.valueOf(it.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ExploreBrowser$lambda$20$0$0$5(final CoroutineScope $scope, final NeteaseLibraryClient $client, final NeteaseDiscoveryCache $cache, final MutableState $errorMessage$delegate, final SnapshotStateMap $memoryCache, final MutableState $category$delegate, final MutableState $playlists$delegate, final MutableState $loading$delegate, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C246@10169L42,246@10134L77:MeloXDiscoveryScreens.kt#301xg3");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-563406770, $changed, -1, "com.lladlam.melox.ui.discovery.ExploreBrowser.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MeloXDiscoveryScreens.kt:246)");
            }
            String strExploreBrowser$lambda$13 = ExploreBrowser$lambda$13($errorMessage$delegate);
            if (strExploreBrowser$lambda$13 == null) {
                strExploreBrowser$lambda$13 = "";
            }
            ComposerKt.sourceInformationMarkerStart($composer, -268024808, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance($scope) | $composer.changedInstance($client) | $composer.changedInstance($cache);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda45
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MeloXDiscoveryScreensKt.ExploreBrowser$lambda$20$0$0$5$0$0($scope, $memoryCache, $category$delegate, $playlists$delegate, $loading$delegate, $errorMessage$delegate, $client, $cache);
                    }
                };
                $composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ErrorState(strExploreBrowser$lambda$13, (Function0) objRememberedValue, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ExploreBrowser$lambda$20$0$0$5$0$0(CoroutineScope $scope, SnapshotStateMap $memoryCache, MutableState $category$delegate, MutableState $playlists$delegate, MutableState $loading$delegate, MutableState $errorMessage$delegate, NeteaseLibraryClient $client, NeteaseDiscoveryCache $cache) {
        BuildersKt__Builders_commonKt.launch$default($scope, null, null, new MeloXDiscoveryScreensKt$ExploreBrowser$3$1$1$6$1$1$1($memoryCache, $category$delegate, $playlists$delegate, $loading$delegate, $errorMessage$delegate, $client, $cache, null), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:90:0x02d7  */
    /* JADX WARN: Multi-variable type inference failed */
    private static final void HomeBrowser(final NeteaseSessionStore neteaseSessionStore, Function1<? super NeteasePlaylistSummary, Unit> function1, Composer composer, final int i) {
        Composer composer2;
        List<NeteasePlaylistSummary> list;
        final CoroutineScope coroutineScope;
        final Function1<? super NeteasePlaylistSummary, Unit> function2 = function1;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1846482517);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(HomeBrowser)N(session,openPlaylist)259@10476L7,260@10519L24,261@10561L138,264@10716L58,265@10795L55,266@10870L34,267@10929L42,281@11333L116,281@11302L147,290@11643L30,292@11724L1666,288@11553L1837:MeloXDiscoveryScreens.kt#301xg3");
        int i2 = i;
        if ((i & 6) == 0) {
            i2 |= composerStartRestartGroup.changed(neteaseSessionStore) ? 4 : 2;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        int i3 = i2;
        if (composerStartRestartGroup.shouldExecute((i3 & 19) != 18, i3 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1846482517, i3, -1, "com.lladlam.melox.ui.discovery.HomeBrowser (MeloXDiscoveryScreens.kt:258)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Context applicationContext = ((Context) objConsume).getApplicationContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)N(getContext)616@28039L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 683736516, "CC(remember):Effects.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                CoroutineScope coroutineScopeCreateCompositionCoroutineScope = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                composerStartRestartGroup.updateRememberedValue(coroutineScopeCreateCompositionCoroutineScope);
                objRememberedValue = coroutineScopeCreateCompositionCoroutineScope;
            }
            CoroutineScope coroutineScope2 = (CoroutineScope) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            String cookie = neteaseSessionStore.getCookie();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -648198219, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(cookie) | composerStartRestartGroup.changed(applicationContext);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            OkHttpClient okHttpClient = null;
            if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                NeteaseLibraryClient neteaseLibraryClient = new NeteaseLibraryClient(new Function0() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda46
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MeloXDiscoveryScreensKt.HomeBrowser$lambda$0$0(applicationContext);
                    }
                }, okHttpClient, 2, null == true ? 1 : 0);
                composerStartRestartGroup.updateRememberedValue(neteaseLibraryClient);
                objRememberedValue2 = neteaseLibraryClient;
            }
            final NeteaseLibraryClient neteaseLibraryClient2 = (NeteaseLibraryClient) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -648193339, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            boolean zChanged2 = composerStartRestartGroup.changed(applicationContext);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Intrinsics.checkNotNull(applicationContext);
                NeteaseDiscoveryCache neteaseDiscoveryCache = new NeteaseDiscoveryCache(applicationContext);
                composerStartRestartGroup.updateRememberedValue(neteaseDiscoveryCache);
                objRememberedValue3 = neteaseDiscoveryCache;
            }
            final NeteaseDiscoveryCache neteaseDiscoveryCache2 = (NeteaseDiscoveryCache) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -648190814, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default);
                objRememberedValue4 = mutableStateMutableStateOf$default;
            }
            final MutableState mutableState = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -648188435, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default2);
                objRememberedValue5 = mutableStateMutableStateOf$default2;
            }
            final MutableState mutableState2 = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -648186539, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default3);
                objRememberedValue6 = mutableStateMutableStateOf$default3;
            }
            final MutableState mutableState3 = (MutableState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            String cookie2 = neteaseSessionStore.getCookie();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -648173537, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(neteaseDiscoveryCache2) | composerStartRestartGroup.changedInstance(neteaseLibraryClient2);
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                MeloXDiscoveryScreensKt$HomeBrowser$1$1 meloXDiscoveryScreensKt$HomeBrowser$1$1 = new MeloXDiscoveryScreensKt$HomeBrowser$1$1(neteaseDiscoveryCache2, mutableState, mutableState2, mutableState3, neteaseLibraryClient2, null);
                composerStartRestartGroup.updateRememberedValue(meloXDiscoveryScreensKt$HomeBrowser$1$1);
                objRememberedValue7 = meloXDiscoveryScreensKt$HomeBrowser$1$1;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.LaunchedEffect(cookie2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue7, composerStartRestartGroup, 0);
            NeteaseHomeSnapshot neteaseHomeSnapshotHomeBrowser$lambda$3 = HomeBrowser$lambda$3(mutableState);
            List<NeteasePlaylistSummary> recommended = neteaseHomeSnapshotHomeBrowser$lambda$3 != null ? neteaseHomeSnapshotHomeBrowser$lambda$3.getRecommended() : null;
            if (recommended == null) {
                recommended = CollectionsKt.emptyList();
            }
            NeteaseHomeSnapshot neteaseHomeSnapshotHomeBrowser$lambda$4 = HomeBrowser$lambda$3(mutableState);
            List<NeteasePlaylistSummary> charts = neteaseHomeSnapshotHomeBrowser$lambda$4 != null ? neteaseHomeSnapshotHomeBrowser$lambda$4.getCharts() : null;
            if (charts == null) {
                charts = CollectionsKt.emptyList();
            }
            boolean z = HomeBrowser$lambda$6(mutableState2) && HomeBrowser$lambda$3(mutableState) != null;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -648163703, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(coroutineScope2) | composerStartRestartGroup.changedInstance(neteaseLibraryClient2) | composerStartRestartGroup.changedInstance(neteaseDiscoveryCache2);
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2) {
                list = recommended;
            } else {
                list = recommended;
                if (objRememberedValue8 != Composer.INSTANCE.getEmpty()) {
                    coroutineScope = coroutineScope2;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                final List<NeteasePlaylistSummary> list2 = list;
                final CoroutineScope coroutineScope3 = coroutineScope;
                final MutableState mutableState4 = mutableState;
                final MutableState mutableState5 = mutableState2;
                final MutableState mutableState6 = mutableState3;
                final List<NeteasePlaylistSummary> list3 = charts;
                function2 = function1;
                composer2 = composerStartRestartGroup;
                PullToRefreshKt.PullToRefreshBox(z, (Function0) objRememberedValue8, SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, null, null, ComposableLambdaKt.rememberComposableLambda(-14154107, true, new Function3() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda48
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return MeloXDiscoveryScreensKt.HomeBrowser$lambda$16(list2, list3, function2, coroutineScope3, neteaseLibraryClient2, neteaseDiscoveryCache2, mutableState4, mutableState5, mutableState6, (BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composerStartRestartGroup, 54), composer2, 1573248, 56);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            coroutineScope = coroutineScope2;
            Function0 function0 = new Function0() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda47
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MeloXDiscoveryScreensKt.HomeBrowser$lambda$15$0(coroutineScope, mutableState2, mutableState3, neteaseLibraryClient2, neteaseDiscoveryCache2, mutableState);
                }
            };
            mutableState3 = mutableState3;
            mutableState2 = mutableState2;
            mutableState = mutableState;
            composerStartRestartGroup.updateRememberedValue(function0);
            objRememberedValue8 = function0;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final List list4 = list;
            final CoroutineScope coroutineScope4 = coroutineScope;
            final MutableState mutableState7 = mutableState;
            final MutableState mutableState8 = mutableState2;
            final MutableState mutableState9 = mutableState3;
            final List list5 = charts;
            function2 = function1;
            composer2 = composerStartRestartGroup;
            PullToRefreshKt.PullToRefreshBox(z, (Function0) objRememberedValue8, SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, null, null, ComposableLambdaKt.rememberComposableLambda(-14154107, true, new Function3() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda48
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return MeloXDiscoveryScreensKt.HomeBrowser$lambda$16(list4, list5, function2, coroutineScope4, neteaseLibraryClient2, neteaseDiscoveryCache2, mutableState7, mutableState8, mutableState9, (BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composerStartRestartGroup, 54), composer2, 1573248, 56);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda49
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXDiscoveryScreensKt.HomeBrowser$lambda$17(neteaseSessionStore, function2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String HomeBrowser$lambda$0$0(Context $appContext) {
        NeteaseSessionStore.Companion companion = NeteaseSessionStore.INSTANCE;
        Intrinsics.checkNotNull($appContext);
        return companion.readCookie($appContext);
    }

    private static final NeteaseHomeSnapshot HomeBrowser$lambda$3(MutableState<NeteaseHomeSnapshot> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean HomeBrowser$lambda$6(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void HomeBrowser$lambda$7(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String HomeBrowser$lambda$9(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:26:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:28:0x00e3 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:29:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:33:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:35:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object HomeBrowser$refresh(MutableState<Boolean> mutableState, MutableState<String> mutableState2, NeteaseLibraryClient client, NeteaseDiscoveryCache cache, MutableState<NeteaseHomeSnapshot> mutableState3, Continuation<? super Unit> continuation) {
        MeloXDiscoveryScreensKt$HomeBrowser$refresh$1 meloXDiscoveryScreensKt$HomeBrowser$refresh$1;
        Object objM9714constructorimpl;
        Object obj;
        NeteaseHomeSnapshot neteaseHomeSnapshot;
        Throwable thM9717exceptionOrNullimpl;
        String message;
        Object objHomeSnapshot;
        if (continuation instanceof MeloXDiscoveryScreensKt$HomeBrowser$refresh$1) {
            meloXDiscoveryScreensKt$HomeBrowser$refresh$1 = (MeloXDiscoveryScreensKt$HomeBrowser$refresh$1) continuation;
            if ((meloXDiscoveryScreensKt$HomeBrowser$refresh$1.label & Integer.MIN_VALUE) != 0) {
                meloXDiscoveryScreensKt$HomeBrowser$refresh$1.label -= Integer.MIN_VALUE;
            } else {
                meloXDiscoveryScreensKt$HomeBrowser$refresh$1 = new MeloXDiscoveryScreensKt$HomeBrowser$refresh$1(continuation);
            }
        } else {
            meloXDiscoveryScreensKt$HomeBrowser$refresh$1 = new MeloXDiscoveryScreensKt$HomeBrowser$refresh$1(continuation);
        }
        Object $result = meloXDiscoveryScreensKt$HomeBrowser$refresh$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (meloXDiscoveryScreensKt$HomeBrowser$refresh$1.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    HomeBrowser$lambda$7(mutableState, true);
                    mutableState2.setValue(null);
                    Result.Companion companion = Result.INSTANCE;
                    meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$0 = mutableState;
                    meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$1 = mutableState2;
                    meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$2 = SpillingKt.nullOutSpilledVariable(client);
                    meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$3 = cache;
                    meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$4 = mutableState3;
                    meloXDiscoveryScreensKt$HomeBrowser$refresh$1.I$0 = 0;
                    meloXDiscoveryScreensKt$HomeBrowser$refresh$1.label = 1;
                    objHomeSnapshot = client.homeSnapshot(meloXDiscoveryScreensKt$HomeBrowser$refresh$1);
                    if (objHomeSnapshot == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    objM9714constructorimpl = Result.constructor_impl((NeteaseHomeSnapshot) objHomeSnapshot);
                    obj = objM9714constructorimpl;
                    if (Result.isSuccess_impl(obj)) {
                        neteaseHomeSnapshot = (NeteaseHomeSnapshot) obj;
                        mutableState3.setValue(neteaseHomeSnapshot);
                        meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$0 = mutableState;
                        meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$1 = mutableState2;
                        meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$2 = SpillingKt.nullOutSpilledVariable(client);
                        meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$3 = SpillingKt.nullOutSpilledVariable(cache);
                        meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$4 = SpillingKt.nullOutSpilledVariable(mutableState3);
                        meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$5 = obj;
                        meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$6 = SpillingKt.nullOutSpilledVariable(neteaseHomeSnapshot);
                        meloXDiscoveryScreensKt$HomeBrowser$refresh$1.I$0 = 0;
                        meloXDiscoveryScreensKt$HomeBrowser$refresh$1.label = 2;
                        if (cache.saveHome(neteaseHomeSnapshot, meloXDiscoveryScreensKt$HomeBrowser$refresh$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    thM9717exceptionOrNullimpl = Result.exceptionOrNull_impl(obj);
                    if (thM9717exceptionOrNullimpl != null) {
                        message = thM9717exceptionOrNullimpl.getMessage();
                        if (message == null) {
                            message = "首页加载失败";
                        }
                        mutableState2.setValue(message);
                    }
                    HomeBrowser$lambda$7(mutableState, false);
                    return Unit.INSTANCE;
                case 1:
                    int i = meloXDiscoveryScreensKt$HomeBrowser$refresh$1.I$0;
                    mutableState3 = (MutableState) meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$4;
                    cache = (NeteaseDiscoveryCache) meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$3;
                    client = (NeteaseLibraryClient) meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$2;
                    mutableState2 = (MutableState) meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$1;
                    mutableState = (MutableState) meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$0;
                    ResultKt.throwOnFailure($result);
                    objHomeSnapshot = $result;
                    objM9714constructorimpl = Result.constructor_impl((NeteaseHomeSnapshot) objHomeSnapshot);
                    obj = objM9714constructorimpl;
                    if (Result.isSuccess_impl(obj)) {
                        neteaseHomeSnapshot = (NeteaseHomeSnapshot) obj;
                        mutableState3.setValue(neteaseHomeSnapshot);
                        meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$0 = mutableState;
                        meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$1 = mutableState2;
                        meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$2 = SpillingKt.nullOutSpilledVariable(client);
                        meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$3 = SpillingKt.nullOutSpilledVariable(cache);
                        meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$4 = SpillingKt.nullOutSpilledVariable(mutableState3);
                        meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$5 = obj;
                        meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$6 = SpillingKt.nullOutSpilledVariable(neteaseHomeSnapshot);
                        meloXDiscoveryScreensKt$HomeBrowser$refresh$1.I$0 = 0;
                        meloXDiscoveryScreensKt$HomeBrowser$refresh$1.label = 2;
                        if (cache.saveHome(neteaseHomeSnapshot, meloXDiscoveryScreensKt$HomeBrowser$refresh$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    thM9717exceptionOrNullimpl = Result.exceptionOrNull_impl(obj);
                    if (thM9717exceptionOrNullimpl != null) {
                        message = thM9717exceptionOrNullimpl.getMessage();
                        if (message == null) {
                            message = "首页加载失败";
                        }
                        mutableState2.setValue(message);
                    }
                    HomeBrowser$lambda$7(mutableState, false);
                    return Unit.INSTANCE;
                case 2:
                    int i2 = meloXDiscoveryScreensKt$HomeBrowser$refresh$1.I$0;
                    obj = meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$5;
                    mutableState2 = (MutableState) meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$1;
                    mutableState = (MutableState) meloXDiscoveryScreensKt$HomeBrowser$refresh$1.L$0;
                    ResultKt.throwOnFailure($result);
                    thM9717exceptionOrNullimpl = Result.exceptionOrNull_impl(obj);
                    if (thM9717exceptionOrNullimpl != null) {
                        message = thM9717exceptionOrNullimpl.getMessage();
                        if (message == null) {
                            message = "首页加载失败";
                        }
                        mutableState2.setValue(message);
                    }
                    HomeBrowser$lambda$7(mutableState, false);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM9714constructorimpl = Result.constructor_impl(ResultKt.createFailure(th));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeBrowser$lambda$15$0(CoroutineScope $scope, MutableState $loading$delegate, MutableState $errorMessage$delegate, NeteaseLibraryClient $client, NeteaseDiscoveryCache $cache, MutableState $snapshot$delegate) {
        BuildersKt__Builders_commonKt.launch$default($scope, null, null, new MeloXDiscoveryScreensKt$HomeBrowser$2$1$1($loading$delegate, $errorMessage$delegate, $client, $cache, $snapshot$delegate, null), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x00f9  */
    static final Unit HomeBrowser$lambda$16(final List $recommended, final List $charts, final Function1 $openPlaylist, final CoroutineScope $scope, final NeteaseLibraryClient $client, final NeteaseDiscoveryCache $cache, final MutableState $snapshot$delegate, final MutableState $loading$delegate, final MutableState $errorMessage$delegate, BoxScope PullToRefreshBox, Composer $composer, int $changed) {
        Modifier modifier;
        Intrinsics.checkNotNullParameter(PullToRefreshBox, "$this$PullToRefreshBox");
        ComposerKt.sourceInformation($composer, "C296@11851L11,300@12068L1316,293@11734L1650:MeloXDiscoveryScreens.kt#301xg3");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-14154107, $changed, -1, "com.lladlam.melox.ui.discovery.HomeBrowser.<anonymous> (MeloXDiscoveryScreens.kt:293)");
            }
            Modifier modifierStatusBarsPadding = WindowInsetsPadding_androidKt.statusBarsPadding(BackgroundKt.m1043backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getBackground(), null, 2, null));
            PaddingValues paddingValuesM1802PaddingValuesa9UjIt4$default = PaddingKt.m1802PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, MeloXLayoutKt.getMeloXBottomContentClearance(), 7, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(Dp.constructor_impl(28));
            ComposerKt.sourceInformationMarkerStart($composer, -717182615, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance($recommended) | $composer.changedInstance($charts) | $composer.changed($openPlaylist) | $composer.changedInstance($scope) | $composer.changedInstance($client) | $composer.changedInstance($cache);
            Object objRememberedValue = $composer.rememberedValue();
            if (!zChangedInstance) {
                modifier = modifierStatusBarsPadding;
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                LazyDslKt.LazyColumn(modifier, null, paddingValuesM1802PaddingValuesa9UjIt4$default, false, horizontalOrVerticalM1497spacedBy0680j_4, null, null, false, null, (Function1) objRememberedValue, $composer, 24960, 490);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                modifier = modifierStatusBarsPadding;
            }
            objRememberedValue = new Function1() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda41
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MeloXDiscoveryScreensKt.HomeBrowser$lambda$16$0$0($recommended, $charts, $openPlaylist, $snapshot$delegate, $scope, $client, $cache, $loading$delegate, $errorMessage$delegate, (LazyListScope) obj);
                }
            };
            $composer.updateRememberedValue(objRememberedValue);
            ComposerKt.sourceInformationMarkerEnd($composer);
            LazyDslKt.LazyColumn(modifier, null, paddingValuesM1802PaddingValuesa9UjIt4$default, false, horizontalOrVerticalM1497spacedBy0680j_4, null, null, false, null, (Function1) objRememberedValue, $composer, 24960, 490);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeBrowser$lambda$16$0$0(final List $recommended, final List $charts, final Function1 $openPlaylist, final MutableState $snapshot$delegate, final CoroutineScope $scope, final NeteaseLibraryClient $client, final NeteaseDiscoveryCache $cache, final MutableState $loading$delegate, final MutableState $errorMessage$delegate, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$MeloXDiscoveryScreensKt.INSTANCE.m9609getLambda$1828055472$app(), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-269161223, true, new Function3() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return MeloXDiscoveryScreensKt.HomeBrowser$lambda$16$0$0$0($recommended, $charts, $openPlaylist, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        if (!$recommended.isEmpty()) {
            LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(1726733141, true, new Function3() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return MeloXDiscoveryScreensKt.HomeBrowser$lambda$16$0$0$1($recommended, $openPlaylist, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }), 3, null);
        }
        if (!$charts.isEmpty()) {
            LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(1383695550, true, new Function3() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return MeloXDiscoveryScreensKt.HomeBrowser$lambda$16$0$0$2($charts, $openPlaylist, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }), 3, null);
        }
        if (HomeBrowser$lambda$3($snapshot$delegate) == null) {
            LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1959439907, true, new Function3() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return MeloXDiscoveryScreensKt.HomeBrowser$lambda$16$0$0$3($scope, $client, $cache, $loading$delegate, $errorMessage$delegate, $snapshot$delegate, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }), 3, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeBrowser$lambda$16$0$0$0(List $recommended, List $charts, Function1 $openPlaylist, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C312@12497L242:MeloXDiscoveryScreens.kt#301xg3");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-269161223, $changed, -1, "com.lladlam.melox.ui.discovery.HomeBrowser.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MeloXDiscoveryScreens.kt:312)");
            }
            HomeEditorialStrip((NeteasePlaylistSummary) CollectionsKt.getOrNull($recommended, 0), (NeteasePlaylistSummary) CollectionsKt.getOrNull($recommended, 1), (NeteasePlaylistSummary) CollectionsKt.firstOrNull($charts), $openPlaylist, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeBrowser$lambda$16$0$0$1(List $recommended, Function1 $openPlaylist, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C320@12821L49:MeloXDiscoveryScreens.kt#301xg3");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1726733141, $changed, -1, "com.lladlam.melox.ui.discovery.HomeBrowser.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MeloXDiscoveryScreens.kt:320)");
            }
            HomeMediaStrip("为你推荐", $recommended, $openPlaylist, $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeBrowser$lambda$16$0$0$2(List $charts, Function1 $openPlaylist, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C323@12949L44:MeloXDiscoveryScreens.kt#301xg3");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1383695550, $changed, -1, "com.lladlam.melox.ui.discovery.HomeBrowser.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MeloXDiscoveryScreens.kt:323)");
            }
            HomeMediaStrip("热门排行", $charts, $openPlaylist, $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeBrowser$lambda$16$0$0$3(final CoroutineScope $scope, final NeteaseLibraryClient $client, final NeteaseDiscoveryCache $cache, final MutableState $loading$delegate, final MutableState $errorMessage$delegate, final MutableState $snapshot$delegate, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C:MeloXDiscoveryScreens.kt#301xg3");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1959439907, $changed, -1, "com.lladlam.melox.ui.discovery.HomeBrowser.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MeloXDiscoveryScreens.kt:327)");
            }
            if (HomeBrowser$lambda$6($loading$delegate)) {
                $composer.startReplaceGroup(-1826072907);
                ComposerKt.sourceInformation($composer, "328@13131L24");
                LoadingState("正在为你准备推荐", $composer, 6);
                $composer.endReplaceGroup();
            } else if (HomeBrowser$lambda$9($errorMessage$delegate) == null) {
                $composer.startReplaceGroup(-1826067441);
                ComposerKt.sourceInformation($composer, "330@13302L18");
                EmptyState("暂无推荐", $composer, 6);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-1826070530);
                ComposerKt.sourceInformation($composer, "329@13239L30,329@13204L65");
                String strHomeBrowser$lambda$9 = HomeBrowser$lambda$9($errorMessage$delegate);
                if (strHomeBrowser$lambda$9 == null) {
                    strHomeBrowser$lambda$9 = "";
                }
                ComposerKt.sourceInformationMarkerStart($composer, -1826069445, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
                boolean zChangedInstance = $composer.changedInstance($scope) | $composer.changedInstance($client) | $composer.changedInstance($cache);
                Object objRememberedValue = $composer.rememberedValue();
                if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function0() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MeloXDiscoveryScreensKt.HomeBrowser$lambda$16$0$0$3$0$0($scope, $loading$delegate, $errorMessage$delegate, $client, $cache, $snapshot$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                ErrorState(strHomeBrowser$lambda$9, (Function0) objRememberedValue, $composer, 0);
                $composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeBrowser$lambda$16$0$0$3$0$0(CoroutineScope $scope, MutableState $loading$delegate, MutableState $errorMessage$delegate, NeteaseLibraryClient $client, NeteaseDiscoveryCache $cache, MutableState $snapshot$delegate) {
        BuildersKt__Builders_commonKt.launch$default($scope, null, null, new MeloXDiscoveryScreensKt$HomeBrowser$3$1$1$4$1$1$1($loading$delegate, $errorMessage$delegate, $client, $cache, $snapshot$delegate, null), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:60:0x029f  */
    /* JADX WARN: Code duplicated, block: B:61:0x02af  */
    /* JADX WARN: Code duplicated, block: B:64:0x02d9  */
    /* JADX WARN: Code duplicated, block: B:65:0x02de  */
    private static final void CategoryPicker(final String selected, final Function1<? super String, Unit> function1, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        long j;
        Composer $composer3;
        long onBackground;
        FontWeight.Companion companion;
        FontWeight medium;
        Composer $composer4 = $composer.startRestartGroup(-1626558015);
        ComposerKt.sourceInformation($composer4, "C(CategoryPicker)N(selected,select)342@13546L21,340@13483L1152:MeloXDiscoveryScreens.kt#301xg3");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer4.changed(selected) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer4.changedInstance(function1) ? 32 : 16;
        }
        if ($composer4.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1626558015, $dirty, -1, "com.lladlam.melox.ui.discovery.CategoryPicker (MeloXDiscoveryScreens.kt:339)");
            }
            Modifier modifierM1807paddingVpY3zN4$default = PaddingKt.m1807paddingVpY3zN4$default(ScrollKt.horizontalScroll$default(Modifier.INSTANCE, ScrollKt.rememberScrollState(0, $composer4, 0, 1), false, null, false, 14, null), Dp.constructor_impl(16), 0.0f, 2, null);
            Arrangement.Horizontal horizontalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(Dp.constructor_impl(8));
            ComposerKt.sourceInformationMarkerStart($composer4, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalM1497spacedBy0680j_4, Alignment.INSTANCE.getTop(), $composer4, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            Composer composer = $composer4;
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierM1807paddingVpY3zN4$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $dirty2 = $dirty;
            int i = ((((48 << 3) & 112) << 6) & 896) | 6;
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
            Composer composerM5188constructorimpl = Updater.constructor_impl(composer);
            Updater.set_impl(composerM5188constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            int i3 = 0;
            ComposerKt.sourceInformationMarkerStart(composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i4 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer, -829245296, "C:MeloXDiscoveryScreens.kt#301xg3");
            composer.startReplaceGroup(-719485951);
            ComposerKt.sourceInformation(composer, "*360@14270L20,348@13785L834");
            Iterable<String> iterable = ExploreCategories;
            int i5 = 0;
            for (final String str : iterable) {
                Iterable iterable2 = iterable;
                boolean zAreEqual = Intrinsics.areEqual(str, selected);
                int i6 = i5;
                String str2 = Intrinsics.areEqual(str, "推荐歌单") ? "推荐" : Intrinsics.areEqual(str, "精品歌单") ? "精品" : str;
                int i7 = i3;
                Composer composer2 = composer;
                Modifier modifierClip = ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(20)));
                if (zAreEqual) {
                    composer.startReplaceGroup(922890144);
                    composer.endReplaceGroup();
                    j = MeloXAccent;
                } else {
                    composer.startReplaceGroup(922892712);
                    ComposerKt.sourceInformation(composer, "358@14171L11");
                    long onBackground2 = MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getOnBackground();
                    long jM6066copywmQWz5c = Color.copy_wmQWz5c(onBackground2, (14 & 1) != 0 ? Color.getAlpha_impl(onBackground2) : 0.07f, (14 & 2) != 0 ? Color.getRed_impl(onBackground2) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(onBackground2) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(onBackground2) : 0.0f);
                    composer.endReplaceGroup();
                    j = jM6066copywmQWz5c;
                }
                Modifier modifierM1043backgroundbw27NRU$default = BackgroundKt.m1043backgroundbw27NRU$default(modifierClip, j, null, 2, null);
                ComposerKt.sourceInformationMarkerStart(composer, 922895081, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
                boolean zChanged = (($dirty2 & 112) == 32) | $composer4.changed(str);
                Object objRememberedValue = composer.rememberedValue();
                if (zChanged) {
                    $composer3 = $composer4;
                } else {
                    $composer3 = $composer4;
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    Modifier modifierM1806paddingVpY3zN4 = PaddingKt.m1806paddingVpY3zN4(ClickableKt.m1078clickableoSLSa3U$default(modifierM1043backgroundbw27NRU$default, false, null, null, null, (Function0) objRememberedValue, 15, null), Dp.constructor_impl(14), Dp.constructor_impl(7));
                    if (zAreEqual) {
                        composer.startReplaceGroup(922899226);
                        composer.endReplaceGroup();
                        onBackground = Color.INSTANCE.m6105getWhite0d7_KjU();
                    } else {
                        composer.startReplaceGroup(922900417);
                        ComposerKt.sourceInformation(composer, "362@14425L11");
                        onBackground = MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getOnBackground();
                        composer.endReplaceGroup();
                    }
                    long j2 = onBackground;
                    long sp = TextUnitKt.getSp(15);
                    long sp2 = TextUnitKt.getSp(18);
                    companion = FontWeight.INSTANCE;
                    if (zAreEqual) {
                        medium = companion.getSemiBold();
                    } else {
                        medium = companion.getMedium();
                    }
                    TextKt.m3912TextNvy7gAk(str2, modifierM1806paddingVpY3zN4, j2, null, sp, null, medium, null, 0L, null, null, sp2, 0, false, 0, 0, null, null, composer, 24576, 48, 260008);
                    iterable = iterable2;
                    i5 = i6;
                    i3 = i7;
                    composer = composer2;
                    $composer4 = $composer3;
                }
                Object obj = new Function0() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda36
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MeloXDiscoveryScreensKt.CategoryPicker$lambda$0$0$0$0(function1, str);
                    }
                };
                composer.updateRememberedValue(obj);
                objRememberedValue = obj;
                ComposerKt.sourceInformationMarkerEnd(composer);
                Modifier modifierM1806paddingVpY3zN5 = PaddingKt.m1806paddingVpY3zN4(ClickableKt.m1078clickableoSLSa3U$default(modifierM1043backgroundbw27NRU$default, false, null, null, null, (Function0) objRememberedValue, 15, null), Dp.constructor_impl(14), Dp.constructor_impl(7));
                if (zAreEqual) {
                    composer.startReplaceGroup(922899226);
                    composer.endReplaceGroup();
                    onBackground = Color.INSTANCE.m6105getWhite0d7_KjU();
                } else {
                    composer.startReplaceGroup(922900417);
                    ComposerKt.sourceInformation(composer, "362@14425L11");
                    onBackground = MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getOnBackground();
                    composer.endReplaceGroup();
                }
                long j3 = onBackground;
                long sp3 = TextUnitKt.getSp(15);
                long sp4 = TextUnitKt.getSp(18);
                companion = FontWeight.INSTANCE;
                if (zAreEqual) {
                    medium = companion.getSemiBold();
                } else {
                    medium = companion.getMedium();
                }
                TextKt.m3912TextNvy7gAk(str2, modifierM1806paddingVpY3zN5, j3, null, sp3, null, medium, null, 0L, null, null, sp4, 0, false, 0, 0, null, null, composer, 24576, 48, 260008);
                iterable = iterable2;
                i5 = i6;
                i3 = i7;
                composer = composer2;
                $composer4 = $composer3;
            }
            $composer2 = $composer4;
            composer.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda37
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return MeloXDiscoveryScreensKt.CategoryPicker$lambda$1(selected, function1, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CategoryPicker$lambda$0$0$0$0(Function1 $select, String $category) {
        $select.invoke($category);
        return Unit.INSTANCE;
    }

    private static final void FeaturedPlaylist(final NeteasePlaylistSummary playlist, String badge, final Function0<Unit> function0, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function2;
        final String str = badge;
        Composer $composer3 = $composer.startRestartGroup(-867328997);
        ComposerKt.sourceInformation($composer3, "C(FeaturedPlaylist)N(playlist,badge,onClick)383@14988L11,377@14771L1872:MeloXDiscoveryScreens.kt#301xg3");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(playlist) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(str) ? 32 : 16;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (!$composer3.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-867328997, $dirty2, -1, "com.lladlam.melox.ui.discovery.FeaturedPlaylist (MeloXDiscoveryScreens.kt:376)");
            }
            Modifier modifierClip = ClipKt.clip(AspectRatioKt.aspectRatio$default(SizeKt.fillMaxWidth$default(PaddingKt.m1807paddingVpY3zN4$default(Modifier.INSTANCE, Dp.constructor_impl(16), 0.0f, 2, null), 0.0f, 1, null), 1.03f, false, 2, null), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(22)));
            long onBackground = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnBackground();
            Modifier modifierM1078clickableoSLSa3U$default = ClickableKt.m1078clickableoSLSa3U$default(BackgroundKt.m1043backgroundbw27NRU$default(modifierClip, Color.copy_wmQWz5c(onBackground, (14 & 1) != 0 ? Color.getAlpha_impl(onBackground) : 0.08f, (14 & 2) != 0 ? Color.getRed_impl(onBackground) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(onBackground) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(onBackground) : 0.0f), null, 2, null), false, null, null, null, function0, 15, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1078clickableoSLSa3U$default);
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
            Composer composerM5188constructorimpl = Updater.constructor_impl($composer3);
            Updater.set_impl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            int i3 = ((0 >> 6) & 112) | 6;
            BoxScope boxScope = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 1470470086, "C386@15093L201,392@15303L345,403@15657L980:MeloXDiscoveryScreens.kt#301xg3");
            SingletonAsyncImageKt.m9399AsyncImage10Xjiaw(playlist.getCoverUrl(), playlist.getName(), SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, null, null, ContentScale.INSTANCE.getCrop(), 0.0f, null, 0, false, $composer3, 1573248, 0, 1976);
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            Brush.Companion companion = Brush.INSTANCE;
            Float fValueOf = Float.valueOf(1.0f);
            long jM6094getBlack0d7_KjU = Color.INSTANCE.m6094getBlack0d7_KjU();
            BoxKt.Box(BackgroundKt.background$default(modifierFillMaxSize$default, Brush.Companion.m6024verticalGradient8A3gB4$default(companion, new Pair[]{TuplesKt.to(Float.valueOf(0.0f), Color.m6058boximpl(Color.INSTANCE.m6103getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(0.58f), Color.m6058boximpl(Color.INSTANCE.m6103getTransparent0d7_KjU())), TuplesKt.to(fValueOf, Color.m6058boximpl(Color.copy_wmQWz5c(jM6094getBlack0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6094getBlack0d7_KjU) : 0.84f, (14 & 2) != 0 ? Color.getRed_impl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6094getBlack0d7_KjU) : 0.0f)))}, 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null), $composer3, 6);
            Modifier modifierM1805padding3ABfNKs = PaddingKt.m1805padding3ABfNKs(boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getBottomStart()), Dp.constructor_impl(20));
            Arrangement.Vertical verticalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(Dp.constructor_impl(6));
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM1497spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer3, modifierM1805padding3ABfNKs);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((48 << 3) & 112) << 6) & 896) | 6;
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
            Composer composerM5188constructorimpl2 = Updater.constructor_impl($composer3);
            Updater.set_impl(composerM5188constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i6 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -753100057, "C409@15862L100,410@15975L283,419@16271L356:MeloXDiscoveryScreens.kt#301xg3");
            long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
            $composer2 = $composer3;
            str = badge;
            TextKt.m3912TextNvy7gAk(str, null, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU) : 0.76f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU) : 0.0f), null, TextUnitKt.getSp(12), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, (($dirty2 >> 3) & 14) | 1597824, 0, 262058);
            TextKt.m3912TextNvy7gAk(playlist.getName(), null, Color.INSTANCE.m6105getWhite0d7_KjU(), null, TextUnitKt.getSp(22), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, TextUnitKt.getSp(27), TextOverflow.INSTANCE.m8816getEllipsisgIe3tQ8(), false, 2, 0, null, null, $composer3, 1597824, 25008, 239530);
            StringBuilder sb = new StringBuilder();
            sb.append("▶  ");
            if (playlist.getPlayCount() > 0) {
                sb.append(compactCount(playlist.getPlayCount()));
            } else {
                String creatorName = playlist.getCreatorName();
                if (StringsKt.isBlank(creatorName)) {
                    creatorName = "网易云音乐";
                }
                sb.append(creatorName);
            }
            String string = sb.toString();
            long jM6105getWhite0d7_KjU2 = Color.INSTANCE.m6105getWhite0d7_KjU();
            TextKt.m3912TextNvy7gAk(string, null, Color.copy_wmQWz5c(jM6105getWhite0d7_KjU2, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU2) : 0.72f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU2) : 0.0f), null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 24960, 0, 262122);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda26
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXDiscoveryScreensKt.FeaturedPlaylist$lambda$1(playlist, str, function0, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void SectionHeader(String title, String trailing, Composer $composer, final int $changed, final int i) {
        final String trailing2;
        Composer $composer2;
        Composer composer;
        final String str = title;
        Composer $composer3 = $composer.startRestartGroup(-1630487299);
        ComposerKt.sourceInformation($composer3, "C(SectionHeader)N(title,trailing)434@16732L407:MeloXDiscoveryScreens.kt#301xg3");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(str) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            trailing2 = trailing;
        } else if (($changed & 48) == 0) {
            trailing2 = trailing;
            $dirty |= $composer3.changed(trailing2) ? 32 : 16;
        } else {
            trailing2 = trailing;
        }
        if (!$composer3.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            String trailing3 = i2 != 0 ? null : trailing2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1630487299, $dirty, -1, "com.lladlam.melox.ui.discovery.SectionHeader (MeloXDiscoveryScreens.kt:433)");
            }
            Modifier modifierM1807paddingVpY3zN4$default = PaddingKt.m1807paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.constructor_impl(16), 0.0f, 2, null);
            Alignment.Vertical bottom = Alignment.INSTANCE.getBottom();
            ComposerKt.sourceInformationMarkerStart($composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), bottom, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1807paddingVpY3zN4$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i3 = ((((390 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor);
            } else {
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl = Updater.constructor_impl($composer3);
            Updater.set_impl(composerM5188constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i4 = (i3 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i5 = ((390 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -764892074, "C440@16897L79,441@16985L27:MeloXDiscoveryScreens.kt#301xg3");
            $composer2 = $composer3;
            str = title;
            TextKt.m3912TextNvy7gAk(str, null, 0L, null, TextUnitKt.getSp(24), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, TextUnitKt.getSp(29), 0, false, 0, 0, null, null, $composer3, ($dirty & 14) | 1597440, 48, 260014);
            SpacerKt.Spacer(RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null), $composer3, 0);
            if (trailing3 == null) {
                $composer3.startReplaceGroup(-764763302);
                $composer3.endReplaceGroup();
                composer = $composer3;
            } else {
                $composer3.startReplaceGroup(-764763301);
                ComposerKt.sourceInformation($composer3, "*442@17086L11,442@17037L94");
                long sp = TextUnitKt.getSp(12);
                long onBackground = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnBackground();
                composer = $composer3;
                TextKt.m3912TextNvy7gAk(trailing3, null, Color.copy_wmQWz5c(onBackground, (14 & 1) != 0 ? Color.getAlpha_impl(onBackground) : 0.42f, (14 & 2) != 0 ? Color.getRed_impl(onBackground) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(onBackground) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(onBackground) : 0.0f), null, sp, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 24576, 0, 262122);
                composer.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            trailing2 = trailing3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda27
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXDiscoveryScreensKt.SectionHeader$lambda$1(str, trailing2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlaylistGridCard(final NeteasePlaylistSummary playlist, Modifier modifier, final Function0<Unit> function0, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Function0<Unit> function1;
        Composer $composer2;
        final Modifier modifier3;
        Modifier modifier4;
        Function0<ComposeUiNode> function2;
        Function0<ComposeUiNode> function3;
        Composer $composer3 = $composer.startRestartGroup(572802025);
        ComposerKt.sourceInformation($composer3, "C(PlaylistGridCard)N(playlist,modifier,onClick)452@17291L1775:MeloXDiscoveryScreens.kt#301xg3");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(playlist) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            function1 = function0;
            $dirty |= $composer3.changedInstance(function1) ? 256 : 128;
        } else {
            function1 = function0;
        }
        int $dirty2 = $dirty;
        if (!$composer3.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(572802025, $dirty2, -1, "com.lladlam.melox.ui.discovery.PlaylistGridCard (MeloXDiscoveryScreens.kt:451)");
            }
            Modifier modifierM1078clickableoSLSa3U$default = ClickableKt.m1078clickableoSLSa3U$default(modifier4, false, null, null, null, function1, 15, null);
            Arrangement.Vertical verticalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(Dp.constructor_impl(7));
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM1497spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1078clickableoSLSa3U$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i3 = ((((48 << 3) & 112) << 6) & 896) | 6;
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
            Composer composerM5188constructorimpl = Updater.constructor_impl($composer3);
            Updater.set_impl(composerM5188constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i4 = (i3 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i5 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -818995597, "C461@17624L11,456@17431L1117,484@18557L222,497@19005L11,492@18788L272:MeloXDiscoveryScreens.kt#301xg3");
            $composer2 = $composer3;
            Modifier modifierClip = ClipKt.clip(AspectRatioKt.aspectRatio$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 1.0f, false, 2, null), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(12)));
            long onBackground = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnBackground();
            Modifier modifierM1043backgroundbw27NRU$default = BackgroundKt.m1043backgroundbw27NRU$default(modifierClip, Color.copy_wmQWz5c(onBackground, (14 & 1) != 0 ? Color.getAlpha_impl(onBackground) : 0.07f, (14 & 2) != 0 ? Color.getRed_impl(onBackground) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(onBackground) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(onBackground) : 0.0f), null, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer3, modifierM1043backgroundbw27NRU$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i6 = ((((0 << 3) & 112) << 6) & 896) | 6;
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
            Composer composerM5188constructorimpl2 = Updater.constructor_impl($composer3);
            Updater.set_impl(composerM5188constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i7 = (i6 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            int i8 = ((0 >> 6) & 112) | 6;
            BoxScope boxScope = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 1067670091, "C463@17695L221:MeloXDiscoveryScreens.kt#301xg3");
            SingletonAsyncImageKt.m9399AsyncImage10Xjiaw(playlist.getCoverUrl(), playlist.getName(), SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, null, null, ContentScale.INSTANCE.getCrop(), 0.0f, null, 0, false, $composer3, 1573248, 0, 1976);
            Composer composer = $composer3;
            if (playlist.getPlayCount() > 0) {
                composer.startReplaceGroup(1067922833);
                ComposerKt.sourceInformation(composer, "470@17976L548");
                String str = "▶ " + compactCount(playlist.getPlayCount());
                Modifier modifierClip2 = ClipKt.clip(PaddingKt.m1805padding3ABfNKs(boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopEnd()), Dp.constructor_impl(8)), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(14)));
                long jM6094getBlack0d7_KjU = Color.INSTANCE.m6094getBlack0d7_KjU();
                composer = composer;
                TextKt.m3912TextNvy7gAk(str, PaddingKt.m1806paddingVpY3zN4(BackgroundKt.m1043backgroundbw27NRU$default(modifierClip2, Color.copy_wmQWz5c(jM6094getBlack0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6094getBlack0d7_KjU) : 0.48f, (14 & 2) != 0 ? Color.getRed_impl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6094getBlack0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6094getBlack0d7_KjU) : 0.0f), null, 2, null), Dp.constructor_impl(8), Dp.constructor_impl(5)), Color.INSTANCE.m6105getWhite0d7_KjU(), null, TextUnitKt.getSp(11), null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 1597824, 0, 262056);
                composer.endReplaceGroup();
            } else {
                composer.startReplaceGroup(1068480213);
                composer.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            TextKt.m3912TextNvy7gAk(playlist.getName(), null, 0L, null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, TextUnitKt.getSp(18), TextOverflow.INSTANCE.m8816getEllipsisgIe3tQ8(), false, 2, 0, null, null, $composer3, 1597440, 25008, 239534);
            String creatorName = playlist.getCreatorName();
            if (StringsKt.isBlank(creatorName)) {
                creatorName = playlist.getTrackCount() + " 首歌曲";
            }
            int iM8816getEllipsisgIe3tQ8 = TextOverflow.INSTANCE.m8816getEllipsisgIe3tQ8();
            long sp = TextUnitKt.getSp(12);
            long onBackground2 = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnBackground();
            TextKt.m3912TextNvy7gAk(creatorName, null, Color.copy_wmQWz5c(onBackground2, (14 & 1) != 0 ? Color.getAlpha_impl(onBackground2) : 0.46f, (14 & 2) != 0 ? Color.getRed_impl(onBackground2) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(onBackground2) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(onBackground2) : 0.0f), null, sp, null, null, null, 0L, null, null, 0L, iM8816getEllipsisgIe3tQ8, false, 1, 0, null, null, $composer3, 24576, 24960, 241642);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda38
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXDiscoveryScreensKt.PlaylistGridCard$lambda$1(playlist, modifier3, function0, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void HomeEditorialStrip(final NeteasePlaylistSummary daily, final NeteasePlaylistSummary fresh, final NeteasePlaylistSummary hot, final Function1<? super NeteasePlaylistSummary, Unit> function1, Composer $composer, final int $changed) {
        NeteasePlaylistSummary neteasePlaylistSummary;
        NeteasePlaylistSummary neteasePlaylistSummary2;
        NeteasePlaylistSummary neteasePlaylistSummary3;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-899302123);
        ComposerKt.sourceInformation($composer3, "C(HomeEditorialStrip)N(daily,fresh,hot,openPlaylist)528@20109L1361,525@19973L1497:MeloXDiscoveryScreens.kt#301xg3");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            neteasePlaylistSummary = daily;
            $dirty |= $composer3.changed(neteasePlaylistSummary) ? 4 : 2;
        } else {
            neteasePlaylistSummary = daily;
        }
        if (($changed & 48) == 0) {
            neteasePlaylistSummary2 = fresh;
            $dirty |= $composer3.changed(neteasePlaylistSummary2) ? 32 : 16;
        } else {
            neteasePlaylistSummary2 = fresh;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            neteasePlaylistSummary3 = hot;
            $dirty |= $composer3.changed(neteasePlaylistSummary3) ? 256 : 128;
        } else {
            neteasePlaylistSummary3 = hot;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 2048 : 1024;
        }
        if (!$composer3.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-899302123, $dirty, -1, "com.lladlam.melox.ui.discovery.HomeEditorialStrip (MeloXDiscoveryScreens.kt:519)");
            }
            final List cards = CollectionsKt.listOf((Object[]) new EditorialCardData[]{new EditorialCardData("每日更新", "每日推荐", "为你定制的歌曲", CollectionsKt.listOf((Object[]) new Color[]{Color.m6058boximpl(ColorKt.Color(4294914656L)), Color.m6058boximpl(ColorKt.Color(4294914107L))}), EditorialGlyph.Calendar, neteasePlaylistSummary), new EditorialCardData("新鲜发行", "新碟上架", "发现最近发行", CollectionsKt.listOf((Object[]) new Color[]{Color.m6058boximpl(ColorKt.Color(4291308779L)), Color.m6058boximpl(ColorKt.Color(4286789355L))}), EditorialGlyph.Disc, neteasePlaylistSummary2), new EditorialCardData("全站热门", "热门排行", "大家都在听", CollectionsKt.listOf((Object[]) new Color[]{Color.m6058boximpl(ColorKt.Color(4294941224L)), Color.m6058boximpl(ColorKt.Color(4294916912L))}), EditorialGlyph.Chart, neteasePlaylistSummary3)});
            PaddingValues paddingValuesM1800PaddingValuesYgX7TsA$default = PaddingKt.m1800PaddingValuesYgX7TsA$default(Dp.constructor_impl(16), 0.0f, 2, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(Dp.constructor_impl(16));
            ComposerKt.sourceInformationMarkerStart($composer3, -940888666, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            boolean zChangedInstance = $composer3.changedInstance(cards) | (($dirty & 7168) == 2048);
            Object objRememberedValue = $composer3.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda39
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return MeloXDiscoveryScreensKt.HomeEditorialStrip$lambda$0$0(cards, function1, (LazyListScope) obj2);
                    }
                };
                $composer3.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            LazyDslKt.LazyRow(null, null, paddingValuesM1800PaddingValuesYgX7TsA$default, false, horizontalOrVerticalM1497spacedBy0680j_4, null, null, false, null, (Function1) objRememberedValue, $composer2, 24960, 491);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda40
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return MeloXDiscoveryScreensKt.HomeEditorialStrip$lambda$1(daily, fresh, hot, function1, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeEditorialStrip$lambda$0$0(final List $cards, final Function1 $openPlaylist, LazyListScope LazyRow) {
        Intrinsics.checkNotNullParameter(LazyRow, "$this$LazyRow");
        final Function1 function1 = new Function1() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$HomeEditorialStrip$lambda$0$0$$inlined$items$default$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                return invoke((EditorialCardData) p1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Void invoke(EditorialCardData editorialCardData) {
                return null;
            }
        };
        LazyRow.items($cards.size(), null, new Function1<Integer, Object>() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$HomeEditorialStrip$lambda$0$0$$inlined$items$default$3
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int index) {
                return function1.invoke($cards.get(index));
            }
        }, ComposableLambdaKt.composableLambdaInstance(802480018, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$HomeEditorialStrip$lambda$0$0$$inlined$items$default$4
            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                return Unit.INSTANCE;
            }

            /* JADX WARN: Code duplicated, block: B:40:0x015a  */
            /* JADX WARN: Code duplicated, block: B:43:0x0166  */
            /* JADX WARN: Code duplicated, block: B:44:0x016c  */
            /* JADX WARN: Code duplicated, block: B:47:0x0364  */
            /* JADX WARN: Code duplicated, block: B:50:0x0370  */
            /* JADX WARN: Code duplicated, block: B:51:0x0376  */
            /* JADX WARN: Code duplicated, block: B:54:0x04a1  */
            /* JADX WARN: Code duplicated, block: B:57:? A[RETURN, SYNTHETIC] */
            public final void invoke(LazyItemScope $this$items, int it, Composer $composer, int $changed) {
                int i;
                Function0<ComposeUiNode> constructor;
                Function0<ComposeUiNode> function0;
                Function0<ComposeUiNode> constructor2;
                Function0<ComposeUiNode> function2;
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
                    int i2 = $dirty & 14;
                    final EditorialCardData editorialCardData = (EditorialCardData) $cards.get(it);
                    $composer.startReplaceGroup(-925102856);
                    ComposerKt.sourceInformation($composer, "CN(card)*533@20297L36,530@20154L1300:MeloXDiscoveryScreens.kt#301xg3");
                    Modifier modifierM1877width3ABfNKs = SizeKt.m1877width3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(326));
                    boolean z = editorialCardData.getPlaylist() != null;
                    ComposerKt.sourceInformationMarkerStart($composer, -1415312037, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
                    boolean zChangedInstance = $composer.changedInstance(editorialCardData) | $composer.changed($openPlaylist);
                    Object objRememberedValue = $composer.rememberedValue();
                    if (!zChangedInstance) {
                        i = 0;
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        Modifier modifierM1078clickableoSLSa3U$default = ClickableKt.m1078clickableoSLSa3U$default(modifierM1877width3ABfNKs, z, null, null, null, (Function0) objRememberedValue, 14, null);
                        int i3 = i;
                        ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                        MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((i3 >> 3) & 14) | ((i3 >> 3) & 112));
                        int i4 = (i3 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                        int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, i));
                        CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM1078clickableoSLSa3U$default);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        int i5 = ((i4 << 6) & 896) | 6;
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
                        int i6 = (i5 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                        int i7 = ((i3 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, 1496459761, "C535@20426L11,535@20367L104,536@20488L66,537@20631L11,537@20571L105,538@20693L747:MeloXDiscoveryScreens.kt#301xg3");
                        String eyebrow = editorialCardData.getEyebrow();
                        long sp = TextUnitKt.getSp(12);
                        long onBackground = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnBackground();
                        TextKt.m3912TextNvy7gAk(eyebrow, null, Color.copy_wmQWz5c(onBackground, (14 & 1) != 0 ? Color.getAlpha_impl(onBackground) : 0.48f, (14 & 2) != 0 ? Color.getRed_impl(onBackground) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(onBackground) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(onBackground) : 0.0f), null, sp, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24576, 0, 262122);
                        TextKt.m3912TextNvy7gAk(editorialCardData.getTitle(), null, 0L, null, TextUnitKt.getSp(20), null, FontWeight.INSTANCE.getMedium(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1597440, 0, 262062);
                        String subtitle = editorialCardData.getSubtitle();
                        long sp2 = TextUnitKt.getSp(14);
                        long onBackground2 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnBackground();
                        TextKt.m3912TextNvy7gAk(subtitle, null, Color.copy_wmQWz5c(onBackground2, (14 & 1) != 0 ? Color.getAlpha_impl(onBackground2) : 0.48f, (14 & 2) != 0 ? Color.getRed_impl(onBackground2) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(onBackground2) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(onBackground2) : 0.0f), null, sp2, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24576, 0, 262122);
                        Modifier modifierBackground$default = BackgroundKt.background$default(ClipKt.clip(SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.constructor_impl(9), 0.0f, 0.0f, 13, null), 0.0f, 1, null), Dp.constructor_impl(214)), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(14))), Brush.INSTANCE.m6028linearGradientmHitzGk((List<Color>) editorialCardData.getColors(), (14 & 2) != 0 ? Offset.INSTANCE.m5839getZeroF1C5BW0() : 0L, (14 & 4) != 0 ? Offset.INSTANCE.m5837getInfiniteF1C5BW0() : 0L, (14 & 8) != 0 ? TileMode.INSTANCE.m6463getClamp3opZhB0() : 0), null, 0.0f, 6, null);
                        ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                        ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                        int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                        CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierBackground$default);
                        constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                        int i8 = ((((0 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function2 = constructor2;
                            $composer.createNode(function2);
                        } else {
                            function2 = constructor2;
                            $composer.useNode();
                        }
                        Composer composerM5188constructorimpl2 = Updater.constructor_impl($composer);
                        Updater.set_impl(composerM5188constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.set_impl(composerM5188constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.set_impl(composerM5188constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.reconcile_impl(composerM5188constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.set_impl(composerM5188constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                        int i9 = (i8 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                        int i10 = ((0 >> 6) & 112) | 6;
                        BoxScope boxScope = BoxScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer, 1128744770, "C546@21032L76,547@21129L293:MeloXDiscoveryScreens.kt#301xg3");
                        MeloXDiscoveryScreensKt.EditorialGlyphIcon(editorialCardData.getGlyph(), SizeKt.m1872size3ABfNKs(boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenter()), Dp.constructor_impl(70)), $composer, 0, 0);
                        TextKt.m3912TextNvy7gAk(editorialCardData.getTitle(), PaddingKt.m1805padding3ABfNKs(boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getBottomStart()), Dp.constructor_impl(18)), Color.INSTANCE.m6105getWhite0d7_KjU(), null, TextUnitKt.getSp(26), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1597824, 0, 262056);
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
                    i = 0;
                    final Function1 function3 = $openPlaylist;
                    Object obj = (Function0) new Function0<Unit>() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$HomeEditorialStrip$1$1$1$1$1
                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            NeteasePlaylistSummary playlist = editorialCardData.getPlaylist();
                            if (playlist != null) {
                                function3.invoke(playlist);
                            }
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue = obj;
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Modifier modifierM1078clickableoSLSa3U$default2 = ClickableKt.m1078clickableoSLSa3U$default(modifierM1877width3ABfNKs, z, null, null, null, (Function0) objRememberedValue, 14, null);
                    int i11 = i;
                    ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((i11 >> 3) & 14) | ((i11 >> 3) & 112));
                    int i12 = (i11 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, i));
                    CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifierM1078clickableoSLSa3U$default2);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int i13 = ((i12 << 6) & 896) | 6;
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
                    Composer composerM5188constructorimpl3 = Updater.constructor_impl($composer);
                    Updater.set_impl(composerM5188constructorimpl3, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.set_impl(composerM5188constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.set_impl(composerM5188constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.reconcile_impl(composerM5188constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.set_impl(composerM5188constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                    int i14 = (i13 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                    int i15 = ((i11 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 1496459761, "C535@20426L11,535@20367L104,536@20488L66,537@20631L11,537@20571L105,538@20693L747:MeloXDiscoveryScreens.kt#301xg3");
                    String eyebrow2 = editorialCardData.getEyebrow();
                    long sp3 = TextUnitKt.getSp(12);
                    long onBackground3 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnBackground();
                    TextKt.m3912TextNvy7gAk(eyebrow2, null, Color.copy_wmQWz5c(onBackground3, (14 & 1) != 0 ? Color.getAlpha_impl(onBackground3) : 0.48f, (14 & 2) != 0 ? Color.getRed_impl(onBackground3) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(onBackground3) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(onBackground3) : 0.0f), null, sp3, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24576, 0, 262122);
                    TextKt.m3912TextNvy7gAk(editorialCardData.getTitle(), null, 0L, null, TextUnitKt.getSp(20), null, FontWeight.INSTANCE.getMedium(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1597440, 0, 262062);
                    String subtitle2 = editorialCardData.getSubtitle();
                    long sp4 = TextUnitKt.getSp(14);
                    long onBackground4 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnBackground();
                    TextKt.m3912TextNvy7gAk(subtitle2, null, Color.copy_wmQWz5c(onBackground4, (14 & 1) != 0 ? Color.getAlpha_impl(onBackground4) : 0.48f, (14 & 2) != 0 ? Color.getRed_impl(onBackground4) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(onBackground4) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(onBackground4) : 0.0f), null, sp4, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24576, 0, 262122);
                    Modifier modifierBackground$default2 = BackgroundKt.background$default(ClipKt.clip(SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.constructor_impl(9), 0.0f, 0.0f, 13, null), 0.0f, 1, null), Dp.constructor_impl(214)), RoundedCornerShapeKt.m2135RoundedCornerShape0680j_4(Dp.constructor_impl(14))), Brush.INSTANCE.m6028linearGradientmHitzGk((List<Color>) editorialCardData.getColors(), (14 & 2) != 0 ? Offset.INSTANCE.m5839getZeroF1C5BW0() : 0L, (14 & 4) != 0 ? Offset.INSTANCE.m5837getInfiniteF1C5BW0() : 0L, (14 & 8) != 0 ? TileMode.INSTANCE.m6463getClamp3opZhB0() : 0), null, 0.0f, 6, null);
                    ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                    int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap4 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer, modifierBackground$default2);
                    constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int i16 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function2 = constructor2;
                        $composer.createNode(function2);
                    } else {
                        function2 = constructor2;
                        $composer.useNode();
                    }
                    Composer composerM5188constructorimpl4 = Updater.constructor_impl($composer);
                    Updater.set_impl(composerM5188constructorimpl4, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.set_impl(composerM5188constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.set_impl(composerM5188constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.reconcile_impl(composerM5188constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.set_impl(composerM5188constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                    int i17 = (i16 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    int i18 = ((0 >> 6) & 112) | 6;
                    BoxScope boxScope2 = BoxScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer, 1128744770, "C546@21032L76,547@21129L293:MeloXDiscoveryScreens.kt#301xg3");
                    MeloXDiscoveryScreensKt.EditorialGlyphIcon(editorialCardData.getGlyph(), SizeKt.m1872size3ABfNKs(boxScope2.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenter()), Dp.constructor_impl(70)), $composer, 0, 0);
                    TextKt.m3912TextNvy7gAk(editorialCardData.getTitle(), PaddingKt.m1805padding3ABfNKs(boxScope2.align(Modifier.INSTANCE, Alignment.INSTANCE.getBottomStart()), Dp.constructor_impl(18)), Color.INSTANCE.m6105getWhite0d7_KjU(), null, TextUnitKt.getSp(26), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1597824, 0, 262056);
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
    public static final void EditorialGlyphIcon(final EditorialGlyph glyph, final Modifier modifier, Composer $composer, final int $changed, final int i) {
        Composer $composer2 = $composer.startRestartGroup(-1292793737);
        ComposerKt.sourceInformation($composer2, "C(EditorialGlyphIcon)N(glyph,modifier)562@21594L1151,562@21577L1168:MeloXDiscoveryScreens.kt#301xg3");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(glyph.ordinal()) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        if (!$composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (i2 != 0) {
                modifier = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1292793737, $dirty, -1, "com.lladlam.melox.ui.discovery.EditorialGlyphIcon (MeloXDiscoveryScreens.kt:561)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -1129228362, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            boolean z = ($dirty & 14) == 4;
            Object objRememberedValue = $composer2.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return MeloXDiscoveryScreensKt.EditorialGlyphIcon$lambda$0$0(glyph, (DrawScope) obj2);
                    }
                };
                $composer2.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifier, (Function1) objRememberedValue, $composer2, ($dirty >> 3) & 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return MeloXDiscoveryScreensKt.EditorialGlyphIcon$lambda$1(glyph, modifier, $changed, i, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit EditorialGlyphIcon$lambda$0$0(EditorialGlyph $glyph, DrawScope $this$Canvas) {
        DrawScope Canvas = $this$Canvas;
        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
        long jM6105getWhite0d7_KjU = Color.INSTANCE.m6105getWhite0d7_KjU();
        long c = Color.copy_wmQWz5c(jM6105getWhite0d7_KjU, (14 & 1) != 0 ? Color.getAlpha_impl(jM6105getWhite0d7_KjU) : 0.28f, (14 & 2) != 0 ? Color.getRed_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(jM6105getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(jM6105getWhite0d7_KjU) : 0.0f);
        float stroke = Size.m5891getMinDimensionimpl(Canvas.mo6642getSizeNHjbRc()) * 0.09f;
        switch (WhenMappings.$EnumSwitchMapping$0[$glyph.ordinal()]) {
            case 1:
                int i = 3;
                DrawScope.m6638drawRoundRectuAw5IA$default($this$Canvas, c, 0L, 0L, CornerRadius.m5777constructorimpl((((long) Float.floatToRawIntBits(10.0f)) << 32) | (((long) Float.floatToRawIntBits(10.0f)) & 4294967295L)), new Stroke(stroke, 0.0f, 0, 0, null, 30, null), 0.0f, null, 0, 230, null);
                int x = 0;
                while (x < i) {
                    int y = 0;
                    while (y < i) {
                        DrawScope.m6623drawCircleVaOC9Bg$default($this$Canvas, c, stroke * 0.36f, Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) ($this$Canvas.mo6642getSizeNHjbRc() >> 32)) * ((x * 0.2f) + 0.3f))) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) ($this$Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * ((y * 0.18f) + 0.38f))) & 4294967295L)), 0.0f, null, null, 0, 120, null);
                        y++;
                        i = 3;
                    }
                    x++;
                    i = 3;
                }
                break;
            case 2:
                DrawScope.m6623drawCircleVaOC9Bg$default($this$Canvas, c, Size.m5891getMinDimensionimpl($this$Canvas.mo6642getSizeNHjbRc()) * 0.41f, 0L, 0.0f, new Stroke(stroke, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                DrawScope.m6623drawCircleVaOC9Bg$default($this$Canvas, c, Size.m5891getMinDimensionimpl($this$Canvas.mo6642getSizeNHjbRc()) * 0.1f, 0L, 0.0f, null, null, 0, 124, null);
                break;
            case 3:
                Iterable iterableListOf = CollectionsKt.listOf((Object[]) new Float[]{Float.valueOf(0.38f), Float.valueOf(0.62f), Float.valueOf(0.88f)});
                int i2 = 0;
                for (Object obj : iterableListOf) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    float fFloatValue = ((Number) obj).floatValue();
                    float stroke2 = stroke;
                    long jM5815constructorimpl = Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * ((i2 * 0.25f) + 0.25f))) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * 0.82f)) & 4294967295L));
                    float fIntBitsToFloat = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() >> 32)) * ((i2 * 0.25f) + 0.25f);
                    Iterable iterable = iterableListOf;
                    float fIntBitsToFloat2 = Float.intBitsToFloat((int) (Canvas.mo6642getSizeNHjbRc() & 4294967295L)) * (0.82f - (fFloatValue * 0.62f));
                    Canvas = $this$Canvas;
                    DrawScope.m6628drawLineNGM6Ib0$default(Canvas, c, jM5815constructorimpl, Offset.m5815constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat2)) & 4294967295L)), stroke2, StrokeCap.INSTANCE.m6443getRoundKaPHkGw(), null, 0.0f, null, 0, WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND, null);
                    stroke = stroke2;
                    i2 = i3;
                    iterableListOf = iterable;
                }
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        return Unit.INSTANCE;
    }

    private static final void HomeMediaStrip(final String title, final List<NeteasePlaylistSummary> list, final Function1<? super NeteasePlaylistSummary, Unit> function1, Composer $composer, final int $changed) {
        Function0<ComposeUiNode> function0;
        Composer $composer2 = $composer.startRestartGroup(553157812);
        ComposerKt.sourceInformation($composer2, "C(HomeMediaStrip)N(title,playlists,openPlaylist)589@22913L542:MeloXDiscoveryScreens.kt#301xg3");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(title) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(list) ? 32 : 16;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 256 : 128;
        }
        if (!$composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(553157812, $dirty, -1, "com.lladlam.melox.ui.discovery.HomeMediaStrip (MeloXDiscoveryScreens.kt:588)");
            }
            Arrangement.Vertical verticalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(Dp.constructor_impl(13));
            ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM1497spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifier);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((48 << 3) & 112) << 6) & 896) | 6;
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
            Composer composerM5188constructorimpl = Updater.constructor_impl($composer2);
            Updater.set_impl(composerM5188constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 801191345, "C590@22981L20,594@23158L291,591@23010L439:MeloXDiscoveryScreens.kt#301xg3");
            SectionHeader(title, null, $composer2, $dirty & 14, 2);
            PaddingValues paddingValuesM1800PaddingValuesYgX7TsA$default = PaddingKt.m1800PaddingValuesYgX7TsA$default(Dp.constructor_impl(16), 0.0f, 2, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1497spacedBy0680j_4 = Arrangement.INSTANCE.m1497spacedBy0680j_4(Dp.constructor_impl(14));
            ComposerKt.sourceInformationMarkerStart($composer2, 25850369, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
            boolean zChangedInstance = $composer2.changedInstance(list) | (($dirty & 896) == 256);
            Object objRememberedValue = $composer2.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda50
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return MeloXDiscoveryScreensKt.HomeMediaStrip$lambda$0$0$0(list, function1, (LazyListScope) obj2);
                    }
                };
                $composer2.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LazyDslKt.LazyRow(null, null, paddingValuesM1800PaddingValuesYgX7TsA$default, false, horizontalOrVerticalM1497spacedBy0680j_4, null, null, false, null, (Function1) objRememberedValue, $composer2, 24960, 491);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda51
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return MeloXDiscoveryScreensKt.HomeMediaStrip$lambda$1(title, list, function1, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeMediaStrip$lambda$0$0$0(final List $playlists, final Function1 $openPlaylist, LazyListScope LazyRow) {
        Intrinsics.checkNotNullParameter(LazyRow, "$this$LazyRow");
        final Function1 function1 = new Function1() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda42
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MeloXDiscoveryScreensKt.HomeMediaStrip$lambda$0$0$0$0((NeteasePlaylistSummary) obj);
            }
        };
        final Function1 function2 = new Function1() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$HomeMediaStrip$lambda$0$0$0$$inlined$items$default$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                return invoke((NeteasePlaylistSummary) p1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Void invoke(NeteasePlaylistSummary neteasePlaylistSummary) {
                return null;
            }
        };
        LazyRow.items($playlists.size(), new Function1<Integer, Object>() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$HomeMediaStrip$lambda$0$0$0$$inlined$items$default$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int index) {
                return function1.invoke($playlists.get(index));
            }
        }, new Function1<Integer, Object>() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$HomeMediaStrip$lambda$0$0$0$$inlined$items$default$3
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int index) {
                return function2.invoke($playlists.get(index));
            }
        }, ComposableLambdaKt.composableLambdaInstance(802480018, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$HomeMediaStrip$lambda$0$0$0$$inlined$items$default$4
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
                final NeteasePlaylistSummary neteasePlaylistSummary = (NeteasePlaylistSummary) $playlists.get(it);
                $composer.startReplaceGroup(-768765430);
                ComposerKt.sourceInformation($composer, "CN(playlist)*599@23380L26,596@23236L189:MeloXDiscoveryScreens.kt#301xg3");
                Modifier modifierM1877width3ABfNKs = SizeKt.m1877width3ABfNKs(Modifier.INSTANCE, Dp.constructor_impl(154));
                ComposerKt.sourceInformationMarkerStart($composer, 2053415542, "CC(remember):MeloXDiscoveryScreens.kt#9igjgp");
                boolean zChanged = $composer.changed($openPlaylist) | ((((i & 112) ^ 48) > 32 && $composer.changed(neteasePlaylistSummary)) || (i & 48) == 32);
                Object objRememberedValue = $composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    final Function1 function3 = $openPlaylist;
                    Object obj = (Function0) new Function0<Unit>() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$HomeMediaStrip$1$1$1$2$1$1
                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            function3.invoke(neteasePlaylistSummary);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                MeloXDiscoveryScreensKt.PlaylistGridCard(neteasePlaylistSummary, modifierM1877width3ABfNKs, (Function0) objRememberedValue, $composer, ((i >> 3) & 14) | 48, 0);
                $composer.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object HomeMediaStrip$lambda$0$0$0$0(NeteasePlaylistSummary it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Long.valueOf(it.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LoadingState(String text, Composer $composer, final int $changed) {
        Composer $composer2;
        final String str = text;
        Composer $composer3 = $composer.startRestartGroup(1420795723);
        ComposerKt.sourceInformation($composer3, "C(LoadingState)N(text)608@23516L355:MeloXDiscoveryScreens.kt#301xg3");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(str) ? 4 : 2;
        }
        if (!$composer3.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1420795723, $dirty, -1, "com.lladlam.melox.ui.discovery.LoadingState (MeloXDiscoveryScreens.kt:607)");
            }
            Modifier modifierM1858height3ABfNKs = SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.constructor_impl(260));
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            Arrangement.Vertical center = Arrangement.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1858height3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((438 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor);
            } else {
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
            int i3 = ((438 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -392472506, "C613@23709L27,614@23820L11,614@23745L120:MeloXDiscoveryScreens.kt#301xg3");
            ProgressIndicatorKt.m3567CircularProgressIndicator4lLiAd8(null, 0L, 0.0f, 0L, 0, 0.0f, $composer3, 0, 63);
            Modifier modifierM1809paddingqDBjuR0$default = PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.constructor_impl(14), 0.0f, 0.0f, 13, null);
            long onBackground = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnBackground();
            $composer2 = $composer3;
            str = text;
            TextKt.m3912TextNvy7gAk(str, modifierM1809paddingqDBjuR0$default, Color.copy_wmQWz5c(onBackground, (14 & 1) != 0 ? Color.getAlpha_impl(onBackground) : 0.48f, (14 & 2) != 0 ? Color.getRed_impl(onBackground) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(onBackground) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(onBackground) : 0.0f), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, ($dirty & 14) | 48, 0, 262136);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda21
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXDiscoveryScreensKt.LoadingState$lambda$1(str, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void ErrorState(String message, Function0<Unit> function0, Composer $composer, final int $changed) {
        final String str;
        Composer $composer2;
        final Function0<Unit> function1 = function0;
        Composer $composer3 = $composer.startRestartGroup(-382779741);
        ComposerKt.sourceInformation($composer3, "C(ErrorState)N(message,retry)620@23952L547:MeloXDiscoveryScreens.kt#301xg3");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(message) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 32 : 16;
        }
        if ($composer3.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-382779741, $dirty, -1, "com.lladlam.melox.ui.discovery.ErrorState (MeloXDiscoveryScreens.kt:619)");
            }
            Modifier modifierM1858height3ABfNKs = SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.constructor_impl(260));
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            Arrangement.Vertical center = Arrangement.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1858height3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((438 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor);
            } else {
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
            int i3 = ((438 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 1532364590, "C625@24181L11,625@24145L81,626@24235L258:MeloXDiscoveryScreens.kt#301xg3");
            long onBackground = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnBackground();
            $composer2 = $composer3;
            TextKt.m3912TextNvy7gAk(message, null, Color.copy_wmQWz5c(onBackground, (14 & 1) != 0 ? Color.getAlpha_impl(onBackground) : 0.55f, (14 & 2) != 0 ? Color.getRed_impl(onBackground) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(onBackground) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(onBackground) : 0.0f), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, $dirty & 14, 0, 262138);
            str = message;
            function1 = function0;
            TextKt.m3912TextNvy7gAk("重新载入", PaddingKt.m1806paddingVpY3zN4(ClickableKt.m1078clickableoSLSa3U$default(ClipKt.clip(PaddingKt.m1809paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.constructor_impl(12), 0.0f, 0.0f, 13, null), RoundedCornerShapeKt.getCircleShape()), false, null, null, null, function0, 15, null), Dp.constructor_impl(18), Dp.constructor_impl(9)), MeloXAccent, null, 0L, null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 1573254, 0, 262072);
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
            str = message;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXDiscoveryScreensKt.ErrorState$lambda$1(str, function1, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void EmptyState(String message, Composer $composer, final int $changed) {
        Composer $composer2;
        final String str = message;
        Composer $composer3 = $composer.startRestartGroup(835507612);
        ComposerKt.sourceInformation($composer3, "C(EmptyState)N(message)637@24561L178:MeloXDiscoveryScreens.kt#301xg3");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(str) ? 4 : 2;
        }
        if (!$composer3.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(835507612, $dirty, -1, "com.lladlam.melox.ui.discovery.EmptyState (MeloXDiscoveryScreens.kt:636)");
            }
            Modifier modifierM1858height3ABfNKs = SizeKt.m1858height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.constructor_impl(260));
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1858height3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((54 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor);
            } else {
                $composer3.useNode();
            }
            Composer composerM5188constructorimpl = Updater.constructor_impl($composer3);
            Updater.set_impl(composerM5188constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.set_impl(composerM5188constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.set_impl(composerM5188constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.reconcile_impl(composerM5188constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.set_impl(composerM5188constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -1674388432, "C638@24688L11,638@24652L81:MeloXDiscoveryScreens.kt#301xg3");
            long onBackground = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnBackground();
            $composer2 = $composer3;
            str = message;
            TextKt.m3912TextNvy7gAk(str, null, Color.copy_wmQWz5c(onBackground, (14 & 1) != 0 ? Color.getAlpha_impl(onBackground) : 0.48f, (14 & 2) != 0 ? Color.getRed_impl(onBackground) : 0.0f, (14 & 4) != 0 ? Color.getGreen_impl(onBackground) : 0.0f, (14 & 8) != 0 ? Color.getBlue_impl(onBackground) : 0.0f), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, $dirty & 14, 0, 262138);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.discovery.MeloXDiscoveryScreensKt$$ExternalSyntheticLambda20
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXDiscoveryScreensKt.EmptyState$lambda$1(str, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private static final String featuredBadge(String category) {
        switch (category.hashCode()) {
            case 25604578:
                if (category.equals("排行榜")) {
                    return "热门榜单";
                }
                return category;
            case 793205809:
                if (category.equals("推荐歌单")) {
                    return "今日推荐";
                }
                return category;
            case 973069452:
                if (category.equals("精品歌单")) {
                    return "编辑精选";
                }
                return category;
            default:
                return category;
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private static final String collectionTitle(String category) {
        switch (category.hashCode()) {
            case 683136:
                if (category.equals("全部")) {
                    return "热门歌单";
                }
                break;
            case 25604578:
                if (category.equals("排行榜")) {
                    return "全部榜单";
                }
                break;
            case 793205809:
                if (category.equals("推荐歌单")) {
                    return "更多推荐";
                }
                break;
            case 973069452:
                if (category.equals("精品歌单")) {
                    return "更多精品";
                }
                break;
        }
        return category + "歌单";
    }

    private static final String compactCount(long count) {
        if (count >= 100000000) {
            return formatCompact(count / 1.0E8d) + "亿";
        }
        if (count < Renderer.DEFAULT_DURATION_TO_PROGRESS_US) {
            return String.valueOf(count);
        }
        return formatCompact(count / 10000.0d) + "万";
    }

    private static final String formatCompact(double value) {
        if (value < 10.0d) {
            if (!(value % 1.0d == 0.0d)) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = String.format(Locale.US, "%.1f", Arrays.copyOf(new Object[]{Double.valueOf(value)}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                return str;
            }
        }
        return String.valueOf((int) value);
    }
}
