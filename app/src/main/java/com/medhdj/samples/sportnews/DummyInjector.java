package com.medhdj.samples.sportnews;

import android.content.Context;

import com.medhdj.samples.data.entity.mapper.NewsEntityMapper;
import com.medhdj.samples.data.repository.NewsDataRepository;
import com.medhdj.samples.data.repository.datasource.NewsDataSourceFactory;
import com.medhdj.samples.domain.executor.PostExecutionThread;
import com.medhdj.samples.domain.executor.ThreadExecutor;
import com.medhdj.samples.domain.repository.NewsRepository;
import com.medhdj.samples.domain.usecase.LoadNewsFeed;
import com.medhdj.samples.sportnews.executor.AppPostExecutionThread;
import com.medhdj.samples.sportnews.executor.AppThreadPoolExecutor;

//TODO replace with dagger
public class DummyInjector {
    public static LoadNewsFeed getNewsFeedUseCase(Context context) {

        NewsDataSourceFactory newsDataSource = new NewsDataSourceFactory(context);
        NewsEntityMapper newsEntityMapper = new NewsEntityMapper();

        NewsRepository newsRepository = new NewsDataRepository(newsDataSource, newsEntityMapper);

        ThreadExecutor threadPoolExecutor = new AppThreadPoolExecutor();
        PostExecutionThread postExecutionThread = new AppPostExecutionThread();

        return new LoadNewsFeed(newsRepository, threadPoolExecutor, postExecutionThread);
    }
}
