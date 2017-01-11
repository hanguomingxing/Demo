package com.me.daydaystudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.me.daydaystudy.base.BaseFragment;
import com.me.daydaystudy.interfaces.ConstantUtils;
import com.me.daydaystudy.manager.HttpManger;
import com.me.daydaystudy.manager.MyCallBack;

import retrofit2.Call;

/**
 * @author :   郗琛
 * @date :   2017/1/10
 */

public class CategoryFragment extends BaseFragment {


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    protected void initData() {
        super.initData();
        HttpManger.getMethod(ConstantUtils.sort, new MyCallBack() {
                    @Override
            public void onFailure(Call<String> call, Throwable t) {
//                requestChangeViewStatus(ERROR_VIEW);
            }

            @Override
            public void onResponse(String response) {
                TextView textView = new TextView(getActivity());
                textView.setText(response);
                requestShowNormalView(textView);
            }
        });
    }
}
