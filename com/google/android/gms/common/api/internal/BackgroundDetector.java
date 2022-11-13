package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public final class BackgroundDetector implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
    private static final BackgroundDetector zzas = new BackgroundDetector();
    private final AtomicBoolean zzat = new AtomicBoolean();
    private final AtomicBoolean zzau = new AtomicBoolean();
    private final ArrayList<BackgroundStateChangeListener> zzav = new ArrayList<>();
    private boolean zzaw = false;

    /* loaded from: classes.dex */
    public interface BackgroundStateChangeListener {
        void onBackgroundStateChanged(boolean z);
    }

    private BackgroundDetector() {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }

    public static BackgroundDetector getInstance() {
        return zzas;
    }

    public static void initialize(Application application) {
        BackgroundDetector backgroundDetector = zzas;
        synchronized (backgroundDetector) {
            if (!backgroundDetector.zzaw) {
                application.registerActivityLifecycleCallbacks(backgroundDetector);
                application.registerComponentCallbacks(backgroundDetector);
                backgroundDetector.zzaw = true;
            }
        }
    }

    public final boolean readCurrentStateIfPossible(boolean z) {
        if (!this.zzau.get()) {
            if (!PlatformVersion.isAtLeastJellyBean()) {
                return z;
            }
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            if (!this.zzau.getAndSet(true) && runningAppProcessInfo.importance > 100) {
                this.zzat.set(true);
            }
        }
        return isInBackground();
    }

    public final boolean isInBackground() {
        return this.zzat.get();
    }

    public final void addListener(BackgroundStateChangeListener backgroundStateChangeListener) {
        synchronized (zzas) {
            this.zzav.add(backgroundStateChangeListener);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        boolean compareAndSet = this.zzat.compareAndSet(true, false);
        this.zzau.set(true);
        if (compareAndSet) {
            onBackgroundStateChanged(false);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        boolean compareAndSet = this.zzat.compareAndSet(true, false);
        this.zzau.set(true);
        if (compareAndSet) {
            onBackgroundStateChanged(false);
        }
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int i) {
        if (i == 20 && this.zzat.compareAndSet(false, true)) {
            this.zzau.set(true);
            onBackgroundStateChanged(true);
        }
    }

    private final void onBackgroundStateChanged(boolean z) {
        synchronized (zzas) {
            ArrayList<BackgroundStateChangeListener> arrayList = this.zzav;
            ArrayList<BackgroundStateChangeListener> arrayList2 = arrayList;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                BackgroundStateChangeListener backgroundStateChangeListener = arrayList.get(i);
                i++;
                backgroundStateChangeListener.onBackgroundStateChanged(z);
            }
        }
    }
}
