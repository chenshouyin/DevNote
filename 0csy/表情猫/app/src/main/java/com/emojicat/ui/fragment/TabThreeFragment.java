package com.emojicat.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emojicat.R;
import com.emojicat.utils.Dbug;


/**
 *
 * Created by CSY on 15/11/3.
 */
public class TabThreeFragment extends BaseFragment {

    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_one_tab_layuot2, null);
        Dbug.d("TabThreeFragment","onCreateView");

        initData();
        addAction();

        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
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

}
