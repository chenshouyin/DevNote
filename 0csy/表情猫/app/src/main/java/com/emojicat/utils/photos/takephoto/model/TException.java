package com.emojicat.utils.photos.takephoto.model;

import com.emojicat.utils.photos.takephoto.model.TExceptionType;

/**
 * Author: JPH
 * Date: 2016/7/26 10:53
 */
public class TException extends Exception{
    String detailMessage;
    public TException(TExceptionType exceptionType) {
        super(exceptionType.getStringValue());
        this.detailMessage=exceptionType.getStringValue();
    }
    public String getDetailMessage() {
        return detailMessage;
    }
}
