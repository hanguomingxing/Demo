package com.me.daydaystudy.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.google.gson.Gson;
import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseFragment;
import com.me.daydaystudy.bean.HotTitleData;
import com.me.daydaystudy.factory.HotChildFragmentFactory;
import com.me.daydaystudy.interfaces.ConstantUtils;
import com.me.daydaystudy.manager.HttpManger;
import com.me.daydaystudy.manager.MyCallBack;
import com.me.daydaystudy.utils.ToastUtil;
import com.me.daydaystudy.view.MyTabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

import static com.me.daydaystudy.R.id.hot_TabLayout;

/**
 * @author : 张鸿鹏
 * @date : 2017/1/11.
 */

public class HotFragment extends BaseFragment {

    private View inflate;
    private ViewPager hot_viewPager;
    private MyTabLayout hot_tabLayout;
    private ArrayList<HotTitleData.DataBean> list = new ArrayList<>();


    /**
     * 请求网络
     */
    @Override
    protected void init() {
        super.init();
        initView();
        requestData();
    }

    private void requestData() {
        HttpManger.getMethod(ConstantUtils.CircleHotTitleUrl, new MyCallBack() {

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                ToastUtil.show(getActivity(), "请求失败");
            }

            @Override
            public void onResponse(String response) {

                HotTitleData hotTitleData = new Gson().fromJson(response, HotTitleData.class);
                List<HotTitleData.DataBean> data = hotTitleData.getData();
                if (data != null && data.size() > 0) {
                    list.add(new HotTitleData.DataBean("-1", "推荐"));
                    list.addAll(data);
                }
                addViewPagerAndTabLayout();
                if (inflate.getParent() == null) {
                    requestShowNormalView(inflate);
                }
            }
        });
    }

    /**
     * 初始化控件
     */
    private void initView() {
        inflate = View.inflate(getActivity(), R.layout.hot_fragment_layout, null);
        hot_viewPager = (ViewPager) inflate.findViewById(R.id.hot_viewPager);
        hot_tabLayout = (MyTabLayout) inflate.findViewById(hot_TabLayout);
        hot_tabLayout.setupWithViewPager(hot_viewPager);

    }

    /**
     * 添加选择器与ViewPager
     */
    private void addViewPagerAndTabLayout() {

        hot_viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                //通过id找到对应的fragment
                Fragment fragment = HotChildFragmentFactory.getHotChildFragmentInstance(list.get(position).getTid());
                return fragment;
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return list.get(position).getName();
            }
        });
    }


}
