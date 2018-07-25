package com.csy.demo.netlib.net.retrofit.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.csy.demo.R;
import com.csy.demo.netlib.net.retrofit.demo.UserRequestParams;
import com.csy.demo.netlib.net.retrofit.demo.UserResponse;
import com.csy.demo.netlib.net.retrofit.module.reponse.LoginResponse;
import com.csy.demo.netlib.net.retrofit.module.reponse.MeiZi;
import com.csy.demo.netlib.net.retrofit.module.request.LoginRequest;
import com.csy.demo.netlib.net.retrofit.net.RetrofitHelper;
import com.csy.net.net.common.BasicResponse;
import com.csy.net.net.common.Constants;
import com.csy.net.net.common.DefaultObserver;
import com.csy.net.net.common.ProgressUtils;
import com.csy.net.net.download.DownloadListener;
import com.csy.net.net.download.DownloadUtils;
import com.csy.net.utils.FileUtils;
import com.csy.net.utils.LogUtils;
import com.csy.net.utils.ToastUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;


public class MainNetActivity extends BaseActivity {
//    @BindView(R.id.myGet)
//    Button myGet;
//    @BindView(R.id.myPost)
//    Button myPost;


    private Button btn;
    ProgressBar progressBar;
    TextView mTvPercent;
    private DownloadUtils downloadUtils;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_net;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mTvPercent = (TextView) findViewById(R.id.tv_percent);
        btn = (Button) findViewById(R.id.btn_download);
        downloadUtils = new DownloadUtils();

        Button myGet = (Button) findViewById(R.id.myGet);
        Button myPost = (Button) findViewById(R.id.myPost);

        myGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //http://www.it399.com/userInfo/getUserInfoBean
//                LoginRequest loginRequest = new LoginRequest(this);
//                loginRequest.setUserId("123456");
//                loginRequest.setPassword("123123");
                UserRequestParams userRequestParams = new UserRequestParams();
                userRequestParams.v = "11";
                RetrofitHelper.getApiService()
                        //指定参数
                        //.getUserInfoByGet("http://www.it399.com/userInfo/getUserInfoBean",new UserRequestParams())
                        .getUserInfoByGet("http://www.it399.com/userInfo/getUserInfoBean",1)
                        .subscribeOn(Schedulers.io())
                        .compose(MainNetActivity.this.<UserResponse>bindToLifecycle())
                        //.compose(ProgressUtils.<LoginResponse>applyProgressBar(this))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new DefaultObserver<UserResponse>() {
                            @Override
                            public void onSuccess(UserResponse response) {
                                ToastUtils.show("获取信息成功"+response);
                            }

                            @Override
                            public void onFail(String message) {
                                super.onFail(message);
                                ToastUtils.show("获取信息失败"+message);

                            }
                        });

            }
        });

        myPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //http://www.it399.com/userInfo/getUserInfoBean
