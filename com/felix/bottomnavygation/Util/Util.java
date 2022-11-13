package com.felix.bottomnavygation.Util;

import android.content.Context;
/* loaded from: classes.dex */
public class Util {
    public static int VALUE_SIZE = 28;
    public static int VALUE_SIZE_ACTIVE = 33;

    public static int convertDpToPixel(int i, Context context) {
        return Math.round(i * (context.getResources().getDisplayMetrics().densityDpi / 160.0f));
    }

    public static int convertPixelsToDp(int i, Context context) {
        return Math.round(i / (context.getResources().getDisplayMetrics().densityDpi / 160.0f));
    }
}
