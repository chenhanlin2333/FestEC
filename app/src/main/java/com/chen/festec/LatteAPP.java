package com.chen.festec;

import android.app.Application;

import com.chen.late_eec.database.DataBaseManager;
import com.chen.late_eec.icon.FontEcMoudle;
import com.chen.latte.app.Latte;
import com.chen.latte.net.interceptor.DebugInterceptor;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;


public class LatteAPP extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcMoudle())
                .withApiHost("http://127.0.0.1/")
                .withInterceptior(new DebugInterceptor("index",R.raw.test))
                .withInterceptior(new DebugInterceptor("regist",R.raw.regist))
                .configure();
        DataBaseManager.getIsntance().init(this);
        initStetho();
    }


    private void initStetho(){
//        Stetho.initialize(
//                Stetho.newInitializerBuilder(this)
//                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
//                        .build());
        Stetho.initializeWithDefaults(this);
    }

}
