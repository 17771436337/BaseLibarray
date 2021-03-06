
package cai.test.com.base.manger;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;


import java.io.File;

import cai.test.com.base.view.activitys.NetworkingAcitivity;
import cai.test.com.base.interfaces.Callback;
import cai.test.com.base.utils.LogUtil;
import cai.test.com.base.ex.HttpException;
import cai.test.com.base.http.HttpMethod;
import cai.test.com.base.http.RequestParams;
import cai.test.com.base.interfaces.BaseCallListener;
import cai.test.com.base.x;


/**
 * Created by Administrator on 2017/11/7.
 * 网络请求的二次封装
 */

public class HttpManger {
    private static HttpManger instance;
    private long cacheTime = 0;

    private HttpManger(){
        cacheTime = 1000*60;
    }





    /**
     * 单例
     * @return
     */
    public  static HttpManger getInstance(){
        if (instance == null){
            instance = new HttpManger();
        }
        return  instance;
    }
    /**
     * http的Get的网络请求（第二次封装）
     * @param url
     * @param params
     * @param listener
     */
    public void get(String url, RequestParams params, BaseCallListener listener){
      HttpManger.getInstance().Http(url, HttpMethod.GET,params,listener);

    }

    /**
     * http的post的网络请求（第二次封装）
     * @param url
     * @param params
     * @param listener
     */
    public void post(String url, RequestParams params, BaseCallListener listener){
        HttpManger.getInstance().Http(url,HttpMethod.POST,params,listener);
    }

    /**
     * Http的请求
     * @param url
     *      需要的请求链接
     * @param method
     *      Http请求的谓词枚举
     * @param params
     *       Http网络请求参数实体
     * @param listener
     * 网络请求参数实体
     */
    public void Http(String url, HttpMethod method, RequestParams params, BaseCallListener listener){
        if (isNetworkConnected()) {
            if (params != null) {
                if (!TextUtils.isEmpty(url) ){
                    //设置接口链接
                    params.setUri(url);
                }else{
                    if (TextUtils.isEmpty(params.getUri())){
                        LogUtil.d("http-url：没有接口链接");
                        return;
                    }
                }

            }else{
               RequestParams data = new RequestParams();
                if (!TextUtils.isEmpty(url) ){
                    //设置接口链接
                    data.setUri(url);
                }else{
                    LogUtil.d("http-url：没有接口链接");
                    return;
                }
            }
            //为请求添加缓存时间
            params.setCacheMaxAge(cacheTime);
            x.http().request(method, params, new BaseCallback(listener));

        }
    }

    /**
     * 下载
     * @param url
     * 下载链接
     * @param path
     * 保存地址
     * @param listener
     * 回调
     */
    public void httpDownload(String url,String path,Callback.ProgressCallback<File> listener){
        if (isNetworkConnected()) {
            RequestParams params = new RequestParams(url);
            params.setSaveFilePath(path);
            //自动为文件命名
            params.setAutoRename(true);
            x.http().post(params, listener);
        }
    }




    /**
     * 判断是否联网
     * @return
     */
    private boolean isNetworkConnected() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) x.app()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }

        //当未联网时跳转界面
        NetworkingAcitivity.startNetActivity(x.app());
        return false;
    }


    /**
     * 回调类复写
     */
    private class BaseCallback implements Callback.CacheCallback<String>,Callback.CommonCallback<String>{
        private String result = null;
        private boolean hasError = false;
        private BaseCallListener listener;


        public BaseCallback(BaseCallListener listener) {
            this.listener = listener;
        }

        @Override
        public boolean onCache(String result) {
            //在setCacheMaxAge设置范围（上面设置的是60秒）内，如果再次调用GET请求，
            //返回true：缓存内容被返回，相信本地缓存，返回false：缓存内容被返回，不相信本地缓存，仍然会请求网络
            LogUtil.i("Http-cache："+result);
            this.result = result;
            listener.onSuccess( this.result);

            return true;
        }

        @Override
        public void onSuccess(String result) {
            // 注意: 如果服务返回304 或 onCache 选择了信任缓存, 这时result为null.
            if (result != null) {
                this.result = result;
            }
            LogUtil.d("http-result："+result);
            listener.onSuccess( this.result);

        }

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
            hasError = true;
            LogUtil.d("http:"+ex.getMessage());
            listener.onFail(ex.getMessage());
            if (ex instanceof HttpException) { // 网络错误
                HttpException httpEx = (HttpException) ex;
                int responseCode = httpEx.getCode();
                String responseMsg = httpEx.getMessage();
                String errorResult = httpEx.getResult();

            } else { // 其他错误

            }
        }

        @Override
        public void onCancelled(CancelledException cex) {
            LogUtil.d("http:cancelled");
            listener.close();
        }

        @Override
        public void onFinished(){
            if (!hasError && result != null) {
                LogUtil.d("http:success");
                listener.close();
            }
        }
    }




}
