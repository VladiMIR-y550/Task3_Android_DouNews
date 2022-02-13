package com.mironenko.dounews.UI.newsListScreen;

import com.mironenko.dounews.UI.mvpBase.IMvpBasePresenter;
import com.mironenko.dounews.UI.mvpBase.IMvpBaseView;
import com.mironenko.dounews.model.api.Article;
import com.mironenko.dounews.model.api.ArticlesNewsList;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface INewsListContract {

    interface IView extends IMvpBaseView {
        void subscribeNews(Single<ArticlesNewsList> dataSource);
    }

    interface IPresenter extends IMvpBasePresenter<IView> {
        void downloadNewsList();
        Observable<List<Article>> getAllDataList();
    }
}
