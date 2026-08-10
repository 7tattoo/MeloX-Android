package com.lladlam.melox.core.library;

import androidx.autofill.HintConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NeteaseLibraryModels.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\bHÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0005HÆ\u0003JS\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010#\u001a\u00020\bHÖ\u0081\u0004J\n\u0010$\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011¨\u0006%"}, d2 = {"Lcom/lladlam/melox/core/library/NeteasePlaylistSummary;", "", TtmlNode.ATTR_ID, "", HintConstants.AUTOFILL_HINT_NAME, "", "coverUrl", "trackCount", "", "creatorName", "playCount", "description", "<init>", "(JLjava/lang/String;Ljava/lang/String;ILjava/lang/String;JLjava/lang/String;)V", "getId", "()J", "getName", "()Ljava/lang/String;", "getCoverUrl", "getTrackCount", "()I", "getCreatorName", "getPlayCount", "getDescription", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class NeteasePlaylistSummary {
    public static final int $stable = 0;
    private final String coverUrl;
    private final String creatorName;
    private final String description;
    private final long id;
    private final String name;
    private final long playCount;
    private final int trackCount;

    public static /* synthetic */ NeteasePlaylistSummary copy$default(NeteasePlaylistSummary neteasePlaylistSummary, long j, String str, String str2, int i, String str3, long j2, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j = neteasePlaylistSummary.id;
        }
        long j3 = j;
        if ((i2 & 2) != 0) {
            str = neteasePlaylistSummary.name;
        }
        String str5 = str;
        if ((i2 & 4) != 0) {
            str2 = neteasePlaylistSummary.coverUrl;
        }
        String str6 = str2;
        if ((i2 & 8) != 0) {
            i = neteasePlaylistSummary.trackCount;
        }
        return neteasePlaylistSummary.copy(j3, str5, str6, i, (i2 & 16) != 0 ? neteasePlaylistSummary.creatorName : str3, (i2 & 32) != 0 ? neteasePlaylistSummary.playCount : j2, (i2 & 64) != 0 ? neteasePlaylistSummary.description : str4);
    }






    public final NeteasePlaylistSummary copy(long id, String name, String coverUrl, int trackCount, String creatorName, long playCount, String description) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(creatorName, "creatorName");
        return new NeteasePlaylistSummary(id, name, coverUrl, trackCount, creatorName, playCount, description);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NeteasePlaylistSummary)) {
            return false;
        }
        NeteasePlaylistSummary neteasePlaylistSummary = (NeteasePlaylistSummary) other;
        return this.id == neteasePlaylistSummary.id && Intrinsics.areEqual(this.name, neteasePlaylistSummary.name) && Intrinsics.areEqual(this.coverUrl, neteasePlaylistSummary.coverUrl) && this.trackCount == neteasePlaylistSummary.trackCount && Intrinsics.areEqual(this.creatorName, neteasePlaylistSummary.creatorName) && this.playCount == neteasePlaylistSummary.playCount && Intrinsics.areEqual(this.description, neteasePlaylistSummary.description);
    }

    public int hashCode() {
        return (((((((((((Long.hashCode(this.id) * 31) + this.name.hashCode()) * 31) + (this.coverUrl == null ? 0 : this.coverUrl.hashCode())) * 31) + Integer.hashCode(this.trackCount)) * 31) + this.creatorName.hashCode()) * 31) + Long.hashCode(this.playCount)) * 31) + (this.description != null ? this.description.hashCode() : 0);
    }

    public String toString() {
        return "NeteasePlaylistSummary(id=" + this.id + ", name=" + this.name + ", coverUrl=" + this.coverUrl + ", trackCount=" + this.trackCount + ", creatorName=" + this.creatorName + ", playCount=" + this.playCount + ", description=" + this.description + ")";
    }

    public NeteasePlaylistSummary(long id, String name, String coverUrl, int trackCount, String creatorName, long playCount, String description) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(creatorName, "creatorName");
        this.id = id;
        this.name = name;
        this.coverUrl = coverUrl;
        this.trackCount = trackCount;
        this.creatorName = creatorName;
        this.playCount = playCount;
        this.description = description;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ NeteasePlaylistSummary(long j, String str, String str2, int i, String str3, long j2, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        long j3;
        String str5;
        if ((i2 & 32) == 0) {
            j3 = j2;
        } else {
            j3 = 0;
        }
        if ((i2 & 64) == 0) {
            str5 = str4;
        } else {
            str5 = null;
        }
        this(j, str, str2, i, str3, j3, str5);
    }

    public final long getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getCoverUrl() {
        return this.coverUrl;
    }

    public final int getTrackCount() {
        return this.trackCount;
    }

    public final String getCreatorName() {
        return this.creatorName;
    }

    public final long getPlayCount() {
        return this.playCount;
    }

    public final String getDescription() {
        return this.description;
    }
}
