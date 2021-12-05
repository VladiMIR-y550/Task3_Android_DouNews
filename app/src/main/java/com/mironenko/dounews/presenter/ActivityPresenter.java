package com.mironenko.dounews.presenter;

import com.mironenko.dounews.view.IActivityShowList;

public class ActivityPresenter {

    private final IActivityShowList showList;

    public ActivityPresenter(IActivityShowList showList) {
        this.showList = showList;
    }

    public void start() {
        showList.showListNews();
    }
}
