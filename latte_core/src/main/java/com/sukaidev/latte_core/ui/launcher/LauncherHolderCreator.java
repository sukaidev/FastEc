package com.sukaidev.latte_core.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;


/**
 * Created by sukaidev on 2019/03/10.
 */
public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHolder> {

    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }
}
