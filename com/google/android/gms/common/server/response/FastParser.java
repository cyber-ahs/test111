package com.google.android.gms.common.server.response;

import android.util.Log;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import kotlin.text.Typography;
import okio.internal.BufferKt;
/* loaded from: classes.dex */
public class FastParser<T extends FastJsonResponse> {
    private static final char[] zaqf = {'u', 'l', 'l'};
    private static final char[] zaqg = {'r', 'u', 'e'};
    private static final char[] zaqh = {'r', 'u', 'e', Typography.quote};
    private static final char[] zaqi = {'a', 'l', 's', 'e'};
    private static final char[] zaqj = {'a', 'l', 's', 'e', Typography.quote};
    private static final char[] zaqk = {'\n'};
    private static final zaa<Integer> zaqm = new com.google.android.gms.common.server.response.zaa();
    private static final zaa<Long> zaqn = new zab();
    private static final zaa<Float> zaqo = new zac();
    private static final zaa<Double> zaqp = new zad();
    private static final zaa<Boolean> zaqq = new zae();
    private static final zaa<String> zaqr = new zaf();
    private static final zaa<BigInteger> zaqs = new zag();
    private static final zaa<BigDecimal> zaqt = new zah();
    private final char[] zaqa = new char[1];
    private final char[] zaqb = new char[32];
    private final char[] zaqc = new char[1024];
    private final StringBuilder zaqd = new StringBuilder(32);
    private final StringBuilder zaqe = new StringBuilder(1024);
    private final Stack<Integer> zaql = new Stack<>();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface zaa<O> {
        O zah(FastParser fastParser, BufferedReader bufferedReader) throws ParseException, IOException;
    }

    /* loaded from: classes.dex */
    public static class ParseException extends Exception {
        public ParseException(String str) {
            super(str);
        }

        public ParseException(String str, Throwable th) {
            super(str, th);
        }

        public ParseException(Throwable th) {
            super(th);
        }
    }

