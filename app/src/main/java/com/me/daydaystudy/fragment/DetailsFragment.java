package com.me.daydaystudy.fragment;

import android.view.View;

import com.google.gson.Gson;
import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseFragment;
import com.me.daydaystudy.interfaces.ConstantUtils;
import com.me.daydaystudy.manager.HttpManger;
import com.me.daydaystudy.manager.MyCallBack;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
/**
 * autour: 孙运泰
 * date: 2017/1/16 14:25
 * update: 2017/1/16
 */
public class DetailsFragment extends BaseFragment {
    private View view;

    @Override
    protected void initData() {
        super.initData();
/*

        String cid0 = getArguments().getString("cid0");
        Toast.makeText(getActivity(), "cidqqqqqqqqq", Toast.LENGTH_SHORT).show();
*/

        Map<String, String> map = new HashMap<>();
//        map.put("",);
        HttpManger.postMethod(ConstantUtils.USER_XiaoBIAN, map, new MyCallBack() {
            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();

                initView();

            }
        });
    }


    private void initView() {

        view = View.inflate(getActivity(), R.layout.detailsfragment, null);

        //展示请求
        requestShowNormalView(view);
    }
}
