package com.mironenko.dounews.UI.newsListScreen;

import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.mironenko.dounews.UI.mvpBase.BasePresenter;
import com.mironenko.dounews.model.remote.Article;
import com.mironenko.dounews.repositories.NewsPageSource;

import io.reactivex.rxjava3.core.Flowable;

public class NewsListPresenter extends BasePresenter<Article, INewsListContract.IView> implements INewsListContract.IPresenter {
    private final int LIMIT_PAGE = 20;

    @Override
    public void downloadNewsList() {
        init();
    }

    private void init() {
        NewsPageSource pageSource = new NewsPageSource();
        Pager<String, Article> pager = new Pager(
                new PagingConfig(LIMIT_PAGE,
                        20,
                        false,
                        20,
                        80),
                () -> pageSource);


        Flowable<PagingData<Article>> articlePagingDataFlowable = PagingRx.getFlowable(pager);
        view.subscribeNews(articlePagingDataFlowable);
        view.showLoading(false);
    }
}
