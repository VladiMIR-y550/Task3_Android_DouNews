package com.mironenko.dounews.presenter;

import com.mironenko.dounews.view.IDetailFragment;

public class NewsDetailedPresenter {

    private final IDetailFragment iDetailFragment;
    private final String articleUrl;

    public NewsDetailedPresenter(IDetailFragment iDetailFragment, String articleUrl) {
        this.iDetailFragment = iDetailFragment;
        this.articleUrl = articleUrl;
    }

    public void loadUrl() {
        iDetailFragment.showDetail(articleUrl);
    }

}
