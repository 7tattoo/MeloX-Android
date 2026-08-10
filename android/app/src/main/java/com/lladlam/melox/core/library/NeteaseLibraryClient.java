package com.lladlam.melox.core.library;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.style.ResolvedStyleKt;
import androidx.core.app.NotificationCompat;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.common.net.HttpHeaders;
import com.lladlam.melox.core.account.NeteaseSessionStore;
import com.lladlam.melox.core.model.SearchSong;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.io.CloseableKt;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: NeteaseLibraryClient.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\f\n\u0002\u0010\u0012\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010\u000eJ&\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u00042\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0086@¢\u0006\u0002\u0010\u0018J\u000e\u0010\u0019\u001a\u00020\u001aH\u0086@¢\u0006\u0002\u0010\u001bJ\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u0016\u001a\u00020\u0017J\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\u0006\u0010\f\u001a\u00020\rJ\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0017J\u000e\u0010 \u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\rJ$\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00132\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\b\b\u0002\u0010#\u001a\u00020$J\u0016\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010&\u001a\u00020'H\u0002J\u0014\u0010(\u001a\u0004\u0018\u00010\u00142\b\u0010)\u001a\u0004\u0018\u00010*H\u0002J\u0014\u0010+\u001a\u0004\u0018\u00010\u001f2\b\u0010)\u001a\u0004\u0018\u00010*H\u0002J\b\u0010,\u001a\u00020-H\u0002J\"\u0010.\u001a\u00020*2\u0006\u0010/\u001a\u00020\u00042\u0006\u00100\u001a\u00020*2\b\b\u0002\u0010#\u001a\u00020$H\u0002J$\u00101\u001a\u00020*2\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004032\u0006\u00104\u001a\u00020\rH\u0002J\u0010\u00105\u001a\u00020\u00042\u0006\u00106\u001a\u00020*H\u0002J\u0010\u00107\u001a\u00020\u00042\u0006\u00108\u001a\u00020\u0004H\u0002J\u0010\u00109\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u0004H\u0002J\u0010\u0010:\u001a\u00020\u00042\u0006\u0010;\u001a\u00020\u0017H\u0002J\u0010\u0010<\u001a\u00020\u00042\u0006\u0010=\u001a\u00020\u0017H\u0002J\u0010\u0010>\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u0004H\u0002J\u0018\u0010?\u001a\u00020@2\u0006\u00100\u001a\u00020@2\u0006\u0010A\u001a\u00020@H\u0002J\f\u0010B\u001a\u00020\u0004*\u00020@H\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Lcom/lladlam/melox/core/library/NeteaseLibraryClient;", "", "cookieProvider", "Lkotlin/Function0;", "", "httpClient", "Lokhttp3/OkHttpClient;", "<init>", "(Lkotlin/jvm/functions/Function0;Lokhttp3/OkHttpClient;)V", "syntheticDeviceId", "snapshot", "Lcom/lladlam/melox/core/library/NeteaseLibrarySnapshot;", "userId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "playlistDetail", "Lcom/lladlam/melox/core/library/NeteasePlaylistDetail;", "playlistId", "discoveryPlaylists", "", "Lcom/lladlam/melox/core/library/NeteasePlaylistSummary;", "category", "limit", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "homeSnapshot", "Lcom/lladlam/melox/core/library/NeteaseHomeSnapshot;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "userPlaylistsBlocking", "likedSongIdsBlocking", "recentSongsBlocking", "Lcom/lladlam/melox/core/model/SearchSong;", "playlistDetailBlocking", "songDetailsBlocking", "ids", "authenticated", "", "parsePlaylists", "array", "Lorg/json/JSONArray;", "parsePlaylist", "value", "Lorg/json/JSONObject;", "parseSong", "ensureLoggedIn", "", "eapi", "uri", "data", "authenticatedEapiHeader", "cookies", "", "timestampMillis", "encodedCookieHeader", "values", "secureUrl", "url", "encodeURIComponent", "randomHex", "byteCount", "randomDigits", "length", "md5Hex", "aesEcbEncrypt", "", "key", "toHexUppercase", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class NeteaseLibraryClient {
    public static final int $stable = 8;
    private final Function0<String> cookieProvider;
    private final OkHttpClient httpClient;
    private final String syntheticDeviceId;

    public NeteaseLibraryClient(Function0<String> cookieProvider, OkHttpClient httpClient) {
        Intrinsics.checkNotNullParameter(cookieProvider, "cookieProvider");
        Intrinsics.checkNotNullParameter(httpClient, "httpClient");
        this.cookieProvider = cookieProvider;
        this.httpClient = httpClient;
        String upperCase = randomHex(26).toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        this.syntheticDeviceId = upperCase;
    }

    public /* synthetic */ NeteaseLibraryClient(Function0 function0, OkHttpClient okHttpClient, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(function0, (i & 2) != 0 ? new OkHttpClient() : okHttpClient);
    }

    /* JADX INFO: renamed from: com.lladlam.melox.core.library.NeteaseLibraryClient$snapshot$2 */
    /* JADX INFO: compiled from: NeteaseLibraryClient.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/lladlam/melox/core/library/NeteaseLibrarySnapshot;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lladlam.melox.core.library.NeteaseLibraryClient$snapshot$2", f = "NeteaseLibraryClient.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C26102 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NeteaseLibrarySnapshot>, Object> {
        final /* synthetic */ long $userId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C26102(long j, Continuation<? super C26102> continuation) {
            super(2, continuation);
            this.$userId = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return NeteaseLibraryClient.this.new C26102(this.$userId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super NeteaseLibrarySnapshot> continuation) {
            return ((C26102) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) throws JSONException, NoSuchAlgorithmException, IOException {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    NeteaseLibraryClient.this.ensureLoggedIn();
                    List playlists = NeteaseLibraryClient.userPlaylistsBlocking$default(NeteaseLibraryClient.this, this.$userId, 0, 2, null);
                    List liked = NeteaseLibraryClient.songDetailsBlocking$default(NeteaseLibraryClient.this, CollectionsKt.take(NeteaseLibraryClient.this.likedSongIdsBlocking(this.$userId), 100), false, 2, null);
                    return new NeteaseLibrarySnapshot(playlists, liked, NeteaseLibraryClient.this.recentSongsBlocking(100));
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object snapshot(long userId, Continuation<? super NeteaseLibrarySnapshot> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C26102(userId, null), continuation);
    }

    /* JADX INFO: renamed from: com.lladlam.melox.core.library.NeteaseLibraryClient$playlistDetail$2 */
    /* JADX INFO: compiled from: NeteaseLibraryClient.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/lladlam/melox/core/library/NeteasePlaylistDetail;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lladlam.melox.core.library.NeteaseLibraryClient$playlistDetail$2", f = "NeteaseLibraryClient.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C26092 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NeteasePlaylistDetail>, Object> {
        final /* synthetic */ long $playlistId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C26092(long j, Continuation<? super C26092> continuation) {
            super(2, continuation);
            this.$playlistId = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return NeteaseLibraryClient.this.new C26092(this.$playlistId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super NeteasePlaylistDetail> continuation) {
            return ((C26092) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    return NeteaseLibraryClient.this.playlistDetailBlocking(this.$playlistId);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object playlistDetail(long playlistId, Continuation<? super NeteasePlaylistDetail> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C26092(playlistId, null), continuation);
    }

    public static /* synthetic */ Object discoveryPlaylists$default(NeteaseLibraryClient neteaseLibraryClient, String str, int i, Continuation continuation, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 50;
        }
        return neteaseLibraryClient.discoveryPlaylists(str, i, continuation);
    }

    /* JADX INFO: renamed from: com.lladlam.melox.core.library.NeteaseLibraryClient$discoveryPlaylists$2 */
    /* JADX INFO: compiled from: NeteaseLibraryClient.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/lladlam/melox/core/library/NeteasePlaylistSummary;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lladlam.melox.core.library.NeteaseLibraryClient$discoveryPlaylists$2", f = "NeteaseLibraryClient.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C26072 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends NeteasePlaylistSummary>>, Object> {
        final /* synthetic */ String $category;
        final /* synthetic */ int $limit;
        int label;
        final /* synthetic */ NeteaseLibraryClient this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C26072(int i, NeteaseLibraryClient neteaseLibraryClient, String str, Continuation<? super C26072> continuation) {
            super(2, continuation);
            this.$limit = i;
            this.this$0 = neteaseLibraryClient;
            this.$category = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C26072(this.$limit, this.this$0, this.$category, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends NeteasePlaylistSummary>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<NeteasePlaylistSummary>>) continuation);
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<NeteasePlaylistSummary>> continuation) {
            return ((C26072) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code duplicated, block: B:20:0x00b6  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) throws JSONException, NoSuchAlgorithmException, IOException {
            JSONObject response;
            String str;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    int boundedLimit = RangesKt.coerceIn(this.$limit, 1, 50);
                    boolean loggedIn = NeteaseSessionStore.INSTANCE.containsMusicU((String) this.this$0.cookieProvider.invoke());
                    switch (this.$category) {
                        case "排行榜":
                            response = this.this$0.eapi("/api/toplist", new JSONObject(), loggedIn);
                            break;
                        case "推荐歌单":
                            NeteaseLibraryClient neteaseLibraryClient = this.this$0;
                            JSONObject jSONObjectPut = new JSONObject().put("limit", boundedLimit).put("total", true).put("n", 1000);
                            Intrinsics.checkNotNullExpressionValue(jSONObjectPut, "put(...)");
                            response = neteaseLibraryClient.eapi("/api/personalized/playlist", jSONObjectPut, loggedIn);
                            break;
                        case "精品歌单":
                            NeteaseLibraryClient neteaseLibraryClient2 = this.this$0;
                            JSONObject jSONObjectPut2 = new JSONObject().put("cat", "全部").put("limit", boundedLimit).put("lasttime", 0).put("total", true);
                            Intrinsics.checkNotNullExpressionValue(jSONObjectPut2, "put(...)");
                            response = neteaseLibraryClient2.eapi("/api/playlist/highquality/list", jSONObjectPut2, loggedIn);
                            break;
                        default:
                            NeteaseLibraryClient neteaseLibraryClient3 = this.this$0;
                            JSONObject jSONObjectPut3 = new JSONObject().put("cat", this.$category).put("order", "hot").put("offset", 0).put("limit", boundedLimit).put("total", true);
                            Intrinsics.checkNotNullExpressionValue(jSONObjectPut3, "put(...)");
                            response = neteaseLibraryClient3.eapi("/api/playlist/list", jSONObjectPut3, loggedIn);
                            break;
                    }
                    NeteaseLibraryClient neteaseLibraryClient4 = this.this$0;
                    String str2 = this.$category;
                    if (Intrinsics.areEqual(str2, "推荐歌单")) {
                        str = "result";
                    } else {
                        str = Intrinsics.areEqual(str2, "排行榜") ? "list" : "playlists";
                    }
                    JSONArray jSONArrayOptJSONArray = response.optJSONArray(str);
                    if (jSONArrayOptJSONArray == null) {
                        jSONArrayOptJSONArray = new JSONArray();
                    }
                    return neteaseLibraryClient4.parsePlaylists(jSONArrayOptJSONArray);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object discoveryPlaylists(String category, int limit, Continuation<? super List<NeteasePlaylistSummary>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C26072(limit, this, category, null), continuation);
    }

    /* JADX INFO: renamed from: com.lladlam.melox.core.library.NeteaseLibraryClient$homeSnapshot$2 */
    /* JADX INFO: compiled from: NeteaseLibraryClient.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/lladlam/melox/core/library/NeteaseHomeSnapshot;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lladlam.melox.core.library.NeteaseLibraryClient$homeSnapshot$2", f = "NeteaseLibraryClient.kt", i = {}, l = {95, ResolvedStyleKt.InheritedFlags}, m = "invokeSuspend", n = {}, nl = {ResolvedStyleKt.InheritedFlags, 94}, s = {}, v = 2)
    static final class C26082 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NeteaseHomeSnapshot>, Object> {
        Object L$0;
        int label;

        C26082(Continuation<? super C26082> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return NeteaseLibraryClient.this.new C26082(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super NeteaseHomeSnapshot> continuation) {
            return ((C26082) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:13:0x004a A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:14:0x004b  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object objDiscoveryPlaylists;
            List list;
            Object objDiscoveryPlaylists2;
            List list2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    objDiscoveryPlaylists = NeteaseLibraryClient.this.discoveryPlaylists("推荐歌单", 16, this);
                    if (objDiscoveryPlaylists == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    list = (List) objDiscoveryPlaylists;
                    this.L$0 = list;
                    this.label = 2;
                    objDiscoveryPlaylists2 = NeteaseLibraryClient.this.discoveryPlaylists("排行榜", 16, this);
                    if (objDiscoveryPlaylists2 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    list2 = list;
                    return new NeteaseHomeSnapshot(list2, (List) objDiscoveryPlaylists2);
                case 1:
                    ResultKt.throwOnFailure($result);
                    objDiscoveryPlaylists = $result;
                    list = (List) objDiscoveryPlaylists;
                    this.L$0 = list;
                    this.label = 2;
                    objDiscoveryPlaylists2 = NeteaseLibraryClient.this.discoveryPlaylists("排行榜", 16, this);
                    if (objDiscoveryPlaylists2 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    list2 = list;
                    return new NeteaseHomeSnapshot(list2, (List) objDiscoveryPlaylists2);
                case 2:
                    list2 = (List) this.L$0;
                    ResultKt.throwOnFailure($result);
                    objDiscoveryPlaylists2 = $result;
                    return new NeteaseHomeSnapshot(list2, (List) objDiscoveryPlaylists2);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object homeSnapshot(Continuation<? super NeteaseHomeSnapshot> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C26082(null), continuation);
    }

    public static /* synthetic */ List userPlaylistsBlocking$default(NeteaseLibraryClient neteaseLibraryClient, long j, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 2000;
        }
        return neteaseLibraryClient.userPlaylistsBlocking(j, i);
    }

    public final List<NeteasePlaylistSummary> userPlaylistsBlocking(long userId, int limit) throws JSONException, NoSuchAlgorithmException, IOException {
        ensureLoggedIn();
        JSONObject jSONObjectPut = new JSONObject().put("uid", userId).put("limit", limit).put("offset", 0).put("includeVideo", true);
        Intrinsics.checkNotNullExpressionValue(jSONObjectPut, "put(...)");
        JSONObject response = eapi("/api/user/playlist", jSONObjectPut, true);
        JSONArray jSONArrayOptJSONArray = response.optJSONArray("playlist");
        if (jSONArrayOptJSONArray == null) {
            jSONArrayOptJSONArray = new JSONArray();
        }
        return parsePlaylists(jSONArrayOptJSONArray);
    }

    public final List<Long> likedSongIdsBlocking(long userId) throws JSONException, NoSuchAlgorithmException, IOException {
        ensureLoggedIn();
        JSONObject jSONObjectPut = new JSONObject().put("uid", userId);
        Intrinsics.checkNotNullExpressionValue(jSONObjectPut, "put(...)");
        JSONObject response = eapi("/api/song/like/get", jSONObjectPut, true);
        JSONArray ids = response.optJSONArray("ids");
        if (ids == null) {
            ids = new JSONArray();
        }
        List listCreateListBuilder = CollectionsKt.createListBuilder(ids.length());
        int length = ids.length();
        for (int i = 0; i < length; i++) {
            Long lValueOf = Long.valueOf(ids.optLong(i));
            if (!(lValueOf.longValue() > 0)) {
                lValueOf = null;
            }
            if (lValueOf != null) {
                listCreateListBuilder.add(Long.valueOf(lValueOf.longValue()));
            }
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    public static /* synthetic */ List recentSongsBlocking$default(NeteaseLibraryClient neteaseLibraryClient, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 100;
        }
        return neteaseLibraryClient.recentSongsBlocking(i);
    }

    public final List<SearchSong> recentSongsBlocking(int limit) throws JSONException, NoSuchAlgorithmException, IOException {
        JSONArray list;
        JSONObject jSONObjectOptJSONObject;
        SearchSong song;
        ensureLoggedIn();
        JSONObject jSONObjectPut = new JSONObject().put("limit", limit);
        Intrinsics.checkNotNullExpressionValue(jSONObjectPut, "put(...)");
        JSONObject response = eapi("/api/play-record/song/list", jSONObjectPut, true);
        JSONObject jSONObjectOptJSONObject2 = response.optJSONObject("data");
        if (jSONObjectOptJSONObject2 == null || (list = jSONObjectOptJSONObject2.optJSONArray("list")) == null) {
            list = new JSONArray();
        }
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        int length = list.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject3 = list.optJSONObject(i);
            if (jSONObjectOptJSONObject3 != null && (jSONObjectOptJSONObject = jSONObjectOptJSONObject3.optJSONObject("data")) != null && (song = parseSong(jSONObjectOptJSONObject)) != null) {
                listCreateListBuilder.add(song);
            }
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    public final NeteasePlaylistDetail playlistDetailBlocking(long playlistId) throws JSONException, NoSuchAlgorithmException, IOException {
        List songs;
        boolean loggedIn = NeteaseSessionStore.INSTANCE.containsMusicU(this.cookieProvider.invoke());
        JSONObject jSONObjectPut = new JSONObject().put(TtmlNode.ATTR_ID, playlistId).put("n", 100).put(CmcdData.STREAMING_FORMAT_SS, 8);
        Intrinsics.checkNotNullExpressionValue(jSONObjectPut, "put(...)");
        JSONObject response = eapi("/api/v6/playlist/detail", jSONObjectPut, loggedIn);
        JSONObject playlist = response.optJSONObject("playlist");
        if (playlist == null) {
            throw new IOException("网易云没有返回歌单详情");
        }
        NeteasePlaylistSummary summary = parsePlaylist(playlist);
        if (summary == null) {
            throw new IOException("无法解析歌单");
        }
        JSONArray trackIds = playlist.optJSONArray("trackIds");
        if (trackIds == null) {
            trackIds = new JSONArray();
        }
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        int i = 0;
        int iMin = Math.min(trackIds.length(), 100);
        while (i < iMin) {
            JSONObject jSONObjectOptJSONObject = trackIds.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                Long lValueOf = Long.valueOf(jSONObjectOptJSONObject.optLong(TtmlNode.ATTR_ID));
                if (!(lValueOf.longValue() > 0)) {
                    lValueOf = null;
                }
                if (lValueOf != null) {
                    listCreateListBuilder.add(Long.valueOf(lValueOf.longValue()));
                }
            }
            i++;
            response = response;
        }
        Object desiredIds = CollectionsKt.build(listCreateListBuilder);
        JSONArray initialTracks = playlist.optJSONArray("tracks");
        if (initialTracks == null) {
            initialTracks = new JSONArray();
        }
        LinkedHashMap byId = new LinkedHashMap();
        int length = initialTracks.length();
        for (int index = 0; index < length; index++) {
            SearchSong song = parseSong(initialTracks.optJSONObject(index));
            if (song != null) {
                byId.put(Long.valueOf(song.getId()), song);
            }
        }
        Collection arrayList = new ArrayList();
        for (Object obj : (Iterable) desiredIds) {
            Object desiredIds2 = desiredIds;
            JSONArray initialTracks2 = initialTracks;
            if (!byId.containsKey((Long) obj)) {
                arrayList.add(obj);
            }
            desiredIds = desiredIds2;
            initialTracks = initialTracks2;
        }
        Object desiredIds3 = desiredIds;
        List missing = (List) arrayList;
        if (!missing.isEmpty()) {
            for (SearchSong searchSong : songDetailsBlocking(missing, loggedIn)) {
                byId.put(Long.valueOf(searchSong.getId()), searchSong);
            }
        }
        if (((Collection) desiredIds3).isEmpty()) {
            Collection collectionValues = byId.values();
            Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
            songs = CollectionsKt.toList(collectionValues);
        } else {
            Iterable iterable = (Iterable) desiredIds3;
            Collection arrayList2 = new ArrayList();
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                Iterable iterable2 = iterable;
                SearchSong searchSong2 = (SearchSong) byId.get((Long) it.next());
                if (searchSong2 != null) {
                    arrayList2.add(searchSong2);
                }
                iterable = iterable2;
            }
            songs = (List) arrayList2;
        }
        return new NeteasePlaylistDetail(summary, songs);
    }

    public static /* synthetic */ List songDetailsBlocking$default(NeteaseLibraryClient neteaseLibraryClient, List list, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = NeteaseSessionStore.INSTANCE.containsMusicU(neteaseLibraryClient.cookieProvider.invoke());
        }
        return neteaseLibraryClient.songDetailsBlocking(list, z);
    }

    public final List<SearchSong> songDetailsBlocking(List<Long> ids, boolean authenticated) throws JSONException, NoSuchAlgorithmException, IOException {
        Intrinsics.checkNotNullParameter(ids, "ids");
        if (ids.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        JSONArray descriptors = new JSONArray();
        Iterator it = CollectionsKt.take(ids, 100).iterator();
        while (it.hasNext()) {
            descriptors.put(new JSONObject().put(TtmlNode.ATTR_ID, ((Number) it.next()).longValue()));
        }
        JSONObject jSONObjectPut = new JSONObject().put("c", descriptors.toString());
        Intrinsics.checkNotNullExpressionValue(jSONObjectPut, "put(...)");
        JSONObject response = eapi("/api/v3/song/detail", jSONObjectPut, authenticated);
        JSONArray songs = response.optJSONArray("songs");
        if (songs == null) {
            songs = new JSONArray();
        }
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        int length = songs.length();
        for (int i = 0; i < length; i++) {
            SearchSong song = parseSong(songs.optJSONObject(i));
            if (song != null) {
                listCreateListBuilder.add(song);
            }
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<NeteasePlaylistSummary> parsePlaylists(JSONArray array) {
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        int length = array.length();
        for (int i = 0; i < length; i++) {
            NeteasePlaylistSummary playlist = parsePlaylist(array.optJSONObject(i));
            if (playlist != null) {
                listCreateListBuilder.add(playlist);
            }
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    private final NeteasePlaylistSummary parsePlaylist(JSONObject value) {
        String description;
        if (value == null) {
            return null;
        }
        long id = value.optLong(TtmlNode.ATTR_ID, -1L);
        if (id <= 0) {
            return null;
        }
        String strOptString = value.optString("description");
        if (StringsKt.isBlank(strOptString)) {
            strOptString = null;
        }
        if (strOptString == null) {
            String strOptString2 = value.optString("copywriter");
            description = !StringsKt.isBlank(strOptString2) ? strOptString2 : null;
        } else {
            description = strOptString;
        }
        String strOptString3 = value.optString(HintConstants.AUTOFILL_HINT_NAME);
        if (StringsKt.isBlank(strOptString3)) {
            strOptString3 = "未命名歌单";
        }
        Intrinsics.checkNotNullExpressionValue(strOptString3, "ifBlank(...)");
        String str = strOptString3;
        String strOptString4 = value.optString("coverImgUrl");
        if (StringsKt.isBlank(strOptString4)) {
            strOptString4 = null;
        }
        String strSecureUrl = strOptString4 != null ? secureUrl(strOptString4) : null;
        int iCoerceAtLeast = RangesKt.coerceAtLeast(value.optInt("trackCount"), 0);
        JSONObject jSONObjectOptJSONObject = value.optJSONObject("creator");
        String strOptString5 = jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.optString("nickname") : null;
        if (strOptString5 == null) {
            strOptString5 = "";
        }
        return new NeteasePlaylistSummary(id, str, strSecureUrl, iCoerceAtLeast, strOptString5, RangesKt.coerceAtLeast(value.optLong("playCount", 0L), 0L), description);
    }

    /* JADX WARN: Code duplicated, block: B:44:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:53:0x00e3  */
    private final SearchSong parseSong(JSONObject value) {
        String artwork;
        String strOptString;
        String strOptString2;
        String strSecureUrl;
        String strOptString3;
        if (value == null) {
            return null;
        }
        long id = value.optLong(TtmlNode.ATTR_ID, -1L);
        if (id <= 0) {
            return null;
        }
        JSONArray artistsArray = value.optJSONArray("ar");
        if (artistsArray == null && (artistsArray = value.optJSONArray("artists")) == null) {
            artistsArray = new JSONArray();
        }
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        int length = artistsArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject = artistsArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null && (strOptString3 = jSONObjectOptJSONObject.optString(HintConstants.AUTOFILL_HINT_NAME)) != null) {
                if (StringsKt.isBlank(strOptString3)) {
                    strOptString3 = null;
                }
                if (strOptString3 != null) {
                    listCreateListBuilder.add(strOptString3);
                }
            }
        }
        String artists = CollectionsKt.joinToString$default(CollectionsKt.build(listCreateListBuilder), " / ", null, null, 0, null, null, 62, null);
        JSONObject album = value.optJSONObject("al");
        if (album == null) {
            album = value.optJSONObject("album");
        }
        if (album != null && (strOptString2 = album.optString("picUrl")) != null) {
            if (StringsKt.isBlank(strOptString2)) {
                strOptString2 = null;
            }
            if (strOptString2 != null && (strSecureUrl = secureUrl(strOptString2)) != null) {
                artwork = strSecureUrl;
            } else if (album != null) {
                artwork = null;
            } else {
                artwork = null;
            }
        } else if (album != null || (strOptString = album.optString("blurPicUrl")) == null) {
            artwork = null;
        } else {
            if (StringsKt.isBlank(strOptString)) {
                strOptString = null;
            }
            if (strOptString != null) {
                artwork = secureUrl(strOptString);
            } else {
                artwork = null;
            }
        }
        long duration = RangesKt.coerceAtLeast(value.optLong("dt", value.optLong("duration", 0L)), 0L);
        String strOptString4 = value.optString(HintConstants.AUTOFILL_HINT_NAME);
        if (StringsKt.isBlank(strOptString4)) {
            strOptString4 = "未知歌曲";
        }
        Intrinsics.checkNotNullExpressionValue(strOptString4, "ifBlank(...)");
        String str = strOptString4;
        String str2 = artists;
        if (StringsKt.isBlank(str2)) {
            str2 = "未知歌手";
        }
        String str3 = str2;
        String strOptString5 = album != null ? album.optString(HintConstants.AUTOFILL_HINT_NAME) : null;
        if (strOptString5 == null) {
            strOptString5 = "";
        }
        return new SearchSong(id, str, str3, strOptString5, artwork, duration);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void ensureLoggedIn() throws IOException {
        if (!NeteaseSessionStore.INSTANCE.containsMusicU(this.cookieProvider.invoke())) {
            throw new IOException("请先登录网易云音乐");
        }
    }

    static /* synthetic */ JSONObject eapi$default(NeteaseLibraryClient neteaseLibraryClient, String str, JSONObject jSONObject, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        return neteaseLibraryClient.eapi(str, jSONObject, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JSONObject eapi(String uri, JSONObject data, boolean authenticated) throws JSONException, NoSuchAlgorithmException, IOException {
        Throwable th;
        long timestampMillis = System.currentTimeMillis();
        String cookieHeader = this.cookieProvider.invoke();
        JSONObject header = authenticated ? authenticatedEapiHeader(NeteaseSessionStore.INSTANCE.parseCookie(cookieHeader), timestampMillis) : new JSONObject().put("os", "ios").put("appver", "9.0.90").put("osver", "18.0").put("requestId", timestampMillis + "_0000");
        JSONObject requestData = new JSONObject(data.toString()).put("header", header).put("e_r", false);
        String json = requestData.toString();
        Intrinsics.checkNotNullExpressionValue(json, "toString(...)");
        String digest = md5Hex("nobody" + uri + "use" + json + "md5forencrypt");
        String encryptedPayload = uri + "-36cd479b6b5-" + json + "-36cd479b6b5-" + digest;
        byte[] bytes = encryptedPayload.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bytes2 = "e82ckenh8dichen8".getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
        String params = toHexUppercase(aesEcbEncrypt(bytes, bytes2));
        Request.Builder requestBuilder = new Request.Builder().url("https://interface.music.163.com" + StringsKt.replace$default(uri, "/api/", "/eapi/", false, 4, (Object) null)).header(HttpHeaders.USER_AGENT, authenticated ? "NeteaseMusic 9.0.90/5038 (iPhone; iOS 16.2; zh_CN)" : "Mozilla/5.0 (iPhone; CPU iPhone OS 18_0 like Mac OS X) AppleWebKit/605.1.15 Mobile/15E148").header(HttpHeaders.ACCEPT, "*/*");
        if (authenticated) {
            Intrinsics.checkNotNull(header);
            requestBuilder.header(HttpHeaders.COOKIE, encodedCookieHeader(header));
        }
        Request request = requestBuilder.post(new FormBody.Builder(null, 1, null).add("params", params).build()).build();
        Response responseExecute = this.httpClient.newCall(request).execute();
        try {
            Response response = responseExecute;
            String strString = response.body().string();
            try {
                if (!response.getIsSuccessful()) {
                    throw new IOException("网易云请求失败：HTTP " + response.code());
                }
                if (StringsKt.isBlank(strString)) {
                    throw new IOException("网易云返回了空响应");
                }
                JSONObject jSONObject = new JSONObject(strString);
                int iOptInt = jSONObject.optInt("code", response.code());
                if (200 <= iOptInt && iOptInt < 300) {
                    CloseableKt.closeFinally(responseExecute, null);
                    return jSONObject;
                }
                String strOptString = jSONObject.optString("message");
                if (StringsKt.isBlank(strOptString)) {
                    try {
                        strOptString = jSONObject.optString(NotificationCompat.CATEGORY_MESSAGE);
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                String str = strOptString;
                if (StringsKt.isBlank(str)) {
                    str = "请求失败";
                }
                throw new IOException("网易云请求失败（" + iOptInt + "）：" + str);
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
        }
        try {
            throw th;
        } catch (Throwable th5) {
            CloseableKt.closeFinally(responseExecute, th);
            throw th5;
        }
    }

    private final JSONObject authenticatedEapiHeader(Map<String, String> cookies, long timestampMillis) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        String str = cookies.get("osver");
        if (str == null) {
            str = "16.2";
        }
        JSONObject jSONObjectPut = jSONObject.put("osver", str);
        String str2 = cookies.get("deviceId");
        if (str2 == null) {
            str2 = this.syntheticDeviceId;
        }
        JSONObject jSONObjectPut2 = jSONObjectPut.put("deviceId", str2);
        String str3 = cookies.get("os");
        if (str3 == null) {
            str3 = "iPhone OS";
        }
        JSONObject jSONObjectPut3 = jSONObjectPut2.put("os", str3);
        String str4 = cookies.get("appver");
        if (str4 == null) {
            str4 = "9.0.90";
        }
        JSONObject jSONObjectPut4 = jSONObjectPut3.put("appver", str4);
        String str5 = cookies.get("versioncode");
        if (str5 == null) {
            str5 = "140";
        }
        JSONObject jSONObjectPut5 = jSONObjectPut4.put("versioncode", str5);
        String str6 = cookies.get("mobilename");
        if (str6 == null) {
            str6 = "";
        }
        JSONObject jSONObjectPut6 = jSONObjectPut5.put("mobilename", str6);
        String strValueOf = cookies.get("buildver");
        if (strValueOf == null) {
            strValueOf = String.valueOf(timestampMillis / 1000);
        }
        JSONObject jSONObjectPut7 = jSONObjectPut6.put("buildver", strValueOf);
        String str7 = cookies.get("resolution");
        if (str7 == null) {
            str7 = "1170x2532";
        }
        JSONObject jSONObjectPut8 = jSONObjectPut7.put("resolution", str7);
        String str8 = cookies.get("__csrf");
        JSONObject jSONObjectPut9 = jSONObjectPut8.put("__csrf", str8 != null ? str8 : "");
        String str9 = cookies.get("channel");
        if (str9 == null) {
            str9 = "distribution";
        }
        JSONObject header = jSONObjectPut9.put("channel", str9).put("requestId", timestampMillis + "_" + randomDigits(4));
        String str10 = cookies.get("MUSIC_U");
        if (str10 != null) {
            if (StringsKt.isBlank(str10)) {
                str10 = null;
            }
            if (str10 != null) {
                header.put("MUSIC_U", str10);
            }
        }
        Intrinsics.checkNotNull(header);
        return header;
    }

    private final String encodedCookieHeader(final JSONObject values) {
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        Iterator<String> itKeys = values.keys();
        while (itKeys.hasNext()) {
            listCreateListBuilder.add(itKeys.next());
        }
        List keys = CollectionsKt.sorted(CollectionsKt.build(listCreateListBuilder));
        return CollectionsKt.joinToString$default(keys, "; ", null, null, 0, null, new Function1() { // from class: com.lladlam.melox.core.library.NeteaseLibraryClient$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NeteaseLibraryClient.encodedCookieHeader$lambda$1(this.f$0, values, (String) obj);
            }
        }, 30, null);
    }

    static final CharSequence encodedCookieHeader$lambda$1(NeteaseLibraryClient this$0, JSONObject $values, String key) throws UnsupportedEncodingException {
        Intrinsics.checkNotNull(key);
        String strEncodeURIComponent = this$0.encodeURIComponent(key);
        String strOptString = $values.optString(key);
        Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
        return strEncodeURIComponent + "=" + this$0.encodeURIComponent(strOptString);
    }

    private final String secureUrl(String url) {
        if (!StringsKt.startsWith(url, "http://", true)) {
            return url;
        }
        return "https://" + StringsKt.substringAfter$default(url, "://", (String) null, 2, (Object) null);
    }

    private final String encodeURIComponent(String value) throws UnsupportedEncodingException {
        String strEncode = URLEncoder.encode(value, Charsets.UTF_8.name());
        Intrinsics.checkNotNullExpressionValue(strEncode, "encode(...)");
        return StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(strEncode, "+", "%20", false, 4, (Object) null), "%21", "!", false, 4, (Object) null), "%27", "'", false, 4, (Object) null), "%28", "(", false, 4, (Object) null), "%29", ")", false, 4, (Object) null), "%7E", "~", false, 4, (Object) null);
    }

    private final String randomHex(int byteCount) {
        byte[] bytes = new byte[byteCount];
        new SecureRandom().nextBytes(bytes);
        return ArraysKt.joinToString$default(bytes, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.lladlam.melox.core.library.NeteaseLibraryClient$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NeteaseLibraryClient.randomHex$lambda$0(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    static final CharSequence randomHex$lambda$0(byte it) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(it)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    private final String randomDigits(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append((char) (new SecureRandom().nextInt(10) + 48));
        }
        return sb.toString();
    }

    private final String md5Hex(String value) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] bytes = value.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDigest = messageDigest.digest(bytes);
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
        return ArraysKt.joinToString$default(bArrDigest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.lladlam.melox.core.library.NeteaseLibraryClient$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NeteaseLibraryClient.md5Hex$lambda$0(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    static final CharSequence md5Hex$lambda$0(byte it) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(it)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    private final byte[] aesEcbEncrypt(byte[] data, byte[] key) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(1, new SecretKeySpec(key, "AES"));
        byte[] bArrDoFinal = cipher.doFinal(data);
        Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "doFinal(...)");
        return bArrDoFinal;
    }

    private final String toHexUppercase(byte[] $this$toHexUppercase) {
        return ArraysKt.joinToString$default($this$toHexUppercase, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.lladlam.melox.core.library.NeteaseLibraryClient$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NeteaseLibraryClient.toHexUppercase$lambda$0(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    static final CharSequence toHexUppercase$lambda$0(byte it) {
        String str = String.format("%02X", Arrays.copyOf(new Object[]{Byte.valueOf(it)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}
