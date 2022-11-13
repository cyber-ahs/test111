package com.google.firebase.iid;

import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import java.security.KeyPair;
/* loaded from: classes.dex */
final class zzz {
    private final KeyPair zzbr;
    private final long zzbs;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzz(KeyPair keyPair, long j) {
        this.zzbr = keyPair;
        this.zzbs = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final KeyPair getKeyPair() {
        return this.zzbr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long getCreationTime() {
        return this.zzbs;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzz) {
            zzz zzzVar = (zzz) obj;
            return this.zzbs == zzzVar.zzbs && this.zzbr.getPublic().equals(zzzVar.zzbr.getPublic()) && this.zzbr.getPrivate().equals(zzzVar.zzbr.getPrivate());
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzbr.getPublic(), this.zzbr.getPrivate(), Long.valueOf(this.zzbs));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String zzv() {
        return Base64.encodeToString(this.zzbr.getPublic().getEncoded(), 11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String zzw() {
        return Base64.encodeToString(this.zzbr.getPrivate().getEncoded(), 11);
    }
}
