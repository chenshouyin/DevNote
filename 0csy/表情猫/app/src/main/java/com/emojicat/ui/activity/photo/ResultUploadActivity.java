package com.emojicat.ui.activity.photo;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.bumptech.glide.Glide;
import com.emojicat.R;
import com.emojicat.ui.widget.recyclerview.utils.Utils;
import com.emojicat.utils.Dbug;
import com.emojicat.utils.ToasUtil;
import com.emojicat.utils.net.NetJudgeUtil;
import com.emojicat.utils.photos.takephoto.model.TImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * - 支持通过相机拍照获取图片
 * - 支持从相册选择图片
 * - 支持从文件选择图片
 * - 支持多图选择
 * - 支持批量图片裁切
 * - 支持批量图片压缩
 * - 支持对图片进行压缩
 * - 支持对图片进行裁剪
 * - 支持对裁剪及压缩参数自定义
 * - 提供自带裁剪工具(可选)
 * - 支持智能选取及裁剪异常处理
 * - 支持因拍照Activity被回收后的自动恢复
 * Author: crazycodeboy
 * Date: 2016/9/21 0007 20:10
 * Version:4.0.0
 * 技术博文：http://www.cboy.me
 * GitHub:https://github.com/crazycodeboy
 * Eamil:crazycodeboy@gmail.com
 */
public class ResultUploadActivity extends Activity {
    ArrayList<TImage> images;
    @BindView(R.id.btUpload)
    Button btUpload;
    @BindView(R.id.etText)
    EditText etText;
    @BindView(R.id.btTextAdd)
    Button btTextAdd;
    @BindView(R.id.etTag)
    EditText etTag;
    @BindView(R.id.btTagAdd)
    Button btTagAdd;

    List<String> mListTexts;//配文描述
    List<String> mListTags;//图片所属标签
    @BindView(R.id.etGroup)
    EditText etGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_layout);
        ButterKnife.bind(this);
        images = (ArrayList<TImage>) getIntent().getSerializableExtra("images");

        mListTexts = new ArrayList<>();
        mListTags = new ArrayList<>();

        showImg();
    }

    private void showImg() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llImages);
        for (int i = 0, j = images.size(); i < j - 1; i += 2) {
            View view = LayoutInflater.from(this).inflate(R.layout.image_show, null);
            ImageView imageView1 = (ImageView) view.findViewById(R.id.imgShow1);
            ImageView imageView2 = (ImageView) view.findViewById(R.id.imgShow2);
            Glide.with(this).load(new File(images.get(i).getCompressPath())).into(imageView1);
            Glide.with(this).load(new File(images.get(i + 1).getCompressPath())).into(imageView2);
            linearLayout.addView(view);
        }
        if (images.size() % 2 == 1) {
            View view = LayoutInflater.from(this).inflate(R.layout.image_show, null);
            ImageView imageView1 = (ImageView) view.findViewById(R.id.imgShow1);
            Glide.with(this).load(new File(images.get(images.size() - 1).getCompressPath())).into(imageView1);
            linearLayout.addView(view);
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.btUpload, R.id.btTextAdd, R.id.btTagAdd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btUpload:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < images.size(); i++) {
                            String group = "";//图片分组
                            Dbug.d("", "" + images.get(i).getOriginalPath());
                            Dbug.d("", "" + images.get(i).getCompressPath());
                            String compressPath = images.get(i).getCompressPath();
                            String imageName = compressPath.substring(compressPath.lastIndexOf("/") + 1, compressPath.length());
                            try {
                                uploadImageTest(compressPath, imageName, mListTexts, mListTags, "尔康");
                                Dbug.d("", "==图片保存成功"+i);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                                Dbug.d("", "==图片保存失败"+i+"=="+e);
                            } catch (AVException e) {
                                e.printStackTrace();
                                Dbug.d("", "==图片保存失败"+i+"=="+e);
                            }

                        }
                    }
                }).start();//子线程同步执行

