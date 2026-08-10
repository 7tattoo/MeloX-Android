package com.lladlam.melox.core.library;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import coil3.util.UtilsKt;
import java.io.File;
import java.util.LinkedHashSet;
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
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.io.FilesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.json.JSONObject;

/* JADX INFO: compiled from: NeteaseLibraryCache.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 !2\u00020\u0001:\u0001!B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH\u0086@¢\u0006\u0002\u0010\fJ\u001e\u0010\r\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\tH\u0086@¢\u0006\u0002\u0010\u0010J\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u000bH\u0086@¢\u0006\u0002\u0010\fJ\u001e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0012H\u0086@¢\u0006\u0002\u0010\u0016J2\u0010\u0017\u001a\u0004\u0018\u0001H\u0018\"\u0004\b\u0000\u0010\u00182\u0006\u0010\u0019\u001a\u00020\u00072\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u0002H\u00180\u001bH\u0082@¢\u0006\u0002\u0010\u001dJ\u001e\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u001cH\u0082@¢\u0006\u0002\u0010 R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/lladlam/melox/core/library/NeteaseLibraryCache;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "directory", "Ljava/io/File;", "loadSnapshot", "Lcom/lladlam/melox/core/library/NeteaseLibrarySnapshot;", "userId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveSnapshot", "", "snapshot", "(JLcom/lladlam/melox/core/library/NeteaseLibrarySnapshot;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadPlaylistDetail", "Lcom/lladlam/melox/core/library/NeteasePlaylistDetail;", "playlistId", "savePlaylistDetail", "detail", "(JLcom/lladlam/melox/core/library/NeteasePlaylistDetail;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readJson", ExifInterface.GPS_DIRECTION_TRUE, UtilsKt.SCHEME_FILE, "decode", "Lkotlin/Function1;", "Lorg/json/JSONObject;", "(Ljava/io/File;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeJson", "value", "(Ljava/io/File;Lorg/json/JSONObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class NeteaseLibraryCache {
    private final File directory;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final Set<Long> refreshedLibraries = new LinkedHashSet();
    private static final Set<Long> refreshedPlaylists = new LinkedHashSet();

    public NeteaseLibraryCache(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.directory = new File(context.getApplicationContext().getFilesDir(), "netease_library_cache");
    }

    public final Object loadSnapshot(long userId, Continuation<? super NeteaseLibrarySnapshot> continuation) {
        return readJson(new File(this.directory, "library_" + userId + ".json"), C26042.INSTANCE, continuation);
    }

    /* JADX INFO: renamed from: com.lladlam.melox.core.library.NeteaseLibraryCache$loadSnapshot$2 */
    /* JADX INFO: compiled from: NeteaseLibraryCache.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C26042 extends FunctionReferenceImpl implements Function1<JSONObject, NeteaseLibrarySnapshot> {
        public static final C26042 INSTANCE = new C26042();

        C26042() {
            super(1, NeteaseLibraryCacheKt.class, "decodeSnapshot", "decodeSnapshot(Lorg/json/JSONObject;)Lcom/lladlam/melox/core/library/NeteaseLibrarySnapshot;", 1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final NeteaseLibrarySnapshot invoke(JSONObject p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return NeteaseLibraryCacheKt.decodeSnapshot(p0);
        }
    }

    public final Object saveSnapshot(long userId, NeteaseLibrarySnapshot snapshot, Continuation<? super Unit> continuation) {
        File file = new File(this.directory, "library_" + userId + ".json");
        JSONObject jSONObjectEncodeSnapshot = NeteaseLibraryCacheKt.encodeSnapshot(snapshot);
        Intrinsics.checkNotNullExpressionValue(jSONObjectEncodeSnapshot, "access$encodeSnapshot(...)");
        Object objWriteJson = writeJson(file, jSONObjectEncodeSnapshot, continuation);
        return objWriteJson == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWriteJson : Unit.INSTANCE;
    }

    public final Object loadPlaylistDetail(long playlistId, Continuation<? super NeteasePlaylistDetail> continuation) {
        return readJson(new File(this.directory, "playlist_" + playlistId + ".json"), C26032.INSTANCE, continuation);
    }

    /* JADX INFO: renamed from: com.lladlam.melox.core.library.NeteaseLibraryCache$loadPlaylistDetail$2 */
    /* JADX INFO: compiled from: NeteaseLibraryCache.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C26032 extends FunctionReferenceImpl implements Function1<JSONObject, NeteasePlaylistDetail> {
        public static final C26032 INSTANCE = new C26032();

        C26032() {
            super(1, NeteaseLibraryCacheKt.class, "decodePlaylistDetail", "decodePlaylistDetail(Lorg/json/JSONObject;)Lcom/lladlam/melox/core/library/NeteasePlaylistDetail;", 1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final NeteasePlaylistDetail invoke(JSONObject p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return NeteaseLibraryCacheKt.decodePlaylistDetail(p0);
        }
    }

    public final Object savePlaylistDetail(long playlistId, NeteasePlaylistDetail detail, Continuation<? super Unit> continuation) {
        File file = new File(this.directory, "playlist_" + playlistId + ".json");
        JSONObject jSONObjectEncodePlaylistDetail = NeteaseLibraryCacheKt.encodePlaylistDetail(detail);
        Intrinsics.checkNotNullExpressionValue(jSONObjectEncodePlaylistDetail, "access$encodePlaylistDetail(...)");
        Object objWriteJson = writeJson(file, jSONObjectEncodePlaylistDetail, continuation);
        return objWriteJson == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWriteJson : Unit.INSTANCE;
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.lladlam.melox.core.library.NeteaseLibraryCache$readJson$2 */
    /* JADX INFO: compiled from: NeteaseLibraryCache.kt */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(m719c = "com.lladlam.melox.core.library.NeteaseLibraryCache$readJson$2", m720f = "NeteaseLibraryCache.kt", m721i = {}, m722l = {}, m723m = "invokeSuspend", m724n = {}, m725nl = {}, m726s = {}, m727v = 2)
    static final class C26052<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
        final /* synthetic */ Function1<JSONObject, T> $decode;
        final /* synthetic */ File $file;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C26052(File file, Function1<? super JSONObject, ? extends T> function1, Continuation<? super C26052> continuation) {
            super(2, continuation);
            this.$file = file;
            this.$decode = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C26052 c26052 = new C26052(this.$file, this.$decode, continuation);
            c26052.L$0 = obj;
            return c26052;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super T> continuation) {
            return ((C26052) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
                        objM9714constructorimpl = Result.constructor_impl(!file.isFile() ? null : function1.invoke(new JSONObject(FilesKt.readText$default(file, null, 1, null))));
                        break;
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM9714constructorimpl = Result.constructor_impl(ResultKt.createFailure(th));
                    }
                    if (Result.isFailure_impl(objM9714constructorimpl)) {
                        return null;
                    }
                    return objM9714constructorimpl;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T> Object readJson(File file, Function1<? super JSONObject, ? extends T> function1, Continuation<? super T> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C26052(file, function1, null), continuation);
    }

    /* JADX INFO: renamed from: com.lladlam.melox.core.library.NeteaseLibraryCache$writeJson$2 */
    /* JADX INFO: compiled from: NeteaseLibraryCache.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(m719c = "com.lladlam.melox.core.library.NeteaseLibraryCache$writeJson$2", m720f = "NeteaseLibraryCache.kt", m721i = {}, m722l = {}, m723m = "invokeSuspend", m724n = {}, m725nl = {}, m726s = {}, m727v = 2)
    static final class C26062 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ File $file;
        final /* synthetic */ JSONObject $value;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C26062(File file, JSONObject jSONObject, Continuation<? super C26062> continuation) {
            super(2, continuation);
            this.$file = file;
            this.$value = jSONObject;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return NeteaseLibraryCache.this.new C26062(this.$file, this.$value, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C26062) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    NeteaseLibraryCache.this.directory.mkdirs();
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
    public final Object writeJson(File file, JSONObject value, Continuation<? super Unit> continuation) {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new C26062(file, value, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: compiled from: NeteaseLibraryCache.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0006J\u000e\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u0006R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/lladlam/melox/core/library/NeteaseLibraryCache$Companion;", "", "<init>", "()V", "refreshedLibraries", "", "", "refreshedPlaylists", "beginLibraryColdStartRefresh", "", "userId", "beginPlaylistColdStartRefresh", "playlistId", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final synchronized boolean beginLibraryColdStartRefresh(long userId) {
            return NeteaseLibraryCache.refreshedLibraries.add(Long.valueOf(userId));
        }

        public final synchronized boolean beginPlaylistColdStartRefresh(long playlistId) {
            return NeteaseLibraryCache.refreshedPlaylists.add(Long.valueOf(playlistId));
        }
    }
}
