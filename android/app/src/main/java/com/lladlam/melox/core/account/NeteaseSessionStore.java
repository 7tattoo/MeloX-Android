package com.lladlam.melox.core.account;

import android.content.Context;
import android.content.SharedPreferences;
import android.webkit.CookieManager;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.media3.container.MdtaMetadataEntry;
import com.lladlam.melox.core.network.NeteaseSearchClient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.OkHttpClient;

/* JADX INFO: compiled from: NeteaseSessionStore.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 /2\u00020\u0001:\u0001/B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00130&2\u0006\u0010'\u001a\u00020\u000bH\u0086@¢\u0006\u0004\b(\u0010)J\u0018\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020\u001aH\u0086@¢\u0006\u0002\u0010-J\u0006\u0010.\u001a\u00020+R\u0016\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \u0007*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R/\u0010\u0014\u001a\u0004\u0018\u00010\u00132\b\u0010\n\u001a\u0004\u0018\u00010\u00138F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0019\u0010\u0012\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R+\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\n\u001a\u00020\u001a8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001f\u0010\u0012\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR/\u0010 \u001a\u0004\u0018\u00010\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\u000b8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b#\u0010\u0012\u001a\u0004\b!\u0010\u000e\"\u0004\b\"\u0010\u0010R\u0011\u0010$\u001a\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b$\u0010\u001c¨\u00060"}, d2 = {"Lcom/lladlam/melox/core/account/NeteaseSessionStore;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "appContext", "kotlin.jvm.PlatformType", "preferences", "Landroid/content/SharedPreferences;", "<set-?>", "", "cookie", "getCookie", "()Ljava/lang/String;", "setCookie", "(Ljava/lang/String;)V", "cookie$delegate", "Landroidx/compose/runtime/MutableState;", "Lcom/lladlam/melox/core/account/NeteaseAccountProfile;", "profile", "getProfile", "()Lcom/lladlam/melox/core/account/NeteaseAccountProfile;", "setProfile", "(Lcom/lladlam/melox/core/account/NeteaseAccountProfile;)V", "profile$delegate", "", "isRefreshing", "()Z", "setRefreshing", "(Z)V", "isRefreshing$delegate", "errorMessage", "getErrorMessage", "setErrorMessage", "errorMessage$delegate", "isLoggedIn", "acceptAuthenticatedCookie", "Lkotlin/Result;", "candidate", "acceptAuthenticatedCookie-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshProfile", "", "force", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clear", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class NeteaseSessionStore {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String KEY_COOKIE = "cookie_header";
    private static final String PREFERENCES_NAME = "netease_session";
    private final Context appContext;

    /* JADX INFO: renamed from: cookie$delegate, reason: from kotlin metadata */
    private final MutableState cookie;

    /* JADX INFO: renamed from: errorMessage$delegate, reason: from kotlin metadata */
    private final MutableState errorMessage;

    /* JADX INFO: renamed from: isRefreshing$delegate, reason: from kotlin metadata */
    private final MutableState isRefreshing;
    private final SharedPreferences preferences;

    /* JADX INFO: renamed from: profile$delegate, reason: from kotlin metadata */
    private final MutableState profile;

    /* JADX INFO: renamed from: com.lladlam.melox.core.account.NeteaseSessionStore$refreshProfile$1 */
    /* JADX INFO: compiled from: NeteaseSessionStore.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lladlam.melox.core.account.NeteaseSessionStore", f = "NeteaseSessionStore.kt", i = {0, 0, 0}, l = {68}, m = "refreshProfile", n = {"$this$refreshProfile_u24lambda_u240\\1", "force", "$i$a$-runCatching-NeteaseSessionStore$refreshProfile$2\\1\\67\\0"}, nl = {MdtaMetadataEntry.TYPE_INDICATOR_INT32}, s = {"L$0", "Z$0", "I$0"}, v = 2)
    static final class C25991 extends ContinuationImpl {
        int I$0;
        Object L$0;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C25991(Continuation<? super C25991> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return NeteaseSessionStore.this.refreshProfile(false, this);
        }
    }

    public NeteaseSessionStore(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.appContext = context.getApplicationContext();
        this.preferences = this.appContext.getSharedPreferences(PREFERENCES_NAME, 0);
        String string = this.preferences.getString(KEY_COOKIE, "");
        this.cookie = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(string != null ? string : "", null, 2, null);
        this.profile = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.isRefreshing = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.errorMessage = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
    }

    private final void setCookie(String str) {
        this.cookie.setValue(str);
    }

    public final String getCookie() {
        return (String) this.cookie.getValue();
    }

    private final void setProfile(NeteaseAccountProfile neteaseAccountProfile) {
        this.profile.setValue(neteaseAccountProfile);
    }

    public final NeteaseAccountProfile getProfile() {
        return (NeteaseAccountProfile) this.profile.getValue();
    }

    private final void setRefreshing(boolean z) {
        this.isRefreshing.setValue(Boolean.valueOf(z));
    }

    public final boolean isRefreshing() {
        return ((Boolean) this.isRefreshing.getValue()).booleanValue();
    }

    private final void setErrorMessage(String str) {
        this.errorMessage.setValue(str);
    }

    public final String getErrorMessage() {
        return (String) this.errorMessage.getValue();
    }

    public final boolean isLoggedIn() {
        return !StringsKt.isBlank(getCookie());
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: acceptAuthenticatedCookie-gIAlu-s, reason: not valid java name */
    public final Object m9598acceptAuthenticatedCookiegIAlus(String str, Continuation<? super Result<NeteaseAccountProfile>> continuation) {
        NeteaseSessionStore$acceptAuthenticatedCookie$1 neteaseSessionStore$acceptAuthenticatedCookie$1;
        Object objM9714constructorimpl;
        String strNormalizeCookie;
        NeteaseSessionStore neteaseSessionStore;
        Object objAccountProfile;
        if (continuation instanceof NeteaseSessionStore$acceptAuthenticatedCookie$1) {
            neteaseSessionStore$acceptAuthenticatedCookie$1 = (NeteaseSessionStore$acceptAuthenticatedCookie$1) continuation;
            if ((neteaseSessionStore$acceptAuthenticatedCookie$1.label & Integer.MIN_VALUE) != 0) {
                neteaseSessionStore$acceptAuthenticatedCookie$1.label -= Integer.MIN_VALUE;
            } else {
                neteaseSessionStore$acceptAuthenticatedCookie$1 = new NeteaseSessionStore$acceptAuthenticatedCookie$1(this, continuation);
            }
        } else {
            neteaseSessionStore$acceptAuthenticatedCookie$1 = new NeteaseSessionStore$acceptAuthenticatedCookie$1(this, continuation);
        }
        Object obj = neteaseSessionStore$acceptAuthenticatedCookie$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (neteaseSessionStore$acceptAuthenticatedCookie$1.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    strNormalizeCookie = INSTANCE.normalizeCookie(str);
                    if (!INSTANCE.containsMusicU(strNormalizeCookie)) {
                        Result.Companion companion = Result.INSTANCE;
                        return Result.constructor-impl(ResultKt.createFailure(new IllegalStateException("未检测到 MUSIC_U 登录 Cookie")));
                    }
                    setRefreshing(true);
                    OkHttpClient okHttpClient = null;
                    Object[] objArr = 0;
                    Object[] objArr2 = 0;
                    setErrorMessage(null);
                    Result.Companion companion2 = Result.INSTANCE;
                    neteaseSessionStore = this;
                    NeteaseSearchClient neteaseSearchClient = new NeteaseSearchClient(okHttpClient, objArr2 == true ? 1 : 0, 3, objArr == true ? 1 : 0);
                    neteaseSessionStore$acceptAuthenticatedCookie$1.L$0 = SpillingKt.nullOutSpilledVariable(str);
                    neteaseSessionStore$acceptAuthenticatedCookie$1.L$1 = strNormalizeCookie;
                    neteaseSessionStore$acceptAuthenticatedCookie$1.L$2 = neteaseSessionStore;
                    neteaseSessionStore$acceptAuthenticatedCookie$1.I$0 = 0;
                    neteaseSessionStore$acceptAuthenticatedCookie$1.label = 1;
                    objAccountProfile = neteaseSearchClient.accountProfile(strNormalizeCookie, neteaseSessionStore$acceptAuthenticatedCookie$1);
                    if (objAccountProfile == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                    break;
                case 1:
                    int i = neteaseSessionStore$acceptAuthenticatedCookie$1.I$0;
                    NeteaseSessionStore neteaseSessionStore2 = (NeteaseSessionStore) neteaseSessionStore$acceptAuthenticatedCookie$1.L$2;
                    strNormalizeCookie = (String) neteaseSessionStore$acceptAuthenticatedCookie$1.L$1;
                    ResultKt.throwOnFailure(obj);
                    neteaseSessionStore = neteaseSessionStore2;
                    objAccountProfile = obj;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            NeteaseAccountProfile neteaseAccountProfile = (NeteaseAccountProfile) objAccountProfile;
            neteaseSessionStore.preferences.edit().putString(KEY_COOKIE, strNormalizeCookie).apply();
            neteaseSessionStore.setCookie(strNormalizeCookie);
            neteaseSessionStore.setProfile(neteaseAccountProfile);
            objM9714constructorimpl = Result.constructor-impl(neteaseAccountProfile);
        } catch (Throwable th) {
            Result.Companion companion3 = Result.INSTANCE;
            objM9714constructorimpl = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable thM9717exceptionOrNullimpl = Result.m9717exceptionOrNullimpl(objM9714constructorimpl);
        if (thM9717exceptionOrNullimpl != null) {
            String message = thM9717exceptionOrNullimpl.getMessage();
            if (message == null) {
                message = "网易云账号验证失败";
            }
            setErrorMessage(message);
        }
        setRefreshing(false);
        return objM9714constructorimpl;
    }

    public static /* synthetic */ Object refreshProfile$default(NeteaseSessionStore neteaseSessionStore, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return neteaseSessionStore.refreshProfile(z, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Multi-variable type inference failed */
    public final Object refreshProfile(boolean z, Continuation<? super Unit> continuation) {
        C25991 c25991;
        Object objM9714constructorimpl;
        Object objAccountProfile;
        if (continuation instanceof C25991) {
            c25991 = (C25991) continuation;
            if ((c25991.label & Integer.MIN_VALUE) != 0) {
                c25991.label -= Integer.MIN_VALUE;
            } else {
                c25991 = new C25991(continuation);
            }
        } else {
            c25991 = new C25991(continuation);
        }
        Object obj = c25991.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (c25991.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    OkHttpClient okHttpClient = null;
                    Object[] objArr = 0;
                    Object[] objArr2 = 0;
                    if (StringsKt.isBlank(getCookie())) {
                        setProfile(null);
                        setErrorMessage(null);
                        return Unit.INSTANCE;
                    }
                    if (!z && getProfile() != null) {
                        return Unit.INSTANCE;
                    }
                    setRefreshing(true);
                    setErrorMessage(null);
                    Result.Companion companion = Result.INSTANCE;
                    NeteaseSessionStore neteaseSessionStore = this;
                    NeteaseSearchClient neteaseSearchClient = new NeteaseSearchClient(okHttpClient, objArr2 == true ? 1 : 0, 3, objArr == true ? 1 : 0);
                    String cookie = neteaseSessionStore.getCookie();
                    c25991.L$0 = SpillingKt.nullOutSpilledVariable(neteaseSessionStore);
                    c25991.Z$0 = z;
                    c25991.I$0 = 0;
                    c25991.label = 1;
                    objAccountProfile = neteaseSearchClient.accountProfile(cookie, c25991);
                    if (objAccountProfile == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                    break;
                case 1:
                    int i = c25991.I$0;
                    boolean z2 = c25991.Z$0;
                    ResultKt.throwOnFailure(obj);
                    objAccountProfile = obj;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            objM9714constructorimpl = Result.constructor-impl((NeteaseAccountProfile) objAccountProfile);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM9714constructorimpl = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.m9721isSuccessimpl(objM9714constructorimpl)) {
            setProfile((NeteaseAccountProfile) objM9714constructorimpl);
        }
        Throwable thM9717exceptionOrNullimpl = Result.m9717exceptionOrNullimpl(objM9714constructorimpl);
        if (thM9717exceptionOrNullimpl != null) {
            String message = thM9717exceptionOrNullimpl.getMessage();
            if (message == null) {
                message = "账号信息读取失败";
            }
            setErrorMessage(message);
        }
        setRefreshing(false);
        return Unit.INSTANCE;
    }

    public final void clear() {
        this.preferences.edit().remove(KEY_COOKIE).apply();
        setCookie("");
        setProfile(null);
        setErrorMessage(null);
        INSTANCE.clearWebViewCookies();
    }

    /* JADX INFO: compiled from: NeteaseSessionStore.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005J\u000e\u0010\r\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005J\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000f2\u0006\u0010\f\u001a\u00020\u0005J\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/lladlam/melox/core/account/NeteaseSessionStore$Companion;", "", "<init>", "()V", "PREFERENCES_NAME", "", "KEY_COOKIE", "readCookie", "context", "Landroid/content/Context;", "containsMusicU", "", "cookieHeader", "normalizeCookie", "parseCookie", "", "clearWebViewCookies", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String readCookie(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            String string = context.getApplicationContext().getSharedPreferences(NeteaseSessionStore.PREFERENCES_NAME, 0).getString(NeteaseSessionStore.KEY_COOKIE, "");
            return string == null ? "" : string;
        }

        public final boolean containsMusicU(String cookieHeader) {
            Intrinsics.checkNotNullParameter(cookieHeader, "cookieHeader");
            String str = parseCookie(cookieHeader).get("MUSIC_U");
            return !(str == null || StringsKt.isBlank(str));
        }

        public final String normalizeCookie(String cookieHeader) {
            Intrinsics.checkNotNullParameter(cookieHeader, "cookieHeader");
            Set setEntrySet = MapsKt.toSortedMap(parseCookie(cookieHeader)).entrySet();
            Intrinsics.checkNotNullExpressionValue(setEntrySet, "<get-entries>(...)");
            return CollectionsKt.joinToString$default(setEntrySet, "; ", null, null, 0, null, new Function1() { // from class: com.lladlam.melox.core.account.NeteaseSessionStore$Companion$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return NeteaseSessionStore.Companion.normalizeCookie$lambda$0((Map.Entry) obj);
                }
            }, 30, null);
        }

        static final CharSequence normalizeCookie$lambda$0(Map.Entry entry) {
            Intrinsics.checkNotNullParameter(entry, "<destruct>");
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            return key + "=" + value;
        }

        public final Map<String, String> parseCookie(String cookieHeader) {
            Intrinsics.checkNotNullParameter(cookieHeader, "cookieHeader");
            int i = 1;
            int i2 = 0;
            Iterable iterableSplit$default = StringsKt.split$default((CharSequence) cookieHeader, new char[]{';'}, false, 0, 6, (Object) null);
            Collection arrayList = new ArrayList();
            Iterator it = iterableSplit$default.iterator();
            while (it.hasNext()) {
                String string = StringsKt.trim((CharSequence) it.next()).toString();
                int i3 = i2;
                char[] cArr = new char[i];
                cArr[i3] = '=';
                List listSplit$default = StringsKt.split$default((CharSequence) string, cArr, false, 2, 2, (Object) null);
                Pair pairM717to = null;
                if (listSplit$default.size() == 2) {
                    String string2 = StringsKt.trim((CharSequence) listSplit$default.get(i3)).toString();
                    String string3 = StringsKt.trim((CharSequence) listSplit$default.get(1)).toString();
                    if (!StringsKt.isBlank(string2)) {
                        pairM717to = TuplesKt.m717to(string2, string3);
                    }
                }
                if (pairM717to != null) {
                    arrayList.add(pairM717to);
                }
                i = 1;
                i2 = 0;
            }
            return MapsKt.toMap((List) arrayList);
        }

        public final void clearWebViewCookies() {
            try {
                Result.Companion companion = Result.INSTANCE;
                Companion companion2 = this;
                CookieManager.getInstance().removeAllCookies(null);
                CookieManager.getInstance().flush();
                Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                Result.constructor-impl(ResultKt.createFailure(th));
            }
        }
    }
}
