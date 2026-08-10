package com.lladlam.melox.core.network;

import androidx.autofill.HintConstants;
import androidx.core.app.NotificationCompat;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import com.google.common.net.HttpHeaders;
import com.lladlam.melox.core.account.NeteaseAccountProfile;
import com.lladlam.melox.core.account.NeteaseSessionStore;
import com.lladlam.melox.core.lyrics.LyricsDocument;
import com.lladlam.melox.core.lyrics.NeteaseLyricParser;
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
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.io.CloseableKt;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
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

/* JADX INFO: compiled from: NeteaseSearchClient.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u000b\n\u0002\u0010\u0012\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B!\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u0006H\u0086@¢\u0006\u0002\u0010\rJ&\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0086@¢\u0006\u0002\u0010\u0014J\"\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0086@¢\u0006\u0002\u0010\u0017J\u0016\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0010H\u0086@¢\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0086@¢\u0006\u0002\u0010\u001eJ\u0016\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001dH\u0086@¢\u0006\u0002\u0010\u001eJ\u0015\u0010 \u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001dH\u0000¢\u0006\u0002\b!J\u0014\u0010\"\u001a\u0004\u0018\u00010\u00062\b\u0010#\u001a\u0004\u0018\u00010$H\u0002J\u0010\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u0006H\u0002J5\u0010'\u001a\u00020$2\u0006\u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020$2\n\b\u0002\u0010*\u001a\u0004\u0018\u00010+2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0002\u0010-J$\u0010.\u001a\u00020$2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006002\u0006\u00101\u001a\u00020\u001dH\u0002J\u0010\u00102\u001a\u00020\u00062\u0006\u00103\u001a\u00020$H\u0002J\u0010\u00104\u001a\u00020\u00062\u0006\u00105\u001a\u00020\u0006H\u0002J\u0010\u00106\u001a\u00020\u00062\u0006\u00107\u001a\u00020\u0013H\u0002J\u0010\u00108\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u0013H\u0002J\u0010\u0010:\u001a\u00020\u00062\u0006\u00105\u001a\u00020\u0006H\u0002J\u0018\u0010;\u001a\u00020<2\u0006\u0010)\u001a\u00020<2\u0006\u0010=\u001a\u00020<H\u0002J\f\u0010>\u001a\u00020\u0006*\u00020<H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006?"}, d2 = {"Lcom/lladlam/melox/core/network/NeteaseSearchClient;", "", "httpClient", "Lokhttp3/OkHttpClient;", "cookieProvider", "Lkotlin/Function0;", "", "<init>", "(Lokhttp3/OkHttpClient;Lkotlin/jvm/functions/Function0;)V", "syntheticDeviceId", "accountProfile", "Lcom/lladlam/melox/core/account/NeteaseAccountProfile;", "cookieHeader", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchSongs", "", "Lcom/lladlam/melox/core/model/SearchSong;", "keywords", "limit", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ensureArtwork", "songs", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "song", "(Lcom/lladlam/melox/core/model/SearchSong;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lyrics", "Lcom/lladlam/melox/core/lyrics/LyricsDocument;", "songId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "playbackUrl", "playbackUrlBlocking", "playbackUrlBlocking$app", "artworkFromAlbum", "albumObject", "Lorg/json/JSONObject;", "secureUrl", "url", "eapi", "uri", "data", "authenticated", "", "cookieHeaderOverride", "(Ljava/lang/String;Lorg/json/JSONObject;Ljava/lang/Boolean;Ljava/lang/String;)Lorg/json/JSONObject;", "authenticatedEapiHeader", "cookies", "", "timestampMillis", "encodedCookieHeader", "values", "encodeURIComponent", "value", "randomHex", "byteCount", "randomDigits", "length", "md5Hex", "aesEcbEncrypt", "", "key", "toHexUppercase", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class NeteaseSearchClient {
    public static final int $stable = 8;
    private final Function0<String> cookieProvider;
    private final OkHttpClient httpClient;
    private final String syntheticDeviceId;

    /* JADX INFO: renamed from: com.lladlam.melox.core.network.NeteaseSearchClient$ensureArtwork$3 */
    /* JADX INFO: compiled from: NeteaseSearchClient.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lladlam.melox.core.network.NeteaseSearchClient", f = "NeteaseSearchClient.kt", i = {0}, l = {159}, m = "ensureArtwork", n = {"song"}, nl = {-1}, s = {"L$0"}, v = 2)
    static final class C26133 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C26133(Continuation<? super C26133> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return NeteaseSearchClient.this.ensureArtwork((SearchSong) null, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public NeteaseSearchClient() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public NeteaseSearchClient(OkHttpClient httpClient, Function0<String> cookieProvider) {
        Intrinsics.checkNotNullParameter(httpClient, "httpClient");
        Intrinsics.checkNotNullParameter(cookieProvider, "cookieProvider");
        this.httpClient = httpClient;
        this.cookieProvider = cookieProvider;
        String upperCase = randomHex(26).toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        this.syntheticDeviceId = upperCase;
    }

    public /* synthetic */ NeteaseSearchClient(OkHttpClient okHttpClient, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new OkHttpClient() : okHttpClient, (i & 2) != 0 ? new Function0() { // from class: com.lladlam.melox.core.network.NeteaseSearchClient$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NeteaseSearchClient._init_$lambda$0();
            }
        } : function0);
    }

    static final String _init_$lambda$0() {
        return "";
    }

    public static /* synthetic */ Object accountProfile$default(NeteaseSearchClient neteaseSearchClient, String str, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            str = neteaseSearchClient.cookieProvider.invoke();
        }
        return neteaseSearchClient.accountProfile(str, continuation);
    }

    /* JADX INFO: renamed from: com.lladlam.melox.core.network.NeteaseSearchClient$accountProfile$2 */
    /* JADX INFO: compiled from: NeteaseSearchClient.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/lladlam/melox/core/account/NeteaseAccountProfile;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lladlam.melox.core.network.NeteaseSearchClient$accountProfile$2", f = "NeteaseSearchClient.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C26112 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NeteaseAccountProfile>, Object> {
        final /* synthetic */ String $cookieHeader;
        int label;
        final /* synthetic */ NeteaseSearchClient this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C26112(String str, NeteaseSearchClient neteaseSearchClient, Continuation<? super C26112> continuation) {
            super(2, continuation);
            this.$cookieHeader = str;
            this.this$0 = neteaseSearchClient;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C26112(this.$cookieHeader, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super NeteaseAccountProfile> continuation) {
            return ((C26112) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) throws IOException {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    if (!NeteaseSessionStore.INSTANCE.containsMusicU(this.$cookieHeader)) {
                        throw new IOException("请先登录网易云音乐");
                    }
                    JSONObject response = this.this$0.eapi("/api/w/nuser/account/get", new JSONObject(), Boxing.boxBoolean(true), this.$cookieHeader);
                    JSONObject profile = response.optJSONObject("profile");
                    if (profile == null) {
                        throw new IOException("网易云登录状态无效");
                    }
                    long userId = profile.optLong("userId", -1L);
                    if (userId <= 0) {
                        throw new IOException("网易云返回了无效的用户信息");
                    }
                    String strOptString = profile.optString("nickname");
                    if (StringsKt.isBlank(strOptString)) {
                        strOptString = "网易云用户";
                    }
                    Intrinsics.checkNotNullExpressionValue(strOptString, "ifBlank(...)");
                    String str = strOptString;
                    String strOptString2 = profile.optString("avatarUrl");
                    if (StringsKt.isBlank(strOptString2)) {
                        strOptString2 = null;
                    }
                    String strSecureUrl = strOptString2 != null ? this.this$0.secureUrl(strOptString2) : null;
                    String strOptString3 = profile.optString("backgroundUrl");
                    if (StringsKt.isBlank(strOptString3)) {
                        strOptString3 = null;
                    }
                    String strSecureUrl2 = strOptString3 != null ? this.this$0.secureUrl(strOptString3) : null;
                    String strOptString4 = profile.optString("signature");
                    return new NeteaseAccountProfile(userId, str, strSecureUrl, strSecureUrl2, !StringsKt.isBlank(strOptString4) ? strOptString4 : null);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object accountProfile(String cookieHeader, Continuation<? super NeteaseAccountProfile> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C26112(cookieHeader, this, null), continuation);
    }

    public static /* synthetic */ Object searchSongs$default(NeteaseSearchClient neteaseSearchClient, String str, int i, Continuation continuation, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 30;
        }
        return neteaseSearchClient.searchSongs(str, i, continuation);
    }

    /* JADX INFO: renamed from: com.lladlam.melox.core.network.NeteaseSearchClient$searchSongs$2 */
    /* JADX INFO: compiled from: NeteaseSearchClient.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/lladlam/melox/core/model/SearchSong;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lladlam.melox.core.network.NeteaseSearchClient$searchSongs$2", f = "NeteaseSearchClient.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C26162 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends SearchSong>>, Object> {
        final /* synthetic */ String $keywords;
        final /* synthetic */ int $limit;
        int label;
        final /* synthetic */ NeteaseSearchClient this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C26162(String str, int i, NeteaseSearchClient neteaseSearchClient, Continuation<? super C26162> continuation) {
            super(2, continuation);
            this.$keywords = str;
            this.$limit = i;
            this.this$0 = neteaseSearchClient;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C26162(this.$keywords, this.$limit, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends SearchSong>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<SearchSong>>) continuation);
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<SearchSong>> continuation) {
            return ((C26162) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) throws JSONException {
            JSONObject response;
            String strOptString;
            String strOptString2;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    String query = StringsKt.trim((CharSequence) this.$keywords).toString();
                    if (query.length() == 0) {
                        return CollectionsKt.emptyList();
                    }
                    JSONObject payload = new JSONObject().put(CmcdData.STREAMING_FORMAT_SS, query).put(ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, 1).put("limit", RangesKt.coerceIn(this.$limit, 1, 50)).put("offset", 0);
                    NeteaseSearchClient neteaseSearchClient = this.this$0;
                    Intrinsics.checkNotNull(payload);
                    JSONObject response2 = NeteaseSearchClient.eapi$default(neteaseSearchClient, "/api/search/get", payload, null, null, 12, null);
                    JSONObject result = response2.optJSONObject("result");
                    if (result == null) {
                        return CollectionsKt.emptyList();
                    }
                    JSONArray songs = result.optJSONArray("songs");
                    if (songs == null) {
                        songs = new JSONArray();
                    }
                    NeteaseSearchClient neteaseSearchClient2 = this.this$0;
                    List listCreateListBuilder = CollectionsKt.createListBuilder();
                    int i = 0;
                    int length = songs.length();
                    while (i < length) {
                        JSONObject jSONObjectOptJSONObject = songs.optJSONObject(i);
                        if (jSONObjectOptJSONObject == null) {
                            response = response2;
                        } else {
                            long jOptLong = jSONObjectOptJSONObject.optLong(TtmlNode.ATTR_ID, -1L);
                            if (jOptLong > 0) {
                                JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("ar");
                                if (jSONArrayOptJSONArray == null && (jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("artists")) == null) {
                                    jSONArrayOptJSONArray = new JSONArray();
                                }
                                List listCreateListBuilder2 = CollectionsKt.createListBuilder();
                                int length2 = jSONArrayOptJSONArray.length();
                                int i2 = 0;
                                while (true) {
                                    response = response2;
                                    if (i2 < length2) {
                                        int i3 = length2;
                                        JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray.optJSONObject(i2);
                                        if (jSONObjectOptJSONObject2 != null && (strOptString2 = jSONObjectOptJSONObject2.optString(HintConstants.AUTOFILL_HINT_NAME)) != null) {
                                            strOptString = StringsKt.isBlank(strOptString2) ? null : strOptString2;
                                            if (strOptString != null) {
                                                Boxing.boxBoolean(listCreateListBuilder2.add(strOptString));
                                            }
                                        }
                                        i2++;
                                        length2 = i3;
                                        response2 = response;
                                    } else {
                                        String strJoinToString$default = CollectionsKt.joinToString$default(CollectionsKt.build(listCreateListBuilder2), " / ", null, null, 0, null, null, 62, null);
                                        JSONObject jSONObjectOptJSONObject3 = jSONObjectOptJSONObject.optJSONObject("al");
                                        if (jSONObjectOptJSONObject3 == null) {
                                            jSONObjectOptJSONObject3 = jSONObjectOptJSONObject.optJSONObject("album");
                                        }
                                        strOptString = jSONObjectOptJSONObject3 != null ? jSONObjectOptJSONObject3.optString(HintConstants.AUTOFILL_HINT_NAME) : null;
                                        if (strOptString == null) {
                                            strOptString = "";
                                        }
                                        String str = strOptString;
                                        String strArtworkFromAlbum = neteaseSearchClient2.artworkFromAlbum(jSONObjectOptJSONObject3);
                                        String strOptString3 = jSONObjectOptJSONObject.optString(HintConstants.AUTOFILL_HINT_NAME, "未知歌曲");
                                        Intrinsics.checkNotNullExpressionValue(strOptString3, "optString(...)");
                                        String str2 = strJoinToString$default;
                                        if (StringsKt.isBlank(str2)) {
                                            str2 = "未知歌手";
                                        }
                                        listCreateListBuilder.add(new SearchSong(jOptLong, strOptString3, str2, str, strArtworkFromAlbum, 0L, 32, null));
                                    }
                                }
                            } else {
                                response = response2;
                            }
                        }
                        i++;
                        query = query;
                        response2 = response;
                    }
                    return CollectionsKt.build(listCreateListBuilder);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object searchSongs(String keywords, int limit, Continuation<? super List<SearchSong>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C26162(keywords, limit, this, null), continuation);
    }

    /* JADX INFO: renamed from: com.lladlam.melox.core.network.NeteaseSearchClient$ensureArtwork$2 */
    /* JADX INFO: compiled from: NeteaseSearchClient.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/lladlam/melox/core/model/SearchSong;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lladlam.melox.core.network.NeteaseSearchClient$ensureArtwork$2", f = "NeteaseSearchClient.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C26122 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends SearchSong>>, Object> {
        final /* synthetic */ List<SearchSong> $songs;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ NeteaseSearchClient this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C26122(List<SearchSong> list, NeteaseSearchClient neteaseSearchClient, Continuation<? super C26122> continuation) {
            super(2, continuation);
            this.$songs = list;
            this.this$0 = neteaseSearchClient;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C26122 c26122 = new C26122(this.$songs, this.this$0, continuation);
            c26122.L$0 = obj;
            return c26122;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends SearchSong>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<SearchSong>>) continuation);
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<SearchSong>> continuation) {
            return ((C26122) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:70:0x01d2  */
        /* JADX WARN: Code duplicated, block: B:85:? A[RETURN, SYNTHETIC] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object objM9714constructorimpl;
            List<SearchSong> list;
            Collection collection;
            String str;
            SearchSong searchSongCopy$default;
            JSONObject jSONObject;
            JSONArray jSONArray;
            JSONArray jSONArray2;
            CoroutineScope $this$withContext = (CoroutineScope) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    boolean zIsEmpty = this.$songs.isEmpty();
                    List<SearchSong> list2 = this.$songs;
                    if (zIsEmpty) {
                        return list2;
                    }
                    List missingIds = SequencesKt.toList(SequencesKt.distinct(SequencesKt.filter(SequencesKt.map(SequencesKt.filter(CollectionsKt.asSequence(list2), new Function1() { // from class: com.lladlam.melox.core.network.NeteaseSearchClient$ensureArtwork$2$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return Boolean.valueOf(NeteaseSearchClient.C26122.invokeSuspend$lambda$0((SearchSong) obj));
                        }
                    }), new Function1() { // from class: com.lladlam.melox.core.network.NeteaseSearchClient$ensureArtwork$2$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return Long.valueOf(((SearchSong) obj).getId());
                        }
                    }), new Function1() { // from class: com.lladlam.melox.core.network.NeteaseSearchClient$ensureArtwork$2$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return Boolean.valueOf(NeteaseSearchClient.C26122.invokeSuspend$lambda$2(((Long) obj).longValue()));
                        }
                    })));
                    if (missingIds.isEmpty()) {
                        return this.$songs;
                    }
                    NeteaseSearchClient neteaseSearchClient = this.this$0;
                    Collection collection2 = this.$songs;
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        JSONArray jSONArray3 = new JSONArray();
                        Iterator it = missingIds.iterator();
                        while (it.hasNext()) {
                            Collection collection3 = collection2;
                            CoroutineScope $this$withContext2 = $this$withContext;
                            List missingIds2 = missingIds;
                            try {
                                jSONArray3.put(new JSONObject().put(TtmlNode.ATTR_ID, ((Number) it.next()).longValue()));
                                collection2 = collection3;
                                $this$withContext = $this$withContext2;
                                missingIds = missingIds2;
                            } catch (Throwable th) {
                                th = th;
                                Result.Companion companion2 = Result.INSTANCE;
                                objM9714constructorimpl = Result.constructor_impl(ResultKt.createFailure(th));
                                list = this.$songs;
                                if (Result.isFailure_impl(objM9714constructorimpl)) {
                                    return list;
                                }
                                return objM9714constructorimpl;
                            }
                        }
                        Collection collection4 = collection2;
                        JSONArray jSONArray4 = jSONArray3;
                        JSONObject jSONObjectPut = new JSONObject().put("c", jSONArray4.toString());
                        Intrinsics.checkNotNullExpressionValue(jSONObjectPut, "put(...)");
                        JSONObject jSONObjectEapi$default = NeteaseSearchClient.eapi$default(neteaseSearchClient, "/api/v3/song/detail", jSONObjectPut, null, null, 12, null);
                        JSONArray jSONArrayOptJSONArray = jSONObjectEapi$default.optJSONArray("songs");
                        if (jSONArrayOptJSONArray == null) {
                            collection = collection4;
                        } else {
                            Map mapCreateMapBuilder = MapsKt.createMapBuilder();
                            int i = 0;
                            int length = jSONArrayOptJSONArray.length();
                            while (i < length) {
                                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                                if (jSONObjectOptJSONObject == null) {
                                    jSONArray2 = jSONArray4;
                                    jSONObject = jSONObjectEapi$default;
                                    jSONArray = jSONArrayOptJSONArray;
                                } else {
                                    jSONObject = jSONObjectEapi$default;
                                    jSONArray = jSONArrayOptJSONArray;
                                    long jOptLong = jSONObjectOptJSONObject.optLong(TtmlNode.ATTR_ID, -1L);
                                    if (jOptLong <= 0) {
                                        jSONArray2 = jSONArray4;
                                    } else {
                                        jSONArray2 = jSONArray4;
                                        JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("al");
                                        if (jSONObjectOptJSONObject2 == null) {
                                            jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("album");
                                        }
                                        String strArtworkFromAlbum = neteaseSearchClient.artworkFromAlbum(jSONObjectOptJSONObject2);
                                        if (strArtworkFromAlbum != null) {
                                        }
                                    }
                                }
                                i++;
                                jSONObjectEapi$default = jSONObject;
                                jSONArrayOptJSONArray = jSONArray;
                                jSONArray4 = jSONArray2;
                            }
                            Map mapBuild = MapsKt.build(mapCreateMapBuilder);
                            Collection<SearchSong> collection5 = collection4;
                            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection5, 10));
                            for (SearchSong searchSong : collection5) {
                                String artworkUrl = searchSong.getArtworkUrl();
                                if (!(artworkUrl == null || StringsKt.isBlank(artworkUrl)) || (str = (String) mapBuild.get(Boxing.boxLong(searchSong.getId()))) == null || (searchSongCopy$default = SearchSong.copy$default(searchSong, 0L, null, null, null, str, 0L, 47, null)) == null) {
                                    searchSongCopy$default = searchSong;
                                }
                                arrayList.add(searchSongCopy$default);
                            }
                            collection = (List) arrayList;
                        }
                        objM9714constructorimpl = Result.constructor_impl(collection);
                        break;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                    list = this.$songs;
                    if (Result.isFailure_impl(objM9714constructorimpl)) {
                        return list;
                    }
                    return objM9714constructorimpl;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        static final boolean invokeSuspend$lambda$0(SearchSong it) {
            String artworkUrl = it.getArtworkUrl();
            return artworkUrl == null || StringsKt.isBlank(artworkUrl);
        }

        static final boolean invokeSuspend$lambda$2(long it) {
            return it > 0;
        }
    }

    public final Object ensureArtwork(List<SearchSong> list, Continuation<? super List<SearchSong>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C26122(list, this, null), continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object ensureArtwork(SearchSong song, Continuation<? super SearchSong> continuation) {
        C26133 c26133;
        Object objEnsureArtwork;
        if (continuation instanceof C26133) {
            c26133 = (C26133) continuation;
            if ((c26133.label & Integer.MIN_VALUE) != 0) {
                c26133.label -= Integer.MIN_VALUE;
            } else {
                c26133 = new C26133(continuation);
            }
        } else {
            c26133 = new C26133(continuation);
        }
        Object $result = c26133.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c26133.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                List<SearchSong> listListOf = CollectionsKt.listOf(song);
                c26133.L$0 = song;
                c26133.label = 1;
                objEnsureArtwork = ensureArtwork(listListOf, c26133);
                if (objEnsureArtwork == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                song = (SearchSong) c26133.L$0;
                ResultKt.throwOnFailure($result);
                objEnsureArtwork = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        SearchSong searchSong = (SearchSong) CollectionsKt.firstOrNull((List) objEnsureArtwork);
        return searchSong == null ? song : searchSong;
    }

    /* JADX INFO: renamed from: com.lladlam.melox.core.network.NeteaseSearchClient$lyrics$2 */
    /* JADX INFO: compiled from: NeteaseSearchClient.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/lladlam/melox/core/lyrics/LyricsDocument;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lladlam.melox.core.network.NeteaseSearchClient$lyrics$2", f = "NeteaseSearchClient.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C26142 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super LyricsDocument>, Object> {
        final /* synthetic */ long $songId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C26142(long j, Continuation<? super C26142> continuation) {
            super(2, continuation);
            this.$songId = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return NeteaseSearchClient.this.new C26142(this.$songId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super LyricsDocument> continuation) {
            return ((C26142) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) throws JSONException {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    NeteaseSearchClient neteaseSearchClient = NeteaseSearchClient.this;
                    JSONObject jSONObjectPut = new JSONObject().put(TtmlNode.ATTR_ID, this.$songId).put("cp", false).put("tv", 0).put("lv", 0).put("rv", 0).put("kv", 0).put("yv", 0).put("ytv", 0).put("yrv", 0);
                    Intrinsics.checkNotNullExpressionValue(jSONObjectPut, "put(...)");
                    JSONObject response = NeteaseSearchClient.eapi$default(neteaseSearchClient, "/api/song/lyric/v1", jSONObjectPut, null, null, 12, null);
                    NeteaseLyricParser neteaseLyricParser = NeteaseLyricParser.INSTANCE;
                    JSONObject jSONObjectOptJSONObject = response.optJSONObject("yrc");
                    String strOptString = jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.optString("lyric") : null;
                    if (strOptString == null) {
                        strOptString = "";
                    }
                    JSONObject jSONObjectOptJSONObject2 = response.optJSONObject("lrc");
                    String strOptString2 = jSONObjectOptJSONObject2 != null ? jSONObjectOptJSONObject2.optString("lyric") : null;
                    if (strOptString2 == null) {
                        strOptString2 = "";
                    }
                    JSONObject jSONObjectOptJSONObject3 = response.optJSONObject("ytlrc");
                    String strOptString3 = jSONObjectOptJSONObject3 != null ? jSONObjectOptJSONObject3.optString("lyric") : null;
                    if (strOptString3 == null) {
                        strOptString3 = "";
                    }
                    JSONObject jSONObjectOptJSONObject4 = response.optJSONObject("tlyric");
                    String strOptString4 = jSONObjectOptJSONObject4 != null ? jSONObjectOptJSONObject4.optString("lyric") : null;
                    if (strOptString4 == null) {
                        strOptString4 = "";
                    }
                    JSONObject jSONObjectOptJSONObject5 = response.optJSONObject("yromalrc");
                    String strOptString5 = jSONObjectOptJSONObject5 != null ? jSONObjectOptJSONObject5.optString("lyric") : null;
                    if (strOptString5 == null) {
                        strOptString5 = "";
                    }
                    JSONObject jSONObjectOptJSONObject6 = response.optJSONObject("romalrc");
                    String strOptString6 = jSONObjectOptJSONObject6 != null ? jSONObjectOptJSONObject6.optString("lyric") : null;
                    return neteaseLyricParser.parse(strOptString, strOptString2, strOptString3, strOptString4, strOptString5, strOptString6 != null ? strOptString6 : "");
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object lyrics(long songId, Continuation<? super LyricsDocument> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C26142(songId, null), continuation);
    }

    /* JADX INFO: renamed from: com.lladlam.melox.core.network.NeteaseSearchClient$playbackUrl$2 */
    /* JADX INFO: compiled from: NeteaseSearchClient.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lladlam.melox.core.network.NeteaseSearchClient$playbackUrl$2", f = "NeteaseSearchClient.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C26152 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
        final /* synthetic */ long $songId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C26152(long j, Continuation<? super C26152> continuation) {
            super(2, continuation);
            this.$songId = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return NeteaseSearchClient.this.new C26152(this.$songId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
            return ((C26152) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    return NeteaseSearchClient.this.playbackUrlBlocking$app(this.$songId);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object playbackUrl(long songId, Continuation<? super String> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C26152(songId, null), continuation);
    }

    public final String playbackUrlBlocking$app(long songId) throws Exception {
        String currentCookie = this.cookieProvider.invoke();
        boolean loggedIn = NeteaseSessionStore.INSTANCE.containsMusicU(currentCookie);
        try {
            JSONObject payload = new JSONObject().put("ids", "[" + songId + "]").put("level", "standard").put("encodeType", "flac");
            Intrinsics.checkNotNull(payload);
            JSONObject response = eapi("/api/song/enhance/player/url/v1", payload, Boolean.valueOf(loggedIn), !StringsKt.isBlank(currentCookie) ? currentCookie : null);
            JSONArray sources = response.optJSONArray("data");
            if (sources == null) {
                sources = new JSONArray();
            }
            int length = sources.length();
            for (int index = 0; index < length; index++) {
                JSONObject source = sources.optJSONObject(index);
                if (source != null && source.optLong(TtmlNode.ATTR_ID, -1L) == songId) {
                    String rawUrl = source.optString("url");
                    if (StringsKt.isBlank(rawUrl)) {
                        rawUrl = null;
                    }
                    if (rawUrl != null) {
                        return secureUrl(rawUrl);
                    }
                }
            }
            if (loggedIn) {
                throw new IOException("网易云登录态未返回可播放链接，可能是账号权限或版权限制");
            }
            return "https://music.163.com/song/media/outer/url?id=" + songId;
        } catch (Exception error) {
            if (loggedIn) {
                if (error instanceof IOException) {
                    throw error;
                }
                throw new IOException("登录态播放链接获取失败", error);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String artworkFromAlbum(JSONObject albumObject) {
        String strOptString;
        String strOptString2;
        String strSecureUrl;
        if (albumObject != null && (strOptString2 = albumObject.optString("picUrl")) != null) {
            if (StringsKt.isBlank(strOptString2)) {
                strOptString2 = null;
            }
            if (strOptString2 != null && (strSecureUrl = secureUrl(strOptString2)) != null) {
                return strSecureUrl;
            }
        }
        if (albumObject == null || (strOptString = albumObject.optString("blurPicUrl")) == null) {
            return null;
        }
        if (StringsKt.isBlank(strOptString)) {
            strOptString = null;
        }
        if (strOptString != null) {
            return secureUrl(strOptString);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String secureUrl(String url) {
        if (StringsKt.startsWith(url, "http://", true)) {
            return "https://" + StringsKt.substringAfter$default(url, "://", (String) null, 2, (Object) null);
        }
        return url;
    }

    static /* synthetic */ JSONObject eapi$default(NeteaseSearchClient neteaseSearchClient, String str, JSONObject jSONObject, Boolean bool, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            bool = null;
        }
        if ((i & 8) != 0) {
            str2 = null;
        }
        return neteaseSearchClient.eapi(str, jSONObject, bool, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:42:0x01f2  */
    /* JADX WARN: Multi-variable type inference failed */
    public final JSONObject eapi(String uri, JSONObject data, Boolean authenticated, String cookieHeaderOverride) throws IOException {
        Throwable th;
        String str;
        long jCurrentTimeMillis = System.currentTimeMillis();
        String strInvoke = cookieHeaderOverride == null ? this.cookieProvider.invoke() : cookieHeaderOverride;
        Map<String, String> cookie = NeteaseSessionStore.INSTANCE.parseCookie(strInvoke);
        boolean zBooleanValue = authenticated != null ? authenticated.booleanValue() : NeteaseSessionStore.INSTANCE.containsMusicU(strInvoke);
        JSONObject jSONObjectAuthenticatedEapiHeader = zBooleanValue ? authenticatedEapiHeader(cookie, jCurrentTimeMillis) : new JSONObject().put("os", "ios").put("appver", "9.0.90").put("osver", "18.0").put("buildver", String.valueOf(jCurrentTimeMillis / 1000)).put("channel", "distribution").put("requestId", jCurrentTimeMillis + "_0000").put("__csrf", "");
        String string = new JSONObject(data.toString()).put("header", jSONObjectAuthenticatedEapiHeader).put("e_r", false).toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        byte[] bytes = (uri + "-36cd479b6b5-" + string + "-36cd479b6b5-" + md5Hex("nobody" + uri + "use" + string + "md5forencrypt")).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bytes2 = "e82ckenh8dichen8".getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
        String hexUppercase = toHexUppercase(aesEcbEncrypt(bytes, bytes2));
        Request.Builder builderHeader = new Request.Builder().url("https://interface.music.163.com" + StringsKt.replace$default(uri, "/api/", "/eapi/", false, 4, (Object) null)).header(HttpHeaders.USER_AGENT, zBooleanValue ? "NeteaseMusic 9.0.90/5038 (iPhone; iOS 16.2; zh_CN)" : "Mozilla/5.0 (iPhone; CPU iPhone OS 18_0 like Mac OS X) AppleWebKit/605.1.15 Mobile/15E148").header(HttpHeaders.ACCEPT, "*/*");
        if (zBooleanValue) {
            Intrinsics.checkNotNull(jSONObjectAuthenticatedEapiHeader);
            builderHeader.header(HttpHeaders.COOKIE, encodedCookieHeader(jSONObjectAuthenticatedEapiHeader));
        }
        Response responseExecute = this.httpClient.newCall(builderHeader.post(new FormBody.Builder(null, 1, 0 == true ? 1 : 0).add("params", hexUppercase).build()).build()).execute();
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
                if (!StringsKt.isBlank(strOptString)) {
                    str = strOptString;
                    if (StringsKt.isBlank(str)) {
                        str = "请求失败";
                    }
                    throw new IOException("网易云请求失败（" + iOptInt + "）：" + str);
                }
                try {
                    strOptString = jSONObject.optString(NotificationCompat.CATEGORY_MESSAGE);
                    str = strOptString;
                    if (StringsKt.isBlank(str)) {
                        str = "请求失败";
                    }
                    try {
                        throw new IOException("网易云请求失败（" + iOptInt + "）：" + str);
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (Throwable th5) {
            th = th5;
        }
        try {
            throw th;
        } catch (Throwable th6) {
            CloseableKt.closeFinally(responseExecute, th);
            throw th6;
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
        return CollectionsKt.joinToString$default(keys, "; ", null, null, 0, null, new Function1() { // from class: com.lladlam.melox.core.network.NeteaseSearchClient$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NeteaseSearchClient.encodedCookieHeader$lambda$1(this.f$0, values, (String) obj);
            }
        }, 30, null);
    }

    static final CharSequence encodedCookieHeader$lambda$1(NeteaseSearchClient this$0, JSONObject $values, String key) throws UnsupportedEncodingException {
        Intrinsics.checkNotNull(key);
        String strEncodeURIComponent = this$0.encodeURIComponent(key);
        String strOptString = $values.optString(key);
        Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
        return strEncodeURIComponent + "=" + this$0.encodeURIComponent(strOptString);
    }

    private final String encodeURIComponent(String value) throws UnsupportedEncodingException {
        String strEncode = URLEncoder.encode(value, Charsets.UTF_8.name());
        Intrinsics.checkNotNullExpressionValue(strEncode, "encode(...)");
        return StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(strEncode, "+", "%20", false, 4, (Object) null), "%21", "!", false, 4, (Object) null), "%27", "'", false, 4, (Object) null), "%28", "(", false, 4, (Object) null), "%29", ")", false, 4, (Object) null), "%7E", "~", false, 4, (Object) null);
    }

    private final String randomHex(int byteCount) {
        byte[] bytes = new byte[byteCount];
        new SecureRandom().nextBytes(bytes);
        return ArraysKt.joinToString$default(bytes, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.lladlam.melox.core.network.NeteaseSearchClient$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NeteaseSearchClient.randomHex$lambda$0(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    static final CharSequence randomHex$lambda$0(byte b) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
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
        return ArraysKt.joinToString$default(bArrDigest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.lladlam.melox.core.network.NeteaseSearchClient$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NeteaseSearchClient.md5Hex$lambda$0(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    static final CharSequence md5Hex$lambda$0(byte b) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
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
        return ArraysKt.joinToString$default($this$toHexUppercase, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.lladlam.melox.core.network.NeteaseSearchClient$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NeteaseSearchClient.toHexUppercase$lambda$0(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    static final CharSequence toHexUppercase$lambda$0(byte b) {
        String str = String.format("%02X", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}
