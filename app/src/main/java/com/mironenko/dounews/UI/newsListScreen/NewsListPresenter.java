package com.mironenko.dounews.UI.newsListScreen;

import android.util.Log;

import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.mironenko.dounews.UI.mvpBase.BasePresenter;
import com.mironenko.dounews.model.remote.Article;
import com.mironenko.dounews.repositories.NewsPageSource;

import io.reactivex.rxjava3.core.Flowable;

public class NewsListPresenter extends BasePresenter<Article, INewsListContract.IView> implements INewsListContract.IPresenter {
    private final int PAGE_SIZE = 5;

    @Override
    public void downloadNewsList() {
        view.showLoading();
        init();
        view.hideLoading();
    }

    @Override
    public void articleSelected(String urlArticle) {
        view.showDetailedNews(urlArticle);
    }

    public void init() {
        NewsPageSource articlePagingSource = new NewsPageSource();
        Pager<String, Article> pager = new Pager(
                new PagingConfig(PAGE_SIZE,
                        10,
                        false,
                        10,
                        30),
                () -> articlePagingSource);

        Flowable<PagingData<Article>> articlePagingDataFlowable = PagingRx.getFlowable(pager);
        view.subscribeNews(articlePagingDataFlowable);

        Log.d("Paging", "Presenter Flowable - " + articlePagingDataFlowable);
    }
}
