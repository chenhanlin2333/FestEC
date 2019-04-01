package com.chen.latte.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;
import android.widget.FrameLayout;

import com.chen.latte.R;
import com.chen.latte.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

// supportActivity 是 fragmentation 的 他是继承v7的包

public abstract class ProxyActivity  extends SupportActivity {

    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);

    }

    private void initContainer(@Nullable Bundle savedInstanceState){
        final FrameLayout container  = new FrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null){
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
