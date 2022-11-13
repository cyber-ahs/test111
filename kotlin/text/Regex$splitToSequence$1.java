package kotlin.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Regex.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "kotlin.text.Regex$splitToSequence$1", f = "Regex.kt", i = {1, 1, 1}, l = {276, 284, 288}, m = "invokeSuspend", n = {"$this$sequence", "matcher", "splitCount"}, s = {"L$0", "L$1", "I$0"})
/* loaded from: classes.dex */
public final class Regex$splitToSequence$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super String>, Continuation<? super Unit>, Object> {
    final /* synthetic */ CharSequence $input;
    final /* synthetic */ int $limit;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ Regex this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Regex$splitToSequence$1(Regex regex, CharSequence charSequence, int i, Continuation<? super Regex$splitToSequence$1> continuation) {
        super(2, continuation);
        this.this$0 = regex;
        this.$input = charSequence;
        this.$limit = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Regex$splitToSequence$1 regex$splitToSequence$1 = new Regex$splitToSequence$1(this.this$0, this.$input, this.$limit, continuation);
        regex$splitToSequence$1.L$0 = obj;
        return regex$splitToSequence$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super String> sequenceScope, Continuation<? super Unit> continuation) {
        return ((Regex$splitToSequence$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0075 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00a4 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0073 -> B:21:0x0076). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Pattern pattern;
        Matcher matcher;
        Regex$splitToSequence$1 regex$splitToSequence$1;
        SequenceScope sequenceScope;
        int i;
        CharSequence charSequence;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        int i3 = 0;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            SequenceScope sequenceScope2 = (SequenceScope) this.L$0;
            pattern = this.this$0.nativePattern;
            matcher = pattern.matcher(this.$input);
            if (this.$limit == 1 || !matcher.find()) {
                this.label = 1;
                if (sequenceScope2.yield(this.$input.toString(), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                regex$splitToSequence$1 = this;
                sequenceScope = sequenceScope2;
                i = 0;
                regex$splitToSequence$1.L$0 = sequenceScope;
                regex$splitToSequence$1.L$1 = matcher;
                regex$splitToSequence$1.I$0 = i;
                regex$splitToSequence$1.label = 2;
                if (sequenceScope.yield(regex$splitToSequence$1.$input.subSequence(i3, matcher.start()).toString(), regex$splitToSequence$1) == coroutine_suspended) {
                }
                i3 = matcher.end();
                i++;
                if (i != regex$splitToSequence$1.$limit - 1) {
                }
                charSequence = regex$splitToSequence$1.$input;
                regex$splitToSequence$1.L$0 = null;
                regex$splitToSequence$1.L$1 = null;
                regex$splitToSequence$1.label = 3;
                if (sequenceScope.yield(charSequence.subSequence(i3, charSequence.length()).toString(), regex$splitToSequence$1) == coroutine_suspended) {
                }
                return Unit.INSTANCE;
            }
        } else if (i2 != 1) {
            if (i2 != 2) {
                if (i2 == 3) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i4 = this.I$0;
            sequenceScope = (SequenceScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            regex$splitToSequence$1 = this;
            i = i4;
            matcher = (Matcher) this.L$1;
            i3 = matcher.end();
            i++;
            if (i != regex$splitToSequence$1.$limit - 1 || !matcher.find()) {
                charSequence = regex$splitToSequence$1.$input;
                regex$splitToSequence$1.L$0 = null;
                regex$splitToSequence$1.L$1 = null;
                regex$splitToSequence$1.label = 3;
                if (sequenceScope.yield(charSequence.subSequence(i3, charSequence.length()).toString(), regex$splitToSequence$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
            regex$splitToSequence$1.L$0 = sequenceScope;
            regex$splitToSequence$1.L$1 = matcher;
            regex$splitToSequence$1.I$0 = i;
            regex$splitToSequence$1.label = 2;
            if (sequenceScope.yield(regex$splitToSequence$1.$input.subSequence(i3, matcher.start()).toString(), regex$splitToSequence$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            i3 = matcher.end();
            i++;
            if (i != regex$splitToSequence$1.$limit - 1) {
            }
            charSequence = regex$splitToSequence$1.$input;
            regex$splitToSequence$1.L$0 = null;
            regex$splitToSequence$1.L$1 = null;
            regex$splitToSequence$1.label = 3;
            if (sequenceScope.yield(charSequence.subSequence(i3, charSequence.length()).toString(), regex$splitToSequence$1) == coroutine_suspended) {
            }
            return Unit.INSTANCE;
        } else {
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
