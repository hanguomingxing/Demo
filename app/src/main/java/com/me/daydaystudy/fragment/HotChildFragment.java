package com.me.daydaystudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseFragment;

/**
 * @author : 张鸿鹏
 * @date : 2017/1/12.
 */

/**
 * 热门的十一个fragment
 */
public class HotChildFragment extends BaseFragment {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    protected void initData() {
        super.initData();
        initView();

    }


    private void initView() {
        View view = View.inflate(getActivity(), R.layout.hot_child_fragmnet, null);
        requestShowNormalView(view);
    }
}
