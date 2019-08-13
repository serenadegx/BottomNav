package com.example.bottomnavview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class BottomNavView extends RadioGroup implements ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;

    public void setupWithViewPager(ViewPager mViewPager) {
        this.mViewPager = mViewPager;
        mViewPager.addOnPageChangeListener(this);
    }

    public BottomNavView(Context context) {
        super(context);
    }

    public BottomNavView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //获取每个子控件，设置点击切换viewpager
        for (int i = 0; i < getChildCount(); i++) {
            final int position = i;
            getChildAt(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //更新子控件check
                    for (int i = 0; i < getChildCount(); i++) {
                        ((BottomChildView) getChildAt(i)).setRadioButtonChecked(false);
                    }
                    ((BottomChildView) getChildAt(position)).setRadioButtonChecked(true);
                    if (mViewPager != null) {
                        mViewPager.setCurrentItem(position, false);//切换关掉滑动效果
                    }
                }
            });
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.i("mango", "Offset:" + positionOffset);
        if (positionOffset > 0) {
            ((BottomChildView) getChildAt(position)).updateAlpha(255 * (1 - positionOffset));
            ((BottomChildView) getChildAt(position + 1)).updateAlpha(255 * positionOffset);
        }
    }

    @Override
    public void onPageSelected(int i) {
        //更新底部子控件的check状态
        for (int j = 0; j < getChildCount(); j++) {
            ((RadioButton) getChildAt(i)).setChecked(false);
        }
        ((RadioButton) getChildAt(i)).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
