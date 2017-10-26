package cai.base.src.com.basetest.base.activitys;

import android.os.Bundle;

/**
 * Created by Administrator on 2017/10/24.
 */

public abstract class BaseSpaceActivity extends BasicsActivity {

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(getContentViewId());
    }
    /**布局ID*/
    protected abstract int getContentViewId();
}
