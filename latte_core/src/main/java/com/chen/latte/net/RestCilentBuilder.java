package com.chen.latte.net;

import com.chen.latte.net.callback.IError;
import com.chen.latte.net.callback.IFailure;
import com.chen.latte.net.callback.IRequest;
import com.chen.latte.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RestCilentBuilder {
    private  String mURL ;
    private  Map<String,Object> mPARAMS = RestCreater.getParams();
    private  ISuccess mISUCCESS;
    private  IError mIERROR;
    private  IRequest mIREQUEST;
    private  IFailure mIFAILURE;
    private  RequestBody mREQUESTBODY;

    public RestCilentBuilder url(String URL) {
        mURL = URL;
        return this;
    }

    public RestCilentBuilder params(WeakHashMap<String, Object> PARAMS) {
        mPARAMS.putAll(PARAMS);
        return this;
    }

    public RestCilentBuilder addKeyValue(String string,Object object){

        mPARAMS.put(string,object);
        return this;
    }

    public RestCilentBuilder success(ISuccess ISUCCESS) {
        mISUCCESS = ISUCCESS;
        return this;
    }

    public RestCilentBuilder error(IError IERROR) {
        mIERROR = IERROR;
        return this;
    }


    public RestCilentBuilder request(IRequest IREQUEST) {
        mIREQUEST = IREQUEST;
        return this;
    }

    public RestCilentBuilder failure(IFailure IFAILURE) {
        mIFAILURE = IFAILURE;
        return this;
    }


    public RestCilentBuilder raw(String raw) {
        this.mREQUESTBODY =  RequestBody.create(MediaType.parse("applciation/json;charset=UTF-8"),raw);
        return this;
    }

    public RestCilent build(){
        return new RestCilent(mURL,mISUCCESS,mPARAMS,mIERROR,mIREQUEST,mIFAILURE,mREQUESTBODY);
    }


}
