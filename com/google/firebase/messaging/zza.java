package com.google.firebase.messaging;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.firebase.iid.zzav;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
/* loaded from: classes.dex */
final class zza {
    private static final AtomicInteger zzdn = new AtomicInteger((int) SystemClock.elapsedRealtime());
    private Bundle zzdo;
    private final Context zzx;

    public zza(Context context) {
        this.zzx = context.getApplicationContext();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzf(Bundle bundle) {
        return "1".equals(zza(bundle, "gcm.n.e")) || zza(bundle, "gcm.n.icon") != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zza(Bundle bundle, String str) {
        String string = bundle.getString(str);
        return string == null ? bundle.getString(str.replace("gcm.n.", "gcm.notification.")) : string;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zzb(Bundle bundle, String str) {
        String valueOf = String.valueOf(str);
        return zza(bundle, "_loc_key".length() != 0 ? valueOf.concat("_loc_key") : new String(valueOf));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public static Object[] zzc(Bundle bundle, String str) {
        String valueOf = String.valueOf(str);
        String zza = zza(bundle, "_loc_args".length() != 0 ? valueOf.concat("_loc_args") : new String(valueOf));
        if (TextUtils.isEmpty(zza)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(zza);
            int length = jSONArray.length();
            String[] strArr = new String[length];
            for (int i = 0; i < length; i++) {
                strArr[i] = jSONArray.opt(i);
            }
            return strArr;
        } catch (JSONException unused) {
            String valueOf2 = String.valueOf(str);
            String substring = ("_loc_args".length() != 0 ? valueOf2.concat("_loc_args") : new String(valueOf2)).substring(6);
            StringBuilder sb = new StringBuilder(String.valueOf(substring).length() + 41 + String.valueOf(zza).length());
            sb.append("Malformed ");
            sb.append(substring);
            sb.append(": ");
            sb.append(zza);
            sb.append("  Default value will be used.");
            Log.w("FirebaseMessaging", sb.toString());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Uri zzg(Bundle bundle) {
        String zza = zza(bundle, "gcm.n.link_android");
        if (TextUtils.isEmpty(zza)) {
            zza = zza(bundle, "gcm.n.link");
        }
        if (TextUtils.isEmpty(zza)) {
            return null;
        }
        return Uri.parse(zza);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0305  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x030e  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x031f  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x032d  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0332  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x034a  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x035f  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x025b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean zzh(Bundle bundle) {
        boolean z;
        int i;
        Integer zzl;
        String zzi;
        Uri defaultUri;
        String zza;
        Intent launchIntentForPackage;
        PendingIntent activity;
        PendingIntent pendingIntent;
        String str;
        String zza2;
        if ("1".equals(zza(bundle, "gcm.n.noui"))) {
            return true;
        }
        if (!((KeyguardManager) this.zzx.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
            if (!PlatformVersion.isAtLeastLollipop()) {
                SystemClock.sleep(10L);
            }
            int myPid = Process.myPid();
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.zzx.getSystemService("activity")).getRunningAppProcesses();
            if (runningAppProcesses != null) {
                Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    ActivityManager.RunningAppProcessInfo next = it.next();
                    if (next.pid == myPid) {
                        if (next.importance == 100) {
                            z = true;
                        }
                    }
                }
            }
        }
        z = false;
        if (z) {
            return false;
        }
        CharSequence zzd = zzd(bundle, "gcm.n.title");
        if (TextUtils.isEmpty(zzd)) {
            zzd = this.zzx.getApplicationInfo().loadLabel(this.zzx.getPackageManager());
        }
        String zzd2 = zzd(bundle, "gcm.n.body");
        String zza3 = zza(bundle, "gcm.n.icon");
        if (!TextUtils.isEmpty(zza3)) {
            Resources resources = this.zzx.getResources();
            i = resources.getIdentifier(zza3, "drawable", this.zzx.getPackageName());
            if ((i == 0 || !zzb(i)) && ((i = resources.getIdentifier(zza3, "mipmap", this.zzx.getPackageName())) == 0 || !zzb(i))) {
                StringBuilder sb = new StringBuilder(String.valueOf(zza3).length() + 61);
                sb.append("Icon resource ");
                sb.append(zza3);
                sb.append(" not found. Notification will use default icon.");
                Log.w("FirebaseMessaging", sb.toString());
            }
            zzl = zzl(zza(bundle, "gcm.n.color"));
            zzi = zzi(bundle);
            if (!TextUtils.isEmpty(zzi)) {
                defaultUri = null;
            } else if (!"default".equals(zzi) && this.zzx.getResources().getIdentifier(zzi, "raw", this.zzx.getPackageName()) != 0) {
                String packageName = this.zzx.getPackageName();
                StringBuilder sb2 = new StringBuilder(String.valueOf(packageName).length() + 24 + String.valueOf(zzi).length());
                sb2.append("android.resource://");
                sb2.append(packageName);
                sb2.append("/raw/");
                sb2.append(zzi);
                defaultUri = Uri.parse(sb2.toString());
            } else {
                defaultUri = RingtoneManager.getDefaultUri(2);
            }
            zza = zza(bundle, "gcm.n.click_action");
            if (TextUtils.isEmpty(zza)) {
                launchIntentForPackage = new Intent(zza);
                launchIntentForPackage.setPackage(this.zzx.getPackageName());
                launchIntentForPackage.setFlags(268435456);
            } else {
                Uri zzg = zzg(bundle);
                if (zzg != null) {
                    launchIntentForPackage = new Intent("android.intent.action.VIEW");
                    launchIntentForPackage.setPackage(this.zzx.getPackageName());
                    launchIntentForPackage.setData(zzg);
                } else {
                    launchIntentForPackage = this.zzx.getPackageManager().getLaunchIntentForPackage(this.zzx.getPackageName());
                    if (launchIntentForPackage == null) {
                        Log.w("FirebaseMessaging", "No activity found to launch app");
                    }
                }
            }
            if (launchIntentForPackage != null) {
                activity = null;
            } else {
                launchIntentForPackage.addFlags(67108864);
                Bundle bundle2 = new Bundle(bundle);
                FirebaseMessagingService.zzj(bundle2);
                launchIntentForPackage.putExtras(bundle2);
                for (String str2 : bundle2.keySet()) {
                    if (str2.startsWith("gcm.n.") || str2.startsWith("gcm.notification.")) {
                        launchIntentForPackage.removeExtra(str2);
                    }
                }
                activity = PendingIntent.getActivity(this.zzx, zzdn.incrementAndGet(), launchIntentForPackage, 1073741824);
            }
            if (bundle != null ? false : "1".equals(bundle.getString("google.c.a.e"))) {
                pendingIntent = null;
            } else {
                Intent intent = new Intent("com.google.firebase.messaging.NOTIFICATION_OPEN");
                zza(intent, bundle);
                intent.putExtra("pending_intent", activity);
                Context context = this.zzx;
                AtomicInteger atomicInteger = zzdn;
                activity = zzav.zza(context, atomicInteger.incrementAndGet(), intent, 1073741824);
                Intent intent2 = new Intent("com.google.firebase.messaging.NOTIFICATION_DISMISS");
                zza(intent2, bundle);
                pendingIntent = zzav.zza(this.zzx, atomicInteger.incrementAndGet(), intent2, 1073741824);
            }
            String zza4 = zza(bundle, "gcm.n.android_channel_id");
            str = "fcm_fallback_notification_channel";
            if (PlatformVersion.isAtLeastO() || this.zzx.getApplicationInfo().targetSdkVersion < 26) {
                str = null;
            } else {
                NotificationManager notificationManager = (NotificationManager) this.zzx.getSystemService(NotificationManager.class);
                if (!TextUtils.isEmpty(zza4)) {
                    if (notificationManager.getNotificationChannel(zza4) != null) {
                        str = zza4;
                    } else {
                        StringBuilder sb3 = new StringBuilder(String.valueOf(zza4).length() + 122);
                        sb3.append("Notification Channel requested (");
                        sb3.append(zza4);
                        sb3.append(") has not been created by the app. Manifest configuration, or default, value will be used.");
                        Log.w("FirebaseMessaging", sb3.toString());
                    }
                }
                String string = zzas().getString("com.google.firebase.messaging.default_notification_channel_id");
                if (!TextUtils.isEmpty(string)) {
                    if (notificationManager.getNotificationChannel(string) != null) {
                        str = string;
                    } else {
                        Log.w("FirebaseMessaging", "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used.");
                    }
                } else {
                    Log.w("FirebaseMessaging", "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used.");
                }
                if (notificationManager.getNotificationChannel("fcm_fallback_notification_channel") == null) {
                    notificationManager.createNotificationChannel(new NotificationChannel("fcm_fallback_notification_channel", this.zzx.getString(R.string.fcm_fallback_notification_channel_label), 3));
                }
            }
            NotificationCompat.Builder smallIcon = new NotificationCompat.Builder(this.zzx).setAutoCancel(true).setSmallIcon(i);
            if (!TextUtils.isEmpty(zzd)) {
                smallIcon.setContentTitle(zzd);
            }
            if (!TextUtils.isEmpty(zzd2)) {
                smallIcon.setContentText(zzd2);
                smallIcon.setStyle(new NotificationCompat.BigTextStyle().bigText(zzd2));
            }
            if (zzl != null) {
                smallIcon.setColor(zzl.intValue());
            }
            if (defaultUri != null) {
                smallIcon.setSound(defaultUri);
            }
            if (activity != null) {
                smallIcon.setContentIntent(activity);
            }
            if (pendingIntent != null) {
                smallIcon.setDeleteIntent(pendingIntent);
            }
            if (str != null) {
                smallIcon.setChannelId(str);
            }
            Notification build = smallIcon.build();
            zza2 = zza(bundle, "gcm.n.tag");
            if (Log.isLoggable("FirebaseMessaging", 3)) {
                Log.d("FirebaseMessaging", "Showing notification");
            }
            NotificationManager notificationManager2 = (NotificationManager) this.zzx.getSystemService("notification");
            if (TextUtils.isEmpty(zza2)) {
                long uptimeMillis = SystemClock.uptimeMillis();
                StringBuilder sb4 = new StringBuilder(37);
                sb4.append("FCM-Notification:");
                sb4.append(uptimeMillis);
                zza2 = sb4.toString();
            }
            notificationManager2.notify(zza2, 0, build);
            return true;
        }
        int i2 = zzas().getInt("com.google.firebase.messaging.default_notification_icon", 0);
        if (i2 == 0 || !zzb(i2)) {
            i2 = this.zzx.getApplicationInfo().icon;
        }
        i = (i2 == 0 || !zzb(i2)) ? 17301651 : i2;
        zzl = zzl(zza(bundle, "gcm.n.color"));
        zzi = zzi(bundle);
        if (!TextUtils.isEmpty(zzi)) {
        }
        zza = zza(bundle, "gcm.n.click_action");
        if (TextUtils.isEmpty(zza)) {
        }
        if (launchIntentForPackage != null) {
        }
        if (bundle != null ? false : "1".equals(bundle.getString("google.c.a.e"))) {
        }
        String zza42 = zza(bundle, "gcm.n.android_channel_id");
        str = "fcm_fallback_notification_channel";
        if (PlatformVersion.isAtLeastO()) {
        }
        str = null;
        NotificationCompat.Builder smallIcon2 = new NotificationCompat.Builder(this.zzx).setAutoCancel(true).setSmallIcon(i);
        if (!TextUtils.isEmpty(zzd)) {
        }
        if (!TextUtils.isEmpty(zzd2)) {
        }
        if (zzl != null) {
        }
        if (defaultUri != null) {
        }
        if (activity != null) {
        }
        if (pendingIntent != null) {
        }
        if (str != null) {
        }
        Notification build2 = smallIcon2.build();
        zza2 = zza(bundle, "gcm.n.tag");
        if (Log.isLoggable("FirebaseMessaging", 3)) {
        }
        NotificationManager notificationManager22 = (NotificationManager) this.zzx.getSystemService("notification");
        if (TextUtils.isEmpty(zza2)) {
        }
        notificationManager22.notify(zza2, 0, build2);
        return true;
    }

    private final String zzd(Bundle bundle, String str) {
        String zza = zza(bundle, str);
        if (TextUtils.isEmpty(zza)) {
            String zzb = zzb(bundle, str);
            if (TextUtils.isEmpty(zzb)) {
                return null;
            }
            Resources resources = this.zzx.getResources();
            int identifier = resources.getIdentifier(zzb, "string", this.zzx.getPackageName());
            if (identifier == 0) {
                String valueOf = String.valueOf(str);
                String substring = ("_loc_key".length() != 0 ? valueOf.concat("_loc_key") : new String(valueOf)).substring(6);
                StringBuilder sb = new StringBuilder(String.valueOf(substring).length() + 49 + String.valueOf(zzb).length());
                sb.append(substring);
                sb.append(" resource not found: ");
                sb.append(zzb);
                sb.append(" Default value will be used.");
                Log.w("FirebaseMessaging", sb.toString());
                return null;
            }
            Object[] zzc = zzc(bundle, str);
            if (zzc == null) {
                return resources.getString(identifier);
            }
            try {
                return resources.getString(identifier, zzc);
            } catch (MissingFormatArgumentException e) {
                String arrays = Arrays.toString(zzc);
                StringBuilder sb2 = new StringBuilder(String.valueOf(zzb).length() + 58 + String.valueOf(arrays).length());
                sb2.append("Missing format argument for ");
                sb2.append(zzb);
                sb2.append(": ");
                sb2.append(arrays);
                sb2.append(" Default value will be used.");
                Log.w("FirebaseMessaging", sb2.toString(), e);
                return null;
            }
        }
        return zza;
    }

    private final boolean zzb(int i) {
        if (Build.VERSION.SDK_INT != 26) {
            return true;
        }
        try {
            if (this.zzx.getResources().getDrawable(i, null) instanceof AdaptiveIconDrawable) {
                StringBuilder sb = new StringBuilder(77);
                sb.append("Adaptive icons cannot be used in notifications. Ignoring icon id: ");
                sb.append(i);
                Log.e("FirebaseMessaging", sb.toString());
                return false;
            }
            return true;
        } catch (Resources.NotFoundException unused) {
            return false;
        }
    }

    private final Integer zzl(String str) {
        if (Build.VERSION.SDK_INT < 21) {
            return null;
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                return Integer.valueOf(Color.parseColor(str));
            } catch (IllegalArgumentException unused) {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 54);
                sb.append("Color ");
                sb.append(str);
                sb.append(" not valid. Notification will use default color.");
                Log.w("FirebaseMessaging", sb.toString());
            }
        }
        int i = zzas().getInt("com.google.firebase.messaging.default_notification_color", 0);
        if (i != 0) {
            try {
                return Integer.valueOf(ContextCompat.getColor(this.zzx, i));
            } catch (Resources.NotFoundException unused2) {
                Log.w("FirebaseMessaging", "Cannot find the color resource referenced in AndroidManifest.");
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zzi(Bundle bundle) {
        String zza = zza(bundle, "gcm.n.sound2");
        return TextUtils.isEmpty(zza) ? zza(bundle, "gcm.n.sound") : zza;
    }

    private static void zza(Intent intent, Bundle bundle) {
        for (String str : bundle.keySet()) {
            if (str.startsWith("google.c.a.") || str.equals("from")) {
                intent.putExtra(str, bundle.getString(str));
            }
        }
    }

    private final Bundle zzas() {
        Bundle bundle = this.zzdo;
        if (bundle != null) {
            return bundle;
        }
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = this.zzx.getPackageManager().getApplicationInfo(this.zzx.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException unused) {
        }
        if (applicationInfo != null && applicationInfo.metaData != null) {
            Bundle bundle2 = applicationInfo.metaData;
            this.zzdo = bundle2;
            return bundle2;
        }
        return Bundle.EMPTY;
    }
}
