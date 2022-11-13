package androidx.core.graphics;

import android.graphics.Bitmap;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Build;
/* loaded from: classes.dex */
public final class BitmapCompat {
    public static int sizeAtStep(int i, int i2, int i3, int i4) {
        return i3 == 0 ? i2 : i3 > 0 ? i * (1 << (i4 - i3)) : i2 << ((-i3) - 1);
    }

    public static boolean hasMipMap(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 17) {
            return Api17Impl.hasMipMap(bitmap);
        }
        return false;
    }

    public static void setHasMipMap(Bitmap bitmap, boolean z) {
        if (Build.VERSION.SDK_INT >= 17) {
            Api17Impl.setHasMipMap(bitmap, z);
        }
    }

    public static int getAllocationByteCount(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 19) {
            return Api19Impl.getAllocationByteCount(bitmap);
        }
        return bitmap.getByteCount();
    }

    /* JADX WARN: Code restructure failed: missing block: B:113:0x01a5, code lost:
        if (androidx.core.graphics.BitmapCompat.Api27Impl.isAlreadyF16AndLinear(r10) == false) goto L96;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Bitmap createScaledBitmap(Bitmap bitmap, int i, int i2, Rect rect, boolean z) {
        int i3;
        double floor;
        double floor2;
        int i4;
        int i5;
        Rect rect2;
        Bitmap bitmap2;
        if (i <= 0 || i2 <= 0) {
            throw new IllegalArgumentException("dstW and dstH must be > 0!");
        }
        if (rect != null && (rect.isEmpty() || rect.left < 0 || rect.right > bitmap.getWidth() || rect.top < 0 || rect.bottom > bitmap.getHeight())) {
            throw new IllegalArgumentException("srcRect must be contained by srcBm!");
        }
        Bitmap copyBitmapIfHardware = Build.VERSION.SDK_INT >= 27 ? Api27Impl.copyBitmapIfHardware(bitmap) : bitmap;
        int width = rect != null ? rect.width() : bitmap.getWidth();
        int height = rect != null ? rect.height() : bitmap.getHeight();
        float f = i / width;
        float f2 = i2 / height;
        int i6 = rect != null ? rect.left : 0;
        int i7 = rect != null ? rect.top : 0;
        if (i6 == 0 && i7 == 0 && i == bitmap.getWidth() && i2 == bitmap.getHeight()) {
            return (bitmap.isMutable() && bitmap == copyBitmapIfHardware) ? bitmap.copy(bitmap.getConfig(), true) : copyBitmapIfHardware;
        }
        Paint paint = new Paint(1);
        paint.setFilterBitmap(true);
        if (Build.VERSION.SDK_INT >= 29) {
            Api29Impl.setPaintBlendMode(paint);
        } else {
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        }
        if (width == i && height == i2) {
            Bitmap createBitmap = Bitmap.createBitmap(i, i2, copyBitmapIfHardware.getConfig());
            new Canvas(createBitmap).drawBitmap(copyBitmapIfHardware, -i6, -i7, paint);
            return createBitmap;
        }
        double log = Math.log(2.0d);
        if (f > 1.0f) {
            i3 = i6;
            floor = Math.ceil(Math.log(f) / log);
        } else {
            i3 = i6;
            floor = Math.floor(Math.log(f) / log);
        }
        int i8 = (int) floor;
        if (f2 > 1.0f) {
            floor2 = Math.ceil(Math.log(f2) / log);
        } else {
            floor2 = Math.floor(Math.log(f2) / log);
        }
        int i9 = (int) floor2;
        Bitmap bitmap3 = null;
        if (!z || Build.VERSION.SDK_INT < 27 || Api27Impl.isAlreadyF16AndLinear(bitmap)) {
            i4 = i3;
            i5 = 0;
        } else {
            Bitmap createBitmapWithSourceColorspace = Api27Impl.createBitmapWithSourceColorspace(i8 > 0 ? sizeAtStep(width, i, 1, i8) : width, i9 > 0 ? sizeAtStep(height, i2, 1, i9) : height, bitmap, true);
            new Canvas(createBitmapWithSourceColorspace).drawBitmap(copyBitmapIfHardware, -i3, -i7, paint);
            i7 = 0;
            i4 = 0;
            i5 = 1;
            bitmap3 = copyBitmapIfHardware;
            copyBitmapIfHardware = createBitmapWithSourceColorspace;
        }
        Rect rect3 = new Rect(i4, i7, width, height);
        Rect rect4 = new Rect();
        int i10 = i8;
        int i11 = i9;
        while (true) {
            if (i10 == 0 && i11 == 0) {
                break;
            }
            if (i10 < 0) {
                i10++;
            } else if (i10 > 0) {
                i10--;
            }
            if (i11 < 0) {
                i11++;
            } else if (i11 > 0) {
                i11--;
            }
            int i12 = i11;
            Paint paint2 = paint;
            Rect rect5 = rect3;
            rect4.set(0, 0, sizeAtStep(width, i, i10, i8), sizeAtStep(height, i2, i12, i9));
            boolean z2 = i10 == 0 && i12 == 0;
            boolean z3 = bitmap3 != null && bitmap3.getWidth() == i && bitmap3.getHeight() == i2;
            if (bitmap3 == null || bitmap3 == bitmap) {
                rect2 = rect4;
            } else {
                if (z) {
                    rect2 = rect4;
                    if (Build.VERSION.SDK_INT >= 27) {
                    }
                } else {
                    rect2 = rect4;
                }
                if (!z2 || (z3 && i5 == 0)) {
                    bitmap2 = bitmap3;
                    Rect rect6 = rect2;
                    new Canvas(bitmap2).drawBitmap(copyBitmapIfHardware, rect5, rect6, paint2);
                    rect5.set(rect6);
                    i11 = i12;
                    Bitmap bitmap4 = copyBitmapIfHardware;
                    copyBitmapIfHardware = bitmap2;
                    rect4 = rect6;
                    rect3 = rect5;
                    paint = paint2;
                    bitmap3 = bitmap4;
                }
            }
            if (bitmap3 != bitmap && bitmap3 != null) {
                bitmap3.recycle();
            }
            int sizeAtStep = sizeAtStep(width, i, i10 > 0 ? i5 : i10, i8);
            int sizeAtStep2 = sizeAtStep(height, i2, i12 > 0 ? i5 : i12, i9);
            if (Build.VERSION.SDK_INT >= 27) {
                bitmap2 = Api27Impl.createBitmapWithSourceColorspace(sizeAtStep, sizeAtStep2, bitmap, z && !z2);
            } else {
                bitmap2 = Bitmap.createBitmap(sizeAtStep, sizeAtStep2, copyBitmapIfHardware.getConfig());
            }
            Rect rect62 = rect2;
            new Canvas(bitmap2).drawBitmap(copyBitmapIfHardware, rect5, rect62, paint2);
            rect5.set(rect62);
            i11 = i12;
            Bitmap bitmap42 = copyBitmapIfHardware;
            copyBitmapIfHardware = bitmap2;
            rect4 = rect62;
            rect3 = rect5;
            paint = paint2;
            bitmap3 = bitmap42;
        }
        if (bitmap3 != bitmap && bitmap3 != null) {
            bitmap3.recycle();
        }
        return copyBitmapIfHardware;
    }

    private BitmapCompat() {
    }

    /* loaded from: classes.dex */
    static class Api17Impl {
        private Api17Impl() {
        }

        static boolean hasMipMap(Bitmap bitmap) {
            return bitmap.hasMipMap();
        }

        static void setHasMipMap(Bitmap bitmap, boolean z) {
            bitmap.setHasMipMap(z);
        }
    }

    /* loaded from: classes.dex */
    static class Api19Impl {
        private Api19Impl() {
        }

        static int getAllocationByteCount(Bitmap bitmap) {
            return bitmap.getAllocationByteCount();
        }
    }

    /* loaded from: classes.dex */
    static class Api27Impl {
        private Api27Impl() {
        }

        static Bitmap createBitmapWithSourceColorspace(int i, int i2, Bitmap bitmap, boolean z) {
            Bitmap.Config config = bitmap.getConfig();
            ColorSpace colorSpace = bitmap.getColorSpace();
            ColorSpace colorSpace2 = ColorSpace.get(ColorSpace.Named.LINEAR_EXTENDED_SRGB);
            if (z && !bitmap.getColorSpace().equals(colorSpace2)) {
                config = Bitmap.Config.RGBA_F16;
                colorSpace = colorSpace2;
            } else if (bitmap.getConfig() == Bitmap.Config.HARDWARE) {
                config = Bitmap.Config.ARGB_8888;
                if (Build.VERSION.SDK_INT >= 31) {
                    config = Api31Impl.getHardwareBitmapConfig(bitmap);
                }
            }
            return Bitmap.createBitmap(i, i2, config, bitmap.hasAlpha(), colorSpace);
        }

        static boolean isAlreadyF16AndLinear(Bitmap bitmap) {
            return bitmap.getConfig() == Bitmap.Config.RGBA_F16 && bitmap.getColorSpace().equals(ColorSpace.get(ColorSpace.Named.LINEAR_EXTENDED_SRGB));
        }

        static Bitmap copyBitmapIfHardware(Bitmap bitmap) {
            if (bitmap.getConfig() == Bitmap.Config.HARDWARE) {
                Bitmap.Config config = Bitmap.Config.ARGB_8888;
                if (Build.VERSION.SDK_INT >= 31) {
                    config = Api31Impl.getHardwareBitmapConfig(bitmap);
                }
                return bitmap.copy(config, true);
            }
            return bitmap;
        }
    }

    /* loaded from: classes.dex */
    static class Api29Impl {
        private Api29Impl() {
        }

        static void setPaintBlendMode(Paint paint) {
            paint.setBlendMode(BlendMode.SRC);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Api31Impl {
        private Api31Impl() {
        }

        static Bitmap.Config getHardwareBitmapConfig(Bitmap bitmap) {
            if (bitmap.getHardwareBuffer().getFormat() == 22) {
                return Bitmap.Config.RGBA_F16;
            }
            return Bitmap.Config.ARGB_8888;
        }
    }
}
