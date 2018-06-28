package com.emojicat.ui.activity.make;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.FindCallback;
import com.bumptech.glide.Glide;
import com.emojicat.R;
import com.emojicat.data.datasource.ImageFechDS;
import com.emojicat.ui.fragment.BaseFragment;
import com.emojicat.ui.widget.recyclerview.adapter.BaseQuickAdapter;
import com.emojicat.ui.widget.recyclerview.adapter.SectionAdapter;
import com.emojicat.ui.widget.recyclerview.data.DataServer;
import com.emojicat.ui.widget.recyclerview.entity.MySection;
import com.emojicat.ui.widget.recyclerview.listener.OnItemClickListener;
import com.emojicat.ui.widget.recyclerview.utils.Utils;
import com.emojicat.utils.Dbug;
import com.emojicat.utils.ToasUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by chenshouyin on 17/4/2.
 */

public class FragmentBottomOne extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    Unbinder unbinder;
    @BindView(R.id.ivLoading)
    ImageView ivLoading;
    @BindView(R.id.rlLoadingView)
    RelativeLayout rlLoadingView;
    private ImageFechDS imageFechDS;
    private boolean mHasLoadedOnce = false;
    private boolean isPrepared = false;
    private SectionAdapter pullToRefreshAdapter;
    private int mCurrentCounter = 0;
    private boolean isErr = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nake_fragment_one_layuot, null);

        unbinder = ButterKnife.bind(this, view);

        initData();
        isPrepared = true;
        lazyLoad();
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

        //和其它item一样排列
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //GridLayoutManager mgr = new GridLayoutManager(getActivity(), 4);
        //mRecyclerView.setLayoutManager(mgr);

        //单独一行
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));

        initAdapter();//初始化,同时给pullToRefreshAdapter设置下拉加载监听

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

                            pullToRefreshAdapter.setNewData(DataServer.getSampleData(true,list));
                            //pullToRefreshAdapter.addData(DataServer.getMultipleItemData(list));
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


    private void initAdapter() {
        //pullToRefreshAdapter = new MultipleItemQuickAdapter(getActivity(), null);
        //pullToRefreshAdapter = new PullToRefreshMakeFragmentOneAdapter();
        pullToRefreshAdapter = new SectionAdapter(R.layout.item_section_content, R.layout.item_section_head, DataServer.getSampleData(true,null));
        //正常列表点击
        pullToRefreshAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MySection mySection = pullToRefreshAdapter.getItem(position);
                if (mySection.isHeader){
                    //Toast.makeText(getActivity(), "onItemClick==isHeader=="+position, Toast.LENGTH_LONG).show();

                }else{
                    //Toast.makeText(getActivity(),"onItemClick==isHeader==not=="+position, Toast.LENGTH_LONG).show();
                    AVFile avImage = mySection.getmAVObject().getAVFile("imageFile");
                    Glide.with(Utils.getContext())
                            .load(avImage.getUrl())
                            //.placeholder(R.mipmap.iv_defualt)
                            .error(R.mipmap.ic_launcher)
                            .crossFade()
                            .into((ImageView) getActivity().findViewById(R.id.ivEmoji));
                }
            }
        });

        //head点击
        pullToRefreshAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId()==R.id.viewPhoto){
                    Toast.makeText(getActivity(), "onItemChildClick" + position+"=viewPhoto=", Toast.LENGTH_LONG).show();

                }else if (view.getId()==R.id.viewGif){
                    Toast.makeText(getActivity(), "onItemChildClick" + position+"=viewGif=", Toast.LENGTH_LONG).show();

                }
                return false;
            }
        });

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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        isPrepared = false;

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

}
