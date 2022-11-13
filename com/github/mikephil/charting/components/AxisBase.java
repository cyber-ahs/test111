package com.github.mikephil.charting.components;

import android.graphics.DashPathEffect;
import android.util.Log;
import com.github.mikephil.charting.formatter.AxisValueFormatter;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public abstract class AxisBase extends ComponentBase {
    protected AxisValueFormatter mAxisValueFormatter;
    public int mDecimals;
    public int mEntryCount;
    protected List<LimitLine> mLimitLines;
    private int mGridColor = -7829368;
    private float mGridLineWidth = 1.0f;
    private int mAxisLineColor = -7829368;
    private float mAxisLineWidth = 1.0f;
    public float[] mEntries = new float[0];
    public float[] mCenteredEntries = new float[0];
    private int mLabelCount = 6;
    protected float mGranularity = 1.0f;
    protected boolean mGranularityEnabled = false;
    protected boolean mForceLabels = false;
    protected boolean mDrawGridLines = true;
    protected boolean mDrawAxisLine = true;
    protected boolean mDrawLabels = true;
    protected boolean mCenterAxisLabels = false;
    private DashPathEffect mGridDashPathEffect = null;
    protected boolean mDrawLimitLineBehindData = false;
    protected boolean mCustomAxisMin = false;
    protected boolean mCustomAxisMax = false;
    public float mAxisMaximum = 0.0f;
    public float mAxisMinimum = 0.0f;
    public float mAxisRange = 0.0f;

    public AxisBase() {
        this.mTextSize = Utils.convertDpToPixel(10.0f);
        this.mXOffset = Utils.convertDpToPixel(5.0f);
        this.mYOffset = Utils.convertDpToPixel(5.0f);
        this.mLimitLines = new ArrayList();
    }

    public void setDrawGridLines(boolean z) {
        this.mDrawGridLines = z;
    }

    public boolean isDrawGridLinesEnabled() {
        return this.mDrawGridLines;
    }

    public void setDrawAxisLine(boolean z) {
        this.mDrawAxisLine = z;
    }

    public boolean isDrawAxisLineEnabled() {
        return this.mDrawAxisLine;
    }

    public void setCenterAxisLabels(boolean z) {
        this.mCenterAxisLabels = z;
    }

    public boolean isCenterAxisLabelsEnabled() {
        return this.mCenterAxisLabels && this.mEntryCount > 1;
    }

    public void setGridColor(int i) {
        this.mGridColor = i;
    }

    public int getGridColor() {
        return this.mGridColor;
    }

    public void setAxisLineWidth(float f) {
        this.mAxisLineWidth = Utils.convertDpToPixel(f);
    }

    public float getAxisLineWidth() {
        return this.mAxisLineWidth;
    }

    public void setGridLineWidth(float f) {
        this.mGridLineWidth = Utils.convertDpToPixel(f);
    }

    public float getGridLineWidth() {
        return this.mGridLineWidth;
    }

    public void setAxisLineColor(int i) {
        this.mAxisLineColor = i;
    }

    public int getAxisLineColor() {
        return this.mAxisLineColor;
    }

    public void setDrawLabels(boolean z) {
        this.mDrawLabels = z;
    }

    public boolean isDrawLabelsEnabled() {
        return this.mDrawLabels;
    }

    public void setLabelCount(int i) {
        if (i > 25) {
            i = 25;
        }
        if (i < 2) {
            i = 2;
        }
        this.mLabelCount = i;
        this.mForceLabels = false;
    }

    public void setLabelCount(int i, boolean z) {
        setLabelCount(i);
        this.mForceLabels = z;
    }

    public boolean isForceLabelsEnabled() {
        return this.mForceLabels;
    }

    public int getLabelCount() {
        return this.mLabelCount;
    }

    public boolean isGranularityEnabled() {
        return this.mGranularityEnabled;
    }

    public void setGranularityEnabled(boolean z) {
        this.mGranularityEnabled = z;
    }

    public float getGranularity() {
        return this.mGranularity;
    }

    public void setGranularity(float f) {
        this.mGranularity = f;
        this.mGranularityEnabled = true;
    }

    public void addLimitLine(LimitLine limitLine) {
        this.mLimitLines.add(limitLine);
        if (this.mLimitLines.size() > 6) {
            Log.e("MPAndroiChart", "Warning! You have more than 6 LimitLines on your axis, do you really want that?");
        }
    }

    public void removeLimitLine(LimitLine limitLine) {
        this.mLimitLines.remove(limitLine);
    }

    public void removeAllLimitLines() {
        this.mLimitLines.clear();
    }

    public List<LimitLine> getLimitLines() {
        return this.mLimitLines;
    }

    public void setDrawLimitLinesBehindData(boolean z) {
        this.mDrawLimitLineBehindData = z;
    }

    public boolean isDrawLimitLinesBehindDataEnabled() {
        return this.mDrawLimitLineBehindData;
    }

    public String getLongestLabel() {
        String str = "";
        for (int i = 0; i < this.mEntries.length; i++) {
            String formattedLabel = getFormattedLabel(i);
            if (formattedLabel != null && str.length() < formattedLabel.length()) {
                str = formattedLabel;
            }
        }
        return str;
    }

    public String getFormattedLabel(int i) {
        return (i < 0 || i >= this.mEntries.length) ? "" : getValueFormatter().getFormattedValue(this.mEntries[i], this);
    }

    public void setValueFormatter(AxisValueFormatter axisValueFormatter) {
        if (axisValueFormatter == null) {
            this.mAxisValueFormatter = new DefaultAxisValueFormatter(this.mDecimals);
        } else {
            this.mAxisValueFormatter = axisValueFormatter;
        }
    }

    public AxisValueFormatter getValueFormatter() {
        AxisValueFormatter axisValueFormatter = this.mAxisValueFormatter;
        if (axisValueFormatter == null) {
            this.mAxisValueFormatter = new DefaultAxisValueFormatter(this.mDecimals);
        } else {
            int decimalDigits = axisValueFormatter.getDecimalDigits();
            int i = this.mDecimals;
            if (decimalDigits != i && (this.mAxisValueFormatter instanceof DefaultAxisValueFormatter)) {
                this.mAxisValueFormatter = new DefaultAxisValueFormatter(i);
            }
        }
        return this.mAxisValueFormatter;
    }

    public void enableGridDashedLine(float f, float f2, float f3) {
        this.mGridDashPathEffect = new DashPathEffect(new float[]{f, f2}, f3);
    }

    public void disableGridDashedLine() {
        this.mGridDashPathEffect = null;
    }

    public boolean isGridDashedLineEnabled() {
        return this.mGridDashPathEffect != null;
    }

    public DashPathEffect getGridDashPathEffect() {
        return this.mGridDashPathEffect;
    }

    public float getAxisMaximum() {
        return this.mAxisMaximum;
    }

    public float getAxisMinimum() {
        return this.mAxisMinimum;
    }

    public void resetAxisMaxValue() {
        this.mCustomAxisMax = false;
    }

    public boolean isAxisMaxCustom() {
        return this.mCustomAxisMax;
    }

    public void resetAxisMinValue() {
        this.mCustomAxisMin = false;
    }

    public boolean isAxisMinCustom() {
        return this.mCustomAxisMin;
    }

    public void setAxisMinValue(float f) {
        this.mCustomAxisMin = true;
        this.mAxisMinimum = f;
        this.mAxisRange = Math.abs(this.mAxisMaximum - f);
    }

    public void setAxisMaxValue(float f) {
        this.mCustomAxisMax = true;
        this.mAxisMaximum = f;
        this.mAxisRange = Math.abs(f - this.mAxisMinimum);
    }

    public void calculate(float f, float f2) {
        if (this.mCustomAxisMin) {
            f = this.mAxisMinimum;
        }
        if (this.mCustomAxisMax) {
            f2 = this.mAxisMaximum;
        }
        if (Math.abs(f2 - f) == 0.0f) {
            f2 += 1.0f;
            f -= 1.0f;
        }
        this.mAxisMinimum = f;
        this.mAxisMaximum = f2;
        this.mAxisRange = Math.abs(f2 - f);
    }
}
