package com.emojicat.ui.activity.photo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.emojicat.R;
import com.emojicat.utils.photos.takephoto.app.TakePhotoActivity;
import com.emojicat.utils.photos.takephoto.model.TImage;
import com.emojicat.utils.photos.takephoto.model.TResult;

import java.util.ArrayList;

/**
 * Created by chenshouyin on 17/4/4.
 */

public class PhotoPicAndSelectActivity extends TakePhotoActivity {
    private CustomHelper customHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView= LayoutInflater.from(this).inflate(R.layout.common_layout,null);
        setContentView(contentView);
        customHelper= CustomHelper.of(contentView);
    }

    public void onClick(View view) {
        customHelper.onClick(view,getTakePhoto());
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        showImg(result.getImages());
    }

    private void showImg(ArrayList<TImage> images) {
        Intent intent=new Intent(this,ResultUploadActivity.class);
        intent.putExtra("images",images);
        startActivity(intent);
    }
}