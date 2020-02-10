package com.medhdj.samples.data.network;

import com.medhdj.samples.data.entity.NewsFeedApiResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

import static com.medhdj.samples.data.network.RestApiClient.NEWS_FEED_ENDPOINT;

public interface NewsFeedRestApi {
    @GET(NEWS_FEED_ENDPOINT)
    Single<NewsFeedApiResponse> getNewsFeed();
}
