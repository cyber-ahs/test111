package org.jsoup.nodes;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import kotlin.text.Typography;
import org.jsoup.SerializationException;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
/* loaded from: classes2.dex */
public class Attribute implements Map.Entry<String, String>, Cloneable {
    private static final String[] booleanAttributes = {"allowfullscreen", "async", "autofocus", "checked", "compact", "declare", "default", "defer", "disabled", "formnovalidate", "hidden", "inert", "ismap", "itemscope", "multiple", "muted", "nohref", "noresize", "noshade", "novalidate", "nowrap", "open", "readonly", "required", "reversed", "seamless", "selected", "sortable", "truespeed", "typemustmatch"};
    private String key;
    private String value;

    public Attribute(String str, String str2) {
        Validate.notEmpty(str);
        Validate.notNull(str2);
        this.key = str.trim();
        this.value = str2;
    }

    @Override // java.util.Map.Entry
    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        Validate.notEmpty(str);
        this.key = str.trim();
    }

    @Override // java.util.Map.Entry
    public String getValue() {
        return this.value;
    }

    @Override // java.util.Map.Entry
    public String setValue(String str) {
        Validate.notNull(str);
        String str2 = this.value;
        this.value = str;
        return str2;
    }

    public String html() {
        StringBuilder sb = new StringBuilder();
        try {
            html(sb, new Document("").outputSettings());
            return sb.toString();
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void html(Appendable appendable, Document.OutputSettings outputSettings) throws IOException {
        appendable.append(this.key);
        if (shouldCollapseAttribute(outputSettings)) {
            return;
        }
        appendable.append("=\"");
        Entities.escape(appendable, this.value, outputSettings, true, false, false);
        appendable.append(Typography.quote);
    }

    public String toString() {
        return html();
    }

    public static Attribute createFromEncoded(String str, String str2) {
        return new Attribute(str, Entities.unescape(str2, true));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isDataAttribute() {
        return this.key.startsWith("data-") && this.key.length() > 5;
    }

    protected final boolean shouldCollapseAttribute(Document.OutputSettings outputSettings) {
        return ("".equals(this.value) || this.value.equalsIgnoreCase(this.key)) && outputSettings.syntax() == Document.OutputSettings.Syntax.html && isBooleanAttribute();
    }

    protected boolean isBooleanAttribute() {
        return Arrays.binarySearch(booleanAttributes, this.key) >= 0;
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Attribute) {
            Attribute attribute = (Attribute) obj;
            String str = this.key;
            if (str == null ? attribute.key == null : str.equals(attribute.key)) {
                String str2 = this.value;
                String str3 = attribute.value;
                if (str2 != null) {
                    if (str2.equals(str3)) {
                        return true;
                    }
                } else if (str3 == null) {
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        String str = this.key;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.value;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public Attribute clone() {
        try {
            return (Attribute) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
