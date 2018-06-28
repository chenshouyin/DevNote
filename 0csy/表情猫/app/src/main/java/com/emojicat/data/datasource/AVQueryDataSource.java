package com.emojicat.data.datasource;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.List;

/**
 * Created by fanglin on 2/27/16.
 */
public interface AVQueryDataSource {

    // 获取query对像
    public AVQuery getQuery();
    // 获取所有数据
    public List<AVObject> getDatas();
    // 是否有下一页数据
    public boolean haveMore();
    // 是否正在载入数据
    public boolean isLoading();
    // 是否已经获取过数据
    public boolean isInitialed();

    // 从数据中删除一个对像
    public void removeObject(final AVObject object);
    // 添加一个数据到列表中
    public void addObject(final AVObject object);
    // 插入一个数据到头部
    public void insertObjectAtFirst(final AVObject object);

    //同步获取数据
    public List<AVObject> fetch();
    // 异步获取数据
    public void fetchInBackground(final FindCallback<AVObject> callback);

    // 重置数据源，在下拉刷新的时候会用到，要在这里重建query对像
    public void reset();
    // 取消请求
    public void cancel();
    // 设置使用升序排序，默认使用降序排序
    public void setUseAscendingOrder(final boolean useAscendingOrder);

    //设置数据一页的大小，默认为20
    public void setPageSize(final Integer pageSize);
    public Integer getPageSize();






}
