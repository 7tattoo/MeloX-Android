package com.lladlam.melox.core.audio;

import androidx.core.app.NotificationCompat;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import com.google.common.net.HttpHeaders;
import com.lladlam.melox.core.account.NeteaseSessionStore;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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

/* JADX INFO: compiled from: NeteaseQualityClient.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B!\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013J\u0010\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J5\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00162\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0004H\u0002¢\u0006\u0002\u0010\u001dJ$\u0010\u001e\u001a\u00020\u00162\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040 2\u0006\u0010!\u001a\u00020\rH\u0002J\u0010\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0016H\u0002J\u0010\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u0004H\u0002J\u0010\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u0004H\u0002J\u0010\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u0010+\u001a\u00020\u00042\u0006\u0010,\u001a\u00020*H\u0002J\u0010\u0010-\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u0004H\u0002J\u0018\u0010.\u001a\u00020/2\u0006\u0010\u0019\u001a\u00020/2\u0006\u00100\u001a\u00020/H\u0002J\f\u00101\u001a\u00020\u0004*\u00020/H\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lcom/lladlam/melox/core/audio/NeteaseQualityClient;", "", "cookieProvider", "Lkotlin/Function0;", "", "httpClient", "Lokhttp3/OkHttpClient;", "<init>", "(Lkotlin/jvm/functions/Function0;Lokhttp3/OkHttpClient;)V", "syntheticDeviceId", "audioAvailability", "Lcom/lladlam/melox/core/audio/SongAudioAvailability;", "songId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "audioAvailabilityBlocking", "playbackSourceBlocking", "Lcom/lladlam/melox/core/audio/NeteasePlaybackSource;", "requestedQuality", "Lcom/lladlam/melox/core/audio/MusicQuality;", "parseAvailability", "song", "Lorg/json/JSONObject;", "eapi", "uri", "data", "authenticated", "", "cookieHeaderOverride", "(Ljava/lang/String;Lorg/json/JSONObject;Ljava/lang/Boolean;Ljava/lang/String;)Lorg/json/JSONObject;", "authenticatedEapiHeader", "cookies", "", "timestampMillis", "encodedCookieHeader", "values", "secureUrl", "url", "encodeURIComponent", "value", "randomHex", "byteCount", "", "randomDigits", "length", "md5Hex", "aesEcbEncrypt", "", "key", "toHexUppercase", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class NeteaseQualityClient {
    public static final int $stable = 8;
    private final Function0<String> cookieProvider;
    private final OkHttpClient httpClient;
    private final String syntheticDeviceId;

    /* JADX WARN: Multi-variable type inference failed */
    public NeteaseQualityClient() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public NeteaseQualityClient(Function0<String> cookieProvider, OkHttpClient httpClient) {
        Intrinsics.checkNotNullParameter(cookieProvider, "cookieProvider");
        Intrinsics.checkNotNullParameter(httpClient, "httpClient");
        this.cookieProvider = cookieProvider;
        this.httpClient = httpClient;
        String upperCase = randomHex(26).toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        this.syntheticDeviceId = upperCase;
    }

    public /* synthetic */ NeteaseQualityClient(Function0 function0, OkHttpClient okHttpClient, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new Function0() { // from class: com.lladlam.melox.core.audio.NeteaseQualityClient$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NeteaseQualityClient._init_$lambda$0();
            }
        } : function0, (i & 2) != 0 ? new OkHttpClient() : okHttpClient);
    }

    static final String _init_$lambda$0() {
        return "";
    }

    /* JADX INFO: renamed from: com.lladlam.melox.core.audio.NeteaseQualityClient$audioAvailability$2 */
    /* JADX INFO: compiled from: NeteaseQualityClient.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/lladlam/melox/core/audio/SongAudioAvailability;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(m719c = "com.lladlam.melox.core.audio.NeteaseQualityClient$audioAvailability$2", m720f = "NeteaseQualityClient.kt", m721i = {}, m722l = {}, m723m = "invokeSuspend", m724n = {}, m725nl = {}, m726s = {}, m727v = 2)
    static final class C26002 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SongAudioAvailability>, Object> {
        final /* synthetic */ long $songId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C26002(long j, Continuation<? super C26002> continuation) {
            super(2, continuation);
            this.$songId = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return NeteaseQualityClient.this.new C26002(this.$songId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super SongAudioAvailability> continuation) {
            return ((C26002) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    return NeteaseQualityClient.this.audioAvailabilityBlocking(this.$songId);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object audioAvailability(long songId, Continuation<? super SongAudioAvailability> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C26002(songId, null), continuation);
    }

    public final SongAudioAvailability audioAvailabilityBlocking(long songId) {
        Object objM9714constructorimpl;
        SongAudioAvailability unknown;
        JSONObject jSONObjectOptJSONObject;
        if (songId <= 0) {
            return SongAudioAvailability.INSTANCE.getUnknown();
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            NeteaseQualityClient neteaseQualityClient = this;
            JSONObject jSONObjectPut = new JSONObject().put("c", new JSONArray().put(new JSONObject().put(TtmlNode.ATTR_ID, songId)).toString());
            Intrinsics.checkNotNullExpressionValue(jSONObjectPut, "put(...)");
            JSONArray jSONArrayOptJSONArray = eapi$default(neteaseQualityClient, "/api/v3/song/detail", jSONObjectPut, null, null, 12, null).optJSONArray("songs");
            if (jSONArrayOptJSONArray == null || (jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(0)) == null) {
                unknown = SongAudioAvailability.INSTANCE.getUnknown();
            } else {
                unknown = neteaseQualityClient.parseAvailability(jSONObjectOptJSONObject);
            }
            objM9714constructorimpl = Result.constructor_impl(unknown);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM9714constructorimpl = Result.constructor_impl(ResultKt.createFailure(th));
        }
        SongAudioAvailability unknown2 = SongAudioAvailability.INSTANCE.getUnknown();
        if (Result.isFailure_impl(objM9714constructorimpl)) {
            objM9714constructorimpl = unknown2;
        }
        return (SongAudioAvailability) objM9714constructorimpl;
    }

    public final NeteasePlaybackSource playbackSourceBlocking(long songId, MusicQuality requestedQuality) throws IOException {
        MusicQuality candidate;
        String currentCookie;
        boolean loggedIn;
        Object next;
        Intrinsics.checkNotNullParameter(requestedQuality, "requestedQuality");
        String currentCookie2 = this.cookieProvider.invoke();
        boolean loggedIn2 = NeteaseSessionStore.INSTANCE.containsMusicU(currentCookie2);
        SongAudioAvailability availability = audioAvailabilityBlocking(songId);
        Iterator<MusicQuality> it = requestedQuality.playbackCandidates(availability).iterator();
        Throwable lastError = null;
        loop0: while (true) {
            if (!it.hasNext()) {
                if (loggedIn2) {
                    throw new IOException("网易云登录态未返回可播放的 " + requestedQuality.getTitle() + " 音源，且 MeloX 降级链路也没有可用资源", lastError);
                }
                MusicQualityRuntime.INSTANCE.recordActual(songId, MusicQuality.Standard);
                return new NeteasePlaybackSource("https://music.163.com/song/media/outer/url?id=" + songId, null, "mp3", MusicQuality.Standard);
            }
            candidate = it.next();
            try {
                JSONObject payload = new JSONObject().put("ids", "[" + songId + "]").put("level", candidate.getApiLevel()).put("encodeType", "flac");
                if (candidate.getRequiresImmersiveType()) {
                    try {
                        payload.put("immerseType", "c51");
                    } catch (Throwable th) {
                        error = th;
                        currentCookie = currentCookie2;
                        loggedIn = loggedIn2;
                    }
                }
                Intrinsics.checkNotNull(payload);
                JSONObject response = eapi("/api/song/enhance/player/url/v1", payload, Boolean.valueOf(loggedIn2), !StringsKt.isBlank(currentCookie2) ? currentCookie2 : null);
                JSONArray sources = response.optJSONArray("data");
                if (sources == null) {
                    sources = new JSONArray();
                }
                Iterator it2 = SequencesKt.mapNotNull(CollectionsKt.asSequence(RangesKt.until(0, sources.length())), new NeteaseQualityClient$playbackSourceBlocking$source$1(sources)).iterator();
                while (it2.hasNext()) {
                    next = it2.next();
                    JSONObject payload2 = payload;
                    currentCookie = currentCookie2;
                    loggedIn = loggedIn2;
                    try {
                        if (((JSONObject) next).optLong(TtmlNode.ATTR_ID, -1L) == songId) {
                            break loop0;
                        }
                        payload = payload2;
                        currentCookie2 = currentCookie;
                        loggedIn2 = loggedIn;
                    } catch (Throwable th2) {
                        error = th2;
                    }
                }
                next = null;
                break loop0;
            } catch (Throwable th3) {
                error = th3;
                currentCookie = currentCookie2;
                loggedIn = loggedIn2;
            }
            lastError = error;
            currentCookie2 = currentCookie;
            loggedIn2 = loggedIn;
        }
        JSONObject source = (JSONObject) next;
        if (source == null) {
            throw new IOException("no source for " + candidate.getApiLevel());
        }
        String rawUrl = source.optString("url");
        if (StringsKt.isBlank(rawUrl)) {
            rawUrl = null;
        }
        if (rawUrl == null) {
            throw new IOException("no URL for " + candidate.getApiLevel());
        }
        MusicQuality.Companion companion = MusicQuality.INSTANCE;
        String strOptString = source.optString("level");
        if (StringsKt.isBlank(strOptString)) {
            strOptString = null;
        }
        MusicQuality actual = companion.fromApiLevel(strOptString);
        if (actual == null) {
            actual = candidate;
        }
        MusicQualityRuntime.INSTANCE.recordActual(songId, actual);
        String strSecureUrl = secureUrl(rawUrl);
        Integer numValueOf = Integer.valueOf(source.optInt("br"));
        if (!(numValueOf.intValue() > 0)) {
            numValueOf = null;
        }
        String strOptString2 = source.optString(ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY);
        if (StringsKt.isBlank(strOptString2)) {
            strOptString2 = null;
        }
        return new NeteasePlaybackSource(strSecureUrl, numValueOf, strOptString2, actual);
    }

    private static final SongAudioResource parseAvailability$resource(JSONObject $song, String key) {
        JSONObject value = $song.optJSONObject(key);
        if (value == null) {
            return null;
        }
        Integer numValueOf = Integer.valueOf(value.optInt("br"));
        if ((numValueOf.intValue() > 0 ? 1 : 0) == 0) {
            numValueOf = null;
        }
        Integer numValueOf2 = Integer.valueOf(value.optInt("sr"));
        if ((numValueOf2.intValue() > 0 ? 1 : 0) == 0) {
            numValueOf2 = null;
        }
        Long lValueOf = Long.valueOf(value.optLong("size"));
        return new SongAudioResource(numValueOf, numValueOf2, lValueOf.longValue() > 0 ? lValueOf : null);
    }

    private final SongAudioAvailability parseAvailability(JSONObject song) {
        boolean z;
        Iterable keys = CollectionsKt.listOf((Object[]) new String[]{CmcdData.STREAM_TYPE_LIVE, CmcdData.OBJECT_TYPE_MANIFEST, CmcdData.STREAMING_FORMAT_HLS, "sq", "hr", "je", "sk", "jm"});
        SongAudioResource availability$resource = parseAvailability$resource(song, CmcdData.STREAM_TYPE_LIVE);
        SongAudioResource availability$resource2 = parseAvailability$resource(song, CmcdData.OBJECT_TYPE_MANIFEST);
        SongAudioResource availability$resource3 = parseAvailability$resource(song, CmcdData.STREAMING_FORMAT_HLS);
        SongAudioResource availability$resource4 = parseAvailability$resource(song, "sq");
        SongAudioResource availability$resource5 = parseAvailability$resource(song, "hr");
        SongAudioResource availability$resource6 = parseAvailability$resource(song, "je");
        SongAudioResource availability$resource7 = parseAvailability$resource(song, "sk");
        SongAudioResource availability$resource8 = parseAvailability$resource(song, "jm");
        Iterable iterable = keys;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            z = false;
        } else {
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                if (song.has((String) it.next())) {
                    z = true;
                }
            }
            z = false;
        }
        return new SongAudioAvailability(availability$resource, availability$resource2, availability$resource3, availability$resource4, availability$resource5, availability$resource6, availability$resource7, availability$resource8, z);
    }

    static /* synthetic */ JSONObject eapi$default(NeteaseQualityClient neteaseQualityClient, String str, JSONObject jSONObject, Boolean bool, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            bool = null;
        }
        if ((i & 8) != 0) {
            str2 = null;
        }
        return neteaseQualityClient.eapi(str, jSONObject, bool, str2);
    }

    /* JADX WARN: Code duplicated, block: B:42:0x01f2  */
    private final JSONObject eapi(String uri, JSONObject data, Boolean authenticated, String cookieHeaderOverride) throws JSONException, NoSuchAlgorithmException, IOException {
        Throwable th;
        String str;
        long timestampMillis = System.currentTimeMillis();
        String cookieHeader = cookieHeaderOverride == null ? this.cookieProvider.invoke() : cookieHeaderOverride;
        Map<String, String> cookie = NeteaseSessionStore.INSTANCE.parseCookie(cookieHeader);
        boolean useAuthenticatedSession = authenticated != null ? authenticated.booleanValue() : NeteaseSessionStore.INSTANCE.containsMusicU(cookieHeader);
        JSONObject header = useAuthenticatedSession ? authenticatedEapiHeader(cookie, timestampMillis) : new JSONObject().put("os", "ios").put("appver", "9.0.90").put("osver", "18.0").put("buildver", String.valueOf(timestampMillis / 1000)).put("channel", "distribution").put("requestId", timestampMillis + "_0000").put("__csrf", "");
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
        String path = StringsKt.replace$default(uri, "/api/", "/eapi/", false, 4, (Object) null);
        Request.Builder requestBuilder = new Request.Builder().url("https://interface.music.163.com" + path).header(HttpHeaders.USER_AGENT, useAuthenticatedSession ? "NeteaseMusic 9.0.90/5038 (iPhone; iOS 16.2; zh_CN)" : "Mozilla/5.0 (iPhone; CPU iPhone OS 18_0 like Mac OS X) AppleWebKit/605.1.15 Mobile/15E148").header(HttpHeaders.ACCEPT, "*/*");
        if (useAuthenticatedSession) {
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
        return CollectionsKt.joinToString$default(keys, "; ", null, null, 0, null, new Function1() { // from class: com.lladlam.melox.core.audio.NeteaseQualityClient$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NeteaseQualityClient.encodedCookieHeader$lambda$1(this.f$0, values, (String) obj);
            }
        }, 30, null);
    }

    static final CharSequence encodedCookieHeader$lambda$1(NeteaseQualityClient this$0, JSONObject $values, String key) throws UnsupportedEncodingException {
        Intrinsics.checkNotNull(key);
        String strEncodeURIComponent = this$0.encodeURIComponent(key);
        String strOptString = $values.optString(key);
        Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
        return strEncodeURIComponent + "=" + this$0.encodeURIComponent(strOptString);
    }

    private final String secureUrl(String url) {
        if (StringsKt.startsWith(url, "http://", true)) {
            return "https://" + StringsKt.substringAfter$default(url, "://", (String) null, 2, (Object) null);
        }
        return url;
    }

    private final String encodeURIComponent(String value) throws UnsupportedEncodingException {
        String strEncode = URLEncoder.encode(value, Charsets.UTF_8.name());
        Intrinsics.checkNotNullExpressionValue(strEncode, "encode(...)");
        return StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(strEncode, "+", "%20", false, 4, (Object) null), "%21", "!", false, 4, (Object) null), "%27", "'", false, 4, (Object) null), "%28", "(", false, 4, (Object) null), "%29", ")", false, 4, (Object) null), "%7E", "~", false, 4, (Object) null);
    }

    private final String randomHex(int byteCount) {
        byte[] bytes = new byte[byteCount];
        new SecureRandom().nextBytes(bytes);
        return ArraysKt.joinToString$default(bytes, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.lladlam.melox.core.audio.NeteaseQualityClient$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NeteaseQualityClient.randomHex$lambda$0(((Byte) obj).byteValue());
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
        return ArraysKt.joinToString$default(bArrDigest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.lladlam.melox.core.audio.NeteaseQualityClient$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NeteaseQualityClient.md5Hex$lambda$0(((Byte) obj).byteValue());
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
        return ArraysKt.joinToString$default($this$toHexUppercase, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.lladlam.melox.core.audio.NeteaseQualityClient$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NeteaseQualityClient.toHexUppercase$lambda$0(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    static final CharSequence toHexUppercase$lambda$0(byte it) {
        String str = String.format("%02X", Arrays.copyOf(new Object[]{Byte.valueOf(it)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}
