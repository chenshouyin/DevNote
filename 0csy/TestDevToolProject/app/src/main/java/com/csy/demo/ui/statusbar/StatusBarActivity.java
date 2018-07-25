package com.csy.demo.devtool.ui.statusbar;

import android.graphics.Color;
import android.os.Bundle;

import com.csy.common.base.BaseActivity;
import com.csy.common.ui.statusbar.StatusBarHelper;
import com.csy.demo.R;

public class StatusBarActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_bar);

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
        helper.setColor(Color.RED);
        // or set background drawable
        // helper.setDrawable(new ColorDrawable(Color.BLUE));
    }


}
