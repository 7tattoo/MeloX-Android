package com.lladlam.melox.ui.theme;

import androidx.compose.foundation.DarkThemeKt;
import androidx.compose.material3.ColorScheme;
import androidx.compose.material3.ColorSchemeKt;
import androidx.compose.material3.MaterialThemeKt;
import androidx.compose.material3.Typography;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.GenericFontFamily;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.media3.exoplayer.RendererCapabilities;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MeloXTheme.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a*\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0011\u0010\t\u001a\r\u0012\u0004\u0012\u00020\u00060\n¢\u0006\u0002\b\u000bH\u0007¢\u0006\u0002\u0010\f\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"LightColors", "Landroidx/compose/material3/ColorScheme;", "DarkColors", "MeloXTypography", "Landroidx/compose/material3/Typography;", "MeloXTheme", "", "darkTheme", "", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(ZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "app"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class MeloXThemeKt {
    private static final Typography MeloXTypography;
    private static final ColorScheme LightColors = ColorSchemeKt.m3114lightColorScheme_VG5OTI$default(ColorKt.Color(4293216333L), Color.INSTANCE.m6105getWhite0d7_KjU(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, ColorKt.Color(4294440954L), ColorKt.Color(4279703322L), ColorKt.Color(4294835710L), ColorKt.Color(4279703322L), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -122884, 65535, null);
    private static final ColorScheme DarkColors = ColorSchemeKt.m3108darkColorScheme_VG5OTI$default(ColorKt.Color(4294927209L), Color.INSTANCE.m6105getWhite0d7_KjU(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, ColorKt.Color(4278913805L), ColorKt.Color(4294309367L), ColorKt.Color(4279571736L), ColorKt.Color(4294309367L), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -122884, 65535, null);

    static final Unit MeloXTheme$lambda$0(boolean z, Function2 function2, int i, int i2, Composer composer, int i3) {
        MeloXTheme(z, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static {
        Typography typography = new Typography(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
        GenericFontFamily sansSerif = FontFamily.INSTANCE.getSansSerif();
        MeloXTypography = new Typography(TextStyle.m8341copyp1EtxEg$default(typography.getDisplayLarge(), 0L, 0L, null, null, null, sansSerif, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777183, null), TextStyle.m8341copyp1EtxEg$default(typography.getDisplayMedium(), 0L, 0L, null, null, null, sansSerif, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777183, null), TextStyle.m8341copyp1EtxEg$default(typography.getDisplaySmall(), 0L, 0L, null, null, null, sansSerif, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777183, null), TextStyle.m8341copyp1EtxEg$default(typography.getHeadlineLarge(), 0L, 0L, null, null, null, sansSerif, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777183, null), TextStyle.m8341copyp1EtxEg$default(typography.getHeadlineMedium(), 0L, 0L, null, null, null, sansSerif, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777183, null), TextStyle.m8341copyp1EtxEg$default(typography.getHeadlineSmall(), 0L, 0L, null, null, null, sansSerif, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777183, null), TextStyle.m8341copyp1EtxEg$default(typography.getTitleLarge(), 0L, 0L, null, null, null, sansSerif, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777183, null), TextStyle.m8341copyp1EtxEg$default(typography.getTitleMedium(), 0L, 0L, null, null, null, sansSerif, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777183, null), TextStyle.m8341copyp1EtxEg$default(typography.getTitleSmall(), 0L, 0L, null, null, null, sansSerif, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777183, null), TextStyle.m8341copyp1EtxEg$default(typography.getBodyLarge(), 0L, 0L, null, null, null, sansSerif, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777183, null), TextStyle.m8341copyp1EtxEg$default(typography.getBodyMedium(), 0L, 0L, null, null, null, sansSerif, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777183, null), TextStyle.m8341copyp1EtxEg$default(typography.getBodySmall(), 0L, 0L, null, null, null, sansSerif, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777183, null), TextStyle.m8341copyp1EtxEg$default(typography.getLabelLarge(), 0L, 0L, null, null, null, sansSerif, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777183, null), TextStyle.m8341copyp1EtxEg$default(typography.getLabelMedium(), 0L, 0L, null, null, null, sansSerif, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777183, null), TextStyle.m8341copyp1EtxEg$default(typography.getLabelSmall(), 0L, 0L, null, null, null, sansSerif, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777183, null));
    }

    public static final void MeloXTheme(final boolean darkTheme, Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        final Function2<? super Composer, ? super Integer, Unit> function2;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(-563642745);
        ComposerKt.sourceInformation($composer2, "C(MeloXTheme)N(darkTheme,content)58@2380L151:MeloXTheme.kt#hj9eyu");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ((i & 1) == 0 && $composer2.changed(darkTheme)) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(content) ? 32 : 16;
        }
        if ($composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "55@2312L21");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 1) != 0) {
                    $dirty &= -15;
                }
            } else if ((i & 1) != 0) {
                darkTheme = DarkThemeKt.isSystemInDarkTheme($composer2, 0);
                $dirty &= -15;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-563642745, $dirty, -1, "com.lladlam.melox.ui.theme.MeloXTheme (MeloXTheme.kt:57)");
            }
            function2 = content;
            MaterialThemeKt.MaterialTheme(darkTheme ? DarkColors : LightColors, null, MeloXTypography, function2, $composer2, (($dirty << 6) & 7168) | RendererCapabilities.DECODER_SUPPORT_MASK, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            function2 = content;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.lladlam.melox.ui.theme.MeloXThemeKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MeloXThemeKt.MeloXTheme$lambda$0(darkTheme, function2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
