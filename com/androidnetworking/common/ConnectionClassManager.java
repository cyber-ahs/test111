package com.androidnetworking.common;

import com.androidnetworking.core.Core;
import com.androidnetworking.interfaces.ConnectionQualityChangeListener;
/* loaded from: classes.dex */
public class ConnectionClassManager {
    private static final long BANDWIDTH_LOWER_BOUND = 10;
    private static final int BYTES_TO_BITS = 8;
    private static final int DEFAULT_GOOD_BANDWIDTH = 2000;
    private static final int DEFAULT_MODERATE_BANDWIDTH = 550;
    private static final int DEFAULT_POOR_BANDWIDTH = 150;
    private static final int DEFAULT_SAMPLES_TO_QUALITY_CHANGE = 5;
    private static final int MINIMUM_SAMPLES_TO_DECIDE_QUALITY = 2;
    private static ConnectionClassManager sInstance;
    private ConnectionQualityChangeListener mConnectionQualityChangeListener;
    private ConnectionQuality mCurrentConnectionQuality = ConnectionQuality.UNKNOWN;
    private int mCurrentBandwidthForSampling = 0;
    private int mCurrentNumberOfSample = 0;
    private int mCurrentBandwidth = 0;

    public static ConnectionClassManager getInstance() {
        if (sInstance == null) {
            synchronized (ConnectionClassManager.class) {
                if (sInstance == null) {
                    sInstance = new ConnectionClassManager();
                }
            }
        }
        return sInstance;
    }

    public synchronized void updateBandwidth(long j, long j2) {
        if (j2 != 0 && j >= 20000) {
            double d = j;
            Double.isNaN(d);
            double d2 = j2;
            Double.isNaN(d2);
            double d3 = ((d * 1.0d) / d2) * 8.0d;
            if (d3 >= 10.0d) {
                int i = this.mCurrentBandwidthForSampling;
                int i2 = this.mCurrentNumberOfSample;
                double d4 = i * i2;
                Double.isNaN(d4);
                double d5 = d4 + d3;
                double d6 = i2 + 1;
                Double.isNaN(d6);
                this.mCurrentBandwidthForSampling = (int) (d5 / d6);
                int i3 = i2 + 1;
                this.mCurrentNumberOfSample = i3;
                if (i3 == 5 || (this.mCurrentConnectionQuality == ConnectionQuality.UNKNOWN && this.mCurrentNumberOfSample == 2)) {
                    ConnectionQuality connectionQuality = this.mCurrentConnectionQuality;
                    int i4 = this.mCurrentBandwidthForSampling;
                    this.mCurrentBandwidth = i4;
                    if (i4 <= 0) {
                        this.mCurrentConnectionQuality = ConnectionQuality.UNKNOWN;
                    } else if (i4 < DEFAULT_POOR_BANDWIDTH) {
                        this.mCurrentConnectionQuality = ConnectionQuality.POOR;
                    } else if (i4 < DEFAULT_MODERATE_BANDWIDTH) {
                        this.mCurrentConnectionQuality = ConnectionQuality.MODERATE;
                    } else if (i4 < DEFAULT_GOOD_BANDWIDTH) {
                        this.mCurrentConnectionQuality = ConnectionQuality.GOOD;
                    } else if (i4 > DEFAULT_GOOD_BANDWIDTH) {
                        this.mCurrentConnectionQuality = ConnectionQuality.EXCELLENT;
                    }
                    if (this.mCurrentNumberOfSample == 5) {
                        this.mCurrentBandwidthForSampling = 0;
                        this.mCurrentNumberOfSample = 0;
                    }
                    if (this.mCurrentConnectionQuality != connectionQuality && this.mConnectionQualityChangeListener != null) {
                        Core.getInstance().getExecutorSupplier().forMainThreadTasks().execute(new Runnable() { // from class: com.androidnetworking.common.ConnectionClassManager.1
                            @Override // java.lang.Runnable
                            public void run() {
                                ConnectionClassManager.this.mConnectionQualityChangeListener.onChange(ConnectionClassManager.this.mCurrentConnectionQuality, ConnectionClassManager.this.mCurrentBandwidth);
                            }
                        });
                    }
                }
            }
        }
    }

    public int getCurrentBandwidth() {
        return this.mCurrentBandwidth;
    }

    public ConnectionQuality getCurrentConnectionQuality() {
        return this.mCurrentConnectionQuality;
    }

    public void setListener(ConnectionQualityChangeListener connectionQualityChangeListener) {
        this.mConnectionQualityChangeListener = connectionQualityChangeListener;
    }

    public void removeListener() {
        this.mConnectionQualityChangeListener = null;
    }

    public static void shutDown() {
        if (sInstance != null) {
            sInstance = null;
        }
    }
}
