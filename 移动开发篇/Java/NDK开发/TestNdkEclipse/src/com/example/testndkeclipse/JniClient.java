package com.example.testndkeclipse;

import android.R.integer;

public class JniClient {
	static public native String AddStr(String strA, String strB);

	static public native void TestDataTypeJ2C(short s, int i, long l, float f,
			double d, char c, boolean z, byte b, String str,int[] array, Object obj,
			MyJavaClass mMyJavaClass);
	
	public static native int[] sumArray(int[] javaArray);
}