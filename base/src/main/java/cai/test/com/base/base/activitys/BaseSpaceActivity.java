package cai.test.com.base.base.activitys;

import android.os.Bundle;

import cai.test.com.base.interfaces.BaseViewInterrfaces;

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
