package com.emojicat.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.emojicat.R;
import com.tool.csy.devcsytool.statusbar.StatusBarHelper;

/**
 * Created by chenshouyin on 17/4/1.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setStatusBar();
    }

    public abstract void initData();


    public abstract void onClick(View view);


    public void setStatusBar() {
        StatusBarHelper helper = new StatusBarHelper(
        /*Activity*/
                this,
                /**
                 * level used in 4.4, below value can be set:
                 * LEVEL_NONE (if you don't need tint StatusBar)
                 * LEVEL_19_TRANSLUCENT (set TRANSLUCENT StatusBar and tint)
                 */
                StatusBarHelper.LEVEL_19_TRANSLUCENT,
                /**
                 * level used in 5.x, can be set:
                 * LEVEL_NONE (if you don't need tint StatusBar)
                 * LEVEL_21_NORMAL (use API in 5.x to tint StatusBar)
                 * LEVEL_21_NORMAL_FULL (use API in 5.x to tint StatusBar and set full screen)
                 * LEVEL_21_VIEW (use a View to tint StatusBar that like LEVEL_19_TRANSLUCENT)
                 */
                StatusBarHelper.LEVEL_21_NORMAL
        );
        // set background color
        helper.setColor(getResources().getColor(R.color.main_color1));
        // or set background drawable
        // helper.setDrawable(new ColorDrawable(Color.BLUE));
    }

    public void startIntentAnim() {
        overridePendingTransition(R.anim.anim_activity_right_in,
                R.anim.anim_activity_left_out);
    }

    public void finishIntentAnim() {
        overridePendingTransition(R.anim.anim_activity_left_in,
                R.anim.anim_activity_right_out);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            finishIntentAnim();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}