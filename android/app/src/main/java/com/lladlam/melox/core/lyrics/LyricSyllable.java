package com.lladlam.melox.core.lyrics;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricModels.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/lladlam/melox/core/lyrics/LyricSyllable;", "", "text", "", "startTimeMs", "", "endTimeMs", "<init>", "(Ljava/lang/String;JJ)V", "getText", "()Ljava/lang/String;", "getStartTimeMs", "()J", "getEndTimeMs", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class LyricSyllable {
    public static final int $stable = 0;
    private final long endTimeMs;
    private final long startTimeMs;
    private final String text;

    public static /* synthetic */ LyricSyllable copy$default(LyricSyllable lyricSyllable, String str, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = lyricSyllable.text;
        }
        if ((i & 2) != 0) {
            j = lyricSyllable.startTimeMs;
        }
        if ((i & 4) != 0) {
            j2 = lyricSyllable.endTimeMs;
        }
        return lyricSyllable.copy(str, j, j2);
    }


    public final LyricSyllable copy(String text, long startTimeMs, long endTimeMs) {
        Intrinsics.checkNotNullParameter(text, "text");
        return new LyricSyllable(text, startTimeMs, endTimeMs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LyricSyllable)) {
            return false;
        }
        LyricSyllable lyricSyllable = (LyricSyllable) other;
        return Intrinsics.areEqual(this.text, lyricSyllable.text) && this.startTimeMs == lyricSyllable.startTimeMs && this.endTimeMs == lyricSyllable.endTimeMs;
    }

    public int hashCode() {
        return (((this.text.hashCode() * 31) + Long.hashCode(this.startTimeMs)) * 31) + Long.hashCode(this.endTimeMs);
    }

    public String toString() {
        return "LyricSyllable(text=" + this.text + ", startTimeMs=" + this.startTimeMs + ", endTimeMs=" + this.endTimeMs + ")";
    }

    public LyricSyllable(String text, long startTimeMs, long endTimeMs) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.text = text;
        this.startTimeMs = startTimeMs;
        this.endTimeMs = endTimeMs;
    }

    public final String getText() {
        return this.text;
    }

    public final long getStartTimeMs() {
        return this.startTimeMs;
    }

    public final long getEndTimeMs() {
        return this.endTimeMs;
    }
}
