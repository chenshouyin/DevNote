package com.emojicat.ui.widget.recyclerview.adapter;


import android.widget.ImageView;

import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.bumptech.glide.Glide;
import com.emojicat.R;
import com.emojicat.ui.widget.recyclerview.entity.MySection;
import com.emojicat.ui.widget.recyclerview.utils.Utils;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class SectionAdapter extends BaseSectionQuickAdapter<MySection, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param sectionHeadResId The section head layout id for each item
     * @param layoutResId      The layout resource id of each item.
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public SectionAdapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, final MySection item) {
//        helper.setText(R.id.header, "====");
//        helper.setVisible(R.id.more, item.isMore());
//        helper.addOnClickListener(R.id.more);

        helper.addOnClickListener(R.id.viewGif);
        helper.addOnClickListener(R.id.viewPhoto);


    }


    @Override
    protected void convert(BaseViewHolder helper, MySection item) {

        AVObject avObject = item.getmAVObject();
        if (avObject!=null){
            //helper.getLayoutPosition()
            AVFile avFile = avObject.getAVFile("imageFile");
            String url = avFile.getUrl();
            ImageView ivImage = helper.getView(R.id.iv);
            Glide.with(Utils.getContext())
                    .load(url)
                    //.placeholder(R.mipmap.iv_defualt)
                    .error(R.mipmap.ic_launcher)
                    .crossFade()
                    .into(ivImage);
            //helper.addOnClickListener(R.id.tv);//点击事件
        }

    }
}
