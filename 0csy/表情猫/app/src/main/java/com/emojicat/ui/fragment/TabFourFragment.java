package com.emojicat.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emojicat.R;
import com.emojicat.utils.Dbug;


public class TabFourFragment extends BaseFragment {

    public TabFourFragment(){

    }

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, null);

        initData();
        addAction();
        Dbug.d("TabFourFragment","onCreateView");

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
    public void onResume() {
        super.onResume();
    }


}


