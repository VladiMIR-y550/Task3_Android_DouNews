package com.mironenko.dounews.UI.newsListScreen;

import com.mironenko.dounews.UI.mvpBase.IMvpBasePresenter;
import com.mironenko.dounews.UI.mvpBase.IMvpBaseView;
import com.mironenko.dounews.model.api.Article;

import java.util.List;

import io.reactivex.Observable;

public interface INewsListContract {

    interface IView extends IMvpBaseView {
        void updateAdapter();
    }

    interface IPresenter extends IMvpBasePresenter<IView> {
        int getNews();
        void downloadArticles();
        Observable<List<Article>> getAllBase(Class<Article> clazz);
        void refreshNews();
    }
}
