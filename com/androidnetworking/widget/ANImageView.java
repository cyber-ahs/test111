package com.androidnetworking.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import com.androidnetworking.error.ANError;
import com.androidnetworking.internal.ANImageLoader;
/* loaded from: classes.dex */
public class ANImageView extends AppCompatImageView {
    private int mDefaultImageId;
    private int mErrorImageId;
    private ANImageLoader.ImageContainer mImageContainer;
    private String mUrl;

    public ANImageView(Context context) {
        this(context, null);
    }

    public ANImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ANImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setImageUrl(String str) {
        this.mUrl = str;
        loadImageIfNecessary(false);
    }

    public void setDefaultImageResId(int i) {
        this.mDefaultImageId = i;
    }

    public void setErrorImageResId(int i) {
        this.mErrorImageId = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void loadImageIfNecessary(boolean z) {
        boolean z2;
        boolean z3;
        int width = getWidth();
        int height = getHeight();
        ImageView.ScaleType scaleType = getScaleType();
        boolean z4 = true;
        if (getLayoutParams() != null) {
            z2 = getLayoutParams().width == -2;
            if (getLayoutParams().height == -2) {
                z3 = true;
                z4 = (z2 || !z3) ? false : false;
                if (width == 0 || height != 0 || z4) {
                    if (!TextUtils.isEmpty(this.mUrl)) {
                        ANImageLoader.ImageContainer imageContainer = this.mImageContainer;
                        if (imageContainer != null) {
                            imageContainer.cancelRequest();
                            this.mImageContainer = null;
                        }
                        setDefaultImageOrNull();
                        return;
                    }
                    ANImageLoader.ImageContainer imageContainer2 = this.mImageContainer;
                    if (imageContainer2 != null && imageContainer2.getRequestUrl() != null) {
                        if (this.mImageContainer.getRequestUrl().equals(this.mUrl)) {
                            return;
                        }
                        this.mImageContainer.cancelRequest();
                        setDefaultImageOrNull();
                    }
                    if (z2) {
                        width = 0;
                    }
                    this.mImageContainer = ANImageLoader.getInstance().get(this.mUrl, new AnonymousClass1(z), width, z3 ? 0 : height, scaleType);
                    return;
                }
                return;
            }
        } else {
            z2 = false;
        }
        z3 = false;
        if (z2) {
        }
        if (width == 0) {
        }
        if (!TextUtils.isEmpty(this.mUrl)) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.androidnetworking.widget.ANImageView$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements ANImageLoader.ImageListener {
        final /* synthetic */ boolean val$isInLayoutPass;

        AnonymousClass1(boolean z) {
            this.val$isInLayoutPass = z;
        }

        @Override // com.androidnetworking.internal.ANImageLoader.ImageListener
        public void onResponse(final ANImageLoader.ImageContainer imageContainer, boolean z) {
            if (z && this.val$isInLayoutPass) {
                ANImageView.this.post(new Runnable() { // from class: com.androidnetworking.widget.ANImageView.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AnonymousClass1.this.onResponse(imageContainer, false);
                    }
                });
            } else if (imageContainer.getBitmap() == null) {
                if (ANImageView.this.mDefaultImageId != 0) {
                    ANImageView aNImageView = ANImageView.this;
                    aNImageView.setImageResource(aNImageView.mDefaultImageId);
                }
            } else {
                ANImageView.this.setImageBitmap(imageContainer.getBitmap());
            }
        }

        @Override // com.androidnetworking.internal.ANImageLoader.ImageListener
        public void onError(ANError aNError) {
            if (ANImageView.this.mErrorImageId != 0) {
                ANImageView aNImageView = ANImageView.this;
                aNImageView.setImageResource(aNImageView.mErrorImageId);
            }
        }
    }

    private void setDefaultImageOrNull() {
        int i = this.mDefaultImageId;
        if (i != 0) {
            setImageResource(i);
        } else {
            setImageBitmap(null);
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        loadImageIfNecessary(true);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        ANImageLoader.ImageContainer imageContainer = this.mImageContainer;
        if (imageContainer != null) {
            imageContainer.cancelRequest();
            setImageBitmap(null);
            this.mImageContainer = null;
        }
        super.onDetachedFromWindow();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }
}
