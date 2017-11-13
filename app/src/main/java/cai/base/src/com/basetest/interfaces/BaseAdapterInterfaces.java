package cai.base.src.com.basetest.interfaces;

import cai.base.src.com.basetest.base.HolderView;

/**
 * Created by Administrator on 2017/11/13.
 * 适配器使用的对应的接口
 */

public interface BaseAdapterInterfaces{

    /**适配器绑定数据*/
    public void bindData(HolderView holder, int position);

    /**
     * 适配器根据不同的type设置不同的参数
     * @param viewType
     *      布局的下标
     * @return
     *      对应的布局文件
     */
    public int getItemLayoutId(int viewType);

}