//                LoginRequest loginRequest = new LoginRequest(this);
//                loginRequest.setUserId("123456");
//                loginRequest.setPassword("123123");
                RetrofitHelper.getApiService2()
                        //指定参数
                        //.getUserInfoByGet("http://www.it399.com/userInfo/getUserInfoBean",new UserRequestParams())
                        .getUserInfoByPost()
                        .subscribeOn(Schedulers.io())
                        .compose(MainNetActivity.this.<UserResponse>bindToLifecycle())
                        .compose(ProgressUtils.<UserResponse>applyProgressBar(MainNetActivity.this))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new DefaultObserver<UserResponse>() {
                            @Override
                            public void onSuccess(UserResponse response) {
                                ToastUtils.show("获取信息成功2"+response);
                            }

                            @Override
                            public void onFail(String message) {
                                super.onFail(message);
                                ToastUtils.show("获取信息失败2"+message);
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                ToastUtils.show("获取信息onError3"+e);

                            }
                        });
            }
        });
    }

    /**
     * Post请求
     */
    public void login(View view) {
        LoginRequest loginRequest = new LoginRequest(this);
        loginRequest.setUserId("123456");
        loginRequest.setPassword("123123");
        RetrofitHelper.getApiService()
                .login(loginRequest)
                .subscribeOn(Schedulers.io())
                .compose(this.<LoginResponse>bindToLifecycle())
                .compose(ProgressUtils.<LoginResponse>applyProgressBar(this))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<LoginResponse>() {
                    @Override
                    public void onSuccess(LoginResponse response) {
                        ToastUtils.show("登录成功");
                        LogUtils.d(response.getAccessToken());
                    }
                });
    }

    /**
     * Get请求
     * @param view
     */
    public void getData(View view) {
        RetrofitHelper.getApiService()
                .getMezi()
                .compose(this.<List<MeiZi>>bindToLifecycle())
                .compose(ProgressUtils.<List<MeiZi>>applyProgressBar(this))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<List<MeiZi>>() {
                    @Override
                    public void onSuccess(List<MeiZi> response) {
                        showToast("请求成功，妹子个数为" + response.size());
                    }
                });
    }

    /**
     * 单文件上传 方法一
     */
    public void uploadFile1(View view) {
        //文件路径
        File file = getFile();
        RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("phone", "12345678901")
                .addFormDataPart("password", "123123")
                .addFormDataPart("uploadFile", file.getName(), fileBody);
        List<MultipartBody.Part> parts = builder.build().parts();
        RetrofitHelper.getApiService()
                .uploadFiles(parts)
                .subscribeOn(Schedulers.io())
                .compose(this.<BasicResponse>bindToLifecycle())
                .compose(ProgressUtils.<BasicResponse>applyProgressBar(this, "上传文件..."))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BasicResponse>() {
                    @Override
                    public void onSuccess(BasicResponse response) {
                        ToastUtils.show("文件上传成功");
                    }
                });
    }

    /**
     * 单文件上传 方法二
     */
    public void uploadFile2(View view) {
        File file = getFile();
        //  图片参数
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part fileBody = MultipartBody.Part.createFormData("uploadFile", file.getName(), requestFile);
        //  手机号参数
        RequestBody phoneBody = RequestBody.create(MediaType.parse("multipart/form-data"), "12345678909");
        //  密码参数
        RequestBody pswBody = RequestBody.create(MediaType.parse("multipart/form-data"), "123123");

        RetrofitHelper.getApiService()
                .uploadFiles(phoneBody,pswBody,fileBody)
                .subscribeOn(Schedulers.io())
                .compose(this.<BasicResponse>bindToLifecycle())
                .compose(ProgressUtils.<BasicResponse>applyProgressBar(this, "上传文件..."))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BasicResponse>() {
                    @Override
                    public void onSuccess(BasicResponse response) {
                        ToastUtils.show("文件上传成功");
                    }
                });
    }

    /**
     * 下载文件
     * @param view
     */
    public void download(View view) {
        btn.setClickable(false);
        downloadUtils.download(Constants.DOWNLOAD_URL, new DownloadListener() {
            @Override
            public void onProgress(int progress) {
                LogUtils.e("--------下载进度：" + progress);
                Log.e("onProgress", "是否在主线程中运行:" + String.valueOf(Looper.getMainLooper() == Looper.myLooper()));
                progressBar.setProgress(progress);
                mTvPercent.setText(String.valueOf(progress) + "%");
            }

            @Override
            public void onSuccess(ResponseBody responseBody) {  //  运行在子线程
                saveFile(responseBody);
                Log.e("onSuccess", "是否在主线程中运行:" + String.valueOf(Looper.getMainLooper() == Looper.myLooper()));
            }

            @Override
            public void onFail(String message) {
                btn.setClickable(true);
                ToastUtils.show("文件下载失败,失败原因：" + message);
                Log.e("onFail", "是否在主线程中运行:" + String.valueOf(Looper.getMainLooper() == Looper.myLooper()));
            }

            @Override
            public void onComplete() {  //  运行在主线程中
                ToastUtils.show("文件下载成功");
                btn.setClickable(true);
            }
        });
    }

    /**
     * 取消下载
     * @param view
     */
    public void cancelDownload(View view) {
        if (downloadUtils != null) {
            downloadUtils.cancelDownload();
            btn.setClickable(true);
        }
    }

    private void saveFile(ResponseBody body) {
        String fileName = "oitsme.apk";
        String fileStoreDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        try {
            InputStream is = body.byteStream();
            File file = new File(fileStoreDir + "/" + fileName);
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                fos.flush();
            }
            fos.close();
            bis.close();
            is.close();
            installApk(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void installApk(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private File getFile(){
        String fileStoreDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        String filePath = fileStoreDir + "/test/test.txt";
        FileUtils.createOrExistsFile(filePath);
        //文件路径
        return new File(filePath);
    }
}
