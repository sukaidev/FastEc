package com.sukaidev.latte_core.ui.scanner;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.sukaidev.latte_core.delegates.LatteDelegate;
import com.sukaidev.latte_core.ui.camera.RequestCodes;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by sukaidev on 2019/05/13.
 * 二维码扫描页.
 */
public class ScannerDelegate extends LatteDelegate implements ZBarScannerView.ResultHandler {

    private ScanView mScanView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mScanView == null) {
            mScanView = new ScanView(getContext());
        }
        mScanView.setAutoFocus(true);
        mScanView.setResultHandler(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mScanView != null) {
            mScanView.startCamera();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mScanView != null) {
            mScanView.stopCameraPreview();
            mScanView.stopCamera();
        }
    }

    @Override
    public Object setLayout() {
        return mScanView;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void handleResult(Result result) {
        final Bundle bundle = new Bundle();
        bundle.putString("SCAN_RESULT", result.getContents());
        setFragmentResult(RequestCodes.SCAN, bundle);
        // 出栈
        getSupportDelegate().pop();
    }
}
