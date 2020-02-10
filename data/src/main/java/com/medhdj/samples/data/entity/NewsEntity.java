package com.medhdj.samples.data.entity;

public class NewsEntity {
    public enum NEWS_ENTITY_TYPE {
        VIDEO,
        STORY
    }

    NEWS_ENTITY_TYPE type;
    long timestamp;
    String title;
    String image;
    String category;
    String videoUrl;
    long views;
    String intro;
    String content;
    String author;

    public NewsEntity(NEWS_ENTITY_TYPE type, long timestamp, String title, String image,
                      String category, String videoUrl, long views,
                      String intro, String content, String author) {
        this.type = type;
        this.timestamp = timestamp;
        this.title = title;
        this.image = image;
        this.category = category;
        this.videoUrl = videoUrl;
        this.views = views;
        this.intro = intro;
        this.content = content;
        this.author = author;
    }

    public NEWS_ENTITY_TYPE getType() {
        return type;
    }

    public void setType(NEWS_ENTITY_TYPE type) {
        this.type = type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
