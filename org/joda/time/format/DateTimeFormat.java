package org.joda.time.format;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReferenceArray;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;
/* loaded from: classes2.dex */
public class DateTimeFormat {
    static final int DATE = 0;
    static final int DATETIME = 2;
    static final int FULL = 0;
    static final int LONG = 1;
    static final int MEDIUM = 2;
    static final int NONE = 4;
    private static final int PATTERN_CACHE_SIZE = 500;
    static final int SHORT = 3;
    static final int TIME = 1;
    private static final ConcurrentHashMap<String, DateTimeFormatter> cPatternCache = new ConcurrentHashMap<>();
    private static final AtomicReferenceArray<DateTimeFormatter> cStyleCache = new AtomicReferenceArray<>(25);

    public static DateTimeFormatter forPattern(String str) {
        return createFormatterForPattern(str);
    }

    public static DateTimeFormatter forStyle(String str) {
        return createFormatterForStyle(str);
    }

    public static String patternForStyle(String str, Locale locale) {
        DateTimeFormatter createFormatterForStyle = createFormatterForStyle(str);
        if (locale == null) {
            locale = Locale.getDefault();
        }
        return ((StyleFormatter) createFormatterForStyle.getPrinter0()).getPattern(locale);
    }

    public static DateTimeFormatter shortDate() {
        return createFormatterForStyleIndex(3, 4);
    }

    public static DateTimeFormatter shortTime() {
        return createFormatterForStyleIndex(4, 3);
    }

    public static DateTimeFormatter shortDateTime() {
        return createFormatterForStyleIndex(3, 3);
    }

    public static DateTimeFormatter mediumDate() {
        return createFormatterForStyleIndex(2, 4);
    }

    public static DateTimeFormatter mediumTime() {
        return createFormatterForStyleIndex(4, 2);
    }

    public static DateTimeFormatter mediumDateTime() {
        return createFormatterForStyleIndex(2, 2);
    }

    public static DateTimeFormatter longDate() {
        return createFormatterForStyleIndex(1, 4);
    }

    public static DateTimeFormatter longTime() {
        return createFormatterForStyleIndex(4, 1);
    }

    public static DateTimeFormatter longDateTime() {
        return createFormatterForStyleIndex(1, 1);
    }

    public static DateTimeFormatter fullDate() {
        return createFormatterForStyleIndex(0, 4);
    }

    public static DateTimeFormatter fullTime() {
        return createFormatterForStyleIndex(4, 0);
    }

