package com.medhdj.samples.data.repository;

import com.medhdj.samples.data.entity.mapper.NewsEntityMapper;
import com.medhdj.samples.data.repository.datasource.NewsDataSourceFactory;
import com.medhdj.samples.domain.model.News;
import com.medhdj.samples.domain.repository.NewsRepository;

import java.util.List;

import io.reactivex.Observable;

public class NewsDataRepository implements NewsRepository {
    private NewsDataSourceFactory newsDataStoreFactory;
    private NewsEntityMapper newsEntityMapper;

    public NewsDataRepository(NewsDataSourceFactory newsDataStoreFactory,
                              NewsEntityMapper newsEntityMapper) {
        this.newsDataStoreFactory = newsDataStoreFactory;
        this.newsEntityMapper = newsEntityMapper;

    }

    @Override
    public Observable<List<News>> news() {
        try {
            NewsDataSourceFactory.NewsDataSource newsDataSource = newsDataStoreFactory
                    .createDataSource();
            return newsDataSource.retreiveNewsFeed().map(newsEntities ->
                    newsEntityMapper.transform(newsEntities));
        } catch (UnsupportedOperationException e) {
            return Observable.error(e);
        }
    }
}
