package com.lladlam.melox.core.account;

import android.content.Context;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import kotlin.Metadata;

/* JADX INFO: compiled from: NeteaseSessionStore.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"rememberNeteaseSessionStore", "Lcom/lladlam/melox/core/account/NeteaseSessionStore;", "(Landroidx/compose/runtime/Composer;I)Lcom/lladlam/melox/core/account/NeteaseSessionStore;", "app"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class NeteaseSessionStoreKt {
    public static final NeteaseSessionStore rememberNeteaseSessionStore(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -2090695986, "C(rememberNeteaseSessionStore)126@4110L7,127@4129L50:NeteaseSessionStore.kt#kigeiz");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2090695986, $changed, -1, "com.lladlam.melox.core.account.rememberNeteaseSessionStore (NeteaseSessionStore.kt:125)");
        }
        ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localContext);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Context context = (Context) objConsume;
        ComposerKt.sourceInformationMarkerStart($composer, 885855456, "CC(remember):NeteaseSessionStore.kt#9igjgp");
        boolean zChanged = $composer.changed(context);
        Object objRememberedValue = $composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object neteaseSessionStore = new NeteaseSessionStore(context);
            $composer.updateRememberedValue(neteaseSessionStore);
            objRememberedValue = neteaseSessionStore;
        }
        NeteaseSessionStore neteaseSessionStore2 = (NeteaseSessionStore) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return neteaseSessionStore2;
    }
}
