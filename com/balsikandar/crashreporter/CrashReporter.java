package com.balsikandar.crashreporter;

import android.content.Context;
import android.content.Intent;
import com.balsikandar.crashreporter.ui.CrashReporterActivity;
import com.balsikandar.crashreporter.utils.CrashReporterExceptionHandler;
import com.balsikandar.crashreporter.utils.CrashReporterNotInitializedException;
import com.balsikandar.crashreporter.utils.CrashUtil;
/* loaded from: classes.dex */
public class CrashReporter {
    private static Context applicationContext = null;
    private static String crashReportPath = null;
    private static boolean isNotificationEnabled = true;

    private CrashReporter() {
    }

    public static void initialize(Context context) {
        applicationContext = context;
        setUpExceptionHandler();
    }

    public static void initialize(Context context, String str) {
        applicationContext = context;
        crashReportPath = str;
        setUpExceptionHandler();
    }

    private static void setUpExceptionHandler() {
        if (Thread.getDefaultUncaughtExceptionHandler() instanceof CrashReporterExceptionHandler) {
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler(new CrashReporterExceptionHandler());
    }

    public static Context getContext() {
        if (applicationContext == null) {
            try {
                throw new CrashReporterNotInitializedException("Initialize CrashReporter : call CrashReporter.initialize(context, crashReportPath)");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return applicationContext;
    }

    public static String getCrashReportPath() {
        return crashReportPath;
    }

    public static boolean isNotificationEnabled() {
        return isNotificationEnabled;
    }

    public static void logException(Exception exc) {
        CrashUtil.logException(exc);
    }

    public static Intent getLaunchIntent() {
        return new Intent(applicationContext, CrashReporterActivity.class).setFlags(268435456);
    }

    public static void disableNotification() {
        isNotificationEnabled = false;
    }
}
