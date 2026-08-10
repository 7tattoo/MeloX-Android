package com.lladlam.melox.core.lyrics;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricModels.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\nHÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/lladlam/melox/core/lyrics/LyricsDocument;", "", "lines", "", "Lcom/lladlam/melox/core/lyrics/LyricLine;", "<init>", "(Ljava/util/List;)V", "getLines", "()Ljava/util/List;", "highlightedIndex", "", "positionMs", "", "(J)Ljava/lang/Integer;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class LyricsDocument {
    public static final int $stable = 8;
    private final List<LyricLine> lines;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LyricsDocument copy$default(LyricsDocument lyricsDocument, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = lyricsDocument.lines;
        }
        return lyricsDocument.copy(list);
    }

    public final List<LyricLine> component1() {
        return this.lines;
    }

    public final LyricsDocument copy(List<LyricLine> lines) {
        Intrinsics.checkNotNullParameter(lines, "lines");
        return new LyricsDocument(lines);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof LyricsDocument) && Intrinsics.areEqual(this.lines, ((LyricsDocument) other).lines);
    }

    public int hashCode() {
        return this.lines.hashCode();
    }

    public String toString() {
        return "LyricsDocument(lines=" + this.lines + ")";
    }

    public LyricsDocument(List<LyricLine> lines) {
        Intrinsics.checkNotNullParameter(lines, "lines");
        this.lines = lines;
    }

    public final List<LyricLine> getLines() {
        return this.lines;
    }

    public final Integer highlightedIndex(long positionMs) {
        if (this.lines.isEmpty()) {
            return null;
        }
        int low = 0;
        int high = this.lines.size();
        while (true) {
            if (low >= high) {
                break;
            }
            int mid = (low + high) >>> 1;
            if (this.lines.get(mid).getTimeMs() <= positionMs) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        Integer numValueOf = Integer.valueOf(low - 1);
        if (numValueOf.intValue() >= 0) {
            return numValueOf;
        }
        return null;
    }
}
