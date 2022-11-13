package com.google.firebase.components;

import com.google.firebase.inject.Provider;
/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
/* loaded from: classes.dex */
final class zzj<T> implements Provider<T> {
    private static final Object zza = new Object();
    private volatile Object zzb = zza;
    private volatile Provider<T> zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzj(ComponentFactory<T> componentFactory, ComponentContainer componentContainer) {
        this.zzc = zzk.zza(componentFactory, componentContainer);
    }

    @Override // com.google.firebase.inject.Provider
    public final T get() {
        T t = (T) this.zzb;
        Object obj = zza;
        if (t == obj) {
            synchronized (this) {
                t = this.zzb;
                if (t == obj) {
                    t = this.zzc.get();
                    this.zzb = t;
                    this.zzc = null;
                }
            }
        }
        return t;
    }
}
