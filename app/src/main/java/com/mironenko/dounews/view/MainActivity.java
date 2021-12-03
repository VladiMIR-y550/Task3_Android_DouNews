package com.mironenko.dounews.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.mironenko.dounews.R;
import com.mironenko.dounews.databinding.ActivityMainBinding;
import com.mironenko.dounews.presenter.ActivityPresenter;

public class MainActivity extends AppCompatActivity implements IActivityShowList {

    private ActivityMainBinding binding;
    private ActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        presenter = new ActivityPresenter(this);
        presenter.start();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showListNews() {
        NewsListFragment newsListFragment = (NewsListFragment) NewsListFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, newsListFragment)
                .commit();
    }
}
