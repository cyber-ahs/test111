package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
/* loaded from: classes.dex */
public final class zacp {
    public static final Status zakw = new Status(8, "The connection to Google Play services was lost");
    private static final BasePendingResult<?>[] zakx = new BasePendingResult[0];
    private final Map<Api.AnyClientKey<?>, Api.Client> zagy;
    final Set<BasePendingResult<?>> zaky = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
    private final zacs zakz = new zacq(this);

    public zacp(Map<Api.AnyClientKey<?>, Api.Client> map) {
        this.zagy = map;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zab(BasePendingResult<? extends Result> basePendingResult) {
        this.zaky.add(basePendingResult);
        basePendingResult.zaa(this.zakz);
    }

    public final void release() {
        BasePendingResult[] basePendingResultArr;
        for (BasePendingResult basePendingResult : (BasePendingResult[]) this.zaky.toArray(zakx)) {
            com.google.android.gms.common.api.zac zacVar = null;
            basePendingResult.zaa((zacs) null);
            if (basePendingResult.zam() == null) {
                if (basePendingResult.zat()) {
                    this.zaky.remove(basePendingResult);
                }
            } else {
                basePendingResult.setResultCallback(null);
                IBinder serviceBrokerBinder = this.zagy.get(((BaseImplementation.ApiMethodImpl) basePendingResult).getClientKey()).getServiceBrokerBinder();
                if (basePendingResult.isReady()) {
                    basePendingResult.zaa(new zacr(basePendingResult, null, serviceBrokerBinder, null));
                } else if (serviceBrokerBinder != null && serviceBrokerBinder.isBinderAlive()) {
                    zacr zacrVar = new zacr(basePendingResult, null, serviceBrokerBinder, null);
                    basePendingResult.zaa(zacrVar);
                    try {
                        serviceBrokerBinder.linkToDeath(zacrVar, 0);
                    } catch (RemoteException unused) {
                        basePendingResult.cancel();
                        zacVar.remove(basePendingResult.zam().intValue());
                    }
                } else {
                    basePendingResult.zaa((zacs) null);
                    basePendingResult.cancel();
                    zacVar.remove(basePendingResult.zam().intValue());
                }
                this.zaky.remove(basePendingResult);
            }
        }
    }

    public final void zabx() {
        for (BasePendingResult basePendingResult : (BasePendingResult[]) this.zaky.toArray(zakx)) {
            basePendingResult.zab(zakw);
        }
    }
}
