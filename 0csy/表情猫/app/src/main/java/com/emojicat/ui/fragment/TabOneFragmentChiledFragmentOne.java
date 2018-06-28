package com.emojicat.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.FindCallback;
import com.emojicat.R;
import com.emojicat.data.datasource.ImageFechDS;
import com.emojicat.rx.EnventActivityMain;
import com.emojicat.rx.RxBus;
import com.emojicat.ui.activity.make.MakeActivity;
import com.emojicat.ui.widget.banner.Banner;
import com.emojicat.ui.widget.banner.BannerConfig;
import com.emojicat.ui.widget.dialog.MainShareDialog;
import com.emojicat.ui.widget.recyclerview.adapter.BaseQuickAdapter;
import com.emojicat.ui.widget.recyclerview.adapter.PullToRefreshAdapter;
import com.emojicat.ui.widget.recyclerview.listener.OnItemClickListener;
import com.emojicat.utils.Dbug;
import com.emojicat.utils.ToasUtil;
import com.emojicat.utils.bitmap.ViewToImageUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.functions.Action1;

/**
 * Created by chenshouyin on 17/4/2.
 */

public class TabOneFragmentChiledFragmentOne extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    Unbinder unbinder;
    @BindView(R.id.rlLoadingView)
    RelativeLayout rlLoadingView;

    private PullToRefreshAdapter pullToRefreshAdapter;
    private int mCurrentCounter = 0;
    private boolean isErr = false;
    private ImageFechDS imageFechDS;
    private boolean mHasLoadedOnce = false;
    private boolean isPrepared = false;


    //轮播图
    private Banner viewBaner;
    private Object[] images = new Object[]{R.mipmap.view_bananer_a, R.mipmap.view_bananer_b,
            R.mipmap.view_bananer_c, R.mipmap.view_bananer_d, R.mipmap.view_bananer_e};
    private String[] titles = new String[]{"----标题--11", "----标题--22", "----标题--33", "----标题--44", "----标题--55"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layuot_table_one_child_fragment_one, null);
        unbinder = ButterKnife.bind(this, view);
        initData();
        isPrepared = true;
        lazyLoad();
        addRxActionListner();
        return view;
    }


    @Override
    public void initData() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                callRefreshData();
            }
        });//mSwipeRefreshLayout设置刷新
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        GridLayoutManager mgr = new GridLayoutManager(getActivity(), 4);
        mRecyclerView.setLayoutManager(mgr);

        initAdapter();//初始化,同时给pullToRefreshAdapter设置下拉加载监听


        viewBaner = new Banner(getActivity());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400);
        viewBaner.setLayoutParams(layoutParams);


    }

    private void callRefreshData() {
        //刷新数据
        imageFechDS.reset();//一定要reset
        pullToRefreshAdapter.setEnableLoadMore(false);
        imageFechDS.fetchInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(final List<AVObject> list, final AVException e) {
                if (e != null) {
                    Dbug.d("imageFechDS.fetchInBackground", "==图片获取失败==" + e);
                    isErr = true;
                    if (mSwipeRefreshLayout != null) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        pullToRefreshAdapter.setEnableLoadMore(true);
                    }
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToasUtil.showToast("刷新失败,请重试" + e);
                            }
                        });
                    }
                } else {
                    Dbug.d("imageFechDS.fetchInBackground", "==图片获取成功==" + list.size());

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //更新UI
                            if (mSwipeRefreshLayout == null) {//Fragmet中,而且是延时任务可能为null
                                pullToRefreshAdapter.setEnableLoadMore(true);
                                return;
                            }
                            pullToRefreshAdapter.setNewData(list);
                            //pullToRefreshAdapter.addData(list);
//                                    pullToRefreshAdapter.addData(list);
//                                    pullToRefreshAdapter.addData(list);
//                                    pullToRefreshAdapter.addData(list);
//                                    pullToRefreshAdapter.addData(list);
//                                    pullToRefreshAdapter.addData(list);
//                                    pullToRefreshAdapter.addData(list);

                            initBanner();//设置banner样式显示位置
                            pullToRefreshAdapter.removeAllHeaderView();//设置前先清空head
                            pullToRefreshAdapter.addHeaderView(viewBaner, 0);//设置显示在头部
                            isErr = false;
                            mCurrentCounter = pullToRefreshAdapter.getData().size();
                            mSwipeRefreshLayout.setRefreshing(false);
                            pullToRefreshAdapter.setEnableLoadMore(true);
                        }
                    });
                }

                rlLoadingView.setVisibility(View.GONE);
            }
        });
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
            return;//has loaded or view has not init


        mHasLoadedOnce = true;
        prepareData();


    }

    private void prepareData() {
        //数据查询
        rlLoadingView.setVisibility(View.VISIBLE);
        imageFechDS = new ImageFechDS();
        imageFechDS.reset();
        callRefreshData();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Dbug.d("", "TabOneFragmentChiledFragmentOne==onDestroyView");
        unbinder.unbind();
        mHasLoadedOnce = false;
        isPrepared = false;

    }


    private void initAdapter() {
        pullToRefreshAdapter = new PullToRefreshAdapter();
        pullToRefreshAdapter.setOnLoadMoreListener(this, mRecyclerView);


        //pullToRefreshAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        //设置加载动画类型
        pullToRefreshAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        pullToRefreshAdapter.setAutoLoadMoreSize(10);// 多少的时候会触发加载更多回调
        mRecyclerView.setAdapter(pullToRefreshAdapter);
        mCurrentCounter = 0;

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                //Toast.makeText(getActivity(), Integer.toString(position), Toast.LENGTH_LONG).show();
                //列表点击监听
            }
        });
    }




    @Override
    public void onLoadMoreRequested() {
        if (mSwipeRefreshLayout == null) {//Fragmet中,而且是延时任务可能为null
            pullToRefreshAdapter.loadMoreEnd(true);//如果当前已有的数据少于PAGE_SIZE说明肯定没有更多数据
            return;
        }
        if (pullToRefreshAdapter.getData().size() < imageFechDS.getPageSize()) {
            pullToRefreshAdapter.loadMoreEnd(true);
            //如果当前已有的数据少于PAGE_SIZE说明肯定没有更多数据
            //gone no more view
        } else {
            if (isErr) {
                Toast.makeText(getActivity(), R.string.network_err, Toast.LENGTH_LONG).show();
                pullToRefreshAdapter.loadMoreFail();
            } else {
                if (imageFechDS.haveMore()) {
                    imageFechDS.fetchInBackground(new FindCallback<AVObject>() {
                        @Override
                        public void done(final List<AVObject> list, AVException e) {
                            if (e != null) {
                                Dbug.d("imageFechDS.fetchInBackground", "==图片获取失败==" + e);
                                pullToRefreshAdapter.loadMoreComplete();
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ToasUtil.showToast("图片获取失败");
                                    }
                                });
                            } else {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //更新UI
                                        pullToRefreshAdapter.addData(list);
                                        mCurrentCounter = pullToRefreshAdapter.getData().size();
                                        pullToRefreshAdapter.loadMoreComplete();
                                    }
                                });
                            }
                        }
                    });
                } else {
                    //no more data
                    pullToRefreshAdapter.loadMoreEnd(false);//visible no more data
                }

            }
            mSwipeRefreshLayout.setEnabled(true);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Dbug.d("", "TabOneFragmentChiledFragmentOne==onDestroy");
    }


    private void initBanner() {
        /************ Banner **************/
        // 样式一:设置指示器居中（CIRCLE_INDICATOR或者CIRCLE_INDICATOR_TITLE模式下）
        setPosition(5);// 设置指示器样式
        // 更多自定义设置 持续更新 https://github.com/youth5201314/banner
        // 一步搞定，设置图片就行了
        viewBaner.setImages(images);
        viewBaner.setOnBannerClickListener(new Banner.OnBannerClickListener() {// 设置点击事件
            @Override
            public void OnBannerClick(View view, int position) {
                Toast.makeText(getActivity().getApplicationContext(), "你点击了：" + position, Toast.LENGTH_LONG).show();
            }
        });
        /************ Banner **************/
    }

    private void setPosition(int type) {
        switch (type) {
            case 0:
                break;
            case 1:
                // 设置样式 显示圆形指示器
                viewBaner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                break;
            case 2:
                // 显示数字指示器
                viewBaner.setBannerStyle(BannerConfig.NUM_INDICATOR);
                break;
            case 3:
                // 显示数字指示器和标题
                viewBaner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
                // 记得设置标题列表哦
                viewBaner.setBannerTitle(titles);
                break;
            case 4:
                // 显示圆形指示器和标题
                viewBaner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
                // 记得设置标题列表哦
                viewBaner.setBannerTitle(titles);
                break;
            case 5:
                // 设置指示器居中（CIRCLE_INDICATOR或者CIRCLE_INDICATOR_TITLE模式下）
                viewBaner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                viewBaner.setIndicatorGravity(BannerConfig.RIGHT);
                viewBaner.setBannerStyle(BannerConfig.INDICATOR_SIZE);
                break;
        }
    }

    private View tempShareView = null;
    private void addRxActionListner() {
        RxBus.getDefault().toObservable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object event) {
                if (event instanceof EnventActivityMain) {
                    //do something
                    if (((EnventActivityMain) event).getKey().equals("viewRootToTabOneFragmentChiledFragmentOne")) {

                        //url,text,postion
                        final String[] args = (String[]) ((EnventActivityMain) event).getMessage();
                        final AVObject avObject = pullToRefreshAdapter.getItem(Integer.parseInt(args[2]));
                        //列表被点击了
                        //ToasUtil.showToast(""+args[2]);
                        MainShareDialog shareDialog = new MainShareDialog(getActivity(),args, new MainShareDialog.DialogShareClick() {
                            @Override
                            public void doClickImage() {
                                ToasUtil.showToast("doClickImage");
                            }

                            @Override
                            public void doClickWeChat() {
                                //UMImage image = new UMImage(getActivity(),args[0]);//网络图片
                                Bitmap bitmap1 = ViewToImageUtil.getViewBitmap(tempShareView,getActivity(),450,450);
                                Dbug.d(getClass().getSimpleName(),"===图片大小=="+bitmap1.getWidth()+"x"+bitmap1.getHeight());
                                UMImage image = new UMImage(getActivity(), bitmap1);//网络图片
                                UMImage thumb =  new UMImage(getActivity(),bitmap1);//缩略图
                                image.setThumb(thumb);
                                //ToasUtil.showToast("doClickWeChat");
                                new ShareAction(getActivity()).setPlatform(SHARE_MEDIA.WEIXIN)
                                        .withMedia(image)
                                        .setCallback(umShareListener)
                                        .share();


                            }

                            @Override
                            public void doClickQQ() {
                               // ToasUtil.showToast("doClickQQ");
                                //UMImage image = new UMImage(getActivity(),args[0]);//网络图片
                                Bitmap bitmap1 = ViewToImageUtil.getViewBitmap(tempShareView,getActivity(),450,450);
                                UMImage image = new UMImage(getActivity(), bitmap1);//网络图片
                                UMImage thumb =  new UMImage(getActivity(),bitmap1);//缩略图
                                image.setThumb(thumb);
                                // ToasUtil.showToast("doClickWeChat");
                                new ShareAction(getActivity()).setPlatform(SHARE_MEDIA.QQ)
                                        .withMedia(image)
                                        .setCallback(umShareListener)
                                        .share();
                            }

                            @Override
                            public void doClickCollection() {
                                //oasUtil.showToast("doClickCollection");
                                Intent intent = new Intent(getActivity(), MakeActivity.class);
                                intent.putExtra("avObject",avObject.toString());
                                startActivity(intent);
                                getActivity().overridePendingTransition(R.anim.anim_activity_right_in,
                                        R.anim.anim_activity_left_out);
                            }

                            @Override
                            public void doClickMore() {
                                ToasUtil.showToast("doClickMore");
                            }

                            @Override
                            public void setShareView(View view) {
                                tempShareView = view;
                            }
                        });
                        shareDialog.show();
                    }
                }
            }
        });
    }


    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }
        @Override
        public void onResult(SHARE_MEDIA platform) {
            //Log.d("plat","platform"+platform);
            Dbug.d(getClass().getSimpleName(),"platform"+platform);
            Toast.makeText(getActivity(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(),platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if(t!=null){
                //Log.d("throw","throw:"+t.getMessage());
                Dbug.d(getClass().getSimpleName(),"throw"+t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(),platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

}
