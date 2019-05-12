package com.sukaidev.latte_core.ui.callback;

import androidx.annotation.Nullable;

/**
 * Created by sukaidev on 2019/05/11.
 */
public interface IGlobalCallback<T> {
    void executeCallback(@Nullable T args);
}
