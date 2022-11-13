package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.FirebaseApp;
import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import com.google.firebase.events.Subscriber;
import com.google.firebase.iid.FirebaseInstanceId;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.slf4j.Marker;
/* loaded from: classes.dex */
public class FirebaseInstanceId {
    private static final long zzai = TimeUnit.HOURS.toSeconds(8);
    private static zzaw zzaj;
    private static ScheduledThreadPoolExecutor zzak;
    private final Executor zzal;
    private final FirebaseApp zzam;
    private final zzan zzan;
    private MessagingChannel zzao;
    private final zzaq zzap;
    private final zzba zzaq;
    private boolean zzar;
    private final zza zzas;

    public static FirebaseInstanceId getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    public static FirebaseInstanceId getInstance(FirebaseApp firebaseApp) {
        return (FirebaseInstanceId) firebaseApp.get(FirebaseInstanceId.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FirebaseInstanceId(FirebaseApp firebaseApp, Subscriber subscriber) {
        this(firebaseApp, new zzan(firebaseApp.getApplicationContext()), zzi.zzf(), zzi.zzf(), subscriber);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class zza {
        private final boolean zzaz;
        private final Subscriber zzba;
        private EventHandler<DataCollectionDefaultChange> zzbb;
        private Boolean zzbc;

        zza(Subscriber subscriber) {
            this.zzba = subscriber;
            boolean zzu = zzu();
            this.zzaz = zzu;
            Boolean zzt = zzt();
            this.zzbc = zzt;
            if (zzt == null && zzu) {
                EventHandler<DataCollectionDefaultChange> eventHandler = new EventHandler(this) { // from class: com.google.firebase.iid.zzq
                    private final FirebaseInstanceId.zza zzbe;

                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        this.zzbe = this;
                    }

                    @Override // com.google.firebase.events.EventHandler
                    public final void handle(Event event) {
                        FirebaseInstanceId.zza zzaVar = this.zzbe;
                        synchronized (zzaVar) {
                            if (zzaVar.isEnabled()) {
                                FirebaseInstanceId.this.zzg();
                            }
                        }
                    }
                };
                this.zzbb = eventHandler;
                subscriber.subscribe(DataCollectionDefaultChange.class, eventHandler);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final synchronized boolean isEnabled() {
            Boolean bool = this.zzbc;
            if (bool != null) {
                return bool.booleanValue();
            }
            return this.zzaz && FirebaseInstanceId.this.zzam.isDataCollectionDefaultEnabled();
        }

        final synchronized void setEnabled(boolean z) {
            EventHandler<DataCollectionDefaultChange> eventHandler = this.zzbb;
            if (eventHandler != null) {
                this.zzba.unsubscribe(DataCollectionDefaultChange.class, eventHandler);
                this.zzbb = null;
            }
            SharedPreferences.Editor edit = FirebaseInstanceId.this.zzam.getApplicationContext().getSharedPreferences("com.google.firebase.messaging", 0).edit();
            edit.putBoolean("auto_init", z);
            edit.apply();
            if (z) {
                FirebaseInstanceId.this.zzg();
            }
            this.zzbc = Boolean.valueOf(z);
        }

        private final Boolean zzt() {
            ApplicationInfo applicationInfo;
            Context applicationContext = FirebaseInstanceId.this.zzam.getApplicationContext();
            SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("com.google.firebase.messaging", 0);
            if (sharedPreferences.contains("auto_init")) {
                return Boolean.valueOf(sharedPreferences.getBoolean("auto_init", false));
            }
            try {
                PackageManager packageManager = applicationContext.getPackageManager();
                if (packageManager == null || (applicationInfo = packageManager.getApplicationInfo(applicationContext.getPackageName(), 128)) == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey("firebase_messaging_auto_init_enabled")) {
                    return null;
                }
                return Boolean.valueOf(applicationInfo.metaData.getBoolean("firebase_messaging_auto_init_enabled"));
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            }
        }

        private final boolean zzu() {
            try {
                Class.forName("com.google.firebase.messaging.FirebaseMessaging");
                return true;
            } catch (ClassNotFoundException unused) {
                Context applicationContext = FirebaseInstanceId.this.zzam.getApplicationContext();
                Intent intent = new Intent("com.google.firebase.MESSAGING_EVENT");
                intent.setPackage(applicationContext.getPackageName());
                ResolveInfo resolveService = applicationContext.getPackageManager().resolveService(intent, 0);
                return (resolveService == null || resolveService.serviceInfo == null) ? false : true;
            }
        }
    }

    private FirebaseInstanceId(FirebaseApp firebaseApp, zzan zzanVar, Executor executor, Executor executor2, Subscriber subscriber) {
        this.zzar = false;
        if (zzan.zza(firebaseApp) == null) {
            throw new IllegalStateException("FirebaseInstanceId failed to initialize, FirebaseApp is missing project ID");
        }
        synchronized (FirebaseInstanceId.class) {
            if (zzaj == null) {
                zzaj = new zzaw(firebaseApp.getApplicationContext());
            }
        }
        this.zzam = firebaseApp;
        this.zzan = zzanVar;
        if (this.zzao == null) {
            MessagingChannel messagingChannel = (MessagingChannel) firebaseApp.get(MessagingChannel.class);
            if (messagingChannel != null && messagingChannel.isAvailable()) {
                this.zzao = messagingChannel;
            } else {
                this.zzao = new zzr(firebaseApp, zzanVar, executor);
            }
        }
        this.zzao = this.zzao;
        this.zzal = executor2;
        this.zzaq = new zzba(zzaj);
        zza zzaVar = new zza(subscriber);
        this.zzas = zzaVar;
        this.zzap = new zzaq(executor);
        if (zzaVar.isEnabled()) {
            zzg();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzg() {
        zzax zzj = zzj();
        if (!zzo() || zzj == null || zzj.zzj(this.zzan.zzad()) || this.zzaq.zzaq()) {
            startSync();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final FirebaseApp zzh() {
        return this.zzam;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zza(boolean z) {
        this.zzar = z;
    }

    private final synchronized void startSync() {
        if (!this.zzar) {
            zza(0L);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zza(long j) {
        zza(new zzay(this, this.zzan, this.zzaq, Math.min(Math.max(30L, j << 1), zzai)), j);
        this.zzar = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(Runnable runnable, long j) {
        synchronized (FirebaseInstanceId.class) {
            if (zzak == null) {
                zzak = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("FirebaseInstanceId"));
            }
            zzak.schedule(runnable, j, TimeUnit.SECONDS);
        }
    }

    public String getId() {
        zzg();
        return zzi();
    }

    private static String zzi() {
        return zzan.zza(zzaj.zzg("").getKeyPair());
    }

    public long getCreationTime() {
        return zzaj.zzg("").getCreationTime();
    }

    public Task<InstanceIdResult> getInstanceId() {
        return zza(zzan.zza(this.zzam), Marker.ANY_MARKER);
    }

    private final Task<InstanceIdResult> zza(final String str, final String str2) {
        final String zzd = zzd(str2);
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzal.execute(new Runnable(this, str, str2, taskCompletionSource, zzd) { // from class: com.google.firebase.iid.zzn
            private final FirebaseInstanceId zzat;
            private final String zzau;
            private final String zzav;
            private final TaskCompletionSource zzaw;
            private final String zzax;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zzat = this;
                this.zzau = str;
                this.zzav = str2;
                this.zzaw = taskCompletionSource;
                this.zzax = zzd;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzat.zza(this.zzau, this.zzav, this.zzaw, this.zzax);
            }
        });
        return taskCompletionSource.getTask();
    }

    public void deleteInstanceId() throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        zza(this.zzao.deleteInstanceId(zzi()));
        zzm();
    }

    @Deprecated
    public String getToken() {
        zzax zzj = zzj();
        if (zzj == null || zzj.zzj(this.zzan.zzad())) {
            startSync();
        }
        if (zzj != null) {
            return zzj.zzbq;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzax zzj() {
        return zzb(zzan.zza(this.zzam), Marker.ANY_MARKER);
    }

    private static zzax zzb(String str, String str2) {
        return zzaj.zzb("", str, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzk() throws IOException {
        return getToken(zzan.zza(this.zzam), Marker.ANY_MARKER);
    }

    public String getToken(String str, String str2) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        return ((InstanceIdResult) zza(zza(str, str2))).getToken();
    }

    private final <T> T zza(Task<T> task) throws IOException {
        try {
            return (T) Tasks.await(task, 30000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | TimeoutException unused) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof IOException) {
                if ("INSTANCE_ID_RESET".equals(cause.getMessage())) {
                    zzm();
                }
                throw ((IOException) cause);
            } else if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else {
                throw new IOException(e);
            }
        }
    }

    public void deleteToken(String str, String str2) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        String zzd = zzd(str2);
        zza(this.zzao.deleteToken(zzi(), zzax.zza(zzb(str, zzd)), str, zzd));
        zzaj.zzc("", str, zzd);
    }

    public final synchronized Task<Void> zza(String str) {
        Task<Void> zza2;
        zza2 = this.zzaq.zza(str);
        startSync();
        return zza2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb(String str) throws IOException {
        zzax zzj = zzj();
        if (zzj == null || zzj.zzj(this.zzan.zzad())) {
            throw new IOException("token not available");
        }
        zza(this.zzao.subscribeToTopic(zzi(), zzj.zzbq, str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzc(String str) throws IOException {
        zzax zzj = zzj();
        if (zzj == null || zzj.zzj(this.zzan.zzad())) {
            throw new IOException("token not available");
        }
        zza(this.zzao.unsubscribeFromTopic(zzi(), zzj.zzbq, str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzl() {
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            return true;
        }
        return Build.VERSION.SDK_INT == 23 && Log.isLoggable("FirebaseInstanceId", 3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zzm() {
        zzaj.zzal();
        if (this.zzas.isEnabled()) {
            startSync();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzn() {
        return this.zzao.isAvailable();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzo() {
        return this.zzao.isChannelBuilt();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzp() throws IOException {
        zza(this.zzao.buildChannel(zzi(), zzax.zza(zzj())));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzq() {
        zzaj.zzh("");
        startSync();
    }

    public final boolean zzr() {
        return this.zzas.isEnabled();
    }

    public final void zzb(boolean z) {
        this.zzas.setEnabled(z);
    }

    private static String zzd(String str) {
        return (str.isEmpty() || str.equalsIgnoreCase("fcm") || str.equalsIgnoreCase("gcm")) ? Marker.ANY_MARKER : str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zza(final String str, String str2, final TaskCompletionSource taskCompletionSource, final String str3) {
        final String zzi = zzi();
        zzax zzb = zzb(str, str2);
        if (zzb != null && !zzb.zzj(this.zzan.zzad())) {
            taskCompletionSource.setResult(new zzx(zzi, zzb.zzbq));
            return;
        }
        final String zza2 = zzax.zza(zzb);
        this.zzap.zza(str, str3, new zzas(this, zzi, zza2, str, str3) { // from class: com.google.firebase.iid.zzo
            private final FirebaseInstanceId zzat;
            private final String zzau;
            private final String zzav;
            private final String zzax;
            private final String zzay;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zzat = this;
                this.zzau = zzi;
                this.zzav = zza2;
                this.zzay = str;
                this.zzax = str3;
            }

            @Override // com.google.firebase.iid.zzas
            public final Task zzs() {
                return this.zzat.zza(this.zzau, this.zzav, this.zzay, this.zzax);
            }
        }).addOnCompleteListener(this.zzal, new OnCompleteListener(this, str, str3, taskCompletionSource, zzi) { // from class: com.google.firebase.iid.zzp
            private final FirebaseInstanceId zzat;
            private final String zzau;
            private final String zzav;
            private final TaskCompletionSource zzaw;
            private final String zzax;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zzat = this;
                this.zzau = str;
                this.zzav = str3;
                this.zzaw = taskCompletionSource;
                this.zzax = zzi;
            }

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.zzat.zza(this.zzau, this.zzav, this.zzaw, this.zzax, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zza(String str, String str2, TaskCompletionSource taskCompletionSource, String str3, Task task) {
        if (task.isSuccessful()) {
            String str4 = (String) task.getResult();
            zzaj.zza("", str, str2, str4, this.zzan.zzad());
            taskCompletionSource.setResult(new zzx(str3, str4));
            return;
        }
        taskCompletionSource.setException(task.getException());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Task zza(String str, String str2, String str3, String str4) {
        return this.zzao.getToken(str, str2, str3, str4);
    }
}
