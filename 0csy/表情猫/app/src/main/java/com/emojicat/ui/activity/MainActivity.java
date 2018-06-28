package com.emojicat.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.emojicat.R;
import com.emojicat.ui.activity.photo.PhotoPicAndSelectActivity;
import com.emojicat.ui.fragment.TabFourFragment;
import com.emojicat.ui.fragment.TabOneFragment;
import com.emojicat.ui.fragment.TabThreeFragment;
import com.emojicat.ui.fragment.TabTwoFragment;
import com.emojicat.utils.Dbug;
import com.emojicat.utils.ToasUtil;
import com.emojicat.utils.app.toolbar.StatusBarCompat;
import com.umeng.socialize.UMShareAPI;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity {

    @BindView(R.id.imageViewFirst)
    ImageView imageViewFirst;
    @BindView(R.id.textViewFirst)
    TextView textViewFirst;
    @BindView(R.id.imageViewSecond)
    ImageView imageViewSecond;
    @BindView(R.id.textViewSecond)
    TextView textViewSecond;
    @BindView(R.id.imageViewThird)
    ImageView imageViewThird;
    @BindView(R.id.textViewThird)
    TextView textViewThird;
    @BindView(R.id.imageViewFourth)
    ImageView imageViewFourth;
    @BindView(R.id.textViewFourth)
    TextView textViewFourth;

    @BindView(R.id.viewFirst)
    LinearLayout viewFirst;
    @BindView(R.id.viewSecond)
    LinearLayout viewSecond;
    @BindView(R.id.viewThird)
    LinearLayout viewThird;
    @BindView(R.id.viewFourth)
    LinearLayout viewFourth;
    Unbinder unbinder;


    private TabOneFragment mTabOneFragment;
    private TabTwoFragment mTabTwoFragment;
    private TabThreeFragment mTabThreeFragment;
    private TabFourFragment mTabFourFragment;

    private static final int INDEX_TabOne = 0;
    private static final int INDEX_TabTwo = 1;
    private static final int INDEX_TabThree = 2;
    private static final int INDEX_TabFour = 3;
    private static final int[] fragmentTags = new int[]{INDEX_TabOne, INDEX_TabTwo,
            INDEX_TabThree, INDEX_TabFour};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setStatusBar();//状态栏颜色
        if (AVUser.getCurrentUser() == null) {
            AVUser.logInInBackground("csy", "csy313", new LogInCallback<AVUser>() {
                @Override
                public void done(AVUser avUser, final AVException e) {
                    if (e == null) {
                        Dbug.d("MainActivity", "登陆成功==");
                    } else {
                        Dbug.d("MainActivity", "登陆失败==" + e);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToasUtil.showToast("登陆失败" + e);
                                //finish();
                            }
                        });

                    }
                }
            });
        } else {
            Dbug.d("MainActivity", "已登陆==");
        }
        //貌似和第一个布局的颜色相同
        StatusBarCompat.compat(this);
        unbinder = ButterKnife.bind(this);
        initData();
