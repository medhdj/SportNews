package com.medhdj.samples.sportnews.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.medhdj.samples.domain.model.News;
import com.medhdj.samples.domain.usecase.LoadNewsFeed;
import com.medhdj.samples.sportnews.DummyInjector;
import com.medhdj.samples.sportnews.R;
import com.medhdj.samples.sportnews.common.ViewModelFactory;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment {

    LoadNewsFeed loadNewsFeedUseCase;
    HomeViewModel homeViewModel;

    ProgressBar progressBar;
    RecyclerView newsRv;

    NewsFeedListAdapter newsFeedListAdapter;


    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(view);

        loadNewsFeedUseCase = DummyInjector.getNewsFeedUseCase(getContext());
        bindViewModel();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        loadNewsFeedUseCase.dispose();
    }


    private void bindViewModel() {
        ViewModelFactory factoryViewModel = new ViewModelFactory(loadNewsFeedUseCase);
        homeViewModel = factoryViewModel.create(HomeViewModel.class);
        observe();
        homeViewModel.initialize();
    }

    private void observe() {
        homeViewModel.newsFeed.observe(this, (Observer<List<News>>) news -> {
            newsFeedListAdapter.notifyAdapter(news);
        });
        homeViewModel.isLoading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void initViews(View view) {
        newsRv = view.findViewById(R.id.news_rv);
        newsRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        newsFeedListAdapter = new NewsFeedListAdapter(getContext());
        newsRv.setAdapter(newsFeedListAdapter);

        progressBar = view.findViewById(R.id.progressBar);
    }
}
