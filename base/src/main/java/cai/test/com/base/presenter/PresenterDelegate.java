package cai.test.com.base.presenter;

import android.support.annotation.Nullable;

import cai.test.com.base.interfaces.PresenterFactory;
import cai.test.com.base.presenter.Presenter;

/**
 * Created by Administrator on 2017/11/27.
 */

public class PresenterDelegate<P extends Presenter> {

    private PresenterFactory<P> presenterFactory;
    private P presenter;

    public PresenterDelegate(@Nullable PresenterFactory<P> presenterFactory) {
        this.presenterFactory = presenterFactory;
    }

    @Nullable
    public PresenterFactory<P> getPresenterFactory() {
        return presenterFactory;
    }

    public void setPresenterFactory(@Nullable PresenterFactory<P> presenterFactory) {
        this.presenterFactory = presenterFactory;
    }

    public P getPresenter() {
        if (presenterFactory != null) {
            if (presenter == null) {
                presenter = presenterFactory.createPresenter();
            }
        }
        return presenter;
    }

    public void onCreate(Object view){
        getPresenter();
        if(presenter != null){
            presenter.setView(view);
        }
    }


}
