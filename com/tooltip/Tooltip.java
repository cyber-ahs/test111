package com.tooltip;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.TextViewCompat;
/* loaded from: classes.dex */
public final class Tooltip {
    private final boolean isCancelable;
    private final boolean isDismissOnClick;
    private final View mAnchorView;
    private final ViewTreeObserver.OnGlobalLayoutListener mArrowLayoutListener;
    private ImageView mArrowView;
    private final View.OnClickListener mClickListener;
    private LinearLayout mContentView;
    private final int mGravity;
    private final ViewTreeObserver.OnGlobalLayoutListener mLocationLayoutListener;
    private final View.OnLongClickListener mLongClickListener;
    private final float mMargin;
    private final View.OnAttachStateChangeListener mOnAttachStateChangeListener;
    private OnClickListener mOnClickListener;
    private OnDismissListener mOnDismissListener;
    private OnLongClickListener mOnLongClickListener;
    private final PopupWindow mPopupWindow;
    private final View.OnTouchListener mTouchListener;

    private Tooltip(Builder builder) {
        this.mClickListener = new View.OnClickListener() { // from class: com.tooltip.Tooltip.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Tooltip.this.mOnClickListener != null) {
                    Tooltip.this.mOnClickListener.onClick(Tooltip.this);
                }
            }
        };
        this.mLongClickListener = new View.OnLongClickListener() { // from class: com.tooltip.Tooltip.4
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                return Tooltip.this.mOnLongClickListener != null && Tooltip.this.mOnLongClickListener.onLongClick(Tooltip.this);
            }
        };
        this.mTouchListener = new View.OnTouchListener() { // from class: com.tooltip.Tooltip.5
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if ((Tooltip.this.isCancelable && motionEvent.getAction() == 4) || (Tooltip.this.isDismissOnClick && motionEvent.getAction() == 1)) {
                    Tooltip.this.dismiss();
                    return true;
                }
                return false;
            }
        };
        this.mLocationLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.tooltip.Tooltip.6
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                Util.removeOnGlobalLayoutListener(Tooltip.this.mContentView, this);
                Tooltip.this.mContentView.getViewTreeObserver().addOnGlobalLayoutListener(Tooltip.this.mArrowLayoutListener);
                PointF calculateLocation = Tooltip.this.calculateLocation();
                Tooltip.this.mPopupWindow.setClippingEnabled(true);
                Tooltip.this.mPopupWindow.update((int) calculateLocation.x, (int) calculateLocation.y, Tooltip.this.mPopupWindow.getWidth(), Tooltip.this.mPopupWindow.getHeight());
            }
        };
        this.mArrowLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.tooltip.Tooltip.7
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                float f;
                float top;
                Util.removeOnGlobalLayoutListener(Tooltip.this.mContentView, this);
                RectF calculateRectOnScreen = Util.calculateRectOnScreen(Tooltip.this.mAnchorView);
                RectF calculateRectOnScreen2 = Util.calculateRectOnScreen(Tooltip.this.mContentView);
                if (Tooltip.this.mGravity == 80 || Tooltip.this.mGravity == 48) {
                    float paddingLeft = Tooltip.this.mContentView.getPaddingLeft() + Util.dpToPx(2.0f);
                    float width = ((calculateRectOnScreen2.width() / 2.0f) - (Tooltip.this.mArrowView.getWidth() / 2.0f)) - (calculateRectOnScreen2.centerX() - calculateRectOnScreen.centerX());
                    if (width > paddingLeft) {
                        f = (((float) Tooltip.this.mArrowView.getWidth()) + width) + paddingLeft > calculateRectOnScreen2.width() ? (calculateRectOnScreen2.width() - Tooltip.this.mArrowView.getWidth()) - paddingLeft : width;
                    } else {
                        f = paddingLeft;
                    }
                    top = (Tooltip.this.mGravity != 48 ? 1 : -1) + Tooltip.this.mArrowView.getTop();
                } else {
                    top = Tooltip.this.mContentView.getPaddingTop() + Util.dpToPx(2.0f);
                    float height = ((calculateRectOnScreen2.height() / 2.0f) - (Tooltip.this.mArrowView.getHeight() / 2.0f)) - (calculateRectOnScreen2.centerY() - calculateRectOnScreen.centerY());
                    if (height > top) {
                        top = (((float) Tooltip.this.mArrowView.getHeight()) + height) + top > calculateRectOnScreen2.height() ? (calculateRectOnScreen2.height() - Tooltip.this.mArrowView.getHeight()) - top : height;
                    }
                    f = Tooltip.this.mArrowView.getLeft() + (Tooltip.this.mGravity != 8388611 ? 1 : -1);
                }
                Tooltip.this.mArrowView.setX(f);
                Tooltip.this.mArrowView.setY(top);
            }
        };
        this.mOnAttachStateChangeListener = new View.OnAttachStateChangeListener() { // from class: com.tooltip.Tooltip.8
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view) {
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
                Tooltip.this.dismiss();
            }
        };
        this.isCancelable = builder.isCancelable;
        this.isDismissOnClick = builder.isDismissOnClick;
        this.mGravity = builder.mGravity;
        this.mMargin = builder.mMargin;
        this.mAnchorView = builder.mAnchorView;
        this.mOnClickListener = builder.mOnClickListener;
        this.mOnLongClickListener = builder.mOnLongClickListener;
        this.mOnDismissListener = builder.mOnDismissListener;
        PopupWindow popupWindow = new PopupWindow(builder.mContext);
        this.mPopupWindow = popupWindow;
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setClippingEnabled(false);
        popupWindow.setWidth(-2);
        popupWindow.setHeight(-2);
        popupWindow.setContentView(getContentView(builder));
        popupWindow.setOutsideTouchable(builder.isCancelable);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.tooltip.Tooltip.1
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                Tooltip.this.mAnchorView.removeOnAttachStateChangeListener(Tooltip.this.mOnAttachStateChangeListener);
                if (Tooltip.this.mOnDismissListener != null) {
                    Tooltip.this.mOnDismissListener.onDismiss();
                }
            }
        });
    }

    private View getContentView(Builder builder) {
        LinearLayout.LayoutParams layoutParams;
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(builder.mBackgroundColor);
        gradientDrawable.setCornerRadius(builder.mCornerRadius);
        int i = (int) builder.mPadding;
        TextView textView = new TextView(builder.mContext);
        TextViewCompat.setTextAppearance(textView, builder.mTextAppearance);
        textView.setText(builder.mText);
        textView.setPadding(i, i, i, i);
        textView.setLineSpacing(builder.mLineSpacingExtra, builder.mLineSpacingMultiplier);
        textView.setTypeface(builder.mTypeface, builder.mTextStyle);
        if (builder.mTextSize >= 0.0f) {
            textView.setTextSize(0, builder.mTextSize);
        }
        if (builder.mTextColor != null) {
            textView.setTextColor(builder.mTextColor);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            textView.setBackground(gradientDrawable);
        } else {
            textView.setBackgroundDrawable(gradientDrawable);
        }
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2, 0.0f);
        layoutParams2.gravity = 17;
        textView.setLayoutParams(layoutParams2);
        ImageView imageView = new ImageView(builder.mContext);
        this.mArrowView = imageView;
        imageView.setImageDrawable(builder.mArrowDrawable);
        int i2 = this.mGravity;
        if (i2 == 48 || i2 == 80) {
            layoutParams = new LinearLayout.LayoutParams((int) builder.mArrowWidth, (int) builder.mArrowHeight, 0.0f);
        } else {
            layoutParams = new LinearLayout.LayoutParams((int) builder.mArrowHeight, (int) builder.mArrowWidth, 0.0f);
        }
        layoutParams.gravity = 17;
        this.mArrowView.setLayoutParams(layoutParams);
        LinearLayout linearLayout = new LinearLayout(builder.mContext);
        this.mContentView = linearLayout;
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        LinearLayout linearLayout2 = this.mContentView;
        int i3 = this.mGravity;
        linearLayout2.setOrientation((i3 == 8388611 || i3 == 8388613) ? 0 : 1);
        int dpToPx = (int) Util.dpToPx(5.0f);
        int i4 = this.mGravity;
        if (i4 == 48 || i4 == 80) {
            this.mContentView.setPadding(dpToPx, 0, dpToPx, 0);
        } else if (i4 == 8388611) {
            this.mContentView.setPadding(0, 0, dpToPx, 0);
        } else if (i4 == 8388613) {
            this.mContentView.setPadding(dpToPx, 0, 0, 0);
        }
        int i5 = this.mGravity;
        if (i5 == 48 || i5 == 8388611) {
            this.mContentView.addView(textView);
            this.mContentView.addView(this.mArrowView);
        } else {
            this.mContentView.addView(this.mArrowView);
            this.mContentView.addView(textView);
        }
        this.mContentView.setOnClickListener(this.mClickListener);
        this.mContentView.setOnLongClickListener(this.mLongClickListener);
        if (builder.isCancelable || builder.isDismissOnClick) {
            this.mContentView.setOnTouchListener(this.mTouchListener);
        }
        return this.mContentView;
    }

    public boolean isShowing() {
        return this.mPopupWindow.isShowing();
    }

    public void show() {
        if (isShowing()) {
            return;
        }
        this.mContentView.getViewTreeObserver().addOnGlobalLayoutListener(this.mLocationLayoutListener);
        this.mAnchorView.addOnAttachStateChangeListener(this.mOnAttachStateChangeListener);
        this.mAnchorView.post(new Runnable() { // from class: com.tooltip.Tooltip.2
            @Override // java.lang.Runnable
            public void run() {
                Tooltip.this.mPopupWindow.showAsDropDown(Tooltip.this.mAnchorView);
            }
        });
    }

    public void dismiss() {
        this.mPopupWindow.dismiss();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.mOnLongClickListener = onLongClickListener;
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PointF calculateLocation() {
        PointF pointF = new PointF();
        RectF calculateRectInWindow = Util.calculateRectInWindow(this.mAnchorView);
        PointF pointF2 = new PointF(calculateRectInWindow.centerX(), calculateRectInWindow.centerY());
        int i = this.mGravity;
        if (i == 48) {
            pointF.x = pointF2.x - (this.mContentView.getWidth() / 2.0f);
            pointF.y = (calculateRectInWindow.top - this.mContentView.getHeight()) - this.mMargin;
        } else if (i == 80) {
            pointF.x = pointF2.x - (this.mContentView.getWidth() / 2.0f);
            pointF.y = calculateRectInWindow.bottom + this.mMargin;
        } else if (i == 8388611) {
            pointF.x = (calculateRectInWindow.left - this.mContentView.getWidth()) - this.mMargin;
            pointF.y = pointF2.y - (this.mContentView.getHeight() / 2.0f);
        } else if (i == 8388613) {
            pointF.x = calculateRectInWindow.right + this.mMargin;
            pointF.y = pointF2.y - (this.mContentView.getHeight() / 2.0f);
        }
        return pointF;
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private boolean isCancelable;
        private boolean isDismissOnClick;
        private View mAnchorView;
        private Drawable mArrowDrawable;
        private float mArrowHeight;
        private float mArrowWidth;
        private int mBackgroundColor;
        private Context mContext;
        private float mCornerRadius;
        private int mGravity;
        private float mLineSpacingExtra;
        private float mLineSpacingMultiplier;
        private float mMargin;
        private OnClickListener mOnClickListener;
        private OnDismissListener mOnDismissListener;
        private OnLongClickListener mOnLongClickListener;
        private float mPadding;
        private String mText;
        private int mTextAppearance;
        private ColorStateList mTextColor;
        private float mTextSize;
        private int mTextStyle;
        private Typeface mTypeface;

        public Builder(MenuItem menuItem) {
            this(menuItem, 0);
        }

        public Builder(MenuItem menuItem, int i) {
            this.mLineSpacingMultiplier = 1.0f;
            this.mTypeface = Typeface.DEFAULT;
            View actionView = menuItem.getActionView();
            if (actionView != null) {
                if (actionView instanceof TooltipActionView) {
                    ((TooltipActionView) actionView).setMenuItem(menuItem);
                }
                init(actionView.getContext(), actionView, i);
                return;
            }
            throw new NullPointerException("anchor menuItem haven`t actionViewClass");
        }

        public Builder(View view) {
            this(view, 0);
        }

        public Builder(View view, int i) {
            this.mLineSpacingMultiplier = 1.0f;
            this.mTypeface = Typeface.DEFAULT;
            init(view.getContext(), view, i);
        }

        private void init(Context context, View view, int i) {
            this.mContext = context;
            this.mAnchorView = view;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, R.styleable.Tooltip);
            this.isCancelable = obtainStyledAttributes.getBoolean(R.styleable.Tooltip_cancelable, false);
            this.isDismissOnClick = obtainStyledAttributes.getBoolean(R.styleable.Tooltip_dismissOnClick, false);
            this.mBackgroundColor = obtainStyledAttributes.getColor(R.styleable.Tooltip_backgroundColor, -7829368);
            this.mCornerRadius = obtainStyledAttributes.getDimension(R.styleable.Tooltip_cornerRadius, -1.0f);
            this.mArrowHeight = obtainStyledAttributes.getDimension(R.styleable.Tooltip_arrowHeight, -1.0f);
            this.mArrowWidth = obtainStyledAttributes.getDimension(R.styleable.Tooltip_arrowWidth, -1.0f);
            this.mArrowDrawable = obtainStyledAttributes.getDrawable(R.styleable.Tooltip_arrowDrawable);
            this.mMargin = obtainStyledAttributes.getDimension(R.styleable.Tooltip_margin, -1.0f);
            this.mTextAppearance = obtainStyledAttributes.getResourceId(R.styleable.Tooltip_textAppearance, -1);
            this.mPadding = obtainStyledAttributes.getDimension(R.styleable.Tooltip_android_padding, -1.0f);
            this.mGravity = obtainStyledAttributes.getInteger(R.styleable.Tooltip_android_gravity, 80);
            this.mText = obtainStyledAttributes.getString(R.styleable.Tooltip_android_text);
            this.mTextSize = obtainStyledAttributes.getDimension(R.styleable.Tooltip_android_textSize, -1.0f);
            this.mTextColor = obtainStyledAttributes.getColorStateList(R.styleable.Tooltip_android_textColor);
            this.mTextStyle = obtainStyledAttributes.getInteger(R.styleable.Tooltip_android_textStyle, -1);
            this.mLineSpacingExtra = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Tooltip_android_lineSpacingExtra, 0);
            this.mLineSpacingMultiplier = obtainStyledAttributes.getFloat(R.styleable.Tooltip_android_lineSpacingMultiplier, this.mLineSpacingMultiplier);
            this.mTypeface = getTypefaceFromAttr(obtainStyledAttributes.getString(R.styleable.Tooltip_android_fontFamily), obtainStyledAttributes.getInt(R.styleable.Tooltip_android_typeface, -1), this.mTextStyle);
            obtainStyledAttributes.recycle();
        }

        public Builder setCancelable(boolean z) {
            this.isCancelable = z;
            return this;
        }

        public Builder setDismissOnClick(boolean z) {
            this.isDismissOnClick = z;
            return this;
        }

        public Builder setBackgroundColor(int i) {
            this.mBackgroundColor = i;
            return this;
        }

        public Builder setCornerRadius(int i) {
            return setCornerRadius(this.mContext.getResources().getDimension(i));
        }

        public Builder setCornerRadius(float f) {
            this.mCornerRadius = f;
            return this;
        }

        public Builder setArrowHeight(int i) {
            return setArrowHeight(this.mContext.getResources().getDimension(i));
        }

        public Builder setArrowHeight(float f) {
            this.mArrowHeight = f;
            return this;
        }

        public Builder setArrowWidth(int i) {
            return setArrowWidth(this.mContext.getResources().getDimension(i));
        }

        public Builder setArrowWidth(float f) {
            this.mArrowWidth = f;
            return this;
        }

        public Builder setArrow(int i) {
            return setArrow(ResourcesCompat.getDrawable(this.mContext.getResources(), i, null));
        }

        public Builder setArrow(Drawable drawable) {
            this.mArrowDrawable = drawable;
            return this;
        }

        public Builder setMargin(int i) {
            return setMargin(this.mContext.getResources().getDimension(i));
        }

        public Builder setMargin(float f) {
            this.mMargin = f;
            return this;
        }

        public Builder setTextAppearance(int i) {
            this.mTextAppearance = i;
            return this;
        }

        public Builder setPadding(int i) {
            return setPadding(this.mContext.getResources().getDimension(i));
        }

        public Builder setPadding(float f) {
            this.mPadding = f;
            return this;
        }

        public Builder setGravity(int i) {
            this.mGravity = i;
            return this;
        }

        public Builder setText(int i) {
            return setText(this.mContext.getString(i));
        }

        public Builder setText(String str) {
            this.mText = str;
            return this;
        }

        public Builder setTextSize(int i) {
            this.mTextSize = this.mContext.getResources().getDimension(i);
            return this;
        }

        public Builder setTextSize(float f) {
            this.mTextSize = TypedValue.applyDimension(2, f, this.mContext.getResources().getDisplayMetrics());
            return this;
        }

        public Builder setTextColor(int i) {
            this.mTextColor = ColorStateList.valueOf(i);
            return this;
        }

        public Builder setTextStyle(int i) {
            this.mTextStyle = i;
            return this;
        }

        public Builder setLineSpacing(int i, float f) {
            this.mLineSpacingExtra = this.mContext.getResources().getDimensionPixelSize(i);
            this.mLineSpacingMultiplier = f;
            return this;
        }

        public Builder setLineSpacing(float f, float f2) {
            this.mLineSpacingExtra = f;
            this.mLineSpacingMultiplier = f2;
            return this;
        }

        public Builder setTypeface(Typeface typeface) {
            this.mTypeface = typeface;
            return this;
        }

        public Builder setOnClickListener(OnClickListener onClickListener) {
            this.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setOnLongClickListener(OnLongClickListener onLongClickListener) {
            this.mOnLongClickListener = onLongClickListener;
            return this;
        }

        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            this.mOnDismissListener = onDismissListener;
            return this;
        }

        public Tooltip build() {
            if (!Gravity.isHorizontal(this.mGravity) && !Gravity.isVertical(this.mGravity)) {
                throw new IllegalArgumentException("Gravity must have be START, END, TOP or BOTTOM.");
            }
            if (this.mArrowHeight == -1.0f) {
                this.mArrowHeight = this.mContext.getResources().getDimension(R.dimen.default_tooltip_arrow_height);
            }
            if (this.mArrowWidth == -1.0f) {
                this.mArrowWidth = this.mContext.getResources().getDimension(R.dimen.default_tooltip_arrow_width);
            }
            if (this.mArrowDrawable == null) {
                this.mArrowDrawable = new ArrowDrawable(this.mBackgroundColor, this.mGravity);
            }
            if (this.mMargin == -1.0f) {
                this.mMargin = this.mContext.getResources().getDimension(R.dimen.default_tooltip_margin);
            }
            if (this.mPadding == -1.0f) {
                this.mPadding = this.mContext.getResources().getDimension(R.dimen.default_tooltip_padding);
            }
            return new Tooltip(this);
        }

        public Tooltip show() {
            Tooltip build = build();
            build.show();
            return build;
        }

        private Typeface getTypefaceFromAttr(String str, int i, int i2) {
            Typeface typeface;
            if (str != null) {
                typeface = Typeface.create(str, i2);
                if (typeface != null) {
                    return typeface;
                }
            } else {
                typeface = null;
            }
            if (i != 1) {
                if (i != 2) {
                    return i != 3 ? typeface : Typeface.MONOSPACE;
                }
                return Typeface.SERIF;
            }
            return Typeface.SANS_SERIF;
        }
    }
}
