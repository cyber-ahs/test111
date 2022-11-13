package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import java.util.List;
/* loaded from: classes.dex */
public class ScatterData extends BarLineScatterCandleBubbleData<IScatterDataSet> {
    public ScatterData() {
    }

    public ScatterData(List<IScatterDataSet> list) {
        super(list);
    }

    public ScatterData(IScatterDataSet... iScatterDataSetArr) {
        super(iScatterDataSetArr);
    }

    public float getGreatestShapeSize() {
        float f = 0.0f;
        for (int i = 0; i < this.mDataSets.size(); i++) {
            float scatterShapeSize = ((IScatterDataSet) this.mDataSets.get(i)).getScatterShapeSize();
            if (scatterShapeSize > f) {
                f = scatterShapeSize;
            }
        }
        return f;
    }
}
