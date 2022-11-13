package com.google.android.gms.common;

import android.content.Intent;
/* loaded from: classes.dex */
public class GooglePlayServicesRepairableException extends UserRecoverableException {
    private final int zzaf;

    public GooglePlayServicesRepairableException(int i, String str, Intent intent) {
        super(str, intent);
        this.zzaf = i;
    }

    public int getConnectionStatusCode() {
        return this.zzaf;
    }
}
