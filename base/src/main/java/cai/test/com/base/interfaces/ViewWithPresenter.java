package cai.test.com.base.interfaces;

import cai.test.com.base.presenter.Presenter;

/**
 * Created by Administrator on 2017/11/27.
 */

public interface ViewWithPresenter<P extends Presenter> {
    PresenterFactory<P> getPresenterFactory();

    void setPresenterFactory(PresenterFactory<P> factory);

    P getPresenter();

}
