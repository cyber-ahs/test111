package com.androidnetworking.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.androidnetworking.model.Progress;
/* loaded from: classes.dex */
public class DownloadProgressHandler extends Handler {
    private final DownloadProgressListener mDownloadProgressListener;

    public DownloadProgressHandler(DownloadProgressListener downloadProgressListener) {
        super(Looper.getMainLooper());
        this.mDownloadProgressListener = downloadProgressListener;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message.what == 1) {
            if (this.mDownloadProgressListener != null) {
                Progress progress = (Progress) message.obj;
                this.mDownloadProgressListener.onProgress(progress.currentBytes, progress.totalBytes);
                return;
            }
            return;
        }
        super.handleMessage(message);
    }
}
