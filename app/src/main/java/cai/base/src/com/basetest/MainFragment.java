package cai.base.src.com.basetest;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import cai.base.src.com.basetest.base.fragments.BaseListFragment;
import cai.base.src.com.basetest.base.fragments.BaseSpaceFragment;
import cai.base.src.com.basetest.base.model.CommonBean;
import cai.base.src.com.basetest.db.TestDb;
import cai.base.src.com.basetest.http.HttpManger;
import cai.base.src.com.basetest.manger.App;

/**
 * Created by Administrator on 2017/10/23.
 */
public class MainFragment extends BaseListFragment{
    ArrayList<String> list ;

    @Override
    protected void init() {
//        Log.i("test","测试");
        setLoadMoreAndRefresh(true,true);
        list = new ArrayList<>();
        list.add("测试");
        list.add("测试");
        list.add("测试");
        list.add("测试");
        list.add("测试");

        addData(list,true);
    }


    @Override
    protected void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        Toast.makeText(context,"单机",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        Toast.makeText(context,"长按",Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    protected void onRefreshData() {
        addData(list,true);
        Toast.makeText(context,"上拉刷新",Toast.LENGTH_SHORT).show();

        RequestParams params = new RequestParams("http://v.juhe.cn/toutiao/index");
        params.addParameter("key","2cf5722b519719df3dea59880c300489");
        params.addBodyParameter("type","top");
        HttpManger.getInstance().post(null, params, new HttpManger.BaseCallListener(){


            @Override
            public void onSuccess(String  pResponse) {
                Toast.makeText(x.app(),"sdasdas",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(String pResponse) {

            }

            @Override
            public void close() {
                Toast.makeText(x.app(),"接口关闭",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onLoadMoreData() {
        addData(list,false);
        Toast.makeText(context,"下拉加载",Toast.LENGTH_SHORT).show();
        TestDb test = new TestDb();
        test.setDate(System.currentTimeMillis());
        test.setText("测试");
//        try {
//            App.getInstance().getDb().save(test);
//        } catch (DbException e) {
//            e.printStackTrace();
//        }

    }

}
