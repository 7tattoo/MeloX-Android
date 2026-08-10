package com.lladlam.melox;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import androidx.activity.EdgeToEdge;
import androidx.activity.compose.ComponentActivityKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import com.lladlam.melox.core.crash.MeloXCrashReporter;
import com.lladlam.melox.p012ui.MeloXAppKt;
import com.lladlam.melox.p012ui.theme.MeloXThemeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0014J\u0012\u0010\u0014\u001a\u00020\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002R+\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/lladlam/melox/MainActivity;", "Landroidx/activity/ComponentActivity;", "<init>", "()V", "<set-?>", "", "openNowPlayingRequest", "getOpenNowPlayingRequest", "()I", "setOpenNowPlayingRequest", "(I)V", "openNowPlayingRequest$delegate", "Landroidx/compose/runtime/MutableIntState;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onNewIntent", "intent", "Landroid/content/Intent;", "consumePlaybackIntent", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class MainActivity extends ComponentActivity {
    public static final String ACTION_OPEN_NOW_PLAYING = "com.lladlam.melox.action.OPEN_NOW_PLAYING";

    /* JADX INFO: renamed from: openNowPlayingRequest$delegate, reason: from kotlin metadata */
    private final MutableIntState openNowPlayingRequest = SnapshotIntStateKt.mutableIntStateOf(0);
    public static final int $stable = 8;

    private final int getOpenNowPlayingRequest() {
        return this.openNowPlayingRequest.getIntValue();
    }

    private final void setOpenNowPlayingRequest(int i) {
        this.openNowPlayingRequest.setIntValue(i);
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String strRestoreBestAvailableReportToClipboard = MeloXCrashReporter.INSTANCE.restoreBestAvailableReportToClipboard(this);
        if (strRestoreBestAvailableReportToClipboard != null) {
            Toast.makeText(this, strRestoreBestAvailableReportToClipboard + " 已复制到剪贴板", 1).show();
        }
        EdgeToEdge.enable$default(this, null, null, 3, null);
        consumePlaybackIntent(getIntent());
        ComponentActivityKt.setContent$default(this, null, ComposableLambdaKt.composableLambdaInstance(1974410241, true, new Function2() { // from class: com.lladlam.melox.MainActivity$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return MainActivity.onCreate$lambda$1(this.f$0, (Composer) obj, ((Integer) obj2).intValue());
            }
        }), 1, null);
    }

    static final Unit onCreate$lambda$1(final MainActivity this$0, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C27@1009L126,27@998L137:MainActivity.kt#mnutbr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1974410241, $changed, -1, "com.lladlam.melox.MainActivity.onCreate.<anonymous> (MainActivity.kt:27)");
            }
            MeloXThemeKt.MeloXTheme(false, ComposableLambdaKt.rememberComposableLambda(1201600007, true, new Function2() { // from class: com.lladlam.melox.MainActivity$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MainActivity.onCreate$lambda$1$0(this.f$0, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 48, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$1$0(MainActivity this$0, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C28@1027L94:MainActivity.kt#mnutbr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1201600007, $changed, -1, "com.lladlam.melox.MainActivity.onCreate.<anonymous>.<anonymous> (MainActivity.kt:28)");
            }
            MeloXAppKt.MeloXApp(this$0.getOpenNowPlayingRequest(), $composer, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onNewIntent(intent);
        setIntent(intent);
        consumePlaybackIntent(intent);
    }

    private final void consumePlaybackIntent(Intent intent) {
        if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, ACTION_OPEN_NOW_PLAYING)) {
            setOpenNowPlayingRequest(getOpenNowPlayingRequest() + 1);
        }
    }
}
