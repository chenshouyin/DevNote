package com.tool.csy.devcsytool.utils.screen;

import android.content.Context;
import android.util.DisplayMetrics;

public class ScreenUtils {
	public static int getScreenWidth(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int w_screen = dm.widthPixels;
		return w_screen;
	}

	public static int getScreenHeight(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int h_screen = dm.heightPixels;
		return h_screen;
	}
	


	public static int getScreenHeightWithoutStatiusBar(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int h_screen = dm.heightPixels;
		return h_screen-getStatusBarHeight(context);
	}

	public static int getStatusBarHeight(Context context){
		 /** 
		 * ��ȡ״̬���߶ȡ�������1 
		 * */  
		int statusBarHeight1 = -1;  
		//��ȡstatus_bar_height��Դ��ID  
		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");  
		if (resourceId > 0) {  
		    //������ԴID��ȡ��Ӧ�ĳߴ�ֵ  
		    statusBarHeight1 = context.getResources().getDimensionPixelSize(resourceId);  
		}
		return statusBarHeight1;  
	}
}
