package cai.base.src.com.basetest.base.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Administrator on 2017/10/24.
 */

public abstract class BaseSpaceFragment extends BasicsFragment {


    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        mView = inflater.inflate(getContentViewId(), null);
    }


    protected abstract int getContentViewId();

}