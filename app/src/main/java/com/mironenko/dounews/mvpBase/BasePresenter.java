package com.mironenko.dounews.mvpBase;

public abstract class BasePresenter<M, V>{
    private V view;
    private M model;

    public V getView() {
        return view;
    }

    public M getModel() {
        return model;
    }

    public void setModel(M model) {
        this.model = model;
    }

    protected void bindView(V view) {
        this.view = view;
    }
    protected void unBindView() {
        view = null;
    }
}
