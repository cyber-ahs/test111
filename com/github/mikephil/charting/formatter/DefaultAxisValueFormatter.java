package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.FormattedStringCache;
import java.text.DecimalFormat;
/* loaded from: classes.dex */
public class DefaultAxisValueFormatter implements AxisValueFormatter {
    protected int digits;
    protected FormattedStringCache.PrimFloat mFormattedStringCache;

    public DefaultAxisValueFormatter(int i) {
        this.digits = i;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 == 0) {
                stringBuffer.append(".");
            }
            stringBuffer.append("0");
        }
        this.mFormattedStringCache = new FormattedStringCache.PrimFloat(new DecimalFormat("###,###,###,##0" + stringBuffer.toString()));
    }

    @Override // com.github.mikephil.charting.formatter.AxisValueFormatter
    public String getFormattedValue(float f, AxisBase axisBase) {
        return this.mFormattedStringCache.getFormattedValue(f);
    }

    @Override // com.github.mikephil.charting.formatter.AxisValueFormatter
    public int getDecimalDigits() {
        return this.digits;
    }
}
