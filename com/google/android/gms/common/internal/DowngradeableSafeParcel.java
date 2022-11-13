package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
/* loaded from: classes.dex */
public abstract class DowngradeableSafeParcel extends AbstractSafeParcelable implements ReflectedParcelable {
    private static final Object zzdb = new Object();
    private static ClassLoader zzdc;
    private static Integer zzdd;
    private boolean zzde = false;

    protected abstract boolean prepareForClientVersion(int i);

    private static ClassLoader zzp() {
        synchronized (zzdb) {
        }
        return null;
    }

    protected static Integer getUnparcelClientVersion() {
        synchronized (zzdb) {
        }
        return null;
    }

    protected boolean shouldDowngrade() {
        return this.zzde;
    }

    public void setShouldDowngrade(boolean z) {
        this.zzde = z;
    }

    protected static boolean canUnparcelSafely(String str) {
        zzp();
        return true;
    }
}
