package com.lladlam.melox;

import android.app.Application;
import com.lladlam.melox.core.crash.MeloXCrashReporter;
import kotlin.Metadata;

/* JADX INFO: compiled from: MeloXApplication.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/lladlam/melox/MeloXApplication;", "Landroid/app/Application;", "<init>", "()V", "onCreate", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class MeloXApplication extends Application {
    public static final int $stable = 8;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        MeloXCrashReporter.INSTANCE.install(this);
    }
}