//        addAction();
        showFragment(INDEX_TabOne);


    }

    @Override
    public void initData() {

    }


    private void showFragment(int index) {
        // there only one fragment whene activity created
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        for (int i = 0; i < fragmentTags.length; i++) {
            Fragment fragment = manager.findFragmentByTag(fragmentTags[i] + "");
            if (fragment != null && fragment.isVisible()) {
                transaction.hide(fragment);
            }
        }
        if (index == INDEX_TabOne) {
            if (mTabOneFragment == null) {
                mTabOneFragment = new TabOneFragment();
                transaction.add(R.id.fragment_container, mTabOneFragment, INDEX_TabOne + "");
            }
            transaction.show(mTabOneFragment);
            transaction.commit();
        } else if (index == INDEX_TabTwo) {
            if (mTabTwoFragment == null) {
                mTabTwoFragment = new TabTwoFragment();
                transaction.add(R.id.fragment_container, mTabTwoFragment, INDEX_TabTwo + "");
            }
            transaction.show(mTabTwoFragment);
            transaction.commit();
        } else if (index == INDEX_TabThree) {
            if (mTabThreeFragment == null) {
                mTabThreeFragment = new TabThreeFragment();
                transaction.add(R.id.fragment_container, mTabThreeFragment, INDEX_TabThree + "");
            }
            transaction.show(mTabThreeFragment);
            transaction.commit();
        } else if (index == INDEX_TabFour) {
            if (mTabFourFragment == null) {
                mTabFourFragment = new TabFourFragment();
                transaction.add(R.id.fragment_container, mTabFourFragment, INDEX_TabFour + "");
            }
            transaction.show(mTabFourFragment);
            transaction.commit();
        }
    }


    public void changeTabUi(int index) {
        int colorSel = getResources().getColor(R.color.main_color3);
        int colorNor = getResources().getColor(R.color.tab_nor_color);

        if (index == INDEX_TabOne) {
            imageViewFirst.setBackgroundResource(R.drawable.ic_home_index_filled);
            imageViewSecond.setBackgroundResource(R.drawable.ic_home_fenlei);
            imageViewThird.setBackgroundResource(R.drawable.ic_home_find);
            imageViewFourth.setBackgroundResource(R.drawable.ic_home_our);

            textViewFirst.setTextColor(colorSel);
            textViewSecond.setTextColor(colorNor);
            textViewThird.setTextColor(colorNor);
            textViewFourth.setTextColor(colorNor);

        } else if (index == INDEX_TabTwo) {
            imageViewFirst.setBackgroundResource(R.drawable.ic_home_index);
            imageViewSecond.setBackgroundResource(R.drawable.ic_home_fenlei_filled);
            imageViewThird.setBackgroundResource(R.drawable.ic_home_find);
            imageViewFourth.setBackgroundResource(R.drawable.ic_home_our);

            textViewFirst.setTextColor(colorNor);
            textViewSecond.setTextColor(colorSel);
            textViewThird.setTextColor(colorNor);
            textViewFourth.setTextColor(colorNor);
        } else if (index == INDEX_TabThree) {
            imageViewFirst.setBackgroundResource(R.drawable.ic_home_index);
            imageViewSecond.setBackgroundResource(R.drawable.ic_home_fenlei);
            imageViewThird.setBackgroundResource(R.drawable.ic_home_find_filled);
            imageViewFourth.setBackgroundResource(R.drawable.ic_home_our);


            textViewFirst.setTextColor(colorNor);
            textViewSecond.setTextColor(colorNor);
            textViewThird.setTextColor(colorSel);
            textViewFourth.setTextColor(colorNor);
        } else if (index == INDEX_TabFour) {


            imageViewFirst.setBackgroundResource(R.drawable.ic_home_index);
            imageViewSecond.setBackgroundResource(R.drawable.ic_home_fenlei);
            imageViewThird.setBackgroundResource(R.drawable.ic_home_find);
            imageViewFourth.setBackgroundResource(R.drawable.ic_home_our_filled);


            textViewFirst.setTextColor(colorNor);
            textViewSecond.setTextColor(colorNor);
            textViewThird.setTextColor(colorNor);
            textViewFourth.setTextColor(colorSel);
        }

        showFragment(index);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();

    }


    @OnClick({R.id.viewFirst, R.id.viewSecond, R.id.viewThird, R.id.viewFourth})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.leftView:
                break;
            case R.id.rightView:
                //选择图片上传
                Intent intent = new Intent(MainActivity.this, PhotoPicAndSelectActivity.class);
                startActivity(intent);
                //startIntentAnim();
                break;
            case R.id.viewFirst:
                changeTabUi(INDEX_TabOne);

                break;
            case R.id.viewSecond:
                changeTabUi(INDEX_TabTwo);

                break;
            case R.id.viewThird:
                changeTabUi(INDEX_TabThree);

                break;
            case R.id.viewFourth:
                changeTabUi(INDEX_TabFour);

                break;


        }
    }


    //fragment中分享,需在所依赖的Activity实现onActivityResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }


    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
//                finishIntentAnim();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
