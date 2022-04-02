package com.mironenko.dounews.UI.newsListScreen;

import com.mironenko.dounews.UI.mvpBase.IMvpBasePresenter;
import com.mironenko.dounews.UI.mvpBase.IMvpBaseView;
import com.mironenko.dounews.model.local.Article;

import java.util.List;

import io.reactivex.Observable;


public interface INewsListContract {

    interface IView extends IMvpBaseView {
        void updateAdapter();
    }

    interface IPresenter extends IMvpBasePresenter<IView> {
        int getBaseSize();
        Observable<List<Article>> getNews();
        void refreshArticles();
        void downloadArticles();
    }
}
