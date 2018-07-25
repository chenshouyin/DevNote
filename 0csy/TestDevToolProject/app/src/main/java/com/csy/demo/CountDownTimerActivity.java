package com.csy.demo;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.csy.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2017-6-28.
 */

public class CountDownTimerActivity extends BaseActivity {
    @BindView(R.id.btCountDownTimer)
    Button btCountDownTimer;
    @BindView(R.id.btCountDownTimerCansel)
    Button btCountDownTimerCansel;
    @BindView(R.id.btCountDownTimerFinish)
    Button btCountDownTimerFinish;
    private CountDownTimer mCountDownTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_time_count_down);
        ButterKnife.bind(this);

        mCountDownTimer = new CountDownTimer(60*1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                btCountDownTimer.setText((millisUntilFinished / 1000) + "秒后可重发");
                btCountDownTimer.setEnabled(false);
            }

            @Override
            public void onFinish() {
                btCountDownTimer.setEnabled(true);
                btCountDownTimer.setText("获取验证码");
            }
        };
    }

    @OnClick(R.id.btCountDownTimer)
    public void onClick() {
    }

    @OnClick({R.id.btCountDownTimer, R.id.btCountDownTimerCansel, R.id.btCountDownTimerFinish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btCountDownTimer:
                mCountDownTimer.start();
                break;
            case R.id.btCountDownTimerCansel:
                mCountDownTimer.cancel();

                break;
            case R.id.btCountDownTimerFinish:
                mCountDownTimer.onFinish();
                mCountDownTimer.cancel();

                break;
        }
    }
}
