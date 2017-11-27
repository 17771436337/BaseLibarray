package cai.test.com.base.annotation;

import cai.test.com.base.presenter.Presenter;

/**
 * Created by Administrator on 2017/11/27.
 */

public @interface RequirePresenter {
    Class<? extends Presenter> value();
}
