package cai.base.src.com.basetest;

import android.util.Log;

import cai.base.src.com.basetest.annotation.FragmentInject;
import cai.base.src.com.basetest.base.BaseFragment;

/**
 * Created by Administrator on 2017/10/23.
 */
@FragmentInject(contentViewId = R.layout.fragment_main)
public class MainFragment extends BaseFragment{
    @Override
    protected void init() {
        Log.i("test","测试");
    }
}
