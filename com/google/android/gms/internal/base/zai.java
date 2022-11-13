package com.google.android.gms.internal.base;

import android.graphics.drawable.Drawable;
/* loaded from: classes.dex */
final class zai extends Drawable.ConstantState {
    int mChangingConfigurations;
    int zanv;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zai(zai zaiVar) {
        if (zaiVar != null) {
            this.mChangingConfigurations = zaiVar.mChangingConfigurations;
            this.zanv = zaiVar.zanv;
        }
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        return new zae(this);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final int getChangingConfigurations() {
        return this.mChangingConfigurations;
    }
}
