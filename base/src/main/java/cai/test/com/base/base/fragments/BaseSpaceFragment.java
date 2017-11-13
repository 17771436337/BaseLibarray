package cai.test.com.base.base.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;

import cai.test.com.base.interfaces.BaseViewInterrfaces;


/**
 * Created by Administrator on 2017/10/24.
 * 空白视图碎片
 */

public abstract class BaseSpaceFragment extends BasicsFragment implements BaseViewInterrfaces {


    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        mView = inflater.inflate(getContentViewId(), null);
        init();
    }




}