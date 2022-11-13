package com.github.mikephil.charting.components;

import android.graphics.Paint;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class Legend extends ComponentBase {
    protected ArrayList<FSize> calculatedLineSizesForCalculateDimensions;
    protected Paint.FontMetrics fontMetricsForCalculateDimensions;
    private boolean isCalculatedLineSizesArrayListResized;
    private Boolean[] mCalculatedLabelBreakPoints;
    private FSize[] mCalculatedLabelSizes;
    private FSize[] mCalculatedLineSizes;
    private int[] mColors;
    private LegendDirection mDirection;
    private boolean mDrawInside;
    private int[] mExtraColors;
    private String[] mExtraLabels;
    private float mFormSize;
    private float mFormToTextSpace;
    private LegendHorizontalAlignment mHorizontalAlignment;
    private boolean mIsLegendCustom;
    private String[] mLabels;
    private float mMaxSizePercent;
    public float mNeededHeight;
    public float mNeededWidth;
    private LegendOrientation mOrientation;
    private LegendForm mShape;
    private float mStackSpace;
    public float mTextHeightMax;
    public float mTextWidthMax;
    private LegendVerticalAlignment mVerticalAlignment;
    private boolean mWordWrapEnabled;
    private float mXEntrySpace;
    private float mYEntrySpace;

    /* loaded from: classes.dex */
    public enum LegendDirection {
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT
    }

    /* loaded from: classes.dex */
    public enum LegendForm {
        SQUARE,
        CIRCLE,
        LINE
    }

    /* loaded from: classes.dex */
    public enum LegendHorizontalAlignment {
        LEFT,
        CENTER,
        RIGHT
    }

    /* loaded from: classes.dex */
    public enum LegendOrientation {
        HORIZONTAL,
        VERTICAL
    }

    /* loaded from: classes.dex */
    public enum LegendPosition {
        RIGHT_OF_CHART,
        RIGHT_OF_CHART_CENTER,
        RIGHT_OF_CHART_INSIDE,
        LEFT_OF_CHART,
        LEFT_OF_CHART_CENTER,
        LEFT_OF_CHART_INSIDE,
        BELOW_CHART_LEFT,
        BELOW_CHART_RIGHT,
        BELOW_CHART_CENTER,
        ABOVE_CHART_LEFT,
        ABOVE_CHART_RIGHT,
        ABOVE_CHART_CENTER,
        PIECHART_CENTER
    }

    /* loaded from: classes.dex */
    public enum LegendVerticalAlignment {
        TOP,
        CENTER,
        BOTTOM
    }

    public Legend() {
        this.mIsLegendCustom = false;
        this.mHorizontalAlignment = LegendHorizontalAlignment.LEFT;
        this.mVerticalAlignment = LegendVerticalAlignment.BOTTOM;
        this.mOrientation = LegendOrientation.HORIZONTAL;
        this.mDrawInside = false;
        this.mDirection = LegendDirection.LEFT_TO_RIGHT;
        this.mShape = LegendForm.SQUARE;
        this.mFormSize = 8.0f;
        this.mXEntrySpace = 6.0f;
        this.mYEntrySpace = 0.0f;
        this.mFormToTextSpace = 5.0f;
        this.mStackSpace = 3.0f;
        this.mMaxSizePercent = 0.95f;
        this.mNeededWidth = 0.0f;
        this.mNeededHeight = 0.0f;
        this.mTextHeightMax = 0.0f;
        this.mTextWidthMax = 0.0f;
        this.mWordWrapEnabled = false;
        this.isCalculatedLineSizesArrayListResized = true;
        this.mCalculatedLabelSizes = new FSize[0];
        this.mCalculatedLabelBreakPoints = new Boolean[0];
        this.mCalculatedLineSizes = new FSize[0];
        this.fontMetricsForCalculateDimensions = new Paint.FontMetrics();
        this.calculatedLineSizesForCalculateDimensions = new ArrayList<>();
        this.mFormSize = Utils.convertDpToPixel(8.0f);
        this.mXEntrySpace = Utils.convertDpToPixel(6.0f);
        this.mYEntrySpace = Utils.convertDpToPixel(0.0f);
        this.mFormToTextSpace = Utils.convertDpToPixel(5.0f);
        this.mTextSize = Utils.convertDpToPixel(10.0f);
        this.mStackSpace = Utils.convertDpToPixel(3.0f);
        this.mXOffset = Utils.convertDpToPixel(5.0f);
        this.mYOffset = Utils.convertDpToPixel(3.0f);
    }

    public Legend(int[] iArr, String[] strArr) {
        this();
        if (iArr == null || strArr == null) {
            throw new IllegalArgumentException("colors array or labels array is NULL");
        }
        if (iArr.length != strArr.length) {
            throw new IllegalArgumentException("colors array and labels array need to be of same size");
        }
        this.mColors = iArr;
        this.mLabels = strArr;
    }

    public Legend(List<Integer> list, List<String> list2) {
        this();
        if (list == null || list2 == null) {
            throw new IllegalArgumentException("colors array or labels array is NULL");
        }
        if (list.size() != list2.size()) {
            throw new IllegalArgumentException("colors array and labels array need to be of same size");
        }
        setComputedColors(list);
        setComputedLabels(list2);
    }

    public void setComputedColors(List<Integer> list) {
        if (this.mColors != null) {
            int size = list.size();
            int[] iArr = this.mColors;
            if (size == iArr.length) {
                Utils.copyIntegers(list, iArr);
                return;
            }
        }
        this.mColors = Utils.convertIntegers(list);
    }

    public void setComputedLabels(List<String> list) {
        String[] strArr = this.mLabels;
        if (strArr != null && strArr.length == list.size()) {
            Utils.copyStrings(list, this.mLabels);
        } else {
            this.mLabels = Utils.convertStrings(list);
        }
    }

    public float getMaximumEntryWidth(Paint paint) {
        float f = 0.0f;
        int i = 0;
        while (true) {
            String[] strArr = this.mLabels;
            if (i < strArr.length) {
                if (strArr[i] != null) {
                    float calcTextWidth = Utils.calcTextWidth(paint, strArr[i]);
                    if (calcTextWidth > f) {
                        f = calcTextWidth;
                    }
                }
                i++;
            } else {
                return f + this.mFormSize + this.mFormToTextSpace;
            }
        }
    }

    public float getMaximumEntryHeight(Paint paint) {
        float f = 0.0f;
        int i = 0;
        while (true) {
            String[] strArr = this.mLabels;
            if (i >= strArr.length) {
                return f;
            }
            if (strArr[i] != null) {
                float calcTextHeight = Utils.calcTextHeight(paint, strArr[i]);
                if (calcTextHeight > f) {
                    f = calcTextHeight;
                }
            }
            i++;
        }
    }

    public int[] getColors() {
        return this.mColors;
    }

    public String[] getLabels() {
        return this.mLabels;
    }

    public String getLabel(int i) {
        return this.mLabels[i];
    }

    public int[] getExtraColors() {
        return this.mExtraColors;
    }

    public String[] getExtraLabels() {
        return this.mExtraLabels;
    }

    public void setExtra(List<Integer> list, List<String> list2) {
        int[] iArr = this.mExtraColors;
        if (iArr != null && iArr.length == list.size()) {
            Utils.copyIntegers(list, this.mExtraColors);
        } else {
            this.mExtraColors = Utils.convertIntegers(list);
        }
        String[] strArr = this.mExtraLabels;
        if (strArr != null && strArr.length == list2.size()) {
            Utils.copyStrings(list2, this.mExtraLabels);
        } else {
            this.mExtraLabels = Utils.convertStrings(list2);
        }
    }

    public void setExtra(int[] iArr, String[] strArr) {
        this.mExtraColors = iArr;
        this.mExtraLabels = strArr;
    }

    public void setCustom(int[] iArr, String[] strArr) {
        if (iArr.length != strArr.length) {
            throw new IllegalArgumentException("colors array and labels array need to be of same size");
        }
        this.mLabels = strArr;
        this.mColors = iArr;
        this.mIsLegendCustom = true;
    }

    public void setCustom(List<Integer> list, List<String> list2) {
        if (list.size() != list2.size()) {
            throw new IllegalArgumentException("colors array and labels array need to be of same size");
        }
        setComputedColors(list);
        setComputedLabels(list2);
        this.mIsLegendCustom = true;
    }

    public void resetCustom() {
        this.mIsLegendCustom = false;
    }

    public boolean isLegendCustom() {
        return this.mIsLegendCustom;
    }

    public LegendPosition getPosition() {
        if (this.mOrientation == LegendOrientation.VERTICAL && this.mHorizontalAlignment == LegendHorizontalAlignment.CENTER && this.mVerticalAlignment == LegendVerticalAlignment.CENTER) {
            return LegendPosition.PIECHART_CENTER;
        }
        return this.mOrientation == LegendOrientation.HORIZONTAL ? this.mVerticalAlignment == LegendVerticalAlignment.TOP ? this.mHorizontalAlignment == LegendHorizontalAlignment.LEFT ? LegendPosition.ABOVE_CHART_LEFT : this.mHorizontalAlignment == LegendHorizontalAlignment.RIGHT ? LegendPosition.ABOVE_CHART_RIGHT : LegendPosition.ABOVE_CHART_CENTER : this.mHorizontalAlignment == LegendHorizontalAlignment.LEFT ? LegendPosition.BELOW_CHART_LEFT : this.mHorizontalAlignment == LegendHorizontalAlignment.RIGHT ? LegendPosition.BELOW_CHART_RIGHT : LegendPosition.BELOW_CHART_CENTER : this.mHorizontalAlignment == LegendHorizontalAlignment.LEFT ? (this.mVerticalAlignment == LegendVerticalAlignment.TOP && this.mDrawInside) ? LegendPosition.LEFT_OF_CHART_INSIDE : this.mVerticalAlignment == LegendVerticalAlignment.CENTER ? LegendPosition.LEFT_OF_CHART_CENTER : LegendPosition.LEFT_OF_CHART : (this.mVerticalAlignment == LegendVerticalAlignment.TOP && this.mDrawInside) ? LegendPosition.RIGHT_OF_CHART_INSIDE : this.mVerticalAlignment == LegendVerticalAlignment.CENTER ? LegendPosition.RIGHT_OF_CHART_CENTER : LegendPosition.RIGHT_OF_CHART;
    }

    public void setPosition(LegendPosition legendPosition) {
        switch (AnonymousClass1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendPosition[legendPosition.ordinal()]) {
            case 1:
            case 2:
            case 3:
                this.mHorizontalAlignment = LegendHorizontalAlignment.LEFT;
                this.mVerticalAlignment = legendPosition == LegendPosition.LEFT_OF_CHART_CENTER ? LegendVerticalAlignment.CENTER : LegendVerticalAlignment.TOP;
                this.mOrientation = LegendOrientation.VERTICAL;
                break;
            case 4:
            case 5:
            case 6:
                this.mHorizontalAlignment = LegendHorizontalAlignment.RIGHT;
                this.mVerticalAlignment = legendPosition == LegendPosition.RIGHT_OF_CHART_CENTER ? LegendVerticalAlignment.CENTER : LegendVerticalAlignment.TOP;
                this.mOrientation = LegendOrientation.VERTICAL;
                break;
            case 7:
            case 8:
            case 9:
                this.mHorizontalAlignment = legendPosition == LegendPosition.ABOVE_CHART_LEFT ? LegendHorizontalAlignment.LEFT : legendPosition == LegendPosition.ABOVE_CHART_RIGHT ? LegendHorizontalAlignment.RIGHT : LegendHorizontalAlignment.CENTER;
                this.mVerticalAlignment = LegendVerticalAlignment.TOP;
                this.mOrientation = LegendOrientation.HORIZONTAL;
                break;
            case 10:
            case 11:
            case 12:
                this.mHorizontalAlignment = legendPosition == LegendPosition.BELOW_CHART_LEFT ? LegendHorizontalAlignment.LEFT : legendPosition == LegendPosition.BELOW_CHART_RIGHT ? LegendHorizontalAlignment.RIGHT : LegendHorizontalAlignment.CENTER;
                this.mVerticalAlignment = LegendVerticalAlignment.BOTTOM;
                this.mOrientation = LegendOrientation.HORIZONTAL;
                break;
            case 13:
                this.mHorizontalAlignment = LegendHorizontalAlignment.CENTER;
                this.mVerticalAlignment = LegendVerticalAlignment.CENTER;
                this.mOrientation = LegendOrientation.VERTICAL;
                break;
        }
        this.mDrawInside = legendPosition == LegendPosition.LEFT_OF_CHART_INSIDE || legendPosition == LegendPosition.RIGHT_OF_CHART_INSIDE;
    }

    public LegendHorizontalAlignment getHorizontalAlignment() {
        return this.mHorizontalAlignment;
    }

    public void setHorizontalAlignment(LegendHorizontalAlignment legendHorizontalAlignment) {
        this.mHorizontalAlignment = legendHorizontalAlignment;
    }

    public LegendVerticalAlignment getVerticalAlignment() {
        return this.mVerticalAlignment;
    }

    public void setVerticalAlignment(LegendVerticalAlignment legendVerticalAlignment) {
        this.mVerticalAlignment = legendVerticalAlignment;
    }

    public LegendOrientation getOrientation() {
        return this.mOrientation;
    }

    public void setOrientation(LegendOrientation legendOrientation) {
        this.mOrientation = legendOrientation;
    }

    public boolean isDrawInsideEnabled() {
        return this.mDrawInside;
    }

    public void setDrawInside(boolean z) {
        this.mDrawInside = z;
    }

    public LegendDirection getDirection() {
        return this.mDirection;
    }

    public void setDirection(LegendDirection legendDirection) {
        this.mDirection = legendDirection;
    }

    public LegendForm getForm() {
        return this.mShape;
    }

    public void setForm(LegendForm legendForm) {
        this.mShape = legendForm;
    }

    public void setFormSize(float f) {
        this.mFormSize = Utils.convertDpToPixel(f);
    }

    public float getFormSize() {
        return this.mFormSize;
    }

    public float getXEntrySpace() {
        return this.mXEntrySpace;
    }

    public void setXEntrySpace(float f) {
        this.mXEntrySpace = Utils.convertDpToPixel(f);
    }

    public float getYEntrySpace() {
        return this.mYEntrySpace;
    }

    public void setYEntrySpace(float f) {
        this.mYEntrySpace = Utils.convertDpToPixel(f);
    }

    public float getFormToTextSpace() {
        return this.mFormToTextSpace;
    }

    public void setFormToTextSpace(float f) {
        this.mFormToTextSpace = Utils.convertDpToPixel(f);
    }

    public float getStackSpace() {
        return this.mStackSpace;
    }

    public void setStackSpace(float f) {
        this.mStackSpace = f;
    }

    public float getFullWidth(Paint paint) {
        float f;
        float f2 = 0.0f;
        int i = 0;
        while (true) {
            String[] strArr = this.mLabels;
            if (i >= strArr.length) {
                return f2;
            }
            if (strArr[i] != null) {
                if (this.mColors[i] != 1122868) {
                    f2 += this.mFormSize + this.mFormToTextSpace;
                }
                f2 += Utils.calcTextWidth(paint, strArr[i]);
                if (i < this.mLabels.length - 1) {
                    f = this.mXEntrySpace;
                    f2 += f;
                    i++;
                } else {
                    i++;
                }
            } else {
                f2 += this.mFormSize;
                if (i < strArr.length - 1) {
                    f = this.mStackSpace;
                    f2 += f;
                    i++;
                } else {
                    i++;
                }
            }
        }
    }

    public float getFullHeight(Paint paint) {
        float f = 0.0f;
        int i = 0;
        while (true) {
            String[] strArr = this.mLabels;
            if (i >= strArr.length) {
                return f;
            }
            if (strArr[i] != null) {
                f += Utils.calcTextHeight(paint, strArr[i]);
                if (i < this.mLabels.length - 1) {
                    f += this.mYEntrySpace;
                }
            }
            i++;
        }
    }

    public void setWordWrapEnabled(boolean z) {
        this.mWordWrapEnabled = z;
    }

    public boolean isWordWrapEnabled() {
        return this.mWordWrapEnabled;
    }

    public float getMaxSizePercent() {
        return this.mMaxSizePercent;
    }

    public void setMaxSizePercent(float f) {
        this.mMaxSizePercent = f;
    }

    public FSize[] getCalculatedLabelSizes() {
        return this.mCalculatedLabelSizes;
    }

    public Boolean[] getCalculatedLabelBreakPoints() {
        return this.mCalculatedLabelBreakPoints;
    }

    public FSize[] getCalculatedLineSizes() {
        if (this.mCalculatedLineSizes == null || this.isCalculatedLineSizesArrayListResized) {
            ArrayList<FSize> arrayList = this.calculatedLineSizesForCalculateDimensions;
            this.mCalculatedLineSizes = (FSize[]) arrayList.toArray(new FSize[arrayList.size()]);
            this.isCalculatedLineSizesArrayListResized = false;
        }
        return this.mCalculatedLineSizes;
    }

    public void calculateDimensions(Paint paint, ViewPortHandler viewPortHandler) {
        FSize[] fSizeArr;
        float f;
        float f2;
        this.mTextWidthMax = getMaximumEntryWidth(paint);
        this.mTextHeightMax = getMaximumEntryHeight(paint);
        int i = AnonymousClass1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation[this.mOrientation.ordinal()];
        int i2 = ColorTemplate.COLOR_SKIP;
        boolean z = false;
        int i3 = 1;
        if (i == 1) {
            float lineHeight = Utils.getLineHeight(paint, this.fontMetricsForCalculateDimensions);
            int length = this.mLabels.length;
            float f3 = 0.0f;
            float f4 = 0.0f;
            float f5 = 0.0f;
            boolean z2 = false;
            for (int i4 = 0; i4 < length; i4++) {
                boolean z3 = this.mColors[i4] != 1122868;
                if (!z2) {
                    f5 = 0.0f;
                }
                if (z3) {
                    if (z2) {
                        f5 += this.mStackSpace;
                    }
                    f5 += this.mFormSize;
                }
                if (this.mLabels[i4] != null) {
                    if (z3 && !z2) {
                        f5 += this.mFormToTextSpace;
                    } else if (z2) {
                        f3 = Math.max(f3, f5);
                        f4 += this.mYEntrySpace + lineHeight;
                        f5 = 0.0f;
                        z2 = false;
                    }
                    f5 += Utils.calcTextWidth(paint, this.mLabels[i4]);
                    if (i4 < length - 1) {
                        f4 += this.mYEntrySpace + lineHeight;
                    }
                } else {
                    f5 += this.mFormSize;
                    if (i4 < length - 1) {
                        f5 += this.mStackSpace;
                    }
                    z2 = true;
                }
                f3 = Math.max(f3, f5);
            }
            this.mNeededWidth = f3;
            this.mNeededHeight = f4;
        } else if (i == 2) {
            int length2 = this.mLabels.length;
            float lineHeight2 = Utils.getLineHeight(paint, this.fontMetricsForCalculateDimensions);
            float lineSpacing = Utils.getLineSpacing(paint, this.fontMetricsForCalculateDimensions) + this.mYEntrySpace;
            float contentWidth = viewPortHandler.contentWidth() * this.mMaxSizePercent;
            FSize[] fSizeArr2 = this.mCalculatedLabelSizes;
            if (fSizeArr2.length != length2) {
                FSize[] fSizeArr3 = new FSize[length2];
                int length3 = fSizeArr2.length;
                for (int i5 = 0; i5 < length3 && i5 < length2; i5++) {
                    fSizeArr3[i5] = this.mCalculatedLabelSizes[i5];
                }
                while (length3 > length2) {
                    length3--;
                    FSize.recycleInstance(this.mCalculatedLabelSizes[length3]);
                }
                this.mCalculatedLabelSizes = fSizeArr3;
            }
            if (this.mCalculatedLabelBreakPoints.length != length2) {
                this.mCalculatedLabelBreakPoints = new Boolean[length2];
            }
            ArrayList<FSize> arrayList = this.calculatedLineSizesForCalculateDimensions;
            FSize.recycleInstances(arrayList);
            arrayList.clear();
            int i6 = -1;
            int i7 = 0;
            int i8 = 0;
            int i9 = -1;
            float f6 = 0.0f;
            float f7 = 0.0f;
            int i10 = 0;
            float f8 = 0.0f;
            while (i7 < length2) {
                boolean z4 = this.mColors[i7] != i2;
                this.mCalculatedLabelBreakPoints[i8] = Boolean.valueOf(z);
                i8 += i3;
                float f9 = i9 == i6 ? 0.0f : f7 + this.mStackSpace;
                String[] strArr = this.mLabels;
                if (strArr[i7] != null) {
                    FSize[] fSizeArr4 = this.mCalculatedLabelSizes;
                    if (fSizeArr4[i10] == null) {
                        fSizeArr4[i10] = Utils.calcTextSize(paint, strArr[i7]);
                    } else {
                        Utils.calcTextSize(paint, strArr[i7], fSizeArr4[i10]);
                    }
                    FSize fSize = this.mCalculatedLabelSizes[i10];
                    i10++;
                    f = f9 + (z4 ? this.mFormToTextSpace + this.mFormSize : 0.0f) + fSize.width;
                } else {
                    FSize[] fSizeArr5 = this.mCalculatedLabelSizes;
                    if (fSizeArr5[i10] == null) {
                        fSizeArr5[i10] = FSize.getInstance(0.0f, 0.0f);
                    } else {
                        fSizeArr5[i10].width = 0.0f;
                        this.mCalculatedLabelSizes[i10].height = 0.0f;
                    }
                    i10++;
                    f = f9 + (z4 ? this.mFormSize : 0.0f);
                    if (i9 == i6) {
                        i9 = i7;
                    }
                }
                if (this.mLabels[i7] != null || i7 == length2 - 1) {
                    float f10 = f8;
                    float f11 = f10 == 0.0f ? 0.0f : this.mXEntrySpace;
                    if (!this.mWordWrapEnabled || f10 == 0.0f || contentWidth - f10 >= f11 + f) {
                        i6 = -1;
                        f2 = f10 + f11 + f;
                    } else {
                        arrayList.add(FSize.getInstance(f10, lineHeight2));
                        float max = Math.max(f6, f10);
                        i6 = -1;
                        this.mCalculatedLabelBreakPoints[i9 > -1 ? i9 : i7] = true;
                        f6 = max;
                        f2 = f;
                    }
                    if (i7 == length2 - 1) {
                        arrayList.add(FSize.getInstance(f2, lineHeight2));
                        f6 = Math.max(f6, f2);
                    }
                    f8 = f2;
                }
                if (this.mLabels[i7] != null) {
                    i9 = -1;
                }
                i7++;
                f7 = f;
                i2 = ColorTemplate.COLOR_SKIP;
                z = false;
                i3 = 1;
            }
            if (arrayList.size() == this.mCalculatedLineSizes.length) {
                int i11 = 0;
                while (true) {
                    FSize[] fSizeArr6 = this.mCalculatedLineSizes;
                    if (i11 >= fSizeArr6.length) {
                        break;
                    }
                    fSizeArr6[i11] = arrayList.get(i11);
                    i11++;
                }
            } else {
                this.isCalculatedLineSizesArrayListResized = true;
            }
            this.mNeededWidth = f6;
            this.mNeededHeight = (lineHeight2 * fSizeArr.length) + (lineSpacing * (this.mCalculatedLineSizes.length == 0 ? 0 : fSizeArr.length - 1));
        }
    }

    /* renamed from: com.github.mikephil.charting.components.Legend$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation;
        static final /* synthetic */ int[] $SwitchMap$com$github$mikephil$charting$components$Legend$LegendPosition;

        static {
            int[] iArr = new int[LegendOrientation.values().length];
            $SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation = iArr;
            try {
                iArr[LegendOrientation.VERTICAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation[LegendOrientation.HORIZONTAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[LegendPosition.values().length];
            $SwitchMap$com$github$mikephil$charting$components$Legend$LegendPosition = iArr2;
            try {
                iArr2[LegendPosition.LEFT_OF_CHART.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendPosition[LegendPosition.LEFT_OF_CHART_INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendPosition[LegendPosition.LEFT_OF_CHART_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendPosition[LegendPosition.RIGHT_OF_CHART.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendPosition[LegendPosition.RIGHT_OF_CHART_INSIDE.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendPosition[LegendPosition.RIGHT_OF_CHART_CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendPosition[LegendPosition.ABOVE_CHART_LEFT.ordinal()] = 7;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendPosition[LegendPosition.ABOVE_CHART_CENTER.ordinal()] = 8;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendPosition[LegendPosition.ABOVE_CHART_RIGHT.ordinal()] = 9;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendPosition[LegendPosition.BELOW_CHART_LEFT.ordinal()] = 10;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendPosition[LegendPosition.BELOW_CHART_CENTER.ordinal()] = 11;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendPosition[LegendPosition.BELOW_CHART_RIGHT.ordinal()] = 12;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendPosition[LegendPosition.PIECHART_CENTER.ordinal()] = 13;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }
}
