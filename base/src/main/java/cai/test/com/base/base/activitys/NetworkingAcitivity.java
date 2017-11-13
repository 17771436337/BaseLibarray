package cai.test.com.base.base.activitys;

import android.view.View;

import org.xutils.view.annotation.Event;

import cai.test.com.base.R;
import cai.test.com.base.utils.AppUtils;


/**
 * Created by Administrator on 2017/11/7.
 * 当没有网络时会出现的界面
 */

public class NetworkingAcitivity extends BaseTitleActivity{



    @Override
    protected boolean isShowBack() {
        return true;
    }

    @Override
    protected String getTitleName() {
        return "没有网络";
    }

    @Override
    protected void onBackClick() {
                finish();
    }

    @Override
    public void init() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_no_network;
    }


    public void startSetting(View v){
        AppUtils.getInstance().startAppNetSettings(this);
    }
}
