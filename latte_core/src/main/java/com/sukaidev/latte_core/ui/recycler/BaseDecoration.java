package com.sukaidev.latte_core.ui.recycler;

import androidx.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * Created by sukaidev on 2019/05/03.
 */
public class BaseDecoration extends DividerItemDecoration {

    private BaseDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DividerLookupImpl(color, size));
    }

    public static BaseDecoration create(@ColorInt int color, int size) {
        return new BaseDecoration(color, size);
    }
}
