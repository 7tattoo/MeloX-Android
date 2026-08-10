package com.lladlam.melox.core.library;

import androidx.autofill.HintConstants;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: NeteaseDiscoveryCache.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0002\u001a\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002\u001a\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¨\u0006\t"}, d2 = {"stableKey", "", "value", "encodePlaylists", "Lorg/json/JSONArray;", "values", "", "Lcom/lladlam/melox/core/library/NeteasePlaylistSummary;", "decodePlaylists", "app"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class NeteaseDiscoveryCacheKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String stableKey(String value) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = value.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDigest = messageDigest.digest(bytes);
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
        return CollectionsKt.joinToString$default(ArraysKt.take(bArrDigest, 10), "", null, null, 0, null, new Function1() { // from class: com.lladlam.melox.core.library.NeteaseDiscoveryCacheKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NeteaseDiscoveryCacheKt.stableKey$lambda$0(((Byte) obj).byteValue());
            }
        }, 30, null);
    }

    static final CharSequence stableKey$lambda$0(byte it) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(it)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final JSONArray encodePlaylists(List<NeteasePlaylistSummary> list) {
        JSONArray jSONArray = new JSONArray();
        for (NeteasePlaylistSummary neteasePlaylistSummary : list) {
            jSONArray.put(new JSONObject().put(TtmlNode.ATTR_ID, neteasePlaylistSummary.getId()).put(HintConstants.AUTOFILL_HINT_NAME, neteasePlaylistSummary.getName()).put("coverUrl", neteasePlaylistSummary.getCoverUrl()).put("trackCount", neteasePlaylistSummary.getTrackCount()).put("creatorName", neteasePlaylistSummary.getCreatorName()).put("playCount", neteasePlaylistSummary.getPlayCount()).put("description", neteasePlaylistSummary.getDescription()));
        }
        return jSONArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<NeteasePlaylistSummary> decodePlaylists(JSONArray values) {
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        int i = 0;
        int i2 = 0;
        int length = values.length();
        while (i2 < length) {
            JSONObject jSONObjectOptJSONObject = values.optJSONObject(i2);
            if (jSONObjectOptJSONObject != null) {
                long jOptLong = jSONObjectOptJSONObject.optLong(TtmlNode.ATTR_ID, -1L);
                if (jOptLong > 0) {
                    String strOptString = jSONObjectOptJSONObject.optString(HintConstants.AUTOFILL_HINT_NAME);
                    if (StringsKt.isBlank(strOptString)) {
                        strOptString = "未命名歌单";
                    }
                    Intrinsics.checkNotNullExpressionValue(strOptString, "ifBlank(...)");
                    String str = strOptString;
                    String strOptString2 = jSONObjectOptJSONObject.optString("coverUrl");
                    String str2 = !StringsKt.isBlank(strOptString2) ? strOptString2 : null;
                    int iCoerceAtLeast = RangesKt.coerceAtLeast(jSONObjectOptJSONObject.optInt("trackCount"), 0);
                    String strOptString3 = jSONObjectOptJSONObject.optString("creatorName");
                    Intrinsics.checkNotNullExpressionValue(strOptString3, "optString(...)");
                    long jCoerceAtLeast = RangesKt.coerceAtLeast(jSONObjectOptJSONObject.optLong("playCount"), 0L);
                    String strOptString4 = jSONObjectOptJSONObject.optString("description");
                    listCreateListBuilder.add(new NeteasePlaylistSummary(jOptLong, str, str2, iCoerceAtLeast, strOptString3, jCoerceAtLeast, !StringsKt.isBlank(strOptString4) ? strOptString4 : null));
                }
            }
            i2++;
            i = i;
        }
        return CollectionsKt.build(listCreateListBuilder);
    }
}
