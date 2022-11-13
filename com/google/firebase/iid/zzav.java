package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import androidx.legacy.content.WakefulBroadcastReceiver;
import java.util.ArrayDeque;
import java.util.Queue;
/* loaded from: classes.dex */
public final class zzav {
    private static zzav zzcx;
    private final SimpleArrayMap<String, String> zzcy = new SimpleArrayMap<>();
    private Boolean zzcz = null;
    final Queue<Intent> zzda = new ArrayDeque();
    private final Queue<Intent> zzdb = new ArrayDeque();

    public static synchronized zzav zzai() {
        zzav zzavVar;
        synchronized (zzav.class) {
            if (zzcx == null) {
                zzcx = new zzav();
            }
            zzavVar = zzcx;
        }
        return zzavVar;
    }

    private zzav() {
    }

    public static PendingIntent zza(Context context, int i, Intent intent, int i2) {
        return PendingIntent.getBroadcast(context, i, zza(context, "com.google.firebase.MESSAGING_EVENT", intent), 1073741824);
    }

    public static void zzb(Context context, Intent intent) {
        context.sendBroadcast(zza(context, "com.google.firebase.INSTANCE_ID_EVENT", intent));
    }

    public static void zzc(Context context, Intent intent) {
        context.sendBroadcast(zza(context, "com.google.firebase.MESSAGING_EVENT", intent));
    }

    private static Intent zza(Context context, String str, Intent intent) {
        Intent intent2 = new Intent(context, FirebaseInstanceIdReceiver.class);
        intent2.setAction(str);
        intent2.putExtra("wrapped_intent", intent);
        return intent2;
    }

    public final Intent zzaj() {
        return this.zzdb.poll();
    }

    public final int zzb(Context context, String str, Intent intent) {
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String valueOf = String.valueOf(str);
            Log.d("FirebaseInstanceId", valueOf.length() != 0 ? "Starting service: ".concat(valueOf) : new String("Starting service: "));
        }
        str.hashCode();
        if (str.equals("com.google.firebase.INSTANCE_ID_EVENT")) {
            this.zzda.offer(intent);
        } else if (str.equals("com.google.firebase.MESSAGING_EVENT")) {
            this.zzdb.offer(intent);
        } else {
            String valueOf2 = String.valueOf(str);
            Log.w("FirebaseInstanceId", valueOf2.length() != 0 ? "Unknown service action: ".concat(valueOf2) : new String("Unknown service action: "));
            return 500;
        }
        Intent intent2 = new Intent(str);
        intent2.setPackage(context.getPackageName());
        return zzd(context, intent2);
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00db A[Catch: IllegalStateException -> 0x0110, SecurityException -> 0x0138, TryCatch #4 {IllegalStateException -> 0x0110, SecurityException -> 0x0138, blocks: (B:42:0x00d7, B:44:0x00db, B:47:0x00e4, B:48:0x00ea, B:50:0x00f2, B:53:0x0104, B:51:0x00f7), top: B:71:0x00d7 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00f2 A[Catch: IllegalStateException -> 0x0110, SecurityException -> 0x0138, TryCatch #4 {IllegalStateException -> 0x0110, SecurityException -> 0x0138, blocks: (B:42:0x00d7, B:44:0x00db, B:47:0x00e4, B:48:0x00ea, B:50:0x00f2, B:53:0x0104, B:51:0x00f7), top: B:71:0x00d7 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00f7 A[Catch: IllegalStateException -> 0x0110, SecurityException -> 0x0138, TryCatch #4 {IllegalStateException -> 0x0110, SecurityException -> 0x0138, blocks: (B:42:0x00d7, B:44:0x00db, B:47:0x00e4, B:48:0x00ea, B:50:0x00f2, B:53:0x0104, B:51:0x00f7), top: B:71:0x00d7 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0104 A[Catch: IllegalStateException -> 0x0110, SecurityException -> 0x0138, TRY_LEAVE, TryCatch #4 {IllegalStateException -> 0x0110, SecurityException -> 0x0138, blocks: (B:42:0x00d7, B:44:0x00db, B:47:0x00e4, B:48:0x00ea, B:50:0x00f2, B:53:0x0104, B:51:0x00f7), top: B:71:0x00d7 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x010e A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final int zzd(Context context, Intent intent) {
        String str;
        ComponentName startService;
        synchronized (this.zzcy) {
            str = this.zzcy.get(intent.getAction());
        }
        try {
            if (str == null) {
                ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
                if (resolveService == null || resolveService.serviceInfo == null) {
                    Log.e("FirebaseInstanceId", "Failed to resolve target intent service, skipping classname enforcement");
                } else {
                    ServiceInfo serviceInfo = resolveService.serviceInfo;
                    if (!context.getPackageName().equals(serviceInfo.packageName) || serviceInfo.name == null) {
                        String str2 = serviceInfo.packageName;
                        String str3 = serviceInfo.name;
                        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 94 + String.valueOf(str3).length());
                        sb.append("Error resolving target intent service, skipping classname enforcement. Resolved service was: ");
                        sb.append(str2);
                        sb.append("/");
                        sb.append(str3);
                        Log.e("FirebaseInstanceId", sb.toString());
                    } else {
                        str = serviceInfo.name;
                        if (str.startsWith(".")) {
                            String valueOf = String.valueOf(context.getPackageName());
                            String valueOf2 = String.valueOf(str);
                            str = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                        }
                        synchronized (this.zzcy) {
                            this.zzcy.put(intent.getAction(), str);
                        }
                    }
                }
                if (this.zzcz == null) {
                    this.zzcz = Boolean.valueOf(context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0);
                }
                if (!this.zzcz.booleanValue()) {
                    startService = WakefulBroadcastReceiver.startWakefulService(context, intent);
                } else {
                    startService = context.startService(intent);
                    Log.d("FirebaseInstanceId", "Missing wake lock permission, service start may be delayed");
                }
                if (startService != null) {
                    Log.e("FirebaseInstanceId", "Error while delivering the message: ServiceIntent not found.");
                    return 404;
                }
                return -1;
            }
            if (this.zzcz == null) {
            }
            if (!this.zzcz.booleanValue()) {
            }
            if (startService != null) {
            }
        } catch (IllegalStateException e) {
            String valueOf3 = String.valueOf(e);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf3).length() + 45);
            sb2.append("Failed to start service while in background: ");
            sb2.append(valueOf3);
            Log.e("FirebaseInstanceId", sb2.toString());
            return 402;
        } catch (SecurityException e2) {
            Log.e("FirebaseInstanceId", "Error while delivering the message to the serviceIntent", e2);
            return 401;
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String valueOf4 = String.valueOf(str);
            Log.d("FirebaseInstanceId", valueOf4.length() != 0 ? "Restricting intent to a specific service: ".concat(valueOf4) : new String("Restricting intent to a specific service: "));
        }
        intent.setClassName(context.getPackageName(), str);
    }
}
