package com.balsikandar.crashreporter.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.core.os.EnvironmentCompat;
import com.google.android.gms.common.internal.AccountType;
import java.util.TimeZone;
import java.util.UUID;
/* loaded from: classes.dex */
public class AppUtils {
    private static String getCurrentLauncherApp(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        try {
            ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
            return (resolveActivity == null || resolveActivity.activityInfo == null) ? "" : resolveActivity.activityInfo.packageName;
        } catch (Exception e) {
            Log.e("AppUtils", "Exception : " + e.getMessage());
            return "";
        }
    }

    private static String getUserIdentity(Context context) {
        if (ActivityCompat.checkSelfPermission(context, "android.permission.GET_ACCOUNTS") == 0) {
            Account[] accounts = ((AccountManager) context.getSystemService("account")).getAccounts();
            String str = null;
            int length = accounts.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Account account = accounts[i];
                if (account.type.equalsIgnoreCase(AccountType.GOOGLE)) {
                    str = account.name;
                    break;
                }
                i++;
            }
            return str != null ? str : "";
        }
        return "";
    }

    public static String getDeviceDetails(Context context) {
        return "Device Information\n\nDEVICE.ID : " + getDeviceId(context) + "\nUSER.ID : " + getUserIdentity(context) + "\nAPP.VERSION : " + getAppVersion(context) + "\nLAUNCHER.APP : " + getCurrentLauncherApp(context) + "\nTIMEZONE : " + timeZone() + "\nVERSION.RELEASE : " + Build.VERSION.RELEASE + "\nVERSION.INCREMENTAL : " + Build.VERSION.INCREMENTAL + "\nVERSION.SDK.NUMBER : " + Build.VERSION.SDK_INT + "\nBOARD : " + Build.BOARD + "\nBOOTLOADER : " + Build.BOOTLOADER + "\nBRAND : " + Build.BRAND + "\nCPU_ABI : " + Build.CPU_ABI + "\nCPU_ABI2 : " + Build.CPU_ABI2 + "\nDISPLAY : " + Build.DISPLAY + "\nFINGERPRINT : " + Build.FINGERPRINT + "\nHARDWARE : " + Build.HARDWARE + "\nHOST : " + Build.HOST + "\nID : " + Build.ID + "\nMANUFACTURER : " + Build.MANUFACTURER + "\nMODEL : " + Build.MODEL + "\nPRODUCT : " + Build.PRODUCT + "\nSERIAL : " + Build.SERIAL + "\nTAGS : " + Build.TAGS + "\nTIME : " + Build.TIME + "\nTYPE : " + Build.TYPE + "\nUNKNOWN : " + EnvironmentCompat.MEDIA_UNKNOWN + "\nUSER : " + Build.USER;
    }

    private static String timeZone() {
        return TimeZone.getDefault().getID();
    }

    private static String getDeviceId(Context context) {
        String androidDeviceId = getAndroidDeviceId(context);
        return androidDeviceId == null ? UUID.randomUUID().toString() : androidDeviceId;
    }

    private static String getAndroidDeviceId(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        if (string == null || string.toLowerCase().equals("9774d56d682e549c")) {
            return null;
        }
        return string;
    }

    private static int getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Could not get package name: " + e);
        }
    }
}
