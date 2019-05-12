package com.sukaidev.latte.ec.main.personal.settings;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.alibaba.fastjson.JSON;
import com.sukaidev.latte.ec.R;
import com.sukaidev.latte.ec.R2;
import com.sukaidev.latte_core.delegates.LatteDelegate;
import com.sukaidev.latte_core.net.RestClient;
import com.sukaidev.latte_core.net.callback.ISuccess;

import butterknife.BindView;

/**
 * Created by sukaidev on 2019/05/12.
 * 关于页面.
 */
public class AboutDelegate extends LatteDelegate {

    @BindView(R2.id.tv_info)
    AppCompatTextView mTextAbout = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_about;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        RestClient.builder()
                .url("about.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final String info = JSON.parseObject(response).getString("data");
                        mTextAbout.setText(info);
                    }
                })
                .build()
                .get();
    }
}
