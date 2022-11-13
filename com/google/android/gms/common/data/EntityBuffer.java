package com.google.android.gms.common.data;

import java.util.ArrayList;
/* loaded from: classes.dex */
public abstract class EntityBuffer<T> extends AbstractDataBuffer<T> {
    private boolean zamd;
    private ArrayList<Integer> zame;

    protected EntityBuffer(DataHolder dataHolder) {
        super(dataHolder);
        this.zamd = false;
    }

    protected String getChildDataMarkerColumn() {
        return null;
    }

    protected abstract T getEntry(int i, int i2);

    protected abstract String getPrimaryDataMarkerColumn();

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0063, code lost:
        if (r6.mDataHolder.getString(r4, r7, r3) == null) goto L16;
     */
    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final T get(int i) {
        int intValue;
        int intValue2;
        zacb();
        int zah = zah(i);
        int i2 = 0;
        if (i >= 0 && i != this.zame.size()) {
            if (i == this.zame.size() - 1) {
                intValue = this.mDataHolder.getCount();
                intValue2 = this.zame.get(i).intValue();
            } else {
                intValue = this.zame.get(i + 1).intValue();
                intValue2 = this.zame.get(i).intValue();
            }
            int i3 = intValue - intValue2;
            if (i3 == 1) {
                int zah2 = zah(i);
                int windowIndex = this.mDataHolder.getWindowIndex(zah2);
                String childDataMarkerColumn = getChildDataMarkerColumn();
                if (childDataMarkerColumn != null) {
                }
            }
            i2 = i3;
        }
        return getEntry(zah, i2);
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    public int getCount() {
        zacb();
        return this.zame.size();
    }

    private final void zacb() {
        synchronized (this) {
            if (!this.zamd) {
                int count = this.mDataHolder.getCount();
                ArrayList<Integer> arrayList = new ArrayList<>();
                this.zame = arrayList;
                if (count > 0) {
                    arrayList.add(0);
                    String primaryDataMarkerColumn = getPrimaryDataMarkerColumn();
                    String string = this.mDataHolder.getString(primaryDataMarkerColumn, 0, this.mDataHolder.getWindowIndex(0));
                    for (int i = 1; i < count; i++) {
                        int windowIndex = this.mDataHolder.getWindowIndex(i);
                        String string2 = this.mDataHolder.getString(primaryDataMarkerColumn, i, windowIndex);
                        if (string2 == null) {
                            StringBuilder sb = new StringBuilder(String.valueOf(primaryDataMarkerColumn).length() + 78);
                            sb.append("Missing value for markerColumn: ");
                            sb.append(primaryDataMarkerColumn);
                            sb.append(", at row: ");
                            sb.append(i);
                            sb.append(", for window: ");
                            sb.append(windowIndex);
                            throw new NullPointerException(sb.toString());
                        }
                        if (!string2.equals(string)) {
                            this.zame.add(Integer.valueOf(i));
                            string = string2;
                        }
                    }
                }
                this.zamd = true;
            }
        }
    }

    private final int zah(int i) {
        if (i < 0 || i >= this.zame.size()) {
            StringBuilder sb = new StringBuilder(53);
            sb.append("Position ");
            sb.append(i);
            sb.append(" is out of bounds for this buffer");
            throw new IllegalArgumentException(sb.toString());
        }
        return this.zame.get(i).intValue();
    }
}
