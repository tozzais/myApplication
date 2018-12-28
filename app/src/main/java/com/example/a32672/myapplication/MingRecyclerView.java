package com.example.a32672.myapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by 32672 on 2018/12/26.
 */


public class MingRecyclerView extends RecyclerView {
    private int mFirstY;
    public MingRecyclerView(Context context) {
        super(context);
    }

    public MingRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MingRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        if(e.getAction()==MotionEvent.ACTION_DOWN)mFirstY= (int) e.getY();
        return super.onInterceptTouchEvent(e);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    public int getTouchPointY() {
        return mFirstY;
    }

}
