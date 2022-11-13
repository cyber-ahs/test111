package com.google.firebase.components;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.components.Component;
import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import com.google.firebase.inject.Provider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
/* loaded from: classes.dex */
public final class zzf extends zza {
    private final List<Component<?>> zza;
    private final Map<Class<?>, zzj<?>> zzb = new HashMap();
    private final zzh zzc;

    @Override // com.google.firebase.components.zza, com.google.firebase.components.ComponentContainer
    public final /* bridge */ /* synthetic */ Object get(Class cls) {
        return super.get(cls);
    }

    public zzf(Executor executor, Iterable<ComponentRegistrar> iterable, Component<?>... componentArr) {
        zzh zzhVar = new zzh(executor);
        this.zzc = zzhVar;
        ArrayList arrayList = new ArrayList();
        arrayList.add(Component.of(zzhVar, zzh.class, Subscriber.class, Publisher.class));
        for (ComponentRegistrar componentRegistrar : iterable) {
            arrayList.addAll(componentRegistrar.getComponents());
        }
        Collections.addAll(arrayList, componentArr);
        List<Component<?>> unmodifiableList = Collections.unmodifiableList(Component.AnonymousClass1.zza(arrayList));
        this.zza = unmodifiableList;
        for (Component<?> component : unmodifiableList) {
            zza(component);
        }
        zza();
    }

    @Override // com.google.firebase.components.ComponentContainer
    public final <T> Provider<T> getProvider(Class<T> cls) {
        Preconditions.checkNotNull(cls, "Null interface requested.");
        return this.zzb.get(cls);
    }

    public final void zza(boolean z) {
        for (Component<?> component : this.zza) {
            if (component.zze() || (component.zzf() && z)) {
                get(component.zza().iterator().next());
            }
        }
        this.zzc.zza();
    }

    private <T> void zza(Component<T> component) {
        zzj<?> zzjVar = new zzj<>(component.zzc(), new zzl(component, this));
        for (Class<? super T> cls : component.zza()) {
            this.zzb.put(cls, zzjVar);
        }
    }

    private void zza() {
        for (Component<?> component : this.zza) {
            for (Dependency dependency : component.zzb()) {
                if (dependency.zzb() && !this.zzb.containsKey(dependency.zza())) {
                    throw new MissingDependencyException(String.format("Unsatisfied dependency for component %s: %s", component, dependency.zza()));
                }
            }
        }
    }
}
