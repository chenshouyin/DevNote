package com.emojicat.ui.fragment;

import android.support.v4.app.Fragment;
import android.view.View;


public abstract class BaseFragment extends Fragment implements View.OnClickListener {


    /** Fragment当前状态是否可见 */
    protected boolean isVisible;

    public void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public abstract void initData();
    public abstract void addAction();

    @Override
    public abstract void onClick(View v);

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }


    /**
     * 可见
     */
    protected void onVisible() {
        lazyLoad();
    }

    /**
     * 不可见
     */
    protected void onInvisible() {
    }

    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    protected abstract void lazyLoad();
}
