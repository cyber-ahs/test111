package com.github.mikephil.charting.renderer.scatter;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.github.mikephil.charting.buffer.ScatterBuffer;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
/* loaded from: classes.dex */
public class XShapeRenderer implements ShapeRenderer {
    @Override // com.github.mikephil.charting.renderer.scatter.ShapeRenderer
    public void renderShape(Canvas canvas, IScatterDataSet iScatterDataSet, ViewPortHandler viewPortHandler, ScatterBuffer scatterBuffer, Paint paint, float f) {
        float f2 = f / 2.0f;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.convertDpToPixel(1.0f));
        for (int i = 0; i < scatterBuffer.size() && viewPortHandler.isInBoundsRight(scatterBuffer.buffer[i]); i += 2) {
            if (viewPortHandler.isInBoundsLeft(scatterBuffer.buffer[i])) {
                int i2 = i + 1;
                if (viewPortHandler.isInBoundsY(scatterBuffer.buffer[i2])) {
                    paint.setColor(iScatterDataSet.getColor(i / 2));
                    canvas.drawLine(scatterBuffer.buffer[i] - f2, scatterBuffer.buffer[i2] - f2, scatterBuffer.buffer[i] + f2, scatterBuffer.buffer[i2] + f2, paint);
                    canvas.drawLine(scatterBuffer.buffer[i] + f2, scatterBuffer.buffer[i2] - f2, scatterBuffer.buffer[i] - f2, scatterBuffer.buffer[i2] + f2, paint);
                }
            }
        }
    }
}
