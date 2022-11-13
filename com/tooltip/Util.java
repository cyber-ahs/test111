package com.tooltip;

import android.content.res.Resources;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.core.view.GravityCompat;
/* loaded from: classes.dex */
final class Util {
    public static int gravityToArrowDirection(int i) {
        if (i != 48) {
            if (i != 80) {
                return i != 8388611 ? i != 8388613 ? i : GravityCompat.START : GravityCompat.END;
            }
            return 48;
        }
        return 80;
    }

    Util() {
    }

    public static RectF calculateRectOnScreen(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return new RectF(iArr[0], iArr[1], iArr[0] + view.getMeasuredWidth(), iArr[1] + view.getMeasuredHeight());
    }

    public static RectF calculateRectInWindow(View view) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        return new RectF(iArr[0], iArr[1], iArr[0] + view.getMeasuredWidth(), iArr[1] + view.getMeasuredHeight());
    }

    public static float pxToDp(float f) {
        return f / Resources.getSystem().getDisplayMetrics().density;
    }

    public static float dpToPx(float f) {
        return f * Resources.getSystem().getDisplayMetrics().density;
    }

    public static void removeOnGlobalLayoutListener(View view, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
        } else {
            view.getViewTreeObserver().removeGlobalOnLayoutListener(onGlobalLayoutListener);
        }
    }
}
