package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes.dex */
public final class ListenerHolder<L> {
    private final zaa zaji;
    private volatile L zajj;
    private final ListenerKey<L> zajk;

    /* loaded from: classes.dex */
    public interface Notifier<L> {
        void notifyListener(L l);

        void onNotifyListenerFailed();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ListenerHolder(Looper looper, L l, String str) {
        this.zaji = new zaa(looper);
        this.zajj = (L) Preconditions.checkNotNull(l, "Listener must not be null");
        this.zajk = new ListenerKey<>(l, Preconditions.checkNotEmpty(str));
    }

    /* loaded from: classes.dex */
    private final class zaa extends com.google.android.gms.internal.base.zal {
        public zaa(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            Preconditions.checkArgument(message.what == 1);
            ListenerHolder.this.notifyListenerInternal((Notifier) message.obj);
        }
    }

    /* loaded from: classes.dex */
    public static final class ListenerKey<L> {
        private final L zajj;
        private final String zajm;

        /* JADX INFO: Access modifiers changed from: package-private */
        public ListenerKey(L l, String str) {
            this.zajj = l;
            this.zajm = str;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ListenerKey) {
                ListenerKey listenerKey = (ListenerKey) obj;
                return this.zajj == listenerKey.zajj && this.zajm.equals(listenerKey.zajm);
            }
            return false;
        }

        public final int hashCode() {
            return (System.identityHashCode(this.zajj) * 31) + this.zajm.hashCode();
        }
    }

    public final void notifyListener(Notifier<? super L> notifier) {
        Preconditions.checkNotNull(notifier, "Notifier must not be null");
        this.zaji.sendMessage(this.zaji.obtainMessage(1, notifier));
    }

    public final boolean hasListener() {
        return this.zajj != null;
    }

    public final void clear() {
        this.zajj = null;
    }

    public final ListenerKey<L> getListenerKey() {
        return this.zajk;
    }

    final void notifyListenerInternal(Notifier<? super L> notifier) {
        Object obj = (L) this.zajj;
        if (obj == null) {
            notifier.onNotifyListenerFailed();
            return;
        }
        try {
            notifier.notifyListener(obj);
        } catch (RuntimeException e) {
            notifier.onNotifyListenerFailed();
            throw e;
        }
    }
}
