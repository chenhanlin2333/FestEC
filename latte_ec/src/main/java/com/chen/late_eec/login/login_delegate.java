package com.chen.late_eec.login;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chen.late_eec.R;
import com.chen.late_eec.R2;
import com.chen.latte.delegates.LatteDelegate;

import butterknife.BindView;

public class login_delegate extends LatteDelegate {
    @BindView(R2.id.txlogin_email)
    TextInputEditText tie_email;

    @BindView(R2.id.txlogin_password)
    TextInputEditText tie_password;

    @BindView(R2.id.bt_login)
    Button login;

    @BindView(R2.id.tv_gotoregist)
    TextView gotoregist;


    @Override
    public Object setLayout() {
        return R.layout.login_delegate;
    }

    @Override
    public void bindview(Bundle savedInstance, View rootview) {

    }


    private boolean checkForm(){

        String email = tie_email.getText().toString();

        String password = tie_password.getText().toString();

        boolean pass = true;

        if (email.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            tie_email.setError("请输入正确的邮箱格式");
            pass =false;
        }else {
            tie_email.setError(null);
        }
        if (password.isEmpty() || password.length() >6  ){
            tie_password.setError("请输入正确的密码格式");
            pass =false;
        }else {
            tie_password.setError(null);
        }
        return pass;
    }
}
