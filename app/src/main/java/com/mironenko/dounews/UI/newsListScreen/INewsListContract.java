package com.mironenko.dounews.UI.newsListScreen;

import androidx.paging.PagingData;

import com.mironenko.dounews.model.remote.Article;
import com.mironenko.dounews.UI.mvpBase.IMvpBasePresenter;
import com.mironenko.dounews.UI.mvpBase.IMvpBaseView;

import io.reactivex.rxjava3.core.Flowable;

public interface INewsListContract {

    interface IView extends IMvpBaseView {
        void subscribeNews(Flowable<PagingData<Article>> articlePagingDataFlowable);
    }

    interface IPresenter extends IMvpBasePresenter<IView> {
        void downloadNewsList();
    }
}
