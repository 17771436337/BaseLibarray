package cai.test.com.base.interfaces;

import android.view.View;

import cai.test.com.base.view.adapter.HolderView;


/**
 * Created by Administrator on 2017/11/13.
 * 适配器的点击事件
 */

public interface BaseAdapterOnClickInterfaces {
    /**对应的Item的单机事件*/
    void onItemClick(View view, HolderView holder, int position);

    /**对应的Item的长按事件*/
    boolean onItemLongClick(View view, HolderView holder, int position);

}
