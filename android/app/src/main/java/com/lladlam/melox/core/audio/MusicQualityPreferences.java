package com.lladlam.melox.core.audio;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MusicQuality.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/lladlam/melox/core/audio/MusicQualityPreferences;", "", "<init>", "()V", "PREFERENCES_NAME", "", "KEY_QUALITY", "read", "Lcom/lladlam/melox/core/audio/MusicQuality;", "context", "Landroid/content/Context;", "write", "", "quality", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class MusicQualityPreferences {
    public static final int $stable = 0;
    public static final MusicQualityPreferences INSTANCE = new MusicQualityPreferences();
    private static final String KEY_QUALITY = "music_quality";
    private static final String PREFERENCES_NAME = "melox_playback";

    private MusicQualityPreferences() {
    }

    public final MusicQuality read(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String raw = context.getApplicationContext().getSharedPreferences(PREFERENCES_NAME, 0).getString(KEY_QUALITY, null);
        MusicQuality musicQualityFromApiLevel = MusicQuality.INSTANCE.fromApiLevel(raw);
        return musicQualityFromApiLevel == null ? MusicQuality.Standard : musicQualityFromApiLevel;
    }

    public final void write(Context context, MusicQuality quality) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(quality, "quality");
        context.getApplicationContext().getSharedPreferences(PREFERENCES_NAME, 0).edit().putString(KEY_QUALITY, quality.getApiLevel()).apply();
        MusicQualityRuntime.INSTANCE.setSelected(quality);
    }
}
