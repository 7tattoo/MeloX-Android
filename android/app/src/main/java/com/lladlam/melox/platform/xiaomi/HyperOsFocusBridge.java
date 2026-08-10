package com.lladlam.melox.platform.xiaomi;

import android.app.Notification;
import android.content.Context;
import android.provider.Settings;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HyperOsFocusBridge.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\u0011B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/lladlam/melox/platform/xiaomi/HyperOsFocusBridge;", "", "<init>", "()V", "FOCUS_PROTOCOL_SETTING", "", "FOCUS_PARAM_KEY", "protocol", "Lcom/lladlam/melox/platform/xiaomi/HyperOsFocusBridge$Protocol;", "context", "Landroid/content/Context;", "supportsSuperIsland", "", "attachFocusParams", "Landroid/app/Notification;", "notification", "islandParamsJson", "Protocol", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class HyperOsFocusBridge {
    public static final int $stable = 0;
    private static final String FOCUS_PARAM_KEY = "miui.focus.param";
    private static final String FOCUS_PROTOCOL_SETTING = "notification_focus_protocol";
    public static final HyperOsFocusBridge INSTANCE = new HyperOsFocusBridge();

    private HyperOsFocusBridge() {
    }

    /* JADX INFO: compiled from: HyperOsFocusBridge.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/lladlam/melox/platform/xiaomi/HyperOsFocusBridge$Protocol;", "", "version", "", "<init>", "(Ljava/lang/String;II)V", "getVersion", "()I", "Unsupported", "HyperOs1", "HyperOs2", "HyperOs3", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public enum Protocol {
        Unsupported(0),
        HyperOs1(1),
        HyperOs2(2),
        HyperOs3(3);

        private final int version;
        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<Protocol> getEntries() {
            return $ENTRIES;
        }

        Protocol(int version) {
            this.version = version;
        }

        public final int getVersion() {
            return this.version;
        }
    }

    public final Protocol protocol(Context context) {
        Object next;
        Intrinsics.checkNotNullParameter(context, "context");
        int version = Settings.System.getInt(context.getContentResolver(), FOCUS_PROTOCOL_SETTING, 0);
        Iterator<Protocol> it = Protocol.getEntries().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!(((Protocol) next).getVersion() == version));
        Protocol protocol = (Protocol) next;
        return protocol == null ? Protocol.Unsupported : protocol;
    }

    public final boolean supportsSuperIsland(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return protocol(context) == Protocol.HyperOs3;
    }

    public final Notification attachFocusParams(Notification notification, String islandParamsJson) {
        Intrinsics.checkNotNullParameter(notification, "notification");
        Intrinsics.checkNotNullParameter(islandParamsJson, "islandParamsJson");
        notification.extras.putString(FOCUS_PARAM_KEY, islandParamsJson);
        return notification;
    }
}
