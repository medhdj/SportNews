package com.medhdj.samples.domain.usecase;

import com.medhdj.samples.domain.TestData;
import com.medhdj.samples.domain.executor.PostExecutionThread;
import com.medhdj.samples.domain.executor.ThreadExecutor;
import com.medhdj.samples.domain.model.News;
import com.medhdj.samples.domain.repository.NewsRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoadNewsFeedTest {


    private LoadNewsFeed loadNewsFeed;

    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;
    @Mock
    private NewsRepository mockNewsRepository;


    @Before
    public void setUp() {
        loadNewsFeed = new LoadNewsFeed(mockNewsRepository, mockThreadExecutor,
                mockPostExecutionThread);
    }

    @Test
    public void testUseOfRepositories() {
        when(mockNewsRepository.news()).thenReturn(TestData.newsObservableMock);

        loadNewsFeed.buildUseCaseObservable(null);

        verify(mockNewsRepository).news();
    }

    @Test
    public void testNewsSorted() {
        when(mockNewsRepository.news()).thenReturn(TestData.newsObservableMock);

        TestObserver<List<News>> newsTestObserver = new TestObserver<>();
        loadNewsFeed.buildUseCaseObservable(null)
                .subscribe(newsTestObserver);

        newsTestObserver.assertValue(news ->
                news.get(0).getTimestamp() == TestData.videos.get(1).getTimestamp());
    }

}