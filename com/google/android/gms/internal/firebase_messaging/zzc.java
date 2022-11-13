package com.google.android.gms.internal.firebase_messaging;

import java.io.PrintStream;
/* loaded from: classes.dex */
public final class zzc {
    private static final zzd zzb;
    private static final int zzc;

    /* loaded from: classes.dex */
    static final class zza extends zzd {
        zza() {
        }

        @Override // com.google.android.gms.internal.firebase_messaging.zzd
        public final void zza(Throwable th, Throwable th2) {
        }
    }

    public static void zza(Throwable th, Throwable th2) {
        zzb.zza(th, th2);
    }

    private static Integer zza() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0068  */
    static {
        Integer num;
        zzd zzaVar;
        try {
            num = zza();
        } catch (Throwable th) {
            th = th;
            num = null;
        }
        if (num != null) {
            try {
            } catch (Throwable th2) {
                th = th2;
                PrintStream printStream = System.err;
                String name = zza.class.getName();
                StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 132);
                sb.append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ");
                sb.append(name);
                sb.append("will be used. The error is: ");
                printStream.println(sb.toString());
                th.printStackTrace(System.err);
                zzaVar = new zza();
                zzb = zzaVar;
                zzc = num != null ? num.intValue() : 1;
            }
            if (num.intValue() >= 19) {
                zzaVar = new zzh();
                zzb = zzaVar;
                zzc = num != null ? num.intValue() : 1;
            }
        }
        if (!Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic")) {
            zzaVar = new zzg();
        } else {
            zzaVar = new zza();
        }
        zzb = zzaVar;
        zzc = num != null ? num.intValue() : 1;
    }
}
