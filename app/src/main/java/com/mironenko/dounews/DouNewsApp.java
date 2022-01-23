package com.mironenko.dounews;

import android.app.Application;

import com.mironenko.dounews.model.IDouApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DouNewsApp extends Application {

    private static final String ROOT_URL = "https://api.dou.ua/";
    public static IDouApi idouApi;

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
                .baseUrl(ROOT_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        idouApi = retrofit.create(IDouApi.class);
    }

}
