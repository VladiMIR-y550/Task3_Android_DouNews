package com.mironenko.dounews.view.news_detailed;

import com.mironenko.dounews.view.news_detailed.IDetailFragment;

public class NewsDetailedPresenter {

    private final IDetailFragment iDetailFragment;

    public NewsDetailedPresenter(IDetailFragment iDetailFragment) {
        this.iDetailFragment = iDetailFragment;

    }

    public void loadUrl(String articleUrl) {
        iDetailFragment.showDetail(articleUrl);
    }

}
