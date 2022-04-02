package com.mironenko.dounews.UI.newsDetailedScreen;

import com.mironenko.dounews.UI.mvpBase.IMvpBasePresenter;
import com.mironenko.dounews.UI.mvpBase.IMvpBaseView;

public interface INewsDetailedContract {

    interface IView extends IMvpBaseView {
        void showNewsDetailed(String urlNewsDetailed);
    }

    interface IPresenter extends IMvpBasePresenter<IView> {
        void urlReceived(String url);
        void downloadNewsDetailed();
    }
}
