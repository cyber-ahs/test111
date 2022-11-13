package munirkhanani.helpers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
/* loaded from: classes2.dex */
public class BadgeIndicator extends RelativeLayout {
    private int backgroundColor;
    private int textColor;
    private TextView textView;

    public BadgeIndicator(Context context, int i, int i2) {
        super(context);
        init(i, i2);
    }

    public BadgeIndicator(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet);
        init(i, i2);
    }

    private void init(int i, int i2) {
        this.backgroundColor = i;
        this.textColor = i2;
        addComponent();
    }

    private void addComponent() {
        setGravity(17);
        setBackground(generateCircleDrawable(this.backgroundColor));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        setPadding(5, 5, 5, 5);
        setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        TextView textView = new TextView(getContext());
        this.textView = textView;
        textView.setLayoutParams(layoutParams2);
        this.textView.setTextColor(ContextCompat.getColor(getContext(), this.textColor));
        this.textView.setTextSize(8.0f);
        this.textView.setGravity(17);
        show(false);
        addView(this.textView);
    }

    private Drawable generateCircleDrawable(int i) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setColor(ContextCompat.getColor(getContext(), this.backgroundColor));
        return shapeDrawable;
    }

    public void show(boolean z) {
        if (z) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }

    public void updateCount(int i) {
        StringBuilder sb;
        TextView textView = this.textView;
        if (textView != null) {
            if (i <= 0) {
                show(false);
                return;
            }
            if (i > 1000) {
                i = 999;
            }
            if (i < 10) {
                sb = new StringBuilder();
                sb.append("0");
                sb.append(i);
            } else {
                sb = new StringBuilder();
                sb.append(i);
                sb.append("");
            }
            textView.setText(sb.toString());
            show(true);
        }
    }
}
