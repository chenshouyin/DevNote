package com.emojicat.utils.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import com.emojicat.R;

/**
 * Created by chenshouyin on 17/4/26.
 */

public class ViewToImageUtil {

    public static Bitmap getViewBitmap(View view, Context context,int width,int height) {
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = null;
        try {
            if (null != view.getDrawingCache()) {
                bitmap = Bitmap.createScaledBitmap(view.getDrawingCache(), width, height, false);
            } else {
                bitmap = ((BitmapDrawable) (context.getResources().getDrawable(R.mipmap.logo))).getBitmap();
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } finally {
            view.setDrawingCacheEnabled(false);
            view.destroyDrawingCache();
        }

        return bitmap;
    }
}
