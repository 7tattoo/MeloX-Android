package com.lladlam.melox.core.audio;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MusicQuality.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u000eJ2\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\f\u0010\nR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/lladlam/melox/core/audio/SongAudioResource;", "", "bitrate", "", "sampleRate", "size", "", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;)V", "getBitrate", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSampleRate", "getSize", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;)Lcom/lladlam/melox/core/audio/SongAudioResource;", "equals", "", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SongAudioResource {
    public static final int $stable = 0;
    private final Integer bitrate;
    private final Integer sampleRate;
    private final Long size;

    public static /* synthetic */ SongAudioResource copy$default(SongAudioResource songAudioResource, Integer num, Integer num2, Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            num = songAudioResource.bitrate;
        }
        if ((i & 2) != 0) {
            num2 = songAudioResource.sampleRate;
        }
        if ((i & 4) != 0) {
            l = songAudioResource.size;
        }
        return songAudioResource.copy(num, num2, l);
    }


    public final SongAudioResource copy(Integer bitrate, Integer sampleRate, Long size) {
        return new SongAudioResource(bitrate, sampleRate, size);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SongAudioResource)) {
            return false;
        }
        SongAudioResource songAudioResource = (SongAudioResource) other;
        return Intrinsics.areEqual(this.bitrate, songAudioResource.bitrate) && Intrinsics.areEqual(this.sampleRate, songAudioResource.sampleRate) && Intrinsics.areEqual(this.size, songAudioResource.size);
    }

    public int hashCode() {
        return ((((this.bitrate == null ? 0 : this.bitrate.hashCode()) * 31) + (this.sampleRate == null ? 0 : this.sampleRate.hashCode())) * 31) + (this.size != null ? this.size.hashCode() : 0);
    }

    public String toString() {
        return "SongAudioResource(bitrate=" + this.bitrate + ", sampleRate=" + this.sampleRate + ", size=" + this.size + ")";
    }

    public SongAudioResource(Integer bitrate, Integer sampleRate, Long size) {
        this.bitrate = bitrate;
        this.sampleRate = sampleRate;
        this.size = size;
    }

    public final Integer getBitrate() {
        return this.bitrate;
    }

    public final Integer getSampleRate() {
        return this.sampleRate;
    }

    public final Long getSize() {
        return this.size;
    }
}
