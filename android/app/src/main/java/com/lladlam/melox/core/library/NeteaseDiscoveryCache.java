package com.lladlam.melox.core.library;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import coil3.util.UtilsKt;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.io.FilesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: NeteaseDiscoveryCache.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\fH\u0086@¢\u0006\u0002\u0010\rJ$\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0086@¢\u0006\u0002\u0010\u0011J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0086@¢\u0006\u0002\u0010\u0014J\u0016\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0013H\u0086@¢\u0006\u0002\u0010\u0017J2\u0010\u0018\u001a\u0004\u0018\u0001H\u0019\"\u0004\b\u0000\u0010\u00192\u0006\u0010\u001a\u001a\u00020\u00072\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u0002H\u00190\u001cH\u0082@¢\u0006\u0002\u0010\u001eJ\u001e\u0010\u001f\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u001dH\u0082@¢\u0006\u0002\u0010!R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/lladlam/melox/core/library/NeteaseDiscoveryCache;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "directory", "Ljava/io/File;", "loadCategory", "", "Lcom/lladlam/melox/core/library/NeteasePlaylistSummary;", "category", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveCategory", "", "playlists", "(Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadHome", "Lcom/lladlam/melox/core/library/NeteaseHomeSnapshot;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveHome", "snapshot", "(Lcom/lladlam/melox/core/library/NeteaseHomeSnapshot;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "read", ExifInterface.GPS_DIRECTION_TRUE, UtilsKt.SCHEME_FILE, "decode", "Lkotlin/Function1;", "Lorg/json/JSONObject;", "(Ljava/io/File;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "write", "value", "(Ljava/io/File;Lorg/json/JSONObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class NeteaseDiscoveryCache {
    private static boolean refreshedHome;
    private final File directory;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final Set<String> refreshedCategories = new LinkedHashSet();

    public NeteaseDiscoveryCache(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.directory = new File(context.getApplicationContext().getFilesDir(), "netease_discovery_cache");
    }

    public final Object loadCategory(String category, Continuation<? super List<NeteasePlaylistSummary>> continuation) throws NoSuchAlgorithmException {
        return read(new File(this.directory, "category_" + NeteaseDiscoveryCacheKt.stableKey(category) + ".json"), new Function1() { // from class: com.lladlam.melox.core.library.NeteaseDiscoveryCache$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NeteaseDiscoveryCache.loadCategory$lambda$0((JSONObject) obj);
            }
        }, continuation);
    }

    static final List loadCategory$lambda$0(JSONObject json) {
        Intrinsics.checkNotNullParameter(json, "json");
        JSONArray jSONArrayOptJSONArray = json.optJSONArray("playlists");
        if (jSONArrayOptJSONArray == null) {
            jSONArrayOptJSONArray = new JSONArray();
        }
        return NeteaseDiscoveryCacheKt.decodePlaylists(jSONArrayOptJSONArray);
    }

    public final Object saveCategory(String category, List<NeteasePlaylistSummary> list, Continuation<? super Unit> continuation) throws JSONException {
        File file = new File(this.directory, "category_" + NeteaseDiscoveryCacheKt.stableKey(category) + ".json");
        JSONObject jSONObjectPut = new JSONObject().put("playlists", NeteaseDiscoveryCacheKt.encodePlaylists(list));
        Intrinsics.checkNotNullExpressionValue(jSONObjectPut, "put(...)");
        Object objWrite = write(file, jSONObjectPut, continuation);
        return objWrite == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWrite : Unit.INSTANCE;
    }

    public final Object loadHome(Continuation<? super NeteaseHomeSnapshot> continuation) {
        return read(new File(this.directory, "home.json"), new Function1() { // from class: com.lladlam.melox.core.library.NeteaseDiscoveryCache$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NeteaseDiscoveryCache.loadHome$lambda$0((JSONObject) obj);
            }
        }, continuation);
    }

    static final NeteaseHomeSnapshot loadHome$lambda$0(JSONObject json) {
        Intrinsics.checkNotNullParameter(json, "json");
        JSONArray jSONArrayOptJSONArray = json.optJSONArray("recommended");
        if (jSONArrayOptJSONArray == null) {
            jSONArrayOptJSONArray = new JSONArray();
        }
        List listDecodePlaylists = NeteaseDiscoveryCacheKt.decodePlaylists(jSONArrayOptJSONArray);
        JSONArray jSONArrayOptJSONArray2 = json.optJSONArray("charts");
        if (jSONArrayOptJSONArray2 == null) {
            jSONArrayOptJSONArray2 = new JSONArray();
        }
        return new NeteaseHomeSnapshot(listDecodePlaylists, NeteaseDiscoveryCacheKt.decodePlaylists(jSONArrayOptJSONArray2));
    }

    public final Object saveHome(NeteaseHomeSnapshot snapshot, Continuation<? super Unit> continuation) throws JSONException {
        File file = new File(this.directory, "home.json");
        JSONObject jSONObjectPut = new JSONObject().put("recommended", NeteaseDiscoveryCacheKt.encodePlaylists(snapshot.getRecommended())).put("charts", NeteaseDiscoveryCacheKt.encodePlaylists(snapshot.getCharts()));
        Intrinsics.checkNotNullExpressionValue(jSONObjectPut, "put(...)");
        Object objWrite = write(file, jSONObjectPut, continuation);
        return objWrite == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWrite : Unit.INSTANCE;
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.lladlam.melox.core.library.NeteaseDiscoveryCache$read$2 */
    /* JADX INFO: compiled from: NeteaseDiscoveryCache.kt */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lladlam.melox.core.library.NeteaseDiscoveryCache$read$2", f = "NeteaseDiscoveryCache.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C26012<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
        final /* synthetic */ Function1<JSONObject, T> $decode;
        final /* synthetic */ File $file;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C26012(File file, Function1<? super JSONObject, ? extends T> function1, Continuation<? super C26012> continuation) {
            super(2, continuation);
            this.$file = file;
            this.$decode = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C26012 c26012 = new C26012(this.$file, this.$decode, continuation);
            c26012.L$0 = obj;
            return c26012;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super T> continuation) {
            return ((C26012) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object objM9714constructorimpl;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    File file = this.$file;
                    Function1<JSONObject, T> function1 = this.$decode;
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objM9714constructorimpl = Result.constructor-impl(!file.isFile() ? null : function1.invoke(new JSONObject(FilesKt.readText$default(file, null, 1, null))));
                        break;
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM9714constructorimpl = Result.constructor-impl(ResultKt.createFailure(th));
                    }
                    if (Result.m9720isFailureimpl(objM9714constructorimpl)) {
                        return null;
                    }
                    return objM9714constructorimpl;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T> Object read(File file, Function1<? super JSONObject, ? extends T> function1, Continuation<? super T> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C26012(file, function1, null), continuation);
    }

    /* JADX INFO: renamed from: com.lladlam.melox.core.library.NeteaseDiscoveryCache$write$2 */
    /* JADX INFO: compiled from: NeteaseDiscoveryCache.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lladlam.melox.core.library.NeteaseDiscoveryCache$write$2", f = "NeteaseDiscoveryCache.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C26022 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ File $file;
        final /* synthetic */ JSONObject $value;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C26022(File file, JSONObject jSONObject, Continuation<? super C26022> continuation) {
            super(2, continuation);
            this.$file = file;
            this.$value = jSONObject;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return NeteaseDiscoveryCache.this.new C26022(this.$file, this.$value, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C26022) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    NeteaseDiscoveryCache.this.directory.mkdirs();
                    File temporary = new File(this.$file.getParentFile(), this.$file.getName() + ".tmp");
                    String string = this.$value.toString();
                    Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                    FilesKt.writeText$default(temporary, string, null, 2, null);
                    if (!temporary.renameTo(this.$file)) {
                        File file = this.$file;
                        String string2 = this.$value.toString();
                        Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                        FilesKt.writeText$default(file, string2, null, 2, null);
                        temporary.delete();
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object write(File file, JSONObject value, Continuation<? super Unit> continuation) {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new C26022(file, value, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: compiled from: NeteaseDiscoveryCache.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0006J\u0006\u0010\u000b\u001a\u00020\bR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/lladlam/melox/core/library/NeteaseDiscoveryCache$Companion;", "", "<init>", "()V", "refreshedCategories", "", "", "refreshedHome", "", "beginCategoryColdStartRefresh", "category", "beginHomeColdStartRefresh", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final synchronized boolean beginCategoryColdStartRefresh(String category) {
            Intrinsics.checkNotNullParameter(category, "category");
            return NeteaseDiscoveryCache.refreshedCategories.add(category);
        }

        public final synchronized boolean beginHomeColdStartRefresh() {
            if (NeteaseDiscoveryCache.refreshedHome) {
                return false;
            }
            NeteaseDiscoveryCache.refreshedHome = true;
            return true;
        }
    }
}
