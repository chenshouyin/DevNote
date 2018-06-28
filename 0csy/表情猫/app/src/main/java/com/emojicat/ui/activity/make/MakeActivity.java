package com.emojicat.ui.activity.make;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.bumptech.glide.Glide;
import com.emojicat.R;
import com.emojicat.ui.activity.BaseActivity;
import com.emojicat.ui.widget.indicator.DachshundTabLayout;
import com.emojicat.ui.widget.indicator.LineMoveIndicator;
import com.emojicat.ui.widget.recyclerview.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenshouyin on 17/4/27.
 */

public class MakeActivity extends BaseActivity {

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.leftView)
    RelativeLayout leftView;
    @BindView(R.id.rightView)
    RelativeLayout rightView;
    @BindView(R.id.layoutTitle)
    RelativeLayout layoutTitle;
    @BindView(R.id.ivEmoji)
    ImageView ivEmoji;
    @BindView(R.id.tab_layout)
    DachshundTabLayout tabLayout;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    @BindView(R.id.tvSend)
    TextView tvSend;
    @BindView(R.id.mainLayout)
    LinearLayout mainLayout;


    private FragmentBottomOne mFragmentBottomOne;
    private FragmentBottomTwo mFragmentBottomTwo;
    private FragmentBottomThree mFragmentBottomThree;
    private FragmentBottomFourth mFragmentBottomFourth;

    //页面列表
    private List<Fragment> fragmentList;
    //标题列表
    List<String> titleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make);
        ButterKnife.bind(this);
        setStatusBar();
        try {
            AVObject avObject = AVObject.parseAVObject(getIntent().getStringExtra("avObject"));
            AVFile avImage = avObject.getAVFile("imageFile");
            Glide.with(Utils.getContext())
                    .load(avImage.getUrl())
                    //.placeholder(R.mipmap.iv_defualt)
                    .error(R.mipmap.ic_launcher)
                    .crossFade()
                    .into(ivEmoji);

        } catch (Exception e) {
            e.printStackTrace();
        }


        initFragment();
        //不显示
        mViewPager.setAdapter(new MyViewPagerAdapter(this.getSupportFragmentManager()));
        //正确的方法
        //mViewPager.setAdapter(new MyViewPagerAdapter(this.getChildFragmentManager()));

        //设置样式
        LineMoveIndicator mLineMoveIndicator = new LineMoveIndicator(tabLayout);
        tabLayout.setSelectedTabIndicatorColor(Color.BLACK);//设置指示器颜色
        tabLayout.setTabTextColors(Color.BLACK, Color.BLACK);//设置文字颜色,分别是非选中的和选中的
        tabLayout.setAnimatedIndicator(mLineMoveIndicator);
        mLineMoveIndicator.setEdgeRadius(0);

        //绑定
        tabLayout.setupWithViewPager(mViewPager);


    }

    private void initFragment() {

        fragmentList = new ArrayList<Fragment>();
        mFragmentBottomOne = new FragmentBottomOne();
        mFragmentBottomTwo = new FragmentBottomTwo();
        mFragmentBottomThree = new FragmentBottomThree();
        mFragmentBottomFourth = new FragmentBottomFourth();

        fragmentList.add(mFragmentBottomOne);
        fragmentList.add(mFragmentBottomTwo);
        fragmentList.add(mFragmentBottomThree);
        fragmentList.add(mFragmentBottomFourth);
        fragmentList.add(mFragmentBottomFourth);


        titleList = new ArrayList<>();
        titleList.add("模板");
        titleList.add("配文");
        titleList.add("文字色");
        titleList.add("背景色");
        titleList.add("贴图");


    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.ivBack, R.id.leftView, R.id.layoutTitle, R.id.ivEmoji})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                break;
            case R.id.leftView:
                finish();
                finishIntentAnim();
                break;

            case R.id.layoutTitle:
                break;
            case R.id.ivEmoji:
                break;
        }
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
