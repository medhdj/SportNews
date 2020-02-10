package com.medhdj.samples.data.repository;

import com.medhdj.samples.data.TestData;
import com.medhdj.samples.data.entity.mapper.NewsEntityMapper;
import com.medhdj.samples.data.repository.datasource.ApiNewsDataSource;
import com.medhdj.samples.data.repository.datasource.NewsDataSourceFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NewsDataRepositoryTest {


    private NewsDataRepository newsDataRepository;

    @Mock
    private NewsDataSourceFactory mockNewsDataSourceFactory;
    @Mock
    private NewsEntityMapper mockNewsEntityMapper;
    @Mock
    private ApiNewsDataSource mockApiNewsDataSource;

    @Before
    public void setUp() {
        newsDataRepository = new NewsDataRepository(mockNewsDataSourceFactory, mockNewsEntityMapper);
        given(mockNewsDataSourceFactory.createDataSource()).willReturn(mockApiNewsDataSource);
    }

    @Test
    public void testGetNews() {
        when(mockApiNewsDataSource.retreiveNewsFeed()).thenReturn(TestData.newsListObservable);

        newsDataRepository.news();

        verify(mockNewsDataSourceFactory).createDataSource();
        verify(mockApiNewsDataSource).retreiveNewsFeed();
    }
}