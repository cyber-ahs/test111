package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.iid.zzab;
import com.google.firebase.iid.zzav;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes.dex */
public class FirebaseMessagingService extends com.google.firebase.iid.zzb {
    private static final Queue<String> zzdr = new ArrayDeque(10);

    public void onDeletedMessages() {
    }

    public void onMessageReceived(RemoteMessage remoteMessage) {
    }

    public void onMessageSent(String str) {
    }

    public void onNewToken(String str) {
    }

    public void onSendError(String str, Exception exc) {
    }

    @Override // com.google.firebase.iid.zzb
    protected final Intent zzb(Intent intent) {
        return zzav.zzai().zzaj();
    }

    @Override // com.google.firebase.iid.zzb
    public final boolean zzc(Intent intent) {
        if ("com.google.firebase.messaging.NOTIFICATION_OPEN".equals(intent.getAction())) {
            PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("pending_intent");
            if (pendingIntent != null) {
                try {
                    pendingIntent.send();
                } catch (PendingIntent.CanceledException unused) {
                    Log.e("FirebaseMessaging", "Notification pending intent canceled");
                }
            }
            if (MessagingAnalytics.shouldUploadMetrics(intent)) {
                MessagingAnalytics.logNotificationOpen(intent);
                return true;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00ea, code lost:
        if (r1.equals("send_error") == false) goto L35;
     */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c1  */
    @Override // com.google.firebase.iid.zzb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzd(Intent intent) {
        Task<Void> zza;
        boolean z;
        String action = intent.getAction();
        if ("com.google.android.c2dm.intent.RECEIVE".equals(action) || "com.google.firebase.messaging.RECEIVE_DIRECT_BOOT".equals(action)) {
            String stringExtra = intent.getStringExtra("google.message_id");
            char c = 2;
            if (TextUtils.isEmpty(stringExtra)) {
                zza = Tasks.forResult(null);
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("google.message_id", stringExtra);
                zza = zzab.zzc(this).zza(2, bundle);
            }
            try {
                if (!TextUtils.isEmpty(stringExtra)) {
                    Queue<String> queue = zzdr;
                    if (queue.contains(stringExtra)) {
                        if (Log.isLoggable("FirebaseMessaging", 3)) {
                            String valueOf = String.valueOf(stringExtra);
                            Log.d("FirebaseMessaging", valueOf.length() != 0 ? "Received duplicate message: ".concat(valueOf) : new String("Received duplicate message: "));
                        }
                        z = true;
                        if (!z) {
                            String stringExtra2 = intent.getStringExtra("message_type");
                            if (stringExtra2 == null) {
                                stringExtra2 = "gcm";
                            }
                            stringExtra2.hashCode();
                            switch (stringExtra2.hashCode()) {
                                case -2062414158:
                                    if (stringExtra2.equals("deleted_messages")) {
                                        c = 0;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case 102161:
                                    if (stringExtra2.equals("gcm")) {
                                        c = 1;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case 814694033:
                                    break;
                                case 814800675:
                                    if (stringExtra2.equals("send_event")) {
                                        c = 3;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                default:
                                    c = 65535;
                                    break;
                            }
                            switch (c) {
                                case 0:
                                    onDeletedMessages();
                                    break;
                                case 1:
                                    if (MessagingAnalytics.shouldUploadMetrics(intent)) {
                                        MessagingAnalytics.logNotificationReceived(intent);
                                    }
                                    Bundle extras = intent.getExtras();
                                    if (extras == null) {
                                        extras = new Bundle();
                                    }
                                    extras.remove("androidx.contentpager.content.wakelockid");
                                    if (zza.zzf(extras)) {
                                        if (!new zza(this).zzh(extras)) {
                                            if (MessagingAnalytics.shouldUploadMetrics(intent)) {
                                                MessagingAnalytics.logNotificationForeground(intent);
                                            }
                                        }
                                    }
                                    onMessageReceived(new RemoteMessage(extras));
                                    break;
                                case 2:
                                    String stringExtra3 = intent.getStringExtra("google.message_id");
                                    if (stringExtra3 == null) {
                                        stringExtra3 = intent.getStringExtra("message_id");
                                    }
                                    onSendError(stringExtra3, new SendException(intent.getStringExtra("error")));
                                    break;
                                case 3:
                                    onMessageSent(intent.getStringExtra("google.message_id"));
                                    break;
                                default:
                                    String valueOf2 = String.valueOf(stringExtra2);
                                    Log.w("FirebaseMessaging", valueOf2.length() != 0 ? "Received message with unknown type: ".concat(valueOf2) : new String("Received message with unknown type: "));
                                    break;
                            }
                        }
                        Tasks.await(zza, 1L, TimeUnit.SECONDS);
                        return;
                    }
                    if (queue.size() >= 10) {
                        queue.remove();
                    }
                    queue.add(stringExtra);
                }
                Tasks.await(zza, 1L, TimeUnit.SECONDS);
                return;
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                String valueOf3 = String.valueOf(e);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf3).length() + 20);
                sb.append("Message ack failed: ");
                sb.append(valueOf3);
                Log.w("FirebaseMessaging", sb.toString());
                return;
            }
            z = false;
            if (!z) {
            }
        } else if ("com.google.firebase.messaging.NOTIFICATION_DISMISS".equals(action)) {
            if (MessagingAnalytics.shouldUploadMetrics(intent)) {
                MessagingAnalytics.logNotificationDismiss(intent);
            }
        } else if ("com.google.firebase.messaging.NEW_TOKEN".equals(action)) {
            onNewToken(intent.getStringExtra("token"));
        } else {
            String valueOf4 = String.valueOf(intent.getAction());
            Log.d("FirebaseMessaging", valueOf4.length() != 0 ? "Unknown intent action: ".concat(valueOf4) : new String("Unknown intent action: "));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzj(Bundle bundle) {
        Iterator<String> it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next != null && next.startsWith("google.c.")) {
                it.remove();
            }
        }
    }
}
