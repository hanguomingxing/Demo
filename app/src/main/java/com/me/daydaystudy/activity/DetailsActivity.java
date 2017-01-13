package com.me.daydaystudy.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseActivity;
import com.me.daydaystudy.fragment.CommentsFragment;
import com.me.daydaystudy.fragment.DetailsFragment;
import com.me.daydaystudy.fragment.DirectoryFragment;

/**
 * autour: 孙运泰
 * date: 2017/1/13 13:43
 * update: 2017/1/13
 */
public class DetailsActivity extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager viewp;
    private String[] title = new String[]{"详情", "目录", "评论(0)"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initView();
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.details_tablayout);
        viewp = (ViewPager) findViewById(R.id.vp);
        tabLayout.addTab(tabLayout.newTab().setText(title[0]), true);

        addTab();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return title.length;
            }

            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = new DetailsFragment();
                        break;
                    case 1:
                        fragment = new DirectoryFragment();
                        break;
                    case 2:
                        fragment = new CommentsFragment();
                        break;
                }
                return fragment;
            }


        });
        viewp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void addTab() {
        for (int i = 1; i < title.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(title[i]), false);
        }
    }

}
