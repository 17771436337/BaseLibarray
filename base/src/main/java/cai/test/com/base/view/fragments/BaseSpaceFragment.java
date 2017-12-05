package cai.test.com.base.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;

import cai.test.com.base.interfaces.BaseViewInterrfaces;
import cai.test.com.base.presenter.Presenter;


/**
 * Created by Administrator on 2017/10/24.
 * 空白视图碎片
 */

public abstract class BaseSpaceFragment<P extends Presenter> extends BasicsFragment<P> {


    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        mView = inflater.inflate(getContentViewId(), null);
    }




}