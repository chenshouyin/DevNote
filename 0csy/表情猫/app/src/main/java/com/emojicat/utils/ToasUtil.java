package com.emojicat.utils;


import android.widget.Toast;

import com.emojicat.ui.widget.recyclerview.utils.Utils;

public class ToasUtil {
    private static Toast mToastShort;

    public static void showToast(String info) {
        if (mToastShort != null) {
            mToastShort.setText(info);
        } else {
            mToastShort = Toast.makeText(Utils.getContext(), info, Toast.LENGTH_SHORT);
        }
        mToastShort.show();
    }


    /**
     * 取消吐司显示
     */
    public static void cancelToast() {
        if (mToastShort != null) {
            mToastShort.cancel();
            mToastShort = null;
        }
    }
}