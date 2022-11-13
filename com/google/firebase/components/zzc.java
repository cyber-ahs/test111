package com.google.firebase.components;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
/* loaded from: classes.dex */
public final /* synthetic */ class zzc implements ComponentFactory {
    private final Object zza;

    private zzc(Object obj) {
        this.zza = obj;
    }

    public static ComponentFactory zza(Object obj) {
        return new zzc(obj);
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer componentContainer) {
        return Component.zza(this.zza);
    }
}
