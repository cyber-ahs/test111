package com.github.mikephil.charting.data;

import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;
/* loaded from: classes.dex */
public class Entry extends BaseEntry implements Parcelable {
    public static final Parcelable.Creator<Entry> CREATOR = new Parcelable.Creator<Entry>() { // from class: com.github.mikephil.charting.data.Entry.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Entry createFromParcel(Parcel parcel) {
            return new Entry(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Entry[] newArray(int i) {
            return new Entry[i];
        }
    };
    private float x;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Entry() {
        this.x = 0.0f;
    }

    public Entry(float f, float f2) {
        super(f2);
        this.x = f;
    }

    public Entry(float f, float f2, Object obj) {
        super(f2, obj);
        this.x = f;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float f) {
        this.x = f;
    }

    public Entry copy() {
        return new Entry(this.x, getY(), getData());
    }

    public boolean equalTo(Entry entry) {
        return entry != null && entry.getData() == getData() && Math.abs(entry.x - this.x) <= 1.0E-6f && Math.abs(entry.getY() - getY()) <= 1.0E-6f;
    }

    public String toString() {
        return "Entry, x: " + this.x + " y (sum): " + getY();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.x);
        parcel.writeFloat(getY());
        if (getData() != null) {
            if (getData() instanceof Parcelable) {
                parcel.writeInt(1);
                parcel.writeParcelable((Parcelable) getData(), i);
                return;
            }
            throw new ParcelFormatException("Cannot parcel an Entry with non-parcelable data");
        }
        parcel.writeInt(0);
    }

    protected Entry(Parcel parcel) {
        this.x = 0.0f;
        this.x = parcel.readFloat();
        setY(parcel.readFloat());
        if (parcel.readInt() == 1) {
            setData(parcel.readParcelable(Object.class.getClassLoader()));
        }
    }
}
