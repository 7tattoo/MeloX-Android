package com.lladlam.melox.ui.player;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MeloXPlayerTransitionKeys.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/lladlam/melox/ui/player/MeloXPlayerContainerKey;", "", "mediaId", "", "<init>", "(Ljava/lang/String;)V", "getMediaId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MeloXPlayerContainerKey {
    public static final int $stable = 0;
    private final String mediaId;

    public static /* synthetic */ MeloXPlayerContainerKey copy$default(MeloXPlayerContainerKey meloXPlayerContainerKey, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = meloXPlayerContainerKey.mediaId;
        }
        return meloXPlayerContainerKey.copy(str);
    }


    public final MeloXPlayerContainerKey copy(String mediaId) {
        Intrinsics.checkNotNullParameter(mediaId, "mediaId");
        return new MeloXPlayerContainerKey(mediaId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof MeloXPlayerContainerKey) && Intrinsics.areEqual(this.mediaId, ((MeloXPlayerContainerKey) other).mediaId);
    }

    public int hashCode() {
        return this.mediaId.hashCode();
    }

    public String toString() {
        return "MeloXPlayerContainerKey(mediaId=" + this.mediaId + ")";
    }

    public MeloXPlayerContainerKey(String mediaId) {
        Intrinsics.checkNotNullParameter(mediaId, "mediaId");
        this.mediaId = mediaId;
    }

    public final String getMediaId() {
        return this.mediaId;
    }
}
