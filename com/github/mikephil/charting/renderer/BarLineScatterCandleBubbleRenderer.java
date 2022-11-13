package com.github.mikephil.charting.renderer;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
/* loaded from: classes.dex */
public abstract class BarLineScatterCandleBubbleRenderer extends DataRenderer {
    protected XBounds mXBounds;

    public BarLineScatterCandleBubbleRenderer(ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mXBounds = new XBounds();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isInBoundsX(Entry entry, IBarLineScatterCandleBubbleDataSet iBarLineScatterCandleBubbleDataSet) {
        if (entry == null) {
            return false;
        }
        return entry != null && ((float) iBarLineScatterCandleBubbleDataSet.getEntryIndex(entry)) < ((float) iBarLineScatterCandleBubbleDataSet.getEntryCount()) * this.mAnimator.getPhaseX();
    }

    /* loaded from: classes.dex */
    protected class XBounds {
        public int max;
        public int min;
        public int range;

        protected XBounds() {
        }

        public void set(BarLineScatterCandleBubbleDataProvider barLineScatterCandleBubbleDataProvider, IBarLineScatterCandleBubbleDataSet iBarLineScatterCandleBubbleDataSet) {
            float max = Math.max(0.0f, Math.min(1.0f, BarLineScatterCandleBubbleRenderer.this.mAnimator.getPhaseX()));
            float lowestVisibleX = barLineScatterCandleBubbleDataProvider.getLowestVisibleX();
            float highestVisibleX = barLineScatterCandleBubbleDataProvider.getHighestVisibleX();
            T entryForXPos = iBarLineScatterCandleBubbleDataSet.getEntryForXPos(lowestVisibleX, DataSet.Rounding.DOWN);
            T entryForXPos2 = iBarLineScatterCandleBubbleDataSet.getEntryForXPos(highestVisibleX, DataSet.Rounding.UP);
            this.min = iBarLineScatterCandleBubbleDataSet.getEntryIndex(entryForXPos);
            int entryIndex = iBarLineScatterCandleBubbleDataSet.getEntryIndex(entryForXPos2);
            this.max = entryIndex;
            this.range = (int) ((entryIndex - this.min) * max);
        }
    }
}
