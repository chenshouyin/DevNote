package com.emojicat.data.datasource;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fanglin on 2/27/16.
 */
public class AVQueryDataSourceBase implements AVQueryDataSource {

    public AVQuery<AVObject> query;

    protected ArrayList<AVObject> datas = new ArrayList<AVObject>();
    protected List<AVObject> preloadDatas = null;
    protected boolean haveMore = true;
    protected boolean loading = false;
    protected boolean initialed = false;
    protected boolean useAscendingOrder;
    protected Date timestamp = new Date();
    protected Integer pageSize = 20;

    @Override
    public AVQuery getQuery() {
        return this.query;
    }

    @Override
    public ArrayList<AVObject> getDatas() {
        return datas;
    }

    @Override
    public boolean haveMore() {
        return haveMore;
    }

    @Override
    public boolean isLoading() {
        return this.loading;
    }

    @Override
    public boolean isInitialed() {
        return this.initialed;
    }

    @Override
    public void removeObject(AVObject object) {
        datas.remove(object);
    }

    @Override
    public void addObject(AVObject object) {
        datas.add(object);
    }

    @Override
    public void insertObjectAtFirst(AVObject object) {
        datas.add(0,object);
    }

    private void preloadNextPageInBackground() {

        new Thread(new Runnable() {
            public void run() {
                if (useAscendingOrder) {
                    query.whereGreaterThan("createdAt",timestamp);
                } else {
                    query.whereLessThan("createdAt",timestamp);
                }
                query.limit(pageSize);

                List<AVObject> results = null;
                try {
                    results = query.find();
                } catch (AVException e) {
                    e.printStackTrace();
                }
                if (results != null && results.size() > 0) {
                    preloadDatas = results;
                } else {
                    preloadDatas = null;
                }
            }
        }).start();

    }


    @Override
    public List<AVObject> fetch() {
        this.initialed = true;
        this.loading = true;

        if(preloadDatas!=null && preloadDatas.size() >0) {
            List<AVObject> results = preloadDatas;
            preloadDatas = null;
            if (results.size() > 0) {
                AVObject lastObject = results.get(results.size()-1);
                timestamp = lastObject.getCreatedAt();
            }
            haveMore = results.size()>=pageSize;
            results = filterDatas(results);
            this.datas.addAll(results);
            loading = false;
            if(haveMore) {
                preloadNextPageInBackground();;
            }
            return results;
        } else {
            if (useAscendingOrder) {
                query.whereGreaterThan("createdAt",timestamp);
            } else {
                query.whereLessThan("createdAt",timestamp);
            }
            query.limit(pageSize);

            List<AVObject> results = null;
            try {
                results = query.find();
            } catch (AVException e) {
                e.printStackTrace();
            }
            if (results == null) {
                results = new ArrayList<AVObject>();
            }

            if (results.size() > 0) {
                AVObject lastObject = results.get(results.size()-1);
                timestamp = lastObject.getCreatedAt();
            }
            haveMore = results.size()>=pageSize;
            results = filterDatas(results);
            this.datas.addAll(results);
            loading = false;
            if(haveMore) {
                preloadNextPageInBackground();
            }
            return results;
        }
    }

    protected List<AVObject> filterDatas(final List<AVObject> datas) {

        return datas;
    }

    @Override
    public void fetchInBackground(final FindCallback<AVObject> callback) {
        this.initialed = true;
        this.loading = true;
        if (useAscendingOrder) {
            query.whereGreaterThan("createdAt",timestamp);
        } else {
            query.whereLessThan("createdAt",timestamp);
        }
        query.limit(pageSize);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (list == null) {
                    list = new ArrayList<AVObject>();
                }

                if (list.size() > 0) {
                    AVObject lastObject = list.get(list.size() - 1);
                    timestamp = lastObject.getCreatedAt();
                }
                haveMore = list.size() >= pageSize;
                list = filterDatas(list);
                datas.addAll(list);
                loading = false;
                callback.done(list, e);
            }
        });
    }

    @Override
    public void reset() {
        if (useAscendingOrder) {
            timestamp = new Date(0);
        } else {
            timestamp = new Date();
        }
        preloadDatas = new ArrayList<AVObject>();
        datas = new ArrayList<AVObject>();
        haveMore = true;
        loading = false;
        cancel();
        query = null;
    }

    @Override
    public void cancel() {
        if (query!=null) {
            query.cancel();
        }
    }

    @Override
    public void setUseAscendingOrder(boolean useAscendingOrder) {
        this.useAscendingOrder = useAscendingOrder;
    }

    @Override
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public Integer getPageSize() {
        return this.pageSize;
    }
}
