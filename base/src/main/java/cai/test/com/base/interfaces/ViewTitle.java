package cai.test.com.base.interfaces;

/**
 * Created by Administrator on 2017/12/1.
 *标题接口
 */

public interface ViewTitle {
    /** 标题返回按钮显示 */
    public  boolean isShowBack();
    /** 标题名字*/
    public  String getTitleName();
    /**
     * 返回键监听事件
     */
    public  void onBackClick();

    /**标题布局*/
    public  int getTitleLayout();
}
