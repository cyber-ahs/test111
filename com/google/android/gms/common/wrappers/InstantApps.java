package com.google.android.gms.common.wrappers;

import android.content.Context;
import com.google.android.gms.common.util.PlatformVersion;
/* loaded from: classes.dex */
public class InstantApps {
    private static Context zzht;
    private static Boolean zzhu;

    public static synchronized boolean isInstantApp(Context context) {
        Boolean bool;
        synchronized (InstantApps.class) {
            Context applicationContext = context.getApplicationContext();
            Context context2 = zzht;
            if (context2 != null && (bool = zzhu) != null && context2 == applicationContext) {
                return bool.booleanValue();
            }
            zzhu = null;
            if (PlatformVersion.isAtLeastO()) {
                zzhu = Boolean.valueOf(applicationContext.getPackageManager().isInstantApp());
            } else {
                try {
                    context.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
                    zzhu = true;
                } catch (ClassNotFoundException unused) {
                    zzhu = false;
                }
            }
            zzht = applicationContext;
            return zzhu.booleanValue();
        }
    }
}
