package cai.test.com.base.view.activitys;

import android.os.Bundle;

import cai.test.com.base.presenter.Presenter;

/**
 * Created by Administrator on 2017/10/24.
 */

public abstract class BaseSpaceActivity<P extends Presenter> extends BasicsActivity<P>  {

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(getContentViewId());
        init();
    }

}
