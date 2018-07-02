package com.csy.net.net.download;

import okhttp3.ResponseBody;

/**
 * Created by csy on 2018/3/21.
 */

public interface DownloadListener {
    void onProgress(int progress);

    void onSuccess(ResponseBody responseBody);

    void onFail(String message);

    void onComplete();
}
