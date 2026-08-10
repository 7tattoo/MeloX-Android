package com.lladlam.melox.core.audio;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: NeteaseQualityClient.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final /* synthetic */ class NeteaseQualityClient$playbackSourceBlocking$source$1 extends FunctionReferenceImpl implements Function1<Integer, JSONObject> {
    NeteaseQualityClient$playbackSourceBlocking$source$1(Object obj) {
        super(1, obj, JSONArray.class, "optJSONObject", "optJSONObject(I)Lorg/json/JSONObject;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ JSONObject invoke(Integer num) {
        return invoke(num.intValue());
    }

    public final JSONObject invoke(int p0) {
        return ((JSONArray) this.receiver).optJSONObject(p0);
    }
}
