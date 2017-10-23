package cai.base.src.com.basetest;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import cai.base.src.com.basetest.annotation.FragmentInject;
import cai.base.src.com.basetest.base.BaseListFragment;
import cai.base.src.com.basetest.enums.FragmentTypeEnum;

/**
 * Created by Administrator on 2017/10/23.
 */
@FragmentInject(contentViewId = R.layout.fragment_main,fragmentType = FragmentTypeEnum.ListFragment)
public class MainFragment extends BaseListFragment{
    @Override
    protected void init() {
        Log.i("test","测试");
    }


    @Override
    protected void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    protected void onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    protected void onRefresh() {

    }

    @Override
    protected void onLoadMore() {

    }
}
