package com.example.csy.testdevtoolproject;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import com.example.csy.testdevtoolproject.ui.dialog.ProgressDialogActivity;
import com.example.csy.testdevtoolproject.ui.dialog.SweetAlertDialogActivity;
import com.example.csy.testdevtoolproject.ui.statusbar.StatusBarActivity;
import com.example.csy.testdevtoolproject.utils.countdowntimer.CountDownTimerActivity;
import com.tool.csy.devcsytool.base.BaseActivity;
import com.tool.csy.devcsytool.ui.dialog.sweetalert.SweetAlertDialog;
import com.tool.csy.devcsytool.ui.statusbar.StatusBarHelper;
import com.tool.csy.devcsytool.utils.log.klog.KLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.btStatusBarActivity)
    Button btStatusBarActivity;
    @BindView(R.id.btProgressDialogActivity)
    Button btProgressDialogActivity;
    @BindView(R.id.btSweetAlertDialogActivity)
    Button btSweetAlertDialogActivity;
    @BindView(R.id.btCountDownTimerActivity)
    Button btCountDownTimerActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setStatusBarColor();
//        StatusBarHelper helper = new StatusBarHelper(
//        /*Activity*/
//                this,
//                /**
//                 * level used in 4.4, below value can be set:
//                 * LEVEL_NONE (if you don't need tint StatusBar)
//                 * LEVEL_19_TRANSLUCENT (set TRANSLUCENT StatusBar and tint)
//                 */
//                StatusBarHelper.LEVEL_19_TRANSLUCENT,
//                /**
//                 * level used in 5.x, can be set:
//                 * LEVEL_NONE (if you don't need tint StatusBar)
//                 * LEVEL_21_NORMAL (use API in 5.x to tint StatusBar)
//                 * LEVEL_21_NORMAL_FULL (use API in 5.x to tint StatusBar and set full screen)
//                 * LEVEL_21_VIEW (use a View to tint StatusBar that like LEVEL_19_TRANSLUCENT)
//                 */
//                StatusBarHelper.LEVEL_21_NORMAL
//        );
//
//        // set background color
//        helper.setColor(getResources().getColor(R.color.colorPrimaryDark));
//        // or set background drawable
//        // helper.setDrawable(new ColorDrawable(Color.BLUE));

        SweetAlertDialog alertDialog = showProgress("加载中...",true,false);
//        alertDialog.showCancelButton(true);
//        alertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
//            @Override
//            public void onClick(SweetAlertDialog sweetAlertDialog) {
//                showToast("取消对话框回调");
//            }
//        });
        final CountDownTimer mCountDownTimer = new CountDownTimer(60*1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                KLog.d("KLog","加载中...");
            }

            @Override
            public void onFinish() {
                cancelProgress();
            }
        }.start();
    }


    @Override
    public void goActivity(Context context, Class<?> cls) {
        super.goActivity(context, cls);
    }

    @OnClick({R.id.btStatusBarActivity, R.id.btProgressDialogActivity, R.id.btSweetAlertDialogActivity, R.id.btCountDownTimerActivity})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btStatusBarActivity:
                goActivity(MainActivity.this, StatusBarActivity.class);
                break;
            case R.id.btProgressDialogActivity:
                goActivity(MainActivity.this, ProgressDialogActivity.class);

                break;
            case R.id.btSweetAlertDialogActivity:
                goActivity(MainActivity.this, SweetAlertDialogActivity.class);
                break;
            case R.id.btCountDownTimerActivity:
                goActivity(MainActivity.this, CountDownTimerActivity.class);
                break;


        }
    }

}
