package cai.base.src.com.basetest.interfaces;

import android.view.View;

import cai.base.src.com.basetest.base.HolderView;

/**
 * Created by Administrator on 2017/11/13.
 * 适配器的点击事件
 */

public interface BaseAdapterOnClickInterfaces {
    /**对应的Item的单机事件*/
    public void onItemClick(View view, HolderView holder, int position);

    /**对应的Item的长按事件*/
    public boolean onItemLongClick(View view,HolderView holder,int position);

}
