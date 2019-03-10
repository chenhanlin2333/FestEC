package com.chen.festec;

import android.app.Application;

import com.chen.late_eec.icon.FontEcMoudle;
import com.chen.latte.app.Latte;
import com.joanzapata.iconify.fonts.FontAwesomeModule;


public class LatteAPP extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcMoudle())
                .withApiHost("http://127.0.0.1/")
                .configure();
    }
}
