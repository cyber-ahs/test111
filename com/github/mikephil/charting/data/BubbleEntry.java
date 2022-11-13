package com.github.mikephil.charting.data;
/* loaded from: classes.dex */
public class BubbleEntry extends Entry {
    private float mSize;

    public BubbleEntry(float f, float f2, float f3) {
        super(f, f2);
        this.mSize = f3;
    }

    public BubbleEntry(float f, float f2, float f3, Object obj) {
        super(f, f2, obj);
        this.mSize = f3;
    }

    @Override // com.github.mikephil.charting.data.Entry
    public BubbleEntry copy() {
        return new BubbleEntry(getX(), getY(), this.mSize, getData());
    }

    public float getSize() {
        return this.mSize;
    }

    public void setSize(float f) {
        this.mSize = f;
    }
}
