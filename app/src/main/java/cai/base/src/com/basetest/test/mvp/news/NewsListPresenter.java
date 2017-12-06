package cai.base.src.com.basetest.test.mvp.news;

import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import cai.base.src.com.basetest.test.Config;
import cai.base.src.com.basetest.test.db.NewsDb;
import cai.test.com.base.ex.DbException;
import cai.test.com.base.http.RequestParams;
import cai.test.com.base.interfaces.BaseCallListener;
import cai.test.com.base.manger.HttpManger;
import cai.test.com.base.presenter.Presenter;
import cai.test.com.base.x;

/**
 * Created by Administrator on 2017/12/4.
 */

public class NewsListPresenter extends Presenter<NewsFragment> implements BaseCallListener {

    private NewsBean type;
    private int page;
    private int size;

    /**获取数据（网络请求数据）*/
    public void getHttpData(String type){
        RequestParams params = new RequestParams("http://v.juhe.cn/toutiao/index");
        params.addParameter("key", Config.News_Key2);
        params.addBodyParameter("type",type);
        HttpManger.getInstance().post(null, params, this);
    }

    /**
     *  获取数据（通过查询数据库判断是否存在数据，如果存在，则直接提取数据，如果不存在，则进行网络请求）
     * @param category
     *          获取数据的类别
     * @param page
     *          当前页数
     * @param size
     *          每页个数
     */

    public void getData(NewsBean category,int page,int size){
        type = category;
        this.page = page;
        this.size = size;
        ArrayList<NewsDb> data = (ArrayList<NewsDb>) NewsDb.getData(category.getTitle(),page,size);
        if (data != null && data.size() > 0 ){
            getView().addData(data);
        }else{
            getHttpData(category.getValue());
        }
    }


    @Override
    public void onSuccess(String pResponse) {
            //将获取到的数据转化为对象
            NewsModel model = new Gson().fromJson(pResponse,NewsModel.class);

          if (model.getResult() != null) {
              //通过去重的方法实现数据保存
              NewsDb.save(model.getResult().getData());
              //当进行网络请求后，重新从数据库提取数据
              getData(type, page, size);
          }
    }

    @Override
    public void onFail(String pResponse) {

    }

    @Override
    public void close() {

    }
}
