package com.emojicat.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emojicat.R;
import com.emojicat.utils.Dbug;


public class TabTwoFragment extends BaseFragment {


    private View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_one_layout, null);
        Dbug.d("TabTwoFragment", "onCreateView");

        return view;
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

    public void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }





}
