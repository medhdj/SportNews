package com.medhdj.samples.sportnews.ui.home;

import android.util.Log;

import com.medhdj.samples.domain.model.News;
import com.medhdj.samples.domain.usecase.LoadNewsFeed;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.observers.DisposableObserver;


public class HomeViewModel extends ViewModel {

    private final LoadNewsFeed loadNewsFeed;

    MutableLiveData newsFeed = new MutableLiveData<List<News>>();
    MutableLiveData isLoading = new MutableLiveData<Boolean>();

    public HomeViewModel(LoadNewsFeed loadNewsFeed) {
        this.loadNewsFeed = loadNewsFeed;
    }


    public void initialize() {
        isLoading.postValue(true);
        loadNewsFeed.execute(new NewsListObserver(), null);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        loadNewsFeed.dispose();
    }

    private final class NewsListObserver extends DisposableObserver<List<News>> {

        @Override
        public void onNext(List<News> news) {
            Log.d("HomeViewModel.NewsListObserver", "onNext: " + news.toString());
            newsFeed.postValue(news);
            isLoading.postValue(false);
        }

        @Override
        public void onError(Throwable e) {
            Log.e("HomeViewModel.NewsListObserver", "onError: " + e.getMessage());
            e.printStackTrace();
            isLoading.postValue(false);
        }

        @Override
        public void onComplete() {
            Log.d("HomeViewModel.NewsListObserver", "onComplete");
            isLoading.postValue(false);
        }
    }

}
