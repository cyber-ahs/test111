package com.google.android.gms.common.api.internal;
/* loaded from: classes.dex */
final class zacg implements Runnable {
    private final /* synthetic */ com.google.android.gms.signin.internal.zaj zagq;
    private final /* synthetic */ zace zakj;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zacg(zace zaceVar, com.google.android.gms.signin.internal.zaj zajVar) {
        this.zakj = zaceVar;
        this.zagq = zajVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zakj.zac(this.zagq);
    }
}
