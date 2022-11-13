package org.joda.time.field;

import java.util.Locale;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.ReadablePartial;
/* loaded from: classes2.dex */
public abstract class BaseDateTimeField extends DateTimeField {
    private final DateTimeFieldType iType;

    @Override // org.joda.time.DateTimeField
    public abstract int get(long j);

    @Override // org.joda.time.DateTimeField
    public abstract DurationField getDurationField();

    @Override // org.joda.time.DateTimeField
    public int getLeapAmount(long j) {
        return 0;
    }

    @Override // org.joda.time.DateTimeField
    public DurationField getLeapDurationField() {
        return null;
    }

    @Override // org.joda.time.DateTimeField
    public abstract int getMaximumValue();

    @Override // org.joda.time.DateTimeField
    public abstract int getMinimumValue();

    @Override // org.joda.time.DateTimeField
    public abstract DurationField getRangeDurationField();

    @Override // org.joda.time.DateTimeField
    public boolean isLeap(long j) {
        return false;
    }

    @Override // org.joda.time.DateTimeField
    public final boolean isSupported() {
        return true;
    }

    @Override // org.joda.time.DateTimeField
    public abstract long roundFloor(long j);

    @Override // org.joda.time.DateTimeField
    public abstract long set(long j, int i);

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseDateTimeField(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("The type must not be null");
        }
        this.iType = dateTimeFieldType;
    }

    @Override // org.joda.time.DateTimeField
    public final DateTimeFieldType getType() {
        return this.iType;
    }

    @Override // org.joda.time.DateTimeField
    public final String getName() {
        return this.iType.getName();
    }

    @Override // org.joda.time.DateTimeField
    public String getAsText(long j, Locale locale) {
        return getAsText(get(j), locale);
    }

    @Override // org.joda.time.DateTimeField
    public final String getAsText(long j) {
        return getAsText(j, (Locale) null);
    }

    @Override // org.joda.time.DateTimeField
    public String getAsText(ReadablePartial readablePartial, int i, Locale locale) {
        return getAsText(i, locale);
    }

    @Override // org.joda.time.DateTimeField
    public final String getAsText(ReadablePartial readablePartial, Locale locale) {
        return getAsText(readablePartial, readablePartial.get(getType()), locale);
    }

    @Override // org.joda.time.DateTimeField
    public String getAsText(int i, Locale locale) {
        return Integer.toString(i);
    }

    @Override // org.joda.time.DateTimeField
    public String getAsShortText(long j, Locale locale) {
        return getAsShortText(get(j), locale);
    }

    @Override // org.joda.time.DateTimeField
    public final String getAsShortText(long j) {
        return getAsShortText(j, (Locale) null);
    }

    @Override // org.joda.time.DateTimeField
    public String getAsShortText(ReadablePartial readablePartial, int i, Locale locale) {
        return getAsShortText(i, locale);
    }

    @Override // org.joda.time.DateTimeField
    public final String getAsShortText(ReadablePartial readablePartial, Locale locale) {
        return getAsShortText(readablePartial, readablePartial.get(getType()), locale);
    }

    @Override // org.joda.time.DateTimeField
    public String getAsShortText(int i, Locale locale) {
        return getAsText(i, locale);
    }

    @Override // org.joda.time.DateTimeField
    public long add(long j, int i) {
        return getDurationField().add(j, i);
    }

    @Override // org.joda.time.DateTimeField
    public long add(long j, long j2) {
        return getDurationField().add(j, j2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0058, code lost:
        if (r13 >= 0) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x005a, code lost:
        r3 = getMinimumValue(r10, r12);
        r4 = r12[r11] + r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0065, code lost:
        if (r4 < r3) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0067, code lost:
        r12[r11] = (int) r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x006b, code lost:
        if (r0 != null) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x006d, code lost:
        if (r11 == 0) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x006f, code lost:
        r0 = r10.getField(r11 - 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0085, code lost:
        if (getRangeDurationField().getType() != r0.getDurationField().getType()) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x008d, code lost:
        throw new java.lang.IllegalArgumentException("Fields invalid for add");
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0093, code lost:
        throw new java.lang.IllegalArgumentException("Maximum value exceeded for add");
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0094, code lost:
        r13 = r13 - ((r3 - 1) - r12[r11]);
        r12 = r0.add(r10, r11 - 1, r12, -1);
        r12[r11] = getMaximumValue(r10, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00ae, code lost:
        return set(r10, r11, r12, r12[r11]);
     */
    @Override // org.joda.time.DateTimeField
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int[] add(ReadablePartial readablePartial, int i, int[] iArr, int i2) {
        if (i2 != 0) {
            DateTimeField dateTimeField = null;
            while (true) {
                if (i2 <= 0) {
                    break;
                }
                int maximumValue = getMaximumValue(readablePartial, iArr);
                long j = iArr[i] + i2;
                if (j <= maximumValue) {
                    iArr[i] = (int) j;
                    break;
                }
                if (dateTimeField == null) {
                    if (i == 0) {
                        throw new IllegalArgumentException("Maximum value exceeded for add");
                    }
                    dateTimeField = readablePartial.getField(i - 1);
                    if (getRangeDurationField().getType() != dateTimeField.getDurationField().getType()) {
                        throw new IllegalArgumentException("Fields invalid for add");
                    }
                }
                i2 -= (maximumValue + 1) - iArr[i];
                iArr = dateTimeField.add(readablePartial, i - 1, iArr, 1);
                iArr[i] = getMinimumValue(readablePartial, iArr);
            }
        } else {
            return iArr;
        }
    }

    @Override // org.joda.time.DateTimeField
    public int[] addWrapPartial(ReadablePartial readablePartial, int i, int[] iArr, int i2) {
        if (i2 == 0) {
            return iArr;
        }
        DateTimeField dateTimeField = null;
        while (true) {
            if (i2 <= 0) {
                break;
            }
            int maximumValue = getMaximumValue(readablePartial, iArr);
            long j = iArr[i] + i2;
            if (j <= maximumValue) {
                iArr[i] = (int) j;
                break;
            }
            if (dateTimeField == null) {
                if (i == 0) {
                    i2 -= (maximumValue + 1) - iArr[i];
                    iArr[i] = getMinimumValue(readablePartial, iArr);
                } else {
                    dateTimeField = readablePartial.getField(i - 1);
                    if (getRangeDurationField().getType() != dateTimeField.getDurationField().getType()) {
                        throw new IllegalArgumentException("Fields invalid for add");
                    }
                }
            }
            i2 -= (maximumValue + 1) - iArr[i];
            iArr = dateTimeField.addWrapPartial(readablePartial, i - 1, iArr, 1);
            iArr[i] = getMinimumValue(readablePartial, iArr);
        }
        while (true) {
            if (i2 >= 0) {
                break;
            }
            int minimumValue = getMinimumValue(readablePartial, iArr);
            long j2 = iArr[i] + i2;
            if (j2 >= minimumValue) {
                iArr[i] = (int) j2;
                break;
            }
            if (dateTimeField == null) {
                if (i == 0) {
                    i2 -= (minimumValue - 1) - iArr[i];
                    iArr[i] = getMaximumValue(readablePartial, iArr);
                } else {
                    dateTimeField = readablePartial.getField(i - 1);
                    if (getRangeDurationField().getType() != dateTimeField.getDurationField().getType()) {
                        throw new IllegalArgumentException("Fields invalid for add");
                    }
                }
            }
            i2 -= (minimumValue - 1) - iArr[i];
            iArr = dateTimeField.addWrapPartial(readablePartial, i - 1, iArr, -1);
            iArr[i] = getMaximumValue(readablePartial, iArr);
        }
        return set(readablePartial, i, iArr, iArr[i]);
    }

    @Override // org.joda.time.DateTimeField
    public long addWrapField(long j, int i) {
        return set(j, FieldUtils.getWrappedValue(get(j), i, getMinimumValue(j), getMaximumValue(j)));
    }

    @Override // org.joda.time.DateTimeField
    public int[] addWrapField(ReadablePartial readablePartial, int i, int[] iArr, int i2) {
        return set(readablePartial, i, iArr, FieldUtils.getWrappedValue(iArr[i], i2, getMinimumValue(readablePartial), getMaximumValue(readablePartial)));
    }

    @Override // org.joda.time.DateTimeField
    public int getDifference(long j, long j2) {
        return getDurationField().getDifference(j, j2);
    }

    @Override // org.joda.time.DateTimeField
    public long getDifferenceAsLong(long j, long j2) {
        return getDurationField().getDifferenceAsLong(j, j2);
    }

    @Override // org.joda.time.DateTimeField
    public int[] set(ReadablePartial readablePartial, int i, int[] iArr, int i2) {
        FieldUtils.verifyValueBounds(this, i2, getMinimumValue(readablePartial, iArr), getMaximumValue(readablePartial, iArr));
        iArr[i] = i2;
        while (true) {
            i++;
            if (i >= readablePartial.size()) {
                return iArr;
            }
            DateTimeField field = readablePartial.getField(i);
            if (iArr[i] > field.getMaximumValue(readablePartial, iArr)) {
                iArr[i] = field.getMaximumValue(readablePartial, iArr);
            }
            if (iArr[i] < field.getMinimumValue(readablePartial, iArr)) {
                iArr[i] = field.getMinimumValue(readablePartial, iArr);
            }
        }
    }

    @Override // org.joda.time.DateTimeField
    public long set(long j, String str, Locale locale) {
        return set(j, convertText(str, locale));
    }

    @Override // org.joda.time.DateTimeField
    public final long set(long j, String str) {
        return set(j, str, null);
    }

    @Override // org.joda.time.DateTimeField
    public int[] set(ReadablePartial readablePartial, int i, int[] iArr, String str, Locale locale) {
        return set(readablePartial, i, iArr, convertText(str, locale));
    }

    protected int convertText(String str, Locale locale) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            throw new IllegalFieldValueException(getType(), str);
        }
    }

    @Override // org.joda.time.DateTimeField
    public int getMinimumValue(long j) {
        return getMinimumValue();
    }

    @Override // org.joda.time.DateTimeField
    public int getMinimumValue(ReadablePartial readablePartial) {
        return getMinimumValue();
    }

    @Override // org.joda.time.DateTimeField
    public int getMinimumValue(ReadablePartial readablePartial, int[] iArr) {
        return getMinimumValue(readablePartial);
    }

    @Override // org.joda.time.DateTimeField
    public int getMaximumValue(long j) {
        return getMaximumValue();
    }

    @Override // org.joda.time.DateTimeField
    public int getMaximumValue(ReadablePartial readablePartial) {
        return getMaximumValue();
    }

    @Override // org.joda.time.DateTimeField
    public int getMaximumValue(ReadablePartial readablePartial, int[] iArr) {
        return getMaximumValue(readablePartial);
    }

    @Override // org.joda.time.DateTimeField
    public int getMaximumTextLength(Locale locale) {
        int maximumValue = getMaximumValue();
        if (maximumValue >= 0) {
            if (maximumValue < 10) {
                return 1;
            }
            if (maximumValue < 100) {
                return 2;
            }
            if (maximumValue < 1000) {
                return 3;
            }
        }
        return Integer.toString(maximumValue).length();
    }

    @Override // org.joda.time.DateTimeField
    public int getMaximumShortTextLength(Locale locale) {
        return getMaximumTextLength(locale);
    }

    @Override // org.joda.time.DateTimeField
    public long roundCeiling(long j) {
        long roundFloor = roundFloor(j);
        return roundFloor != j ? add(roundFloor, 1) : j;
    }

    @Override // org.joda.time.DateTimeField
    public long roundHalfFloor(long j) {
        long roundFloor = roundFloor(j);
        long roundCeiling = roundCeiling(j);
        return j - roundFloor <= roundCeiling - j ? roundFloor : roundCeiling;
    }

    @Override // org.joda.time.DateTimeField
    public long roundHalfCeiling(long j) {
        long roundFloor = roundFloor(j);
        long roundCeiling = roundCeiling(j);
        return roundCeiling - j <= j - roundFloor ? roundCeiling : roundFloor;
    }

    @Override // org.joda.time.DateTimeField
    public long roundHalfEven(long j) {
        long roundFloor = roundFloor(j);
        long roundCeiling = roundCeiling(j);
        long j2 = j - roundFloor;
        long j3 = roundCeiling - j;
        return j2 < j3 ? roundFloor : (j3 >= j2 && (get(roundCeiling) & 1) != 0) ? roundFloor : roundCeiling;
    }

    @Override // org.joda.time.DateTimeField
    public long remainder(long j) {
        return j - roundFloor(j);
    }

    @Override // org.joda.time.DateTimeField
    public String toString() {
        return "DateTimeField[" + getName() + ']';
    }
}
