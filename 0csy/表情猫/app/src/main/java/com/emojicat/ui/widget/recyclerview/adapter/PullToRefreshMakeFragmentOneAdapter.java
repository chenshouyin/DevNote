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

import java.util.List;
import java.util.Random;


/**
 * 文 件 名: PullToRefreshAdapter
 * 创 建 人: Allen
 * 创建日期: 16/12/24 19:55
 * 邮   箱: AllenCoder@126.com
 * 修改时间：
 * 修改备注：
 */
public class PullToRefreshMakeFragmentOneAdapter extends BaseQuickAdapter<AVObject, BaseViewHolder> {
    public PullToRefreshMakeFragmentOneAdapter() {
        super( R.layout.item_recyclerview_make_buttom_one, null);// 初始化数据
    }

    @Override
    protected void convert(final BaseViewHolder helper, AVObject item) {
        ImageView ivImage = helper.getView(R.id.ivImage1);
        AVFile avFile = item.getAVFile("imageFile");
        String text = getText(item);
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
        viewRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.getDefault().send(new EnventActivityMain("viewRootToTabOneFragmentChiledFragmentOne",args));
            }
        });
    }


    private String getText(AVObject item){
        List<String> texts = item.getList("imageTexs");
        String text = "表情喵";
        if (texts!=null && texts.size()>0){
            Random random = new Random();
            text = texts.get(random.nextInt(texts.size()-1));//生成随机数
        }
        return text;
    }


}
