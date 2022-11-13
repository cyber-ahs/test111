package com.github.mikephil.charting.data;

import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.renderer.scatter.ShapeRenderer;
import com.github.mikephil.charting.renderer.scatter.SquareShapeRenderer;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ShapeRendererHandler;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class ScatterDataSet extends LineScatterCandleRadarDataSet<Entry> implements IScatterDataSet {
    private int mScatterShapeHoleColor;
    private float mScatterShapeHoleRadius;
    protected ShapeRenderer mShapeRenderer;
    private float mShapeSize;

    public ScatterDataSet(List<Entry> list, String str) {
        super(list, str);
        this.mShapeSize = 15.0f;
        this.mShapeRenderer = new SquareShapeRenderer();
        this.mScatterShapeHoleRadius = 0.0f;
        this.mScatterShapeHoleColor = ColorTemplate.COLOR_NONE;
    }

    @Override // com.github.mikephil.charting.data.DataSet
    public DataSet<Entry> copy() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.mValues.size(); i++) {
            arrayList.add(((Entry) this.mValues.get(i)).copy());
        }
        ScatterDataSet scatterDataSet = new ScatterDataSet(arrayList, getLabel());
        scatterDataSet.mDrawValues = this.mDrawValues;
        scatterDataSet.mValueColors = this.mValueColors;
        scatterDataSet.mColors = this.mColors;
        scatterDataSet.mShapeSize = this.mShapeSize;
        scatterDataSet.mShapeRenderer = this.mShapeRenderer;
        scatterDataSet.mScatterShapeHoleRadius = this.mScatterShapeHoleRadius;
        scatterDataSet.mScatterShapeHoleColor = this.mScatterShapeHoleColor;
        scatterDataSet.mHighlightLineWidth = this.mHighlightLineWidth;
        scatterDataSet.mHighLightColor = this.mHighLightColor;
        scatterDataSet.mHighlightDashPathEffect = this.mHighlightDashPathEffect;
        return scatterDataSet;
    }

    public void setScatterShapeSize(float f) {
        this.mShapeSize = f;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IScatterDataSet
    public float getScatterShapeSize() {
        return this.mShapeSize;
    }

    public void setScatterShape(ScatterChart.ScatterShape scatterShape) {
        this.mShapeRenderer = new ShapeRendererHandler().getShapeRenderer(scatterShape);
    }

    public void setShapeRenderer(ShapeRenderer shapeRenderer) {
        this.mShapeRenderer = shapeRenderer;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IScatterDataSet
    public ShapeRenderer getShapeRenderer() {
        return this.mShapeRenderer;
    }

    public void setScatterShapeHoleRadius(float f) {
        this.mScatterShapeHoleRadius = f;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IScatterDataSet
    public float getScatterShapeHoleRadius() {
        return this.mScatterShapeHoleRadius;
    }

    public void setScatterShapeHoleColor(int i) {
        this.mScatterShapeHoleColor = i;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IScatterDataSet
    public int getScatterShapeHoleColor() {
        return this.mScatterShapeHoleColor;
    }
}
