package cai.base.src.com.basetest.base.activitys;

import android.os.Bundle;

import cai.base.src.com.basetest.interfaces.BaseViewInterrfaces;

/**
 * Created by Administrator on 2017/10/24.
 */

public abstract class BaseSpaceActivity extends BasicsActivity implements BaseViewInterrfaces {

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(getContentViewId());
        init();
    }

}
