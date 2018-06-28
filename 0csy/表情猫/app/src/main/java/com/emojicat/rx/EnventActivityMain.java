package com.emojicat.rx;

/**
 * Created by chenshouyin on 17/4/25.
 */

public class EnventActivityMain {
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    private String key;
    private Object message;

    public EnventActivityMain(String key, Object message){
        this.key = key;
        this.message = message;
    }
}
