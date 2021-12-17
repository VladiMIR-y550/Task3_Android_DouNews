package com.mironenko.dounews.newsListScreen;

import com.mironenko.dounews.model.remote.Article;
import com.mironenko.dounews.mvpBase.IMvpBaseView;

import java.util.List;

public interface INewsListContract {

    interface IView extends IMvpBaseView {
        void showNewsList(List<Article> newsList);
        void showDetailedNews(String selectedNews);
    }

    interface IPresenter {
        void attachView(IView view);
        void detachView();
        void downloadNewsList();
        void articleSelected(int position);
    }
}
