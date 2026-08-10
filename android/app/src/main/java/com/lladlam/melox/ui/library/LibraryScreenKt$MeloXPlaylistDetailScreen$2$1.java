package com.lladlam.melox.p012ui.library;

import androidx.compose.runtime.MutableState;
import com.lladlam.melox.core.library.NeteasePlaylistSummary;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LibraryScreen.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(m719c = "com.lladlam.melox.ui.library.LibraryScreenKt$MeloXPlaylistDetailScreen$2$1", m720f = "LibraryScreen.kt", m721i = {}, m722l = {655}, m723m = "invokeSuspend", m724n = {}, m725nl = {656}, m726s = {}, m727v = 2)
final class LibraryScreenKt$MeloXPlaylistDetailScreen$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ NeteasePlaylistSummary $displayed;
    final /* synthetic */ MutableState<MeloXDetailPalette> $palette$delegate;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LibraryScreenKt$MeloXPlaylistDetailScreen$2$1(NeteasePlaylistSummary neteasePlaylistSummary, MutableState<MeloXDetailPalette> mutableState, Continuation<? super LibraryScreenKt$MeloXPlaylistDetailScreen$2$1> continuation) {
        super(2, continuation);
        this.$displayed = neteasePlaylistSummary;
        this.$palette$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LibraryScreenKt$MeloXPlaylistDetailScreen$2$1(this.$displayed, this.$palette$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LibraryScreenKt$MeloXPlaylistDetailScreen$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object objPaletteFor;
        MutableState<MeloXDetailPalette> mutableState;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                MutableState<MeloXDetailPalette> mutableState2 = this.$palette$delegate;
                this.L$0 = mutableState2;
                this.label = 1;
                objPaletteFor = MeloXDetailPaletteProvider.INSTANCE.paletteFor(this.$displayed.getCoverUrl(), this);
                if (objPaletteFor == coroutine_suspended) {
                    return coroutine_suspended;
                }
                mutableState = mutableState2;
                break;
                break;
            case 1:
                mutableState = (MutableState) this.L$0;
                ResultKt.throwOnFailure($result);
                objPaletteFor = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        mutableState.setValue((MeloXDetailPalette) objPaletteFor);
        return Unit.INSTANCE;
    }
}
