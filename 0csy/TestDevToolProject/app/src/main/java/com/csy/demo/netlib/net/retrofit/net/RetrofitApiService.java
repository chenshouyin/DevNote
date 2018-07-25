package com.csy.demo.netlib.net.retrofit.net;

import com.csy.demo.netlib.net.retrofit.demo.UserResponse;
import com.csy.demo.netlib.net.retrofit.module.reponse.LoginResponse;
import com.csy.demo.netlib.net.retrofit.module.reponse.MeiZi;
import com.csy.demo.netlib.net.retrofit.module.request.LoginRequest;
import com.csy.net.net.common.BasicResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by dell on 2017/4/1.
 */

public interface RetrofitApiService {

    /**
     * 指定具体链接
     * @param url
     * @param
     * @return
     */
    @GET
//    Observable<UserResponse> getUserInfoByGet(@Url String url,@Body UserRequestParams request);
//    @GET
//    Observable<UserResponse> getUserInfoByGet(@Url String url);
    Observable<UserResponse> getUserInfoByGet(@Url String url,@Query("page") int page);




    @POST("/userInfo/getUserInfoBean")
    Observable<UserResponse> getUserInfoByPost();

    /**
     * 添加head也可以是map
     * @param lang
     * @return
     */
    @GET("/")
    Call<ResponseBody> foo(@Header("Accept-Language") String lang);

    @Headers("Cache-Control: public, max-age=100")
    @GET("福利/10/1")
    Observable<List<MeiZi>> getMezi();

    /**
     * 登录 appId secret
     * 使用实体类作为参数
     * @return
     */
    @POST("sec/v1.1.0/login")
    Observable<LoginResponse> login(@Body LoginRequest request);

    /**
     * 使用map作为参数
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("sec/v1.1.0/login")
    Observable<LoginResponse> login(@FieldMap Map<String, Object> map);

    /**
     * @param page
     * @param number
     * @return
     */
    @Headers("Cache-Control: public, max-age=100")//设置缓存 缓存时间为100s
    @GET("everySay/selectAll.do")
    Observable<List<MeiZi>> lookBack(@Query("page") int page, @Query("rows") int number);

    /**
     * 单文件上传 方法一
     * @param partList
     * @return
     */
    @Multipart
    @POST("upload/uploadFile.do")
    Observable<BasicResponse> uploadFiles(@Part List<MultipartBody.Part> partList);

    /**
     * 单文件上传 方法二
     * @return
     */
    @Multipart
    @POST("upload/uploadFile.do")
    Observable<BasicResponse<BasicResponse>> uploadFiles(@Part("phone") RequestBody phone, @Part("password") RequestBody password, @Part MultipartBody.Part image);

    /**
     * 多文件上传 方法一
     * @param description
     * @param imgs1
     * @param imgs2
     * @return
     */
    @POST("upload/uploadFile.do")
    Observable<BasicResponse> uploadFiles(@Part("filename") String description,
                                          @Part("pic\"; filename=\"image1.png") RequestBody imgs1,
                                          @Part("pic\"; filename=\"image2.png") RequestBody imgs2);

    /**
     * 多文件上传 方法二
     * @param description
     * @param maps
     * @return
     */
    @POST("upload/uploadFile.do")
    Observable<BasicResponse> uploadFiles(@Part("filename") String description, @PartMap() Map<String, RequestBody> maps);
}
