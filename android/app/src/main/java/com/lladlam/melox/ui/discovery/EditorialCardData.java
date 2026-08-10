package com.lladlam.melox.ui.discovery;

import androidx.compose.ui.graphics.Color;
import com.lladlam.melox.core.library.NeteasePlaylistSummary;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MeloXDiscoveryScreens.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\t\u0010\u001d\u001a\u00020\nHÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\fHÆ\u0003JM\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fHÆ\u0001J\u0014\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010#\u001a\u00020$HÖ\u0081\u0004J\n\u0010%\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006&"}, d2 = {"Lcom/lladlam/melox/ui/discovery/EditorialCardData;", "", "eyebrow", "", "title", "subtitle", "colors", "", "Landroidx/compose/ui/graphics/Color;", "glyph", "Lcom/lladlam/melox/ui/discovery/EditorialGlyph;", "playlist", "Lcom/lladlam/melox/core/library/NeteasePlaylistSummary;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/lladlam/melox/ui/discovery/EditorialGlyph;Lcom/lladlam/melox/core/library/NeteasePlaylistSummary;)V", "getEyebrow", "()Ljava/lang/String;", "getTitle", "getSubtitle", "getColors", "()Ljava/util/List;", "getGlyph", "()Lcom/lladlam/melox/ui/discovery/EditorialGlyph;", "getPlaylist", "()Lcom/lladlam/melox/core/library/NeteasePlaylistSummary;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class EditorialCardData {
    private final List<Color> colors;
    private final String eyebrow;
    private final EditorialGlyph glyph;
    private final NeteasePlaylistSummary playlist;
    private final String subtitle;
    private final String title;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ EditorialCardData copy$default(EditorialCardData editorialCardData, String str, String str2, String str3, List list, EditorialGlyph editorialGlyph, NeteasePlaylistSummary neteasePlaylistSummary, int i, Object obj) {
        if ((i & 1) != 0) {
            str = editorialCardData.eyebrow;
        }
        if ((i & 2) != 0) {
            str2 = editorialCardData.title;
        }
        if ((i & 4) != 0) {
            str3 = editorialCardData.subtitle;
        }
        if ((i & 8) != 0) {
            list = editorialCardData.colors;
        }
        if ((i & 16) != 0) {
            editorialGlyph = editorialCardData.glyph;
        }
        if ((i & 32) != 0) {
            neteasePlaylistSummary = editorialCardData.playlist;
        }
        EditorialGlyph editorialGlyph2 = editorialGlyph;
        NeteasePlaylistSummary neteasePlaylistSummary2 = neteasePlaylistSummary;
        return editorialCardData.copy(str, str2, str3, list, editorialGlyph2, neteasePlaylistSummary2);
    }




    public final List<Color> component4() {
        return this.colors;
    }



    public final EditorialCardData copy(String eyebrow, String title, String subtitle, List<Color> colors, EditorialGlyph glyph, NeteasePlaylistSummary playlist) {
        Intrinsics.checkNotNullParameter(eyebrow, "eyebrow");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(subtitle, "subtitle");
        Intrinsics.checkNotNullParameter(colors, "colors");
        Intrinsics.checkNotNullParameter(glyph, "glyph");
        return new EditorialCardData(eyebrow, title, subtitle, colors, glyph, playlist);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EditorialCardData)) {
            return false;
        }
        EditorialCardData editorialCardData = (EditorialCardData) other;
        return Intrinsics.areEqual(this.eyebrow, editorialCardData.eyebrow) && Intrinsics.areEqual(this.title, editorialCardData.title) && Intrinsics.areEqual(this.subtitle, editorialCardData.subtitle) && Intrinsics.areEqual(this.colors, editorialCardData.colors) && this.glyph == editorialCardData.glyph && Intrinsics.areEqual(this.playlist, editorialCardData.playlist);
    }

    public int hashCode() {
        return (((((((((this.eyebrow.hashCode() * 31) + this.title.hashCode()) * 31) + this.subtitle.hashCode()) * 31) + this.colors.hashCode()) * 31) + this.glyph.hashCode()) * 31) + (this.playlist == null ? 0 : this.playlist.hashCode());
    }

    public String toString() {
        return "EditorialCardData(eyebrow=" + this.eyebrow + ", title=" + this.title + ", subtitle=" + this.subtitle + ", colors=" + this.colors + ", glyph=" + this.glyph + ", playlist=" + this.playlist + ")";
    }

    public EditorialCardData(String eyebrow, String title, String subtitle, List<Color> colors, EditorialGlyph glyph, NeteasePlaylistSummary playlist) {
        Intrinsics.checkNotNullParameter(eyebrow, "eyebrow");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(subtitle, "subtitle");
        Intrinsics.checkNotNullParameter(colors, "colors");
        Intrinsics.checkNotNullParameter(glyph, "glyph");
        this.eyebrow = eyebrow;
        this.title = title;
        this.subtitle = subtitle;
        this.colors = colors;
        this.glyph = glyph;
        this.playlist = playlist;
    }

    public final String getEyebrow() {
        return this.eyebrow;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getSubtitle() {
        return this.subtitle;
    }

    public final List<Color> getColors() {
        return this.colors;
    }

    public final EditorialGlyph getGlyph() {
        return this.glyph;
    }

    public final NeteasePlaylistSummary getPlaylist() {
        return this.playlist;
    }
}
