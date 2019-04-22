package com.chen.festec;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.chen.latte.delegates.LatteDelegate;
import com.chen.latte.net.RestCilent;
import com.chen.latte.net.callback.IError;
import com.chen.latte.net.callback.IFailure;
import com.chen.latte.net.callback.IRequest;
import com.chen.latte.net.callback.ISuccess;
import com.chen.latte.ui.LatteLaoder;

public class MainDelegate extends LatteDelegate {

    private final Handler mHandler = new Handler();
    @Override
    public Object setLayout() {
        return R.layout.delegate_main;
    }

    @Override
    public void bindview(Bundle savedInstance, View rootview) {
        testRestCilent();
    }


    private void testRestCilent(){
        LatteLaoder.showLoading(getActivity());
        RestCilent.builder()
                .url("http://127.0.0.1//index")
                .error(new IError() {
                    @Override
                    public void onError(int id, String mesg){
                    }
                })
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
                    }
                })
                .request(new IRequest() {
                    @Override
                    public void onRequeststrat() {

                    }

                    @Override
                    public void onRequestend() {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .build()
                .get();
    }
}
