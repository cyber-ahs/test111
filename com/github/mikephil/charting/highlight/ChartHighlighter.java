package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.MPPointD;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class ChartHighlighter<T extends BarLineScatterCandleBubbleDataProvider> implements Highlighter {
    protected T mChart;
    protected List<Highlight> mHighlightBuffer = new ArrayList();

    public ChartHighlighter(T t) {
        this.mChart = t;
    }

    @Override // com.github.mikephil.charting.highlight.Highlighter
    public Highlight getHighlight(float f, float f2) {
        MPPointD valsForTouch = getValsForTouch(f, f2);
        MPPointD.recycleInstance(valsForTouch);
        return getHighlightForX((float) valsForTouch.x, f, f2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MPPointD getValsForTouch(float f, float f2) {
        return this.mChart.getTransformer(YAxis.AxisDependency.LEFT).getValuesByTouchPoint(f, f2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Highlight getHighlightForX(float f, float f2, float f3) {
        List<Highlight> highlightsAtXPos = getHighlightsAtXPos(f, f2, f3);
        if (highlightsAtXPos.isEmpty()) {
            return null;
        }
        return getClosestHighlightByPixel(highlightsAtXPos, f2, f3, getMinimumDistance(highlightsAtXPos, f3, YAxis.AxisDependency.LEFT) < getMinimumDistance(highlightsAtXPos, f3, YAxis.AxisDependency.RIGHT) ? YAxis.AxisDependency.LEFT : YAxis.AxisDependency.RIGHT, this.mChart.getMaxHighlightDistance());
    }

    protected float getMinimumDistance(List<Highlight> list, float f, YAxis.AxisDependency axisDependency) {
        float f2 = Float.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            Highlight highlight = list.get(i);
            if (highlight.getAxis() == axisDependency) {
                float abs = Math.abs(getHighlightPos(highlight) - f);
                if (abs < f2) {
                    f2 = abs;
                }
            }
        }
        return f2;
    }

    protected float getHighlightPos(Highlight highlight) {
        return highlight.getYPx();
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.github.mikephil.charting.interfaces.datasets.IDataSet] */
    protected List<Highlight> getHighlightsAtXPos(float f, float f2, float f3) {
        Highlight buildHighlight;
        this.mHighlightBuffer.clear();
        BarLineScatterCandleBubbleData data = getData();
        if (data == null) {
            return this.mHighlightBuffer;
        }
        int dataSetCount = data.getDataSetCount();
        for (int i = 0; i < dataSetCount; i++) {
            ?? dataSetByIndex = data.getDataSetByIndex(i);
            if (dataSetByIndex.isHighlightEnabled() && (buildHighlight = buildHighlight(dataSetByIndex, i, f, DataSet.Rounding.CLOSEST)) != null) {
                this.mHighlightBuffer.add(buildHighlight);
            }
        }
        return this.mHighlightBuffer;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Highlight buildHighlight(IDataSet iDataSet, int i, float f, DataSet.Rounding rounding) {
        Entry entryForXPos = iDataSet.getEntryForXPos(f, rounding);
        if (entryForXPos == null) {
            return null;
        }
        MPPointD pixelsForValues = this.mChart.getTransformer(iDataSet.getAxisDependency()).getPixelsForValues(entryForXPos.getX(), entryForXPos.getY());
        return new Highlight(entryForXPos.getX(), entryForXPos.getY(), (float) pixelsForValues.x, (float) pixelsForValues.y, i, iDataSet.getAxisDependency());
    }

    public Highlight getClosestHighlightByPixel(List<Highlight> list, float f, float f2, YAxis.AxisDependency axisDependency, float f3) {
        Highlight highlight = null;
        for (int i = 0; i < list.size(); i++) {
            Highlight highlight2 = list.get(i);
            if (axisDependency == null || highlight2.getAxis() == axisDependency) {
                float distance = getDistance(f, f2, highlight2.getXPx(), highlight2.getYPx());
                if (distance < f3) {
                    highlight = highlight2;
                    f3 = distance;
                }
            }
        }
        return highlight;
    }

    protected float getDistance(float f, float f2, float f3, float f4) {
        return (float) Math.hypot(f - f3, f2 - f4);
    }

    protected BarLineScatterCandleBubbleData getData() {
        return this.mChart.getData();
    }
}