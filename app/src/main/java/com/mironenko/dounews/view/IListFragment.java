package com.mironenko.dounews.view;

public interface IListFragment extends ILoadingView {

    void uploadArticle(String url);

    void showNewsList();

}
