package com.lladlam.melox.core.library;

import com.lladlam.melox.core.model.SearchSong;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NeteaseLibraryModels.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/lladlam/melox/core/library/NeteasePlaylistDetail;", "", "summary", "Lcom/lladlam/melox/core/library/NeteasePlaylistSummary;", "songs", "", "Lcom/lladlam/melox/core/model/SearchSong;", "<init>", "(Lcom/lladlam/melox/core/library/NeteasePlaylistSummary;Ljava/util/List;)V", "getSummary", "()Lcom/lladlam/melox/core/library/NeteasePlaylistSummary;", "getSongs", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class NeteasePlaylistDetail {
    public static final int $stable = 8;
    private final List<SearchSong> songs;
    private final NeteasePlaylistSummary summary;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ NeteasePlaylistDetail copy$default(NeteasePlaylistDetail neteasePlaylistDetail, NeteasePlaylistSummary neteasePlaylistSummary, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            neteasePlaylistSummary = neteasePlaylistDetail.summary;
        }
        if ((i & 2) != 0) {
            list = neteasePlaylistDetail.songs;
        }
        return neteasePlaylistDetail.copy(neteasePlaylistSummary, list);
    }


    public final List<SearchSong> component2() {
        return this.songs;
    }

    public final NeteasePlaylistDetail copy(NeteasePlaylistSummary summary, List<SearchSong> songs) {
        Intrinsics.checkNotNullParameter(summary, "summary");
        Intrinsics.checkNotNullParameter(songs, "songs");
        return new NeteasePlaylistDetail(summary, songs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NeteasePlaylistDetail)) {
            return false;
        }
        NeteasePlaylistDetail neteasePlaylistDetail = (NeteasePlaylistDetail) other;
        return Intrinsics.areEqual(this.summary, neteasePlaylistDetail.summary) && Intrinsics.areEqual(this.songs, neteasePlaylistDetail.songs);
    }

    public int hashCode() {
        return (this.summary.hashCode() * 31) + this.songs.hashCode();
    }

    public String toString() {
        return "NeteasePlaylistDetail(summary=" + this.summary + ", songs=" + this.songs + ")";
    }

    public NeteasePlaylistDetail(NeteasePlaylistSummary summary, List<SearchSong> songs) {
        Intrinsics.checkNotNullParameter(summary, "summary");
        Intrinsics.checkNotNullParameter(songs, "songs");
        this.summary = summary;
        this.songs = songs;
    }

    public final NeteasePlaylistSummary getSummary() {
        return this.summary;
    }

    public final List<SearchSong> getSongs() {
        return this.songs;
    }
}
