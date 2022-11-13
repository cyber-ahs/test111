package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.FormattedStringCache;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.text.DecimalFormat;
/* loaded from: classes.dex */
public class PercentFormatter implements ValueFormatter, AxisValueFormatter {
    protected FormattedStringCache.Generic<Integer, Float> mFormattedStringCache;
    protected FormattedStringCache.PrimFloat mFormattedStringCacheAxis;

    @Override // com.github.mikephil.charting.formatter.AxisValueFormatter
    public int getDecimalDigits() {
        return 1;
    }

    public PercentFormatter() {
        this.mFormattedStringCache = new FormattedStringCache.Generic<>(new DecimalFormat("###,###,##0.0"));
        this.mFormattedStringCacheAxis = new FormattedStringCache.PrimFloat(new DecimalFormat("###,###,##0.0"));
    }

    public PercentFormatter(DecimalFormat decimalFormat) {
        this.mFormattedStringCache = new FormattedStringCache.Generic<>(decimalFormat);
        this.mFormattedStringCacheAxis = new FormattedStringCache.PrimFloat(decimalFormat);
    }

    @Override // com.github.mikephil.charting.formatter.ValueFormatter
    public String getFormattedValue(float f, Entry entry, int i, ViewPortHandler viewPortHandler) {
        return this.mFormattedStringCache.getFormattedValue(Float.valueOf(f), Integer.valueOf(i)) + " %";
    }

    @Override // com.github.mikephil.charting.formatter.AxisValueFormatter
    public String getFormattedValue(float f, AxisBase axisBase) {
        return this.mFormattedStringCacheAxis.getFormattedValue(f) + " %";
    }
}
