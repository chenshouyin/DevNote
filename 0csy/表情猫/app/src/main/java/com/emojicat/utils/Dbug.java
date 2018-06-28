package com.emojicat.utils;

import android.util.Log;

/**
 * Log打印类,可控制开关
 */
public class Dbug {
	private static boolean IS_DEBUG = true;
	private final static String HeadStr = "BQM====LOG>>>>>";
	public static void setDebug(boolean isOpen) {
		IS_DEBUG = isOpen;
	}

	public static void v(String tag, String msg) {
		if(IS_DEBUG){
			Log.v(tag, HeadStr+msg);
		}
	}
	public static void d(String tag, String msg) {
		if(IS_DEBUG){
			Log.d(tag, HeadStr+msg);
		}
	}
	public static void i(String tag, String msg) {
		if(IS_DEBUG){
			Log.i(tag, HeadStr+msg);
		}
	}
	public static void w(String tag, String msg) {
		if(IS_DEBUG){
			Log.w(tag, HeadStr+msg);
		}
	}
	public static void e(String tag, String msg) {
		if(IS_DEBUG){
			Log.e(tag, HeadStr+msg);
		}
	}
}
