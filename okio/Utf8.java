package okio;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Utf8.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\u001a\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0001H\u0080\b\u001a\u0011\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0007H\u0080\b\u001a4\u0010\u0010\u001a\u00020\u0001*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\u0080\bø\u0001\u0000\u001a4\u0010\u0017\u001a\u00020\u0001*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\u0080\bø\u0001\u0000\u001a4\u0010\u0018\u001a\u00020\u0001*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\u0080\bø\u0001\u0000\u001a4\u0010\u0019\u001a\u00020\u0016*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00160\u0015H\u0080\bø\u0001\u0000\u001a4\u0010\u001a\u001a\u00020\u0016*\u00020\u001b2\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00160\u0015H\u0080\bø\u0001\u0000\u001a4\u0010\u001c\u001a\u00020\u0016*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\u0080\bø\u0001\u0000\u001a%\u0010\u001d\u001a\u00020\u001e*\u00020\u001b2\b\b\u0002\u0010\u0012\u001a\u00020\u00012\b\b\u0002\u0010\u0013\u001a\u00020\u0001H\u0007¢\u0006\u0002\b\u001f\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\tX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006 "}, d2 = {"HIGH_SURROGATE_HEADER", "", "LOG_SURROGATE_HEADER", "MASK_2BYTES", "MASK_3BYTES", "MASK_4BYTES", "REPLACEMENT_BYTE", "", "REPLACEMENT_CHARACTER", "", "REPLACEMENT_CODE_POINT", "isIsoControl", "", "codePoint", "isUtf8Continuation", "byte", "process2Utf8Bytes", "", "beginIndex", "endIndex", "yield", "Lkotlin/Function1;", "", "process3Utf8Bytes", "process4Utf8Bytes", "processUtf16Chars", "processUtf8Bytes", "", "processUtf8CodePoints", "utf8Size", "", "size", "okio"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes2.dex */
public final class Utf8 {
    public static final int HIGH_SURROGATE_HEADER = 55232;
    public static final int LOG_SURROGATE_HEADER = 56320;
    public static final int MASK_2BYTES = 3968;
    public static final int MASK_3BYTES = -123008;
    public static final int MASK_4BYTES = 3678080;
    public static final byte REPLACEMENT_BYTE = 63;
    public static final char REPLACEMENT_CHARACTER = 65533;
    public static final int REPLACEMENT_CODE_POINT = 65533;

    public static final boolean isIsoControl(int i) {
        return (i >= 0 && 31 >= i) || (127 <= i && 159 >= i);
    }

    public static final boolean isUtf8Continuation(byte b) {
        return (b & 192) == 128;
    }

    public static final long size(String str) {
        return size$default(str, 0, 0, 3, null);
    }

    public static final long size(String str, int i) {
        return size$default(str, i, 0, 2, null);
    }

    public static /* synthetic */ long size$default(String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        return size(str, i, i2);
    }

