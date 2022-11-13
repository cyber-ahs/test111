package com.google.android.gms.common.util;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;
/* loaded from: classes.dex */
public final class zza {
    private static long zzgt;
    private static final IntentFilter filter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    private static float zzgu = Float.NaN;

    public static int zzg(Context context) {
        int i;
        if (context == null || context.getApplicationContext() == null) {
            return -1;
        }
        Intent registerReceiver = context.getApplicationContext().registerReceiver(null, filter);
        int i2 = ((registerReceiver == null ? 0 : registerReceiver.getIntExtra("plugged", 0)) & 7) != 0 ? 1 : 0;
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            return -1;
        }
        if (PlatformVersion.isAtLeastKitKatWatch()) {
            i = powerManager.isInteractive();
        } else {
            i = powerManager.isScreenOn();
        }
        return (i << 1) | i2;
    }

    public static synchronized float zzh(Context context) {
        synchronized (zza.class) {
            if (SystemClock.elapsedRealtime() - zzgt < 60000 && !Float.isNaN(zzgu)) {
                return zzgu;
            }
            Intent registerReceiver = context.getApplicationContext().registerReceiver(null, filter);
            if (registerReceiver != null) {
                zzgu = registerReceiver.getIntExtra("level", -1) / registerReceiver.getIntExtra("scale", -1);
            }
            zzgt = SystemClock.elapsedRealtime();
            return zzgu;
        }
    }
}
