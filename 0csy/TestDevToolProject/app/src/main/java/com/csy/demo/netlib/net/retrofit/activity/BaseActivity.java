package com.csy.demo.netlib.net.retrofit.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;

import com.csy.net.utils.ToastUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;


public abstract class BaseActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        init(savedInstanceState);
    }
    protected void showToast(String msg) {
        ToastUtils.show(msg);
    }

    protected abstract @LayoutRes int getLayoutId();

    protected abstract void init(Bundle savedInstanceState);
}
