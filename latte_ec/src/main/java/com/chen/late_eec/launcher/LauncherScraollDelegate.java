package com.chen.late_eec.launcher;

import android.os.Bundle;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chen.late_eec.R;
import com.chen.latte.delegates.LatteDelegate;
import com.chen.latte.ui.lunchar.LauncherHolderCreator;
import com.chen.latte.ui.lunchar.ScrolllauncherTag;
import com.chen.latte.util.storage.Lattepreference;

import java.util.ArrayList;

public class LauncherScraollDelegate extends LatteDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INSTANCE = new ArrayList<>();

    private void initbanner(){
        INSTANCE.add(R.mipmap.launcher_01);
        INSTANCE.add(R.mipmap.launcher_02);
        INSTANCE.add(R.mipmap.launcher_03);
        INSTANCE.add(R.mipmap.launcher_04);
        INSTANCE.add(R.mipmap.launcher_05);
        mConvenientBanner
                .setPages(new LauncherHolderCreator(),INSTANCE)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void bindview(Bundle savedInstance, View rootview) {
        initbanner();
    }

    @Override
    public void onItemClick(int position) {
        if (position == INSTANCE.size()-1){
            Lattepreference.setflage(ScrolllauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);
            //
        }
    }
}
