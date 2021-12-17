package com.mironenko.dounews.newsDetailedScreen;

import com.mironenko.dounews.mvpBase.IMvpBaseView;

public interface INewsDetailedContract {

    interface IView extends IMvpBaseView {
        void showNewsDetailed(String urlNewsDetailed);
    }

    interface IPresenter {
        void attachView(IView view);
        void detachView();
        void urlReceived(String url);
        void downloadNewsDetailed();
    }
}
