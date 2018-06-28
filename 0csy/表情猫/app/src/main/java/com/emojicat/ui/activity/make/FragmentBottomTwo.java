package com.emojicat.ui.activity.make;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.emojicat.ui.widget.dialog.MakeShareDialog;
import com.emojicat.ui.widget.recyclerview.adapter.BaseQuickAdapter;
import com.emojicat.ui.widget.recyclerview.adapter.SectionMakeTextAdapter;
import com.emojicat.ui.widget.recyclerview.data.DataServer;
import com.emojicat.ui.widget.recyclerview.entity.MySection;
import com.emojicat.ui.widget.recyclerview.listener.OnItemClickListener;
import com.emojicat.ui.widget.recyclerview.utils.Utils;
import com.emojicat.utils.Dbug;
import com.emojicat.utils.ToasUtil;
import com.emojicat.utils.photos.editimage.utils.BitmapUtils;
import com.emojicat.utils.photos.imageedit.operate.OperateUtils;
import com.emojicat.utils.photos.imageedit.operate.OperateView;
import com.emojicat.utils.photos.imageedit.operate.TextObject;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by chenshouyin on 17/4/2.
 */

public class FragmentBottomTwo extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener, TextWatcher {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    Unbinder unbinder;
    @BindView(R.id.ivLoading)
    ImageView ivLoading;
    @BindView(R.id.rlLoadingView)
    RelativeLayout rlLoadingView;
    @BindView(R.id.mInputText)
    EditText mInputText;
    private ImageFechDS imageFechDS;
    private boolean mHasLoadedOnce = false;
    private boolean isPrepared = false;
    private SectionMakeTextAdapter pullToRefreshAdapter;
    private int mCurrentCounter = 0;
    private boolean isErr = false;
    //贴图
    private EditText inputEditText;
    private InputMethodManager imm;
    private LinearLayout content_layout;
    private OperateView operateView;
    private ImageView ivEmoji;
    private OperateUtils operateUtils;
    private TextObject textObj;
    private View rightView;
    private View rootView;
    final Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (content_layout.getWidth() != 0) {
                    Dbug.e("LinearLayoutW======", content_layout.getWidth() + "");
                    Dbug.e("LinearLayoutH=====", content_layout.getHeight() + "");
                    // 取消定时器
                    timer.cancel();
                    fillContent();

                } else {
                    Dbug.e("LinearLayout=====", "null");
                    //ToasUtil.showToast("===NULL==");

                }
            }
        }
    };

    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        public void run() {
            Message message = new Message();
            message.what = 1;
            myHandler.sendMessage(message);
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nake_fragment_two_layuot, null);

        unbinder = ButterKnife.bind(this, view);
        mInputText.addTextChangedListener(this);
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        ivEmoji = (ImageView) getActivity().findViewById(R.id.ivEmoji);
        rightView = getActivity().findViewById(R.id.rightView);
        rootView = getActivity().findViewById(R.id.rootView);
        rightView.setOnClickListener(this);
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
        if (v==rightView){
            btnSave();
        }

    }

    @Override
    protected void lazyLoad() {
        if (mHasLoadedOnce || !isPrepared)
            return;//has loaded or view has not init
        operateUtils = new OperateUtils(getActivity());
        content_layout = (LinearLayout) getActivity().findViewById(R.id.mainLayout);
        // 延迟每次延迟10 毫秒 隔1秒执行一次
        timer.schedule(task, 10, 1000);
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

                            pullToRefreshAdapter.setNewData(DataServer.getSampleData(true, list));
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
        pullToRefreshAdapter = new SectionMakeTextAdapter(R.layout.item_section_make_text_content, R.layout.item_section_make_text_head, DataServer.getSampleData(true, null));
        //正常列表点击
        pullToRefreshAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MySection mySection = pullToRefreshAdapter.getItem(position);
                if (mySection.isHeader) {
                    //Toast.makeText(getActivity(), "onItemClick==isHeader=="+position, Toast.LENGTH_LONG).show();

                } else {
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
                if (view.getId() == R.id.viewPhoto) {
                    Toast.makeText(getActivity(), "onItemChildClick" + position + "=viewPhoto=", Toast.LENGTH_LONG).show();

                } else if (view.getId() == R.id.viewGif) {
                    Toast.makeText(getActivity(), "onItemChildClick" + position + "=viewGif=", Toast.LENGTH_LONG).show();

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

        //inputEditText = (EditText) pullToRefreshAdapter.getViewByPosition(0,R.id.etEditText);

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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        //mTextStickerView change
        String text = s.toString().trim();

        operateView.moveAllItem();//先全部移除再添加


        //5中心,150离x边界  100离y边界
        TextObject textObj = operateUtils.getTextObject(text,
                operateView, 5, 150, operateView.getHeight());
        if (textObj != null) {
//            if (menuWindow != null)
//            {
//                textObj.setColor(menuWindow.getColor());//字体颜色
//            }
//            textObj.setTypeface(typeface);//字体
            textObj.commit();
            operateView.addItem(textObj);
            operateView.setOnListener(new OperateView.MyListener() {
                public void onClick(TextObject tObject) {
                    //alert(tObject);
                    ToasUtil.showToast("编辑");
                }
            });
        }

//        if (text.length()==4){
//            operateView.moveAllItem();
//        }
    }


    public void hideInput() {
        if (getActivity() != null && getActivity().getCurrentFocus() != null && isInputMethodShow()) {
            imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public boolean isInputMethodShow() {
        return imm.isActive();
    }


    private void fillContent() {
        //Bitmap resizeBmp = BitmapFactory.decodeFile(camera_path);
        //Bitmap resizeBmp = BitmapUtils.getBitmapFromView(ivEmoji);
        Bitmap resizeBmp = BitmapUtils.getViewBitmap(ivEmoji);
        operateView = new OperateView(getActivity(), resizeBmp);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                resizeBmp.getWidth(), resizeBmp.getHeight());
        operateView.setLayoutParams(layoutParams);
        content_layout.addView(operateView);
        //operateView.setMultiAdd(true); //设置此参数，可以添加多个文字
        operateView.setMultiAdd(false); //设置此参数，可以添加多个文字
    }


    private void btnSave() {
        operateView.save();
        Bitmap bmp = getBitmapByView(rootView);
        shareBitmap(bmp);
    }


    // 将模板View的图片转化为Bitmap
    public Bitmap getBitmapByView(View v) {
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }


    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            //Log.d("plat","platform"+platform);
            Dbug.d(getClass().getSimpleName(), "platform" + platform);
            Toast.makeText(getActivity(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(), platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                //Log.d("throw","throw:"+t.getMessage());
                Dbug.d(getClass().getSimpleName(), "throw" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(), platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };


    private void shareBitmap(final Bitmap bitmap1) {
        MakeShareDialog shareDialog = new MakeShareDialog(getActivity(), bitmap1, new MakeShareDialog.DialogShareClick() {
            @Override
            public void doClickImage() {
                ToasUtil.showToast("doClickImage");
            }

            @Override
            public void doClickWeChat() {
                //UMImage image = new UMImage(getActivity(),args[0]);//网络图片
//                Bitmap bitmap1 = bitmap;
                Dbug.d(getClass().getSimpleName(), "===图片大小==" + bitmap1.getWidth() + "x" + bitmap1.getHeight());
                UMImage image = new UMImage(getActivity(), bitmap1);//网络图片
                UMImage thumb = new UMImage(getActivity(), bitmap1);//缩略图
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
//                Bitmap bitmap1 = ViewToImageUtil.getViewBitmap(tempShareView, getActivity(), 450, 450);
                UMImage image = new UMImage(getActivity(), bitmap1);//网络图片
                UMImage thumb = new UMImage(getActivity(), bitmap1);//缩略图
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
//                Intent intent = new Intent(getActivity(), MakeActivity.class);
//                intent.putExtra("avObject", avObject.toString());
//                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.anim_activity_right_in,
//                        R.anim.anim_activity_left_out);
            }

            @Override
            public void doClickMore() {
                ToasUtil.showToast("doClickMore");
            }

            @Override
            public void setShareView(View view) {
//                tempShareView = view;
            }
        });
        shareDialog.show();
    }

}
