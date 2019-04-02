package com.chen.latte.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;

import com.chen.latte.app.Latte;

public class Dimenutil {
    public static int getScrreenWidth(){
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }


    public static int getScrreenHeiht(){
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
