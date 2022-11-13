package com.google.firebase.components;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
/* loaded from: classes.dex */
public final class Component<T> {
    private final Set<Class<? super T>> zza;
    private final Set<Dependency> zzb;
    private final int zzc;
    private final ComponentFactory<T> zzd;
    private final Set<Class<?>> zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object zza(Object obj) {
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object zzb(Object obj) {
        return obj;
    }

    /* synthetic */ Component(Set set, Set set2, int i, ComponentFactory componentFactory, Set set3, byte b) {
        this(set, set2, i, componentFactory, set3);
    }

    private Component(Set<Class<? super T>> set, Set<Dependency> set2, int i, ComponentFactory<T> componentFactory, Set<Class<?>> set3) {
        this.zza = Collections.unmodifiableSet(set);
        this.zzb = Collections.unmodifiableSet(set2);
        this.zzc = i;
        this.zzd = componentFactory;
        this.zze = Collections.unmodifiableSet(set3);
    }

    public final Set<Class<? super T>> zza() {
        return this.zza;
    }

    public final Set<Dependency> zzb() {
        return this.zzb;
    }

    public final ComponentFactory<T> zzc() {
        return this.zzd;
    }

    public final Set<Class<?>> zzd() {
        return this.zze;
    }

    public final boolean zze() {
        return this.zzc == 1;
    }

    public final boolean zzf() {
        return this.zzc == 2;
    }

    public final String toString() {
        return "Component<" + Arrays.toString(this.zza.toArray()) + ">{" + this.zzc + ", deps=" + Arrays.toString(this.zzb.toArray()) + "}";
    }

    public static <T> Builder<T> builder(Class<T> cls) {
        return new Builder<>(cls, new Class[0], (byte) 0);
    }

    public static <T> Builder<T> builder(Class<T> cls, Class<? super T>... clsArr) {
        return new Builder<>(cls, clsArr, (byte) 0);
    }

    @Deprecated
    public static <T> Component<T> of(Class<T> cls, T t) {
        return builder(cls).factory(zzb.zza(t)).build();
    }

    @SafeVarargs
    public static <T> Component<T> of(T t, Class<T> cls, Class<? super T>... clsArr) {
        return builder(cls, clsArr).factory(zzc.zza(t)).build();
    }

    /* compiled from: com.google.firebase:firebase-common@@16.0.2 */
    /* loaded from: classes.dex */
    public static class Builder<T> {
        private final Set<Class<? super T>> zza;
        private final Set<Dependency> zzb;
        private int zzc;
        private ComponentFactory<T> zzd;
        private Set<Class<?>> zze;

        /* synthetic */ Builder(Class cls, Class[] clsArr, byte b) {
            this(cls, clsArr);
        }

        private Builder(Class<T> cls, Class<? super T>... clsArr) {
            HashSet hashSet = new HashSet();
            this.zza = hashSet;
            this.zzb = new HashSet();
            this.zzc = 0;
            this.zze = new HashSet();
            Preconditions.checkNotNull(cls, "Null interface");
            hashSet.add(cls);
            for (Class<? super T> cls2 : clsArr) {
                Preconditions.checkNotNull(cls2, "Null interface");
            }
            Collections.addAll(this.zza, clsArr);
        }

        public Builder<T> add(Dependency dependency) {
            Preconditions.checkNotNull(dependency, "Null dependency");
            Preconditions.checkArgument(!this.zza.contains(dependency.zza()), "Components are not allowed to depend on interfaces they themselves provide.");
            this.zzb.add(dependency);
            return this;
        }

        public Builder<T> alwaysEager() {
            return zza(1);
        }

        public Builder<T> eagerInDefaultApp() {
            return zza(2);
        }

        public Builder<T> publishes(Class<?> cls) {
            this.zze.add(cls);
            return this;
        }

        private Builder<T> zza(int i) {
            Preconditions.checkState(this.zzc == 0, "Instantiation type has already been set.");
            this.zzc = i;
            return this;
        }

        public Builder<T> factory(ComponentFactory<T> componentFactory) {
            this.zzd = (ComponentFactory) Preconditions.checkNotNull(componentFactory, "Null factory");
            return this;
        }

        public Component<T> build() {
            Preconditions.checkState(this.zzd != null, "Missing required property: factory.");
            return new Component<>(new HashSet(this.zza), new HashSet(this.zzb), this.zzc, this.zzd, this.zze, (byte) 0);
        }
    }

    /* compiled from: com.google.firebase:firebase-common@@16.0.2 */
    /* renamed from: com.google.firebase.components.Component$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1<T> {
        private final T zza;
        private final zze<T> zzb;

        public static AnonymousClass1<Context> zza(Context context) {
            return new AnonymousClass1<>(context, new zzd((byte) 0));
        }

        private AnonymousClass1(T t, zze<T> zzeVar) {
            this.zza = t;
            this.zzb = zzeVar;
        }

        public List<ComponentRegistrar> zza() {
            return zzb(this.zzb.zza(this.zza));
        }

        private static List<ComponentRegistrar> zzb(List<String> list) {
            ArrayList arrayList = new ArrayList();
            for (String str : list) {
                try {
                    Class<?> cls = Class.forName(str);
                    if (!ComponentRegistrar.class.isAssignableFrom(cls)) {
                        Log.w("ComponentDiscovery", String.format("Class %s is not an instance of %s", str, "com.google.firebase.components.ComponentRegistrar"));
                    } else {
                        arrayList.add((ComponentRegistrar) cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
                    }
                } catch (ClassNotFoundException e) {
                    Log.w("ComponentDiscovery", String.format("Class %s is not an found.", str), e);
                } catch (IllegalAccessException e2) {
                    Log.w("ComponentDiscovery", String.format("Could not instantiate %s.", str), e2);
                } catch (InstantiationException e3) {
                    Log.w("ComponentDiscovery", String.format("Could not instantiate %s.", str), e3);
                } catch (NoSuchMethodException e4) {
                    Log.w("ComponentDiscovery", String.format("Could not instantiate %s", str), e4);
                } catch (InvocationTargetException e5) {
                    Log.w("ComponentDiscovery", String.format("Could not instantiate %s", str), e5);
                }
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static List<Component<?>> zza(List<Component<?>> list) {
            zzg zzgVar;
            HashMap hashMap = new HashMap(list.size());
            for (Component<?> component : list) {
                zzg zzgVar2 = new zzg(component);
                for (Class<? super Object> cls : component.zza()) {
                    if (hashMap.put(cls, zzgVar2) != null) {
                        throw new IllegalArgumentException(String.format("Multiple components provide %s.", cls));
                    }
                }
            }
            for (zzg zzgVar3 : hashMap.values()) {
                for (Dependency dependency : zzgVar3.zzb().zzb()) {
                    if (dependency.zzc() && (zzgVar = (zzg) hashMap.get(dependency.zza())) != null) {
                        zzgVar3.zza(zzgVar);
                        zzgVar.zzb(zzgVar3);
                    }
                }
            }
            HashSet<zzg> hashSet = new HashSet(hashMap.values());
            Set<zzg> zza = zza(hashSet);
            ArrayList arrayList = new ArrayList();
            while (!zza.isEmpty()) {
                zzg next = zza.iterator().next();
                zza.remove(next);
                arrayList.add(next.zzb());
                for (zzg zzgVar4 : next.zza()) {
                    zzgVar4.zzc(next);
                    if (zzgVar4.zzc()) {
                        zza.add(zzgVar4);
                    }
                }
            }
            if (arrayList.size() == list.size()) {
                Collections.reverse(arrayList);
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            for (zzg zzgVar5 : hashSet) {
                if (!zzgVar5.zzc() && !zzgVar5.zzd()) {
                    arrayList2.add(zzgVar5.zzb());
                }
            }
            throw new DependencyCycleException(arrayList2);
        }

        private static Set<zzg> zza(Set<zzg> set) {
            HashSet hashSet = new HashSet();
            for (zzg zzgVar : set) {
                if (zzgVar.zzc()) {
                    hashSet.add(zzgVar);
                }
            }
            return hashSet;
        }
    }
}
