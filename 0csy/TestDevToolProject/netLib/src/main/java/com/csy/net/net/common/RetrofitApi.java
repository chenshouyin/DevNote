package com.csy.net.net.common;

import retrofit2.Retrofit;

/**
 * Created by csy on 2017/4/1.
 */

public class RetrofitApi {
    public static <T> T getApiService(Class<T> cls,String baseUrl) {
        Retrofit retrofit = RetrofitUtils.getRetrofitBuilder(baseUrl).build();
        return retrofit.create(cls);
    }
}
