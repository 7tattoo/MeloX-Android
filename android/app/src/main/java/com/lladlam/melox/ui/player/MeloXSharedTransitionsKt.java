package com.lladlam.melox.ui.player;

import kotlin.Metadata;

/* JADX INFO: compiled from: MeloXSharedTransitions.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0000¨\u0006\u0004"}, d2 = {"sharedArtworkKey", "Lcom/lladlam/melox/ui/player/MeloXSharedArtworkKey;", "mediaId", "", "app"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class MeloXSharedTransitionsKt {
    public static final MeloXSharedArtworkKey sharedArtworkKey(String mediaId) {
        return new MeloXSharedArtworkKey(mediaId == null ? "" : mediaId);
    }
}
