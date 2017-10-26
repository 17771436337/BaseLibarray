package cai.base.src.com.basetest.base.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import cai.base.src.com.basetest.R;
import cai.base.src.com.basetest.base.activitys.BasicsActivity;
import cai.base.src.com.basetest.enums.FragmentTypeEnum;

import static cai.base.src.com.basetest.enums.FragmentTypeEnum.*;

/**
 * Created by Administrator on 2017/10/24.
 */

public class BaseSpaceFragment extends BasicsFragment {



    @Override
    protected void init() {

    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        mView = inflater.inflate(contentViewId, null);
    }


}