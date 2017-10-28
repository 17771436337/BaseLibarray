package cai.base.src.com.basetest;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import cai.base.src.com.basetest.base.fragments.BaseListFragment;
import cai.base.src.com.basetest.base.fragments.BaseSpaceFragment;

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
    }

    @Override
    protected void onLoadMoreData() {
        addData(list,false);
        Toast.makeText(context,"下拉加载",Toast.LENGTH_SHORT).show();
    }

}
