package com.chen.latte.net;

import android.content.Context;
import android.provider.Telephony;

import com.chen.latte.app.Latte;
import com.chen.latte.net.callback.IError;
import com.chen.latte.net.callback.IFailure;
import com.chen.latte.net.callback.IRequest;
import com.chen.latte.net.callback.ISuccess;
import com.chen.latte.net.callback.LatteCallBack;
import com.chen.latte.net.ui.LatteLaoder;
import com.chen.latte.net.ui.LoaderCreater;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;

public class RestCilent {

    private  final String URL ;
    private  final Map<String,Object> PARAMS = RestCreater.getParams();
    private  final ISuccess ISUCCESS;
    private  final IError IERROR;
    private  final IRequest IREQUEST;
    private  final IFailure IFAILURE;
    private  final RequestBody REQUESTBODY;
    private  final Context MCONTENXT;

    public RestCilent(String URLc,
                      ISuccess ISUCCESS,
                      Map<String,Object> map,
                      IError IERROR,
                      IRequest IREQUEST,
                      IFailure IFAILURE,
                      RequestBody REQUESTBODY,
                      Context Context) {
        this.URL = URLc;
        this.PARAMS.putAll(map);
        this.ISUCCESS = ISUCCESS;
        this.IERROR = IERROR;
        this.IREQUEST = IREQUEST;
        this.IFAILURE = IFAILURE;
        this.REQUESTBODY = REQUESTBODY;
        this.MCONTENXT = Context;
    }

    public static RestCilentBuilder builder(){
        return new RestCilentBuilder();
    }

    private void request(HttpMethod method){
        final RestService service  = RestCreater.getRrest_Service();
        Call<String> call = null;
        if (IREQUEST != null){
            IREQUEST.onRequeststrat();
            if (MCONTENXT!=null){
                LatteLaoder.showLoading(MCONTENXT);
            }
        }

        switch (method){
            case GET:
                call = service.get(URL,PARAMS);
                break;
            case POST:
                call = service.post(URL,PARAMS);
                break;
            case DELETE:
                call = service.delete(URL,PARAMS);
                break;
            case PUT:
                call = service.put(URL,PARAMS);
            default:
                break;
        }
        if (call != null){
            call.enqueue(getCallback());
        }
    }

    public LatteCallBack getCallback(){
        return new LatteCallBack(ISUCCESS,IERROR,IREQUEST,IFAILURE);
    }

    public void get(){
        request(HttpMethod.GET);
    }

    public void post(){
        request(HttpMethod.POST);
    }
    public void put(){
        request(HttpMethod.PUT);
    }
    public void delete(){
        request(HttpMethod.DELETE);
    }
}
