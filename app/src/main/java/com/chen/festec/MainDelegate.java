package com.chen.festec;

import android.os.Bundle;
import android.view.View;

import com.chen.latte.delegates.LatteDelegate;

public class MainDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_main;
    }

    @Override
    public void bindview(Bundle savedInstance, View rootview) {

    }
}
