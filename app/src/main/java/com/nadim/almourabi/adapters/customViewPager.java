package com.nadim.almourabi.adapters;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by nadim on 4/16/18.
 * from tunisia with love
 */
public class customViewPager extends ViewPager {

    public customViewPager(Context context) {
        super(context);
    }

    public customViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
