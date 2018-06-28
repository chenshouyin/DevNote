package com.emojicat.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emojicat.R;
import com.emojicat.utils.Dbug;


/**
 * Created by chenshouyin on 17/4/2.
 */

public class TabOneFragmentChiledFragmentThree extends BaseFragment {

    private boolean mHasLoadedOnce = false;
    private boolean isPrepared = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layuot_table_one_child_fragment_three, null);


        Dbug.d("","TabOneFragmentChiledFragmentThree==onCreateView");

        initData();
        isPrepared = true;
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
        if (mHasLoadedOnce || !isPrepared)
            return;
        mHasLoadedOnce = true;

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Dbug.d("","TabOneFragmentChiledFragmentThree==onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Dbug.d("","TabOneFragmentChiledFragmentThree==onDestroyView");
        mHasLoadedOnce = false;
        isPrepared = false;
    }
}
