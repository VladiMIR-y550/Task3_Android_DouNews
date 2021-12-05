package com.mironenko.dounews.view.news_list;

import com.mironenko.dounews.view.ILoadingView;

public interface IListFragment extends ILoadingView {

    void uploadArticle(String url);

    void showNewsList();

}
