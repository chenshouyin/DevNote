package com.emojicat.ui.widget.recyclerview.adapter;

import android.content.Context;

import com.emojicat.R;
import com.emojicat.ui.widget.recyclerview.entity.MultipleItem;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 * modify by AllenCoder
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    public MultipleItemQuickAdapter(Context context, List data) {
        super(data);
        //如果需要返回其它布局,可在此返回
        //addItemType(MultipleItem.IMG_ADD, R.layout.item_image_view);
        //addItemType(MultipleItem.IMG_ADD, R.layout.item_image_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.IMG_ADD:
                helper.setText(R.id.tv, item.getContent());
                break;
            case MultipleItem.IMG_NORM:
                switch (helper.getLayoutPosition() %
                        2) {
                    case 0:
                        helper.setImageResource(R.id.iv, R.mipmap.activity_main_news);
                        break;
                    case 1:
                        helper.setImageResource(R.id.iv, R.mipmap.activity_main_search);
                        break;

                }
                break;
        }
    }

}