    public static DateTimeFormatter fullDateTime() {
        return createFormatterForStyleIndex(0, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void appendPatternTo(DateTimeFormatterBuilder dateTimeFormatterBuilder, String str) {
        parsePatternTo(dateTimeFormatterBuilder, str);
    }

    protected DateTimeFormat() {
    }

    private static void parsePatternTo(DateTimeFormatterBuilder dateTimeFormatterBuilder, String str) {
        boolean z;
        int length = str.length();
        int[] iArr = new int[1];
        int i = 0;
        while (i < length) {
            iArr[0] = i;
            String parseToken = parseToken(str, iArr);
            int i2 = iArr[0];
            int length2 = parseToken.length();
            if (length2 == 0) {
                return;
            }
            char charAt = parseToken.charAt(0);
            if (charAt == '\'') {
                String substring = parseToken.substring(1);
                if (substring.length() == 1) {
                    dateTimeFormatterBuilder.appendLiteral(substring.charAt(0));
                } else {
                    dateTimeFormatterBuilder.appendLiteral(new String(substring));
                }
            } else if (charAt == 'K') {
                dateTimeFormatterBuilder.appendHourOfHalfday(length2);
            } else if (charAt != 'M') {
                if (charAt == 'S') {
                    dateTimeFormatterBuilder.appendFractionOfSecond(length2, length2);
                } else if (charAt == 'a') {
                    dateTimeFormatterBuilder.appendHalfdayOfDayText();
                } else if (charAt == 'h') {
                    dateTimeFormatterBuilder.appendClockhourOfHalfday(length2);
                } else if (charAt == 'k') {
                    dateTimeFormatterBuilder.appendClockhourOfDay(length2);
                } else if (charAt == 'm') {
                    dateTimeFormatterBuilder.appendMinuteOfHour(length2);
                } else if (charAt == 's') {
                    dateTimeFormatterBuilder.appendSecondOfMinute(length2);
                } else if (charAt == 'G') {
                    dateTimeFormatterBuilder.appendEraText();
                } else if (charAt != 'H') {
                    if (charAt != 'Y') {
                        if (charAt != 'Z') {
                            if (charAt == 'd') {
                                dateTimeFormatterBuilder.appendDayOfMonth(length2);
                            } else if (charAt != 'e') {
                                switch (charAt) {
                                    case 'C':
                                        dateTimeFormatterBuilder.appendCenturyOfEra(length2, length2);
                                        break;
                                    case 'D':
                                        dateTimeFormatterBuilder.appendDayOfYear(length2);
                                        break;
                                    case 'E':
                                        if (length2 >= 4) {
                                            dateTimeFormatterBuilder.appendDayOfWeekText();
                                            break;
                                        } else {
                                            dateTimeFormatterBuilder.appendDayOfWeekShortText();
                                            break;
                                        }
                                    default:
                                        switch (charAt) {
                                            case 'w':
                                                dateTimeFormatterBuilder.appendWeekOfWeekyear(length2);
                                                break;
                                            case 'x':
                                            case 'y':
                                                break;
                                            case 'z':
                                                if (length2 >= 4) {
                                                    dateTimeFormatterBuilder.appendTimeZoneName();
                                                    break;
                                                } else {
                                                    dateTimeFormatterBuilder.appendTimeZoneShortName(null);
                                                    break;
                                                }
                                            default:
                                                throw new IllegalArgumentException("Illegal pattern component: " + parseToken);
                                        }
                                }
                            } else {
                                dateTimeFormatterBuilder.appendDayOfWeek(length2);
                            }
                        } else if (length2 == 1) {
                            dateTimeFormatterBuilder.appendTimeZoneOffset(null, "Z", false, 2, 2);
                        } else if (length2 == 2) {
                            dateTimeFormatterBuilder.appendTimeZoneOffset(null, "Z", true, 2, 2);
                        } else {
                            dateTimeFormatterBuilder.appendTimeZoneId();
                        }
                    }
                    if (length2 == 2) {
                        if (i2 + 1 < length) {
                            iArr[0] = iArr[0] + 1;
                            z = !isNumericToken(parseToken(str, iArr));
                            iArr[0] = iArr[0] - 1;
                        } else {
                            z = true;
                        }
                        if (charAt == 'x') {
                            dateTimeFormatterBuilder.appendTwoDigitWeekyear(new DateTime().getWeekyear() - 30, z);
                        } else {
                            dateTimeFormatterBuilder.appendTwoDigitYear(new DateTime().getYear() - 30, z);
                        }
                    } else {
                        if (i2 + 1 < length) {
                            iArr[0] = iArr[0] + 1;
                            r3 = isNumericToken(parseToken(str, iArr)) ? length2 : 9;
                            iArr[0] = iArr[0] - 1;
                        }
                        if (charAt == 'Y') {
                            dateTimeFormatterBuilder.appendYearOfEra(length2, r3);
                        } else if (charAt == 'x') {
                            dateTimeFormatterBuilder.appendWeekyear(length2, r3);
                        } else if (charAt == 'y') {
                            dateTimeFormatterBuilder.appendYear(length2, r3);
                        }
                    }
                } else {
                    dateTimeFormatterBuilder.appendHourOfDay(length2);
                }
            } else if (length2 < 3) {
                dateTimeFormatterBuilder.appendMonthOfYear(length2);
            } else if (length2 >= 4) {
                dateTimeFormatterBuilder.appendMonthOfYearText();
            } else {
                dateTimeFormatterBuilder.appendMonthOfYearShortText();
            }
            i = i2 + 1;
        }
    }

    private static String parseToken(String str, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        int i = iArr[0];
        int length = str.length();
        char charAt = str.charAt(i);
        if ((charAt >= 'A' && charAt <= 'Z') || (charAt >= 'a' && charAt <= 'z')) {
            sb.append(charAt);
            while (true) {
                int i2 = i + 1;
                if (i2 >= length || str.charAt(i2) != charAt) {
                    break;
                }
                sb.append(charAt);
                i = i2;
            }
        } else {
            sb.append('\'');
            boolean z = false;
            while (i < length) {
                char charAt2 = str.charAt(i);
                if (charAt2 != '\'') {
                    if (!z && ((charAt2 >= 'A' && charAt2 <= 'Z') || (charAt2 >= 'a' && charAt2 <= 'z'))) {
                        i--;
                        break;
                    }
                    sb.append(charAt2);
                } else {
                    int i3 = i + 1;
                    if (i3 >= length || str.charAt(i3) != '\'') {
                        z = !z;
                    } else {
                        sb.append(charAt2);
                        i = i3;
                    }
                }
                i++;
            }
        }
        iArr[0] = i;
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0013 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean isNumericToken(String str) {
        int length = str.length();
        if (length > 0) {
            switch (str.charAt(0)) {
                case 'C':
                case 'D':
                case 'F':
                case 'H':
                case 'K':
                case 'S':
                case 'W':
                case 'Y':
                case 'c':
                case 'd':
                case 'e':
                case 'h':
                case 'k':
                case 'm':
                case 's':
                case 'w':
                case 'x':
                case 'y':
                    return true;
                case 'M':
                    if (length <= 2) {
                    }
                    break;
            }
        }
        return false;
    }

    private static DateTimeFormatter createFormatterForPattern(String str) {
        DateTimeFormatter putIfAbsent;
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Invalid pattern specification");
        }
        ConcurrentHashMap<String, DateTimeFormatter> concurrentHashMap = cPatternCache;
        DateTimeFormatter dateTimeFormatter = concurrentHashMap.get(str);
        if (dateTimeFormatter == null) {
            DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();
            parsePatternTo(dateTimeFormatterBuilder, str);
            DateTimeFormatter formatter = dateTimeFormatterBuilder.toFormatter();
            return (concurrentHashMap.size() >= PATTERN_CACHE_SIZE || (putIfAbsent = concurrentHashMap.putIfAbsent(str, formatter)) == null) ? formatter : putIfAbsent;
        }
        return dateTimeFormatter;
    }

    private static DateTimeFormatter createFormatterForStyle(String str) {
        if (str == null || str.length() != 2) {
            throw new IllegalArgumentException("Invalid style specification: " + str);
        }
        int selectStyle = selectStyle(str.charAt(0));
        int selectStyle2 = selectStyle(str.charAt(1));
        if (selectStyle == 4 && selectStyle2 == 4) {
            throw new IllegalArgumentException("Style '--' is invalid");
        }
        return createFormatterForStyleIndex(selectStyle, selectStyle2);
    }

    private static DateTimeFormatter createFormatterForStyleIndex(int i, int i2) {
        int i3 = (i << 2) + i + i2;
        AtomicReferenceArray<DateTimeFormatter> atomicReferenceArray = cStyleCache;
        if (i3 >= atomicReferenceArray.length()) {
            return createDateTimeFormatter(i, i2);
        }
        DateTimeFormatter dateTimeFormatter = atomicReferenceArray.get(i3);
        if (dateTimeFormatter == null) {
            DateTimeFormatter createDateTimeFormatter = createDateTimeFormatter(i, i2);
            return !atomicReferenceArray.compareAndSet(i3, null, createDateTimeFormatter) ? atomicReferenceArray.get(i3) : createDateTimeFormatter;
        }
        return dateTimeFormatter;
    }

    private static DateTimeFormatter createDateTimeFormatter(int i, int i2) {
        StyleFormatter styleFormatter = new StyleFormatter(i, i2, i == 4 ? 1 : i2 == 4 ? 0 : 2);
        return new DateTimeFormatter(styleFormatter, styleFormatter);
    }

    private static int selectStyle(char c) {
        if (c != '-') {
            if (c != 'F') {
                if (c != 'S') {
                    if (c != 'L') {
                        if (c == 'M') {
                            return 2;
                        }
                        throw new IllegalArgumentException("Invalid style character: " + c);
                    }
                    return 1;
                }
                return 3;
            }
            return 0;
        }
        return 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class StyleFormatter implements InternalPrinter, InternalParser {
        private static final ConcurrentHashMap<StyleFormatterCacheKey, DateTimeFormatter> cCache = new ConcurrentHashMap<>();
        private final int iDateStyle;
        private final int iTimeStyle;
        private final int iType;

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return 40;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return 40;
        }

        StyleFormatter(int i, int i2, int i3) {
            this.iDateStyle = i;
            this.iTimeStyle = i2;
            this.iType = i3;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            getFormatter(locale).getPrinter0().printTo(appendable, j, chronology, i, dateTimeZone, locale);
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            getFormatter(locale).getPrinter0().printTo(appendable, readablePartial, locale);
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            return getFormatter(dateTimeParserBucket.getLocale()).getParser0().parseInto(dateTimeParserBucket, charSequence, i);
        }

        private DateTimeFormatter getFormatter(Locale locale) {
            if (locale == null) {
                locale = Locale.getDefault();
            }
            StyleFormatterCacheKey styleFormatterCacheKey = new StyleFormatterCacheKey(this.iType, this.iDateStyle, this.iTimeStyle, locale);
            ConcurrentHashMap<StyleFormatterCacheKey, DateTimeFormatter> concurrentHashMap = cCache;
            DateTimeFormatter dateTimeFormatter = concurrentHashMap.get(styleFormatterCacheKey);
            if (dateTimeFormatter == null) {
                DateTimeFormatter forPattern = DateTimeFormat.forPattern(getPattern(locale));
                DateTimeFormatter putIfAbsent = concurrentHashMap.putIfAbsent(styleFormatterCacheKey, forPattern);
                return putIfAbsent != null ? putIfAbsent : forPattern;
            }
            return dateTimeFormatter;
        }

        String getPattern(Locale locale) {
            DateFormat dateInstance;
            int i = this.iType;
            if (i == 0) {
                dateInstance = DateFormat.getDateInstance(this.iDateStyle, locale);
            } else if (i == 1) {
                dateInstance = DateFormat.getTimeInstance(this.iTimeStyle, locale);
            } else {
                dateInstance = i != 2 ? null : DateFormat.getDateTimeInstance(this.iDateStyle, this.iTimeStyle, locale);
            }
            if (!(dateInstance instanceof SimpleDateFormat)) {
                throw new IllegalArgumentException("No datetime pattern for locale: " + locale);
            }
            return ((SimpleDateFormat) dateInstance).toPattern();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class StyleFormatterCacheKey {
        private final int combinedTypeAndStyle;
        private final Locale locale;

        public StyleFormatterCacheKey(int i, int i2, int i3, Locale locale) {
            this.locale = locale;
            this.combinedTypeAndStyle = i + (i2 << 4) + (i3 << 8);
        }

        public int hashCode() {
            int i = (this.combinedTypeAndStyle + 31) * 31;
            Locale locale = this.locale;
            return i + (locale == null ? 0 : locale.hashCode());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && (obj instanceof StyleFormatterCacheKey)) {
                StyleFormatterCacheKey styleFormatterCacheKey = (StyleFormatterCacheKey) obj;
                if (this.combinedTypeAndStyle != styleFormatterCacheKey.combinedTypeAndStyle) {
                    return false;
                }
                Locale locale = this.locale;
                if (locale == null) {
                    if (styleFormatterCacheKey.locale != null) {
                        return false;
                    }
                } else if (!locale.equals(styleFormatterCacheKey.locale)) {
                    return false;
                }
                return true;
            }
            return false;
        }
    }
}
