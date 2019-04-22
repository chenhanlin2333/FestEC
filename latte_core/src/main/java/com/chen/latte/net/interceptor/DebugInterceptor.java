package com.chen.latte.net.interceptor;

import android.support.annotation.RawRes;

import com.chen.latte.util.file.FileUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DebugInterceptor extends Baseinterceptor {
    private final String url;
    private final int valueid;

    public DebugInterceptor(String url, int valueid) {
        this.url = url;
        this.valueid = valueid;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final String url = chain.request().url().toString();
        if (url.contains(this.url)){
            Response response = debugResponse(chain,valueid);
            return response;
        }
        return chain.proceed(chain.request()) ;
    }

    private Response debugResponse(Chain chain,@RawRes int rawid){
        final String json = FileUtil.getRawFile(rawid);
        return getResponse(chain,json);
    }

    private Response getResponse(Chain chain,String json){
         return new Response.Builder()
                 .code(200)
                 .addHeader("Content-type","application/json")
                 .body(ResponseBody.create(MediaType.parse("application/json"),json))
                 .message("OK")
                 .request(chain.request())
                 .protocol(Protocol.HTTP_1_1)
                 .build();
    }






}
