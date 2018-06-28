package com.emojicat.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.emojicat.R;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenshouyin on 17/4/3.
 */

public class SplishActivity extends BaseActivity {


    @BindView(R.id.gifSplishLogo)
    ImageView gifSplishLogo;

    private Handler mHandler = new MyHandler(this);

    private static class MyHandler extends Handler {
        private final WeakReference<Activity> mActivity;

        public MyHandler(Activity activity) {
            mActivity = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            System.out.println(msg);
            if (mActivity.get() == null) {
                return;
            }
            Intent intent = new Intent(mActivity.get(), MainActivity.class);
            mActivity.get().startActivity(intent);
            mActivity.get().overridePendingTransition(R.anim.anim_activity_right_in,
                    R.anim.anim_activity_left_out);
            mActivity.get().finish();

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splish);
        setStatusBar();
        ButterKnife.bind(this);

        //表情文字在线制作 http://www.qt86.com/346
        gifSplishLogo.setImageResource(R.drawable.activity_splish_animlist);
        AnimationDrawable animationDrawable = (AnimationDrawable) gifSplishLogo.getDrawable();
        animationDrawable.start();

        mHandler.sendMessageDelayed(Message.obtain(), 2000);
    }

    @Override
    public void initData() {

    }


    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
