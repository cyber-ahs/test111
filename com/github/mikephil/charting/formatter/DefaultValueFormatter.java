package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.FormattedStringCache;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.text.DecimalFormat;
/* loaded from: classes.dex */
public class DefaultValueFormatter implements ValueFormatter {
    protected int mDecimalDigits;
    protected FormattedStringCache.Generic<Integer, Float> mFormattedStringCache;

    public DefaultValueFormatter(int i) {
        setup(i);
    }

    public void setup(int i) {
        this.mDecimalDigits = i;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 == 0) {
                stringBuffer.append(".");
            }
            stringBuffer.append("0");
        }
        this.mFormattedStringCache = new FormattedStringCache.Generic<>(new DecimalFormat("###,###,###,##0" + stringBuffer.toString()));
    }

    @Override // com.github.mikephil.charting.formatter.ValueFormatter
    public String getFormattedValue(float f, Entry entry, int i, ViewPortHandler viewPortHandler) {
        return this.mFormattedStringCache.getFormattedValue(Float.valueOf(f), Integer.valueOf(i));
    }

    public int getDecimalDigits() {
        return this.mDecimalDigits;
    }
}
