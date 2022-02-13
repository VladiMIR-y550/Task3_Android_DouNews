package com.mironenko.dounews;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mironenko.dounews.UI.newsListScreen.NewsListFragment;
import com.mironenko.dounews.databinding.ActivityMainBinding;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private Realm realm;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Realm.init(this);
        if (savedInstanceState == null) {
            showList();
        }
    }

    private void showList() {
        NewsListFragment newsListFragment = NewsListFragment.newInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, newsListFragment)
                .commit();
    }

    @Override
    protected void onDestroy() {
        binding = null;
        super.onDestroy();
    }
}
