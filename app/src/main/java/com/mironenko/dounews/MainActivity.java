package com.mironenko.dounews;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mironenko.dounews.UI.newsListScreen.NewsListFragment;
import com.mironenko.dounews.databinding.ActivityMainBinding;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        DouNewsApplicationComponent douNewsApplicationComponent = DaggerDouNewsApplicationComponent.builder()
//                .contextModule(new ContextModule(this))
//                .build();
//        douNewsApplicationComponent.inject(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
