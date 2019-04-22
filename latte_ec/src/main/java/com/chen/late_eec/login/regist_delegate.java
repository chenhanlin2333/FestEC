package com.chen.late_eec.login;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;
import com.chen.late_eec.R;
import com.chen.late_eec.R2;
import com.chen.latte.delegates.LatteDelegate;
import com.chen.latte.net.RestCilent;
import com.chen.latte.net.callback.ISuccess;
import butterknife.BindView;
import butterknife.OnClick;

public class regist_delegate extends LatteDelegate {

    @BindView(R2.id.tx_name)
    TextInputEditText tie_name;

    @BindView(R2.id.tx_email)
    TextInputEditText tie_email;

    @BindView(R2.id.tx_phone)
    TextInputEditText tie_phone;

    @BindView(R2.id.tx_password)
    TextInputEditText tie_password;

    @BindView(R2.id.tx_repassword)
    TextInputEditText tie_repassword;

    @OnClick(R2.id.bt_regester)
    void regest(){
        if (checkForm()){
            RestCilent.builder()
                    .url("http://127.0.0.1/regist")
                    .params("name",tie_name.getText().toString())
                    .params("email",tie_email.getText().toString())
                    .params("phone",tie_phone.getText().toString())
                    .params("password",tie_password.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String result) {
                            Toast.makeText(getActivity(),result,Toast.LENGTH_SHORT);
                            Signhandler.onSignUp(result,mISignListener);
                        }
                    })
                    .build()
                    .post();
            Toast.makeText(getContext(), "注册成功", Toast.LENGTH_SHORT).show();
        }
    }

    private ISignListener mISignListener = null;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener){
            mISignListener = (ISignListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.regist_delegate;
    }

    @Override
    public void bindview(Bundle savedInstance, View rootview) {

    }

    private boolean checkForm(){
        String name = tie_name.getText().toString();
        String email = tie_email.getText().toString();
        String phone = tie_phone.getText().toString();
        String password = tie_password.getText().toString();
        String repassword = tie_repassword.getText().toString();
        boolean pass = true;

        if (name.isEmpty()){
            tie_name.setError("请输入姓名");
            pass =false;
        }else {
            tie_name.setError(null);
        }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            tie_email.setError("请输入正确的邮箱格式");
            pass =false;
        }else {
            tie_email.setError(null);
        }
        if (phone.isEmpty() || phone.length()!= 11){
            tie_phone.setError("请输入正确的手机号");
            pass =false;
        }else {
            tie_phone.setError(null);
        }

        if (password.isEmpty() || password.length() <6  ){
            tie_password.setError("请输入正确的密码格式");
            pass =false;
        }else {
            tie_password.setError(null);
        }

        if (!repassword.equals(password)){
            tie_repassword.setError("再次输入的密码和密码不相同");
            pass =false;
        }else {
            tie_repassword.setError(null);
        }
        return true;
    }
}
