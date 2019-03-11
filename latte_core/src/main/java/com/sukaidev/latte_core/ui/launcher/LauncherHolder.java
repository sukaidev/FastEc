package com.sukaidev.latte_core.ui.launcher;

import android.content.Context;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * Created by sukaidev on 2019/03/10.
 */
public  class LauncherHolder implements Holder<Integer> {

    private AppCompatImageView mImageView = null;

    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mImageView.setBackgroundResource(data);
    }
}
