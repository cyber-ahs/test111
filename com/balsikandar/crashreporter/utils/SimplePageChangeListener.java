package com.balsikandar.crashreporter.utils;

import androidx.viewpager.widget.ViewPager;
/* loaded from: classes.dex */
public abstract class SimplePageChangeListener implements ViewPager.OnPageChangeListener {
    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public abstract void onPageSelected(int i);
}
