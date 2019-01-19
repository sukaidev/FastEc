package com.sukaidev.latte_core.net;

import com.sukaidev.latte_core.app.ConfigType;
import com.sukaidev.latte_core.app.Latte;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by sukaidev on 2019/01/19.
 */
public class RestCreator {

    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }

    private static final class RetrofitHolder {

        private static final String BASE_URL = (String) Latte.getConfiguratons().get(ConfigType.API_HOST.name());

        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private static final class OkHttpHodler {

        private static final int TIME_OUT = 60;

        private static final OkHttpClient OKHPPT_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private static final class RestServiceHolder{

        private static final RestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);


    }
}
