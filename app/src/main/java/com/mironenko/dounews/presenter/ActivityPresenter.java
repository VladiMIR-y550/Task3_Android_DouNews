package com.mironenko.dounews.presenter;

import com.mironenko.dounews.view.IActivityShowList;

public class ActivityPresenter {
    private final IActivityShowList showList;

    /**
     * приводим activity к интерфейсу, можно передевать разные Activity и если она имплементирует интерфейс IShowList
     * тогда вызовяться методы, если нет то ничего не произойдёт
     * но в Presenter-е ничего не должно быть от Android SDK
     * @param showList
     */
    public ActivityPresenter(IActivityShowList showList) {
        this.showList = showList;
    }

    public void start() {
        showList.showListNews();
    }
}
