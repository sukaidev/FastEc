package com.sukaidev.latte_core.ui.camera;

import android.net.Uri;

import com.sukaidev.latte_core.delegates.PermissionCheckerDelegate;
import com.sukaidev.latte_core.util.file.FileUtil;

/**
 * Created by sukaidev on 2019/05/10.
 * 照相机调用.
 */
public class LatteCamera {

    public static Uri createCropFile() {
        return Uri.parse(FileUtil.createFile("crop_image", FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }


}
