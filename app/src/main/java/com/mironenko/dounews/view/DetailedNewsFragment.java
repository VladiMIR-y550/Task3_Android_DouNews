package com.mironenko.dounews.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mironenko.dounews.databinding.FragmentDetailedNewsBinding;
import com.mironenko.dounews.presenter.NewsDetailedPresenter;

public class DetailedNewsFragment extends Fragment implements IDetailFragment {

    private static final String KEY_LOAD_URL = "loadURL";
    private FragmentDetailedNewsBinding binding;
    private NewsDetailedPresenter detailedPresenter;
    private final String loadUrl;

    public DetailedNewsFragment(String loadUrl) {
        this.loadUrl = loadUrl;
    }

    public static DetailedNewsFragment newInstance(String loadUrl) {

        Bundle args = new Bundle();
        args.putString(KEY_LOAD_URL, loadUrl);
        /**
         * TODO put in Bundle
         */

        DetailedNewsFragment fragment = new DetailedNewsFragment(loadUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        binding = FragmentDetailedNewsBinding.inflate(inflater);

        detailedPresenter = new NewsDetailedPresenter(this, loadUrl);
        detailedPresenter.loadUrl();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showDetail(String url) {
        binding.webViewDetailed.loadUrl(url);
    }
}
