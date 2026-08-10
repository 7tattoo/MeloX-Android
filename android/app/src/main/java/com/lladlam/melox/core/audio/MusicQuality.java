package com.lladlam.melox.core.audio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MusicQuality.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0081\u0002\u0018\u0000 \u001c2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001cB\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00000\u00162\u0006\u0010\u001a\u001a\u00020\u001bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00000\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018j\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u001d"}, d2 = {"Lcom/lladlam/melox/core/audio/MusicQuality;", "", "apiLevel", "", "title", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getApiLevel", "()Ljava/lang/String;", "getTitle", "Standard", "High", "Lossless", "HiResolution", "HighDefinitionSurround", "ImmersiveSurround", "UltraClearMaster", "requiresImmersiveType", "", "getRequiresImmersiveType", "()Z", "playbackFallbacks", "", "getPlaybackFallbacks", "()Ljava/util/List;", "playbackCandidates", "availability", "Lcom/lladlam/melox/core/audio/SongAudioAvailability;", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public enum MusicQuality {
    Standard("standard", "标准"),
    High("exhigh", "高品质"),
    Lossless("lossless", "无损"),
    HiResolution("hires", "Hi-Res"),
    HighDefinitionSurround("jyeffect", "高清环绕声"),
    ImmersiveSurround("sky", "沉浸环绕声"),
    UltraClearMaster("jymaster", "超清母带");

    private final String apiLevel;
    private final String title;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: MusicQuality.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MusicQuality.values().length];
            try {
                iArr[MusicQuality.Standard.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[MusicQuality.High.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[MusicQuality.Lossless.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[MusicQuality.HiResolution.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[MusicQuality.HighDefinitionSurround.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[MusicQuality.ImmersiveSurround.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[MusicQuality.UltraClearMaster.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static EnumEntries<MusicQuality> getEntries() {
        return $ENTRIES;
    }

    MusicQuality(String apiLevel, String title) {
        this.apiLevel = apiLevel;
        this.title = title;
    }

    public final String getApiLevel() {
        return this.apiLevel;
    }

    public final String getTitle() {
        return this.title;
    }

    public final boolean getRequiresImmersiveType() {
        return this == ImmersiveSurround;
    }

    public final List<MusicQuality> getPlaybackFallbacks() {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
                return CollectionsKt.listOf(Standard);
            case 2:
                return CollectionsKt.listOf((Object[]) new MusicQuality[]{High, Standard});
            case 3:
                return CollectionsKt.listOf((Object[]) new MusicQuality[]{Lossless, High, Standard});
            case 4:
                return CollectionsKt.listOf((Object[]) new MusicQuality[]{HiResolution, Lossless, High, Standard});
            case 5:
                return CollectionsKt.listOf((Object[]) new MusicQuality[]{HighDefinitionSurround, Lossless, High, Standard});
            case 6:
                return CollectionsKt.listOf((Object[]) new MusicQuality[]{ImmersiveSurround, HighDefinitionSurround, Lossless, High, Standard});
            case 7:
                return CollectionsKt.listOf((Object[]) new MusicQuality[]{UltraClearMaster, HiResolution, Lossless, High, Standard});
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final List<MusicQuality> playbackCandidates(SongAudioAvailability availability) {
        Intrinsics.checkNotNullParameter(availability, "availability");
        Iterable playbackFallbacks = getPlaybackFallbacks();
        Collection arrayList = new ArrayList();
        for (Object obj : playbackFallbacks) {
            if (!Intrinsics.areEqual((Object) availability.supports(((MusicQuality) obj).apiLevel), (Object) false)) {
                arrayList.add(obj);
            }
        }
        return (List) arrayList;
    }

    /* JADX INFO: compiled from: MusicQuality.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lcom/lladlam/melox/core/audio/MusicQuality$Companion;", "", "<init>", "()V", "fromApiLevel", "Lcom/lladlam/melox/core/audio/MusicQuality;", "level", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MusicQuality fromApiLevel(String level) {
            Object next;
            Iterator<MusicQuality> it = MusicQuality.getEntries().iterator();
            while (it.hasNext()) {
                next = it.next();
                if (Intrinsics.areEqual(((MusicQuality) next).getApiLevel(), level)) {
                    return (MusicQuality) next;
                }
            }
            next = null;
            return (MusicQuality) next;
        }
    }
}
