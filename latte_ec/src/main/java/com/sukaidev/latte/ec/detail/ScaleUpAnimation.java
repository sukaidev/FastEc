package com.sukaidev.latte.ec.detail;

import android.animation.ObjectAnimator;
import android.view.View;

import com.daimajia.androidanimations.library.BaseViewAnimator;

/**
 * Created by sukaidev on 2019/05/15.
 */
public class ScaleUpAnimation extends BaseViewAnimator {

    @Override
    protected void prepare(View target) {
        getAnimatorAgent().playTogether(
                ObjectAnimator.ofFloat(target,"scaleX",0.8f,1f,1.4f,1.2f,1),
                ObjectAnimator.ofFloat(target,"scaleY",0.8f,1f,1.4f,1.2f,1)
        );
    }
}
