package com.example.testndkeclipse;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Button bt1, bt2, bt3,bt4, bt5, bt6,bt7,bt8,bt9,bt10;
	static {
		// 加载so库
		System.loadLibrary("TestNdk");// lib和.so为前缀后缀,不用加上
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViews();
		// String strFromC = JniClient.AddStr("Java2C_参数1", "Java2C_参数2");

	}

	private void findViews() {
		// TODO Auto-generated method stub
		bt1 = (Button) this.findViewById(R.id.bt1);
		bt1.setOnClickListener(this);
		bt2 = (Button) this.findViewById(R.id.bt2);
		bt2.setOnClickListener(this);
		bt3 = (Button) this.findViewById(R.id.bt3);
		bt3.setOnClickListener(this);
		bt4 = (Button) this.findViewById(R.id.bt4);
		bt4.setOnClickListener(this);
		bt5 = (Button) this.findViewById(R.id.bt5);
		bt5.setOnClickListener(this);
		bt6 = (Button) this.findViewById(R.id.bt6);
		bt6.setOnClickListener(this);
		bt7 = (Button) this.findViewById(R.id.bt7);
		bt7.setOnClickListener(this);
		bt8 = (Button) this.findViewById(R.id.bt8);
		bt8.setOnClickListener(this);
		bt9 = (Button) this.findViewById(R.id.bt9);
		bt9.setOnClickListener(this);
		bt10 = (Button) this.findViewById(R.id.bt10);
		bt10.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == bt1) {
			//环境搭建
			Uri uri = Uri
					.parse("http://blog.csdn.net/e_inch_photo/article/details/74923317");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
		} else if (v == bt2) {
			//添加Log打印到logcat
			Uri uri = Uri
					.parse("http://blog.csdn.net/e_inch_photo/article/details/74926529");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
		} else if (v == bt3) {
			//Android NDK开发相关知识集合
			Uri uri = Uri
					.parse("http://www.jianshu.com/p/e7a765691067");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
			
			String strFromC = JniClient.AddStr("Java2C_参数1", "Java2C_参数2");
			bt3.setText(strFromC);
		}else if (v == bt4) {
			short s = 1;
			int i = 10;
			long l= 100;
			float f=  1000.00f;
			boolean z = true;
			int[] array = null;
			MyJavaClass mMyJavaClass = null;
			Object obj = null;
			String str = null;
			double d = 10000.000;
			byte b = 1;
			char c = 60;
			//JNI 数据类型与 Java 数据类型的映射关系 http://www.jianshu.com/p/04786ef923f4
			
			JniClient.TestDataTypeJ2C(s, i, l, f, d, c, z, b, str, array, obj, mMyJavaClass);
		}else if (v == bt5) {
			//C中处理Java中传递的字符串-字符串相加
			//http://www.jianshu.com/p/de2a9141b1e6
			String strFromC = JniClient.AddStr("Java2C_参数1", "Java2C_参数2");
			bt5.setText(bt5.getText()+strFromC);
		}else if (v == bt6) {
			//http://www.jianshu.com/p/f2d3f71a1c99
			int[] javaArray = new int[]{10,20,30,40,50,60};
			int[] javaArrayResult = JniClient.sumArray(javaArray );
			if (javaArrayResult!=null) {
				Toast.makeText(MainActivity.this, "native中返回数组"+ javaArrayResult[0],Toast.LENGTH_SHORT).show();
			}
		}else if (v == bt7) {
			//http://www.jianshu.com/p/f2d3f71a1c99			
		}else if (v == bt8) {
			
		}else if (v == bt9) {
			
		}else if (v == bt10) {
			//http://wiki.jikexueyuan.com/project/jni-ndk-developer-guide/array.html
			
		}
	}

}
