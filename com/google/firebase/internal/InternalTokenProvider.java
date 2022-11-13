package com.google.firebase.internal;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.GetTokenResult;
/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
@Deprecated
/* loaded from: classes.dex */
public interface InternalTokenProvider {
    Task<GetTokenResult> getAccessToken(boolean z);

    String getUid();
}
