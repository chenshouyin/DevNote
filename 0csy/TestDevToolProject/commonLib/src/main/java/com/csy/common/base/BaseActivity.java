package com.csy.common.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.Toast;

import com.csy.common.R;
import com.csy.common.ui.dialog.sweetalert.SweetAlertDialog;
import com.csy.common.ui.statusbar.StatusBarHelper;
import com.csy.common.ui.toast.ToastUitl;

/**
 * Created by user on 2017-6-23.
 */

abstract public class BaseActivity extends AppCompatActivity {

    private ToastUitl toastUtil;
    private SweetAlertDialog pDialog0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toastUtil = new ToastUitl(this);
    }

    /**
     * 跳转activity 外部只传类名即可
     * 如需传参等自定义操作可重写该方法
     */
    public void goActivity(Context context,  Class<?> cls){
        Intent intent = new Intent(context,cls);
        startActivity(intent);
    }


    /**
     *销毁activity
     * 如需传参等自定义操作可重写该方法
     */
    public void finishActivity(){
        finish();
    }

    /**
     *
     * @param message
     */
    public void showToast(String message){
        toastUtil.showToast(message);
    }

    /**
     * 取消打印Toast
     */
    public void cancelToast(){
        toastUtil.cancelToast();
    }
    /**
     *
     * @param resId
     */
    public void showToast(int resId){
        toastUtil.showToast(resId);
    }


    //记录用户首次点击返回键的时间
    private long firstTime=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN){
            if (System.currentTimeMillis()-firstTime>2000){
                showToast("再按一次退出");
                firstTime=System.currentTimeMillis();
            }else{
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     *
     * @param tips
     * @param cancelable
     * @param cancelOutsideTouch
     */
    public SweetAlertDialog showProgress(String tips,boolean cancelable,boolean cancelOutsideTouch){
        if (pDialog0==null){
            if (TextUtils.isEmpty(tips)){
                tips = "加载中...";
            }
            pDialog0 = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                    .setTitleText(tips);
        }
        pDialog0.show();
        pDialog0.setCancelable(cancelable);
        pDialog0.setCanceledOnTouchOutside(cancelOutsideTouch);
        return pDialog0;
    }

    public void cancelProgress(){
        if (pDialog0!=null){
            pDialog0.dismissWithAnimation();
        }
    }

    public void setStatusBarColor(){
        StatusBarHelper helper = new StatusBarHelper(
        /*Activity*/
                this,
                /**
                 * level used in 4.4, below value can be set:
                 * LEVEL_NONE (if you don't need tint StatusBar)
                 * LEVEL_19_TRANSLUCENT (set TRANSLUCENT StatusBar and tint)
                 */
                StatusBarHelper.LEVEL_19_TRANSLUCENT,
                /**
                 * level used in 5.x, can be set:
                 * LEVEL_NONE (if you don't need tint StatusBar)
                 * LEVEL_21_NORMAL (use API in 5.x to tint StatusBar)
                 * LEVEL_21_NORMAL_FULL (use API in 5.x to tint StatusBar and set full screen)
                 * LEVEL_21_VIEW (use a View to tint StatusBar that like LEVEL_19_TRANSLUCENT)
                 */
                StatusBarHelper.LEVEL_21_NORMAL
        );
        // set background color
        helper.setColor(Color.RED);
    }
}