//                for (int i = 0; i < images.size(); i++) {
//                    String group = "";//图片分组
//                    Dbug.d("", "" + images.get(i).getOriginalPath());
//                    Dbug.d("", "" + images.get(i).getCompressPath());
//                    String compressPath = images.get(i).getCompressPath();
//                    String imageName = compressPath.substring(compressPath.lastIndexOf("/") + 1, compressPath.length());
//                    try {
//                        uploadImage(compressPath, imageName, mListTexts, mListTags, group);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
                break;
            case R.id.btTextAdd:
                if (!"".equals(etText.getText().toString())) {
                    mListTexts.add(etText.getText().toString());
                    etText.setText("");
                }

                break;

            case R.id.btTagAdd:
                if (!"".equals(etTag.getText().toString())) {
                    mListTags.add(etTag.getText().toString());
                }
                etTag.setText("");
                break;
        }
    }


    /**
     * @param filePath  表情路径
     * @param fileName  表情名字
     * @param listTexts 配文数组
     * @param tags      标签数组
     * @param group     表情分组
     * @return
     * @throws FileNotFoundException
     */
    private boolean uploadImage(String filePath, String fileName, final List<String> listTexts, final List<String> tags, final String group) throws FileNotFoundException {

        //先上传图片
        if (NetJudgeUtil.isNetworkAvailable(Utils.getContext())) {
            final AVFile avImageFile = AVFile.withAbsoluteLocalPath(fileName, filePath);
            avImageFile.saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null) {
                        //上传图片成功,设置图片信息
                        AVObject avObject = new AVObject("image");
                        avObject.put("imageFile", avImageFile);//首先是图片File
                        avObject.put("imageOwner", AVUser.getCurrentUser());//其次图片上传者
                        avObject.put("imageTexs", listTexts);//图片文字描述,数组
                        Random random = new Random();
                        avObject.put("virtualTotalShareCount", random.nextInt(1000));//图片虚拟分享数,1000以内随机数
                        avObject.put("virtualSeeCount", random.nextInt(1000));//图片虚拟查看数
                        avObject.put("tags", tags);//图片标签
                        avObject.put("group", group);//表情分组
                        try {
                            avObject.save();
                        } catch (AVException e1) {
                            e1.printStackTrace();
                        }

                    } else {
                        ToasUtil.showToast("上传失败,请重试" + e);
                    }
                }
            });


        } else {
            ToasUtil.showToast("当前无可用网络,请检查网络状态...");
        }

        return false;
    }


    /**
     * @param filePath  表情路径
     * @param fileName  表情名字
     * @param listTexts 配文数组
     * @param tags      标签数组
     * @param group     表情分组
     * @return
     * @throws FileNotFoundException
     */
    private boolean uploadImageTest(String filePath, String fileName, final List<String> listTexts, final List<String> tags, final String group) throws FileNotFoundException, AVException {

        //先上传图片
        if (NetJudgeUtil.isNetworkAvailable(Utils.getContext())) {
            final AVFile avImageFile = AVFile.withAbsoluteLocalPath(fileName, filePath);
            avImageFile.save();
            //上传图片成功,设置图片信息
            AVObject avObject = new AVObject("image");
            avObject.put("imageFile", avImageFile);//首先是图片File
            avObject.put("imageOwner", AVUser.getCurrentUser());//其次图片上传者
            avObject.put("imageTexs", listTexts);//图片文字描述,数组
            Random random = new Random();
            avObject.put("virtualTotalShareCount", random.nextInt(1000));//图片虚拟分享数,1000以内随机数
            avObject.put("virtualSeeCount", random.nextInt(1000));//图片虚拟查看数
            avObject.put("tags", tags);//图片标签
            avObject.put("group", group);//表情分组
            avObject.saveEventually(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    //保证一定会上传
                    if (e == null) {
                        Dbug.d("", "==图片信息更新成功");
                    } else {
                        Dbug.d("", "==图片信息更新失败" + e);
                    }
                }
            });

        } else {
            ToasUtil.showToast("当前无可用网络,请检查网络状态...");
        }

        return false;
    }


}
