package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
/* loaded from: classes.dex */
public abstract class AxisRenderer extends Renderer {
    protected AxisBase mAxis;
    protected Paint mAxisLabelPaint;
    protected Paint mAxisLinePaint;
    protected Paint mGridPaint;
    protected Paint mLimitLinePaint;
    protected Transformer mTrans;

    public abstract void renderAxisLabels(Canvas canvas);

    public abstract void renderAxisLine(Canvas canvas);

    public abstract void renderGridLines(Canvas canvas);

    public abstract void renderLimitLines(Canvas canvas);

    public AxisRenderer(ViewPortHandler viewPortHandler, Transformer transformer, AxisBase axisBase) {
        super(viewPortHandler);
        this.mTrans = transformer;
        this.mAxis = axisBase;
        if (this.mViewPortHandler != null) {
            this.mAxisLabelPaint = new Paint(1);
            Paint paint = new Paint();
            this.mGridPaint = paint;
            paint.setColor(-7829368);
            this.mGridPaint.setStrokeWidth(1.0f);
            this.mGridPaint.setStyle(Paint.Style.STROKE);
            this.mGridPaint.setAlpha(90);
            Paint paint2 = new Paint();
            this.mAxisLinePaint = paint2;
            paint2.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.mAxisLinePaint.setStrokeWidth(1.0f);
            this.mAxisLinePaint.setStyle(Paint.Style.STROKE);
            Paint paint3 = new Paint(1);
            this.mLimitLinePaint = paint3;
            paint3.setStyle(Paint.Style.STROKE);
        }
    }

    public Paint getPaintAxisLabels() {
        return this.mAxisLabelPaint;
    }

    public Paint getPaintGrid() {
        return this.mGridPaint;
    }

    public Paint getPaintAxisLine() {
        return this.mAxisLinePaint;
    }

    public Transformer getTransformer() {
        return this.mTrans;
    }

    public void computeAxis(float f, float f2, boolean z) {
        float f3;
        double d;
        if (this.mViewPortHandler != null && this.mViewPortHandler.contentWidth() > 10.0f && !this.mViewPortHandler.isFullyZoomedOutY()) {
            MPPointD valuesByTouchPoint = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop());
            MPPointD valuesByTouchPoint2 = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom());
            if (!z) {
                f3 = (float) valuesByTouchPoint2.y;
                d = valuesByTouchPoint.y;
            } else {
                f3 = (float) valuesByTouchPoint.y;
                d = valuesByTouchPoint2.y;
            }
            MPPointD.recycleInstance(valuesByTouchPoint);
            MPPointD.recycleInstance(valuesByTouchPoint2);
            f = f3;
            f2 = (float) d;
        }
        computeAxisValues(f, f2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void computeAxisValues(float f, float f2) {
        double ceil;
        double nextUp;
        int i;
        float f3 = f;
        int labelCount = this.mAxis.getLabelCount();
        double abs = Math.abs(f2 - f3);
        if (labelCount == 0 || abs <= 0.0d) {
            this.mAxis.mEntries = new float[0];
            this.mAxis.mEntryCount = 0;
            return;
        }
        double d = labelCount;
        Double.isNaN(abs);
        Double.isNaN(d);
        double roundToNextSignificant = Utils.roundToNextSignificant(abs / d);
        if (this.mAxis.isGranularityEnabled() && roundToNextSignificant < this.mAxis.getGranularity()) {
            roundToNextSignificant = this.mAxis.getGranularity();
        }
        double roundToNextSignificant2 = Utils.roundToNextSignificant(Math.pow(10.0d, (int) Math.log10(roundToNextSignificant)));
        Double.isNaN(roundToNextSignificant2);
        if (((int) (roundToNextSignificant / roundToNextSignificant2)) > 5) {
            Double.isNaN(roundToNextSignificant2);
            roundToNextSignificant = Math.floor(roundToNextSignificant2 * 10.0d);
        }
        boolean isCenterAxisLabelsEnabled = this.mAxis.isCenterAxisLabelsEnabled();
        if (this.mAxis.isForceLabelsEnabled()) {
            float f4 = ((float) abs) / (labelCount - 1);
            this.mAxis.mEntryCount = labelCount;
            if (this.mAxis.mEntries.length < labelCount) {
                this.mAxis.mEntries = new float[labelCount];
            }
            for (int i2 = 0; i2 < labelCount; i2++) {
                this.mAxis.mEntries[i2] = f3;
                f3 += f4;
            }
        } else {
            if (roundToNextSignificant == 0.0d) {
                ceil = 0.0d;
            } else {
                double d2 = f3;
                Double.isNaN(d2);
                ceil = Math.ceil(d2 / roundToNextSignificant) * roundToNextSignificant;
            }
            if (isCenterAxisLabelsEnabled) {
                ceil -= roundToNextSignificant;
            }
            if (roundToNextSignificant == 0.0d) {
                nextUp = 0.0d;
            } else {
                double d3 = f2;
                Double.isNaN(d3);
                nextUp = Utils.nextUp(Math.floor(d3 / roundToNextSignificant) * roundToNextSignificant);
            }
            if (roundToNextSignificant != 0.0d) {
                i = isCenterAxisLabelsEnabled;
                for (double d4 = ceil; d4 <= nextUp; d4 += roundToNextSignificant) {
                    i++;
                }
            } else {
                i = isCenterAxisLabelsEnabled;
            }
            this.mAxis.mEntryCount = i;
            if (this.mAxis.mEntries.length < i) {
                this.mAxis.mEntries = new float[i];
            }
            for (int i3 = 0; i3 < i; i3++) {
                if (ceil == 0.0d) {
                    ceil = 0.0d;
                }
                this.mAxis.mEntries[i3] = (float) ceil;
                ceil += roundToNextSignificant;
            }
            labelCount = i;
        }
        if (roundToNextSignificant < 1.0d) {
            this.mAxis.mDecimals = (int) Math.ceil(-Math.log10(roundToNextSignificant));
        } else {
            this.mAxis.mDecimals = 0;
        }
        if (isCenterAxisLabelsEnabled != 0) {
            if (this.mAxis.mCenteredEntries.length < labelCount) {
                this.mAxis.mCenteredEntries = new float[labelCount];
            }
            float f5 = (this.mAxis.mEntries[1] - this.mAxis.mEntries[0]) / 2.0f;
            for (int i4 = 0; i4 < labelCount; i4++) {
                this.mAxis.mCenteredEntries[i4] = this.mAxis.mEntries[i4] + f5;
            }
        }
    }
}
