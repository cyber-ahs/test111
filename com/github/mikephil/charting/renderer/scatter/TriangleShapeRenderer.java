package com.github.mikephil.charting.renderer.scatter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.github.mikephil.charting.buffer.ScatterBuffer;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
/* loaded from: classes.dex */
public class TriangleShapeRenderer implements ShapeRenderer {
    protected Path mTrianglePathBuffer = new Path();

    @Override // com.github.mikephil.charting.renderer.scatter.ShapeRenderer
    public void renderShape(Canvas canvas, IScatterDataSet iScatterDataSet, ViewPortHandler viewPortHandler, ScatterBuffer scatterBuffer, Paint paint, float f) {
        ViewPortHandler viewPortHandler2 = viewPortHandler;
        float f2 = f / 2.0f;
        float convertDpToPixel = (f - (Utils.convertDpToPixel(iScatterDataSet.getScatterShapeHoleRadius()) * 2.0f)) / 2.0f;
        int scatterShapeHoleColor = iScatterDataSet.getScatterShapeHoleColor();
        paint.setStyle(Paint.Style.FILL);
        Path path = this.mTrianglePathBuffer;
        path.reset();
        int i = 0;
        while (i < scatterBuffer.size() && viewPortHandler2.isInBoundsRight(scatterBuffer.buffer[i])) {
            if (viewPortHandler2.isInBoundsLeft(scatterBuffer.buffer[i])) {
                int i2 = i + 1;
                if (viewPortHandler2.isInBoundsY(scatterBuffer.buffer[i2])) {
                    paint.setColor(iScatterDataSet.getColor(i / 2));
                    path.moveTo(scatterBuffer.buffer[i], scatterBuffer.buffer[i2] - f2);
                    path.lineTo(scatterBuffer.buffer[i] + f2, scatterBuffer.buffer[i2] + f2);
                    path.lineTo(scatterBuffer.buffer[i] - f2, scatterBuffer.buffer[i2] + f2);
                    double d = f;
                    if (d > 0.0d) {
                        path.lineTo(scatterBuffer.buffer[i], scatterBuffer.buffer[i2] - f2);
                        path.moveTo((scatterBuffer.buffer[i] - f2) + convertDpToPixel, (scatterBuffer.buffer[i2] + f2) - convertDpToPixel);
                        path.lineTo((scatterBuffer.buffer[i] + f2) - convertDpToPixel, (scatterBuffer.buffer[i2] + f2) - convertDpToPixel);
                        path.lineTo(scatterBuffer.buffer[i], (scatterBuffer.buffer[i2] - f2) + convertDpToPixel);
                        path.lineTo((scatterBuffer.buffer[i] - f2) + convertDpToPixel, (scatterBuffer.buffer[i2] + f2) - convertDpToPixel);
                    }
                    path.close();
                    canvas.drawPath(path, paint);
                    path.reset();
                    if (d > 0.0d && scatterShapeHoleColor != 1122867) {
                        paint.setColor(scatterShapeHoleColor);
                        path.moveTo(scatterBuffer.buffer[i], (scatterBuffer.buffer[i2] - f2) + convertDpToPixel);
                        path.lineTo((scatterBuffer.buffer[i] + f2) - convertDpToPixel, (scatterBuffer.buffer[i2] + f2) - convertDpToPixel);
                        path.lineTo((scatterBuffer.buffer[i] - f2) + convertDpToPixel, (scatterBuffer.buffer[i2] + f2) - convertDpToPixel);
                        path.close();
                        canvas.drawPath(path, paint);
                        path.reset();
                    }
                    i += 2;
                    viewPortHandler2 = viewPortHandler;
                }
            }
            i += 2;
            viewPortHandler2 = viewPortHandler;
        }
    }
}
