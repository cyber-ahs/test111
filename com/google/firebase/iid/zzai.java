package com.google.firebase.iid;

import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
/* loaded from: classes.dex */
final class zzai {
    private final Messenger zzag;
    private final zzl zzce;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzai(IBinder iBinder) throws RemoteException {
        String interfaceDescriptor = iBinder.getInterfaceDescriptor();
        if ("android.os.IMessenger".equals(interfaceDescriptor)) {
            this.zzag = new Messenger(iBinder);
            this.zzce = null;
        } else if ("com.google.android.gms.iid.IMessengerCompat".equals(interfaceDescriptor)) {
            this.zzce = new zzl(iBinder);
            this.zzag = null;
        } else {
            String valueOf = String.valueOf(interfaceDescriptor);
            Log.w("MessengerIpcClient", valueOf.length() != 0 ? "Invalid interface descriptor: ".concat(valueOf) : new String("Invalid interface descriptor: "));
            throw new RemoteException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void send(Message message) throws RemoteException {
        Messenger messenger = this.zzag;
        if (messenger != null) {
            messenger.send(message);
            return;
        }
        zzl zzlVar = this.zzce;
        if (zzlVar != null) {
            zzlVar.send(message);
            return;
        }
        throw new IllegalStateException("Both messengers are null");
    }
}
