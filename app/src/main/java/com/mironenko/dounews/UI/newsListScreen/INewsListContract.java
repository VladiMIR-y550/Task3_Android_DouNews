package com.mironenko.dounews.UI.newsListScreen;

import com.mironenko.dounews.UI.mvpBase.IMvpBasePresenter;
import com.mironenko.dounews.UI.mvpBase.IMvpBaseView;
import com.mironenko.dounews.UI.newsListScreen.adapter.NewsViewHolder;
import com.mironenko.dounews.model.remote.Article;
import com.mironenko.dounews.model.remote.ArticlesNewsList;

import io.reactivex.Single;

public interface INewsListContract {

    interface IView extends IMvpBaseView {
        void subscribeNews(Single<ArticlesNewsList> dataSource);
        void onArticleSelected(String urlArticle);
    }

    interface IPresenter extends IMvpBasePresenter<IView> {
        void downloadNewsList(int page);
        void onBindViewHolder(NewsViewHolder holder, int position);
        void onItemClicked(String urlArticle);
        int getPageNum();
        int getPAGE_SIZE();
    }

    interface ItemView{
        void bindItem(Article item);
    }
}
