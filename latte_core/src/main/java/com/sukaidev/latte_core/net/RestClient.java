package com.sukaidev.latte_core.net;

import com.sukaidev.latte_core.net.callback.IError;
import com.sukaidev.latte_core.net.callback.IFailure;
import com.sukaidev.latte_core.net.callback.IRequest;
import com.sukaidev.latte_core.net.callback.ISuccess;
import com.sukaidev.latte_core.net.callback.RequestCallback;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by sukaidev on 2019/01/19.
 */
public class RestClient {

    private final String URL;
    private static final Map<String, Object> mParams = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;

    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body) {
        this.URL = url;
        mParams.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        switch (method) {
            case GET:
                call = service.get(URL, mParams);
                break;
            case POST:
                call = service.post(URL, mParams);
                break;
            case PUT:
                call = service.put(URL, mParams);
                break;
            case DELETE:
                call = service.delete(URL, mParams);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallBack());
        }
    }

    private Callback<String> getRequestCallBack(){
        return new RequestCallback(REQUEST,SUCCESS,FAILURE,ERROR);
    }

    public final void get(){
        request(HttpMethod.GET);
    }


    public final void post(){
        request(HttpMethod.POST);
    }

    public final void put(){
        request(HttpMethod.PUT);
    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }
}

