package com.csy.demo.netlib.net.retrofit.fragment;

import android.os.Bundle;

import com.csy.demo.R;


/**
 * Created by csy on 2017/9/30.
 * Fragment没有运行 内容仅供参考
 */

public class TestFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        getData();
    }

    public void getData() {

       /* RetrofitApi.getApiService(RetrofitApiService.class)
                .getMezi()
                .compose(this.<BasicResponse<List<MeiZi>>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BasicResponse<List<MeiZi>>>(getActivity()) {
                    @Override
                    public void onSuccess(BasicResponse<List<MeiZi>> response) {
                        List<MeiZi> results = response.getResults();
                        showToast("请求成功，妹子个数为"+results.size());
                    }
                });*/
    }
}
