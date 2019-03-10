package com.chen.latte.app;


import android.content.Context;

import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public final class Latte {

    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONFIGHT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }


    private static WeakHashMap<String,Object> getConfigurations(){
        return Configurator.getInstance().getLatteConfigs();
    }
}
