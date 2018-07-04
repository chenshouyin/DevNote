package com.csy.demo.devtool.netlib.net.retrofit.module.request;


import com.csy.net.utils.SharedPreferencesHelper;
import com.csy.net.utils.Utils;

/**
 * Created by csy on 2017/10/25.
 * Description:
 */

public class BasicRequest {
    public String token = (String) SharedPreferencesHelper.get(Utils.getContext(), "token", "");

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
