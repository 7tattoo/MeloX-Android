package com.lladlam.melox.ui.player;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.common.net.HttpHeaders;
import java.net.URI;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.io.CloseableKt;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: compiled from: ArtworkDynamicPalette.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\r\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\tH\u0086@¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J7\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\tH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/lladlam/melox/ui/player/ArtworkDynamicPaletteProvider;", "", "<init>", "()V", "GRID", "", "TARGET_SIZE", "cache", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/lladlam/melox/ui/player/ArtworkDynamicPalette;", "http", "Lokhttp3/OkHttpClient;", "paletteFor", "url", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "makePalette", "bitmap", "Landroid/graphics/Bitmap;", "averageColor", "Landroidx/compose/ui/graphics/Color;", TtmlNode.LEFT, "top", TtmlNode.RIGHT, "bottom", "averageColor-JlNiLsg", "(Landroid/graphics/Bitmap;IIII)J", "optimizedArtworkUrl", "source", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ArtworkDynamicPaletteProvider {
    private static final int GRID = 3;
    private static final int TARGET_SIZE = 160;
    public static final ArtworkDynamicPaletteProvider INSTANCE = new ArtworkDynamicPaletteProvider();
    private static final ConcurrentHashMap<String, ArtworkDynamicPalette> cache = new ConcurrentHashMap<>();
    private static final OkHttpClient http = new OkHttpClient();
    public static final int $stable = 8;

    private ArtworkDynamicPaletteProvider() {
    }

    public final Object paletteFor(String url, Continuation<? super ArtworkDynamicPalette> continuation) {
        if (url != null) {
            String source = !StringsKt.isBlank(url) ? url : null;
            if (source != null) {
                ArtworkDynamicPalette artworkDynamicPalette = cache.get(source);
                if (artworkDynamicPalette != null) {
                    return artworkDynamicPalette;
                }
                return BuildersKt.withContext(Dispatchers.getIO(), new C26503(source, null), continuation);
            }
        }
        return ArtworkDynamicPalette.INSTANCE.getFallback();
    }

    /* JADX INFO: renamed from: com.lladlam.melox.ui.player.ArtworkDynamicPaletteProvider$paletteFor$3 */
    /* JADX INFO: compiled from: ArtworkDynamicPalette.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/lladlam/melox/ui/player/ArtworkDynamicPalette;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lladlam.melox.ui.player.ArtworkDynamicPaletteProvider$paletteFor$3", f = "ArtworkDynamicPalette.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C26503 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArtworkDynamicPalette>, Object> {
        final /* synthetic */ String $source;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C26503(String str, Continuation<? super C26503> continuation) {
            super(2, continuation);
            this.$source = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C26503 c26503 = new C26503(this.$source, continuation);
            c26503.L$0 = obj;
            return c26503;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ArtworkDynamicPalette> continuation) {
            return ((C26503) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:49:0x00f4  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object objM9714constructorimpl;
            Bitmap bitmapCreateScaledBitmap;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    ArtworkDynamicPalette artworkDynamicPalette = (ArtworkDynamicPalette) ArtworkDynamicPaletteProvider.cache.get(this.$source);
                    if (artworkDynamicPalette != null) {
                        return artworkDynamicPalette;
                    }
                    String str = this.$source;
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        Response responseExecute = ArtworkDynamicPaletteProvider.http.newCall(new Request.Builder().url(ArtworkDynamicPaletteProvider.INSTANCE.optimizedArtworkUrl(str)).header(HttpHeaders.USER_AGENT, "MeloX-Android/0.1").build()).execute();
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
                            if (bitmapDecodeByteArray.getWidth() == ArtworkDynamicPaletteProvider.TARGET_SIZE && bitmapDecodeByteArray.getHeight() == ArtworkDynamicPaletteProvider.TARGET_SIZE) {
                                bitmapCreateScaledBitmap = bitmapDecodeByteArray;
                            } else {
                                bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapDecodeByteArray, ArtworkDynamicPaletteProvider.TARGET_SIZE, ArtworkDynamicPaletteProvider.TARGET_SIZE, true);
                                Intrinsics.checkNotNull(bitmapCreateScaledBitmap);
                            }
                            try {
                                ArtworkDynamicPalette artworkDynamicPaletteMakePalette = ArtworkDynamicPaletteProvider.INSTANCE.makePalette(bitmapCreateScaledBitmap);
                                if (bitmapCreateScaledBitmap != bitmapDecodeByteArray) {
                                    bitmapCreateScaledBitmap.recycle();
                                }
                                bitmapDecodeByteArray.recycle();
                                CloseableKt.closeFinally(responseExecute, null);
                                objM9714constructorimpl = Result.constructor-impl(artworkDynamicPaletteMakePalette);
                                if (Result.m9717exceptionOrNullimpl(objM9714constructorimpl) != null) {
                                    objM9714constructorimpl = ArtworkDynamicPalette.INSTANCE.getFallback();
                                }
                                ArtworkDynamicPalette palette = (ArtworkDynamicPalette) objM9714constructorimpl;
                                ArtworkDynamicPaletteProvider.cache.put(this.$source, palette);
                                return palette;
                            } catch (Throwable th) {
                                if (bitmapCreateScaledBitmap != bitmapDecodeByteArray) {
                                    bitmapCreateScaledBitmap.recycle();
                                }
                                bitmapDecodeByteArray.recycle();
                                throw th;
                            }
                            if (Result.m9717exceptionOrNullimpl(objM9714constructorimpl) != null) {
                                objM9714constructorimpl = ArtworkDynamicPalette.INSTANCE.getFallback();
                            }
                            ArtworkDynamicPalette palette2 = (ArtworkDynamicPalette) objM9714constructorimpl;
                            ArtworkDynamicPaletteProvider.cache.put(this.$source, palette2);
                            return palette2;
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
                        objM9714constructorimpl = Result.constructor-impl(ResultKt.createFailure(th4));
                    }
                    Result.Companion companion3 = Result.INSTANCE;
                    objM9714constructorimpl = Result.constructor-impl(ResultKt.createFailure(th4));
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ArtworkDynamicPalette makePalette(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int cellWidth = width / 3;
        int cellHeight = height / 3;
        List listCreateListBuilder = CollectionsKt.createListBuilder(9);
        int i = 0;
        while (i < 3) {
            int i2 = 0;
            while (i2 < 3) {
                listCreateListBuilder.add(Color.m6058boximpl(INSTANCE.m9672averageColorJlNiLsg(bitmap, i2 * cellWidth, i * cellHeight, i2 == 2 ? width : (i2 + 1) * cellWidth, i == 2 ? height : (i + 1) * cellHeight)));
                i2++;
            }
            i++;
        }
        List cells = CollectionsKt.build(listCreateListBuilder);
        return new ArtworkDynamicPalette(cells, m9672averageColorJlNiLsg(bitmap, 0, 0, width, height), null);
    }

    /* JADX INFO: renamed from: averageColor-JlNiLsg, reason: not valid java name */
    private final long m9672averageColorJlNiLsg(Bitmap bitmap, int left, int top, int right, int bottom) {
        long red = 0;
        long green = 0;
        long blue = 0;
        long count = 0;
        for (int y = top; y < bottom; y += 2) {
            for (int x = left; x < right; x += 2) {
                int pixel = bitmap.getPixel(x, y);
                red += (long) ((pixel >> 16) & 255);
                green += (long) ((pixel >> 8) & 255);
                blue += (long) (pixel & 255);
                count++;
            }
        }
        return count == 0 ? ColorKt.Color(4284173125L) : ColorKt.Color$default(RangesKt.coerceIn((red / count) / 255.0f, 0.0f, 1.0f), RangesKt.coerceIn((green / count) / 255.0f, 0.0f, 1.0f), RangesKt.coerceIn((blue / count) / 255.0f, 0.0f, 1.0f), 1.0f, null, 16, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:17:0x003a  */
    public final String optimizedArtworkUrl(String source) {
        Object objM9714constructorimpl;
        boolean z;
        try {
            Result.Companion companion = Result.INSTANCE;
            ArtworkDynamicPaletteProvider artworkDynamicPaletteProvider = this;
            objM9714constructorimpl = Result.constructor-impl(new URI(source));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM9714constructorimpl = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.m9720isFailureimpl(objM9714constructorimpl)) {
            objM9714constructorimpl = null;
        }
        URI uri = (URI) objM9714constructorimpl;
        if (uri == null) {
            return source;
        }
        String host = uri.getHost();
        if (host != null) {
            z = StringsKt.endsWith$default(host, ".music.126.net", false, 2, (Object) null);
        }
        if (!z) {
            return source;
        }
        char separator = StringsKt.contains$default((CharSequence) source, '?', false, 2, (Object) null) ? Typography.amp : '?';
        return StringsKt.contains$default((CharSequence) source, (CharSequence) "param=", false, 2, (Object) null) ? source : source + separator + "param=160y160";
    }
}
