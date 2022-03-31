package com.mironenko.dounews;

import android.app.Application;

import com.mironenko.dounews.model.di.component.AppComponent;
import com.mironenko.dounews.model.di.component.DaggerAppComponent;
import com.mironenko.dounews.model.di.component.DataComponent;
import com.mironenko.dounews.model.di.component.FragmentComponent;
import com.mironenko.dounews.model.di.module.AppModule;
import com.mironenko.dounews.model.di.module.NetworkModule;

import io.realm.Realm;

public class DouNewsApp extends Application {

    private static final String ROOT_URL = "https://api.dou.ua/";
    private final int PAGE_SIZE = 20;
    private AppComponent appComponent;
    private DataComponent dataComponent;
    private FragmentComponent fragmentComponent;

    protected static DouNewsApp instance;

    public static DouNewsApp get() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        Realm.init(instance);

        appComponent = DaggerAppComponent.builder()
                .application(this)
                .appModule(new AppModule(getApplicationContext()))
                .networkModule(new NetworkModule(ROOT_URL))
                .build();
        dataComponent = appComponent.dataComponent().build();
        fragmentComponent = appComponent.listFragmentComponent().build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public DataComponent getDataComponent() {
        return dataComponent;
    }

    public FragmentComponent getFragmentComponent() {
        return fragmentComponent;
    }

    public void clearFragmentComponent() {
        fragmentComponent = null;
    }
}
