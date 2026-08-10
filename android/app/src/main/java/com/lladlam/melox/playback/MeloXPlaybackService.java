package com.lladlam.melox.playback;

import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.Player;
import androidx.media3.datasource.DefaultHttpDataSource;
import androidx.media3.datasource.ResolvingDataSource;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionService;
import com.google.common.net.HttpHeaders;
import com.lladlam.melox.MainActivity;
import com.lladlam.melox.core.account.NeteaseSessionStore;
import com.lladlam.melox.core.network.NeteaseSearchClient;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MeloXPlaybackService.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0016J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\tH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/lladlam/melox/playback/MeloXPlaybackService;", "Landroidx/media3/session/MediaSessionService;", "<init>", "()V", "player", "Landroidx/media3/exoplayer/ExoPlayer;", "mediaSession", "Landroidx/media3/session/MediaSession;", "onCreate", "", "onGetSession", "controllerInfo", "Landroidx/media3/session/MediaSession$ControllerInfo;", "onDestroy", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class MeloXPlaybackService extends MediaSessionService {

    @Deprecated
    public static final String TAG = "MeloXPlayback";
    private MediaSession mediaSession;
    private ExoPlayer player;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    @Override // androidx.media3.session.MediaSessionService, androidx.lifecycle.LifecycleService, android.app.Service
    public void onCreate() {
        super.onCreate();
        DefaultHttpDataSource.Factory httpDataSourceFactory = new DefaultHttpDataSource.Factory().setAllowCrossProtocolRedirects(true).setDefaultRequestProperties(MapsKt.mapOf(TuplesKt.m717to(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Linux; Android 14) AppleWebKit/537.36 Chrome/124 Mobile Safari/537.36"), TuplesKt.m717to(HttpHeaders.REFERER, "https://music.163.com/")));
        Intrinsics.checkNotNullExpressionValue(httpDataSourceFactory, "setDefaultRequestProperties(...)");
        Function0 cookieProvider = new Function0() { // from class: com.lladlam.melox.playback.MeloXPlaybackService$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NeteaseSessionStore.INSTANCE.readCookie(this.f$0);
            }
        };
        ResolvingDataSource.Factory resolvingDataSourceFactory = new ResolvingDataSource.Factory(httpDataSourceFactory, new NeteasePlaybackResolver(cookieProvider, new NeteaseSearchClient(null, cookieProvider, 1, null)));
        DefaultMediaSourceFactory mediaSourceFactory = new DefaultMediaSourceFactory(this).setDataSourceFactory(resolvingDataSourceFactory);
        Intrinsics.checkNotNullExpressionValue(mediaSourceFactory, "setDataSourceFactory(...)");
        ExoPlayer exoPlayer = new ExoPlayer.Builder(this).setMediaSourceFactory(mediaSourceFactory).setWakeMode(1).build();
        exoPlayer.setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(2).build(), true);
        exoPlayer.setHandleAudioBecomingNoisy(true);
        Intrinsics.checkNotNullExpressionValue(exoPlayer, "apply(...)");
        exoPlayer.addListener(new Player.Listener() { // from class: com.lladlam.melox.playback.MeloXPlaybackService.onCreate.1
            @Override // androidx.media3.common.Player.Listener
            public void onPlayerError(PlaybackException error) {
                Intrinsics.checkNotNullParameter(error, "error");
                Log.e(MeloXPlaybackService.TAG, "Playback failed: code=" + error.getErrorCodeName() + ", message=" + error.getMessage(), error);
            }

            @Override // androidx.media3.common.Player.Listener
            public void onIsPlayingChanged(boolean isPlaying) {
                Log.d(MeloXPlaybackService.TAG, "isPlaying=" + isPlaying + ", ongoing=" + MeloXPlaybackService.this.isPlaybackOngoing());
            }
        });
        Intent sessionActivityIntent = new Intent(this, (Class<?>) MainActivity.class);
        sessionActivityIntent.setAction(MainActivity.ACTION_OPEN_NOW_PLAYING);
        sessionActivityIntent.setFlags(603979776);
        PendingIntent sessionActivity = PendingIntent.getActivity(this, 1001, sessionActivityIntent, 201326592);
        this.player = exoPlayer;
        this.mediaSession = new MediaSession.Builder(this, exoPlayer).setSessionActivity(sessionActivity).build();
    }

    @Override // androidx.media3.session.MediaSessionService
    public MediaSession onGetSession(MediaSession.ControllerInfo controllerInfo) {
        Intrinsics.checkNotNullParameter(controllerInfo, "controllerInfo");
        Log.d(TAG, "Controller connected: " + controllerInfo.getPackageName());
        return this.mediaSession;
    }

    @Override // androidx.media3.session.MediaSessionService, androidx.lifecycle.LifecycleService, android.app.Service
    public void onDestroy() {
        MediaSession mediaSession = this.mediaSession;
        if (mediaSession != null) {
            mediaSession.release();
        }
        this.mediaSession = null;
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null) {
            exoPlayer.release();
        }
        this.player = null;
        super.onDestroy();
    }

    /* JADX INFO: compiled from: MeloXPlaybackService.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/lladlam/melox/playback/MeloXPlaybackService$Companion;", "", "<init>", "()V", "TAG", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
