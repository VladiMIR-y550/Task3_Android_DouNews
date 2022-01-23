package com.mironenko.dounews.UI.newsDetailedScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mironenko.dounews.InternetConnection;
import com.mironenko.dounews.databinding.FragmentDetailedNewsBinding;

public class NewsDetailedFragment extends Fragment implements INewsDetailedContract.IView {
    private final String KEY_URL_NEWS = "Url news";

    private FragmentDetailedNewsBinding binding;
    private final INewsDetailedContract.IPresenter detailedPresenter = new NewsDetailedPresenter();

    public static NewsDetailedFragment newInstance() {
        Bundle args = new Bundle();
        NewsDetailedFragment fragment = new NewsDetailedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailedNewsBinding.inflate(inflater);

        detailedPresenter.attachView(this);

        if (getArguments() != null) {
            String urlNews = getArguments().getString(KEY_URL_NEWS);
            detailedPresenter.urlReceived(urlNews);
        }

        if (InternetConnection.checkConnection(requireContext())) {
            detailedPresenter.downloadNewsDetailed();
        }

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        detailedPresenter.detachView();
        binding = null;
        super.onDestroyView();
    }

    @Override
    public void showNewsDetailed(String urlNewsDetailed) {
        binding.webViewDetailed.loadUrl(urlNewsDetailed);
    }

    @Override
    public void showLoading(boolean showProgress) {
        if (showProgress) {
            binding.progressDetailed.progressLayout.setVisibility(View.VISIBLE);
        } else {
            binding.progressDetailed.progressLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), "Something went wrong...", Toast.LENGTH_SHORT).show();
    }
}
