package com.mironenko.dounews.UI.newsListScreen;

import android.util.Log;

import com.mironenko.dounews.DouNewsApp;
import com.mironenko.dounews.UI.mvpBase.BasePresenter;
import com.mironenko.dounews.model.api.Article;
import com.mironenko.dounews.model.api.ArticlesNewsList;
import com.mironenko.dounews.model.local.INewsDB;
import com.mironenko.dounews.model.local.NewsDB;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsListPresenter extends BasePresenter<Article, INewsListContract.IView> implements INewsListContract.IPresenter {
    private final int PAGE_SIZE = 20;
    private INewsDB baseNews = NewsDB.getInstance();
    private List<Article> data = new ArrayList<>();
    private Observable<List<Article>> articleSource;
    private static NewsListPresenter presenter;

    public static synchronized NewsListPresenter getInstance() {
        if (presenter == null) {
            presenter = new NewsListPresenter();
        }
        return presenter;
    }

    @Override
    public void downloadNewsList() {
        view.subscribeNews(dataSource(data.size()));
    }

    private Single<ArticlesNewsList> dataSource(int page) {
        return DouNewsApp.idouApi.getNextArticlesIntObservable(PAGE_SIZE, page)
                .subscribeOn(Schedulers.io())
                .map(this::getSaveListIntoDatabase)
                .doOnSuccess(articlesNewsList -> {
                    data.addAll(articlesNewsList.getResults());
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    private ArticlesNewsList getSaveListIntoDatabase(ArticlesNewsList articlesNewsList) {
        baseNews.saveArticlesFromResponseBody(articlesNewsList);
        return articlesNewsList;
    }

    public Observable<List<Article>> getAllDataList() {
        articleSource = Observable.fromCallable(new Callable<List<Article>>() {
            @Override
            public List<Article> call() throws Exception {
                return checkThread(data);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return articleSource;
    }

    private List<Article> checkThread(List<Article> list) {
        Log.d("RxThread", "" + Thread.currentThread().getName());
        return list;
    }
}
