package com.github.mikephil.charting.renderer.scatter;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.github.mikephil.charting.buffer.ScatterBuffer;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
/* loaded from: classes.dex */
public class SquareShapeRenderer implements ShapeRenderer {
    @Override // com.github.mikephil.charting.renderer.scatter.ShapeRenderer
    public void renderShape(Canvas canvas, IScatterDataSet iScatterDataSet, ViewPortHandler viewPortHandler, ScatterBuffer scatterBuffer, Paint paint, float f) {
        float f2 = f / 2.0f;
        float convertDpToPixel = Utils.convertDpToPixel(iScatterDataSet.getScatterShapeHoleRadius());
        float f3 = (f - (convertDpToPixel * 2.0f)) / 2.0f;
        float f4 = f3 / 2.0f;
        int scatterShapeHoleColor = iScatterDataSet.getScatterShapeHoleColor();
        for (int i = 0; i < scatterBuffer.size() && viewPortHandler.isInBoundsRight(scatterBuffer.buffer[i]); i += 2) {
            if (viewPortHandler.isInBoundsLeft(scatterBuffer.buffer[i])) {
                int i2 = i + 1;
                if (viewPortHandler.isInBoundsY(scatterBuffer.buffer[i2])) {
                    paint.setColor(iScatterDataSet.getColor(i / 2));
                    if (f > 0.0d) {
                        paint.setStyle(Paint.Style.STROKE);
                        paint.setStrokeWidth(f3);
                        canvas.drawRect((scatterBuffer.buffer[i] - convertDpToPixel) - f4, (scatterBuffer.buffer[i2] - convertDpToPixel) - f4, scatterBuffer.buffer[i] + convertDpToPixel + f4, scatterBuffer.buffer[i2] + convertDpToPixel + f4, paint);
                        if (scatterShapeHoleColor != 1122867) {
                            paint.setStyle(Paint.Style.FILL);
                            paint.setColor(scatterShapeHoleColor);
                            canvas.drawRect(scatterBuffer.buffer[i] - convertDpToPixel, scatterBuffer.buffer[i2] - convertDpToPixel, scatterBuffer.buffer[i] + convertDpToPixel, scatterBuffer.buffer[i2] + convertDpToPixel, paint);
                        }
                    } else {
                        paint.setStyle(Paint.Style.FILL);
                        canvas.drawRect(scatterBuffer.buffer[i] - f2, scatterBuffer.buffer[i2] - f2, scatterBuffer.buffer[i] + f2, scatterBuffer.buffer[i2] + f2, paint);
                    }
                }
            }
        }
    }
}
