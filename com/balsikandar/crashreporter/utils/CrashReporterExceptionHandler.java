package com.balsikandar.crashreporter.utils;

import java.lang.Thread;
/* loaded from: classes.dex */
public class CrashReporterExceptionHandler implements Thread.UncaughtExceptionHandler {
    private Thread.UncaughtExceptionHandler exceptionHandler = Thread.getDefaultUncaughtExceptionHandler();

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        CrashUtil.saveCrashReport(th);
        this.exceptionHandler.uncaughtException(thread, th);
    }
}
