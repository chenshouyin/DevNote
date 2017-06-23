package com.tool.csy.devcsytool.statusbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;

/**
 * 使用5.x自带的方法设置status bar颜色。适用于大部分的情况。
 * created by csy on 2017/05/03.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class StatusBarHelperImpl21Normal extends StatusBarHelperImplBase {

    public StatusBarHelperImpl21Normal(Activity activity) {
        super(activity);
    }

    @Override
    protected void setColor(int color) {
        mActivity.getWindow().setStatusBarColor(color);
        setActivityRootLayoutFitSystemWindowsInternal();
    }

    @Override
    protected void setDrawable(Drawable drawable) {
        // not support
    }

    @Override
    protected void destroy() {
        // don't need
    }
}
