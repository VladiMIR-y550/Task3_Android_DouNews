package com.mironenko.dounews.view.news_list;

import com.mironenko.dounews.view.ILoadingView;

public interface INewsListContract {

    interface INewsListView extends ILoadingView {
        void uploadArticle(String url);

        void showNewsList();
    }
    interface INewsListPresenter {
       void fetchNewsList();

        void chooseNews(int position);
    }
}
