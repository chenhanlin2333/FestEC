package com.chen.latte.ui.lunchar;

import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;


public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHolder> {

    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }
}
