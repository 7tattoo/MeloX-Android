package com.lladlam.melox.p012ui.library;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.compose.ui.graphics.ColorKt;
import com.google.common.net.HttpHeaders;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.io.CloseableKt;
import kotlin.ranges.RangesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: compiled from: MeloXDetailPalette.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\nH\u0086@¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\nH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/lladlam/melox/ui/library/MeloXDetailPaletteProvider;", "", "<init>", "()V", "TARGET_SIZE", "", "http", "Lokhttp3/OkHttpClient;", "cache", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/lladlam/melox/ui/library/MeloXDetailPalette;", "paletteFor", "url", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "makePalette", "bitmap", "Landroid/graphics/Bitmap;", "optimizedArtworkUrl", "source", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class MeloXDetailPaletteProvider {
    private static final int TARGET_SIZE = 160;
    public static final MeloXDetailPaletteProvider INSTANCE = new MeloXDetailPaletteProvider();
    private static final OkHttpClient http = new OkHttpClient();
    private static final ConcurrentHashMap<String, MeloXDetailPalette> cache = new ConcurrentHashMap<>();
    public static final int $stable = 8;

    private MeloXDetailPaletteProvider() {
    }

    public final Object paletteFor(String url, Continuation<? super MeloXDetailPalette> continuation) {
        if (url != null) {
            String source = !StringsKt.isBlank(url) ? url : null;
            if (source != null) {
                MeloXDetailPalette meloXDetailPalette = cache.get(source);
                if (meloXDetailPalette != null) {
                    return meloXDetailPalette;
                }
                return BuildersKt.withContext(Dispatchers.getIO(), new C26493(source, null), continuation);
            }
        }
        return MeloXDetailPalette.INSTANCE.getLightFallback();
    }

    /* JADX INFO: renamed from: com.lladlam.melox.ui.library.MeloXDetailPaletteProvider$paletteFor$3 */
    /* JADX INFO: compiled from: MeloXDetailPalette.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/lladlam/melox/ui/library/MeloXDetailPalette;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lladlam.melox.ui.library.MeloXDetailPaletteProvider$paletteFor$3", f = "MeloXDetailPalette.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C26493 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super MeloXDetailPalette>, Object> {
        final /* synthetic */ String $source;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C26493(String str, Continuation<? super C26493> continuation) {
            super(2, continuation);
            this.$source = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C26493 c26493 = new C26493(this.$source, continuation);
            c26493.L$0 = obj;
            return c26493;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super MeloXDetailPalette> continuation) {
            return ((C26493) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:51:0x0119  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object objM9714constructorimpl;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    MeloXDetailPalette meloXDetailPalette = (MeloXDetailPalette) MeloXDetailPaletteProvider.cache.get(this.$source);
                    if (meloXDetailPalette != null) {
                        return meloXDetailPalette;
                    }
                    String str = this.$source;
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        Response responseExecute = MeloXDetailPaletteProvider.http.newCall(new Request.Builder().url(MeloXDetailPaletteProvider.INSTANCE.optimizedArtworkUrl(str)).header(HttpHeaders.USER_AGENT, "MeloX-Android/0.1").build()).execute();
                        try {
                            Response response = responseExecute;
                            if (!response.getIsSuccessful()) {
                                throw new IllegalStateException(("Artwork HTTP " + response.code()).toString());
                            }
                            byte[] bArrBytes = response.body().bytes();
                            Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrBytes, 0, bArrBytes.length);
                            if (bitmapDecodeByteArray == null) {
                                throw new IllegalStateException("Unable to decode artwork".toString());
                            }
                            int iMax = Math.max(bitmapDecodeByteArray.getWidth(), bitmapDecodeByteArray.getHeight());
                            float f = iMax > MeloXDetailPaletteProvider.TARGET_SIZE ? 160.0f / iMax : 1.0f;
                            Bitmap bitmapCreateScaledBitmap = f < 1.0f ? Bitmap.createScaledBitmap(bitmapDecodeByteArray, RangesKt.coerceAtLeast((int) (bitmapDecodeByteArray.getWidth() * f), 1), RangesKt.coerceAtLeast((int) (bitmapDecodeByteArray.getHeight() * f), 1), true) : bitmapDecodeByteArray;
                            Intrinsics.checkNotNull(bitmapCreateScaledBitmap);
                            try {
                                MeloXDetailPalette meloXDetailPaletteMakePalette = MeloXDetailPaletteProvider.INSTANCE.makePalette(bitmapCreateScaledBitmap);
                                if (bitmapCreateScaledBitmap != bitmapDecodeByteArray) {
                                    bitmapCreateScaledBitmap.recycle();
                                }
                                bitmapDecodeByteArray.recycle();
                                CloseableKt.closeFinally(responseExecute, null);
                                objM9714constructorimpl = Result.constructor_impl(meloXDetailPaletteMakePalette);
                                if (Result.exceptionOrNull_impl(objM9714constructorimpl) != null) {
                                    objM9714constructorimpl = MeloXDetailPalette.INSTANCE.getLightFallback();
                                }
                                MeloXDetailPalette palette = (MeloXDetailPalette) objM9714constructorimpl;
                                MeloXDetailPaletteProvider.cache.put(this.$source, palette);
                                return palette;
                            } catch (Throwable th) {
                                if (bitmapCreateScaledBitmap != bitmapDecodeByteArray) {
                                    bitmapCreateScaledBitmap.recycle();
                                }
                                bitmapDecodeByteArray.recycle();
                                throw th;
                            }
                        } catch (Throwable th2) {
                            try {
                                throw th2;
                            } catch (Throwable th3) {
                                CloseableKt.closeFinally(responseExecute, th2);
                                throw th3;
                            }
                        }
                    } catch (Throwable th4) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM9714constructorimpl = Result.constructor_impl(ResultKt.createFailure(th4));
                    }
                    Result.Companion companion3 = Result.INSTANCE;
                    objM9714constructorimpl = Result.constructor_impl(ResultKt.createFailure(th4));
                    if (Result.exceptionOrNull_impl(objM9714constructorimpl) != null) {
                        objM9714constructorimpl = MeloXDetailPalette.INSTANCE.getLightFallback();
                    }
                    MeloXDetailPalette palette2 = (MeloXDetailPalette) objM9714constructorimpl;
                    MeloXDetailPaletteProvider.cache.put(this.$source, palette2);
                    return palette2;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MeloXDetailPalette makePalette(Bitmap bitmap) {
        double r = 0.0d;
        double g = 0.0d;
        double b = 0.0d;
        long count = 0;
        int height = bitmap.getHeight();
        for (int y = 0; y < height; y++) {
            int width = bitmap.getWidth();
            for (int x = 0; x < width; x++) {
                int pixel = bitmap.getPixel(x, y);
                r += ((double) ((pixel >> 16) & 255)) / 255.0d;
                g += ((double) ((pixel >> 8) & 255)) / 255.0d;
                b += ((double) (pixel & 255)) / 255.0d;
                count++;
            }
        }
        if (count == 0) {
            return MeloXDetailPalette.INSTANCE.getLightFallback();
        }
        double r2 = r / count;
        double g2 = g / count;
        double b2 = b / count;
        double luminance = (0.2126d * r2) + (0.7152d * g2) + (0.0722d * b2);
        boolean dark = luminance < 0.52d;
        double mix = dark ? 0.055d : 0.94d;
        double sourceWeight = dark ? 0.38d : 0.3d;
        double neutralWeight = dark ? 0.62d : 0.7d;
        long background = ColorKt.Color$default(RangesKt.coerceIn((float) ((r2 * sourceWeight) + (mix * neutralWeight)), 0.0f, 1.0f), RangesKt.coerceIn((float) ((g2 * sourceWeight) + (mix * neutralWeight)), 0.0f, 1.0f), RangesKt.coerceIn((float) ((b2 * sourceWeight) + (mix * neutralWeight)), 0.0f, 1.0f), 1.0f, null, 16, null);
        return new MeloXDetailPalette(background, dark, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String optimizedArtworkUrl(String source) {
        if (!StringsKt.contains$default((CharSequence) source, (CharSequence) ".music.126.net", false, 2, (Object) null)) {
            return source;
        }
        String withoutParam = StringsKt.trimEnd(new Regex("([?&])param=[^&]*&?", RegexOption.IGNORE_CASE).replace(source, new Function1() { // from class: com.lladlam.melox.ui.library.MeloXDetailPaletteProvider$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MeloXDetailPaletteProvider.optimizedArtworkUrl$lambda$0((MatchResult) obj);
            }
        }), '?', Typography.amp);
        char separator = StringsKt.contains$default((CharSequence) withoutParam, '?', false, 2, (Object) null) ? Typography.amp : '?';
        return withoutParam + separator + "param=160y160";
    }

    static final CharSequence optimizedArtworkUrl$lambda$0(MatchResult match) {
        Intrinsics.checkNotNullParameter(match, "match");
        return StringsKt.startsWith$default(match.getValue(), "?", false, 2, (Object) null) ? "?" : "&";
    }
}
