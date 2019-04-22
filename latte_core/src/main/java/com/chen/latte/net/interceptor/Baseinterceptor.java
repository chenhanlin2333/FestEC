package com.chen.latte.net.interceptor;

import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Response;

public abstract class Baseinterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }

    protected LinkedHashMap<String,String> geturlParamteters(Chain chain){
        HttpUrl url = chain.request().url();
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        for (int i = 0; i < url.querySize(); i++) {
            map.put(url.queryParameterName(i),url.queryParameterValue(i));
        }
        return map;
    }

    protected LinkedHashMap<String,String> getBodyParameter(Chain chain){
        final FormBody formBody = (FormBody) chain.request().body();
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        for (int i = 0; i < formBody.size(); i++) {
            map.put(formBody.name(i),formBody.value(i));
        }
        return map;
    }




    protected String getUrlParameters(Chain chain,String key){
         return chain.request().url().queryParameter(key);
    }

    protected String getBodyParameter(Chain chain,String key){
        return  geturlParamteters(chain).get(key);
    }
}
