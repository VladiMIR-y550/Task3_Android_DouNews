package com.mironenko.dounews.view.news_detailed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mironenko.dounews.databinding.FragmentDetailedNewsBinding;

public class DetailedNewsFragment extends Fragment implements IDetailFragment {

    private static final String KEY_LOAD_URL = "loadURL";
    private FragmentDetailedNewsBinding binding;

    public static DetailedNewsFragment newInstance(String loadUrl) {

        Bundle args = new Bundle();
        args.putString(KEY_LOAD_URL, loadUrl);
        DetailedNewsFragment fragment = new DetailedNewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        binding = FragmentDetailedNewsBinding.inflate(inflater);

        String loadUrl = requireArguments().getString(KEY_LOAD_URL);
        NewsDetailedPresenter detailedPresenter = new NewsDetailedPresenter(this);
        detailedPresenter.loadUrl(loadUrl);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        binding = null;
        super.onDestroy();
    }

    @Override
    public void showLoading() {
        binding.progressDetailed.progressLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.progressDetailed.progressLayout.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), "Sorry something went wrong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDetail(String url) {
        binding.webViewDetailed.loadUrl(url);
    }
}
