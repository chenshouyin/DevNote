package com.emojicat.utils.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

public class BitmapUtil
{
	
	
	/**
	 * 
	 * 第一种方法： 
	 * 
	 * View 转成 Bitmap
	 * 
	 * @param view
	 * @return
	 */
	public static Bitmap convertViewToBitmap(View view)
	{
		if(view == null)
		{
			return null;
		}
		/**
		 * 需要优化： 
		 * 
		 * View 过大容易内存溢出，这需要优化， View 控制在一定的范围内。
		 * 
		 * OutOfMemoryError
		 * 
		 * 必须优化
		 * 必须优化
		 * 必须优化
		 *
		 * 宽 高  需要 等比 
		 * 
		 */

		System.out.println("---view---Width-1--- : " + view.getWidth()); //  1513
		System.out.println("---view---Height-1--- : " + view.getHeight()); // 53387
		
		Bitmap bitmap = null;

		if(view.getWidth() > 0 && view.getHeight() > 0)
		{
			bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.RGB_565);//创建画布
			// 利用bitmap生成画布
			Canvas canvas = new Canvas(bitmap);
			// 把view中的内容绘制在画布上
			view.draw(canvas);
			return bitmap;
		}
		return null;
	}

	
	/**
	 * 
	 * 第二种方法： 
	 * 
	 * 把View绘制到Bitmap上
	 * @param view 需要绘制的View
	 * @param width 该View的宽度
	 * @param height 该View的高度
	 * @return 返回Bitmap对象
	 * 
	 */
	public Bitmap getViewBitmap(View comBitmap, int width, int height) {
		Bitmap bitmap = null;
		if (comBitmap != null) {
			comBitmap.clearFocus();
			comBitmap.setPressed(false);

			boolean willNotCache = comBitmap.willNotCacheDrawing();
			comBitmap.setWillNotCacheDrawing(false);

			// Reset the drawing cache background color to fully transparent
			// for the duration of this operation
			int color = comBitmap.getDrawingCacheBackgroundColor();
			comBitmap.setDrawingCacheBackgroundColor(0);
			float alpha = comBitmap.getAlpha();
			comBitmap.setAlpha(1.0f);

			if (color != 0) {
				comBitmap.destroyDrawingCache();
			}
			
			int widthSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
			int heightSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
			comBitmap.measure(widthSpec, heightSpec);
			comBitmap.layout(0, 0, width, height);

			comBitmap.buildDrawingCache();
			Bitmap cacheBitmap = comBitmap.getDrawingCache();
			if (cacheBitmap == null) {
//				Log.e("view.ProcessImageToBlur", "failed getViewBitmap(" + comBitmap + ")", 
//						new RuntimeException());
				return null;
			}
			bitmap = Bitmap.createBitmap(cacheBitmap);
			// Restore the view
			comBitmap.setAlpha(alpha);
			comBitmap.destroyDrawingCache();
			comBitmap.setWillNotCacheDrawing(willNotCache);
			comBitmap.setDrawingCacheBackgroundColor(color);
		}
		return bitmap;
	}
	
	
    /** 
     * 第一种解决方案 在不改变图片形状的同时，判断，如果h>w，则按h压缩，否则在w>h或w=h的情况下，按宽度压缩 
     * 
     * @param h 
     * @param w 
     * @return 
     */  
    public static int getPercent(float h, float w) {  
        int p = 0;  
        float p2 = 0.0f;  
        if (h > w) {  
            p2 = 297 / h * 100;  
        } else {  
            p2 = 210 / w * 100;  
        }  
        p = Math.round(p2);  
        return p;  
    }  
	
    /** 
     * 第二种解决方案，统一按照宽度压缩 这样来的效果是，所有图片的宽度是相等的，自我认为给客户的效果是最好的 
     * 
     * @param args 
     */  
    public static int getPercent2(float h, float w) {  
        int p = 0;  
        float p2 = 0.0f;  
        p2 = 530 / w * 100;  
        p = Math.round(p2);  
        return p;  
    }  
    
	
	/**
	 * 
	 * @param pathName  文件路径
	 * @param targetWidth   目标  宽度
	 * @param targetHeight  目标  高度
	 * @return
	 */
	public static Bitmap compressBySize(String pathName, int targetWidth, int targetHeight)
	{
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(pathName, opts);
		float imgWidth = opts.outWidth;
		float imgHeight = opts.outHeight;
		int widthRatio = (int) Math.ceil(imgWidth / (float) targetWidth);
		int heightRatio = (int) Math.ceil(imgHeight / (float) targetHeight);
		opts.inSampleSize = 1;
		if (widthRatio > 1 || widthRatio > 1)
		{
			if (widthRatio > heightRatio)
			{
				opts.inSampleSize = widthRatio;
			}
			else
			{
				opts.inSampleSize = heightRatio;
			}
		}

		opts.inJustDecodeBounds = false;
		bitmap = BitmapFactory.decodeFile(pathName, opts);
		return bitmap;
	}
	

}
