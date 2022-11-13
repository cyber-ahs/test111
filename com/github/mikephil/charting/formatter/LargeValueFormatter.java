package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.FormattedStringCache;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.text.DecimalFormat;
/* loaded from: classes.dex */
public class LargeValueFormatter implements ValueFormatter, AxisValueFormatter {
    private static final int MAX_LENGTH = 5;
    private static String[] SUFFIX = {"", "k", "m", "b", "t"};
    protected FormattedStringCache.PrimDouble mFormattedStringCache;
    private String mText;

    @Override // com.github.mikephil.charting.formatter.AxisValueFormatter
    public int getDecimalDigits() {
        return 0;
    }

    public LargeValueFormatter() {
        this.mText = "";
        this.mFormattedStringCache = new FormattedStringCache.PrimDouble(new DecimalFormat("###E00"));
    }

    public LargeValueFormatter(String str) {
        this();
        this.mText = str;
    }

    @Override // com.github.mikephil.charting.formatter.ValueFormatter
    public String getFormattedValue(float f, Entry entry, int i, ViewPortHandler viewPortHandler) {
        return makePretty(f) + this.mText;
    }

    @Override // com.github.mikephil.charting.formatter.AxisValueFormatter
    public String getFormattedValue(float f, AxisBase axisBase) {
        return makePretty(f) + this.mText;
    }

    public void setAppendix(String str) {
        this.mText = str;
    }

    public void setSuffix(String[] strArr) {
        SUFFIX = strArr;
    }

    private String makePretty(double d) {
        String formattedValue = this.mFormattedStringCache.getFormattedValue(d);
        int numericValue = Character.getNumericValue(formattedValue.charAt(formattedValue.length() - 1));
        String replaceAll = formattedValue.replaceAll("E[0-9][0-9]", SUFFIX[Integer.valueOf(Character.getNumericValue(formattedValue.charAt(formattedValue.length() - 2)) + "" + numericValue).intValue() / 3]);
        while (true) {
            if (replaceAll.length() <= 5 && !replaceAll.matches("[0-9]+\\.[a-z]")) {
                return replaceAll;
            }
            replaceAll = replaceAll.substring(0, replaceAll.length() - 2) + replaceAll.substring(replaceAll.length() - 1);
        }
    }
}
