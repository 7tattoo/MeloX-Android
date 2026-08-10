package com.lladlam.melox.core.library;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NeteaseLibraryModels.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J)\u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0014\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/lladlam/melox/core/library/NeteaseHomeSnapshot;", "", "recommended", "", "Lcom/lladlam/melox/core/library/NeteasePlaylistSummary;", "charts", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "getRecommended", "()Ljava/util/List;", "getCharts", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class NeteaseHomeSnapshot {
    public static final int $stable = 8;
    private final List<NeteasePlaylistSummary> charts;
    private final List<NeteasePlaylistSummary> recommended;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ NeteaseHomeSnapshot copy$default(NeteaseHomeSnapshot neteaseHomeSnapshot, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = neteaseHomeSnapshot.recommended;
        }
        if ((i & 2) != 0) {
            list2 = neteaseHomeSnapshot.charts;
        }
        return neteaseHomeSnapshot.copy(list, list2);
    }

    public final List<NeteasePlaylistSummary> component1() {
        return this.recommended;
    }

    public final List<NeteasePlaylistSummary> component2() {
        return this.charts;
    }

    public final NeteaseHomeSnapshot copy(List<NeteasePlaylistSummary> recommended, List<NeteasePlaylistSummary> charts) {
        Intrinsics.checkNotNullParameter(recommended, "recommended");
        Intrinsics.checkNotNullParameter(charts, "charts");
        return new NeteaseHomeSnapshot(recommended, charts);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NeteaseHomeSnapshot)) {
            return false;
        }
        NeteaseHomeSnapshot neteaseHomeSnapshot = (NeteaseHomeSnapshot) other;
        return Intrinsics.areEqual(this.recommended, neteaseHomeSnapshot.recommended) && Intrinsics.areEqual(this.charts, neteaseHomeSnapshot.charts);
    }

    public int hashCode() {
        return (this.recommended.hashCode() * 31) + this.charts.hashCode();
    }

    public String toString() {
        return "NeteaseHomeSnapshot(recommended=" + this.recommended + ", charts=" + this.charts + ")";
    }

    public NeteaseHomeSnapshot(List<NeteasePlaylistSummary> recommended, List<NeteasePlaylistSummary> charts) {
        Intrinsics.checkNotNullParameter(recommended, "recommended");
        Intrinsics.checkNotNullParameter(charts, "charts");
        this.recommended = recommended;
        this.charts = charts;
    }

    public final List<NeteasePlaylistSummary> getRecommended() {
        return this.recommended;
    }

    public final List<NeteasePlaylistSummary> getCharts() {
        return this.charts;
    }
}
