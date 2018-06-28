package com.emojicat.utils;

import com.avos.avoscloud.AVObject;

import java.util.List;
import java.util.Random;

/**
 * Created by chenshouyin on 17/5/7.
 */

public class CommenUtils {
    public static String getText(AVObject item){
        List<String> texts = item.getList("imageTexs");
        String text = "表情喵";
        if (texts!=null && texts.size()>0){
            Random random = new Random();
            text = texts.get(random.nextInt(texts.size()-1));//生成随机数
        }
        return text;
    }
}