    public static final long size(String utf8Size, int i, int i2) {
        int i3;
        Intrinsics.checkNotNullParameter(utf8Size, "$this$utf8Size");
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("beginIndex < 0: " + i).toString());
        }
        if (!(i2 >= i)) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + i2 + " < " + i).toString());
        }
        if (!(i2 <= utf8Size.length())) {
            throw new IllegalArgumentException(("endIndex > string.length: " + i2 + " > " + utf8Size.length()).toString());
        }
        long j = 0;
        while (i < i2) {
            char charAt = utf8Size.charAt(i);
            if (charAt < 128) {
                j++;
            } else {
                if (charAt < 2048) {
                    i3 = 2;
                } else if (charAt < 55296 || charAt > 57343) {
                    i3 = 3;
                } else {
                    int i4 = i + 1;
                    char charAt2 = i4 < i2 ? utf8Size.charAt(i4) : (char) 0;
                    if (charAt > 56319 || charAt2 < 56320 || charAt2 > 57343) {
                        j++;
                        i = i4;
                    } else {
                        j += 4;
                        i += 2;
                    }
                }
                j += i3;
            }
            i++;
        }
        return j;
    }

    public static final void processUtf8Bytes(String processUtf8Bytes, int i, int i2, Function1<? super Byte, Unit> yield) {
        int i3;
        char charAt;
        Intrinsics.checkNotNullParameter(processUtf8Bytes, "$this$processUtf8Bytes");
        Intrinsics.checkNotNullParameter(yield, "yield");
        while (i < i2) {
            char charAt2 = processUtf8Bytes.charAt(i);
            if (Intrinsics.compare((int) charAt2, 128) < 0) {
                yield.invoke(Byte.valueOf((byte) charAt2));
                i++;
                while (i < i2 && Intrinsics.compare((int) processUtf8Bytes.charAt(i), 128) < 0) {
                    yield.invoke(Byte.valueOf((byte) processUtf8Bytes.charAt(i)));
                    i++;
                }
            } else {
                if (Intrinsics.compare((int) charAt2, 2048) < 0) {
                    yield.invoke(Byte.valueOf((byte) ((charAt2 >> 6) | 192)));
                    yield.invoke(Byte.valueOf((byte) ((charAt2 & '?') | 128)));
                } else if (55296 > charAt2 || 57343 < charAt2) {
                    yield.invoke(Byte.valueOf((byte) ((charAt2 >> '\f') | 224)));
                    yield.invoke(Byte.valueOf((byte) (((charAt2 >> 6) & 63) | 128)));
                    yield.invoke(Byte.valueOf((byte) ((charAt2 & '?') | 128)));
                } else if (Intrinsics.compare((int) charAt2, 56319) > 0 || i2 <= (i3 = i + 1) || 56320 > (charAt = processUtf8Bytes.charAt(i3)) || 57343 < charAt) {
                    yield.invoke(Byte.valueOf((byte) REPLACEMENT_BYTE));
                } else {
                    int charAt3 = ((charAt2 << '\n') + processUtf8Bytes.charAt(i3)) - 56613888;
                    yield.invoke(Byte.valueOf((byte) ((charAt3 >> 18) | 240)));
                    yield.invoke(Byte.valueOf((byte) (((charAt3 >> 12) & 63) | 128)));
                    yield.invoke(Byte.valueOf((byte) (((charAt3 >> 6) & 63) | 128)));
                    yield.invoke(Byte.valueOf((byte) ((charAt3 & 63) | 128)));
                    i += 2;
                }
                i++;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0096, code lost:
        if (((r16[r4] & 192) == 128) == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0117, code lost:
        if (((r16[r4] & 192) == 128) == false) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void processUtf8CodePoints(byte[] processUtf8CodePoints, int i, int i2, Function1<? super Integer, Unit> yield) {
        int i3;
        Intrinsics.checkNotNullParameter(processUtf8CodePoints, "$this$processUtf8CodePoints");
        Intrinsics.checkNotNullParameter(yield, "yield");
        int i4 = i;
        while (i4 < i2) {
            byte b = processUtf8CodePoints[i4];
            if (b >= 0) {
                yield.invoke(Integer.valueOf(b));
                i4++;
                while (i4 < i2 && processUtf8CodePoints[i4] >= 0) {
                    yield.invoke(Integer.valueOf(processUtf8CodePoints[i4]));
                    i4++;
                }
            } else {
                if ((b >> 5) == -2) {
                    int i5 = i4 + 1;
                    if (i2 > i5) {
                        byte b2 = processUtf8CodePoints[i4];
                        byte b3 = processUtf8CodePoints[i5];
                        if ((b3 & 192) == 128) {
                            int i6 = (b3 ^ ByteCompanionObject.MIN_VALUE) ^ (b2 << 6);
                            yield.invoke(i6 < 128 ? Integer.valueOf((int) REPLACEMENT_CODE_POINT) : Integer.valueOf(i6));
                            Unit unit = Unit.INSTANCE;
                            i3 = 2;
                        }
                    }
                    yield.invoke(Integer.valueOf((int) REPLACEMENT_CODE_POINT));
                    Unit unit2 = Unit.INSTANCE;
                    i3 = 1;
                } else if ((b >> 4) == -2) {
                    int i7 = i4 + 2;
                    if (i2 <= i7) {
                        yield.invoke(Integer.valueOf((int) REPLACEMENT_CODE_POINT));
                        Unit unit3 = Unit.INSTANCE;
                        int i8 = i4 + 1;
                        if (i2 > i8) {
                        }
                        i3 = 1;
                    } else {
                        byte b4 = processUtf8CodePoints[i4];
                        byte b5 = processUtf8CodePoints[i4 + 1];
                        if ((b5 & 192) == 128) {
                            byte b6 = processUtf8CodePoints[i7];
                            if ((b6 & 192) == 128) {
                                int i9 = ((b6 ^ ByteCompanionObject.MIN_VALUE) ^ (b5 << 6)) ^ (b4 << 12);
                                yield.invoke((i9 >= 2048 && (55296 > i9 || 57343 < i9)) ? Integer.valueOf(i9) : Integer.valueOf((int) REPLACEMENT_CODE_POINT));
                                Unit unit4 = Unit.INSTANCE;
                                i3 = 3;
                            } else {
                                yield.invoke(Integer.valueOf((int) REPLACEMENT_CODE_POINT));
                                Unit unit5 = Unit.INSTANCE;
                                i3 = 2;
                            }
                        } else {
                            yield.invoke(Integer.valueOf((int) REPLACEMENT_CODE_POINT));
                            Unit unit6 = Unit.INSTANCE;
                            i3 = 1;
                        }
                    }
                } else if ((b >> 3) == -2) {
                    int i10 = i4 + 3;
                    if (i2 <= i10) {
                        yield.invoke(Integer.valueOf((int) REPLACEMENT_CODE_POINT));
                        Unit unit7 = Unit.INSTANCE;
                        int i11 = i4 + 1;
                        if (i2 > i11) {
                            if ((processUtf8CodePoints[i11] & 192) == 128) {
                                int i12 = i4 + 2;
                                if (i2 > i12) {
                                }
                                i3 = 2;
                            }
                        }
                        i3 = 1;
                    } else {
                        byte b7 = processUtf8CodePoints[i4];
                        byte b8 = processUtf8CodePoints[i4 + 1];
                        if ((b8 & 192) == 128) {
                            byte b9 = processUtf8CodePoints[i4 + 2];
                            if ((b9 & 192) == 128) {
                                byte b10 = processUtf8CodePoints[i10];
                                if ((b10 & 192) == 128) {
                                    int i13 = (((b10 ^ ByteCompanionObject.MIN_VALUE) ^ (b9 << 6)) ^ (b8 << 12)) ^ (b7 << 18);
                                    yield.invoke((i13 <= 1114111 && (55296 > i13 || 57343 < i13) && i13 >= 65536) ? Integer.valueOf(i13) : Integer.valueOf((int) REPLACEMENT_CODE_POINT));
                                    Unit unit8 = Unit.INSTANCE;
                                    i3 = 4;
                                } else {
                                    yield.invoke(Integer.valueOf((int) REPLACEMENT_CODE_POINT));
                                    Unit unit9 = Unit.INSTANCE;
                                    i3 = 3;
                                }
                            } else {
                                yield.invoke(Integer.valueOf((int) REPLACEMENT_CODE_POINT));
                                Unit unit10 = Unit.INSTANCE;
                                i3 = 2;
                            }
                        } else {
                            yield.invoke(Integer.valueOf((int) REPLACEMENT_CODE_POINT));
                            Unit unit11 = Unit.INSTANCE;
                            i3 = 1;
                        }
                    }
                } else {
                    yield.invoke(Integer.valueOf((int) REPLACEMENT_CODE_POINT));
                    i4++;
                }
                i4 += i3;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0098, code lost:
        if (((r16[r4] & 192) == 128) == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0119, code lost:
        if (((r16[r4] & 192) == 128) == false) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void processUtf16Chars(byte[] processUtf16Chars, int i, int i2, Function1<? super Character, Unit> yield) {
        int i3;
        Intrinsics.checkNotNullParameter(processUtf16Chars, "$this$processUtf16Chars");
        Intrinsics.checkNotNullParameter(yield, "yield");
        int i4 = i;
        while (i4 < i2) {
            byte b = processUtf16Chars[i4];
            if (b >= 0) {
                yield.invoke(Character.valueOf((char) b));
                i4++;
                while (i4 < i2 && processUtf16Chars[i4] >= 0) {
                    yield.invoke(Character.valueOf((char) processUtf16Chars[i4]));
                    i4++;
                }
            } else {
                if ((b >> 5) == -2) {
                    int i5 = i4 + 1;
                    if (i2 > i5) {
                        byte b2 = processUtf16Chars[i4];
                        byte b3 = processUtf16Chars[i5];
                        if ((b3 & 192) == 128) {
                            int i6 = (b3 ^ ByteCompanionObject.MIN_VALUE) ^ (b2 << 6);
                            yield.invoke(Character.valueOf(i6 < 128 ? (char) REPLACEMENT_CODE_POINT : (char) i6));
                            Unit unit = Unit.INSTANCE;
                            i3 = 2;
                        }
                    }
                    yield.invoke(Character.valueOf((char) REPLACEMENT_CODE_POINT));
                    Unit unit2 = Unit.INSTANCE;
                    i3 = 1;
                } else if ((b >> 4) == -2) {
                    int i7 = i4 + 2;
                    if (i2 <= i7) {
                        yield.invoke(Character.valueOf((char) REPLACEMENT_CODE_POINT));
                        Unit unit3 = Unit.INSTANCE;
                        int i8 = i4 + 1;
                        if (i2 > i8) {
                        }
                        i3 = 1;
                    } else {
                        byte b4 = processUtf16Chars[i4];
                        byte b5 = processUtf16Chars[i4 + 1];
                        if ((b5 & 192) == 128) {
                            byte b6 = processUtf16Chars[i7];
                            if ((b6 & 192) == 128) {
                                int i9 = ((b6 ^ ByteCompanionObject.MIN_VALUE) ^ (b5 << 6)) ^ (b4 << 12);
                                yield.invoke(Character.valueOf((i9 >= 2048 && (55296 > i9 || 57343 < i9)) ? (char) i9 : (char) REPLACEMENT_CODE_POINT));
                                Unit unit4 = Unit.INSTANCE;
                                i3 = 3;
                            } else {
                                yield.invoke(Character.valueOf((char) REPLACEMENT_CODE_POINT));
                                Unit unit5 = Unit.INSTANCE;
                                i3 = 2;
                            }
                        } else {
                            yield.invoke(Character.valueOf((char) REPLACEMENT_CODE_POINT));
                            Unit unit6 = Unit.INSTANCE;
                            i3 = 1;
                        }
                    }
                } else if ((b >> 3) == -2) {
                    int i10 = i4 + 3;
                    if (i2 <= i10) {
                        yield.invoke(Character.valueOf(REPLACEMENT_CHARACTER));
                        Unit unit7 = Unit.INSTANCE;
                        int i11 = i4 + 1;
                        if (i2 > i11) {
                            if ((processUtf16Chars[i11] & 192) == 128) {
                                int i12 = i4 + 2;
                                if (i2 > i12) {
                                }
                                i3 = 2;
                            }
                        }
                        i3 = 1;
                    } else {
                        byte b7 = processUtf16Chars[i4];
                        byte b8 = processUtf16Chars[i4 + 1];
                        if ((b8 & 192) == 128) {
                            byte b9 = processUtf16Chars[i4 + 2];
                            if ((b9 & 192) == 128) {
                                byte b10 = processUtf16Chars[i10];
                                if ((b10 & 192) == 128) {
                                    int i13 = (((b10 ^ ByteCompanionObject.MIN_VALUE) ^ (b9 << 6)) ^ (b8 << 12)) ^ (b7 << 18);
                                    if (i13 <= 1114111 && ((55296 > i13 || 57343 < i13) && i13 >= 65536 && i13 != 65533)) {
                                        yield.invoke(Character.valueOf((char) ((i13 >>> 10) + HIGH_SURROGATE_HEADER)));
                                        yield.invoke(Character.valueOf((char) ((i13 & 1023) + LOG_SURROGATE_HEADER)));
                                    } else {
                                        yield.invoke(Character.valueOf(REPLACEMENT_CHARACTER));
                                    }
                                    Unit unit8 = Unit.INSTANCE;
                                    i3 = 4;
                                } else {
                                    yield.invoke(Character.valueOf(REPLACEMENT_CHARACTER));
                                    Unit unit9 = Unit.INSTANCE;
                                    i3 = 3;
                                }
                            } else {
                                yield.invoke(Character.valueOf(REPLACEMENT_CHARACTER));
                                Unit unit10 = Unit.INSTANCE;
                                i3 = 2;
                            }
                        } else {
                            yield.invoke(Character.valueOf(REPLACEMENT_CHARACTER));
                            Unit unit11 = Unit.INSTANCE;
                            i3 = 1;
                        }
                    }
                } else {
                    yield.invoke(Character.valueOf(REPLACEMENT_CHARACTER));
                    i4++;
                }
                i4 += i3;
            }
        }
    }

    public static final int process2Utf8Bytes(byte[] process2Utf8Bytes, int i, int i2, Function1<? super Integer, Unit> yield) {
        Intrinsics.checkNotNullParameter(process2Utf8Bytes, "$this$process2Utf8Bytes");
        Intrinsics.checkNotNullParameter(yield, "yield");
        int i3 = i + 1;
        Integer valueOf = Integer.valueOf((int) REPLACEMENT_CODE_POINT);
        if (i2 <= i3) {
            yield.invoke(valueOf);
            return 1;
        }
        byte b = process2Utf8Bytes[i];
        byte b2 = process2Utf8Bytes[i3];
        if (!((b2 & 192) == 128)) {
            yield.invoke(valueOf);
            return 1;
        }
        int i4 = (b2 ^ ByteCompanionObject.MIN_VALUE) ^ (b << 6);
        if (i4 < 128) {
            yield.invoke(valueOf);
            return 2;
        }
        yield.invoke(Integer.valueOf(i4));
        return 2;
    }

    public static final int process3Utf8Bytes(byte[] process3Utf8Bytes, int i, int i2, Function1<? super Integer, Unit> yield) {
        Intrinsics.checkNotNullParameter(process3Utf8Bytes, "$this$process3Utf8Bytes");
        Intrinsics.checkNotNullParameter(yield, "yield");
        int i3 = i + 2;
        Integer valueOf = Integer.valueOf((int) REPLACEMENT_CODE_POINT);
        if (i2 <= i3) {
            yield.invoke(valueOf);
            int i4 = i + 1;
            if (i2 > i4) {
                if ((process3Utf8Bytes[i4] & 192) == 128) {
                    return 2;
                }
            }
            return 1;
        }
        byte b = process3Utf8Bytes[i];
        byte b2 = process3Utf8Bytes[i + 1];
        if (!((b2 & 192) == 128)) {
            yield.invoke(valueOf);
            return 1;
        }
        byte b3 = process3Utf8Bytes[i3];
        if (!((b3 & 192) == 128)) {
            yield.invoke(valueOf);
            return 2;
        }
        int i5 = ((b3 ^ ByteCompanionObject.MIN_VALUE) ^ (b2 << 6)) ^ (b << 12);
        if (i5 < 2048) {
            yield.invoke(valueOf);
            return 3;
        } else if (55296 <= i5 && 57343 >= i5) {
            yield.invoke(valueOf);
            return 3;
        } else {
            yield.invoke(Integer.valueOf(i5));
            return 3;
        }
    }

    public static final int process4Utf8Bytes(byte[] process4Utf8Bytes, int i, int i2, Function1<? super Integer, Unit> yield) {
        Intrinsics.checkNotNullParameter(process4Utf8Bytes, "$this$process4Utf8Bytes");
        Intrinsics.checkNotNullParameter(yield, "yield");
        int i3 = i + 3;
        Integer valueOf = Integer.valueOf((int) REPLACEMENT_CODE_POINT);
        if (i2 <= i3) {
            yield.invoke(valueOf);
            int i4 = i + 1;
            if (i2 > i4) {
                if ((process4Utf8Bytes[i4] & 192) == 128) {
                    int i5 = i + 2;
                    if (i2 > i5) {
                        if ((process4Utf8Bytes[i5] & 192) == 128) {
                            return 3;
                        }
                    }
                    return 2;
                }
            }
            return 1;
        }
        byte b = process4Utf8Bytes[i];
        byte b2 = process4Utf8Bytes[i + 1];
        if (!((b2 & 192) == 128)) {
            yield.invoke(valueOf);
            return 1;
        }
        byte b3 = process4Utf8Bytes[i + 2];
        if (!((b3 & 192) == 128)) {
            yield.invoke(valueOf);
            return 2;
        }
        byte b4 = process4Utf8Bytes[i3];
        if (!((b4 & 192) == 128)) {
            yield.invoke(valueOf);
            return 3;
        }
        int i6 = (((b4 ^ ByteCompanionObject.MIN_VALUE) ^ (b3 << 6)) ^ (b2 << 12)) ^ (b << 18);
        if (i6 > 1114111) {
            yield.invoke(valueOf);
            return 4;
        } else if (55296 <= i6 && 57343 >= i6) {
            yield.invoke(valueOf);
            return 4;
        } else if (i6 < 65536) {
            yield.invoke(valueOf);
            return 4;
        } else {
            yield.invoke(Integer.valueOf(i6));
            return 4;
        }
    }
}
