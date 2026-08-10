package com.lladlam.melox.playback;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.media3.common.C1565C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.session.MediaController;
import androidx.media3.session.SessionToken;
import com.google.common.util.concurrent.ListenableFuture;
import com.lladlam.melox.core.audio.MusicQuality;
import com.lladlam.melox.core.audio.MusicQualityPreferences;
import com.lladlam.melox.core.audio.MusicQualityRuntime;
import com.lladlam.melox.core.model.SearchSong;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PlaybackCommands.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J<\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0016\b\u0002\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\r\u0018\u00010\u0016J\u0016\u0010\u0018\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u001aJ\u0014\u0010\u001b\u001a\u00020\u001c*\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/lladlam/melox/playback/PlaybackCommands;", "", "<init>", "()V", "TAG", "", "mainHandler", "Landroid/os/Handler;", "mainExecutor", "Ljava/util/concurrent/Executor;", "activeController", "Landroidx/media3/session/MediaController;", "playQueue", "", "context", "Landroid/content/Context;", "songs", "", "Lcom/lladlam/melox/core/model/SearchSong;", "selectedSongId", "", "onFailure", "Lkotlin/Function1;", "", "changeQuality", "quality", "Lcom/lladlam/melox/core/audio/MusicQuality;", "toMediaItem", "Landroidx/media3/common/MediaItem;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PlaybackCommands {
    private static final String TAG = "MeloXPlayback";
    private static volatile MediaController activeController;
    public static final PlaybackCommands INSTANCE = new PlaybackCommands();
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());
    private static final Executor mainExecutor = new Executor() { // from class: com.lladlam.melox.playback.PlaybackCommands$$ExternalSyntheticLambda1
        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            PlaybackCommands.mainHandler.post(runnable);
        }
    };
    public static final int $stable = 8;

    private PlaybackCommands() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void playQueue$default(PlaybackCommands playbackCommands, Context context, List list, long j, Function1 function1, int i, Object obj) {
        Function1 function2;
        if ((i & 8) == 0) {
            function2 = function1;
        } else {
            function2 = null;
        }
        playbackCommands.playQueue(context, list, j, function2);
    }

    public final void playQueue(Context context, final List<SearchSong> songs, final long selectedSongId, final Function1<? super Throwable, Unit> onFailure) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(songs, "songs");
        Context appContext = context.getApplicationContext();
        MusicQualityPreferences musicQualityPreferences = MusicQualityPreferences.INSTANCE;
        Intrinsics.checkNotNull(appContext);
        final MusicQuality quality = musicQualityPreferences.read(appContext);
        MusicQualityRuntime.INSTANCE.setSelected(quality);
        SessionToken token = new SessionToken(appContext, new ComponentName(appContext, (Class<?>) MeloXPlaybackService.class));
        final ListenableFuture<MediaController> listenableFutureBuildAsync = new MediaController.Builder(appContext, token).buildAsync();
        Intrinsics.checkNotNullExpressionValue(listenableFutureBuildAsync, "buildAsync(...)");
        listenableFutureBuildAsync.addListener(new Runnable() { // from class: com.lladlam.melox.playback.PlaybackCommands$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                PlaybackCommands.playQueue$lambda$0(listenableFutureBuildAsync, songs, selectedSongId, quality, onFailure);
            }
        }, mainExecutor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static final void playQueue$lambda$0(ListenableFuture $controllerFuture, List $songs, long $selectedSongId, MusicQuality $quality, Function1 $onFailure) {
        boolean z;
        try {
            MediaController controller = (MediaController) $controllerFuture.get();
            List list = $songs;
            if (list.isEmpty()) {
                return;
            }
            List list2 = list;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                try {
                    arrayList.add(INSTANCE.toMediaItem((SearchSong) it.next(), $quality));
                } catch (Throwable th) {
                    error = th;
                }
            }
            List queue = (List) arrayList;
            int i = 0;
            Iterator it2 = $songs.iterator();
            while (true) {
                z = true;
                if (!it2.hasNext()) {
                    i = -1;
                    break;
                } else {
                    if (((SearchSong) it2.next()).getId() == $selectedSongId) {
                        break;
                    } else {
                        i++;
                    }
                }
            }
            Integer numValueOf = Integer.valueOf(i);
            if ((numValueOf.intValue() >= 0 ? 1 : 0) == 0) {
                numValueOf = null;
            }
            int startIndex = numValueOf != null ? numValueOf.intValue() : 0;
            MediaController mediaController = activeController;
            if (mediaController != null) {
                if (mediaController == controller) {
                    z = false;
                }
                MediaController mediaController2 = z ? mediaController : null;
                if (mediaController2 != null) {
                    mediaController2.release();
                }
            }
            activeController = controller;
            controller.setMediaItems(queue, startIndex, C1565C.TIME_UNSET);
            controller.prepare();
            controller.play();
            Log.d("MeloXPlayback", "Playback queue dispatched: size=" + queue.size() + ", start=" + startIndex + ", song=" + $selectedSongId + ", quality=" + $quality.getApiLevel());
            return;
        } catch (Throwable th2) {
            error = th2;
        }
        Log.e("MeloXPlayback", "Unable to connect MediaController", error);
        if ($onFailure != null) {
            $onFailure.invoke(error);
        }
    }

    public final void changeQuality(Context context, MusicQuality quality) {
        MediaItem mediaItemBuild;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(quality, "quality");
        Context appContext = context.getApplicationContext();
        MusicQualityPreferences musicQualityPreferences = MusicQualityPreferences.INSTANCE;
        Intrinsics.checkNotNull(appContext);
        musicQualityPreferences.write(appContext, quality);
        MusicQualityRuntime.INSTANCE.setSelected(quality);
        MusicQualityRuntime.clear$default(MusicQualityRuntime.INSTANCE, null, 1, null);
        MediaController controller = activeController;
        if (controller == null) {
            return;
        }
        int currentIndex = RangesKt.coerceAtLeast(controller.getCurrentMediaItemIndex(), 0);
        long currentPosition = RangesKt.coerceAtLeast(controller.getCurrentPosition(), 0L);
        boolean shouldResume = controller.getPlayWhenReady();
        int mediaItemCount = controller.getMediaItemCount();
        ArrayList arrayList = new ArrayList(mediaItemCount);
        int i = 0;
        while (i < mediaItemCount) {
            MediaItem mediaItemAt = controller.getMediaItemAt(i);
            Intrinsics.checkNotNullExpressionValue(mediaItemAt, "getMediaItemAt(...)");
            String mediaId = mediaItemAt.mediaId;
            Intrinsics.checkNotNullExpressionValue(mediaId, "mediaId");
            Long longOrNull = StringsKt.toLongOrNull(mediaId);
            if (longOrNull == null) {
                mediaItemBuild = mediaItemAt;
            } else {
                mediaItemBuild = new MediaItem.Builder().setMediaId(mediaItemAt.mediaId).setUri(NeteasePlaybackResolver.INSTANCE.uriForSong(longOrNull.longValue(), quality)).setMediaMetadata(mediaItemAt.mediaMetadata).build();
                Intrinsics.checkNotNull(mediaItemBuild);
            }
            arrayList.add(mediaItemBuild);
            i++;
            appContext = appContext;
            shouldResume = shouldResume;
            mediaItemCount = mediaItemCount;
        }
        boolean shouldResume2 = shouldResume;
        ArrayList items = arrayList;
        if (items.isEmpty()) {
            return;
        }
        controller.setMediaItems(items, RangesKt.coerceIn(currentIndex, 0, CollectionsKt.getLastIndex(items)), currentPosition);
        controller.prepare();
        if (shouldResume2) {
            controller.play();
        }
    }

    private final MediaItem toMediaItem(SearchSong $this$toMediaItem, MusicQuality quality) {
        MediaMetadata.Builder mediaType = new MediaMetadata.Builder().setTitle($this$toMediaItem.getName()).setArtist($this$toMediaItem.getArtists()).setAlbumTitle($this$toMediaItem.getAlbum()).setMediaType(1);
        String artworkUrl = $this$toMediaItem.getArtworkUrl();
        if (artworkUrl != null) {
            if (StringsKt.isBlank(artworkUrl)) {
                artworkUrl = null;
            }
            if (artworkUrl != null) {
                mediaType.setArtworkUri(Uri.parse(artworkUrl));
            }
        }
        MediaMetadata metadata = mediaType.build();
        Intrinsics.checkNotNullExpressionValue(metadata, "build(...)");
        MediaItem mediaItemBuild = new MediaItem.Builder().setMediaId(String.valueOf($this$toMediaItem.getId())).setUri(NeteasePlaybackResolver.INSTANCE.uriForSong($this$toMediaItem.getId(), quality)).setMediaMetadata(metadata).build();
        Intrinsics.checkNotNullExpressionValue(mediaItemBuild, "build(...)");
        return mediaItemBuild;
    }
}
