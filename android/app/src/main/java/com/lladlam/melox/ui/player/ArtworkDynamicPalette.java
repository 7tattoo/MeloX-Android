package com.lladlam.melox.ui.player;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ArtworkDynamicPalette.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0081\b\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0010\u0010\u000e\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u000f\u0010\u000bJ*\u0010\u0010\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001a\u00020\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u001b"}, d2 = {"Lcom/lladlam/melox/ui/player/ArtworkDynamicPalette;", "", "cells", "", "Landroidx/compose/ui/graphics/Color;", "average", "<init>", "(Ljava/util/List;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getCells", "()Ljava/util/List;", "getAverage-0d7_KjU", "()J", "J", "component1", "component2", "component2-0d7_KjU", "copy", "copy-4WTKRHQ", "(Ljava/util/List;J)Lcom/lladlam/melox/ui/player/ArtworkDynamicPalette;", "equals", "", "other", "hashCode", "", "toString", "", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ArtworkDynamicPalette {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static final ArtworkDynamicPalette Fallback;
    private final long average;
    private final List<Color> cells;

    public /* synthetic */ ArtworkDynamicPalette(List list, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: copy-4WTKRHQ$default, reason: not valid java name */
    public static /* synthetic */ ArtworkDynamicPalette m9668copy4WTKRHQ$default(ArtworkDynamicPalette artworkDynamicPalette, List list, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            list = artworkDynamicPalette.cells;
        }
        if ((i & 2) != 0) {
            j = artworkDynamicPalette.average;
        }
        return artworkDynamicPalette.m9670copy4WTKRHQ(list, j);
    }

    public final List<Color> component1() {
        return this.cells;
    }


    /* JADX INFO: renamed from: copy-4WTKRHQ, reason: not valid java name */
    public final ArtworkDynamicPalette m9670copy4WTKRHQ(List<Color> cells, long average) {
        Intrinsics.checkNotNullParameter(cells, "cells");
        return new ArtworkDynamicPalette(cells, average, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ArtworkDynamicPalette)) {
            return false;
        }
        ArtworkDynamicPalette artworkDynamicPalette = (ArtworkDynamicPalette) other;
        return Intrinsics.areEqual(this.cells, artworkDynamicPalette.cells) && Color.m6069equalsimpl0(this.average, artworkDynamicPalette.average);
    }

    public int hashCode() {
        return (this.cells.hashCode() * 31) + Color.m6075hashCodeimpl(this.average);
    }

    public String toString() {
        return "ArtworkDynamicPalette(cells=" + this.cells + ", average=" + Color.m6076toStringimpl(this.average) + ")";
    }

    private ArtworkDynamicPalette(List<Color> cells, long average) {
        Intrinsics.checkNotNullParameter(cells, "cells");
        this.cells = cells;
        this.average = average;
    }

    public final List<Color> getCells() {
        return this.cells;
    }

    /* JADX INFO: renamed from: getAverage-0d7_KjU, reason: not valid java name */
    public final long m9671getAverage0d7_KjU() {
        return this.average;
    }

    /* JADX INFO: compiled from: ArtworkDynamicPalette.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/lladlam/melox/ui/player/ArtworkDynamicPalette$Companion;", "", "<init>", "()V", "Fallback", "Lcom/lladlam/melox/ui/player/ArtworkDynamicPalette;", "getFallback", "()Lcom/lladlam/melox/ui/player/ArtworkDynamicPalette;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ArtworkDynamicPalette getFallback() {
            return ArtworkDynamicPalette.Fallback;
        }
    }

    static {
        DefaultConstructorMarker defaultConstructorMarker = null;
        INSTANCE = new Companion(defaultConstructorMarker);
        ArrayList arrayList = new ArrayList(9);
        for (int i = 0; i < 9; i++) {
            arrayList.add(Color.m6058boximpl(ColorKt.Color(4284173125L)));
        }
        Fallback = new ArtworkDynamicPalette(arrayList, ColorKt.Color(4284173125L), defaultConstructorMarker);
    }
}
