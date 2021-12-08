package com.mironenko.dounews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.mironenko.dounews.databinding.ActivityMainBinding;
import com.mironenko.dounews.presenter.ActivityPresenter;
import com.mironenko.dounews.view.IActivityShowList;
import com.mironenko.dounews.view.IScreenManager;
import com.mironenko.dounews.view.news_list.NewsListFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ActivityPresenter presenter;
    private IScreenManager screenManager = new ScreenManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        NewsListFragment newsListFragment = (NewsListFragment) NewsListFragment.newInstance();
        screenManager.changeScreen(newsListFragment);


//        if (savedInstanceState == null) {
//            presenter = new ActivityPresenter(this);
//            presenter.start();
//        }
    }

//    @Override
//    public void showListNews() {
//        NewsListFragment newsListFragment = (NewsListFragment) NewsListFragment.newInstance();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.fragment_container, newsListFragment)
//                .commit();
//    }

    @Override
    protected void onDestroy() {
        binding = null;
        presenter = null;
        super.onDestroy();
    }
}
