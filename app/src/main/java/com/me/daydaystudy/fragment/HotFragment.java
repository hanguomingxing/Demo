package com.me.daydaystudy.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseFragment;
import com.me.daydaystudy.bean.HotTitleData;
import com.me.daydaystudy.interfaces.ConstantUtils;
import com.me.daydaystudy.manager.HttpManger;
import com.me.daydaystudy.manager.MyCallBack;
import com.me.daydaystudy.utils.ToastUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * @author : 张鸿鹏
 * @date : 2017/1/11.
 */

public class HotFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    private View inflate;
    private ViewPager hot_viewPager;
    private TabLayout hot_tabLayout;
    private int lastPosition = -1;
    private TextView currentTextView;
    private TextView nextTextView;
    private TextView lastTextView;
    private ArrayList<HotTitleData.DataBean> list = new ArrayList<>();
    private ArrayList<String> titleList = new ArrayList<>();


    /**
     * 请求网络
     */
    @Override
    protected void initData() {
        super.initData();

        HttpManger.getMethod(ConstantUtils.CircleHotTitleUrl, new MyCallBack() {

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                ToastUtil.show(getActivity(), "请求失败");
            }

            @Override
            public void onResponse(String response) {
                initView();
                HotTitleData hotTitleData = new Gson().fromJson(response, HotTitleData.class);
                List<HotTitleData.DataBean> data = hotTitleData.getData();
                if (data != null && data.size() > 0) {
//                    list.add(new HotTitleData.DataBean());
//                    titleList.add("推荐");
                    list.addAll(data);
                    for (int i = 0; i < data.size(); i++) {
                        titleList.add(data.get(i).getName());
                    }
                }

                addViewPagerAndTabLayout();

            }
        });
    }

    /**
     * 初始化控件
     */
    private void initView() {
        inflate = View.inflate(getActivity(), R.layout.hot_fragment_layout, null);
        hot_viewPager = (ViewPager) inflate.findViewById(R.id.hot_viewPager);
        hot_tabLayout = (TabLayout) inflate.findViewById(R.id.hot_TabLayout);

        requestShowNormalView(inflate);
    }

    /**
     * 添加选择器与ViewPager
     */
    private void addViewPagerAndTabLayout() {

        hot_tabLayout.setupWithViewPager(hot_viewPager);
        hot_viewPager.addOnPageChangeListener(this);
        hot_tabLayout.setTabTextColors(0xFFFFFFFF, 0xFFFFE625);
        currentTextView = getTabView(0);
        nextTextView = getTabView(1);
        hot_viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                HotChildFragment hotChildFragment = new HotChildFragment();
                Bundle bundle = new Bundle();
                bundle.putString("titleTid", list.get(position).getTid());
                hotChildFragment.setArguments(bundle);
                return hotChildFragment;
            }

            @Override
            public int getCount() {
                return titleList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position);
            }
        });
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //最终取值  0-0.5之间  也就是 0-150之间
//        int color = (int) (positionOffset * 100);
        //设置透明度
//        changeTextColorAlpha(color, lastPosition > position ? RIGHT : LEFT);
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            lastTextView = getTabView(hot_viewPager.getCurrentItem() - 1);
            currentTextView = getTabView(hot_viewPager.getCurrentItem());
            nextTextView = getTabView(hot_viewPager.getCurrentItem() + 1);
            //记录上一个position
            lastPosition = hot_viewPager.getCurrentItem();
        }
    }

    private static final int LEFT = 0;
    private static final int RIGHT = 1;

    private void changeTextColorAlpha(int color, int i) {
//        FFE625
        if (i == LEFT) {
            if (nextTextView != null)
                nextTextView.setTextColor(Color.argb(255, 255, color * (255 - 230) + 230, color * (255 - 37) + 37));
        } else if (i == RIGHT) {
            if (lastTextView != null)
                lastTextView.setTextColor(Color.argb(255, 255, color * (255 - 230) + 230, color * (255 - 37) + 37));
        }
        if (currentTextView != null)
            currentTextView.setTextColor(Color.argb(255, 255, color * (255 - 230) + 230, color * (255 - 37) + 37));
    }

    /**
     * 获取tabView
     *
     * @param position
     * @return
     */
    private TextView getTabView(int position) {
        if (position > titleList.size() - 1 || position < 0)
            return null;
        View tabView = ((ViewGroup) hot_tabLayout.getChildAt(0)).getChildAt(position);
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
