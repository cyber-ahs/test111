package org.joda.time.format;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.internal.connection.RealConnection;
import okio.Utf8;
import org.joda.time.Chronology;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.joda.time.ReadablePartial;
import org.joda.time.field.MillisDurationField;
import org.joda.time.field.PreciseDateTimeField;
/* loaded from: classes2.dex */
public class DateTimeFormatterBuilder {
    private ArrayList<Object> iElementPairs = new ArrayList<>();
    private Object iFormatter;

    public DateTimeFormatter toFormatter() {
        Object formatter = getFormatter();
        InternalPrinter internalPrinter = isPrinter(formatter) ? (InternalPrinter) formatter : null;
        InternalParser internalParser = isParser(formatter) ? (InternalParser) formatter : null;
        if (internalPrinter != null || internalParser != null) {
            return new DateTimeFormatter(internalPrinter, internalParser);
        }
        throw new UnsupportedOperationException("Both printing and parsing not supported");
    }

    public DateTimePrinter toPrinter() {
        Object formatter = getFormatter();
        if (isPrinter(formatter)) {
            return InternalPrinterDateTimePrinter.of((InternalPrinter) formatter);
        }
        throw new UnsupportedOperationException("Printing is not supported");
    }

    public DateTimeParser toParser() {
        Object formatter = getFormatter();
        if (isParser(formatter)) {
            return InternalParserDateTimeParser.of((InternalParser) formatter);
        }
        throw new UnsupportedOperationException("Parsing is not supported");
    }

    public boolean canBuildFormatter() {
        return isFormatter(getFormatter());
    }

    public boolean canBuildPrinter() {
        return isPrinter(getFormatter());
    }

    public boolean canBuildParser() {
        return isParser(getFormatter());
    }

    public void clear() {
        this.iFormatter = null;
        this.iElementPairs.clear();
    }

    public DateTimeFormatterBuilder append(DateTimeFormatter dateTimeFormatter) {
        if (dateTimeFormatter == null) {
            throw new IllegalArgumentException("No formatter supplied");
        }
        return append0(dateTimeFormatter.getPrinter0(), dateTimeFormatter.getParser0());
    }

    public DateTimeFormatterBuilder append(DateTimePrinter dateTimePrinter) {
        checkPrinter(dateTimePrinter);
        return append0(DateTimePrinterInternalPrinter.of(dateTimePrinter), null);
    }

    public DateTimeFormatterBuilder append(DateTimeParser dateTimeParser) {
        checkParser(dateTimeParser);
        return append0(null, DateTimeParserInternalParser.of(dateTimeParser));
    }

    public DateTimeFormatterBuilder append(DateTimePrinter dateTimePrinter, DateTimeParser dateTimeParser) {
        checkPrinter(dateTimePrinter);
        checkParser(dateTimeParser);
        return append0(DateTimePrinterInternalPrinter.of(dateTimePrinter), DateTimeParserInternalParser.of(dateTimeParser));
    }

    public DateTimeFormatterBuilder append(DateTimePrinter dateTimePrinter, DateTimeParser[] dateTimeParserArr) {
        if (dateTimePrinter != null) {
            checkPrinter(dateTimePrinter);
        }
        if (dateTimeParserArr == null) {
            throw new IllegalArgumentException("No parsers supplied");
        }
        int length = dateTimeParserArr.length;
        int i = 0;
        if (length == 1) {
            if (dateTimeParserArr[0] == null) {
                throw new IllegalArgumentException("No parser supplied");
            }
            return append0(DateTimePrinterInternalPrinter.of(dateTimePrinter), DateTimeParserInternalParser.of(dateTimeParserArr[0]));
        }
        InternalParser[] internalParserArr = new InternalParser[length];
        while (i < length - 1) {
            InternalParser of = DateTimeParserInternalParser.of(dateTimeParserArr[i]);
            internalParserArr[i] = of;
            if (of == null) {
                throw new IllegalArgumentException("Incomplete parser array");
            }
            i++;
        }
        internalParserArr[i] = DateTimeParserInternalParser.of(dateTimeParserArr[i]);
        return append0(DateTimePrinterInternalPrinter.of(dateTimePrinter), new MatchingParser(internalParserArr));
    }

    public DateTimeFormatterBuilder appendOptional(DateTimeParser dateTimeParser) {
        checkParser(dateTimeParser);
        return append0(null, new MatchingParser(new InternalParser[]{DateTimeParserInternalParser.of(dateTimeParser), null}));
    }

    private void checkParser(DateTimeParser dateTimeParser) {
        if (dateTimeParser == null) {
            throw new IllegalArgumentException("No parser supplied");
        }
    }

    private void checkPrinter(DateTimePrinter dateTimePrinter) {
        if (dateTimePrinter == null) {
            throw new IllegalArgumentException("No printer supplied");
        }
    }

    private DateTimeFormatterBuilder append0(Object obj) {
        this.iFormatter = null;
        this.iElementPairs.add(obj);
        this.iElementPairs.add(obj);
        return this;
    }

    private DateTimeFormatterBuilder append0(InternalPrinter internalPrinter, InternalParser internalParser) {
        this.iFormatter = null;
        this.iElementPairs.add(internalPrinter);
        this.iElementPairs.add(internalParser);
        return this;
    }

    public DateTimeFormatterBuilder appendLiteral(char c) {
        return append0(new CharacterLiteral(c));
    }

