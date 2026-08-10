package com.lladlam.melox.core.audio;

import androidx.core.app.FrameMetricsAggregator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MusicQuality.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u0000 ,2\u00020\u0001:\u0001,Bq\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u0019\u001a\u0004\u0018\u00010\f2\u0006\u0010\u001a\u001a\u00020\u001b¢\u0006\u0002\u0010\u001cJ\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010%\u001a\u00020\fHÆ\u0003Js\u0010&\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0014\u0010'\u001a\u00020\f2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010)\u001a\u00020*HÖ\u0081\u0004J\n\u0010+\u001a\u00020\u001bHÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0018¨\u0006-"}, d2 = {"Lcom/lladlam/melox/core/audio/SongAudioAvailability;", "", "standard", "Lcom/lladlam/melox/core/audio/SongAudioResource;", "medium", "high", "lossless", "hiResolution", "highDefinitionSurround", "immersiveSurround", "ultraClearMaster", "isKnown", "", "<init>", "(Lcom/lladlam/melox/core/audio/SongAudioResource;Lcom/lladlam/melox/core/audio/SongAudioResource;Lcom/lladlam/melox/core/audio/SongAudioResource;Lcom/lladlam/melox/core/audio/SongAudioResource;Lcom/lladlam/melox/core/audio/SongAudioResource;Lcom/lladlam/melox/core/audio/SongAudioResource;Lcom/lladlam/melox/core/audio/SongAudioResource;Lcom/lladlam/melox/core/audio/SongAudioResource;Z)V", "getStandard", "()Lcom/lladlam/melox/core/audio/SongAudioResource;", "getMedium", "getHigh", "getLossless", "getHiResolution", "getHighDefinitionSurround", "getImmersiveSurround", "getUltraClearMaster", "()Z", "supports", "apiLevel", "", "(Ljava/lang/String;)Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SongAudioAvailability {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final SongAudioAvailability Unknown = new SongAudioAvailability(null, null, null, null, null, null, null, null, false, FrameMetricsAggregator.EVERY_DURATION, null);
    private final SongAudioResource hiResolution;
    private final SongAudioResource high;
    private final SongAudioResource highDefinitionSurround;
    private final SongAudioResource immersiveSurround;
    private final boolean isKnown;
    private final SongAudioResource lossless;
    private final SongAudioResource medium;
    private final SongAudioResource standard;
    private final SongAudioResource ultraClearMaster;

    public SongAudioAvailability() {
        this(null, null, null, null, null, null, null, null, false, FrameMetricsAggregator.EVERY_DURATION, null);
    }

    public static /* synthetic */ SongAudioAvailability copy$default(SongAudioAvailability songAudioAvailability, SongAudioResource songAudioResource, SongAudioResource songAudioResource2, SongAudioResource songAudioResource3, SongAudioResource songAudioResource4, SongAudioResource songAudioResource5, SongAudioResource songAudioResource6, SongAudioResource songAudioResource7, SongAudioResource songAudioResource8, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            songAudioResource = songAudioAvailability.standard;
        }
        if ((i & 2) != 0) {
            songAudioResource2 = songAudioAvailability.medium;
        }
        if ((i & 4) != 0) {
            songAudioResource3 = songAudioAvailability.high;
        }
        if ((i & 8) != 0) {
            songAudioResource4 = songAudioAvailability.lossless;
        }
        if ((i & 16) != 0) {
            songAudioResource5 = songAudioAvailability.hiResolution;
        }
        if ((i & 32) != 0) {
            songAudioResource6 = songAudioAvailability.highDefinitionSurround;
        }
        if ((i & 64) != 0) {
            songAudioResource7 = songAudioAvailability.immersiveSurround;
        }
        if ((i & 128) != 0) {
            songAudioResource8 = songAudioAvailability.ultraClearMaster;
        }
        if ((i & 256) != 0) {
            z = songAudioAvailability.isKnown;
        }
        SongAudioResource songAudioResource9 = songAudioResource8;
        boolean z2 = z;
        SongAudioResource songAudioResource10 = songAudioResource6;
        SongAudioResource songAudioResource11 = songAudioResource7;
        SongAudioResource songAudioResource12 = songAudioResource5;
        SongAudioResource songAudioResource13 = songAudioResource3;
        return songAudioAvailability.copy(songAudioResource, songAudioResource2, songAudioResource13, songAudioResource4, songAudioResource12, songAudioResource10, songAudioResource11, songAudioResource9, z2);
    }










    public final SongAudioAvailability copy(SongAudioResource standard, SongAudioResource medium, SongAudioResource high, SongAudioResource lossless, SongAudioResource hiResolution, SongAudioResource highDefinitionSurround, SongAudioResource immersiveSurround, SongAudioResource ultraClearMaster, boolean isKnown) {
        return new SongAudioAvailability(standard, medium, high, lossless, hiResolution, highDefinitionSurround, immersiveSurround, ultraClearMaster, isKnown);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SongAudioAvailability)) {
            return false;
        }
        SongAudioAvailability songAudioAvailability = (SongAudioAvailability) other;
        return Intrinsics.areEqual(this.standard, songAudioAvailability.standard) && Intrinsics.areEqual(this.medium, songAudioAvailability.medium) && Intrinsics.areEqual(this.high, songAudioAvailability.high) && Intrinsics.areEqual(this.lossless, songAudioAvailability.lossless) && Intrinsics.areEqual(this.hiResolution, songAudioAvailability.hiResolution) && Intrinsics.areEqual(this.highDefinitionSurround, songAudioAvailability.highDefinitionSurround) && Intrinsics.areEqual(this.immersiveSurround, songAudioAvailability.immersiveSurround) && Intrinsics.areEqual(this.ultraClearMaster, songAudioAvailability.ultraClearMaster) && this.isKnown == songAudioAvailability.isKnown;
    }

    public int hashCode() {
        return ((((((((((((((((this.standard == null ? 0 : this.standard.hashCode()) * 31) + (this.medium == null ? 0 : this.medium.hashCode())) * 31) + (this.high == null ? 0 : this.high.hashCode())) * 31) + (this.lossless == null ? 0 : this.lossless.hashCode())) * 31) + (this.hiResolution == null ? 0 : this.hiResolution.hashCode())) * 31) + (this.highDefinitionSurround == null ? 0 : this.highDefinitionSurround.hashCode())) * 31) + (this.immersiveSurround == null ? 0 : this.immersiveSurround.hashCode())) * 31) + (this.ultraClearMaster != null ? this.ultraClearMaster.hashCode() : 0)) * 31) + Boolean.hashCode(this.isKnown);
    }

    public String toString() {
        return "SongAudioAvailability(standard=" + this.standard + ", medium=" + this.medium + ", high=" + this.high + ", lossless=" + this.lossless + ", hiResolution=" + this.hiResolution + ", highDefinitionSurround=" + this.highDefinitionSurround + ", immersiveSurround=" + this.immersiveSurround + ", ultraClearMaster=" + this.ultraClearMaster + ", isKnown=" + this.isKnown + ")";
    }

    public SongAudioAvailability(SongAudioResource standard, SongAudioResource medium, SongAudioResource high, SongAudioResource lossless, SongAudioResource hiResolution, SongAudioResource highDefinitionSurround, SongAudioResource immersiveSurround, SongAudioResource ultraClearMaster, boolean isKnown) {
        this.standard = standard;
        this.medium = medium;
        this.high = high;
        this.lossless = lossless;
        this.hiResolution = hiResolution;
        this.highDefinitionSurround = highDefinitionSurround;
        this.immersiveSurround = immersiveSurround;
        this.ultraClearMaster = ultraClearMaster;
        this.isKnown = isKnown;
    }

    public /* synthetic */ SongAudioAvailability(SongAudioResource songAudioResource, SongAudioResource songAudioResource2, SongAudioResource songAudioResource3, SongAudioResource songAudioResource4, SongAudioResource songAudioResource5, SongAudioResource songAudioResource6, SongAudioResource songAudioResource7, SongAudioResource songAudioResource8, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : songAudioResource, (i & 2) != 0 ? null : songAudioResource2, (i & 4) != 0 ? null : songAudioResource3, (i & 8) != 0 ? null : songAudioResource4, (i & 16) != 0 ? null : songAudioResource5, (i & 32) != 0 ? null : songAudioResource6, (i & 64) != 0 ? null : songAudioResource7, (i & 128) != 0 ? null : songAudioResource8, (i & 256) != 0 ? false : z);
    }

    public final SongAudioResource getStandard() {
        return this.standard;
    }

    public final SongAudioResource getMedium() {
        return this.medium;
    }

    public final SongAudioResource getHigh() {
        return this.high;
    }

    public final SongAudioResource getLossless() {
        return this.lossless;
    }

    public final SongAudioResource getHiResolution() {
        return this.hiResolution;
    }

    public final SongAudioResource getHighDefinitionSurround() {
        return this.highDefinitionSurround;
    }

    public final SongAudioResource getImmersiveSurround() {
        return this.immersiveSurround;
    }

    public final SongAudioResource getUltraClearMaster() {
        return this.ultraClearMaster;
    }

    public final boolean isKnown() {
        return this.isKnown;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final Boolean supports(String apiLevel) {
        Intrinsics.checkNotNullParameter(apiLevel, "apiLevel");
        if (!this.isKnown) {
            return null;
        }
        switch (apiLevel.hashCode()) {
            case -1919475908:
                if (apiLevel.equals("lossless")) {
                    return Boolean.valueOf(this.lossless != null);
                }
                break;
            case -1289398059:
                if (apiLevel.equals("exhigh")) {
                    return Boolean.valueOf(this.high != null);
                }
                break;
            case -1238020192:
                if (apiLevel.equals("jyeffect")) {
                    return Boolean.valueOf(this.highDefinitionSurround != null);
                }
                break;
            case -1013202831:
                if (apiLevel.equals("jymaster")) {
                    return Boolean.valueOf(this.ultraClearMaster != null);
                }
                break;
            case 113953:
                if (apiLevel.equals("sky")) {
                    return Boolean.valueOf(this.immersiveSurround != null);
                }
                break;
            case 99287039:
                if (apiLevel.equals("hires")) {
                    return Boolean.valueOf(this.hiResolution != null);
                }
                break;
            case 1312628413:
                if (apiLevel.equals("standard")) {
                    return Boolean.valueOf(this.standard != null);
                }
                break;
        }
        return false;
    }

    /* JADX INFO: compiled from: MusicQuality.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/lladlam/melox/core/audio/SongAudioAvailability$Companion;", "", "<init>", "()V", "Unknown", "Lcom/lladlam/melox/core/audio/SongAudioAvailability;", "getUnknown", "()Lcom/lladlam/melox/core/audio/SongAudioAvailability;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SongAudioAvailability getUnknown() {
            return SongAudioAvailability.Unknown;
        }
    }
}
