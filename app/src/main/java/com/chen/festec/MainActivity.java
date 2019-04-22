package com.chen.festec;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.widget.Toast;

import com.chen.late_eec.login.ISignListener;
import com.chen.late_eec.login.login_delegate;
import com.chen.late_eec.login.regist_delegate;
import com.chen.latte.activities.ProxyActivity;
import com.chen.latte.delegates.LatteDelegate;

public class MainActivity extends ProxyActivity  implements ISignListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new regist_delegate();
    }

    @Override
    public void onSignInSuccess() {

    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
    }
}
