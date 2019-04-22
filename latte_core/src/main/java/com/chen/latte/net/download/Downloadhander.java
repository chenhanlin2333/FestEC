package com.chen.latte.net.download;

import android.os.AsyncTask;

import com.chen.latte.net.RestCreater;
import com.chen.latte.net.callback.IError;
import com.chen.latte.net.callback.IFailure;
import com.chen.latte.net.callback.IRequest;
import com.chen.latte.net.callback.ISuccess;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Downloadhander {

    private  String URL  = null;
    private  Map<String,Object> PARAMS = RestCreater.getParams() ;

    private  ISuccess ISUCCESS = null;
    private  IError IERROR = null;
    private  IRequest IREQUEST = null;
    private  IFailure IFAILURE = null;

    private  String DOWNLOAD_DIR = null;
    private  String EXTENSION = null;
    private  String NAME = null;

    public Downloadhander(String URL, Map<String, Object> PARAMS, ISuccess ISUCCESS, IError IERROR, IRequest IREQUEST, IFailure IFAILURE, String DOWNLOAD_DIR, String EXTENSION, String NAME) {
        this.URL = URL;
        this.PARAMS = PARAMS;
        this.ISUCCESS = ISUCCESS;
        this.IERROR = IERROR;
        this.IREQUEST = IREQUEST;
        this.IFAILURE = IFAILURE;
        this.DOWNLOAD_DIR = DOWNLOAD_DIR;
        this.EXTENSION = EXTENSION;
        this.NAME = NAME;
    }

    public final void handleDownload(){
        if (IREQUEST != null){
            IREQUEST.onRequeststrat();
        }
        Call<ResponseBody> call = RestCreater.getRrest_Service().download(URL,PARAMS);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    final ResponseBody responseBody = response.body();
                    SaveFileTask saveFileTask = new SaveFileTask(IREQUEST,ISUCCESS);
                    saveFileTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DOWNLOAD_DIR,EXTENSION,responseBody,NAME);

                    //判断saveFileTask 完成了
                    if (saveFileTask.isCancelled()){
                        if (IREQUEST != null){
                            IREQUEST.onRequestend();
                        }
                    }
                }else {
                    if (IERROR != null){
                        IERROR.onError(response.code(), response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (IFAILURE != null){
                    IFAILURE.onFailure();
                }
            }
        });

    }
}
