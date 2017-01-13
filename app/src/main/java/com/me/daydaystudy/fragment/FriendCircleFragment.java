package com.me.daydaystudy.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.daydaystudy.R;
import com.me.daydaystudy.activity.MainActivity;
import com.me.daydaystudy.base.BaseFragment;
import com.me.daydaystudy.factory.CircleyFragmentFactory;

/**
 * @author :   郗琛
 * @date :   2017/1/10
 */

public class FriendCircleFragment extends BaseFragment {
    private String[] tabTitle = new String[]{"话题", "热门", "关注"};
    private TabLayout tabLayout;
    private ViewPager viewRoot;

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
                return tabTitle.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabTitle[position];
            }
        });
        tabLayout.setupWithViewPager(viewRoot);


        return viewRoot;
    }


}
