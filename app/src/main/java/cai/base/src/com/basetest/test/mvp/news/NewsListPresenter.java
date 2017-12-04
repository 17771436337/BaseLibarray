package cai.base.src.com.basetest.test.mvp.news;

import android.widget.Toast;

import cai.base.src.com.basetest.test.Config;
import cai.test.com.base.http.RequestParams;
import cai.test.com.base.interfaces.BaseCallListener;
import cai.test.com.base.manger.HttpManger;
import cai.test.com.base.presenter.Presenter;
import cai.test.com.base.x;

/**
 * Created by Administrator on 2017/12/4.
 */

public class NewsListPresenter extends Presenter<NewsFragment> implements BaseCallListener {

    /**获取数据*/
    public void getData(String type){
        RequestParams params = new RequestParams("http://v.juhe.cn/toutiao/index");
        params.addParameter("key", Config.News_Key);
        params.addBodyParameter("type",type);
        HttpManger.getInstance().post(null, params, this);
    }

    @Override
    public void onSuccess(String pResponse) {

    }

    @Override
    public void onFail(String pResponse) {

    }

    @Override
    public void close() {

    }
}
