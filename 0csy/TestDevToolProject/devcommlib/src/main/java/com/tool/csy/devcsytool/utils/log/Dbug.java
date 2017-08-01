package com.tool.csy.devcsytool.utils.log;

import android.util.Log;

public class Dbug {
	private static boolean IS_DEBUG = true;
	private final static String HeadStr = "BJ====LOG>>>";
	public static void openOrCloseDebug(boolean isOpen) {
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
