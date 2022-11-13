package com.github.mikephil.charting.buffer;

import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
/* loaded from: classes.dex */
public class ScatterBuffer extends AbstractBuffer<IScatterDataSet> {
    public ScatterBuffer(int i) {
        super(i);
    }

    protected void addForm(float f, float f2) {
        float[] fArr = this.buffer;
        int i = this.index;
        this.index = i + 1;
        fArr[i] = f;
        float[] fArr2 = this.buffer;
        int i2 = this.index;
        this.index = i2 + 1;
        fArr2[i2] = f2;
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [com.github.mikephil.charting.data.Entry] */
    @Override // com.github.mikephil.charting.buffer.AbstractBuffer
    public void feed(IScatterDataSet iScatterDataSet) {
        float entryCount = iScatterDataSet.getEntryCount() * this.phaseX;
        for (int i = 0; i < entryCount; i++) {
            ?? entryForIndex = iScatterDataSet.getEntryForIndex(i);
            addForm(entryForIndex.getX(), entryForIndex.getY() * this.phaseY);
        }
        reset();
    }
}
