package com.lladlam.melox.core.audio;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NeteaseQualityClient.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\bHÆ\u0003J<\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u0018J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/lladlam/melox/core/audio/NeteasePlaybackSource;", "", "url", "", "bitrate", "", "format", "quality", "Lcom/lladlam/melox/core/audio/MusicQuality;", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/lladlam/melox/core/audio/MusicQuality;)V", "getUrl", "()Ljava/lang/String;", "getBitrate", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getFormat", "getQuality", "()Lcom/lladlam/melox/core/audio/MusicQuality;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/lladlam/melox/core/audio/MusicQuality;)Lcom/lladlam/melox/core/audio/NeteasePlaybackSource;", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class NeteasePlaybackSource {
    public static final int $stable = 0;
    private final Integer bitrate;
    private final String format;
    private final MusicQuality quality;
    private final String url;

    public static /* synthetic */ NeteasePlaybackSource copy$default(NeteasePlaybackSource neteasePlaybackSource, String str, Integer num, String str2, MusicQuality musicQuality, int i, Object obj) {
        if ((i & 1) != 0) {
            str = neteasePlaybackSource.url;
        }
        if ((i & 2) != 0) {
            num = neteasePlaybackSource.bitrate;
        }
        if ((i & 4) != 0) {
            str2 = neteasePlaybackSource.format;
        }
        if ((i & 8) != 0) {
            musicQuality = neteasePlaybackSource.quality;
        }
        return neteasePlaybackSource.copy(str, num, str2, musicQuality);
    }



    public final NeteasePlaybackSource copy(String url, Integer bitrate, String format, MusicQuality quality) {
        Intrinsics.checkNotNullParameter(url, "url");
        return new NeteasePlaybackSource(url, bitrate, format, quality);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NeteasePlaybackSource)) {
            return false;
        }
        NeteasePlaybackSource neteasePlaybackSource = (NeteasePlaybackSource) other;
        return Intrinsics.areEqual(this.url, neteasePlaybackSource.url) && Intrinsics.areEqual(this.bitrate, neteasePlaybackSource.bitrate) && Intrinsics.areEqual(this.format, neteasePlaybackSource.format) && this.quality == neteasePlaybackSource.quality;
    }

    public int hashCode() {
        return (((((this.url.hashCode() * 31) + (this.bitrate == null ? 0 : this.bitrate.hashCode())) * 31) + (this.format == null ? 0 : this.format.hashCode())) * 31) + (this.quality != null ? this.quality.hashCode() : 0);
    }

    public String toString() {
        return "NeteasePlaybackSource(url=" + this.url + ", bitrate=" + this.bitrate + ", format=" + this.format + ", quality=" + this.quality + ")";
    }

    public NeteasePlaybackSource(String url, Integer bitrate, String format, MusicQuality quality) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.url = url;
        this.bitrate = bitrate;
        this.format = format;
        this.quality = quality;
    }

    public final String getUrl() {
        return this.url;
    }

    public final Integer getBitrate() {
        return this.bitrate;
    }

    public final String getFormat() {
        return this.format;
    }

    public final MusicQuality getQuality() {
        return this.quality;
    }
}
