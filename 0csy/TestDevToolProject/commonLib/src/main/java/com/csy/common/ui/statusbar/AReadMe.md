
Useage

官方使用参考
https://github.com/naturs/StatusBarHelper

onCerat中使用
```
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
```

其它使用参考
初始化可放在BaseActivity
```
package me.naturs.statusbarhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import me.naturs.library.statusbar.StatusBarHelper;

/**
 * Created by naturs on 2016/2/21.
 */
public abstract class BaseActivity extends SwipeBackActivity {

    protected Toolbar mToolbar;

    protected StatusBarHelper mStatusBarHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutResource());

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        onTintStatusBar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onTintStatusBar() {
        if (mStatusBarHelper == null) {
            mStatusBarHelper = new StatusBarHelper(this, StatusBarHelper.LEVEL_19_TRANSLUCENT,
                    StatusBarHelper.LEVEL_21_VIEW);
        }
        mStatusBarHelper.setColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    protected abstract int getLayoutResource();

}


```


1.状态栏变色

```
package me.naturs.statusbarhelper;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.Random;

/**
 * Created by naturs on 2016/2/21.
 */
public class StatusBarColorActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor();
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_status_bar_color;
    }

    void changeColor() {
        Random random = new Random();
        int R = random.nextInt(256);
        int G = random.nextInt(256);
        int B = random.nextInt(256);
        int color = Color.argb(255, R, G, B);
        mToolbar.setBackgroundColor(color);
        mStatusBarHelper.setColor(color);
    }
}


```



2.白色状态栏
```
package me.naturs.statusbarhelper;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import me.naturs.library.statusbar.StatusBarHelper;

/**
 *
 * Created by naturs on 2016/2/21.
 */
public class WhiteStatusBarActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToolbar.setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_white;
    }

    @Override
    protected void onTintStatusBar() {
        if (mStatusBarHelper == null) {
            mStatusBarHelper = new StatusBarHelper(this, StatusBarHelper.LEVEL_19_TRANSLUCENT,
                    StatusBarHelper.LEVEL_21_VIEW);
        }
        mStatusBarHelper.setActivityRootLayoutFitSystemWindows(false);
        mStatusBarHelper.setDrawable(getResources().getDrawable(R.drawable.drawable_status_bar_bg));
    }
    
}

```

3.全屏
```
/*
 * Copyright 2014 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.naturs.statusbarhelper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * A layout that draws something in the insets passed to {@link #fitSystemWindows(Rect)}, i.e. the area above UI chrome
 * (status and navigation bars, overlay action bars).
 */
public class ScrimInsetsFrameLayout extends FrameLayout {
    private Drawable mInsetForeground;
    private boolean mConsumeInsets;

    private Rect mInsets;
    private Rect mTempRect = new Rect();
    private OnInsetsCallback mOnInsetsCallback;

    public ScrimInsetsFrameLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        final TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ScrimInsetsView, defStyle, 0);
        if (a == null) {
            return;
        }
        mInsetForeground = a.getDrawable(R.styleable.ScrimInsetsView_appInsetForeground);
        mConsumeInsets = a.getBoolean(R.styleable.ScrimInsetsView_appConsumeInsets, true);
        a.recycle();

        setWillNotDraw(true);
    }

    @Override
    protected boolean fitSystemWindows(Rect insets) {
        mInsets = new Rect(insets);
        setWillNotDraw(mInsetForeground == null);
        ViewCompat.postInvalidateOnAnimation(this);
        if (mOnInsetsCallback != null) {
            mOnInsetsCallback.onInsetsChanged(insets);
        }
        return mConsumeInsets || super.fitSystemWindows(insets);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        int width = getWidth();
        int height = getHeight();
        if (mInsets != null && mInsetForeground != null) {
            int sc = canvas.save();
            canvas.translate(getScrollX(), getScrollY());

            // Top
            mTempRect.set(0, 0, width, mInsets.top);
            mInsetForeground.setBounds(mTempRect);
            mInsetForeground.draw(canvas);

            // Bottom
            mTempRect.set(0, height - mInsets.bottom, width, height);
            mInsetForeground.setBounds(mTempRect);
            mInsetForeground.draw(canvas);

            // Left
            mTempRect.set(0, mInsets.top, mInsets.left, height - mInsets.bottom);
            mInsetForeground.setBounds(mTempRect);
            mInsetForeground.draw(canvas);

            // Right
            mTempRect.set(width - mInsets.right, mInsets.top, width, height - mInsets.bottom);
            mInsetForeground.setBounds(mTempRect);
            mInsetForeground.draw(canvas);

            canvas.restoreToCount(sc);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mInsetForeground != null) {
            mInsetForeground.setCallback(this);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mInsetForeground != null) {
            mInsetForeground.setCallback(null);
        }
    }

    /**
     * Allows the calling container to specify a callback for custom processing when insets change (i.e. when
     * {@link #fitSystemWindows(Rect)} is called. This is useful for setting padding on UI elements based on
     * UI chrome insets (e.g. a Google Map or a ListView). When using with ListView or GridView, remember to set
     * clipToPadding to false.
     */
    public void setOnInsetsCallback(OnInsetsCallback onInsetsCallback) {
        mOnInsetsCallback = onInsetsCallback;
    }

    public interface OnInsetsCallback {
        void onInsetsChanged(Rect insets);
    }
}
```

4.输入EdieTtext适配

```
package me.naturs.statusbarhelper;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import me.naturs.library.statusbar.StatusBarHelper;

/**
 *
 * Created by naturs on 2016/2/21.
 */
public class ListWithHeaderActivity extends BaseActivity {

    ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListView = (ListView) findViewById(android.R.id.list);

        int screenWidth = getResources().getDisplayMetrics().widthPixels;

        View header = LayoutInflater.from(this).inflate(R.layout.layout_list_header, null);
        ImageView headerIv = (ImageView) header.findViewById(R.id.image);
        ViewGroup.LayoutParams lp = headerIv.getLayoutParams();
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = (int) (screenWidth * 9F / 16F);
        mListView.addHeaderView(header);

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 100; i ++) {
            data.add(String.valueOf(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        mListView.setAdapter(adapter);

        mToolbar.setBackgroundColor(Color.TRANSPARENT);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_full_screen;
    }

    @Override
    protected void onTintStatusBar() {
        if (mStatusBarHelper == null) {
            mStatusBarHelper = new StatusBarHelper(this, StatusBarHelper.LEVEL_19_TRANSLUCENT,
                    StatusBarHelper.LEVEL_21_NORMAL_FULL);
        }
        mStatusBarHelper.setActivityRootLayoutFitSystemWindows(false);
        mStatusBarHelper.setColor(Color.TRANSPARENT);
    }

}

```