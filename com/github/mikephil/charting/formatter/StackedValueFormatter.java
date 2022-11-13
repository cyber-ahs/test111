package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.FormattedStringCache;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.text.DecimalFormat;
/* loaded from: classes.dex */
public class StackedValueFormatter implements ValueFormatter {
    private String mAppendix;
    private boolean mDrawWholeStack;
    private DecimalFormat mFormat;
    private FormattedStringCache.Generic mFormattedStringCache;
    private FormattedStringCache.Generic mFormattedStringCacheWholeStack;

    public StackedValueFormatter(boolean z, String str, int i) {
        this.mDrawWholeStack = z;
        this.mAppendix = str;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 == 0) {
                stringBuffer.append(".");
            }
            stringBuffer.append("0");
        }
        this.mFormattedStringCache = new FormattedStringCache.Generic(new DecimalFormat("###,###,###,##0" + stringBuffer.toString()));
        this.mFormattedStringCacheWholeStack = new FormattedStringCache.Generic(new DecimalFormat("###,###,###,##0" + stringBuffer.toString()));
    }

    @Override // com.github.mikephil.charting.formatter.ValueFormatter
    public String getFormattedValue(float f, Entry entry, int i, ViewPortHandler viewPortHandler) {
        BarEntry barEntry;
        float[] yVals;
        FormattedStringCache.Generic generic = this.mFormattedStringCache;
        if (!this.mDrawWholeStack && (entry instanceof BarEntry) && (yVals = (barEntry = (BarEntry) entry).getYVals()) != null) {
            if (yVals[yVals.length - 1] == f) {
                generic = this.mFormattedStringCacheWholeStack;
                f = barEntry.getY();
            } else {
                generic = null;
            }
        }
        if (generic == null) {
            return "";
        }
        return generic.getFormattedValue(Float.valueOf(f), Integer.valueOf(i)) + this.mAppendix;
    }
}
