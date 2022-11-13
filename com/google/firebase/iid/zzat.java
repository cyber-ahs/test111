package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.iid.zzl;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes.dex */
final class zzat {
    private static int zzcf;
    private static PendingIntent zzcr;
    private final zzan zzan;
    private final SimpleArrayMap<String, TaskCompletionSource<Bundle>> zzcs = new SimpleArrayMap<>();
    private Messenger zzct = new Messenger(new zzau(this, Looper.getMainLooper()));
    private Messenger zzcu;
    private zzl zzcv;
    private final Context zzx;

    public zzat(Context context, zzan zzanVar) {
        this.zzx = context;
        this.zzan = zzanVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb(Message message) {
        if (message != null && (message.obj instanceof Intent)) {
            Intent intent = (Intent) message.obj;
            intent.setExtrasClassLoader(new zzl.zza());
            if (intent.hasExtra("google.messenger")) {
                Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                if (parcelableExtra instanceof zzl) {
                    this.zzcv = (zzl) parcelableExtra;
                }
                if (parcelableExtra instanceof Messenger) {
                    this.zzcu = (Messenger) parcelableExtra;
                }
            }
            Intent intent2 = (Intent) message.obj;
            String action = intent2.getAction();
            if (!"com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    String valueOf = String.valueOf(action);
                    Log.d("FirebaseInstanceId", valueOf.length() != 0 ? "Unexpected response action: ".concat(valueOf) : new String("Unexpected response action: "));
                    return;
                }
                return;
            }
            String stringExtra = intent2.getStringExtra("registration_id");
            if (stringExtra == null) {
                stringExtra = intent2.getStringExtra("unregistered");
            }
            if (stringExtra == null) {
                String stringExtra2 = intent2.getStringExtra("error");
                if (stringExtra2 == null) {
                    String valueOf2 = String.valueOf(intent2.getExtras());
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf2).length() + 49);
                    sb.append("Unexpected response, no error or registration id ");
                    sb.append(valueOf2);
                    Log.w("FirebaseInstanceId", sb.toString());
                    return;
                }
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    String valueOf3 = String.valueOf(stringExtra2);
                    Log.d("FirebaseInstanceId", valueOf3.length() != 0 ? "Received InstanceID error ".concat(valueOf3) : new String("Received InstanceID error "));
                }
                if (stringExtra2.startsWith("|")) {
                    String[] split = stringExtra2.split("\\|");
                    if (split.length <= 2 || !"ID".equals(split[1])) {
                        String valueOf4 = String.valueOf(stringExtra2);
                        Log.w("FirebaseInstanceId", valueOf4.length() != 0 ? "Unexpected structured response ".concat(valueOf4) : new String("Unexpected structured response "));
                        return;
                    }
                    String str = split[2];
                    String str2 = split[3];
                    if (str2.startsWith(":")) {
                        str2 = str2.substring(1);
                    }
                    zza(str, intent2.putExtra("error", str2).getExtras());
                    return;
                }
                synchronized (this.zzcs) {
                    for (int i = 0; i < this.zzcs.size(); i++) {
                        zza(this.zzcs.keyAt(i), intent2.getExtras());
                    }
                }
                return;
            }
            Matcher matcher = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.*)").matcher(stringExtra);
            if (!matcher.matches()) {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    String valueOf5 = String.valueOf(stringExtra);
                    Log.d("FirebaseInstanceId", valueOf5.length() != 0 ? "Unexpected response string: ".concat(valueOf5) : new String("Unexpected response string: "));
                    return;
                }
                return;
            }
            String group = matcher.group(1);
            String group2 = matcher.group(2);
            Bundle extras = intent2.getExtras();
            extras.putString("registration_id", group2);
            zza(group, extras);
            return;
        }
        Log.w("FirebaseInstanceId", "Dropping invalid message");
    }

    private static synchronized void zza(Context context, Intent intent) {
        synchronized (zzat.class) {
            if (zzcr == null) {
                Intent intent2 = new Intent();
                intent2.setPackage("com.google.example.invalidpackage");
                zzcr = PendingIntent.getBroadcast(context, 0, intent2, 0);
            }
            intent.putExtra("app", zzcr);
        }
    }

    private final void zza(String str, Bundle bundle) {
        synchronized (this.zzcs) {
            TaskCompletionSource<Bundle> remove = this.zzcs.remove(str);
            if (remove == null) {
                String valueOf = String.valueOf(str);
                Log.w("FirebaseInstanceId", valueOf.length() != 0 ? "Missing callback for ".concat(valueOf) : new String("Missing callback for "));
                return;
            }
            remove.setResult(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Bundle zzc(Bundle bundle) throws IOException {
        if (this.zzan.zzaf() >= 12000000) {
            try {
                return (Bundle) Tasks.await(zzab.zzc(this.zzx).zzb(1, bundle));
            } catch (InterruptedException | ExecutionException e) {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    String valueOf = String.valueOf(e);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 22);
                    sb.append("Error making request: ");
                    sb.append(valueOf);
                    Log.d("FirebaseInstanceId", sb.toString());
                }
                if ((e.getCause() instanceof zzal) && ((zzal) e.getCause()).getErrorCode() == 4) {
                    return zzd(bundle);
                }
                return null;
            }
        }
        return zzd(bundle);
    }

    private final Bundle zzd(Bundle bundle) throws IOException {
        Bundle zze = zze(bundle);
        if (zze == null || !zze.containsKey("google.messenger")) {
            return zze;
        }
        Bundle zze2 = zze(bundle);
        if (zze2 == null || !zze2.containsKey("google.messenger")) {
            return zze2;
        }
        return null;
    }

    private static synchronized String zzah() {
        String num;
        synchronized (zzat.class) {
            int i = zzcf;
            zzcf = i + 1;
            num = Integer.toString(i);
        }
        return num;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x00a3, code lost:
        if (r8.zzcv != null) goto L55;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00ed A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.google.android.gms.tasks.TaskCompletionSource, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v14, types: [java.util.concurrent.TimeUnit] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x00d1 -> B:65:0x00dc). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x00d7 -> B:65:0x00dc). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final Bundle zze(Bundle bundle) throws IOException {
        TaskCompletionSource taskCompletionSource;
        TaskCompletionSource taskCompletionSource2;
        String zzah = zzah();
        ?? taskCompletionSource3 = new TaskCompletionSource();
        synchronized (this.zzcs) {
            this.zzcs.put(zzah, taskCompletionSource3);
        }
        if (this.zzan.zzac() == 0) {
            throw new IOException("MISSING_INSTANCEID_SERVICE");
        }
        Intent intent = new Intent();
        intent.setPackage("com.google.android.gms");
        if (this.zzan.zzac() == 2) {
            intent.setAction("com.google.iid.TOKEN_REQUEST");
        } else {
            intent.setAction("com.google.android.c2dm.intent.REGISTER");
        }
        intent.putExtras(bundle);
        zza(this.zzx, intent);
        StringBuilder sb = new StringBuilder(String.valueOf(zzah).length() + 5);
        sb.append("|ID|");
        sb.append(zzah);
        sb.append("|");
        intent.putExtra("kid", sb.toString());
        int i = 3;
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String valueOf = String.valueOf(intent.getExtras());
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 8);
            sb2.append("Sending ");
            sb2.append(valueOf);
            Log.d("FirebaseInstanceId", sb2.toString());
        }
        intent.putExtra("google.messenger", this.zzct);
        if (this.zzcu == null) {
            taskCompletionSource2 = taskCompletionSource3;
        }
        Message obtain = Message.obtain();
        obtain.obj = intent;
        try {
            Messenger messenger = this.zzcu;
            if (messenger != null) {
                messenger.send(obtain);
                taskCompletionSource = taskCompletionSource3;
            } else {
                this.zzcv.send(obtain);
                taskCompletionSource = taskCompletionSource3;
            }
        } catch (RemoteException unused) {
            taskCompletionSource2 = taskCompletionSource3;
            if (Log.isLoggable("FirebaseInstanceId", i)) {
                Log.d("FirebaseInstanceId", "Messenger failed, fallback to startService");
                taskCompletionSource2 = taskCompletionSource3;
            }
            if (this.zzan.zzac() == 2) {
                this.zzx.sendBroadcast(intent);
                taskCompletionSource = taskCompletionSource2;
            } else {
                this.zzx.startService(intent);
                taskCompletionSource = taskCompletionSource2;
            }
            try {
                Task task = taskCompletionSource.getTask();
                taskCompletionSource3 = 30000;
                i = TimeUnit.MILLISECONDS;
                Bundle bundle2 = (Bundle) Tasks.await(task, 30000L, i);
                synchronized (this.zzcs) {
                }
            } catch (Throwable th) {
                synchronized (this.zzcs) {
                    this.zzcs.remove(zzah);
                    throw th;
                }
            }
        }
        try {
            Task task2 = taskCompletionSource.getTask();
            taskCompletionSource3 = 30000;
            i = TimeUnit.MILLISECONDS;
            Bundle bundle22 = (Bundle) Tasks.await(task2, 30000L, i);
            synchronized (this.zzcs) {
                this.zzcs.remove(zzah);
            }
            return bundle22;
        } catch (InterruptedException | TimeoutException unused2) {
            Log.w("FirebaseInstanceId", "No response");
            throw new IOException("TIMEOUT");
        } catch (ExecutionException e) {
            throw new IOException(e);
        }
    }
}
