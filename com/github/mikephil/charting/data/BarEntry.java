package com.github.mikephil.charting.data;

import com.github.mikephil.charting.highlight.Range;
/* loaded from: classes.dex */
public class BarEntry extends Entry {
    private float mNegativeSum;
    private float mPositiveSum;
    private Range[] mRanges;
    private float[] mYVals;

    public BarEntry(float f, float[] fArr) {
        super(f, calcSum(fArr));
        this.mYVals = fArr;
        calcRanges();
        calcPosNegSum();
    }

    public BarEntry(float f, float f2) {
        super(f, f2);
    }

    public BarEntry(float f, float[] fArr, String str) {
        super(f, calcSum(fArr), str);
        this.mYVals = fArr;
        calcRanges();
        calcPosNegSum();
    }

    public BarEntry(float f, float f2, Object obj) {
        super(f, f2, obj);
    }

    @Override // com.github.mikephil.charting.data.Entry
    public BarEntry copy() {
        BarEntry barEntry = new BarEntry(getX(), getY(), getData());
        barEntry.setVals(this.mYVals);
        return barEntry;
    }

    public float[] getYVals() {
        return this.mYVals;
    }

    public void setVals(float[] fArr) {
        setY(calcSum(fArr));
        this.mYVals = fArr;
        calcPosNegSum();
        calcRanges();
    }

    @Override // com.github.mikephil.charting.data.BaseEntry
    public float getY() {
        return super.getY();
    }

    public Range[] getRanges() {
        return this.mRanges;
    }

    public boolean isStacked() {
        return this.mYVals != null;
    }

    public float getBelowSum(int i) {
        float[] fArr = this.mYVals;
        float f = 0.0f;
        if (fArr == null) {
            return 0.0f;
        }
        for (int length = fArr.length - 1; length > i && length >= 0; length--) {
            f += this.mYVals[length];
        }
        return f;
    }

    public float getPositiveSum() {
        return this.mPositiveSum;
    }

    public float getNegativeSum() {
        return this.mNegativeSum;
    }

    private void calcPosNegSum() {
        float[] fArr = this.mYVals;
        if (fArr == null) {
            this.mNegativeSum = 0.0f;
            this.mPositiveSum = 0.0f;
            return;
        }
        float f = 0.0f;
        float f2 = 0.0f;
        for (float f3 : fArr) {
            if (f3 <= 0.0f) {
                f += Math.abs(f3);
            } else {
                f2 += f3;
            }
        }
        this.mNegativeSum = f;
        this.mPositiveSum = f2;
    }

    private static float calcSum(float[] fArr) {
        float f = 0.0f;
        if (fArr == null) {
            return 0.0f;
        }
        for (float f2 : fArr) {
            f += f2;
        }
        return f;
    }

    protected void calcRanges() {
        float[] yVals = getYVals();
        if (yVals == null || yVals.length == 0) {
            return;
        }
        this.mRanges = new Range[yVals.length];
        float f = -getNegativeSum();
        int i = 0;
        float f2 = 0.0f;
        while (true) {
            Range[] rangeArr = this.mRanges;
            if (i >= rangeArr.length) {
                return;
            }
            float f3 = yVals[i];
            if (f3 < 0.0f) {
                rangeArr[i] = new Range(f, f + f3);
                f += Math.abs(f3);
            } else {
                float f4 = f3 + f2;
                rangeArr[i] = new Range(f2, f4);
                f2 = f4;
            }
            i++;
        }
    }
}
