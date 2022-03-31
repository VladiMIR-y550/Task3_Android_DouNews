package com.mironenko.dounews.model.di.component;

import android.app.Application;

import com.mironenko.dounews.model.di.module.AppModule;
import com.mironenko.dounews.model.di.module.DBModule;
import com.mironenko.dounews.model.di.module.NetworkModule;
import com.mironenko.dounews.model.local.LocalDataBase;
import com.mironenko.dounews.model.remote.ResponseApi;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component (modules = {AppModule.class, NetworkModule.class, DBModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        Builder appModule(AppModule appModule);
        Builder networkModule(NetworkModule networkModule);
        AppComponent build();
    }

    DataComponent.Builder dataComponent();
    FragmentComponent.Builder listFragmentComponent();

    void injectLocalDataBase(LocalDataBase localDataBase);
    void injectResponseApi(ResponseApi responseApi);
}