    public void parse(InputStream inputStream, T t) throws ParseException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1024);
        try {
            try {
                this.zaql.push(0);
                char zaj = zaj(bufferedReader);
                if (zaj == 0) {
                    throw new ParseException("No data to parse");
                }
                if (zaj == '[') {
                    this.zaql.push(5);
                    Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = t.getFieldMappings();
                    if (fieldMappings.size() != 1) {
                        throw new ParseException("Object array response class must have a single Field");
                    }
                    FastJsonResponse.Field<?, ?> value = fieldMappings.entrySet().iterator().next().getValue();
                    t.addConcreteTypeArrayInternal(value, value.zapu, zaa(bufferedReader, value));
                } else if (zaj == '{') {
                    this.zaql.push(1);
                    zaa(bufferedReader, t);
                } else {
                    StringBuilder sb = new StringBuilder(19);
                    sb.append("Unexpected token: ");
                    sb.append(zaj);
                    throw new ParseException(sb.toString());
                }
                zak(0);
                try {
                    bufferedReader.close();
                } catch (IOException unused) {
                    Log.w("FastParser", "Failed to close reader while parsing.");
                }
            } catch (IOException e) {
                throw new ParseException(e);
            }
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (IOException unused2) {
                Log.w("FastParser", "Failed to close reader while parsing.");
            }
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:138:0x029b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:140:0x027d A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean zaa(BufferedReader bufferedReader, FastJsonResponse fastJsonResponse) throws ParseException, IOException {
        int i;
        HashMap hashMap;
        char zaj;
        Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = fastJsonResponse.getFieldMappings();
        String zaa2 = zaa(bufferedReader);
        if (zaa2 == null) {
            zak(1);
            return false;
        }
        while (zaa2 != null) {
            FastJsonResponse.Field<?, ?> field = fieldMappings.get(zaa2);
            if (field == null) {
                zaa2 = zab(bufferedReader);
            } else {
                this.zaql.push(4);
                switch (field.zapq) {
                    case 0:
                        if (field.zapr) {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, (ArrayList<Integer>) zaa(bufferedReader, zaqm));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zad(bufferedReader));
                        }
                        i = 4;
                        zak(i);
                        zak(2);
                        zaj = zaj(bufferedReader);
                        if (zaj != ',') {
                            zaa2 = zaa(bufferedReader);
                            break;
                        } else if (zaj != '}') {
                            StringBuilder sb = new StringBuilder(55);
                            sb.append("Expected end of object or field separator, but found: ");
                            sb.append(zaj);
                            throw new ParseException(sb.toString());
                        } else {
                            zaa2 = null;
                            break;
                        }
                    case 1:
                        if (field.zapr) {
                            fastJsonResponse.zab((FastJsonResponse.Field) field, (ArrayList<BigInteger>) zaa(bufferedReader, zaqs));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zaf(bufferedReader));
                        }
                        i = 4;
                        zak(i);
                        zak(2);
                        zaj = zaj(bufferedReader);
                        if (zaj != ',') {
                        }
                        break;
                    case 2:
                        if (field.zapr) {
                            fastJsonResponse.zac(field, zaa(bufferedReader, zaqn));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zae(bufferedReader));
                        }
                        i = 4;
                        zak(i);
                        zak(2);
                        zaj = zaj(bufferedReader);
                        if (zaj != ',') {
                        }
                        break;
                    case 3:
                        if (field.zapr) {
                            fastJsonResponse.zad(field, zaa(bufferedReader, zaqo));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zag(bufferedReader));
                        }
                        i = 4;
                        zak(i);
                        zak(2);
                        zaj = zaj(bufferedReader);
                        if (zaj != ',') {
                        }
                        break;
                    case 4:
                        if (field.zapr) {
                            fastJsonResponse.zae(field, zaa(bufferedReader, zaqp));
                        } else {
                            fastJsonResponse.zaa(field, zah(bufferedReader));
                        }
                        i = 4;
                        zak(i);
                        zak(2);
                        zaj = zaj(bufferedReader);
                        if (zaj != ',') {
                        }
                        break;
                    case 5:
                        if (field.zapr) {
                            fastJsonResponse.zaf(field, zaa(bufferedReader, zaqt));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zai(bufferedReader));
                        }
                        i = 4;
                        zak(i);
                        zak(2);
                        zaj = zaj(bufferedReader);
                        if (zaj != ',') {
                        }
                        break;
                    case 6:
                        if (field.zapr) {
                            fastJsonResponse.zag(field, zaa(bufferedReader, zaqq));
                            i = 4;
                            zak(i);
                            zak(2);
                            zaj = zaj(bufferedReader);
                            if (zaj != ',') {
                            }
                        } else {
                            fastJsonResponse.zaa(field, zaa(bufferedReader, false));
                            i = 4;
                            zak(i);
                            zak(2);
                            zaj = zaj(bufferedReader);
                            if (zaj != ',') {
                            }
                        }
                        break;
                    case 7:
                        if (field.zapr) {
                            fastJsonResponse.zah(field, zaa(bufferedReader, zaqr));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zac(bufferedReader));
                        }
                        i = 4;
                        zak(i);
                        zak(2);
                        zaj = zaj(bufferedReader);
                        if (zaj != ',') {
                        }
                        break;
                    case 8:
                        fastJsonResponse.zaa((FastJsonResponse.Field) field, Base64Utils.decode(zaa(bufferedReader, this.zaqc, this.zaqe, zaqk)));
                        i = 4;
                        zak(i);
                        zak(2);
                        zaj = zaj(bufferedReader);
                        if (zaj != ',') {
                        }
                        break;
                    case 9:
                        fastJsonResponse.zaa((FastJsonResponse.Field) field, Base64Utils.decodeUrlSafe(zaa(bufferedReader, this.zaqc, this.zaqe, zaqk)));
                        i = 4;
                        zak(i);
                        zak(2);
                        zaj = zaj(bufferedReader);
                        if (zaj != ',') {
                        }
                        break;
                    case 10:
                        char zaj2 = zaj(bufferedReader);
                        if (zaj2 == 'n') {
                            zab(bufferedReader, zaqf);
                            hashMap = null;
                        } else if (zaj2 != '{') {
                            throw new ParseException("Expected start of a map object");
                        } else {
                            this.zaql.push(1);
                            hashMap = new HashMap();
                            while (true) {
                                char zaj3 = zaj(bufferedReader);
                                if (zaj3 == 0) {
                                    throw new ParseException("Unexpected EOF");
                                }
                                if (zaj3 == '\"') {
                                    String zab = zab(bufferedReader, this.zaqb, this.zaqd, null);
                                    if (zaj(bufferedReader) != ':') {
                                        String valueOf = String.valueOf(zab);
                                        throw new ParseException(valueOf.length() != 0 ? "No map value found for key ".concat(valueOf) : new String("No map value found for key "));
                                    } else if (zaj(bufferedReader) != '\"') {
                                        String valueOf2 = String.valueOf(zab);
                                        throw new ParseException(valueOf2.length() != 0 ? "Expected String value for key ".concat(valueOf2) : new String("Expected String value for key "));
                                    } else {
                                        hashMap.put(zab, zab(bufferedReader, this.zaqb, this.zaqd, null));
                                        char zaj4 = zaj(bufferedReader);
                                        if (zaj4 != ',') {
                                            if (zaj4 == '}') {
                                                zak(1);
                                            } else {
                                                StringBuilder sb2 = new StringBuilder(48);
                                                sb2.append("Unexpected character while parsing string map: ");
                                                sb2.append(zaj4);
                                                throw new ParseException(sb2.toString());
                                            }
                                        }
                                    }
                                } else if (zaj3 == '}') {
                                    zak(1);
                                }
                                i = 4;
                                zak(i);
                                zak(2);
                                zaj = zaj(bufferedReader);
                                if (zaj != ',') {
                                }
                            }
                        }
                        fastJsonResponse.zaa((FastJsonResponse.Field) field, (Map<String, String>) hashMap);
                        i = 4;
                        zak(i);
                        zak(2);
                        zaj = zaj(bufferedReader);
                        if (zaj != ',') {
                        }
                        break;
                    case 11:
                        if (field.zapr) {
                            char zaj5 = zaj(bufferedReader);
                            if (zaj5 == 'n') {
                                zab(bufferedReader, zaqf);
                                fastJsonResponse.addConcreteTypeArrayInternal(field, field.zapu, null);
                            } else {
                                this.zaql.push(5);
                                if (zaj5 != '[') {
                                    throw new ParseException("Expected array start");
                                }
                                fastJsonResponse.addConcreteTypeArrayInternal(field, field.zapu, zaa(bufferedReader, field));
                            }
                        } else {
                            char zaj6 = zaj(bufferedReader);
                            if (zaj6 != 'n') {
                                this.zaql.push(1);
                                if (zaj6 != '{') {
                                    throw new ParseException("Expected start of object");
                                }
                                try {
                                    FastJsonResponse zacp = field.zacp();
                                    zaa(bufferedReader, zacp);
                                    fastJsonResponse.addConcreteTypeInternal(field, field.zapu, zacp);
                                } catch (IllegalAccessException e) {
                                    throw new ParseException("Error instantiating inner object", e);
                                } catch (InstantiationException e2) {
                                    throw new ParseException("Error instantiating inner object", e2);
                                }
                            } else {
                                zab(bufferedReader, zaqf);
                                fastJsonResponse.addConcreteTypeInternal(field, field.zapu, null);
                            }
                        }
                        i = 4;
                        zak(i);
                        zak(2);
                        zaj = zaj(bufferedReader);
                        if (zaj != ',') {
                        }
                        break;
                    default:
                        int i2 = field.zapq;
                        StringBuilder sb3 = new StringBuilder(30);
                        sb3.append("Invalid field type ");
                        sb3.append(i2);
                        throw new ParseException(sb3.toString());
                }
            }
        }
        zak(1);
        return true;
    }

    private final String zaa(BufferedReader bufferedReader) throws ParseException, IOException {
        this.zaql.push(2);
        char zaj = zaj(bufferedReader);
        if (zaj == '\"') {
            this.zaql.push(3);
            String zab = zab(bufferedReader, this.zaqb, this.zaqd, null);
            zak(3);
            if (zaj(bufferedReader) == ':') {
                return zab;
            }
            throw new ParseException("Expected key/value separator");
        } else if (zaj == ']') {
            zak(2);
            zak(1);
            zak(5);
            return null;
        } else if (zaj == '}') {
            zak(2);
            return null;
        } else {
            StringBuilder sb = new StringBuilder(19);
            sb.append("Unexpected token: ");
            sb.append(zaj);
            throw new ParseException(sb.toString());
        }
    }

    private final String zab(BufferedReader bufferedReader) throws ParseException, IOException {
        bufferedReader.mark(1024);
        char zaj = zaj(bufferedReader);
        if (zaj == '\"') {
            if (bufferedReader.read(this.zaqa) == -1) {
                throw new ParseException("Unexpected EOF while parsing string");
            }
            char c = this.zaqa[0];
            boolean z = false;
            do {
                if (c != '\"' || z) {
                    z = c == '\\' ? !z : false;
                    if (bufferedReader.read(this.zaqa) == -1) {
                        throw new ParseException("Unexpected EOF while parsing string");
                    }
                    c = this.zaqa[0];
                }
            } while (!Character.isISOControl(c));
            throw new ParseException("Unexpected control character while reading string");
        } else if (zaj == ',') {
            throw new ParseException("Missing value");
        } else {
            int i = 1;
            if (zaj == '[') {
                this.zaql.push(5);
                bufferedReader.mark(32);
                if (zaj(bufferedReader) == ']') {
                    zak(5);
                } else {
                    bufferedReader.reset();
                    boolean z2 = false;
                    boolean z3 = false;
                    while (i > 0) {
                        char zaj2 = zaj(bufferedReader);
                        if (zaj2 == 0) {
                            throw new ParseException("Unexpected EOF while parsing array");
                        }
                        if (Character.isISOControl(zaj2)) {
                            throw new ParseException("Unexpected control character while reading array");
                        }
                        if (zaj2 == '\"' && !z2) {
                            z3 = !z3;
                        }
                        if (zaj2 == '[' && !z3) {
                            i++;
                        }
                        if (zaj2 == ']' && !z3) {
                            i--;
                        }
                        z2 = (zaj2 == '\\' && z3) ? !z2 : false;
                    }
                    zak(5);
                }
            } else if (zaj == '{') {
                this.zaql.push(1);
                bufferedReader.mark(32);
                char zaj3 = zaj(bufferedReader);
                if (zaj3 == '}') {
                    zak(1);
                } else if (zaj3 == '\"') {
                    bufferedReader.reset();
                    zaa(bufferedReader);
                    do {
                    } while (zab(bufferedReader) != null);
                    zak(1);
                } else {
                    StringBuilder sb = new StringBuilder(18);
                    sb.append("Unexpected token ");
                    sb.append(zaj3);
                    throw new ParseException(sb.toString());
                }
            } else {
                bufferedReader.reset();
                zaa(bufferedReader, this.zaqc);
            }
        }
        char zaj4 = zaj(bufferedReader);
        if (zaj4 == ',') {
            zak(2);
            return zaa(bufferedReader);
        } else if (zaj4 == '}') {
            zak(2);
            return null;
        } else {
            StringBuilder sb2 = new StringBuilder(18);
            sb2.append("Unexpected token ");
            sb2.append(zaj4);
            throw new ParseException(sb2.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String zac(BufferedReader bufferedReader) throws ParseException, IOException {
        return zaa(bufferedReader, this.zaqb, this.zaqd, null);
    }

    private final <O> ArrayList<O> zaa(BufferedReader bufferedReader, zaa<O> zaaVar) throws ParseException, IOException {
        char zaj = zaj(bufferedReader);
        if (zaj == 'n') {
            zab(bufferedReader, zaqf);
            return null;
        } else if (zaj != '[') {
            throw new ParseException("Expected start of array");
        } else {
            this.zaql.push(5);
            ArrayList<O> arrayList = new ArrayList<>();
            while (true) {
                bufferedReader.mark(1024);
                char zaj2 = zaj(bufferedReader);
                if (zaj2 == 0) {
                    throw new ParseException("Unexpected EOF");
                }
                if (zaj2 != ',') {
                    if (zaj2 == ']') {
                        zak(5);
                        return arrayList;
                    }
                    bufferedReader.reset();
                    arrayList.add(zaaVar.zah(this, bufferedReader));
                }
            }
        }
    }

    private final String zaa(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, char[] cArr2) throws ParseException, IOException {
        char zaj = zaj(bufferedReader);
        if (zaj != '\"') {
            if (zaj == 'n') {
                zab(bufferedReader, zaqf);
                return null;
            }
            throw new ParseException("Expected string");
        }
        return zab(bufferedReader, cArr, sb, cArr2);
    }

    private static String zab(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, char[] cArr2) throws ParseException, IOException {
        boolean z;
        sb.setLength(0);
        bufferedReader.mark(cArr.length);
        boolean z2 = false;
        boolean z3 = false;
        while (true) {
            int read = bufferedReader.read(cArr);
            if (read != -1) {
                for (int i = 0; i < read; i++) {
                    char c = cArr[i];
                    if (Character.isISOControl(c)) {
                        if (cArr2 != null) {
                            for (char c2 : cArr2) {
                                if (c2 == c) {
                                    z = true;
                                    break;
                                }
                            }
                        }
                        z = false;
                        if (!z) {
                            throw new ParseException("Unexpected control character while reading string");
                        }
                    }
                    if (c == '\"' && !z2) {
                        sb.append(cArr, 0, i);
                        bufferedReader.reset();
                        bufferedReader.skip(i + 1);
                        if (z3) {
                            return JsonUtils.unescapeString(sb.toString());
                        }
                        return sb.toString();
                    }
                    if (c == '\\') {
                        z2 = !z2;
                        z3 = true;
                    } else {
                        z2 = false;
                    }
                }
                sb.append(cArr, 0, read);
                bufferedReader.mark(cArr.length);
            } else {
                throw new ParseException("Unexpected EOF while parsing string");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int zad(BufferedReader bufferedReader) throws ParseException, IOException {
        int i;
        int i2;
        boolean z;
        int zaa2 = zaa(bufferedReader, this.zaqc);
        int i3 = 0;
        if (zaa2 == 0) {
            return 0;
        }
        char[] cArr = this.zaqc;
        if (zaa2 > 0) {
            if (cArr[0] == '-') {
                i = Integer.MIN_VALUE;
                i2 = 1;
                z = true;
            } else {
                i = -2147483647;
                i2 = 0;
                z = false;
            }
            if (i2 < zaa2) {
                int i4 = i2 + 1;
                int digit = Character.digit(cArr[i2], 10);
                if (digit < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                int i5 = -digit;
                i2 = i4;
                i3 = i5;
            }
            while (i2 < zaa2) {
                int i6 = i2 + 1;
                int digit2 = Character.digit(cArr[i2], 10);
                if (digit2 < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                if (i3 < -214748364) {
                    throw new ParseException("Number too large");
                }
                int i7 = i3 * 10;
                if (i7 < i + digit2) {
                    throw new ParseException("Number too large");
                }
                i3 = i7 - digit2;
                i2 = i6;
            }
            if (z) {
                if (i2 > 1) {
                    return i3;
                }
                throw new ParseException("No digits to parse");
            }
            return -i3;
        }
        throw new ParseException("No number to parse");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long zae(BufferedReader bufferedReader) throws ParseException, IOException {
        long j;
        boolean z;
        int zaa2 = zaa(bufferedReader, this.zaqc);
        long j2 = 0;
        if (zaa2 == 0) {
            return 0L;
        }
        char[] cArr = this.zaqc;
        if (zaa2 > 0) {
            int i = 0;
            if (cArr[0] == '-') {
                j = Long.MIN_VALUE;
                i = 1;
                z = true;
            } else {
                j = -9223372036854775807L;
                z = false;
            }
            if (i < zaa2) {
                int i2 = i + 1;
                int digit = Character.digit(cArr[i], 10);
                if (digit < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                i = i2;
                j2 = -digit;
            }
            while (i < zaa2) {
                int i3 = i + 1;
                int digit2 = Character.digit(cArr[i], 10);
                if (digit2 < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                if (j2 < BufferKt.OVERFLOW_ZONE) {
                    throw new ParseException("Number too large");
                }
                long j3 = j2 * 10;
                long j4 = digit2;
                if (j3 < j + j4) {
                    throw new ParseException("Number too large");
                }
                j2 = j3 - j4;
                i = i3;
            }
            if (z) {
                if (i > 1) {
                    return j2;
                }
                throw new ParseException("No digits to parse");
            }
            return -j2;
        }
        throw new ParseException("No number to parse");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BigInteger zaf(BufferedReader bufferedReader) throws ParseException, IOException {
        int zaa2 = zaa(bufferedReader, this.zaqc);
        if (zaa2 == 0) {
            return null;
        }
        return new BigInteger(new String(this.zaqc, 0, zaa2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zaa(BufferedReader bufferedReader, boolean z) throws ParseException, IOException {
        while (true) {
            char zaj = zaj(bufferedReader);
            if (zaj != '\"') {
                if (zaj == 'f') {
                    zab(bufferedReader, z ? zaqj : zaqi);
                    return false;
                } else if (zaj == 'n') {
                    zab(bufferedReader, zaqf);
                    return false;
                } else if (zaj == 't') {
                    zab(bufferedReader, z ? zaqh : zaqg);
                    return true;
                } else {
                    StringBuilder sb = new StringBuilder(19);
                    sb.append("Unexpected token: ");
                    sb.append(zaj);
                    throw new ParseException(sb.toString());
                }
            } else if (z) {
                throw new ParseException("No boolean value found in string");
            } else {
                z = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float zag(BufferedReader bufferedReader) throws ParseException, IOException {
        int zaa2 = zaa(bufferedReader, this.zaqc);
        if (zaa2 == 0) {
            return 0.0f;
        }
        return Float.parseFloat(new String(this.zaqc, 0, zaa2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double zah(BufferedReader bufferedReader) throws ParseException, IOException {
        int zaa2 = zaa(bufferedReader, this.zaqc);
        if (zaa2 == 0) {
            return 0.0d;
        }
        return Double.parseDouble(new String(this.zaqc, 0, zaa2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BigDecimal zai(BufferedReader bufferedReader) throws ParseException, IOException {
        int zaa2 = zaa(bufferedReader, this.zaqc);
        if (zaa2 == 0) {
            return null;
        }
        return new BigDecimal(new String(this.zaqc, 0, zaa2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <T extends FastJsonResponse> ArrayList<T> zaa(BufferedReader bufferedReader, FastJsonResponse.Field<?, ?> field) throws ParseException, IOException {
        ArrayList<T> arrayList = (ArrayList<T>) new ArrayList();
        char zaj = zaj(bufferedReader);
        if (zaj == ']') {
            zak(5);
            return arrayList;
        } else if (zaj == 'n') {
            zab(bufferedReader, zaqf);
            zak(5);
            return null;
        } else if (zaj == '{') {
            this.zaql.push(1);
            while (true) {
                try {
                    FastJsonResponse zacp = field.zacp();
                    if (!zaa(bufferedReader, zacp)) {
                        return arrayList;
                    }
                    arrayList.add(zacp);
                    char zaj2 = zaj(bufferedReader);
                    if (zaj2 != ',') {
                        if (zaj2 == ']') {
                            zak(5);
                            return arrayList;
                        }
                        StringBuilder sb = new StringBuilder(19);
                        sb.append("Unexpected token: ");
                        sb.append(zaj2);
                        throw new ParseException(sb.toString());
                    } else if (zaj(bufferedReader) != '{') {
                        throw new ParseException("Expected start of next object in array");
                    } else {
                        this.zaql.push(1);
                    }
                } catch (IllegalAccessException e) {
                    throw new ParseException("Error instantiating inner object", e);
                } catch (InstantiationException e2) {
                    throw new ParseException("Error instantiating inner object", e2);
                }
            }
        } else {
            StringBuilder sb2 = new StringBuilder(19);
            sb2.append("Unexpected token: ");
            sb2.append(zaj);
            throw new ParseException(sb2.toString());
        }
    }

    private final char zaj(BufferedReader bufferedReader) throws ParseException, IOException {
        if (bufferedReader.read(this.zaqa) == -1) {
            return (char) 0;
        }
        while (Character.isWhitespace(this.zaqa[0])) {
            if (bufferedReader.read(this.zaqa) == -1) {
                return (char) 0;
            }
        }
        return this.zaqa[0];
    }

    private final int zaa(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i;
        char zaj = zaj(bufferedReader);
        if (zaj != 0) {
            if (zaj != ',') {
                if (zaj == 'n') {
                    zab(bufferedReader, zaqf);
                    return 0;
                }
                bufferedReader.mark(1024);
                if (zaj == '\"') {
                    i = 0;
                    boolean z = false;
                    while (i < cArr.length && bufferedReader.read(cArr, i, 1) != -1) {
                        char c = cArr[i];
                        if (Character.isISOControl(c)) {
                            throw new ParseException("Unexpected control character while reading string");
                        }
                        if (c == '\"' && !z) {
                            bufferedReader.reset();
                            bufferedReader.skip(i + 1);
                            return i;
                        }
                        z = c == '\\' ? !z : false;
                        i++;
                    }
                } else {
                    cArr[0] = zaj;
                    i = 1;
                    while (i < cArr.length && bufferedReader.read(cArr, i, 1) != -1) {
                        if (cArr[i] == '}' || cArr[i] == ',' || Character.isWhitespace(cArr[i]) || cArr[i] == ']') {
                            bufferedReader.reset();
                            bufferedReader.skip(i - 1);
                            cArr[i] = 0;
                            return i;
                        }
                        i++;
                    }
                }
                if (i == cArr.length) {
                    throw new ParseException("Absurdly long value");
                }
                throw new ParseException("Unexpected EOF");
            }
            throw new ParseException("Missing value");
        }
        throw new ParseException("Unexpected EOF");
    }

    private final void zab(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i = 0;
        while (i < cArr.length) {
            int read = bufferedReader.read(this.zaqb, 0, cArr.length - i);
            if (read == -1) {
                throw new ParseException("Unexpected EOF");
            }
            for (int i2 = 0; i2 < read; i2++) {
                if (cArr[i2 + i] != this.zaqb[i2]) {
                    throw new ParseException("Unexpected character");
                }
            }
            i += read;
        }
    }

    private final void zak(int i) throws ParseException {
        if (this.zaql.isEmpty()) {
            StringBuilder sb = new StringBuilder(46);
            sb.append("Expected state ");
            sb.append(i);
            sb.append(" but had empty stack");
            throw new ParseException(sb.toString());
        }
        int intValue = this.zaql.pop().intValue();
        if (intValue == i) {
            return;
        }
        StringBuilder sb2 = new StringBuilder(46);
        sb2.append("Expected state ");
        sb2.append(i);
        sb2.append(" but had ");
        sb2.append(intValue);
        throw new ParseException(sb2.toString());
    }
}
