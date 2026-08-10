package com.lladlam.melox.p012ui.account;

import androidx.compose.runtime.MutableState;
import androidx.media3.container.MdtaMetadataEntry;
import com.lladlam.melox.core.account.NeteaseSessionStore;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: NeteaseLoginScreen.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(m719c = "com.lladlam.melox.ui.account.NeteaseLoginScreenKt$NeteaseLoginScreen$2$1", m720f = "NeteaseLoginScreen.kt", m721i = {0, 1}, m722l = {74, 85}, m723m = "invokeSuspend", m724n = {"candidate", "candidate"}, m725nl = {MdtaMetadataEntry.TYPE_INDICATOR_8_BIT_UNSIGNED_INT, -1}, m726s = {"L$0", "L$0"}, m727v = 2)
final class NeteaseLoginScreenKt$NeteaseLoginScreen$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<String> $handledCookie$delegate;
    final /* synthetic */ Function0<Unit> $onLoggedIn;
    final /* synthetic */ NeteaseSessionStore $session;
    final /* synthetic */ MutableState<String> $verificationError$delegate;
    final /* synthetic */ MutableState<Boolean> $verifying$delegate;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    NeteaseLoginScreenKt$NeteaseLoginScreen$2$1(NeteaseSessionStore neteaseSessionStore, Function0<Unit> function0, MutableState<String> mutableState, MutableState<Boolean> mutableState2, MutableState<String> mutableState3, Continuation<? super NeteaseLoginScreenKt$NeteaseLoginScreen$2$1> continuation) {
        super(2, continuation);
        this.$session = neteaseSessionStore;
        this.$onLoggedIn = function0;
        this.$handledCookie$delegate = mutableState;
        this.$verifying$delegate = mutableState2;
        this.$verificationError$delegate = mutableState3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NeteaseLoginScreenKt$NeteaseLoginScreen$2$1(this.$session, this.$onLoggedIn, this.$handledCookie$delegate, this.$verifying$delegate, this.$verificationError$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((NeteaseLoginScreenKt$NeteaseLoginScreen$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0041  */
    /* JADX WARN: Code duplicated, block: B:19:0x0080 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:22:0x008e  */
    /* JADX WARN: Code duplicated, block: B:24:0x009d  */
    /* JADX WARN: Code duplicated, block: B:26:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:28:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:32:0x00c9 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:33:0x00ca  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x00ca -> B:34:0x00cd). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:24:0x009d
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            Method dump skipped, instruction units count: 218
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lladlam.melox.p012ui.account.NeteaseLoginScreenKt$NeteaseLoginScreen$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
