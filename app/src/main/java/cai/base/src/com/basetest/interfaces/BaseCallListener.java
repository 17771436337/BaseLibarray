package cai.base.src.com.basetest.interfaces;

/**
 * Created by Administrator on 2017/11/13.
 * 网络请求接口
 * 重新接口回调，方便设置缓存数据
 */

public interface BaseCallListener {
    /**数据请求成功*/
    public void onSuccess(String pResponse);

    /**数据请求失败*/
    public void onFail(String pResponse);

    /**数据请求接口关闭*/
    public void close();
}
