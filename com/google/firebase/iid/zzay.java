package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.util.Log;
import java.io.IOException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzay implements Runnable {
    private final zzan zzan;
    private final zzba zzaq;
    private final long zzdh;
    private final PowerManager.WakeLock zzdi;
    private final FirebaseInstanceId zzdj;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzay(FirebaseInstanceId firebaseInstanceId, zzan zzanVar, zzba zzbaVar, long j) {
        this.zzdj = firebaseInstanceId;
        this.zzan = zzanVar;
        this.zzaq = zzbaVar;
        this.zzdh = j;
        PowerManager.WakeLock newWakeLock = ((PowerManager) getContext().getSystemService("power")).newWakeLock(1, "fiid-sync");
        this.zzdi = newWakeLock;
        newWakeLock.setReferenceCounted(false);
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzdi.acquire();
        try {
            this.zzdj.zza(true);
            if (!this.zzdj.zzn()) {
                this.zzdj.zza(false);
            } else if (!zzao()) {
                new zzaz(this).zzap();
            } else {
                if (zzam() && zzan() && this.zzaq.zzc(this.zzdj)) {
                    this.zzdj.zza(false);
                } else {
                    this.zzdj.zza(this.zzdh);
                }
            }
        } finally {
            this.zzdi.release();
        }
    }

    private final boolean zzam() {
        try {
            if (this.zzdj.zzo()) {
                return true;
            }
            this.zzdj.zzp();
            return true;
        } catch (IOException e) {
            String valueOf = String.valueOf(e.getMessage());
            Log.e("FirebaseInstanceId", valueOf.length() != 0 ? "Build channel failed: ".concat(valueOf) : new String("Build channel failed: "));
            return false;
        }
    }

    private final boolean zzan() {
        zzax zzj = this.zzdj.zzj();
        if (zzj == null || zzj.zzj(this.zzan.zzad())) {
            try {
                String zzk = this.zzdj.zzk();
                if (zzk == null) {
                    Log.e("FirebaseInstanceId", "Token retrieval failed: null");
                    return false;
                }
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    Log.d("FirebaseInstanceId", "Token successfully retrieved");
                }
                if (zzj == null || (zzj != null && !zzk.equals(zzj.zzbq))) {
                    Context context = getContext();
                    Intent intent = new Intent("com.google.firebase.messaging.NEW_TOKEN");
                    intent.putExtra("token", zzk);
                    zzav.zzc(context, intent);
                    zzav.zzb(context, new Intent("com.google.firebase.iid.TOKEN_REFRESH"));
                }
                return true;
            } catch (IOException | SecurityException e) {
                String valueOf = String.valueOf(e.getMessage());
                Log.e("FirebaseInstanceId", valueOf.length() != 0 ? "Token retrieval failed: ".concat(valueOf) : new String("Token retrieval failed: "));
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Context getContext() {
        return this.zzdj.zzh().getApplicationContext();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzao() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
