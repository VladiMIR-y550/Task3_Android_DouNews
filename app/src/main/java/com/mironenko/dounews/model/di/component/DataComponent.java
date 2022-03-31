package com.mironenko.dounews.model.di.component;

import com.mironenko.dounews.UI.newsListScreen.NewsListPresenter;
import com.mironenko.dounews.model.DataStore;
import com.mironenko.dounews.model.di.anotation.DataScope;
import com.mironenko.dounews.model.di.module.DataStoreModule;

import dagger.Subcomponent;

@DataScope
@Subcomponent (modules = {DataStoreModule.class})
public interface DataComponent {

    @Subcomponent.Builder
    interface Builder {
        DataComponent build();
    }

    void injectListPresenter(NewsListPresenter presenter);
    void injectDataStore(DataStore dataStore);
}
