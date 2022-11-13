package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import javax.annotation.Nullable;
/* loaded from: classes.dex */
final class zzaz extends BroadcastReceiver {
    @Nullable
    private zzay zzdk;

    public zzaz(zzay zzayVar) {
        this.zzdk = zzayVar;
    }

    public final void zzap() {
        if (FirebaseInstanceId.zzl()) {
            Log.d("FirebaseInstanceId", "Connectivity change received registered");
        }
        this.zzdk.getContext().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        zzay zzayVar = this.zzdk;
        if (zzayVar != null && zzayVar.zzao()) {
            if (FirebaseInstanceId.zzl()) {
                Log.d("FirebaseInstanceId", "Connectivity changed. Starting background sync.");
            }
            FirebaseInstanceId.zza(this.zzdk, 0L);
            this.zzdk.getContext().unregisterReceiver(this);
            this.zzdk = null;
        }
    }
}
