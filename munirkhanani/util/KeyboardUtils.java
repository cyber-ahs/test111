package munirkhanani.util;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.util.HashMap;
/* loaded from: classes2.dex */
public class KeyboardUtils implements ViewTreeObserver.OnGlobalLayoutListener {
    private static final int MAGIC_NUMBER = 200;
    private static HashMap<SoftKeyboardToggleListener, KeyboardUtils> sListenerMap = new HashMap<>();
    private SoftKeyboardToggleListener mCallback;
    private View mRootView;
    private float mScreenDensity;
    private Boolean prevValue = null;

    /* loaded from: classes2.dex */
    public interface SoftKeyboardToggleListener {
        void onToggleSoftKeyboard(boolean z);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        Rect rect = new Rect();
        this.mRootView.getWindowVisibleDisplayFrame(rect);
        boolean z = ((float) (this.mRootView.getRootView().getHeight() - (rect.bottom - rect.top))) / this.mScreenDensity > 200.0f;
        if (this.mCallback != null) {
            Boolean bool = this.prevValue;
            if (bool == null || z != bool.booleanValue()) {
                this.prevValue = Boolean.valueOf(z);
                this.mCallback.onToggleSoftKeyboard(z);
            }
        }
    }

    public static void addKeyboardToggleListener(Activity activity, SoftKeyboardToggleListener softKeyboardToggleListener) {
        removeKeyboardToggleListener(softKeyboardToggleListener);
        sListenerMap.put(softKeyboardToggleListener, new KeyboardUtils(activity, softKeyboardToggleListener));
    }

    public static void removeKeyboardToggleListener(SoftKeyboardToggleListener softKeyboardToggleListener) {
        if (sListenerMap.containsKey(softKeyboardToggleListener)) {
            sListenerMap.get(softKeyboardToggleListener).removeListener();
            sListenerMap.remove(softKeyboardToggleListener);
        }
    }

    private void removeListener() {
        this.mCallback = null;
        this.mRootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    private KeyboardUtils(Activity activity, SoftKeyboardToggleListener softKeyboardToggleListener) {
        this.mCallback = softKeyboardToggleListener;
        View childAt = ((ViewGroup) activity.findViewById(16908290)).getChildAt(0);
        this.mRootView = childAt;
        childAt.getViewTreeObserver().addOnGlobalLayoutListener(this);
        this.mScreenDensity = activity.getResources().getDisplayMetrics().density;
    }
}
