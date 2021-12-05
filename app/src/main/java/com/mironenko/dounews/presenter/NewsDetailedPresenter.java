package com.mironenko.dounews.presenter;

import com.mironenko.dounews.view.IDetailFragment;

public class NewsDetailedPresenter {

    private final IDetailFragment iDetailFragment;

    public NewsDetailedPresenter(IDetailFragment iDetailFragment) {
        this.iDetailFragment = iDetailFragment;

    }

    public void loadUrl(String articleUrl) {
        iDetailFragment.showDetail(articleUrl);
    }

}
