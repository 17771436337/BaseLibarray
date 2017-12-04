package cai.base.src.com.basetest.test.mvp.news;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import cai.base.src.com.basetest.R;
import cai.test.com.base.annotation.RequirePresenter;
import cai.test.com.base.view.adapter.HolderView;
import cai.test.com.base.view.fragments.BaseListFragment;
import cai.test.com.base.http.RequestParams;
import cai.test.com.base.interfaces.BaseCallListener;
import cai.test.com.base.manger.HttpManger;
import cai.test.com.base.x;

/**
 * Created by Administrator on 2017/10/23.
 */
@RequirePresenter(NewsListPresenter.class)
public class NewsFragment<NewsModle,NewsListPresenter> extends BaseListFragment{
    ArrayList<NewsModle> list ;

    @Override
    public void init() {
        setLoadMoreAndRefresh(true,true);
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
    public void onRefresh() {
        addData(list,true);
        Toast.makeText(context,"上拉刷新",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMore() {
        addData(list,false);
        Toast.makeText(context,"下拉加载",Toast.LENGTH_SHORT).show();

    }


    @Override
    public void bindData(HolderView holder, int position) {
        if (position % 2 == 0){
            holder.setText(R.id.text_test,"测试数据："+position);

        }

    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.item_fragment_news;
    }
}
