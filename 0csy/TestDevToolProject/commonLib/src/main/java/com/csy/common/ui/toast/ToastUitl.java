package com.csy.common.ui.toast;


import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class ToastUitl
{

	private Context context;
	
	public ToastUitl(Context  context)
	{
		this.context = context;
	}

	/**
	 * Toast 提示框
	 */
	private Toast mToast;

	/**
	 * 弹出Toast提示
	 * 
	 * @param resId
	 *            资源ID
	 */
	public void showToast(int resId) {
		if (null == mToast) {
			mToast = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(resId);
		}
		mToast.show();
	}

	/**
	 * 弹出Toast提示
	 * 
	 * @param message
	 *            提示消息
	 */
	public void showToast(String message) {
		if(!TextUtils.isEmpty(message)){
			if (null == mToast) {
				mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
			} else {
				mToast.setText(message);
			}
			mToast.show();
		}
	}

	/**
	 * 取消弹出Toast提示
	 */
	public void cancelToast() {
		if (null != mToast) {
			mToast.cancel();
		}
	}
}
