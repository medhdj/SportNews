package com.medhdj.samples.domain.model;

import static com.medhdj.samples.domain.model.News.NEWS_TYPE.STORY;

public class Story extends News {
    String intro;
    String content;
    String author;

    public Story(long timestamp, String title, String image, String category, String intro,
                 String content, String author) {
        super(timestamp, title, image, category,STORY);
        this.intro = intro;
        this.content = content;
        this.author = author;
    }

    public String getIntro() {
        return intro;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }
}
