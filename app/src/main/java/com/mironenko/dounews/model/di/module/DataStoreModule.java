package com.mironenko.dounews.model.di.module;

import com.mironenko.dounews.model.DataStore;
import com.mironenko.dounews.model.IDataStore;
import com.mironenko.dounews.model.local.ILocalDataBase;
import com.mironenko.dounews.model.local.LocalDataBase;
import com.mironenko.dounews.model.remote.IResponseApi;
import com.mironenko.dounews.model.remote.ResponseApi;

import dagger.Module;
import dagger.Provides;

@Module
public class DataStoreModule {

    @Provides
    IDataStore provideDataStoreImpl() {
        return new DataStore();
    }

    @Provides
    ILocalDataBase provideLocalDataBaseImpl() {
        return new LocalDataBase();
    }

    @Provides
    IResponseApi provideResponseApiImpl() {
        return new ResponseApi();
    }
}
