package com.github.mikephil.charting.formatter;

import java.text.Format;
import java.util.ArrayList;
import java.util.HashMap;
/* loaded from: classes.dex */
public class FormattedStringCache {
    protected Format mFormat;

    public Format getFormat() {
        return this.mFormat;
    }

    public FormattedStringCache(Format format) {
        this.mFormat = format;
    }

    /* loaded from: classes.dex */
    public static class Generic<K, V> extends FormattedStringCache {
        private HashMap<K, String> mCachedStringsHashMap;
        private HashMap<K, V> mCachedValuesHashMap;

        public Generic(Format format) {
            super(format);
            this.mCachedStringsHashMap = new HashMap<>();
            this.mCachedValuesHashMap = new HashMap<>();
        }

        public String getFormattedValue(V v, K k) {
            if (!this.mCachedValuesHashMap.containsKey(k)) {
                this.mCachedStringsHashMap.put(k, this.mFormat.format(v));
                this.mCachedValuesHashMap.put(k, v);
            }
            if (!v.equals(this.mCachedValuesHashMap.get(k))) {
                this.mCachedStringsHashMap.put(k, this.mFormat.format(v));
                this.mCachedValuesHashMap.put(k, v);
            }
            return this.mCachedStringsHashMap.get(k);
        }
    }

    /* loaded from: classes.dex */
    public static class PrimIntFloat extends FormattedStringCache {
        private ArrayList<String> cachedStrings;
        private ArrayList<Float> cachedValues;

        public PrimIntFloat(Format format) {
            super(format);
            this.cachedValues = new ArrayList<>();
            this.cachedStrings = new ArrayList<>();
        }

        public String getFormattedValue(float f, int i) {
            boolean z;
            boolean z2 = false;
            if (this.cachedValues.size() <= i) {
                for (int i2 = i; i2 >= 0; i2--) {
                    if (i2 == 0) {
                        this.cachedValues.add(Float.valueOf(f));
                        this.cachedStrings.add("");
                    } else {
                        this.cachedValues.add(Float.valueOf(Float.NaN));
                        this.cachedStrings.add("");
                    }
                }
                z = false;
            } else {
                z = true;
            }
            if (z) {
                Float f2 = this.cachedValues.get(i);
                if (f2 != null && f2.floatValue() == f) {
                    z2 = true;
                }
                z = z2;
            }
            if (!z) {
                this.cachedValues.set(i, Float.valueOf(f));
                this.cachedStrings.set(i, this.mFormat.format(Float.valueOf(f)));
            }
            return this.cachedStrings.get(i);
        }
    }

    /* loaded from: classes.dex */
    public static class PrimFloat extends FormattedStringCache {
        private ArrayList<String> cachedStrings;
        private ArrayList<Float> cachedValues;

        public PrimFloat(Format format) {
            super(format);
            this.cachedValues = new ArrayList<>();
            this.cachedStrings = new ArrayList<>();
        }

        public String getFormattedValue(float f) {
            int size = this.cachedValues.size();
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= size) {
                    i = -1;
                    break;
                } else if (this.cachedValues.get(i).floatValue() == f) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            if (!z) {
                this.cachedValues.add(Float.valueOf(f));
                this.cachedStrings.add(this.mFormat.format(Float.valueOf(f)));
                i = this.cachedValues.size() - 1;
            }
            return this.cachedStrings.get(i);
        }
    }

    /* loaded from: classes.dex */
    public static class PrimDouble extends FormattedStringCache {
        private ArrayList<String> cachedStrings;
        private ArrayList<Double> cachedValues;

        public PrimDouble(Format format) {
            super(format);
            this.cachedValues = new ArrayList<>();
            this.cachedStrings = new ArrayList<>();
        }

        public String getFormattedValue(double d) {
            int size = this.cachedValues.size();
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= size) {
                    i = -1;
                    break;
                } else if (this.cachedValues.get(i).doubleValue() == d) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            if (!z) {
                this.cachedValues.add(Double.valueOf(d));
                this.cachedStrings.add(this.mFormat.format(Double.valueOf(d)));
                i = this.cachedValues.size() - 1;
            }
            return this.cachedStrings.get(i);
        }
    }
}
