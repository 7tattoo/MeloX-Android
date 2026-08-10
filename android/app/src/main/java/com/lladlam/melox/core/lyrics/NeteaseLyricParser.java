package com.lladlam.melox.core.lyrics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: LyricModels.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J>\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\f2\b\b\u0002\u0010\u0011\u001a\u00020\fJ\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\fJ\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\fJ\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J \u0010\u0018\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0019\u001a\u00020\u00142\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0002J\u001c\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/lladlam/melox/core/lyrics/NeteaseLyricParser;", "", "<init>", "()V", "ANNOTATION_TOLERANCE_MS", "", "lrcTimestamp", "Lkotlin/text/Regex;", "yrcSyllableTiming", "parse", "Lcom/lladlam/melox/core/lyrics/LyricsDocument;", "yrc", "", "lrc", "translatedYrc", "translatedLrc", "romanizedYrc", "romanizedLrc", "parseLrc", "", "Lcom/lladlam/melox/core/lyrics/LyricLine;", "source", "parseYrc", "selectSecondary", "nearestSecondary", "target", "candidates", "inferDurations", "lines", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class NeteaseLyricParser {
    private static final long ANNOTATION_TOLERANCE_MS = 750;
    public static final NeteaseLyricParser INSTANCE = new NeteaseLyricParser();
    private static final Regex lrcTimestamp = new Regex("\\[(\\d+):(\\d+(?:[.:]\\d+)?)\\]");
    private static final Regex yrcSyllableTiming = new Regex("\\((\\d+),(\\d+),(\\d+)\\)");
    public static final int $stable = 8;

    private NeteaseLyricParser() {
    }

    public static /* synthetic */ LyricsDocument parse$default(NeteaseLyricParser neteaseLyricParser, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = "";
        }
        if ((i & 8) != 0) {
            str4 = "";
        }
        if ((i & 16) != 0) {
            str5 = "";
        }
        if ((i & 32) != 0) {
            str6 = "";
        }
        return neteaseLyricParser.parse(str, str2, str3, str4, str5, str6);
    }

    public final LyricsDocument parse(String yrc, String lrc, String translatedYrc, String translatedLrc, String romanizedYrc, String romanizedLrc) {
        Intrinsics.checkNotNullParameter(yrc, "yrc");
        Intrinsics.checkNotNullParameter(lrc, "lrc");
        Intrinsics.checkNotNullParameter(translatedYrc, "translatedYrc");
        Intrinsics.checkNotNullParameter(translatedLrc, "translatedLrc");
        Intrinsics.checkNotNullParameter(romanizedYrc, "romanizedYrc");
        Intrinsics.checkNotNullParameter(romanizedLrc, "romanizedLrc");
        List<LyricLine> yrc2 = parseYrc(yrc);
        List<LyricLine> lrc2 = !yrc2.isEmpty() ? yrc2 : parseLrc(lrc);
        if (lrc2.isEmpty()) {
            return new LyricsDocument(CollectionsKt.emptyList());
        }
        List<LyricLine> listSelectSecondary = selectSecondary(translatedYrc, translatedLrc);
        List<LyricLine> listSelectSecondary2 = selectSecondary(romanizedYrc, romanizedLrc);
        Iterable<LyricLine> iterable = lrc2;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (LyricLine lyricLine : iterable) {
            arrayList.add(LyricLine.copy$default(lyricLine, 0L, null, null, null, INSTANCE.nearestSecondary(lyricLine, listSelectSecondary), INSTANCE.nearestSecondary(lyricLine, listSelectSecondary2), 15, null));
        }
        return new LyricsDocument((List) arrayList);
    }

    public final List<LyricLine> parseLrc(String source) {
        Intrinsics.checkNotNullParameter(source, "source");
        Iterable result = (List) new ArrayList();
        for (String raw : StringsKt.lineSequence(source)) {
            List<MatchResult> matches = SequencesKt.toList(Regex.findAll$default(lrcTimestamp, raw, 0, 2, null));
            if (!matches.isEmpty()) {
                int i = 1;
                String strSubstring = raw.substring(((MatchResult) CollectionsKt.last(matches)).getRange().getLast() + 1);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                String text = StringsKt.trim((CharSequence) strSubstring).toString();
                if (!StringsKt.isBlank(text)) {
                    for (MatchResult match : matches) {
                        Long longOrNull = StringsKt.toLongOrNull(match.getGroupValues().get(i));
                        if (longOrNull == null) {
                            i = 1;
                        } else {
                            long minutes = longOrNull.longValue();
                            Double doubleOrNull = StringsKt.toDoubleOrNull(StringsKt.replace$default(match.getGroupValues().get(2), ':', '.', false, 4, (Object) null));
                            if (doubleOrNull != null) {
                                double seconds = doubleOrNull.doubleValue();
                                ((Collection) result).add(new LyricLine((long) (((minutes * 60.0d) + seconds) * 1000.0d), null, text, null, null, null, 58, null));
                                i = 1;
                            } else {
                                i = 1;
                            }
                        }
                    }
                }
            }
        }
        return inferDurations(CollectionsKt.sortedWith(result, new Comparator() { // from class: com.lladlam.melox.core.lyrics.NeteaseLyricParser$parseLrc$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Long.valueOf(((LyricLine) t).getTimeMs()), Long.valueOf(((LyricLine) t2).getTimeMs()));
            }
        }));
    }

    public final List<LyricLine> parseYrc(String source) {
        Long longOrNull;
        IntRange range;
        Intrinsics.checkNotNullParameter(source, "source");
        List result = new ArrayList();
        Iterator<String> it = StringsKt.lineSequence(source).iterator();
        while (it.hasNext()) {
            String raw = it.next();
            String line = StringsKt.trim((CharSequence) raw).toString();
            int i = 2;
            if (StringsKt.startsWith$default((CharSequence) line, '[', false, 2, (Object) null)) {
                int close = StringsKt.indexOf$default((CharSequence) line, ']', 0, false, 6, (Object) null);
                int i2 = 1;
                if (close > 1) {
                    String strSubstring = line.substring(1, close);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                    List timing = StringsKt.split$default((CharSequence) strSubstring, new char[]{','}, false, 0, 6, (Object) null);
                    String str = (String) CollectionsKt.getOrNull(timing, 0);
                    if (str == null || (longOrNull = StringsKt.toLongOrNull(str)) == null) {
                        result = result;
                        it = it;
                    } else {
                        long startMs = longOrNull.longValue();
                        String str2 = (String) CollectionsKt.getOrNull(timing, 1);
                        Long durationMs = str2 != null ? StringsKt.toLongOrNull(str2) : null;
                        String content = line.substring(close + 1);
                        Intrinsics.checkNotNullExpressionValue(content, "substring(...)");
                        List<MatchResult> matches = SequencesKt.toList(Regex.findAll$default(yrcSyllableTiming, content, 0, 2, null));
                        if (matches.isEmpty()) {
                            String text = StringsKt.trim((CharSequence) content).toString();
                            if (!StringsKt.isBlank(text)) {
                                result.add(new LyricLine(startMs, durationMs, text, null, null, null, 56, null));
                            }
                        } else {
                            List listCreateListBuilder = CollectionsKt.createListBuilder();
                            int i3 = 0;
                            for (MatchResult matchResult : matches) {
                                int i4 = i3;
                                i3++;
                                Long longOrNull2 = StringsKt.toLongOrNull(matchResult.getGroupValues().get(i2));
                                if (longOrNull2 != null) {
                                    long jLongValue = longOrNull2.longValue();
                                    Long longOrNull3 = StringsKt.toLongOrNull(matchResult.getGroupValues().get(i));
                                    if (longOrNull3 != null) {
                                        long jLongValue2 = longOrNull3.longValue();
                                        int last = matchResult.getRange().getLast() + i2;
                                        MatchResult matchResult2 = (MatchResult) CollectionsKt.getOrNull(matches, i4 + 1);
                                        int length = (matchResult2 == null || (range = matchResult2.getRange()) == null) ? content.length() : range.getFirst();
                                        if (length >= last) {
                                            String strSubstring2 = content.substring(last, length);
                                            Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                                            if (!(strSubstring2.length() == 0)) {
                                                listCreateListBuilder.add(new LyricSyllable(strSubstring2, jLongValue, jLongValue + RangesKt.coerceAtLeast(jLongValue2, 1L)));
                                            }
                                        }
                                    }
                                }
                                result = result;
                                it = it;
                                raw = raw;
                                i2 = 1;
                                i = 2;
                            }
                            List result2 = result;
                            Iterator<String> it2 = it;
                            List syllables = CollectionsKt.build(listCreateListBuilder);
                            String text2 = StringsKt.trim((CharSequence) CollectionsKt.joinToString$default(syllables, "", null, null, 0, null, new Function1() { // from class: com.lladlam.melox.core.lyrics.NeteaseLyricParser$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return NeteaseLyricParser.parseYrc$lambda$1((LyricSyllable) obj);
                                }
                            }, 30, null)).toString();
                            if (StringsKt.isBlank(text2)) {
                                result = result2;
                                it = it2;
                            } else {
                                result2.add(new LyricLine(startMs, durationMs, text2, syllables, null, null, 48, null));
                                result = result2;
                                it = it2;
                            }
                        }
                    }
                }
            }
        }
        return CollectionsKt.sortedWith(result, new Comparator() { // from class: com.lladlam.melox.core.lyrics.NeteaseLyricParser$parseYrc$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Long.valueOf(((LyricLine) t).getTimeMs()), Long.valueOf(((LyricLine) t2).getTimeMs()));
            }
        });
    }

    static final CharSequence parseYrc$lambda$1(LyricSyllable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getText();
    }

    private final List<LyricLine> selectSecondary(String yrc, String lrc) {
        List<LyricLine> yrc2 = parseYrc(yrc);
        return !yrc2.isEmpty() ? yrc2 : parseLrc(lrc);
    }

    private final String nearestSecondary(LyricLine target, List<LyricLine> candidates) {
        Object next;
        if (candidates.isEmpty()) {
            return null;
        }
        Iterator it = candidates.iterator();
        if (it.hasNext()) {
            next = it.next();
            if (it.hasNext()) {
                long jAbs = Math.abs(((LyricLine) next).getTimeMs() - target.getTimeMs());
                do {
                    Object next2 = it.next();
                    long jAbs2 = Math.abs(((LyricLine) next2).getTimeMs() - target.getTimeMs());
                    if (jAbs > jAbs2) {
                        next = next2;
                        jAbs = jAbs2;
                    }
                } while (it.hasNext());
            }
        } else {
            next = null;
        }
        LyricLine candidate = (LyricLine) next;
        if (candidate == null || Math.abs(candidate.getTimeMs() - target.getTimeMs()) > ANNOTATION_TOLERANCE_MS) {
            return null;
        }
        String text = StringsKt.trim((CharSequence) candidate.getText()).toString();
        if ((StringsKt.isBlank(text) || Intrinsics.areEqual(text, StringsKt.trim((CharSequence) target.getText()).toString())) ? false : true) {
            return text;
        }
        return null;
    }

    private final List<LyricLine> inferDurations(List<LyricLine> lines) {
        List<LyricLine> list = lines;
        List<LyricLine> list2 = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        int i = 0;
        for (Object obj : list2) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            LyricLine lyricLineCopy$default = (LyricLine) obj;
            if (lyricLineCopy$default.getDurationMs() != null) {
                list2 = list2;
            } else {
                LyricLine lyricLine = (LyricLine) CollectionsKt.getOrNull(list, i + 1);
                Long lValueOf = lyricLine != null ? Long.valueOf(lyricLine.getTimeMs()) : null;
                lyricLineCopy$default = LyricLine.copy$default(lyricLineCopy$default, 0L, Long.valueOf(lValueOf != null ? RangesKt.coerceAtLeast(lValueOf.longValue() - lyricLineCopy$default.getTimeMs(), 100L) : RangesKt.coerceIn(((long) lyricLineCopy$default.getText().length()) * 320, 2000L, 8000L)), null, null, null, null, 61, null);
            }
            arrayList.add(lyricLineCopy$default);
            list = lines;
            i = i2;
            list2 = list2;
        }
        return (List) arrayList;
    }
}
