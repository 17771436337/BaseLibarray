package cai.base.src.com.basetest.interfaces;

/**
 * Created by Administrator on 2017/11/13.
 * 网络请求接口
 * 重新接口回调，方便设置缓存数据
 */

public interface BaseCallListener {
    public void onSuccess(String pResponse);

    public void onFail(String pResponse);

    public void close();
}
