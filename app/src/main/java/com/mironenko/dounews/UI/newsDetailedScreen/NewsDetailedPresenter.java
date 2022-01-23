package com.mironenko.dounews.UI.newsDetailedScreen;

import com.mironenko.dounews.UI.mvpBase.BasePresenter;

public class NewsDetailedPresenter extends BasePresenter<String, INewsDetailedContract.IView> implements INewsDetailedContract.IPresenter {

    @Override
    public void urlReceived(String url) {
        setModel(url);
    }

    @Override
    public void downloadNewsDetailed() {
        view.showLoading(true);

        if (view != null) {
            view.showNewsDetailed(model);
            view.showLoading(false);

        } else {
            view.showLoading(false);
            view.showError();
        }
    }
}
