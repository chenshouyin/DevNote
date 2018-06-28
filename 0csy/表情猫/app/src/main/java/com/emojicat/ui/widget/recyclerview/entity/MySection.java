package com.emojicat.ui.widget.recyclerview.entity;


import com.avos.avoscloud.AVObject;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MySection extends SectionEntity<AVObject> {
    public AVObject getmAVObject() {
        return mAVObject;
    }



    private boolean isMore;

    public void setmAVObject(AVObject mAVObject) {
        this.mAVObject = mAVObject;
    }

    private AVObject mAVObject;
    public MySection(boolean isHeader, AVObject mAVObject, boolean isMroe) {
        super(isHeader, mAVObject);
        this.isMore = isMroe;
    }

    public MySection(AVObject t) {
        super(t);
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean mroe) {
        isMore = mroe;
    }
}
