package cai.base.src.com.basetest.base;

import android.view.LayoutInflater;

import cai.base.src.com.basetest.R;
import cai.base.src.com.basetest.annotation.FragmentInject;
import cai.base.src.com.basetest.enums.FragmentTypeEnum;

/**
 * Created by Administrator on 2017/10/23.
 */

@FragmentInject(fragmentType = FragmentTypeEnum.ListFragment)
public abstract class BaseListFragment extends BaseFragment {

    @Override
    protected void setListFragment(LayoutInflater inflater) {
        mView = inflater.inflate(R.layout.fragment_base_list,null);
        fragmentType = FragmentTypeEnum.ListFragment;
        isFragmentType(inflater);

    }



    /**适配器*/
    public class Adapter{

    }


}
