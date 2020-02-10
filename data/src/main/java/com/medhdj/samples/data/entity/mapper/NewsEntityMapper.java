package com.medhdj.samples.data.entity.mapper;

import com.medhdj.samples.data.entity.NewsEntity;
import com.medhdj.samples.data.entity.NewsFeedApiResponse;
import com.medhdj.samples.domain.model.News;
import com.medhdj.samples.domain.model.Story;
import com.medhdj.samples.domain.model.Video;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewsEntityMapper {


    private NewsEntity createVideoNewsEntity(NewsEntity newsEntity) {
        newsEntity.setType(NewsEntity.NEWS_ENTITY_TYPE.VIDEO);
        return newsEntity;
    }

    private NewsEntity createStoryNewsEntity(NewsEntity newsEntity) {
        newsEntity.setType(NewsEntity.NEWS_ENTITY_TYPE.STORY);
        return newsEntity;
    }

    private Story createStory(NewsEntity newsEntity) {
        Story story = new Story(newsEntity.getTimestamp(), newsEntity.getTitle(), newsEntity.getImage(),
                newsEntity.getCategory(), newsEntity.getIntro(), newsEntity.getContent(), newsEntity.getAuthor());
        return story;

    }

    private Video creaVideo(NewsEntity newsEntity) {
        Video video = new Video(newsEntity.getTimestamp(), newsEntity.getTitle(), newsEntity.getImage(),
                newsEntity.getCategory(), newsEntity.getVideoUrl(), newsEntity.getViews());
        return video;
    }

    public List<NewsEntity> transform(NewsFeedApiResponse newsFeedApiResponse) {
        Stream<NewsEntity> storiesStream = newsFeedApiResponse.getStories()
                .stream()
                .map(this::createStoryNewsEntity);

        Stream<NewsEntity> videosStream = newsFeedApiResponse.getVideos()
                .stream()
                .map(this::createVideoNewsEntity);

        return Stream.concat(storiesStream, videosStream).collect(Collectors.toList());
    }


    public News transform(NewsEntity newsEntity) {
        News news = null;
        if (newsEntity != null) {
            if (NewsEntity.NEWS_ENTITY_TYPE.STORY.equals(newsEntity.getType())) {
                news = createStory(newsEntity);
            } else {
                news = creaVideo(newsEntity);
            }
        }
        return news;
    }


    public List<News> transform(Collection<NewsEntity> newsEntityCollection) {
        final List<News> newsList = new ArrayList<>(10);
        for (NewsEntity newsEntity : newsEntityCollection) {
            final News news = transform(newsEntity);
            if (newsEntity != null) {
                newsList.add(news);
            }
        }
        return newsList;
    }
}
