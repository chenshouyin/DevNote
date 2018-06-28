package com.emojicat.utils.app.typeface;

import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by chenshouyin on 17/4/4.
 * <p>
 * 如果每次都这样加载,界面就会卡顿,所以我提取出来了一个工具类.
 * 示例中涉及到了两个自定义的字体,用枚举实现了单例模式,将字体存
 * 储在静态变量中,避免每次都去 assets 中加载,更改之后页面就流畅了.
 */

public enum TypefaceUtils {
    TYPEFACE;

    private static Typeface typeface50;
    private static Typeface typeface55;

    public void set50Typeface(TextView textView) {
        if (typeface50 == null)
            typeface50 = Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/HYQiHei-50S.otf");
        textView.setTypeface(typeface50);
    }

    public void set55Typeface(TextView textView) {
        if (typeface55 == null)
            typeface55 = Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/HYQiHei-55S.otf");
        textView.setTypeface(typeface55);
    }
}