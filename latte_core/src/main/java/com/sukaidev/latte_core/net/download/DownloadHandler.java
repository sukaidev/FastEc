package com.sukaidev.latte_core.net.download;

import android.os.AsyncTask;

import com.sukaidev.latte_core.net.RestCreator;
import com.sukaidev.latte_core.net.callback.IError;
import com.sukaidev.latte_core.net.callback.IFailure;
import com.sukaidev.latte_core.net.callback.IRequest;
import com.sukaidev.latte_core.net.callback.ISuccess;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sukaidev on 2019/03/09.
 */
public class DownloadHandler {

    private final String URL;
    private static final Map<String, Object> mParams = RestCreator.getParams();
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    public DownloadHandler(String url,
                           IRequest request,
                           String downloadDir,
                           String extension,
                           String name,
                           ISuccess iSuccess,
                           IFailure iFailure,
                           IError iError) {
        this.URL = url;
        this.REQUEST = request;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.SUCCESS = iSuccess;
        this.FAILURE = iFailure;
        this.ERROR = iError;
    }

    public final void handleDownload() {
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        RestCreator.getRestService().download(URL, mParams)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            ResponseBody responseBody = response.body();
                            final SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS);
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR, EXTENSION, responseBody, NAME);

                            // 这里一定要注意判断，否则下载不全
                            if (task.isCancelled()) {
                                if (REQUEST != null) {
                                    REQUEST.onRequestEnd();
                                }
                            }
                        } else {
                            if (ERROR != null)
                                ERROR.onError(response.code(), response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (FAILURE != null) {
                            FAILURE.onFailure();
                        }
                    }
                });
    }
}
