package com.emojicat.ui.widget.recyclerview.data;


import com.avos.avoscloud.AVObject;
import com.emojicat.ui.widget.recyclerview.entity.MultipleItem;
import com.emojicat.ui.widget.recyclerview.entity.MySection;

import java.util.ArrayList;
import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class DataServer {

//    private static final String HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK = "https://avatars1.githubusercontent.com/u/7698209?v=3&s=460";
    private static final String CYM_CHAD = "CymChad";

    private DataServer() {
    }

//    public static List<Status> getSampleData(int lenth) {
//        List<Status> list = new ArrayList<>();
//        for (int i = 0; i < lenth; i++) {
//            Status status = new Status();
//            status.setUserName("Chad" + i);
//            status.setCreatedAt("04/05/" + i);
//            status.setRetweet(i % 2 == 0);
//            status.setUserAvatar("https://avatars1.githubusercontent.com/u/7698209?v=3&s=460");
//            status.setText("BaseRecyclerViewAdpaterHelper https://www.recyclerview.org");
//            list.add(status);
//        }
//        return list;
//    }
//
//    public static List<Status> addData(List list, int dataSize) {
//        for (int i = 0; i < dataSize; i++) {
//            Status status = new Status();
//            status.setUserName("Chad" + i);
//            status.setCreatedAt("04/05/" + i);
//            status.setRetweet(i % 2 == 0);
//            status.setUserAvatar("https://avatars1.githubusercontent.com/u/7698209?v=3&s=460");
//            status.setText("Powerful and flexible RecyclerAdapter https://github.com/CymChad/BaseRecyclerViewAdapterHelper");
//            list.add(status);
//        }
//
//        return list;
//    }
//
    public static List<MySection> getSampleData(boolean isRefresh,List<AVObject> avObjects) {
        if (avObjects == null || avObjects.size()==0)
            return null;
        List<MySection> list = new ArrayList<>();
        if (isRefresh){//只有头部才显示
            list.add(new MySection(true, null, false));
        }
        for (int i=0;i<avObjects.size();i++){
            MySection mMySection = new MySection(avObjects.get(i));
            mMySection.setmAVObject(avObjects.get(i));
            list.add(mMySection);
        }
        return list;
    }
//
//    public static List<String> getStrData() {
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            String str = HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK;
//            if (i % 2 == 0) {
//                str = CYM_CHAD;
//            }
//            list.add(str);
//        }
//        return list;
//    }

    public static synchronized List<MultipleItem> getMultipleItemData(List<AVObject> mData,boolean isRefresh) {
        if (mData==null)
            return null;
        List<MultipleItem> list = new ArrayList<>();
        for (int i = 0; i < mData.size(); i++) {
            if (isRefresh){
                //第一个图片显示特殊
                list.add(new MultipleItem(MultipleItem.IMG_ADD, MultipleItem.getImgSpanSize(0),mData.get(i)));
            }else{
                list.add(new MultipleItem(MultipleItem.IMG_NORM, MultipleItem.getImgSpanSize(0),mData.get(i)));
            }
        }
        return list;
    }

//    public static List<MultipleItem> getMultipleChildView() {
//        List<MultipleItem> list = new ArrayList<>();
//        for (int i = 0; i <= 4; i++) {
//            list.add(new ClickEntity(ClickEntity.CLICK_ITEM_VIEW, MultipleItem.TEXT_SPAN_SIZE, CYM_CHAD));
//            list.add(new MultipleItem(MultipleItem.IMG, MultipleItem.BIG_IMG_SPAN_SIZE));
//            list.add(new MultipleItem(MultipleItem.IMG, MultipleItem.IMG_SPAN_SIZE));
//            list.add(new MultipleItem(MultipleItem.IMG, MultipleItem.IMG_SPAN_SIZE));
//            list.add(new MultipleItem(MultipleItem.IMG, MultipleItem.IMG_SPAN_SIZE));
//        }
//
//        return list;
//    }


}
