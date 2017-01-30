package com.me.daydaystudy.manager;


import android.text.TextUtils;

import com.me.daydaystudy.interfaces.ProjectAPI;

import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.me.daydaystudy.interfaces.ConstantUtils.BASE_URL;

/**
 * Created by zhiyuan on 17/1/11.
 */

public class HttpManger {


    //----------------------------------------------------------------------------------------------
    //下方为get请求

    /**
     * 通过get请求请求数据
     *
     * @param url
     * @param callback
     */
    public static void getMethod(String url, MyCallBack callback) {
        getMethod(url, callback, 0);
    }

    /**
     * 通过get请求请求数据
     *
     * @param url
     * @param callback
     * @param time     超时时间
     */
    public static void getMethod(final String url, final MyCallBack callback, final int time) {
        String respond = "";
        if (time > 0) {
            respond = SaveCache.getCache(url);
        }
        if (!TextUtils.isEmpty(respond)) {
            callback.onResponse(respond);
            return;
        }
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(ScalarsConverterFactory.create()).build();

        ProjectAPI projectAPI = retrofit.create(ProjectAPI.class);

        Call<String> call = projectAPI.getMethod(url);

        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response == null)
                    throw new RuntimeException("response 为空" + response);
                if (response.body() != null) {
                    SaveCache.saveCache(url, response.body(), time);
                    callback.onResponse(response.body());
                } else {
                    callback.onFailure(call, new RuntimeException("数据出错，body为空" + response));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call, t);
            }

        });

    }


    //----------------------------------------------------------------------------------------------
    //下方为post请求

    /**
     * 普通的post请求
     *
     * @param url
     * @param map
     * @param callback
     */
    public static void postMethod(String url, Map<String, String> map, final MyCallBack callback) {
        postMethod(url, map, callback, 0);
    }

    /**
     * 带保存时间的post请求
     *
     * @param url
     * @param map
     * @param callback
     * @param time
     */

    public static void postMethod(String url, Map<String, String> map, final MyCallBack callback, int time) {
        postMethod(false, false, url, map, callback, time);
    }


    /**
     * 带cookie的post请求
     *
     * @param url
     * @param map
     * @param callback
     */
    public static void postMethodAndReadCookoe(String url, Map<String, String> map, final MyCallBack callback) {
        postMethod(true, false, url, map, callback, 0);
    }

    /**
     * 请求的cookie写入到本地的请求
     *
     * @param url
     * @param map
     * @param callback
     */
    public static void postMethodAndSaveCookie(String url, Map<String, String> map, final MyCallBack callback) {
        postMethod(false, true, url, map, callback, 0);
    }

    /**
     * 通过post请求请求数据
     *
     * @param isReadCookie
     * @param isSaveCookie
     * @param url
     * @param map
     * @param callback
     */
    public static void postMethod(boolean isReadCookie, boolean isSaveCookie, final String url, Map<String, String> map, final MyCallBack callback, final int time) {
        if (time > 0) {
            //如果本地有数据，直接返回
            if (!TextUtils.isEmpty(SaveCache.getCache(url))) {
                callback.onResponse(SaveCache.getCache(url));
                return;
            }
        }

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (isReadCookie) {
            builder.addInterceptor(new ReadCookiesInterceptor());
        } else if (isSaveCookie) {
            builder.addInterceptor(new SaveCookiesInterceptor());
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(builder.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ProjectAPI projectAPI = retrofit.create(ProjectAPI.class);

        Call<String> call = projectAPI.postMethod(url, map);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                SaveCache.saveCache(url, response.body(), time);
                callback.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }
}
