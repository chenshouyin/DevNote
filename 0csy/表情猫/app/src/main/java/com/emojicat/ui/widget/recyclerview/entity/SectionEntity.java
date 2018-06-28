package com.emojicat.ui.widget.recyclerview.entity;

import com.avos.avoscloud.AVObject;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public abstract class SectionEntity<T> {
    public boolean isHeader;
    public T t;
    public AVObject mAVObject;

    public SectionEntity(boolean isHeader, AVObject mAVObject) {
        this.isHeader = isHeader;
        this.mAVObject = mAVObject;
        this.t = null;
    }

    public SectionEntity(T t) {
        this.isHeader = false;
        this.mAVObject = null;
        this.t = t;
    }
}
