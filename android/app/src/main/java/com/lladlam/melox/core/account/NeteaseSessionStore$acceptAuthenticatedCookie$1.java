package com.lladlam.melox.core.account;

import androidx.core.view.MotionEventCompat;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: NeteaseSessionStore.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(m719c = "com.lladlam.melox.core.account.NeteaseSessionStore", m720f = "NeteaseSessionStore.kt", m721i = {0, 0, 0, 0}, m722l = {45}, m723m = "acceptAuthenticatedCookie-gIAlu-s", m724n = {"candidate", "normalized", "$this$acceptAuthenticatedCookie_gIAlu_s_u24lambda_u240\\1", "$i$a$-runCatching-NeteaseSessionStore$acceptAuthenticatedCookie$2\\1\\44\\0"}, m725nl = {MotionEventCompat.AXIS_GENERIC_15}, m726s = {"L$0", "L$1", "L$2", "I$0"}, m727v = 2)
final class NeteaseSessionStore$acceptAuthenticatedCookie$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ NeteaseSessionStore this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    NeteaseSessionStore$acceptAuthenticatedCookie$1(NeteaseSessionStore neteaseSessionStore, Continuation<? super NeteaseSessionStore$acceptAuthenticatedCookie$1> continuation) {
        super(continuation);
        this.this$0 = neteaseSessionStore;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM9598acceptAuthenticatedCookiegIAlus = this.this$0.acceptAuthenticatedCookie(null, this);
        return objM9598acceptAuthenticatedCookiegIAlus == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM9598acceptAuthenticatedCookiegIAlus : Result.box_impl(objM9598acceptAuthenticatedCookiegIAlus);
    }
}
