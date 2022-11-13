package com.google.android.gms.common.config;

import android.content.Context;
import android.os.Binder;
import android.os.StrictMode;
import android.util.Log;
import java.util.HashSet;
/* loaded from: classes.dex */
public abstract class GservicesValue<T> {
    private static final Object sLock = new Object();
    private static zza zzbl;
    private static int zzbm;
    private static Context zzbn;
    private static HashSet<String> zzbo;
    protected final String mKey;
    protected final T zzbp;
    private T zzbq = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface zza {
        Long getLong(String str, Long l);

        String getString(String str, String str2);

        Boolean zza(String str, Boolean bool);

        Float zza(String str, Float f);

        Integer zza(String str, Integer num);
    }

    public static boolean isInitialized() {
        synchronized (sLock) {
        }
        return false;
    }

    protected abstract T zzd(String str);

    private static boolean zzi() {
        synchronized (sLock) {
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GservicesValue(String str, T t) {
        this.mKey = str;
        this.zzbp = t;
    }

    public void override(T t) {
        Log.w("GservicesValue", "GservicesValue.override(): test should probably call initForTests() first");
        this.zzbq = t;
        synchronized (sLock) {
            zzi();
        }
    }

    public void resetOverride() {
        this.zzbq = null;
    }

    public final T get() {
        T t = this.zzbq;
        if (t != null) {
            return t;
        }
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        Object obj = sLock;
        synchronized (obj) {
        }
        synchronized (obj) {
            zzbo = null;
            zzbn = null;
            try {
            } catch (SecurityException unused) {
                long clearCallingIdentity = Binder.clearCallingIdentity();
                T zzd = zzd(this.mKey);
                Binder.restoreCallingIdentity(clearCallingIdentity);
                return zzd;
            } finally {
                StrictMode.setThreadPolicy(allowThreadDiskReads);
            }
        }
        return zzd(this.mKey);
    }

    @Deprecated
    public final T getBinderSafe() {
        return get();
    }

    public static GservicesValue<Boolean> value(String str, boolean z) {
        return new com.google.android.gms.common.config.zza(str, Boolean.valueOf(z));
    }

    public static GservicesValue<Long> value(String str, Long l) {
        return new zzb(str, l);
    }

    public static GservicesValue<Integer> value(String str, Integer num) {
        return new zzc(str, num);
    }

    public static GservicesValue<Float> value(String str, Float f) {
        return new zzd(str, f);
    }

    public static GservicesValue<String> value(String str, String str2) {
        return new zze(str, str2);
    }
}
