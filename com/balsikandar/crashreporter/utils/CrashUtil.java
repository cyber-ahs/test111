package com.balsikandar.crashreporter.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.balsikandar.crashreporter.CrashReporter;
import com.balsikandar.crashreporter.R;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/* loaded from: classes.dex */
public class CrashUtil {
    private static final String TAG = "CrashUtil";

    private CrashUtil() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getCrashLogTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
    }

    public static void saveCrashReport(Throwable th) {
        String crashReportPath = CrashReporter.getCrashReportPath();
        writeToFile(crashReportPath, getCrashLogTime() + Constants.CRASH_SUFFIX + Constants.FILE_EXTENSION, getStackTrace(th));
        showNotification(th.getLocalizedMessage(), true);
    }

    public static void logException(final Exception exc) {
        new Thread(new Runnable() { // from class: com.balsikandar.crashreporter.utils.CrashUtil.1
            @Override // java.lang.Runnable
            public void run() {
                String crashReportPath = CrashReporter.getCrashReportPath();
                CrashUtil.writeToFile(crashReportPath, CrashUtil.getCrashLogTime() + Constants.EXCEPTION_SUFFIX + Constants.FILE_EXTENSION, CrashUtil.getStackTrace(exc));
                CrashUtil.showNotification(exc.getLocalizedMessage(), false);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void writeToFile(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            str = getDefaultPath();
        }
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            str = getDefaultPath();
            String str4 = TAG;
            Log.e(str4, "Path provided doesn't exists : " + file + "\nSaving crash report at : " + getDefaultPath());
        }
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str + File.separator + str2));
            bufferedWriter.write(str3);
            bufferedWriter.flush();
            bufferedWriter.close();
            String str5 = TAG;
            Log.d(str5, "crash report saved in : " + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void showNotification(String str, boolean z) {
        if (CrashReporter.isNotificationEnabled()) {
            Context context = CrashReporter.getContext();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setSmallIcon(R.drawable.ic_warning_black_24dp);
            Intent launchIntent = CrashReporter.getLaunchIntent();
            launchIntent.putExtra(Constants.LANDING, z);
            launchIntent.setAction(Long.toString(System.currentTimeMillis()));
            builder.setContentIntent(PendingIntent.getActivity(context, 0, launchIntent, 0));
            builder.setContentTitle(context.getString(R.string.view_crash_report));
            if (TextUtils.isEmpty(str)) {
                builder.setContentText(context.getString(R.string.check_your_message_here));
            } else {
                builder.setContentText(str);
            }
            builder.setAutoCancel(true);
            builder.setColor(ContextCompat.getColor(context, R.color.colorAccent_CrashReporter));
            ((NotificationManager) context.getSystemService("notification")).notify(1, builder.build());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getStackTrace(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        String obj = stringWriter.toString();
        printWriter.close();
        return obj;
    }

    public static String getDefaultPath() {
        String str = CrashReporter.getContext().getExternalFilesDir(null).getAbsolutePath() + File.separator + Constants.CRASH_REPORT_DIR;
        new File(str).mkdirs();
        return str;
    }
}
