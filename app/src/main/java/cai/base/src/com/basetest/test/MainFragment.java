package cai.base.src.com.basetest.test;

import android.view.View;
import android.widget.Toast;

import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import cai.base.src.com.basetest.R;
import cai.test.com.base.base.HolderView;
import cai.test.com.base.base.fragments.BaseListFragment;
import cai.test.com.base.interfaces.BaseCallListener;
import cai.test.com.base.manger.AppManger;
import cai.test.com.base.manger.HttpManger;

/**
 * Created by Administrator on 2017/10/23.
 */
public class MainFragment extends BaseListFragment {
    ArrayList<String> list ;

    @Override
    public void init() {
        setLoadMoreAndRefresh(true,true);
        list = new ArrayList<>();
        list.add("测试");
        list.add("测试");
        list.add("测试");
        list.add("测试");
        list.add("测试");
        list.add("测试");
        list.add("测试");
        list.add("测试");
        list.add("测试");
        list.add("测试");

        addData(list,true);
    }


    @Override
    public void onItemClick(View view, HolderView holder, int position) {
        Toast.makeText(context,"单机",Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onItemLongClick(View view, HolderView holder, int position) {
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
        HttpManger.getInstance().post(null, params, new BaseCallListener(){
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
        try {
            AppManger.getInstances().getDb().save(test);
        } catch (DbException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void bindData(HolderView holder, int position) {
        if (position % 2 == 0){
            holder.setText(R.id.text_test,"测试数据："+position);

        }

    }

    @Override
    public int getItemLayoutId(int viewType) {

        return R.layout.item_test_text;
    }
}
