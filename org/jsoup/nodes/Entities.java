package org.jsoup.nodes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.text.Typography;
import org.jsoup.SerializationException;
import org.jsoup.helper.DataUtil;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.parser.CharacterReader;
import org.jsoup.parser.Parser;
/* loaded from: classes2.dex */
public class Entities {
    static final int codepointRadix = 36;
    private static final int empty = -1;
    private static final String emptyName = "";
    private static final HashMap<String, String> multipoints = new HashMap<>();
    private static final char[] codeDelims = {',', ';'};

    /* loaded from: classes2.dex */
    public enum EscapeMode {
        xhtml("entities-xhtml.properties", 4),
        base("entities-base.properties", 106),
        extended("entities-full.properties", 2125);
        
        private int[] codeKeys;
        private int[] codeVals;
        private String[] nameKeys;
        private String[] nameVals;

        EscapeMode(String str, int i) {
            Entities.load(this, str, i);
        }

        int codepointForName(String str) {
            int binarySearch = Arrays.binarySearch(this.nameKeys, str);
            if (binarySearch >= 0) {
                return this.codeVals[binarySearch];
            }
            return -1;
        }

        String nameForCodepoint(int i) {
            int binarySearch = Arrays.binarySearch(this.codeKeys, i);
            if (binarySearch >= 0) {
                String[] strArr = this.nameVals;
                if (binarySearch < strArr.length - 1) {
                    int i2 = binarySearch + 1;
                    if (this.codeKeys[i2] == i) {
                        return strArr[i2];
                    }
                }
                return strArr[binarySearch];
            }
            return "";
        }

        private int size() {
            return this.nameKeys.length;
        }
    }

    private Entities() {
    }

    public static boolean isNamedEntity(String str) {
        return EscapeMode.extended.codepointForName(str) != -1;
    }

    public static boolean isBaseNamedEntity(String str) {
        return EscapeMode.base.codepointForName(str) != -1;
    }

    public static Character getCharacterByName(String str) {
        return Character.valueOf((char) EscapeMode.extended.codepointForName(str));
    }

    public static String getByName(String str) {
        String str2 = multipoints.get(str);
        if (str2 != null) {
            return str2;
        }
        int codepointForName = EscapeMode.extended.codepointForName(str);
        return codepointForName != -1 ? new String(new int[]{codepointForName}, 0, 1) : "";
    }

    public static int codepointsForName(String str, int[] iArr) {
        String str2 = multipoints.get(str);
        if (str2 != null) {
            iArr[0] = str2.codePointAt(0);
            iArr[1] = str2.codePointAt(1);
            return 2;
        }
        int codepointForName = EscapeMode.extended.codepointForName(str);
        if (codepointForName != -1) {
            iArr[0] = codepointForName;
            return 1;
        }
        return 0;
    }

