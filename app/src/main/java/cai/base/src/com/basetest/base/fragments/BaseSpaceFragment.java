package cai.base.src.com.basetest.base.fragments;

import android.view.LayoutInflater;

import cai.base.src.com.basetest.R;
import cai.base.src.com.basetest.enums.FragmentTypeEnum;

import static cai.base.src.com.basetest.enums.FragmentTypeEnum.*;

/**
 * Created by Administrator on 2017/10/24.
 */

public abstract class BaseSpaceFragment extends BasicsFragment {


    @Override
    protected void init() {

    }

    @Override
    protected void onListFragment(LayoutInflater inflater) {

    }

    @Override
    protected void onSpaceFragment(LayoutInflater inflater) {
        mView = inflater.inflate(contentViewId, null);
    }

    @Override
    protected FragmentTypeEnum getFragmentType() {
        return SpaceFragment;
    }

}