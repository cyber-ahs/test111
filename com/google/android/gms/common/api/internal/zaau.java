package com.google.android.gms.common.api.internal;

import java.util.concurrent.locks.Lock;
/* loaded from: classes.dex */
abstract class zaau implements Runnable {
    private final /* synthetic */ zaak zagi;

    private zaau(zaak zaakVar) {
        this.zagi = zaakVar;
    }

    protected abstract void zaan();

    @Override // java.lang.Runnable
    public void run() {
        Lock lock;
        Lock lock2;
        zabe zabeVar;
        lock = this.zagi.zaen;
        lock.lock();
        try {
            if (Thread.interrupted()) {
                return;
            }
            zaan();
        } catch (RuntimeException e) {
            zabeVar = this.zagi.zafs;
            zabeVar.zab(e);
        } finally {
            lock2 = this.zagi.zaen;
            lock2.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zaau(zaak zaakVar, zaal zaalVar) {
        this(zaakVar);
    }
}
