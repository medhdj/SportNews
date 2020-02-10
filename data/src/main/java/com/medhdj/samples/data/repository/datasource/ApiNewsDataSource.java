package com.medhdj.samples.data.repository.datasource;

import com.medhdj.samples.data.entity.NewsEntity;
import com.medhdj.samples.data.entity.mapper.NewsEntityMapper;
import com.medhdj.samples.data.network.NewsFeedRestApi;

import java.util.List;

import io.reactivex.Observable;

public class ApiNewsDataSource implements NewsDataSourceFactory.NewsDataSource {

    private final NewsFeedRestApi newsFeedRestApi;
    private final NewsEntityMapper newsEntityMapper;

    public ApiNewsDataSource(NewsFeedRestApi newsFeedRestApi, NewsEntityMapper newsEntityMapper) {
        this.newsFeedRestApi = newsFeedRestApi;
        this.newsEntityMapper = newsEntityMapper;
    }

    @Override
    public Observable<List<NewsEntity>> retreiveNewsFeed() {
        return newsFeedRestApi.getNewsFeed()
                .toObservable().map(newsFeedApiResponse -> newsEntityMapper.transform(newsFeedApiResponse));
    }
}
