package com.chen.latte.net.callback;

import android.view.InputQueue;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LatteCallBack implements Callback<String> {

    private  final ISuccess ISUCCESS;
    private  final IError IERROR;
    private  final IRequest IREQUEST;
    private  final IFailure IFAILURE;

    public LatteCallBack(ISuccess ISUCCESS, IError IERROR, IRequest IREQUEST, IFailure IFAILURE) {
        this.ISUCCESS = ISUCCESS;
        this.IERROR = IERROR;
        this.IREQUEST = IREQUEST;
        this.IFAILURE = IFAILURE;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()){
            if (call.isExecuted()){
                if (ISUCCESS != null){
                    ISUCCESS.onSuccess(response.body());
                }
                if (IREQUEST != null){
                    IREQUEST.onRequestend();
                }
            }
        }else {
            if (IERROR != null){
                IERROR.onError(response.code(),response.message());
            }
            if (IREQUEST != null){
                IREQUEST.onRequestend();
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (IFAILURE !=null){
            IFAILURE.onFailure();
        }
        if (IREQUEST != null){
            IREQUEST.onRequestend();
        }
    }
}
