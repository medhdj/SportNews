package com.medhdj.samples.data.entity;

import java.util.List;

/**
 * classe qui represnete la reponse de l'API
 */
public class NewsFeedApiResponse {
    List<NewsEntity> stories;
    List<NewsEntity> videos;

    public List<NewsEntity> getStories() {
        return stories;
    }

    public List<NewsEntity> getVideos() {
        return videos;
    }
}
