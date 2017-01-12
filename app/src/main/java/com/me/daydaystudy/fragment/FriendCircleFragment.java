package com.me.daydaystudy.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.me.daydaystudy.R;
import com.me.daydaystudy.activity.MainActivity;
import com.me.daydaystudy.base.BaseFragment;

import java.lang.reflect.Field;

/**
 * @author :   郗琛
 * @date :   2017/1/10
 */

public class FriendCircleFragment extends BaseFragment {
    private ViewPager viewRoot;
    private String[] tabTitle = new String[]{"话题", "热门", "关注"};
    private TabLayout tabLayout;
    private ViewPager viewRoot;
    private int lastPosition = -1;
    private TextView currentTextView;
    private TextView nextTextView;
    private TextView lastTextView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        tabLayout = ((MainActivity) getActivity()).getTabLayout();
        tabLayout.setTabTextColors(0xFFFFFFFF, 0xFFFFFFFF);
        tabLayout.setSelectedTabIndicatorColor(0xFFFFFFFF);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewRoot = (ViewPager) inflater.inflate(R.layout.fragment_circle, null);
        viewRoot.setOffscreenPageLimit(2);
        viewRoot.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return CircleyFragmentFactory.getFragmentInstance(position);
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabTitle[position];
            }
        });
        tabLayout.setupWithViewPager(viewRoot);

        /*//test
        viewRoot.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                //最终取值  0-0.5之间  也就是 0-150之间
                double alpha = (Math.abs(positionOffset - 0.5)) * 300;
                //设置透明度
                changeTextColorAlpha((int) (alpha) + 100, lastPosition > position ? RIGHT : LEFT);

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    lastTextView = getTabView(viewRoot.getCurrentItem() - 1);
                    currentTextView = getTabView(viewRoot.getCurrentItem());
                    nextTextView = getTabView(viewRoot.getCurrentItem() + 1);

                    //记录上一个position
                    lastPosition = viewRoot.getCurrentItem();
                }
            }
        });
        currentTextView = getTabView(0);
        nextTextView = getTabView(1);*/
        return viewRoot;
    }

    private static final int LEFT = 0;
    private static final int RIGHT = 1;

    /*private void changeTextColorAlpha(int alpha, int i) {

        if (i == LEFT) {
            if (nextTextView != null)
                nextTextView.setTextColor(Color.argb(alpha, 255, 255, 255));
        } else if (i == RIGHT) {
            if (lastTextView != null)
                lastTextView.setTextColor(Color.argb(alpha, 255, 255, 255));
        }
        if (currentTextView != null)
            currentTextView.setTextColor(Color.argb(alpha, 255, 255, 255));
    }*/

    /**
     * 获取tabView
     *
     * @param position
     * @return
     *//*
    private TextView getTabView(int position) {
        if (position > tabTitle.length - 1 || position < 0)
            return null;
        View tabView = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(position);
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
    }*/
}
