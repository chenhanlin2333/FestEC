package com.chen.late_eec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;


import com.chen.late_eec.R;
import com.chen.late_eec.R2;

import com.chen.latte.delegates.LatteDelegate;
import com.chen.latte.ui.lunchar.ScrolllauncherTag;
import com.chen.latte.util.storage.Lattepreference;
import com.chen.latte.util.timer.BaseTimerTask;
import com.chen.latte.util.timer.TimeListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

public class LauncherDelegate extends LatteDelegate implements TimeListener {

    private int mCount = 5 ;

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTextTimer;
    private Timer mTimer = null;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView(){
        if (mTimer != null){
            mTimer.cancel();
            mTimer = null;
            checkStartScraollDelegate();
        }
    }

    private void checkStartScraollDelegate(){
        boolean isf = Lattepreference.getflage(ScrolllauncherTag.HAS_FIRST_LAUNCHER_APP.name());
        if (!isf){
            start(new LauncherScraollDelegate(),SINGLETASK);
        }else {
            //TODO 检查用户是否登录app

        }
    }

    private void initTimer(){
        mTimer = new Timer();
        final BaseTimerTask task  = new BaseTimerTask(this);
        mTimer.schedule(task,0,1000);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initTimer();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void bindview(Bundle savedInstance, View rootview) {

    }

    @Override
    public void ontimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTextTimer != null){
                    mTextTimer.setText(MessageFormat.format("跳过\n{0}s",mCount));
                    mCount--;
                    if (mCount<0){
                        if (mTimer != null){
                            mTimer.cancel();
                            mTimer = null;
                            checkStartScraollDelegate();
                        }
                    }
                }
            }
        });
    }
}
