package com.chen.festec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.chen.latte.activities.ProxyActivity;
import com.chen.latte.app.Latte;
import com.chen.latte.delegates.LatteDelegate;

public class MainActivity extends ProxyActivity {


    @Override
    public LatteDelegate setRootDelegate() {
        return new MainDelegate();
    }
}
