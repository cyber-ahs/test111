package com.tooltip;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
/* loaded from: classes.dex */
final class ArrowDrawable extends ColorDrawable {
    private final int mBackgroundColor;
    private final int mGravity;
    private final Paint mPaint;
    private Path mPath;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrowDrawable(int i, int i2) {
        Paint paint = new Paint(1);
        this.mPaint = paint;
        this.mGravity = Util.gravityToArrowDirection(i2);
        this.mBackgroundColor = 0;
        paint.setColor(i);
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        updatePath(rect);
    }

    private synchronized void updatePath(Rect rect) {
        Path path = new Path();
        this.mPath = path;
        int i = this.mGravity;
        if (i == 48) {
            path.moveTo(0.0f, rect.height());
            this.mPath.lineTo(rect.width() / 2, 0.0f);
            this.mPath.lineTo(rect.width(), rect.height());
            this.mPath.lineTo(0.0f, rect.height());
        } else if (i == 80) {
            path.moveTo(0.0f, 0.0f);
            this.mPath.lineTo(rect.width() / 2, rect.height());
            this.mPath.lineTo(rect.width(), 0.0f);
            this.mPath.lineTo(0.0f, 0.0f);
        } else if (i == 8388611) {
            path.moveTo(rect.width(), rect.height());
            this.mPath.lineTo(0.0f, rect.height() / 2);
            this.mPath.lineTo(rect.width(), 0.0f);
            this.mPath.lineTo(rect.width(), rect.height());
        } else if (i == 8388613) {
            path.moveTo(0.0f, 0.0f);
            this.mPath.lineTo(rect.width(), rect.height() / 2);
            this.mPath.lineTo(0.0f, rect.height());
            this.mPath.lineTo(0.0f, 0.0f);
        }
        this.mPath.close();
    }

    @Override // android.graphics.drawable.ColorDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        canvas.drawColor(this.mBackgroundColor);
        if (this.mPath == null) {
            updatePath(getBounds());
        }
        canvas.drawPath(this.mPath, this.mPaint);
    }

    @Override // android.graphics.drawable.ColorDrawable, android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
    }

    @Override // android.graphics.drawable.ColorDrawable
    public void setColor(int i) {
        this.mPaint.setColor(i);
    }

    @Override // android.graphics.drawable.ColorDrawable, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.ColorDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        if (this.mPaint.getColorFilter() != null) {
            return -3;
        }
        int color = this.mPaint.getColor() >>> 24;
        if (color != 0) {
            return color != 255 ? -3 : -1;
        }
        return -2;
    }
}
