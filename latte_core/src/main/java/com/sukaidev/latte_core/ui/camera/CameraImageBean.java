package com.sukaidev.latte_core.ui.camera;

import android.net.Uri;

/**
 * Created by sukaidev on 2019/05/10.
 * 存储一些中间值.
 */
public final class CameraImageBean {

    private Uri mPath = null;
    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance(){
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri mPath) {
        this.mPath = mPath;
    }
}
