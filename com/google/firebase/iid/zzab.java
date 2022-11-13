package com.google.firebase.iid;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
/* loaded from: classes.dex */
public final class zzab {
    private static zzab zzbt;
    private final ScheduledExecutorService zzbu;
    private zzad zzbv = new zzad(this);
    private int zzbw = 1;
    private final Context zzx;

    public static synchronized zzab zzc(Context context) {
        zzab zzabVar;
        synchronized (zzab.class) {
            if (zzbt == null) {
                zzbt = new zzab(context, Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("MessengerIpcClient")));
            }
            zzabVar = zzbt;
        }
        return zzabVar;
    }

    private zzab(Context context, ScheduledExecutorService scheduledExecutorService) {
        this.zzbu = scheduledExecutorService;
        this.zzx = context.getApplicationContext();
    }

    public final Task<Void> zza(int i, Bundle bundle) {
        return zza(new zzaj(zzx(), 2, bundle));
    }

    public final Task<Bundle> zzb(int i, Bundle bundle) {
        return zza(new zzam(zzx(), 1, bundle));
    }

    private final synchronized <T> Task<T> zza(zzak<T> zzakVar) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(zzakVar);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 9);
            sb.append("Queueing ");
            sb.append(valueOf);
            Log.d("MessengerIpcClient", sb.toString());
        }
        if (!this.zzbv.zzb(zzakVar)) {
            zzad zzadVar = new zzad(this);
            this.zzbv = zzadVar;
            zzadVar.zzb(zzakVar);
        }
        return zzakVar.zzcg.getTask();
    }

    private final synchronized int zzx() {
        int i;
        i = this.zzbw;
        this.zzbw = i + 1;
        return i;
    }
}
