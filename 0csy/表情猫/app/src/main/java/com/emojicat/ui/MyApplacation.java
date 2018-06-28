package com.emojicat.ui;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVOSCloud;
import com.emojicat.ui.widget.recyclerview.utils.Utils;
import com.emojicat.utils.Dbug;
import com.emojicat.utils.app.AppUtils;
import com.squareup.leakcanary.LeakCanary;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by chenshouyin on 17/4/1.
 */

public class MyApplacation extends Application{

    private boolean isDebug = false;
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);//分包
        isDebug = AppUtils.isApkDebugable(this);
        if (isDebug){
            //调试状态才启用内存溢出检测以及Log打印
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return;
            }
            LeakCanary.install(this);
            // Normal app init code...

            Dbug.setDebug(true);
            Dbug.d("MyApplacation","==isApkDebugable==true");

        }
        Utils.init(this);
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"sJi0wJ9fzoev6pI3WxXr9kg5-gzGzoHsz","BWe25TvWdQ0LsAYnAnfyzueL");
        AVAnalytics.enableCrashReport(this, true);//统计分析代码


        //友盟
        UMShareAPI.get(this);

        if (isDebug){
            //放在 SDK 初始化语句 AVOSCloud.initialize() 后面，只需要调用一次即可,SDK 会把网络请求、错误消息
            //等信息输出到 IDE 的日志窗口，或是浏览器 Console 或是 LeanCloud 控制台的 云引擎日志 中
            //开启 SDK 的调试日志（debug log）来方便追踪问题，在应用发布之前，请关闭调试日志，以免暴露敏感数据。
            AVOSCloud.setDebugLogEnabled(true);

        }


    }

    {
        Config.DEBUG = true;//友盟调试日志

        //各个平台的配置，建议放在全局Application或者程序入口
        PlatformConfig.setWeixin("wxd735f3424fcfddd5", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("1106011035", "sD0OIDeb8DRW8JDt");
    }
}
