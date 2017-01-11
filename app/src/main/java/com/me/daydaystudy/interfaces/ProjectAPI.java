package com.me.daydaystudy.interfaces;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by zhiyuan on 17/1/11.
 */

public interface ProjectAPI {

    @GET
    Call<String> getMethod(@Url String url);

    @FormUrlEncoded
    @POST
    Call<String> postMethod(@Url String url, @FieldMap Map<String, String> map);

}
