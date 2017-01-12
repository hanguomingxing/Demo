package com.me.daydaystudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseFragment;
import com.me.daydaystudy.interfaces.ConstantUtils;
import com.me.daydaystudy.manager.HttpManger;
import com.me.daydaystudy.manager.MyCallBack;
import com.youth.banner.Banner;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**
 * @author : 张鸿鹏
 * @date : 2017/1/11.
 */

public class HotFragment extends BaseFragment {

    private View inflate;
    private Banner banner;
    private ViewPager hot_viewPager;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 请求网络
     */
    @Override
    protected void initData() {
        super.initData();

        Map<String, String> map = new HashMap<String, String>();
        map.put("tid", "79");
        map.put("page", "1");
        HttpManger.postMethod(ConstantUtils.CircleHotUrl, map, new MyCallBack() {
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                initView();
            }
        });

    }

    /**
     * 初始化控件
     */
    private void initView() {
        inflate = View.inflate(getActivity(), R.layout.hot_fragment_layout, null);
        hot_viewPager = (ViewPager) inflate.findViewById(R.id.hot_viewPager);
        requestShowNormalView(inflate);

    }


}
