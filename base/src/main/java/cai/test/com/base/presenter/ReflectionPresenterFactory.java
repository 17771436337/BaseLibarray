package cai.test.com.base.presenter;

import android.support.annotation.Nullable;

import cai.test.com.base.annotation.RequirePresenter;
import cai.test.com.base.interfaces.PresenterFactory;

/**
 * Created by Administrator on 2017/11/27.
 */

public class ReflectionPresenterFactory<P extends Presenter> implements PresenterFactory<P> {
    private Class<P> presenterClass;

    @Nullable
    public static <P extends Presenter> ReflectionPresenterFactory<P> fromViewClass(Class<?> viewClass) {
        RequirePresenter annotation = viewClass.getAnnotation(RequirePresenter.class);
        //noinspection unchecked
        Class<P> presenterClass = annotation == null ? null : (Class<P>)annotation.value();
        return presenterClass == null ? null : new ReflectionPresenterFactory<>(presenterClass);
    }

    public ReflectionPresenterFactory(Class<P> presenterClass) {
        this.presenterClass = presenterClass;
    }

    @Override
    public P createPresenter() {
        try {
            return presenterClass.newInstance();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
