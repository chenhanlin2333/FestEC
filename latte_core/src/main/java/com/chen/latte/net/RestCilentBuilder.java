package com.chen.latte.net;

import android.content.Context;

import com.chen.latte.net.callback.IError;
import com.chen.latte.net.callback.IFailure;
import com.chen.latte.net.callback.IRequest;
import com.chen.latte.net.callback.ISuccess;

import java.io.File;
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
    private Context mContext;
    private File mFile;
    private String DOWNLOAD_DIR;
    private String EXTENSION;
    private String NAME;


    public void setFile(File file){
        mFile = file;
    }

    public void setFile(String file){
        File file1 = new File(file);
        mFile =file1;
    }

    public RestCilentBuilder url(String URL) {
        mURL = URL;
        return this;
    }

    public RestCilentBuilder params(WeakHashMap<String, Object> PARAMS) {
        mPARAMS.putAll(PARAMS);
        return this;
    }
    public RestCilentBuilder params(String key,String value) {
        mPARAMS.put(key,value);
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
    public RestCilentBuilder addcontextandload(Context context){
        this.mContext = context;
        return this;
    }


    public final RestCilentBuilder dir(String dir){
        this.DOWNLOAD_DIR = dir;
        return this;
    }

    public final RestCilentBuilder extension(String extension){
        this.DOWNLOAD_DIR = extension;
        return this;
    }


    public final RestCilentBuilder filename(String name){
        this.NAME = name;
        return this;
    }



    public RestCilent build(){
        return new RestCilent(mURL,mISUCCESS,mPARAMS,mIERROR,mIREQUEST,mIFAILURE,mREQUESTBODY,mFile,mContext,DOWNLOAD_DIR,EXTENSION,NAME);
    }


}
