package com.balsikandar.crashreporter.utils;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/* loaded from: classes.dex */
public class FileUtils {
    public static final String TAG = "FileUtils";

    private FileUtils() {
    }

    public static boolean delete(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return delete(new File(str));
    }

    public static boolean delete(File file) {
        boolean z = true;
        if (exists(file)) {
            if (file.isFile()) {
                return file.delete();
            }
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                return false;
            }
            for (File file2 : listFiles) {
                z |= delete(file2);
            }
            return file.delete() | z;
        }
        return true;
    }

    public static boolean exists(File file) {
        return file != null && file.exists();
    }

    public static String cleanPath(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            return new File(str).getCanonicalPath();
        } catch (Exception unused) {
            return str;
        }
    }

    public static final String getParent(File file) {
        if (file == null) {
            return null;
        }
        return file.getParent();
    }

    public static final String getParent(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return getParent(new File(cleanPath(str)));
    }

    public static boolean deleteFiles(String str) {
        if (TextUtils.isEmpty(str)) {
            str = CrashUtil.getDefaultPath();
        }
        return delete(str);
    }

    public static String readFirstLineFromFile(File file) {
        String str = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            str = bufferedReader.readLine();
            bufferedReader.close();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return str;
        }
    }

    public static String readFromFile(File file) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
                sb.append('\n');
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
