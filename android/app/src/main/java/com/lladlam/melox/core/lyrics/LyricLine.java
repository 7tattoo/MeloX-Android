package com.lladlam.melox.core.lyrics;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricModels.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0011J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0006HÆ\u0003JV\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010 J\u0014\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010$\u001a\u00020%HÖ\u0081\u0004J\n\u0010&\u001a\u00020\u0006HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014¨\u0006'"}, d2 = {"Lcom/lladlam/melox/core/lyrics/LyricLine;", "", "timeMs", "", "durationMs", "text", "", "syllables", "", "Lcom/lladlam/melox/core/lyrics/LyricSyllable;", "translation", "romanization", "<init>", "(JLjava/lang/Long;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "getTimeMs", "()J", "getDurationMs", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getText", "()Ljava/lang/String;", "getSyllables", "()Ljava/util/List;", "getTranslation", "getRomanization", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(JLjava/lang/Long;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)Lcom/lladlam/melox/core/lyrics/LyricLine;", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class LyricLine {
    public static final int $stable = 8;
    private final Long durationMs;
    private final String romanization;
    private final List<LyricSyllable> syllables;
    private final String text;
    private final long timeMs;
    private final String translation;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LyricLine copy$default(LyricLine lyricLine, long j, Long l, String str, List list, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            j = lyricLine.timeMs;
        }
        long j2 = j;
        if ((i & 2) != 0) {
            l = lyricLine.durationMs;
        }
        Long l2 = l;
        if ((i & 4) != 0) {
            str = lyricLine.text;
        }
        String str4 = str;
        if ((i & 8) != 0) {
            list = lyricLine.syllables;
        }
        List list2 = list;
        if ((i & 16) != 0) {
            str2 = lyricLine.translation;
        }
        String str5 = str2;
        if ((i & 32) != 0) {
            str3 = lyricLine.romanization;
        }
        return lyricLine.copy(j2, l2, str4, list2, str5, str3);
    }




    public final List<LyricSyllable> component4() {
        return this.syllables;
    }



    public final LyricLine copy(long timeMs, Long durationMs, String text, List<LyricSyllable> syllables, String translation, String romanization) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(syllables, "syllables");
        return new LyricLine(timeMs, durationMs, text, syllables, translation, romanization);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LyricLine)) {
            return false;
        }
        LyricLine lyricLine = (LyricLine) other;
        return this.timeMs == lyricLine.timeMs && Intrinsics.areEqual(this.durationMs, lyricLine.durationMs) && Intrinsics.areEqual(this.text, lyricLine.text) && Intrinsics.areEqual(this.syllables, lyricLine.syllables) && Intrinsics.areEqual(this.translation, lyricLine.translation) && Intrinsics.areEqual(this.romanization, lyricLine.romanization);
    }

    public int hashCode() {
        return (((((((((Long.hashCode(this.timeMs) * 31) + (this.durationMs == null ? 0 : this.durationMs.hashCode())) * 31) + this.text.hashCode()) * 31) + this.syllables.hashCode()) * 31) + (this.translation == null ? 0 : this.translation.hashCode())) * 31) + (this.romanization != null ? this.romanization.hashCode() : 0);
    }

    public String toString() {
        return "LyricLine(timeMs=" + this.timeMs + ", durationMs=" + this.durationMs + ", text=" + this.text + ", syllables=" + this.syllables + ", translation=" + this.translation + ", romanization=" + this.romanization + ")";
    }

    public LyricLine(long timeMs, Long durationMs, String text, List<LyricSyllable> syllables, String translation, String romanization) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(syllables, "syllables");
        this.timeMs = timeMs;
        this.durationMs = durationMs;
        this.text = text;
        this.syllables = syllables;
        this.translation = translation;
        this.romanization = romanization;
    }

    public /* synthetic */ LyricLine(long j, Long l, String str, List list, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, (i & 2) != 0 ? null : l, str, (i & 8) != 0 ? CollectionsKt.emptyList() : list, (i & 16) != 0 ? null : str2, (i & 32) != 0 ? null : str3);
    }

    public final long getTimeMs() {
        return this.timeMs;
    }

    public final Long getDurationMs() {
        return this.durationMs;
    }

    public final String getText() {
        return this.text;
    }

    public final List<LyricSyllable> getSyllables() {
        return this.syllables;
    }

    public final String getTranslation() {
        return this.translation;
    }

    public final String getRomanization() {
        return this.romanization;
    }
}
