package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.BiConsumer;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes.dex */
public abstract class TaskApiCall<A extends Api.AnyClient, ResultT> {
    private final Feature[] zakd;
    private final boolean zakk;

    @Deprecated
    public TaskApiCall() {
        this.zakd = null;
        this.zakk = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void doExecute(A a, TaskCompletionSource<ResultT> taskCompletionSource) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Builder<A extends Api.AnyClient, ResultT> {
        private Feature[] zakd;
        private boolean zakk;
        private RemoteCall<A, TaskCompletionSource<ResultT>> zakl;

        private Builder() {
            this.zakk = true;
        }

        @Deprecated
        public Builder<A, ResultT> execute(final BiConsumer<A, TaskCompletionSource<ResultT>> biConsumer) {
            this.zakl = new RemoteCall(biConsumer) { // from class: com.google.android.gms.common.api.internal.zacj
                private final BiConsumer zake;

                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    this.zake = biConsumer;
                }

                @Override // com.google.android.gms.common.api.internal.RemoteCall
                public final void accept(Object obj, Object obj2) {
                    this.zake.accept((Api.AnyClient) obj, (TaskCompletionSource) obj2);
                }
            };
            return this;
        }

        public Builder<A, ResultT> run(RemoteCall<A, TaskCompletionSource<ResultT>> remoteCall) {
            this.zakl = remoteCall;
            return this;
        }

        public Builder<A, ResultT> setFeatures(Feature... featureArr) {
            this.zakd = featureArr;
            return this;
        }

        public Builder<A, ResultT> setAutoResolveMissingFeatures(boolean z) {
            this.zakk = z;
            return this;
        }

        public TaskApiCall<A, ResultT> build() {
            Preconditions.checkArgument(this.zakl != null, "execute parameter required");
            return new zack(this, this.zakd, this.zakk);
        }
    }

    private TaskApiCall(Feature[] featureArr, boolean z) {
        this.zakd = featureArr;
        this.zakk = z;
    }

    public final Feature[] zabt() {
        return this.zakd;
    }

    public boolean shouldAutoResolveMissingFeatures() {
        return this.zakk;
    }

    public static <A extends Api.AnyClient, ResultT> Builder<A, ResultT> builder() {
        return new Builder<>();
    }
}
