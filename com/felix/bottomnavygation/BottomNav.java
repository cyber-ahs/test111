package com.felix.bottomnavygation;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import com.felix.bottomnavygation.Util.Util;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class BottomNav extends LinearLayout {
    private List<ItemNav> itens;
    private OnTabSelectedListener tabSelectedListener;

    /* loaded from: classes.dex */
    public interface OnTabSelectedListener {
        void onTabLongSelected(int i);

        void onTabSelected(int i);
    }

    public BottomNav(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BottomNav(Context context) {
        super(context);
        setOrientation(0);
    }

    public void addItemNav(ItemNav itemNav) {
        if (this.itens == null) {
            this.itens = new ArrayList();
        }
        this.itens.add(itemNav);
    }

    public void setTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        this.tabSelectedListener = onTabSelectedListener;
    }

    public void build() {
        List<ItemNav> list = this.itens;
        if (list == null || list.size() <= 0) {
            return;
        }
        deselectAll();
        for (final int i = 0; i < this.itens.size(); i++) {
            final ItemNav itemNav = this.itens.get(i);
            if (i == 0) {
                itemNav.select();
            }
            itemNav.setOnClickListener(new View.OnClickListener() { // from class: com.felix.bottomnavygation.BottomNav.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (BottomNav.this.tabSelectedListener != null) {
                        BottomNav.this.deselectAll();
                        itemNav.select();
                        TypedValue typedValue = new TypedValue();
                        BottomNav.this.getContext().getTheme().resolveAttribute(16843868, typedValue, true);
                        itemNav.setBackgroundResource(typedValue.resourceId);
                        BottomNav.this.tabSelectedListener.onTabSelected(i);
                    }
                }
            });
            itemNav.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.felix.bottomnavygation.BottomNav.2
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view) {
                    if (itemNav.isProfile()) {
                        BottomNav.this.deselectAll();
                        itemNav.select();
                        TypedValue typedValue = new TypedValue();
                        BottomNav.this.getContext().getTheme().resolveAttribute(16843868, typedValue, true);
                        itemNav.setBackgroundResource(typedValue.resourceId);
                        BottomNav.this.tabSelectedListener.onTabLongSelected(i);
                        return true;
                    }
                    return false;
                }
            });
            addView(itemNav);
        }
    }

    public void dimentionsItemDp(int i) {
        Util.VALUE_SIZE = i;
        Util.VALUE_SIZE = i + 5;
    }

    public void deselectAll() {
        for (ItemNav itemNav : this.itens) {
            itemNav.deselect();
        }
    }

    public void updateImageProfile(String str) {
        for (ItemNav itemNav : this.itens) {
            if (itemNav.isProfile()) {
                itemNav.updatePathImageProfile(str);
            }
        }
    }

    public void selectTab(int i) {
        List<ItemNav> list;
        if (this.tabSelectedListener == null || (list = this.itens) == null || list.size() <= 0) {
            return;
        }
        boolean z = false;
        for (int i2 = 0; i2 < this.itens.size(); i2++) {
            if (i == i2) {
                deselectAll();
                this.itens.get(i).select();
                z = true;
            }
        }
        if (z) {
            this.tabSelectedListener.onTabSelected(i);
        }
    }
}
