package com.me.daydaystudy.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseActivity;
import com.me.daydaystudy.factory.MainFragmentFactory;

public class MainActivity extends BaseActivity {

    private ViewPager mainVp;
    private RadioGroup footMainRg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initTitle();
        changeCurrentItem(0);
        changeTitle(0);
    }

    /**
     * 初始化title
     */
    private void initTitle() {
        findViewById(R.id.title_home).setVisibility(View.VISIBLE);
    }


    /**
     * 初始化控件
     */
    private void initView() {
        mainVp = (ViewPager) findViewById(R.id.main_vp);
        footMainRg = (RadioGroup) findViewById(R.id.footer_main);

        //viewPager的适配器
        mainVp.setOffscreenPageLimit(3);
        mainVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return MainFragmentFactory.getFragmentInstance(position);
            }

            @Override
            public int getCount() {
                return 4;
            }
        });

        //viewPager的改变监听
        mainVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTitle(position);
                changeCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //footer监听
        for (int i = 0; i < footMainRg.getChildCount(); i++) {
            final int finalI = i;
            footMainRg.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeTitle(finalI);
                    changeCurrentItem(finalI);
                }
            });
        }
    }


    /**
     * 设置当前的位置
     *
     * @param position
     */
    public void changeCurrentItem(int position) {
        mainVp.setCurrentItem(position);
        ((RadioButton) footMainRg.getChildAt(position)).setChecked(true);
    }


    /**
     * 动态改变title
     */
    public void changeTitle(int position) {
        findViewById(R.id.title_addFriend).setVisibility(position == 2 ? View.VISIBLE : View.GONE);
        findViewById(R.id.title_tab).setVisibility(position == 2 ? View.VISIBLE : View.GONE);
        findViewById(R.id.title_search).setVisibility(position != 3 ? View.VISIBLE : View.GONE);
        findViewById(R.id.title_home).setVisibility(position == 0 ? View.VISIBLE : View.GONE);
        findViewById(R.id.title_text).setVisibility(position == 1 ? View.VISIBLE : View.GONE);
        findViewById(R.id.mine_title).setVisibility(position == 3 ? View.VISIBLE : View.GONE);
    }


    public TabLayout getTabLayout() {
        return (TabLayout) findViewById(R.id.title_tab);
    }
}
