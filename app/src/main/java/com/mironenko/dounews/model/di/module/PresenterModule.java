package com.mironenko.dounews.model.di.module;

import com.mironenko.dounews.UI.newsDetailedScreen.INewsDetailedContract;
import com.mironenko.dounews.UI.newsDetailedScreen.NewsDetailedPresenter;
import com.mironenko.dounews.UI.newsListScreen.INewsListContract;
import com.mironenko.dounews.UI.newsListScreen.NewsListPresenter;
import com.mironenko.dounews.model.di.anotation.ListScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @ListScope
    @Provides
    INewsListContract.IPresenter provideNewsListPresenter() {
        return NewsListPresenter.getInstance();
    }

    @ListScope
    @Provides
    INewsDetailedContract.IPresenter provideNewsDetailedPresenter() {
        return NewsDetailedPresenter.getInstance();
    }
}
