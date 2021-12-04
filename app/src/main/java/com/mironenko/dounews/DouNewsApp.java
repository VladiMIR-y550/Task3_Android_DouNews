package com.mironenko.dounews;

import android.app.Application;

import com.mironenko.dounews.model.remote.IdouApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DouNewsApp extends Application {

    public IdouApi idouApi;

    @Override
    public void onCreate() {
        super.onCreate();

        configureRetrofit();
    }

    private void configureRetrofit() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.dou.ua")
                .baseUrl("https://api.dou.ua/articles/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())             //перевращает Gson в объект
                .build();

        idouApi = retrofit.create(IdouApi.class);
    }
}
