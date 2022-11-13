package com.balsikandar.crashreporter.utils;
/* loaded from: classes.dex */
public class CrashReporterNotInitializedException extends CrashReporterException {
    static final long serialVersionUID = 1;

    public CrashReporterNotInitializedException() {
    }

    public CrashReporterNotInitializedException(String str) {
        super(str);
    }

    public CrashReporterNotInitializedException(String str, Throwable th) {
        super(str, th);
    }

    public CrashReporterNotInitializedException(Throwable th) {
        super(th);
    }
}
