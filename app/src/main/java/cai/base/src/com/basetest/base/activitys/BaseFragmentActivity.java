package cai.base.src.com.basetest.base.activitys;

import android.os.Bundle;

import cai.base.src.com.basetest.enums.ActivityTypeEnum;

/**
 * Created by Administrator on 2017/10/24.
 */

public abstract class BaseFragmentActivity extends BasicsActivity {


    @Override
    protected void onBackClick() {

    }

    @Override
    protected void onFragmentActivity(Bundle savedInstanceState) {


    }

    @Override
    protected ActivityTypeEnum getActivityType() {
        return ActivityTypeEnum.FragmentActivity;
    }

    @Override
    protected void onHomeActivity(Bundle savedInstanceState) { }
    @Override
    protected void onSpaceActivity(Bundle savedInstanceState) {}

    @Override
    protected void onTitleActivity(Bundle savedInstanceState) { }
}
