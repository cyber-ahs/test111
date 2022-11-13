package com.google.android.gms.common.images;

import android.net.Uri;
import com.google.android.gms.common.internal.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zab {
    public final Uri uri;

    public zab(Uri uri) {
        this.uri = uri;
    }

    public final int hashCode() {
        return Objects.hashCode(this.uri);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zab) {
            if (this == obj) {
                return true;
            }
            return Objects.equal(((zab) obj).uri, this.uri);
        }
        return false;
    }
}
