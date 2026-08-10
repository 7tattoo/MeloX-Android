package com.lladlam.melox.core.library;

import androidx.autofill.HintConstants;
import com.lladlam.melox.core.model.SearchSong;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: NeteaseLibraryCache.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0018\u0010\u0006\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0007H\u0002\u001a\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0016\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002\u001a\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u001a\u0018\u0010\u000f\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\u0006\u0010\u0003\u001a\u00020\rH\u0002\u001a\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0016\u0010\u0011\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00120\fH\u0002\u001a\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\f2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u001a\u0016\u0010\u0014\u001a\u0004\u0018\u00010\u0015*\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u0015H\u0002¨\u0006\u0017"}, d2 = {"encodeSnapshot", "Lorg/json/JSONObject;", "kotlin.jvm.PlatformType", "value", "Lcom/lladlam/melox/core/library/NeteaseLibrarySnapshot;", "decodeSnapshot", "encodePlaylistDetail", "Lcom/lladlam/melox/core/library/NeteasePlaylistDetail;", "decodePlaylistDetail", "encodePlaylists", "Lorg/json/JSONArray;", "values", "", "Lcom/lladlam/melox/core/library/NeteasePlaylistSummary;", "decodePlaylists", "encodePlaylist", "decodePlaylist", "encodeSongs", "Lcom/lladlam/melox/core/model/SearchSong;", "decodeSongs", "optNullableString", "", HintConstants.AUTOFILL_HINT_NAME, "app"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class NeteaseLibraryCacheKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final JSONObject encodeSnapshot(NeteaseLibrarySnapshot value) {
        return new JSONObject().put("playlists", encodePlaylists(value.getPlaylists())).put("likedSongs", encodeSongs(value.getLikedSongs())).put("recentSongs", encodeSongs(value.getRecentSongs()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NeteaseLibrarySnapshot decodeSnapshot(JSONObject value) throws JSONException {
        JSONArray jSONArrayOptJSONArray = value.optJSONArray("playlists");
        if (jSONArrayOptJSONArray == null) {
            jSONArrayOptJSONArray = new JSONArray();
        }
        List<NeteasePlaylistSummary> listDecodePlaylists = decodePlaylists(jSONArrayOptJSONArray);
        JSONArray jSONArrayOptJSONArray2 = value.optJSONArray("likedSongs");
        if (jSONArrayOptJSONArray2 == null) {
            jSONArrayOptJSONArray2 = new JSONArray();
        }
        List<SearchSong> listDecodeSongs = decodeSongs(jSONArrayOptJSONArray2);
        JSONArray jSONArrayOptJSONArray3 = value.optJSONArray("recentSongs");
        if (jSONArrayOptJSONArray3 == null) {
            jSONArrayOptJSONArray3 = new JSONArray();
        }
        return new NeteaseLibrarySnapshot(listDecodePlaylists, listDecodeSongs, decodeSongs(jSONArrayOptJSONArray3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final JSONObject encodePlaylistDetail(NeteasePlaylistDetail value) {
        return new JSONObject().put("summary", encodePlaylist(value.getSummary())).put("songs", encodeSongs(value.getSongs()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NeteasePlaylistDetail decodePlaylistDetail(JSONObject value) throws JSONException {
        JSONObject jSONObject = value.getJSONObject("summary");
        Intrinsics.checkNotNullExpressionValue(jSONObject, "getJSONObject(...)");
        NeteasePlaylistSummary summary = decodePlaylist(jSONObject);
        JSONArray jSONArrayOptJSONArray = value.optJSONArray("songs");
        if (jSONArrayOptJSONArray == null) {
            jSONArrayOptJSONArray = new JSONArray();
        }
        return new NeteasePlaylistDetail(summary, decodeSongs(jSONArrayOptJSONArray));
    }

    private static final JSONArray encodePlaylists(List<NeteasePlaylistSummary> list) {
        JSONArray jSONArray = new JSONArray();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put(encodePlaylist((NeteasePlaylistSummary) it.next()));
        }
        return jSONArray;
    }

    private static final List<NeteasePlaylistSummary> decodePlaylists(JSONArray values) {
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        int length = values.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject = values.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                listCreateListBuilder.add(decodePlaylist(jSONObjectOptJSONObject));
            }
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    private static final JSONObject encodePlaylist(NeteasePlaylistSummary value) {
        return new JSONObject().put(TtmlNode.ATTR_ID, value.getId()).put(HintConstants.AUTOFILL_HINT_NAME, value.getName()).put("coverUrl", value.getCoverUrl()).put("trackCount", value.getTrackCount()).put("creatorName", value.getCreatorName()).put("playCount", value.getPlayCount()).put("description", value.getDescription());
    }

    private static final NeteasePlaylistSummary decodePlaylist(JSONObject value) throws JSONException {
        long j = value.getLong(TtmlNode.ATTR_ID);
        String strOptString = value.optString(HintConstants.AUTOFILL_HINT_NAME);
        Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
        String strOptNullableString = optNullableString(value, "coverUrl");
        int iOptInt = value.optInt("trackCount");
        String strOptString2 = value.optString("creatorName");
        Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
        return new NeteasePlaylistSummary(j, strOptString, strOptNullableString, iOptInt, strOptString2, value.optLong("playCount"), optNullableString(value, "description"));
    }

    private static final JSONArray encodeSongs(List<SearchSong> list) {
        JSONArray jSONArray = new JSONArray();
        for (SearchSong searchSong : list) {
            jSONArray.put(new JSONObject().put(TtmlNode.ATTR_ID, searchSong.getId()).put(HintConstants.AUTOFILL_HINT_NAME, searchSong.getName()).put("artists", searchSong.getArtists()).put("album", searchSong.getAlbum()).put("artworkUrl", searchSong.getArtworkUrl()).put("durationMs", searchSong.getDurationMs()));
        }
        return jSONArray;
    }

    private static final List<SearchSong> decodeSongs(JSONArray values) throws JSONException {
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        int length = values.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject = values.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                long j = jSONObjectOptJSONObject.getLong(TtmlNode.ATTR_ID);
                String strOptString = jSONObjectOptJSONObject.optString(HintConstants.AUTOFILL_HINT_NAME);
                Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
                String strOptString2 = jSONObjectOptJSONObject.optString("artists");
                Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
                String strOptString3 = jSONObjectOptJSONObject.optString("album");
                Intrinsics.checkNotNullExpressionValue(strOptString3, "optString(...)");
                listCreateListBuilder.add(new SearchSong(j, strOptString, strOptString2, strOptString3, optNullableString(jSONObjectOptJSONObject, "artworkUrl"), jSONObjectOptJSONObject.optLong("durationMs")));
            }
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    private static final String optNullableString(JSONObject $this$optNullableString, String name) {
        if ($this$optNullableString.isNull(name)) {
            return null;
        }
        String strOptString = $this$optNullableString.optString(name);
        if (StringsKt.isBlank(strOptString)) {
            return null;
        }
        return strOptString;
    }
}
