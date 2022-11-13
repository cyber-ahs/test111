package com.tooltip;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/* loaded from: classes.dex */
public class TooltipActionView extends FrameLayout {
    private ImageView mImageView;
    private MenuItem mMenuItem;
    private MenuItem.OnMenuItemClickListener mOnMenuItemClickListener;
    private TextView mTextView;

    public TooltipActionView(Context context) {
        this(context, null);
    }

    public TooltipActionView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16843480);
    }

    public TooltipActionView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setClickable(true);
        setLongClickable(true);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.action_button_width) - getResources().getDimensionPixelSize(R.dimen.action_button_padding), -2, 17);
        this.mTextView = new TextView(context);
        this.mImageView = new ImageView(context);
        this.mTextView.setDuplicateParentStateEnabled(true);
        this.mImageView.setDuplicateParentStateEnabled(true);
        addView(this.mTextView, layoutParams);
        addView(this.mImageView, layoutParams);
    }

    @Override // android.view.View
    public boolean performClick() {
        MenuItem.OnMenuItemClickListener onMenuItemClickListener = this.mOnMenuItemClickListener;
        if (onMenuItemClickListener != null) {
            onMenuItemClickListener.onMenuItemClick(this.mMenuItem);
        }
        return super.performClick();
    }

    @Override // android.view.View
    public boolean performLongClick() {
        MenuItem menuItem = this.mMenuItem;
        if (menuItem != null && !TextUtils.isEmpty(menuItem.getTitle())) {
            int[] iArr = new int[2];
            Rect rect = new Rect();
            getLocationOnScreen(iArr);
            getWindowVisibleDisplayFrame(rect);
            Context context = getContext();
            int width = getWidth();
            int height = getHeight();
            int i = iArr[1] + (height / 2);
            int i2 = context.getResources().getDisplayMetrics().widthPixels;
            Toast makeText = Toast.makeText(context, this.mMenuItem.getTitle(), 0);
            if (i < rect.height()) {
                makeText.setGravity(8388661, (i2 - iArr[0]) - (width / 2), height);
            } else {
                makeText.setGravity(81, 0, height);
            }
            makeText.show();
        }
        return super.performLongClick();
    }

    public MenuItem getMenuItem() {
        return this.mMenuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        if (this.mMenuItem != menuItem) {
            this.mMenuItem = menuItem;
            View actionView = menuItem.getActionView();
            if (actionView == null || !actionView.equals(this)) {
                return;
            }
            if (menuItem.getIcon() != null) {
                this.mImageView.setImageDrawable(menuItem.getIcon());
            } else if (menuItem.getTitle() != null) {
                this.mTextView.setText(menuItem.getTitle());
            }
        }
    }

    public void setOnMenuItemClick(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.mOnMenuItemClickListener = onMenuItemClickListener;
    }
}
