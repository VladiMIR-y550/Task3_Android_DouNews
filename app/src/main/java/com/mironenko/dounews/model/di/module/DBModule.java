package com.mironenko.dounews.model.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

@Module
public class DBModule {

    @Provides
    @Singleton
    public Realm provideRealm (RealmConfiguration realmConfiguration) {
        Realm.setDefaultConfiguration(realmConfiguration);
        return Realm.getDefaultInstance();
    }

    @Provides
    @Singleton
    public RealmConfiguration provideRealmConfiguration() {
        return new RealmConfiguration.Builder()
                .name("newsDB.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
    }
}
