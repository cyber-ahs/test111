package com.google.android.gms.common.api.internal;

import android.content.Context;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import java.util.ArrayList;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zaan extends zaau {
    final /* synthetic */ zaak zagi;
    private final Map<Api.Client, zaam> zagk;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaan(zaak zaakVar, Map<Api.Client, zaam> map) {
        super(zaakVar, null);
        this.zagi = zaakVar;
        this.zagk = map;
    }

    @Override // com.google.android.gms.common.api.internal.zaau
    public final void zaan() {
        GoogleApiAvailabilityLight googleApiAvailabilityLight;
        Context context;
        boolean z;
        Context context2;
        zabe zabeVar;
        com.google.android.gms.signin.zad zadVar;
        zabe zabeVar2;
        Context context3;
        boolean z2;
        googleApiAvailabilityLight = this.zagi.zaex;
        GoogleApiAvailabilityCache googleApiAvailabilityCache = new GoogleApiAvailabilityCache(googleApiAvailabilityLight);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Api.Client client : this.zagk.keySet()) {
            if (client.requiresGooglePlayServices()) {
                z2 = this.zagk.get(client).zaeb;
                if (!z2) {
                    arrayList.add(client);
                }
            }
            arrayList2.add(client);
        }
        int i = -1;
        int i2 = 0;
        if (arrayList.isEmpty()) {
            int size = arrayList2.size();
            while (i2 < size) {
                Object obj = arrayList2.get(i2);
                i2++;
                context3 = this.zagi.mContext;
                i = googleApiAvailabilityCache.getClientAvailability(context3, (Api.Client) obj);
                if (i == 0) {
                    break;
                }
            }
        } else {
            int size2 = arrayList.size();
            while (i2 < size2) {
                Object obj2 = arrayList.get(i2);
                i2++;
                context = this.zagi.mContext;
                i = googleApiAvailabilityCache.getClientAvailability(context, (Api.Client) obj2);
                if (i != 0) {
                    break;
                }
            }
        }
        if (i != 0) {
            ConnectionResult connectionResult = new ConnectionResult(i, null);
            zabeVar2 = this.zagi.zafs;
            zabeVar2.zaa(new zaao(this, this.zagi, connectionResult));
            return;
        }
        z = this.zagi.zagc;
        if (z) {
            zadVar = this.zagi.zaga;
            zadVar.connect();
        }
        for (Api.Client client2 : this.zagk.keySet()) {
            zaam zaamVar = this.zagk.get(client2);
            if (client2.requiresGooglePlayServices()) {
                context2 = this.zagi.mContext;
                if (googleApiAvailabilityCache.getClientAvailability(context2, client2) != 0) {
                    zabeVar = this.zagi.zafs;
                    zabeVar.zaa(new zaap(this, this.zagi, zaamVar));
                }
            }
            client2.connect(zaamVar);
        }
    }
}
