package com.chen.festec;

import android.app.Application;

import com.chen.latte.app.Latte;

public class LatteAPP extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://127.0.0.1/")
                .configure();


    }
}
