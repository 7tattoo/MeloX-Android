package com.lladlam.melox.ui.library;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: MeloXDetailPalette.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0081\b\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\r\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\u000e\u0010\tJ\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J$\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0014\u0010\u0013\u001a\u00020\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001a"}, d2 = {"Lcom/lladlam/melox/ui/library/MeloXDetailPalette;", "", "background", "Landroidx/compose/ui/graphics/Color;", "prefersDarkAppearance", "", "<init>", "(JZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getBackground-0d7_KjU", "()J", "J", "getPrefersDarkAppearance", "()Z", "component1", "component1-0d7_KjU", "component2", "copy", "copy-DxMtmZc", "(JZ)Lcom/lladlam/melox/ui/library/MeloXDetailPalette;", "equals", "other", "hashCode", "", "toString", "", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MeloXDetailPalette {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static final MeloXDetailPalette DarkFallback;
    private static final MeloXDetailPalette LightFallback;
    private final long background;
    private final boolean prefersDarkAppearance;

    public /* synthetic */ MeloXDetailPalette(long j, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, z);
    }

    /* JADX INFO: renamed from: copy-DxMtmZc$default, reason: not valid java name */
    public static /* synthetic */ MeloXDetailPalette m9664copyDxMtmZc$default(MeloXDetailPalette meloXDetailPalette, long j, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            j = meloXDetailPalette.background;
        }
        if ((i & 2) != 0) {
            z = meloXDetailPalette.prefersDarkAppearance;
        }
        return meloXDetailPalette.m9666copyDxMtmZc(j, z);
    }



    /* JADX INFO: renamed from: copy-DxMtmZc, reason: not valid java name */
    public final MeloXDetailPalette m9666copyDxMtmZc(long background, boolean prefersDarkAppearance) {
        return new MeloXDetailPalette(background, prefersDarkAppearance, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MeloXDetailPalette)) {
            return false;
        }
        MeloXDetailPalette meloXDetailPalette = (MeloXDetailPalette) other;
        return Color.m6069equalsimpl0(this.background, meloXDetailPalette.background) && this.prefersDarkAppearance == meloXDetailPalette.prefersDarkAppearance;
    }

    public int hashCode() {
        return (Color.m6075hashCodeimpl(this.background) * 31) + Boolean.hashCode(this.prefersDarkAppearance);
    }

    public String toString() {
        return "MeloXDetailPalette(background=" + Color.m6076toStringimpl(this.background) + ", prefersDarkAppearance=" + this.prefersDarkAppearance + ")";
    }

    private MeloXDetailPalette(long background, boolean prefersDarkAppearance) {
        this.background = background;
        this.prefersDarkAppearance = prefersDarkAppearance;
    }

    /* JADX INFO: renamed from: getBackground-0d7_KjU, reason: not valid java name */
    public final long m9667getBackground0d7_KjU() {
        return this.background;
    }

    public final boolean getPrefersDarkAppearance() {
        return this.prefersDarkAppearance;
    }

    /* JADX INFO: compiled from: MeloXDetailPalette.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/lladlam/melox/ui/library/MeloXDetailPalette$Companion;", "", "<init>", "()V", "LightFallback", "Lcom/lladlam/melox/ui/library/MeloXDetailPalette;", "getLightFallback", "()Lcom/lladlam/melox/ui/library/MeloXDetailPalette;", "DarkFallback", "getDarkFallback", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MeloXDetailPalette getLightFallback() {
            return MeloXDetailPalette.LightFallback;
        }

        public final MeloXDetailPalette getDarkFallback() {
            return MeloXDetailPalette.DarkFallback;
        }
    }

    static {
        DefaultConstructorMarker defaultConstructorMarker = null;
        INSTANCE = new Companion(defaultConstructorMarker);
        LightFallback = new MeloXDetailPalette(ColorKt.Color(4292927712L), false, defaultConstructorMarker);
        DarkFallback = new MeloXDetailPalette(ColorKt.Color(4280887593L), true, defaultConstructorMarker);
    }
}
