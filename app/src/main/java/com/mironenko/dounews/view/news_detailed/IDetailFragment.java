package com.mironenko.dounews.view.news_detailed;

import com.mironenko.dounews.view.ILoadingView;

public interface IDetailFragment extends ILoadingView {

    void showDetail(String url);
}
