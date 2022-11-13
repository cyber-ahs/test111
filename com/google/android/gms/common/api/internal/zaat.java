package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.concurrent.locks.Lock;
/* loaded from: classes.dex */
final class zaat implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private final /* synthetic */ zaak zagi;

    private zaat(zaak zaakVar) {
        this.zagi = zaakVar;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public final void onConnectionSuspended(int i) {
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        com.google.android.gms.signin.zad zadVar;
        zadVar = this.zagi.zaga;
        zadVar.zaa(new zaar(this.zagi));
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        Lock lock;
        Lock lock2;
        boolean zad;
        lock = this.zagi.zaen;
        lock.lock();
        try {
            zad = this.zagi.zad(connectionResult);
            if (!zad) {
                this.zagi.zae(connectionResult);
            } else {
                this.zagi.zaar();
                this.zagi.zaap();
            }
        } finally {
            lock2 = this.zagi.zaen;
            lock2.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zaat(zaak zaakVar, zaal zaalVar) {
        this(zaakVar);
    }
}
