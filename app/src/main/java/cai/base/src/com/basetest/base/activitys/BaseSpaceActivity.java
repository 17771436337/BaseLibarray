package cai.base.src.com.basetest.base.activitys;

import android.os.Bundle;

import cai.base.src.com.basetest.R;
import cai.base.src.com.basetest.enums.ActivityTypeEnum;

/**
 * Created by Administrator on 2017/10/24.
 */

public abstract class BaseSpaceActivity extends BasicsActivity {

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(contentViewId);
    }

}
