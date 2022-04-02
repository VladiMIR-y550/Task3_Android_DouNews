package com.mironenko.dounews.model.di.component;

import com.mironenko.dounews.UI.newsDetailedScreen.NewsDetailedFragment;
import com.mironenko.dounews.UI.newsListScreen.NewsListFragment;
import com.mironenko.dounews.model.di.anotation.ListScope;
import com.mironenko.dounews.model.di.module.FragmentModule;
import com.mironenko.dounews.model.di.module.PresenterModule;

import dagger.Subcomponent;

@ListScope
@Subcomponent (modules = {FragmentModule.class, PresenterModule.class})
public interface FragmentComponent {

    @Subcomponent.Builder
    interface Builder {
        FragmentComponent build();
    }

    void injectNewsListFragment(NewsListFragment newsListFragment);
    void injectNewsDetailedFragment(NewsDetailedFragment detailedFragment);
}
