package com.mironenko.dounews.UI.mvpBase;

public interface IMvpBasePresenter<V extends IMvpBaseView> {

    void attachView(V view);

    void detachView();
}
