package com.lladlam.melox.core.model;

import androidx.autofill.HintConstants;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchSong.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003JG\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020!HÖ\u0081\u0004J\n\u0010\"\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0011\u0010\u0014\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000f¨\u0006#"}, d2 = {"Lcom/lladlam/melox/core/model/SearchSong;", "", TtmlNode.ATTR_ID, "", HintConstants.AUTOFILL_HINT_NAME, "", "artists", "album", "artworkUrl", "durationMs", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getId", "()J", "getName", "()Ljava/lang/String;", "getArtists", "getAlbum", "getArtworkUrl", "getDurationMs", "playbackUrl", "getPlaybackUrl", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SearchSong {
    public static final int $stable = 0;
    private final String album;
    private final String artists;
    private final String artworkUrl;
    private final long durationMs;
    private final long id;
    private final String name;

    public static /* synthetic */ SearchSong copy$default(SearchSong searchSong, long j, String str, String str2, String str3, String str4, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = searchSong.id;
        }
        long j3 = j;
        if ((i & 2) != 0) {
            str = searchSong.name;
        }
        String str5 = str;
        if ((i & 4) != 0) {
            str2 = searchSong.artists;
        }
        String str6 = str2;
        if ((i & 8) != 0) {
            str3 = searchSong.album;
        }
        String str7 = str3;
        if ((i & 16) != 0) {
            str4 = searchSong.artworkUrl;
        }
        return searchSong.copy(j3, str5, str6, str7, str4, (i & 32) != 0 ? searchSong.durationMs : j2);
    }

    public final SearchSong copy(long id, String name, String artists, String album, String artworkUrl, long durationMs) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(artists, "artists");
        Intrinsics.checkNotNullParameter(album, "album");
        return new SearchSong(id, name, artists, album, artworkUrl, durationMs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchSong)) {
            return false;
        }
        SearchSong searchSong = (SearchSong) other;
        return this.id == searchSong.id && Intrinsics.areEqual(this.name, searchSong.name) && Intrinsics.areEqual(this.artists, searchSong.artists) && Intrinsics.areEqual(this.album, searchSong.album) && Intrinsics.areEqual(this.artworkUrl, searchSong.artworkUrl) && this.durationMs == searchSong.durationMs;
    }

    public int hashCode() {
        return (((((((((Long.hashCode(this.id) * 31) + this.name.hashCode()) * 31) + this.artists.hashCode()) * 31) + this.album.hashCode()) * 31) + (this.artworkUrl == null ? 0 : this.artworkUrl.hashCode())) * 31) + Long.hashCode(this.durationMs);
    }

    public String toString() {
        return "SearchSong(id=" + this.id + ", name=" + this.name + ", artists=" + this.artists + ", album=" + this.album + ", artworkUrl=" + this.artworkUrl + ", durationMs=" + this.durationMs + ")";
    }

    public SearchSong(long id, String name, String artists, String album, String artworkUrl, long durationMs) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(artists, "artists");
        Intrinsics.checkNotNullParameter(album, "album");
        this.id = id;
        this.name = name;
        this.artists = artists;
        this.album = album;
        this.artworkUrl = artworkUrl;
        this.durationMs = durationMs;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ SearchSong(long j, String str, String str2, String str3, String str4, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        long j3;
        if ((i & 32) == 0) {
            j3 = j2;
        } else {
            j3 = 0;
        }
        this(j, str, str2, str3, str4, j3);
    }

    public final long getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getArtists() {
        return this.artists;
    }

    public final String getAlbum() {
        return this.album;
    }

    public final String getArtworkUrl() {
        return this.artworkUrl;
    }

    public final long getDurationMs() {
        return this.durationMs;
    }

    public final String getPlaybackUrl() {
        return "https://music.163.com/song/media/outer/url?id=" + this.id;
    }
}
