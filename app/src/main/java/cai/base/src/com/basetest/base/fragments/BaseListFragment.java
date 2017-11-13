package cai.base.src.com.basetest.base.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;

import cai.base.src.com.basetest.R;
import cai.base.src.com.basetest.base.HolderView;
import cai.base.src.com.basetest.interfaces.BaseAdapterInterfaces;
import cai.base.src.com.basetest.interfaces.BaseAdapterOnClickInterfaces;
import cai.base.src.com.basetest.interfaces.BaseViewInterrfaces;

/**
 * Created by Administrator on 2017/10/23.
 * 列表展示数据
 */
public abstract class BaseListFragment<T> extends BasicsFragment implements XRecyclerView.LoadingListener ,BaseAdapterInterfaces,BaseAdapterOnClickInterfaces ,BaseViewInterrfaces {
    /**
     * grid布局与瀑布流布局默认行数
     */
    private int mSpanCount = 1;
    /**
     * 默认为0单行布局
     */
    private int mListType = 0;
    /**上拉下拉加载布局控件*/
    protected XRecyclerView mRecyclerView;
    /**适配器*/
    private BaseAdapter  mAdapter;
    /**数据列表*/
    private ArrayList<T> list;


    /**
     * 排列方式默认垂直
     */
    private boolean mIsVertical = true;
    /**是否允许下拉刷新（默认不允许）*/
    private boolean isLoadMore = false;
    /** 是否允许上拉加载(默认允许)*/
    private boolean isRefresh = true;





    /**"上拉刷新数据*/
    protected abstract void onRefreshData();

    /**下拉加载数据*/
    protected abstract void onLoadMoreData();

    /**显示时不需要该布局*/
    @Override
    public int getContentViewId() {
        return R.layout.fragment_base_list;
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        mView = inflater.inflate(getContentViewId(),null);

        mRecyclerView = mView.findViewById(R.id.recyclerview);
        list = new ArrayList<>();
        mAdapter = new BaseAdapter();
        chooseListType(mListType,mIsVertical);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        setLoadMoreAndRefresh(isLoadMore,isRefresh);
        mRecyclerView.setLoadingListener(this);
        init();

    }


    /**
     * 添加数据
     * @param data
     *      需要添加的数据
     * @param isClear
     *       是否需要清空之前的数据
     */
    protected void addData(ArrayList<T>  data,boolean isClear){
       if (isClear){
           list.clear();
       }
       list.addAll(data);
    }




    /**设置是否上拉或者下拉*/
    protected void setLoadMoreAndRefresh(boolean isLoadMore,boolean isRefresh){
        mRecyclerView.setLoadingMoreEnabled(isLoadMore);
        mRecyclerView.setPullRefreshEnabled(isRefresh);
    }


    @Override
    public void onRefresh() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onRefreshData();
                mRecyclerView.refreshComplete();
                mAdapter.notifyDataSetChanged();
            }
        },1000);



    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onLoadMoreData();
                mRecyclerView.loadMoreComplete();
                mAdapter.notifyDataSetChanged();

            }
        },1000);

    }




    /**适配器*/
    public class BaseAdapter extends RecyclerView.Adapter<HolderView>{

        @Override
        public HolderView onCreateViewHolder(ViewGroup parent, int viewType) {
            HolderView holder =  HolderView.get(context,parent,getItemLayoutId(viewType));
            return holder;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public void onBindViewHolder(final HolderView holder, final int position) {
            bindData(holder,position);
            //---------------------------------------------------------------
            /**列表的单机事件*/
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick(v,holder,position);
                }
            });

            /**列表的长按事件*/
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return onItemLongClick(v,holder,position);
                }
            });

        }
        @Override
        public int getItemCount() {
            if (list != null) {
                return list.size();
            }else{
                LogUtil.d("BaseListFragment：列表为空");
                return 0;
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
 protected class BaseListType{
        protected static final int LINEAR_LAYOUT_MANAGER = 0;
        protected static final int GRID_LAYOUT_MANAGER = 1;
        protected static  final int STAGGERED_GRID_LAYOUT_MANAGER = 2;
  }


}
