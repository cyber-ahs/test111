package kotlin.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequenceScope;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SlidingWindow.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003H\u008a@"}, d2 = {"<anonymous>", "", "T", "Lkotlin/sequences/SequenceScope;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "kotlin.collections.SlidingWindowKt$windowedIterator$1", f = "SlidingWindow.kt", i = {0, 0, 0, 2, 2, 3, 3}, l = {34, 40, 49, 55, 58}, m = "invokeSuspend", n = {"$this$iterator", "buffer", "gap", "$this$iterator", "buffer", "$this$iterator", "buffer"}, s = {"L$0", "L$1", "I$0", "L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes.dex */
public final class SlidingWindowKt$windowedIterator$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super List<? extends T>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Iterator<T> $iterator;
    final /* synthetic */ boolean $partialWindows;
    final /* synthetic */ boolean $reuseBuffer;
    final /* synthetic */ int $size;
    final /* synthetic */ int $step;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SlidingWindowKt$windowedIterator$1(int i, int i2, Iterator<? extends T> it, boolean z, boolean z2, Continuation<? super SlidingWindowKt$windowedIterator$1> continuation) {
        super(2, continuation);
        this.$size = i;
        this.$step = i2;
        this.$iterator = it;
        this.$reuseBuffer = z;
        this.$partialWindows = z2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SlidingWindowKt$windowedIterator$1 slidingWindowKt$windowedIterator$1 = new SlidingWindowKt$windowedIterator$1(this.$size, this.$step, this.$iterator, this.$reuseBuffer, this.$partialWindows, continuation);
        slidingWindowKt$windowedIterator$1.L$0 = obj;
        return slidingWindowKt$windowedIterator$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super List<? extends T>> sequenceScope, Continuation<? super Unit> continuation) {
        return ((SlidingWindowKt$windowedIterator$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00e4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0170  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00a8 -> B:29:0x00ab). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x012f -> B:58:0x0132). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:69:0x0167 -> B:71:0x016a). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        int i;
        RingBuffer ringBuffer;
        Iterator it;
        SequenceScope sequenceScope;
        SlidingWindowKt$windowedIterator$1 slidingWindowKt$windowedIterator$1;
        int i2;
        SequenceScope sequenceScope2;
        SlidingWindowKt$windowedIterator$1 slidingWindowKt$windowedIterator$12;
        ArrayList arrayList;
        Iterator it2;
        RingBuffer ringBuffer2;
        SequenceScope sequenceScope3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            SequenceScope sequenceScope4 = (SequenceScope) this.L$0;
            int coerceAtMost = RangesKt.coerceAtMost(this.$size, 1024);
            i = this.$step - this.$size;
            if (i >= 0) {
                ArrayList arrayList2 = new ArrayList(coerceAtMost);
                i2 = 0;
                sequenceScope2 = sequenceScope4;
                slidingWindowKt$windowedIterator$12 = this;
                arrayList = arrayList2;
                it2 = this.$iterator;
                while (it2.hasNext()) {
                }
                if (!arrayList.isEmpty()) {
                    slidingWindowKt$windowedIterator$12.L$0 = null;
                    slidingWindowKt$windowedIterator$12.L$1 = null;
                    slidingWindowKt$windowedIterator$12.L$2 = null;
                    slidingWindowKt$windowedIterator$12.label = 2;
                    if (sequenceScope2.yield(arrayList, slidingWindowKt$windowedIterator$12) == coroutine_suspended) {
                    }
                }
                return Unit.INSTANCE;
            }
            ringBuffer = new RingBuffer(coerceAtMost);
            it = this.$iterator;
            sequenceScope = sequenceScope4;
            slidingWindowKt$windowedIterator$1 = this;
            while (it.hasNext()) {
            }
            if (slidingWindowKt$windowedIterator$1.$partialWindows) {
            }
            return Unit.INSTANCE;
        } else if (i3 == 1) {
            int i4 = this.I$0;
            it2 = (Iterator) this.L$2;
            arrayList = (ArrayList) this.L$1;
            sequenceScope2 = (SequenceScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            slidingWindowKt$windowedIterator$12 = this;
            i = i4;
            if (slidingWindowKt$windowedIterator$12.$reuseBuffer) {
                arrayList = new ArrayList(slidingWindowKt$windowedIterator$12.$size);
            } else {
                arrayList.clear();
            }
            i2 = i;
            while (it2.hasNext()) {
                Object next = it2.next();
                if (i2 > 0) {
                    i2--;
                } else {
                    arrayList.add(next);
                    if (arrayList.size() == slidingWindowKt$windowedIterator$12.$size) {
                        slidingWindowKt$windowedIterator$12.L$0 = sequenceScope2;
                        slidingWindowKt$windowedIterator$12.L$1 = arrayList;
                        slidingWindowKt$windowedIterator$12.L$2 = it2;
                        slidingWindowKt$windowedIterator$12.I$0 = i;
                        slidingWindowKt$windowedIterator$12.label = 1;
                        if (sequenceScope2.yield(arrayList, slidingWindowKt$windowedIterator$12) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        if (slidingWindowKt$windowedIterator$12.$reuseBuffer) {
                        }
                        i2 = i;
                        while (it2.hasNext()) {
                        }
                    }
                }
            }
            if ((!arrayList.isEmpty()) && (slidingWindowKt$windowedIterator$12.$partialWindows || arrayList.size() == slidingWindowKt$windowedIterator$12.$size)) {
                slidingWindowKt$windowedIterator$12.L$0 = null;
                slidingWindowKt$windowedIterator$12.L$1 = null;
                slidingWindowKt$windowedIterator$12.L$2 = null;
                slidingWindowKt$windowedIterator$12.label = 2;
                if (sequenceScope2.yield(arrayList, slidingWindowKt$windowedIterator$12) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        } else {
            if (i3 != 2) {
                if (i3 == 3) {
                    it = (Iterator) this.L$2;
                    ringBuffer = (RingBuffer) this.L$1;
                    sequenceScope = (SequenceScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    slidingWindowKt$windowedIterator$1 = this;
                    ringBuffer.removeFirst(slidingWindowKt$windowedIterator$1.$step);
                    while (it.hasNext()) {
                        ringBuffer.add((RingBuffer) it.next());
                        if (ringBuffer.isFull()) {
                            int size = ringBuffer.size();
                            int i5 = slidingWindowKt$windowedIterator$1.$size;
                            if (size >= i5) {
                                List arrayList3 = slidingWindowKt$windowedIterator$1.$reuseBuffer ? ringBuffer : new ArrayList(ringBuffer);
                                slidingWindowKt$windowedIterator$1.L$0 = sequenceScope;
                                slidingWindowKt$windowedIterator$1.L$1 = ringBuffer;
                                slidingWindowKt$windowedIterator$1.L$2 = it;
                                slidingWindowKt$windowedIterator$1.label = 3;
                                if (sequenceScope.yield(arrayList3, slidingWindowKt$windowedIterator$1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                ringBuffer.removeFirst(slidingWindowKt$windowedIterator$1.$step);
                                while (it.hasNext()) {
                                }
                            } else {
                                ringBuffer = ringBuffer.expanded(i5);
                            }
                        }
                    }
                    if (slidingWindowKt$windowedIterator$1.$partialWindows) {
                        ringBuffer2 = ringBuffer;
                        sequenceScope3 = sequenceScope;
                        if (ringBuffer2.size() <= slidingWindowKt$windowedIterator$1.$step) {
                        }
                    }
                    return Unit.INSTANCE;
                } else if (i3 == 4) {
                    ringBuffer2 = (RingBuffer) this.L$1;
                    sequenceScope3 = (SequenceScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    slidingWindowKt$windowedIterator$1 = this;
                    ringBuffer2.removeFirst(slidingWindowKt$windowedIterator$1.$step);
                    if (ringBuffer2.size() <= slidingWindowKt$windowedIterator$1.$step) {
                        List arrayList4 = slidingWindowKt$windowedIterator$1.$reuseBuffer ? ringBuffer2 : new ArrayList(ringBuffer2);
                        slidingWindowKt$windowedIterator$1.L$0 = sequenceScope3;
                        slidingWindowKt$windowedIterator$1.L$1 = ringBuffer2;
                        slidingWindowKt$windowedIterator$1.L$2 = null;
                        slidingWindowKt$windowedIterator$1.label = 4;
                        if (sequenceScope3.yield(arrayList4, slidingWindowKt$windowedIterator$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        ringBuffer2.removeFirst(slidingWindowKt$windowedIterator$1.$step);
                        if (ringBuffer2.size() <= slidingWindowKt$windowedIterator$1.$step) {
                            if (!ringBuffer2.isEmpty()) {
                                slidingWindowKt$windowedIterator$1.L$0 = null;
                                slidingWindowKt$windowedIterator$1.L$1 = null;
                                slidingWindowKt$windowedIterator$1.L$2 = null;
                                slidingWindowKt$windowedIterator$1.label = 5;
                                if (sequenceScope3.yield(ringBuffer2, slidingWindowKt$windowedIterator$1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                            return Unit.INSTANCE;
                        }
                    }
                } else if (i3 != 5) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }
}
