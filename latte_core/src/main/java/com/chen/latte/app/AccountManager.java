package com.chen.latte.app;

import com.chen.latte.util.storage.Lattepreference;

public class AccountManager {

    private enum SignTag{
        SIGN_TAG
    }

    //保存用户登录状态
    public static void setSigState(boolean state){
        Lattepreference.setflage(SignTag.SIGN_TAG.name(),state);
    }


    private static boolean isSign(){
        return Lattepreference.getflage(SignTag.SIGN_TAG.name());
    }

    public static void checkcount(IuserChecker checker){
        if (isSign()){
            checker.onSingin();
        }else {
            checker.onNotSingin();
        }
    }

}
