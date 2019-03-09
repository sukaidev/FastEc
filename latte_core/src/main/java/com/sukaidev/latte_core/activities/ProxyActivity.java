package com.sukaidev.latte_core.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;


import com.sukaidev.latte_core.R;
import com.sukaidev.latte_core.delegates.LatteDelegate;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.ContentFrameLayout;
import me.yokeyword.fragmentation.SupportActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by sukaidev on 2019/01/18.
 */
public abstract class ProxyActivity extends SupportActivity{

    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);

        OkHttpClient client = new OkHttpClient();

    }

    @SuppressLint("RestrictedApi")
    private void initContainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null){
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
