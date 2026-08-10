package com.lladlam.melox.core.audio;

import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MusicQuality.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0005J\u0017\u0010\u0011\u001a\u0004\u0018\u00010\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\u0012J\u0017\u0010\u0013\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00050\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/lladlam/melox/core/audio/MusicQualityRuntime;", "", "<init>", "()V", "selected", "Lcom/lladlam/melox/core/audio/MusicQuality;", "getSelected", "()Lcom/lladlam/melox/core/audio/MusicQuality;", "setSelected", "(Lcom/lladlam/melox/core/audio/MusicQuality;)V", "actualBySong", "Ljava/util/concurrent/ConcurrentHashMap;", "", "recordActual", "", "songId", "quality", "actualFor", "(Ljava/lang/Long;)Lcom/lladlam/melox/core/audio/MusicQuality;", "clear", "(Ljava/lang/Long;)V", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class MusicQualityRuntime {
    public static final MusicQualityRuntime INSTANCE = new MusicQualityRuntime();
    private static volatile MusicQuality selected = MusicQuality.Standard;
    private static final ConcurrentHashMap<Long, MusicQuality> actualBySong = new ConcurrentHashMap<>();
    public static final int $stable = 8;

    private MusicQualityRuntime() {
    }

    public final MusicQuality getSelected() {
        return selected;
    }

    public final void setSelected(MusicQuality musicQuality) {
        Intrinsics.checkNotNullParameter(musicQuality, "<set-?>");
        selected = musicQuality;
    }

    public final void recordActual(long songId, MusicQuality quality) {
        Intrinsics.checkNotNullParameter(quality, "quality");
        actualBySong.put(Long.valueOf(songId), quality);
    }

    public final MusicQuality actualFor(Long songId) {
        if (songId != null) {
            return actualBySong.get(songId);
        }
        return null;
    }

    public static /* synthetic */ void clear$default(MusicQualityRuntime musicQualityRuntime, Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            l = null;
        }
        musicQualityRuntime.clear(l);
    }

    public final void clear(Long songId) {
        ConcurrentHashMap<Long, MusicQuality> concurrentHashMap = actualBySong;
        if (songId == null) {
            concurrentHashMap.clear();
        } else {
            concurrentHashMap.remove(songId);
        }
    }
}
