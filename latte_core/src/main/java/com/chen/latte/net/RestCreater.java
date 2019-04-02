package com.chen.latte.net;

import com.chen.latte.app.ConfigType;
import com.chen.latte.app.Latte;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RestCreater {


    public static WeakHashMap<String,Object> getParams(){
        return ParamsHolder.PARMAS;
    }

    private static final class ParamsHolder{
        public static WeakHashMap<String,Object> PARMAS = new WeakHashMap<>();
    }

    public static Retrofit getRrtrofit(){
        return RertrofitHolder.RETROFITINSTANCE;
    }


    public static RestService getRrest_Service(){
        return RestServiceHolder.REST_SERVICE;
    }


    private static final class RertrofitHolder{
        private static final String BASE_URL = (String) Latte.getConfigurations().get(ConfigType.API_HOST.name());
        private static final Retrofit RETROFITINSTANCE = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(Okhttpholder.cilent)
                .build();
    }

    private static final class Okhttpholder{
        private static long timeout = 60;
        private static OkHttpClient cilent = new OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .build();
    }


    private static final class RestServiceHolder{
        private static final RestService REST_SERVICE = RertrofitHolder.RETROFITINSTANCE.create(RestService.class);
    }
}
