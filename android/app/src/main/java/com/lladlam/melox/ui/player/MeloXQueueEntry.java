package com.lladlam.melox.ui.player;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MeloXPlayerUi.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J=\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e¨\u0006\u001d"}, d2 = {"Lcom/lladlam/melox/ui/player/MeloXQueueEntry;", "", "index", "", "mediaId", "", "title", "artist", "artworkUrl", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getIndex", "()I", "getMediaId", "()Ljava/lang/String;", "getTitle", "getArtist", "getArtworkUrl", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MeloXQueueEntry {
    public static final int $stable = 0;
    private final String artist;
    private final String artworkUrl;
    private final int index;
    private final String mediaId;
    private final String title;

    public static /* synthetic */ MeloXQueueEntry copy$default(MeloXQueueEntry meloXQueueEntry, int i, String str, String str2, String str3, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = meloXQueueEntry.index;
        }
        if ((i2 & 2) != 0) {
            str = meloXQueueEntry.mediaId;
        }
        if ((i2 & 4) != 0) {
            str2 = meloXQueueEntry.title;
        }
        if ((i2 & 8) != 0) {
            str3 = meloXQueueEntry.artist;
        }
        if ((i2 & 16) != 0) {
            str4 = meloXQueueEntry.artworkUrl;
        }
        String str5 = str4;
        String str6 = str2;
        return meloXQueueEntry.copy(i, str, str6, str3, str5);
    }






    public final MeloXQueueEntry copy(int index, String mediaId, String title, String artist, String artworkUrl) {
        Intrinsics.checkNotNullParameter(mediaId, "mediaId");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(artist, "artist");
        return new MeloXQueueEntry(index, mediaId, title, artist, artworkUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MeloXQueueEntry)) {
            return false;
        }
        MeloXQueueEntry meloXQueueEntry = (MeloXQueueEntry) other;
        return this.index == meloXQueueEntry.index && Intrinsics.areEqual(this.mediaId, meloXQueueEntry.mediaId) && Intrinsics.areEqual(this.title, meloXQueueEntry.title) && Intrinsics.areEqual(this.artist, meloXQueueEntry.artist) && Intrinsics.areEqual(this.artworkUrl, meloXQueueEntry.artworkUrl);
    }

    public int hashCode() {
        return (((((((Integer.hashCode(this.index) * 31) + this.mediaId.hashCode()) * 31) + this.title.hashCode()) * 31) + this.artist.hashCode()) * 31) + (this.artworkUrl == null ? 0 : this.artworkUrl.hashCode());
    }

    public String toString() {
        return "MeloXQueueEntry(index=" + this.index + ", mediaId=" + this.mediaId + ", title=" + this.title + ", artist=" + this.artist + ", artworkUrl=" + this.artworkUrl + ")";
    }

    public MeloXQueueEntry(int index, String mediaId, String title, String artist, String artworkUrl) {
        Intrinsics.checkNotNullParameter(mediaId, "mediaId");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(artist, "artist");
        this.index = index;
        this.mediaId = mediaId;
        this.title = title;
        this.artist = artist;
        this.artworkUrl = artworkUrl;
    }

    public final int getIndex() {
        return this.index;
    }

    public final String getMediaId() {
        return this.mediaId;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getArtist() {
        return this.artist;
    }

    public final String getArtworkUrl() {
        return this.artworkUrl;
    }
}
