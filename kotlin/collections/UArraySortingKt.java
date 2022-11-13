package kotlin.collections;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: UArraySorting.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0010\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u0014\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u0016\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b \u0010\u0018\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b!\u0010\u001a\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, d2 = {"partition", "", "array", "Lkotlin/UByteArray;", "left", "right", "partition-4UcCI2c", "([BII)I", "Lkotlin/UIntArray;", "partition-oBK06Vg", "([III)I", "Lkotlin/ULongArray;", "partition--nroSd4", "([JII)I", "Lkotlin/UShortArray;", "partition-Aa5vz7o", "([SII)I", "quickSort", "", "quickSort-4UcCI2c", "([BII)V", "quickSort-oBK06Vg", "([III)V", "quickSort--nroSd4", "([JII)V", "quickSort-Aa5vz7o", "([SII)V", "sortArray", "fromIndex", "toIndex", "sortArray-4UcCI2c", "sortArray-oBK06Vg", "sortArray--nroSd4", "sortArray-Aa5vz7o", "kotlin-stdlib"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class UArraySortingKt {
    /* renamed from: partition-4UcCI2c  reason: not valid java name */
    private static final int m504partition4UcCI2c(byte[] bArr, int i, int i2) {
        int i3;
        byte m131getw2LRezQ = UByteArray.m131getw2LRezQ(bArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                int m131getw2LRezQ2 = UByteArray.m131getw2LRezQ(bArr, i) & UByte.MAX_VALUE;
                i3 = m131getw2LRezQ & UByte.MAX_VALUE;
                if (Intrinsics.compare(m131getw2LRezQ2, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UByteArray.m131getw2LRezQ(bArr, i2) & UByte.MAX_VALUE, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                byte m131getw2LRezQ3 = UByteArray.m131getw2LRezQ(bArr, i);
                UByteArray.m136setVurrAj0(bArr, i, UByteArray.m131getw2LRezQ(bArr, i2));
                UByteArray.m136setVurrAj0(bArr, i2, m131getw2LRezQ3);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-4UcCI2c  reason: not valid java name */
    private static final void m508quickSort4UcCI2c(byte[] bArr, int i, int i2) {
        int m504partition4UcCI2c = m504partition4UcCI2c(bArr, i, i2);
        int i3 = m504partition4UcCI2c - 1;
        if (i < i3) {
            m508quickSort4UcCI2c(bArr, i, i3);
        }
        if (m504partition4UcCI2c < i2) {
            m508quickSort4UcCI2c(bArr, m504partition4UcCI2c, i2);
        }
    }

    /* renamed from: partition-Aa5vz7o  reason: not valid java name */
    private static final int m505partitionAa5vz7o(short[] sArr, int i, int i2) {
        int i3;
        short m391getMh2AYeg = UShortArray.m391getMh2AYeg(sArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                int m391getMh2AYeg2 = UShortArray.m391getMh2AYeg(sArr, i) & UShort.MAX_VALUE;
                i3 = m391getMh2AYeg & UShort.MAX_VALUE;
                if (Intrinsics.compare(m391getMh2AYeg2, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UShortArray.m391getMh2AYeg(sArr, i2) & UShort.MAX_VALUE, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                short m391getMh2AYeg3 = UShortArray.m391getMh2AYeg(sArr, i);
                UShortArray.m396set01HTLdE(sArr, i, UShortArray.m391getMh2AYeg(sArr, i2));
                UShortArray.m396set01HTLdE(sArr, i2, m391getMh2AYeg3);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-Aa5vz7o  reason: not valid java name */
    private static final void m509quickSortAa5vz7o(short[] sArr, int i, int i2) {
        int m505partitionAa5vz7o = m505partitionAa5vz7o(sArr, i, i2);
        int i3 = m505partitionAa5vz7o - 1;
        if (i < i3) {
            m509quickSortAa5vz7o(sArr, i, i3);
        }
        if (m505partitionAa5vz7o < i2) {
            m509quickSortAa5vz7o(sArr, m505partitionAa5vz7o, i2);
        }
    }

    /* renamed from: partition-oBK06Vg  reason: not valid java name */
    private static final int m506partitionoBK06Vg(int[] iArr, int i, int i2) {
        int m209getpVg5ArA = UIntArray.m209getpVg5ArA(iArr, (i + i2) / 2);
        while (i <= i2) {
            while (UnsignedKt.uintCompare(UIntArray.m209getpVg5ArA(iArr, i), m209getpVg5ArA) < 0) {
                i++;
            }
            while (UnsignedKt.uintCompare(UIntArray.m209getpVg5ArA(iArr, i2), m209getpVg5ArA) > 0) {
                i2--;
            }
            if (i <= i2) {
                int m209getpVg5ArA2 = UIntArray.m209getpVg5ArA(iArr, i);
                UIntArray.m214setVXSXFK8(iArr, i, UIntArray.m209getpVg5ArA(iArr, i2));
                UIntArray.m214setVXSXFK8(iArr, i2, m209getpVg5ArA2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-oBK06Vg  reason: not valid java name */
    private static final void m510quickSortoBK06Vg(int[] iArr, int i, int i2) {
        int m506partitionoBK06Vg = m506partitionoBK06Vg(iArr, i, i2);
        int i3 = m506partitionoBK06Vg - 1;
        if (i < i3) {
            m510quickSortoBK06Vg(iArr, i, i3);
        }
        if (m506partitionoBK06Vg < i2) {
            m510quickSortoBK06Vg(iArr, m506partitionoBK06Vg, i2);
        }
    }

    /* renamed from: partition--nroSd4  reason: not valid java name */
    private static final int m503partitionnroSd4(long[] jArr, int i, int i2) {
        long m287getsVKNKU = ULongArray.m287getsVKNKU(jArr, (i + i2) / 2);
        while (i <= i2) {
            while (UnsignedKt.ulongCompare(ULongArray.m287getsVKNKU(jArr, i), m287getsVKNKU) < 0) {
                i++;
            }
            while (UnsignedKt.ulongCompare(ULongArray.m287getsVKNKU(jArr, i2), m287getsVKNKU) > 0) {
                i2--;
            }
            if (i <= i2) {
                long m287getsVKNKU2 = ULongArray.m287getsVKNKU(jArr, i);
                ULongArray.m292setk8EXiF4(jArr, i, ULongArray.m287getsVKNKU(jArr, i2));
                ULongArray.m292setk8EXiF4(jArr, i2, m287getsVKNKU2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort--nroSd4  reason: not valid java name */
    private static final void m507quickSortnroSd4(long[] jArr, int i, int i2) {
        int m503partitionnroSd4 = m503partitionnroSd4(jArr, i, i2);
        int i3 = m503partitionnroSd4 - 1;
        if (i < i3) {
            m507quickSortnroSd4(jArr, i, i3);
        }
        if (m503partitionnroSd4 < i2) {
            m507quickSortnroSd4(jArr, m503partitionnroSd4, i2);
        }
    }

    /* renamed from: sortArray-4UcCI2c  reason: not valid java name */
    public static final void m512sortArray4UcCI2c(byte[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m508quickSort4UcCI2c(array, i, i2 - 1);
    }

    /* renamed from: sortArray-Aa5vz7o  reason: not valid java name */
    public static final void m513sortArrayAa5vz7o(short[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m509quickSortAa5vz7o(array, i, i2 - 1);
    }

    /* renamed from: sortArray-oBK06Vg  reason: not valid java name */
    public static final void m514sortArrayoBK06Vg(int[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m510quickSortoBK06Vg(array, i, i2 - 1);
    }

    /* renamed from: sortArray--nroSd4  reason: not valid java name */
    public static final void m511sortArraynroSd4(long[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m507quickSortnroSd4(array, i, i2 - 1);
    }
}
