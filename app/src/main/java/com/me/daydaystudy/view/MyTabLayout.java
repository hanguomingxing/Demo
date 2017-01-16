package com.me.daydaystudy.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Field;

import static android.graphics.Color.argb;

/**
 * @author :   郗琛
 * @date :   2017/1/13
 */

public class MyTabLayout extends TabLayout implements ViewPager.OnPageChangeListener {
    private int lastPosition = 0;
    private ViewPager viewPager;
    private TextView currentTextView;
    private TextView nextTextView;
    private TextView lastTextView;
    private int normalTextColor = 0xFFFFFFFF;
    private int selectTextColor = 0xFF000000;
    private int defaultIndicatorColor = 0xFFFFE625;


    public MyTabLayout(Context context) {
        super(context);
    }

    public MyTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setViewPager(final ViewPager viewPager) {
        this.viewPager = viewPager;
        this.setupWithViewPager(viewPager);
        viewPager.setOnPageChangeListener(this);
        setTabTextColors(selectTextColor);
        setSelectedTabIndicatorColor(defaultIndicatorColor);
        currentTextView = getTabView(0);
        nextTextView = getTabView(1);
        this.setOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(Tab tab) {

            }

            @Override
            public void onTabReselected(Tab tab) {

            }
        });
    }

    public void setTabTextColors(int selectedColor) {
        setTabTextColors(normalTextColor, selectedColor);
    }

    @Override
    public void setTabTextColors(int normalColor, int selectedColor) {
        selectTextColor = selectedColor;
        super.setTabTextColors(normalTextColor, selectedColor);
    }


    @Override
    public void setSelectedTabIndicatorColor(@ColorInt int color) {
        defaultIndicatorColor = color;
        super.setSelectedTabIndicatorColor(color);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        //最终取值  0-0.5之间  也就是 0-150之间

        //设置透明度
        if (positionOffset != 0 && lastPosition > position)
            changeTextColorAlpha(positionOffset, RIGHT);
        if (positionOffset != 0 && lastPosition == position)
            changeTextColorAlpha(1 - positionOffset, LEFT);

    }

    private static final int LEFT = 0;
    private static final int RIGHT = 1;

    private void changeTextColorAlpha(float percentage, int direction) {
        float last = percentage;
        float current = (1 - percentage);
        if (direction == LEFT) {
            if (nextTextView != null) {
                nextTextView.setTextColor(argb(255, (int) (Color.red(normalTextColor) * last), (int) (Color.red(normalTextColor) * last), (int) (Color.red(normalTextColor) * last)));
            }
        } else if (direction == RIGHT) {
            if (lastTextView != null) {
                lastTextView.setTextColor(argb(255, (int) (Color.red(normalTextColor) * last), (int) (Color.red(normalTextColor) * last), (int) (Color.red(normalTextColor) * last)));
            }
        }
        if (currentTextView != null) {
            currentTextView.setTextColor(argb(255, (int) (Color.red(normalTextColor) * current), (int) (Color.red(normalTextColor) * current), (int) (Color.red(normalTextColor) * current)));
        }
    }

    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            lastPosition = viewPager.getCurrentItem();

            lastTextView = getTabView(viewPager.getCurrentItem() - 1);
            currentTextView = getTabView(viewPager.getCurrentItem());
            nextTextView = getTabView(viewPager.getCurrentItem() + 1);
        }
    }

    /**
     * 获取tabView
     *
     * @param position
     * @return
     */
    private TextView getTabView(int position) {
        if (position > viewPager.getAdapter().getCount() - 1 || position < 0)
            return null;
        View tabView = ((ViewGroup) this.getChildAt(0)).getChildAt(position);
        if (tabView == null)
            return null;
        Class<? extends View> tabViewClass = tabView.getClass();
        try {
            Field textView = tabViewClass.getDeclaredField("mTextView");
            textView.setAccessible(true);
            return (TextView) textView.get(tabView);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
