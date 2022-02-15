package com.mironenko.dounews.UI.newsListScreen;

import com.mironenko.dounews.UI.mvpBase.IMvpBasePresenter;
import com.mironenko.dounews.UI.mvpBase.IMvpBaseView;
import com.mironenko.dounews.model.api.Article;
<<<<<<< HEAD
import com.mironenko.dounews.model.api.ArticlesNewsList;
=======
>>>>>>> developer-rxJava

import java.util.List;

import io.reactivex.Observable;
<<<<<<< HEAD
import io.reactivex.Single;
=======
>>>>>>> developer-rxJava

public interface INewsListContract {

    interface IView extends IMvpBaseView {
<<<<<<< HEAD
        void subscribeNews(Single<ArticlesNewsList> dataSource);
    }

    interface IPresenter extends IMvpBasePresenter<IView> {
        void downloadNewsList();
        Observable<List<Article>> getAllDataList();
=======
        void updateAdapter();
    }

    interface IPresenter extends IMvpBasePresenter<IView> {
        int getNews();
        void downloadArticles();
        Observable<List<Article>> getAllBase(Class<Article> clazz);
        void refreshNews();
>>>>>>> developer-rxJava
    }
}
