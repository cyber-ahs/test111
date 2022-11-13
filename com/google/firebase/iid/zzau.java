package com.google.firebase.iid;

import android.os.Looper;
import android.os.Message;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzau extends com.google.android.gms.internal.firebase_messaging.zza {
    private final /* synthetic */ zzat zzcw;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzau(zzat zzatVar, Looper looper) {
        super(looper);
        this.zzcw = zzatVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        this.zzcw.zzb(message);
    }
}
