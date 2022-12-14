package kotlinx.coroutines.flow.internal;

import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.YieldKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
/* compiled from: Combine.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "R", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2", f = "Combine.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {57, 79, 82}, m = "invokeSuspend", n = {"latestValues", "resultChannel", "lastReceivedEpoch", "remainingAbsentValues", "currentEpoch", "latestValues", "resultChannel", "lastReceivedEpoch", "remainingAbsentValues", "currentEpoch", "latestValues", "resultChannel", "lastReceivedEpoch", "remainingAbsentValues", "currentEpoch"}, s = {"L$0", "L$1", "L$2", "I$0", "I$1", "L$0", "L$1", "L$2", "I$0", "I$1", "L$0", "L$1", "L$2", "I$0", "I$1"})
/* loaded from: classes2.dex */
final class CombineKt$combineInternal$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0<T[]> $arrayFactory;
    final /* synthetic */ Flow<T>[] $flows;
    final /* synthetic */ FlowCollector<R> $this_combineInternal;
    final /* synthetic */ Function3<FlowCollector<? super R>, T[], Continuation<? super Unit>, Object> $transform;
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public CombineKt$combineInternal$2(Flow<? extends T>[] flowArr, Function0<T[]> function0, Function3<? super FlowCollector<? super R>, ? super T[], ? super Continuation<? super Unit>, ? extends Object> function3, FlowCollector<? super R> flowCollector, Continuation<? super CombineKt$combineInternal$2> continuation) {
        super(2, continuation);
        this.$flows = flowArr;
        this.$arrayFactory = function0;
        this.$transform = function3;
        this.$this_combineInternal = flowCollector;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CombineKt$combineInternal$2 combineKt$combineInternal$2 = new CombineKt$combineInternal$2(this.$flows, this.$arrayFactory, this.$transform, this.$this_combineInternal, continuation);
        combineKt$combineInternal$2.L$0 = obj;
        return combineKt$combineInternal$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CombineKt$combineInternal$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00f2 A[LOOP:0: B:28:0x00f2->B:35:0x0116, LOOP_START, PHI: r3 r10 
      PHI: (r3v2 int) = (r3v1 int), (r3v3 int) binds: [B:25:0x00ed, B:35:0x0116] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r10v6 kotlin.collections.IndexedValue) = (r10v5 kotlin.collections.IndexedValue), (r10v19 kotlin.collections.IndexedValue) binds: [B:25:0x00ed, B:35:0x0116] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r11v1, types: [kotlinx.coroutines.flow.Flow[], kotlinx.coroutines.flow.Flow<T>[]] */
    /* JADX WARN: Type inference failed for: r2v12, types: [int] */
    /* JADX WARN: Type inference failed for: r2v7, types: [int] */
    /* JADX WARN: Type inference failed for: r2v9, types: [int] */
    /* JADX WARN: Type inference failed for: r7v0, types: [kotlinx.coroutines.flow.Flow<T>[]] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x013b -> B:20:0x00ca). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:48:0x0170 -> B:46:0x016c). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        int length;
        Object[] objArr;
        byte[] bArr;
        CombineKt$combineInternal$2 combineKt$combineInternal$2;
        Channel channel;
        Object[] objArr2;
        Object obj2;
        CombineKt$combineInternal$2 combineKt$combineInternal$22;
        Channel channel2;
        byte b;
        int i;
        IndexedValue indexedValue;
        Object[] objArr3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        byte b2 = 0;
        int i3 = 2;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            length = this.$flows.length;
            if (length == 0) {
                return Unit.INSTANCE;
            }
            objArr = new Object[length];
            ArraysKt.fill$default(objArr, NullSurrogateKt.UNINITIALIZED, 0, 0, 6, (Object) null);
            Channel Channel$default = ChannelKt.Channel$default(length, null, null, 6, null);
            AtomicInteger atomicInteger = new AtomicInteger(length);
            for (int i4 = 0; i4 < length; i4++) {
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(this.$flows, i4, atomicInteger, Channel$default, null), 3, null);
                atomicInteger = atomicInteger;
            }
            bArr = new byte[length];
            combineKt$combineInternal$2 = this;
            channel = Channel$default;
        } else if (i2 == 1) {
            ?? r2 = this.I$1;
            i = this.I$0;
            byte[] bArr2 = (byte[]) this.L$2;
            channel2 = (Channel) this.L$1;
            ResultKt.throwOnFailure(obj);
            obj2 = ((ChannelResult) obj).m1511unboximpl();
            objArr2 = (Object[]) this.L$0;
            combineKt$combineInternal$22 = this;
            b = r2;
            bArr = bArr2;
            indexedValue = (IndexedValue) ChannelResult.m1504getOrNullimpl(obj2);
            if (indexedValue != null) {
                return Unit.INSTANCE;
            }
            do {
                int index = indexedValue.getIndex();
                Object obj3 = objArr2[index];
                objArr2[index] = indexedValue.getValue();
                if (obj3 == NullSurrogateKt.UNINITIALIZED) {
                    i--;
                }
                if (bArr[index] == b) {
                    break;
                }
                bArr[index] = b;
                indexedValue = (IndexedValue) ChannelResult.m1504getOrNullimpl(channel2.mo1493tryReceivePtdJZtk());
            } while (indexedValue != null);
            if (i != 0) {
                objArr3 = objArr2;
            } else {
                Object[] objArr4 = (Object[]) combineKt$combineInternal$22.$arrayFactory.invoke();
                if (objArr4 == null) {
                    Function3 function3 = combineKt$combineInternal$22.$transform;
                    Object obj4 = combineKt$combineInternal$22.$this_combineInternal;
                    combineKt$combineInternal$22.L$0 = objArr2;
                    combineKt$combineInternal$22.L$1 = channel2;
                    combineKt$combineInternal$22.L$2 = bArr;
                    combineKt$combineInternal$22.I$0 = i;
                    combineKt$combineInternal$22.I$1 = b;
                    combineKt$combineInternal$22.label = i3;
                    if (function3.invoke(obj4, objArr2, combineKt$combineInternal$22) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    length = i;
                    b2 = b;
                    channel = channel2;
                    combineKt$combineInternal$2 = combineKt$combineInternal$22;
                    objArr = objArr2;
                } else {
                    objArr3 = objArr2;
                    ArraysKt.copyInto$default(objArr2, objArr4, 0, 0, 0, 14, (Object) null);
                    Function3 function32 = combineKt$combineInternal$22.$transform;
                    Object obj5 = combineKt$combineInternal$22.$this_combineInternal;
                    combineKt$combineInternal$22.L$0 = objArr3;
                    combineKt$combineInternal$22.L$1 = channel2;
                    combineKt$combineInternal$22.L$2 = bArr;
                    combineKt$combineInternal$22.I$0 = i;
                    combineKt$combineInternal$22.I$1 = b;
                    combineKt$combineInternal$22.label = 3;
                    if (function32.invoke(obj5, objArr4, combineKt$combineInternal$22) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
            length = i;
            objArr = objArr3;
            b2 = b;
            channel = channel2;
            combineKt$combineInternal$2 = combineKt$combineInternal$22;
            i3 = 2;
        } else if (i2 == 2) {
            ?? r22 = this.I$1;
            int i5 = this.I$0;
            ResultKt.throwOnFailure(obj);
            length = i5;
            objArr = (Object[]) this.L$0;
            b2 = r22;
            bArr = (byte[]) this.L$2;
            channel = (Channel) this.L$1;
            combineKt$combineInternal$2 = this;
        } else if (i2 != 3) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ?? r23 = this.I$1;
            int i6 = this.I$0;
            ResultKt.throwOnFailure(obj);
            length = i6;
            objArr = (Object[]) this.L$0;
            b2 = r23;
            bArr = (byte[]) this.L$2;
            channel = (Channel) this.L$1;
            combineKt$combineInternal$2 = this;
            i3 = 2;
        }
        byte b3 = (byte) (b2 + 1);
        combineKt$combineInternal$2.L$0 = objArr;
        combineKt$combineInternal$2.L$1 = channel;
        combineKt$combineInternal$2.L$2 = bArr;
        combineKt$combineInternal$2.I$0 = length;
        combineKt$combineInternal$2.I$1 = b3;
        combineKt$combineInternal$2.label = 1;
        obj2 = channel.mo1492receiveCatchingJP2dKIU(combineKt$combineInternal$2);
        if (obj2 == coroutine_suspended) {
            return coroutine_suspended;
        }
        combineKt$combineInternal$22 = combineKt$combineInternal$2;
        objArr2 = objArr;
        channel2 = channel;
        b = b3;
        i = length;
        indexedValue = (IndexedValue) ChannelResult.m1504getOrNullimpl(obj2);
        if (indexedValue != null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Combine.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "R", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1", f = "Combine.kt", i = {}, l = {34}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Flow<T>[] $flows;
        final /* synthetic */ int $i;
        final /* synthetic */ AtomicInteger $nonClosed;
        final /* synthetic */ Channel<IndexedValue<Object>> $resultChannel;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Flow<? extends T>[] flowArr, int i, AtomicInteger atomicInteger, Channel<IndexedValue<Object>> channel, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$flows = flowArr;
            this.$i = i;
            this.$nonClosed = atomicInteger;
            this.$resultChannel = channel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$flows, this.$i, this.$nonClosed, this.$resultChannel, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            AtomicInteger atomicInteger;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow[] flowArr = this.$flows;
                    int i2 = this.$i;
                    this.label = 1;
                    if (flowArr[i2].collect(new C00081(this.$resultChannel, i2), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                if (atomicInteger.decrementAndGet() == 0) {
                    SendChannel.DefaultImpls.close$default(this.$resultChannel, null, 1, null);
                }
                return Unit.INSTANCE;
            } finally {
                if (this.$nonClosed.decrementAndGet() == 0) {
                    SendChannel.DefaultImpls.close$default(this.$resultChannel, null, 1, null);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: Combine.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0003H\u008a@??\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "R", "T", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        /* renamed from: kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1$1  reason: invalid class name and collision with other inner class name */
        /* loaded from: classes2.dex */
        public static final class C00081<T> implements FlowCollector, SuspendFunction {
            final /* synthetic */ int $i;
            final /* synthetic */ Channel<IndexedValue<Object>> $resultChannel;

            C00081(Channel<IndexedValue<Object>> channel, int i) {
                this.$resultChannel = channel;
                this.$i = i;
            }

            /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
            /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
            /* JADX WARN: Removed duplicated region for block: B:21:0x0056 A[RETURN] */
            @Override // kotlinx.coroutines.flow.FlowCollector
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final Object emit(T t, Continuation<? super Unit> continuation) {
                CombineKt$combineInternal$2$1$1$emit$1 combineKt$combineInternal$2$1$1$emit$1;
                Object coroutine_suspended;
                int i;
                if (continuation instanceof CombineKt$combineInternal$2$1$1$emit$1) {
                    combineKt$combineInternal$2$1$1$emit$1 = (CombineKt$combineInternal$2$1$1$emit$1) continuation;
                    if ((combineKt$combineInternal$2$1$1$emit$1.label & Integer.MIN_VALUE) != 0) {
                        combineKt$combineInternal$2$1$1$emit$1.label -= Integer.MIN_VALUE;
                        Object obj = combineKt$combineInternal$2$1$1$emit$1.result;
                        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        i = combineKt$combineInternal$2$1$1$emit$1.label;
                        if (i != 0) {
                            ResultKt.throwOnFailure(obj);
                            Channel<IndexedValue<Object>> channel = this.$resultChannel;
                            IndexedValue<Object> indexedValue = new IndexedValue<>(this.$i, t);
                            combineKt$combineInternal$2$1$1$emit$1.label = 1;
                            if (channel.send(indexedValue, combineKt$combineInternal$2$1$1$emit$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else if (i != 1) {
                            if (i == 2) {
                                ResultKt.throwOnFailure(obj);
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        } else {
                            ResultKt.throwOnFailure(obj);
                        }
                        combineKt$combineInternal$2$1$1$emit$1.label = 2;
                        if (YieldKt.yield(combineKt$combineInternal$2$1$1$emit$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    }
                }
                combineKt$combineInternal$2$1$1$emit$1 = new CombineKt$combineInternal$2$1$1$emit$1(this, continuation);
                Object obj2 = combineKt$combineInternal$2$1$1$emit$1.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = combineKt$combineInternal$2$1$1$emit$1.label;
                if (i != 0) {
                }
                combineKt$combineInternal$2$1$1$emit$1.label = 2;
                if (YieldKt.yield(combineKt$combineInternal$2$1$1$emit$1) == coroutine_suspended) {
                }
                return Unit.INSTANCE;
            }
        }
    }
}
