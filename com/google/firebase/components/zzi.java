package com.google.firebase.components;

import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
/* loaded from: classes.dex */
public final /* synthetic */ class zzi implements Runnable {
    private final Map.Entry zza;
    private final Event zzb;

    private zzi(Map.Entry entry, Event event) {
        this.zza = entry;
        this.zzb = event;
    }

    public static Runnable zza(Map.Entry entry, Event event) {
        return new zzi(entry, event);
    }

    @Override // java.lang.Runnable
    public final void run() {
        ((EventHandler) this.zza.getKey()).handle(this.zzb);
    }
}
