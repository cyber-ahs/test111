package com.balsikandar.crashreporter.utils;
/* loaded from: classes.dex */
public class CrashReporterException extends RuntimeException {
    static final long serialVersionUID = 1;

    public CrashReporterException() {
    }

    public CrashReporterException(String str) {
        super(str);
    }

    public CrashReporterException(String str, Object... objArr) {
        this(String.format(str, objArr));
    }

    public CrashReporterException(String str, Throwable th) {
        super(str, th);
    }

    public CrashReporterException(Throwable th) {
        super(th);
    }

    @Override // java.lang.Throwable
    public String toString() {
        return getMessage();
    }
}
