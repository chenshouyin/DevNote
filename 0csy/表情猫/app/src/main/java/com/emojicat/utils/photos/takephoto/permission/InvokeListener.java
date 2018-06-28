package com.emojicat.utils.photos.takephoto.permission;

import com.emojicat.utils.photos.takephoto.model.InvokeParam;

/**
 * 授权管理回调
 */
public interface InvokeListener {
    PermissionManager.TPermissionType invoke(InvokeParam invokeParam);
}
