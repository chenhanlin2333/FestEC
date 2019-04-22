package com.chen.latte.util.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.chen.latte.app.Latte;
/**
 * 提示:
 * Activity.getPreferences(int mode)生成 Activity名.xml 用于Activity内部存储
 * PreferenceManager.getDefaultSharedPreferences(Context)生成 包名_preferences.xml
 * Context.getSharedPreferences(String name,int mode)生成name.xml
 */

public class Lattepreference {
    private static final SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(Latte.getApplication());
    public static final String FIRSTOPEN = " first";


    public static void setflage(String falgename,boolean flage){
        mSharedPreferences.edit().putBoolean(falgename,flage);
    }

    public static boolean getflage(String flagename){
        return mSharedPreferences.getBoolean(flagename,false);
    }

}
