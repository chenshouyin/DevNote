package com.emojicat.ui.widget.recyclerview.entity;


import com.avos.avoscloud.AVObject;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MultipleItem implements MultiItemEntity {
    public static final int IMG_ADD = 1;
    public static final int IMG_NORM = 2;
    public static final int TEXT_SPAN_SIZE = 3;
    public static final int IMG_SPAN_SIZE = 1;
    public static final int IMG_TEXT_SPAN_SIZE = 4;
    public static final int IMG_TEXT_SPAN_SIZE_MIN = 2;
    private int itemType;
    private int spanSize;
    private AVObject mAVObject;
    public MultipleItem(int itemType, int spanSize, AVObject mAVObject) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.mAVObject = mAVObject;
    }

    public MultipleItem(int itemType, int spanSize) {
        this.itemType = itemType;
        this.spanSize = spanSize;
    }

    public static int getImgSpanSize(int size){

        return size;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
