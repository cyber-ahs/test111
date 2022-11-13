package org.jsoup.parser;

import java.util.Iterator;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
/* loaded from: classes2.dex */
public class ParseSettings {
    public static final ParseSettings htmlDefault = new ParseSettings(false, false);
    public static final ParseSettings preserveCase = new ParseSettings(true, true);
    private final boolean preserveAttributeCase;
    private final boolean preserveTagCase;

    public ParseSettings(boolean z, boolean z2) {
        this.preserveTagCase = z;
        this.preserveAttributeCase = z2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String normalizeTag(String str) {
        String trim = str.trim();
        return !this.preserveTagCase ? trim.toLowerCase() : trim;
    }

    String normalizeAttribute(String str) {
        String trim = str.trim();
        return !this.preserveAttributeCase ? trim.toLowerCase() : trim;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Attributes normalizeAttributes(Attributes attributes) {
        if (!this.preserveAttributeCase) {
            Iterator<Attribute> it = attributes.iterator();
            while (it.hasNext()) {
                Attribute next = it.next();
                next.setKey(next.getKey().toLowerCase());
            }
        }
        return attributes;
    }
}
