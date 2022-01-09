package com.mironenko.dounews.UI.mvpBase;

public abstract class BasePresenter<M, V extends IMvpBaseView> implements IMvpBasePresenter<V> {
    protected V view;
    protected M model;

    public void setModel(M model) {
        this.model = model;
    }

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
