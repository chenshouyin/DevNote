package com.tool.csy.devcsytool.utils.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by chenshouyin on 2017/11/29.
 * 我的博客:http://blog.csdn.net/e_inch_photo
 * 我的Github:https://github.com/chenshouyin
 * SpUtils存储基本类型以及对象
 * 也可拓展存储图片,Base64编码,存字符串
 */


public class SpUtils {

    private static SpUtils SpUtils = null;

    //单例模式，把Context传进去  
    public static SpUtils getInstance(Context context) {
        if (SpUtils == null) {
            synchronized (SpUtils.class) {
                if (SpUtils == null) {
                    SpUtils = new SpUtils();
                    SpUtils.setContext(context);
                    return SpUtils;
                }
            }
        }

        return SpUtils;
    }

    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }



    //存Boolean 型数据  
    public void putBoolean(String key, boolean value) {
        try {
            SharedPreferences.Editor editor = getSP().edit();
            editor.putBoolean(key, value);
            editor.commit();
        } catch (NullPointerException exception) {
            Log.d("sp", "" + exception);
        }
    }

    //取Boolean 型数据
    public boolean getBoolean(String key, boolean defValue) {
        try {
            return getSP().getBoolean(key, defValue);
        } catch (NullPointerException exception) {
            Log.d("sp", "" + exception);
            return defValue;
        }
    }

    //存Long 型数据
    public void putLong(String key, long value) {
        try {
            SharedPreferences.Editor editor = getSP().edit();
            editor.putLong(key, value);
            editor.commit();
        } catch (NullPointerException exception) {
            Log.d("sp", "" + exception);
        }
    }


    //取Long 型数据  
    public long getLong(String key, long defValue) {
        try {
            return getSP().getLong(key, defValue);
        } catch (NullPointerException exception) {
            Log.d("sp", "" + exception);
            return defValue;
        }
    }

    //存整型
    public void putInt(String key, int value) {
        try {
            SharedPreferences.Editor editor = getSP().edit();
            editor.putInt(key, value);
            editor.commit();
        } catch (Exception e) {
            Log.d("sp", "" + e);
        }
    }

    //取整型  
    public int getInt(String key, int defaultValue) {
        try {
            return getSP().getInt(key, defaultValue);
        } catch (Exception e) {
            Log.d("sp", "" + e);
            return defaultValue;

        }
    }


    //取String  
    public String getString(String key, String defValue) {
        try {
            return getSP().getString(key, defValue);
        } catch (NullPointerException e) {
            Log.d("sp", "" + e);
            return defValue;
        }
    }

    //存String  
    public void putString(String key, String value) {
        try {
            SharedPreferences.Editor editor = getSP().edit();
            editor.putString(key, value);
            editor.commit();
        } catch (NullPointerException e) {
            Log.d("sp", "" + e);
        }
    }

    //清除数据  
    public void clear() {
        try {
            SharedPreferences.Editor editor = getSP().edit();
            editor.clear();
            editor.commit();
        } catch (NullPointerException e) {
            Log.d("sp", "" + e);
        }
    }

    //获得SharedPreferences对象  
    private SharedPreferences getSP() {
        return context.getSharedPreferences("_sp_file_", Context.MODE_PRIVATE);
    }

    private SharedPreferences getSP(String name) {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }


    /**
     * 保存实体类,实际上是当做string存的
     * 也可保存List<Enty>只要是可序列化的
     * @param key
     * @param obj
     */
    public void putBean(String key, Object obj) {
        if (obj instanceof Serializable) {// obj必须实现Serializable接口，否则会出问题
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(obj);
                String string64 = new String(Base64.encode(baos.toByteArray(), 0));
                putString(key,string64);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            throw new IllegalArgumentException(
                    "the obj must implement Serializble");
        }

    }

    public Object getBean(String key) {
        Object obj = null;
        try {
            String base64 = getString(key,"");
            if (base64.equals("")) {
                return null;
            }
            byte[] base64Bytes = Base64.decode(base64.getBytes(), 1);
            ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}

