package com.google.firebase.iid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzad implements ServiceConnection {
    int state;
    final Messenger zzbx;
    zzai zzby;
    final Queue<zzak<?>> zzbz;
    final SparseArray<zzak<?>> zzca;
    final /* synthetic */ zzab zzcb;

    private zzad(zzab zzabVar) {
        this.zzcb = zzabVar;
        this.state = 0;
        this.zzbx = new Messenger(new com.google.android.gms.internal.firebase_messaging.zza(Looper.getMainLooper(), new Handler.Callback(this) { // from class: com.google.firebase.iid.zzae
            private final zzad zzcc;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zzcc = this;
            }

            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return this.zzcc.zza(message);
            }
        }));
        this.zzbz = new ArrayDeque();
        this.zzca = new SparseArray<>();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized boolean zzb(zzak zzakVar) {
        Context context;
        ScheduledExecutorService scheduledExecutorService;
        int i = this.state;
        if (i == 0) {
            this.zzbz.add(zzakVar);
            Preconditions.checkState(this.state == 0);
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Starting bind to GmsCore");
            }
            this.state = 1;
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gms");
            ConnectionTracker connectionTracker = ConnectionTracker.getInstance();
            context = this.zzcb.zzx;
            if (!connectionTracker.bindService(context, intent, this, 1)) {
                zza(0, "Unable to bind to service");
            } else {
                scheduledExecutorService = this.zzcb.zzbu;
                scheduledExecutorService.schedule(new Runnable(this) { // from class: com.google.firebase.iid.zzaf
                    private final zzad zzcc;

                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        this.zzcc = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        this.zzcc.zzaa();
                    }
                }, 30L, TimeUnit.SECONDS);
            }
            return true;
        } else if (i == 1) {
            this.zzbz.add(zzakVar);
            return true;
        } else if (i == 2) {
            this.zzbz.add(zzakVar);
            zzy();
            return true;
        } else {
            if (i != 3 && i != 4) {
                int i2 = this.state;
                StringBuilder sb = new StringBuilder(26);
                sb.append("Unknown state: ");
                sb.append(i2);
                throw new IllegalStateException(sb.toString());
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zza(Message message) {
        int i = message.arg1;
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            StringBuilder sb = new StringBuilder(41);
            sb.append("Received response to request: ");
            sb.append(i);
            Log.d("MessengerIpcClient", sb.toString());
        }
        synchronized (this) {
            zzak<?> zzakVar = this.zzca.get(i);
            if (zzakVar == null) {
                StringBuilder sb2 = new StringBuilder(50);
                sb2.append("Received response for unknown request: ");
                sb2.append(i);
                Log.w("MessengerIpcClient", sb2.toString());
                return true;
            }
            this.zzca.remove(i);
            zzz();
            Bundle data = message.getData();
            if (data.getBoolean("unsupported", false)) {
                zzakVar.zza(new zzal(4, "Not supported by GmsCore"));
            } else {
                zzakVar.zzb(data);
            }
            return true;
        }
    }

    @Override // android.content.ServiceConnection
    public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service connected");
        }
        if (iBinder == null) {
            zza(0, "Null service connection");
            return;
        }
        try {
            this.zzby = new zzai(iBinder);
            this.state = 2;
            zzy();
        } catch (RemoteException e) {
            zza(0, e.getMessage());
        }
    }

    private final void zzy() {
        ScheduledExecutorService scheduledExecutorService;
        scheduledExecutorService = this.zzcb.zzbu;
        scheduledExecutorService.execute(new Runnable(this) { // from class: com.google.firebase.iid.zzag
            private final zzad zzcc;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zzcc = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                final zzak<?> poll;
                ScheduledExecutorService scheduledExecutorService2;
                Context context;
                final zzad zzadVar = this.zzcc;
                while (true) {
                    synchronized (zzadVar) {
                        if (zzadVar.state != 2) {
                            return;
                        }
                        if (zzadVar.zzbz.isEmpty()) {
                            zzadVar.zzz();
                            return;
                        }
                        poll = zzadVar.zzbz.poll();
                        zzadVar.zzca.put(poll.zzcf, poll);
                        scheduledExecutorService2 = zzadVar.zzcb.zzbu;
                        scheduledExecutorService2.schedule(new Runnable(zzadVar, poll) { // from class: com.google.firebase.iid.zzah
                            private final zzad zzcc;
                            private final zzak zzcd;

                            /* JADX INFO: Access modifiers changed from: package-private */
                            {
                                this.zzcc = zzadVar;
                                this.zzcd = poll;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                this.zzcc.zza(this.zzcd.zzcf);
                            }
                        }, 30L, TimeUnit.SECONDS);
                    }
                    if (Log.isLoggable("MessengerIpcClient", 3)) {
                        String valueOf = String.valueOf(poll);
                        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 8);
                        sb.append("Sending ");
                        sb.append(valueOf);
                        Log.d("MessengerIpcClient", sb.toString());
                    }
                    context = zzadVar.zzcb.zzx;
                    Messenger messenger = zzadVar.zzbx;
                    Message obtain = Message.obtain();
                    obtain.what = poll.what;
                    obtain.arg1 = poll.zzcf;
                    obtain.replyTo = messenger;
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("oneWay", poll.zzab());
                    bundle.putString("pkg", context.getPackageName());
                    bundle.putBundle("data", poll.zzch);
                    obtain.setData(bundle);
                    try {
                        zzadVar.zzby.send(obtain);
                    } catch (RemoteException e) {
                        zzadVar.zza(2, e.getMessage());
                    }
                }
            }
        });
    }

    @Override // android.content.ServiceConnection
    public final synchronized void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service disconnected");
        }
        zza(2, "Service disconnected");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zza(int i, String str) {
        Context context;
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(str);
            Log.d("MessengerIpcClient", valueOf.length() != 0 ? "Disconnected: ".concat(valueOf) : new String("Disconnected: "));
        }
        int i2 = this.state;
        if (i2 == 0) {
            throw new IllegalStateException();
        }
        if (i2 != 1 && i2 != 2) {
            if (i2 == 3) {
                this.state = 4;
                return;
            } else if (i2 == 4) {
                return;
            } else {
                int i3 = this.state;
                StringBuilder sb = new StringBuilder(26);
                sb.append("Unknown state: ");
                sb.append(i3);
                throw new IllegalStateException(sb.toString());
            }
        }
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Unbinding service");
        }
        this.state = 4;
        ConnectionTracker connectionTracker = ConnectionTracker.getInstance();
        context = this.zzcb.zzx;
        connectionTracker.unbindService(context, this);
        zzal zzalVar = new zzal(i, str);
        for (zzak<?> zzakVar : this.zzbz) {
            zzakVar.zza(zzalVar);
        }
        this.zzbz.clear();
        for (int i4 = 0; i4 < this.zzca.size(); i4++) {
            this.zzca.valueAt(i4).zza(zzalVar);
        }
        this.zzca.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zzz() {
        Context context;
        if (this.state == 2 && this.zzbz.isEmpty() && this.zzca.size() == 0) {
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Finished handling requests, unbinding");
            }
            this.state = 3;
            ConnectionTracker connectionTracker = ConnectionTracker.getInstance();
            context = this.zzcb.zzx;
            connectionTracker.unbindService(context, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zzaa() {
        if (this.state == 1) {
            zza(1, "Timed out while binding");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zza(int i) {
        zzak<?> zzakVar = this.zzca.get(i);
        if (zzakVar != null) {
            StringBuilder sb = new StringBuilder(31);
            sb.append("Timing out request: ");
            sb.append(i);
            Log.w("MessengerIpcClient", sb.toString());
            this.zzca.remove(i);
            zzakVar.zza(new zzal(3, "Timed out waiting for response"));
            zzz();
        }
    }
}
