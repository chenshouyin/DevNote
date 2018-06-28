package com.emojicat.ui.activity.make;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emojicat.R;
import com.emojicat.ui.fragment.BaseFragment;
import com.emojicat.ui.widget.recyclerview.adapter.BaseQuickAdapter;

/**
 * Created by chenshouyin on 17/4/2.
 */

public class FragmentBottomFourth extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nake_fragment_fourth_layuot, null);

        return view;
    }


    @Override
    public void initData() {

    }

    @Override
    public void addAction() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void lazyLoad() {

    }


    @Override
    public void onLoadMoreRequested() {

    }
}
