package com.lladlam.melox.core.account;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NeteaseAccountModels.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003JA\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e¨\u0006\u001e"}, d2 = {"Lcom/lladlam/melox/core/account/NeteaseAccountProfile;", "", "userId", "", "nickname", "", "avatarUrl", "backgroundUrl", "signature", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getUserId", "()J", "getNickname", "()Ljava/lang/String;", "getAvatarUrl", "getBackgroundUrl", "getSignature", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class NeteaseAccountProfile {
    public static final int $stable = 0;
    private final String avatarUrl;
    private final String backgroundUrl;
    private final String nickname;
    private final String signature;
    private final long userId;

    public static /* synthetic */ NeteaseAccountProfile copy$default(NeteaseAccountProfile neteaseAccountProfile, long j, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            j = neteaseAccountProfile.userId;
        }
        long j2 = j;
        if ((i & 2) != 0) {
            str = neteaseAccountProfile.nickname;
        }
        String str5 = str;
        if ((i & 4) != 0) {
            str2 = neteaseAccountProfile.avatarUrl;
        }
        String str6 = str2;
        if ((i & 8) != 0) {
            str3 = neteaseAccountProfile.backgroundUrl;
        }
        String str7 = str3;
        if ((i & 16) != 0) {
            str4 = neteaseAccountProfile.signature;
        }
        return neteaseAccountProfile.copy(j2, str5, str6, str7, str4);
    }




    public final NeteaseAccountProfile copy(long userId, String nickname, String avatarUrl, String backgroundUrl, String signature) {
        Intrinsics.checkNotNullParameter(nickname, "nickname");
        return new NeteaseAccountProfile(userId, nickname, avatarUrl, backgroundUrl, signature);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NeteaseAccountProfile)) {
            return false;
        }
        NeteaseAccountProfile neteaseAccountProfile = (NeteaseAccountProfile) other;
        return this.userId == neteaseAccountProfile.userId && Intrinsics.areEqual(this.nickname, neteaseAccountProfile.nickname) && Intrinsics.areEqual(this.avatarUrl, neteaseAccountProfile.avatarUrl) && Intrinsics.areEqual(this.backgroundUrl, neteaseAccountProfile.backgroundUrl) && Intrinsics.areEqual(this.signature, neteaseAccountProfile.signature);
    }

    public int hashCode() {
        return (((((((Long.hashCode(this.userId) * 31) + this.nickname.hashCode()) * 31) + (this.avatarUrl == null ? 0 : this.avatarUrl.hashCode())) * 31) + (this.backgroundUrl == null ? 0 : this.backgroundUrl.hashCode())) * 31) + (this.signature != null ? this.signature.hashCode() : 0);
    }

    public String toString() {
        return "NeteaseAccountProfile(userId=" + this.userId + ", nickname=" + this.nickname + ", avatarUrl=" + this.avatarUrl + ", backgroundUrl=" + this.backgroundUrl + ", signature=" + this.signature + ")";
    }

    public NeteaseAccountProfile(long userId, String nickname, String avatarUrl, String backgroundUrl, String signature) {
        Intrinsics.checkNotNullParameter(nickname, "nickname");
        this.userId = userId;
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
        this.backgroundUrl = backgroundUrl;
        this.signature = signature;
    }

    public final long getUserId() {
        return this.userId;
    }

    public final String getNickname() {
        return this.nickname;
    }

    public final String getAvatarUrl() {
        return this.avatarUrl;
    }

    public final String getBackgroundUrl() {
        return this.backgroundUrl;
    }

    public final String getSignature() {
        return this.signature;
    }
}
