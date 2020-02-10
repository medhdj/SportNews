package com.medhdj.samples.domain.model;

public class News {
    public enum NEWS_TYPE {
        VIDEO, STORY
    }

    long timestamp;
    String title;
    String image;
    String category;
    NEWS_TYPE newsType;

    public News(long timestamp, String title, String image, String category, NEWS_TYPE news_type) {
        this.timestamp = timestamp;
        this.title = title;
        this.image = image;
        this.category = category;
        this.newsType = news_type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }

    public NEWS_TYPE getNewsType() {
        return newsType;
    }
}
