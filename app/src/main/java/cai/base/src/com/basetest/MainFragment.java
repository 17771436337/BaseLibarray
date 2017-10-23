package cai.base.src.com.basetest;

import android.util.Log;

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


}
