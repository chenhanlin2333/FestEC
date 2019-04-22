package com.chen.late_eec.login;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chen.late_eec.database.DataBaseManager;
import com.chen.late_eec.database.UserProfile;
import com.chen.latte.app.AccountManager;

public class Signhandler {

    public static void  onSignUp(String response,ISignListener signListener){
        final JSONObject profileJson = JSON.parseObject(response);

        final long userid = profileJson.getLong("userid");
        final String name = profileJson.getString("name");
        final String avatar =profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");
        final UserProfile profile =    new UserProfile(userid,name,avatar,gender,address);
        DataBaseManager.getIsntance().getDao().insert(profile);

        //已经注册并成功登录
        AccountManager.setSigState(true);
        signListener.onSignInSuccess();
    }
}
