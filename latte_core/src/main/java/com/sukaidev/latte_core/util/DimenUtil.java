package com.sukaidev.latte_core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.sukaidev.latte_core.app.Latte;

/**
 * Created by sukaidev on 2019/03/09.
 */
public class DimenUtil {

    // 拿到屏幕的宽
    public static int getScreenWidth() {
        final Resources resource = Latte.getApplication().getResources();
        final DisplayMetrics dm = resource.getDisplayMetrics();
        return dm.widthPixels;
    }

    // 拿到屏幕的高
    public static int getScreenHeight() {
        final Resources resource = Latte.getApplication().getResources();
        final DisplayMetrics dm = resource.getDisplayMetrics();
        return dm.heightPixels;
    }
}
