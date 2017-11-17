package cai.test.com.base.base.activitys;

import android.content.Context;
import android.content.Intent;
import android.view.View;


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

    /**跳转到无网络状态的Activity*/
    public static void startNetActivity(Context context){
        Intent intent = new Intent(context , NetworkingAcitivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_no_network;
    }


    public void startSetting(View v){
        AppUtils.getInstance().startAppNetSettings(this);
    }
}
