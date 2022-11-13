package com.google.firebase.components;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
/* loaded from: classes.dex */
final class zzd implements zze<Context> {
    private zzd() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzd(byte b) {
        this();
    }

    @Override // com.google.firebase.components.zze
    public final /* synthetic */ List zza(Context context) {
        Bundle zza2 = zza2(context);
        if (zza2 == null) {
            Log.w("ComponentDiscovery", "Could not retrieve metadata, returning empty list of registrars.");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (String str : zza2.keySet()) {
            if ("com.google.firebase.components.ComponentRegistrar".equals(zza2.get(str)) && str.startsWith("com.google.firebase.components:")) {
                arrayList.add(str.substring(31));
            }
        }
        return arrayList;
    }

    /* renamed from: zza  reason: avoid collision after fix types in other method */
    private static Bundle zza2(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                Log.w("ComponentDiscovery", "Context has no PackageManager.");
                return null;
            }
            ServiceInfo serviceInfo = packageManager.getServiceInfo(new ComponentName(context, ComponentDiscoveryService.class), 128);
            if (serviceInfo == null) {
                Log.w("ComponentDiscovery", "ComponentDiscoveryService has no service info.");
                return null;
            }
            return serviceInfo.metaData;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w("ComponentDiscovery", "Application info not found.");
            return null;
        }
    }
}
