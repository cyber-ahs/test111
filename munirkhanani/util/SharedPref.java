package munirkhanani.util;

import android.content.Context;
import android.content.SharedPreferences;
/* loaded from: classes2.dex */
public class SharedPref {
    private static String IndicesObject = "IndicesObject";
    private static String isAllUsersMessagesEnabled = "isAllUsersMessagesEnabled";
    private static String isBiometricEnabled = "isBiometricEnabled";
    private static String isRowShowString = "isRowShow";
    private static String isSavedPin = "SavedPin";
    static SharedPreferences sharedPreferences = null;
    private static String theme_name = "theme_name";

    public static void getInstance(Context context) {
        try {
            sharedPreferences = context.getSharedPreferences("SharedPref", 0);
        } catch (Exception unused) {
        }
    }

    public static void setIndicesObject(String str) {
        try {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(IndicesObject, str);
            edit.apply();
        } catch (Exception unused) {
        }
    }

    public static String getIndicesObject() {
        return sharedPreferences.getString(IndicesObject, "");
    }

    public static void setSavedPin(String str) {
        try {
            SharedPreferences sharedPreferences2 = sharedPreferences;
            if (sharedPreferences2 != null) {
                SharedPreferences.Editor edit = sharedPreferences2.edit();
                edit.putString(isSavedPin, str);
                edit.apply();
            }
        } catch (Exception unused) {
        }
    }

    public static String getSavedPin() {
        try {
            return sharedPreferences.getString(isSavedPin, "");
        } catch (Exception unused) {
            return "";
        }
    }

    public static void setRowShow(Boolean bool) {
        try {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean(isRowShowString, bool.booleanValue());
            edit.apply();
        } catch (Exception unused) {
        }
    }

    public static Boolean getRowShow() {
        try {
            return Boolean.valueOf(sharedPreferences.getBoolean(isRowShowString, true));
        } catch (Exception unused) {
            return false;
        }
    }

    public static void setIsBiometricEnabled(Boolean bool) {
        try {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean(isBiometricEnabled, bool.booleanValue());
            edit.apply();
        } catch (Exception unused) {
        }
    }

    public static Boolean getIsBiometricEnabled() {
        try {
            return Boolean.valueOf(sharedPreferences.getBoolean(isBiometricEnabled, false));
        } catch (Exception unused) {
            return false;
        }
    }

    public static void setIsAllUsersMessagesEnabled(Boolean bool) {
        try {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean(isAllUsersMessagesEnabled, bool.booleanValue());
            edit.apply();
        } catch (Exception unused) {
        }
    }

    public static Boolean getIsAllUsersMessagesEnabled() {
        try {
            return Boolean.valueOf(sharedPreferences.getBoolean(isAllUsersMessagesEnabled, false));
        } catch (Exception unused) {
            return false;
        }
    }

    public static String getTheme_name() {
        try {
            return sharedPreferences.getString(theme_name, "");
        } catch (Exception unused) {
            return "";
        }
    }

    public static void setTheme_name(String str) {
        try {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(str, str);
            edit.apply();
        } catch (Exception unused) {
        }
    }
}
