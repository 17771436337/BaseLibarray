package cai.base.src.com.basetest.base.activitys;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import cai.base.src.com.basetest.R;
import cai.base.src.com.basetest.enums.ActivityTypeEnum;

/**
 * Created by Administrator on 2017/10/24.
 */

public abstract class BaseTitleActivity extends BasicsActivity {
    @Override
    protected void initData() {

    }

    @Override
    protected void onBackClick() {

    }

    @Override
    protected ActivityTypeEnum getActivityType() {
        return ActivityTypeEnum.TitleActivity;
    }

    @Override
    protected void onHomeActivity(Bundle savedInstanceState) {}

    @Override
    protected void onFragmentActivity(Bundle savedInstanceState) {}

    @Override
    protected void onSpaceActivity(Bundle savedInstanceState) {}

    @Override
    protected void onTitleActivity(Bundle savedInstanceState) {
        setContentView(R.layout.layout_base_activity);
    }

}
