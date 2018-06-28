package com.emojicat.ui.widget.recyclerview.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.bumptech.glide.Glide;
import com.emojicat.R;
import com.emojicat.rx.EnventActivityMain;
import com.emojicat.rx.RxBus;
import com.emojicat.ui.widget.recyclerview.utils.Utils;
import com.emojicat.utils.CommenUtils;


/**
 * 文 件 名: PullToRefreshAdapter
 * 创 建 人: Allen
 * 创建日期: 16/12/24 19:55
 * 邮   箱: AllenCoder@126.com
 * 修改时间：
 * 修改备注：
 */
public class PullToRefreshAdapter extends BaseQuickAdapter<AVObject, BaseViewHolder> {
    public PullToRefreshAdapter() {
        super( R.layout.item_recyclerview_home, null);// 初始化数据
    }

    @Override
    protected void convert(final BaseViewHolder helper, AVObject item) {
        ImageView ivImage = helper.getView(R.id.ivHomeImage1);
        AVFile avFile = item.getAVFile("imageFile");
        String text = CommenUtils.getText(item);
        String url = avFile.getUrl();
        String postion = ""+(helper.getAdapterPosition()-1);
        Glide.with(Utils.getContext())
                .load(url)
                //.placeholder(R.mipmap.iv_defualt)
                .error(R.mipmap.ic_launcher)
                .crossFade()
                .into(ivImage);

        ( (TextView)helper.getView(R.id.tvText)).setText(text);

        View viewRoot = helper.getView(R.id.viewRoot);
        final String[] args = new String[]{url,text,postion};
        //helper.addOnClickListener(R.id.ivHomeImage1);
        viewRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.getDefault().send(new EnventActivityMain("viewRootToTabOneFragmentChiledFragmentOne",args));
            }
        });
    }



}
