package cai.base.src.com.basetest.base.fragments;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import cai.base.src.com.basetest.R;
import cai.base.src.com.basetest.enums.FragmentTypeEnum;
import cai.base.src.com.basetest.views.CustomRefreshFootView;
import cai.base.src.com.basetest.views.CustomRefreshHeadView;

/**
 * Created by Administrator on 2017/10/23.
 * 列表展示数据
 */


public abstract class BaseListFragment extends BasicsFragment implements OnLoadMoreListener, OnRefreshListener {
    /**
     * grid布局与瀑布流布局默认行数
     */
    private int mSpanCount = 1;
    /**
     * 默认为0单行布局
     */
    private int mListType = 0;
    /**
     * 排列方式默认垂直
     */
    private boolean mIsVertical = true;
    /**是否允许下拉刷新（默认不允许）*/
    private boolean isLoadMore = false;
    /** 是否允许上拉加载(默认允许)*/
    private boolean isRefresh = true;



    protected RecyclerView mRecyclerView;
    private SwipeToLoadLayout mSwipeToLoadLayout;
    protected ListAdapter  mAdapter;
    CustomRefreshHeadView headView;

    /**对应的Item的单机事件*/
    protected abstract void onItemClick(View view,RecyclerView.ViewHolder holder,int position);

    /**对应的Item的长按事件*/
    protected abstract void onItemLongClick(View view,RecyclerView.ViewHolder holder,int position);

//    /**上拉刷新*/
//    protected abstract void onRefresh();
//
//    /** 下拉加载*/
//    protected abstract void  onLoadMore();




    @Override
    protected void onListFragment(LayoutInflater inflater) {
        mView = inflater.inflate(R.layout.fragment_base_list,null);
        mSwipeToLoadLayout = mView.findViewById(R.id.swipe_to_loadlayout);
        mRecyclerView = mView.findViewById(R.id.swipe_target);
        CustomRefreshFootView footView = new CustomRefreshFootView(context);
        mSwipeToLoadLayout.setLoadMoreFooterView(footView);
headView =mView.findViewById(R.id.swipe_refresh_header);
//        mSwipeToLoadLayout.setRefreshHeaderView(headView);

        mAdapter = new ListAdapter();
        chooseListType(0,true);

        mSwipeToLoadLayout.setOnLoadMoreListener(this);
        mSwipeToLoadLayout.setOnRefreshListener(this);
    }


    @Override
    protected FragmentTypeEnum getFragmentType() {
        return FragmentTypeEnum.ListFragment;
    }

    @Override
    protected void onSpaceFragment(LayoutInflater inflater) {}

    @Override
    public void onLoadMore() {
        Log.i("test","测试成功:onLoadMore");

    }

    @Override
    public void onRefresh() {
        Log.i("test","测试成功:onRefresh");
        mSwipeToLoadLayout.removeView(headView);
        mSwipeToLoadLayout.setRefreshing(false);
    }


    /**适配器*/
    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>{

        @Override
        public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_test_text, parent, false);
            ListViewHolder holder = new ListViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ListViewHolder holder, int position) {

        }
        @Override
        public int getItemCount() {
            return 5;
        }

       class ListViewHolder extends RecyclerView.ViewHolder{

           public ListViewHolder(View itemView) {
               super(itemView);
           }
       }
    }






    /**
     * @param listType 选择布局种类
     */
    protected void chooseListType(int listType, boolean isVertical) {
        switch (listType) {
            case BaseListType.LINEAR_LAYOUT_MANAGER:
                //设置布局管理器
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

                linearLayoutManager.setOrientation(isVertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);

                mRecyclerView.setLayoutManager(linearLayoutManager);
                break;
            case BaseListType.GRID_LAYOUT_MANAGER:

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), mSpanCount);

                gridLayoutManager.setOrientation(isVertical ? GridLayoutManager.VERTICAL : GridLayoutManager.HORIZONTAL);

                mRecyclerView.setLayoutManager(gridLayoutManager);
                break;
            case BaseListType.STAGGERED_GRID_LAYOUT_MANAGER:
                //设置布局管理器
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager
                        (mSpanCount, isVertical ? StaggeredGridLayoutManager.VERTICAL : StaggeredGridLayoutManager.HORIZONTAL);

                mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
                break;
            default:
                //设置布局管理器
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

                layoutManager.setOrientation(isVertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);

                mRecyclerView.setLayoutManager(layoutManager);
                break;
        }
        mRecyclerView.setAdapter(mAdapter);
    }







    /**设置对应的RecyclerView的显示类别值*/
 private class BaseListType{
        private static final int LINEAR_LAYOUT_MANAGER = 0;
        private static final int GRID_LAYOUT_MANAGER = 1;
        private static  final int STAGGERED_GRID_LAYOUT_MANAGER = 2;
  }


}
