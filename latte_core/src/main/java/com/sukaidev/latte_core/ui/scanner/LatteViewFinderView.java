package com.sukaidev.latte_core.ui.scanner;

import android.content.Context;
import android.util.AttributeSet;

import androidx.core.content.ContextCompat;

import com.sukaidev.latte_core.R;

import me.dm7.barcodescanner.core.ViewFinderView;

/**
 * Created by sukaidev on 2019/05/13.
 */
public class LatteViewFinderView extends ViewFinderView {


    public LatteViewFinderView(Context context) {
        this(context, null);
    }

    public LatteViewFinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        mSquareViewFinder = true;
        mBorderPaint.setColor(ContextCompat.getColor(getContext(), R.color.app_main));
        mLaserPaint.setColor(ContextCompat.getColor(getContext(), R.color.app_main));
    }
}
