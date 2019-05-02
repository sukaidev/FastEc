package com.sukaidev.latte_core.ui.bannner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by sukaidev on 2019/05/02.
 */
public class HolderCreator implements CBViewHolderCreator<ImageHolder> {

    @Override
    public ImageHolder createHolder() {
        return new ImageHolder();
    }
}
