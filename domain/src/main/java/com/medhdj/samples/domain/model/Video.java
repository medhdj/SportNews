package com.medhdj.samples.domain.model;

import static com.medhdj.samples.domain.model.News.NEWS_TYPE.VIDEO;

public class Video extends News {
    String videoUrl;
    long views;

    public Video(long timestamp, String title, String image, String category, String videoUrl,
                 long views) {
        super(timestamp, title, image, category, VIDEO);
        this.videoUrl = videoUrl;
        this.views = views;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public long getViews() {
        return views;
    }
}
