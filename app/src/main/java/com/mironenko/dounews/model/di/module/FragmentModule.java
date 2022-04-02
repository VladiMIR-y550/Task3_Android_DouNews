package com.mironenko.dounews.model.di.module;

import com.mironenko.dounews.UI.newsListScreen.adapter.PagingAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    @Provides
    PagingAdapter providePagingAdapter() {
        return new PagingAdapter();
    }
}
