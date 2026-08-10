package com.lladlam.melox.core.library;

import com.lladlam.melox.core.model.SearchSong;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NeteaseLibraryModels.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B1\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J9\u0010\u0011\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000b¨\u0006\u0019"}, d2 = {"Lcom/lladlam/melox/core/library/NeteaseLibrarySnapshot;", "", "playlists", "", "Lcom/lladlam/melox/core/library/NeteasePlaylistSummary;", "likedSongs", "Lcom/lladlam/melox/core/model/SearchSong;", "recentSongs", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getPlaylists", "()Ljava/util/List;", "getLikedSongs", "getRecentSongs", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class NeteaseLibrarySnapshot {
    public static final int $stable = 8;
    private final List<SearchSong> likedSongs;
    private final List<NeteasePlaylistSummary> playlists;
    private final List<SearchSong> recentSongs;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ NeteaseLibrarySnapshot copy$default(NeteaseLibrarySnapshot neteaseLibrarySnapshot, List list, List list2, List list3, int i, Object obj) {
        if ((i & 1) != 0) {
            list = neteaseLibrarySnapshot.playlists;
        }
        if ((i & 2) != 0) {
            list2 = neteaseLibrarySnapshot.likedSongs;
        }
        if ((i & 4) != 0) {
            list3 = neteaseLibrarySnapshot.recentSongs;
        }
        return neteaseLibrarySnapshot.copy(list, list2, list3);
    }

    public final List<NeteasePlaylistSummary> component1() {
        return this.playlists;
    }

    public final List<SearchSong> component2() {
        return this.likedSongs;
    }

    public final List<SearchSong> component3() {
        return this.recentSongs;
    }

    public final NeteaseLibrarySnapshot copy(List<NeteasePlaylistSummary> playlists, List<SearchSong> likedSongs, List<SearchSong> recentSongs) {
        Intrinsics.checkNotNullParameter(playlists, "playlists");
        Intrinsics.checkNotNullParameter(likedSongs, "likedSongs");
        Intrinsics.checkNotNullParameter(recentSongs, "recentSongs");
        return new NeteaseLibrarySnapshot(playlists, likedSongs, recentSongs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NeteaseLibrarySnapshot)) {
            return false;
        }
        NeteaseLibrarySnapshot neteaseLibrarySnapshot = (NeteaseLibrarySnapshot) other;
        return Intrinsics.areEqual(this.playlists, neteaseLibrarySnapshot.playlists) && Intrinsics.areEqual(this.likedSongs, neteaseLibrarySnapshot.likedSongs) && Intrinsics.areEqual(this.recentSongs, neteaseLibrarySnapshot.recentSongs);
    }

    public int hashCode() {
        return (((this.playlists.hashCode() * 31) + this.likedSongs.hashCode()) * 31) + this.recentSongs.hashCode();
    }

    public String toString() {
        return "NeteaseLibrarySnapshot(playlists=" + this.playlists + ", likedSongs=" + this.likedSongs + ", recentSongs=" + this.recentSongs + ")";
    }

    public NeteaseLibrarySnapshot(List<NeteasePlaylistSummary> playlists, List<SearchSong> likedSongs, List<SearchSong> recentSongs) {
        Intrinsics.checkNotNullParameter(playlists, "playlists");
        Intrinsics.checkNotNullParameter(likedSongs, "likedSongs");
        Intrinsics.checkNotNullParameter(recentSongs, "recentSongs");
        this.playlists = playlists;
        this.likedSongs = likedSongs;
        this.recentSongs = recentSongs;
    }

    public final List<NeteasePlaylistSummary> getPlaylists() {
        return this.playlists;
    }

    public final List<SearchSong> getLikedSongs() {
        return this.likedSongs;
    }

    public final List<SearchSong> getRecentSongs() {
        return this.recentSongs;
    }
}
