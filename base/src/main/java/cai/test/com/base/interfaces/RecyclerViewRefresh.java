package cai.test.com.base.interfaces;

/**
 * Created by Administrator on 2017/11/17.
 * 列表的上拉刷新下拉加载接口
 */

public interface RecyclerViewRefresh {
    /**"上拉刷新数据（已经通过线程发送并且实现刷新）*/
    void onRefresh();
    /**下拉加载数据（已经通过线程发送并且实现刷新）*/
    void onLoadMore();
}
