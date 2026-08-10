package com.lladlam.melox.playback;

import android.net.Uri;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.ResolvingDataSource;
import com.lladlam.melox.core.audio.MusicQuality;
import com.lladlam.melox.core.audio.MusicQualityRuntime;
import com.lladlam.melox.core.audio.NeteaseQualityClient;
import com.lladlam.melox.core.network.NeteaseSearchClient;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: NeteasePlaybackResolver.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0002\u0014\u0015B!\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\fH\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/lladlam/melox/playback/NeteasePlaybackResolver;", "Landroidx/media3/datasource/ResolvingDataSource$Resolver;", "cookieProvider", "Lkotlin/Function0;", "", "client", "Lcom/lladlam/melox/core/network/NeteaseSearchClient;", "<init>", "(Lkotlin/jvm/functions/Function0;Lcom/lladlam/melox/core/network/NeteaseSearchClient;)V", "resolvedUris", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/lladlam/melox/playback/NeteasePlaybackResolver$ResolveKey;", "Landroid/net/Uri;", "qualityClient", "Lcom/lladlam/melox/core/audio/NeteaseQualityClient;", "resolveDataSpec", "Landroidx/media3/datasource/DataSpec;", "dataSpec", "resolveReportedUri", "uri", "ResolveKey", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class NeteasePlaybackResolver implements ResolvingDataSource.Resolver {
    private static final String MELOX_SCHEME = "melox";
    private static final String QUALITY_QUERY = "quality";
    private static final String SONG_HOST = "song";
    private final NeteaseSearchClient client;
    private final Function0<String> cookieProvider;
    private final NeteaseQualityClient qualityClient;
    private final ConcurrentHashMap<ResolveKey, Uri> resolvedUris;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    /* JADX WARN: Multi-variable type inference failed */
    public NeteasePlaybackResolver() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public NeteasePlaybackResolver(Function0<String> cookieProvider, NeteaseSearchClient client) {
        Intrinsics.checkNotNullParameter(cookieProvider, "cookieProvider");
        Intrinsics.checkNotNullParameter(client, "client");
        this.cookieProvider = cookieProvider;
        this.client = client;
        this.resolvedUris = new ConcurrentHashMap<>();
        this.qualityClient = new NeteaseQualityClient(this.cookieProvider, null, 2, null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ NeteasePlaybackResolver(Function0 function0, NeteaseSearchClient neteaseSearchClient, int i, DefaultConstructorMarker defaultConstructorMarker) {
        function0 = (i & 1) != 0 ? new Function0() { // from class: com.lladlam.melox.playback.NeteasePlaybackResolver$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NeteasePlaybackResolver._init_$lambda$0();
            }
        } : function0;
        this(function0, (i & 2) != 0 ? new NeteaseSearchClient(null, function0, 1, null) : neteaseSearchClient);
    }

    static final String _init_$lambda$0() {
        return "";
    }

    /* JADX INFO: compiled from: NeteasePlaybackResolver.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0007HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/lladlam/melox/playback/NeteasePlaybackResolver$ResolveKey;", "", "songId", "", NeteasePlaybackResolver.QUALITY_QUERY, "Lcom/lladlam/melox/core/audio/MusicQuality;", "cookieHeader", "", "<init>", "(JLcom/lladlam/melox/core/audio/MusicQuality;Ljava/lang/String;)V", "getSongId", "()J", "getQuality", "()Lcom/lladlam/melox/core/audio/MusicQuality;", "getCookieHeader", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final /* data */ class ResolveKey {
        private final String cookieHeader;
        private final MusicQuality quality;
        private final long songId;

        public static /* synthetic */ ResolveKey copy$default(ResolveKey resolveKey, long j, MusicQuality musicQuality, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                j = resolveKey.songId;
            }
            if ((i & 2) != 0) {
                musicQuality = resolveKey.quality;
            }
            if ((i & 4) != 0) {
                str = resolveKey.cookieHeader;
            }
            return resolveKey.copy(j, musicQuality, str);
        }

        

        

        

        public final ResolveKey copy(long songId, MusicQuality quality, String cookieHeader) {
            Intrinsics.checkNotNullParameter(quality, "quality");
            Intrinsics.checkNotNullParameter(cookieHeader, "cookieHeader");
            return new ResolveKey(songId, quality, cookieHeader);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ResolveKey)) {
                return false;
            }
            ResolveKey resolveKey = (ResolveKey) other;
            return this.songId == resolveKey.songId && this.quality == resolveKey.quality && Intrinsics.areEqual(this.cookieHeader, resolveKey.cookieHeader);
        }

        public int hashCode() {
            return (((Long.hashCode(this.songId) * 31) + this.quality.hashCode()) * 31) + this.cookieHeader.hashCode();
        }

        public String toString() {
            return "ResolveKey(songId=" + this.songId + ", quality=" + this.quality + ", cookieHeader=" + this.cookieHeader + ")";
        }

        public ResolveKey(long songId, MusicQuality quality, String cookieHeader) {
            Intrinsics.checkNotNullParameter(quality, "quality");
            Intrinsics.checkNotNullParameter(cookieHeader, "cookieHeader");
            this.songId = songId;
            this.quality = quality;
            this.cookieHeader = cookieHeader;
        }

        public final long getSongId() {
            return this.songId;
        }

        public final MusicQuality getQuality() {
            return this.quality;
        }

        public final String getCookieHeader() {
            return this.cookieHeader;
        }
    }

    @Override // androidx.media3.datasource.ResolvingDataSource.Resolver
    public DataSpec resolveDataSpec(DataSpec dataSpec) throws IOException {
        Long longOrNull;
        Intrinsics.checkNotNullParameter(dataSpec, "dataSpec");
        Uri uri = dataSpec.uri;
        Intrinsics.checkNotNullExpressionValue(uri, "uri");
        if (!Intrinsics.areEqual(uri.getScheme(), MELOX_SCHEME) || !Intrinsics.areEqual(uri.getHost(), SONG_HOST)) {
            return dataSpec;
        }
        String lastPathSegment = uri.getLastPathSegment();
        if (lastPathSegment == null || (longOrNull = StringsKt.toLongOrNull(lastPathSegment)) == null) {
            throw new IOException("Invalid MeloX song URI: " + uri);
        }
        long songId = longOrNull.longValue();
        MusicQuality requestedQuality = MusicQuality.INSTANCE.fromApiLevel(uri.getQueryParameter(QUALITY_QUERY));
        if (requestedQuality == null) {
            requestedQuality = MusicQualityRuntime.INSTANCE.getSelected();
        }
        String currentCookieHeader = this.cookieProvider.invoke();
        ResolveKey key = new ResolveKey(songId, requestedQuality, currentCookieHeader);
        Uri resolved = this.resolvedUris.get(key);
        if (resolved == null) {
            NeteasePlaybackResolver neteasePlaybackResolver = this;
            Uri uri2 = Uri.parse(neteasePlaybackResolver.qualityClient.playbackSourceBlocking(songId, requestedQuality).getUrl());
            neteasePlaybackResolver.resolvedUris.put(key, uri2);
            resolved = uri2;
        }
        DataSpec dataSpecWithUri = dataSpec.withUri(resolved);
        Intrinsics.checkNotNullExpressionValue(dataSpecWithUri, "withUri(...)");
        return dataSpecWithUri;
    }

    @Override // androidx.media3.datasource.ResolvingDataSource.Resolver
    public Uri resolveReportedUri(Uri uri) {
        String lastPathSegment;
        Long longOrNull;
        Intrinsics.checkNotNullParameter(uri, "uri");
        if (!Intrinsics.areEqual(uri.getScheme(), MELOX_SCHEME) || !Intrinsics.areEqual(uri.getHost(), SONG_HOST) || (lastPathSegment = uri.getLastPathSegment()) == null || (longOrNull = StringsKt.toLongOrNull(lastPathSegment)) == null) {
            return uri;
        }
        long songId = longOrNull.longValue();
        MusicQuality requestedQuality = MusicQuality.INSTANCE.fromApiLevel(uri.getQueryParameter(QUALITY_QUERY));
        if (requestedQuality == null) {
            requestedQuality = MusicQualityRuntime.INSTANCE.getSelected();
        }
        String currentCookieHeader = this.cookieProvider.invoke();
        Uri uri2 = this.resolvedUris.get(new ResolveKey(songId, requestedQuality, currentCookieHeader));
        return uri2 == null ? uri : uri2;
    }

    /* JADX INFO: compiled from: NeteasePlaybackResolver.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/lladlam/melox/playback/NeteasePlaybackResolver$Companion;", "", "<init>", "()V", "MELOX_SCHEME", "", "SONG_HOST", "QUALITY_QUERY", "uriForSong", "Landroid/net/Uri;", "songId", "", NeteasePlaybackResolver.QUALITY_QUERY, "Lcom/lladlam/melox/core/audio/MusicQuality;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ Uri uriForSong$default(Companion companion, long j, MusicQuality musicQuality, int i, Object obj) {
            if ((i & 2) != 0) {
                musicQuality = MusicQualityRuntime.INSTANCE.getSelected();
            }
            return companion.uriForSong(j, musicQuality);
        }

        public final Uri uriForSong(long songId, MusicQuality quality) {
            Intrinsics.checkNotNullParameter(quality, "quality");
            Uri uriBuild = new Uri.Builder().scheme(NeteasePlaybackResolver.MELOX_SCHEME).authority(NeteasePlaybackResolver.SONG_HOST).appendPath(String.valueOf(songId)).appendQueryParameter(NeteasePlaybackResolver.QUALITY_QUERY, quality.getApiLevel()).build();
            Intrinsics.checkNotNullExpressionValue(uriBuild, "build(...)");
            return uriBuild;
        }
    }
}
