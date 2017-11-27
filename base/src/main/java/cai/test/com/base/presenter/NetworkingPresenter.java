package cai.test.com.base.presenter;

import cai.test.com.base.utils.AppUtils;
import cai.test.com.base.view.activitys.NetworkingAcitivity;

/**
 * Created by Administrator on 2017/11/27.
 */

public class NetworkingPresenter extends Presenter<NetworkingAcitivity> {

    public void test(){
        AppUtils.getInstance().startAppNetSettings(getView());
    }
}
