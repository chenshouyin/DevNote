package com.csy.demo.devtool.netlib.net.retrofit.net;


import com.csy.net.net.common.Constants;
import com.csy.net.net.common.RetrofitApi;

public class RetrofitHelper {
    private static RetrofitApiService mIdeaApiService;

    public static RetrofitApiService getApiService(){
        return mIdeaApiService;
    }
    static {
       mIdeaApiService= RetrofitApi.getApiService(RetrofitApiService.class, Constants.API_SERVER_URL);
    }

    public static RetrofitApiService getApiService2(){
        return mIdeaApiService;
    }
    static {
        mIdeaApiService= RetrofitApi.getApiService(RetrofitApiService.class, Constants.API_SERVER_URL2);
    }
}
