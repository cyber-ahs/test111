package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.Api;
/* loaded from: classes.dex */
public final class Common {
    public static final Api<Api.ApiOptions.NoOptions> API;
    public static final Api.ClientKey<zai> CLIENT_KEY;
    private static final Api.AbstractClientBuilder<zai, Api.ApiOptions.NoOptions> zapg;
    public static final zac zaph;

    static {
        Api.ClientKey<zai> clientKey = new Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        zab zabVar = new zab();
        zapg = zabVar;
        API = new Api<>("Common.API", zabVar, clientKey);
        zaph = new zad();
    }
}
