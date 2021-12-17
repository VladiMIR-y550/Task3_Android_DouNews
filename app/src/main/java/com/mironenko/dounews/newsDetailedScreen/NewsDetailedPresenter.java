package com.mironenko.dounews.newsDetailedScreen;

import com.mironenko.dounews.mvpBase.BasePresenter;

public class NewsDetailedPresenter extends BasePresenter<String, INewsDetailedContract.IView> implements INewsDetailedContract.IPresenter {

    @Override
    public void attachView(INewsDetailedContract.IView view) {
        bindView(view);
    }

    @Override
    public void detachView() {
        unBindView();
    }

    @Override
    public void urlReceived(String url) {
        setModel(url);
    }

    @Override
    public void downloadNewsDetailed() {
        getView().showLoading();

        if (getModel() != null) {
            getView().showNewsDetailed(getModel());
            getView().hideLoading();

        } else {
            getView().hideLoading();
            getView().showError();
        }
    }
}
