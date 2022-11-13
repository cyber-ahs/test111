package com.google.firebase.iid;

import android.text.TextUtils;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.IOException;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzba {
    private final zzaw zzaj;
    private int zzdl = 0;
    private final Map<Integer, TaskCompletionSource<Void>> zzdm = new ArrayMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzba(zzaw zzawVar) {
        this.zzaj = zzawVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized Task<Void> zza(String str) {
        String zzak;
        TaskCompletionSource<Void> taskCompletionSource;
        synchronized (this.zzaj) {
            zzak = this.zzaj.zzak();
            zzaw zzawVar = this.zzaj;
            StringBuilder sb = new StringBuilder(String.valueOf(zzak).length() + 1 + String.valueOf(str).length());
            sb.append(zzak);
            sb.append(",");
            sb.append(str);
            zzawVar.zzf(sb.toString());
        }
        taskCompletionSource = new TaskCompletionSource<>();
        this.zzdm.put(Integer.valueOf(this.zzdl + (TextUtils.isEmpty(zzak) ? 0 : zzak.split(",").length - 1)), taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized boolean zzaq() {
        return zzar() != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x000c, code lost:
        if (com.google.firebase.iid.FirebaseInstanceId.zzl() == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x000e, code lost:
        android.util.Log.d("FirebaseInstanceId", "topic sync succeeded");
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0016, code lost:
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean zzc(FirebaseInstanceId firebaseInstanceId) {
        TaskCompletionSource<Void> remove;
        while (true) {
            synchronized (this) {
                String zzar = zzar();
                if (zzar == null) {
                    break;
                } else if (!zza(firebaseInstanceId, zzar)) {
                    return false;
                } else {
                    synchronized (this) {
                        remove = this.zzdm.remove(Integer.valueOf(this.zzdl));
                        zzk(zzar);
                        this.zzdl++;
                    }
                    if (remove != null) {
                        remove.setResult(null);
                    }
                }
            }
        }
    }

    private final String zzar() {
        String zzak;
        synchronized (this.zzaj) {
            zzak = this.zzaj.zzak();
        }
        if (TextUtils.isEmpty(zzak)) {
            return null;
        }
        String[] split = zzak.split(",");
        if (split.length <= 1 || TextUtils.isEmpty(split[1])) {
            return null;
        }
        return split[1];
    }

    private final synchronized boolean zzk(String str) {
        synchronized (this.zzaj) {
            String zzak = this.zzaj.zzak();
            String valueOf = String.valueOf(str);
            if (zzak.startsWith(valueOf.length() != 0 ? ",".concat(valueOf) : new String(","))) {
                String valueOf2 = String.valueOf(str);
                this.zzaj.zzf(zzak.substring((valueOf2.length() != 0 ? ",".concat(valueOf2) : new String(",")).length()));
                return true;
            }
            return false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0035, code lost:
        if (r4 == 1) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0038, code lost:
        r7.zzc(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003f, code lost:
        if (com.google.firebase.iid.FirebaseInstanceId.zzl() == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0041, code lost:
        android.util.Log.d("FirebaseInstanceId", "unsubscribe operation succeeded");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean zza(FirebaseInstanceId firebaseInstanceId, String str) {
        String[] split = str.split("!");
        if (split.length == 2) {
            String str2 = split[0];
            String str3 = split[1];
            char c = 65535;
            try {
                int hashCode = str2.hashCode();
                if (hashCode != 83) {
                    if (hashCode == 85 && str2.equals("U")) {
                        c = 1;
                    }
                } else if (str2.equals("S")) {
                    c = 0;
                }
                firebaseInstanceId.zzb(str3);
                if (FirebaseInstanceId.zzl()) {
                    Log.d("FirebaseInstanceId", "subscribe operation succeeded");
                }
            } catch (IOException e) {
                String valueOf = String.valueOf(e.getMessage());
                Log.e("FirebaseInstanceId", valueOf.length() != 0 ? "Topic sync failed: ".concat(valueOf) : new String("Topic sync failed: "));
                return false;
            }
        }
        return true;
    }
}
