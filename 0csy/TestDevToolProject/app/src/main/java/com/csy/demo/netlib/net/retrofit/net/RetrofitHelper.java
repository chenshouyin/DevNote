package com.csy.demo.netlib.net.retrofit.net;


import com.csy.net.net.common.Constants;
import com.csy.net.net.common.RetrofitApi;

public class RetrofitHelper {
    private static com.csy.demo.netlib.net.retrofit.net.RetrofitApiService mIdeaApiService;
    private static com.csy.demo.netlib.net.retrofit.net.RetrofitApiService mIdeaApiService2;

    public static com.csy.demo.netlib.net.retrofit.net.RetrofitApiService getApiService(){
        return mIdeaApiService;
    }
    static {
       mIdeaApiService= RetrofitApi.getApiService(com.csy.demo.netlib.net.retrofit.net.RetrofitApiService.class, Constants.API_SERVER_URL);
    }

    public static com.csy.demo.netlib.net.retrofit.net.RetrofitApiService getApiService2(){
        return mIdeaApiService2;
    }
    static {
        mIdeaApiService2= RetrofitApi.getApiService(com.csy.demo.netlib.net.retrofit.net.RetrofitApiService.class, Constants.API_SERVER_URL2);
    }
}
