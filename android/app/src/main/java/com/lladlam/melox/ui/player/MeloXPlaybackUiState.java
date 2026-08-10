package com.lladlam.melox.ui.player;

import android.net.Uri;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotLongStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.media3.common.C1565C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Player;
import androidx.media3.session.MediaController;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MeloXPlayerUi.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000]\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b*\u0001^\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0000¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020\u0005H\u0000¢\u0006\u0002\bcJ\r\u0010d\u001a\u00020aH\u0000¢\u0006\u0002\beJ\r\u0010f\u001a\u00020aH\u0000¢\u0006\u0002\bgJ\u0016\u0010h\u001a\b\u0012\u0004\u0012\u00020:092\u0006\u0010i\u001a\u00020jH\u0002J\u0006\u0010k\u001a\u00020aJ\u000e\u0010l\u001a\u00020a2\u0006\u0010&\u001a\u00020%J\u0006\u0010m\u001a\u00020aJ\u0006\u0010n\u001a\u00020aJ\u000e\u0010o\u001a\u00020a2\u0006\u0010p\u001a\u00020AJ\u0006\u0010q\u001a\u00020aJ\u0006\u0010r\u001a\u00020aJ\u000e\u0010s\u001a\u00020a2\u0006\u0010t\u001a\u00020QR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R/\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00078F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR+\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0012\u0010\u000e\u001a\u0004\b\u0010\u0010\n\"\u0004\b\u0011\u0010\fR+\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0016\u0010\u000e\u001a\u0004\b\u0014\u0010\n\"\u0004\b\u0015\u0010\fR+\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001a\u0010\u000e\u001a\u0004\b\u0018\u0010\n\"\u0004\b\u0019\u0010\fR/\u0010\u001b\u001a\u0004\u0018\u00010\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00078F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001e\u0010\u000e\u001a\u0004\b\u001c\u0010\n\"\u0004\b\u001d\u0010\fR+\u0010 \u001a\u00020\u001f2\u0006\u0010\u0006\u001a\u00020\u001f8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b$\u0010\u000e\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R+\u0010&\u001a\u00020%2\u0006\u0010\u0006\u001a\u00020%8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b+\u0010,\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R+\u0010-\u001a\u00020%2\u0006\u0010\u0006\u001a\u00020%8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b0\u0010,\u001a\u0004\b.\u0010(\"\u0004\b/\u0010*R+\u00101\u001a\u00020\u001f2\u0006\u0010\u0006\u001a\u00020\u001f8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b4\u0010\u000e\u001a\u0004\b2\u0010!\"\u0004\b3\u0010#R+\u00105\u001a\u00020\u001f2\u0006\u0010\u0006\u001a\u00020\u001f8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b8\u0010\u000e\u001a\u0004\b6\u0010!\"\u0004\b7\u0010#R7\u0010;\u001a\b\u0012\u0004\u0012\u00020:092\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020:098F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b@\u0010\u000e\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R+\u0010B\u001a\u00020A2\u0006\u0010\u0006\u001a\u00020A8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bG\u0010H\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR+\u0010I\u001a\u00020A2\u0006\u0010\u0006\u001a\u00020A8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bL\u0010H\u001a\u0004\bJ\u0010D\"\u0004\bK\u0010FR+\u0010M\u001a\u00020\u001f2\u0006\u0010\u0006\u001a\u00020\u001f8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bP\u0010\u000e\u001a\u0004\bN\u0010!\"\u0004\bO\u0010#R+\u0010R\u001a\u00020Q2\u0006\u0010\u0006\u001a\u00020Q8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bW\u0010X\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR\u0011\u0010Y\u001a\u00020\u001f8F¢\u0006\u0006\u001a\u0004\bZ\u0010!R\u0011\u0010[\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\\\u0010\nR\u0010\u0010]\u001a\u00020^X\u0082\u0004¢\u0006\u0004\n\u0002\u0010_¨\u0006u"}, d2 = {"Lcom/lladlam/melox/ui/player/MeloXPlaybackUiState;", "", "<init>", "()V", "controller", "Landroidx/media3/session/MediaController;", "<set-?>", "", "mediaId", "getMediaId", "()Ljava/lang/String;", "setMediaId", "(Ljava/lang/String;)V", "mediaId$delegate", "Landroidx/compose/runtime/MutableState;", "title", "getTitle", "setTitle", "title$delegate", "artist", "getArtist", "setArtist", "artist$delegate", "album", "getAlbum", "setAlbum", "album$delegate", "artworkUrl", "getArtworkUrl", "setArtworkUrl", "artworkUrl$delegate", "", "isPlaying", "()Z", "setPlaying", "(Z)V", "isPlaying$delegate", "", "positionMs", "getPositionMs", "()J", "setPositionMs", "(J)V", "positionMs$delegate", "Landroidx/compose/runtime/MutableLongState;", "durationMs", "getDurationMs", "setDurationMs", "durationMs$delegate", "hasPrevious", "getHasPrevious", "setHasPrevious", "hasPrevious$delegate", "hasNext", "getHasNext", "setHasNext", "hasNext$delegate", "", "Lcom/lladlam/melox/ui/player/MeloXQueueEntry;", "queue", "getQueue", "()Ljava/util/List;", "setQueue", "(Ljava/util/List;)V", "queue$delegate", "", "currentIndex", "getCurrentIndex", "()I", "setCurrentIndex", "(I)V", "currentIndex$delegate", "Landroidx/compose/runtime/MutableIntState;", "repeatMode", "getRepeatMode", "setRepeatMode", "repeatMode$delegate", "shuffleEnabled", "getShuffleEnabled", "setShuffleEnabled", "shuffleEnabled$delegate", "", "volume", "getVolume", "()F", "setVolume", "(F)V", "volume$delegate", "Landroidx/compose/runtime/MutableFloatState;", "hasMedia", "getHasMedia", "repeatModeTitle", "getRepeatModeTitle", "listener", "com/lladlam/melox/ui/player/MeloXPlaybackUiState$listener$1", "Lcom/lladlam/melox/ui/player/MeloXPlaybackUiState$listener$1;", "bind", "", "newController", "bind$app", "unbind", "unbind$app", "refresh", "refresh$app", "buildQueue", "player", "Landroidx/media3/common/Player;", "togglePlayPause", "seekTo", "previous", "next", "playQueueIndex", "index", "toggleShuffle", "cycleRepeatMode", "changeVolume", "value", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class MeloXPlaybackUiState {
    public static final int $stable = 0;
    private MediaController controller;

    /* JADX INFO: renamed from: mediaId$delegate, reason: from kotlin metadata */
    private final MutableState mediaId = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    /* JADX INFO: renamed from: title$delegate, reason: from kotlin metadata */
    private final MutableState title = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);

    /* JADX INFO: renamed from: artist$delegate, reason: from kotlin metadata */
    private final MutableState artist = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);

    /* JADX INFO: renamed from: album$delegate, reason: from kotlin metadata */
    private final MutableState album = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);

    /* JADX INFO: renamed from: artworkUrl$delegate, reason: from kotlin metadata */
    private final MutableState artworkUrl = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    /* JADX INFO: renamed from: isPlaying$delegate, reason: from kotlin metadata */
    private final MutableState isPlaying = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);

    /* JADX INFO: renamed from: positionMs$delegate, reason: from kotlin metadata */
    private final MutableLongState positionMs = SnapshotLongStateKt.mutableLongStateOf(0);

    /* JADX INFO: renamed from: durationMs$delegate, reason: from kotlin metadata */
    private final MutableLongState durationMs = SnapshotLongStateKt.mutableLongStateOf(0);

    /* JADX INFO: renamed from: hasPrevious$delegate, reason: from kotlin metadata */
    private final MutableState hasPrevious = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);

    /* JADX INFO: renamed from: hasNext$delegate, reason: from kotlin metadata */
    private final MutableState hasNext = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);

    /* JADX INFO: renamed from: queue$delegate, reason: from kotlin metadata */
    private final MutableState queue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(CollectionsKt.emptyList(), null, 2, null);

    /* JADX INFO: renamed from: currentIndex$delegate, reason: from kotlin metadata */
    private final MutableIntState currentIndex = SnapshotIntStateKt.mutableIntStateOf(-1);

    /* JADX INFO: renamed from: repeatMode$delegate, reason: from kotlin metadata */
    private final MutableIntState repeatMode = SnapshotIntStateKt.mutableIntStateOf(0);

    /* JADX INFO: renamed from: shuffleEnabled$delegate, reason: from kotlin metadata */
    private final MutableState shuffleEnabled = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);

    /* JADX INFO: renamed from: volume$delegate, reason: from kotlin metadata */
    private final MutableFloatState volume = PrimitiveSnapshotStateKt.mutableFloatStateOf(1.0f);
    private final MeloXPlaybackUiState$listener$1 listener = new Player.Listener() { // from class: com.lladlam.melox.ui.player.MeloXPlaybackUiState$listener$1
        @Override // androidx.media3.common.Player.Listener
        public void onEvents(Player player, Player.Events events) {
            Intrinsics.checkNotNullParameter(player, "player");
            Intrinsics.checkNotNullParameter(events, "events");
            this.this$0.refresh$app();
        }
    };

    private final void setMediaId(String str) {
        this.mediaId.setValue(str);
    }

    public final String getMediaId() {
        return (String) this.mediaId.getValue();
    }

    private final void setTitle(String str) {
        this.title.setValue(str);
    }

    public final String getTitle() {
        return (String) this.title.getValue();
    }

    private final void setArtist(String str) {
        this.artist.setValue(str);
    }

    public final String getArtist() {
        return (String) this.artist.getValue();
    }

    private final void setAlbum(String str) {
        this.album.setValue(str);
    }

    public final String getAlbum() {
        return (String) this.album.getValue();
    }

    private final void setArtworkUrl(String str) {
        this.artworkUrl.setValue(str);
    }

    public final String getArtworkUrl() {
        return (String) this.artworkUrl.getValue();
    }

    private final void setPlaying(boolean z) {
        this.isPlaying.setValue(Boolean.valueOf(z));
    }

    public final boolean isPlaying() {
        return ((Boolean) this.isPlaying.getValue()).booleanValue();
    }

    private final void setPositionMs(long j) {
        this.positionMs.setLongValue(j);
    }

    public final long getPositionMs() {
        return this.positionMs.getLongValue();
    }

    private final void setDurationMs(long j) {
        this.durationMs.setLongValue(j);
    }

    public final long getDurationMs() {
        return this.durationMs.getLongValue();
    }

    private final void setHasPrevious(boolean z) {
        this.hasPrevious.setValue(Boolean.valueOf(z));
    }

    public final boolean getHasPrevious() {
        return ((Boolean) this.hasPrevious.getValue()).booleanValue();
    }

    private final void setHasNext(boolean z) {
        this.hasNext.setValue(Boolean.valueOf(z));
    }

    public final boolean getHasNext() {
        return ((Boolean) this.hasNext.getValue()).booleanValue();
    }

    private final void setQueue(List<MeloXQueueEntry> list) {
        this.queue.setValue(list);
    }

    public final List<MeloXQueueEntry> getQueue() {
        return (List) this.queue.getValue();
    }

    private final void setCurrentIndex(int i) {
        this.currentIndex.setIntValue(i);
    }

    public final int getCurrentIndex() {
        return this.currentIndex.getIntValue();
    }

    private final void setRepeatMode(int i) {
        this.repeatMode.setIntValue(i);
    }

    public final int getRepeatMode() {
        return this.repeatMode.getIntValue();
    }

    private final void setShuffleEnabled(boolean z) {
        this.shuffleEnabled.setValue(Boolean.valueOf(z));
    }

    public final boolean getShuffleEnabled() {
        return ((Boolean) this.shuffleEnabled.getValue()).booleanValue();
    }

    private final void setVolume(float f) {
        this.volume.setFloatValue(f);
    }

    public final float getVolume() {
        return this.volume.getFloatValue();
    }

    public final boolean getHasMedia() {
        return getMediaId() != null;
    }

    public final String getRepeatModeTitle() {
        switch (getRepeatMode()) {
            case 1:
                return "单曲循环";
            case 2:
                return "列表循环";
            default:
                return "循环关闭";
        }
    }

    public final void bind$app(MediaController newController) {
        Intrinsics.checkNotNullParameter(newController, "newController");
        MediaController mediaController = this.controller;
        if (mediaController != null) {
            mediaController.removeListener(this.listener);
        }
        this.controller = newController;
        newController.addListener(this.listener);
        refresh$app();
    }

    public final void unbind$app() {
        MediaController mediaController = this.controller;
        if (mediaController != null) {
            mediaController.removeListener(this.listener);
        }
        MediaController mediaController2 = this.controller;
        if (mediaController2 != null) {
            mediaController2.release();
        }
        this.controller = null;
    }

    public final void refresh$app() {
        MediaController player = this.controller;
        if (player == null) {
            return;
        }
        MediaItem item = player.getCurrentMediaItem();
        MediaMetadata EMPTY = player.getMediaMetadata();
        if (Intrinsics.areEqual(EMPTY, MediaMetadata.EMPTY)) {
            EMPTY = null;
        }
        if (EMPTY == null) {
            EMPTY = item != null ? item.mediaMetadata : null;
            if (EMPTY == null) {
                EMPTY = MediaMetadata.EMPTY;
                Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
            }
        }
        setMediaId(item != null ? item.mediaId : null);
        CharSequence charSequence = EMPTY.title;
        String string = charSequence != null ? charSequence.toString() : null;
        if (string == null) {
            string = "";
        }
        setTitle(string);
        CharSequence charSequence2 = EMPTY.artist;
        String string2 = charSequence2 != null ? charSequence2.toString() : null;
        if (string2 == null) {
            string2 = "";
        }
        setArtist(string2);
        CharSequence charSequence3 = EMPTY.albumTitle;
        String string3 = charSequence3 != null ? charSequence3.toString() : null;
        setAlbum(string3 != null ? string3 : "");
        Uri uri = EMPTY.artworkUri;
        setArtworkUrl(uri != null ? uri.toString() : null);
        setPlaying(player.isPlaying());
        setPositionMs(RangesKt.coerceAtLeast(player.getCurrentPosition(), 0L));
        Long lValueOf = Long.valueOf(player.getDuration());
        long jLongValue = lValueOf.longValue();
        Long l = (jLongValue > C1565C.TIME_UNSET ? 1 : (jLongValue == C1565C.TIME_UNSET ? 0 : -1)) == 0 || (jLongValue > 0L ? 1 : (jLongValue == 0L ? 0 : -1)) < 0 ? null : lValueOf;
        setDurationMs(l != null ? l.longValue() : 0L);
        setHasPrevious(player.hasPreviousMediaItem());
        setHasNext(player.hasNextMediaItem());
        setCurrentIndex(player.getCurrentMediaItemIndex());
        setRepeatMode(player.getRepeatMode());
        setShuffleEnabled(player.getShuffleModeEnabled());
        setVolume(player.getVolume());
        setQueue(buildQueue(player));
    }

    private final List<MeloXQueueEntry> buildQueue(Player player) {
        int mediaItemCount = player.getMediaItemCount();
        ArrayList arrayList = new ArrayList(mediaItemCount);
        for (int i = 0; i < mediaItemCount; i++) {
            int i2 = i;
            MediaItem mediaItemAt = player.getMediaItemAt(i2);
            Intrinsics.checkNotNullExpressionValue(mediaItemAt, "getMediaItemAt(...)");
            MediaMetadata mediaMetadata = mediaItemAt.mediaMetadata;
            Intrinsics.checkNotNullExpressionValue(mediaMetadata, "mediaMetadata");
            String mediaId = mediaItemAt.mediaId;
            Intrinsics.checkNotNullExpressionValue(mediaId, "mediaId");
            CharSequence charSequence = mediaMetadata.title;
            String string = null;
            String string2 = charSequence != null ? charSequence.toString() : null;
            if (string2 == null) {
                string2 = "";
            }
            String str = string2;
            if (StringsKt.isBlank(str)) {
                str = "未知歌曲";
            }
            String str2 = str;
            CharSequence charSequence2 = mediaMetadata.artist;
            String string3 = charSequence2 != null ? charSequence2.toString() : null;
            String str3 = string3 != null ? string3 : "";
            Uri uri = mediaMetadata.artworkUri;
            if (uri != null) {
                string = uri.toString();
            }
            arrayList.add(new MeloXQueueEntry(i2, mediaId, str2, str3, string));
        }
        return arrayList;
    }

    public final void togglePlayPause() {
        MediaController mediaController = this.controller;
        if (mediaController != null) {
            if (mediaController.isPlaying()) {
                mediaController.pause();
            } else {
                mediaController.play();
            }
        }
    }

    public final void seekTo(long positionMs) {
        MediaController mediaController = this.controller;
        if (mediaController != null) {
            mediaController.seekTo(RangesKt.coerceIn(positionMs, 0L, RangesKt.coerceAtLeast(getDurationMs(), 0L)));
        }
    }

    public final void previous() {
        MediaController mediaController = this.controller;
        if (mediaController != null) {
            mediaController.seekToPreviousMediaItem();
        }
    }

    public final void next() {
        MediaController mediaController = this.controller;
        if (mediaController != null) {
            mediaController.seekToNextMediaItem();
        }
    }

    public final void playQueueIndex(int index) {
        MediaController player = this.controller;
        if (player == null) {
            return;
        }
        boolean z = false;
        if (index >= 0 && index < player.getMediaItemCount()) {
            z = true;
        }
        if (z) {
            player.seekToDefaultPosition(index);
            player.play();
        }
    }

    public final void toggleShuffle() {
        MediaController mediaController = this.controller;
        if (mediaController != null) {
            mediaController.setShuffleModeEnabled(!mediaController.getShuffleModeEnabled());
            refresh$app();
        }
    }

    public final void cycleRepeatMode() {
        int i;
        MediaController mediaController = this.controller;
        if (mediaController != null) {
            switch (mediaController.getRepeatMode()) {
                case 0:
                    i = 2;
                    break;
                case 1:
                default:
                    i = 0;
                    break;
                case 2:
                    i = 1;
                    break;
            }
            mediaController.setRepeatMode(i);
            refresh$app();
        }
    }

    public final void changeVolume(float value) {
        MediaController mediaController = this.controller;
        if (mediaController != null) {
            mediaController.setVolume(RangesKt.coerceIn(value, 0.0f, 1.0f));
            setVolume(mediaController.getVolume());
        }
    }
}
