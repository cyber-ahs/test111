package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
final class zaar extends com.google.android.gms.signin.internal.zac {
    private final WeakReference<zaak> zagj;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zaar(zaak zaakVar) {
        this.zagj = new WeakReference<>(zaakVar);
    }

    @Override // com.google.android.gms.signin.internal.zac, com.google.android.gms.signin.internal.zad
    public final void zab(com.google.android.gms.signin.internal.zaj zajVar) {
        zabe zabeVar;
        zaak zaakVar = this.zagj.get();
        if (zaakVar == null) {
            return;
        }
        zabeVar = zaakVar.zafs;
        zabeVar.zaa(new zaas(this, zaakVar, zaakVar, zajVar));
    }
}
