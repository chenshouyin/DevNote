package com.example.csy.testdevtoolproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tool.csy.devcsytool.statusbar.StatusBarHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        helper.setColor(getResources().getColor(R.color.colorPrimaryDark));
        // or set background drawable
        // helper.setDrawable(new ColorDrawable(Color.BLUE));
    }
}