    public DateTimeFormatterBuilder appendLiteral(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Literal must not be null");
        }
        int length = str.length();
        if (length != 0) {
            if (length == 1) {
                return append0(new CharacterLiteral(str.charAt(0)));
            }
            return append0(new StringLiteral(str));
        }
        return this;
    }

    public DateTimeFormatterBuilder appendDecimal(DateTimeFieldType dateTimeFieldType, int i, int i2) {
        if (dateTimeFieldType != null) {
            if (i2 < i) {
                i2 = i;
            }
            if (i < 0 || i2 <= 0) {
                throw new IllegalArgumentException();
            }
            if (i <= 1) {
                return append0(new UnpaddedNumber(dateTimeFieldType, i2, false));
            }
            return append0(new PaddedNumber(dateTimeFieldType, i2, false, i));
        }
        throw new IllegalArgumentException("Field type must not be null");
    }

    public DateTimeFormatterBuilder appendFixedDecimal(DateTimeFieldType dateTimeFieldType, int i) {
        if (dateTimeFieldType != null) {
            if (i <= 0) {
                throw new IllegalArgumentException("Illegal number of digits: " + i);
            }
            return append0(new FixedNumber(dateTimeFieldType, i, false));
        }
        throw new IllegalArgumentException("Field type must not be null");
    }

    public DateTimeFormatterBuilder appendSignedDecimal(DateTimeFieldType dateTimeFieldType, int i, int i2) {
        if (dateTimeFieldType != null) {
            if (i2 < i) {
                i2 = i;
            }
            if (i < 0 || i2 <= 0) {
                throw new IllegalArgumentException();
            }
            if (i <= 1) {
                return append0(new UnpaddedNumber(dateTimeFieldType, i2, true));
            }
            return append0(new PaddedNumber(dateTimeFieldType, i2, true, i));
        }
        throw new IllegalArgumentException("Field type must not be null");
    }

    public DateTimeFormatterBuilder appendFixedSignedDecimal(DateTimeFieldType dateTimeFieldType, int i) {
        if (dateTimeFieldType != null) {
            if (i <= 0) {
                throw new IllegalArgumentException("Illegal number of digits: " + i);
            }
            return append0(new FixedNumber(dateTimeFieldType, i, true));
        }
        throw new IllegalArgumentException("Field type must not be null");
    }

    public DateTimeFormatterBuilder appendText(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        return append0(new TextField(dateTimeFieldType, false));
    }

    public DateTimeFormatterBuilder appendShortText(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        return append0(new TextField(dateTimeFieldType, true));
    }

    public DateTimeFormatterBuilder appendFraction(DateTimeFieldType dateTimeFieldType, int i, int i2) {
        if (dateTimeFieldType != null) {
            if (i2 < i) {
                i2 = i;
            }
            if (i < 0 || i2 <= 0) {
                throw new IllegalArgumentException();
            }
            return append0(new Fraction(dateTimeFieldType, i, i2));
        }
        throw new IllegalArgumentException("Field type must not be null");
    }

    public DateTimeFormatterBuilder appendFractionOfSecond(int i, int i2) {
        return appendFraction(DateTimeFieldType.secondOfDay(), i, i2);
    }

    public DateTimeFormatterBuilder appendFractionOfMinute(int i, int i2) {
        return appendFraction(DateTimeFieldType.minuteOfDay(), i, i2);
    }

    public DateTimeFormatterBuilder appendFractionOfHour(int i, int i2) {
        return appendFraction(DateTimeFieldType.hourOfDay(), i, i2);
    }

    public DateTimeFormatterBuilder appendFractionOfDay(int i, int i2) {
        return appendFraction(DateTimeFieldType.dayOfYear(), i, i2);
    }

    public DateTimeFormatterBuilder appendMillisOfSecond(int i) {
        return appendDecimal(DateTimeFieldType.millisOfSecond(), i, 3);
    }

    public DateTimeFormatterBuilder appendMillisOfDay(int i) {
        return appendDecimal(DateTimeFieldType.millisOfDay(), i, 8);
    }

    public DateTimeFormatterBuilder appendSecondOfMinute(int i) {
        return appendDecimal(DateTimeFieldType.secondOfMinute(), i, 2);
    }

    public DateTimeFormatterBuilder appendSecondOfDay(int i) {
        return appendDecimal(DateTimeFieldType.secondOfDay(), i, 5);
    }

    public DateTimeFormatterBuilder appendMinuteOfHour(int i) {
        return appendDecimal(DateTimeFieldType.minuteOfHour(), i, 2);
    }

    public DateTimeFormatterBuilder appendMinuteOfDay(int i) {
        return appendDecimal(DateTimeFieldType.minuteOfDay(), i, 4);
    }

    public DateTimeFormatterBuilder appendHourOfDay(int i) {
        return appendDecimal(DateTimeFieldType.hourOfDay(), i, 2);
    }

    public DateTimeFormatterBuilder appendClockhourOfDay(int i) {
        return appendDecimal(DateTimeFieldType.clockhourOfDay(), i, 2);
    }

    public DateTimeFormatterBuilder appendHourOfHalfday(int i) {
        return appendDecimal(DateTimeFieldType.hourOfHalfday(), i, 2);
    }

    public DateTimeFormatterBuilder appendClockhourOfHalfday(int i) {
        return appendDecimal(DateTimeFieldType.clockhourOfHalfday(), i, 2);
    }

    public DateTimeFormatterBuilder appendDayOfWeek(int i) {
        return appendDecimal(DateTimeFieldType.dayOfWeek(), i, 1);
    }

    public DateTimeFormatterBuilder appendDayOfMonth(int i) {
        return appendDecimal(DateTimeFieldType.dayOfMonth(), i, 2);
    }

    public DateTimeFormatterBuilder appendDayOfYear(int i) {
        return appendDecimal(DateTimeFieldType.dayOfYear(), i, 3);
    }

    public DateTimeFormatterBuilder appendWeekOfWeekyear(int i) {
        return appendDecimal(DateTimeFieldType.weekOfWeekyear(), i, 2);
    }

    public DateTimeFormatterBuilder appendWeekyear(int i, int i2) {
        return appendSignedDecimal(DateTimeFieldType.weekyear(), i, i2);
    }

    public DateTimeFormatterBuilder appendMonthOfYear(int i) {
        return appendDecimal(DateTimeFieldType.monthOfYear(), i, 2);
    }

    public DateTimeFormatterBuilder appendYear(int i, int i2) {
        return appendSignedDecimal(DateTimeFieldType.year(), i, i2);
    }

    public DateTimeFormatterBuilder appendTwoDigitYear(int i) {
        return appendTwoDigitYear(i, false);
    }

    public DateTimeFormatterBuilder appendTwoDigitYear(int i, boolean z) {
        return append0(new TwoDigitYear(DateTimeFieldType.year(), i, z));
    }

    public DateTimeFormatterBuilder appendTwoDigitWeekyear(int i) {
        return appendTwoDigitWeekyear(i, false);
    }

    public DateTimeFormatterBuilder appendTwoDigitWeekyear(int i, boolean z) {
        return append0(new TwoDigitYear(DateTimeFieldType.weekyear(), i, z));
    }

    public DateTimeFormatterBuilder appendYearOfEra(int i, int i2) {
        return appendDecimal(DateTimeFieldType.yearOfEra(), i, i2);
    }

    public DateTimeFormatterBuilder appendYearOfCentury(int i, int i2) {
        return appendDecimal(DateTimeFieldType.yearOfCentury(), i, i2);
    }

    public DateTimeFormatterBuilder appendCenturyOfEra(int i, int i2) {
        return appendSignedDecimal(DateTimeFieldType.centuryOfEra(), i, i2);
    }

    public DateTimeFormatterBuilder appendHalfdayOfDayText() {
        return appendText(DateTimeFieldType.halfdayOfDay());
    }

    public DateTimeFormatterBuilder appendDayOfWeekText() {
        return appendText(DateTimeFieldType.dayOfWeek());
    }

    public DateTimeFormatterBuilder appendDayOfWeekShortText() {
        return appendShortText(DateTimeFieldType.dayOfWeek());
    }

    public DateTimeFormatterBuilder appendMonthOfYearText() {
        return appendText(DateTimeFieldType.monthOfYear());
    }

    public DateTimeFormatterBuilder appendMonthOfYearShortText() {
        return appendShortText(DateTimeFieldType.monthOfYear());
    }

    public DateTimeFormatterBuilder appendEraText() {
        return appendText(DateTimeFieldType.era());
    }

    public DateTimeFormatterBuilder appendTimeZoneName() {
        return append0(new TimeZoneName(0, null), null);
    }

    public DateTimeFormatterBuilder appendTimeZoneName(Map<String, DateTimeZone> map) {
        TimeZoneName timeZoneName = new TimeZoneName(0, map);
        return append0(timeZoneName, timeZoneName);
    }

    public DateTimeFormatterBuilder appendTimeZoneShortName() {
        return append0(new TimeZoneName(1, null), null);
    }

    public DateTimeFormatterBuilder appendTimeZoneShortName(Map<String, DateTimeZone> map) {
        TimeZoneName timeZoneName = new TimeZoneName(1, map);
        return append0(timeZoneName, timeZoneName);
    }

    public DateTimeFormatterBuilder appendTimeZoneId() {
        return append0(TimeZoneId.INSTANCE, TimeZoneId.INSTANCE);
    }

    public DateTimeFormatterBuilder appendTimeZoneOffset(String str, boolean z, int i, int i2) {
        return append0(new TimeZoneOffset(str, str, z, i, i2));
    }

    public DateTimeFormatterBuilder appendTimeZoneOffset(String str, String str2, boolean z, int i, int i2) {
        return append0(new TimeZoneOffset(str, str2, z, i, i2));
    }

    public DateTimeFormatterBuilder appendPattern(String str) {
        DateTimeFormat.appendPatternTo(this, str);
        return this;
    }

    private Object getFormatter() {
        Object obj = this.iFormatter;
        if (obj == null) {
            if (this.iElementPairs.size() == 2) {
                Object obj2 = this.iElementPairs.get(0);
                Object obj3 = this.iElementPairs.get(1);
                if (obj2 == null) {
                    obj = obj3;
                } else if (obj2 == obj3 || obj3 == null) {
                    obj = obj2;
                }
            }
            if (obj == null) {
                obj = new Composite(this.iElementPairs);
            }
            this.iFormatter = obj;
        }
        return obj;
    }

    private boolean isPrinter(Object obj) {
        if (obj instanceof InternalPrinter) {
            if (obj instanceof Composite) {
                return ((Composite) obj).isPrinter();
            }
            return true;
        }
        return false;
    }

    private boolean isParser(Object obj) {
        if (obj instanceof InternalParser) {
            if (obj instanceof Composite) {
                return ((Composite) obj).isParser();
            }
            return true;
        }
        return false;
    }

    private boolean isFormatter(Object obj) {
        return isPrinter(obj) || isParser(obj);
    }

    static void appendUnknownString(Appendable appendable, int i) throws IOException {
        while (true) {
            i--;
            if (i < 0) {
                return;
            }
            appendable.append(Utf8.REPLACEMENT_CHARACTER);
        }
    }

    /* loaded from: classes2.dex */
    static class CharacterLiteral implements InternalPrinter, InternalParser {
        private final char iValue;

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return 1;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return 1;
        }

        CharacterLiteral(char c) {
            this.iValue = c;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            appendable.append(this.iValue);
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            appendable.append(this.iValue);
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            char upperCase;
            char upperCase2;
            if (i >= charSequence.length()) {
                return i ^ (-1);
            }
            char charAt = charSequence.charAt(i);
            char c = this.iValue;
            return (charAt == c || (upperCase = Character.toUpperCase(charAt)) == (upperCase2 = Character.toUpperCase(c)) || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2)) ? i + 1 : i ^ (-1);
        }
    }

    /* loaded from: classes2.dex */
    static class StringLiteral implements InternalPrinter, InternalParser {
        private final String iValue;

        StringLiteral(String str) {
            this.iValue = str;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return this.iValue.length();
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            appendable.append(this.iValue);
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            appendable.append(this.iValue);
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return this.iValue.length();
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            return DateTimeFormatterBuilder.csStartsWithIgnoreCase(charSequence, i, this.iValue) ? i + this.iValue.length() : i ^ (-1);
        }
    }

    /* loaded from: classes2.dex */
    static abstract class NumberFormatter implements InternalPrinter, InternalParser {
        protected final DateTimeFieldType iFieldType;
        protected final int iMaxParsedDigits;
        protected final boolean iSigned;

        NumberFormatter(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            this.iFieldType = dateTimeFieldType;
            this.iMaxParsedDigits = i;
            this.iSigned = z;
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return this.iMaxParsedDigits;
        }

        /* JADX WARN: Code restructure failed: missing block: B:28:0x005e, code lost:
            if (r10 <= '9') goto L12;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x0061, code lost:
            r5 = r5 + 1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            int i2;
            int i3;
            char charAt;
            int min = Math.min(this.iMaxParsedDigits, charSequence.length() - i);
            int i4 = 0;
            boolean z = false;
            boolean z2 = false;
            while (i4 < min) {
                int i5 = i + i4;
                char charAt2 = charSequence.charAt(i5);
                if (i4 != 0 || ((charAt2 != '-' && charAt2 != '+') || !this.iSigned)) {
                    break;
                }
                boolean z3 = charAt2 == '-';
                boolean z4 = charAt2 == '+';
                int i6 = i4 + 1;
                if (i6 >= min || (charAt = charSequence.charAt(i5 + 1)) < '0' || charAt > '9') {
                    boolean z5 = z3;
                    z2 = z4;
                    z = z5;
                    break;
                }
                min = Math.min(min + 1, charSequence.length() - i);
                i4 = i6;
                boolean z6 = z3;
                z2 = z4;
                z = z6;
            }
            if (i4 == 0) {
                return i ^ (-1);
            }
            if (i4 < 9) {
                int i7 = (z || z2) ? i + 1 : i;
                int i8 = i7 + 1;
                try {
                    int charAt3 = charSequence.charAt(i7) - '0';
                    i2 = i + i4;
                    while (i8 < i2) {
                        i8++;
                        charAt3 = (((charAt3 << 3) + (charAt3 << 1)) + charSequence.charAt(i8)) - 48;
                    }
                    i3 = z ? -charAt3 : charAt3;
                } catch (StringIndexOutOfBoundsException unused) {
                    return i ^ (-1);
                }
            } else if (z2) {
                i2 = i + i4;
                i3 = Integer.parseInt(charSequence.subSequence(i + 1, i2).toString());
            } else {
                int i9 = i + i4;
                i3 = Integer.parseInt(charSequence.subSequence(i, i9).toString());
                i2 = i9;
            }
            dateTimeParserBucket.saveField(this.iFieldType, i3);
            return i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class UnpaddedNumber extends NumberFormatter {
        protected UnpaddedNumber(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            super(dateTimeFieldType, i, z);
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return this.iMaxParsedDigits;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            try {
                FormatUtils.appendUnpaddedInteger(appendable, this.iFieldType.getField(chronology).get(j));
            } catch (RuntimeException unused) {
                appendable.append(Utf8.REPLACEMENT_CHARACTER);
            }
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            if (readablePartial.isSupported(this.iFieldType)) {
                try {
                    FormatUtils.appendUnpaddedInteger(appendable, readablePartial.get(this.iFieldType));
                    return;
                } catch (RuntimeException unused) {
                    appendable.append(Utf8.REPLACEMENT_CHARACTER);
                    return;
                }
            }
            appendable.append(Utf8.REPLACEMENT_CHARACTER);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class PaddedNumber extends NumberFormatter {
        protected final int iMinPrintedDigits;

        protected PaddedNumber(DateTimeFieldType dateTimeFieldType, int i, boolean z, int i2) {
            super(dateTimeFieldType, i, z);
            this.iMinPrintedDigits = i2;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return this.iMaxParsedDigits;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            try {
                FormatUtils.appendPaddedInteger(appendable, this.iFieldType.getField(chronology).get(j), this.iMinPrintedDigits);
            } catch (RuntimeException unused) {
                DateTimeFormatterBuilder.appendUnknownString(appendable, this.iMinPrintedDigits);
            }
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            if (readablePartial.isSupported(this.iFieldType)) {
                try {
                    FormatUtils.appendPaddedInteger(appendable, readablePartial.get(this.iFieldType), this.iMinPrintedDigits);
                    return;
                } catch (RuntimeException unused) {
                    DateTimeFormatterBuilder.appendUnknownString(appendable, this.iMinPrintedDigits);
                    return;
                }
            }
            DateTimeFormatterBuilder.appendUnknownString(appendable, this.iMinPrintedDigits);
        }
    }

    /* loaded from: classes2.dex */
    static class FixedNumber extends PaddedNumber {
        protected FixedNumber(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            super(dateTimeFieldType, i, z, i);
        }

        @Override // org.joda.time.format.DateTimeFormatterBuilder.NumberFormatter, org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            int i2;
            char charAt;
            int parseInto = super.parseInto(dateTimeParserBucket, charSequence, i);
            if (parseInto >= 0 && parseInto != (i2 = this.iMaxParsedDigits + i)) {
                if (this.iSigned && ((charAt = charSequence.charAt(i)) == '-' || charAt == '+')) {
                    i2++;
                }
                return parseInto > i2 ? (i2 + 1) ^ (-1) : parseInto < i2 ? parseInto ^ (-1) : parseInto;
            }
            return parseInto;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class TwoDigitYear implements InternalPrinter, InternalParser {
        private final boolean iLenientParse;
        private final int iPivot;
        private final DateTimeFieldType iType;

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return 2;
        }

        TwoDigitYear(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            this.iType = dateTimeFieldType;
            this.iPivot = i;
            this.iLenientParse = z;
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return this.iLenientParse ? 4 : 2;
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            int i2;
            int i3;
            int i4;
            int length = charSequence.length() - i;
            if (this.iLenientParse) {
                int i5 = 0;
                boolean z = false;
                boolean z2 = false;
                while (i5 < length) {
                    char charAt = charSequence.charAt(i + i5);
                    if (i5 != 0 || (charAt != '-' && charAt != '+')) {
                        if (charAt < '0' || charAt > '9') {
                            break;
                        }
                        i5++;
                    } else {
                        z2 = charAt == '-';
                        if (z2) {
                            i5++;
                        } else {
                            i++;
                            length--;
                        }
                        z = true;
                    }
                }
                if (i5 == 0) {
                    return i ^ (-1);
                }
                if (z || i5 != 2) {
                    if (i5 >= 9) {
                        i2 = i5 + i;
                        i3 = Integer.parseInt(charSequence.subSequence(i, i2).toString());
                    } else {
                        int i6 = z2 ? i + 1 : i;
                        int i7 = i6 + 1;
                        try {
                            int charAt2 = charSequence.charAt(i6) - '0';
                            i2 = i5 + i;
                            while (i7 < i2) {
                                i7++;
                                charAt2 = (((charAt2 << 3) + (charAt2 << 1)) + charSequence.charAt(i7)) - 48;
                            }
                            i3 = z2 ? -charAt2 : charAt2;
                        } catch (StringIndexOutOfBoundsException unused) {
                            return i ^ (-1);
                        }
                    }
                    dateTimeParserBucket.saveField(this.iType, i3);
                    return i2;
                }
            } else if (Math.min(2, length) < 2) {
                return i ^ (-1);
            }
            char charAt3 = charSequence.charAt(i);
            if (charAt3 < '0' || charAt3 > '9') {
                return i ^ (-1);
            }
            int i8 = charAt3 - '0';
            char charAt4 = charSequence.charAt(i + 1);
            if (charAt4 < '0' || charAt4 > '9') {
                return i ^ (-1);
            }
            int i9 = (((i8 << 3) + (i8 << 1)) + charAt4) - 48;
            int i10 = this.iPivot;
            if (dateTimeParserBucket.getPivotYear() != null) {
                i10 = dateTimeParserBucket.getPivotYear().intValue();
            }
            int i11 = i10 - 50;
            if (i11 >= 0) {
                i4 = i11 % 100;
            } else {
                i4 = ((i11 + 1) % 100) + 99;
            }
            dateTimeParserBucket.saveField(this.iType, i9 + ((i11 + (i9 < i4 ? 100 : 0)) - i4));
            return i + 2;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            int twoDigitYear = getTwoDigitYear(j, chronology);
            if (twoDigitYear < 0) {
                appendable.append(Utf8.REPLACEMENT_CHARACTER);
                appendable.append(Utf8.REPLACEMENT_CHARACTER);
                return;
            }
            FormatUtils.appendPaddedInteger(appendable, twoDigitYear, 2);
        }

        private int getTwoDigitYear(long j, Chronology chronology) {
            try {
                int i = this.iType.getField(chronology).get(j);
                if (i < 0) {
                    i = -i;
                }
                return i % 100;
            } catch (RuntimeException unused) {
                return -1;
            }
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            int twoDigitYear = getTwoDigitYear(readablePartial);
            if (twoDigitYear < 0) {
                appendable.append(Utf8.REPLACEMENT_CHARACTER);
                appendable.append(Utf8.REPLACEMENT_CHARACTER);
                return;
            }
            FormatUtils.appendPaddedInteger(appendable, twoDigitYear, 2);
        }

        private int getTwoDigitYear(ReadablePartial readablePartial) {
            if (readablePartial.isSupported(this.iType)) {
                try {
                    int i = readablePartial.get(this.iType);
                    if (i < 0) {
                        i = -i;
                    }
                    return i % 100;
                } catch (RuntimeException unused) {
                    return -1;
                }
            }
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class TextField implements InternalPrinter, InternalParser {
        private static Map<Locale, Map<DateTimeFieldType, Object[]>> cParseCache = new ConcurrentHashMap();
        private final DateTimeFieldType iFieldType;
        private final boolean iShort;

        TextField(DateTimeFieldType dateTimeFieldType, boolean z) {
            this.iFieldType = dateTimeFieldType;
            this.iShort = z;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return this.iShort ? 6 : 20;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            try {
                appendable.append(print(j, chronology, locale));
            } catch (RuntimeException unused) {
                appendable.append(Utf8.REPLACEMENT_CHARACTER);
            }
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            try {
                appendable.append(print(readablePartial, locale));
            } catch (RuntimeException unused) {
                appendable.append(Utf8.REPLACEMENT_CHARACTER);
            }
        }

        private String print(long j, Chronology chronology, Locale locale) {
            DateTimeField field = this.iFieldType.getField(chronology);
            if (this.iShort) {
                return field.getAsShortText(j, locale);
            }
            return field.getAsText(j, locale);
        }

        private String print(ReadablePartial readablePartial, Locale locale) {
            if (readablePartial.isSupported(this.iFieldType)) {
                DateTimeField field = this.iFieldType.getField(readablePartial.getChronology());
                if (this.iShort) {
                    return field.getAsShortText(readablePartial, locale);
                }
                return field.getAsText(readablePartial, locale);
            }
            return "�";
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return estimatePrintedLength();
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            int intValue;
            Map map;
            Locale locale = dateTimeParserBucket.getLocale();
            Map<DateTimeFieldType, Object[]> map2 = cParseCache.get(locale);
            if (map2 == null) {
                map2 = new ConcurrentHashMap<>();
                cParseCache.put(locale, map2);
            }
            Object[] objArr = map2.get(this.iFieldType);
            if (objArr == null) {
                map = new ConcurrentHashMap(32);
                MutableDateTime.Property property = new MutableDateTime(0L, DateTimeZone.UTC).property(this.iFieldType);
                int minimumValueOverall = property.getMinimumValueOverall();
                int maximumValueOverall = property.getMaximumValueOverall();
                if (maximumValueOverall - minimumValueOverall > 32) {
                    return i ^ (-1);
                }
                intValue = property.getMaximumTextLength(locale);
                while (minimumValueOverall <= maximumValueOverall) {
                    property.set(minimumValueOverall);
                    map.put(property.getAsShortText(locale), Boolean.TRUE);
                    map.put(property.getAsShortText(locale).toLowerCase(locale), Boolean.TRUE);
                    map.put(property.getAsShortText(locale).toUpperCase(locale), Boolean.TRUE);
                    map.put(property.getAsText(locale), Boolean.TRUE);
                    map.put(property.getAsText(locale).toLowerCase(locale), Boolean.TRUE);
                    map.put(property.getAsText(locale).toUpperCase(locale), Boolean.TRUE);
                    minimumValueOverall++;
                }
                if ("en".equals(locale.getLanguage()) && this.iFieldType == DateTimeFieldType.era()) {
                    map.put("BCE", Boolean.TRUE);
                    map.put("bce", Boolean.TRUE);
                    map.put("CE", Boolean.TRUE);
                    map.put("ce", Boolean.TRUE);
                    intValue = 3;
                }
                map2.put(this.iFieldType, new Object[]{map, Integer.valueOf(intValue)});
            } else {
                intValue = ((Integer) objArr[1]).intValue();
                map = (Map) objArr[0];
            }
            for (int min = Math.min(charSequence.length(), intValue + i); min > i; min--) {
                String charSequence2 = charSequence.subSequence(i, min).toString();
                if (map.containsKey(charSequence2)) {
                    dateTimeParserBucket.saveField(this.iFieldType, charSequence2, locale);
                    return min;
                }
            }
            return i ^ (-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class Fraction implements InternalPrinter, InternalParser {
        private final DateTimeFieldType iFieldType;
        protected int iMaxDigits;
        protected int iMinDigits;

        protected Fraction(DateTimeFieldType dateTimeFieldType, int i, int i2) {
            this.iFieldType = dateTimeFieldType;
            i2 = i2 > 18 ? 18 : i2;
            this.iMinDigits = i;
            this.iMaxDigits = i2;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return this.iMaxDigits;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            printTo(appendable, j, chronology);
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            printTo(appendable, readablePartial.getChronology().set(readablePartial, 0L), readablePartial.getChronology());
        }

        protected void printTo(Appendable appendable, long j, Chronology chronology) throws IOException {
            String l;
            DateTimeField field = this.iFieldType.getField(chronology);
            int i = this.iMinDigits;
            try {
                long remainder = field.remainder(j);
                if (remainder != 0) {
                    long[] fractionData = getFractionData(remainder, field);
                    long j2 = fractionData[0];
                    int i2 = (int) fractionData[1];
                    if ((2147483647L & j2) == j2) {
                        l = Integer.toString((int) j2);
                    } else {
                        l = Long.toString(j2);
                    }
                    int length = l.length();
                    while (length < i2) {
                        appendable.append('0');
                        i--;
                        i2--;
                    }
                    if (i < i2) {
                        while (i < i2 && length > 1 && l.charAt(length - 1) == '0') {
                            i2--;
                            length--;
                        }
                        if (length < l.length()) {
                            for (int i3 = 0; i3 < length; i3++) {
                                appendable.append(l.charAt(i3));
                            }
                            return;
                        }
                    }
                    appendable.append(l);
                    return;
                }
                while (true) {
                    i--;
                    if (i < 0) {
                        return;
                    }
                    appendable.append('0');
                }
            } catch (RuntimeException unused) {
                DateTimeFormatterBuilder.appendUnknownString(appendable, i);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x007a A[LOOP:0: B:3:0x000a->B:27:0x007a, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:28:0x006c A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private long[] getFractionData(long j, DateTimeField dateTimeField) {
            long j2;
            long unitMillis = dateTimeField.getDurationField().getUnitMillis();
            int i = this.iMaxDigits;
            while (true) {
                switch (i) {
                    case 1:
                        j2 = 10;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                            return new long[]{(j * j2) / unitMillis, i};
                        }
                        i--;
                    case 2:
                        j2 = 100;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 3:
                        j2 = 1000;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 4:
                        j2 = 10000;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 5:
                        j2 = 100000;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 6:
                        j2 = 1000000;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 7:
                        j2 = 10000000;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 8:
                        j2 = 100000000;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 9:
                        j2 = 1000000000;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 10:
                        j2 = RealConnection.IDLE_CONNECTION_HEALTHY_NS;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 11:
                        j2 = 100000000000L;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 12:
                        j2 = 1000000000000L;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 13:
                        j2 = 10000000000000L;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 14:
                        j2 = 100000000000000L;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 15:
                        j2 = 1000000000000000L;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 16:
                        j2 = 10000000000000000L;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 17:
                        j2 = 100000000000000000L;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    case 18:
                        j2 = 1000000000000000000L;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                    default:
                        j2 = 1;
                        if ((unitMillis * j2) / j2 == unitMillis) {
                        }
                        break;
                }
            }
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return this.iMaxDigits;
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            DateTimeField field = this.iFieldType.getField(dateTimeParserBucket.getChronology());
            int min = Math.min(this.iMaxDigits, charSequence.length() - i);
            long unitMillis = field.getDurationField().getUnitMillis() * 10;
            long j = 0;
            int i2 = 0;
            while (i2 < min) {
                char charAt = charSequence.charAt(i + i2);
                if (charAt < '0' || charAt > '9') {
                    break;
                }
                i2++;
                unitMillis /= 10;
                j += (charAt - '0') * unitMillis;
            }
            long j2 = j / 10;
            if (i2 != 0 && j2 <= 2147483647L) {
                dateTimeParserBucket.saveField(new PreciseDateTimeField(DateTimeFieldType.millisOfSecond(), MillisDurationField.INSTANCE, field.getDurationField()), (int) j2);
                return i + i2;
            }
            return i ^ (-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class TimeZoneOffset implements InternalPrinter, InternalParser {
        private final int iMaxFields;
        private final int iMinFields;
        private final boolean iShowSeparators;
        private final String iZeroOffsetParseText;
        private final String iZeroOffsetPrintText;

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
        }

        TimeZoneOffset(String str, String str2, boolean z, int i, int i2) {
            this.iZeroOffsetPrintText = str;
            this.iZeroOffsetParseText = str2;
            this.iShowSeparators = z;
            if (i <= 0 || i2 < i) {
                throw new IllegalArgumentException();
            }
            if (i > 4) {
                i = 4;
                i2 = 4;
            }
            this.iMinFields = i;
            this.iMaxFields = i2;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            int i = this.iMinFields;
            int i2 = (i + 1) << 1;
            if (this.iShowSeparators) {
                i2 += i - 1;
            }
            String str = this.iZeroOffsetPrintText;
            return (str == null || str.length() <= i2) ? i2 : this.iZeroOffsetPrintText.length();
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            String str;
            if (dateTimeZone == null) {
                return;
            }
            if (i == 0 && (str = this.iZeroOffsetPrintText) != null) {
                appendable.append(str);
                return;
            }
            if (i >= 0) {
                appendable.append('+');
            } else {
                appendable.append('-');
                i = -i;
            }
            int i2 = i / DateTimeConstants.MILLIS_PER_HOUR;
            FormatUtils.appendPaddedInteger(appendable, i2, 2);
            if (this.iMaxFields == 1) {
                return;
            }
            int i3 = i - (i2 * DateTimeConstants.MILLIS_PER_HOUR);
            if (i3 != 0 || this.iMinFields > 1) {
                int i4 = i3 / DateTimeConstants.MILLIS_PER_MINUTE;
                if (this.iShowSeparators) {
                    appendable.append(':');
                }
                FormatUtils.appendPaddedInteger(appendable, i4, 2);
                if (this.iMaxFields == 2) {
                    return;
                }
                int i5 = i3 - (i4 * DateTimeConstants.MILLIS_PER_MINUTE);
                if (i5 != 0 || this.iMinFields > 2) {
                    int i6 = i5 / 1000;
                    if (this.iShowSeparators) {
                        appendable.append(':');
                    }
                    FormatUtils.appendPaddedInteger(appendable, i6, 2);
                    if (this.iMaxFields == 3) {
                        return;
                    }
                    int i7 = i5 - (i6 * 1000);
                    if (i7 != 0 || this.iMinFields > 3) {
                        if (this.iShowSeparators) {
                            appendable.append('.');
                        }
                        FormatUtils.appendPaddedInteger(appendable, i7, 3);
                    }
                }
            }
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return estimatePrintedLength();
        }

        /* JADX WARN: Code restructure failed: missing block: B:42:0x0083, code lost:
            if (r6 <= '9') goto L36;
         */
        @Override // org.joda.time.format.InternalParser
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            boolean z;
            int parseTwoDigits;
            int parseTwoDigits2;
            int parseTwoDigits3;
            char charAt;
            int length = charSequence.length() - i;
            String str = this.iZeroOffsetParseText;
            boolean z2 = false;
            if (str != null) {
                if (str.length() == 0) {
                    if (length <= 0 || ((charAt = charSequence.charAt(i)) != '-' && charAt != '+')) {
                        dateTimeParserBucket.setOffset((Integer) 0);
                        return i;
                    }
                } else if (DateTimeFormatterBuilder.csStartsWithIgnoreCase(charSequence, i, this.iZeroOffsetParseText)) {
                    dateTimeParserBucket.setOffset((Integer) 0);
                    return i + this.iZeroOffsetParseText.length();
                }
            }
            if (length <= 1) {
                return i ^ (-1);
            }
            char charAt2 = charSequence.charAt(i);
            if (charAt2 == '-') {
                z = true;
            } else if (charAt2 != '+') {
                return i ^ (-1);
            } else {
                z = false;
            }
            int i2 = length - 1;
            int i3 = i + 1;
            if (digitCount(charSequence, i3, 2) >= 2 && (parseTwoDigits = FormatUtils.parseTwoDigits(charSequence, i3)) <= 23) {
                int i4 = parseTwoDigits * DateTimeConstants.MILLIS_PER_HOUR;
                int i5 = i2 - 2;
                int i6 = i3 + 2;
                if (i5 > 0) {
                    char charAt3 = charSequence.charAt(i6);
                    if (charAt3 == ':') {
                        i5--;
                        i6++;
                        z2 = true;
                    } else if (charAt3 >= '0') {
                    }
                    int digitCount = digitCount(charSequence, i6, 2);
                    if (digitCount != 0 || z2) {
                        if (digitCount >= 2 && (parseTwoDigits2 = FormatUtils.parseTwoDigits(charSequence, i6)) <= 59) {
                            i4 += parseTwoDigits2 * DateTimeConstants.MILLIS_PER_MINUTE;
                            int i7 = i5 - 2;
                            i6 += 2;
                            if (i7 > 0) {
                                if (z2) {
                                    if (charSequence.charAt(i6) == ':') {
                                        i7--;
                                        i6++;
                                    }
                                }
                                int digitCount2 = digitCount(charSequence, i6, 2);
                                if (digitCount2 != 0 || z2) {
                                    if (digitCount2 >= 2 && (parseTwoDigits3 = FormatUtils.parseTwoDigits(charSequence, i6)) <= 59) {
                                        i4 += parseTwoDigits3 * 1000;
                                        i6 += 2;
                                        if (i7 - 2 > 0) {
                                            if (z2) {
                                                if (charSequence.charAt(i6) == '.' || charSequence.charAt(i6) == ',') {
                                                    i6++;
                                                }
                                            }
                                            int digitCount3 = digitCount(charSequence, i6, 3);
                                            if (digitCount3 != 0 || z2) {
                                                if (digitCount3 < 1) {
                                                    return i6 ^ (-1);
                                                }
                                                int i8 = i6 + 1;
                                                i4 += (charSequence.charAt(i6) - '0') * 100;
                                                if (digitCount3 > 1) {
                                                    i6 = i8 + 1;
                                                    i4 += (charSequence.charAt(i8) - '0') * 10;
                                                    if (digitCount3 > 2) {
                                                        i4 += charSequence.charAt(i6) - '0';
                                                        i6++;
                                                    }
                                                } else {
                                                    i6 = i8;
                                                }
                                            }
                                        }
                                    }
                                    return i6 ^ (-1);
                                }
                            }
                        }
                        return i6 ^ (-1);
                    }
                }
                if (z) {
                    i4 = -i4;
                }
                dateTimeParserBucket.setOffset(Integer.valueOf(i4));
                return i6;
            }
            return i3 ^ (-1);
        }

        private int digitCount(CharSequence charSequence, int i, int i2) {
            int i3 = 0;
            for (int min = Math.min(charSequence.length() - i, i2); min > 0; min--) {
                char charAt = charSequence.charAt(i + i3);
                if (charAt < '0' || charAt > '9') {
                    break;
                }
                i3++;
            }
            return i3;
        }
    }

    /* loaded from: classes2.dex */
    static class TimeZoneName implements InternalPrinter, InternalParser {
        static final int LONG_NAME = 0;
        static final int SHORT_NAME = 1;
        private final Map<String, DateTimeZone> iParseLookup;
        private final int iType;

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
        }

        TimeZoneName(int i, Map<String, DateTimeZone> map) {
            this.iType = i;
            this.iParseLookup = map;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return this.iType == 1 ? 4 : 20;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            appendable.append(print(j - i, dateTimeZone, locale));
        }

        private String print(long j, DateTimeZone dateTimeZone, Locale locale) {
            if (dateTimeZone == null) {
                return "";
            }
            int i = this.iType;
            if (i != 0) {
                return i != 1 ? "" : dateTimeZone.getShortName(j, locale);
            }
            return dateTimeZone.getName(j, locale);
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return this.iType == 1 ? 4 : 20;
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            Map<String, DateTimeZone> map = this.iParseLookup;
            if (map == null) {
                map = DateTimeUtils.getDefaultTimeZoneNames();
            }
            String str = null;
            for (String str2 : map.keySet()) {
                if (DateTimeFormatterBuilder.csStartsWith(charSequence, i, str2) && (str == null || str2.length() > str.length())) {
                    str = str2;
                }
            }
            if (str != null) {
                dateTimeParserBucket.setZone(map.get(str));
                return i + str.length();
            }
            return i ^ (-1);
        }
    }

    /* loaded from: classes2.dex */
    enum TimeZoneId implements InternalPrinter, InternalParser {
        INSTANCE;
        
        private static final List<String> ALL_IDS;
        private static final List<String> BASE_GROUPED_IDS = new ArrayList();
        private static final Map<String, List<String>> GROUPED_IDS;
        static final int MAX_LENGTH;
        static final int MAX_PREFIX_LENGTH;

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
        }

        static {
            ArrayList<String> arrayList = new ArrayList(DateTimeZone.getAvailableIDs());
            ALL_IDS = arrayList;
            Collections.sort(arrayList);
            GROUPED_IDS = new HashMap();
            int i = 0;
            int i2 = 0;
            for (String str : arrayList) {
                int indexOf = str.indexOf(47);
                if (indexOf >= 0) {
                    indexOf = indexOf < str.length() ? indexOf + 1 : indexOf;
                    i2 = Math.max(i2, indexOf);
                    String substring = str.substring(0, indexOf + 1);
                    String substring2 = str.substring(indexOf);
                    Map<String, List<String>> map = GROUPED_IDS;
                    if (!map.containsKey(substring)) {
                        map.put(substring, new ArrayList());
                    }
                    map.get(substring).add(substring2);
                } else {
                    BASE_GROUPED_IDS.add(str);
                }
                i = Math.max(i, str.length());
            }
            MAX_LENGTH = i;
            MAX_PREFIX_LENGTH = i2;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return MAX_LENGTH;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            appendable.append(dateTimeZone != null ? dateTimeZone.getID() : "");
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return MAX_LENGTH;
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            String str;
            int i2;
            int i3;
            List<String> list = BASE_GROUPED_IDS;
            int length = charSequence.length();
            int min = Math.min(length, MAX_PREFIX_LENGTH + i);
            int i4 = i;
            while (true) {
                if (i4 >= min) {
                    str = "";
                    i2 = i;
                    break;
                } else if (charSequence.charAt(i4) == '/') {
                    str = charSequence.subSequence(i, i4 + 1).toString();
                    i2 = str.length() + i;
                    list = GROUPED_IDS.get(i4 < length ? str + charSequence.charAt(i3) : str);
                    if (list == null) {
                        return i ^ (-1);
                    }
                } else {
                    i4++;
                }
            }
            String str2 = null;
            for (int i5 = 0; i5 < list.size(); i5++) {
                String str3 = list.get(i5);
                if (DateTimeFormatterBuilder.csStartsWith(charSequence, i2, str3) && (str2 == null || str3.length() > str2.length())) {
                    str2 = str3;
                }
            }
            if (str2 != null) {
                dateTimeParserBucket.setZone(DateTimeZone.forID(str + str2));
                return i2 + str2.length();
            }
            return i ^ (-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class Composite implements InternalPrinter, InternalParser {
        private final int iParsedLengthEstimate;
        private final InternalParser[] iParsers;
        private final int iPrintedLengthEstimate;
        private final InternalPrinter[] iPrinters;

        Composite(List<Object> list) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            decompose(list, arrayList, arrayList2);
            if (arrayList.contains(null) || arrayList.isEmpty()) {
                this.iPrinters = null;
                this.iPrintedLengthEstimate = 0;
            } else {
                int size = arrayList.size();
                this.iPrinters = new InternalPrinter[size];
                int i = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    InternalPrinter internalPrinter = (InternalPrinter) arrayList.get(i2);
                    i += internalPrinter.estimatePrintedLength();
                    this.iPrinters[i2] = internalPrinter;
                }
                this.iPrintedLengthEstimate = i;
            }
            if (arrayList2.contains(null) || arrayList2.isEmpty()) {
                this.iParsers = null;
                this.iParsedLengthEstimate = 0;
                return;
            }
            int size2 = arrayList2.size();
            this.iParsers = new InternalParser[size2];
            int i3 = 0;
            for (int i4 = 0; i4 < size2; i4++) {
                InternalParser internalParser = (InternalParser) arrayList2.get(i4);
                i3 += internalParser.estimateParsedLength();
                this.iParsers[i4] = internalParser;
            }
            this.iParsedLengthEstimate = i3;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return this.iPrintedLengthEstimate;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            InternalPrinter[] internalPrinterArr = this.iPrinters;
            if (internalPrinterArr == null) {
                throw new UnsupportedOperationException();
            }
            Locale locale2 = locale == null ? Locale.getDefault() : locale;
            for (InternalPrinter internalPrinter : internalPrinterArr) {
                internalPrinter.printTo(appendable, j, chronology, i, dateTimeZone, locale2);
            }
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            InternalPrinter[] internalPrinterArr = this.iPrinters;
            if (internalPrinterArr == null) {
                throw new UnsupportedOperationException();
            }
            if (locale == null) {
                locale = Locale.getDefault();
            }
            for (InternalPrinter internalPrinter : internalPrinterArr) {
                internalPrinter.printTo(appendable, readablePartial, locale);
            }
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return this.iParsedLengthEstimate;
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            InternalParser[] internalParserArr = this.iParsers;
            if (internalParserArr == null) {
                throw new UnsupportedOperationException();
            }
            int length = internalParserArr.length;
            for (int i2 = 0; i2 < length && i >= 0; i2++) {
                i = internalParserArr[i2].parseInto(dateTimeParserBucket, charSequence, i);
            }
            return i;
        }

        boolean isPrinter() {
            return this.iPrinters != null;
        }

        boolean isParser() {
            return this.iParsers != null;
        }

        private void decompose(List<Object> list, List<Object> list2, List<Object> list3) {
            int size = list.size();
            for (int i = 0; i < size; i += 2) {
                Object obj = list.get(i);
                if (obj instanceof Composite) {
                    addArrayToList(list2, ((Composite) obj).iPrinters);
                } else {
                    list2.add(obj);
                }
                Object obj2 = list.get(i + 1);
                if (obj2 instanceof Composite) {
                    addArrayToList(list3, ((Composite) obj2).iParsers);
                } else {
                    list3.add(obj2);
                }
            }
        }

        private void addArrayToList(List<Object> list, Object[] objArr) {
            if (objArr != null) {
                for (Object obj : objArr) {
                    list.add(obj);
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    static class MatchingParser implements InternalParser {
        private final int iParsedLengthEstimate;
        private final InternalParser[] iParsers;

        MatchingParser(InternalParser[] internalParserArr) {
            int estimateParsedLength;
            this.iParsers = internalParserArr;
            int length = internalParserArr.length;
            int i = 0;
            while (true) {
                length--;
                if (length >= 0) {
                    InternalParser internalParser = internalParserArr[length];
                    if (internalParser != null && (estimateParsedLength = internalParser.estimateParsedLength()) > i) {
                        i = estimateParsedLength;
                    }
                } else {
                    this.iParsedLengthEstimate = i;
                    return;
                }
            }
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return this.iParsedLengthEstimate;
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            int i2;
            int i3;
            InternalParser[] internalParserArr = this.iParsers;
            int length = internalParserArr.length;
            Object saveState = dateTimeParserBucket.saveState();
            boolean z = false;
            Object obj = null;
            int i4 = i;
            int i5 = i4;
            int i6 = 0;
            while (true) {
                if (i6 >= length) {
                    break;
                }
                InternalParser internalParser = internalParserArr[i6];
                if (internalParser != null) {
                    int parseInto = internalParser.parseInto(dateTimeParserBucket, charSequence, i);
                    if (parseInto >= i) {
                        if (parseInto <= i4) {
                            continue;
                        } else if (parseInto >= charSequence.length() || (i3 = i6 + 1) >= length || internalParserArr[i3] == null) {
                            break;
                        } else {
                            obj = dateTimeParserBucket.saveState();
                            i4 = parseInto;
                        }
                    } else if (parseInto < 0 && (i2 = parseInto ^ (-1)) > i5) {
                        i5 = i2;
                    }
                    dateTimeParserBucket.restoreState(saveState);
                    i6++;
                } else if (i4 <= i) {
                    return i;
                } else {
                    z = true;
                }
            }
            if (i4 > i || (i4 == i && z)) {
                if (obj != null) {
                    dateTimeParserBucket.restoreState(obj);
                }
                return i4;
            }
            return i5 ^ (-1);
        }
    }

    static boolean csStartsWith(CharSequence charSequence, int i, String str) {
        int length = str.length();
        if (charSequence.length() - i < length) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (charSequence.charAt(i + i2) != str.charAt(i2)) {
                return false;
            }
        }
        return true;
    }

    static boolean csStartsWithIgnoreCase(CharSequence charSequence, int i, String str) {
        char upperCase;
        char upperCase2;
        int length = str.length();
        if (charSequence.length() - i < length) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = charSequence.charAt(i + i2);
            char charAt2 = str.charAt(i2);
            if (charAt != charAt2 && (upperCase = Character.toUpperCase(charAt)) != (upperCase2 = Character.toUpperCase(charAt2)) && Character.toLowerCase(upperCase) != Character.toLowerCase(upperCase2)) {
                return false;
            }
        }
        return true;
    }
}
