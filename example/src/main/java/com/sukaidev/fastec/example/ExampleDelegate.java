package com.sukaidev.fastec.example;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sukaidev.latte_core.delegates.LatteDelegate;
import com.sukaidev.latte_core.net.RestClient;
import com.sukaidev.latte_core.net.callback.IError;
import com.sukaidev.latte_core.net.callback.IFailure;
import com.sukaidev.latte_core.net.callback.IRequest;
import com.sukaidev.latte_core.net.callback.ISuccess;

import androidx.annotation.Nullable;

/**
 * Created by sukaidev on 2019/01/18.
 */
public class ExampleDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();

    }

    private void testRestClient(){
        RestClient.builder()
                .url("http://10.0.2.2/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(),"onFailure",Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(),code,Toast.LENGTH_LONG).show();

                    }
                })
                .build().get();
    }
}
