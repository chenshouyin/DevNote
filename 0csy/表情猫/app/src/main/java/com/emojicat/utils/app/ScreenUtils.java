package com.emojicat.utils.app;

/**
 * Created by chenshouyin on 17/5/14.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * @Description: 获得屏幕相关的辅助类
 * @ClassName: ScreenUtils
 * @Author: BiHaidong
 * @Version: V1.0
 * @Date: 2015-4-1 上午9:51:43
 */
public class ScreenUtils {
    private ScreenUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * @Description: 获得屏幕高度
     * @Title: getScreenWidth
     * @param context
     * @return
     * @ReturnType: int
     * @Author: BiHaidong
     * @Version: V1.0
     * @Date: 2015-4-1 上午9:52:08
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * @Description: 获得屏幕宽度
     * @Title: getScreenHeight
     * @param context
     * @return
     * @ReturnType: int
     * @Author: BiHaidong
     * @Version: V1.0
     * @Date: 2015-4-1 上午9:52:14
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * @Description: 获得状态栏的高度
     * @Title: getStatusHeight
     * @param context
     * @return
     * @ReturnType: int
     * @Author: BiHaidong
     * @Version: V1.0
     * @Date: 2015-4-1 上午9:52:23
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * @Description: 获取当前屏幕截图，包含状态栏
     * @Title: snapShotWithStatusBar
     * @param activity
     * @return
     * @ReturnType: Bitmap
     * @Author: BiHaidong
     * @Version: V1.0
     * @Date: 2015-4-1 上午9:52:31
     */
    public static Bitmap snapShotWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;

    }

    /**
     * @Description: 获取当前屏幕截图，不包含状态栏
     * @Title: snapShotWithoutStatusBar
     * @param activity
     * @return
     * @ReturnType: Bitmap
     * @Author: BiHaidong
     * @Version: V1.0
     * @Date: 2015-4-1 上午9:52:38
     */
    public static Bitmap snapShotWithoutStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        return bp;

    }

}