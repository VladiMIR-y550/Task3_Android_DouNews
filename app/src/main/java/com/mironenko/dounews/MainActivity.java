package com.mironenko.dounews;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mironenko.dounews.databinding.ActivityMainBinding;
import com.mironenko.dounews.UI.newsDetailedScreen.NewsDetailedFragment;
import com.mironenko.dounews.UI.newsListScreen.NewsListFragment;

public class MainActivity extends AppCompatActivity implements NewsListFragment.OnNewsSelectedListener {
    private final String KEY_URL_NEWS = "Url news";

    private ActivityMainBinding binding;
    private final NewsListFragment listFragment = NewsListFragment.newInstance();
    private final NewsDetailedFragment detailedFragment = NewsDetailedFragment.newInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (savedInstanceState == null) {
            showList();
        }
    }

    @Override
    public void newsSelected(String urlNews) {
        showDetailed(urlNews);
    }

    @Override
    protected void onDestroy() {
        binding = null;
        super.onDestroy();
    }

    private void showList() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, listFragment)
                .commit();
    }

    private void showDetailed(String urlNews) {
        Bundle args = new Bundle();
        args.putString(KEY_URL_NEWS, urlNews);
        detailedFragment.setArguments(args);

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, detailedFragment)
                .commit();
    }
}