package com.chen.latte.delegates;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chen.latte.activities.ProxyActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;



public abstract class BaseDelegate extends SwipeBackFragment {

    public abstract Object setLayout();
    public Unbinder mbinder = null;
    public abstract void bindview(Bundle savedInstance,View rootview);



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView  = null;
        if (setLayout() instanceof Integer){
            rootView = inflater.inflate((Integer) setLayout(),container,false);
        }else if (setLayout()instanceof View){
            rootView = (View) setLayout();
        }
        if (rootView != null){
            mbinder = ButterKnife.bind(this,rootView);
            bindview(savedInstanceState,rootView);
        }

        return rootView;
    }

    public final ProxyActivity getProxyActivity(){
        return (ProxyActivity) _mActivity;
    }

    @Override
    public void onDestroy() {
        if (mbinder !=null){
            mbinder.unbind();
        }
        super.onDestroy();
    }
}