    static String escape(String str, Document.OutputSettings outputSettings) {
        StringBuilder sb = new StringBuilder(str.length() * 2);
        try {
            escape(sb, str, outputSettings, false, false, false);
            return sb.toString();
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void escape(Appendable appendable, String str, Document.OutputSettings outputSettings, boolean z, boolean z2, boolean z3) throws IOException {
        EscapeMode escapeMode = outputSettings.escapeMode();
        CharsetEncoder encoder = outputSettings.encoder();
        CoreCharset byName = CoreCharset.byName(encoder.charset().name());
        int length = str.length();
        int i = 0;
        boolean z4 = false;
        boolean z5 = false;
        while (i < length) {
            int codePointAt = str.codePointAt(i);
            if (z2) {
                if (StringUtil.isWhitespace(codePointAt)) {
                    if ((!z3 || z4) && !z5) {
                        appendable.append(' ');
                        z5 = true;
                    }
                    i += Character.charCount(codePointAt);
                } else {
                    z4 = true;
                    z5 = false;
                }
            }
            if (codePointAt < 65536) {
                char c = (char) codePointAt;
                if (c != '\"') {
                    if (c == '&') {
                        appendable.append("&amp;");
                    } else if (c != '<') {
                        if (c != '>') {
                            if (c == 160) {
                                if (escapeMode != EscapeMode.xhtml) {
                                    appendable.append("&nbsp;");
                                } else {
                                    appendable.append("&#xa0;");
                                }
                            } else if (canEncode(byName, c, encoder)) {
                                appendable.append(c);
                            } else {
                                appendEncoded(appendable, escapeMode, codePointAt);
                            }
                        } else if (!z) {
                            appendable.append("&gt;");
                        } else {
                            appendable.append(c);
                        }
                    } else if (!z || escapeMode == EscapeMode.xhtml) {
                        appendable.append("&lt;");
                    } else {
                        appendable.append(c);
                    }
                } else if (z) {
                    appendable.append("&quot;");
                } else {
                    appendable.append(c);
                }
            } else {
                String str2 = new String(Character.toChars(codePointAt));
                if (encoder.canEncode(str2)) {
                    appendable.append(str2);
                } else {
                    appendEncoded(appendable, escapeMode, codePointAt);
                }
            }
            i += Character.charCount(codePointAt);
        }
    }

    private static void appendEncoded(Appendable appendable, EscapeMode escapeMode, int i) throws IOException {
        String nameForCodepoint = escapeMode.nameForCodepoint(i);
        if (nameForCodepoint != "") {
            appendable.append(Typography.amp).append(nameForCodepoint).append(';');
        } else {
            appendable.append("&#x").append(Integer.toHexString(i)).append(';');
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String unescape(String str) {
        return unescape(str, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String unescape(String str, boolean z) {
        return Parser.unescapeEntities(str, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.jsoup.nodes.Entities$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$jsoup$nodes$Entities$CoreCharset;

        static {
            int[] iArr = new int[CoreCharset.values().length];
            $SwitchMap$org$jsoup$nodes$Entities$CoreCharset = iArr;
            try {
                iArr[CoreCharset.ascii.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jsoup$nodes$Entities$CoreCharset[CoreCharset.utf.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static boolean canEncode(CoreCharset coreCharset, char c, CharsetEncoder charsetEncoder) {
        int i = AnonymousClass1.$SwitchMap$org$jsoup$nodes$Entities$CoreCharset[coreCharset.ordinal()];
        if (i == 1) {
            return c < 128;
        } else if (i != 2) {
            return charsetEncoder.canEncode(c);
        } else {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public enum CoreCharset {
        ascii,
        utf,
        fallback;

        /* JADX INFO: Access modifiers changed from: private */
        public static CoreCharset byName(String str) {
            if (str.equals("US-ASCII")) {
                return ascii;
            }
            if (str.startsWith("UTF-")) {
                return utf;
            }
            return fallback;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void load(EscapeMode escapeMode, String str, int i) {
        int i2;
        escapeMode.nameKeys = new String[i];
        escapeMode.codeVals = new int[i];
        escapeMode.codeKeys = new int[i];
        escapeMode.nameVals = new String[i];
        InputStream resourceAsStream = Entities.class.getResourceAsStream(str);
        if (resourceAsStream == null) {
            throw new IllegalStateException("Could not read resource " + str + ". Make sure you copy resources for " + Entities.class.getCanonicalName());
        }
        try {
            CharacterReader characterReader = new CharacterReader(Charset.forName("ascii").decode(DataUtil.readToByteBuffer(resourceAsStream, 0)).toString());
            int i3 = 0;
            while (!characterReader.isEmpty()) {
                String consumeTo = characterReader.consumeTo('=');
                characterReader.advance();
                int parseInt = Integer.parseInt(characterReader.consumeToAny(codeDelims), 36);
                char current = characterReader.current();
                characterReader.advance();
                if (current == ',') {
                    i2 = Integer.parseInt(characterReader.consumeTo(';'), 36);
                    characterReader.advance();
                } else {
                    i2 = -1;
                }
                String consumeTo2 = characterReader.consumeTo('\n');
                if (consumeTo2.charAt(consumeTo2.length() - 1) == '\r') {
                    consumeTo2 = consumeTo2.substring(0, consumeTo2.length() - 1);
                }
                int parseInt2 = Integer.parseInt(consumeTo2, 36);
                characterReader.advance();
                escapeMode.nameKeys[i3] = consumeTo;
                escapeMode.codeVals[i3] = parseInt;
                escapeMode.codeKeys[parseInt2] = parseInt;
                escapeMode.nameVals[parseInt2] = consumeTo;
                if (i2 != -1) {
                    multipoints.put(consumeTo, new String(new int[]{parseInt, i2}, 0, 2));
                }
                i3++;
            }
        } catch (IOException unused) {
            throw new IllegalStateException("Error reading resource " + str);
        }
    }
}
