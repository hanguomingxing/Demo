package com.me.daydaystudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.daydaystudy.base.BaseFragment;
import com.me.daydaystudy.interfaces.ConstantUtils;
import com.me.daydaystudy.manager.HttpManger;
import com.me.daydaystudy.manager.MyCallBack;

import java.util.HashMap;

import retrofit2.Call;

/**
 * @author :   郗琛
 * @date :   2017/1/10
 */

public class CategoryFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        HashMap<String, String> stringStringHashMap = new HashMap<>();

        HttpManger.getMethod(ConstantUtils.sort, new MyCallBack() {
                    @Override
            public void onFailure(Call<String> call, Throwable t) {

            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
            }
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
