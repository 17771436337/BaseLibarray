package cai.test.com.base.view.dialogs;

import android.app.Dialog;

import cai.test.com.base.interfaces.BaseViewInterrfaces;

/**
 * Created by Administrator on 2017/11/18.
 * 弹框的基类
 */

public abstract class BaseDialog implements BaseViewInterrfaces{

    /**重写显示*/
    public abstract void show();

    /**重写取消*/
    public abstract void dismiss();

    @Override
    public void init() {

    }

    @Override
    public int getContentViewId() {
        return 0;
    }
}
