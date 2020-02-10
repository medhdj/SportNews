package com.medhdj.samples.domain.usecase;

import com.medhdj.samples.domain.executor.PostExecutionThread;
import com.medhdj.samples.domain.executor.ThreadExecutor;
import com.medhdj.samples.domain.model.News;
import com.medhdj.samples.domain.repository.NewsRepository;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.Observable;

public class LoadNewsFeed extends BaseUseCase<List<News>, Void> {
    NewsRepository newsRepository;

    public LoadNewsFeed(NewsRepository newsRepository, ThreadExecutor threadExecutor,
                        PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.newsRepository = newsRepository;
    }

    @Override
    Observable<List<News>> buildUseCaseObservable(Void aVoid) {
        return newsRepository.news().map(news -> news.stream()
                .sorted((news1, news2) -> Long.compare(news2.getTimestamp(), news1.getTimestamp()))
                .collect(Collectors.toList()));
    }
}
