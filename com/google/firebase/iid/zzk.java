package com.google.firebase.iid;

import java.util.concurrent.Executor;
/* loaded from: classes.dex */
final /* synthetic */ class zzk implements Executor {
    static final Executor zzaf = new zzk();

    private zzk() {
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
