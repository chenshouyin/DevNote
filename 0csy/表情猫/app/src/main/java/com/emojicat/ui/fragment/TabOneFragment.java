package com.emojicat.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.emojicat.R;
import com.emojicat.ui.widget.indicator.DachshundTabLayout;
import com.emojicat.ui.widget.indicator.LineMoveIndicator;
import com.emojicat.utils.Dbug;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 第一次启动的时候只会初始化这一个fragment其它不会初始化
 */
public class TabOneFragment extends BaseFragment {

    @BindView(R.id.tab_layout)
    DachshundTabLayout tabLayout;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    Unbinder unbinder;
    @BindView(R.id.leftView)
    RelativeLayout leftView;
    @BindView(R.id.rightView)
    RelativeLayout rightView;


    private View view;

    private TabOneFragmentChiledFragmentOne mTabOneFragmentChiledFragmentOne;
    private TabOneFragmentChiledFragmentTwo mTabOneFragmentChiledFragmentTwo;
    private TabOneFragmentChiledFragmentThree mTabOneFragmentChiledFragmentThree;
    //页面列表
    private List<Fragment> fragmentList;
    //标题列表
    List<String> titleList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_one_layout, null);
        Dbug.d("TabTwoFragment", "onCreateView");

        unbinder = ButterKnife.bind(this, view);
        addAction();

        fragmentList = new ArrayList<Fragment>();
        mTabOneFragmentChiledFragmentOne = new TabOneFragmentChiledFragmentOne();
        mTabOneFragmentChiledFragmentTwo = new TabOneFragmentChiledFragmentTwo();
        mTabOneFragmentChiledFragmentThree = new TabOneFragmentChiledFragmentThree();
        fragmentList.add(mTabOneFragmentChiledFragmentOne);
        fragmentList.add(mTabOneFragmentChiledFragmentTwo);
        fragmentList.add(mTabOneFragmentChiledFragmentThree);


        titleList = new ArrayList<>();
        titleList.add("最热");
        titleList.add("最新");
        titleList.add("模板");

        //不显示
        //mViewPager.setAdapter(new MyViewPagerAdapter(getActivity().getSupportFragmentManager()));
        //正确的方法
        mViewPager.setAdapter(new MyViewPagerAdapter(this.getChildFragmentManager()));


        DachshundTabLayout tabLayout = (DachshundTabLayout) view.findViewById(R.id.tab_layout);
        //设置样式
        LineMoveIndicator mLineMoveIndicator = new LineMoveIndicator(tabLayout);
        tabLayout.setSelectedTabIndicatorColor(Color.BLACK);//设置指示器颜色
        tabLayout.setTabTextColors(Color.BLACK, Color.BLACK);//设置文字颜色,分别是非选中的和选中的
        tabLayout.setAnimatedIndicator(mLineMoveIndicator);
        mLineMoveIndicator.setEdgeRadius(0);

        //绑定
        tabLayout.setupWithViewPager(mViewPager);


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
        unbinder.unbind();
    }


    public class MyViewPagerAdapter extends FragmentPagerAdapter {

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
        }

        @Override
        public Fragment getItem(int arg0) {
            return fragmentList.get(arg0);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // TODO Auto-generated method stub
            return titleList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //如果注释这行，那么不管怎么切换，page都不会被销毁
            //super.destroyItem(container, position, object);
        }
    }


}
