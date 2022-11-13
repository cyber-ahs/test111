package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public class LegendRenderer extends Renderer {
    protected ArrayList<Integer> colorsForComputeLegend;
    protected Paint.FontMetrics fontMetricsForRenderLegent;
    protected ArrayList<String> labelsForComputeLegend;
    protected Legend mLegend;
    protected Paint mLegendFormPaint;
    protected Paint mLegendLabelPaint;

    public LegendRenderer(ViewPortHandler viewPortHandler, Legend legend) {
        super(viewPortHandler);
        this.labelsForComputeLegend = new ArrayList<>(16);
        this.colorsForComputeLegend = new ArrayList<>(16);
        this.fontMetricsForRenderLegent = new Paint.FontMetrics();
        this.mLegend = legend;
        Paint paint = new Paint(1);
        this.mLegendLabelPaint = paint;
        paint.setTextSize(Utils.convertDpToPixel(9.0f));
        this.mLegendLabelPaint.setTextAlign(Paint.Align.LEFT);
        Paint paint2 = new Paint(1);
        this.mLegendFormPaint = paint2;
        paint2.setStyle(Paint.Style.FILL);
        this.mLegendFormPaint.setStrokeWidth(3.0f);
    }

    public Paint getLabelPaint() {
        return this.mLegendLabelPaint;
    }

    public Paint getFormPaint() {
        return this.mLegendFormPaint;
    }

    /* JADX WARN: Type inference failed for: r4v3, types: [com.github.mikephil.charting.interfaces.datasets.IDataSet] */
    /* JADX WARN: Type inference failed for: r7v6, types: [com.github.mikephil.charting.interfaces.datasets.IDataSet] */
    public void computeLegend(ChartData<?> chartData) {
        if (!this.mLegend.isLegendCustom()) {
            ArrayList<String> arrayList = this.labelsForComputeLegend;
            ArrayList<Integer> arrayList2 = this.colorsForComputeLegend;
            arrayList.clear();
            arrayList2.clear();
            for (int i = 0; i < chartData.getDataSetCount(); i++) {
                ?? dataSetByIndex = chartData.getDataSetByIndex(i);
                List<Integer> colors = dataSetByIndex.getColors();
                int entryCount = dataSetByIndex.getEntryCount();
                if (dataSetByIndex instanceof IBarDataSet) {
                    IBarDataSet iBarDataSet = (IBarDataSet) dataSetByIndex;
                    if (iBarDataSet.isStacked()) {
                        String[] stackLabels = iBarDataSet.getStackLabels();
                        for (int i2 = 0; i2 < colors.size() && i2 < iBarDataSet.getStackSize(); i2++) {
                            arrayList.add(stackLabels[i2 % stackLabels.length]);
                            arrayList2.add(colors.get(i2));
                        }
                        if (iBarDataSet.getLabel() != null) {
                            arrayList2.add(Integer.valueOf((int) ColorTemplate.COLOR_SKIP));
                            arrayList.add(iBarDataSet.getLabel());
                        }
                    }
                }
                if (dataSetByIndex instanceof IPieDataSet) {
                    IPieDataSet iPieDataSet = (IPieDataSet) dataSetByIndex;
                    for (int i3 = 0; i3 < colors.size() && i3 < entryCount; i3++) {
                        arrayList.add(iPieDataSet.getEntryForIndex(i3).getLabel());
                        arrayList2.add(colors.get(i3));
                    }
                    if (iPieDataSet.getLabel() != null) {
                        arrayList2.add(Integer.valueOf((int) ColorTemplate.COLOR_SKIP));
                        arrayList.add(iPieDataSet.getLabel());
                    }
                } else {
                    if (dataSetByIndex instanceof ICandleDataSet) {
                        ICandleDataSet iCandleDataSet = (ICandleDataSet) dataSetByIndex;
                        if (iCandleDataSet.getDecreasingColor() != 1122867) {
                            arrayList2.add(Integer.valueOf(iCandleDataSet.getDecreasingColor()));
                            arrayList2.add(Integer.valueOf(iCandleDataSet.getIncreasingColor()));
                            arrayList.add(null);
                            arrayList.add(dataSetByIndex.getLabel());
                        }
                    }
                    for (int i4 = 0; i4 < colors.size() && i4 < entryCount; i4++) {
                        if (i4 < colors.size() - 1 && i4 < entryCount - 1) {
                            arrayList.add(null);
                        } else {
                            arrayList.add(chartData.getDataSetByIndex(i).getLabel());
                        }
                        arrayList2.add(colors.get(i4));
                    }
                }
            }
            if (this.mLegend.getExtraColors() != null && this.mLegend.getExtraLabels() != null) {
                for (int i5 : this.mLegend.getExtraColors()) {
                    arrayList2.add(Integer.valueOf(i5));
                }
                Collections.addAll(arrayList, this.mLegend.getExtraLabels());
            }
            this.mLegend.setComputedColors(arrayList2);
            this.mLegend.setComputedLabels(arrayList);
        }
        Typeface typeface = this.mLegend.getTypeface();
        if (typeface != null) {
            this.mLegendLabelPaint.setTypeface(typeface);
        }
        this.mLegendLabelPaint.setTextSize(this.mLegend.getTextSize());
        this.mLegendLabelPaint.setColor(this.mLegend.getTextColor());
        this.mLegend.calculateDimensions(this.mLegendLabelPaint, this.mViewPortHandler);
    }

    public void renderLegend(Canvas canvas) {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        int i;
        Legend.LegendHorizontalAlignment legendHorizontalAlignment;
        FSize[] fSizeArr;
        float f6;
        Canvas canvas2;
        float f7;
        float f8;
        float f9;
        float f10;
        float contentTop;
        Legend.LegendDirection legendDirection;
        float f11;
        float f12;
        float f13;
        float f14;
        float contentBottom;
        float contentRight;
        float contentLeft;
        double d;
        if (this.mLegend.isEnabled()) {
            Typeface typeface = this.mLegend.getTypeface();
            if (typeface != null) {
                this.mLegendLabelPaint.setTypeface(typeface);
            }
            this.mLegendLabelPaint.setTextSize(this.mLegend.getTextSize());
            this.mLegendLabelPaint.setColor(this.mLegend.getTextColor());
            float lineHeight = Utils.getLineHeight(this.mLegendLabelPaint, this.fontMetricsForRenderLegent);
            float lineSpacing = Utils.getLineSpacing(this.mLegendLabelPaint, this.fontMetricsForRenderLegent) + this.mLegend.getYEntrySpace();
            float calcTextHeight = lineHeight - (Utils.calcTextHeight(this.mLegendLabelPaint, "ABC") / 2.0f);
            String[] labels = this.mLegend.getLabels();
            int[] colors = this.mLegend.getColors();
            float formToTextSpace = this.mLegend.getFormToTextSpace();
            float xEntrySpace = this.mLegend.getXEntrySpace();
            Legend.LegendOrientation orientation = this.mLegend.getOrientation();
            Legend.LegendHorizontalAlignment horizontalAlignment = this.mLegend.getHorizontalAlignment();
            Legend.LegendVerticalAlignment verticalAlignment = this.mLegend.getVerticalAlignment();
            Legend.LegendDirection direction = this.mLegend.getDirection();
            float formSize = this.mLegend.getFormSize();
            float stackSpace = this.mLegend.getStackSpace();
            float yOffset = this.mLegend.getYOffset();
            float xOffset = this.mLegend.getXOffset();
            float f15 = stackSpace;
            int i2 = AnonymousClass1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment[horizontalAlignment.ordinal()];
            float f16 = xEntrySpace;
            if (i2 != 1) {
                if (i2 == 2) {
                    f = lineHeight;
                    f2 = formToTextSpace;
                    if (orientation == Legend.LegendOrientation.VERTICAL) {
                        contentRight = this.mViewPortHandler.getChartWidth();
                    } else {
                        contentRight = this.mViewPortHandler.contentRight();
                    }
                    f3 = contentRight - xOffset;
                    if (direction == Legend.LegendDirection.LEFT_TO_RIGHT) {
                        f3 -= this.mLegend.mNeededWidth;
                    }
                } else if (i2 != 3) {
                    f = lineHeight;
                    f2 = formToTextSpace;
                    xOffset = 0.0f;
                } else {
                    if (orientation == Legend.LegendOrientation.VERTICAL) {
                        contentLeft = this.mViewPortHandler.getChartWidth() / 2.0f;
                    } else {
                        contentLeft = this.mViewPortHandler.contentLeft() + (this.mViewPortHandler.contentWidth() / 2.0f);
                    }
                    f3 = contentLeft + (direction == Legend.LegendDirection.LEFT_TO_RIGHT ? xOffset : -xOffset);
                    if (orientation == Legend.LegendOrientation.VERTICAL) {
                        double d2 = f3;
                        if (direction == Legend.LegendDirection.LEFT_TO_RIGHT) {
                            f2 = formToTextSpace;
                            double d3 = -this.mLegend.mNeededWidth;
                            Double.isNaN(d3);
                            f = lineHeight;
                            double d4 = xOffset;
                            Double.isNaN(d4);
                            d = (d3 / 2.0d) + d4;
                        } else {
                            f = lineHeight;
                            f2 = formToTextSpace;
                            double d5 = this.mLegend.mNeededWidth;
                            Double.isNaN(d5);
                            double d6 = xOffset;
                            Double.isNaN(d6);
                            d = (d5 / 2.0d) - d6;
                        }
                        Double.isNaN(d2);
                        f3 = (float) (d2 + d);
                    } else {
                        f = lineHeight;
                        f2 = formToTextSpace;
                    }
                }
                xOffset = f3;
            } else {
                f = lineHeight;
                f2 = formToTextSpace;
                if (orientation != Legend.LegendOrientation.VERTICAL) {
                    xOffset += this.mViewPortHandler.contentLeft();
                }
                if (direction == Legend.LegendDirection.RIGHT_TO_LEFT) {
                    f3 = this.mLegend.mNeededWidth + xOffset;
                    xOffset = f3;
                }
            }
            int i3 = AnonymousClass1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation[orientation.ordinal()];
            int i4 = ColorTemplate.COLOR_SKIP;
            if (i3 != 1) {
                if (i3 != 2) {
                    return;
                }
                int i5 = AnonymousClass1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment[verticalAlignment.ordinal()];
                if (i5 == 1) {
                    contentTop = (horizontalAlignment == Legend.LegendHorizontalAlignment.CENTER ? 0.0f : this.mViewPortHandler.contentTop()) + yOffset;
                } else if (i5 == 2) {
                    if (horizontalAlignment == Legend.LegendHorizontalAlignment.CENTER) {
                        contentBottom = this.mViewPortHandler.getChartHeight();
                    } else {
                        contentBottom = this.mViewPortHandler.contentBottom();
                    }
                    contentTop = contentBottom - (this.mLegend.mNeededHeight + yOffset);
                } else {
                    contentTop = i5 != 3 ? 0.0f : ((this.mViewPortHandler.getChartHeight() / 2.0f) - (this.mLegend.mNeededHeight / 2.0f)) + this.mLegend.getYOffset();
                }
                float f17 = contentTop;
                boolean z = false;
                int i6 = 0;
                float f18 = 0.0f;
                while (i6 < labels.length) {
                    Boolean valueOf = Boolean.valueOf(colors[i6] != i4);
                    if (valueOf.booleanValue()) {
                        f12 = direction == Legend.LegendDirection.LEFT_TO_RIGHT ? xOffset + f18 : xOffset - (formSize - f18);
                        f11 = f15;
                        legendDirection = direction;
                        drawForm(canvas, f12, f17 + calcTextHeight, i6, this.mLegend);
                        if (legendDirection == Legend.LegendDirection.LEFT_TO_RIGHT) {
                            f12 += formSize;
                        }
                    } else {
                        legendDirection = direction;
                        f11 = f15;
                        f12 = xOffset;
                    }
                    if (labels[i6] != null) {
                        if (!valueOf.booleanValue() || z) {
                            f13 = f2;
                            if (z) {
                                f12 = xOffset;
                            }
                        } else {
                            if (legendDirection == Legend.LegendDirection.LEFT_TO_RIGHT) {
                                f14 = f2;
                                f13 = f14;
                            } else {
                                f13 = f2;
                                f14 = -f13;
                            }
                            f12 += f14;
                        }
                        if (legendDirection == Legend.LegendDirection.RIGHT_TO_LEFT) {
                            f12 -= Utils.calcTextWidth(this.mLegendLabelPaint, labels[i6]);
                        }
                        float f19 = f12;
                        if (!z) {
                            drawLabel(canvas, f19, f17 + f, labels[i6]);
                        } else {
                            f17 += f + lineSpacing;
                            drawLabel(canvas, f19, f17 + f, labels[i6]);
                        }
                        f17 += f + lineSpacing;
                        f18 = 0.0f;
                    } else {
                        f13 = f2;
                        f18 += formSize + f11;
                        z = true;
                    }
                    i6++;
                    f2 = f13;
                    direction = legendDirection;
                    f15 = f11;
                    i4 = ColorTemplate.COLOR_SKIP;
                }
                return;
            }
            float f20 = f2;
            FSize[] calculatedLineSizes = this.mLegend.getCalculatedLineSizes();
            FSize[] calculatedLabelSizes = this.mLegend.getCalculatedLabelSizes();
            Boolean[] calculatedLabelBreakPoints = this.mLegend.getCalculatedLabelBreakPoints();
            int i7 = AnonymousClass1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment[verticalAlignment.ordinal()];
            if (i7 != 1) {
                if (i7 == 2) {
                    yOffset = (this.mViewPortHandler.getChartHeight() - yOffset) - this.mLegend.mNeededHeight;
                } else {
                    yOffset = i7 != 3 ? 0.0f : yOffset + ((this.mViewPortHandler.getChartHeight() - this.mLegend.mNeededHeight) / 2.0f);
                }
            }
            int length = labels.length;
            float f21 = xOffset;
            int i8 = 0;
            int i9 = 0;
            while (i8 < length) {
                int i10 = length;
                if (i8 >= calculatedLabelBreakPoints.length || !calculatedLabelBreakPoints[i8].booleanValue()) {
                    f4 = f21;
                    f5 = yOffset;
                } else {
                    f5 = yOffset + f + lineSpacing;
                    f4 = xOffset;
                }
                if (f4 == xOffset && horizontalAlignment == Legend.LegendHorizontalAlignment.CENTER && i9 < calculatedLineSizes.length) {
                    f4 += (direction == Legend.LegendDirection.RIGHT_TO_LEFT ? calculatedLineSizes[i9].width : -calculatedLineSizes[i9].width) / 2.0f;
                    i9++;
                }
                int i11 = i9;
                boolean z2 = colors[i8] != 1122868;
                boolean z3 = labels[i8] == null;
                if (z2) {
                    if (direction == Legend.LegendDirection.RIGHT_TO_LEFT) {
                        f4 -= formSize;
                    }
                    float f22 = f4;
                    i = i8;
                    fSizeArr = calculatedLineSizes;
                    canvas2 = canvas;
                    f6 = xOffset;
                    f7 = f20;
                    legendHorizontalAlignment = horizontalAlignment;
                    drawForm(canvas, f22, f5 + calcTextHeight, i, this.mLegend);
                    f4 = direction == Legend.LegendDirection.LEFT_TO_RIGHT ? f22 + formSize : f22;
                } else {
                    i = i8;
                    legendHorizontalAlignment = horizontalAlignment;
                    fSizeArr = calculatedLineSizes;
                    f6 = xOffset;
                    canvas2 = canvas;
                    f7 = f20;
                }
                if (!z3) {
                    if (z2) {
                        f4 += direction == Legend.LegendDirection.RIGHT_TO_LEFT ? -f7 : f7;
                    }
                    if (direction == Legend.LegendDirection.RIGHT_TO_LEFT) {
                        f4 -= calculatedLabelSizes[i].width;
                    }
                    drawLabel(canvas2, f4, f5 + f, labels[i]);
                    if (direction == Legend.LegendDirection.LEFT_TO_RIGHT) {
                        f4 += calculatedLabelSizes[i].width;
                    }
                    if (direction == Legend.LegendDirection.RIGHT_TO_LEFT) {
                        f8 = f16;
                        f10 = -f8;
                    } else {
                        f8 = f16;
                        f10 = f8;
                    }
                    f9 = f4 + f10;
                } else {
                    f8 = f16;
                    f9 = f4 + (direction == Legend.LegendDirection.RIGHT_TO_LEFT ? -f15 : f15);
                }
                f16 = f8;
                i8 = i + 1;
                f20 = f7;
                yOffset = f5;
                i9 = i11;
                xOffset = f6;
                calculatedLineSizes = fSizeArr;
                horizontalAlignment = legendHorizontalAlignment;
                f21 = f9;
                length = i10;
            }
        }
    }

    protected void drawForm(Canvas canvas, float f, float f2, int i, Legend legend) {
        if (legend.getColors()[i] == 1122868) {
            return;
        }
        this.mLegendFormPaint.setColor(legend.getColors()[i]);
        float formSize = legend.getFormSize();
        float f3 = formSize / 2.0f;
        int i2 = AnonymousClass1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendForm[legend.getForm().ordinal()];
        if (i2 == 1) {
            canvas.drawCircle(f + f3, f2, f3, this.mLegendFormPaint);
        } else if (i2 == 2) {
            canvas.drawRect(f, f2 - f3, f + formSize, f2 + f3, this.mLegendFormPaint);
        } else if (i2 != 3) {
        } else {
            canvas.drawLine(f, f2, f + formSize, f2, this.mLegendFormPaint);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.github.mikephil.charting.renderer.LegendRenderer$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$github$mikephil$charting$components$Legend$LegendForm;
        static final /* synthetic */ int[] $SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment;
        static final /* synthetic */ int[] $SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation;
        static final /* synthetic */ int[] $SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment;

        static {
            int[] iArr = new int[Legend.LegendForm.values().length];
            $SwitchMap$com$github$mikephil$charting$components$Legend$LegendForm = iArr;
            try {
                iArr[Legend.LegendForm.CIRCLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendForm[Legend.LegendForm.SQUARE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendForm[Legend.LegendForm.LINE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[Legend.LegendOrientation.values().length];
            $SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation = iArr2;
            try {
                iArr2[Legend.LegendOrientation.HORIZONTAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation[Legend.LegendOrientation.VERTICAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr3 = new int[Legend.LegendVerticalAlignment.values().length];
            $SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment = iArr3;
            try {
                iArr3[Legend.LegendVerticalAlignment.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment[Legend.LegendVerticalAlignment.BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment[Legend.LegendVerticalAlignment.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr4 = new int[Legend.LegendHorizontalAlignment.values().length];
            $SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment = iArr4;
            try {
                iArr4[Legend.LegendHorizontalAlignment.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment[Legend.LegendHorizontalAlignment.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment[Legend.LegendHorizontalAlignment.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    protected void drawLabel(Canvas canvas, float f, float f2, String str) {
        canvas.drawText(str, f, f2, this.mLegendLabelPaint);
    }
}
