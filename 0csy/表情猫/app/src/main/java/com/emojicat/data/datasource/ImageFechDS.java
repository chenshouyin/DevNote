package com.emojicat.data.datasource;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;

/**
 * Created by fanglin on 2/27/16.
 */
public class ImageFechDS extends AVQueryDataSourceBase {


    public ImageFechDS() {
        super();
        this.reset();
    }

    @Override
    public void reset() {
        super.reset();
        //查询赞
        AVQuery<AVObject> queryImage = AVQuery.getQuery("image");
        queryImage.include("imageFile");
        queryImage.include("imageOwner");
        queryImage.addDescendingOrder("updatedAt");
        this.query = queryImage;
    }
}
