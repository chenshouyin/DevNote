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

	private Button bt1, bt2, bt3,bt4, bt5, bt6,bt7;
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
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == bt1) {
			Uri uri = Uri
					.parse("http://blog.csdn.net/e_inch_photo/article/details/74923317");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
		} else if (v == bt2) {
			Uri uri = Uri
					.parse("http://blog.csdn.net/e_inch_photo/article/details/74926529");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
		} else if (v == bt3) {
			Uri uri = Uri
					.parse("http://www.jianshu.com/p/e7a765691067");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
			
			String strFromC = JniClient.AddStr("Java2C_参数1", "Java2C_参数2");
			bt1.setText(strFromC);
		}else if (v == bt4) {
			
			String strFromC = JniClient.AddStr("Java2C_参数1", "Java2C_参数2");
			bt1.setText(strFromC);
		}
	}

}
