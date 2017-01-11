package com.me.daydaystudy.manager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author :   郗琛
 * @date :   2017/1/11
 */

public abstract class MyCallBack implements Callback<String> {

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        onResponse(response.body());
    }

    @Override
    public abstract void onFailure(Call<String> call, Throwable t);

    /**
     * 我只要一个字符串
     *
     * @param response
     */
    public abstract void onResponse(String response);

}
